package frc.team4373.robot.input.filters;

/**
 * A Javadoc template. TODO: Update SwerveTwistFilter Javadoc.
 */
public class SwerveTwistFilter extends DoubleTypeFilter {
    @Override
    public Double applyFilter(Double val) {
        return Math.pow(val, 3);
    }
}
