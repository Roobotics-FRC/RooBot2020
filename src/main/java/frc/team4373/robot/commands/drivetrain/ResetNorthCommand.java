package frc.team4373.robot.commands.drivetrain;

import edu.wpi.first.wpilibj.command.Command;
import frc.team4373.robot.subsystems.Drivetrain;

public class ResetNorthCommand extends Command {
    private Drivetrain drivetrain;

    public ResetNorthCommand() {
        requires(this.drivetrain = Drivetrain.getInstance());
    }

    @Override
    protected void execute() {
        this.drivetrain.resetPigeonYaw();
    }

    @Override
    protected boolean isFinished() {
        return true;
    }
}
