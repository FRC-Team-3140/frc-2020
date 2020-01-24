package frc.libs;

import edu.wpi.first.wpilibj.DutyCycleEncoder;

public class VelocityDutyCycleEncoder extends DutyCycleEncoder {
  public DutyCycleEncoder(int channel) {
    super(channel);
  }
  
   public double getRate() {
    return 0; // TODO:
   }
}
