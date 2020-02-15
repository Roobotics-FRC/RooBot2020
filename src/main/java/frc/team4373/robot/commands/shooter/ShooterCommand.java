package frc.team4373.robot.commands.shooter;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.team4373.robot.RobotMap;
import frc.team4373.robot.input.OI;
import frc.team4373.robot.subsystems.Shooter;

public class ShooterCommand extends Command {
    private Shooter shooter;

    public ShooterCommand() {
        requires(this.shooter = Shooter.getInstance());
    }

    @Override
    protected void execute() {
        // Conversion from [1, -1] to [0, 1] is now in a filter and OI.
        // double slider = OI.getInstance().getDriveJoystick().rooGetThrottle();
        double speed = SmartDashboard.getNumber("shoot_speed", 0);
        shooter.setVelocity(speed * RobotMap.SHOOTER_MAX_SPEED_NATIVE_UNITS);
    }

    @Override
    protected boolean isFinished() {
        return false;
    }

    @Override
    protected void end() {
        shooter.stopShooter();
    }

    @Override
    protected void interrupted() {
        this.end();

    }
}
