package a02;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Circular Queue class implementation by
 * 
 * @author Wesley Elliott & Jamison Tidwell
 * 
 * @param <Item>
 */

public class CircularQueue<Item> implements Iterable<Item> {

	int capacity, front, rear; // Size of Circular Queue
	Item items[];
	  
	/**
	 * Constructor for class CircularQueue initializes front and rear of queue, as well as generic abject array
	 * 
	 * @param <Item>
	 */
	@SuppressWarnings("unchecked")
	public CircularQueue(int capacity) {
		super();
		try {
			if(capacity < 1) {
				throw new IllegalArgumentException("Capacity can not be less than 1");
			}
			this.capacity = capacity;
			this.front = -1;
		    this.rear = -1;
		    this.items = (Item[]) new Object[capacity]; // Initialize the generic array
		}catch(Exception e) {
			System.out.println("Caught an Exception: " + e.getMessage());
		}
	}
	

	// Check if the queue is full
	  boolean isFull() {
	    if (front == 0 && rear == capacity - 1) {
	      return true;
	    }
	    if (front == rear + 1) {
	      return true;
	    }
	    return false;
	  }
	  
	  
	  
	// Check if the queue is empty
	  boolean isEmpty() {
	    if (front == -1)
	      return true;
	    else
	      return false;
	  }
	  
	  
	  
	// Adding an element
	  void enqueue(Item item) {
		  try {
		      if (isFull()) {
		          throw new UnsupportedOperationException("Queue is full");
		      } else {
		          if (front == -1)
		              front = 0;
		          rear = (rear + 1) % capacity;
		          items[rear] = item;
		          System.out.println("Inserted " + item);
		      }
		  }catch(Exception e) {
			  System.out.println("Caught an Exception: " + e.getMessage());
		  }
	  }
	  
	  
	  
	// Removing an element
	  Item dequeue() {
		  try {
		    Item element;
		    if (isEmpty()) {
		      throw new NoSuchElementException("Queue is empty");
		    } else {
		      element = items[front];
		      if (front == rear) {
		        front = -1;
		        rear = -1;
		      } /* Q has only one element, so we reset the queue after deleting it. */
		      else {
		        front = (front + 1) % capacity;
		      }
		      return (element);
		    }
		  }catch(Exception e) {
			  System.out.println("Caught an Exception: " + e.getMessage());
		  }
		  return null;
	  }
	  
	  
	  /**
	   * TODO
	   * This method should return the number of elements currently stored in the queue. Note,
	   * that this is different from the size of the array (capacity) of a queue. To avoid confusion,
	   * refer to the fixed number of elements that can be stored in the queue as capacity.
	   * 
	   * @return size of queue
	   */
	  public int size() {
		  return 0;
		  //TODO initialize size() here
	  }
	  
	  
	  /**
	   * TODO
	   * There was no description for this method in the assignment but based on the JUnit tests I think
	   * its just supposed to return the first value of the queue without removing it hence the "peek"
	   * 
	   * @return first element in the queue without removing it
	   */
	  public Item peek() {
		  try {
			    if (isEmpty()) {
			      throw new NoSuchElementException("Queue is empty");
			    } else {
			    	//TODO Initialize peek() here
			    }
			  }catch(Exception e) {
				  System.out.println("Caught an Exception: " + e.getMessage());
			  }
			  return null;
	  }
	  
	  
	  //creates new Iterator class within method.
	  @Override
	  public Iterator<Item> iterator() {
	      return new Iterator<Item>() {
	          private int current = front;

	          //checks if we are at the end of the queue, 
	          @Override
	          public boolean hasNext() {
	              return current != -1;
	          }

	          //iterates through array using hasNext to check index
	          @Override
	          public Item next() {
	              if (!hasNext()) {
	                  throw new NoSuchElementException();
	              }
	              Item item = items[current];
	              if (current == rear) {
	                  current = -1; // Reset to indicate the end of the queue
	              } else {
	                  current = (current + 1) % capacity;
	              }
	              return item;
	          }
	      };
	  }

	  
	  
	  // iterates over queue and builds correct corresponding string
	  @Override
		public String toString() {
		  StringBuilder result = new StringBuilder("");
		  
		  Iterator<Item> iterator = iterator();
		    while (iterator.hasNext()) {
		        result.append(iterator.next()).append(" ");
		    }
		    
		    return result.toString();
		}
	  
	  
		/**
		 * TODO create a more better test code
		 * 
		 * @param args
		 */
		public static void main(String[] args) {
			CircularQueue<Integer> q = new CircularQueue<>(5);

		    // Fails because front = -1
		    q.dequeue();

		    q.enqueue(1);
		    q.enqueue(2);
		    q.enqueue(3);
		    q.enqueue(4);
		    q.enqueue(5);

		    // Fails to enqueue because front == 0 && rear == SIZE - 1
		    q.enqueue(6);

		    System.out.println(q.toString());

		    Integer elem = q.dequeue();

		    if (elem != null) {
		      System.out.println("Deleted Element is " + elem);
		    }
		    System.out.println(q.toString());

		    q.enqueue(7);

		    System.out.println(q.toString());

		    // Fails to enqueue because front == rear + 1
		    q.enqueue(8);
	
		}

}