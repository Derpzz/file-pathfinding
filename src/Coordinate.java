public class Coordinate extends DijkstraVertex {
    
    private int x;
    private int y;
    private Position position;

    public Coordinate(int x, int y, Position position){
        super(x + "," + y);
        this.x = x;
        this.y = y;
        this.position = position;
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
}
