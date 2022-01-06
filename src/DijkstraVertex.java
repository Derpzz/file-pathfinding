import graph.Vertex;

public class DijkstraVertex extends Vertex{
    
    private int distance;
    private DijkstraVertex previousItem;

    public DijkstraVertex(String pID){
        super(pID);
        distance = -1;
      }

    public int getDistance(){
        return distance;
    }

    public DijkstraVertex getPrevious(){
        return previousItem;
    }

    public void setDistance(int distance){
        this.distance = distance;
    }

    public void setPrevious(DijkstraVertex previousItem){
        this.previousItem = previousItem;
    }
}
