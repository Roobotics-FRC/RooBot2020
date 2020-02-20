package frc.team4373.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.team4373.robot.commands.camera.VisionQuerierCommand;
import frc.team4373.robot.commands.drivetrain.RotateAngleOffsetAuton;
import frc.team4373.robot.commands.drivetrain.TimedDriveAuton;
import frc.team4373.robot.commands.shooter.ShooterShootCommand;

public class DriveAndShootAuton extends CommandGroup {

    /**
     * Drives off the initiation line, turns to a specified angle, and  shoots.
     */
    public DriveAndShootAuton() {
        addSequential(new TimedDriveAuton(0.5, 1, 90));
        addSequential(new VisionQuerierCommand("degree_offset", 2,
                (d) -> new RotateAngleOffsetAuton(d, true)));
        addSequential(new TimedDriveAuton(0.25, 0.55, 0));
        addSequential(new ShooterShootCommand(), 5);
    }
}
