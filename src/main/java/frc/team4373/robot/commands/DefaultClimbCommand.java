package frc.team4373.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.team4373.robot.subsystems.Climber;

public class DefaultClimbCommand extends Command {
    private Climber climber;

    public DefaultClimbCommand() {
        requires(this.climber = Climber.getInstance());
    }

    @Override
    protected void execute() {
        this.climber.stopLift();
    }

    @Override
    protected boolean isFinished() {
        return false;
    }

    @Override
    protected void end() {
        this.climber.stopLift();
    }

    @Override
    protected void interrupted() {
        this.end();
    }
}
