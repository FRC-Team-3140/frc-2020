package frc.robot;

import edu.wpi.first.wpilibj.kinematics.DifferentialDriveKinematics;

// Use meters, seconds, and volts for all motion profiling based units.
public interface Constants {
  public static final class DriveConstants {
    public final static int LEFT_DRIVE_MASTER = 3;
    public final static int RIGHT_DRIVE_MASTER = 2;
    public final static int LEFT_DRIVE_SLAVE1 = 4;
    public final static int RIGHT_DRIVE_SLAVE1 = 5;

    //public static final boolean leftEncoderReversed = false;
    //public static final boolean rightEncoderReversed = true;

    //Drivetrain gear ratio's number with respect to 1. i.e. 12:1
    public static final double highGear = 12.86;
    public static final double lowGear = 6.25;
    public static final boolean lockedInHighGear = true;
    public static double gearRatio = lockedInHighGear ? highGear:lowGear;

    //Distance between center of wheel thicknesses
    //Or (distance between wheel outsides + distance between wheel insides) / 2
    public static final double kTrackwidthMeters = 0.57785;

    public static final DifferentialDriveKinematics kDriveKinematics = new DifferentialDriveKinematics(
        kTrackwidthMeters);

    public static final int kEncoderCPR = 1; //(Native units for the Spark Max are in Rotations)
    public static final double kWheelDiameterMeters = 0.1016;
    public static final double kEncoderDistancePerPulse = (kWheelDiameterMeters * Math.PI) / ((double) kEncoderCPR * gearRatio);
    public static final boolean kGyroReversed = true;

    // Update from Characterization tool.
    public static final double ksVolts = 0.15;
    public static final double kvVoltSecondsPerMeter = 5.07;
    public static final double kaVoltSecondsSquaredPerMeter = 0.351;
    public static final double kPDriveVel = 5.96;
    public static final double kIDriveVel = 0;
    public static final double kDDriveVel = 2.62;
  }

  public static final class OIConstants {
    public static final int xboxPort = 1;
    public static final double deadBand = 0.1;
  }

  public static final class AutoConstants {
    // Reasonable baseline values for a RAMSETE follower in units of meters and
    // seconds
    public static final double kRamseteB = 2;
    public static final double kRamseteZeta = 0.7;
  }
}
