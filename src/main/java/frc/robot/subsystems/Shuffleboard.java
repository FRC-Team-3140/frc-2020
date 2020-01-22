package frc.robot.subsystems;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants;
import frc.robot.RobotContainer;

public class Shuffleboard implements Constants {

    static double[] PIDVals = new double[15]; // left PID, right PID, turn PID

    public Shuffleboard() {

    }

    public void pullFromSDB() {
        double[] pidVals = new double[PIDVals.length];

        pidVals[0] = SmartDashboard.getNumber("DT Left kP", DRIVE_LEFT_P);
        pidVals[1] = SmartDashboard.getNumber("DT Left kI", DRIVE_LEFT_I);
        pidVals[2] = SmartDashboard.getNumber("DT Left kD", DRIVE_LEFT_D);
        pidVals[3] = SmartDashboard.getNumber("DT Right kP", DRIVE_RIGHT_P);
        pidVals[4] = SmartDashboard.getNumber("DT Right kI", DRIVE_RIGHT_I);
        pidVals[5] = SmartDashboard.getNumber("DT Right kD", DRIVE_RIGHT_D);
        pidVals[6] = SmartDashboard.getNumber("DT Turn kP", DRIVE_TURN_P);
        pidVals[7] = SmartDashboard.getNumber("DT Turn kI", DRIVE_TURN_I);
        pidVals[8] = SmartDashboard.getNumber("DT Turn kD", DRIVE_TURN_D);
        pidVals[9] = SmartDashboard.getNumber("Turret kP", TURRET_P);
        pidVals[10] = SmartDashboard.getNumber("Turret kI", TURRET_I);
        pidVals[11] = SmartDashboard.getNumber("Turret kD", TURRET_D);
        pidVals[12] = SmartDashboard.getNumber("Hood kP", HOOD_P);
        pidVals[13] = SmartDashboard.getNumber("Hood kI", HOOD_I);
        pidVals[14] = SmartDashboard.getNumber("Hood kD", HOOD_D);

        boolean update = false;
        for(int i = 0; i < pidVals.length; i++) {
            if(pidVals[i] != PIDVals[i]) {
                //TODO here
                PIDVals[i] = pidVals[i];
                update = true;
            }
        }

        if(update) {
            RobotContainer.dt.updatePIDVals(PIDVals[0], PIDVals[1], PIDVals[2], PIDVals[3], PIDVals[4], PIDVals[5],
                                            PIDVals[6], PIDVals[7], PIDVals[8]);
        }
    }

    public void pushToSB() {
        SmartDashboard.putNumber("DT Left kP", DRIVE_LEFT_P);
        SmartDashboard.putNumber("DT Left kI", DRIVE_LEFT_I);
        SmartDashboard.putNumber("DT Left kD", DRIVE_LEFT_D);
        SmartDashboard.putNumber("DT Right kP", DRIVE_RIGHT_P);
        SmartDashboard.putNumber("DT Right kI", DRIVE_RIGHT_I);
        SmartDashboard.putNumber("DT Right kD", DRIVE_RIGHT_D);
        SmartDashboard.putNumber("DT Right kP", DRIVE_RIGHT_P);
        SmartDashboard.putNumber("DT Turn kP", DRIVE_TURN_P);
        SmartDashboard.putNumber("DT Turn kI", DRIVE_TURN_I);
        SmartDashboard.putNumber("DT Turn kD", DRIVE_TURN_D);

        SmartDashboard.putNumber("Turret kP", DRIVE_TURN_P);
        SmartDashboard.putNumber("Turret kI", DRIVE_TURN_I);
        SmartDashboard.putNumber("Turret kD", DRIVE_TURN_D);

        SmartDashboard.putNumber("Hood kP", HOOD_P);
        SmartDashboard.putNumber("Hood kI", HOOD_I);
        SmartDashboard.putNumber("Hood kD", HOOD_D);

    }
}
