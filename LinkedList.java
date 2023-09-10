/**
 * Class of linked list
 * @author Harold Connamacher
 * @author Ethan Tobey
 */
import java.util.NoSuchElementException;

/**
 * A class to represent a linked list of nodes. 
 * The node is implemented as a non-static nest class
 */
public class LinkedList<T> implements Iterable<T> {
  /** the first node of the list, or null if the list is empty */
  private Node firstNode;
  
  /**
   * Creates an initially empty linked list
   */
  public LinkedList() {
    firstNode = null;
  }
  
  /**
   * Returns the first node.
   */
  public Node getFirstNode() {
    return firstNode;
  }

  /**
   * Changes the front node.
   * @param node  the node that will be the first node of the new linked list
   */
  protected void setFirstNode(Node node) {
    this.firstNode = node;
  }

  /**
   * Return whether the list is empty
   * @return true if the list is empty
   */
  public boolean isEmpty() {
    return (getFirstNode() == null);
  }
  
  /**
   * moves first node back n places
   * @param n  the number of places to move the first node back
   */
  public void moveBack(int n) {
    if (this.length() > n) {
      Node oldFirst = this.getFirstNode();
      this.setFirstNode(this.getFirstNode().getNext());
      Node next = this.getFirstNode().getNext();
      Node previous = this.getFirstNode();
      // sets next & preious to appropriate nextNode and previous node n nodes back
      for (int i = 0; i < n-1; i++) {
        next = next.getNext();
        previous = previous.getNext();
      }
      oldFirst.setNext(next);
      previous.setNext(oldFirst);
      }
    }
  
  /**
   * moves first node to end of list
   */
  public void moveFirstToLast() {
    Node nodeptr = this.getFirstNode();
    this.removeFromFront();
    this.addToEnd(nodeptr.getElement());
  }
  
  /**
   * moves last node to the start of list
   */
  public void moveLastToFirst() {
    if (this.length() > 1) {
      Node nodeptr = getFirstNode().getNext();
      Node previous = getFirstNode();
      // sets nodeptr to last node, and previous to penultimate node
      while (nodeptr.getNext() != null) {
        nodeptr = nodeptr.getNext();
        previous = previous.getNext();
      }
      previous.setNext(null);
      this.addToFront(nodeptr.getElement());
    }
  }
  
  /**
   * reverses all nodes in list
   */
  public void reverseList() {
    int moveNumber = this.length() - 1;
    //moves front node back a number of places, then decreases number of places for next loop
    while (moveNumber > 0) {
      this.moveBack(moveNumber);
      moveNumber--;
    }
  }
  
  /**
   * reverses first k nodes of the linked list
   * leaves the rest of the list unchanged
   */
  public void reverseFirstK(int k) {
    int moveNumber = k - 1;
    //moves front node back a number of places, then decreases number of places for next loop
    while (moveNumber > 0) {
      this.moveBack(moveNumber);
      moveNumber--;
    }
  }
  
  /**
   * Add an element to the front of the linked list
   */
  public void addToFront(T element) {
    setFirstNode(new Node(element, getFirstNode()));
  }
  
  /**
   * Removes and returns the element at the front of the linked list
   * @return the element removed from the front of the linked list
   * @throws NoSuchElementException if the list is empty
   */
  public T removeFromFront() {
    if (isEmpty())
      throw new NoSuchElementException();
    else {
      T save = getFirstNode().getElement();
      setFirstNode(getFirstNode().getNext());
      return save;
    }
  }

  /**
   * Returns the length of the linked list
   * @return the number of nodes in the list
   */
  public int length() {
    int count = 0;
    Node nodeptr = getFirstNode();
    while (nodeptr != null) {
      count++;
      nodeptr = nodeptr.getNext();
    }
    return count;
  }
  
  /**
   * Adds an element to the end of the linkd list
   * @param element the element to insert at the end
   */
  public void addToEnd(T element) {
    if (isEmpty())
      addToFront(element);
    else {
      Node nodeptr = getFirstNode();
      while (nodeptr.getNext() != null) 
        nodeptr = nodeptr.getNext();
      nodeptr.setNext(new Node(element, null));
    }
  }
  
  /**
   * Return an iterator for this list
   * @return the iterator for the list
   */
  @Override
  public LinkedListIterator<T> iterator() {
    return new LinkedListIterator<T>(getFirstNode());
    //return null;
  }
  
  /* Static methods and generics: 
   *   Generic types only go with instance methods and fields
   *   If I want a generic here, I must declare it in the method header,
   *   before the return type 
   */
  
  /**
   * Prints the contents of a list to System.out.
   * @param list the list to print
   */
  public static <S> void printList(LinkedList<S> list) {
    for (S element : list) {
      System.out.print(element);
      System.out.print(" ");
    }
    System.out.println();
  }
  
  /* Generic types and wildcards:
   *    If we don't care what the generic type is because we don't use that type 
   *     (other than calling Object methods on it)
   *    we can use a wildcard that means we don't care what the generic type is 
   */
  
  /**
   * Prints the contents of a linked list to System.out.
   * @param list the linked list to print
   */
  public static void printLinkedList(LinkedList<?> list) {
    for (Object element : list) {
      System.out.print(element);
      System.out.print(" ");
    }
    System.out.println();
  }
    
  /**
   * Take a linked list that is already sorted in order and add a new element
   * into the correct location
   * @param list the linked list
   * @param element the element to add
   */
  public static <S extends Comparable<? super S>> void insertInOrder(LinkedList<S> list, S element) {
    if (list.isEmpty()) {
      list.addToFront(element);
    }
    else {
      // case 1: the element goes to front of list
      if (element.compareTo(list.getFirstNode().getElement()) < 0)
        list.addToFront(element);
      // case 2: the element goes elsewhere, create a nodeptr to find where it goes
      else {
        LinkedList<S>.Node nodeptr = list.getFirstNode();
        while (nodeptr.getNext() != null && nodeptr.getNext().getElement().compareTo(element) < 0)
          nodeptr = nodeptr.getNext();
        // when the loop ends the element goes after nodeptr
        nodeptr.setNext(list.new Node(element, nodeptr.getNext()));
      }
    }
  }
  
  /** The nested class for nodes, it is non-static so it has acces to the generic
    * of LinkedList and does not need to declare its own
    */
  public class Node {
    /** the element stored in the node */
    private T element;
    
    /** a reference to the next node of the list */
    private Node next;
    
    /**
     * the constructor
     * @param element  the element to store in the node
     * @param next  a reference to the next node of the list 
     */
    public Node(T element, Node next) {
      this.element = element;
      this.next = next;
    }
    
    /**
     * Returns the element stored in the node
     * @return the element stored in the node
     */
    public T getElement() {
      return element;
    }
    
    /**
     * Returns the next node of the list
     * @return the next node of the list
     */
    public Node getNext() {
      return next;
    }
    
    /**
     * Changes the element stored in this node
     * @param element the new element to store
     */
    public void setElement(T element) {
      this.element = element;
    }
    
    /**
     * Changes the next pointer for this node
     * @param next the node that should come after this node in the list
     */
    public void setNext(Node next) {
      this.next = next;
    }
  }
}