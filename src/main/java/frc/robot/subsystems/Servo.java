/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.HardwareAdapter;

public class Servo extends SubsystemBase implements HardwareAdapter{
  /**
   * Creates a new Servo.
   */
  public Servo() {

  }

  public void move(double pos) {
    servo.set(pos);
  }
  public void stop() {
    servo.set(0);
  }
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
