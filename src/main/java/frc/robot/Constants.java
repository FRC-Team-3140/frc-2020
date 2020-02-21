package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide
 * numerical or boolean constants. This class should not be used for any other
 * purpose. All constants should be declared globally (i.e. public static). Do
 * not put anything functional in this class.
 */
public interface Constants {
	public interface GeneralConstants {
		// OI
		public final static double DEADBAND = 0.1;

		public interface PhysicalConstants {
			// Field dimensions, etc.
		}

		public interface RobotPhysicalConstants {
			// Gear Ratio's
			public static final double hoodGearRatio = 0.7159;
		}

		public interface SensorConstants {
			// Position Conversions
			public static final int kHoodEncoderCPR = 0; // Encoder Counts/Pulses per Rotation (usually > 1)
		}
	}

	public interface ElectricalPortConstants {
		// Xbox Controllers
		public static final int xboxPrimaryDriver = 0;

		// Shooter Rotary Components
		public final static int HOOD_MOTOR = 15;
	}

	public interface ControllerConstants {
		public interface Hood {
			// The constants below are 10X the characterization tool output's.
			public static final double ksVolts = 0;
			public static final double kvVoltSecondsPerMeter = 0;
			public static final double kaVoltSecondsSquaredPerMeter = 0;
			// The constants below are not 10X
			public static final double kPDriveVel = 0;
			public static final double kIDriveVel = 0;
			public static final double kDDriveVel = 0;
		}
	}
}
