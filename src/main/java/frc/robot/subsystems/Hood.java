/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.HardwareAdapter;

public class Hood extends SubsystemBase implements HardwareAdapter {
  public Hood() {
		setElevatorDefaults();
		configSensors();
		zeroSensors();
  }
  
  @Override
  public void periodic() {
    // Sets encoders to 0 if the arm is at the bottom (this helps to avoid offset)
    if (isArmAtBottom())
			zeroSensors();
  }

	
	/*************************
	 * TALON SUPPORT METHODS *
	 ************************/
	private void configSensors() {
		hoodMotor.configSelectedFeedbackSensor(magEncoder, pidIdx, timeout);
		hoodMotor.setSensorPhase(true);
	}
	
	private void setBrakeMode() {
		hoodMotor.setNeutralMode(BRAKE_MODE);
		hoodMotor.setNeutralMode(BRAKE_MODE);
	}
	
	private void setCtrlMode() {
	}
	
	private void setElevatorDefaults() {
		setCtrlMode();
		setBrakeMode();
		hoodMotor.setInverted(true);
	}

	/**************************
	 * SENSOR SUPPORT METHODS *
	 **************************/
	public void zeroSensors() {
		hoodMotor.getSensorCollection().setQuadraturePosition(0, 10);
	}
	
	// Checks if the intake is at bottom
	public boolean isArmAtBottom() {
		return !bottomSwitch.get();
	}
	
	// Checks if intake is at the top
	public boolean isArmAtTop() {
		return !topSwitch.get();
	}
	
	// Returns whether or not the intake has reached the set position. Pos is in inches
	public boolean isIntakeAtPos(double pos) {
		return Math.abs(getDistanceFromPos(pos)) < hoodTolerance;
	}
	
	public boolean isIntakeAbovePosition(double pos) {
		return getDistanceFromPos(pos) < 0;
	}
	
	/*
	 * CURRENT METHODS
	 */
	public double getElevatorMasterCurrent() {
		return hoodMotor.getOutputCurrent();
	}
	
	/**********************
	 * ENC OUTPUT METHODS *
	 **********************/
	public double getElevatorVelocity() {
		return hoodMotor.getSelectedSensorVelocity(pidIdx);
	}
	
	// Gets the number of revolutions of the encoder
	private double getElevatorRevs() {
		return hoodMotor.getSelectedSensorPosition(pidIdx) / quadConversionFactor;
	}
	
	public double getTicksTravelled() {
		return hoodMotor.getSelectedSensorPosition(pidIdx);
	}
	
	// Get the distance the elevator has traveled in inches
	public double getDistanceTravelled() {
		return 2 * getElevatorRevs() * spindleCircum / elevatorGearRatio;
	}
	
	// Returns distance from a set position
	private double getDistanceFromPos(double pos) {
		return pos - getDistanceTravelled();
	}

		
	public int distanceToTicks(double distanceInches) {
		return (int) (EncoderHelper.inchesToEncoderTicks(distanceInches, spindleCircum, quadConversionFactor) * elevatorGearRatio / 2);
	}
	

			
	public void moveWithJoystick(double throttle) {
		move(driveHelper.handleDeadband(throttle, elevatorDeadband));
	}
	
	public void move(double throttle) {
    hoodMotor.set(throttle);
		SmartDashboard.putNumber("Elevator Input", throttle);
		if((isArmAtTop() && throttle > 0) || (isArmAtBottom() && throttle < 0))
			throttle = 0.0;
		elevatorMaster.set(driveHelper.handleOverPower(throttle));
	}
}
