package frc.team4373.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.team4373.robot.RobotMap;
import frc.team4373.robot.Utils;
import frc.team4373.robot.commands.climber.ClimberCommand;

public class Climber extends Subsystem {
    private static volatile Climber instance;

    /**
     * The getter for the Climber class.
     * @return the singleton Climber object.
     */
    public static Climber getInstance() {
        if (instance == null) {
            synchronized (Climber.class) {
                if (instance == null) {
                    instance = new Climber();
                }
            }
        }
        return instance;
    }

    private WPI_TalonSRX lift;
    private WPI_TalonSRX leftWinch;
    private WPI_TalonSRX rightWinch;

    /**
     * Constructs a new Climber.
     */
    private Climber() {
        this.lift = new WPI_TalonSRX(RobotMap.CLIMB_LIFT_CONFIG.id);
        this.leftWinch = new WPI_TalonSRX(RobotMap.CLIMB_WINCH_1_CONFIG.id);
        this.rightWinch = new WPI_TalonSRX(RobotMap.CLIMB_WINCH_2_CONFIG.id);

        this.lift.setInverted(RobotMap.CLIMB_LIFT_CONFIG.inverted);
        this.leftWinch.setInverted(RobotMap.CLIMB_WINCH_1_CONFIG.inverted);
        this.rightWinch.setInverted(RobotMap.CLIMB_WINCH_2_CONFIG.inverted);

        this.lift.setNeutralMode(RobotMap.CLIMB_LIFT_CONFIG.neutralMode);
        this.leftWinch.setNeutralMode(RobotMap.CLIMB_WINCH_1_CONFIG.neutralMode);
        this.rightWinch.setNeutralMode(RobotMap.CLIMB_WINCH_2_CONFIG.neutralMode);
    }

    /**
     * Sets the lift to extend (i.e., full power upward).
     */
    public void extendLift() {
        this.lift.set(ControlMode.PercentOutput, RobotMap.CLIMB_ELEVATOR_MOVE_SPEED);
    }

    /**
     * Sets the lift to retract (i.e., full power downward).
     */
    public void retractLift() {
        this.lift.set(ControlMode.PercentOutput, -RobotMap.CLIMB_ELEVATOR_MOVE_SPEED);
    }

    /**
     * Stops the lift (i.e., zero output).
     */
    public void stopLift() {
        this.lift.stopMotor();
    }

    /**
     * Raises winch 1 at the specified percent output (or stops it, if power = 0).
     * @param power the percent of full output at which to raise, [0, 1].
     */
    public void raiseLeftWinch(double power) {
        power = constrainWinchOutput(power);
        this.leftWinch.set(ControlMode.PercentOutput, power);
    }

    /**
     * Raises winch 2 at the specified percent output (or stops it, if power = 0).
     * @param power the percent of full output at which to raise, [0, 1].
     */
    public void raiseRightWinch(double power) {
        power = constrainWinchOutput(power);
        this.rightWinch.set(ControlMode.PercentOutput, power);
    }

    /**
     * Safety-checks the power to be fed to a winch by clamping it to [-100%, 100%] of safe range
     * and then ensuring that it is positive—winches can only be raised, not lowered.
     * @param power the raw, unchecked percent-output value.
     * @return the power, constrained to safe bounds for the winch motors.
     */
    private double constrainWinchOutput(double power) {
        power = Utils.constrainPercentOutput(power);
        if (power < 0) power = 0;
        power *= RobotMap.CLIMB_WINCH_MAX_SPEED;
        return power;
    }

    /**
     * Gets the percent output of the lift.
     * @return the percent output of the lift.
     */
    public double getLiftPercentOutput() {
        return lift.getMotorOutputPercent();
    }

    /**
     * Gets the percent output of the right winch motor.
     * @return the percent output of the right winch motor.
     */
    public double getRightWinchPercentOutput() {
        return rightWinch.getMotorOutputPercent();
    }

    /**
     * Gets the percent output of the left winch motor.
     * @return the percent output of the left winch motor.
     */
    public double getLeftWinchPercentOutput() {
        return leftWinch.getMotorOutputPercent();
    }

    @Override
    protected void initDefaultCommand() {
        setDefaultCommand(new ClimberCommand());

    }
}
