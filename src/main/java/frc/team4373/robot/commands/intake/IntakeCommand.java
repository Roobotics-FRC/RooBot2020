package frc.team4373.robot.commands.intake;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import frc.team4373.robot.RobotMap;
import frc.team4373.robot.input.OI;
import frc.team4373.robot.subsystems.Intake;

public class IntakeCommand extends Command {
    private static final double MIN_SERVO_RELEASE_TIME_SEC = 0.5;
    private Intake intake;
    private double depressTime;

    public IntakeCommand() {
        requires(this.intake = Intake.getInstance());
    }

    @Override
    protected void execute() {
        // Ball intake from ground
        if (OI.getInstance().getOperatorJoystick().getRawButton(RobotMap.OPER_INTAKE_BUTTON)) {
            this.intake.intake();
        } else {
            this.intake.stop();
        }

        // Ball release to shooter
        double now = Timer.getFPGATimestamp();
        if (OI.getInstance().getOperatorJoystick().getRawButtonPressed(
                RobotMap.OPER_BALL_RELEASE_BUTTON)) {
            this.depressTime = now;
        }

        if (now < depressTime + MIN_SERVO_RELEASE_TIME_SEC
                || OI.getInstance().getOperatorJoystick().getRawButton(
                        RobotMap.OPER_BALL_RELEASE_BUTTON)) {
            this.intake.releaseBall();
        } else {
            this.intake.retainBall();
        }
    }

    @Override
    protected boolean isFinished() {
        return false;
    }
}
