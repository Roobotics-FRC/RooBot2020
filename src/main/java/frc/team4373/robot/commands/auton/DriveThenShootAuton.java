package frc.team4373.robot.commands.auton;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.team4373.robot.RobotMap;
import frc.team4373.robot.commands.camera.VisionQuerierCommand;
import frc.team4373.robot.commands.drivetrain.*;
import frc.team4373.robot.commands.shooter.ShooterShootCommand;

/**
 * Drives off the initiation line, aims, and shoots.
 * <ol>
 *     <li>Drives backward from the initiation line.
 *     <li>Aims at the target.
 *     <li>Strafes right to line up the shooter instead of the camera.
 *     <li>Shoot for 5 seconds.
 * </ol>
 */
public class DriveThenShootAuton extends CommandGroup {

    /**
     * Drives off the initiation line, turns to the target, and shoots.
     *
     * <p>See the class documentation for details.
     */
    public DriveThenShootAuton() {
        addSequential(new TimedDriveAuton(0.5, 1, 90));
        addSequential(new VisionQuerierCommand(RobotMap.VISION_ANG_OFFSET_FIELD, 2,
                RequirementFreeRotateAngleOffsetAuton::new));
        // FIXME: Change camera angle to include offset, then remove this line
        addSequential(new TimedDriveAuton(0.25, 0.55, 0));
        addSequential(new ShooterShootCommand(RobotMap.AUTON_LINE_SHOOT_SPEED), 5);
    }
}
