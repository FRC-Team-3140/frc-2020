/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.HardwareAdapter;

public class Climber extends SubsystemBase implements HardwareAdapter {
  public Climber() {
    setFollowers();
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
    // This method will be called once per scheduler run
  }
}
