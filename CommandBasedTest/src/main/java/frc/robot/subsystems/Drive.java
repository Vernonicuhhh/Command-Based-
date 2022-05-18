package frc.robot.subsystems;

import com.ctre.phoenix.sensors.WPI_Pigeon2;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Common.SwerveModule;

public class Drive  extends SubsystemBase{
    

    private SwerveModule frontLeft = new SwerveModule();
    private SwerveModule frontRight = new SwerveModule();
    private SwerveModule rearLeft = new SwerveModule();
    private SwerveModule rearRight = new SwerveModule();

    WPI_Pigeon2 gyro = new WPI_Pigeon2(0);


    public void driveFromChassis(ChassisSpeeds speeds){
        var states = DriveConstants.
    }

    private void holdMotors(){
        driveFromChassis(new ChassisSpeeds());
    }

    private void setModuleStates(SwerveModuleState[] state){
        frontLeft.setState(state[0]);
        frontRight.setState(state[1]);
        rearLeft.setState(state[2]);
        rearRight.setState(state[3]);
    }

    public Rotation2d getHeading(){
        return  gyro.getRotation2d();
    }

    public void resetHeading(){
        gyro.reset();
    }
    
}
