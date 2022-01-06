import java.util.LinkedList;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

import graph.Edge;
import graph.Vertex;
import graph.Graph;

public class Dijkstra {

    //Custom Tuple
    public static class Pair<one, two>{
        public one first;
        public two second;
    }

    private Graph OriginGraph;
    private Graph DijkstraGraph;
    private Queue<Coordinate> VertexList;
    private static final Position start = Position.start;
    private static final Position end = Position.end;

    public Dijkstra(Graph g){
        this.OriginGraph = g;
        this.VertexList = new LinkedList<>();
    }

    private void processVertices(Coordinate curVertex){
        if(curVertex == null)
            return;

        this.DijkstraGraph = OriginGraph;
        curVertex.setDistance(0);

        while(curVertex!=null){
            curVertex.setMark(true);
            List<Edge> curEdgesProp = DijkstraGraph.getEdges(curVertex);
            List<Edge> curEdges = curEdgesProp;

            for(Edge curEdge : curEdges){
                //Convert Vertex[] to Coordinate[]
                Coordinate[] vertices = new Coordinate[2]; 
                Vertex[] oldVertex = curEdge.getVertices();
                vertices[0] = (Coordinate) oldVertex[0];
                vertices[1] = (Coordinate) oldVertex[1];

                if(vertices[0]==curVertex){
                    int distance = curVertex.getDistance() + (int) curEdge.getWeight();
                    if(vertices[1].getDistance()==-1 || distance < vertices[1].getDistance()){
                        vertices[1].setDistance(distance);
                        vertices[1].setPrevious(curVertex);
                    }
                if(!vertices[1].isMarked())
                    VertexList.add(vertices[1]);

                } else if(vertices[1]==curVertex){
                    int distance = curVertex.getDistance() + (int) curEdge.getWeight();
                    if(vertices[0].getDistance()==-1 || distance < vertices[0].getDistance()){
                        vertices[0].setDistance(distance);
                        vertices[0].setPrevious(curVertex);
                    }
                    if(!vertices[0].isMarked())
                        VertexList.add(vertices[0]);
                    }   
            }
            curVertex = VertexList.poll();
        }
        
        //processVertices(VertexList.poll()); For recursive exec
    }

    public void getWay(String filePath) throws Exception{
        List<Coordinate> vertices = Converter.EdgeToCoordList(OriginGraph.getVertices());
        Pair<Coordinate, Coordinate> coordinate;
        Coordinate from;
        Coordinate to;

        //Determine start and end
        try{
            coordinate = getStartEnd(vertices);
        } catch(Exception e){
            IO.print(e.getMessage());
            return;
        }
            
        from = coordinate.first;
        to = coordinate.second;

        if(DijkstraGraph==null){
            processVertices(from);
        }

        //Getting the path
        List<Coordinate> path = new ArrayList<Coordinate>();
        Coordinate current = to;
        while(current!=from){
            path.add(0, current);
            if(current==null){
                IO.print("There's no possible way...I guess");
                return;
            }
            current = (Coordinate)current.getPrevious();
        }
        path.add(0, current);

        IO.print("Done!");
        IO.writeCoordinatesToFile(filePath, path);
    }

    private static Pair<Coordinate, Coordinate> getStartEnd(java.util.List<Coordinate> vertices) throws Exception{
        Pair<Coordinate, Coordinate> startToEnd = new Pair<Coordinate, Coordinate>();
        Coordinate from = null;
        Coordinate to = null;

        for(Coordinate vertex : vertices){
            if(vertex.getPosition()==start)
                if(from==null)
                    from = vertex;
                else
                    throw new Exception("Multiple first nodes");
            else if (vertex.getPosition()==end)
                if(to==null)
                    to = vertex;
                else
                    throw new Exception("Multiple last nodes");
        }

        if(from==null||to==null)
            throw new Exception("Well you forgot the start or the end or maybe the whole file!");

        startToEnd.first = from;
        startToEnd.second = to;

        return startToEnd;
    }
}
