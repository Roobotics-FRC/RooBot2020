package frc.team4373.robot.commands.util;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Temporarily takes control of a given subsystem so that it returns to its default command.
 */
public class ClearSubsystemCommand extends Command {
    /**
     * Constructs a new ClearSubsystemCommand for a given subsystem.
     * @param subsystem the subsystem of which to take control.
     */
    public ClearSubsystemCommand(Subsystem subsystem) {
        requires(subsystem);
    }

    @Override
    protected boolean isFinished() {
        return true;
    }
}