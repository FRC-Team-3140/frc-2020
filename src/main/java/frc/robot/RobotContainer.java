package frc.robot;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj2.command.Command;

import frc.robot.Constants.OIConstants;
import frc.robot.commands.auto.DoNothingAuto;
import frc.robot.commands.auto.EightBallAuto;
import frc.robot.commands.drivetrain.Drive;
import frc.robot.subsystems.Drivetrain;
import frc.libs.TrajectoryFollower;
import frc.libs.XboxController;

public class RobotContainer {
  public static final Drivetrain dt = new Drivetrain();
  public static final XboxController xbox = new XboxController(OIConstants.xboxPort);

  private SendableChooser<Command> chooser;

  public RobotContainer() {
    chooser = new SendableChooser<>();
    // By adding all of our trajectory commands to the chooser
    // We are effectively importing all of the .json trajectory files on robot startup.
    // This is because the chooser object is made on robot init and so everything added to the chooser 
    // is made on robot init as well. This saves time during auto as .json file loading can take some time,
    // and this time would normally be wasted with the robot just sitting still during auto.
    chooser.setDefaultOption("Do Nothing", new DoNothingAuto());
    chooser.addOption("8BallAuto", new EightBallAuto());
    chooser.addOption("Drive Around Post", TrajectoryFollower.makeFollowingCommandForAuto("AroundPostTest.wpilib.json", 15));
    chooser.addOption("Hold Position Test", TrajectoryFollower.makeFollowingCommandForAuto("HoldPosition_For3Min.wpilib.json", 180));

    configureButtonBindings();
    configureDefaultCommands();
  }

  private void configureButtonBindings() {
  }

  private void configureDefaultCommands() {
    dt.setDefaultCommand(new Drive());
  }

  public Command getAutonomousCommand() {
    return chooser.getSelected();
  }
}
