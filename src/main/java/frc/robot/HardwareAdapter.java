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

	// INTAKE
	public static WPI_TalonSRX intakeMotor = new WPI_TalonSRX(INTAKE_MOTOR);

	public static final Compressor compressor = new Compressor(PCM);
    
    public static final WPI_TalonSRX climberMaster = new WPI_TalonSRX(CLIMBER_MASTER);
    public static final WPI_TalonSRX climberSlave = new WPI_TalonSRX(CLIMBER_SLAVE);
    public static final DigitalInput climberBot = new DigitalInput(5);
    public static final DigitalInput climberTop = new DigitalInput(6);

    public static final DoubleSolenoid intakeSolenoid = new DoubleSolenoid(PCM,INTAKE_SOLENOID_EXT,INTAKE_SOLENOID_RET);
    
    // FLYWHEEL
    public static final CANSparkMax flyWheelMaster = new CANSparkMax(FLYWHEEL_MASTER, MotorType.kBrushless);
    public static final CANSparkMax flyWheelSlave1 = new CANSparkMax(FLYWHEEL_SLAVE1, MotorType.kBrushless);

    // FEEDER
    public static final CANSparkMax feeder = new CANSparkMax(FEEDER, MotorType.kBrushless);
    public static final WPI_TalonSRX feeder2 = new WPI_TalonSRX(FEEDER_2);

    // ANGLED HOOD
    public static final WPI_TalonSRX hoodMotor = new WPI_TalonSRX(HOOD_MOTOR);

    // TURRET
    public static final WPI_TalonSRX turretMotor = new WPI_TalonSRX(TURRET_MOTOR);
    
    // CHAMBER/FEEDER LIMIT SWITCHES
    public static final DigitalInput chamberOut = new DigitalInput(CHAMBER_OUT); // switch near shooter
    public static final DigitalInput chamberIn = new DigitalInput(CHAMBER_IN); // "entrance" switch
}
