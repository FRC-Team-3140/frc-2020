package frc.robot;

//import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

// import edu.wpi.first.wpilibj.DutyCycleEncoder;
// import edu.wpi.first.wpilibj.Encoder;
import frc.lib.VelocityDutyCycleEncoder;
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

    //public static final CANEncoder leftEncoder = leftDriveMaster.getEncoder();
    //public static final CANEncoder rightEncoder = rightDriveMaster.getEncoder();

   // public static final Encoder leftEncoder = new Encoder(DriveConstants.Left_Encoder_A_PORT, 
     //   DriveConstants.Left_Encoder_B_PORT, DriveConstants.Left_Encoder_I_PORT, DriveConstants.leftEncoderReversed);
    //public static final Encoder rightEncoder = new Encoder(DriveConstants.Right_Encoder_A_PORT,
       // DriveConstants.Right_Encoder_B_PORT, DriveConstants.Right_Encoder_I_PORT, DriveConstants.rightEncoderReversed);

       public static final VelocityDutyCycleEncoder leftEncoder = new VelocityDutyCycleEncoder(DriveConstants.Left_Encoder_PWM_PORT);
       public static final VelocityDutyCycleEncoder rightEncoder = new VelocityDutyCycleEncoder(DriveConstants.Right_Encoder_PWM_PORT);


       // public static final DutyCycleEncoder leftEncoder = new DutyCycleEncoder(DriveConstants.Left_Encoder_PWM_PORT);
       // public static final DutyCycleEncoder rightEncoder = new DutyCycleEncoder(DriveConstants.Right_Encoder_PWM_PORT);
}
