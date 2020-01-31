package frc.team4373.robot.input;

import edu.wpi.first.wpilibj.Joystick;
import frc.team4373.robot.input.filters.DoubleTypeFilter;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * This class extends the WPILib Joystick class to add deadzone and filter functionality.
 */
public class RooJoystick extends Joystick {
    private final class AxisConfiguration {
        DoubleTypeFilter filter;
        double deadzone;

        /**
         * Constructs a configuration for an axis with the specified filter and deadzone.
         * @param filter the filter through which to process all axis values.
         * @param deadzone the threshold below which all values will be zeroed.
         */
        AxisConfiguration(DoubleTypeFilter filter, double deadzone) {
            this.filter = filter;
            this.deadzone = deadzone;
        }
    }

    private DoubleTypeFilter defaultFilter = null;
    private double defaultDeadzone = 0;
    private Map<Integer, AxisConfiguration> axisMap;

    /**
     * Constructs a joystick on the specified port.
     * @param port the port to which the joystick is connected.
     */
    public RooJoystick(int port) {
        super(port);
        // Joystick access can be multithreaded, so use a thread-safe type
        this.axisMap = new ConcurrentHashMap<>();
    }

    /**
     * Constructs a joystick on the specified port with a default filter and deadzone.
     * These defaults can be overridden on a per-axis basis. All axes without specified
     * filters and deadzones will be processed using the default values.
     * @param port the port to which the joystick is connected.
     * @param defaultFilter the filter to apply by default to joystick axes.
     * @param defaultDeadzone the deadzone to apply by default to joystick axes.
     */
    public RooJoystick(int port, DoubleTypeFilter defaultFilter, double defaultDeadzone) {
        this(port);
        this.defaultFilter = defaultFilter;
        this.defaultDeadzone = defaultDeadzone;
    }

    /**
     * Configures the filter and deadzone for the specified axis.
     * @param axis the axis to configure.
     * @param filter the filter to apply to the axis.
     * @param deadzone the axis' deadzone (all values <= this magnitude will be zeroed).
     */
    public void configureAxis(int axis, DoubleTypeFilter filter, double deadzone) {
        axisMap.put(axis, new AxisConfiguration(filter, deadzone));
    }

    /**
     * Ignores input if it is within the deadzone (if it is negligible).
     * @param input the input value to be checked.
     * @param deadzone the deadzone value against which to check.
     * @return the input value if it is large enough, or 0 if it is negligible.
     */
    private double applyDeadzone(double input, double deadzone) {
        return Math.abs(input) <= deadzone ? 0 : input;
    }

    /**
     * Returns the filtered and deadzoned value of a joystick axis.
     * @param axis the axis to read from.
     * @return the processed axis value.
     */
    public double getAxis(int axis) {
        double rawAxis = this.getRawAxis(axis);
        AxisConfiguration config = this.axisMap.get(axis);
        if (config == null) {
            if (defaultFilter != null) {
                return applyDeadzone(defaultFilter.applyFilter(rawAxis), defaultDeadzone);
            }
            return applyDeadzone(rawAxis, defaultDeadzone);
        }
        return applyDeadzone(config.filter.applyFilter(rawAxis), config.deadzone);
    }

    /**
     * Calculates a normalized (0–360) angle from the y-axis to the joystick's filtered position.
     * This is analogous to a polar theta-value.
     * @return the normalized angle from the y-axis to the joystick location, in degrees.
     */
    public double getAngle() {
        // Compute the angle relative to the y-axis (90°)
        double rawAngle = 90 - Math.toDegrees(Math.atan2(rooGetY(), rooGetX()));
        // Normalize the angle so that it is positive
        return ((rawAngle % 360) + 360) % 360;
    }

    /**
     * Returns the absolute (positive) distance from the origin by which the joystick has been
     * displaced. This is analogous to a polar r-value.
     * @return the distance the joystick has been moved from the origin.
     */
    public double getDistance() {
        return Math.sqrt(Math.pow(rooGetX(), 2) + Math.pow(rooGetY(), 2));
    }

    // Axis convenience methods

    public double rooGetX() {
        return this.getAxis(this.getXChannel());
    }

    public double rooGetY() {
        return this.getAxis(this.getYChannel());
    }

    public double rooGetZ() {
        return this.getAxis(this.getZChannel());
    }

    public double rooGetTwist() {
        return this.getAxis(this.getTwistChannel());
    }

    public double rooGetThrottle() {
        return this.getAxis(this.getThrottleChannel());
    }
}
