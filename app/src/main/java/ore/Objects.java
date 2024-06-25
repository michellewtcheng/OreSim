package ore;

import ch.aplu.jgamegrid.*;

public class Objects extends GameGrid {
    private Pusher pusher;
    private Bulldozer bulldozer;
    private Excavator excavator;

    public Objects(){
        this.pusher = new Pusher();
        this.bulldozer = new Bulldozer();
        this.excavator = new Excavator();
    }

    public Pusher getPusher() {
        return pusher;
    }

    public Bulldozer getBulldozer() {
        return bulldozer;
    }

    public Excavator getExcavator() {
        return excavator;
    }

}
