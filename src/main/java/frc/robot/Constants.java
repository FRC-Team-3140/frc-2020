package frc.robot;

import edu.wpi.first.wpilibj.kinematics.DifferentialDriveKinematics;

// Use meters, seconds, degrees (-180deg to 180deg), and volts for all motion profiling based units.
public interface Constants {
  public static final class DriveConstants {
    public final static int LEFT_DRIVE_MASTER = 3;
    public final static int RIGHT_DRIVE_MASTER = 2;
    public final static int LEFT_DRIVE_SLAVE1 = 4;
    public final static int RIGHT_DRIVE_SLAVE1 = 5;

    public final static int Left_Encoder_PWM_PORT = 0;
    public final static int Right_Encoder_PWM_PORT = 1;

    //Drivetrain gear ratio's number with respect to 1. i.e. 12:1
    public static final double highGear = 12.86;
    public static final double lowGear = 6.25;
    public static final boolean lockedInHighGear = true;
    public static double gearRatio = lockedInHighGear ? highGear:lowGear;
    //public static double gearRatio = 1.0;

    //Distance between center of wheel thicknesses
    //Or (distance between wheel outsides + distance between wheel insides) / 2
    public static final double kTrackwidthMeters = 0.57785;

    public static final DifferentialDriveKinematics kDriveKinematics = new DifferentialDriveKinematics(
        kTrackwidthMeters);

    // Wheel Diameter
    public static final double kWheelDiameterMeters = 0.1016;

    // Position Conversions
    //(Native units for the Spark Max are in Rotations)
    public static final int kEncoderCPR = 1;  //2048; // Encoder Counts/Pulses per Rotation (usually > 1)
    public static final double kEncoderMetersPerPulse = (kWheelDiameterMeters * Math.PI) / ((double) kEncoderCPR * gearRatio);

    // Velocity Conversions
    private static final double RPM_to_RPS = ((double) 1 / 60);
    private static final double metersPerRotation = kEncoderMetersPerPulse * kEncoderCPR;
    public static final double kEncoderLinearMetersPerSecondPerRPM = metersPerRotation * RPM_to_RPS; 

    // True to make Counter Clockwise Angles Positive, and Clockwise Negative.
    public static final boolean kGyroReversed = true; 
    
    // Update from Characterization tool.
    public static final double ksVolts = 0.116;
    public static final double kvVoltSecondsPerMeter = 5.06;
    public static final double kaVoltSecondsSquaredPerMeter = 0.327;
    //2.75 (Scaled to 42 counts/rotation using talon instead of onboard option when gains were calculated.)
    // w/ post encoder gearing 0.0653
    public static final double kPDriveVel = 11.1; 
    public static final double kIDriveVel = 0;
    public static final double kDDriveVel = 0;
  }

  public static final class OIConstants {
    public static final int xboxPort = 0;
    public static final double deadBand = 0.15;
  }

  public static final class AutoConstants {
    // Reasonable baseline values for a RAMSETE follower in units of meters and
    // seconds
    public static final double kRamseteB = 2;
    public static final double kRamseteZeta = 0.7;
  }
}
