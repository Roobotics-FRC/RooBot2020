package frc.team4373.robot.commands.shooter;

import edu.wpi.first.wpilibj.command.Command;
import frc.team4373.robot.RobotMap;
import frc.team4373.robot.subsystems.Intake;
import frc.team4373.robot.subsystems.Shooter;

public class ShooterReuptakeCommand extends Command {
    private Shooter shooter;
    private Intake intake;

    public ShooterReuptakeCommand() {
        requires(this.shooter = Shooter.getInstance());
        requires(this.intake = Intake.getInstance());
    }

    @Override
    protected void execute() {
        this.shooter.setVelocity(-RobotMap.SHOOTER_REUPTAKE_SPEED);
        this.intake.releaseBall();
    }

    @Override
    protected boolean isFinished() {
        return false;
    }

    @Override
    protected void end() {
        this.shooter.stopShooter();
        this.intake.retainBall();
    }
}
