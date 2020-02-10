package frc.team4373.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.team4373.robot.RobotMap;

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

    private WPI_TalonSRX shooterMotor1;
    private WPI_TalonSRX shooterMotor2;

    private Shooter() {
        RobotMap.MotorConfig shooterMotor1Config = RobotMap.SHOOTER_MOTOR_1_CONFIG;
        RobotMap.MotorConfig shooterMotor2Config = RobotMap.SHOOTER_MOTOR_2_CONFIG;
        this.shooterMotor1 = new WPI_TalonSRX(shooterMotor1Config.id);
        this.shooterMotor2 = new WPI_TalonSRX(shooterMotor2Config.id);
        this.shooterMotor1.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder);

        this.shooterMotor1.setInverted(shooterMotor1Config.inverted);
        this.shooterMotor2.setInverted(shooterMotor2Config.inverted);

        this.shooterMotor1.setNeutralMode(shooterMotor1Config.neutralMode);
        this.shooterMotor2.setNeutralMode(shooterMotor2Config.neutralMode);

        this.shooterMotor1.setSensorPhase(shooterMotor1Config.encoderPhase);

        this.shooterMotor1.config_kF(RobotMap.PID_IDX, shooterMotor1Config.gains.kF);
        this.shooterMotor1.config_kP(RobotMap.PID_IDX, shooterMotor1Config.gains.kP);
        this.shooterMotor1.config_kI(RobotMap.PID_IDX, shooterMotor1Config.gains.kI);
        this.shooterMotor1.config_kD(RobotMap.PID_IDX, shooterMotor1Config.gains.kD);

        this.shooterMotor2.follow(this.shooterMotor1);
    }


    public void setPercentOutput(double speed) {
        this.shooterMotor1.set(ControlMode.PercentOutput, speed);

    }

    public double getMotor1PercentOutput() {
        return this.shooterMotor1.getMotorOutputPercent();
    }

    public double getMotor2PercentOutput() {
        return this.shooterMotor2.getMotorOutputPercent();
    }
    /**
     * Sets the setpoint for velocity closed-loop.
     * @param speed the setpoint (IN ENCODER UNITS).
     */

    public void setVelocity(double speed) {
        this.shooterMotor1.set(ControlMode.Velocity, speed);

    }
    
    public double getVelocity() {
        return this.shooterMotor1.getSelectedSensorVelocity();
    }

    public void stopShooter() {
        this.shooterMotor1.stopMotor();
    }

    @Override
    protected void initDefaultCommand() {

    }

}
