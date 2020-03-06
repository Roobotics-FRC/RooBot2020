package frc.team4373.robot.input.filters;

/**
 * Filters and inverts Xbox throttle input.
 */
public class XboxThrottleFilter extends DoubleTypeFilter {
    @Override
    public Double applyFilter(Double val) {
        return -Math.copySign(Math.pow(val, 2), val);
    }
}
