package frc.robot;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants.  This class should not be used for any other purpose.  All constants should be
 * declared globally (i.e. public static).  Do not put anything functional in this class.
 */
public interface Constants {

	// PID for drive
	public final static double DRIVE_LEFT_P = .12;
	public final static double DRIVE_LEFT_I = 0;
	public final static double DRIVE_LEFT_D = 1.5;
	public final static double DRIVE_RIGHT_P = .11;
	public final static double DRIVE_RIGHT_I = 0;
	public final static double DRIVE_RIGHT_D = 1.5;
	public final static double DRIVE_TURN_P = .008;
	public final static double DRIVE_TURN_I = 0;
	public final static double DRIVE_TURN_D = 0;
	public final static double DRIVE_LEFT_FF = 0;
	public final static double DRIVE_RIGHT_FF = 0;

	// PID for turret 
	public final static double TURRET_P = 1;
	public final static double TURRET_I = 0;
	public final static double TURRET_D = 0;

	// PID for angled hood
	public final static double HOOD_P = 1;
	public final static double HOOD_I = 0;
	public final static double HOOD_D = 0;

	// WHEELS
	public final static double WHEEL_DIAMETER = 4; // inches
	public final static double DT_INCHES_TO_TICKS =1;// 1/0.0233;
	public final static double DT_TICKS_TO_INCHES =  1;// TODO test for this val
	public final static double DT_MAX_INPUT_PID = 0.5;
	public final static double DT_RAMP_RATE = 2; // time it takes for drivetrain to go from 0 to max throttle
	public final static double DT_MAX_VELOCITY = 7; // ft/s
	public final static double DT_MAX_ACCEL = DT_MAX_VELOCITY / DT_RAMP_RATE;
	public final static double SETPOINT_DT = .1; // seconds

	public final static double DT_TOL_IN = 1;
	public final static double DT_TOL_ANGLE = 3; // in degrees

	public final static double DEADBAND = 0.1;

	public final static int LEFT_DRIVE_MASTER = 3;
	public final static int RIGHT_DRIVE_MASTER = 2;
	public final static int LEFT_DRIVE_SLAVE1 = 4;
	public final static int RIGHT_DRIVE_SLAVE1 = 5;
	public final static int LEFT_DRIVE_SLAVE2 = 6;
	public final static int RIGHT_DRIVE_SLAVE2 = 7;

	public final static int PCM = 1;

	public final static Value EXT = Value.kForward;
	public final static Value RET = Value.kReverse;
	public final static Value OFF = Value.kOff;

}
