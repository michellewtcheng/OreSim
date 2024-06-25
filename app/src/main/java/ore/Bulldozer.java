package ore;

import ch.aplu.jgamegrid.*;

import java.awt.*;

public class Bulldozer extends Machine {
    private int clayRemoved = 0;
    public Bulldozer() {
        super(true, "sprites/bulldozer.png");  // Rotatable
    }
    /**
     * Check if we can move the Bulldozer into the location
     */
    public boolean canMove(Location location, Color borderColor) {
        // Test if try to move into border, rock or clay
        Color c = gameGrid.getBg().getColor(location);
        Ore ore = (Ore)gameGrid.getOneActorAt(location, Ore.class);
        Rock rock = (Rock)gameGrid.getOneActorAt(location, Rock.class);
        Clay clay = (Clay)gameGrid.getOneActorAt(location, Clay.class);
        Pusher pusher = (Pusher)gameGrid.getOneActorAt(location, Pusher.class);
        Excavator excavator = (Excavator)gameGrid.getOneActorAt(location, Excavator.class);

        if (c.equals(borderColor) || rock != null || pusher != null || excavator != null || ore != null)
            return false;
        else if (clay != null ) {
            clayRemoved++;
            gameGrid.removeActor(clay);
            return true;
        }
        return true;
    }

    public int getClayRemoved(){
        return clayRemoved;
    }
}
