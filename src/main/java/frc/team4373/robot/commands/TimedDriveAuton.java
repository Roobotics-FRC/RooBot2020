package frc.team4373.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.team4373.robot.subsystems.Drivetrain;
import frc.team4373.swerve.WheelVector;

public class TimedDriveAuton extends Command {
    private Drivetrain drivetrain;

    private double time;

    private WheelVector.VectorSet autonSetpoint;


    /**
     * Constructs a time based, driving auton.
     * @param speed the speed at which the robot moves.
     * @param time the time the command runs for.
     * @param angle the angle at which the robot moves.
     */
    public TimedDriveAuton(double speed, double time, double angle) {
        requires(this.drivetrain = Drivetrain.getInstance());

        this.time = time;

        WheelVector autonVector = new WheelVector(speed, angle);
        autonSetpoint = new WheelVector.VectorSet(autonVector, autonVector, autonVector,
                autonVector);

    }

    @Override
    protected void initialize() {
        setTimeout(time);

    }

    @Override
    protected boolean isFinished() {
        return this.isTimedOut();
    }

    @Override
    protected void execute() {
        this.drivetrain.setWheelsPID(autonSetpoint);
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
