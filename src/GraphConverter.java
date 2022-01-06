import graph.Graph;
import graph.Edge;
import java.util.List;
import java.util.ArrayList;

public class GraphConverter {

    private static final Position start = Position.start;
    private static final Position end = Position.end;
    private static final Position way = Position.way;
    private static final Position empty = Position.empty;

    public static Graph matrixToGraph(char[][] matrix){
        Graph g = new Graph();
        List<Coordinate> coordinates = new ArrayList<Coordinate>();
        Position currentPosition;
        
        for(int i = 0; i < matrix.length; i++){
            for(int j = 0; j < matrix[i].length; j++){
                currentPosition = way;

                if(matrix[i][j]==start.getLetter()||matrix[i][j]==end.getLetter()||matrix[i][j]==way.getLetter()){
                    if(matrix[i][j]==start.getLetter())
                        currentPosition = start;
                    else if(matrix[i][j]==end.getLetter())
                        currentPosition = end;
                    Coordinate curCoordinate = new Coordinate(i, j, currentPosition);
                    coordinates.add(curCoordinate);
                    g.addVertex(curCoordinate);
                }
                else {
                    coordinates.add(new Coordinate(i, j, empty));
                    continue;
                }

                //After this point everything could be written in first if after line 25
                
                int curPos = i * matrix[0].length + j;

                if(i!=0){
                    if(matrix[i-1][j]==start.getLetter()||matrix[i-1][j]==end.getLetter()||matrix[i-1][j]==way.getLetter()){
                        int zeileDavor = curPos - j - (matrix[0].length - j);
                        g.addEdge(new Edge(coordinates.get(zeileDavor), coordinates.get(curPos), 1));
                    }   
                }

                if(j!=0){
                    if(matrix[i][j-1]==start.getLetter()||matrix[i][j-1]==end.getLetter()||matrix[i][j-1]==way.getLetter())
                        g.addEdge(new Edge(coordinates.get(curPos-1), coordinates.get(curPos), 1));
                }
            }
        }
        return g;
    }

    public static char[][] pathToMatrix(List<Coordinate> shortestPath){
        char[][] matrix;
        
        //Get largestX and largestY to determine arrayLength
        int biggestX = 0;
        int biggestY = 0;
        for(Coordinate aktuell : shortestPath){
            if(aktuell.getCoords()[0] > biggestX)
                biggestX = aktuell.getCoords()[0];
            if(aktuell.getCoords()[1] > biggestY)
                biggestY = aktuell.getCoords()[1];
        }
        matrix = new char[biggestX + 1][biggestY + 1];

        //Fill with spaces
        for(int i = 0; i < matrix.length; i++){
            for(int j = 0; j < matrix[i].length; j++){
                matrix[i][j] = empty.getLetter();
            }
        }

        //Fill in the actual path
        for(int i = 0; i <= shortestPath.size() - 1; i++){
            Coordinate aktuell = shortestPath.get(i);
            matrix[aktuell.getCoords()[0]][aktuell.getCoords()[1]] = aktuell.getPosition().getLetter();//way.letter;
        }

        return matrix;
    }
}
