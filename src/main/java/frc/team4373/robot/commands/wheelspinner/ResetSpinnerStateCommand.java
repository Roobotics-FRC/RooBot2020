package frc.team4373.robot.commands.wheelspinner;

import edu.wpi.first.wpilibj.command.Command;
import frc.team4373.robot.subsystems.WheelSpinner;

public class ResetSpinnerStateCommand extends Command {
    private WheelSpinner spinner;

    public ResetSpinnerStateCommand() {
        requires(this.spinner = WheelSpinner.getInstance());
    }

    @Override
    protected void execute() {
        this.spinner.stopSpinner();
        this.spinner.retractSpinner();
    }

    @Override
    protected boolean isFinished() {
        return true;
    }
}
