import graph.Graph;

public class Main {
    public static void main(String[] args) throws Exception {
        char[][] matrix = new IO().readFileToMatrix("");
        Graph g = GraphConverter.matrixToGraph(matrix);
        new Dijkstra(g).getWay("");
    }  
}
