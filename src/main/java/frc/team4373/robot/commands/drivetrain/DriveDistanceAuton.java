package frc.team4373.robot.commands.drivetrain;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.command.PIDCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.team4373.robot.RobotMap;
import frc.team4373.robot.subsystems.Drivetrain;

/**
 * Drives a distance in the specified direction. Field/bot-oriented driving is determined by the
 * drivetrain's current drive mode (own-ship- or field-up).
 */
@SuppressWarnings("removal")
public class DriveDistanceAuton extends PIDCommand {
    private static final RobotMap.PID DRIVE_GAINS = new RobotMap.PID(0, 0.01, 0, 0);
    private static final double PID_OUTPUT_THRESHOLD = 0.2;

    private PIDController rotationController;
    private PIDSource rotationSource;
    private PIDOutput rotationOutputLambda;
    private double startAngle;
    private double rotationPIDOutput;

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
        super(DRIVE_GAINS.kP, DRIVE_GAINS.kI, DRIVE_GAINS.kD);
        requires(this.drivetrain = Drivetrain.getInstance());

        this.distance = distance;
        this.angle = angle;

        this.rotationSource = new PIDSource() {
            @Override
            public void setPIDSourceType(PIDSourceType pidSource) {
                return;
            }

            @Override
            public PIDSourceType getPIDSourceType() {
                return PIDSourceType.kDisplacement;
            }

            @Override
            public double pidGet() {
                return drivetrain.getAngle() - startAngle;
            }
        };

        this.rotationOutputLambda = output -> {
            this.rotationPIDOutput = output;
        };

        this.rotationController = new PIDController(RobotMap.DRIVE_STRAIGHT_ROTATE_GAINS.kP,
                RobotMap.DRIVE_STRAIGHT_ROTATE_GAINS.kI,
                RobotMap.DRIVE_STRAIGHT_ROTATE_GAINS.kD,
                this.rotationSource, this.rotationOutputLambda);
        this.rotationController.setSetpoint(0);
        this.rotationController.setOutputRange(-1, 1);
    }

    @Override
    protected void initialize() {
        double targetPosition = drivetrain.getAverageDriveMotorPosition()
                + this.distance / RobotMap.ENCODER_UNITS_TO_INCHES;
        this.finished = false;
        this.setSetpoint(targetPosition);
        this.startAngle = drivetrain.getAngle();
        this.rotationController.enable();
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
        this.drivetrain.drive(rotationPIDOutput, x, y);
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
