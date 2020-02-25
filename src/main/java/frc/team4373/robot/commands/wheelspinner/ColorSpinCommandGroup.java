package frc.team4373.robot.commands.wheelspinner;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import frc.team4373.robot.RobotMap;

public class ColorSpinCommandGroup extends CommandGroup {
    /**
     * Constructs a new command group for deploying the wheel spinner and spinning
     * the wheel spinner to a color.
     */
    public ColorSpinCommandGroup() {
        addSequential(new DeploySpinnerCommand());
        addSequential(new WheelSpinnerColorCommand());
        addSequential(new RetractSpinnerCommand());
    }
}
