package frc.team4373.robot.commands.wheelspinner;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;
import frc.team4373.robot.subsystems.WheelSpinner;

/**
 * Spins the wheel spinner until the color specified by the game data is reached.
 */
public class WheelSpinnerColorCommand extends Command {
    private WheelSpinner spinner;

    private WheelSpinner.Color targetColor;
    private boolean finished;

    public WheelSpinnerColorCommand() {
        requires(this.spinner = WheelSpinner.getInstance());
    }

    @Override
    protected void initialize() {
        this.finished = false;

        String gameData = DriverStation.getInstance().getGameSpecificMessage();
        switch (gameData.charAt(0)) {
            case 'B':
                this.targetColor = WheelSpinner.Color.BLUE;
                break;
            case 'G':
                this.targetColor = WheelSpinner.Color.GREEN;
                break;
            case 'R':
                this.targetColor = WheelSpinner.Color.RED;
                break;
            case 'Y':
                this.targetColor = WheelSpinner.Color.YELLOW;
                break;
            default:
                this.finished = true;
        }
    }

    @Override
    protected void execute() {
        this.spinner.spin();
        if (this.spinner.getColor() == targetColor) {
            this.finished = true;
            this.spinner.stopSpinner();
        }
    }

    @Override
    protected boolean isFinished() {
        return this.finished;
    }

    @Override
    protected void end() {
        this.spinner.stopSpinner();
    }

    @Override
    protected void interrupted() {
        this.end();
    }
}
