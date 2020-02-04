/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.kauailabs.navx.frc.AHRS;
import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.ControlType;
import com.revrobotics.CANSparkMax.IdleMode;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.trajectory.TrapezoidProfile;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.HardwareAdapter;

public class Drivetrain extends SubsystemBase implements Constants, HardwareAdapter {

  private double kP = 1.5;
  private double VISION_TARGET_TOL = .05;

  private CANPIDController leftPIDController, rightPIDController;
  private CANEncoder leftEncoder, rightEncoder;

  private Timer timer = new Timer();
  private AHRS navX;

  private double turnP = DRIVE_TURN_P, turnI = DRIVE_TURN_I, turnD = DRIVE_RIGHT_D;

  private TrapezoidProfile rightProfile, leftProfile;

  public Drivetrain() {
    setFollowers();
    configPID();
    leftEncoder.setPositionConversionFactor(.0233*42);
    rightEncoder.setPositionConversionFactor(.0233*42);

    leftDriveMaster.setIdleMode(IdleMode.kBrake);
    rightDriveMaster.setIdleMode(IdleMode.kBrake);
    leftDriveSlave1.setIdleMode(IdleMode.kBrake);
    rightDriveSlave1.setIdleMode(IdleMode.kBrake);
    try {
      navX = new AHRS(SPI.Port.kMXP); 
  } catch (RuntimeException ex ) {
      DriverStation.reportError("Error instantiating navX-MXP:  " + ex.getMessage(), true);
  }

  }

  // very primitive turn to target method
  public void turnToVisionTarget() {
    double x = SmartDashboard.getNumber("x", 0) - 0.5;
    arcadeDrive(0,kP * x);
  }

  public boolean isFacingVisionTarget() {
    double x = SmartDashboard.getNumber("x", 0) - 0.5;
    if(Math.abs(x) < VISION_TARGET_TOL) return true;
    else return false;
  }

  // moves robot wheels to a certain position
  public void moveToPos(double inches) {
    leftPIDController.setReference(inches*DT_TICKS_TO_INCHES, ControlType.kPosition);
    rightPIDController.setReference(inches*DT_TICKS_TO_INCHES, ControlType.kPosition);
  }

  // creates trapezoidal profiles
  public void setPosTrapProfile(double inches) {
    leftProfile = new TrapezoidProfile(new TrapezoidProfile.Constraints(DT_MAX_VELOCITY, DT_MAX_ACCEL), new TrapezoidProfile.State(inches, 0),
        new TrapezoidProfile.State(0, 0));
    rightProfile = new TrapezoidProfile(new TrapezoidProfile.Constraints(DT_MAX_VELOCITY, DT_MAX_ACCEL), new TrapezoidProfile.State(inches, 0),
        new TrapezoidProfile.State(0, 0));
  }

  // moves to whatever profile is currently generated in the class
  public void moveToPosProfile() {
    double timeStep = ((int) (timer.get() / SETPOINT_DT) + 1) * SETPOINT_DT;
    TrapezoidProfile.State lState = leftProfile.calculate(timeStep);
    TrapezoidProfile.State rState = rightProfile.calculate(timeStep);
    System.out.println("left: " + lState.position + "     right: " + rState.position);

    leftPIDController.setReference(lState.position * DT_TICKS_TO_INCHES, ControlType.kPosition, 0, DRIVE_LEFT_FF*lState.velocity);
    rightPIDController.setReference(rState.position * DT_TICKS_TO_INCHES, ControlType.kPosition, 0, DRIVE_RIGHT_FF*rState.velocity);
  }

  double lastError=0;
  double lastIntegral = 0;
  double lastTimerValue = 0;
  public void turnToAngle(double angle) {
    double error = angle - navX.getAngle();
    double p = DRIVE_TURN_P * error;
    lastIntegral += error;
    double i = (lastIntegral) * DRIVE_TURN_I;
    System.out.println(timer.get()-lastTimerValue);
    double d = DRIVE_TURN_D *(error-lastError) / (timer.get()-lastTimerValue);
    lastTimerValue = timer.get();
    lastError = error;

    double output = 12*(p+i+d);
    System.out.println("output in turn to angle: " + output);
    rightDriveMaster.setVoltage(-output);
    leftDriveMaster.setVoltage( output);
    System.out.println("turn to angle outputs: " +leftDriveMaster.get() + " "+ rightDriveMaster.get());

  }

  public boolean isAtAngle(double angle) {
    return Math.abs(navX.getAngle()-angle) < DT_TOL_ANGLE;
  }

  public boolean isLeftAtPos(double inches) {
    return Math.abs(leftEncoder.getPosition()/DT_TICKS_TO_INCHES - inches) < DT_TOL_IN;
  }
  
  public boolean isRightAtPos(double inches) {
    return Math.abs(rightEncoder.getPosition()/DT_TICKS_TO_INCHES - inches) < DT_TOL_IN;
  }

  public void resetSensorsForPID() {
    resetEncoders();
    navX.reset();
  }

  public void resetEncoders() {
    leftEncoder.setPosition(0);
    rightEncoder.setPosition(0);
  }

  public void restartTimer() {
    timer.reset();
    timer.start();
  }

  public void arcadeDrive(double throttle, double heading) {
    tankDrive(throttle - heading, throttle + heading);
  }

  public void tankDrive(double left, double right) {
    SmartDashboard.putNumber("left input", left);
    SmartDashboard.putNumber("right input", right);
    leftDriveMaster.set(left);
    rightDriveMaster.set(right);
  }

  public void stop() {
    leftDriveMaster.set(0);
    rightDriveMaster.set(0);
  }

  public void toSB() {
    SmartDashboard.putNumber("DT Left", leftEncoder.getPosition());
    SmartDashboard.putNumber("DT Right", rightEncoder.getPosition());
    //SmartDashboard.putNumber("DT encoder factor", leftEncoder.getPositionConversionFactor());

    SmartDashboard.putNumber("DT Left Inches", leftEncoder.getPosition()*DT_INCHES_TO_TICKS);
    SmartDashboard.putNumber("DT Right Inches", rightEncoder.getPosition()*DT_INCHES_TO_TICKS);

    SmartDashboard.putNumber("Gyro Heading", navX.getAngle());
  }

  public void updatePIDVals(double leftP, double leftI, double leftD, double rightP, double rightI, double rightD, 
                              double tP, double tI, double tD) {
    leftPIDController.setP(leftP);
    leftPIDController.setI(leftI);
    leftPIDController.setD(leftD);
    rightPIDController.setP(rightP);
    rightPIDController.setI(rightI);
    rightPIDController.setD(rightD);
    turnP = tP;
    turnI = tI;
    turnD = tD;
  }

  private void configPID() {
    leftPIDController = leftDriveMaster.getPIDController();
    rightPIDController = rightDriveMaster.getPIDController();
    leftEncoder = leftDriveMaster.getEncoder();
    rightEncoder = rightDriveMaster.getEncoder();

    leftPIDController.setP(DRIVE_LEFT_P);
    leftPIDController.setI(DRIVE_LEFT_I);
    leftPIDController.setD(DRIVE_LEFT_D);
    rightPIDController.setP(DRIVE_RIGHT_P);
    rightPIDController.setI(DRIVE_RIGHT_I);
    rightPIDController.setD(DRIVE_RIGHT_D);
    leftPIDController.setOutputRange(-DT_MAX_INPUT_PID, DT_MAX_INPUT_PID);
    rightPIDController.setOutputRange(-DT_MAX_INPUT_PID, DT_MAX_INPUT_PID);

    leftDriveMaster.setClosedLoopRampRate(DT_RAMP_RATE);
    rightDriveMaster.setClosedLoopRampRate(DT_RAMP_RATE);
  }

  private void setFollowers() {
    leftDriveSlave1.follow(leftDriveMaster);
   // leftDriveSlave2.follow(leftDriveMaster);
    rightDriveSlave1.follow(rightDriveMaster);
    //rightDriveSlave2.follow(rightDriveMaster);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    toSB();
  }
}
