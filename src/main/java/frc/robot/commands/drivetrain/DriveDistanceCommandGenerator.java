package frc.robot.commands.drivetrain;

import java.util.ArrayList;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.controller.RamseteController;
import edu.wpi.first.wpilibj.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.geometry.Translation2d;
import edu.wpi.first.wpilibj.trajectory.Trajectory;
import edu.wpi.first.wpilibj.trajectory.TrajectoryConfig;
import edu.wpi.first.wpilibj.trajectory.TrajectoryGenerator;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.RamseteCommand;

import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.commands.otherCommands.DesiredPose_SMDB_Sender;

public class DriveDistanceCommandGenerator implements Constants.ControllerConstants.General, Constants.ControllerConstants.DriveTrain.TrajectoryFollowing {
    private double distance = 0;

    // Pass the desired distance to this class in meters.
    // Positive or negative, negative will drive backwards from current pose
    public DriveDistanceCommandGenerator(double distance) {
        this.distance = distance;
    }

    public Command getCommand() {
        var startingPose = new Pose2d(new Translation2d(0, 0), new Rotation2d(0));
        var endingPose = new Pose2d(new Translation2d(Math.abs(distance), 0), new Rotation2d(0));
        var interiorWaypoints = new ArrayList<Translation2d>();

        TrajectoryConfig config = new TrajectoryConfig(trajectoryMaxVelocity,
                trajectoryMaxAcceleration);

        Trajectory trajectory = TrajectoryGenerator.generateTrajectory(startingPose, interiorWaypoints, endingPose,
                config);

        // Build RamseteCommand, this command follows the trajectory in auto.
        RamseteCommand ramseteCommand = new RamseteCommand(trajectory, RobotContainer.dt::getCurrentPose,
                new RamseteController(kRamseteB, kRamseteZeta),
                new SimpleMotorFeedforward(ksVolts, kvVoltSecondsPerMeter,
                        kaVoltSecondsSquaredPerMeter),
                kDriveKinematics, RobotContainer.dt::getWheelSpeeds,
                new PIDController(kPDriveVel, kIDriveVel, kDDriveVel),
                new PIDController(kPDriveVel, kIDriveVel, kDDriveVel),
                // RamseteCommand passes volts to the callback
                RobotContainer.dt::tankDriveVolts, RobotContainer.dt);

        // 1. Reset sensors
        // 2. Run Path
        // In parallel update SMDB until Path is completed
        // 3. Stop driving at end of path.
        if (distance < 0) {
            return new InstantCommand(() -> RobotContainer.dt.resetAll())
                    .andThen(() -> RobotContainer.dt.setTrajectoryReversed(true))
                    .andThen(ramseteCommand.deadlineWith(new DesiredPose_SMDB_Sender(trajectory))
                            .andThen(() -> RobotContainer.dt.setTrajectoryReversed(false))
                            .andThen(() -> RobotContainer.dt.tankDriveVolts(0, 0)));
        } else {
            return new InstantCommand(() -> RobotContainer.dt.resetAll())
                    .andThen(ramseteCommand.deadlineWith(new DesiredPose_SMDB_Sender(trajectory))
                            .andThen(() -> RobotContainer.dt.tankDriveVolts(0, 0)));
        }
    }
}