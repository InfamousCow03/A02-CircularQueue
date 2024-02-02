package a02;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import a02.CircularQueue;

// = = = =   Do Not Modify This Class = = = =
/**
 * Tests the constructor, methods, and iterator of class CircularQueue.
 * 
 * @author CSIS
 */
public class CircularQueueTest {

	private CircularQueue<Integer> q1 = new CircularQueue<>(1);
	private CircularQueue<String> q4 = new CircularQueue<>(4);
	private CircularQueue<String> q4full = new CircularQueue<>(4);
	
	@Before
	public void beforeTest() {
		q1 = new CircularQueue<>(1);
		q4 = new CircularQueue<>(4);
		
		q4full = new CircularQueue<>(4);
		q4full.enqueue("a");
		q4full.enqueue("b");
		q4full.enqueue("c");
		q4full.enqueue("d");
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void constructor_capacityTooSmall() {
		new CircularQueue<Character>(0);
	}
	
	// = = = ifFull = = =
	
	@Test (timeout = 1000)
	public void isFull_true_tinyQueue() {
		q1.enqueue(10);
		assertTrue(q1.isFull());
	}
	
	@Test (timeout = 1000)
	public void isFull_true_largerQueue() {
		assertTrue(q4full.isFull());
	}	
	
	@Test (timeout = 1000)
	public void isFull_false() {
		assertFalse(q1.isFull());
	}
	
	// = = = isEmpty = = =
	
	@Test (timeout = 1000)
	public void isEmpty_newQueue() {
		assertTrue(q1.isEmpty());
	}
	
	@Test (timeout = 1000)
	public void isEmpty_afterAddingAndDeletingAnElement_tinyQueue() {
		q1.enqueue(10);
		assertFalse(q1.isEmpty());
		q1.dequeue();
		assertTrue(q1.isEmpty());
	}	
	
	@Test (timeout = 1000)
	public void isEmpty_false() {
		assertFalse(q4full.isEmpty());
	}	
	
	@Test (timeout = 1000)
	public void isEmpty_falseForPartiallyFilledQueue() {
		q4.enqueue("a");
		q4.enqueue("b");
		assertFalse(q4.isEmpty());
	}
	
	// = = = size = = =
	
	@Test (timeout = 1000)
	public void size_newQueue() {
		assertEquals(0, q1.size());
	}
	
	@Test (timeout = 1000)
	public void size_oneElement() {
		q4.enqueue("a");
		assertEquals(1, q4.size());
	}		
	
	@Test (timeout = 1000)
	public void size_fullQueue() {
		assertEquals(4, q4full.size());
	}
	
	// = = = toString = = = 
	
	@Test (timeout = 1000)
	public void toString_emptyQueue() {
		assertEquals("", q1.toString());
	}
	
	@Test 
	public void toString_oneELement() {
		q1.enqueue(10);
		String s = q1.toString();
		assertEquals("10 ", q1.toString());
	}
	
	@Test (timeout = 1000)
	public void toString_multipleElements() {
		assertEquals("a b c d ", q4full.toString());
	}
	
	// = = = enqueue = = = 
	
	@Test (timeout = 1000)
	public void enqueue_oneElement() {
		System.out.println("Before enqueue 10");
		q1.enqueue(10);
		assertEquals("10 ", q1.toString());
	}	
	
	@Test (timeout = 1000)
	public void enqueue_multipleElements() {
		q4.enqueue("a");
		q4.enqueue("b");
		q4.enqueue("c");
		assertEquals("a b c ", q4.toString());
	}
	
	// = = = dequeue = = = 
	
	@Test (timeout = 1000)
	public void dequeue_onlyElement() {
		q1.enqueue(10);
		q1.dequeue();
		assertEquals("", q1.toString());
	}	
	
	@Test (timeout = 1000)
	public void dequeue_oneOfMultipleElements() {
		q4full.dequeue();
		assertEquals("b c d ", q4full.toString());
	}
	
	@Test (timeout = 1000)
	public void dequeue_multipleElements() {
		q4full.dequeue();
		q4full.dequeue();
		q4full.dequeue();
		assertEquals("d ", q4full.toString());
	}
	
	// = = = peek = = = 
	
	@Test (timeout = 1000)
	public void peek_onlyElement() {
		q1.enqueue(10);
		assertEquals(Integer.valueOf(10), q1.peek());
	}	
	
	@Test (timeout = 1000)
	public void peek_fullQueueMultipleElements() {
		assertEquals("a", q4full.peek());
	}
	
	@Test (timeout = 1000)
	public void peek_partiallyFilledQueue() {
		q4.enqueue("a");
		q4.enqueue("b");
		assertEquals("a", q4.peek());
	}

	// = = = iterator = = =
	
	@Test (timeout = 1000)
	public void iterator_emptyQueue() {
		for(Integer el : q1) {
			fail("No element should be found in an empty queue.");
		}
	}
	
	@Test (timeout = 1000)
	public void iterator_oneElementQueue() {
		q1.enqueue(10);
		
		StringBuilder sb = new StringBuilder();
		for(Integer el : q1) {
			sb.append(el + " ");
		}
		assertEquals("10 ", sb.toString());
		
		sb = new StringBuilder();
		for(Integer el : q1) {
			sb.append(el + " ");
		}
		assertEquals("The iterator might have changed the queue.", "10 ", sb.toString());
	}	
	
	@Test (timeout = 1000)
	public void iterator_fullfourElementQueue() {
		
		StringBuilder sb = new StringBuilder();
		for(String el : q4full) {
			sb.append(el + " ");
		}
		assertEquals("a b c d ", sb.toString());
		
		sb = new StringBuilder();
		for(String el : q4full) {
			sb.append(el + " ");
		}
		assertEquals("The iterator might have changed the queue.", "a b c d ", sb.toString());
	}
	
	
	@Test (timeout = 1000)
	public void iterator_partiallyFilledQueue() {
		q4.enqueue("a");
		q4.enqueue("b");
		q4.enqueue("c");
		
		StringBuilder sb = new StringBuilder();
		for(String el : q4) {
			sb.append(el + " ");
		}
		assertEquals("a b c ", sb.toString());
	}
	
	// = = = combo = = =
	
	@Test //(timeout = 1000)
	public void combinationOfMethodCalls() {
		assertTrue(q4.isEmpty());
		assertFalse(q4.isFull());
		assertEquals(0, q4.size());
		
		q4.enqueue("a");
		assertFalse(q4.isEmpty());
		assertFalse(q4.isFull());
		assertEquals(1, q4.size());
		assertEquals("a ", q4.toString());
		assertEquals("a", q4.peek());
		
		q4.enqueue("b");
		assertFalse(q4.isEmpty());
		assertFalse(q4.isFull());
		assertEquals(2, q4.size());
		assertEquals("a b ", q4.toString());
		assertEquals("a", q4.peek());
		
		q4.dequeue();
		assertFalse(q4.isEmpty());
		assertFalse(q4.isFull());
		assertEquals(1, q4.size());
		assertEquals("b ", q4.toString());
		assertEquals("b", q4.peek());
		
		q4.enqueue("c");
		q4.enqueue("d");
		q4.enqueue("e");
		assertFalse(q4.isEmpty());
		assertTrue(q4.isFull());
		assertEquals(4, q4.size());
		assertEquals("b c d e ", q4.toString());
		assertEquals("b", q4.peek());
		StringBuilder sb = new StringBuilder();  // test iterator
		for(String el : q4) {  
			sb.append(el).append(" ");
		}
		assertEquals("b c d e ", sb.toString());
		
		q4.dequeue();
		q4.dequeue();
		assertFalse(q4.isEmpty());
		assertFalse(q4.isFull());
		assertEquals(2, q4.size());
		assertEquals("d e ", q4.toString());
		assertEquals("d", q4.peek());
		sb.delete(0, sb.length()); // empty StringBuilder to test iterator
		for(String el : q4) {  
			sb.append(el).append(" ");
		}
		assertEquals("d e ", sb.toString());
		
		q4.dequeue();
		assertEquals("e", q4.peek());
		q4.dequeue();
		assertTrue(q4.isEmpty());
		assertFalse(q4.isFull());
		assertEquals(0, q4.size());
		assertEquals("", q4.toString());

	}
		
	
}