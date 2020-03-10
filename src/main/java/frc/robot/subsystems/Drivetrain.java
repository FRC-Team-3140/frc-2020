package frc.robot.subsystems;

import com.revrobotics.CANSparkMax.IdleMode;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.HardwareAdapter;

public class Drivetrain extends SubsystemBase implements HardwareAdapter {
   public Drivetrain() {
    setupMotors();
  }

  /***********
   * DRIVING *
   ***********/
  public void arcadeDrive(double throttle, double heading) {
    tankDrive(throttle - heading, throttle + heading);
  }

  public void tankDrive(double left, double right) {
    leftDriveMaster.set(left);
    rightDriveMaster.set(right);
  }

  /************
   * PERIODIC *
   ************/
  @Override
  public void periodic() {
    if(!leftDriveSlave1.isFollower())
      System.err.println("Left Slave 1 Is Not A Follower");

    if(!leftDriveSlave2.isFollower())
      System.err.println("Left Slave 2 Is Not A Follower");

    if(!rightDriveSlave1.isFollower())
      System.err.println("Right Slave 1 Is Not A Follower");

    if(!rightDriveSlave2.isFollower())
      System.err.println("Right Slave 2 Is Not A Follower");

    System.out.println(leftDriveMaster.getOutputCurrent() + "\t" + 
      leftDriveSlave1.getOutputCurrent() + "\t" + leftDriveSlave2.getOutputCurrent() + "\t" 
      + ":" + "\t" + rightDriveMaster.getOutputCurrent() + "\t" + 
      rightDriveSlave1.getOutputCurrent() + "\t" + rightDriveSlave2.getOutputCurrent());
  }

  /**********
   * CONFIG *
   **********/
  public void setupMotors() {
    boolean leftInverted = true;
    boolean rightInverted = false;

    leftDriveMaster.clearFaults();
    leftDriveSlave1.clearFaults();
    leftDriveSlave2.clearFaults();
    rightDriveMaster.clearFaults();
    rightDriveSlave1.clearFaults();
    rightDriveSlave2.clearFaults();

    /*
    leftDriveMaster.setSmartCurrentLimit(60);
    leftDriveSlave1.setSmartCurrentLimit(60);
    leftDriveSlave2.setSmartCurrentLimit(60);
    rightDriveMaster.setSmartCurrentLimit(60);
    rightDriveSlave1.setSmartCurrentLimit(60);
    rightDriveSlave2.setSmartCurrentLimit(60);
*/

    leftDriveMaster.setInverted(leftInverted);
    leftDriveSlave1.follow(leftDriveMaster);
    leftDriveSlave1.setInverted(leftInverted);
    leftDriveSlave2.follow(leftDriveMaster);
    leftDriveSlave2.setInverted(leftInverted);

    rightDriveMaster.setInverted(rightInverted);
    rightDriveSlave1.follow(rightDriveMaster);
    rightDriveSlave1.setInverted(rightInverted);
    rightDriveSlave2.follow(rightDriveMaster);
    rightDriveSlave2.setInverted(rightInverted);

    setIdleMode(IdleMode.kBrake);

    leftDriveMaster.burnFlash();
    leftDriveSlave1.burnFlash();
    leftDriveSlave2.burnFlash();
    rightDriveMaster.burnFlash();
    rightDriveSlave1.burnFlash();
    rightDriveSlave2.burnFlash();
  }

  public void setIdleMode(IdleMode mode) {
    leftDriveMaster.setIdleMode(mode);
    leftDriveSlave1.setIdleMode(mode);
    leftDriveSlave2.setIdleMode(mode);

    rightDriveMaster.setIdleMode(mode);
    rightDriveSlave1.setIdleMode(mode);
    rightDriveSlave2.setIdleMode(mode);
  }
}
