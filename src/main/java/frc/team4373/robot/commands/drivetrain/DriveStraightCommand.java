package frc.team4373.robot.commands.drivetrain;

import edu.wpi.first.wpilibj.command.PIDCommand;
import frc.team4373.robot.RobotMap;
import frc.team4373.robot.input.OI;
import frc.team4373.robot.subsystems.Drivetrain;

import static frc.team4373.robot.Utils.isZero;

public class DriveStraightCommand extends PIDCommand {
    private long lastManualRot = 0;

    private Drivetrain drivetrain;

    /**
     * Constructs a new command that assists the driver in driving straight.
     */
    public DriveStraightCommand() {
        super(RobotMap.DRIVE_STRAIGHT_ROTATE_GAINS.kP, RobotMap.DRIVE_STRAIGHT_ROTATE_GAINS.kI,
                RobotMap.DRIVE_STRAIGHT_ROTATE_GAINS.kD);
        requires(this.drivetrain = Drivetrain.getInstance());
    }

    @Override
    protected void initialize() {
        this.setSetpoint(returnPIDInput());
    }

    @Override
    protected double returnPIDInput() {
        return drivetrain.getAngle();
    }

    @Override
    protected void usePIDOutput(double rotPIDOutput) {
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

        boolean translating = !isZero(x) || !isZero(y);
        boolean rotating = !isZero(rotation);

        if (!rotating && translating && System.currentTimeMillis()
                > lastManualRot + RobotMap.DRIVE_STRAIGHT_COOLDOWN_MS) {
            // Not rotating & it's been long enough that we have a stable setpoint. Drive straight.
            double rotAssist = rotPIDOutput * RobotMap.DRIVE_ASSIST_MAX_TURN_SPEED;
            this.drivetrain.drive(rotAssist, x, y);

            // We want to maintain the existing setpoint, so break out so we don't set a new one.
            return;
        } else if (rotating || translating) {
            // We haven't cooled down long enough or we're still rotating. Drive normally.
            this.lastManualRot = System.currentTimeMillis();
            this.drivetrain.drive(rotation, x, y);
        } else if (OI.getInstance().getDriveJoystick().getRawButton(
                RobotMap.DRIVE_DISABLE_BRAKE_BUTTON)) {
            // We're not moving, but the driver doesn't want to brake. Just stop.
            this.drivetrain.stop();
        } else {
            // We're not moving & we're okay to brake. Brake.
            this.drivetrain.brake();
        }
        this.setSetpoint(drivetrain.getAngle());
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
