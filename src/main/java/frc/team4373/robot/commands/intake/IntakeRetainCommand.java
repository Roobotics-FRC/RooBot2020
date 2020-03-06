package frc.team4373.robot.commands.intake;

import edu.wpi.first.wpilibj.command.Command;
import frc.team4373.robot.subsystems.Intake;

public class IntakeRetainCommand extends Command {
    private Intake intake;

    public IntakeRetainCommand() {
        requires(this.intake = Intake.getInstance());
    }

    @Override
    protected void execute() {
        this.intake.retainBall();
    }

    @Override
    protected boolean isFinished() {
        return false;
    }
}
