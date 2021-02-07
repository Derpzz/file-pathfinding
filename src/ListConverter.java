import java.util.LinkedList;
//import graph.*;

public class ListConverter<E, E2> {
    public java.util.List<E> nrwListToList(graph.List<E2> propList){
        java.util.List<E> newList = new LinkedList<>();
        propList.toFirst();
        while(propList.hasAccess()){
            //Add checks
            try{
                newList.add((E)propList.getContent());
            } catch(Exception e){
                IO.print("ERROR encountered during cast of Listelements\n" + e.getMessage());
            }
            propList.next();
        }
        return newList;
    }
}
