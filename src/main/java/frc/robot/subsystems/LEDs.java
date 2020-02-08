/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.HardwareAdapter;

public class LEDs extends SubsystemBase implements HardwareAdapter {
  public AddressableLEDBuffer ledBuffer;

  public LEDs() {
    ledBuffer = new AddressableLEDBuffer(120); // black leds length
    led.setLength(ledBuffer.getLength());

    // Set the data
    led.setData(ledBuffer);
    led.start();

    // clear leds
    /*for (var i = 0; i < ledBuffer.getLength(); i++) 
      ledBuffer.setHSV(i, 0, 0, 0);      
    
    led.setData(ledBuffer);*/

    rainbow(0);
  }

  public void rainbow(int startIndex) {
    double hueIncrement = 179.0/(ledBuffer.getLength()-1);

    for (int j = 0; j < ledBuffer.getLength(); j++) {
      int i = (j+startIndex) % ledBuffer.getLength(); // index of led
      int hue =(int) (j *hueIncrement);
      ledBuffer.setHSV(i, hue, 225, 50);   
    }
    led.setData(ledBuffer);
  }

  @Override
  public void periodic() {
  }
}
