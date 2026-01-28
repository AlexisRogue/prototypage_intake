// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.
package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.subsystems.Intake;

public class RobotContainer {

    Joystick stick = new Joystick(0);
    JoystickButton button1 = new JoystickButton(stick, 0);
    JoystickButton button2 = new JoystickButton(stick, 1);
    JoystickButton button3 = new JoystickButton(stick, 2);
    Intake intake = new Intake();

    public RobotContainer() {
        configureBindings();

        button1.onTrue(intake.spinStopIntake(0.5));// Intake le fuel
        button2.onTrue(intake.extendIntake(0.5)).onFalse(intake.stopExtending());// Extend le intake
        button3.onTrue(intake.retractIntake(0.5)).onFalse(intake.stopExtending());// Retract le intake
    }

    private void configureBindings() {

    }

    public Command getAutonomousCommand() {
        return Commands.print("No autonomous command configured");
    }
}
