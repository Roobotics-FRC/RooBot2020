package frc.team4373.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.team4373.robot.RobotMap;

public class WheelSpinner extends Subsystem {
    private static volatile WheelSpinner instance;

    /**
     * The getter for the WheelSpinner class.
     * @return the singleton WheelSpinner object.
     */
    public static WheelSpinner getInstance() {
        if (instance == null) {
            synchronized (WheelSpinner.class) {
                if (instance == null) {
                    instance = new WheelSpinner();
                }
            }
        }
        return instance;
    }

    private WPI_TalonSRX wheelSpinnerMotor;
    private Servo servo;

    private WheelSpinner() {
        this.wheelSpinnerMotor = new WPI_TalonSRX(RobotMap.WHEEL_SPINNER_MOTOR_CONFIG.id);
        this.wheelSpinnerMotor.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder);
        this.wheelSpinnerMotor.setInverted(RobotMap.WHEEL_SPINNER_MOTOR_CONFIG.inverted);
        this.wheelSpinnerMotor.setNeutralMode(RobotMap.WHEEL_SPINNER_MOTOR_CONFIG.neutralMode);
        this.wheelSpinnerMotor.setSensorPhase(RobotMap.WHEEL_SPINNER_MOTOR_CONFIG.encoderPhase);

        this.wheelSpinnerMotor.config_kF(RobotMap.PID_IDX,
                RobotMap.WHEEL_SPINNER_MOTOR_CONFIG.gains.kF);
        this.wheelSpinnerMotor.config_kP(RobotMap.PID_IDX,
                RobotMap.WHEEL_SPINNER_MOTOR_CONFIG.gains.kP);
        this.wheelSpinnerMotor.config_kI(RobotMap.PID_IDX,
                RobotMap.WHEEL_SPINNER_MOTOR_CONFIG.gains.kI);
        this.wheelSpinnerMotor.config_kD(RobotMap.PID_IDX,
                RobotMap.WHEEL_SPINNER_MOTOR_CONFIG.gains.kD);
    }

    public double getWheelSpinnerPosition() {
        return this.wheelSpinnerMotor.getSelectedSensorPosition();
    }

    public void spinAtPower(double power) {
        wheelSpinnerMotor.set(ControlMode.PercentOutput, power);

    }

    public void stopSpinner() {
        wheelSpinnerMotor.set(ControlMode.PercentOutput, 0);
    }

    public double getWheelSpinnerPercentOutput() {
        return this.wheelSpinnerMotor.getMotorOutputPercent();
    }


    public void deploySpinnerServo(double deployAngle) { //FIXME: Add Actual Angle (in RobotMap)
        servo.set(deployAngle);

    }

    public void retractSpinnerServo(double retractAngle) { //FIXME: Add actual Angle (in RobotMap)
        servo.set(retractAngle);

    }

    @Override
    protected void initDefaultCommand() {

    }
}
