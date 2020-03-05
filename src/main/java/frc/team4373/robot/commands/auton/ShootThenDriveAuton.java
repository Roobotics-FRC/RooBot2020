package frc.team4373.robot.commands.auton;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.team4373.robot.RobotMap;
import frc.team4373.robot.commands.camera.VisionQuerierCommand;
import frc.team4373.robot.commands.drivetrain.RequirementFreeRotateAngleOffsetAuton;
import frc.team4373.robot.commands.drivetrain.TimedDriveAuton;
import frc.team4373.robot.commands.shooter.ShooterShootCommand;

/**
 * Shoots and then drives forward off the initiation line.
 */
public class ShootThenDriveAuton extends CommandGroup {

    /**
     * Constructs a new shoot-then-drive auton.
     */
    public ShootThenDriveAuton() {
        addSequential(new VisionQuerierCommand(RobotMap.VISION_ANG_OFFSET_FIELD,
                RobotMap.VISION_ALIGN_ALLOWABLE_OFFSET_DEG,
                RequirementFreeRotateAngleOffsetAuton::new));
        addSequential(new ShooterShootCommand(RobotMap.AUTON_LINE_SHOOT_SPEED),
                RobotMap.AUTON_SHOOT_TIME_SEC);
        addSequential(new TimedDriveAuton(0.5, 1, 90));
    }
}