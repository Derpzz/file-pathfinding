import java.util.LinkedList;
import java.util.Queue;

import graph.*;

public class Dijkstra {

    //Custom Tuple
    public static class Pair<one, two>{
        public one first;
        public two second;
    }

    private Graph OriginGraph;
    private Graph DijkstraGraph;
    private Queue<Coordinate> VertexList;

    public Dijkstra(Graph g){
        this.OriginGraph = g;
        this.VertexList = new LinkedList<>();
    }

    public Graph findShortestPathFromTo(Coordinate from, Coordinate to){
        this.DijkstraGraph = OriginGraph;
        from.setDistance(0);
        processVertices(from);
        return DijkstraGraph;
    }

    private void processVertices(Coordinate curVertex){
        if(curVertex == null)
            return;
        curVertex.setMark(true);
        List<Edge> curEdgesProp = DijkstraGraph.getEdges(curVertex);
        //for(Edge curEdge : curEdgesProp)
        //Nicht möglich wegen propritärer Lösung der Klasse graph.List:
        //Konvertierung nötig in java.util.List, da iterator nötig ist
        ListConverter<Edge, Edge> converter = new ListConverter<>();
        java.util.List<Edge> curEdges = converter.nrwListToList(curEdgesProp);

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
                int distance = curVertex.getDistance() + (int)curEdge.getWeight();
                if(vertices[0].getDistance()==-1 || distance < vertices[0].getDistance()){
                    vertices[0].setDistance(distance);
                    vertices[0].setPrevious(curVertex);
                }

                if(!vertices[0].isMarked())
                    VertexList.add(vertices[0]);
            }
        }
        processVertices(VertexList.poll());
    }

    public void getWay(String filePath){
        java.util.List<Coordinate> vertices = new ListConverter<Coordinate, Vertex>().nrwListToList(OriginGraph.getVertices());
        Pair<Coordinate, Coordinate> Coordinate;

        try{
            Coordinate = getStartEnd(vertices);
        } catch(Exception e){
            IO.print(e.getMessage());
            return;
        }
        
        if(Coordinate.first==null||Coordinate.second==null){
            IO.print("Well you forgot the start or the end or maybe the whole file! But I'm not going to give you an empty file!");
            IO.writeFile(filePath, "Have a nice day! ヾ(￣▽￣) Bye~Bye~");
            return;
        }
            
        Coordinate from = Coordinate.first;
        Coordinate to = Coordinate.second;

        if(DijkstraGraph==null){
            findShortestPathFromTo(from, to);
        }

        //Getting the path
        java.util.List<Coordinate> path = new java.util.ArrayList<Coordinate>();
        Coordinate aktuell = to;
        while(aktuell!=from){
            path.add(0, aktuell);
            //Debug file coords
            if(aktuell!=null){
                System.out.println(aktuell.getID());
            }
            else{
                IO.print("There's no possible way...I guess");
                return;
            }
            aktuell = (Coordinate)aktuell.getPrevious();
        }
        path.add(0, aktuell);
        System.out.println(aktuell.getID());

        IO.writeCoordinatesToFile(filePath, path);
    }

    private static Pair<Coordinate, Coordinate> getStartEnd(java.util.List<Coordinate> vertices) throws Exception{
        Pair<Coordinate, Coordinate> startToEnd = new Pair<Coordinate, Coordinate>();
        Coordinate from = null;
        Coordinate to = null;
        Position start = Position.start;
        Position end = Position.end;

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

        startToEnd.first = from;
        startToEnd.second = to;

        return startToEnd;
    }

    /*public static Graph findShortestPathFromTo(Graph g, Vertex from, Vertex to){      TO BE DONE
        Queue<DijkstraVertex> q = new LinkedList<>();
        return new Graph();
    }*/
}
