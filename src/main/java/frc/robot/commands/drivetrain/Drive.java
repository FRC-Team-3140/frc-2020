package frc.robot.commands.drivetrain;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.Constants.OIConstants;

public class Drive extends CommandBase {

  public Drive() {
    addRequirements(RobotContainer.dt);
  }

  @Override
  public void initialize() {
  }

  @Override
  public void execute() {
    double throttle = RobotContainer.xbox.getSmoothedMainY();
    double heading = RobotContainer.xbox.getSmoothedAltX();

    // Implement Deadband
    if(Math.abs(throttle) < OIConstants.deadBand)
      throttle = 0;

    if(Math.abs(heading) < OIConstants.deadBand)
      heading = 0;

    RobotContainer.dt.arcadeDrive(throttle, heading);
  }

  @Override
  public void end(boolean interrupted) {
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
