package src.games.basic.examples.ticTacToe;

enum Ownership {
    UNDEFINED,
    CROSS,
    CIRCLE
}
public class Info {

    private boolean occupied;
    private Ownership spot;

    Info(){
        this.occupied = false;
        this.spot = Ownership.UNDEFINED;
    }

    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }

    public void setInfo(Ownership info){
        this.spot = info;
    }

    public Ownership getSpotInfo() {
        return spot;
    }

    public boolean GetIsOccupied() {
        return occupied;
    }
}
