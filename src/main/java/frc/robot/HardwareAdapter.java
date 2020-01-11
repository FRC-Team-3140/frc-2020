/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;

/**
 * Instantiates the hardware
 */
public interface HardwareAdapter extends Constants {

    // DRIVETRAIN
   /* public static final CANSparkMax leftDriveMaster = new CANSparkMax(2, MotorType.kBrushless);
    public static final CANSparkMax rightDriveMaster = new CANSparkMax(3, MotorType.kBrushless);
    public static final CANSparkMax leftDriveSlave1 = new CANSparkMax(4, MotorType.kBrushless);
    public static final CANSparkMax rightDriveSlave1 = new CANSparkMax(5, MotorType.kBrushless);
    public static final CANSparkMax leftDriveSlave2 = new CANSparkMax(6, MotorType.kBrushless);
	public static final CANSparkMax rightDriveSlave2 = new CANSparkMax(7, MotorType.kBrushless);*/
	
	public static final WPI_TalonSRX leftDriveMaster = new WPI_TalonSRX(LEFT_DRIVE_MASTER);
	public static final WPI_TalonSRX rightDriveMaster = new WPI_TalonSRX(RIGHT_DRIVE_MASTER);
	public static final WPI_TalonSRX leftDriveSlave1 = new WPI_TalonSRX(LEFT_DRIVE_SLAVE1);
	public static final WPI_TalonSRX rightDriveSlave1 = new WPI_TalonSRX(RIGHT_DRIVE_SLAVE1);

	public static final Compressor compressor = new Compressor(PCM);

	public static final DoubleSolenoid shifter = new DoubleSolenoid(PCM,2,5);


}
