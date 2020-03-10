package frc.team4373.robot.commands.auton;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.team4373.robot.RobotMap;
import frc.team4373.robot.commands.drivetrain.TimedDriveAuton;
import frc.team4373.robot.commands.intake.IntakeReleaseCommand;
import frc.team4373.robot.commands.shooter.ShooterShootCommand;

public class RamThenShootAuton extends CommandGroup {
    /**
     * Constructs a new ram-then-shoot auton.
     */
    public RamThenShootAuton() {
        addSequential(new TimedDriveAuton(4, 0.4, 90));
        addSequential(new ShooterShootCommand(RobotMap.SHOOT_FROM_WALL_SPEED),
                RobotMap.AUTON_SHOOT_TIME_SEC);
        addParallel(new IntakeReleaseCommand());
    }
}
