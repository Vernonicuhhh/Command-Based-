package frc.robot.util.sensors;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Timer;

public class PhotoElectric {
    private AnalogInput photoElectric;
    private double lastState;
    private double lastOpen;

    public enum PhotoElectricState{
        BROKEN,
        OPEN
    }
    public PhotoElectric(int channel){
        photoElectric = new AnalogInput(channel);
    }

    public PhotoElectricState check(){
        if(photoElectric.getAverageValue()<5){
            //TODO check if the value for the average value is constant, we should probably smart dashboard test this 
            if (lastState!=photoElectric.getAverageValue())
                lastOpen=Timer.getFPGATimestamp();
            lastState = photoElectric.getAverageValue();
            return PhotoElectricState.BROKEN;
        }

        else{
            if (lastState!=photoElectric.getAverageValue())
                lastOpen=Timer.getFPGATimestamp();
            lastState = photoElectric.getAverageValue();
            return PhotoElectricState.OPEN;

        }
    }
    
    public double getLastOpen(){
        return lastOpen;
    }
}
