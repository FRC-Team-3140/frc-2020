package frc.robot;

import java.io.IOException;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.loops.Looper;

public class Robot extends TimedRobot {
  private Command autoCommand;
  private RobotContainer robotContainer;

  private final Looper enabledLooper = new Looper();
  private final Looper disabledLooper = new Looper();

  private final SubsystemManager subsystemManager = SubsystemManager.getInstance();

  @Override
  public void robotInit() {
    robotContainer = new RobotContainer();

    subsystemManager.setSubsystems(RobotContainer.dt);

    // Import auto's when robot initializes to save time.
    try {
      autoCommand = robotContainer.getAutonomousCommand();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void robotPeriodic() {
    subsystemManager.outputToSmartDashboard();
    CommandScheduler.getInstance().run();
  }

  @Override
  public void disabledInit() {
    enabledLooper.stop();
    disabledLooper.start();

    // Reset sensors
    RobotContainer.dt.zeroSensors();
  }

  @Override
  public void disabledPeriodic() {
  }

  @Override
  public void autonomousInit() {
    disabledLooper.stop();
    enabledLooper.start();

    // Reset sensors
    RobotContainer.dt.zeroSensors();

    // Run autoCommand
    if (autoCommand != null) {
      autoCommand.schedule();
    }
  }

  @Override
  public void autonomousPeriodic() {
  }

  @Override
  public void teleopInit() {
    disabledLooper.stop();
    enabledLooper.start();

    if (autoCommand != null) {
      autoCommand.cancel();
    }
  }

  @Override
  public void teleopPeriodic() {
  }

  @Override
  public void testInit() {    
    disabledLooper.stop();
    enabledLooper.start();
    
    CommandScheduler.getInstance().cancelAll();
  }

  @Override
  public void testPeriodic() {
  }
}
