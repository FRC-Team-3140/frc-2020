package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import frc.robot.commands.drivetrain.Drive;
import frc.robot.commands.pneumatics.shifter.ShiftDown;
import frc.robot.commands.pneumatics.shifter.ShiftUp;
import frc.robot.commands.servo.MoveServo;
import frc.robot.commands.servo.StopServo;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Pneumatics;
import frc.robot.subsystems.Servo;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.libs.*;

/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and OI devices
  public static final XboxController xbox = new XboxController(0);

  public static final Drivetrain dt = new Drivetrain();
  public static final Pneumatics pn = new Pneumatics();
  public static final Servo sr = new Servo();

  public RobotContainer() {
    configureButtonBindings();
    configureDefaultCommands();
  }

  /**
   * Use this method to define your button->command mappings.  Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    xbox.leftBumper.whenPressed(new ShiftUp());
    xbox.leftBumper.whenReleased(new ShiftDown());
    xbox.rightTrigger.whenPressed(new MoveServo());
    xbox.rightTrigger.whenReleased(new StopServo());
  }

  private void configureDefaultCommands() {
    dt.setDefaultCommand(new Drive());
  }


  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    return new WaitCommand(15);
  }
}
