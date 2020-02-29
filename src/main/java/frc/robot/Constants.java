package frc.robot;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

public interface Constants {
	public interface GeneralConstants {
		// Pnuematics
		public final static Value EXT = Value.kForward;
		public final static Value RET = Value.kReverse;
		public final static Value OFF = Value.kOff;
		// OI
		public final static double DEADBAND = 0.05;
	}

	public interface ElectricalPortConstants {
		// Xbox Controllers
		public static final int xboxPrimaryDriver = 0;
		public static final int xboxSecondaryDriver = 1;

		// Other Can Bus
		public final static int PCM = 1;

		// Drivetrain
		public final static int LEFT_DRIVE_MASTER = 6;
		public final static int LEFT_DRIVE_SLAVE1 = 5;
		public final static int LEFT_DRIVE_SLAVE2 = 7;

		public final static int RIGHT_DRIVE_MASTER = 11;
		public final static int RIGHT_DRIVE_SLAVE1 = 2; 
		public final static int RIGHT_DRIVE_SLAVE2 = 4;
		
		// Climber
		public final static int CLIMBER_MASTER = 13;
		public final static int CLIMBER_SLAVE = 14;

		// Pnuematic solenoids
		public final static int INTAKE_SOLENOID_EXT = 0;
		public final static int INTAKE_SOLENOID_RET = 3;
		public final static int CLIMBER_LOCK_SOLENOID_EXT = 2;
		public final static int CLIMBER_LOCK_SOLENOID_RET = 1;
	}
}