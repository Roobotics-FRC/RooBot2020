package frc.team4373.robot;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import frc.team4373.swerve.SwerveConfig;

/**
 * Holds various mappings and constants.
 */
public class RobotMap {
    public static SwerveConfig getSwerveConfig() {
        return new SwerveConfig(
                new SwerveConfig.RobotDimensions(30, 30),
                new SwerveConfig.WheelsConfig(
                        new SwerveConfig.MotorConfig(17,
                                true,
                                NeutralMode.Brake,
                                true,
                                new SwerveConfig.PID(0, 0.4, 0, 0)),
                        new SwerveConfig.MotorConfig(18,
                                false,
                                NeutralMode.Brake,
                                true,
                                new SwerveConfig.PID(0, 3.5, 0, 3)),
                        new SwerveConfig.MotorConfig(13,
                                false,
                                NeutralMode.Brake,
                                true,
                                new SwerveConfig.PID(0, 0.4, 0, 0)),
                        new SwerveConfig.MotorConfig(14,
                                false,
                                NeutralMode.Brake,
                                true,//FIXME: maybe false, was oscillating
                                new SwerveConfig.PID(0, 3.5, 0, 3)),
                        new SwerveConfig.MotorConfig(15,
                                true,
                                NeutralMode.Brake,
                                true,
                                new SwerveConfig.PID(0, 0.4, 0, 0)),
                        new SwerveConfig.MotorConfig(16,
                                false,
                                NeutralMode.Brake,
                                true,//FIXME: maybe false, was oscillating
                                new SwerveConfig.PID(0, 3.5, 0, 3)),
                        new SwerveConfig.MotorConfig(11,
                                false,
                                NeutralMode.Brake,
                                true,
                                new SwerveConfig.PID(0, 0.4, 0, 0)),
                        new SwerveConfig.MotorConfig(12,
                                false,
                                NeutralMode.Brake,
                                true,
                                new SwerveConfig.PID(0, 3.5, 0, 3)),
                        8400,
                        40),
                19);
    }

    // OI devices
    public static final int DRIVE_JOYSTICK_PORT = 0;
    public static final int OPERATOR_JOYSTICK_PORT = 1;
    public static final double JOYSTICK_DEFAULT_DEADZONE = 0.09;

    // Buttons and axes
    public static final int OPER_INTAKE_BUTTON = 6;

    // Non-motor devices
    public static final int SHOOTER_RELEASE_SERVO_PORT = 1;

    // Physical state constants
    public static final double INTAKE_SERVO_RELEASE_ANGLE = 1;
    public static final double INTAKE_SERVO_RETAIN_ANGLE = 0;
    public static final double INTAKE_GROUND_SPEED = 1;
    public static final double INTAKE_UPTAKE_SPEED = 1;

    // Motor configurations
    public static final MotorConfig SHOOTER_CONFIG =
            new MotorConfig(21, false, NeutralMode.Brake, true,
                    new PID(0, 0, 0,0));
    public static final MotorConfig CLIMB_LIFT_CONFIG =
            new MotorConfig(41, false, NeutralMode.Brake);
    public static final MotorConfig CLIMB_WINCH_1_CONFIG =
            new MotorConfig(42, false, NeutralMode.Brake);
    public static final MotorConfig CLIMB_WINCH_2_CONFIG =
            new MotorConfig(44, false, NeutralMode.Brake);
    public static final MotorConfig INTAKE_GROUND_MOTOR =
            new MotorConfig(31, false, NeutralMode.Coast);
    public static final MotorConfig INTAKE_UPTAKE_MOTOR =
            new MotorConfig(32, false, NeutralMode.Brake);

    // Talon constants
    public static final int PID_IDX = 0;

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
