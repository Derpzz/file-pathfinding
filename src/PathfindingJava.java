import graph.Graph;

public class PathfindingJava {
    public static void main(String[] args) throws Exception {
        //C:\Users\AdemS\source\repos\Pathfinding Java NRW\src\fileTest.txt
        //C:\Users\AdemS\source\repos\Pathfinding Java NRW\src\output.txt
        String input = IO.getInput("Path of input file:");
        String output = IO.getInput("Path of output file:");
        char[][] matrix = new IO().readFileToMatrix(input);
        Graph g = GraphConverter.matrixToGraph(matrix);
        new Dijkstra(g).getWay(output);
    }  
}