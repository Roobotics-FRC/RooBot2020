package frc.team4373.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.team4373.robot.subsystems.Climber;
import frc.team4373.robot.subsystems.Drivetrain;
import frc.team4373.robot.subsystems.Intake;
import frc.team4373.robot.subsystems.Shooter;
import frc.team4373.swerve.SwerveDrivetrain;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
    /**
     * Constructor for the Robot class. Variable initialization occurs here;
     * WPILib-related setup should occur in {@link #robotInit}.
     */
    public Robot() {
    }

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     *
     * <p>All SmartDashboard fields should be initially added here.</p>
     */
    @Override
    public void robotInit() {
        // Climber.getInstance();
        // Intake.getInstance();
        // Shooter.getInstance();
        Drivetrain.getInstance();

        // TODO: For debug purposes only
        SmartDashboard.putNumber("shoot_speed", 0);
        SmartDashboard.putNumber("manual_rot_angle", 0);
        SmartDashboard.putNumber("kP", 0);
        SmartDashboard.putNumber("kI", 0);
        SmartDashboard.putNumber("kD", 0);
    }

    /**
     * This function is called every robot packet, no matter the mode. Use
     * this for items like diagnostics that you want run during disabled,
     * autonomous, teleoperated, and test.
     *
     * <p>This runs after the mode-specific periodic functions but before
     * LiveWindow and SmartDashboard integrated updating.
     */
    @Override
    public void robotPeriodic() {
        SmartDashboard.putNumber("shooter/encoder_vel", Shooter.getInstance().getVelocity());
        SmartDashboard.putNumber("shooter/motor1_output",
                Shooter.getInstance().getMotor1PercentOutput());
        SmartDashboard.putNumber("shooter/motor2_output",
                Shooter.getInstance().getMotor2PercentOutput());

        SmartDashboard.putNumber("climber/lift_output",
                Climber.getInstance().getLiftPercentOutput());
        SmartDashboard.putNumber("climber/right_winch_output",
                Climber.getInstance().getRightWinchPercentOutput());
        SmartDashboard.putNumber("climber/left_winch_output",
                Climber.getInstance().getLeftWinchPercentOutput());

        SmartDashboard.putNumber("intake/ground_intake_output",
                Intake.getInstance().getGroundIntakePercentOutput());
        SmartDashboard.putNumber("intake/uptake_intake_output",
                Intake.getInstance().getUptakeIntakePercentOutput());
    }

    /**
     * This function is called once when Sandstorm mode starts.
     */
    @Override
    public void autonomousInit() {
        Drivetrain.getInstance().setBrakeMode(SwerveDrivetrain.BrakeMode.NONE);
    }

    /**
     * This function is called once when teleoperated mode starts.
     */
    @Override
    public void teleopInit() {
        Drivetrain.getInstance().setBrakeMode(SwerveDrivetrain.BrakeMode.IMPLODE);
    }

    /**
     * This function is called periodically during Sandstorm mode.
     */
    @Override
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    /**
     * This function is called periodically during operator control.
     */
    @Override
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
    }

    /**
     * This function is called periodically during test mode.
     */
    @Override
    public void testPeriodic() {
    }

    /**
     * This function runs periodically in disabled mode.
     * It is used to verify that the selected auton configuration is legal.
     * <br>
     * <b>UNDER NO CIRCUMSTANCES SHOULD SUBSYSTEMS BE ACCESSED OR ENGAGED IN THIS METHOD.</b>
     */
    @Override
    public void disabledPeriodic() {
    }
}
