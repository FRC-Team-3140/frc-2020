package frc.lib;

import edu.wpi.first.wpilibj.DutyCycleEncoder;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class VelocityDutyCycleEncoder extends DutyCycleEncoder {
  private final Timer m_timer = new Timer();
  private double lastPosition;
  private double lastTime;
  
  public VelocityDutyCycleEncoder(int channel) {
    super(channel);
    m_timer.reset();
    m_timer.start();
    lastPosition = getDistance();
    lastTime = m_timer.get();
  }
  
  @Override
  public void reset() {
    super.reset();
    m_timer.reset();
    lastPosition = getDistance();
    lastTime = m_timer.get();
  }
  
  // Returns Encoder Distance / Seconds
  // This should only be called from the readPeriodicInputs() method in Drivetrain.
  // Calling it from anywhere else will lead to inacurate results.
  // If you need this method's value call periodicIO.____Velocity; instead. 
  public double getRate() {
    // Get current Position and Time

    // ?? Do I need to put a deadband on the encoder distance so velocity doesn't drift when the bot
    // isn't moving i.e. if distance < 0.001 set to 0????
    double currentPosition = getDistance();
    double currentTime = m_timer.get();

    double distanceDelta = currentPosition - lastPosition;
    double timeDelta = currentTime - lastTime;
    
    // Calculate current Velocity
    double currentVelocity = distanceDelta / timeDelta;
    
    // Debugging
    SmartDashboard.putNumber("Velocity Encoder DT", timeDelta);
    System.out.println("DT: " + timeDelta);    

    // Set previous Position and Time to current Position and Time
    lastPosition = currentPosition;
    lastTime = currentTime;
    
    return currentVelocity;
  }
}
