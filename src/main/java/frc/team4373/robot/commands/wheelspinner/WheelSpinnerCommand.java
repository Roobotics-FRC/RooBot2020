package frc.team4373.robot.commands.wheelspinner;

import edu.wpi.first.wpilibj.command.PIDCommand;
import frc.team4373.robot.RobotMap;
import frc.team4373.robot.subsystems.WheelSpinner;

public class WheelSpinnerCommand extends PIDCommand {
    private static final double MOTOR_OUTPUT_THRESHOLD = 0.1;
    public static final RobotMap.PID pid = new RobotMap.PID(0,0,0,0);

    private WheelSpinner wheelSpinner;
    private boolean finished;


    public WheelSpinnerCommand() {
        super("WheelSpinnerCommand", pid.kP, pid.kI, pid.kD);
        requires(this.wheelSpinner = WheelSpinner.getInstance());
    }

    @Override
    protected void initialize() {
        this.setSetpoint(wheelSpinner.getWheelSpinnerPosition() + RobotMap.WHEEL_SPINNER_OFFSET);
    }

    @Override
    protected double returnPIDInput() {
        return this.wheelSpinner.getWheelSpinnerPosition();
    }

    @Override
    protected void usePIDOutput(double output) {
        if (Math.abs(output) <= MOTOR_OUTPUT_THRESHOLD) {
            this.finished = true;
            return;
        }
        wheelSpinner.spinAtPower(output);
    }

    @Override
    protected boolean isFinished() {
        return this.finished;
    }

    @Override
    protected void end() {
        this.wheelSpinner.stopSpinner();
    }

    @Override
    protected void interrupted() {
        this.end();
    }
}
