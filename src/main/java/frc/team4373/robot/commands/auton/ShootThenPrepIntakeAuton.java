package frc.team4373.robot.commands.auton;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import frc.team4373.robot.RobotMap;
import frc.team4373.robot.commands.camera.VisionQuerierCommand;
import frc.team4373.robot.commands.drivetrain.DriveDistanceAuton;
import frc.team4373.robot.commands.drivetrain.RequirementFreeRotateAngleOffsetAuton;
import frc.team4373.robot.commands.intake.IntakeReleaseCommand;
import frc.team4373.robot.commands.shooter.ShooterShootCommand;

/**
 * An auton command that shoots and then gets in position to intake for teleop.
 *
 * <p>It does the following:
 * <ul>
 *     <li>Aligns to target.
 *     <li>Shoots balls.
 *     <li>Drives back 100 inches (to around the intake zone).
 * </ul>
 */
public class ShootThenPrepIntakeAuton extends CommandGroup {
    /**
     * Constructs a new shoot-then-drive-to-intake-zone auton.
     */
    public ShootThenPrepIntakeAuton() {
        addSequential(new VisionQuerierCommand(RobotMap.VISION_ANG_OFFSET_FIELD,
                RobotMap.VISION_ALIGN_ALLOWABLE_OFFSET_DEG,
                RequirementFreeRotateAngleOffsetAuton::new));
        addSequential(new ShooterShootCommand(RobotMap.AUTON_LINE_SHOOT_SPEED),
                RobotMap.AUTON_SHOOT_TIME_SEC);
        addSequential(new WaitCommand(RobotMap.SHOOTER_TIME_TO_SPIN_UP_SEC));
        addSequential(new IntakeReleaseCommand());
        addSequential(new DriveDistanceAuton(100, 0.2, 90)); // -90 is forward at start
    }
}
