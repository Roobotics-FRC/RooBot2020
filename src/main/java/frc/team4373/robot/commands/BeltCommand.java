package frc.team4373.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.team4373.robot.input.OI;
import frc.team4373.robot.subsystems.Belt;

public class BeltCommand extends Command {
    private Belt belt;

    public BeltCommand() {
        requires(this.belt = Belt.getInstance());
    }

    @Override
    protected void execute() {
        double speed = OI.getInstance().getOperatorJoystick().getAxis(5);
        //Xbox controller, right stick Y
        this.belt.setSpeed(speed);
    }

    @Override
    protected boolean isFinished() {
        return false;
    }

    @Override
    protected void end() {
        this.belt.stop();
    }

    @Override
    protected void interrupted() {
        this.end();
    }
}
