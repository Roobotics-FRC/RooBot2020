package frc.team4373.robot.subsystems;

import frc.team4373.robot.RobotMap;
import frc.team4373.robot.input.OI;
import frc.team4373.swerve.SwerveConfig;
import frc.team4373.swerve.SwerveDrivetrain;
import frc.team4373.swerve.commands.SwerveDriveWithJoystick;

/**
 * A Javadoc template. TODO: Update Drivetrain Javadoc.
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
                    instance = new Drivetrain(RobotMap.getSwerveConfig());
                }
            }
        }
        return instance;
    }

    protected Drivetrain(SwerveConfig config) {
        super(config);
    }

    @Override
    protected void initDefaultCommand() {
        setDefaultCommand(new SwerveDriveWithJoystick(OI.getInstance().getDriveJoystick()::rooGetX,
                OI.getInstance().getDriveJoystick()::rooGetY,
                OI.getInstance().getDriveJoystick()::rooGetTwist,
                this));
    }
}