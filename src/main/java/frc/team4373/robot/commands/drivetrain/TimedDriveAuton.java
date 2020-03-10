package frc.team4373.robot.commands.drivetrain;

import edu.wpi.first.wpilibj.command.PIDCommand;
import frc.team4373.robot.RobotMap;
import frc.team4373.robot.subsystems.Drivetrain;

public class TimedDriveAuton extends PIDCommand {
    private Drivetrain drivetrain;

    private double speed;
    private double time;
    private double angle;

    /**
     * Constructs a time based, driving auton.
     * @param time the time the command runs for.
     * @param speed the speed at which the robot moves.
     * @param angle the angle at which the robot moves.
     */
    public TimedDriveAuton(double time, double speed, double angle) {
        super(RobotMap.DRIVE_STRAIGHT_ROTATE_GAINS.kP, RobotMap.DRIVE_STRAIGHT_ROTATE_GAINS.kI,
                RobotMap.DRIVE_STRAIGHT_ROTATE_GAINS.kD);
        requires(this.drivetrain = Drivetrain.getInstance());

        this.time = time;
        this.speed = speed;
        this.angle = angle;
    }

    @Override
    protected void initialize() {
        setTimeout(time);
        this.setSetpoint(returnPIDInput());
    }

    @Override
    protected double returnPIDInput() {
        return drivetrain.getAngle();
    }

    @Override
    protected void usePIDOutput(double rotOutput) {
        double x = Math.cos(Math.toRadians(this.angle)) * speed;
        double y = Math.sin(Math.toRadians(this.angle)) * speed;
        this.drivetrain.drive(rotOutput * RobotMap.DRIVE_ASSIST_MAX_TURN_SPEED, x, y);
    }

    @Override
    protected boolean isFinished() {
        return this.isTimedOut();
    }

    @Override
    protected void end() {
        this.drivetrain.stop();
    }

    @Override
    protected void interrupted() {
        this.end();
    }
}
