package frc.team4373.robot.commands.intake;

import edu.wpi.first.wpilibj.command.Command;
import frc.team4373.robot.subsystems.Intake;

public class IntakeReleaseCommand extends Command {
    private static double WAIT_ITERATIONS = 25;
    private Intake intake;
    private double iterations = 0;

    public IntakeReleaseCommand() {
        requires(this.intake = Intake.getInstance());
    }

    @Override
    protected void initialize() {
        this.iterations = 0;
    }

    @Override
    protected void execute() {
        if (++this.iterations >= WAIT_ITERATIONS) {
            this.intake.releaseBall();
        }
    }

    @Override
    protected boolean isFinished() {
        return this.iterations > WAIT_ITERATIONS;
    }
}
