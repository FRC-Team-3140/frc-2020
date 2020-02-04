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
 * 
 * max speed of robot: ~15 ft/s
 */
public class TrapezoidalProfileGenerator {
    public double maxAccel, maxSpeed, distance, dt;

    private List<Double> distances = new ArrayList<>();
    private List<Double> speeds = new ArrayList<>();
    private List<Double> times = new ArrayList<>();

    /*
     * @param maxAccel  maximum acceleration in units per sec^2
     * @param maxSpeed  maximum speed in units per sec
     * @param distance  distance in units to drive
     * @param dt        time interval between each trajectory point in seconds
     */
    public TrapezoidalProfileGenerator(double maxAccel, double maxSpeed, double distance, double dt) {
        this.maxAccel = maxAccel;
        this.maxSpeed = maxSpeed;
        this.distance = distance;
        this.dt = dt;
    }
    private void distanceFunction(double t) {
        if(maxSpeed / maxAccel *maxSpeed >= distance) { // if this will be a triangular profile
        }
        else {
            
        }

    }

    private void makeProfile() {
        if(maxSpeed / maxAccel *maxSpeed >= distance) { // if this will be a triangular profile
            
        }
        else { // if this will be a trapezoidal profile

            /*
             * Velocity vs time graph:
             * 
             *     _______ maxSpeed
             *    /|     |\
             *   / |     | \
             *  /d1|  d2 |d3\
             * /___|_____|___\
             *   t1   t2   t3
             */
            double t1 = maxSpeed/maxAccel;
            double d1 = .5 *t1 * maxSpeed;
            double d2 = distance - 2*d1;
            double t2 = d2 / maxSpeed;
            double totalTime  = 2*t1 + t2;
            
            double currentSpeed = 0, currentDistance = 0, currentTime=0;
            double seg1EndIndex;
            // the ramping up segment
            while(currentSpeed < maxSpeed) {

                if(currentSpeed + maxAccel*dt > maxSpeed) {
                    double dv = maxSpeed - currentSpeed;
                    currentSpeed = maxSpeed; 
                    currentTime += maxAccel/dv;
                    currentDistance += currentSpeed * maxAccel/dv;
                    break;
                } 
                else {
                    currentTime += dt;
                    currentSpeed += maxAccel * dt;
                    currentDistance += currentSpeed * dt;    
                }
                times.add(currentTime);
                distances.add(currentDistance);
                speeds.add(currentSpeed);
            }
            
        }
    }

}
