package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;

public interface HardwareAdapter extends Constants {

    // DRIVETRAIN
    public static final WPI_TalonSRX leftDriveMaster = new WPI_TalonSRX(2);
    public static final WPI_TalonSRX rightDriveMaster = new WPI_TalonSRX(3);
    public static final WPI_TalonSRX leftDriveSlave1 = new WPI_TalonSRX(4);
    public static final WPI_TalonSRX rightDriveSlave1 = new WPI_TalonSRX(5);
    //public static final CANSparkMax leftDriveSlave2 = new CANSparkMax(6, MotorType.kBrushless);
	//public static final CANSparkMax rightDriveSlave2 = new CANSparkMax(7, MotorType.kBrushless);

	public static final Compressor compressor = new Compressor(PCM);

	public static final DoubleSolenoid shifter = new DoubleSolenoid(PCM,2,5);


}
