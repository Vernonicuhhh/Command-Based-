package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.IntakeConstants;
import frc.robot.util.CommonConversions;

public class Intake extends SubsystemBase{
    
    private WPI_TalonSRX intake;
    private DoubleSolenoid intakeSolenoid;

    public Intake(){
        intake = new WPI_TalonSRX(IntakeConstants.DEVICE_ID_INTAKE);
        intakeSolenoid = new DoubleSolenoid(PneumaticsModuleType.CTREPCM,IntakeConstants.INTAKE_SOLENOID_FORWARD,IntakeConstants.INTAKE_SOLENOID_REVERSE);
    }

    public void intake(boolean useVel){
        if(useVel)
            setIntakeVelocity(3000);
        else
            setIntakePercent(IntakeConstants.INTAKE_SPEED);
    }

    public void stopIntake(){
        setIntakePercent(0);
    }

    public void toggleIntake(){
        setSolenoid((getSolenoidVal()==Value.kForward)?Value.kReverse:Value.kForward);
    }

    private void setSolenoid(Value val){
        intakeSolenoid.set(val);
    }

    private Value getSolenoidVal(){
        return intakeSolenoid.get();
    }

    private void setIntakePercent(double percentOutput){
        intake.set(percentOutput);
    }

    private void setIntakeVelocity(double velRPM){
        intake.set(ControlMode.Velocity, CommonConversions.RPMToStepsPerDecisec(velRPM));
    }
}