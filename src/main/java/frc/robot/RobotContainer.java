package frc.robot;

import frc.robot.commands.FlywheelFromXBOX;
import frc.robot.subsystems.Flywheel;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.libs.*;

public class RobotContainer implements Constants.ElectricalPortConstants {
  // The robot's subsystems and OI devices
  public static final XboxController xbox = new XboxController(xboxPrimaryDriver);

  public static final Flywheel fw = new Flywheel();

  public RobotContainer() {
    configureButtonBindings();
    configureDefaultCommands();
  }

  private void configureButtonBindings() {
  }

  private void configureDefaultCommands() {
    fw.setDefaultCommand(new FlywheelFromXBOX());
  }

  public Command getAutonomousCommand() {
    return new WaitCommand(15);
  }
}
