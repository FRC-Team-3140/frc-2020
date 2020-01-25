package frc.libs;

import edu.wpi.first.wpilibj.DutyCycleEncoder;
import edu.wpi.first.wpilibj.Timer;

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
  public double getRate() {
    // Get current Position and Time
    double currentPosition = getDistance();
    double currentTime = m_timer.get();
    
    // Calculate current Velocity
    double currentVelocity = Math.signum(currentPosition) * Math.abs(currentPosition - lastPosition)
      * (currentTime - lastTime);
    
    // Set previous Position and Time to current Position and Time
    lastPosition = currentPosition;
    lastTime = currentTime;
    
    return currentVelocity;
  }
}
