/**
 * Class of the game application
 * @author Ethan Tobey
 */
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

public class Guillotine extends Application { 
  /** number of cards to be used in game - default of 20 */
  private static int deckSize = 20;
  /** an array of all the cards in the game */
  private Card[] deck = {
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
    new Card("Archbishop", "Church", 4),
    new Card("Nun", "Church", 3),
    new Card("Bishop", "Church", 2),
    new Card("Priest", "Church", 1),
    new Card("Priest", "Church", 1),
    new SpecialCard("Heretic", "Church"),
    new Card("Governor", "Civic", 4),
    new Card("Mayor", "Civic", 3),
    new Card("Councilman", "Civic", 3),
    new Card("Judge", "Civic", 2),
    new Card("Judge", "Civic", 2),
    new SpecialCard("Tax Collector", "Civic"),
    new Card("Sheriff", "Civic", 1),
    new Card("Sheriff", "Civic", 1),
    new SpecialCard("Palace Guard", "Military"),
    new SpecialCard("Palace Guard", "Military"),
    new SpecialCard("Palace Guard", "Military"),
    new SpecialCard("Palace Guard", "Military"),
    new SpecialCard("Palace Guard", "Military"),
    new Card("General", "Military", 4),
    new Card("Colonel", "Military", 3),
    new Card("Captain", "Military", 2),
    new Card("Lieutenant", "Military", 1),
    new Card("Lieutenant", "Military", 1),
    new SpecialCard("Tragic Figure", "Commoner"),
    new Card("Heroic Figure", "Commoner", -3),
    new Card("Student", "Commoner", -1),
    new Card("Student", "Commoner", -1),
    new Card("Student", "Commoner", -1),
    new Card("Student", "Commoner", -1),
  };
  /** linked list of cards on the table */
  private LinkedList tableCards = new LinkedList<Card>();
  /** array of player 1 cards */
  private Card[] p1Cards = null;
  /** tracks where to add next card in p1Cards */
  private int p1Index = 0;
  /** array of player 2 cards */
  private Card[] p2Cards = null;
  /** tracks where to add next card in p2Cards */
  private int p2Index = 0;
  /** tracks which player's turn it is */
  private boolean player1Turn = true;
  /** tracks score of player 1 */
  private int p1Score = 0;
  /** tracks score of player 2 */
  private int p2Score = 0; 
  /** array of all p1Buttons*/
  private Button[] p1Buttons = new Button[10];
  /** array that tracks which p1 buttons have been pressed*/ 
  private boolean[] p1ButtonsActive = {true, true, true, true, true, true, true, true, true};
  /** array of all p2Buttons*/
  private Button[] p2Buttons = new Button[10];
  /** array that tracks which p2 buttons have been pressed */
  private boolean[] p2ButtonsActive = {true, true, true, true, true, true, true, true, true};
  /** label of score for player 1*/
  private Label p1ScoreLabel;
  /** label of score for player 2*/
  private Label p2ScoreLabel;
  /** VBox of Player 1 GUI */
  private VBox p1GUI = new VBox(10);
  /** VBox of Player 2 GUI */
  private VBox p2GUI = new VBox(10);
  /** VBox of the tableCards GUI */
  private VBox tableCardsGUI = new VBox(2);
  
  /**
   * start method of the application
   * @param primaryStage  the stage of the application
   */
  public void start(Stage primaryStage) {
    this.shuffleCards(this.getTableCards());
    this.setP1Cards(new Card[this.getDeckSize()]);
    this.setP2Cards(new Card[this.getDeckSize()]);
  //  LinkedList.printLinkedList(this.getTableCards());  //DEBUG
    
    //tilepane that contains the players and cards on the game display
    TilePane layout = new TilePane();
    layout.setPrefColumns(3);
    layout.setPrefHeight(900);
    layout.setTileAlignment(Pos.TOP_CENTER); 
    this.getP1GUI().setAlignment(Pos.TOP_LEFT);
    this.getP2GUI().setAlignment(Pos.TOP_RIGHT);    
    this.getTableCardsGUI().setAlignment(Pos.TOP_CENTER);
    p1ScoreLabel =  new Label("P1 Score: " + this.getP1Score());
    p2ScoreLabel =  new Label("P2 Score: " + this.getP2Score());
    p1ScoreLabel.setFont(new Font(15));
    p2ScoreLabel.setFont(new Font(15));
    
    //MAKE THE BUTTONS - Player 1
    Button p1MoveBack4 = new Button("4 Space Back");  //button to move first card back 4
    p1MoveBack4.setPrefSize(130, 15);
    p1MoveBack4.setOnAction(e -> {
      this.getTableCards().moveBack(4);
      this.getP1ButtonsActive()[0] = false;
      p1MoveBack4.setDisable(true);
      this.updateGUI();
    });
    Button p1MoveBack3 = new Button("3 Space Back");  //button to move first card back 3
    p1MoveBack3.setPrefSize(130, 15);
    p1MoveBack3.setOnAction(e -> {
      this.getTableCards().moveBack(3);
      this.getP1ButtonsActive()[1] = false;
      p1MoveBack3.setDisable(true);
      this.updateGUI();
    });
    Button p1MoveBack2 = new Button("2 Space Back");  //button to move first card back 2
    p1MoveBack2.setPrefSize(130, 15);
    p1MoveBack2.setOnAction(e -> {
      this.getTableCards().moveBack(2);
      this.getP1ButtonsActive()[2] = false;
      p1MoveBack2.setDisable(true);
      this.updateGUI();
    });
    Button p1MoveBack1 = new Button("1 Space Back");  //button to move first card back 1
    p1MoveBack1.setPrefSize(130, 15);
    p1MoveBack1.setOnAction(e -> {
      this.getTableCards().moveBack(1);
      this.getP1ButtonsActive()[3] = false;
      p1MoveBack1.setDisable(true);
      this.updateGUI();
    });
    Button p1FrontToBack = new Button("Move Front To Back");  //button to move first card to back
    p1FrontToBack.setPrefSize(130, 15);
    p1FrontToBack.setOnAction(e -> {
      this.getTableCards().moveFirstToLast();
      this.getP1ButtonsActive()[4] = false;
      p1FrontToBack.setDisable(true);
      this.updateGUI();
    });
    Button p1LastToFront = new Button("Move Last To Front");  //button to move last card to front
    p1LastToFront.setPrefSize(130, 15);
    p1LastToFront.setOnAction(e -> {
      this.getTableCards().moveLastToFirst();
      this.getP1ButtonsActive()[5] = false;
      p1LastToFront.setDisable(true);
      this.updateGUI();
    });
    Button p1Reverse = new Button("Reverse Card Order");      //button to reverse order of cards
    p1Reverse.setPrefSize(130, 15);
    p1Reverse.setOnAction(e -> {
      this.getTableCards().reverseList();
      this.getP1ButtonsActive()[6] = false;
      p1Reverse.setDisable(true);
      this.updateGUI();
    });
    Button p1ReverseFirst5 = new Button("Reverse First 5 Cards"); //button to reverse first 5 cards
    p1ReverseFirst5.setPrefSize(130, 15);
    p1ReverseFirst5.setOnAction(e -> {
      this.getTableCards().reverseFirstK(5);
      this.getP1ButtonsActive()[7] = false;
      p1ReverseFirst5.setDisable(true);
      this.updateGUI();
    });
    Button p1SkipTurn = new Button("Skip turn");  //button to skip player's turn
    p1SkipTurn.setPrefSize(130, 15);
    p1SkipTurn.setOnAction(e -> {
      this.endTurn();
      this.getP1ButtonsActive()[8] = false;
      p1SkipTurn.setDisable(true);
    });
    Button p1TakeCard = new Button("Take Card");  //button for player one to take a card
    p1TakeCard.setPrefSize(130, 15);
    p1TakeCard.setOnAction(e -> this.takeCard());
    
    this.getP1GUI().getChildren().addAll(this.getP1ScoreLabel(), p1MoveBack4, p1MoveBack3, p1MoveBack2, p1MoveBack1, p1FrontToBack,
                             p1LastToFront, p1Reverse, p1ReverseFirst5, p1SkipTurn, p1TakeCard);
    int p1ButtonIndex = 0; //index for the loop
    //assigns all buttons for P1 to p1Buttons array
    for (Node node : this.getP1GUI().getChildren()) {
      if (node instanceof Button) {
        this.getP1Buttons()[p1ButtonIndex] = (Button)node;
        p1ButtonIndex++;
      }
    }
     
    // PLAYER 2 BUTTONS
    Button p2MoveBack4 = new Button("4 Space Back");  //button to move first card back 4
    p2MoveBack4.setDisable(true);
    p2MoveBack4.setPrefSize(130, 15);
    p2MoveBack4.setOnAction(e -> {
      this.getTableCards().moveBack(4);
      this.getP2ButtonsActive()[0] = false;
      p2MoveBack4.setDisable(true);
      this.updateGUI();
    });
    Button p2MoveBack3 = new Button("3 Space Back");  //button to move first card back 3
    p2MoveBack3.setDisable(true);
    p2MoveBack3.setPrefSize(130, 15);
    p2MoveBack3.setOnAction(e -> {
      this.getTableCards().moveBack(3);
      this.getP2ButtonsActive()[1] = false;
      p2MoveBack3.setDisable(true);
      this.updateGUI();
    });
    Button p2MoveBack2 = new Button("2 Space Back");  //button to move first card back 2
    p2MoveBack2.setDisable(true);
    p2MoveBack2.setPrefSize(130, 15);
    p2MoveBack2.setOnAction(e -> {
      this.getTableCards().moveBack(2);
      this.getP2ButtonsActive()[2] = false;
      p2MoveBack2.setDisable(true);
      this.updateGUI();
    });
    Button p2MoveBack1 = new Button("1 Space Back");  //button to move first card back 1
    p2MoveBack1.setDisable(true);
    p2MoveBack1.setPrefSize(130, 15);
    p2MoveBack1.setOnAction(e -> {
      this.getTableCards().moveBack(1);
      this.getP2ButtonsActive()[3] = false;
      p2MoveBack1.setDisable(true);
      this.updateGUI();
    });
    Button p2FrontToBack = new Button("Move Front To Back");  //button to move first card to back
    p2FrontToBack.setDisable(true);
    p2FrontToBack.setPrefSize(130, 15);
    p2FrontToBack.setOnAction(e -> {
      this.getTableCards().moveFirstToLast();
      this.getP2ButtonsActive()[4] = false;
      p2FrontToBack.setDisable(true);
      this.updateGUI();
    });
    Button p2LastToFront = new Button("Move Last To Front");  //button to move last card to front
    p2LastToFront.setDisable(true);
    p2LastToFront.setPrefSize(130, 15);
    p2LastToFront.setOnAction(e -> {
      this.getTableCards().moveLastToFirst();
      this.getP2ButtonsActive()[5] = false;
      p2LastToFront.setDisable(true);
      this.updateGUI();
    });
    Button p2Reverse = new Button("Reverse Card Order");      //button to reverse order of cards
    p2Reverse.setDisable(true);
    p2Reverse.setPrefSize(130, 15);
    p2Reverse.setOnAction(e -> {
      this.getTableCards().reverseList();
      this.getP2ButtonsActive()[6] = false;
      p2Reverse.setDisable(true);
      this.updateGUI();
    });
    Button p2ReverseFirst5 = new Button("Reverse First 5 Cards"); //button to reverse first 5 cards
    p2ReverseFirst5.setDisable(true);
    p2ReverseFirst5.setPrefSize(130, 15);
    p2ReverseFirst5.setOnAction(e -> {
      this.getTableCards().reverseFirstK(5);
      this.getP2ButtonsActive()[7] = false;
      p2ReverseFirst5.setDisable(true);
      this.updateGUI();
    });
    Button p2SkipTurn = new Button("Skip turn");  //button to skip player's turn
    p2SkipTurn.setDisable(true);
    p2SkipTurn.setPrefSize(130, 15);
    p2SkipTurn.setOnAction(e -> {
      this.endTurn();
      this.getP2ButtonsActive()[8] = false;
      p2SkipTurn.setDisable(true);
    });
    Button p2TakeCard = new Button("Take Card");  //button for player one to take a card
    p2TakeCard.setDisable(true);
    p2TakeCard.setPrefSize(130, 15);
    p2TakeCard.setOnAction(e -> this.takeCard());
    
    this.getP2GUI().getChildren().addAll(this.getP2ScoreLabel(), p2MoveBack4, p2MoveBack3, p2MoveBack2, p2MoveBack1, p2FrontToBack,
                             p2LastToFront, p2Reverse, p2ReverseFirst5, p2SkipTurn, p2TakeCard);
    int p2ButtonIndex = 0; //index for the loop
    //assigns all buttons for P1 to p2Buttons array
    for (Node node : this.getP2GUI().getChildren()) {
      if (node instanceof Button) {
        this.getP2Buttons()[p2ButtonIndex] = (Button)node;
        p2ButtonIndex++;
      }
    }
    this.updateGUI(); 
    
    layout.getChildren().addAll(this.getP1GUI(), this.getTableCardsGUI(), this.getP2GUI()); 
    // the scene of the game
    Scene scene = new Scene(layout);
    primaryStage.setScene(scene);
    primaryStage.setTitle("Guillotine");
    primaryStage.show();
  }
  
  /**
   * main method of the application
   * @param args  string array of inputs to main method
   */
  public static void main(String[] args) {
    if (args.length > 0) {
      if (Integer.parseInt(args[0]) >= 2 && Integer.parseInt(args[0]) <= 39)
        Guillotine.setDeckSize(Integer.parseInt(args[0]));
      else
        Guillotine.setDeckSize(20); 
    } 
    Application.launch(args);
  }
  
  /**
   * Takes first card from tableCards and moves it to player1 or 2
   * Ends player's turn
   */
  public void takeCard() {
    //move first card from tableCards to p1Cards
    if (this.getTableCards().length() > 0) {
      if (this.getPlayer1Turn()) {
        this.getP1Cards()[this.getP1Index()] = (Card)this.getTableCards().removeFromFront();
        this.setP1Index(this.getP1Index() + 1);
        this.updateSpecialPoints(this.getP1Cards(), this.getP1Cards()[this.getP1Index() - 1]);
        this.updatePlayerScore(this.getP1Cards()); 
        this.getP1GUI().getChildren().add(this.getTableCardsGUI().getChildren().get(0)); 
      }
      //move first card from tableCards to p2Cards
      else {
        this.getP2Cards()[this.getP2Index()] = (Card)this.getTableCards().removeFromFront();
        this.setP2Index(this.getP2Index() + 1);
        this.updateSpecialPoints(this.getP2Cards(), this.getP2Cards()[this.getP2Index() - 1]);
        this.updatePlayerScore(this.getP2Cards());
        this.getP2GUI().getChildren().add(this.getTableCardsGUI().getChildren().get(0)); 
      }
      this.endTurn(); 
    }
    //deactivate all buttons, show who won
    if (this.getTableCards().length() == 0) {
      this.endGame();
    }
  }
  
  /**
   * ends the game
   */
  public void endGame() {
    //deactivate all p1 buttons
      for (int i = 0; i < this.getP1Buttons().length; i++) {
        if (this.getP1Buttons()[i] != null)
          this.getP1Buttons()[i].setDisable(true);
      }
      //deactivate all p2 buttons
      for (int i = 0; i < this.getP1Buttons().length; i++) {
        if (this.getP2Buttons()[i] != null)
          this.getP2Buttons()[i].setDisable(true);
      }
      Label winner = new Label();
      winner.setFont(new Font(30));
      if (this.getP1Score() > this.getP2Score())
        winner.setText("Player 1 Wins!!!");
      else if (this.getP2Score() > this.getP1Score())
        winner.setText("Player 2 Wins!!!");
      else if (this.getP2Score() == this.getP1Score())
        winner.setText("It's a Tie!");
      this.getTableCardsGUI().getChildren().add(winner);    
  }
  
  /**
   * ends current players' turn and updates GUI
   */
  public void endTurn() {
    if (this.getPlayer1Turn()) {
      //deactivate all p1 buttons
      for (int i = 0; i < this.getP1Buttons().length; i++) {
        if (this.getP1Buttons()[i] != null)
          this.getP1Buttons()[i].setDisable(true);
      } 
      //activate all notAlreadyPressed p2 buttons
      for (int i = 0; i < this.getP1ButtonsActive().length; i++) {
        if (this.getP2ButtonsActive()[i] == true && this.getP2Buttons()[i] != null)
          this.getP2Buttons()[i].setDisable(false);
        else
          if (this.getP2Buttons()[i] != null)
            this.getP2Buttons()[i].setDisable(true); 
      }
      if (this.getP2Buttons()[9] != null)
        this.getP2Buttons()[9].setDisable(false);
      this.setPlayer1Turn(false);
    }
    else {
      //deactivate all p2 buttons
      for (int i = 0; i < this.getP1Buttons().length; i++) {
        if (this.getP2Buttons()[i] != null)
          this.getP2Buttons()[i].setDisable(true);
      }
      //activate all notAlreadPressed p1 buttons
      for (int i = 0; i < this.getP1ButtonsActive().length; i++) {
        if (this.getP1ButtonsActive()[i] == true && this.getP1Buttons()[i] != null)
          this.getP1Buttons()[i].setDisable(false);
        else
          if (this.getP1Buttons()[i] != null)
            this.getP1Buttons()[i].setDisable(true);
      }
      if (this.getP1Buttons()[9] != null)
        this.getP1Buttons()[9].setDisable(false);
      this.setPlayer1Turn(true);
    } 
     this.updateGUI();
  }
  
  /**
   * helper method to update the GUI
   */
  public void updateGUI() {
    if (this.getP1ScoreLabel() != null)
      this.getP1ScoreLabel().setText("P1 Score: " + this.getP1Score());
    if (this.getP2ScoreLabel() != null)
      this.getP2ScoreLabel().setText("P2 Score: " + this.getP2Score());
    
    // displays tableCards on table
    this.getTableCardsGUI().getChildren().remove(0, this.getTableCardsGUI().getChildren().size());
    for (Object o : this.getTableCards()) {
      if (o instanceof Card) {
        Card card = (Card)o;
        Button b = new Button();
        if (card instanceof SpecialCard) {
          b.setText("Name: " + card.getName() + "     Group: " + card.getType() + 
                              "\n" + "                    Points: *"); 
        }
        else {
          b.setText("Name: " + card.getName() + "     Group: " + card.getType() + 
                              "\n" + "                    Points: " + card.getPoints());
        }
        b.setPrefSize(240, 40);
        this.getTableCardsGUI().getChildren().add(b);
      }
    } 
  }
  
  /**
   * helper method to update points of every special card in an array
   * @param array  array of cards to update points for
   * @param cardCollected  card just collected from table by player
   */
  public void updateSpecialPoints(Card[] array, Card cardCollected) {
    //for each card in array, checks if it is a special card, runs its updatePoints method
    for (int i = 0; i < array.length; i++) {
      if (array[i] instanceof SpecialCard) {
        SpecialCard c = (SpecialCard)array[i];
        c.updatePoints(cardCollected);
      }
    }
  }
  
  /**
   * helper method to update score of a player
   * @param array  array to count score from
   */
  public void updatePlayerScore(Card[] array) {
    int count = 0;
    //tallies all points in given array
    for (int i = 0; i < array.length; i++) {
      if (array[i] != null)
        count = count + array[i].getPoints();
    }
    if (this.getPlayer1Turn())
      this.setP1Score(count);
    else
      this.setP2Score(count); 
  }
  
  /**
   * helper method to shuffle deck
   * @param tableCards  linked list of cards to be shuffled 
   */
  public void shuffleCards(LinkedList tableCards) {
    //keeps track of which card is selected so as to not double select cards
    byte[] cardTracker = new byte[Guillotine.getDeckSize()];
    // index of the while loop
    int i = 0;    
    //for each requisite card in deck, select a new random card
    while (i < Guillotine.getDeckSize()) {
      //random index value to take card from deck
      int randomIndex = (byte)(Math.random() * 40);
      if (randomIndex == 40)
        randomIndex = (byte)39;
      if (!byteContains(cardTracker, (byte)(randomIndex))) {
        tableCards.addToFront(this.getDeck()[randomIndex]);
        cardTracker[i] = (byte)randomIndex; 
        i++;
      } 
    } 
  }
  
  /**
   * helper method to check if byte array contains a value
   * @param array  the array to check
   * @param value  value to check to see if array contains
   * @return true if value contained
   */
  public boolean byteContains(byte[] array, byte value) {
    //for each index in array, check if element == value
    for (int i = 0; i < array.length; i++) {
      if (array[i] == value)
        return true;
    }
    return false;
  }
  
// ------------------------------- GETTER SETTER METHODS --------------------------
  /**
   * sets deckSize integer to input
   * @param deckSize  new size of deck for game
   */
  public static void setDeckSize(int deckSize) {
    Guillotine.deckSize = deckSize;
  }
  
  /**
   * returns decksize integer
   * @return deck size
   */
  public static int getDeckSize() {
    return Guillotine.deckSize;
  }
  
  /**
   * returns deck array variable
   * @return deck array of cards
   */
  public Card[] getDeck() {
    return this.deck;
  }
  
  /**
   * sets deck array
   * @param deck  new deck for the game
   */
  public void setDeck(Card[] deck) {
    this.deck = deck;
  }
  
  /**
   * returns tableCards linked list variable
   * @return tableCards linked list of cards
   */
  public LinkedList getTableCards() {
    return this.tableCards;
  }
  
  /**
   * sets tableCards linked list
   * @param cards  new tableCards for the linked list
   */
  public void setTableCards(LinkedList cards) {
    this.tableCards = cards;
  }
  
  /**
   * returns p1Cards array variable
   * @return p1Cards array of cards
   */
  public Card[] getP1Cards() {
    return this.p1Cards;
  }
  
  /**
   * sets p1Cards array
   * @param cards  new p1Cards for the array
   */
  public void setP1Cards(Card[] cards) {
    this.p1Cards = cards;
  }
  
  /**
   * returns p2Cards array variable
   * @return p2Cards array of cards
   */
  public Card[] getP2Cards() {
    return this.p2Cards;
  }
  
  /**
   * sets p2Cards array
   * @param cards  new p2Cards for the array
   */
  public void setP2Cards(Card[] cards) {
    this.p2Cards = cards;
  }
  
  /**
   * returns player1Turn boolean variable
   * @return player1Turn boolean
   */
  public boolean getPlayer1Turn() {
    return this.player1Turn;
  }
  
  /**
   * sets player1Turn boolean
   * @param turn  true or false, where true is p1 turn, and false is p2 turn
   */
  public void setPlayer1Turn(boolean turn) {
    this.player1Turn = turn;
  }
  
  /**
   * returns p1Index int 
   * @return p1Index int
   */
  public int getP1Index() {
    return this.p1Index;
  }
  
  /**
   * sets value of p1Index to input
   * @param index  new index for p1Index
   */
  public void setP1Index(int index) {
    this.p1Index = index;
  }
  
   /**
   * returns p2Index int 
   * @return p2Index int
   */
  public int getP2Index() {
    return this.p2Index;
  }
  
  /**
   * sets value of p2Index to input
   * @param index  new index for p2Index
   */
  public void setP2Index(int index) {
    this.p2Index = index;
  }
  
  /**
   * returns p1Score int 
   * @return p1Score int
   */
  public int getP1Score() {
    return this.p1Score;
  }
  
  /**
   * sets value of p1Score to input
   * @param score  new index for p1Score
   */
  public void setP1Score(int score) {
    this.p1Score = score;
  }
  
  /**
   * returns p2Score int 
   * @return p2Score int
   */
  public int getP2Score() {
    return this.p2Score;
  }
  
  /**
   * sets value of p2Score to input
   * @param score  new index for p2Score
   */
  public void setP2Score(int score) {
    this.p2Score = score;
  }
  
  /**
   * returns p1Buttons array
   * @return  p1Buttons array
   */
  public Button[] getP1Buttons() {
    return this.p1Buttons;
  }
  
   /**
   * returns p1ButtonsActive array
   * @return  p1ButtonsActive array
   */
  public boolean[] getP1ButtonsActive() {
    return this.p1ButtonsActive;
  }
  
  /**
   * returns p2Buttons array
   * @return  p2Buttons array
   */
  public Button[] getP2Buttons() {
    return this.p2Buttons;
  }
  
   /**
   * returns p2ButtonsActive array
   * @return  p2ButtonsActive array
   */
  public boolean[] getP2ButtonsActive() {
    return this.p2ButtonsActive;
  }
  
  /**
   * returns p1ScoreLabel label
   * @return  p1ScoreLabel label
   */
  public Label getP1ScoreLabel() {
    return this.p1ScoreLabel;
  }
  
  /**
   * returns p2ScoreLabel label
   * @return  p2ScoreLabel label
   */
  public Label getP2ScoreLabel() {
    return this.p2ScoreLabel;
  }
  
  /**
   * returns p1GUI VBox
   * @return  p1GUI VBox
   */
  public VBox getP1GUI() {
    return this.p1GUI;
  }
  
  /**
   * returns p2GUI VBox
   * @return  p2GUI VBox
   */
  public VBox getP2GUI() {
    return this.p2GUI;
  }
  
  /**
   * returns tableCardsGUI VBox
   * @return  tableCardsGUI VBox
   */
  public VBox getTableCardsGUI() {
    return this.tableCardsGUI;
  }
}