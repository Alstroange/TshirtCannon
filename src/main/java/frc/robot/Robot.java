// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;


/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends TimedRobot {
  //private final PWMSparkMax m_leftDrive = new PWMSparkMax(0);
  //private final PWMSparkMax m_rightDrive = new PWMSparkMax(1);
  //private final DifferentialDrive m_robotDrive = new DifferentialDrive(m_leftDrive, m_rightDrive);
 
  private final Timer m_timer = new Timer();

  VictorSPX Vspx1 = new VictorSPX(0); // needs to match device ID in tuner // right
  VictorSPX Vspx2 = new VictorSPX(1); //right
  VictorSPX Vspx3 = new VictorSPX(3); //left
  VictorSPX Vspx4 = new VictorSPX(4); //left
  private final XboxController m_controller = new XboxController(0);

  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {
    // We need to invert one side of the drivetrain so that positive voltages
    // result in both sides moving forward. Depending on how your robot's
    // gearbox is constructed, you might have to invert the left side instead.
    //m_rightDrive.setInverted(true);


    
  }

  /** This function is run once each time the robot enters autonomous mode. */
  @Override
  public void autonomousInit() {
    m_timer.reset();
    m_timer.start();
  }

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {
    // Drive for 2 seconds
    if (m_timer.get() < 2.0) {
      // Drive forwards half speed, make sure to turn input squaring off
      //m_robotDrive.arcadeDrive(0.5, 0.0, false);
    } else {
      //m_robotDrive.stopMotor(); // stop robot
    }
  }

  /** This function is called once each time the robot enters teleoperated mode. */
  @Override
  public void teleopInit() {
    //doesn't look like it's doing
    Vspx1.setInverted(false);
    Vspx2.setInverted(false);
    Vspx3.setInverted(false);
    Vspx4.setInverted(false);
  }
  /** This function is called periodically during teleoperated mode. */
  @Override
  public void teleopPeriodic() {
    //m_robotDrive.arcadeDrive(-m_controller.getLeftY(), -m_controller.getRightX());
    
    double vert = m_controller.getRawAxis(1) * .2;
    double horiz = m_controller.getRawAxis(0) * .2;
    Vspx1.set(ControlMode.PercentOutput, vert * (1-horiz));//right
    Vspx2.set(ControlMode.PercentOutput, vert * (1-horiz));//right
    Vspx3.set(ControlMode.PercentOutput, vert * -1 * (1-horiz * -1));//left
    Vspx4.set(ControlMode.PercentOutput, vert * -1 * (1-horiz * -1));//left
  }

  /** This function is called once each time the robot enters test mode. */
  @Override
  public void testInit() {}

  /** This function is called periodically during test mode. */
  @Override
  public void testPeriodic() {}
}
