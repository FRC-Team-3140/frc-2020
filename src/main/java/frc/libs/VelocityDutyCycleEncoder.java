package frc.libs;

import edu.wpi.first.wpilibj.DutyCycleEncoder;
import edu.wpi.first.wpilibj.Timer;
import frc.loopController.Loop;
import frc.loopController.Looper;

public class VelocityDutyCycleEncoder extends DutyCycleEncoder implements Loop {
  private final Timer m_timer = new Timer();
  private Looper looper = new Looper(0.01); // Update velocity @ 100hz
  private double lastPosition;
  private double lastTime;
  private double currentVelocity;
  
  public VelocityDutyCycleEncoder(int channel) {
    super(channel);
    m_timer.reset();
    m_timer.start();

    lastPosition = getDistance();
    lastTime = m_timer.get();

    looper.register(this);
    looper.start();
  }
  
  @Override
  public void reset() {
    super.reset();
    m_timer.reset();
    
    lastPosition = getDistance();
    lastTime = m_timer.get();

    // Needed???
    currentVelocity = 0;
  }
  
  // Returns Encoder Distance / Seconds
  public double getRate() {
    return currentVelocity;
  }

  @Override
  public void onStart() {
  }

  @Override
  public void onLoop() {
    // Get current Position and Time
    double currentPosition = getDistance();
    double currentTime = m_timer.get();
    
    // Calculate current Velocity
    currentVelocity = (currentPosition - lastPosition)
      / (currentTime - lastTime);
    
    // Set previous Position and Time to current Position and Time
    lastPosition = currentPosition;
    lastTime = currentTime;
  }

  @Override
  public void onStop() {
  }
}
