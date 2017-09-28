import java.util.*;

/**
 * Class for testing the SearchAndSort class
 * @author Omar
 *
 */

public class SearchAndSortTester {

	private enum ListToUse {
		mergeSort
	};
	 // TODO: THIS IS WHERE YOU CHOOSE WHICH LIST TO TEST
	private final ListToUse LIST_TO_USE = ListToUse.mergeSort;

	// possible results expected in tests
	private enum Result {
		EmptyCollection, ElementNotFound, IndexOutOfBounds, IllegalState, ConcurrentModification, NoSuchElement, 
		NoException, UnexpectedException,
		True, False, Pass, Fail, 
		MatchingValue,
		ValidString
	};

	// named elements for use in tests
	private static final Integer ELEMENT_A = new Integer(1);
	private static final Integer ELEMENT_B = new Integer(2);
	private static final Integer ELEMENT_C = new Integer(3);
	private static final Integer ELEMENT_D = new Integer(4);

	// instance variables for tracking test results
	private int passes = 0;
	private int failures = 0;
	private int total = 0;

	/**
	 * @param args not used
	 */
	public static void main(String[] args) {
		// to avoid every method being static
		SearchAndSortTester tester = new SearchAndSortTester();
		tester.runTests();
	}

	/**
	 * Print test results in a consistent format
	 * 
	 * @param testDesc description of the test
	 * @param result indicates if the test passed or failed
	 */
	private void printTest(String testDesc, boolean result) {
		total++;
		if (result) { passes++; }
		else { failures++; }
		System.out.printf("%-46s\t%s\n", testDesc, (result ? "   PASS" : "***FAIL***"));
	}

	/** Print a final summary */
	private void printFinalSummary() {
		System.out.printf("\nTotal Tests: %d,  Passed: %d,  Failed: %d\n",
				total, passes, failures);
	}

	/** XXX <- see the blue box on the right of the scroll bar? this tag aids in navigating long files
	 * Run tests to confirm required functionality from list constructors and methods */
	private void runTests() {
		//recommended scenario naming: start_change_result
		test_newList(); //aka noList_constructor_emptyList
		test_sort21_12();
		test_sort10_01();
		test_sort321_123();
		
		// report final verdict
		printFinalSummary();
	}

	//////////////////////////////////////
	// SCENARIO: NEW EMPTY LIST
	//  XXX Scenario 1
	//////////////////////////////////////
	
	/**
	 * Returns a ListADT for the "new empty list" scenario.
	 * Scenario: no list -> constructor -> [ ]
	 * 
	 * NOTE: the return type is a basic ListADT reference, so each test method
	 * will need to cast the reference to the specific interface (Indexed or
	 * UnorderedListADT) containing the method being tested.
	 *
	 * @return a new, empty ListADT
	*/

	/** Run all tests on scenario: no list -> constructor -> [ ] */
	private void test_newList() {
		try {
			// ListADT
			System.out.println("no list -> constructor -> [ ]");
			printTest("newList_sort", testSort("", ""));
			printTest("newList_sortComparator", testSortComparator("", ""));
			printTest("newList_findSmallest", testFindSmallest("1", 1));
			printTest("newList_findSmallestComparator", testFindSmallestComparator("1", 1));
			printTest("newList_findLargest", testFindLargest("1", 1));
			printTest("newList_findLargestComparator", testFindLargestComparator("1", 1));
			System.out.println();
		} catch (Exception e) {
			System.out.printf("***UNABLE TO RUN/COMPLETE %s***\n", "test_newList");
			e.printStackTrace();
		}
	}
	
	private void test_sort21_12() {
		try {
			// ListADT
			System.out.println("no list -> constructor -> [ ]");
			printTest("newList_sort", testSort("21", "12"));
			printTest("newList_sortComparator", testSortComparator("21", "12"));
			printTest("newList_findSmallest", testFindSmallest("12", 1));
			printTest("newList_findSmallestComparator", testFindSmallestComparator("12", 1));
			printTest("newList_findLargest", testFindLargest("12", 2));
			printTest("newList_findLargestComparator", testFindLargestComparator("12", 2));
			System.out.println();
		} catch (Exception e) {
			System.out.printf("***UNABLE TO RUN/COMPLETE %s***\n", "test_newList");
			e.printStackTrace();
		}
	}
	
	private void test_sort10_01() {
		try {
			// ListADT
			System.out.println("no list -> constructor -> [ ]");
			printTest("newList_sort", testSort("10", "01"));
			printTest("newList_sortComparator", testSortComparator("10", "01"));
			printTest("newList_findSmallest", testFindSmallest("01", 0));
			printTest("newList_findSmallestComparator", testFindSmallestComparator("01", 0));
			printTest("newList_findLargest", testFindLargest("01", 1));
			printTest("newList_findLargestComparator", testFindLargestComparator("01", 1));
			System.out.println();
		} catch (Exception e) {
			System.out.printf("***UNABLE TO RUN/COMPLETE %s***\n", "test_newList");
			e.printStackTrace();
		}
	}
	
	private void test_sort321_123() {
		try {
			// ListADT
			System.out.println("no list -> constructor -> [ ]");
			printTest("newList_sort", testSort("321", "123"));
			printTest("newList_sortComparator", testSortComparator("321", "123"));
			printTest("newList_findSmallest", testFindSmallest("123", 1));
			printTest("newList_findSmallestComparator", testFindSmallestComparator("123", 1));
			printTest("newList_findLargest", testFindLargest("123", 3));
			printTest("newList_findLargestComparator", testFindLargestComparator("123", 3));
			System.out.println();
		} catch (Exception e) {
			System.out.printf("***UNABLE TO RUN/COMPLETE %s***\n", "test_newList");
			e.printStackTrace();
		}
	}
	
	private boolean testSort(String elements, String result)
	{
		try
		{
			int index = 0;
			WrappedDLL<Integer> list = new WrappedDLL<Integer>();
			for(int i = 0; i<elements.length(); i++)
			{
				String name = elements.charAt(i) + "";
				list.addToRear(Integer.parseInt(name));
			}

			SearchAndSort.sort(list);
			
			for(int i = 0; i<result.length(); i++)
			{
				String name = result.charAt(i) + "";
				if (list.get(i) != Integer.parseInt(name))
					return false;
			}
		}
		
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	private boolean testSortComparator(String elements, String result)
	{
		try
		{
			int index = 0;
			WrappedDLL<Integer> list = new WrappedDLL<Integer>();
			for(int i = 0; i<elements.length(); i++)
			{
				String name = elements.charAt(i) + "";
				list.addToRear(Integer.parseInt(name));
			}

			SearchAndSort.sort(list, new ComparableComparator<Integer>());
			
			for(int i = 0; i<result.length(); i++)
			{
				String name = result.charAt(i) + "";
				if (list.get(i) != Integer.parseInt(name))
					return false;
			}
		}
		
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	private boolean testFindSmallest(String elements, int result)
	{
		try
		{
			int index = 0;
			WrappedDLL<Integer> list = new WrappedDLL<Integer>();
			for(int i = 0; i<elements.length(); i++)
			{
				String name = elements.charAt(i) + "";
				list.addToRear(Integer.parseInt(name));
			}
			int smallest = SearchAndSort.findSmallest(list);
			
			if(smallest != result)
				return false;
		}

		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	private boolean testFindSmallestComparator(String elements, int result)
	{
		try
		{
			int index = 0;
			WrappedDLL<Integer> list = new WrappedDLL<Integer>();
			for(int i = 0; i<elements.length(); i++)
			{
				String name = elements.charAt(i) + "";
				list.addToRear(Integer.parseInt(name));
			}
			
			int smallest = SearchAndSort.findSmallest(list, new ComparableComparator<Integer>());
			
			if(smallest != result)
				return false;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	private boolean testFindLargest(String elements, int result)
	{
		try
		{
			int index = 0;
			WrappedDLL<Integer> list = new WrappedDLL<Integer>();
			for(int i = 0; i<elements.length(); i++)
			{
				String name = elements.charAt(i) + "";
				list.addToRear(Integer.parseInt(name));
			}
			
			int largest = SearchAndSort.findLargest(list);
			
			if(largest != result)
				return false;
		}
		
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	private boolean testFindLargestComparator(String elements, int result)
	{
		try
		{
			int index = 0;
			WrappedDLL<Integer> list = new WrappedDLL<Integer>();
			System.out.println(elements);
			for(int i = 0; i<elements.length(); i++)
			{
				String name = elements.charAt(i) + "";
				list.addToRear(Integer.parseInt(name));
			}

			int largest = SearchAndSort.findLargest(list, new ComparableComparator<Integer>());
			System.out.println(list.toString());
			if(largest != result)
				return false;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
}
