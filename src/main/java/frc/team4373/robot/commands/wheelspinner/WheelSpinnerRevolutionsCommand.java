package frc.team4373.robot.commands.wheelspinner;

import edu.wpi.first.wpilibj.command.Command;
import frc.team4373.robot.subsystems.WheelSpinner;

/**
 * Spins the wheel (Control Panel) the given number of times, based on the color sensor.
 */
public class WheelSpinnerRevolutionsCommand extends Command {
    private static final int PIE_SLICES_PER_CIRCLE = 8;
    private WheelSpinner wheelSpinner;

    private WheelSpinner.Color previousColor;
    private int colorChanges;
    private final int targetColorChanges;

    public WheelSpinnerRevolutionsCommand(int revolutions) {
        targetColorChanges = revolutions * PIE_SLICES_PER_CIRCLE;
        requires(this.wheelSpinner = WheelSpinner.getInstance());
    }

    @Override
    protected void initialize() {
        previousColor = wheelSpinner.getColor();
        colorChanges = 0;
    }

    @Override
    protected void execute() {
        wheelSpinner.spin();
        if (previousColor != wheelSpinner.getColor()) {
            ++colorChanges;
        }
    }

    @Override
    protected boolean isFinished() {
        return colorChanges >= targetColorChanges;
    }

    @Override
    protected void end() {
        wheelSpinner.stopSpinner();
    }

    @Override
    protected void interrupted() {
        this.end();
    }
}
