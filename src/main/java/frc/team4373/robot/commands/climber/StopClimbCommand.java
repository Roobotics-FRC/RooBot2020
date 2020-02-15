package frc.team4373.robot.commands.climber;

import edu.wpi.first.wpilibj.command.Command;
import frc.team4373.robot.subsystems.Climber;

public class StopClimbCommand extends Command {
    private Climber climber;

    public StopClimbCommand() {
        requires(this.climber = Climber.getInstance());
    }

    @Override
    protected void execute() {
        this.climber.stopLift();
        this.climber.raiseLeftWinch(0);
        this.climber.raiseRightWinch(0);

    }

    @Override
    protected boolean isFinished() {
        return false;
    }
}
