package frc.team4373.robot.commands.drivetrain;

/**
 * Rotate the robot a specified number of degrees, without requiring the drivetrain.
 *
 * <p>Note that the drivetrain will still be accessed, so this is unsafe.
 */
public class RequirementFreeRotateAngleOffsetAuton extends RotateAngleOffsetAuton {
    public RequirementFreeRotateAngleOffsetAuton(double offset) {
        super(offset);
        clearRequirements();
    }
}
