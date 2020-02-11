package frc.robot;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants.  This class should not be used for any other purpose.  All constants should be
 * declared globally (i.e. public static).  Do not put anything functional in this class.
 */
public interface Constants {

	public final static int CHAMBER_POS_SHIFT = 20; // in encoder ticks

	/*
	 * MOTORS
	 */
	public final static double DEADBAND = 0.1;

	public final static int LEFT_DRIVE_MASTER = 3;
	public final static int RIGHT_DRIVE_MASTER = 2;
	public final static int LEFT_DRIVE_SLAVE1 = 4;
	public final static int RIGHT_DRIVE_SLAVE1 = 5;
	public final static int LEFT_DRIVE_SLAVE2 = 6;
	public final static int RIGHT_DRIVE_SLAVE2 = 7;

	public final static int INTAKE_MOTOR = 8;

	public final static int FLYWHEEL_MASTER = 9;
	public final static int FLYWHEEL_SLAVE1 = 10;
	public final static int FLYWHEEL_SLAVE2 = 11;
	public final static int FEEDER = 12;

	public final static int HOOD_MOTOR = 16;
	public final static int TURRET_MOTOR = 17;

	// LIMIT SWITCHES
	public final static int CHAMBER_OUT = 0;
	public final static int CHAMBER_1 = 1;
	public final static int CHAMBER_2 = 2;
	public final static int CHAMBER_3 = 3;
	public final static int CHAMBER_4 = 4;
	public final static int CHAMBER_5 = 5;

	public final static int PCM = 1;
	public final static int INTAKE_SOLENOID_EXT = 2;
	public final static int INTAKE_SOLENOID_RET = 5;

	public final static Value EXT = Value.kForward;
	public final static Value RET = Value.kReverse;
	public final static Value OFF = Value.kOff;

}
