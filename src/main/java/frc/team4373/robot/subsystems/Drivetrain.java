package frc.team4373.robot.subsystems;

import frc.team4373.robot.RobotMap;
import frc.team4373.robot.commands.drivetrain.DrivetrainCommand;
import frc.team4373.swerve.SwerveDrivetrain;

/**
 * The swerve drivetrain subsystem.
 */
public class Drivetrain extends SwerveDrivetrain {
    private static volatile Drivetrain instance;

    /**
     * The getter for the Drivetrain class.
     * @return the singleton Drivetrain object.
     */
    public static Drivetrain getInstance() {
        if (instance == null) {
            synchronized (Drivetrain.class) {
                if (instance == null) {
                    instance = new Drivetrain();
                }
            }
        }
        return instance;
    }

    protected Drivetrain() {
        super(RobotMap.getSwerveConfig());
        this.setPigeonYaw(-90);
    }

    @Override
    protected void initDefaultCommand() {
        setDefaultCommand(new DrivetrainCommand());
    }
}
