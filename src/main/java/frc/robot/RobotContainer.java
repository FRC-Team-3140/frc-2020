package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import frc.robot.commands.climber.ExtendClimber;
import frc.robot.commands.climber.RetractClimber;
import frc.robot.commands.drivetrain.Drive;
import frc.robot.commands.pneumatics.intake.DeployIntake;
import frc.robot.commands.pneumatics.intake.RetractIntake;
import frc.robot.subsystems.Climber;
import frc.robot.commands.angledHood.AngleWithJoystick;
import frc.robot.commands.intake.SpinIntakeIn;
import frc.robot.commands.intake.SpinIntakeOff;
import frc.robot.commands.turret.AngleWithTurret;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Feeder;
import frc.robot.subsystems.Flywheel;
import frc.robot.subsystems.Hood;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Pneumatics;
import frc.robot.subsystems.Turret;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.libs.*;

/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a "declarative" paradigm, very little robot logic should
 * actually be handled in the {@link Robot} periodic methods (other than the
 * scheduler calls). Instead, the structure of the robot (including subsystems,
 * commands, and button mappings) should be declared here.
 */
public class RobotContainer implements Constants.ElectricalPortConstants {
  // The robot's subsystems and OI devices
  public static final XboxController xbox = new XboxController(xboxPrimaryDriver);
  public static final XboxController xbox2 = new XboxController(xboxSecondaryDriver);

  public static final Drivetrain dt = new Drivetrain();
  public static final Pneumatics pn = new Pneumatics();
  public static final Climber cl = new Climber();
  public static final Intake in = new Intake();
  public static final Flywheel fw = new Flywheel();
  public static final Feeder fd = new Feeder();
  public static final Hood hd = new Hood();
  public static final Turret tr = new Turret();

  public RobotContainer() {
    configureButtonBindings();
    configureDefaultCommands();
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be
   * created by instantiating a {@link GenericHID} or one of its subclasses
   * ({@link edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then
   * passing it to a {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    // xbox.leftBumper.whileHeld(new HoldPositionController);
    // xbox.rightBumper.whenPressed(() -> dt.setIdleMode(IdleMode.kBrake));
    // xbox.rightBumper.whenReleased(() -> dt.setIdleMode(dt.getPreviousIdleMode()));


    xbox2.leftBumper.whenPressed(new DeployIntake().alongWith(new SpinIntakeIn()));
    xbox2.leftBumper.whenReleased(new RetractIntake().alongWith(new SpinIntakeOff()));

    // xbox2 right bumper, dump balls

    xbox2.y.whileHeld(new ExtendClimber());
    xbox2.a.whileHeld(new RetractClimber());
  
    // xbox2 x automated shooting
    // xbox2 b manual shooting

  }

  private void configureDefaultCommands() {
    dt.setDefaultCommand(new Drive());
    hd.setDefaultCommand(new AngleWithJoystick());
    tr.setDefaultCommand(new AngleWithTurret());
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
