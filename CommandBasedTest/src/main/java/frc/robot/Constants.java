package frc.robot;

import edu.wpi.first.math.controller.SimpleMotorFeedforward;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.util.Color;
import frc.robot.Common.SwerveModule;

public interface Constants {
    
    public static class DriveConstants{
        public static final int MAX_TANGENTIAL_VELOCITY = 4; 
        public static final double MAX_TELE_TANGENTIAL_VELOCITY = 3.5; 
        public static final double MAX_TELE_ANGULAR_VELOCITY = .85*Math.PI; 
        public static final double WHEELBASE_WIDTH = .762;
        public static final double WHEELBASE_LENGTH = .762;
        public static final double WHEEL_DIAMETER_METERS = .1016;


        private static final int FRONT_LEFT_DRIVE_PORT = 1;
        private static final int FRONT_RIGHT_DRIVE_PORT = 4;
        private static final int REAR_LEFT_DRIVE_PORT = 7;
        private static final int REAR_RIGHT_DRIVE_PORT = 10;

        private static final int FRONT_LEFT_TURN_PORT = 2;
        private static final int FRONT_RIGHT_TURN_PORT = 5;
        private static final int REAR_LEFT_TURN_PORT = 8;
        private static final int REAR_RIGHT_TURN_PORT = 11;

        private static final int FRONT_LEFT_ENCODER_PORT = 3;
        private static final int FRONT_RIGHT_ENCODER_PORT = 6;
        private static final int REAR_LEFT_ENCODER_PORT = 9;
        private static final int REAR_RIGHT_ENCODER_PORT = 12;

        private static final double FRONT_LEFT_ENCODER_OFFSET = 11.162109375;
        private static final double FRONT_RIGHT_ENCODER_OFFSET = -110.830078125;
        private static final double REAR_LEFT_ENCODER_OFFSET = -6.328125;
        private static final double REAR_RIGHT_ENCODER_OFFSET = -79.716796875;
        
        public static SwerveModule FRONT_LEFT_MODULE = new  SwerveModule(FRONT_LEFT_DRIVE_PORT,FRONT_LEFT_TURN_PORT,FRONT_LEFT_ENCODER_PORT,FRONT_LEFT_ENCODER_OFFSET);
        public static SwerveModule FRONT_RIGHT_MODULE = new SwerveModule(FRONT_RIGHT_DRIVE_PORT,FRONT_RIGHT_TURN_PORT,FRONT_RIGHT_ENCODER_PORT,FRONT_RIGHT_ENCODER_OFFSET);
        public static SwerveModule REAR_LEFT_MODULE = new SwerveModule(REAR_LEFT_DRIVE_PORT,REAR_LEFT_TURN_PORT,REAR_LEFT_ENCODER_PORT,REAR_LEFT_ENCODER_OFFSET);
        public static SwerveModule REAR_RIGHT_MODULE = new SwerveModule(REAR_RIGHT_DRIVE_PORT,REAR_RIGHT_TURN_PORT,REAR_RIGHT_ENCODER_PORT,-79.8046875);

        public static final Translation2d FRONT_LEFT_MODULE_POSITION = new Translation2d(WHEELBASE_WIDTH/2,WHEELBASE_LENGTH/2);
        public static final Translation2d REAR_LEFT_MODULE_POSITION = new Translation2d(WHEELBASE_WIDTH/2,-WHEELBASE_LENGTH/2);
        public static final Translation2d FRONT_RIGHT_MODULE_POSITION = new Translation2d(-WHEELBASE_WIDTH/2,WHEELBASE_LENGTH/2);
        public static final Translation2d REAR_RIGHT_MODULE_POSITION = new Translation2d(-WHEELBASE_WIDTH/2,-WHEELBASE_LENGTH/2);

        public static final SwerveDriveKinematics DRIVE_KINEMATICS = new SwerveDriveKinematics(FRONT_LEFT_MODULE_POSITION,REAR_LEFT_MODULE_POSITION,FRONT_RIGHT_MODULE_POSITION,REAR_RIGHT_MODULE_POSITION);

        public static final double TURN_kP = 3/2;
        public static final double TURN_kD = .015;

        public static final double AUTO_CONTROLLER_kP = 2;

    }     

    public static final class ModuleConstants{
        public static final double driveKS = 0.71003;
        public static final double driveKV = 2.2783;
        public static final double driveKA = 0.25953;

        public static final SimpleMotorFeedforward DRIVE_FEEDFORWARD = new SimpleMotorFeedforward(driveKS,driveKV,driveKA);
        public static final double TURNING_KP = .25;

        public static final double CLOSED_LOOP_RAMP_RATE  = .2;
        public static final double OPEN_LOOP_RAMP_RATE  = .25;

        public static final double TURN_GEAR_RATIO  = 12.8;
        public static final double DRIVE_GEAR_RATIO  = 6.75;

        public static final String SWERVE_CANIVORE_ID = "swerve";

        public static final int TIMEOUT_MS = 25;
    }

}
