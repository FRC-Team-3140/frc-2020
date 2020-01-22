package frc.robot.subsystems;

import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.SPI;
//import com.revrobotics.CANEncoder;

//import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveWheelSpeeds;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.robot.HardwareAdapter;
import frc.robot.Constants;

public class Drivetrain extends SubsystemBase implements HardwareAdapter, Constants {
  //private final DifferentialDrive differentialDrive = new DifferentialDrive(leftMotors, rightMotors);
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
/*
  public void setMaxOutput(double maxOutput) {
    differentialDrive.setMaxOutput(maxOutput);
  }
  */

  @Override
  public void periodic() {
    //Try negating either side's encoders // Didn't help
    odometry.update(Rotation2d.fromDegrees(getHeading()), leftEncoder.getPosition(), rightEncoder.getPosition());
    SmartDashboard.putNumber("Gyro Heading: ", getHeading());
    SmartDashboard.putNumber("Left Encoder: ", leftEncoder.getPosition());
    SmartDashboard.putNumber("Right Encoder: ", rightEncoder.getPosition());
  }

  public void arcadeDrive(double throttle, double heading) {
    tankDrive(throttle - heading, throttle + heading);
  }

  public void tankDrive(double left, double right) {
    leftDriveMaster.set(left);
    rightDriveMaster.set(right);
    //differentialDrive.tankDrive(left, right, false);
  }

  public void tankDriveVolts(double leftVolts, double rightVolts) {
    // leftMotors.setVoltage(leftVolts);
    //rightMotors.setVoltage(rightVolts);
    //differentialDrive.tankDrive(leftVolts/12, rightVolts/12, false);
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
/*
  public void resetOdometry(Pose2d pose) {
    resetEncoders();
    // Reset Gyro Too?
    odometry.resetPosition(pose, Rotation2d.fromDegrees(getHeading()));
  }
*/
  //public CANEncoder getLeftEncoder() {
    //return leftEncoder;
  //}

  //public CANEncoder getRightEncoder() {
    //return rightEncoder;
  //}

  //public double getAverageEncoderDistance() {
    //return (leftEncoder.getPosition() + rightEncoder.getPosition()) / 2.0;
  //}

  public DifferentialDriveWheelSpeeds getWheelSpeeds() {
    //TODO: Remove division by 60???? // Didn't help
    //Try negating either side's encoders // Didn't help
    return new DifferentialDriveWheelSpeeds(leftEncoder.getVelocity(), rightEncoder.getVelocity());
  }

  public double getHeading() {
    // -180deg to 180deg
    double yaw = navx.getYaw();
    // Convert to 0-360deg
    //yaw -= 180;
    // Return angle with reverse correction if needed
    return yaw * (DriveConstants.kGyroReversed ? -1.0 : 1.0);
  }

  //public double getTurnRate() {
    //return navx.getRate() * (DriveConstants.kGyroReversed ? -1.0 : 1.0);
  //}

  public Pose2d getPose() {
    return odometry.getPoseMeters();
  }
}
