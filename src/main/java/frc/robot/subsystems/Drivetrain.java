package frc.robot.subsystems;

import frc.robot.loops.ILooper;
import frc.robot.loops.Loop;

import com.kauailabs.navx.frc.AHRS;
import com.revrobotics.CANSparkMax.IdleMode;

import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveWheelSpeeds;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class Drivetrain extends Subsystem {
  // Variables
  private final AHRS navx = new AHRS(SPI.Port.kMXP);
  private PeriodicIO periodicIO;
  public enum DriveTrainMode {Arcade, Tank, TankVolts};

  // Subsystem Constructor
  public Drivetrain() {
    periodicIO = new PeriodicIO();
    periodicIO.odometry = new DifferentialDriveOdometry(Rotation2d.fromDegrees(getHeading()));

    configMotorControllers();
  }

// Begin generic Subsystem methods section.
  private void configMotorControllers() {
    leftEncoder.setDistancePerRotation(DriveConstants.kEncoderMetersPerPulse);
    rightEncoder.setDistancePerRotation(DriveConstants.kEncoderMetersPerPulse);
    leftDriveSlave1.follow(leftDriveMaster);
    rightDriveSlave1.follow(rightDriveMaster);
    leftDriveMaster.setIdleMode(IdleMode.kBrake);
    rightDriveMaster.setIdleMode(IdleMode.kBrake);
  }

  private void arcade(double throttle, double heading) {
    tankDrive(throttle - heading, throttle + heading);
  }

  private void tank(double left, double right) {
    leftDriveMaster.set(left);
    rightDriveMaster.set(right);
  }

  private void tankVolts(double leftVolts, double rightVolts) {
    /*
    leftVolts = leftVolts/12;
    if(Math.abs(leftVolts) > 12)
      leftVolts = Math.signum(leftVolts) * 12;

    rightVolts = rightVolts/12;
    if(Math.abs(rightVolts) > 12)
      rightVolts = Math.signum(rightVolts) * 12;
    */
    leftDriveMaster.setVoltage(leftVolts);
    rightDriveMaster.setVoltage(rightVolts);
  }
// End generic Subsystem methods section.

// Begin syncronized IO section.
  public static class PeriodicIO {
    // Inputs
    public double timestamp;
    public DriveTrainMode dtMode;
    public double leftDistance;
    public double rightDistance;
    public double leftVelocity;
    public double rightVelocity;
    public double averageVelocity;
    public double averagevelocityMagnitude;
    public double angularVelocity;
    public double heading;
    public DifferentialDriveOdometry odometry;
    public Pose2d currentPose;

    // Outputs
    // Arcade Drive
    public double throttle;
    public double headingThrottle;
    // Tank Drive
    public double leftThrottle;
    public double rightThrottle;
    // Tank Drive Volts
    public double leftVolts;
    public double rightVolts;
  }

  @Override
  public synchronized void readPeriodicInputs() {
    periodicIO.timestamp = Timer.getFPGATimestamp();
    periodicIO.leftDistance = leftEncoder.getDistance();
    periodicIO.rightDistance = -rightEncoder.getDistance();
    periodicIO.leftVelocity = leftEncoder.getRate();
    periodicIO.rightVelocity = -rightEncoder.getRate();
    periodicIO.averageVelocity = (periodicIO.leftVelocity + periodicIO.rightVelocity) / 2.0;
    periodicIO.averagevelocityMagnitude = (Math.abs(periodicIO.leftVelocity) + Math.abs(periodicIO.rightVelocity)) / 2.0;
    periodicIO.angularVelocity = (periodicIO.rightVelocity - periodicIO.leftVelocity) / DriveConstants.kTrackwidthMeters;
    periodicIO.heading = navx.getYaw() * (DriveConstants.kGyroReversed ? -1.0 : 1.0);
    periodicIO.odometry.update(Rotation2d.fromDegrees(periodicIO.heading), periodicIO.leftDistance, periodicIO.rightDistance);
    periodicIO.currentPose = periodicIO.odometry.getPoseMeters();
  }

  @Override
  public synchronized void writePeriodicOutputs() {
    switch(periodicIO.dtMode) {
      case Arcade:
        arcade(periodicIO.throttle, periodicIO.headingThrottle);
        break;
      case Tank:
        tank(periodicIO.leftThrottle, periodicIO.rightThrottle);       
        break;
      case TankVolts:
        tankVolts(periodicIO.leftVolts, periodicIO.rightVolts);
        break;
    }
  }
// End synchronized IO section.

// Begin loop management section.
  @Override
  public void registerEnabledLoops(ILooper in) {
    in.register(new Loop() {
      @Override
      public void onStart(double timestamp) {
        synchronized(Drivetrain.this) {
          stop();
        }
      }

      @Override
      public void onLoop(double timestamp) {
        synchronized(Drivetrain.this) {
          // On Loop Code
        }
      }

      @Override
      public void onStop(double timestamp) {
        stop();
      }
    });
  }
// End loop management section.


// Begin zeroing sensors section.
  private void zeroEncoders() {
    leftEncoder.reset();
    rightEncoder.reset();
  }

  private void zeroGyro() {
    navx.reset();
  }
  
  private void zeroOdometry() {
    Pose2d defaultPose = new Pose2d();
    periodicIO.odometry.resetPosition(defaultPose, Rotation2d.fromDegrees(getHeading()));
  }
  
  @Override
  public void zeroSensors() {
    zeroEncoders();
    zeroGyro();
    zeroOdometry();
  }
// End zeroing sensors section.

// Begin data accessor section.
  public double getLeftEncoderDistance() {
    return periodicIO.leftDistance;
  }

  public double getRightEncoderDistance() {
    return periodicIO.rightDistance;
  }

  public double getLeftEncoderVelocity() {
    return periodicIO.leftVelocity;
  }

  public double getRightEncoderVelocity() {
    return periodicIO.rightVelocity;
  }

  public double getAverageEncoderVelocity() {
    return periodicIO.averageVelocity;
  }

  public double getAverageEncoderVelocityMagnitude() {
    return periodicIO.averagevelocityMagnitude;
  }

  public double getAngularVelocity() {
    return periodicIO.angularVelocity;
  }

  public DifferentialDriveWheelSpeeds getWheelSpeeds() {
    return new DifferentialDriveWheelSpeeds(periodicIO.leftVelocity, periodicIO.rightVelocity);
  }

  public double getHeading() {
    return periodicIO.heading;
  }

<<<<<<< HEAD
  public Pose2d getCurrentPose() {
    return periodicIO.currentPose;
  }

  public synchronized double getTimestamp() {
    return periodicIO.timestamp;
  }

  public synchronized DriveTrainMode getCurrentMode() {
    return periodicIO.dtMode;
  }
// End data accessor section.

// Begin data mutators section.
  public synchronized void setBrakeMode(boolean brake) {
    if(brake) {
      leftDriveMaster.setIdleMode(IdleMode.kBrake);
      rightDriveMaster.setIdleMode(IdleMode.kBrake);
    }
    else {
      leftDriveMaster.setIdleMode(IdleMode.kCoast);
      rightDriveMaster.setIdleMode(IdleMode.kCoast);
    }
  }

  public synchronized void arcadeDrive(double throttle, double headingThrottle) {
    periodicIO.dtMode = DriveTrainMode.Arcade;
    periodicIO.throttle = throttle;
    periodicIO.headingThrottle = headingThrottle;
  }

  public synchronized void tankDrive(double leftThrottle, double rightThrottle) {
    periodicIO.dtMode = DriveTrainMode.Tank;
    periodicIO.leftThrottle = leftThrottle;
    periodicIO.rightThrottle = rightThrottle;
  }

  public synchronized void tankDriveVolts(double leftVolts, double rightVolts) {
    periodicIO.dtMode = DriveTrainMode.TankVolts;
    periodicIO.leftVolts = leftVolts;
    periodicIO.rightVolts = rightVolts;
  }
// End data mutators section.

// Begin telemetry updating section.
  @Override
  public void outputTelemetry() {
    SmartDashboard.putNumber("Gyro Heading (deg): ", periodicIO.heading);
    SmartDashboard.putNumber("Left Encoder Distance (m): ", periodicIO.leftDistance);
    SmartDashboard.putNumber("Right Encoder Distance (m): ", periodicIO.rightDistance);
    SmartDashboard.putNumber("Left Encoder Velocity (m/s): ", periodicIO.leftVelocity);
    SmartDashboard.putNumber("Right Encoder Velocity (m/s): ", periodicIO.rightVelocity);
    SmartDashboard.putNumber("Average Velocity (m/s): ", periodicIO.averageVelocity);

    // Debugging
    // System.out.println("Left Volts: " + periodicIO.leftVolts);
    // System.out.println("Right Volts: " + periodicIO.rightVolts);
    System.out.println("Left Encoder Velocity (m/s): " + periodicIO.leftVelocity); 
    System.out.println("Right Encoder Velocity (m/s): " + periodicIO.rightVelocity); 
  }
// End telemetry updating section.

// Start loop specific methods.
  @Override
  public void stop() {
    // Code to run on once when the loop changes state to not running.
=======
  public Pose2d getPose() {
    return odometry.getPoseMeters();
>>>>>>> parent of f1874c7... Minor fix and build test.
  }
// End loop specific methods.
}
