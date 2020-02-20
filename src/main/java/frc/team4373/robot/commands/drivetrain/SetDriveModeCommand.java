package frc.team4373.robot.commands.drivetrain;

import edu.wpi.first.wpilibj.command.Command;
import frc.team4373.robot.subsystems.Drivetrain;
import frc.team4373.swerve.SwerveDrivetrain;

public class SetDriveModeCommand extends Command {
    private SwerveDrivetrain.DriveMode mode;
    private Drivetrain drivetrain;

    public SetDriveModeCommand(SwerveDrivetrain.DriveMode mode) {
        requires(this.drivetrain = Drivetrain.getInstance());
        this.mode = mode;
    }

    @Override
    protected void execute() {
        this.drivetrain.setDriveMode(this.mode);
    }

    @Override
    protected boolean isFinished() {
        return true;
    }
}
