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

public class Drivetrain extends SubsystemBase implements HardwareAdapter, Constants {
  private final DifferentialDriveOdometry odometry;

  public Drivetrain() {
    setFollowers();
    leftDriveMaster.setInverted(false);
    leftDriveMaster.setIdleMode(IdleMode.kBrake);
    rightDriveMaster.setInverted(true);
    rightDriveMaster.setIdleMode(IdleMode.kBrake);

    
    leftEncoder.setDistancePerRotation(DriveConstants.kEncoderMetersPerPulse);
    rightEncoder.setDistancePerRotation(DriveConstants.kEncoderMetersPerPulse);

    resetEncoders();
    resetGyro();
    odometry = new DifferentialDriveOdometry(Rotation2d.fromDegrees(getHeading()));
  }

  private void setFollowers() {
    leftDriveSlave1.follow(leftDriveMaster);
    rightDriveSlave1.follow(rightDriveMaster);

    leftDriveSlave1.setIdleMode(IdleMode.kBrake);
    rightDriveSlave1.setIdleMode(IdleMode.kBrake);
  }

  public double getLeftEncoderDistance() {
    return leftEncoder.getDistance();
  }

  public double getRightEncoderDistance() {
    return -rightEncoder.getDistance();
  }

  public double getLeftEncoderVelocity() {
    return leftEncoder.getRate();
  }

  public double getRightEncoderVelocity() {
    return -rightEncoder.getRate();
  }

  @Override
  public void periodic() {
    odometry.update(Rotation2d.fromDegrees(getHeading()), getLeftEncoderDistance(), getRightEncoderDistance());
    SmartDashboard.putNumber("Gyro Heading (deg): ", getHeading());
    SmartDashboard.putNumber("Left Encoder Distance (m): ", getLeftEncoderDistance());
    SmartDashboard.putNumber("Right Encoder Distance (m): ", getRightEncoderDistance());
    SmartDashboard.putNumber("Left Encoder Velocity (m/s): ", getLeftEncoderVelocity());
    SmartDashboard.putNumber("Right Encoder Velocity (m/s): ", getRightEncoderVelocity());
    SmartDashboard.putNumber("Average Velocity (m/s): ", (getLeftEncoderVelocity() + getRightEncoderVelocity()) / 2);
    //System.out.println("left: " + getLeftEncoderVelocity() + "  right: " + getRightEncoderVelocity());
  }

  public void arcadeDrive(double throttle, double heading) {
    tankDrive(throttle - heading, throttle + heading);
  }

  public void tankDrive(double left, double right) {
    leftDriveMaster.set(left);
    rightDriveMaster.set(right);
  }

  public void tankDriveVolts(double leftVolts, double rightVolts) {
    /*
    leftVolts = leftVolts/12;
    if(Math.abs(leftVolts) > 12)
      leftVolts = Math.signum(leftVolts) * 12;

    rightVolts = rightVolts/12;
    if(Math.abs(rightVolts) > 12)
      rightVolts = Math.signum(rightVolts) * 12;
    */
    
    System.out.println("leftVolts: " + leftVolts + "  rightVolts: " + rightVolts);

    leftDriveMaster.setVoltage(leftVolts);
    rightDriveMaster.setVoltage(rightVolts);
  }

  public void resetEncoders() {
    leftEncoder.reset();
    rightEncoder.reset();
  }

  public void resetGyro() {
    navx.reset();
  }
  
  public void resetOdometry() {
    Pose2d defaultPose = new Pose2d();
    odometry.resetPosition(defaultPose, Rotation2d.fromDegrees(getHeading()));
  }
  
  public void resetAll() {
    resetEncoders();
    resetGyro();
    resetOdometry();
  }

  public DifferentialDriveWheelSpeeds getWheelSpeeds() {
    return new DifferentialDriveWheelSpeeds(getLeftEncoderVelocity(), getRightEncoderVelocity());
  }

  public double getHeading() {
    return navx.getYaw() * (DriveConstants.kGyroReversed ? -1.0 : 1.0);
  }

  public Pose2d getCurrentPose() {
    return odometry.getPoseMeters();
  }
}
