package frc.team4373.robot.input.filters;

/**
 * A filter to minimize fiddliness of joystick twist.
 */
public class SwerveTwistFilter extends DoubleTypeFilter {
    @Override
    public Double applyFilter(Double val) {
        return Math.pow(val, 3);
    }
}
