package graph;

import java.io.Serializable;

/**
 * <p>
 * Materialien zu den zentralen NRW-Abiturpruefungen im Fach Informatik (Vorschlag)
 * </p>
 * <p>
 * Klasse Vertex
 * </p>
 * <p>
 * Die Klasse Vertex stellt einen einzelnen Knoten eines Graphen dar. Jedes Objekt 
 * dieser Klasse verfuegt ueber eine im Graphen eindeutige ID als String und kann diese 
 * ID zurueckliefern. Darueber hinaus kann eine Markierung gesetzt und abgefragt werden.
 * </p>
 * 
 * @author Volker Quade
 * @version April 2015
 */
@SuppressWarnings("serial")
public class Vertex implements Serializable{
  //Einmalige ID des Knotens, Inhaltsobjekt und Markierung
  private String id;
  private boolean mark;

  /**
   * Ein neues, unmarkiertes Objekt vom Typ Vertex ohne Inhaltsobjekt wird erstellt. Der von diesem Objekt
   * repraesentierte Knoten ist noch in keinen Graphen eingefuegt. 
   */
  public Vertex(String pID){
    id = pID;
    mark = false;
  }

  /**
   * Die Anfrage liefert die ID des Knotens als String.
   */
  public String getID(){
    return new String(id);
  }

  /**
   * Der Auftrag setzt die Markierung des Knotens auf pMark.
   */
  public void setMark(boolean pMark){
    mark = pMark;
  }

  /**
   * Die Anfrage liefert true, wenn der Knoten mit true markiert ist, ansonsten false.
   */
  public boolean isMarked(){
    return mark;
  }

}
