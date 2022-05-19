package frc.robot.subsystems;

import com.ctre.phoenix.sensors.WPI_Pigeon2;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.DriveConstants;
import frc.robot.util.SwerveModule;

public class Drive  extends SubsystemBase{
    

    private SwerveModule frontLeft;
    private SwerveModule frontRight;
    private SwerveModule rearLeft; 
    private SwerveModule rearRight;

    private WPI_Pigeon2 gyro;

    public Drive(){
        frontLeft = DriveConstants.FRONT_LEFT_MODULE;
        frontRight = DriveConstants.FRONT_RIGHT_MODULE;
        rearLeft = DriveConstants.REAR_LEFT_MODULE;
        rearRight = DriveConstants.REAR_RIGHT_MODULE;
        gyro = new WPI_Pigeon2(0);
    }

    @Override
    public void simulationPeriodic() {
        frontLeft.updateSim();
        rearLeft.updateSim();
        frontRight.updateSim();
        rearRight.updateSim();
    }


    public void driveFromChassis(ChassisSpeeds speeds){
        var states = DriveConstants.DRIVE_KINEMATICS.toSwerveModuleStates(speeds);
        SwerveDriveKinematics.desaturateWheelSpeeds(states, DriveConstants.MAX_TANGENTIAL_VELOCITY);
        setModuleStates(states);
    }

    public void driveFromChassis(ChassisSpeeds speeds, Translation2d center){
        var states = DriveConstants.DRIVE_KINEMATICS.toSwerveModuleStates(speeds,center);
        SwerveDriveKinematics.desaturateWheelSpeeds(states, DriveConstants.MAX_TANGENTIAL_VELOCITY);
        setModuleStates(states);
    }

    public void holdMotors(){
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
