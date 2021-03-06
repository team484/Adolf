package org.usfirst.frc.team484.robot.subsystems;

import org.usfirst.frc.team484.robot.Robot;
import org.usfirst.frc.team484.robot.commands.ShooterDoNothing;

import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class BallShooter extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new ShooterDoNothing());
;    }
    public void setShooterSpeed(double speed) {
    	Robot.io.shooterMotor.set(speed);
    	Robot.io.shooterLED.set(Relay.Value.kForward);
    }
    public void doNothing() {
    	Robot.io.shooterMotor.set(0.0);
    	Robot.io.shooterLED.set(Relay.Value.kOff);
    }
}

