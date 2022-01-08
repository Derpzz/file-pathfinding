import graph.Vertex;

public class Coordinate extends Vertex {
    
    private int x;
    private int y;
    private Position position;
    private int distance;
    private Coordinate previousItem;

    public Coordinate(int x, int y, Position position){
        super(x + "," + y);
        this.x = x;
        this.y = y;
        this.position = position;
        distance = -1;
    }

    @Override
    public String getID(){
        return x + ","+ y;
    }

    public int[] getCoords(){
        return new int[] {x, y};
    }

    public Position getPosition(){
        return position;
    }

    public int getDistance(){
        return distance;
    }

    public Coordinate getPrevious(){
        return previousItem;
    }

    public void setDistance(int distance){
        this.distance = distance;
    }

    public void setPrevious(Coordinate previousItem){
        this.previousItem = previousItem;
    }
}
