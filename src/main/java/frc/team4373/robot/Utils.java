package frc.team4373.robot;

public class Utils {
    /**
     * Constrains a percent output to [-1, 1].
     * @param output the percent output value to constrain.
     * @return the input percent output constrained to the safe range.
     */
    public static double constrainPercentOutput(double output) {
        if (output > 1) {
            return 1;
        }
        if (output < -1) {
            return -1;
        }
        return output;
    }

    /**
     * Checks whether a given double is equal to zero within an equality threshold.
     * @param n the double to check.
     * @return true if it is zero, or false otherwise.
     */
    public static boolean isZero(double n) {
        return Math.abs(n) < RobotMap.FP_EQUALITY_THRESHOLD;
    }
}
