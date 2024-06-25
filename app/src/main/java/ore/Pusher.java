package ore;

import ch.aplu.jgamegrid.*;

import java.awt.*;
import java.util.List;

public class Pusher extends Machine {

    public Pusher() {
        super(true, "sprites/pusher.png");  // Rotatable
    }

    /**
     * Check if we can move the pusher into the location
     */
    public boolean canMove(Location location, Color borderColor) {
        // Test if try to move into border, rock or clay
        Color c = gameGrid.getBg().getColor(location);
        Rock rock = (Rock) gameGrid.getOneActorAt(location, Rock.class);
        Clay clay = (Clay) gameGrid.getOneActorAt(location, Clay.class);
        Bulldozer bulldozer = (Bulldozer) gameGrid.getOneActorAt(location, Bulldozer.class);
        Excavator excavator = (Excavator) gameGrid.getOneActorAt(location, Excavator.class);
        if (c.equals(borderColor) || rock != null || clay != null || bulldozer != null || excavator != null)
            return false;

        else // Test if there is an ore
        {
            Ore ore = (Ore) gameGrid.getOneActorAt(location, Ore.class);
            if (ore != null) {

                // Try to move the ore
                ore.setDirection(getDirection());
                if (moveOre(ore, borderColor))
                    return true;
                else
                    return false;

            }
        }

        return true;
    }


    /**
     * When the pusher pushes the ore in 1 direction, this method will be called to check if the ore can move in that direction
     * and if it can move, then it changes the location
     */
    private boolean moveOre(Ore ore, Color borderColor) {
        Location next = ore.getNextMoveLocation();
        // Test if try to move into border
        Color c = gameGrid.getBg().getColor(next);
        Rock rock = (Rock) gameGrid.getOneActorAt(next, Rock.class);
        Clay clay = (Clay) gameGrid.getOneActorAt(next, Clay.class);
        if (c.equals(borderColor) || rock != null || clay != null)
            return false;

        // Test if there is another ore
        Ore neighbourOre = (Ore) gameGrid.getOneActorAt(next, Ore.class);
        if (neighbourOre != null)
            return false;

        resetTarget(ore);

        // Move the ore
        ore.setLocation(next);

        checkAtTarget(next, ore);

        return true;
    }

    // Check if we are at a target when moving an ore
    private void checkAtTarget(Location next, Ore ore){
        Target nextTarget = (Target) gameGrid.getOneActorAt(next, Target.class);
        if (nextTarget != null) {
            ore.show(1);
            nextTarget.hide();
        } else {
            ore.show(0);
        }
    }

    // Reset the target if the ore is moved out of target
    private void resetTarget(Ore ore){
        Location currentLocation = ore.getLocation();
        List<Actor> actors = gameGrid.getActorsAt(currentLocation);
        if (actors != null) {
            for (Actor actor : actors) {
                if (actor instanceof Target) {
                    Target currentTarget = (Target) actor;
                    currentTarget.show();
                    ore.show(0);
                }
            }
        }
    }
}
