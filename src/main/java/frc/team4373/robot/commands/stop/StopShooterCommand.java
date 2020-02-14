package frc.team4373.robot.commands.stop;

import edu.wpi.first.wpilibj.command.Command;
import frc.team4373.robot.subsystems.Shooter;

public class StopShooterCommand extends Command {
    private Shooter shooter;

    public StopShooterCommand() {
        requires(this.shooter = Shooter.getInstance());
    }

    @Override
    protected void execute() {
        this.shooter.stopShooter();
    }

    @Override
    protected boolean isFinished() {
        return false;
    }


}
