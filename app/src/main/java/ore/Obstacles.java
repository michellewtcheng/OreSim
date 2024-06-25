package ore;

import ch.aplu.jgamegrid.*;

public abstract class Obstacles extends Actor {
    public Obstacles(String filename){
        super(filename);

    }

    public Obstacles(String filename, int nbSprites){
        super(filename, nbSprites);
    }

}