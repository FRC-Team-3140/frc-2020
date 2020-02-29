/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.HardwareAdapter;

public class Climber extends SubsystemBase implements HardwareAdapter {
  double MAX_HEIGHT = 6.52;
  public Climber() {
    setFollowers();
    climberMaster.setNeutralMode(NeutralMode.Brake);
    climberSlave.setNeutralMode(NeutralMode.Brake);
    climberEncoder.reset();
  }

  public void climberSetValue(double value) {

    value *= -1;
    if(Math.abs(value) < .1) climberMaster.set(0);
    else climberMaster.set(value);
    
  }

  public void climberExtend() {
      climberMaster.set(1);
  }

  public void climberRetract() {
    climberMaster.set(-1);
  }

  public void climberOff() {
    climberMaster.set(0);
  }

  private void setFollowers() {
    climberSlave.follow(climberMaster);
  }

  @Override
  public void periodic() {
    SmartDashboard.putNumber("Climber Height", climberEncoder.getDistance());
  }
}
