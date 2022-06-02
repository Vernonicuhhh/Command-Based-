package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.DriveConstants;
import frc.robot.subsystems.Drive;

public class DriveFieldOriented extends CommandBase {

    private final Drive drive;
    private DoubleSupplier fwd,str,rot;
    private boolean offCenter;

    private Translation2d center;

    public DriveFieldOriented(DoubleSupplier fwd, DoubleSupplier str, DoubleSupplier rot, Drive instance){
        this.fwd = fwd;
        this.str= str;
        this.rot = rot;

        drive = instance;
        addRequirements(drive);
    }

    public DriveFieldOriented(DoubleSupplier fwd, DoubleSupplier str, DoubleSupplier rot,Translation2d center ,Drive instance){
        this.fwd = fwd;
        this.str = str;
        this.rot = rot;
        offCenter = true;
        this.center = center;

        drive = instance;
        addRequirements(drive);
    }

    @Override
    public void execute() {
       double vx =  modifyInputs(fwd.getAsDouble(),false);
       double vy =  modifyInputs(str.getAsDouble(),false);
       double omega = modifyInputs(rot.getAsDouble(), true);

         if(offCenter)
             drive.driveFromChassis(new ChassisSpeeds(vx, vy, omega), center);
         else
             drive.driveFromChassis(new ChassisSpeeds(vx,vy,omega));
    }

    @Override
    public void end(boolean interrupted) {
        drive.holdMotors();
    }


    private double modifyInputs(double val, boolean isRot){
        if(isRot){
            if(Math.abs(val)<.15){
                val = 0;
            }
            return val*DriveConstants.MAX_TELE_ANGULAR_VELOCITY;
        }
        else{
            if(Math.abs(val)<.1){
                val = 0;
            }
            return val*DriveConstants.MAX_TELE_TANGENTIAL_VELOCITY;
        }
    }

}
