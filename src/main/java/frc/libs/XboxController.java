package frc.libs;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.Constants;

public final class XboxController extends Joystick implements Constants.GeneralConstants {
	// Internal Buttons
	public JoystickButton a;
	public JoystickButton b;
	public JoystickButton x;
	public JoystickButton y;
	public JoystickButton select;
	public JoystickButton start;
	// Thumb-stick
	public JoystickButton leftJoystickPress;
	public JoystickButton rightJoystickPress;
	// Bumpers
	public JoystickButton leftBumper;
	public JoystickButton rightBumper;
	// Triggers
	public AnalogButton leftTrigger;
	public AnalogButton rightTrigger;
	// DPAD
	public DpadButton dpadUp;
	public DpadButton dpadDown;
	
	public XboxController(int port) {
		super(port);
		// Buttons
		a = new JoystickButton(this, 1);
		b = new JoystickButton(this, 2);
		x = new JoystickButton(this, 3);
		y = new JoystickButton(this, 4);
		leftBumper = new JoystickButton(this, 5);
		rightBumper = new JoystickButton(this, 6);
		select = new JoystickButton(this, 7);
		start = new JoystickButton(this, 8);
		leftJoystickPress = new JoystickButton(this, 9);
		rightJoystickPress = new JoystickButton(this, 10);
		leftTrigger = new AnalogButton(this, 2, 0.1);
		rightTrigger = new AnalogButton(this, 3, 0.1);
		dpadDown = new DpadButton(this, 0);
		dpadUp = new DpadButton(this, 180);
	}
	
	public double getMainX(){
		return -super.getRawAxis(0);
	}
	
	public double getMainY(){
		return -super.getRawAxis(1);
	}
	
	public double getAltX(){
		return -super.getRawAxis(4);
	}
	
	public double getAltY(){
		return -super.getRawAxis(5);
	}

	public double getLeftTrigger() {
		return super.getRawAxis(2);
	}

	public double getRightTrigger() {
		return super.getRawAxis(3);
	}
	
	public double getSmoothedMainX() {
		return getDeadband(-Math.pow(super.getRawAxis(0), 3));
		//return getDeadband(-Math.sin(Math.PI/2 * super.getRawAxis(0)));
	}
	
	public double getSmoothedMainY() {
		return getDeadband(-Math.pow(super.getRawAxis(1), 3));
		//return getDeadband(-Math.sin(Math.PI/2 * super.getRawAxis(1)));
	}
	
	public double getSmoothedAltX() {
		return getDeadband(-Math.pow(super.getRawAxis(4), 3));
		//return getDeadband(-Math.sin(Math.PI/2 * super.getRawAxis(4)));
	}
	
	public double getSmoothedAltY() {
		return getDeadband(-Math.pow(super.getRawAxis(5), 3));
		//return getDeadband(-Math.sin(Math.PI/2 * super.getRawAxis(5)));
	}

	private double getDeadband(double d) {
		if(Math.abs(d) > DEADBAND) return d;
		else return 0;
	}
}
