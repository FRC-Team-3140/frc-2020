package frc.libs;

import java.io.IOException;
import java.nio.file.Path;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Filesystem;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.controller.RamseteController;
import edu.wpi.first.wpilibj.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.wpilibj.trajectory.Trajectory;
import edu.wpi.first.wpilibj.trajectory.TrajectoryUtil;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.RamseteCommand;

import frc.robot.RobotContainer;
import frc.robot.Constants.AutoConstants;
import frc.robot.Constants.DriveConstants;
import frc.robot.commands.auto.DoNothingAuto;
import frc.robot.commands.otherCommands.DesiredPose_SMDB_Sender;

public class TrajectoryFollower {
        public static Command makeFollowingCommandForAuto(String fileName, int timeout) {
                // fileName e.x. "AroundPostTest.wpilib.json";
                // timeout e.x. 15s max for Auto (Amount of time in seconds that can pass from
                // start of comman until it automatically cancels)
                String filePath = "output/".concat(fileName);

                try {
                        // Import trajectory
                        Path trajectoryPath = Filesystem.getDeployDirectory().toPath().resolve(filePath);
                        Trajectory trajectory = TrajectoryUtil.fromPathweaverJson(trajectoryPath);

                        // Create a generic zeroed robot pose to set the path relative to.
                        // This is done so we can preemptively import the paths/
                        // The robot will have to be zeroed before this auto command is run
                        // in order to be consistent with this assumption which we have modified
                        // the path relative to.
                        DifferentialDriveOdometry odometry = new DifferentialDriveOdometry(Rotation2d.fromDegrees(0));
                        Pose2d zeroedPose = odometry.getPoseMeters();

                        // Make path relative to a zeroed robot
                        var transform = zeroedPose.minus(trajectory.getInitialPose());
                        Trajectory robotRelativeTrajectory = trajectory.transformBy(transform);

                        // Build RamseteCommand, this command follows the trajectory in auto.
                        RamseteCommand ramseteCommand = new RamseteCommand(robotRelativeTrajectory,
                                        RobotContainer.dt::getCurrentPose,
                                        new RamseteController(AutoConstants.kRamseteB, AutoConstants.kRamseteZeta),
                                        new SimpleMotorFeedforward(DriveConstants.ksVolts,
                                                        DriveConstants.kvVoltSecondsPerMeter,
                                                        DriveConstants.kaVoltSecondsSquaredPerMeter),
                                        DriveConstants.kDriveKinematics, RobotContainer.dt::getWheelSpeeds,
                                        new PIDController(DriveConstants.kPDriveVel, DriveConstants.kIDriveVel,
                                                        DriveConstants.kDDriveVel),
                                        new PIDController(DriveConstants.kPDriveVel, DriveConstants.kIDriveVel,
                                                        DriveConstants.kDDriveVel),
                                        // RamseteCommand passes volts to the callback
                                        RobotContainer.dt::tankDriveVolts, RobotContainer.dt);

                        // 1. Reset sensors
                        // 2. Run Path Forward until timeout expires,
                        // In parallel update SMDB until Path is completed or timeout expires
                        // 3. Stop driving at end of path.
                        return new InstantCommand(() -> RobotContainer.dt.resetAll()).andThen(ramseteCommand
                                        .withTimeout(timeout)
                                        .deadlineWith(new DesiredPose_SMDB_Sender(robotRelativeTrajectory))
                                        .andThen(() -> RobotContainer.dt.tankDriveVolts(0, 0)));
                } catch (IOException ex) {
                        DriverStation.reportError("Unable to open trajectory: ", ex.getStackTrace());
                        return new DoNothingAuto();
                }

        }
}
