package graph;
import java.util.ArrayList;
import java.util.List;

//import java.io.Serializable;

/**
 * <p>
 * Materialien zu den zentralen NRW-Abiturpruefungen im Fach Informatik (Vorschlag)
 * </p>
 * <p>
 * Klasse Graph
 * </p>
 * <p>
 * Die Klasse Graph stellt einen ungerichteten, kantengewichteten Graphen dar. Es koennen 
 * Knoten- und Kantenobjekte hinzugefuegt und entfernt, flache Kopien der Knoten- und Kantenlisten 
 * des Graphen angefragt und Markierungen von Knoten und Kanten gesetzt und ueberprueft werden.
 * Des Weiteren kann eine Liste der Nachbarn eines bestimmten Knoten, eine Liste der inzidenten 
 * Kanten eines bestimmten Knoten und die Kante von einem bestimmten Knoten zu einem 
 * anderen bestimmten Knoten angefragt werden. Abgesehen davon kann abgefragt werden, welches 
 * Knotenobjekt zu einer bestimmten ID gehoert und ob der Graph leer ist.
 * </p>
 * 
 * @author Volker Quade
 * @version April 2015
 */
//@SuppressWarnings("serial")
public class Graph /*implements Serializable*/{
  private List<Vertex> vertices;
  private List<Edge> edges;

  /**
   * Ein Objekt vom Typ Graph wird erstellt. Der von diesem Objekt 
   * repraesentierte Graph ist leer.
   */
  public Graph(){
    //Leere Listen fuer Knoten und Kanten erstellen
    vertices = new ArrayList<Vertex>();
    edges = new ArrayList<Edge>();
  }

  /**
   * Die Anfrage liefert eine Liste aller Knotenobjekte vom Typ List<Vertex>.
   */
  public List<Vertex> getVertices(){
    return vertices;
  }

  /**
   * Die Anfrage liefert eine Liste aller Kantenobjekte vom Typ List<Edge>.
   */
  public List<Edge> getEdges(){
    return edges;
  }

  /**
   * Die Anfrage liefert das Knotenobjekt mit pID als ID. Ist ein solchen Knotenobjekt nicht im Graphen enthalten,
   * wird null zurueckgeliefert.
   */
  public Vertex getVertex(String pID){
    //Vertex-Objekt mit pID als ID suchen
    Vertex result = null;
    for (Vertex vertex:vertices){
      if (vertex.getID().equals(pID)){
        result = vertex;
        break;
      }
    }

    //Objekt zurueckliefern
    return result;
  }

  /**
   * Der Auftrag fuegt den Knoten pVertex vom Typ Vertex in den Graphen ein, sofern es noch keinen
   * Knoten mit demselben ID-Eintrag wie pVertex im Graphen gibt und pVertex eine ID ungleich null hat. 
   * Ansonsten passiert nichts.
   */
  public void addVertex(Vertex pVertex){
    //Pruefen, ob der Vertex eine ID hat.
    if (pVertex != null && pVertex.getID() != null) {
      //Pruefen, ob nicht schon ein Vertex mit gleicher ID enthalten ist.
      boolean freeID = true;
      for (Vertex vertex:vertices /*&& freeID*/){
        if (vertex.getID().equals(pVertex.getID())){
          freeID = false;
          break;
        }
      }

      //Wenn die ID noch frei ist, den Vertex einfuegen, sonst nichts tun.
      if (freeID){
        vertices.add(pVertex);      
      }
    }
  }

  /**
   * Der Auftrag fuegt die Kante pEdge in den Graphen ein, sofern beide durch die Kante verbundenen Knoten
   * im Graphen enthalten sind, nicht identisch sind und noch keine Kante zwischen den Knoten existiert. Ansonsten passiert nichts.
   */
  public void addEdge(Edge pEdge){ 
    if (pEdge != null){
      Vertex[] vertexPair = pEdge.getVertices();
      if (vertexPair[0] != null && vertexPair[1] != null && 
      // this.getVertex(vertexPair[0].getID()) == vertexPair[0] && 
      // this.getVertex(vertexPair[1].getID()) == vertexPair[1] &&
      vertices.contains(vertexPair[0]) &&
      vertices.contains(vertexPair[1]) &&
      this.getEdge(vertexPair[0], vertexPair[1]) == null &&
      vertexPair[0] != vertexPair[1]){
        edges.add(pEdge); 
      }
    }
  }

  /**
   * Der Auftrag entfernt den Knoten pVertex aus dem Graphen und loescht alle Kanten, die mit ihm inzident sind.
   * Ist der Knoten pVertex nicht im Graphen enthalten, passiert nichts.
   */
  public void removeVertex(Vertex pVertex){
    //Inzidente Kanten entfernen.
    for (Edge edge:edges){
      Vertex[] akt = edge.getVertices();
      if (akt[0] == pVertex || akt[1] == pVertex){
        edges.remove(edge);
      }
    }

    //Knoten entfernen
    vertices.remove(pVertex);
  }

  /**
   * Der Auftrag entfernt die Kante pEdge aus dem Graphen. Ist die Kante pEdge nicht 
   * im Graphen enthalten, passiert nichts.
   */
  public void removeEdge(Edge pEdge){
    //Kante aus Kantenliste des Graphen entfernen.

    //KÖNNTE DIE KANTE AUCH DOPPELT VORKOMMEN?
    edges.remove(pEdge);
  }

  /**
   * Der Auftrag setzt die Markierungen aller Knoten des Graphen auf pMark.
   */
  public void setAllVertexMarks(boolean pMark){
    for (Vertex vertex:vertices){
      vertex.setMark(pMark);
    }
  }

  /**
   * Der Auftrag setzt die Markierungen aller Kanten des Graphen auf pMark.
   */
  public void setAllEdgeMarks(boolean pMark){
    for (Edge edge:edges){
      edge.setMark(pMark);
    }
  }

  /**
   * Die Anfrage liefert true, wenn alle Knoten des Graphen mit true markiert sind, ansonsten false.
   */
  public boolean allVerticesMarked(){
    boolean result = true;
    for (Vertex vertex:vertices){
      if (!vertex.isMarked()){
        result = false;
        break;
      }
    }
    return result;
  }

  /**
   * Die Anfrage liefert true, wenn alle Kanten des Graphen mit true markiert sind, ansonsten false.
   */
  public boolean allEdgesMarked(){
    boolean result = true;
    for (Edge edge:edges){
      if (!edge.isMarked()){
        result = false;
        break;
      }
    }
    return result;
  }

  /**
   * Die Anfrage liefert alle Nachbarn des Knotens pVertex als Liste vom Typ List<Vertex>. Hat der Knoten
   * pVertex keine Nachbarn in diesem Graphen oder ist gar nicht in diesem Graphen enthalten, so 
   * wird eine leere Liste zurueckgeliefert.
   */
  public List<Vertex> getNeighbours(Vertex pVertex){
    List<Vertex> result = new ArrayList<Vertex>();
    for (Edge edge:edges){
      Vertex[] vertexPair = edge.getVertices();
      if (vertexPair[0] == pVertex) {
        result.add(vertexPair[1]);
      } else { 
        if (vertexPair[1] == pVertex){
          result.add(vertexPair[0]);
        }
      }
    }    
    return result;
  }

  /**
   * Die Anfrage liefert alle inzidenten Kanten zum Knoten pVertex. Hat der Knoten
   * pVertex keine inzidenten Kanten in diesem Graphen oder ist gar nicht in diesem Graphen enthalten, so 
   * wird eine leere Liste zurueckgeliefert.
   */
  public List<Edge> getEdges(Vertex pVertex){
    List<Edge> result = new ArrayList<Edge>();

    for (Edge edge:edges){
      Vertex[] vertexPair = edge.getVertices();
      if (vertexPair[0] == pVertex) {
        result.add(edge);
      } else{ 
        if (vertexPair[1] == pVertex){
          result.add(edge);
        }
      }
    }    
    return result;
  }

  /**
   * Die Anfrage liefert die Kante, welche die Knoten pVertex und pAnotherVertex verbindet, 
   * als Objekt vom Typ Edge. Ist der Knoten pVertex oder der Knoten pAnotherVertex nicht 
   * im Graphen enthalten oder gibt es keine Kante, die beide Knoten verbindet, so wird null 
   * zurueckgeliefert.
   */
  public Edge getEdge(Vertex pVertex, Vertex pAnotherVertex){
    Edge result = null;

    for (Edge edge:edges){
      Vertex[] vertexPair = edge.getVertices();
      if ((vertexPair[0] == pVertex && vertexPair[1] == pAnotherVertex) ||
      (vertexPair[0] == pAnotherVertex && vertexPair[1] == pVertex)) {
        result = edge;
        //edges.toLast();
        break;//???
      } 
    }    
    return result;
  }

  /**
   * Die Anfrage liefert true, wenn der Graph keine Knoten enthaelt, ansonsten false.
   */
  public boolean isEmpty(){
    return vertices.isEmpty();
  } 

}
