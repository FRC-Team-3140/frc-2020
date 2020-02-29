/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.HardwareAdapter;

public class Hood extends SubsystemBase implements HardwareAdapter {

  NetworkTable table;
  NetworkTableEntry pos, velo;

  public Hood() {
    hoodMotor.setNeutralMode(NeutralMode.Brake);

    table = NetworkTableInstance.getDefault().getTable("Hood");
    pos = table.getEntry("Hood Position Ticks");
    velo = table.getEntry("Hood Velocity Ticks");
  }

  public void drive(double throttle) {
    hoodMotor.set(throttle);
  }

  public int getPositionTicks() { // 4096 ticks per rot
    return hoodMotor.getSelectedSensorPosition();
  }

  public int getVelocityTicks() {
    return hoodMotor.getSelectedSensorVelocity();
  }

  public void resetEncoder() {
    hoodMotor.setSelectedSensorPosition(0);
  }

  @Override
  public void periodic() {
    //pos.setDouble(getPositionTicks());
    //velo.setDouble(getVelocityTicks());

  }
}
