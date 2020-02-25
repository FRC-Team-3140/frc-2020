package frc.robot;

import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.OIConstants;
import frc.robot.commands.auto.AutoGenerator;
import frc.robot.commands.drivetrain.Drive;
import frc.robot.commands.drivetrain.HoldPositionController;
import frc.robot.commands.drivetrain.TimedDrive;
import frc.robot.subsystems.Drivetrain;
import frc.libs.XboxController;

public class RobotContainer {
  // By creating an AutoGenerator object
  // We are effectively importing all of the .json trajectory files on robot
  // startup.
  // This is because the AutoGenerator object is made on robot init and so everything
  // .json file is loaded on robot init as well. This saves time during auto as .json file
  // loading can take some time, and this time would normally be wasted with the robot just 
  // sitting still during auto.

  // All classes required by any class instantiated here must, be instantiated before the instatiated class.
  public static final Drivetrain dt = new Drivetrain();
  // e.x. AutoGenerator uses Drivetrain classes, so it must be made after drivetrain
  public static final AutoGenerator ag = new AutoGenerator();
  public static final XboxController xbox = new XboxController(OIConstants.xboxPort);
  private static final SendableChooser<Command> chooser = new SendableChooser<>();

  public RobotContainer() {
    chooser.setName("Please Select and Auto");
    chooser.setDefaultOption("Do Nothing", ag.getDoNothingAuto());
    chooser.addOption("Drive Straight", ag.getDriveStraightAuto());
    chooser.addOption("Three Ball Auto", ag.getThreeBallAuto());
    chooser.addOption("Five Ball Auto", ag.getFiveBallAuto());
    chooser.addOption("Eight Ball Auto", ag.getEightBallAuto());
    chooser.addOption("Ten Ball Auto", ag.getTenBallAuto());
    chooser.addOption("Drive Around Post", ag.makeFollowingCommandForAuto("AroundPostTest.wpilib.json"));
    chooser.addOption("Hold Position Test", new HoldPositionController());
    chooser.addOption("Timed Drive", new TimedDrive(0.5, 2));

    Shuffleboard.getTab("Selector").add(chooser);

    configureButtonBindings();
    configureDefaultCommands();
  }

  private void configureButtonBindings() {
    xbox.x.whileHeld(new HoldPositionController());
  }

  private void configureDefaultCommands() {
    dt.setDefaultCommand(new Drive());
  }

  public Command getAutonomousCommand() {
    return chooser.getSelected();
  }
}
