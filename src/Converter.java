import java.util.List;
import java.util.ArrayList;
import graph.*;

public class Converter {

    /**
     * 
     * @param list VertexList that is to be convertet
     * @return  Convertet List
     * @throws Exception CastError of ListElements during convertion
     */
    static List<Coordinate> EdgeToCoordList(List<Vertex> list) throws Exception{
        List<Coordinate> result = new ArrayList<Coordinate>();
        for (Vertex vertex:list) {
            try{
                result.add((Coordinate) vertex);
            } catch(Exception e){
                throw new Exception("ERROR encountered during cast of Listelements\n" + e.getMessage());
            }
        }
        return result;
    }
}
