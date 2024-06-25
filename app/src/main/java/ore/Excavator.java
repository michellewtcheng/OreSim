package ore;
import ch.aplu.jgamegrid.Location;

import java.awt.*;

public class Excavator extends Machine {
    private int rocksRemoved = 0;

    public Excavator()    {
        super(true, "sprites/excavator.png");  // Rotatable
    }

    /**
     * Check if we can move the excavator into the location
     */
    public boolean canMove(Location location, Color borderColor) {
        // Test if try to move into border, rock or clay
        Color c = gameGrid.getBg().getColor(location);
        Rock rock = (Rock)gameGrid.getOneActorAt(location, Rock.class);
        Ore ore = (Ore)gameGrid.getOneActorAt(location, Ore.class);
        Clay clay = (Clay)gameGrid.getOneActorAt(location, Clay.class);
        Pusher pusher = (Pusher)gameGrid.getOneActorAt(location, Pusher.class);
        Bulldozer bulldozer = (Bulldozer)gameGrid.getOneActorAt(location, Bulldozer.class);

        if (c.equals(borderColor) || clay != null || pusher != null || bulldozer != null || ore != null)
            return false;
        else if (rock != null) {
            rocksRemoved++;
            gameGrid.removeActor(rock);
            return true;
        }
        return true;
    }

    public int getRocksRemoved(){
        return rocksRemoved;
    }

}