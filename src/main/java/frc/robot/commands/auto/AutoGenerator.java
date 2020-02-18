package frc.robot.commands.auto;

import java.io.IOException;
import java.nio.file.Path;

import com.revrobotics.CANSparkMax.IdleMode;

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
import frc.robot.commands.blankCommand;

public class AutoGenerator {
    private Command doNothingAuto;
    private Command driveStraightAuto;
    private Command threeBallAuto;
    private Command fiveBallAuto;
    private Command eightBallAuto;
    private Command tenBallAuto;

    public AutoGenerator() {
        makeDoNothingAuto();
        makeDriveStraightAuto();
        makeThreeBallAuto();
        makeFiveBallAuto();
        makeEightBallAuto();
        makeTenBallAuto();
    }

    // Build auto's
    private void makeDoNothingAuto() {
        doNothingAuto = new DoNothingAuto();
    }

    private void makeDriveStraightAuto() {
        driveStraightAuto = new DoNothingAuto();
    }

    private void makeThreeBallAuto() {
        threeBallAuto = new DoNothingAuto();
    }

    private void makeFiveBallAuto() {
        fiveBallAuto = new DoNothingAuto();
    }

    private void makeEightBallAuto() {
        eightBallAuto = new blankCommand().andThen(() -> RobotContainer.dt.setTrajectoryReversed(true))
                .andThen(makeFollowingCommandForAuto("Side_to_Ball_Pickup.wpilib.json"))
                .andThen(() -> RobotContainer.dt.setTrajectoryReversed(false))
                .andThen(makeFollowingCommandForAuto("Ball_Pickup_to_Initialization_Line.wpilib.json"))
                .andThen(makeFollowingCommandForAuto("RightSideInitializationLine_to_ShootingLocation.wpilib.json"))
                .andThen(makeFollowingCommandForAuto("ShootingLocation_to_CollectBallsFromControlPanel.wpilib.json"))
                .andThen(() -> RobotContainer.dt.setTrajectoryReversed(true))
                .andThen(makeFollowingCommandForAuto("replacentTest.wpilib.json"));
        // TODO:
        // Brake Mode On
        // TrajectoryFollowing Drive to Shooting Location
        // In Parallel Home and Rotate Shooter to 90deg.
        // TrajectoryFollowing HoldPosition
        // In Parallel shoot with vision
        // End Shooting and HoldPosition Off
        // Home and Stow Shooter
        // In Parallel TrajectoryFollowing to Ball Pickup
        // In Parallel Time Delay then Intake On
        // Intake Off
        // TrajectoryFollowing to Shooting Location
        // In Parallel Home and Rotate Shooter to 90deg.
        // TrajectoryFollowing HoldPosition
        // In Parallel shoot with vision
        // End Shooting and HoldPosition Off
        // Home and Stow Shooter
        // Brake Mode Off
    }

    public void makeTenBallAuto() {
        // RightSideInitForward_To_BallPickup1
        // BallPickup1_To_Shooting_Location
        // Shooting_Location_To_BallPickup2
        // BallPickup2_To_BallPickup3
        // BallPickup3_To_ShootingLocation
        tenBallAuto = new blankCommand().andThen(() -> RobotContainer.dt.setIdleMode(IdleMode.kBrake))
            .andThen(() -> RobotContainer.dt.setTrajectoryReversed(false))
            .andThen(makeFollowingCommandForAuto("RightSideInitForward_To_BallPickup1.wpilib.json"))
            .andThen(() -> RobotContainer.dt.setTrajectoryReversed(true))
            .andThen(makeFollowingCommandForAuto("BallPickup1_To_Shooting_Location.wpilib.json"))
            .andThen(() -> RobotContainer.dt.setTrajectoryReversed(false))
            .andThen(makeFollowingCommandForAuto("Shooting_Location_To_BallPickup2.wpilib.json"))
            .andThen(makeFollowingCommandForAuto("BallPickup2_To_BallPickup3.wpilib.json"))
            .andThen(() -> RobotContainer.dt.setTrajectoryReversed(true))
            .andThen(makeFollowingCommandForAuto("BallPickup3_To_ShootingLocation.wpilib.json"))
            .andThen(() -> RobotContainer.dt.setIdleMode(IdleMode.kCoast));
    }

    // Make auto's accessible
    public Command getDoNothingAuto() {
        return doNothingAuto;
    }

    public Command getDriveStraightAuto() {
        return driveStraightAuto;
    }

    public Command getThreeBallAuto() {
        return threeBallAuto;
    }

    public Command getFiveBallAuto() {
        return fiveBallAuto;
    }

    public Command getEightBallAuto() {
        return eightBallAuto;
    }

    public Command getTenBallAuto() {
        return tenBallAuto;
    }

    // Generates commands that follow trajectories
    public Command makeFollowingCommandForAuto(String fileName) {
        // fileName e.x. "AroundPostTest.wpilib.json";
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
                    new SimpleMotorFeedforward(DriveConstants.ksVolts, DriveConstants.kvVoltSecondsPerMeter,
                            DriveConstants.kaVoltSecondsSquaredPerMeter),
                    DriveConstants.kDriveKinematics, RobotContainer.dt::getWheelSpeeds,
                    new PIDController(DriveConstants.kPDriveVel, DriveConstants.kIDriveVel, DriveConstants.kDDriveVel),
                    new PIDController(DriveConstants.kPDriveVel, DriveConstants.kIDriveVel, DriveConstants.kDDriveVel),
                    // RamseteCommand passes volts to the callback
                    RobotContainer.dt::tankDriveVolts, RobotContainer.dt);

            // 1. Reset sensors
            // 2. Run Path Forward
            // In parallel update SMDB until Path is completed
            // 3. Stop driving at end of path.
            return new InstantCommand(() -> RobotContainer.dt.resetAll())
                    .andThen(ramseteCommand.deadlineWith(new DesiredPose_SMDB_Sender(robotRelativeTrajectory))
                            .andThen(() -> RobotContainer.dt.tankDriveVolts(0, 0)));
        } catch (IOException ex) {
            DriverStation.reportError("Unable to open trajectory: " + fileName, ex.getStackTrace());
            return new DoNothingAuto();
        }

    }
}