package frc.robot;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
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

    public final SpeedControllerGroup leftMotors = new SpeedControllerGroup(leftDriveMaster, leftDriveSlave1);
    public final SpeedControllerGroup rightMotors = new SpeedControllerGroup(rightDriveMaster, rightDriveSlave1);

    public static final CANEncoder leftEncoder = leftDriveMaster.getEncoder();
    public static final CANEncoder rightEncoder = rightDriveMaster.getEncoder();
}
