package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.revrobotics.CANSparkMax.IdleMode;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.HardwareAdapter;

public class Feeder extends SubsystemBase implements HardwareAdapter {
  public Feeder() {
    ballFeeder.setIdleMode(IdleMode.kBrake);
    shooterFeeder.setNeutralMode(NeutralMode.Brake);
    //ballFeeder.setInverted(true);
  }

  public void feedShooter() {
    ballFeeder.set(-1);
    shooterFeeder.set(.5);
  }

  public void reverseFeeder() {
    ballFeeder.set(1);
    shooterFeeder.set(-.5);
  }

  public void pushUpFeeder() {
    ballFeeder.set(-1);
    shooterFeeder.set(-.25);
  }

  public void stopFeed() {
    ballFeeder.set(0);
    shooterFeeder.set(0);
  }

  @Override
  public void periodic() {
  }
}
