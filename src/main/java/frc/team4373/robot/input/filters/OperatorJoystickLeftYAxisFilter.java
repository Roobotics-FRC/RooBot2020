package frc.team4373.robot.input.filters;

/**
 * A Javadoc template. TODO: Update OperatorJoystickLeftYAxisFilter Javadoc.
 */
public class OperatorJoystickLeftYAxisFilter extends DoubleTypeFilter {
    @Override
    public Double applyFilter(Double val) {
        return Math.pow(val, 2) / 2;
    }
}
