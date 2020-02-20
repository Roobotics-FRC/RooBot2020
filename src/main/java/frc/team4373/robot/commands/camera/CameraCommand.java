package frc.team4373.robot.commands.camera;

import edu.wpi.first.wpilibj.command.Command;
import frc.team4373.robot.subsystems.Camera;

/**
 * A placeholder command that maintains control of the camera subsystem.
 */
public class CameraCommand extends Command {
    /**
     * Constructs a camera command.
     */
    public CameraCommand() {
        requires(Camera.getInstance());
    }

    @Override
    protected boolean isFinished() {
        return false;
    }
}
