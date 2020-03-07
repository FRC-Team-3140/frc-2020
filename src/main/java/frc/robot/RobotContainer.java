package frc.robot;

import frc.robot.commands.climber.ClimbJoystick;
import frc.robot.subsystems.Climber;
import frc.robot.commands.auto.DoNothingAuto;

import frc.libs.*;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj2.command.Command;

public class RobotContainer implements Constants.ElectricalPortConstants {
  // The robot's subsystems and OI devices
  public static final Climber cl = new Climber();
 
  private static final SendableChooser<Command> chooser = new SendableChooser<>();

  // Xbox controllers
  public static final XboxController xbox = new XboxController(xboxPrimaryDriver);
  public static final XboxController xbox2 = new XboxController(xboxSecondaryDriver);

  public RobotContainer() {
    chooser.setName("Please Select and Auto"); // (this works; find alternatives)
    chooser.addOption("Do Nothing", new DoNothingAuto());
    

    Shuffleboard.getTab("Selector").add(chooser);

    configureButtonBindings();
    configureDefaultCommands();
  }

  private void configureButtonBindings() {
    // Primary Driver Controls
  }

  private void configureDefaultCommands() {
    cl.setDefaultCommand(new ClimbJoystick());
  }

  public Command getAutonomousCommand() {
    return chooser.getSelected();
  }
}
