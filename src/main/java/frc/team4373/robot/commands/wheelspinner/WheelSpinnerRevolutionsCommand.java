package frc.team4373.robot.commands.wheelspinner;

import edu.wpi.first.wpilibj.command.Command;
import frc.team4373.robot.subsystems.WheelSpinner;

public class WheelSpinnerRevolutionsCommand extends Command {
    private WheelSpinner spinner;

    private final int revolutionSetpoint;

    private int revolutionCount;
    private WheelSpinner.Color startColor;
    // Indicates whether we are still over the
    private boolean startColorPresent;
    private boolean finished;

    /**
     * Constructs a new revolution-based wheel spinner command.
     * @param count the number of revolutions of the wheel to complete.
     */
    public WheelSpinnerRevolutionsCommand(int count) {
        requires(this.spinner = WheelSpinner.getInstance());
        this.revolutionSetpoint = count;
    }

    @Override
    protected void initialize() {
        this.revolutionCount = 0;
        this.startColor = this.spinner.getColor();
        this.startColorPresent = true;
        this.finished = false;
    }

    @Override
    protected void execute() {
        this.spinner.spin();
        WheelSpinner.Color currentColor = this.spinner.getColor();
        if (currentColor != startColor) {
            this.startColorPresent = false;
        } else if (!this.startColorPresent) {
            this.startColorPresent = true;

            if (++this.revolutionCount >= this.revolutionSetpoint) {
                this.finished = true;
            }
        }
    }

    @Override
    protected boolean isFinished() {
        return this.finished;
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
