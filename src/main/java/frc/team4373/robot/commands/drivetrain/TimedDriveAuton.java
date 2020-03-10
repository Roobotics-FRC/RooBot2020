package frc.team4373.robot.commands.drivetrain;

import edu.wpi.first.wpilibj.command.PIDCommand;
import frc.team4373.robot.RobotMap;
import frc.team4373.robot.subsystems.Drivetrain;
import frc.team4373.swerve.WheelVector;

public class TimedDriveAuton extends PIDCommand {
    private Drivetrain drivetrain;

    private double speed;
    private double time;
    private double angle;

    private WheelVector.VectorSet autonSetpoint;

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

        WheelVector autonVector = new WheelVector(speed, angle);
        autonSetpoint =
                new WheelVector.VectorSet(autonVector, autonVector, autonVector, autonVector);
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
    protected void usePIDOutput(double output) {
        this.drivetrain.setWheelsPID(autonSetpoint);
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
