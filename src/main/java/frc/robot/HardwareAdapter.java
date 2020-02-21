package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public interface HardwareAdapter extends Constants.ElectricalPortConstants {
    // Shooter Rotary Components
    public static final WPI_TalonSRX hoodMotor = new WPI_TalonSRX(HOOD_MOTOR);
}
