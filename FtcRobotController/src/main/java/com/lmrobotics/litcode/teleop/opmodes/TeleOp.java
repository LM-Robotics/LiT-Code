package com.lmrobotics.litcode.teleop.opmodes;

import com.lmrobotics.litcode.autonomous.opmodes.AutoOpModeBase;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.lmrobotics.litcode.devices.DriveSystem;
import com.lmrobotics.litcode.devices.ArmSystem;

/** Enables control of the robot with the gamepad
 */
public class TeleOp extends OpMode
{
    private static final float deadBand = 0.07f;

    public DriveSystem drive;

    public ArmSystem arm;

    float scaleSpeed = 1.0f;

    @Override
    public void init()
    {
        // AutoOpModeBase is used to get telemetry by most classes
        AutoOpModeBase.telemetryAccess = telemetry;
        drive = new DriveSystem(this.hardwareMap);
//        arm = new ArmSystem(this.hardwareMap);
    }

    @Override
    public void loop()
    {
        float leftDriveControl = gamepad1.left_stick_y;
        telemetry.addData("LEFT", leftDriveControl);
        float rightDriveControl = gamepad1.right_stick_y;
        // Left and right drive motor control


        if (Math.abs(leftDriveControl) >= deadBand)
        {
            drive.setLeft(leftDriveControl / scaleSpeed);
        }
        else
        {
            drive.setLeft(0);
        }
        // To control the robot with the left stick
        if (Math.abs(rightDriveControl)  >= deadBand)
        {
            drive.setRight(rightDriveControl / scaleSpeed);
        }
        else
        {
            drive.setRight(0);
        }
        // To control the robot with the right stick
        if (gamepad1.left_bumper || gamepad1.right_bumper)
        {
            scaleSpeed =  2.0f;
        }
        else
        {
            scaleSpeed = 1.0f;
        }
//        if (gamepad1.right_trigger)
//        {
//            arm.armUp(20);
//        }
//        else if (gamepad1.left_trigger)
//        {
//           arm.armDown(-20);
//        }
//        else
//        {
//          arm.armUp(0);
//        }
    }
}
