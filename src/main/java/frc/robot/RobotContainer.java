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
import frc.robot.subsystems.Drivetrain;

import frc.libs.XboxController;

public class RobotContainer {
  public static final Drivetrain dt = new Drivetrain();
  public static final XboxController xbox = new XboxController(OIConstants.xboxPort);

  public RobotContainer() {
    configureButtonBindings();
    configureDefaultCommands();
  }

  private void configureButtonBindings() {
    // Drive at half speed when the right bumper is held
    xbox.rightBumper.whenPressed(() -> dt.setMaxOutput(0.5));
    xbox.rightBumper.whenPressed(() -> dt.setMaxOutput(1));
  }

  private void configureDefaultCommands() {
    dt.setDefaultCommand(new Drive());
  }

  public Command getAutonomousCommand() throws IOException {
    Trajectory trajectory = TrajectoryUtil
        .fromPathweaverJson(Paths.get("/home/lvuser/deploy/TestPath.wpilib.json"));

    RamseteCommand ramseteCommand = new RamseteCommand(trajectory, dt::getPose,
        new RamseteController(AutoConstants.kRamseteB, AutoConstants.kRamseteZeta),
        new SimpleMotorFeedforward(DriveConstants.ksVolts, DriveConstants.kvVoltSecondsPerMeter,
            DriveConstants.kaVoltSecondsSquaredPerMeter),
        DriveConstants.kDriveKinematics, dt::getWheelSpeeds,
        new PIDController(DriveConstants.kPDriveVel, DriveConstants.kIDriveVel, DriveConstants.kDDriveVel),
        new PIDController(DriveConstants.kPDriveVel, DriveConstants.kIDriveVel, DriveConstants.kDDriveVel),
        // RamseteCommand passes volts to the callback
        dt::tankDriveVolts, dt);

    // Run path following command, then stop at the end.
    return ramseteCommand.andThen(() -> dt.tankDriveVolts(0, 0));
  }
}
