package org.usfirst.frc.team484.robot.commands;

import org.usfirst.frc.team484.robot.Robot;
import org.usfirst.frc.team484.robot.RobotSettings;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class AutoRotate extends Command {
	double ang;
	PIDController pid;
    public AutoRotate(double ang) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	this.ang = ang;
    	requires(Robot.driveTrain);
    	pid = new PIDController(RobotSettings.rotateKP, RobotSettings.rotateKI, RobotSettings.rotateKD, new PIDSource() {
			
			@Override
			public void setPIDSourceType(PIDSourceType pidSource) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public double pidGet() {
				// TODO Auto-generated method stub
				SmartDashboard.putNumber("value", Robot.driveTrain.getRobotAngle());
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
				// TODO Auto-generated method stub
				Robot.driveTrain.driveWithValues(0.0, 0.0, -output * 12.0 / 9.0);
				SmartDashboard.putNumber("Write", output);
				
			}
		});
    
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.io.bottomGyro.reset();
    	Robot.io.topGyro.reset();
    	pid.reset();
    	pid.setSetpoint(ang);
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
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
