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
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.HardwareAdapter;

public class Turret extends SubsystemBase implements HardwareAdapter {
  NetworkTable table;
  NetworkTableEntry pos, velo;
  private Timer timer = new Timer();

  public double kP = 1, kI =0, kD = 0;

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

  double lastT = 0;
  double lastI = 0;
  double lastX = 0;
  public void turnToTarget() {
    double tx = SmartDashboard.getNumber("tx", 0);
    double t = timer.get();
    double dt = t - lastT;
    if(Math.abs(tx) <= 1) {
      double p = kP * tx;
      double i = kI * (lastI + dt*tx);
      double d = kD * (tx - lastX) / dt;
      double output = p+i+d;
      if(output > 0.33) output = .33; // limit
      turretMotor.set(output);
    }
    else System.out.println("NO TARGET");
  }

  public boolean isFacingTarget() {
    double tx = SmartDashboard.getNumber("tx", 0);
    return Math.abs(tx) < 8;
  }

  public void startTimer() {
    timer.reset();
    timer.start();
  }
  
  public void stopTimer() {
    timer.stop();
    timer.reset();
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
