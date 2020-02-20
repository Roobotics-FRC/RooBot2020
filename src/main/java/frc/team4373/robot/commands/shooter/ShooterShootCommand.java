package frc.team4373.robot.commands.shooter;

import edu.wpi.first.wpilibj.command.Command;
import frc.team4373.robot.RobotMap;
import frc.team4373.robot.input.OI;
import frc.team4373.robot.subsystems.Shooter;

/**
 * Shoots the balls from the shooter.
 */
public class ShooterShootCommand extends Command {
    private Shooter shooter;

    public ShooterShootCommand() {
        requires(this.shooter = Shooter.getInstance());
    }

    @Override
    protected void initialize() {

    }

    @Override
    protected void execute() {
        shooter.setPercentOutput(1);
        shooter.setVelocity(0.715 * RobotMap.SHOOTER_MAX_SPEED_NATIVE_UNITS);
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
