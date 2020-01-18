/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.HardwareAdapter;

public class Drivetrain extends SubsystemBase implements HardwareAdapter {

  private double kP = 1.5;
  private double VISION_TARGET_TOL = .05;

  public Drivetrain() {
	  setFollowers();
  }

  // very primitive turn to target method
  public void turnToVisionTarget() {
    double x = SmartDashboard.getNumber("x", 0) - 0.5;
    arcadeDrive(0,kP * x);
  }

  public boolean isFacingVisionTarget() {
    double x = SmartDashboard.getNumber("x", 0) - 0.5;
    if(Math.abs(x) < VISION_TARGET_TOL) return true;
    else return false;
  }

  public void arcadeDrive(double throttle, double heading) {
    tankDrive(throttle - heading, throttle + heading);
  }

  public void tankDrive(double left, double right) {
    leftDriveMaster.set(left);
    rightDriveMaster.set(right);
  }

  private void setFollowers() {
    leftDriveSlave1.follow(leftDriveMaster);
   // leftDriveSlave2.follow(leftDriveMaster);
    rightDriveSlave1.follow(rightDriveMaster);
    //rightDriveSlave2.follow(rightDriveMaster);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    
  }
}
