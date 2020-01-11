package frc.robot.commands.pneumatics.shifter;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.Constants;

public class ShiftDown extends SequentialCommandGroup implements Constants{

  public ShiftDown() {
    // Add your commands in the super() call, e.g.
    // super(new FooCommand(), new BarCommand());
    super(new Shift(RET), new WaitCommand(.2), new Shift(OFF));
  }
}
