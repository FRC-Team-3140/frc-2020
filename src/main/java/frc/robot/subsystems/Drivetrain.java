package frc.robot.subsystems;

import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveWheelSpeeds;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.robot.HardwareAdapter;
import frc.robot.Constants;

public class Drivetrain extends SubsystemBase implements HardwareAdapter, Constants {
  private final AHRS navx = new AHRS(SPI.Port.kMXP);
  private final DifferentialDriveOdometry odometry;

  public Drivetrain() {
    setFollowers();
    leftEncoder.setPositionConversionFactor(DriveConstants.kEncoderMetersPerPulse);
    leftEncoder.setVelocityConversionFactor(DriveConstants.kEncoderLinearMetersPerSecondPerRPM);
    rightEncoder.setPositionConversionFactor(DriveConstants.kEncoderMetersPerPulse);
    rightEncoder.setVelocityConversionFactor(DriveConstants.kEncoderLinearMetersPerSecondPerRPM);
    resetEncoders();
    resetGyro();
    odometry = new DifferentialDriveOdometry(Rotation2d.fromDegrees(getHeading()));
  }

  private void setFollowers() {
    leftDriveSlave1.follow(leftDriveMaster);
    rightDriveSlave1.follow(rightDriveMaster);
  }

  @Override
  public void periodic() {
    odometry.update(Rotation2d.fromDegrees(getHeading()), leftEncoder.getPosition(), rightEncoder.getPosition());
    SmartDashboard.putNumber("Gyro Heading (deg): ", getHeading());
    SmartDashboard.putNumber("Left Encoder Distance (m): ", leftEncoder.getPosition());
    SmartDashboard.putNumber("Right Encoder Distance (m): ", rightEncoder.getPosition());
    SmartDashboard.putNumber("Left Encoder Velocity (m/s): ", leftEncoder.getVelocity());
    SmartDashboard.putNumber("Right Encoder Velocity (m/s): ", rightEncoder.getVelocity());
  }

  public void arcadeDrive(double throttle, double heading) {
    tankDrive(throttle - heading, throttle + heading);
  }

  public void tankDrive(double left, double right) {
    leftDriveMaster.set(left);
    rightDriveMaster.set(right);
  }

  public void tankDriveVolts(double leftVolts, double rightVolts) {
    leftDriveMaster.setVoltage(leftVolts);
    rightDriveMaster.setVoltage(rightVolts);
  }

  public void resetEncoders() {
    leftEncoder.setPosition(0);
    rightEncoder.setPosition(0);
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
    return new DifferentialDriveWheelSpeeds(leftEncoder.getVelocity(), rightEncoder.getVelocity());
  }

  public double getHeading() {
    return navx.getYaw() * (DriveConstants.kGyroReversed ? -1.0 : 1.0);
  }

  public Pose2d getCurrentPose() {
    return odometry.getPoseMeters();
  }
}
