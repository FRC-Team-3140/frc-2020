package frc.robot;

import java.util.Set;

import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.robot.Constants.OIConstants;
import frc.robot.commands.auto.DoNothingAuto;
import frc.robot.commands.drivetrain.Drive;
import frc.robot.commands.drivetrain.HoldPositionController;
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
    chooser.setName("Please Select and Auto");
    chooser.setDefaultOption("Do Nothing", new DoNothingAuto());
    Command eightball = new Command(){
    
      @Override
      public Set<Subsystem> getRequirements() {
        // TODO Auto-generated method stub
        return null;
      }
      public boolean isFinished() {
        return true;
      }
    };
    eightball.andThen(TrajectoryFollower.makeFollowingCommandForAuto("AroundPostTest.wpilib.json", 5))
      .andThen(TrajectoryFollower.makeFollowingCommandForAuto("AroundPostTest.wpilib.json", 5))
      .andThen(TrajectoryFollower.makeFollowingCommandForAuto("AroundPostTest.wpilib.json", 5));
      
    chooser.addOption("8BallAuto", eightball);
    chooser.addOption("Drive Around Post", TrajectoryFollower.makeFollowingCommandForAuto("AroundPostTest.wpilib.json", 15));
    chooser.addOption("Hold Position Test", new HoldPositionController());//TrajectoryFollower.makeFollowingCommandForAuto("HoldPosition_For3Min.wpilib.json", 180));

    Shuffleboard.getTab("Selector").add(chooser);

    configureButtonBindings();
    configureDefaultCommands();
  }

  private void configureButtonBindings() {
  }

  private void configureDefaultCommands() {
    dt.setDefaultCommand(new Drive());
    xbox.x.whileHeld(new HoldPositionController());
  }

  public Command getAutonomousCommand() {
    return chooser.getSelected();
  }
}
