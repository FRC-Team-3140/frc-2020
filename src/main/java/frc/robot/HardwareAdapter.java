package frc.robot;

import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.SPI;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import frc.libs.VelocityDutyCycleEncoder;
import frc.robot.Constants.DriveConstants;

public interface HardwareAdapter {
    public static final CANSparkMax leftDriveMaster = new CANSparkMax(DriveConstants.LEFT_DRIVE_MASTER,
            MotorType.kBrushless);
    public static final CANSparkMax rightDriveMaster = new CANSparkMax(DriveConstants.RIGHT_DRIVE_MASTER,
            MotorType.kBrushless);
    public static final CANSparkMax leftDriveSlave1 = new CANSparkMax(DriveConstants.LEFT_DRIVE_SLAVE1,
            MotorType.kBrushless);
    public static final CANSparkMax rightDriveSlave1 = new CANSparkMax(DriveConstants.RIGHT_DRIVE_SLAVE1,
            MotorType.kBrushless);

    public static final VelocityDutyCycleEncoder leftEncoder = new VelocityDutyCycleEncoder(DriveConstants.Left_Encoder_PWM_PORT);
    public static final VelocityDutyCycleEncoder rightEncoder = new VelocityDutyCycleEncoder(DriveConstants.Right_Encoder_PWM_PORT);

    public static final AHRS navx = new AHRS(SPI.Port.kMXP);
}
