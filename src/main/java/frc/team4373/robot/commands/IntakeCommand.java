package frc.team4373.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.team4373.robot.RobotMap;
import frc.team4373.robot.input.OI;
import frc.team4373.robot.subsystems.Intake;

public class IntakeCommand extends Command {
    private Intake intake;

    public IntakeCommand() {
        requires(this.intake = Intake.getInstance());
    }

    @Override
    protected void execute() {
        if (OI.getInstance().getOperatorJoystick().getRawButton(RobotMap.OPER_INTAKE_BUTTON)) {
            this.intake.intake();
        } else {
            this.intake.stop();
        }

        if (OI.getInstance().getOperatorJoystick()
                .getRawButton(RobotMap.OPER_BALL_RELEASE_BUTTON)) {
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
