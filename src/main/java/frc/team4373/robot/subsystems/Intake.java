package frc.team4373.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.team4373.robot.RobotMap;
import frc.team4373.robot.commands.StopIntakeCommand;

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

    private Intake() {
        this.groundIntake = new WPI_TalonSRX(RobotMap.INTAKE_GROUND_MOTOR.id);
        this.uptakeIntake = new WPI_TalonSRX(RobotMap.INTAKE_UPTAKE_MOTOR.id);

        this.uptakeIntake.setInverted(RobotMap.INTAKE_GROUND_MOTOR.inverted);
        this.groundIntake.setInverted(RobotMap.INTAKE_UPTAKE_MOTOR.inverted);

        this.groundIntake.setNeutralMode(RobotMap.INTAKE_GROUND_MOTOR.neutralMode);
        this.uptakeIntake.setNeutralMode(RobotMap.INTAKE_UPTAKE_MOTOR.neutralMode);
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
        setDefaultCommand(new StopIntakeCommand());
    }
}
