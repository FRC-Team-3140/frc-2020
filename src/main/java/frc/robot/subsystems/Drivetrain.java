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
  private boolean reversedTrajectory = false;

  public boolean isTrajectoryReversed() {
    return reversedTrajectory;
  }

  public void setTrajectoryReversed(boolean reversed) {
    reversedTrajectory = reversed;
  }

  public Drivetrain() {
    setupMotors();

    leftEncoder.setPositionConversionFactor(DriveConstants.kEncoderMetersPerPulse);
    rightEncoder.setPositionConversionFactor(DriveConstants.kEncoderMetersPerPulse);
    leftEncoder.setVelocityConversionFactor(DriveConstants.kEncoderLinearMetersPerSecondPerRPM);
    rightEncoder.setVelocityConversionFactor(DriveConstants.kEncoderLinearMetersPerSecondPerRPM);

    resetEncoders();
    resetGyro();
    odometry = new DifferentialDriveOdometry(Rotation2d.fromDegrees(getHeading()));
  }

  private void setupMotors() {
    boolean leftInverted = true;
    boolean rightInverted = false;
    if(!DriveConstants.isCompetition) {
      leftInverted = false;
      rightInverted = true;
    }

    leftDriveMaster.setInverted(leftInverted);
    leftDriveSlave1.follow(leftDriveMaster);
    leftDriveSlave1.setInverted(leftInverted);
    // Comment the 2 lines below out when working on the chassis bot
    //leftDriveSlave2.follow(leftDriveMaster);
    //leftDriveSlave2.setInverted(leftInverted);

    rightDriveMaster.setInverted(rightInverted);
    rightDriveSlave1.follow(rightDriveMaster);
    rightDriveSlave1.setInverted(rightInverted);
    // Comment the 2 lines below out when working on the chassis bot
    //rightDriveSlave2.follow(rightDriveMaster);
    //rightDriveSlave2.setInverted(rightInverted);

    setIdleMode(IdleMode.kCoast);
  }

  public IdleMode getIdleMode() {
    return leftDriveMaster.getIdleMode();
  }

  public void setIdleMode(IdleMode mode) {
    leftDriveMaster.setIdleMode(mode);
    leftDriveSlave1.setIdleMode(mode);
    // Comment the line below out when working on the chassis bot
    //leftDriveSlave2.setIdleMode(mode);

    rightDriveMaster.setIdleMode(mode);
    rightDriveSlave1.setIdleMode(mode);
    // Comment the line below out when working on the chassis bot
    //rightDriveSlave2.setIdleMode(mode);
  }

  public double getLeftEncoderDistance() {
    double output = leftEncoder.getPosition();

    if(reversedTrajectory) output *= -1;
    return output;
  }

  public double getRightEncoderDistance() {
    double output = rightEncoder.getPosition();

    if(reversedTrajectory) output *= -1;
    return output;
  }

  public double getLeftEncoderVelocity() {
    double output = rightEncoder.getVelocity();

    if(reversedTrajectory) output *= -1;
    return output;
  }

  public double getRightEncoderVelocity() {
    double output = rightEncoder.getVelocity();

    if(reversedTrajectory) output *= -1;
    return output;
  }

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
      leftVolts *= -1;
      rightVolts *= -1;
      double temp = rightVolts;
      rightVolts = leftVolts;
      leftVolts = temp;
    }   

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
  
  private void resetOdometry() {
    Pose2d defaultPose = new Pose2d();
    odometry.resetPosition(defaultPose, Rotation2d.fromDegrees(getHeading()));
  }
  
  public void resetAll() {
    resetEncoders();
    resetGyro();
    resetOdometry();
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
    double output = navx.getYaw() * (DriveConstants.kGyroReversed ? -1.0 : 1.0);
    if(reversedTrajectory) {
       //output *= -1;
       //output = -Math.signum(output) * (180 - Math.abs(output));
    }
    return output;
  }

  public Pose2d getCurrentPose() {
    return odometry.getPoseMeters();
  }
}
