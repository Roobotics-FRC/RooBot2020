package frc.team4373.robot.commands;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.networktables.NetworkTableType;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.PIDCommand;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.team4373.robot.RobotMap;

import java.util.function.Function;

public class VisionQuerierCommand extends Command {
    private enum State {
        POLLING, SETTING, WAITING
    }

    private State state;

    private Function<Double, PIDCommand> constructor;
    private String visionField;
    private double tolerance;

    private PIDCommand command;
    private NetworkTable visionTable;

    private double accumulator = 0;
    private double iterationCount = 0;
    private boolean finished = false;

    /**
     * Constructs a command responsible for managing repeated querying of a vision
     * Network Table field and corresponding PID commands to attain a state
     * within a given tolerance.
     *
     * <p>Note: the vision field, tolerance, and constructor input must all be in the same
     * units.</p>
     *
     * @param visionField the field on the vision NetworkTable to query for values.
     * @param tolerance the absolute value of the input at which the setpoint will be considered
     *                  to be attained and the command will exit. This value <i>must</i> be
     *                  positive.
     * @param constructor the constructor of the PIDCommand that takes an input from the given
     *                    vision table field.
     */
    public VisionQuerierCommand(String visionField, double tolerance,
                                Function<Double, PIDCommand> constructor) {
        this.visionField = visionField;
        this.tolerance = tolerance;
        this.constructor = constructor;

        this.visionTable = NetworkTableInstance.getDefault().getTable(RobotMap.VISION_TABLE_NAME);
        this.state = State.POLLING;
    }

    @Override
    protected void initialize() {
        if (this.visionTable.getEntry(this.visionField).getType() != NetworkTableType.kDouble) {
            DriverStation.reportError(
                    "Non-double vision field (" + this.visionField
                            + ") passed to VisionQuerierCommand; exiting...", false);
            this.finished = true;
        } else {
            resetStateVars();
            this.finished = false;
        }
    }

    @Override
    protected void execute() {
        switch (state) {
            case POLLING:
                SmartDashboard.putString("v/state", "polling");
                if (this.iterationCount >= RobotMap.VISION_SAMPLE_COUNT) {
                    this.state = State.SETTING;
                    break;
                }
                double sample = visionTable.getEntry(visionField).getDouble(0);
                SmartDashboard.putNumber("v/sample", sample);
                this.accumulator += sample;
                ++this.iterationCount;
                break;
            case SETTING:
                SmartDashboard.putString("v/state", "setting");

                // Compute setpoint by taking polling average
                // The Network Tables field gives us the amount by which we're off,
                // so we want to move opposite that direction to reach 0
                double setpoint = -(this.accumulator / this.iterationCount);
                SmartDashboard.putNumber("v/setpt", setpoint);

                // Reset all variables for next iteration before we spin up the new command
                resetStateVars();

                // Check if offset setpoint would be less than acceptable tolerance
                // (i.e., we're close enough)
                if (Math.abs(setpoint) < this.tolerance) {
                    System.out.println("Within tolerance");
                    this.finished = true;
                    break;
                }

                // Start the command—will steal control from us b/c we require the drivetrain
                this.command = this.constructor.apply(setpoint);
                Scheduler.getInstance().add(this.command);
                this.state = State.WAITING;
                break;
            case WAITING:
                SmartDashboard.putString("v/state", "waiting");
                if (command.isRunning()) {
                    SmartDashboard.putString("v/auton_cmd_state", "running");
                } else {
                    SmartDashboard.putString("v/auton_cmd_state", "done");
                    this.state = State.POLLING;
                }
                break;
            default:
                // We're in an undefined state—get out!
                SmartDashboard.putString("v/state", "undefined");
                this.finished = true;
                break;
        }
    }

    @Override
    protected boolean isFinished() {
        return this.finished;
    }

    /**
     * Resets all state/polling variables to their initial, start-of-polling state.
     */
    private void resetStateVars() {
        this.state = State.POLLING;
        this.accumulator = 0;
        this.iterationCount = 0;
    }
}
