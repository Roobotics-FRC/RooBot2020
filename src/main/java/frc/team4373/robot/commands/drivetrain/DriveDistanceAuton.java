package frc.team4373.robot.commands.drivetrain;

import edu.wpi.first.wpilibj.command.PIDCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.team4373.robot.RobotMap;
import frc.team4373.robot.subsystems.Drivetrain;

/**
 * Drives a distance in the specified direction. Field/bot-oriented driving is determined by the
 * drivetrain's current drive mode (own-ship- or field-up).
 */
public class DriveDistanceAuton extends PIDCommand {
    public static final RobotMap.PID GAINS = new RobotMap.PID(0, 0.01, 0, 0);
    public static final double PID_OUTPUT_THRESHOLD = 0.2;

    private Drivetrain drivetrain;

    private double distance;
    private double angle;

    private boolean finished = false;

    /**
     * Constructs a new distance-driving auton.
     * @param distance the distance to drive in inches.
     * @param angle the angle at which to drive.
     */
    public DriveDistanceAuton(double distance, double angle) {
        super(GAINS.kP, GAINS.kI, GAINS.kD);
        requires(this.drivetrain = Drivetrain.getInstance());

        this.distance = distance;
        this.angle = angle;
    }


    @Override
    protected void initialize() {
        double targetPosition = drivetrain.getAverageDriveMotorPosition()
                + this.distance / RobotMap.ENCODER_UNITS_TO_INCHES;
        this.finished = false;
        this.setSetpoint(targetPosition);
    }

    @Override
    protected double returnPIDInput() {
        return drivetrain.getAverageDriveMotorPosition();
    }

    @Override
    protected void usePIDOutput(double output) {
        SmartDashboard.putNumber("swerve/auton_output", output);
        if (Math.abs(output) <= PID_OUTPUT_THRESHOLD) {
            this.finished = true;
            return;
        }
        double x = Math.cos(Math.toRadians(this.angle)) * RobotMap.AUTON_DRIVE_SPEED;
        double y = Math.sin(Math.toRadians(this.angle)) * RobotMap.AUTON_DRIVE_SPEED;
        this.drivetrain.drive(0, x, y);
    }

    @Override
    protected boolean isFinished() {
        return this.finished;
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
