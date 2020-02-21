package frc.robot;

import frc.robot.commands.angledHood.AngleWithJoystick;
import frc.robot.subsystems.Hood;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.libs.*;

public class RobotContainer implements Constants.ElectricalPortConstants {
  // The robot's subsystems and OI devices
  public static final XboxController xbox = new XboxController(xboxPrimaryDriver);

  public static final Hood hd = new Hood();

  public RobotContainer() {
    configureButtonBindings();
    configureDefaultCommands();
  }

  private void configureButtonBindings() {
  }

  private void configureDefaultCommands() {
    hd.setDefaultCommand(new AngleWithJoystick());
  }

  public Command getAutonomousCommand() {
    return new WaitCommand(15);
  }
}
