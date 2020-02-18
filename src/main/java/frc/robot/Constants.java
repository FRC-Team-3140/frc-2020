package frc.robot;

import edu.wpi.first.wpilibj.kinematics.DifferentialDriveKinematics;

// Use meters, seconds, degrees (180deg to -180deg), and volts for all motion profiling based units.
public interface Constants {
  public static final class DriveConstants {
    public final static int LEFT_DRIVE_MASTER = 3;
    public final static int RIGHT_DRIVE_MASTER = 2;
    public final static int LEFT_DRIVE_SLAVE1 = 4;
    public final static int RIGHT_DRIVE_SLAVE1 = 5;
    // Comment out the 2 lines below when working on the chassis bot
    public final static int LEFT_DRIVE_SLAVE2 = 6;
    public final static int RIGHT_DRIVE_SLAVE2 = 7;

    public final static boolean isCompetition = false;

    // Drivetrain gear ratio's number with respect to 1. i.e. 12:1
    private static final double compBotGear = 7.88;
    private static final double lowGear = 12.86;
    private static final double highGear = 6.25;
    private static final boolean lockedInLowGear = false;
    public static double gearRatio = isCompetition ? compBotGear : (lockedInLowGear ? lowGear:highGear);

    // Distance between wheel centers + needed controller gain offsets
    // Generated from Robot Characterization tool, should be a position number (generally > 0.33m)
    public static final double kTrackwidthMeters = 0.6729;

    public static final DifferentialDriveKinematics kDriveKinematics = new DifferentialDriveKinematics(
        kTrackwidthMeters);

    // Wheel Diameter
    private static final double kWheelDiameterMetersComp = 0.1524;
    private static final double kWheelDiameterMetersChassis = 0.1016;
    public static final double kWheelDiameterMeters = isCompetition ? kWheelDiameterMetersComp : kWheelDiameterMetersChassis;

    // Position Conversions
    //(Native units for the Spark Max are in Rotations)
    public static final int kEncoderCPR = 1;  // Encoder Counts/Pulses per Rotation (usually > 1)
    public static final double kEncoderMetersPerPulse = (kWheelDiameterMeters * Math.PI) / ((double) kEncoderCPR * gearRatio);

    // Velocity Conversions
    private static final double RPM_to_RPS = ((double) 1 / 60);
    private static final double metersPerRotation = kEncoderMetersPerPulse * kEncoderCPR;
    public static final double kEncoderLinearMetersPerSecondPerRPM = metersPerRotation * RPM_to_RPS; 

    // True to make Counter Clockwise Angles Positive, and Clockwise Negative.
    public static final boolean kGyroReversed = true; 
    
    // Update from Characterization tool.
    // KS, KV, AND KA should be 10X the values of the robot characterization tool???
    // Leave KP alone, with this multiplicaiton it seems to work well.
    // Should check for a 10X factor being left out of the encoders in the robot characterization 
    // tool generated code, even though the response back from that code in the tool, seemed to be the correct distance driven.
    // either way this is potentially the source of the difference between the expected and useful control values.
    public static final double ksVolts = 1.29; // Working well 10X characterized constant 1.29;
    public static final double kvVoltSecondsPerMeter = 50.8; // Working well 10X characterized constant 50.8;
    public static final double kaVoltSecondsSquaredPerMeter = 4.54; // Working well 10X charachterized constant 4.54;
    
    //Generated values 0.129, 5.08, 0.454
    public static final double kPDriveVel = 14.2;
    public static final double kIDriveVel = 0;
    public static final double kDDriveVel = 0;

    // Hold Position
    // From characterization tool using position instead of velocity modes
    // Use P gain only
    // Max position error 0.01 units
    // Max velocitry error 0.02 units
    // Max control effort 7 volts
    public static final double holdPositonKP = 49.5;
    public static final double holdPositonKI = 0;
    public static final double holdPositonKD = 0;
  }

  public static final class OIConstants {
    public static final int xboxPort = 0;
    public static final double deadBand = 0.01;
  }

  public static final class AutoConstants {
    // Reasonable baseline values for a RAMSETE follower in units of meters and
    // seconds
    public static final double kRamseteB = 2;
    public static final double kRamseteZeta = 0.7;
  }
}
