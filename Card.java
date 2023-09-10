/**
 * Class to represent a Card
 * @author Ethan Tobey
 */
public class Card {
  
  /** stores the name of the card */
  private String name;
  /** stores the type of the card */
  private String type;
  /** stores the points value of the card */
  private int points;
  
  /**
   * the constructor
   * @param name  the name of the card
   * @param type  the type of the card
   * @param points  the points value of the card
   */
  public Card(String name, String type, int points) {
    this.name = name;
    this.type = type;
    this.points = points;
  }
  
  /**
   * returns name of the card
   * @return name of card
   */
  public String getName() {
    return this.name;
  }
  
  /**
   * returns type of the card
   * @return type of card
   */
  public String getType() {
    return this.type;
  }
  
  /**
   * returns points vlaue of the card
   * @return points value of card
   */
  public int getPoints() {
    return this.points;
  }
  
  /**
   * changes the name of the card
   * @param name  new name for card
   */
  public void setName(String name) {
    this.name = name;
  }
  
  /**
   * changes the type of the card
   * @param type  new type of the card
   */
  public void setType(String type) {
    this.type = type;
  }
  
  /**
   * changes the points value of the card
   * @param points  new points value of the card
   */
  public void setPoints(int points) {
    this.points = points;
  }
}