package frc.robot;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveKinematics;

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
			public static final double flyWheelGearRatio = 1.33;
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
		public static final int xboxSecondaryDriver = 1;

		// Other Can Bus
		public final static int PDP = 0;
		public final static int PCM = 1;

		// Drivetrain
		public final static int LEFT_DRIVE_MASTER = 3;
		public final static int RIGHT_DRIVE_MASTER = 2;
		public final static int LEFT_DRIVE_SLAVE1 = 4;
		public final static int RIGHT_DRIVE_SLAVE1 = 5;
		public final static int LEFT_DRIVE_SLAVE2 = 6;
		public final static int RIGHT_DRIVE_SLAVE2 = 7;

		// Intake
		public final static int INTAKE_MOTOR = 8;

		// Shooter
		public final static int FLYWHEEL_MASTER = 9;
		public final static int FLYWHEEL_SLAVE = 10;

		// Feeders
		public final static int BALL_FEEDER = 11;
		public final static int SHOOTER_FEEDER = 12;

		// Climber
		public final static int CLIMBER_MASTER = 13;
		public final static int CLIMBER_SLAVE = 14;

		// Shooter Rotary Components
		public final static int HOOD_MOTOR = 15;
		public final static int TURRET_MOTOR = 16;

		// Limit Switches
		public final static int HOOD_LIMIT = 0;
		public final static int TURRET_LIMIT = 1;

		// Pnuematic solenoids
		public final static int INTAKE_SOLENOID_EXT = 0;
		public final static int INTAKE_SOLENOID_RET = 1;
		public final static int CLIMBER_LOCK_SOLENOID_EXT = 2;
		public final static int CLIMBER_LOCK_SOLENOID_RET = 3;
	}

	public interface ControllerConstants {
		// General
		public static final double kRamseteB = 2;
		public static final double kRamseteZeta = 0.7;

		public interface DriveTrain {
			public static final double trackWidthMeters = 0;
			public static final DifferentialDriveKinematics driveKinematics = new DifferentialDriveKinematics(
					trackWidthMeters);
			// The constants below are 10X the characterization tool output's.
			public static final double ksVolts = 1.29;
			public static final double kvVoltSecondsPerMeter = 50.8;
			public static final double kaVoltSecondsSquaredPerMeter = 4.54;
			// The constants below are not 10X
			public static final double kPDriveVel = 14.2;
			public static final double kIDriveVel = 0;
			public static final double kDDriveVel = 0;
		}

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
	}
}
