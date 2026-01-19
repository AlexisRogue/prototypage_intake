// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.ctre.phoenix6.hardware.TalonFX;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.ConditionalCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Intake extends SubsystemBase {
  TalonFX motor = new TalonFX(6);
  boolean spinning = false;

  /** Creates a new Intake. */
  public Intake() {
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public Command spinIntake(int voltage) {
    spinning = true;
    return this.run(() -> motor.setVoltage(voltage));
  }

  public Command stopIntake() {
    spinning = false;
    return this.run(() -> motor.setVoltage(0));
  }

  public ConditionalCommand spinStopIntake(int voltage) {
    return new ConditionalCommand(stopIntake(), spinIntake(voltage), () -> spinning);

  }
}
