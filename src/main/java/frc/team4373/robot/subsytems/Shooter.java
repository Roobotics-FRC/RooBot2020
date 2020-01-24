package frc.team4373.robot.subsytems;

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

    private WPI_TalonSRX shooterMotor;

    private Shooter() {
        RobotMap.MotorConfig shooterMotorConfig = RobotMap.SHOOTER_CONFIG;
        this.shooterMotor = new WPI_TalonSRX(shooterMotorConfig.id);
        this.shooterMotor.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder);

        this.shooterMotor.setInverted(shooterMotorConfig.inverted);

        this.shooterMotor.setNeutralMode(shooterMotorConfig.neutralMode);

        this.shooterMotor.setSensorPhase(shooterMotorConfig.encoderPhase);

        this.shooterMotor.config_kF(RobotMap.PID_IDX, shooterMotorConfig.gains.kF);
        this.shooterMotor.config_kP(RobotMap.PID_IDX, shooterMotorConfig.gains.kP);
        this.shooterMotor.config_kI(RobotMap.PID_IDX, shooterMotorConfig.gains.kI);
        this.shooterMotor.config_kD(RobotMap.PID_IDX, shooterMotorConfig.gains.kD);

    }


    public void setPercentOutput(double speed) {
        this.shooterMotor.set(ControlMode.PercentOutput, speed);

    }

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
    
    public double getVelocity() {
        return this.shooterMotor.getSelectedSensorVelocity();
    }

    public void stopShooter() {
        this.shooterMotor.stopMotor();
    }

    @Override
    protected void initDefaultCommand() {

    }

}
