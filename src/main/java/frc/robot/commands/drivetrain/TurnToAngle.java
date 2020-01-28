/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.drivetrain;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;

public class TurnToAngle extends CommandBase {
  double angle;
  // angle is in degrees!!!
  public TurnToAngle(double angle) {
    addRequirements(RobotContainer.dt);
    this.angle = angle;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    RobotContainer.dt.resetSensorsForPID();
    RobotContainer.dt.restartTimer();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    RobotContainer.dt.turnToAngle(angle);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    System.out.println("turn to angle command finished");
    RobotContainer.dt.stop();
    //RobotContainer.dt.resetSensorsForPID();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return RobotContainer.dt.isAtAngle(angle);
  }
}
