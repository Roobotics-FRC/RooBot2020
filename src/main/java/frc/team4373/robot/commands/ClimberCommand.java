package frc.team4373.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.team4373.robot.RobotMap;
import frc.team4373.robot.input.OI;
import frc.team4373.robot.subsystems.Climber;

public class ClimberCommand extends Command {
    private Climber climber;

    public ClimberCommand() {
        requires(this.climber = Climber.getInstance());
    }

    @Override
    protected void execute() {
        switch (OI.getInstance().getOperatorJoystick().getPOV()) {
            case 0:
            case 45:
            case 315:
                climber.extendLift();
                break;
            case 135:
            case 180:
            case 225:
                climber.retractLift();
                break;
            default:
                climber.stopLift();
                break;
        }
    }

    @Override
    protected boolean isFinished() {
        return false;
    }

    @Override
    protected void end() {
        this.climber.stopLift();
    }

    @Override
    protected void interrupted() {
        this.end();
    }
}
