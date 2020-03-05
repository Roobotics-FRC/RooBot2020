package frc.team4373.robot.commands.util;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.team4373.robot.subsystems.*;

/**
 * A command group that returns all subsystems to their default commands.
 */
public class ClearSubsystemsCommandGroup extends CommandGroup {
    /**
     * Constructs a ClearSubsystemsCommandGroup.
     */
    public ClearSubsystemsCommandGroup() {
        addParallel(new ClearSubsystemCommand(Camera.getInstance()));
        addParallel(new ClearSubsystemCommand(Climber.getInstance()));
        addParallel(new ClearSubsystemCommand(Drivetrain.getInstance()));
        addParallel(new ClearSubsystemCommand(Intake.getInstance()));
        addParallel(new ClearSubsystemCommand(Shooter.getInstance()));
        // addParallel(new ClearSubsystemCommand(WheelSpinner.getInstance()));
    }
}