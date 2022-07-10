package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkMax.ControlType;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Constants.IndexerConstants;
import frc.robot.util.sensors.BeamBreak;
import frc.robot.util.sensors.PhotoElectric;
import frc.robot.util.sensors.BeamBreak.BeamBreakState;
import frc.robot.util.sensors.PhotoElectric.PhotoElectricState;

public class Indexer extends SubsystemBase{

    private CANSparkMax indexerMotor;
    private SparkMaxPIDController controller;
    private IndexerState indexerState = IndexerState.OFF;
    private PhotoElectric photoElectricSensor;
    private BeamBreak beamBreak;


    public Indexer(){
        indexerMotor = new CANSparkMax(Constants.IndexerConstants.DEVICE_ID_INDEXER,MotorType.kBrushless);
        controller = indexerMotor.getPIDController();
        controller.setP(0);

        photoElectricSensor = new PhotoElectric(IndexerConstants.PHOTO_ELECTRIC_CHANEL);
        beamBreak = new BeamBreak(IndexerConstants.BEAM_BREAK_CHANEL);
    }

    public enum IndexerState{
        FORWARD_MANUAL,
        EJECT,
        SHOOTING,
        AUTO_INDEX,
        OFF
    }

    public void setIndexerState(IndexerState wantedState){
        indexerState = wantedState;
    }

    @Override
    public void periodic() {
        switch(indexerState){
            case FORWARD_MANUAL:
                index(false, IndexerConstants.FORWARD_SPEED, IndexerConstants.FORWARD_RPM);
                break;
            case EJECT:
                index(false, IndexerConstants.REVERSE_SPEED, IndexerConstants.REVERSE_RPM);
                break;
            case SHOOTING:
                index(false, IndexerConstants.SHOOTING_SPEED, IndexerConstants.SHOOTING_RPM);
                break;
            case AUTO_INDEX:
                autoIndex();
                break;
            case OFF:
                stopIndexer();
                break;
        }

        SmartDashboard.putString("Indexer State", indexerState.toString());
    }

    public void index(boolean useVelocity,double pctSpeed, double vel){
        if(useVelocity)
            setVelocity(vel);
        else
            setSpeed(pctSpeed);
    }

    private void autoIndex(){
        if(photoElectricSensor.check()==PhotoElectricState.BROKEN && beamBreak.check()==BeamBreakState.OPEN)
            index(false,IndexerConstants.FORWARD_SPEED,IndexerConstants.FORWARD_RPM);
        else
            setIndexerState(IndexerState.OFF);
    }

    private void testAutoIndex(){
        photoElectricSensor.check();
        if((Timer.getFPGATimestamp() - photoElectricSensor.getLastOpen()) < SmartDashboard.getNumber("Indexing Time", 1))
            index(false,IndexerConstants.FORWARD_SPEED,IndexerConstants.FORWARD_RPM);
        else
            setIndexerState(IndexerState.OFF);
            
    }

    public void stopIndexer(){
        setSpeed(0);
    }

    private void setSpeed(double speed){
        indexerMotor.set(speed);
    }

    private void setVelocity(double setpointRPM){
        controller.setReference(setpointRPM, ControlType.kVelocity);
    }

}
