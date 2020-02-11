package frc.team4373.robot.input.filters;

/**
 * Converts the slider from [1, -1] to [0, 1] on a Logitech Extreme 3D Pro joystick.
 */
public class LogitechSliderAxisFilter extends DoubleTypeFilter {
    @Override
    public Double applyFilter(Double val) {
        return (1 - val) / 2d;
        // return (((val * -1) + 1) / 2);
    }
}
