/**
 * Class to represent cards of variable point values
 * @author Ethan Tobey
 */
public class SpecialCard extends Card {
  
  /**
   * The constructor. Sets initial points value based on what card it is.
   * @param name  the name of the card
   * @param type  the type of the card
   */
  public SpecialCard(String name, String type) {
    super(name, type, 0);
    if (this.getName().equals("Count") || this.getName().equals("Countess") ||
        this.getName().equals("Lord") || this.getName().equals("Lady"))
      this.setPoints(2);
  }
  
  /**
   * updates points value of card depending on cards' points condition
   * @param cardCollected  the card most recently picked up from table by player
   */
  public void updatePoints(Card cardCollected) {
    if (this.getName().equals("Count")) {
      if (cardCollected.getName().equals("Countess"))
        this.setPoints(4);
    }
    if (this.getName().equals("Countess")) {
      if (cardCollected.getName().equals("Count"))
        this.setPoints(4);
    }
    if (this.getName().equals("Lord")) {
      if (cardCollected.getName().equals("Lady"))
        this.setPoints(4);
    }
    if (this.getName().equals("Lady")) {
      if (cardCollected.getName().equals("Lord"))
        this.setPoints(4);
    }
    if (this.getName().equals("Heretic")) {
      if (cardCollected.getType().equals("Church"))
        this.setPoints(this.getPoints() + 1);
    }
    if (this.getName().equals("Tax Collector")) {
      if (cardCollected.getType().equals("Civic"))
        this.setPoints(this.getPoints() + 1);
    }
    if (this.getName().equals("Palace Guard")) {
      if (cardCollected.getName().equals("Palace Guard"))
        this.setPoints(this.getPoints() + 1);
    }
    if (this.getName().equals("Tragic Figure")) {
      if (cardCollected.getType().equals("Commoner"))
        this.setPoints(this.getPoints() - 1);
    }
  }
}