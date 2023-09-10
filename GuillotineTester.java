
/**
 * A class that tests the methods of Guillotine, Card, SpecialCard, and LinkedList
 * @author Ethan Tobey
 */
import org.junit.*;
import static org.junit.Assert.*;
import java.lang.Integer;
import java.lang.Math;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.scene.Node;

public class GuillotineTester {
  
  /**
   * Tests the takeCard method of Guillotine
   */
  @Test
  public void testTakeCard() {
    Guillotine game = new Guillotine();
    Card[] x = {
    new Card("King Louis XVI", "Royal", 5),
    new Card("Marie Antoinette", "Royal", 5),
    new Card("Regent", "Royal", 4),
    new Card("Duke", "Royal", 3),
    new Card("Baron", "Royal", 3),
    new SpecialCard("Count", "Royal"),
    new SpecialCard("Countess", "Royal"),
    new SpecialCard("Lord", "Royal"),
    new SpecialCard("Lady", "Royal"),
    new Card("Cardinal", "Church", 5),
    };
    for (int i = 0; i < x.length; i++)
      game.getTableCards().addToFront(x[i]);
    //Test player 1
    Card c = (Card)game.getTableCards().getFirstNode().getElement();
    Card s = (Card)game.getTableCards().removeFromFront();
    assertEquals(c, s);
    //Test player 2
    game.setPlayer1Turn(false);
    Card z = (Card)game.getTableCards().getFirstNode().getElement();
    Card y = (Card)game.getTableCards().removeFromFront();
    assertEquals(z,y);
  }
  
  /**
   * Tests the endTurn method of Guillotine
   */
  @Test
  public void testEndTurn() {
    //Test player 1 inactive when p2's turn
    Guillotine game = new Guillotine();
    game.endTurn();
    for (int i = 0; i > game.getP1ButtonsActive().length; i++)
      assertEquals(false, game.getP1ButtonsActive()[i]);
  //Test player 2 inactive when p1's turn
    game.endTurn();
    for (int i = 0; i > game.getP2ButtonsActive().length; i++)
      assertEquals(false, game.getP2ButtonsActive()[i]);
  }
  
  /**
   * Tests the updateGUI method of Guillotine
   */
  @Test
  public void testUpdateGUI() {
    Guillotine game = new Guillotine();
    Card[] x = {};
    for (int i = 0; i < x.length; i++)
      game.getTableCards().addToFront(x[i]);
    game.updateGUI();
    //test 0
    assertEquals("[]", game.getTableCardsGUI().getChildren().toString());
    //test 1
    Guillotine game2 = new Guillotine();
    Card[] y = {new Card("King Louis XVI", "Royal", 5)};
    for (int i = 0; i < x.length; i++)
      game2.getTableCards().addToFront(x[i]);
    game2.updateGUI();
    assertEquals("[]",game2.getTableCardsGUI().getChildren().toString());
    //test many
    Guillotine game3 = new Guillotine();
    Card[] z = {
      new Card("King Louis XVI", "Royal", 5), 
      new Card("Marie Antoinette", "Royal", 5),
      new Card("Regent", "Royal", 4),
      new Card("Duke", "Royal", 3),
      new Card("Baron", "Royal", 3),
      new SpecialCard("Count", "Royal"),
      new SpecialCard("Countess", "Royal"),
      new SpecialCard("Lord", "Royal"),
      new SpecialCard("Lady", "Royal"),
      new Card("Cardinal", "Church", 5)};
    for (int i = 0; i < x.length; i++)
      game3.getTableCards().addToFront(x[i]);
    game3.updateGUI();
    assertEquals("[]",game3.getTableCardsGUI().getChildren().toString());
    //test first
    Guillotine game4 = new Guillotine();
    Card[] a = {new SpecialCard("Lady", "Royal")};
    for (int i = 0; i < x.length; i++)
      game4.getTableCards().addToFront(x[i]);
    game4.updateGUI();
    assertEquals("[]",game4.getTableCardsGUI().getChildren().toString());
    //test last
    Guillotine game5 = new Guillotine();
    Card[] b = {new Card("King Louis XVI", "Royal", 5), new SpecialCard("Lady", "Royal")};
    for (int i = 0; i < x.length; i++)
      game5.getTableCards().addToFront(x[i]);
    game5.updateGUI();
    assertEquals("[]",game5.getTableCardsGUI().getChildren().toString());
    //test middle
    Guillotine game6 = new Guillotine();
    Card[] c = {new Card("King Louis XVI", "Royal", 5), new SpecialCard("Lady", "Royal"),
    new Card("King Louis XVI", "Royal", 5)};
    for (int i = 0; i < x.length; i++)
      game6.getTableCards().addToFront(x[i]);
    game6.updateGUI();
    assertEquals("[]",game6.getTableCardsGUI().getChildren().toString());
  }
  
  /**
   * Tests the updateSpecialPoints method of Guillotine
   */
  @Test
  public void testUpdateSpecialPoints() {
    Guillotine game = new Guillotine();
    Card[] x = {};
    Card c = new SpecialCard("Lady", "Royal");
    //test 0
    game.updateSpecialPoints(x, c);
    assertEquals(2, c.getPoints());
    //test 1
    Card[] y = {new SpecialCard("Lord", "Royal")};
    game.updateSpecialPoints(y, c);
    assertEquals(4, y[0].getPoints());
    //test many
    Card[] z = { new Card("King Louis XVI", "Royal", 5), 
      new Card("Marie Antoinette", "Royal", 5),
      new Card("Regent", "Royal", 4),
      new Card("Duke", "Royal", 3),
      new Card("Baron", "Royal", 3),
      new SpecialCard("Lord", "Royal")};
    game.updateSpecialPoints(z, c);
    assertEquals(4, z[5].getPoints());
    //Test first
    Card[] q = {new SpecialCard("Lady", "Royal")};
    game.updateSpecialPoints(q, c);
    assertEquals(2, q[0].getPoints());
    //test last
    Card[] w = {new Card("King Louis XVI", "Royal", 5), 
      new Card("Marie Antoinette", "Royal", 5),
      new Card("Regent", "Royal", 4),
      new Card("Duke", "Royal", 3),
      new Card("Baron", "Royal", 3),
      new SpecialCard("Lady", "Royal")};
    game.updateSpecialPoints(z, c);
    assertEquals(2, w[5].getPoints());
    //test middle
    Card[] e = { new Card("King Louis XVI", "Royal", 5), 
      new Card("Marie Antoinette", "Royal", 5),
      new Card("Regent", "Royal", 4),
      new Card("Duke", "Royal", 3),
      new SpecialCard("Lady", "Royal"),
      new Card("Baron", "Royal", 3),};
    game.updateSpecialPoints(e, c);
    assertEquals(2, e[4].getPoints());
  }
  
  /**
   * Tests the updatePlayerScore method of Guillotine
   */
  @Test
  public void testUpdatePlayerScore() {
    Guillotine game = new Guillotine();
    //Test 0
    Card[] x = {};
    game.updatePlayerScore(x);
    assertEquals(0, game.getP1Score());
    //Test 1
    Card[] y = {new Card("King Louis XVI", "Royal", 5)};
    game.updatePlayerScore(y);
    assertEquals(5, game.getP1Score());
    //Test many
    Card[] z= {new Card("King Louis XVI", "Royal", 5), 
      new Card("Marie Antoinette", "Royal", 5),
      new Card("Regent", "Royal", 4),
      new Card("Duke", "Royal", 3),
      new Card("Baron", "Royal", 3)};
    game.updatePlayerScore(z);
    assertEquals(20, game.getP1Score());
    //test first
    Card[] a = {new SpecialCard("Lord", "Royal")};
    game.updatePlayerScore(a);
    assertEquals(2, game.getP1Score());
    //test last
    Card[] b = {new Card("King Louis XVI", "Royal", 5), 
      new SpecialCard("Lord", "Royal")};
    game.updatePlayerScore(b);
    assertEquals(7, game.getP1Score());
    //test middle
    Card[] c = {new Card("King Louis XVI", "Royal", 5), 
      new SpecialCard("Lord", "Royal"),
      new Card("Regent", "Royal", 4)};
    game.updatePlayerScore(c);
    assertEquals(11, game.getP1Score());
  }
  
  /**
   * Tests the byteContains method of Guillotine
   */
  @Test
  public void testByteContains() {
    Guillotine game = new Guillotine();
    byte b = 5;
    //test 0
    byte[] x = {};
    assertEquals(false, game.byteContains(x, b));
    //test 1
    byte[] y = {5};
    assertEquals(true, game.byteContains(y, b));
    //test many
    byte[] z = {5,5,5,5,5};
    assertEquals(true, game.byteContains(z, b));
    //test first
    byte[] q = {5,2,3,4,1};
    assertEquals(true, game.byteContains(q, b));
    //test last
    byte[] w = {1,2,3,4,5};
    assertEquals(true, game.byteContains(w, b));
    //test middle
    byte[] e = {1,2,5,3,4};
    assertEquals(true, game.byteContains(e, b));
  }
  
  /**
   * Tests all getter / setter methods in Guillotine
   */
  @Test
  public void testGuillotineGetterSetter() {
    Guillotine game = new Guillotine();
    //test getDeckSize
    assertEquals(20, game.getDeckSize());
    //test setDeckSize
    game.setDeckSize(10);
    assertEquals(10, game.getDeckSize());
    //test setDeck
    Card[] deck = {};
    game.setDeck(deck);
    assertEquals(deck, game.getDeck());
    //test getDeck
    Card[] deck2 = {};
    game.setDeck(deck2);
    assertEquals(deck2, game.getDeck());
    //test setTableCards
    LinkedList t = new LinkedList();
    game.setTableCards(t);
    assertEquals(t, game.getTableCards());
    //test getTableCards
    assertEquals(t, game.getTableCards());
    //test getP1Cards
    assertEquals(null, game.getP1Cards());
    //test setP1Cards
    Card[] cards = {};
    game.setP1Cards(cards);
    assertEquals(cards, game.getP1Cards());
    //test getP2Cards
    assertEquals(null, game.getP2Cards());
    //test setP2Cards
    game.setP2Cards(cards);
    assertEquals(cards, game.getP2Cards());
    //test getPlayer1Turn
    assertEquals(true, game.getPlayer1Turn());
    //test setPlayer1Turn
    game.setPlayer1Turn(false);
    assertEquals(false, game.getPlayer1Turn());
    //test getP1Index
    assertEquals(0, game.getP1Index());
    //test setP1Index
    game.setP1Index(1);
    assertEquals(1, game.getP1Index());
    //test getP2Index
    assertEquals(0, game.getP2Index());
    //test setP2Index
    game.setP2Index(1);
    assertEquals(1, game.getP2Index());
    //test getP1Score
    assertEquals(0, game.getP1Score());
    //test setP1Score
    game.setP1Score(1);
    assertEquals(1, game.getP1Score());
    //test getP2Score
    assertEquals(0, game.getP2Score());
    //test setP2Score
    game.setP2Score(1);
    assertEquals(1, game.getP2Score());
    //test getP1Buttons
    assertEquals(null, game.getP1Buttons()[0]);
    //test getP1ButtonsActive
    assertEquals(true, game.getP1ButtonsActive()[0]);
    //test getP2Buttons
    assertEquals(null, game.getP2Buttons()[0]);
    //test getP2ButtonsActive
    assertEquals(true, game.getP2ButtonsActive()[0]);
    //test getP1ScoreLabel
    assertEquals(null, game.getP1ScoreLabel());
    //test getP2ScoreLabel
    assertEquals(null, game.getP2ScoreLabel());
    //test getP1GUI
    assertTrue(game.getP1GUI() instanceof VBox);
    //test getP2GUI
    assertTrue(game.getP2GUI() instanceof VBox);
    //test getTableCardsGUI
    assertTrue(game.getTableCardsGUI() instanceof VBox);
  }
  
  /**
   * Tests the moveBack method of LinkedList
   */
  @Test
  public void testMoveBack() {
    LinkedList l = new LinkedList();
    //test 0
    l.moveBack(1);
    assertEquals(null, l.getFirstNode());
    //test 1
    Integer x = new Integer(5);
    l.addToFront(x);
    l.moveBack(1);
    assertEquals(x, l.getFirstNode().getElement());
    //test many
    Integer y = new Integer(3);
    l.addToFront(y);
    Integer z = new Integer(2);
    l.addToFront(z);
    Integer a = new Integer(12);
    l.addToFront(a);
    l.moveBack(2);
    assertEquals(a, l.getFirstNode().getNext().getNext().getElement());
    //test first
    l.moveBack(1);
    assertEquals(y, l.getFirstNode().getElement());
    //test last
    Integer b = new Integer(1);
    l.addToFront(b);
    l.moveBack(l.length() - 1);
    assertEquals(b, l.getFirstNode().getNext().getNext().getNext().getNext().getElement());
    //test middle
    Integer c = new Integer(7);
    l.addToFront(c);
    l.moveBack(3);
    assertEquals(c, l.getFirstNode().getNext().getNext().getNext().getElement());
  }
  
  /**
   * Tests moveFirstToLast method of LinkedList
   */
  @Test
  public void testMoveFirstToLast() {
    LinkedList l = new LinkedList();
    //test 0
    assertEquals(null, l.getFirstNode());
    //test 1
    Integer x = new Integer(1);
    l.addToFront(x);
    l.moveFirstToLast();
    assertEquals(x, l.getFirstNode().getElement());
    //test many
    Integer y = new Integer(2);
    l.addToFront(y);
    Integer z = new Integer(3);
    l.addToFront(z);
    Integer a = new Integer(4);
    l.addToFront(a);
    l.moveFirstToLast();
    assertEquals(a, l.getFirstNode().getNext().getNext().getNext().getElement());
    //test first
    Integer b = new Integer(200);
    l.addToFront(b);
    l.moveFirstToLast();
    assertEquals(b, l.getFirstNode().getNext().getNext().getNext().getNext().getElement());
    //test last
    Double c = new Double(1.2);
    l.addToFront(c);
    l.moveFirstToLast();
    assertEquals(c,l.getFirstNode().getNext().getNext().getNext().getNext().getNext().getElement());
    //test middle
    assertEquals(z, l.getFirstNode().getElement());
  }
  
  /**
   * tests moveLastToFirst method of LinkedList
    */
  @Test
  public void testMoveLastToFirst() {
    LinkedList l = new LinkedList();
    //test 0
    l.moveLastToFirst();
    assertEquals(null, l.getFirstNode());
    //test 1
    Integer x = new Integer(1);
    l.addToFront(x);
    l.moveLastToFirst();
    assertEquals(x, l.getFirstNode().getElement());
    //test many
    Integer y = new Integer(2);
    l.addToFront(y);
    Integer z = new Integer(3);
    l.addToFront(z);
    l.moveLastToFirst();
    assertEquals(x, l.getFirstNode().getElement());
    //test first
    Integer a = new Integer(-21);
    l.addToFront(a);
    l.moveLastToFirst();
    assertEquals(y, l.getFirstNode().getElement());
    //test last
    Integer b = new Integer(200);
    l.addToEnd(b);
    l.moveLastToFirst();
    assertEquals(b, l.getFirstNode().getElement());
    //test middle
    l.moveBack(2);
    l.moveLastToFirst();
    assertEquals(z, l.getFirstNode().getElement());
  }
  
  /**
   * tests reverseList method of LinkedList
   */
  @Test
  public void testReverseList() {
    LinkedList l = new LinkedList();
    LinkedList comparison = new LinkedList();
    //test 0
    l.reverseList();
    assertEquals(null, l.getFirstNode());
    //test 1
    Integer x = new Integer(1);
    l.addToFront(x);
    comparison.addToEnd(x);
    l.reverseList();
    assertEquals(x, l.getFirstNode().getElement());
    //test many
    Integer y = new Integer(2);
    l.addToFront(y);
    comparison.addToEnd(y);
    Integer z = new Integer(3);
    l.addToFront(z);
    comparison.addToEnd(z);
    l.reverseList();
    assertEquals(comparison.getFirstNode().getElement(), l.getFirstNode().getElement());
    assertEquals(comparison.getFirstNode().getNext().getElement(), 
                 l.getFirstNode().getNext().getElement());
    assertEquals(comparison.getFirstNode().getNext().getNext().getElement(), 
                 l.getFirstNode().getNext().getNext().getElement());
    //test first
    assertEquals(comparison.getFirstNode().getElement(), l.getFirstNode().getElement());
    //test last
    assertEquals(comparison.getFirstNode().getNext().getNext().getElement(), 
                 l.getFirstNode().getNext().getNext().getElement());
    //test middle
     assertEquals(comparison.getFirstNode().getNext().getElement(), 
                 l.getFirstNode().getNext().getElement());
  }
  
  /**
   * tests reverseFirstK method of LinkedList
   */
  @Test
  public void testReverseFirstK() {
    LinkedList l = new LinkedList();
    LinkedList comparison = new LinkedList();
    LinkedList original = new LinkedList();
    //test 0
    l.reverseFirstK(0);
    assertEquals(null, l.getFirstNode());
    //test 1
    Integer x = new Integer(1);
    l.addToFront(x);
    comparison.addToEnd(x);
    l.reverseFirstK(1);
    assertEquals(x, l.getFirstNode().getElement());
    //test many
    Integer y = new Integer(2);
    l.addToFront(y);
    comparison.addToEnd(y);
    Integer z = new Integer(3);
    l.addToFront(z);
    comparison.addToEnd(z);
    original = l;
    l.reverseFirstK(3);
    assertEquals(comparison.getFirstNode().getElement(), l.getFirstNode().getElement());
    assertEquals(comparison.getFirstNode().getNext().getElement(), 
                 l.getFirstNode().getNext().getElement());
    assertEquals(comparison.getFirstNode().getNext().getNext().getElement(), 
                 l.getFirstNode().getNext().getNext().getElement());
    //test first   - k = 1
    l.reverseFirstK(1);
    assertEquals(comparison.getFirstNode().getElement(), l.getFirstNode().getElement());
    //test last - k = linked list length
    l.reverseFirstK(3);
    assertEquals(original.getFirstNode().getElement(), l.getFirstNode().getElement());
    assertEquals(original.getFirstNode().getNext().getElement(), 
                 l.getFirstNode().getNext().getElement());
    assertEquals(original.getFirstNode().getNext().getNext().getElement(), 
                 l.getFirstNode().getNext().getNext().getElement());
    //test middle - k is somewhere in middle
    l.reverseFirstK(2);
    assertEquals(y, l.getFirstNode().getElement());
  }
  
  /**
   * Tests updatePoints method of SpecialCard class
   */
  @Test
  public void testUpdatePoints() {
    //test count
    SpecialCard count = new SpecialCard("Count", "");
    count.updatePoints(new SpecialCard("Countess", ""));
    assertEquals(4, count.getPoints());
    //test countess
    SpecialCard countess = new SpecialCard("Countess", "");
    countess.updatePoints(new SpecialCard("Count", ""));
    assertEquals(4, countess.getPoints());
    //test lord
    SpecialCard lord = new SpecialCard("Lord", "");
    lord.updatePoints(new SpecialCard("Lady", ""));
    assertEquals(4, lord.getPoints());
    //test lady
    SpecialCard lady = new SpecialCard("Lady", "");
    lady.updatePoints(new SpecialCard("Lord", ""));
    assertEquals(4, lady.getPoints());
    //test heretic
    SpecialCard heretic = new SpecialCard("Heretic", "");
    heretic.updatePoints(new Card("", "Church", 2));
    assertEquals(1, heretic.getPoints());
    //test tax collector
    SpecialCard taxCollector = new SpecialCard("Tax Collector", "");
    taxCollector.updatePoints(new Card("", "Civic", 2));
    assertEquals(1, taxCollector.getPoints());
    //test palace guard
    SpecialCard guard = new SpecialCard("Palace Guard", "");
    guard.updatePoints(new SpecialCard("Palace Guard", ""));
    assertEquals(1, guard.getPoints());
    //test tragic figure
    SpecialCard figure = new SpecialCard("Tragic Figure", "");
    figure.updatePoints(new Card("", "Commoner", 2));
    assertEquals(-1, figure.getPoints());
  }
  
  /**
   * Tests all getter / setter methods of Card class
   */
  @Test
  public void testCardGetterSetter() {
    Card c = new Card("name", "type", 1);
    //test getName
    assertEquals("name", c.getName());
    //test setName
    c.setName("dude");
    assertEquals("dude", c.getName());
    //test getType
    assertEquals("type", c.getType());
    //test setType
    c.setType("dangerous");
    assertEquals("dangerous", c.getType());
    //test getPoints
    assertEquals(1, c.getPoints());
    //test setPoints
    c.setPoints(2);
    assertEquals(2, c.getPoints());
  }
}
