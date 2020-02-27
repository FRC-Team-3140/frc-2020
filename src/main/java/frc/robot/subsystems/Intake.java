/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.HardwareAdapter;

public class Intake extends SubsystemBase implements HardwareAdapter {
  public Intake() {
    intakeMotor.setNeutralMode(NeutralMode.Brake);
  }

  public void spinIn() {
    intakeMotor.set(.5);
  }

  public void spinOut() {
    intakeMotor.set(-.5);
  }

  public void spinOff() {
    intakeMotor.set(0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
