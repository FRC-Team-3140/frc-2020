package frc.libs;

//import edu.wpi.first.wpilibj.DutyCycleEncoder;
//import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class DutyCycleEncoderSubsystem extends SubsystemBase {
  /*
  DutyCycleEncoder encoder;
  Timer timer = new Timer();

  double lastDistance = 0;
  double lastTime = 0;
  double velocity;

  public DutyCycleEncoderSubsystem(DutyCycleEncoder encoder) {
    this.encoder = encoder;
    timer.reset();
    timer.start();
  }

  public DutyCycleEncoder getEncoder() {
    return encoder;
  }

  @Override
  public void periodic() {
    double distance = encoder.getDistance();
    double time = timer.get();

    velocity = (distance - lastDistance) / (time - lastTime); 

    lastDistance = distance;
    lastTime = time;
  }
  */
}
