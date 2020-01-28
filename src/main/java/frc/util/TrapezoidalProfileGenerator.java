/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.util;

import java.util.ArrayList;
import java.util.List;

/**
 * Generates a trapezoidal profile from inputs
 * 
 * Returns a trapezoidal or triangular profile depending on inputs
 */
public class TrapezoidalProfileGenerator {
    public double maxAccel, maxSpeed, distance;

    private List<Double> distances = new ArrayList<>();

    public TrapezoidalProfileGenerator(double maxAccel, double maxSpeed, double distance) {
        this.maxAccel = maxAccel;
        this.maxSpeed = maxSpeed;
        this.distance = distance;

    }

    private void makeProfile() {
        if(maxSpeed / maxAccel *maxSpeed <= distance) { // if this will be a triangular profile

        }
        else { // if this will be a trapezoidal profile

        }
    }

}
