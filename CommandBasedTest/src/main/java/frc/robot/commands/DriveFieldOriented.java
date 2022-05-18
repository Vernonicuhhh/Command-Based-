package frc.robot.commands;

import java.util.function.Supplier;

import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drive;

public class DriveFieldOriented extends CommandBase {

    private double fwd,str,rot;
    private final Drive drive;
    
    private boolean offCetner;

    private Translation2d center;

    public DriveFieldOriented(Supplier<Double> fwd, Supplier<Double> str, Supplier<Double> rot, Drive instance){
        this.fwd = fwd.get();
        this.str = str.get();
        this.rot = rot.get();

        drive = instance;
        addRequirements(drive);
    }

    public DriveFieldOriented(Supplier<Double> fwd, Supplier<Double> str, Supplier<Double> rot,Translation2d center ,Drive instance){
        this.fwd = fwd.get();
        this.str = str.get();
        this.rot = rot.get();
        offCetner = true;
        this.center = center;

        drive = instance;
        addRequirements(drive);
    }

    @Override
    public void execute() {
        applyDeadband();

        if(offCetner)
            drive.driveFromChassis(new ChassisSpeeds(fwd, str, rot), center);
        else
            drive.driveFromChassis(new ChassisSpeeds(fwd,str,rot));
    }

    @Override
    public void end(boolean interrupted) {
        drive.holdMotors();
    }


    private void applyDeadband(){
        if(Math.abs(fwd)<.1){
            fwd = 0;
        }
        if(Math.abs(str)<.1){
            str = 0;
        }
        if(Math.abs(rot)<.15){
            rot = 0;
        }
    }
}
