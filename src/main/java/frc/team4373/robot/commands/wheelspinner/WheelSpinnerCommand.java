package frc.team4373.robot.commands.wheelspinner;

import edu.wpi.first.wpilibj.command.Command;
import frc.team4373.robot.subsystems.WheelSpinner;

public class WheelSpinnerCommand extends Command {
    private WheelSpinner wheelSpinner;

    public WheelSpinnerCommand() {
        requires(this.wheelSpinner = WheelSpinner.getInstance());
    }

    @Override
    protected void initialize() {
    }

    @Override
    protected void execute() {

    }

    @Override
    protected boolean isFinished() {
        return false;
    }

    @Override
    protected void end() {
    }

    @Override
    protected void interrupted() {
        this.end();
    }
}
