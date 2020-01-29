package frc.team4373.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.team4373.robot.RobotMap;

public class Intake extends Subsystem {
    private static volatile Intake instance;

    /**
     * The getter for the Intake class.
     * @return the singleton Intake object.
     */
    public static Intake getInstance() {
        if (instance == null) {
            synchronized (Intake.class) {
                if (instance == null) {
                    instance = new Intake();
                }
            }
        }
        return instance;
    }

    private WPI_TalonSRX groundIntake;
    private WPI_TalonSRX uptakeIntake;
    private Servo servo;

    private Intake() {
        this.groundIntake = new WPI_TalonSRX(RobotMap.INTAKE_GROUND_MOTOR.id);
        this.uptakeIntake = new WPI_TalonSRX(RobotMap.INTAKE_UPTAKE_MOTOR.id);

        this.uptakeIntake.setInverted(RobotMap.INTAKE_GROUND_MOTOR.inverted);
        this.groundIntake.setInverted(RobotMap.INTAKE_UPTAKE_MOTOR.inverted);

        this.groundIntake.setNeutralMode(RobotMap.INTAKE_GROUND_MOTOR.neutralMode);
        this.uptakeIntake.setNeutralMode(RobotMap.INTAKE_UPTAKE_MOTOR.neutralMode);

        this.servo = new Servo(RobotMap.INTAKE_RELEASE_SERVO_PORT);
    }

    /**
     * Intakes a ball from the ground by running the ground and uptake motors.
     */
    public void intake() {
        this.setGroundIntakeMotor(RobotMap.INTAKE_GROUND_SPEED);
        this.setUptakeIntakeMotor(RobotMap.INTAKE_UPTAKE_SPEED);
    }

    /**
     * Stops all (ground and uptake) intake motors.
     */
    public void stop() {
        this.groundIntake.stopMotor();
        this.uptakeIntake.stopMotor();
    }

    /**
     * Sets the servo to the ball-release angle.
     */
    public void releaseBall() {
        servo.set(RobotMap.INTAKE_SERVO_RELEASE_ANGLE);
    }

    /**
     * Sets the servo to the ball-retention angle.
     */
    public void retainBall() {
        servo.set(RobotMap.INTAKE_SERVO_RETAIN_ANGLE);
    }

    /**
     * Sets the percent output of the ground intake motor.
     * @param speed the percent output of the motor.
     */
    private void setGroundIntakeMotor(double speed) {
        groundIntake.set(ControlMode.PercentOutput, speed);
    }

    /**
     * Sets the percent output of the ground intake motor.
     * @param speed the percent output of the motor.
     */
    private void setUptakeIntakeMotor(double speed) {
        uptakeIntake.set(ControlMode.PercentOutput, speed);
    }

    @Override
    protected void initDefaultCommand() {

    }
}
