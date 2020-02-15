package frc.team4373.robot.commands.drivetrain;

import edu.wpi.first.wpilibj.command.PIDCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.team4373.robot.RobotMap;
import frc.team4373.robot.Utils;
import frc.team4373.robot.input.*;
import frc.team4373.robot.subsystems.Drivetrain;
import frc.team4373.swerve.SwerveDrivetrain;


/**
 * Rotates the robot a specified number of degrees.
 */
public class RotateAngleOffsetAuton extends PIDCommand {
    private static final double MOTOR_OUTPUT_THRESHOLD_MULTIPLIER = 0.25;
    private static final RobotMap.PID pid = new RobotMap.PID(0, 0.09, 0.05, 0.31);

    private Drivetrain drivetrain;
    private double offset;
    private double targetAngle;
    private boolean finished = false;
    private double maxSpeed = RobotMap.AUTON_TURN_SPEED;

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
        maxSpeed = SmartDashboard.getNumber("rot_max_speed", RobotMap.AUTON_TURN_SPEED);
        this.getPIDController().setOutputRange(
                -maxSpeed, maxSpeed);
        this.finished = false;
        this.getPIDController().setP(SmartDashboard.getNumber("kP", 0));
        this.getPIDController().setI(SmartDashboard.getNumber("kI", 0));
        this.getPIDController().setD(SmartDashboard.getNumber("kD", 0));
    }

    @Override
    protected boolean isFinished() {
        SmartDashboard.putNumber("targetAngle", targetAngle);
        return this.finished;
    }

    @Override
    protected double returnPIDInput() {
        return drivetrain.getPigeonYawRaw();
    }

    @Override
    protected void usePIDOutput(double output) {
        SmartDashboard.putNumber("rot_output", output);
        if (Math.abs(output) <= maxSpeed * MOTOR_OUTPUT_THRESHOLD_MULTIPLIER) {
            this.finished = true;
            return;
        }
        drivetrain.drive(output, 0, 0);
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
