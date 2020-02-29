package frc.robot;

import frc.robot.commands.climber.ClimbJoystick;
import frc.robot.commands.climber.ClimberOff;
import frc.robot.commands.climber.ExtendClimber;
import frc.robot.commands.climber.RetractClimber;
import frc.robot.commands.drivetrain.Drive;
import frc.robot.commands.drivetrain.Fix;
import frc.robot.commands.drivetrain.ReducedSpeedTurningDrive;
import frc.robot.commands.drivetrain.TimedDrive;
import frc.robot.commands.pneumatics.climber.LockClimber;
import frc.robot.commands.pneumatics.climber.UnlockClimber;
import frc.robot.subsystems.Climber;
import frc.robot.commands.auto.DoNothingAuto;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Pneumatics;
import frc.libs.*;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj2.command.Command;

public class RobotContainer implements Constants.ElectricalPortConstants {
  // The robot's subsystems and OI devices
  public static final Drivetrain dt = new Drivetrain();
  public static final Pneumatics pn = new Pneumatics();
  public static final Climber cl = new Climber();
 
  private static final SendableChooser<Command> chooser = new SendableChooser<>();

  // Xbox controllers
  public static final XboxController xbox = new XboxController(xboxPrimaryDriver);
  public static final XboxController xbox2 = new XboxController(xboxSecondaryDriver);

  public RobotContainer() {
    chooser.setName("Please Select and Auto"); // (this works; find alternatives)
    chooser.addOption("Do Nothing", new DoNothingAuto());
    chooser.setDefaultOption("Timed Drive", new TimedDrive(0.5, 0.5));

    Shuffleboard.getTab("Selector").add(chooser);

    configureButtonBindings();
    configureDefaultCommands();
  }

  private void configureButtonBindings() {
    // Primary Driver Controls
    xbox.x.whenPressed(new Fix());
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
    cl.setDefaultCommand(new ClimbJoystick());
  }

  public Command getAutonomousCommand() {
    return chooser.getSelected();
  }
}
