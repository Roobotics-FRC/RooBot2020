package frc.team4373.robot.input;

import frc.team4373.robot.RobotMap;
import frc.team4373.robot.input.filters.LogitechFilter;
import frc.team4373.robot.input.filters.XboxFilter;

/**
 * OI provides access to operator interface devices.
 */
public final class OI {
    private static volatile OI oi = null;
    private RooJoystick driveJoystick;
    private RooJoystick operatorJoystick;

    private OI() {
        //FIXME: These filters need to be tested.
        /*
        FineGrainedPiecewiseFilter: https://www.desmos.com/calculator/3rhniwotk2
        XboxAxisFilter: https://www.desmos.com/calculator/r6t3rzmh2x

        Template for new filters: https://www.desmos.com/calculator/jbb9fc5zwh
         */
        this.driveJoystick = new RooJoystick(RobotMap.DRIVE_JOYSTICK_PORT,
                new LogitechFilter(), RobotMap.JOYSTICK_DEFAULT_DEADZONE);
        this.operatorJoystick = new RooJoystick(RobotMap.OPERATOR_JOYSTICK_PORT,
                new XboxFilter(), RobotMap.JOYSTICK_DEFAULT_DEADZONE);
    }

    /**
     * The getter for the OI singleton.
     *
     * @return The static OI singleton object.
     */
    public static OI getInstance() {
        if (oi == null) {
            synchronized (OI.class) {
                if (oi == null) {
                    oi = new OI();
                }
            }
        }
        return oi;
    }

    /**
     * Gets the drive joystick controlling the robot.
     * @return The drive joystick controlling the robot.
     */
    public RooJoystick getDriveJoystick() {
        return this.driveJoystick;
    }

    /**
     * Gets the operator joystick controlling the robot.
     * @return The operator joystick controlling the robot.
     */
    public RooJoystick getOperatorJoystick() {
        return this.operatorJoystick;
    }
}
