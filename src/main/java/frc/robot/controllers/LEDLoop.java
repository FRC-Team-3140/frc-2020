package frc.robot.controllers;

import frc.libs.loops.Loop;
import frc.robot.RobotContainer;

/**
 * Add your docs here.
 */
public class LEDLoop implements Loop{

    @Override
    public void onStart() {
        // TODO Auto-generated method stub

    }

    int startIndex = 0;
    @Override
    public void onLoop() {
        if(startIndex % RobotContainer.led.ledBuffer.getLength() == 0) 
            startIndex = 0;
        RobotContainer.led.rainbow(startIndex);
        startIndex++;
    }

    @Override
    public void onStop() {
        // TODO Auto-generated method stub

    }
}
