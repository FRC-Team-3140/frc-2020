package frc.robot.commands.turret;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;

public class TurnToTarget extends CommandBase {

  public TurnToTarget() {
    addRequirements(RobotContainer.tr);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    RobotContainer.tr.startTimer();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    RobotContainer.tr.turnToTarget();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    RobotContainer.tr.stopTimer();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    System.out.println("facing target!");
    return RobotContainer.tr.isFacingTarget();
  }
}
