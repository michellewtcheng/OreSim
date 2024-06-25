package ore;


import ch.aplu.jgamegrid.*;

public class Direction{
    public Location setMachineDirection(String move, Actor actor){
        Location next = null;
        switch (move) {
            case "L":
                next = actor.getLocation().getNeighbourLocation(Location.WEST);
                actor.setDirection(Location.WEST);
                break;
            case "U":
                next = actor.getLocation().getNeighbourLocation(Location.NORTH);
                actor.setDirection(Location.NORTH);
                break;
            case "R":
                next = actor.getLocation().getNeighbourLocation(Location.EAST);
                actor.setDirection(Location.EAST);
                break;
            case "D":
                next = actor.getLocation().getNeighbourLocation(Location.SOUTH);
                actor.setDirection(Location.SOUTH);
                break;
        }
        return next;
    }
}
