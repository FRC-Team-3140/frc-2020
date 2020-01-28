/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.drivetrain;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;

public class DriveToDistance extends CommandBase implements Constants {
  double inches;
  public DriveToDistance(double inches) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(RobotContainer.dt);
    this.inches = inches;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    RobotContainer.dt.resetEncoders();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    RobotContainer.dt.moveToPos(inches);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    //RobotContainer.dt.stop();
    System.out.println("drive to distance finished (" + inches +")");
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return RobotContainer.dt.isLeftAtPos(inches) && RobotContainer.dt.isRightAtPos(inches);
  }
}
