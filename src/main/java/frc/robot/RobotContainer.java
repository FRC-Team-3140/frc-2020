package frc.robot;

import java.io.IOException;
import java.nio.file.Paths;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.controller.RamseteController;
import edu.wpi.first.wpilibj.controller.SimpleMotorFeedforward;
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

import frc.libs.XboxController;

public class RobotContainer {
  public static final Drivetrain dt = new Drivetrain();
  public static final XboxController xbox = new XboxController(OIConstants.xboxPort);

  // TODO: Add Deadband to Xbox Controller
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
    dt.resetAll();
    
    Trajectory trajectory = TrajectoryUtil
        .fromPathweaverJson(Paths.get("/home/lvuser/deploy/TestPath.wpilib.json"));
    
    // Need to add this??
    var transform = dt.getCurrentPose().minus(trajectory.getInitialPose());
    Trajectory newTrajectory = trajectory.transformBy(transform);
    // Same thing, but more efficient computation?
    // Trajectory newTrajectory = trajectory.relativeTo(dt.getCurrentPose());

    RamseteCommand ramseteCommand = new RamseteCommand(newTrajectory, dt::getCurrentPose,
        new RamseteController(AutoConstants.kRamseteB, AutoConstants.kRamseteZeta),
        new SimpleMotorFeedforward(DriveConstants.ksVolts, DriveConstants.kvVoltSecondsPerMeter,
            DriveConstants.kaVoltSecondsSquaredPerMeter),
        DriveConstants.kDriveKinematics, dt::getWheelSpeeds,
        new PIDController(DriveConstants.kPDriveVel, DriveConstants.kIDriveVel, DriveConstants.kDDriveVel),
        new PIDController(DriveConstants.kPDriveVel, DriveConstants.kIDriveVel, DriveConstants.kDDriveVel),
        // RamseteCommand passes volts to the callback
        dt::tankDriveVolts, dt);

    // Run path following command, then stop at the end.
    return ramseteCommand.deadlineWith(new DesiredPose_SMDB_Sender(newTrajectory)).andThen(() -> dt.tankDriveVolts(0, 0));
  }
}
