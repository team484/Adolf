package org.usfirst.frc.team484.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoGearPlace extends CommandGroup {

    public AutoGearPlace() {
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
		addSequential(new PointWheels(270), 1);
    	addSequential(new AutoDriveAngleVision(270, .4), 5.5); //90?
    	addSequential(new AutoDriveAngle(90, 0.4), 0.2);
    	//addSequential(new AutoDoNothing(), 1);
    	//addSequential(new AutoCenterRobot(), 5);
    	//addSequential(new AutoDoNothing(), 1);
    	//addSequential(new AutoDriveAngle(270, .4), 1);
    }
}
