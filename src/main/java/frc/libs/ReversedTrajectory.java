package frc.libs;

import java.util.List;
import edu.wpi.first.wpilibj.trajectory.Trajectory;

public class ReversedTrajectory extends Trajectory {
    private double totalTime;

    public ReversedTrajectory(List<State> states) {
        super(states);
        totalTime = super.getTotalTimeSeconds();
    }

    @Override
    public State sample(double timeSeconds) {
        return super.sample(totalTime - timeSeconds);
    }
}
