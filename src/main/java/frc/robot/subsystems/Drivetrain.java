package frc.robot.subsystems;

import com.revrobotics.CANEncoder;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveWheelSpeeds;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.robot.HardwareAdapter;
import frc.robot.Constants;

public class Drivetrain extends SubsystemBase implements HardwareAdapter, Constants {
  private final DifferentialDrive differentialDrive = new DifferentialDrive(leftMotors, rightMotors);
  private final Gyro m_gyro = new ADXRS450_Gyro(); // Change
  private final DifferentialDriveOdometry odometry;

  public Drivetrain() {
    setFollowers();
    leftEncoder.setPositionConversionFactor(DriveConstants.kEncoderDistancePerPulse);
    rightEncoder.setPositionConversionFactor(DriveConstants.kEncoderDistancePerPulse);
    resetEncoders();
    odometry = new DifferentialDriveOdometry(Rotation2d.fromDegrees(getHeading()));
  }

  private void setFollowers() {
    leftDriveSlave1.follow(leftDriveMaster);
    rightDriveSlave1.follow(rightDriveMaster);
  }

  public void setMaxOutput(double maxOutput) {
    differentialDrive.setMaxOutput(maxOutput);
  }

  @Override
  public void periodic() {
    odometry.update(Rotation2d.fromDegrees(getHeading()), leftEncoder.getPosition(), leftEncoder.getPosition());
  }

  public void arcadeDrive(double throttle, double heading) {
    tankDrive(throttle - heading, throttle + heading);
  }

  public void tankDrive(double left, double right) {
    leftDriveMaster.set(left);
    rightDriveMaster.set(right);
  }

  public void tankDriveVolts(double leftVolts, double rightVolts) {
    leftMotors.setVoltage(leftVolts);
    rightMotors.setVoltage(-rightVolts);
  }

  public void resetEncoders() {
    leftEncoder.setPosition(0);
    rightEncoder.setPosition(0);
  }

  public void resetGyro() {
    gyro.reset();
  }

  public void resetOdometry(Pose2d pose) {
    resetEncoders();
    odometry.resetPosition(pose, Rotation2d.fromDegrees(getHeading()));
  }

  public CANEncoder getLeftEncoder() {
    return leftEncoder;
  }

  public CANEncoder getRightEncoder() {
    return rightEncoder;
  }

  public double getAverageEncoderDistance() {
    return (leftEncoder.getPosition() + rightEncoder.getPosition()) / 2.0;
  }

  public DifferentialDriveWheelSpeeds getWheelSpeeds() {
    return new DifferentialDriveWheelSpeeds(leftEncoder.getVelocity(), rightEncoder.getVelocity());
  }

  public double getHeading() {
    // Redo????
    return Math.IEEEremainder(gyro.getAngle(), 360) * (DriveConstants.kGyroReversed ? -1.0 : 1.0);
  }

  public double getTurnRate() {
    return gyro.getRate() * (DriveConstants.kGyroReversed ? -1.0 : 1.0);
  }

  public Pose2d getPose() {
    return odometry.getPoseMeters();
  }
}
