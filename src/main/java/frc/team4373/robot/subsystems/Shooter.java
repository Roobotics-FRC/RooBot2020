package frc.team4373.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.team4373.robot.RobotMap;
import frc.team4373.robot.Utils;

public class Shooter extends Subsystem {
    private static volatile Shooter instance;

    /**
     * The getter for the Shooter class.
     * @return the singleton Shooter object.
     */

    public static Shooter getInstance() {
        if (instance == null) {
            synchronized (Shooter.class) {
                if (instance == null) {
                    instance = new Shooter();
                }
            }
        }
        return instance;
    }

    private WPI_TalonSRX shooterMotor;
    private Servo servo;

    private Shooter() {
        RobotMap.MotorConfig shooterMotorConfig = RobotMap.SHOOTER_CONFIG;
        this.shooterMotor = new WPI_TalonSRX(shooterMotorConfig.ID);
        this.shooterMotor.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder);

        this.shooterMotor.setInverted(shooterMotorConfig.inverted);

        this.shooterMotor.setNeutralMode(shooterMotorConfig.neutralMode);

        this.shooterMotor.setSensorPhase(shooterMotorConfig.encoderPhase);

        this.shooterMotor.config_kF(RobotMap.PID_IDX, shooterMotorConfig.gains.kF);
        this.shooterMotor.config_kP(RobotMap.PID_IDX, shooterMotorConfig.gains.kP);
        this.shooterMotor.config_kI(RobotMap.PID_IDX, shooterMotorConfig.gains.kI);
        this.shooterMotor.config_kD(RobotMap.PID_IDX, shooterMotorConfig.gains.kD);

        this.servo = new Servo(RobotMap.SHOOTER_RELEASE_SERVO_PORT);
    }

    /**
     * Sets the percent output of the shooter.
     * @param speed the percent output, [-1, 1].
     */
    public void setPercentOutput(double speed) {
        speed = Utils.constrainPercentOutput(speed);
        this.shooterMotor.set(ControlMode.PercentOutput, speed);

    }

    /**
     * Gets the percent output of the shooter.
     * @return the percent output, [-1, 1].
     */
    public double getPercentOutput() {
        return this.shooterMotor.getMotorOutputPercent();
    }

    /**
     * Sets the setpoint for velocity closed-loop.
     * @param speed the setpoint (IN ENCODER UNITS).
     */
    public void setVelocity(double speed) {
        this.shooterMotor.set(ControlMode.Velocity, speed);

    }

    /**
     * Gets the velocity of the shooter motor.
     * @return the velocity in encoder units.
     */
    public double getVelocity() {
        return this.shooterMotor.getSelectedSensorVelocity();
    }

    /**
     * Stops the shooter motor.
     */
    public void stopShooter() {
        this.shooterMotor.stopMotor();
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

    @Override
    protected void initDefaultCommand() {

    }

}
