package frc.team4373.robot.commands.drivetrain;

import edu.wpi.first.wpilibj.command.PIDCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.team4373.robot.RobotMap;
import frc.team4373.robot.subsystems.Drivetrain;


/**
 * Rotates the robot a specified number of degrees.
 */
public class RotateAngleOffsetAuton extends PIDCommand {
    private static final double MOTOR_OUTPUT_THRESHOLD = 0.2;
    private static final RobotMap.PID pid = new RobotMap.PID(0, 0.08, 0.05, 0.15);

    private Drivetrain drivetrain;
    private double offset;
    private double targetAngle;
    private boolean finished = false;

    /**
     * Constructs an offset rotator auton command.
     * @param offset the angle offset by which to rotate, in degrees.
     */
    public RotateAngleOffsetAuton(double offset) {
        super("RotateAngleAuton", pid.kP, pid.kI, pid.kD);
        requires(this.drivetrain = Drivetrain.getInstance());
        this.offset = offset;
    }

    @Override
    protected void initialize() {
        targetAngle = drivetrain.getPigeonYawRaw() + offset;
        this.setSetpoint(targetAngle);
        this.finished = false;
        setTimeout(RobotMap.MAX_TURN_AUTON_TIME_SEC);
    }

    @Override
    protected boolean isFinished() {
        SmartDashboard.putNumber("targetAngle", targetAngle);
        return this.finished || this.isTimedOut();
    }

    @Override
    protected double returnPIDInput() {
        return drivetrain.getPigeonYawRaw();
    }

    @Override
    protected void usePIDOutput(double output) {
        SmartDashboard.putNumber("output", output);
        if (Math.abs(output) <= MOTOR_OUTPUT_THRESHOLD) {
            this.finished = true;
            return;
        }
        drivetrain.drive(output * RobotMap.AUTON_TURN_SPEED, 0, 0);
    }

    @Override
    protected void end() {
        drivetrain.stop();
    }

    @Override
    protected void interrupted() {
        end();
    }
}
