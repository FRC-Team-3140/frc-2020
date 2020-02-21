package frc.robot;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide
 * numerical or boolean constants. This class should not be used for any other
 * purpose. All constants should be declared globally (i.e. public static). Do
 * not put anything functional in this class.
 */
public interface Constants {
	public interface GeneralConstants {
		// Pnuematics
		public final static Value EXT = Value.kForward;
		public final static Value RET = Value.kReverse;
		public final static Value OFF = Value.kOff;
		// OI
		public final static double DEADBAND = 0.1;

		public interface PhysicalConstants {
			// Field dimensions, etc.
		}

		public interface RobotPhysicalConstants {
			// Gear Ratio's
			public static final double driveTrainGearRatio = 7.88;
			public static final double flyWheelGearRatio = 0.7159;
			// Diameters
			public static final double wheelDiameterMeters = 0.1524;
			public static final double flyWheelDiameterMeters = 0.1016;
		}

		public interface SensorConstants {
			// Position Conversions
			// (Native units for the Spark Max are in Rotations)
			public static final int kDriveTrainEncoderCPR = 1; // Encoder Counts/Pulses per Rotation (usually > 1)
			public static final int kFlyWheelEncoderCPR = 1; // Encoder Counts/Pulses per Rotation (usually > 1)
			public static final double kDriveTrainEncoderMetersPerPulse = (RobotPhysicalConstants.wheelDiameterMeters
					* Math.PI) / ((double) kDriveTrainEncoderCPR * RobotPhysicalConstants.driveTrainGearRatio);
			public static final double kFlyWheelEncoderMetersPerPulse = (RobotPhysicalConstants.flyWheelDiameterMeters
					* Math.PI) / ((double) kFlyWheelEncoderCPR * RobotPhysicalConstants.flyWheelGearRatio);

			// Velocity Conversions
			public static final double RPM_to_RPS = ((double) 1 / 60);
			public static final double driveTrainMetersPerRotation = kDriveTrainEncoderMetersPerPulse
					* kDriveTrainEncoderCPR;
			public static final double flyWheelMetersPerRotation = kFlyWheelEncoderMetersPerPulse
					* kFlyWheelEncoderCPR;
			public static final double kDriveTrainEncoderLinearMetersPerSecondPerRPM = driveTrainMetersPerRotation
					* RPM_to_RPS;
			public static final double kFlyWheelEncoderLinearMetersPerSecondPerRPM = flyWheelMetersPerRotation
					* RPM_to_RPS;
		}
	}

	public interface ElectricalPortConstants {
		// Xbox Controllers
		public static final int xboxPrimaryDriver = 0;

		// Shooter
		public final static int FLYWHEEL_MASTER = 9;
		public final static int FLYWHEEL_SLAVE = 10;

		// Shooter Rotary Components
		public final static int HOOD_MOTOR = 15;
		public final static int TURRET_MOTOR = 16;
	}

	public interface ControllerConstants {
		public interface FlyWheel {
			// The constants below are 10X the characterization tool output's.
			public static final double ksVolts = 0;
			public static final double kvVoltSecondsPerMeter = 0;
			public static final double kaVoltSecondsSquaredPerMeter = 0;
			// The constants below are not 10X
			public static final double kPDriveVel = 0;
			public static final double kIDriveVel = 0;
			public static final double kDDriveVel = 0;
		}

		public interface Turret {
			// The constants below are 10X the characterization tool output's.
			public static final double ksVolts = 0;
			public static final double kvVoltSecondsPerMeter = 0;
			public static final double kaVoltSecondsSquaredPerMeter = 0;
			// The constants below are not 10X
			public static final double kPDriveVel = 0;
			public static final double kIDriveVel = 0;
			public static final double kDDriveVel = 0;
		}

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
