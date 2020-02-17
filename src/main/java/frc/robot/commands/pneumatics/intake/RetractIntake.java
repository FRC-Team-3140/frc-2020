package frc.robot.commands.pneumatics.intake;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.Constants;

public class RetractIntake extends SequentialCommandGroup implements Constants {

  public RetractIntake() {
    super(new MoveIntake(EXT), new WaitCommand(.2), new MoveIntake(OFF));
  }
}