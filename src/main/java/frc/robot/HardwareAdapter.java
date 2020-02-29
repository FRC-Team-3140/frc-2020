package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import com.kauailabs.navx.frc.AHRS;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PowerDistributionPanel;

public interface HardwareAdapter extends Constants.ElectricalPortConstants {
    // Drivetrain
    public static final CANSparkMax leftDriveMaster = new CANSparkMax(LEFT_DRIVE_MASTER, MotorType.kBrushless);
    public static final CANSparkMax rightDriveMaster = new CANSparkMax(RIGHT_DRIVE_MASTER, MotorType.kBrushless);
    public static final CANSparkMax leftDriveSlave1 = new CANSparkMax(LEFT_DRIVE_SLAVE1, MotorType.kBrushless);
    public static final CANSparkMax rightDriveSlave1 = new CANSparkMax(RIGHT_DRIVE_SLAVE1, MotorType.kBrushless);
    public static final CANSparkMax leftDriveSlave2 = new CANSparkMax(LEFT_DRIVE_SLAVE2, MotorType.kBrushless);
    public static final CANSparkMax rightDriveSlave2 = new CANSparkMax(RIGHT_DRIVE_SLAVE2, MotorType.kBrushless);

    // Climber
    public static final WPI_VictorSPX climberMaster = new WPI_VictorSPX(CLIMBER_MASTER);
    public static final WPI_VictorSPX climberSlave = new WPI_VictorSPX(CLIMBER_SLAVE);

    // Pnuematics
    public static final Compressor compressor = new Compressor(PCM);
    public static final DoubleSolenoid climberLockSolenoid = new DoubleSolenoid(PCM, CLIMBER_LOCK_SOLENOID_EXT,
            CLIMBER_LOCK_SOLENOID_RET);

    // Encoders
    public static final CANEncoder leftEncoder = new CANEncoder(leftDriveMaster);
    public static final CANEncoder rightEncoder = new CANEncoder(rightDriveMaster);

    // Gyro
    public static final AHRS navx = new AHRS(SPI.Port.kMXP);
    
    // Analog Inputs
    // Relays
    // Limit Switches
    // Cameras
}
