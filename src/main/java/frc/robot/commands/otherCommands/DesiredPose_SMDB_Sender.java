package frc.robot.commands.otherCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.trajectory.Trajectory;
import edu.wpi.first.wpilibj.trajectory.Trajectory.State;

public class DesiredPose_SMDB_Sender extends CommandBase {
  private final Timer m_timer = new Timer();
  private final Trajectory m_trajectory;
  private State currentTrajectoryState;
  private Pose2d currentlyDesiredPose;
  private double currentlyDesiredVelocity;
  private double currentlyDesiredAngle;

  public DesiredPose_SMDB_Sender(Trajectory trajectory) {
    m_trajectory = trajectory;
  }

  @Override
  public void initialize() {
    m_timer.reset();
    m_timer.start();
  }

  @Override
  public void execute() {
    double currentTime = m_timer.get();
    currentTrajectoryState = m_trajectory.sample(currentTime);
    currentlyDesiredPose = currentTrajectoryState.poseMeters;
    currentlyDesiredVelocity = currentTrajectoryState.velocityMetersPerSecond;
    currentlyDesiredAngle = currentlyDesiredPose.getRotation().getDegrees();
    
    SmartDashboard.putNumber("Desired Velocity (m/s): ", currentlyDesiredVelocity);
    SmartDashboard.putNumber("Desired Heading (deg): ", currentlyDesiredAngle);
  }

  @Override
  public void end(boolean interrupted) {
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
