package org.usfirst.frc.team484.robot.commands;

import org.usfirst.frc.team484.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutoDriveIR extends Command {
	double distance;
	int i = 0;
    public AutoDriveIR(double setpoint) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	i = 0;
    	distance = setpoint;
    	requires(Robot.driveTrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	System.out.println("IR: " + Robot.infraredSensor.getAverageVoltage());
    	if(Robot.infraredSensor.getAverageVoltage() >= distance){
    		i++;
    	} else{
    		i = 0;
    	}
    	System.out.println(Robot.infraredSensor.getAverageVoltage());
    	Robot.driveTrain.driveWithValues(0, 0.4 , 0);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return i >= 5;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.driveTrain.doNothing();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}

