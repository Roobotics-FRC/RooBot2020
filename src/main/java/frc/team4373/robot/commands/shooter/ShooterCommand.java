package frc.team4373.robot.commands.shooter;

import edu.wpi.first.wpilibj.command.Command;
import frc.team4373.robot.subsystems.Shooter;

public class ShooterCommand extends Command {
    private Shooter shooter;

    public ShooterCommand() {
        requires(this.shooter = Shooter.getInstance());
    }

    @Override
    protected void execute() {
        shooter.stopShooter();
    }

    @Override
    protected boolean isFinished() {
        return false;
    }

    @Override
    protected void end() {
        shooter.stopShooter();
    }

    @Override
    protected void interrupted() {
        this.end();
    }
}
