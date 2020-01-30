package frc.team4373.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.team4373.robot.subsystems.Intake;

public class StopIntakeCommand extends Command {
    private Intake intake;

    public StopIntakeCommand() {
        requires(this.intake = Intake.getInstance());
    }

    @Override
    protected void execute() {
        this.intake.stop();
    }

    @Override
    protected boolean isFinished() {
        return false;
    }

    @Override
    protected void end() {
        this.intake.stop();
    }

    @Override
    protected void interrupted() {
        this.end();
    }
}
