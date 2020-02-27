package frc.robot.commands.drivetrain;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;

public class ReducedSpeedTurningDrive extends CommandBase {
    public ReducedSpeedTurningDrive() {
        addRequirements(RobotContainer.dt);
      }

    @Override
    public void initialize() {
    }

    @Override
    public void execute() {
        double throttle = RobotContainer.xbox.getSmoothedMainY();
        double heading = RobotContainer.xbox.getSmoothedAltX();

        RobotContainer.dt.arcadeDrive(throttle, -heading * 0.33);
    }

    @Override
    public void end(boolean interrupted) {
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
