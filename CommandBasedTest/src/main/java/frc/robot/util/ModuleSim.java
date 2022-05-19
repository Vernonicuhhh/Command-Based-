package frc.robot.util;

import com.ctre.phoenix.motorcontrol.TalonFXSensorCollection;
import com.ctre.phoenix.motorcontrol.TalonFXSimCollection;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.sensors.CANCoderSimCollection;
import com.ctre.phoenix.sensors.WPI_CANCoder;

import edu.wpi.first.math.system.plant.DCMotor;
import edu.wpi.first.math.system.plant.LinearSystemId;
import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.wpilibj.simulation.FlywheelSim;

public class ModuleSim {

    private TalonFXSimCollection driveCollection;
    private TalonFXSimCollection turnCollection;
    private CANCoderSimCollection encoderCollection;
    private FlywheelSim driveSim;
    private FlywheelSim turnSim;

    public ModuleSim(WPI_TalonFX drive, WPI_TalonFX turn, WPI_CANCoder encoder){
        driveCollection = drive.getSimCollection();
        turnCollection = turn.getSimCollection();

        encoderCollection = encoder.getSimCollection();

        driveSim = new FlywheelSim(LinearSystemId.identifyVelocitySystem(2, .29), DCMotor.getFalcon500(1), 6.75);
        turnSim = new FlywheelSim(LinearSystemId.identifyVelocitySystem(2, 1.24), DCMotor.getFalcon500(1), 12.8);
    }

    public void simPeriodic(){
        driveSim.setInputVoltage(driveCollection.getMotorOutputLeadVoltage());
        turnSim.setInputVoltage(turnCollection.getMotorOutputLeadVoltage());

        driveSim.update(.02);
        turnSim.update(.02);

        updateSensors();
        
        setBusVoltages();

    }

    private void updateSensors(){
        double turnVelStepsPerDecisec = turnSim.getAngularVelocityRPM()*2048/600*12.8;
        double turnSteps = turnVelStepsPerDecisec*10*.02;

        double driveVelStepsPerDecisec = driveSim.getAngularVelocityRPM()*2048/600*12.8;
        double driveSteps = driveVelStepsPerDecisec*10*.02;
        
        encoderCollection.addPosition((int)turnSteps);

        turnCollection.setIntegratedSensorVelocity((int)turnVelStepsPerDecisec);
        turnCollection.setIntegratedSensorRawPosition((int)turnSteps);

        driveCollection.setIntegratedSensorVelocity((int)driveVelStepsPerDecisec);
        driveCollection.setIntegratedSensorRawPosition((int)driveSteps);
    }

    private void setBusVoltages(){
        driveCollection.setBusVoltage(RobotController.getBatteryVoltage());
        turnCollection.setBusVoltage(RobotController.getBatteryVoltage());
        encoderCollection.setBusVoltage(RobotController.getBatteryVoltage());
    }



    
}
