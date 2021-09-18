/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.HardwareAdapter;

public class Flywheel extends SubsystemBase implements HardwareAdapter {
  public Flywheel() {
    flyWheelMaster.setInverted(true);
    setFollowers();
  }

  private void setFollowers() {
    flyWheelSlave1.follow(flyWheelMaster, true);
  }
   
  public void shootOut() {
    flyWheelMaster.set(1);
  }

  public void shootOff() {
    flyWheelMaster.set(0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
