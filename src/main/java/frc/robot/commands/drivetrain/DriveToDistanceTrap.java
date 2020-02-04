package frc.robot.commands.drivetrain;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.RobotContainer;

public class DriveToDistanceTrap extends CommandBase {
  double inches;

  public DriveToDistanceTrap(double inches) {
    addRequirements(RobotContainer.dt);
    this.inches = inches;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    RobotContainer.dt.resetEncoders();
    RobotContainer.dt.setPosTrapProfile(inches);
    RobotContainer.dt.restartTimer();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    RobotContainer.dt.moveToPosProfile();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    RobotContainer.dt.tankDrive(0, 0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return RobotContainer.dt.isLeftAtPos(inches) && RobotContainer.dt.isRightAtPos(inches);
  }
}
