package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public interface HardwareAdapter extends Constants.ElectricalPortConstants {
    // Shooter
    public static final CANSparkMax flyWheelMaster = new CANSparkMax(FLYWHEEL_MASTER, MotorType.kBrushless);
    public static final CANSparkMax flyWheelSlave1 = new CANSparkMax(FLYWHEEL_SLAVE, MotorType.kBrushless);

    // Shooter Rotary Components
    public static final WPI_TalonSRX hoodMotor = new WPI_TalonSRX(HOOD_MOTOR);
    public static final WPI_TalonSRX turretMotor = new WPI_TalonSRX(TURRET_MOTOR);
}
