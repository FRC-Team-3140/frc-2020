package frc.robot;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.Servo;

public interface HardwareAdapter extends Constants {

    public static final Servo servo = new Servo(0);

    // DRIVETRAIN
    public static final CANSparkMax leftDriveMaster = new CANSparkMax(LEFT_DRIVE_MASTER, MotorType.kBrushless);
    public static final CANSparkMax rightDriveMaster = new CANSparkMax(RIGHT_DRIVE_MASTER, MotorType.kBrushless);
    public static final CANSparkMax leftDriveSlave1 = new CANSparkMax(LEFT_DRIVE_SLAVE1, MotorType.kBrushless);
    public static final CANSparkMax rightDriveSlave1 = new CANSparkMax(RIGHT_DRIVE_SLAVE1, MotorType.kBrushless);
    public static final CANSparkMax leftDriveSlave2 = new CANSparkMax(LEFT_DRIVE_SLAVE2, MotorType.kBrushless);
	public static final CANSparkMax rightDriveSlave2 = new CANSparkMax(RIGHT_DRIVE_SLAVE2, MotorType.kBrushless);

	//public static final Compressor compressor = new Compressor(PCM);

	//public static final DoubleSolenoid shifter = new DoubleSolenoid(PCM,2,5);


}
