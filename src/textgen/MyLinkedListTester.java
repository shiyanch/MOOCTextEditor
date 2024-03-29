/**
 * 
 */
package textgen;

import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;

/**
 * @author UC San Diego MOOC team
 *
 */
public class MyLinkedListTester {

	private static final int LONG_LIST_LENGTH =10; 

	MyLinkedList<String> shortList;
	MyLinkedList<Integer> emptyList;
	MyLinkedList<Integer> longerList;
	MyLinkedList<Integer> list1;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		// Feel free to use these lists, or add your own
	    shortList = new MyLinkedList<String>();
		shortList.add("A");
		shortList.add("B");
		emptyList = new MyLinkedList<Integer>();
		longerList = new MyLinkedList<Integer>();
		for (int i = 0; i < LONG_LIST_LENGTH; i++)
		{
			longerList.add(i);
		}
		list1 = new MyLinkedList<Integer>();
		list1.add(65);
		list1.add(21);
		list1.add(42);
		
	}

	
	/** Test if the get method is working correctly.
	 */
	/*You should not need to add much to this method.
	 * We provide it as an example of a thorough test. */
	@Test
	public void testGet()
	{
		//test empty list, get should throw an exception
		try {
			emptyList.get(0);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
			
		}
		
		// test short list, first contents, then out of bounds
		assertEquals("Check first", "A", shortList.get(0));
		assertEquals("Check second", "B", shortList.get(1));
		
		try {
			shortList.get(-1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		try {
			shortList.get(2);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		// test longer list contents
		for(int i = 0; i<LONG_LIST_LENGTH; i++ ) {
			assertEquals("Check "+i+ " element", (Integer)i, longerList.get(i));
		}
		
		// test off the end of the longer array
		try {
			longerList.get(-1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		try {
			longerList.get(LONG_LIST_LENGTH);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		}
	}
	
	
	/** Test removing an element from the list.
	 * We've included the example from the concept challenge.
	 * You will want to add more tests.  */
	@Test
	public void testRemove()
	{
		int a = list1.remove(0);
		assertEquals("Remove: check a is correct ", 65, a);
		assertEquals("Remove: check element 0 is correct ", (Integer)21, list1.get(0));
		assertEquals("Remove: check size is correct ", 2, list1.size());
		
		int b = list1.remove(1);
		assertEquals("Remove: check b is correct ", 42, b);
		assertEquals("Remove: check element 0 is correct ", (Integer)21, list1.get(0));
		assertEquals("Remove: check size is correct ", 1, list1.size());
		
		try {
			list1.remove(3);
			fail("Remove: delete out of index");
		}
		catch(IndexOutOfBoundsException e) {
			
		}
		
		int c = list1.remove(0);
		assertEquals("Remove: check c is correct ", 21, c);
		assertEquals("Remove: check size is correct ", 0, list1.size());
		
		try {
			list1.remove(0);
			fail("Remove: check delete empty list");
		}
		catch(IndexOutOfBoundsException e) {
			
		}
		
		try {
			list1.remove(-1);
			fail("Remove: check lower bound");
		}
		catch(IndexOutOfBoundsException e) {
			
		}
	}
	
	/** Test adding an element into the end of the list, specifically
	 *  public boolean add(E element)
	 * */
	@Test
	public void testAddEnd()
	{
		shortList.add(2, "C");
		assertEquals("Check add end", "C", shortList.get(2));
		emptyList.add(1);
		assertEquals("Check add end", "1", emptyList.get(0).toString());
		
	}

	
	/** Test the size of the list */
	@Test
	public void testSize()
	{
		assertEquals("Check size of emptyList", 0, emptyList.size());
		
		emptyList.add(3);
		assertEquals("Check size after add an element", 1, emptyList.size());
		
		emptyList.remove(0);
		assertEquals("Check size of emptyList", 0, emptyList.size());
	}

	
	
	/** Test adding an element into the list at a specified index,
	 * specifically:
	 * public void add(int index, E element)
	 * */
	@Test
	public void testAddAtIndex()
	{
		try {
			shortList.add(0,  null);
			fail("Set null element");
		}
		catch(NullPointerException e) {
			
		}
	    
		shortList.add(0, "D");
		assertEquals("Check insert to the head", "D", shortList.get(0));
		
		shortList.add(3, "E");
		assertEquals("Check insert to the end", "E", shortList.get(3));
		
		shortList.add(2, "F");
		assertEquals("Check inside the list", "F", shortList.get(2));
		
		try {
			shortList.add(-1, "G");
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
			
		}
		
		try {
			shortList.add(10, "H");
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
			
		}
		
	}
	
	/** Test setting an element in the list */
	@Test
	public void testSet()
	{
		try {
			shortList.set(0,  null);
			fail("Set null element");
		}
		catch(NullPointerException e) {
			
		}
	    
		String a = shortList.set(0, "X");
		assertEquals("Set: new first element", "X", shortList.get(0));
		assertEquals("Set: check a is correct", "A", a);
		
		String b = shortList.set(1, "Y");
		assertEquals("Set: last element", "Y", shortList.get(1));
		assertEquals("Set: check b is correct", "B", b);
		
		try {
			shortList.set(10, "Z");
			fail("Check out of bounds");
		}
		catch(IndexOutOfBoundsException e) {
			
		}
	    
		try {
			shortList.set(-1, "#");
			fail("Check out of bounds");
		}
		catch(IndexOutOfBoundsException e) {
			
		}
		
		
	}
	
	
	// TODO: Optionally add more test methods.
	
}
