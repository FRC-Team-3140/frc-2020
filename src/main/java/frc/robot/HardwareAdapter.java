package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;

public interface HardwareAdapter extends Constants {

    // DRIVETRAIN
    public static final CANSparkMax leftDriveMaster = new CANSparkMax(3, MotorType.kBrushless);
    public static final CANSparkMax rightDriveMaster = new CANSparkMax(2, MotorType.kBrushless);
    public static final CANSparkMax leftDriveSlave1 = new CANSparkMax(4, MotorType.kBrushless);
    public static final CANSparkMax rightDriveSlave1 = new CANSparkMax(5, MotorType.kBrushless);
    //public static final CANSparkMax leftDriveSlave2 = new CANSparkMax(6, MotorType.kBrushless);
	//public static final CANSparkMax rightDriveSlave2 = new CANSparkMax(7, MotorType.kBrushless);

	public static final Compressor compressor = new Compressor(PCM);

<<<<<<< HEAD
	// PNEUMATICS
	public static final DoubleSolenoid intakeSolenoid = new DoubleSolenoid(PCM,2,5);
=======
    //public static final DoubleSolenoid shifter = new DoubleSolenoid(PCM,2,5);
    
    public static final WPI_TalonSRX climberMaster = new WPI_TalonSRX(CLIMBER_MASTER);
    public static final WPI_TalonSRX climberSlave = new WPI_TalonSRX(CLIMBER_SLAVE);
    public static final DigitalInput climberBot = new DigitalInput(5);
    public static final DigitalInput climberTop = new DigitalInput(6);

>>>>>>> origin/climber


}
