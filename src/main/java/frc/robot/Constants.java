package frc.robot;

import edu.wpi.first.wpilibj.kinematics.DifferentialDriveKinematics;

public interface Constants {
  public static final class DriveConstants {
    public final static int LEFT_DRIVE_MASTER = 3;
    public final static int RIGHT_DRIVE_MASTER = 2;
    public final static int LEFT_DRIVE_SLAVE1 = 4;
    public final static int RIGHT_DRIVE_SLAVE1 = 5;

    //public static final int[] kLeftEncoderPorts = new int[] { 0, 1 };
    //public static final int[] kRightEncoderPorts = new int[] { 2, 3 };
    //public static final boolean kLeftEncoderReversed = false;
    //public static final boolean kRightEncoderReversed = true;

    public static final double kTrackwidthMeters = 0.69;
    public static final DifferentialDriveKinematics kDriveKinematics = new DifferentialDriveKinematics(
        kTrackwidthMeters);

    //public static final int kEncoderCPR = 1024;
    //public static final double kWheelDiameterMeters = 0.15;
    //public static final double kEncoderDistancePerPulse =
        // Assumes the encoders are directly mounted on the wheel shafts
        //(kWheelDiameterMeters * Math.PI) / (double) kEncoderCPR;

    public static final boolean kGyroReversed = true;

    // Update from Characterization tool.
    public static final double ksVolts = 0.22;
    public static final double kvVoltSecondsPerMeter = 1.98;
    public static final double kaVoltSecondsSquaredPerMeter = 0.2;

    // Example value only - as above, this must be tuned for your drive!
    public static final double kPDriveVel = 8.5;
    public static final double kIDriveVel = 8.5;
    public static final double kDDriveVel = 8.5;
  }

  public static final class OIConstants {
    public static final int xboxPort = 1;
    public static final double deadBand = 0.1;
  }

  public static final class AutoConstants {
    //public static final double kMaxSpeedMetersPerSecond = 3;
    //public static final double kMaxAccelerationMetersPerSecondSquared = 3;

    // Reasonable baseline values for a RAMSETE follower in units of meters and
    // seconds
    public static final double kRamseteB = 2;
    public static final double kRamseteZeta = 0.7;
  }
}
