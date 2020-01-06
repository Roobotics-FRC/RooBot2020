package frc.team4373.robot.input.filters;

/**
 * A generic, abstract Double-based filter type.
 */
public abstract class DoubleTypeFilter implements GenericFilter<Double> {
    public abstract Double applyFilter(Double val);
}