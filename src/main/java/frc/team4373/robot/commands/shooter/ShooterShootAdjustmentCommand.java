package frc.team4373.robot.commands.shooter;

import frc.team4373.robot.RobotMap;
import frc.team4373.robot.input.OI;

/**
 * A Javadoc template. TODO: Update ShooterShootAdjustmentCommand Javadoc.
 */
public class ShooterShootAdjustmentCommand extends ShooterShootCommand {
    @Override
    protected void execute() {
        double adjustment =
                OI.getInstance().getOperatorJoystick().getAxis(
                        RobotMap.OPERATOR_JOYSTICK_LEFT_STICK_Y_AXIS);
        this.shooter.setVelocity((velocity + adjustment) * RobotMap.SHOOTER_MAX_SPEED_NATIVE_UNITS);
    }
}
