package frc.team4373.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class DriveAndShootAuton extends CommandGroup {

    /**
     * Drives off the initiation line, turns to a specified angle, and  shoots.
     */
    public DriveAndShootAuton() {
        addSequential(new TimedDriveAuton(0.5, 1, 0));
        addSequential(new VisionQuerierCommand("degree_offset", 2, RotateAngleOffsetAuton::new));
        addSequential(new ShooterShootCommand(),5);
    }
}
