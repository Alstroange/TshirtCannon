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
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Compressor;


/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends TimedRobot {

  //Victor SPX 
  //deviceNumber matches the device ID shown in PhoenixTunerX
  VictorSPX Vspx1 = new VictorSPX(0); //left 
  VictorSPX Vspx2 = new VictorSPX(1); //left
  VictorSPX Vspx3 = new VictorSPX(3); //right
  VictorSPX Vspx4 = new VictorSPX(4); //right

  //Xbox Controller
  //default is port 0
  private final XboxController m_controller = new XboxController(0);

  

  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {}

  /** This function is run once each time the robot enters autonomous mode. */
  @Override
  public void autonomousInit() {}

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {}

  /** This function is called once each time the robot enters teleoperated mode. */
  @Override
  public void teleopInit() {

    //doesn't look like it did anything as compared to how the wheels turned without it
    Vspx1.setInverted(false);
    Vspx2.setInverted(false);
    Vspx3.setInverted(false);
    Vspx4.setInverted(false);
  }

  /** This function is called periodically during teleoperated mode. */
  @Override
  public void teleopPeriodic() {
    
    //gets the Xbox controller values for: 
    // * .2 to make it slower 

    //left joystick on the controller
    double vert = m_controller.getRawAxis(1) * .2;
    //right joystick on the controller
    double horiz = m_controller.getRawAxis(4) * .2;

    //if we aren't moving the the bot forwards or back, then we can turn it
    if(vert >= -0.09 && vert <= 0.09)
    {
      Vspx1.set(ControlMode.PercentOutput, horiz);//left
      Vspx2.set(ControlMode.PercentOutput, horiz);//left
      Vspx3.set(ControlMode.PercentOutput, horiz);//right
      Vspx4.set(ControlMode.PercentOutput, horiz);//right
    }

    //if we aren't turning the bot, we can move it forwards and back
    if(horiz >= -0.09 && horiz <= 0.09)
    {
      Vspx1.set(ControlMode.PercentOutput, vert * -1);//left
      Vspx2.set(ControlMode.PercentOutput, vert * -1);//left
      Vspx3.set(ControlMode.PercentOutput, vert);//right
      Vspx4.set(ControlMode.PercentOutput, vert);//right
    }
  }

  /** This function is called once each time the robot enters test mode. */
  @Override
  public void testInit() {}

  /** This function is called periodically during test mode. */
  @Override
  public void testPeriodic() {}
}
