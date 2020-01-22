package frc.robot;

import java.io.IOException;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;

// Currently we reset all sensors 
// whenever the robot enters a new game state.
public class Robot extends TimedRobot {
  private Command autoCommand;
  private RobotContainer robotContainer;

  @Override
  public void robotInit() {
    robotContainer = new RobotContainer();
  }

  @Override
  public void robotPeriodic() {
    CommandScheduler.getInstance().run();
  }

  @Override
  public void disabledInit() {
    RobotContainer.dt.resetEncoders();
    RobotContainer.dt.resetGyro();
  }

  @Override
  public void disabledPeriodic() {
  }

  @Override
  public void autonomousInit() {
    RobotContainer.dt.resetEncoders();
    RobotContainer.dt.resetGyro();

    try {
      autoCommand = robotContainer.getAutonomousCommand();
    } catch (IOException e) {
      e.printStackTrace();
    }

    if (autoCommand != null) {
      autoCommand.schedule();
    }
  }

  @Override
  public void autonomousPeriodic() {
  }

  @Override
  public void teleopInit() {
    RobotContainer.dt.resetEncoders();
    RobotContainer.dt.resetGyro();

    if (autoCommand != null) {
      autoCommand.cancel();
    }
  }

  @Override
  public void teleopPeriodic() {
  }

  @Override
  public void testInit() {    
    RobotContainer.dt.resetEncoders();
    RobotContainer.dt.resetGyro();

    CommandScheduler.getInstance().cancelAll();
  }

  @Override
  public void testPeriodic() {
  }
}
