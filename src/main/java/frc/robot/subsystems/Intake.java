// Copyright (c) FIRST and other WPILib contributors.// Open Source Software; you can modify and/or share it under the terms of// the WPILib BSD license file in the root directory of this project.
package frc.robot.subsystems;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.InvertedValue;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.ConditionalCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Intake extends SubsystemBase {

    boolean spinning = false;
    boolean extending = false;
    TalonFX rollerMotor;
    TalonFX extendMotor;
    TalonFXConfiguration extendingMotorConfig;

    /**
     * Creates a new Intake.
     */
    public Intake() {
        rollerMotor = new TalonFX(Constants.kRollerMotorID);
        extendMotor = new TalonFX(Constants.kExtendMotorID);
        extendingMotorConfig.MotorOutput.Inverted = InvertedValue.Clockwise_Positive;
    }

    @Override

    public void periodic() {
        // This method will be called once per scheduler run  }
    }

    public Command spinIntake(double voltage) {
        spinning = true;
        return this.run(() -> rollerMotor.setVoltage(voltage));
    }

    public Command stopIntake() {
        spinning = false;
        return this.run(() -> rollerMotor.setVoltage(0));
    }

    public Command extendIntake(double voltage) {
        extending = true;
        return this.run(() -> extendMotor.setVoltage(voltage + Constants.kGExtension));

    }

    public Command stopExtending() {
        extending = false;
        return this.run(() -> extendMotor.setVoltage(Constants.kGExtension));

    }

    public Command retractIntake(double voltage) {
        extending = false;
        return this.run(() -> extendMotor.setVoltage(-voltage + Constants.kGExtension));

    }

    public ConditionalCommand spinStopIntake(double voltage) {
        return new ConditionalCommand(stopIntake(), spinIntake(voltage), () -> spinning);

    }
}
