package frc.robot;

import frc.robot.subsystems.Flywheel;
import frc.robot.subsystems.Hood;
import frc.robot.subsystems.Turret;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.libs.*;

public class RobotContainer implements Constants.ElectricalPortConstants {
  // The robot's subsystems and OI devices
  public static final XboxController xbox = new XboxController(xboxPrimaryDriver);
  public static final XboxController xbox2 = new XboxController(xboxSecondaryDriver);

  public static final Flywheel fw = new Flywheel();
  public static final Hood hd = new Hood();
  public static final Turret tr = new Turret();

  public RobotContainer() {
    configureButtonBindings();
    configureDefaultCommands();
  }

  private void configureButtonBindings() {
  }

  private void configureDefaultCommands() {
  }

  public Command getAutonomousCommand() {
    return new WaitCommand(15);
  }
}
