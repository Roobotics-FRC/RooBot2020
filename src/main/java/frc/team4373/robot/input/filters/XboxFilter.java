package frc.team4373.robot.input.filters;

/**
 * The usual filter for the Xbox controller used as the operator joystick.
 * https://www.desmos.com/calculator/8tov0anluh
 */
public class XboxFilter extends DoubleTypeFilter {
    @Override
    public Double applyFilter(Double input) {
        return Math.copySign(Math.pow(Math.abs(input), 3.5), input);
    }
}