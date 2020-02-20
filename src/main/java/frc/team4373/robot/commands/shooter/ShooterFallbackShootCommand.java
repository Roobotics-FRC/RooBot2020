package frc.team4373.robot.commands.shooter;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.team4373.robot.RobotMap;
import frc.team4373.robot.input.OI;
import frc.team4373.robot.subsystems.Shooter;

public class ShooterFallbackShootCommand extends Command {
    private Shooter shooter;

    public ShooterFallbackShootCommand() {
        requires(this.shooter = Shooter.getInstance());
    }

    @Override
    protected void execute() {
        // double sliderVal = OI.getInstance().getDriveJoystick().rooGetThrottle();
        double sliderVal = SmartDashboard.getNumber("shoot_speed", 0);
        shooter.setVelocity(sliderVal * RobotMap.SHOOTER_MAX_SPEED_NATIVE_UNITS);
    }

    @Override
    protected boolean isFinished() {
        return false;
    }
}
