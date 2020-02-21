package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public interface HardwareAdapter extends Constants.ElectricalPortConstants {
    // Shooter Rotary Components
    public static final WPI_TalonSRX turretMotor = new WPI_TalonSRX(TURRET_MOTOR);
}
