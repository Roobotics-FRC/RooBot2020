package frc.team4373.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.team4373.robot.RobotMap;
import frc.team4373.robot.Utils;

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

    private WPI_TalonSRX lift1;
    private WPI_TalonSRX lift2;
    private WPI_TalonSRX winch1;
    private WPI_TalonSRX winch2;
    private WPI_TalonSRX trolley;

    /**
     * Constructs a new Climber.
     */
    private Climber() {
        this.lift1 = new WPI_TalonSRX(RobotMap.CLIMB_LIFT_1_CONFIG.id);
        this.lift2 = new WPI_TalonSRX(RobotMap.CLIMB_LIFT_2_CONFIG.id);
        this.winch1 = new WPI_TalonSRX(RobotMap.CLIMB_WINCH_1_CONFIG.id);
        this.winch2 = new WPI_TalonSRX(RobotMap.CLIMB_WINCH_2_CONFIG.id);
        this.trolley = new WPI_TalonSRX(RobotMap.CLIMB_TROLLEY_CONFIG.id);

        this.lift1.setInverted(RobotMap.CLIMB_LIFT_1_CONFIG.inverted);
        this.lift2.setInverted(RobotMap.CLIMB_LIFT_2_CONFIG.inverted);
        this.winch1.setInverted(RobotMap.CLIMB_WINCH_1_CONFIG.inverted);
        this.winch2.setInverted(RobotMap.CLIMB_WINCH_2_CONFIG.inverted);
        this.trolley.setInverted(RobotMap.CLIMB_TROLLEY_CONFIG.inverted);

        this.lift1.setNeutralMode(RobotMap.CLIMB_LIFT_1_CONFIG.neutralMode);
        this.lift2.setNeutralMode(RobotMap.CLIMB_LIFT_2_CONFIG.neutralMode);
        this.winch1.setNeutralMode(RobotMap.CLIMB_WINCH_1_CONFIG.neutralMode);
        this.winch2.setNeutralMode(RobotMap.CLIMB_WINCH_2_CONFIG.neutralMode);
        this.trolley.setNeutralMode(RobotMap.CLIMB_TROLLEY_CONFIG.neutralMode);
    }

    /**
     * Sets both lifts to extend (i.e., full power upward).
     */
    public void extendLifts() {
        this.lift1.set(ControlMode.PercentOutput, 1);
        this.lift2.set(ControlMode.PercentOutput, 1);
    }

    /**
     * Sets both lifts to retract (i.e., full power downward).
     */
    public void retractLifts() {
        this.lift1.set(ControlMode.PercentOutput, -1);
        this.lift2.set(ControlMode.PercentOutput, -1);
    }

    /**
     * Stops both lifts (i.e., zero output).
     */
    public void stopLifts() {
        this.lift1.stopMotor();
        this.lift2.stopMotor();
    }

    /**
     * Raises winch 1 at the specified percent output (or stops it, if power = 0).
     * @param power the percent of full output at which to raise, [0, 1].
     */
    public void raiseWinch1(double power) {
        power = constrainWinchOutput(power);
        this.winch1.set(ControlMode.PercentOutput, power);
    }

    /**
     * Raises winch 2 at the specified percent output (or stops it, if power = 0).
     * @param power the percent of full output at which to raise, [0, 1].
     */
    public void raiseWinch2(double power) {
        power = constrainWinchOutput(power);
        this.winch2.set(ControlMode.PercentOutput, power);
    }

    /**
     * Sets the power to the trolley.
     * @param power the percent output at which to move the trolley, [-1, 1].
     */
    public void setTrolley(double power) {
        power = Utils.constrainPercentOutput(power);
        this.trolley.set(ControlMode.PercentOutput, power);
    }

    /**
     * Safety-checks the power to be fed to a winch by clamping it to [-1, 1] and then ensuring
     * that it is positive (i.e., [0, 1])—winches can only be raised, not lowered.
     * @param power the raw, unchecked percent-output value.
     * @return the power, constrained to safe bounds for the winch motors.
     */
    private double constrainWinchOutput(double power) {
        power = Utils.constrainPercentOutput(power);
        if (power < 0) power = 0;
        return power;
    }

    @Override
    protected void initDefaultCommand() {

    }
}
