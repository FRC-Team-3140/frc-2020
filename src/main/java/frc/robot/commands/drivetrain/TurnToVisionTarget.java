package frc.robot.commands.drivetrain;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;

public class TurnToVisionTarget extends CommandBase {
  /**
   * Creates a new TurnToVisionTarget.
   */
  public TurnToVisionTarget() {
    addRequirements(RobotContainer.dt);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    RobotContainer.dt.turnToVisionTarget();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    RobotContainer.dt.tankDrive(0, 0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return RobotContainer.dt.isFacingVisionTarget();
  }
}