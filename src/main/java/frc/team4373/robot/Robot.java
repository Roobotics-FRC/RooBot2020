package frc.team4373.robot;

import com.revrobotics.*;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {

    ColorSensorV3 sensor = new ColorSensorV3(RobotMap.COLOR_SENSOR_PORT);
    //ColorMatch matcher = new ColorMatch();

    /**
     * Constructor for the Robot class. Variable initialization occurs here;
     * WPILib-related setup should occur in {@link #robotInit}.
     */
    public Robot() {
    }

    Color myColor = ColorMatch.makeColor(0.1, 0.5, 0.4);

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     *
     * <p>All SmartDashboard fields should be initially added here.</p>
     */
    @Override
    public void robotInit() {
       /* matcher.addColorMatch(Color.kBlue);
        // matcher.addColorMatch(Color.kRed);
        // matcher.addColorMatch(Color.kGreen);
        matcher.addColorMatch(myColor);
        */
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
    }
    /**
     * This function is called once when Sandstorm mode starts.
     */
    @Override
    public void autonomousInit() {
    }

    /**
     * This function is called once when teleoperated mode starts.
     */
    @Override
    public void teleopInit() {
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

        double red = sensor.getRed();
        double blue = sensor.getBlue();
        double green = sensor.getGreen();
        double maxVal = (red + blue + green);

        SmartDashboard.putNumber("Red", red);
        SmartDashboard.putNumber("Blue", blue);
        SmartDashboard.putNumber("Green", green);
        SmartDashboard.putNumber("Green Norm", green / maxVal);
        SmartDashboard.putNumber("Red Norm", red / maxVal);
        SmartDashboard.putNumber("Blue Norm", blue / maxVal);

        if (red / maxVal > 0.4) {
            SmartDashboard.putString("Color", "red");
        } else if (blue / maxVal > 0.2) {
            if (green / maxVal > 0.5) {
                SmartDashboard.putString("Color", "green");
            } else {
                SmartDashboard.putString("Color", "blue");
            }
        } else {
            SmartDashboard.putString("Color", "yellow");
        }


        /*Color c = sensor.getColor();
        SmartDashboard.putString("color", ctos(c));
        ColorMatchResult match = matcher.matchClosestColor(c);
        SmartDashboard.putString("closest color match", ctos(match.color));
        SmartDashboard.putNumber("match confidence", match.confidence);

         */
    }

    /*private String ctos(Color c) {
        return c == Color.kBlue ? "BLUE" : c == myColor ? "CUSTOM" : "AAAAACK";
        // return Arrays.toString(new double[] {c.red, c.green, c.blue});

     */

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
