package frc.team4373.robot;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import edu.wpi.first.wpilibj.I2C;
import frc.team4373.swerve.SwerveConfig;

/**
 * Holds various mappings and constants.
 */
public class RobotMap {
    /**
     * Gets the config for the swerve drivetrain.
     * @return the config for the drivetrain.
     */
    public static SwerveConfig getSwerveConfig() {
        SwerveConfig.RobotDimensions dimensions = new SwerveConfig.RobotDimensions(30, 30);
        SwerveConfig.MotorConfig right1Drive = new SwerveConfig.MotorConfig(17,
                true,
                NeutralMode.Brake,
                true,
                new SwerveConfig.PID(0, 0.2, 0, 0.05));
        SwerveConfig.MotorConfig right1Rotate = new SwerveConfig.MotorConfig(18,
                true,
                NeutralMode.Brake,
                false,
                new SwerveConfig.PID(0, 1, 0, 0.05));
        SwerveConfig.MotorConfig right2Drive = new SwerveConfig.MotorConfig(13,
                false,
                NeutralMode.Brake,
                true,
                new SwerveConfig.PID(0, 0.2, 0, 0.05));
        SwerveConfig.MotorConfig right2Rotate = new SwerveConfig.MotorConfig(14,
                true,
                NeutralMode.Brake,
                false, // FIXME: maybe false, was oscillating
                new SwerveConfig.PID(0, 1, 0, 0.05));
        SwerveConfig.MotorConfig left1Drive = new SwerveConfig.MotorConfig(15,
                true,
                NeutralMode.Brake,
                true,
                new SwerveConfig.PID(0, 0.2, 0, 0.05));
        SwerveConfig.MotorConfig left1Rotate = new SwerveConfig.MotorConfig(16,
                true,
                NeutralMode.Brake,
                false, // FIXME: maybe false, was oscillating
                new SwerveConfig.PID(0, 1, 0, 0.05));
        SwerveConfig.MotorConfig left2Drive = new SwerveConfig.MotorConfig(11,
                false,
                NeutralMode.Brake,
                true,
                new SwerveConfig.PID(0, 0.2, 0, 0.05));
        SwerveConfig.MotorConfig left2Rotate = new SwerveConfig.MotorConfig(12,
                true,
                NeutralMode.Brake,
                false,
                new SwerveConfig.PID(0, 1, 0, 0.05));
        double maxWheelSpeed = 8400;
        int ampLimit = 35;
        SwerveConfig.WheelsConfig wheelsConfig = new SwerveConfig.WheelsConfig(
                right1Drive, right1Rotate, right2Drive, right2Rotate,
                left1Drive, left1Rotate, left2Drive, left2Rotate,
                maxWheelSpeed, ampLimit
        );
        int pigeonID = 19;

        return new SwerveConfig(dimensions, wheelsConfig, pigeonID);
    }

    // OI devices
    public static final int DRIVE_JOYSTICK_PORT = 0;
    public static final int OPERATOR_JOYSTICK_PORT = 1;
    public static final double JOYSTICK_DEFAULT_DEADZONE = 0.09;

    // Buttons and axes
    public static final int DRIVE_RESET_NORTH_BUTTON = 7;
    public static final int DRIVE_NORTH_UP_BUTTON = 10;
    public static final int DRIVE_OWN_SHIP_UP_BUTTON = 12;
    public static final int DRIVE_SLOWER_SPEED_BUTTON = 2;
    public static final int DRIVE_CLEAR_COMMANDS_BUTTON = 11;
    public static final int DRIVE_VISION_ALIGN_BUTTON = 4;
    public static final int OPER_BALL_RELEASE_BUTTON = 5; // left bumper
    public static final int OPER_INTAKE_BUTTON = 6; // right bumper
    public static final int OPER_RAISE_L_WINCH_AXIS = 2; // L trigger
    public static final int OPER_RAISE_R_WINCH_AXIS = 3; // R trigger
    public static final int OPER_SHOOT_BUTTON = 4; // Y button
    public static final int OPER_FALLBACK_SHOOT_BUTTON = 2; // B button

    public static final double OPER_ROTATE_VIB_INTENSITY = 0.5;

    // Speed constants
    public static final double CLIMB_ELEVATOR_MOVE_SPEED = 1;
    public static final double CLIMB_WINCH_MAX_SPEED = 0.8;
    public static final double SHOOTER_MAX_SPEED_NATIVE_UNITS = 100000;
    public static final double DRIVE_SLOWER_SPEED_FACTOR = 4;
    public static final double AUTON_TURN_SPEED = 0.25;

    // Non-motor devices
    public static final int INTAKE_RELEASE_SERVO_PORT = 9;
    public static final int BOTTOM_LIMIT_SWITCH_DIO_PORT = 0;
    public static final int TOP_LIMIT_SWITCH_DIO_PORT = 1;
    public static final int WHEEL_SPINNER_DEPLOY_SERVO_PORT = 8;
    public static final I2C.Port COLOR_SENSOR_PORT = I2C.Port.kOnboard;

    // Physical state constants
    public static final double INTAKE_SERVO_RELEASE_ANGLE = 0.5;
    public static final double INTAKE_SERVO_RETAIN_ANGLE = 0;
    public static final double SPINNER_SERVO_DEPLOY_ANGLE = 0.5;
    public static final double SPINNER_SERVO_RETRACT_ANGLE = 0;
    public static final double INTAKE_SPEED = 1;
    public static final double SPINNER_SPEED = 0.2;

    // Colors
    public static final double RED_THRESHOLD = 0.4;
    public static final double BLUE_THRESHOLD = 0.2;
    public static final double GREEN_THRESHOLD = 0.5;

    // Vision
    public static final double VISION_SAMPLE_COUNT = 10;
    public static final String VISION_TABLE_NAME = "Vision";
    public static final String VISION_ANG_OFFSET_FIELD = "degree_offset";
    public static final String VISION_DIST_FIELD = "current_distance";
    public static final double VISION_ALIGN_ALLOWABLE_OFFSET_DEG = 1;
    public static final double MAX_TURN_AUTON_TIME_SEC = 3;
    public static final double INTER_QUERY_DELAY_SEC = 0.45;

    // Motor configurations
    public static final MotorConfig SHOOTER_MOTOR_1_CONFIG =
            new MotorConfig(21, true, NeutralMode.Brake, false,
                    new PID(0, 0.05, 0,0.05));
    public static final MotorConfig SHOOTER_MOTOR_2_CONFIG =
            new MotorConfig(22, true, NeutralMode.Brake);
    public static final MotorConfig CLIMB_LIFT_CONFIG =
            new MotorConfig(41, true, NeutralMode.Brake);
    public static final MotorConfig CLIMB_LEFT_WINCH_CONFIG =
            new MotorConfig(42, false, NeutralMode.Brake);
    public static final MotorConfig CLIMB_RIGHT_WINCH_CONFIG =
            new MotorConfig(43, false, NeutralMode.Brake);
    public static final MotorConfig INTAKE_MOTOR_CONFIG =
            new MotorConfig(31, true, NeutralMode.Coast);
    public static final MotorConfig WHEEL_SPINNER_MOTOR_CONFIG =
            new MotorConfig(51, false, NeutralMode.Brake);

    // Talon constants
    public static final int PID_IDX = 0;

    // Timeouts
    public static final double SPINNER_DEPLOY_TIME_SEC = 1.5;

    // Programmatic resources
    public static final double FP_EQUALITY_THRESHOLD = 1e-5;

    // Utility classes
    public static final class MotorConfig {
        public final int id;
        public final boolean inverted;
        public final NeutralMode neutralMode;
        public final boolean encoderPhase;
        public final PID gains;

        /**
         * Constructs a new MotorConfig for a motor using closed-loop control.
         * @param id the CAN ID of the motor.
         * @param inverted whether to invert motor output values.
         * @param neutralMode the motor's neutral mode.
         * @param encoderPhase whether the motor is out of phase with its sensor.
         * @param gains the PID gains for this motor's closed-loop control.
         */
        public MotorConfig(int id, boolean inverted,
                           NeutralMode neutralMode, boolean encoderPhase, PID gains) {
            this.id = id;
            this.inverted = inverted;
            this.neutralMode = neutralMode;
            this.encoderPhase = encoderPhase;
            this.gains = gains;
        }

        /**
         * Constructs a new MotorConfig for a motor not under closed-loop control.
         * @param id the CAN ID of the motor.
         * @param inverted whether to invert motor output values.
         * @param neutralMode the motor's neutral mode.
         */
        public MotorConfig(int id, boolean inverted, NeutralMode neutralMode) {
            this.id = id;
            this.inverted = inverted;
            this.neutralMode = neutralMode;

            this.encoderPhase = false;
            this.gains = new PID(0, 0, 0,0);
        }

    }

    // PID- and sensor-related constants
    public static final class PID {
        public final double kF;
        public final double kP;
        public final double kI;
        public final double kD;

        /**
         * Constructs a new PID parameters object.
         * @param kF feed-forward gain.
         * @param kP proportional gain.
         * @param kI integral gain.
         * @param kD derivative gain.
         */
        public PID(double kF, double kP, double kI, double kD) {
            this.kF = kF;
            this.kP = kP;
            this.kI = kI;
            this.kD = kD;
        }
    }
}
