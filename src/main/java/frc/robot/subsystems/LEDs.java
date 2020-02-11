/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
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
    for (var i = 0; i < ledBuffer.getLength(); i++) 
      ledBuffer.setHSV(i, 0, 0, 0);      
    
    led.setData(ledBuffer);

    rainbow(0,0);
  }

  // Rainbow but only lights up a certain number of segments out of 5
  public void rainbow(int startIndex, int segments) {

    double hueIncrement = 179.0/(ledBuffer.getLength()-1);
    int segmentLength = 120/5;

    int amountToLight = segmentLength * segments;

    for (int j = 0; j < ledBuffer.getLength(); j++) {
      int i = (j+startIndex) % ledBuffer.getLength(); // index of led
      int hue =(int) (j *hueIncrement);

      if(i >= amountToLight) {
        ledBuffer.setHSV(i, 0, 0, 0); 
        System.out.println("hello wtf");
      }  
      else {
        ledBuffer.setHSV(i, hue, 225, 50);   
        System.out.println("wtf 2 j:" + i+" "  + amountToLight);
      }
    }
    led.setData(ledBuffer);

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

  int lastSDBNum = 0;
  @Override
  public void periodic() {
    int newNum = (int) SmartDashboard.getNumber("LED segments", 0);
    if(newNum != lastSDBNum) {
      rainbow(0, newNum);
      lastSDBNum = newNum;
    }
  }
}
