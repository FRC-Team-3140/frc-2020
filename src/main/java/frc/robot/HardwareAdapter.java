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

	// PNEUMATICS
    public static final DoubleSolenoid intakeSolenoid = new DoubleSolenoid(PCM,2,5);
    
    // FLYWHEEL
    public static final CANSparkMax flyWheelMaster = new CANSparkMax(FLYWHEEL_MASTER, MotorType.kBrushless);
    public static final CANSparkMax flyWheelSlave1 = new CANSparkMax(FLYWHEEL_SLAVE1, MotorType.kBrushless);
    public static final CANSparkMax flyWheelSlave2 = new CANSparkMax(FLYWHEEL_SLAVE2, MotorType.kBrushless);

    // FEEDER
    public static final CANSparkMax feeder = new CANSparkMax(FEEDER, MotorType.kBrushless);

    // ANGLED HOOD
    public static final WPI_TalonSRX hoodMotor = new WPI_TalonSRX(HOOD_MOTOR);

    // TURRET
    public static final WPI_TalonSRX turretMotor = new WPI_TalonSRX(TURRET_MOTOR);
    
    // CHAMBER/FEEDER LIMIT SWITCHES
    public static final DigitalInput chamberOut = new DigitalInput(CHAMBER_OUT);
    public static final DigitalInput chamber1 = new DigitalInput(CHAMBER_1);
    public static final DigitalInput chamber2 = new DigitalInput(CHAMBER_2);
    public static final DigitalInput chamber3 = new DigitalInput(CHAMBER_3);
    public static final DigitalInput chamber4 = new DigitalInput(CHAMBER_4);
    public static final DigitalInput chamber5 = new DigitalInput(CHAMBER_5);

}
