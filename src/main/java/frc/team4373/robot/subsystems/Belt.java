package frc.team4373.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.team4373.robot.commands.BeltCommand;

public class Belt extends Subsystem {

    private static volatile Belt instance;

    /**
     * The getter for the Belt class.
     * @return the singleton Belt object.
     */
    public static Belt getInstance() {
        if (instance == null) {
            synchronized (Belt.class) {
                if (instance == null) {
                    instance = new Belt();
                }
            }
        }
        return instance;
    }

    private WPI_TalonSRX motor;
    private Belt(){
        this.motor = new WPI_TalonSRX(20)
        this.motor.setInverted(false);
        this.motor.setNeutralMode(NeutralMode.Brake);
    }

    public void setSpeed(double speed){
        this.motor.set(ControlMode.PercentOutput, speed);
    }

    public void stop() {
        this.motor.stopMotor();
    }



    @Override
    protected void initDefaultCommand() {
        setDefaultCommand(new BeltCommand());
    }
}
