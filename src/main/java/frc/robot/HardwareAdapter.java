package frc.robot;

import edu.wpi.first.wpilibj.PWMVictorSPX;

public interface HardwareAdapter extends Constants.ElectricalPortConstants {
   

    // Climber
    public static final PWMVictorSPX climberMaster = new PWMVictorSPX(CLIMBER_MASTER);
    
}
