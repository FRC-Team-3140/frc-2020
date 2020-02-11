package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class EightBallAuto extends CommandBase {
  public EightBallAuto() {
    // TODO:
    // Brake Mode On
    // TrajectoryFollowing Drive to Shooting Location
      // In Parallel Home and Rotate Shooter to 90deg.
    // TrajectoryFollowing HoldPosition 
      // In Parallel shoot with vision
    // End Shooting and HoldPosition Off
    // Home and Stow Shooter
      // In Parallel TrajectoryFollowing to Ball Pickup
        // In Parallel Time Delay then Intake On
    // Intake Off
    // TrajectoryFollowing to Shooting Location
      // In Parallel Home and Rotate Shooter to 90deg.
    // TrajectoryFollowing HoldPosition 
      // In Parallel shoot with vision
    // End Shooting and HoldPosition Off
    // Home and Stow Shooter
    //Brake Mode Off
  }

  @Override
  public void initialize() {
  }

  @Override
  public void execute() {
  }

  @Override
  public void end(boolean interrupted) {
  }

  @Override
  public boolean isFinished() {
    return true;
  }
}
