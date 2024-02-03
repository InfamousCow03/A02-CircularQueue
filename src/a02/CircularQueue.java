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

	int capacity, front, rear, currentSize; // Size of Circular Queue
	Item items[];
	  
	/**
	 * Constructor for class CircularQueue initializes front and rear of queue, as well as generic abject array
	 * 
	 * @param <Item>
	 */
	@SuppressWarnings("unchecked")
	public CircularQueue(int capacity) {
	    super();
	    if (capacity < 1) {
	        throw new IllegalArgumentException("Capacity can not be less than 1");
	    }
	    this.capacity = capacity;
	    this.front = -1;
	    this.rear = -1;
	    this.items = (Item[]) new Object[capacity];
	    this.currentSize = 0;
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
		          throw new IllegalArgumentException("Queue is full");
		      } else {
		          if (front == -1)
		              front = 0;
		          rear = (rear + 1) % capacity;
		          currentSize++;
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
		      currentSize--;
		      return (element);
		    }
		  }catch(Exception e) {
			  System.out.println("Caught an Exception: " + e.getMessage());
		  }
		  return null;
	  }
	  
	  
	  /**
	   * This method should return the number of elements currently stored in the queue. Note,
	   * that this is different from the size of the array (capacity) of a queue. To avoid confusion,
	   * refer to the fixed number of elements that can be stored in the queue as capacity.
	   * 
	   * @return size of queue
	   */
	  public int size() {
		  return currentSize;
	  }
	  
	  
	  /**
	   * 
	   * @return first element in the queue without removing it
	   */
	  public Item peek() {
		  try {
			    if (isEmpty()) {
			      throw new NoSuchElementException("Queue is empty");
			    } else {
			    	return items[front];
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
		 * 
		 * @param args
		 */
	  public static void main(String[] args) {
	        CircularQueue<Integer> queue = new CircularQueue<>(5);


	        System.out.println("Is Queue Empty? " + queue.isEmpty());


	        queue.enqueue(1);
	        queue.enqueue(2);
	        queue.enqueue(3);


	        System.out.println("Queue Size: " + queue.size());


	        System.out.println("Peek: " + queue.peek());


	        System.out.println("Queue Contents: " + queue.toString());


	        Integer dequeuedItem = queue.dequeue();
	        if (dequeuedItem != null) {
	            System.out.println("Dequeued Element: " + dequeuedItem);
	        }


	        System.out.println("Is Queue Full? " + queue.isFull());


	        System.out.println("Queue Contents after Dequeue: " + queue.toString());


	        queue.enqueue(4);


	        System.out.println("Queue Contents after Enqueue: " + queue.toString());


	        System.out.println("Is Queue Full? " + queue.isFull());


	        try {
	            queue.dequeue();
	        } catch (NoSuchElementException e) {
	            System.out.println("Exception caught: " + e.getMessage());
	        }


	        try {
	            queue.enqueue(5);
	            queue.enqueue(6);
	        } catch (IllegalArgumentException e) {
	            System.out.println("Exception caught: " + e.getMessage());
	        }
	    }

}
