package frc.robot.commands.pneumatics.shifter;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.Constants;

public class ShiftUp extends SequentialCommandGroup implements Constants {

  public ShiftUp() {
    super(new Shift(EXT), new WaitCommand(.2), new Shift(OFF));
  }
}
