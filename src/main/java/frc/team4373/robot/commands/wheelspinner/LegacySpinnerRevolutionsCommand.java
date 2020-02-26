package frc.team4373.robot.commands.wheelspinner;

import edu.wpi.first.wpilibj.command.Command;
import frc.team4373.robot.subsystems.WheelSpinner;

public class LegacySpinnerRevolutionsCommand extends Command {
    private WheelSpinner spinner;

    private final int revolutionSetpoint;

    private int repetitionCount;
    private WheelSpinner.Color startColor;
    // Indicates whether we are still over the same color
    private boolean startColorPresent;

    /**
     * Constructs a new revolution-based wheel spinner command.
     * @param count the number of revolutions of the wheel to complete.
     */
    public LegacySpinnerRevolutionsCommand(int count) {
        requires(this.spinner = WheelSpinner.getInstance());
        this.revolutionSetpoint = count;
    }

    @Override
    protected void initialize() {
        this.repetitionCount = 0;
        this.startColor = this.spinner.getColor();
        this.startColorPresent = true;
    }

    @Override
    protected void execute() {
        this.spinner.spin();
        WheelSpinner.Color currentColor = this.spinner.getColor();
        if (currentColor != startColor) {
            this.startColorPresent = false;
        } else if (!this.startColorPresent) {
            this.startColorPresent = true;
            ++this.repetitionCount;
        }
    }

    @Override
    protected boolean isFinished() {
        // There are 2 occurrences of each color per revolution
        return this.repetitionCount / 2 >= this.revolutionSetpoint;
    }

    @Override
    protected void end() {
        this.spinner.stopSpinner();
    }

    @Override
    protected void interrupted() {
        this.end();
    }
}
