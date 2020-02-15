package frc.robot.commands.drivetrain;

import com.revrobotics.CANSparkMax.IdleMode;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.Constants.DriveConstants;

public class HoldPositionController extends CommandBase {
    private PIDController leftPID;
    private PIDController rightPID;
    private IdleMode previousIdleMode;

    public HoldPositionController() {
        addRequirements(RobotContainer.dt);
    }

    @Override
    public void initialize() {
        RobotContainer.dt.resetAll();
        leftPID = new PIDController(DriveConstants.holdPositonKP, DriveConstants.holdPositonKI,
                DriveConstants.holdPositonKD);
        rightPID = new PIDController(DriveConstants.holdPositonKP, DriveConstants.holdPositonKI,
                DriveConstants.holdPositonKD);
        leftPID.reset();
        rightPID.reset();
        previousIdleMode = RobotContainer.dt.getIdleMode();
        RobotContainer.dt.setIdleMode(IdleMode.kBrake);
    }

    @Override
    public void execute() {
        double left = leftPID.calculate(RobotContainer.dt.getLeftEncoderDistance(), 0.0);
        double right = rightPID.calculate(RobotContainer.dt.getRightEncoderDistance(), 0.0);
        RobotContainer.dt.tankDrive(left / 12, right / 12);
    }
 
    @Override
    public void end(boolean interrupted) {
        RobotContainer.dt.setIdleMode(previousIdleMode);
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
