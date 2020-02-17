package frc.robot;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants.  This class should not be used for any other purpose.  All constants should be
 * declared globally (i.e. public static).  Do not put anything functional in this class.
 */
public interface Constants {

	public final static int VISION_TARGET_TOL = 10; // in pixels

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
