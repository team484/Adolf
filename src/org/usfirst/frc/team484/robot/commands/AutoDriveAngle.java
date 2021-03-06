package org.usfirst.frc.team484.robot.commands;

import org.usfirst.frc.team484.robot.Robot;
import org.usfirst.frc.team484.robot.RobotSettings;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutoDriveAngle extends Command {

	double deg;
	double mag;
	PIDController pid;
    public AutoDriveAngle(double deg, double mag) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	this.deg = deg;
    	this.mag = mag;
    	requires(Robot.driveTrain);
    	pid = new PIDController(RobotSettings.rotateKP, RobotSettings.rotateKI, RobotSettings.kD, new PIDSource() {
			
			@Override
			public void setPIDSourceType(PIDSourceType pidSource) {}
			
			@Override
			public double pidGet() {
				return Robot.driveTrain.getRobotAngle();
			}
			
			@Override
			public PIDSourceType getPIDSourceType() {
				// TODO Auto-generated method stub
				return PIDSourceType.kDisplacement;
			}
		}, new PIDOutput() {
			
			@Override
			public void pidWrite(double output) {
		    	Robot.driveTrain.driveWithValues(deg, mag, -output);
			}
		});
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	pid.reset();
    	pid.setSetpoint(Robot.driveTrain.getRobotAngle());
    	pid.enable();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	pid.disable();
    	Robot.driveTrain.doNothing();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
