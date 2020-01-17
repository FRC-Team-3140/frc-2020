package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;

public interface HardwareAdapter extends Constants {

    // DRIVETRAIN
    public static final CANSparkMax leftDriveMaster = new CANSparkMax(3, MotorType.kBrushless);
    public static final CANSparkMax rightDriveMaster = new CANSparkMax(2, MotorType.kBrushless);
    public static final CANSparkMax leftDriveSlave1 = new CANSparkMax(4, MotorType.kBrushless);
    public static final CANSparkMax rightDriveSlave1 = new CANSparkMax(5, MotorType.kBrushless);
    //public static final CANSparkMax leftDriveSlave2 = new CANSparkMax(6, MotorType.kBrushless);
	//public static final CANSparkMax rightDriveSlave2 = new CANSparkMax(7, MotorType.kBrushless);

	//public static final Compressor compressor = new Compressor(PCM);

	//public static final DoubleSolenoid shifter = new DoubleSolenoid(PCM,2,5);


}
