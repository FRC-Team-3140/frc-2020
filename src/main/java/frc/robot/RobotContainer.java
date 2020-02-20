package frc.robot;

import frc.robot.commands.climber.ExtendClimber;
import frc.robot.commands.climber.RetractClimber;
import frc.robot.commands.drivetrain.Drive;
import frc.robot.commands.feeder.IncrementFeeder;
import frc.robot.commands.feeder.ReverseFeeder;
import frc.robot.commands.feeder.StopInting;
import frc.robot.commands.flywheel.FlywheelShootOff;
import frc.robot.commands.flywheel.FlywheelShootOut;
import frc.robot.commands.pneumatics.intake.DeployIntake;
import frc.robot.commands.pneumatics.intake.RetractIntake;
import frc.robot.subsystems.Climber;
import frc.robot.commands.angledHood.AngleWithJoystick;
import frc.robot.commands.intake.SpinIntakeIn;
import frc.robot.commands.intake.SpinIntakeOff;
import frc.robot.commands.intake.SpinIntakeOut;
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

  private void configureButtonBindings() {
    // xbox.leftBumper.whileHeld(new HoldPositionController);
    // xbox.rightBumper.whenPressed(() -> dt.setIdleMode(IdleMode.kBrake));
    // xbox.rightBumper.whenReleased(() -> dt.setIdleMode(dt.getPreviousIdleMode()));

    // Intake balls
    xbox2.leftBumper.whenPressed(new DeployIntake().alongWith(new SpinIntakeIn()).alongWith(new IncrementFeeder()));
    xbox2.leftBumper.whenReleased(new RetractIntake().alongWith(new SpinIntakeOff()).alongWith(new StopInting()));
    // Dump balls if there's a jam
    xbox2.rightBumper.whenPressed(new DeployIntake().alongWith(new SpinIntakeOut()).alongWith(new ReverseFeeder()));
    xbox2.rightBumper.whenReleased(new RetractIntake().alongWith(new SpinIntakeOff()).alongWith(new StopInting()));

    xbox2.dpadUp.whileHeld(new ExtendClimber());
    xbox2.dpadDown.whileHeld(new RetractClimber());
  
    // xbox2 x automated shooting
    xbox2.b.whileHeld(new FlywheelShootOut());
    xbox2.b.whenReleased(new FlywheelShootOff());
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
