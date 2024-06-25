package ore;

import ch.aplu.jgamegrid.*;

import java.awt.*;
import java.util.List;

public abstract class Machine extends Actor {
    private List<String> controls;
    private boolean isAutoMode;
    private int autoMovementIndex = 0;
    private int moveCount = 0;
    private Direction direction = new Direction();

    public Machine (boolean isRotatable, java.lang.String filename){
        super(isRotatable, filename);
    }

    public abstract boolean canMove(Location location, Color borderColor);

    /**
     * Sets up machines according to the game mode and the controls that direct their movement
     */
    public void setUp(boolean isAutoMode, List<String> controls) {
        this.isAutoMode = isAutoMode;
        this.controls = controls;
    }

    /**
     * Moves specified machine automatically based on the instructions input from properties file
     */
    public void autoMoveNext(boolean isFinished, Color borderColor, String currMachine){
        if (controls != null && autoMovementIndex < controls.size()) {
            String[] currentMove = controls.get(autoMovementIndex).split("-");
            String machine = currentMove[0];
            String move = currentMove[1];

            autoMovementIndex++;

            if (machine.equals(currMachine)) {

                if (isFinished) {
                    return;
                }

                Location next = direction.setMachineDirection(move, this);

                Target curTarget = (Target) gameGrid.getOneActorAt(getLocation(), Target.class);
                if (curTarget != null){
                    curTarget.show();
                }
                if (next != null && canMove(next, borderColor))
                {
                    setLocation(next);
                    moveCount++;
                }
                gameGrid.refresh();
            }
        }
    }

    public int getMoveCount() {
        return moveCount;
    }
}
