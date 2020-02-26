package frc.team4373.robot.commands.wheelspinner;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import frc.team4373.robot.RobotMap;

public class RevolutionSpinCommandGroup extends CommandGroup {
    /**
     * Constructs a new command group for deploying the wheel spinner and spinning
     * the wheel spinner a number of revolutions.
     * @param revolutions the number of revolutions to spin the wheel.
     */
    public RevolutionSpinCommandGroup(int revolutions) {
        addSequential(new DeploySpinnerCommand());
        addSequential(new WheelSpinnerRevolutionsCommand(revolutions));
        addSequential(new RetractSpinnerCommand());
    }
}
