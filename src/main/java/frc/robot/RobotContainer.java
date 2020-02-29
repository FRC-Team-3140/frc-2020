package frc.robot;

import frc.robot.commands.climber.ClimberOff;
import frc.robot.commands.climber.ExtendClimber;
import frc.robot.commands.climber.RetractClimber;
import frc.robot.commands.drivetrain.Drive;
import frc.robot.commands.drivetrain.DriveDistanceCommandGenerator;
import frc.robot.commands.drivetrain.HoldPositionController;
import frc.robot.commands.drivetrain.ReducedSpeedTurningDrive;
import frc.robot.commands.drivetrain.TimedDrive;
import frc.robot.commands.pneumatics.climber.LockClimber;
import frc.robot.commands.pneumatics.climber.UnlockClimber;
import frc.robot.subsystems.Climber;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Pneumatics;
import frc.libs.*;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.WaitCommand;

public class RobotContainer implements Constants.ElectricalPortConstants {
  // The robot's subsystems and OI devices
 
  // Subsystems and class objects used elsewhere in the code
  //  are declared here.
  // All classes required by any class instantiated here must, be instantiated before the instatiated class.
  public static final Drivetrain dt = new Drivetrain();
  public static final Pneumatics pn = new Pneumatics();
  public static final Climber cl = new Climber();

  // Xbox controllers
  public static final XboxController xbox = new XboxController(xboxPrimaryDriver);
  public static final XboxController xbox2 = new XboxController(xboxSecondaryDriver);

  private UsbCamera camera;

  public RobotContainer() {
    camera = CameraServer.getInstance().startAutomaticCapture();
    camera.setFPS(30);
    camera.setResolution(320, 240);

    configureButtonBindings();
    configureDefaultCommands();
  }

  private void configureButtonBindings() {
    // Primary Driver Controls
    //xbox.leftBumper.whileHeld(new HoldPositionController());
    xbox.rightBumper.whileHeld(new ReducedSpeedTurningDrive());

    // Climber
    xbox2.dpadUp.whenPressed(new ExtendClimber());
    xbox2.dpadUp.whenReleased(new ClimberOff());
    xbox2.dpadDown.whenReleased(new ClimberOff());
    xbox2.dpadDown.whenPressed(new RetractClimber());

    // climber piston
    xbox2.select.whenPressed(new LockClimber());
    xbox2.start.whenPressed(new UnlockClimber());
  }

  private void configureDefaultCommands() {
    dt.setDefaultCommand(new Drive());
  }

  public Command getAutonomousCommand() {
    return new TimedDrive(0.5, 0.5);
  }
}
