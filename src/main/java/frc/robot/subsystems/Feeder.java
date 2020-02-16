package frc.robot.subsystems;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.HardwareAdapter;

public class Feeder extends SubsystemBase implements HardwareAdapter {
  int numBalls = 0;

  boolean[] lastSwitchStates = new boolean[2]; //keeps track of the last state of the limit switches
  public Feeder() {
    numBalls = 0;
    updateSwitchStates();
  }

  public void feedShooter() {
    feeder.set(1);
    feeder2.set(1);
  }

  public void pushUpFeeder() {
    feeder.set(1);
    feeder2.set(-.5);
  }

  public void stopFeed() {
    feeder.set(0);
    feeder2.set(0);
  }

  private void updateSwitchStates() {
    lastSwitchStates[0] = chamberOut.get();
    lastSwitchStates[1] = chamberIn.get();
  }

  // tracks number of balls
  @Override
  public void periodic() {
    if(lastSwitchStates[0] == false && chamberOut.get() == true) {
      numBalls--;
    }

    if(lastSwitchStates[1] == false && chamberIn.get() == true) {
      numBalls++;
    }

    SmartDashboard.putNumber("Balls", 0);

    updateSwitchStates();
  }
}
