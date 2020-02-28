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

public class Turret extends SubsystemBase implements HardwareAdapter {
  NetworkTable table;
  NetworkTableEntry pos, velo;

  public Turret() {
    turretMotor.setNeutralMode(NeutralMode.Brake);
    turretMotor.setInverted(true);
    
    table = NetworkTableInstance.getDefault().getTable("Turret");
    pos = table.getEntry("Turret Position Ticks");
    velo = table.getEntry("Turret Velocity Ticks");
  }

  public void drive(double throttle) {
    turretMotor.set(throttle*.5);
  }

  public int getPositionTicks() { // 4096 ticks per rot
    return turretMotor.getSelectedSensorPosition();
  }

  public int getVelocityTicks() {
    return turretMotor.getSelectedSensorVelocity();
  }

  public void resetEncoder() {
    turretMotor.setSelectedSensorPosition(0);
  }

  @Override
  public void periodic() {
    //pos.setDouble(getPositionTicks());
    //velo.setDouble(getVelocityTicks());
  }

}
