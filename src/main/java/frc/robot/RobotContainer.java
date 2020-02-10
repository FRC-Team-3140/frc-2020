package frc.robot;

import java.io.IOException;
import java.nio.file.Paths;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.controller.RamseteController;
import edu.wpi.first.wpilibj.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.wpilibj.trajectory.Trajectory;
import edu.wpi.first.wpilibj.trajectory.TrajectoryUtil;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RamseteCommand;

import frc.robot.Constants.AutoConstants;
import frc.robot.Constants.DriveConstants;
import frc.robot.Constants.OIConstants;
import frc.robot.commands.drivetrain.Drive;
import frc.robot.commands.otherCommands.DesiredPose_SMDB_Sender;
import frc.robot.subsystems.Drivetrain;
import frc.libs.ReversedTrajectory;
import frc.libs.XboxController;

public class RobotContainer {
  public static final Drivetrain dt = new Drivetrain();
  public static final XboxController xbox = new XboxController(OIConstants.xboxPort);

  public RobotContainer() {
    configureButtonBindings();
    configureDefaultCommands();
  }

  private void configureButtonBindings() {
  }

  private void configureDefaultCommands() {
    dt.setDefaultCommand(new Drive());
  }

  public Command getAutonomousCommand() throws IOException {    
    //Import trajectory
    Trajectory trajectory = TrajectoryUtil
        .fromPathweaverJson(Paths.get("/home/lvuser/deploy/Around Post.wpilib.json"));//"/home/lvuser/deploy/TestPath.wpilib.json"));
    
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
    //ReversedTrajectory reversedRobotRelativeTrajectory = new ReversedTrajectory(robotRelativeTrajectory.getStates());

    // Build RamseteCommand, this command follows the trajectory in auto.
    RamseteCommand ramseteCommand = new RamseteCommand(robotRelativeTrajectory, dt::getCurrentPose,
        new RamseteController(AutoConstants.kRamseteB, AutoConstants.kRamseteZeta),
        new SimpleMotorFeedforward(DriveConstants.ksVolts, DriveConstants.kvVoltSecondsPerMeter,
            DriveConstants.kaVoltSecondsSquaredPerMeter),
        DriveConstants.kDriveKinematics, dt::getWheelSpeeds,
        new PIDController(DriveConstants.kPDriveVel, DriveConstants.kIDriveVel, DriveConstants.kDDriveVel),
        new PIDController(DriveConstants.kPDriveVel, DriveConstants.kIDriveVel, DriveConstants.kDDriveVel),
        // RamseteCommand passes volts to the callback
        dt::tankDriveVolts, dt);
/*
    // Build Reverse RamseteCommand, this command follows the trajectory backwards in auto.
    RamseteCommand reversedRamseteCommand = new RamseteCommand(reversedRobotRelativeTrajectory, dt::getCurrentPose,
        new RamseteController(AutoConstants.kRamseteB, AutoConstants.kRamseteZeta),
        new SimpleMotorFeedforward(DriveConstants.ksVolts, DriveConstants.kvVoltSecondsPerMeter,
            DriveConstants.kaVoltSecondsSquaredPerMeter),
        DriveConstants.kDriveKinematics, dt::getWheelSpeeds,
        new PIDController(DriveConstants.kPDriveVel, DriveConstants.kIDriveVel, DriveConstants.kDDriveVel),
        new PIDController(DriveConstants.kPDriveVel, DriveConstants.kIDriveVel, DriveConstants.kDDriveVel),
        // RamseteCommand passes volts to the callback
        dt::tankDriveVolts, dt);
*/
    return 
    // Run Path Forward, In parallel update SMDB, then stop driving at end of path.
    ramseteCommand.deadlineWith(new DesiredPose_SMDB_Sender(robotRelativeTrajectory)).andThen(() -> dt.tankDriveVolts(0, 0));
    // Then Run Path Backwards, In parallel update SMDB, then stop driving at end of path.
    //.andThen(reversedRamseteCommand.deadlineWith(new DesiredPose_SMDB_Sender(reversedRobotRelativeTrajectory)).andThen(() -> dt.tankDriveVolts(0, 0)));
  }
}
