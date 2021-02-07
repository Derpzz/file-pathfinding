package graph;

import java.io.Serializable;

/**
 * <p>
 * Materialien zu den zentralen NRW-Abiturpruefungen im Fach Informatik (Vorschlag)
 * </p>
 * <p>
 * Klasse Edge
 * </p>
 * <p>
 * Die Klasse Edge stellt eine einzelne, ungerichtete Kante eines Graphen dar. 
 * Beim Erstellen werden die beiden durch sie zu verbindenden Knotenobjekte und eine 
 * Gewichtung als double uebergeben. Die Gewichtung und beide Knotenobjekte koennen 
 * abgefragt werden. Des Weiteren kann eine Markierung gesetzt und abgefragt werden.
 * </p>
 * 
 * @author Volker Quade
 * @version Mai 2015
 */
@SuppressWarnings("serial")
public class Edge implements Serializable{  
  private Vertex[] vertices;
  private double weight;
  private boolean mark;

  /**
   * Ein neues unmarkiertes Objekt vom Typ Edge wird erstellt. Die von diesem Objekt 
   * repraesentierte Kante verbindet die Knoten pVertex und pAnotherVertex mit der 
   * Gewichtung pWeight und ist noch in keinen Graphen eingefuegt.
   */
  public Edge(Vertex pVertex, Vertex pAnotherVertex, double pWeight){
    vertices = new Vertex[2];
    vertices[0] = pVertex;
    vertices[1] = pAnotherVertex;
    weight = pWeight;
    mark = false;
  }

  /**
   * Die Anfrage gibt die beiden Knoten, die durch die Kante verbunden werden, als Feld vom Typ Vertex zurueck. Das Feld hat 
   * genau zwei Eintraege mit den Indexwerten 0 und 1.
   */
  public Vertex[] getVertices(){
    Vertex[] result = new Vertex[2];
    result[0] = vertices[0]; 
    result[1] = vertices[1];
    return result;
  }
  
  /**
   * Der Auftrag setzt das Gewicht der Kante auf pWeight.
   */
  public void setWeight(double pWeight){
    weight = pWeight;
  } 
  
  /**
   * Die Anfrage liefert das Gewicht der Kante als double.
   */
  public double getWeight(){
    return weight;
  } 

  /**
   * Der Auftrag setzt die Markierung der Kante auf pMark.
   */
  public void setMark(boolean pMark){
    mark = pMark;
  }

  /**
   * Die Anfrage liefert true, wenn die Kante mit true markiert ist, ansonsten false.
   */
  public boolean isMarked(){
    return mark;
  }

}
