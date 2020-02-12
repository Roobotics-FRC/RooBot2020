package frc.team4373.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.team4373.robot.input.OI;
import frc.team4373.robot.subsystems.Drivetrain;

public class DrivetrainCommand extends Command {
    // Represents the number of 50-millisecond intervals to wait before braking
    private static final int BRAKE_DELAY_COUNTS = 40;

    private Drivetrain drivetrain;

    private double brakeCountdown;

    public DrivetrainCommand() {
        requires(this.drivetrain = Drivetrain.getInstance());
    }

    @Override
    protected void initialize() {
        super.initialize();
    }

    @Override
    protected void execute() {
        double x = OI.getInstance().getDriveJoystick().rooGetX();
        double y = -OI.getInstance().getDriveJoystick().rooGetY();
        double rotation = OI.getInstance().getDriveJoystick().rooGetTwist();

        if (x != 0 || y != 0 || rotation != 0) {
            this.brakeCountdown = 0;
            this.drivetrain.drive(rotation, x, y);
        } else {
            ++this.brakeCountdown;
            if (this.brakeCountdown > BRAKE_DELAY_COUNTS) {
                this.drivetrain.brake();
            } else {
                this.drivetrain.stop();
            }
        }
    }

    @Override
    protected void end() {
        this.drivetrain.stop();
    }

    @Override
    protected void interrupted() {
        this.end();
    }

    @Override
    protected boolean isFinished() {
        return false;
    }
}
