package frc.robot;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public interface HardwareAdapter extends Constants.ElectricalPortConstants {
    // Shooter
    public static final CANSparkMax flyWheelMaster = new CANSparkMax(FLYWHEEL_MASTER, MotorType.kBrushless);
    public static final CANSparkMax flyWheelSlave1 = new CANSparkMax(FLYWHEEL_SLAVE, MotorType.kBrushless);
}
