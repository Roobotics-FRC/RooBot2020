package frc.team4373.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.team4373.robot.RobotMap;

public class Intake extends Subsystem {
    private static volatile Intake instance;


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
        this.groundIntake = new WPI_TalonSRX(RobotMap.GROUND_INTAKE_MOTOR_PORT);
        this.uptakeIntake = new WPI_TalonSRX(RobotMap.UPTAKE_INTAKE_MOTOR_PORT);
        this.uptakeIntake.setInverted(false);
        this.groundIntake.setInverted(false);
        this.groundIntake.setNeutralMode(NeutralMode.Brake);
        this.uptakeIntake.setNeutralMode(NeutralMode.Brake);
        this.servo = new Servo(RobotMap.RELEASE_BALL_SERVO_PORT);
    }

    public void setGroundIntakeMotor(double speed) {
        groundIntake.set(ControlMode.PercentOutput, speed);
    }

    public void setUptakeIntakeMotor(double speed) {
        uptakeIntake.set(ControlMode.PercentOutput, speed);
    }

    public void releaseBall() {
        servo.set(RobotMap.BALL_RELEASE_SERVO_ANGLE);
    }

    public void lockBall() {
        servo.set(RobotMap.BALL_LOCK_SERVO_ANGLE);
    }

    @Override
    protected void initDefaultCommand() {

    }
}
