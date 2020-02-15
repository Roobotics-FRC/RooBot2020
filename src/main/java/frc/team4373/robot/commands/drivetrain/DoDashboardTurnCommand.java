package frc.team4373.robot.commands.drivetrain;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DoDashboardTurnCommand extends Command {

    @Override
    protected void execute() {
        Scheduler.getInstance().add(new RotateAngleOffsetAuton(
                SmartDashboard.getNumber("manual_rot_angle", 0)
        ));
    }

    @Override
    protected boolean isFinished() {
        return true;
    }
}
