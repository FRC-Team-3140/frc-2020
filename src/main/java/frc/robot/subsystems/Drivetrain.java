package frc.robot.subsystems;

import com.revrobotics.CANSparkMax.IdleMode;

import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveWheelSpeeds;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.HardwareAdapter;
import frc.robot.Constants;

public class Drivetrain extends SubsystemBase implements HardwareAdapter, Constants.GeneralConstants.SensorConstants {
  private final DifferentialDriveOdometry odometry;
  private boolean reversedTrajectory = false;

  public Drivetrain() {
    setupMotors();

    leftEncoder.setPositionConversionFactor(kDriveTrainEncoderMetersPerPulse);
    rightEncoder.setPositionConversionFactor(kDriveTrainEncoderMetersPerPulse);
    leftEncoder.setVelocityConversionFactor(kDriveTrainEncoderLinearMetersPerSecondPerRPM);
    rightEncoder.setVelocityConversionFactor(kDriveTrainEncoderLinearMetersPerSecondPerRPM);

    resetEncoders();
    resetGyro();
    odometry = new DifferentialDriveOdometry(Rotation2d.fromDegrees(getHeading()));
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

  public void tankDriveVolts(double leftVolts, double rightVolts) {
    // TODO:
    // Comment these lines out and then stop multiplying constants by 10????
    leftVolts = leftVolts/12;
    rightVolts = rightVolts/12;

    System.out.println("leftVolts: " + leftVolts + "  rightVolts: " + rightVolts);

    if(Math.abs(leftVolts) > 12)
      leftVolts = Math.signum(leftVolts) * 12;

    if(Math.abs(rightVolts) > 12)
      rightVolts = Math.signum(rightVolts) * 12;    

    if(reversedTrajectory) {
      leftDriveMaster.setVoltage(-rightVolts);
      rightDriveMaster.setVoltage(-leftVolts);
    }
    else {
      leftDriveMaster.setVoltage(leftVolts);
      rightDriveMaster.setVoltage(rightVolts);
    }
  }

  /************
   * PERIODIC *
   ************/
  @Override
  public void periodic() {
    if(isTrajectoryReversed())
      odometry.update(Rotation2d.fromDegrees(getHeading()), getRightEncoderDistance(), getLeftEncoderDistance());
    else
      odometry.update(Rotation2d.fromDegrees(getHeading()), getLeftEncoderDistance(), getRightEncoderDistance());
    SmartDashboard.putNumber("Gyro Heading (deg): ", getHeading());
    SmartDashboard.putNumber("Left Encoder Distance (m): ", getLeftEncoderDistance());
    SmartDashboard.putNumber("Right Encoder Distance (m): ", getRightEncoderDistance());
    SmartDashboard.putNumber("Left Encoder Velocity (m/s): ", getLeftEncoderVelocity());
    SmartDashboard.putNumber("Right Encoder Velocity (m/s): ", getRightEncoderVelocity());
    SmartDashboard.putNumber("Average Velocity (m/s): ", (getLeftEncoderVelocity() + getRightEncoderVelocity()) / 2);
  }

  /******************
   * SETTER METHODS *
   ******************/
  public void setTrajectoryReversed(boolean reversed) {
    reversedTrajectory = reversed;
  }

  /**********
   * CONFIG *
   **********/
  private void setupMotors() {
    boolean leftInverted = true;
    boolean rightInverted = false;

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
  }

  public void setIdleMode(IdleMode mode) {
    leftDriveMaster.setIdleMode(mode);
    leftDriveSlave1.setIdleMode(mode);
    leftDriveSlave2.setIdleMode(mode);

    rightDriveMaster.setIdleMode(mode);
    rightDriveSlave1.setIdleMode(mode);
    rightDriveSlave2.setIdleMode(mode);
  }

  /**********
   * RESETS *
   **********/

  public void resetEncoders() {
    leftEncoder.setPosition(0);
    rightEncoder.setPosition(0);
  }

  public void resetGyro() {
    navx.reset();
  }
  
  private void resetOdometry() {
    Pose2d defaultPose = new Pose2d();
    odometry.resetPosition(defaultPose, Rotation2d.fromDegrees(getHeading()));
  }
  
  public void resetAll() {
    resetEncoders();
    resetGyro();
    resetOdometry();
  }

  /******************
   * GETTER METHODS *
   ******************/

  public IdleMode getIdleMode() {
    return leftDriveMaster.getIdleMode();
  }

  public boolean isTrajectoryReversed() {
    return reversedTrajectory;
  }
  
  public double getLeftEncoderDistance() {
    double output = leftEncoder.getPosition();

    return reversedTrajectory ? -output : output;
  }

  public double getRightEncoderDistance() {
    double output = rightEncoder.getPosition();

    return reversedTrajectory ? -output : output;
  }

  public double getLeftEncoderVelocity() {
    double output = rightEncoder.getVelocity();

    return reversedTrajectory ? -output : output;
  }

  public double getRightEncoderVelocity() {
    double output = rightEncoder.getVelocity();

    return reversedTrajectory ? -output : output;
  }

  // Returns left and right linear speeds in m/s
  public DifferentialDriveWheelSpeeds getWheelSpeeds() {
    if(isTrajectoryReversed())
      return new DifferentialDriveWheelSpeeds(getRightEncoderVelocity(), getLeftEncoderVelocity());
    else
      return new DifferentialDriveWheelSpeeds(getLeftEncoderVelocity(), getRightEncoderVelocity());
  }

  // (kGyroReversed == true) 180 deg. to -180 deg. CCWP
  // (kGyroReversed == false) -180 deg. to 180 deg. CWP
  public double getHeading() {
    return navx.getYaw() * (kGyroReversed ? -1.0 : 1.0);
  }

  public Pose2d getCurrentPose() {
    return odometry.getPoseMeters();
  }
}
