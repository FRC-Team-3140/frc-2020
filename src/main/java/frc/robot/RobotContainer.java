package frc.robot;

import frc.robot.commands.climber.ExtendClimber;
import frc.robot.commands.climber.RetractClimber;
import frc.robot.commands.drivetrain.Drive;
import frc.robot.commands.drivetrain.DriveDistanceCommandGenerator;
import frc.robot.commands.drivetrain.HoldPositionController;
import frc.robot.commands.drivetrain.ReducedSpeedTurningDrive;
import frc.robot.commands.drivetrain.TimedDrive;
import frc.robot.commands.feeder.IncrementFeeder;
import frc.robot.commands.feeder.ReverseFeeder;
import frc.robot.commands.feeder.StopInting;
import frc.robot.commands.flywheel.FlywheelShootOff;
import frc.robot.commands.flywheel.FlywheelShootOut;
import frc.robot.commands.pneumatics.climber.LockClimber;
import frc.robot.commands.pneumatics.climber.UnlockClimber;
import frc.robot.commands.pneumatics.intake.DeployIntake;
import frc.robot.commands.pneumatics.intake.RetractIntake;
import frc.robot.subsystems.Climber;
import frc.robot.commands.angledHood.AngleWithJoystick;
import frc.robot.commands.auto.AutoGenerator;
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
import frc.libs.*;

import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj2.command.Command;

public class RobotContainer implements Constants.ElectricalPortConstants {
  // The robot's subsystems and OI devices
 
  // Subsystems and class objects used elsewhere in the code
  //  are declared here.
  // All classes required by any class instantiated here must, be instantiated before the instatiated class.
  public static final Drivetrain dt = new Drivetrain();
  public static final Pneumatics pn = new Pneumatics();
  public static final Climber cl = new Climber();
  public static final Intake in = new Intake();
  public static final Flywheel fw = new Flywheel();
  public static final Feeder fd = new Feeder();
  public static final Hood hd = new Hood();
  public static final Turret tr = new Turret();
  
  // e.x. AutoGenerator uses Drivetrain classes, so it must be made after drivetrain

  // By creating an AutoGenerator object
  // We are effectively importing all of the .json trajectory files on robot
  // startup.
  // This is because the AutoGenerator object is made on robot init and so everything
  // .json file is loaded on robot init as well. This saves time during auto as .json file
  // loading can take some time, and this time would normally be wasted with the robot just 
  // sitting still during auto.
  public static final AutoGenerator ag = new AutoGenerator();
  private static final SendableChooser<Command> chooser = new SendableChooser<>();

  // Xbox controllers
  public static final XboxController xbox = new XboxController(xboxPrimaryDriver);
  public static final XboxController xbox2 = new XboxController(xboxSecondaryDriver);

  public RobotContainer() {
    chooser.setName("Please Select and Auto");
    chooser.setDefaultOption("Do Nothing", ag.getDoNothingAuto());
    chooser.addOption("Timed Drive", new TimedDrive(0.5, 2));

    /*
    chooser.addOption("Drive Straight", ag.getDriveStraightAuto());
    chooser.addOption("Three Ball Auto", ag.getThreeBallAuto());
    chooser.addOption("Five Ball Auto", ag.getFiveBallAuto());
    chooser.addOption("Eight Ball Auto", ag.getEightBallAuto());
    chooser.addOption("Ten Ball Auto", ag.getTenBallAuto());
    chooser.addOption("Drive Around Post", ag.makeFollowingCommandForAuto("AroundPostTest.wpilib.json"));
    chooser.addOption("Hold Position Test", new HoldPositionController());
    chooser.addOption("Timed Drive", new TimedDrive(0.5, 2));
    chooser.addOption("Trajectory Distance Drive", new DriveDistanceCommandGenerator(3).getCommand());
    chooser.addOption("Trajectory Distance Drive Backwards", new DriveDistanceCommandGenerator(-3).getCommand());
    */

    Shuffleboard.getTab("Selector").add(chooser);

    configureButtonBindings();
    configureDefaultCommands();
  }

  private void configureButtonBindings() {
    // Primary Driver Controls
    //xbox.leftBumper.whileHeld(new HoldPositionController());
    xbox.rightBumper.whileHeld(new ReducedSpeedTurningDrive());

    // Intake balls
    xbox2.leftBumper.whenPressed(new DeployIntake().alongWith(new SpinIntakeIn()).alongWith(new IncrementFeeder()));
    xbox2.leftBumper.whenReleased(new RetractIntake().alongWith(new SpinIntakeOff()).alongWith(new StopInting()));
    
    // Dump balls if there's a jam
    xbox2.rightBumper.whenPressed(new DeployIntake().alongWith(new SpinIntakeOut()).alongWith(new ReverseFeeder()));
    xbox2.rightBumper.whenReleased(new RetractIntake().alongWith(new SpinIntakeOff()).alongWith(new StopInting()));

    // Climber
    xbox2.dpadUp.whenPressed(new ExtendClimber());
    xbox2.dpadDown.whenPressed(new RetractClimber());
  
    // xbox2 x automated shooting
    xbox2.b.whileHeld(new FlywheelShootOut());
    xbox2.b.whenReleased(new FlywheelShootOff());

    // climber piston
    xbox2.start.whenPressed(new LockClimber());
    xbox2.select.whenPressed(new UnlockClimber());
  }

  private void configureDefaultCommands() {
    dt.setDefaultCommand(new Drive());
    hd.setDefaultCommand(new AngleWithJoystick());
    tr.setDefaultCommand(new AngleWithTurret());
  }

  public Command getAutonomousCommand() {
    return chooser.getSelected();
  }
}
