/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.HardwareAdapter;

public class Drivetrain extends SubsystemBase implements HardwareAdapter {

  NetworkTableInstance nt;
  NetworkTable table;

  private double kP_VISION = 1/285.0;
  public Drivetrain() {
    setFollowers();
    rightDriveMaster.setInverted(true);
    rightDriveSlave1.setInverted(true);

    leftDriveMaster.setNeutralMode(NeutralMode.Brake);
    rightDriveSlave1.setNeutralMode(NeutralMode.Brake);
    leftDriveSlave1.setNeutralMode(NeutralMode.Brake);
    rightDriveMaster.setNeutralMode(NeutralMode.Brake);

    nt = NetworkTableInstance.getDefault();
    table = nt.getTable("Target Info");
  }

  // very primitive turn to target method
  public void turnToVisionTarget() {
    double x = table.getEntry("tx").getDouble(0);
    arcadeDrive(0, -kP_VISION * x);
  }
  
  public boolean isFacingVisionTarget() {
    double x = table.getEntry("tx").getDouble(0);
    System.out.println(x);
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
