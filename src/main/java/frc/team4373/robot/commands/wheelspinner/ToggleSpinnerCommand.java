package frc.team4373.robot.commands.wheelspinner;

import edu.wpi.first.wpilibj.command.Command;
import frc.team4373.robot.RobotMap;
import frc.team4373.robot.subsystems.WheelSpinner;

public class ToggleSpinnerCommand extends Command {
    private WheelSpinner spinner;

    public ToggleSpinnerCommand() {
        requires(this.spinner = WheelSpinner.getInstance());
    }

    @Override
    protected void initialize() {
        setTimeout(RobotMap.SPINNER_DEPLOY_TIME_SEC);
    }

    @Override
    protected void execute() {
        if (this.spinner.isRetracted()) {
            this.spinner.deploySpinner();
        } else {
            this.spinner.retractSpinner();
        }
    }

    @Override
    protected boolean isFinished() {
        return this.isTimedOut();
    }
}
