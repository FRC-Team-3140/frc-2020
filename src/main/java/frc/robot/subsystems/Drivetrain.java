/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.HardwareAdapter;

public class Drivetrain extends SubsystemBase implements HardwareAdapter {

  public Drivetrain() {
	  setFollowers();
  }

  public void arcadeDrive(double throttle, double heading) {
    tankDrive(throttle + heading, throttle - heading);
  }

  public void tankDrive(double left, double right) {
    leftDriveMaster.set(left);
    rightDriveMaster.set(-right);
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
