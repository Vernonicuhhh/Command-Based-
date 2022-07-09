package frc.robot.commands.Intaking;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Indexer;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Indexer.IndexerState;

public class IntakeBall extends CommandBase {

    private Intake intake;
    private Indexer indexer;

    public IntakeBall(Indexer indexer, Intake intake){
        this.indexer = indexer;
        this.intake = intake;

        addRequirements(indexer,intake);
    }

    @Override
    public void execute() {
        intake.intake(false);
        indexer.setIndexerState(IndexerState.FORWARD_MANUAL);
    }

    @Override
    public boolean isFinished() {
        intake.stopIntake();
        indexer.stopIndexer();
        return super.isFinished();
    }
    
}
