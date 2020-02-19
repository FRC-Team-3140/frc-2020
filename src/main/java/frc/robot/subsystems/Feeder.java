package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.HardwareAdapter;

public class Feeder extends SubsystemBase implements HardwareAdapter {
  public Feeder() {
  }

  public void feedShooter() {
    ballFeeder.set(1);
    shooterFeeder.set(1);
  }

  public void reverseFeeder() {
    ballFeeder.set(-1);
    shooterFeeder.set(-1);
  }

  public void pushUpFeeder() {
    ballFeeder.set(1);
    shooterFeeder.set(-.5);
  }

  public void stopFeed() {
    ballFeeder.set(0);
    shooterFeeder.set(0);
  }

  @Override
  public void periodic() {
  }
}
