package frc.team4373.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.team4373.robot.subsystems.Drivetrain;

public class StopDrivetrainCommand extends Command {
    private Drivetrain drivetrain;

    public StopDrivetrainCommand() {
        this.drivetrain.stop();
    }

    @Override
    protected boolean isFinished() {
        return false;
    }
}
