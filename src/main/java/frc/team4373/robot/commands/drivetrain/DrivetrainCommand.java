package frc.team4373.robot.commands.drivetrain;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.team4373.robot.RobotMap;
import frc.team4373.robot.input.OI;
import frc.team4373.robot.subsystems.Drivetrain;

import static frc.team4373.robot.Utils.isZero;

public class DrivetrainCommand extends Command {
    private Drivetrain drivetrain;

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

        if (OI.getInstance().getDriveJoystick().getRawButton(RobotMap.DRIVE_SLOWER_SPEED_BUTTON)) {
            x /= RobotMap.DRIVE_SLOWER_SPEED_FACTOR;
            y /= RobotMap.DRIVE_SLOWER_SPEED_FACTOR;
            rotation /= RobotMap.DRIVE_SLOWER_SPEED_FACTOR;
            this.drivetrain.drive(rotation, x, y);
            return;
        }

        if (!isZero(x) || !isZero(y) || !isZero(rotation)) {
            this.drivetrain.drive(rotation, x, y);
        } else if (OI.getInstance().getDriveJoystick().getRawButton(
                RobotMap.DRIVE_DISABLE_BRAKE_BUTTON)) {
            this.drivetrain.stop();
        } else {
            this.drivetrain.brake();
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
