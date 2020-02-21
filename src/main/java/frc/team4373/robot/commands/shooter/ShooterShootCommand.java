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

    private double velocity;

    /**
     * Shoots the balls from the shooter at the specified velocity.
     * @param velocity the velocity to shoot the balls at.
     */
    public ShooterShootCommand(double velocity) {
        requires(this.shooter = Shooter.getInstance());
        this.velocity = velocity;
    }

    /**
     * Shoots the ball from the shooter at max speed.
     */
    public ShooterShootCommand() {
        this(1);
    }

    @Override
    protected void initialize() {

    }

    @Override
    protected void execute() {
        shooter.setVelocity(velocity * RobotMap.SHOOTER_MAX_SPEED_NATIVE_UNITS);
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
