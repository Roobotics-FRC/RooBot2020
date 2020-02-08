package frc.team4373.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
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
        double slider = OI.getInstance().getDriveJoystick().rooGetThrottle();
        shooter.setVelocity(slider * RobotMap.SHOOTER_MAX_SPEED_NATIVE_UNITS);

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
