package frc.robot.util.sensors;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Timer;

public class BeamBreak {
    private DigitalInput beamBreak;
    private boolean lastState;
    private double lastOpen;

    public enum BeamBreakState{
        BROKEN,
        OPEN
    }
    public BeamBreak(int channel){
        beamBreak = new DigitalInput(channel);
    }

    public BeamBreakState check(){
        if(beamBreak.get()==false){
            if (lastState!=beamBreak.get())
                lastOpen=Timer.getFPGATimestamp();
            lastState = beamBreak.get();
            return BeamBreakState.BROKEN;
        }

        else{
            if (lastState!=beamBreak.get())
                lastOpen=Timer.getFPGATimestamp();
            lastState = beamBreak.get();
            return BeamBreakState.OPEN;

        }
    }
    
    public double getLastOpen(){
        return lastOpen;
    }
    
}
