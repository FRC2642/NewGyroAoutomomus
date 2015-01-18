package org.usfirst.frc.team2642.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.RobotDrive.MotorType;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
    Gyro gyro;
    RobotDrive robotDrive;
    Joystick stick;
    final int frontLeftChannel	= 2;
    final int rearLeftChannel	= 3;
    final int frontRightChannel	= 1;
    final int rearRightChannel	= 0;
    final int joystickChannel	= 0;
    double Kp = 0.03;
    double angle;
    int autoLoopCounter;  
    public void robotInit() {                                                               //This function is run when the robot is first started up
        gyro = new Gyro(0); 
       
    	robotDrive = new RobotDrive(2, 3, 1, 0);
        //robotDrive.setExpiration(0.1);
    	robotDrive.setInvertedMotor(MotorType.kFrontLeft, true);	                        // invert the left side motors
    	robotDrive.setInvertedMotor(MotorType.kRearLeft, true);	                           	// you may need to change or remove this to match your robot
        stick = new Joystick(joystickChannel);
    }
    
    public void autonomousInt() {                                                              //This function is run once each time the robot enters autonomous mode
    	gyro.reset();
    	autoLoopCounter = 0;

    }
    
    public void autonomousPeriodic() {                                                      //This function is called periodically during autonomous
    	angle = gyro.getAngle();
    	if(autoLoopCounter < 1000) {
    		robotDrive.mecanumDrive_Cartesian(0, -0.4, angle*Kp, 0);  // drive towards heading 0
    	}else{
    		robotDrive.mecanumDrive_Cartesian(0.0, 0.0, 0.0, 0.0);
    	}
	}
    //-1*angle*Kp
    
    public void teleopInit(){                                                               //This function is called once each time the robot enters tele-operated mode
    	gyro.reset();
    }
    
    public void teleopPeriodic() {                                                          //This function is called periodically during operator control
        System.out.println(gyro.getAngle());
        robotDrive.mecanumDrive_Cartesian(stick.getX(), stick.getY(), stick.getRawAxis(4), 0);
    }

    public void testPeriodic() {                                                            //This function is called periodically during test mode
    	 LiveWindow.run();
    }
    
}
