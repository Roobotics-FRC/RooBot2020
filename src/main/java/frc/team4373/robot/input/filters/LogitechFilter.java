package frc.team4373.robot.input.filters;

/**
 * The usual filter for the Logitech Extreme 3D Pro joystick used as the drive joystick.
 * https://www.desmos.com/calculator/xtnoz96ctg
 */
public class LogitechFilter extends DoubleTypeFilter {
    @Override
    public Double applyFilter(Double input) {
        return Math.copySign(Math.pow(input, 2), input);
    }
}
