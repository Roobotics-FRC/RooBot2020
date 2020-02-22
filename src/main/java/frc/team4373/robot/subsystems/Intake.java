package frc.team4373.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.team4373.robot.RobotMap;
import frc.team4373.robot.commands.intake.IntakeCommand;

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

    private WPI_TalonSRX intakeMotor;
    private Servo servo;

    private Intake() {
        this.intakeMotor = new WPI_TalonSRX(RobotMap.INTAKE_MOTOR_CONFIG.id);
        this.intakeMotor.setInverted(RobotMap.INTAKE_MOTOR_CONFIG.inverted);
        this.intakeMotor.setNeutralMode(RobotMap.INTAKE_MOTOR_CONFIG.neutralMode);

        this.servo = new Servo(RobotMap.INTAKE_RELEASE_SERVO_PORT);
    }

    /**
     * Intakes a ball from the ground by running the ground and uptake motors.
     */
    public void intake() {
        this.setIntakeMotor(RobotMap.INTAKE_SPEED);
    }

    /**
     * Stops all (ground and uptake) intake motors.
     */
    public void stop() {
        this.intakeMotor.stopMotor();
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
     * Sets the percent output of the intake motor.
     * @param speed the percent output of the motor.
     */
    private void setIntakeMotor(double speed) {
        intakeMotor.set(ControlMode.PercentOutput, speed);
    }

    /**
     * Gets the percent output of the intake motor motor.
     * @return the percent output of the intake motor.
     */
    public double getIntakePercentOutput() {
        return intakeMotor.getMotorOutputPercent();
    }

    /**
     * Returns whether the balls are currently being retained.
     * @return whether the servo is in the retain state.
     */
    public boolean getBallsAreRetained() {
        return servo.get() == RobotMap.INTAKE_SERVO_RETAIN_ANGLE;
    }

    @Override
    protected void initDefaultCommand() {
        setDefaultCommand(new IntakeCommand());
    }
}
