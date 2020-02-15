package frc.team4373.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.team4373.robot.commands.camera.CameraCommand;

/**
 * A placeholder subsystem that allows for tracking and killing commands that require only the
 * camera.
 */
public class Camera extends Subsystem {
    private static volatile Camera instance;

    /**
     * The getter for the Camera class.
     * @return the singleton Camera object.
     */
    public static Camera getInstance() {
        if (instance == null) {
            synchronized (Camera.class) {
                if (instance == null) {
                    instance = new Camera();
                }
            }
        }
        return instance;
    }

    @Override
    protected void initDefaultCommand() {
        setDefaultCommand(new CameraCommand());
    }
}
