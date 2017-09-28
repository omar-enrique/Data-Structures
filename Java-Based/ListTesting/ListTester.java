import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A unit test class for lists that implement both UnorderedListADT and
 * IndexedListADT. This is a set of black box tests that should work for any
 * implementation of these interfaces.
 * 
 * NOTE: One example test is given for each interface method using a new list to
 * get you started.
 * 
 * @author mvail, mhthomas, Omar
 */
public class ListTester {
	//possible lists that could be tested
	private enum ListToUse {
		goodList, badList, arrayList, singleLinkedList, doubleLinkedList
	};
	 // TODO: THIS IS WHERE YOU CHOOSE WHICH LIST TO TEST
	private final ListToUse LIST_TO_USE = ListToUse.doubleLinkedList;

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
		ListTester tester = new ListTester();
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
		test_emptyList_addToFrontA_A();
		test_emptyList_addToRearA_A();
		test_emptyList_addA_A();
		test_emptyList_addA0_A();
		test_A_removeFirst_emptyList();
		test_A_removeLast_emptyList();		 
		test_A_addToFrontB_BA();
		test_A_addToRearB_AB();
		test_A_addAfterAB_AB();
		test_A_remove0_empty();

		test_A_removeA_emptyList();


		test_AB_addC_ABC();
//		test_A_removeLast_emptyList();
//		test_A_removeA_emptyList();
		//and so on
		
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
	private ListADT<Integer> newList() {
		ListADT<Integer> listToUse;
		switch (LIST_TO_USE) {
//		case goodList:
//			listToUse = new GoodList<Integer>();
//			break;
//		case badList:
//			listToUse = new BadList<Integer>();
//			break;
//		 case arrayList:
//		 listToUse = new ArrayList<Integer>();
//		 break;
//		 case singleLinkedList:
//		 listToUse = new SingleLinkedList<Integer>();
//		 break;
		 case doubleLinkedList:
		 listToUse = new DoubleLinkedList<Integer>();
		 break;
		default:
			listToUse = null;
		}
		return listToUse;
	}

	/** Run all tests on scenario: no list -> constructor -> [ ] */
	private void test_newList() {
		// recommended test naming: start_change_result_testName
		// e.g. A_addToFront_BA_testSize
		// AB_addC1_ACB_testFirst
		// A_remove0_empty_testLast

		//try-catch is necessary to prevent an Exception from the scenario builder method from bringing
		//down the whole test suite
		try {
			// ListADT
			System.out.println("no list -> constructor -> [ ]");
			printTest("newList_testRemoveFirst", testRemoveFirst(newList(), null, Result.EmptyCollection));
			printTest("newList_testRemoveLast", testRemoveLast(newList(), null, Result.EmptyCollection));
			printTest("newList_testRemoveA", testRemoveElement(newList(), null, Result.ElementNotFound));
			printTest("newList_testFirst", testFirst(newList(), null, Result.EmptyCollection));
			printTest("newList_testLast", testLast(newList(), null, Result.EmptyCollection));
			printTest("newList_testContainsA", testContains(newList(), ELEMENT_A, Result.False));
			printTest("newList_testIsEmpty", testIsEmpty(newList(), Result.True));
			printTest("newList_testSize", testSize(newList(), 0));
			printTest("newList_testToString", testToString(newList(), Result.ValidString));
			// UnorderedListADT
			printTest("newList_testAddToFrontA", testAddToFront(newList(), ELEMENT_A, Result.NoException));
			printTest("newList_testAddToRearA", testAddToRear(newList(), ELEMENT_A, Result.NoException));
			printTest(	"newList_testAddAfterBA", testAddAfter(newList(), ELEMENT_B, ELEMENT_A, Result.ElementNotFound));
			// IndexedListADT
			printTest("newList_testAddAtIndexNeg1", testAddAtIndex(newList(), -1, ELEMENT_A, Result.IndexOutOfBounds));
			printTest("newList_testAddAtIndex0", testAddAtIndex(newList(), 0, ELEMENT_A, Result.NoException));
			printTest("newList_testAddAtIndex1", testAddAtIndex(newList(), 1, ELEMENT_A, Result.IndexOutOfBounds));
			printTest("newList_testSetNeg1A", testSet(newList(), -1, ELEMENT_A, Result.IndexOutOfBounds));
			printTest("newList_testSet0A", testSet(newList(), 0, ELEMENT_A, Result.IndexOutOfBounds));
			printTest("newList_testAddA", testAdd(newList(), ELEMENT_A, Result.NoException));
			printTest("newList_testGetNeg1", testGet(newList(), -1, null, Result.IndexOutOfBounds));
			printTest("newList_testGet0", testGet(newList(), 0, null, Result.IndexOutOfBounds));
			printTest("newList_testIndexOfA", testIndexOf(newList(), ELEMENT_A, -1));
			printTest("newList_testRemoveNeg1", testRemoveIndex(newList(), -1, null, Result.IndexOutOfBounds));
			printTest("newList_testRemove0", testRemoveIndex(newList(), 0, null, Result.IndexOutOfBounds));
			// Iterator
			printTest("newList_testIterator", testIterator(newList(), Result.NoException));
			printTest("newList_testIteratorHasNext", testIteratorHasNext(newList().iterator(), Result.False));
			printTest("newList_testIteratorNext", testIteratorNext(newList().iterator(), null, Result.NoSuchElement));
			printTest("newList_testIteratorRemove", testIteratorRemove(newList().iterator(), Result.IllegalState));
			System.out.println();
		} catch (Exception e) {
			System.out.printf("***UNABLE TO RUN/COMPLETE %s***\n", "test_newList");
			e.printStackTrace();
		}
	}

	////////////////////////////////////////////////
	// SCENARIO: [ ] -> addToFront(A) -> [A]
	//  XXX Scenario 2
	////////////////////////////////////////////////
	
	/** Scenario: empty list -> addToFront(A) -> [A] 
	 * @return [A] after addToFront(A)
	 */
	private ListADT<Integer> emptyList_addToFrontA_A() {
		// cast to UnorderedListADT to make addToFront() available
		UnorderedListADT<Integer> list = (UnorderedListADT<Integer>) newList(); 
		list.addToFront(ELEMENT_A);
		return list;
	}

	/** Run all tests on scenario: empty list -> addToFront(A) -> [A] */
	private void test_emptyList_addToFrontA_A() {
		// recommended test naming: start_change_result_testName
		// e.g. A_addToFront_BA_testSize
		// AB_addC1_ACB_testFirst
		// A_remove0_empty_testLast

		//try-catch is necessary to prevent an Exception from the scenario builder method from bringing
		//down the whole test suite
		try {
			// ListADT
			System.out.println("empty list -> addToFront(A) -> [A]");
			printTest("emptyList_addToFrontA_A_testRemoveFirst", testRemoveFirst(emptyList_addToFrontA_A(), ELEMENT_A, Result.MatchingValue));
			printTest("emptyList_addToFrontA_A_testRemoveLast", testRemoveLast(emptyList_addToFrontA_A(), ELEMENT_A, Result.MatchingValue));
			printTest("emptyList_addToFrontA_A_testRemoveA", testRemoveElement(emptyList_addToFrontA_A(), ELEMENT_A, Result.MatchingValue));
			printTest("emptyList_addToFrontA_A_testRemoveB", testRemoveElement(emptyList_addToFrontA_A(), ELEMENT_B, Result.ElementNotFound));
			printTest("emptyList_addToFrontA_A_testFirst", testFirst(emptyList_addToFrontA_A(), ELEMENT_A, Result.MatchingValue));
			printTest("emptyList_addToFrontA_A_testLast", testLast(emptyList_addToFrontA_A(), ELEMENT_A, Result.MatchingValue));
			printTest("emptyList_addToFrontA_A_testContainsA", testContains(emptyList_addToFrontA_A(), ELEMENT_A, Result.True));
			printTest("emptyList_addToFrontA_A_testContainsB", testContains(emptyList_addToFrontA_A(), ELEMENT_B, Result.False));
			printTest("emptyList_addToFrontA_A_testIsEmpty", testIsEmpty(emptyList_addToFrontA_A(), Result.False));
			printTest("emptyList_addToFrontA_A_testSize", testSize(emptyList_addToFrontA_A(), 1));
			printTest("emptyList_addToFrontA_A_testToString", testToString(emptyList_addToFrontA_A(), Result.ValidString));
			// UnorderedListADT
			printTest("emptyList_addToFrontA_A_testAddToFrontB", testAddToFront(emptyList_addToFrontA_A(), ELEMENT_B, Result.NoException));
			printTest("emptyList_addToFrontA_A_testAddToRearB", testAddToRear(emptyList_addToFrontA_A(), ELEMENT_A, Result.NoException));
			printTest(	"emptyList_addToFrontA_A_testAddAfterCB", testAddAfter(emptyList_addToFrontA_A(), ELEMENT_C, ELEMENT_B, Result.ElementNotFound));
			printTest(	"emptyList_addToFrontA_A_testAddAfterAB", testAddAfter(emptyList_addToFrontA_A(), ELEMENT_A, ELEMENT_B, Result.NoException));
			// IndexedListADT
			printTest("emptyList_addToFrontA_A_testAddAtIndexNeg1B", testAddAtIndex(emptyList_addToFrontA_A(), -1, ELEMENT_B, Result.IndexOutOfBounds));
			printTest("emptyList_addToFrontA_A_testAddAtIndex0B", testAddAtIndex(emptyList_addToFrontA_A(), 0, ELEMENT_B, Result.NoException));
			printTest("emptyList_addToFrontA_A_testAddAtIndex1B", testAddAtIndex(emptyList_addToFrontA_A(), 1, ELEMENT_B, Result.NoException));
			printTest("emptyList_addToFrontA_A_testSetNeg1B", testSet(emptyList_addToFrontA_A(), -1, ELEMENT_B, Result.IndexOutOfBounds));
			printTest("emptyList_addToFrontA_A_testSet0B", testSet(emptyList_addToFrontA_A(), 0, ELEMENT_B, Result.NoException));
			printTest("emptyList_addToFrontA_A_testAddB", testAdd(emptyList_addToFrontA_A(), ELEMENT_B, Result.NoException));
			printTest("emptyList_addToFrontA_A_testGetNeg1", testGet(emptyList_addToFrontA_A(), -1, null, Result.IndexOutOfBounds));
			printTest("emptyList_addToFrontA_A_testGet0", testGet(emptyList_addToFrontA_A(), 0, ELEMENT_A, Result.MatchingValue));
			printTest("emptyList_addToFrontA_A_testIndexOfA", testIndexOf(emptyList_addToFrontA_A(), ELEMENT_A, 0));
			printTest("emptyList_addToFrontA_A_testIndexOfB", testIndexOf(emptyList_addToFrontA_A(), ELEMENT_B, -1));
			printTest("emptyList_addToFrontA_A_testRemoveNeg1", testRemoveIndex(emptyList_addToFrontA_A(), -1, null, Result.IndexOutOfBounds));
			printTest("emptyList_addToFrontA_A_testRemove0", testRemoveIndex(emptyList_addToFrontA_A(), 0, ELEMENT_A, Result.MatchingValue));
			printTest("emptyList_addToFrontA_A_testRemove1", testRemoveIndex(emptyList_addToFrontA_A(), 1, null, Result.IndexOutOfBounds));
			// Iterator
			printTest("emptyList_addToFrontA_A_testIterator", testIterator(emptyList_addToFrontA_A(), Result.NoException));
			printTest("emptyList_addToFrontA_A_testIteratorHasNext", testIteratorHasNext(emptyList_addToFrontA_A().iterator(), Result.True));
			printTest("emptyList_addToFrontA_A_testIteratorNext", testIteratorNext(emptyList_addToFrontA_A().iterator(), ELEMENT_A, Result.MatchingValue));
			printTest("emptyList_addToFrontA_A_testIteratorRemove", testIteratorRemove(emptyList_addToFrontA_A().iterator(), Result.IllegalState));
			printTest("emptyList_addToFrontA_A_iteratorNext_testIteratorHasNext", testIteratorHasNext(iteratorAfterNext(emptyList_addToFrontA_A(), 1), Result.False));
			printTest("emptyList_addToFrontA_A_iteratorNext_testIteratorNext", testIteratorNext(iteratorAfterNext(emptyList_addToFrontA_A(), 1), null, Result.NoSuchElement));
			printTest("emptyList_addToFrontA_A_iteratorNext_testIteratorRemove", testIteratorRemove(iteratorAfterNext(emptyList_addToFrontA_A(), 1), Result.NoException));
			printTest("emptyList_addToFrontA_A_iteratorNextRemove_testIteratorHasNext", testIteratorHasNext(iteratorAfterRemove(iteratorAfterNext(emptyList_addToFrontA_A(), 1)), Result.False));
			printTest("emptyList_addToFrontA_A_iteratorNextRemove_testIteratorNext", testIteratorNext(iteratorAfterRemove(iteratorAfterNext(emptyList_addToFrontA_A(), 1)), null, Result.NoSuchElement));
			printTest("emptyList_addToFrontA_A_iteratorNextRemove_testIteratorRemove", testIteratorRemove(iteratorAfterRemove(iteratorAfterNext(emptyList_addToFrontA_A(), 1)), Result.IllegalState));
			System.out.println();
		} catch (Exception e) {
			System.out.printf("***UNABLE TO RUN/COMPLETE %s***\n", "test_emptyList_addToFrontA_A");
			e.printStackTrace();
		}
	}
	
	////////////////////////////////////////////////
	// SCENARIO: [ ] -> addToRear(A) -> [A]
	//  XXX Scenario 3
	////////////////////////////////////////////////
	
	/** Scenario: empty list -> addToRear(A) -> [A] 
	 * @return [A] after addToRear(A)
	 */
	private  ListADT<Integer> emptyList_addToRearA_A() {
		// cast to UnorderedListADT to make addToFront() available
		UnorderedListADT<Integer> list = (UnorderedListADT<Integer>) newList(); 
		list.addToRear(ELEMENT_A);
		return list;
	}

	/** Run all tests on scenario: empty list -> addToRear(A) -> [A] */
	private void test_emptyList_addToRearA_A() {
		// recommended test naming: start_change_result_testName
		// e.g. A_addToFront_BA_testSize
		// AB_addC1_ACB_testFirst
		// A_remove0_empty_testLast

		//try-catch is necessary to prevent an Exception from the scenario builder method from bringing
		//down the whole test suite
		try {
			// ListADT
			System.out.println("empty list -> addToRear(A) -> [A]");
			printTest("emptyList_addToRearA_A_testRemoveFirst", testRemoveFirst(emptyList_addToRearA_A(), ELEMENT_A, Result.MatchingValue));
			printTest("emptyList_addToRearA_A_testRemoveLast", testRemoveLast(emptyList_addToRearA_A(), ELEMENT_A, Result.MatchingValue));
			printTest("emptyList_addToRearA_A_testRemoveA", testRemoveElement(emptyList_addToRearA_A(), ELEMENT_A, Result.MatchingValue));
			printTest("emptyList_addToRearA_A_testRemoveB", testRemoveElement(emptyList_addToRearA_A(), ELEMENT_B, Result.ElementNotFound));
			printTest("emptyList_addToRearA_A_testFirst", testFirst(emptyList_addToRearA_A(), ELEMENT_A, Result.MatchingValue));
			printTest("emptyList_addToRearA_A_testLast", testLast(emptyList_addToRearA_A(), ELEMENT_A, Result.MatchingValue));
			printTest("emptyList_addToRearA_A_testContainsA", testContains(emptyList_addToRearA_A(), ELEMENT_A, Result.True));
			printTest("emptyList_addToRearA_A_testContainsB", testContains(emptyList_addToRearA_A(), ELEMENT_B, Result.False));
			printTest("emptyList_addToRearA_A_testIsEmpty", testIsEmpty(emptyList_addToRearA_A(), Result.False));
			printTest("emptyList_addToRearA_A_testSize", testSize(emptyList_addToRearA_A(), 1));
			printTest("emptyList_addToRearA_A_testToString", testToString(emptyList_addToRearA_A(), Result.ValidString));
			// UnorderedListADT
			printTest("emptyList_addToRearA_A_testAddToFrontB", testAddToFront(emptyList_addToRearA_A(), ELEMENT_B, Result.NoException));
			printTest("emptyList_addToRearA_A_testAddToRearB", testAddToRear(emptyList_addToRearA_A(), ELEMENT_A, Result.NoException));
			printTest(	"emptyList_addToRearA_A_testAddAfterCB", testAddAfter(emptyList_addToRearA_A(), ELEMENT_C, ELEMENT_B, Result.ElementNotFound));
			printTest(	"emptyList_addToRearA_A_testAddAfterAB", testAddAfter(emptyList_addToRearA_A(), ELEMENT_A, ELEMENT_B, Result.NoException));
			// IndexedListADT
			printTest("emptyList_addToRearA_A_testAddAtIndexNeg1B", testAddAtIndex(emptyList_addToRearA_A(), -1, ELEMENT_B, Result.IndexOutOfBounds));
			printTest("emptyList_addToRearA_A_testAddAtIndex0B", testAddAtIndex(emptyList_addToRearA_A(), 0, ELEMENT_B, Result.NoException));
			printTest("emptyList_addToRearA_A_testAddAtIndex1B", testAddAtIndex(emptyList_addToRearA_A(), 1, ELEMENT_B, Result.NoException));
			printTest("emptyList_addToRearA_A_testSetNeg1B", testSet(emptyList_addToRearA_A(), -1, ELEMENT_B, Result.IndexOutOfBounds));
			printTest("emptyList_addToRearA_A_testSet0B", testSet(emptyList_addToRearA_A(), 0, ELEMENT_B, Result.NoException));
			printTest("emptyList_addToRearA_A_testAddB", testAdd(emptyList_addToRearA_A(), ELEMENT_B, Result.NoException));
			printTest("emptyList_addToRearA_A_testGetNeg1", testGet(emptyList_addToRearA_A(), -1, null, Result.IndexOutOfBounds));
			printTest("emptyList_addToRearA_A_testGet0", testGet(emptyList_addToRearA_A(), 0, ELEMENT_A, Result.MatchingValue));
			printTest("emptyList_addToRearA_A_testIndexOfA", testIndexOf(emptyList_addToRearA_A(), ELEMENT_A, 0));
			printTest("emptyList_addToRearA_A_testIndexOfB", testIndexOf(emptyList_addToRearA_A(), ELEMENT_B, -1));
			printTest("emptyList_addToRearA_A_testRemoveNeg1", testRemoveIndex(emptyList_addToRearA_A(), -1, null, Result.IndexOutOfBounds));
			printTest("emptyList_addToRearA_A_testRemove0", testRemoveIndex(emptyList_addToRearA_A(), 0, ELEMENT_A, Result.MatchingValue));
			printTest("emptyList_addToRearA_A_testRemove1", testRemoveIndex(emptyList_addToRearA_A(), 1, null, Result.IndexOutOfBounds));
			// Iterator
			printTest("emptyList_addToRearA_A_testIterator", testIterator(emptyList_addToRearA_A(), Result.NoException));
			printTest("emptyList_addToRearA_A_testIteratorHasNext", testIteratorHasNext(emptyList_addToRearA_A().iterator(), Result.True));
			printTest("emptyList_addToRearA_A_testIteratorNext", testIteratorNext(emptyList_addToFrontA_A().iterator(), ELEMENT_A, Result.MatchingValue));
			printTest("emptyList_addToRearA_A_testIteratorRemove", testIteratorRemove(emptyList_addToFrontA_A().iterator(), Result.IllegalState));
			printTest("emptyList_addToRearA_A_iteratorNext_testIteratorHasNext", testIteratorHasNext(iteratorAfterNext(emptyList_addToRearA_A(), 1), Result.False));
			printTest("emptyList_addToRearA_A_iteratorNext_testIteratorNext", testIteratorNext(iteratorAfterNext(emptyList_addToRearA_A(), 1), null, Result.NoSuchElement));
			printTest("emptyList_addToRearA_A_iteratorNext_testIteratorRemove", testIteratorRemove(iteratorAfterNext(emptyList_addToRearA_A(), 1), Result.NoException));
			printTest("emptyList_addToRearA_A_iteratorNextRemove_testIteratorHasNext", testIteratorHasNext(iteratorAfterRemove(iteratorAfterNext(emptyList_addToRearA_A(), 1)), Result.False));
			printTest("emptyList_addToRearA_A_iteratorNextRemove_testIteratorNext", testIteratorNext(iteratorAfterRemove(iteratorAfterNext(emptyList_addToRearA_A(), 1)), null, Result.NoSuchElement));
			printTest("emptyList_addToRearA_A_iteratorNextRemove_testIteratorRemove", testIteratorRemove(iteratorAfterRemove(iteratorAfterNext(emptyList_addToRearA_A(), 1)), Result.IllegalState));
			System.out.println();
		} catch (Exception e) {
			System.out.printf("***UNABLE TO RUN/COMPLETE %s***\n", "test_emptyList_addToFrontA_A");
			e.printStackTrace();
		}
	}
	
	////////////////////////////////////////////////
	// SCENARIO: [ ] -> add(A) -> [A]
	//  XXX Scenario 4
	////////////////////////////////////////////////
	
	/** Scenario: empty list -> add(A) -> [A] 
	 * @return [A] after add(A)
	 */
	private  ListADT<Integer> emptyList_addA_A() {
		// cast to UnorderedListADT to make addToFront() available
		IndexedListADT<Integer> list = (IndexedListADT<Integer>) newList(); 
		list.add(ELEMENT_A);
		return list;
	}

	/** Run all tests on scenario: empty list -> add(A) -> [A] */
	private void test_emptyList_addA_A() {
		// recommended test naming: start_change_result_testName
		// e.g. A_addToFront_BA_testSize
		// AB_addC1_ACB_testFirst
		// A_remove0_empty_testLast

		//try-catch is necessary to prevent an Exception from the scenario builder method from bringing
		//down the whole test suite
		try {
			// ListADT
			System.out.println("empty list -> add(A) -> [A]");
			printTest("emptyList_addA_A_testRemoveFirst", testRemoveFirst(emptyList_addA_A(), ELEMENT_A, Result.MatchingValue));
			printTest("emptyList_addA_A_testRemoveLast", testRemoveLast(emptyList_addA_A(), ELEMENT_A, Result.MatchingValue));
			printTest("emptyList_addA_A_testRemoveA", testRemoveElement(emptyList_addA_A(), ELEMENT_A, Result.MatchingValue));
			printTest("emptyList_addA_A_testRemoveB", testRemoveElement(emptyList_addA_A(), ELEMENT_B, Result.ElementNotFound));
			printTest("emptyList_addA_A_testFirst", testFirst(emptyList_addA_A(), ELEMENT_A, Result.MatchingValue));
			printTest("emptyList_addA_A_testLast", testLast(emptyList_addA_A(), ELEMENT_A, Result.MatchingValue));
			printTest("emptyList_addA_A_testContainsA", testContains(emptyList_addA_A(), ELEMENT_A, Result.True));
			printTest("emptyList_addA_A_testContainsB", testContains(emptyList_addA_A(), ELEMENT_B, Result.False));
			printTest("emptyList_addA_A_testIsEmpty", testIsEmpty(emptyList_addA_A(), Result.False));
			printTest("emptyList_addA_A_testSize", testSize(emptyList_addA_A(), 1));
			printTest("emptyList_addA_A_testToString", testToString(emptyList_addA_A(), Result.ValidString));
			// UnorderedListADT
			printTest("emptyList_addA_A_testAddToFrontB", testAddToFront(emptyList_addA_A(), ELEMENT_B, Result.NoException));
			printTest("emptyList_addA_A_testAddToRearB", testAddToRear(emptyList_addA_A(), ELEMENT_A, Result.NoException));
			printTest("emptyList_addA_A_testAddAfterCB", testAddAfter(emptyList_addA_A(), ELEMENT_C, ELEMENT_B, Result.ElementNotFound));
			printTest("emptyList_addA_A_testAddAfterAB", testAddAfter(emptyList_addA_A(), ELEMENT_A, ELEMENT_B, Result.NoException));
			// IndexedListADT
			printTest("emptyList_addA_A_testAddAtIndexNeg1B", testAddAtIndex(emptyList_addA_A(), -1, ELEMENT_B, Result.IndexOutOfBounds));
			printTest("emptyList_addA_A_testAddAtIndex0B", testAddAtIndex(emptyList_addA_A(), 0, ELEMENT_B, Result.NoException));
			printTest("emptyList_addA_A_testAddAtIndex1B", testAddAtIndex(emptyList_addA_A(), 1, ELEMENT_B, Result.NoException));
			printTest("emptyList_addA_A_testSetNeg1B", testSet(emptyList_addA_A(), -1, ELEMENT_B, Result.IndexOutOfBounds));
			printTest("emptyList_addA_A_testSet0B", testSet(emptyList_addA_A(), 0, ELEMENT_B, Result.NoException));
			printTest("emptyList_addA_A_testAddB", testAdd(emptyList_addA_A(), ELEMENT_B, Result.NoException));
			printTest("emptyList_addA_A_testGetNeg1", testGet(emptyList_addA_A(), -1, null, Result.IndexOutOfBounds));
			printTest("emptyList_addA_A_testGet0", testGet(emptyList_addA_A(), 0, ELEMENT_A, Result.MatchingValue));
			printTest("emptyList_addA_A_testIndexOfA", testIndexOf(emptyList_addA_A(), ELEMENT_A, 0));
			printTest("emptyList_addA_A_testIndexOfB", testIndexOf(emptyList_addA_A(), ELEMENT_B, -1));
			printTest("emptyList_addA_A_testRemoveNeg1", testRemoveIndex(emptyList_addA_A(), -1, null, Result.IndexOutOfBounds));
			printTest("emptyList_addA_A_testRemove0", testRemoveIndex(emptyList_addA_A(), 0, ELEMENT_A, Result.MatchingValue));
			printTest("emptyList_addA_A_testRemove1", testRemoveIndex(emptyList_addA_A(), 1, null, Result.IndexOutOfBounds));
			// Iterator
			printTest("emptyList_addA_A_testIterator", testIterator(emptyList_addA_A(), Result.NoException));
			printTest("emptyList_addA_A_testIteratorHasNext", testIteratorHasNext(emptyList_addA_A().iterator(), Result.True));
			printTest("emptyList_addA_A_testIteratorNext", testIteratorNext(emptyList_addA_A().iterator(), ELEMENT_A, Result.MatchingValue));
			printTest("emptyList_addA_A_testIteratorRemove", testIteratorRemove(emptyList_addA_A().iterator(), Result.IllegalState));
			printTest("emptyList_addA_A_iteratorNext_testIteratorHasNext", testIteratorHasNext(iteratorAfterNext(emptyList_addA_A(), 1), Result.False));
			printTest("emptyList_addA_A_iteratorNext_testIteratorNext", testIteratorNext(iteratorAfterNext(emptyList_addA_A(), 1), null, Result.NoSuchElement));
			printTest("emptyList_addA_A_iteratorNext_testIteratorRemove", testIteratorRemove(iteratorAfterNext(emptyList_addA_A(), 1), Result.NoException));
			printTest("emptyList_addA_A_iteratorNextRemove_testIteratorHasNext", testIteratorHasNext(iteratorAfterRemove(iteratorAfterNext(emptyList_addA_A(), 1)), Result.False));
			printTest("emptyList_addA_A_iteratorNextRemove_testIteratorNext", testIteratorNext(iteratorAfterRemove(iteratorAfterNext(emptyList_addA_A(), 1)), null, Result.NoSuchElement));
			printTest("emptyList_addA_A_iteratorNextRemove_testIteratorRemove", testIteratorRemove(iteratorAfterRemove(iteratorAfterNext(emptyList_addA_A(), 1)), Result.IllegalState));
			System.out.println();
		} catch (Exception e) {
			System.out.printf("***UNABLE TO RUN/COMPLETE %s***\n", "test_emptyList_addToFrontA_A");
			e.printStackTrace();
		}
	}
	
	////////////////////////////////////////////////
	// SCENARIO: [ ] -> add(A) -> [A]
	//  XXX Scenario 5
	////////////////////////////////////////////////
	
	/** Scenario: empty list -> add(0,A) -> [A] 
	 * @return [A] after add(0,A)
	 */
	private  ListADT<Integer> emptyList_addA0_A() {
		// cast to UnorderedListADT to make addToFront() available
		IndexedListADT<Integer> list = (IndexedListADT<Integer>) newList(); 
		list.add(0, ELEMENT_A);
		return list;
	}

	/** Run all tests on scenario: empty list -> add(0,A) -> [A] */
	private void test_emptyList_addA0_A() {
		// recommended test naming: start_change_result_testName
		// e.g. A_addToFront_BA_testSize
		// AB_addC1_ACB_testFirst
		// A_remove0_empty_testLast

		//try-catch is necessary to prevent an Exception from the scenario builder method from bringing
		//down the whole test suite
		try {
			// ListADT
			System.out.println("empty list -> add(0,A) -> [A]");
			printTest("emptyList_addA0_A_testRemoveFirst", testRemoveFirst(emptyList_addA0_A(), ELEMENT_A, Result.MatchingValue));
			printTest("emptyList_addA0_A_testRemoveLast", testRemoveLast(emptyList_addA0_A(), ELEMENT_A, Result.MatchingValue));
			printTest("emptyList_addA0_A_testRemoveA", testRemoveElement(emptyList_addA0_A(), ELEMENT_A, Result.MatchingValue));
			printTest("emptyList_addA0_A_testRemoveB", testRemoveElement(emptyList_addA0_A(), ELEMENT_B, Result.ElementNotFound));
			printTest("emptyList_addA0_A_testFirst", testFirst(emptyList_addA0_A(), ELEMENT_A, Result.MatchingValue));
			printTest("emptyList_addA0_A_testLast", testLast(emptyList_addA0_A(), ELEMENT_A, Result.MatchingValue));
			printTest("emptyList_addA0_A_testContainsA", testContains(emptyList_addA0_A(), ELEMENT_A, Result.True));
			printTest("emptyList_addA0_A_testContainsB", testContains(emptyList_addA0_A(), ELEMENT_B, Result.False));
			printTest("emptyList_addA0_A_testIsEmpty", testIsEmpty(emptyList_addA0_A(), Result.False));
			printTest("emptyList_addA0_A_testSize", testSize(emptyList_addA0_A(), 1));
			printTest("emptyList_addA0_A_testToString", testToString(emptyList_addA0_A(), Result.ValidString));
			// UnorderedListADT
			printTest("emptyList_addA0_A_testAddToFrontB", testAddToFront(emptyList_addA0_A(), ELEMENT_B, Result.NoException));
			printTest("emptyList_addA0_A_testAddToRearB", testAddToRear(emptyList_addA0_A(), ELEMENT_A, Result.NoException));
			printTest("emptyList_addA0_A_testAddAfterCB", testAddAfter(emptyList_addA0_A(), ELEMENT_C, ELEMENT_B, Result.ElementNotFound));
			printTest("emptyList_addA0_A_testAddAfterAB", testAddAfter(emptyList_addA0_A(), ELEMENT_A, ELEMENT_B, Result.NoException));
			// IndexedListADT
			printTest("emptyList_addA0_A_testAddAtIndexNeg1B", testAddAtIndex(emptyList_addA0_A(), -1, ELEMENT_B, Result.IndexOutOfBounds));
			printTest("emptyList_addA0_A_testAddAtIndex0B", testAddAtIndex(emptyList_addA0_A(), 0, ELEMENT_B, Result.NoException));
			printTest("emptyList_addA0_A_testAddAtIndex1B", testAddAtIndex(emptyList_addA0_A(), 1, ELEMENT_B, Result.NoException));
			printTest("emptyList_addA0_A_testSetNeg1B", testSet(emptyList_addA0_A(), -1, ELEMENT_B, Result.IndexOutOfBounds));
			printTest("emptyList_addA0_A_testSet0B", testSet(emptyList_addA0_A(), 0, ELEMENT_B, Result.NoException));
			printTest("emptyList_addA0_A_testAddB", testAdd(emptyList_addA0_A(), ELEMENT_B, Result.NoException));
			printTest("emptyList_addA0_A_testGetNeg1", testGet(emptyList_addA0_A(), -1, null, Result.IndexOutOfBounds));
			printTest("emptyList_addA0_A_testGet0", testGet(emptyList_addA0_A(), 0, ELEMENT_A, Result.MatchingValue));
			printTest("emptyList_addA0_A_testIndexOfA", testIndexOf(emptyList_addA0_A(), ELEMENT_A, 0));
			printTest("emptyList_addA0_A_testIndexOfB", testIndexOf(emptyList_addA0_A(), ELEMENT_B, -1));
			printTest("emptyList_addA0_A_testRemoveNeg1", testRemoveIndex(emptyList_addA0_A(), -1, null, Result.IndexOutOfBounds));
			printTest("emptyList_addA0_A_testRemove0", testRemoveIndex(emptyList_addA0_A(), 0, ELEMENT_A, Result.MatchingValue));
			printTest("emptyList_addA0_A_testRemove1", testRemoveIndex(emptyList_addA0_A(), 1, null, Result.IndexOutOfBounds));
			// Iterator
			printTest("emptyList_addA0_A_testIterator", testIterator(emptyList_addA0_A(), Result.NoException));
			printTest("emptyList_addA0_A_testIteratorHasNext", testIteratorHasNext(emptyList_addA0_A().iterator(), Result.True));
			printTest("emptyList_addA0_A_testIteratorNext", testIteratorNext(emptyList_addA0_A().iterator(), ELEMENT_A, Result.MatchingValue));
			printTest("emptyList_addA0_A_testIteratorRemove", testIteratorRemove(emptyList_addA0_A().iterator(), Result.IllegalState));
			printTest("emptyList_addA0_A_iteratorNext_testIteratorHasNext", testIteratorHasNext(iteratorAfterNext(emptyList_addA0_A(), 1), Result.False));
			printTest("emptyList_addA0_A_iteratorNext_testIteratorNext", testIteratorNext(iteratorAfterNext(emptyList_addA0_A(), 1), null, Result.NoSuchElement));
			printTest("emptyList_addA0_A_iteratorNext_testIteratorRemove", testIteratorRemove(iteratorAfterNext(emptyList_addA0_A(), 1), Result.NoException));
			printTest("emptyList_addA0_A_iteratorNextRemove_testIteratorHasNext", testIteratorHasNext(iteratorAfterRemove(iteratorAfterNext(emptyList_addA0_A(), 1)), Result.False));
			printTest("emptyList_addA0_A_iteratorNextRemove_testIteratorNext", testIteratorNext(iteratorAfterRemove(iteratorAfterNext(emptyList_addA0_A(), 1)), null, Result.NoSuchElement));
			printTest("emptyList_addA0_A_iteratorNextRemove_testIteratorRemove", testIteratorRemove(iteratorAfterRemove(iteratorAfterNext(emptyList_addA0_A(), 1)), Result.IllegalState));
			System.out.println();
		} catch (Exception e) {
			System.out.printf("***UNABLE TO RUN/COMPLETE %s***\n", "test_emptyList_addToFrontA_A");
			e.printStackTrace();
		}
	}
	
	////////////////////////////////////////////////
	// SCENARIO: [A] -> removeFirst() -> [ ]
	//  XXX Scenario 6
	////////////////////////////////////////////////
	
	/** Scenario: [A] -> removeFirst() -> empty list 
	 * @return [ ] after removeFirst()
	 */
	private  ListADT<Integer> A_removeFirst_emptyList() {
		// cast to UnorderedListADT to make addToFront() available
		IndexedListADT<Integer> list = (IndexedListADT<Integer>) newList(); 
		list.add(ELEMENT_A);
		list.removeFirst();
		return list;
	}

	/** Run all tests on scenario: [A] -> removeFirst() -> empty list */
	private void test_A_removeFirst_emptyList() {
		// recommended test naming: start_change_result_testName
		// e.g. A_addToFront_BA_testSize
		// AB_addC1_ACB_testFirst
		// A_remove0_empty_testLast

		//try-catch is necessary to prevent an Exception from the scenario builder method from bringing
		//down the whole test suite
		try {
			// ListADT
			System.out.println("[A] -> removeFirst() -> empty list");
			printTest("A_removeFirst_emptyList_testRemoveFirst", testRemoveFirst(A_removeFirst_emptyList(), null, Result.EmptyCollection));
			printTest("A_removeFirst_emptyList_testRemoveLast", testRemoveLast(A_removeFirst_emptyList(), null, Result.EmptyCollection));
			printTest("A_removeFirst_emptyList_testRemoveA", testRemoveElement(A_removeFirst_emptyList(), ELEMENT_A, Result.ElementNotFound));
			printTest("A_removeFirst_emptyList_testRemoveB", testRemoveElement(A_removeFirst_emptyList(), ELEMENT_B, Result.ElementNotFound));
			printTest("A_removeFirst_emptyList_testFirst", testFirst(A_removeFirst_emptyList(), null, Result.EmptyCollection));
			printTest("A_removeFirst_emptyList_testLast", testLast(A_removeFirst_emptyList(), null, Result.EmptyCollection));
			printTest("A_removeFirst_emptyList_testContainsA", testContains(A_removeFirst_emptyList(), ELEMENT_A, Result.False));
			printTest("A_removeFirst_emptyList_testContainsB", testContains(A_removeFirst_emptyList(), ELEMENT_B, Result.False));
			printTest("A_removeFirst_emptyList_testIsEmpty", testIsEmpty(A_removeFirst_emptyList(), Result.True));
			printTest("A_removeFirst_emptyList_testSize", testSize(A_removeFirst_emptyList(), 0));
			printTest("A_removeFirst_emptyList_testToString", testToString(A_removeFirst_emptyList(), Result.ValidString));
			// UnorderedListADT
			printTest("A_removeFirst_emptyList_testAddToFrontB", testAddToFront(A_removeFirst_emptyList(), ELEMENT_B, Result.NoException));
			printTest("A_removeFirst_emptyList_testAddToRearB", testAddToRear(A_removeFirst_emptyList(), ELEMENT_A, Result.NoException));
			printTest("A_removeFirst_emptyList_testAddAfterCB", testAddAfter(A_removeFirst_emptyList(), ELEMENT_C, ELEMENT_B, Result.ElementNotFound));
			printTest("A_removeFirst_emptyList_testAddAfterAB", testAddAfter(A_removeFirst_emptyList(), ELEMENT_A, ELEMENT_B, Result.ElementNotFound));
			// IndexedListADT
			printTest("A_removeFirst_emptyList_testAddAtIndexNeg1B", testAddAtIndex(A_removeFirst_emptyList(), -1, ELEMENT_B, Result.IndexOutOfBounds));
			printTest("A_removeFirst_emptyList_testAddAtIndex0B", testAddAtIndex(A_removeFirst_emptyList(), 0, ELEMENT_B, Result.NoException));
			printTest("A_removeFirst_emptyList_testAddAtIndex1B", testAddAtIndex(A_removeFirst_emptyList(), 1, ELEMENT_B, Result.IndexOutOfBounds));
			printTest("A_removeFirst_emptyList_testSetNeg1B", testSet(A_removeFirst_emptyList(), -1, ELEMENT_B, Result.IndexOutOfBounds));
			printTest("A_removeFirst_emptyList_testSet0B", testSet(A_removeFirst_emptyList(), 0, ELEMENT_B, Result.IndexOutOfBounds));
			printTest("A_removeFirst_emptyList_testAddB", testAdd(A_removeFirst_emptyList(), ELEMENT_B, Result.NoException));
			printTest("A_removeFirst_emptyList_testGetNeg1", testGet(A_removeFirst_emptyList(), -1, null, Result.IndexOutOfBounds));
			printTest("A_removeFirst_emptyList_testGet0", testGet(A_removeFirst_emptyList(), 0, null, Result.IndexOutOfBounds));
			printTest("A_removeFirst_emptyList_testIndexOfA", testIndexOf(A_removeFirst_emptyList(), ELEMENT_A, -1));
			printTest("A_removeFirst_emptyList_testIndexOfB", testIndexOf(A_removeFirst_emptyList(), ELEMENT_B, -1));
			printTest("A_removeFirst_emptyList_testRemoveNeg1", testRemoveIndex(A_removeFirst_emptyList(), -1, null, Result.IndexOutOfBounds));
			printTest("A_removeFirst_emptyList_testRemove0", testRemoveIndex(A_removeFirst_emptyList(), 0, null, Result.IndexOutOfBounds));
			printTest("A_removeFirst_emptyList_testRemove1", testRemoveIndex(A_removeFirst_emptyList(), 1, null, Result.IndexOutOfBounds));
			//Iterators
			printTest("A_removeFirst_emptyList_testIterator", testIterator(A_removeFirst_emptyList(), Result.NoException));
			printTest("A_removeFirst_emptyList_testIteratorHasNext", testIteratorHasNext(A_removeFirst_emptyList().iterator(), Result.False));
			printTest("A_removeFirst_emptyList_testIteratorNext", testIteratorNext(A_removeFirst_emptyList().iterator(), null, Result.NoSuchElement));
			printTest("A_removeFirst_emptyList_testIteratorRemove", testIteratorRemove(A_removeFirst_emptyList().iterator(), Result.IllegalState));
			printTest("A_removeFirst_emptyList_iteratorNext_testIteratorHasNext", testIteratorHasNext(iteratorAfterNext(A_removeFirst_emptyList(), 0), Result.False));
//			printTest("A_removeFirst_emptyList_iteratorNext_testIteratorNext", testIteratorNext(iteratorAfterNext(A_removeFirst_emptyList(), 1), null, Result.NoSuchElement));
//			printTest("A_removeFirst_emptyList_iteratorNext_testIteratorRemove", testIteratorRemove(iteratorAfterNext(A_removeFirst_emptyList(), 1), Result.NoException));
//			printTest("A_removeFirst_emptyList_iteratorNextRemove_testIteratorHasNext", testIteratorHasNext(iteratorAfterRemove(iteratorAfterNext(A_removeFirst_emptyList(), 1)), Result.False));
//			printTest("A_removeFirst_emptyList_iteratorNextRemove_testIteratorNext", testIteratorNext(iteratorAfterRemove(iteratorAfterNext(A_removeFirst_emptyList(), 1)), null, Result.NoSuchElement));
//			printTest("A_removeFirst_emptyList_iteratorNextRemove_testIteratorRemove", testIteratorRemove(iteratorAfterRemove(iteratorAfterNext(A_removeFirst_emptyList(), 1)), Result.IllegalState));
		
			
			System.out.println();
		} catch (Exception e) {
			System.out.printf("***UNABLE TO RUN/COMPLETE %s***\n", "test_emptyList_addToFrontA_A");
			e.printStackTrace();
		}
	}	
	
	////////////////////////////////////////////////
	// SCENARIO: [A] -> removeLast() -> [ ]
	//  XXX Scenario 7
	////////////////////////////////////////////////
	
	/** Scenario: [A] -> removeLast() -> empty list 
	 * @return [ ] after removeLast()
	 */
	private  ListADT<Integer> A_removeLast_emptyList() {
		// cast to UnorderedListADT to make addToFront() available
		IndexedListADT<Integer> list = (IndexedListADT<Integer>) newList(); 
		list.add(ELEMENT_A);
		list.removeLast();
		return list;
	}

	/** Run all tests on scenario: [A] -> removeLast() -> empty list */
	private void test_A_removeLast_emptyList() {
		// recommended test naming: start_change_result_testName
		// e.g. A_addToFront_BA_testSize
		// AB_addC1_ACB_testFirst
		// A_remove0_empty_testLast

		//try-catch is necessary to prevent an Exception from the scenario builder method from bringing
		//down the whole test suite
		try {
			// ListADT
			System.out.println("[A] -> removeLast() -> empty list");
			printTest("A_removeLast_emptyList_testRemoveFirst", testRemoveFirst(A_removeLast_emptyList(), null, Result.EmptyCollection));
			printTest("A_removeLast_emptyList_testRemoveLast", testRemoveLast(A_removeLast_emptyList(), null, Result.EmptyCollection));
			printTest("A_removeLast_emptyList_testRemoveA", testRemoveElement(A_removeLast_emptyList(), ELEMENT_A, Result.ElementNotFound));
			printTest("A_removeLast_emptyList_testRemoveB", testRemoveElement(A_removeLast_emptyList(), ELEMENT_B, Result.ElementNotFound));
			printTest("A_removeLast_emptyList_testFirst", testFirst(A_removeLast_emptyList(), null, Result.EmptyCollection));
			printTest("A_removeLast_emptyList_testLast", testLast(A_removeLast_emptyList(), null, Result.EmptyCollection));
			printTest("A_removeLast_emptyList_testContainsA", testContains(A_removeLast_emptyList(), ELEMENT_A, Result.False));
			printTest("A_removeLast_emptyList_testContainsB", testContains(A_removeLast_emptyList(), ELEMENT_B, Result.False));
			printTest("A_removeLast_emptyList_testIsEmpty", testIsEmpty(A_removeLast_emptyList(), Result.True));
			printTest("A_removeLast_emptyList_testSize", testSize(A_removeLast_emptyList(), 0));
			printTest("A_removeLast_emptyList_testToString", testToString(A_removeLast_emptyList(), Result.ValidString));
			// UnorderedListADT
			printTest("A_removeLast_emptyList_testAddToFrontB", testAddToFront(A_removeLast_emptyList(), ELEMENT_B, Result.NoException));
			printTest("A_removeLast_emptyList_testAddToRearB", testAddToRear(A_removeLast_emptyList(), ELEMENT_A, Result.NoException));
			printTest("A_removeLast_emptyList_testAddAfterCB", testAddAfter(A_removeLast_emptyList(), ELEMENT_C, ELEMENT_B, Result.ElementNotFound));
			printTest("A_removeLast_emptyList_testAddAfterAB", testAddAfter(A_removeLast_emptyList(), ELEMENT_A, ELEMENT_B, Result.ElementNotFound));
			// IndexedListADT
			printTest("A_removeLast_emptyList_testAddAtIndexNeg1B", testAddAtIndex(A_removeLast_emptyList(), -1, ELEMENT_B, Result.IndexOutOfBounds));
			printTest("A_removeLast_emptyList_testAddAtIndex0B", testAddAtIndex(A_removeLast_emptyList(), 0, ELEMENT_B, Result.NoException));
			printTest("A_removeLast_emptyList_testAddAtIndex1B", testAddAtIndex(A_removeLast_emptyList(), 1, ELEMENT_B, Result.IndexOutOfBounds));
			printTest("A_removeLast_emptyList_testSetNeg1B", testSet(A_removeLast_emptyList(), -1, ELEMENT_B, Result.IndexOutOfBounds));
			printTest("A_removeLast_emptyList_testSet0B", testSet(A_removeLast_emptyList(), 0, ELEMENT_B, Result.IndexOutOfBounds));
			printTest("A_removeLast_emptyList_testAddB", testAdd(A_removeLast_emptyList(), ELEMENT_B, Result.NoException));
			printTest("A_removeLast_emptyList_testGetNeg1", testGet(A_removeLast_emptyList(), -1, null, Result.IndexOutOfBounds));
			printTest("A_removeLast_emptyList_testGet0", testGet(A_removeLast_emptyList(), 0, null, Result.IndexOutOfBounds));
			printTest("test_A_removeLast_emptyList_testIndexOfA", testIndexOf(A_removeLast_emptyList(), ELEMENT_A, -1));
			printTest("test_A_removeLast_emptyList_testIndexOfB", testIndexOf(A_removeLast_emptyList(), ELEMENT_B, -1));
			printTest("test_A_removeLast_emptyList_testRemoveNeg1", testRemoveIndex(A_removeLast_emptyList(), -1, null, Result.IndexOutOfBounds));
			printTest("test_A_removeLast_emptyList_testRemove0", testRemoveIndex(A_removeLast_emptyList(), 0, null, Result.IndexOutOfBounds));
			printTest("test_A_removeLast_emptyList_testRemove1", testRemoveIndex(A_removeLast_emptyList(), 1, null, Result.IndexOutOfBounds));
			//Iterators
		/*	printTest("emptyList_addA0_A_testIterator", testIterator(A_removeFirst_emptyList(), Result.NoException));
			printTest("emptyList_addA0_A_testIteratorHasNext", testIteratorHasNext(A_removeFirst_emptyList().iterator(), Result.False));
			printTest("emptyList_addA0_A_testIteratorNext", testIteratorNext(A_removeFirst_emptyList().iterator(), null, Result.EmptyCollection));
			printTest("emptyList_addA0_A_testIteratorRemove", testIteratorRemove(A_removeFirst_emptyList().iterator(), Result.IllegalState));
			printTest("emptyList_addA0_A_iteratorNext_testIteratorHasNext", testIteratorHasNext(iteratorAfterNext(A_removeFirst_emptyList(), 0), Result.False));
			printTest("emptyList_addA0_A_iteratorNext_testIteratorNext", testIteratorNext(iteratorAfterNext(A_removeFirst_emptyList(), 1), null, Result.NoSuchElement));
			printTest("emptyList_addA0_A_iteratorNext_testIteratorRemove", testIteratorRemove(iteratorAfterNext(A_removeFirst_emptyList(), 1), Result.NoException));
			printTest("emptyList_addA0_A_iteratorNextRemove_testIteratorHasNext", testIteratorHasNext(iteratorAfterRemove(iteratorAfterNext(A_removeFirst_emptyList(), 1)), Result.False));
			printTest("emptyList_addA0_A_iteratorNextRemove_testIteratorNext", testIteratorNext(iteratorAfterRemove(iteratorAfterNext(A_removeFirst_emptyList(), 1)), null, Result.NoSuchElement));
			printTest("emptyList_addA0_A_iteratorNextRemove_testIteratorRemove", testIteratorRemove(iteratorAfterRemove(iteratorAfterNext(A_removeFirst_emptyList(), 1)), Result.IllegalState));
		*/
			
			System.out.println();
		} catch (Exception e) {
			System.out.printf("***UNABLE TO RUN/COMPLETE %s***\n", "test_emptyList_addToFrontA_A");
			e.printStackTrace();
		}
	}	
	

	////////////////////////////////////////////////
	// SCENARIO: [A] -> remove(A) -> [ ]
	//  XXX Scenario 8
	////////////////////////////////////////////////
	
	/** Scenario: [A] -> remove(A) -> empty list 
	 * @return [ ] after remove(A)
	 */
	private  ListADT<Integer> A_removeA_emptyList() {
		// cast to UnorderedListADT to make addToFront() available
		IndexedListADT<Integer> list = (IndexedListADT<Integer>) newList(); 
		list.add(ELEMENT_A);
		list.remove(ELEMENT_A);
		return list;
	}

	/** Run all tests on scenario: [A] -> remove(A) -> empty list */
	private void test_A_removeA_emptyList() {
		// recommended test naming: start_change_result_testName
		// e.g. A_addToFront_BA_testSize
		// AB_addC1_ACB_testFirst
		// A_remove0_empty_testLast

		//try-catch is necessary to prevent an Exception from the scenario builder method from bringing
		//down the whole test suite
		try {
			// ListADT
			System.out.println("[A] -> remove(A) -> empty list");
			printTest("A_removeA_emptyList_testRemoveFirst", testRemoveFirst(A_removeA_emptyList(), null, Result.EmptyCollection));
			printTest("A_removeA_emptyList_testRemoveLast", testRemoveLast(A_removeA_emptyList(), null, Result.EmptyCollection));
			printTest("A_removeA_emptyList_testRemoveA", testRemoveElement(A_removeA_emptyList(), ELEMENT_A, Result.ElementNotFound));
			printTest("A_removeA_emptyList_testRemoveB", testRemoveElement(A_removeA_emptyList(), ELEMENT_B, Result.ElementNotFound));
			printTest("A_removeA_emptyList_testFirst", testFirst(A_removeA_emptyList(), null, Result.EmptyCollection));
			printTest("A_removeA_emptyList_testLast", testLast(A_removeA_emptyList(), null, Result.EmptyCollection));
			printTest("A_removeA_emptyList_testContainsA", testContains(A_removeA_emptyList(), ELEMENT_A, Result.False));
			printTest("A_removeA_emptyList_testContainsB", testContains(A_removeA_emptyList(), ELEMENT_B, Result.False));
			printTest("A_removeA_emptyList_testIsEmpty", testIsEmpty(A_removeA_emptyList(), Result.True));
			printTest("A_removeA_emptyList_testSize", testSize(A_removeA_emptyList(), 0));
			printTest("A_removeA_emptyList_testToString", testToString(A_removeA_emptyList(), Result.ValidString));
			// UnorderedListADT
			printTest("A_removeA_emptyList_testAddToFrontB", testAddToFront(A_removeA_emptyList(), ELEMENT_B, Result.NoException));
			printTest("A_removeA_emptyList_testAddToRearB", testAddToRear(A_removeA_emptyList(), ELEMENT_A, Result.NoException));
			printTest("A_removeA_emptyList_testAddAfterCB", testAddAfter(A_removeA_emptyList(), ELEMENT_C, ELEMENT_B, Result.ElementNotFound));
			printTest("A_removeA_emptyList_testAddAfterAB", testAddAfter(A_removeA_emptyList(), ELEMENT_A, ELEMENT_B, Result.ElementNotFound));
			// IndexedListADT
			printTest("A_removeA_emptyList_testAddAtIndexNeg1B", testAddAtIndex(A_removeA_emptyList(), -1, ELEMENT_B, Result.IndexOutOfBounds));
			printTest("A_removeA_emptyList_testAddAtIndex0B", testAddAtIndex(A_removeA_emptyList(), 0, ELEMENT_B, Result.NoException));
			printTest("A_removeA_emptyList_testAddAtIndex1B", testAddAtIndex(A_removeA_emptyList(), 1, ELEMENT_B, Result.IndexOutOfBounds));
			printTest("A_removeA_emptyList_testSetNeg1B", testSet(A_removeA_emptyList(), -1, ELEMENT_B, Result.IndexOutOfBounds));
			printTest("A_removeA_emptyList_testSet0B", testSet(A_removeA_emptyList(), 0, ELEMENT_B, Result.IndexOutOfBounds));
			printTest("A_removeA_emptyList_testAddB", testAdd(A_removeA_emptyList(), ELEMENT_B, Result.NoException));
			printTest("A_removeA_emptyList_testGetNeg1", testGet(A_removeA_emptyList(), -1, null, Result.IndexOutOfBounds));
			printTest("A_removeA_emptyList_testGet0", testGet(A_removeA_emptyList(), 0, null, Result.IndexOutOfBounds));
			printTest("A_removeA_emptyList_testIndexOfA", testIndexOf(A_removeA_emptyList(), ELEMENT_A, -1));
			printTest("A_removeA_emptyList_testIndexOfB", testIndexOf(A_removeA_emptyList(), ELEMENT_B, -1));
			printTest("A_removeA_emptyList_testRemoveNeg1", testRemoveIndex(A_removeA_emptyList(), -1, null, Result.IndexOutOfBounds));
			printTest("A_removeA_emptyList_testRemove0", testRemoveIndex(A_removeA_emptyList(), 0, null, Result.IndexOutOfBounds));
			printTest("A_removeA_emptyList_testRemove1", testRemoveIndex(A_removeA_emptyList(), 1, null, Result.IndexOutOfBounds));
			//Iterators
		/*	printTest("emptyList_addA0_A_testIterator", testIterator(A_removeFirst_emptyList(), Result.NoException));
			printTest("emptyList_addA0_A_testIteratorHasNext", testIteratorHasNext(A_removeFirst_emptyList().iterator(), Result.False));
			printTest("emptyList_addA0_A_testIteratorNext", testIteratorNext(A_removeFirst_emptyList().iterator(), null, Result.EmptyCollection));
			printTest("emptyList_addA0_A_testIteratorRemove", testIteratorRemove(A_removeFirst_emptyList().iterator(), Result.IllegalState));
			printTest("emptyList_addA0_A_iteratorNext_testIteratorHasNext", testIteratorHasNext(iteratorAfterNext(A_removeFirst_emptyList(), 0), Result.False));
			printTest("emptyList_addA0_A_iteratorNext_testIteratorNext", testIteratorNext(iteratorAfterNext(A_removeFirst_emptyList(), 1), null, Result.NoSuchElement));
			printTest("emptyList_addA0_A_iteratorNext_testIteratorRemove", testIteratorRemove(iteratorAfterNext(A_removeFirst_emptyList(), 1), Result.NoException));
			printTest("emptyList_addA0_A_iteratorNextRemove_testIteratorHasNext", testIteratorHasNext(iteratorAfterRemove(iteratorAfterNext(A_removeFirst_emptyList(), 1)), Result.False));
			printTest("emptyList_addA0_A_iteratorNextRemove_testIteratorNext", testIteratorNext(iteratorAfterRemove(iteratorAfterNext(A_removeFirst_emptyList(), 1)), null, Result.NoSuchElement));
			printTest("emptyList_addA0_A_iteratorNextRemove_testIteratorRemove", testIteratorRemove(iteratorAfterRemove(iteratorAfterNext(A_removeFirst_emptyList(), 1)), Result.IllegalState));
		*/
			
			System.out.println();
		} catch (Exception e) {
			System.out.printf("***UNABLE TO RUN/COMPLETE %s***\n", "test_emptyList_addToFrontA_A");
			e.printStackTrace();
		}
	}	
	
	////////////////////////////////////////////////
	// SCENARIO: [A] -> addToFront(B) -> [B,A]
	//  XXX Scenario 9
	////////////////////////////////////////////////
	
	/** Scenario: [A] -> addToFront(B) -> [B,A] 
	 * @return [B,A] after addToFront(B)
	 */
	private  ListADT<Integer> A_addToFrontB_BA() {
		// cast to UnorderedListADT to make addToFront() available
		UnorderedListADT<Integer> list = (UnorderedListADT<Integer>) newList(); 
		list.addToRear(ELEMENT_A);
		list.addToFront(ELEMENT_B);
		return list;
	}

	/** Run all tests on scenario: [A] -> addToFront(B) -> [B,A] */
	private void test_A_addToFrontB_BA() {
		// recommended test naming: start_change_result_testName
		// e.g. A_addToFront_BA_testSize
		// AB_addC1_ACB_testFirst
		// A_remove0_empty_testLast

		//try-catch is necessary to prevent an Exception from the scenario builder method from bringing
		//down the whole test suite
		try {
			// ListADT
			System.out.println("[A] -> addToFront(B) -> [B,A]");
			printTest("A_addToFrontB_BA_testRemoveFirst", testRemoveFirst(A_addToFrontB_BA(), ELEMENT_B, Result.MatchingValue));
			printTest("A_addToFrontB_BA_testRemoveLast", testRemoveLast(A_addToFrontB_BA(), ELEMENT_A, Result.MatchingValue));
			printTest("A_addToFrontB_BA_testRemoveA", testRemoveElement(A_addToFrontB_BA(), ELEMENT_A, Result.MatchingValue));
			printTest("A_addToFrontB_BA_testRemoveB", testRemoveElement(A_addToFrontB_BA(), ELEMENT_B, Result.MatchingValue));
			printTest("A_addToFrontB_BA_testFirst", testFirst(A_addToFrontB_BA(), ELEMENT_B, Result.MatchingValue));
			printTest("A_addToFrontB_BA_testLast", testLast(A_addToFrontB_BA(), ELEMENT_A, Result.MatchingValue));
			printTest("A_addToFrontB_BA_testContainsA", testContains(A_addToFrontB_BA(), ELEMENT_A, Result.True));
			printTest("A_addToFrontB_BA_testContainsB", testContains(A_addToFrontB_BA(), ELEMENT_B, Result.True));
			printTest("A_addToFrontB_BA_testIsEmpty", testIsEmpty(A_addToFrontB_BA(), Result.False));
			printTest("A_addToFrontB_BA_testSize", testSize(A_addToFrontB_BA(), 2));
			printTest("A_addToFrontB_BA_testToString", testToString(A_addToFrontB_BA(), Result.ValidString));
			// UnorderedListADT
			printTest("A_addToFrontB_BA_testAddToFrontB", testAddToFront(A_addToFrontB_BA(), ELEMENT_B, Result.NoException));
			printTest("A_addToFrontB_BA_testAddToRearB", testAddToRear(A_addToFrontB_BA(), ELEMENT_A, Result.NoException));
			printTest("A_addToFrontB_BA_testAddAfterCB", testAddAfter(A_addToFrontB_BA(), ELEMENT_C, ELEMENT_B, Result.ElementNotFound));
			printTest("A_addToFrontB_BA_testAddAfterAB", testAddAfter(A_addToFrontB_BA(), ELEMENT_A, ELEMENT_B, Result.NoException));
			// IndexedListADT
			printTest("A_addToFrontB_BA_testAddAtIndexNeg1B", testAddAtIndex(A_addToFrontB_BA(), -1, ELEMENT_B, Result.IndexOutOfBounds));
			printTest("A_addToFrontB_BA_testAddAtIndex0B", testAddAtIndex(A_addToFrontB_BA(), 0, ELEMENT_B, Result.NoException));
			printTest("A_addToFrontB_BA_testAddAtIndex1B", testAddAtIndex(A_addToFrontB_BA(), 1, ELEMENT_B, Result.NoException));
			printTest("A_addToFrontB_BA_testSetNeg1B", testSet(A_addToFrontB_BA(), -1, ELEMENT_B, Result.IndexOutOfBounds));
			printTest("A_addToFrontB_BA_testSet0B", testSet(A_addToFrontB_BA(), 0, ELEMENT_B, Result.NoException));
			printTest("A_addToFrontB_BA_testAddB", testAdd(A_addToFrontB_BA(), ELEMENT_B, Result.NoException));
			printTest("A_addToFrontB_BA_testGetNeg1", testGet(A_addToFrontB_BA(), -1, ELEMENT_B, Result.IndexOutOfBounds));
			printTest("A_addToFrontB_BA_testGet0", testGet(A_addToFrontB_BA(), 0, ELEMENT_B, Result.MatchingValue));
			printTest("A_addToFrontB_BA_testIndexOfA", testIndexOf(A_addToFrontB_BA(), ELEMENT_A, 1));
			printTest("A_addToFrontB_BA_testIndexOfB", testIndexOf(A_addToFrontB_BA(), ELEMENT_B, 0));
			printTest("A_addToFrontB_BA_testRemoveNeg1", testRemoveIndex(A_addToFrontB_BA(), -1, null, Result.IndexOutOfBounds));
			printTest("A_addToFrontB_BA_testRemove0", testRemoveIndex(A_addToFrontB_BA(), 0, ELEMENT_B, Result.MatchingValue));
			printTest("A_addToFrontB_BA_testRemove1", testRemoveIndex(A_addToFrontB_BA(), 1, ELEMENT_A, Result.MatchingValue));
			//Iterators
		/*	printTest("emptyList_addA0_A_testIterator", testIterator(A_removeFirst_emptyList(), Result.NoException));
			printTest("emptyList_addA0_A_testIteratorHasNext", testIteratorHasNext(A_removeFirst_emptyList().iterator(), Result.False));
			printTest("emptyList_addA0_A_testIteratorNext", testIteratorNext(A_removeFirst_emptyList().iterator(), null, Result.EmptyCollection));
			printTest("emptyList_addA0_A_testIteratorRemove", testIteratorRemove(A_removeFirst_emptyList().iterator(), Result.IllegalState));
			printTest("emptyList_addA0_A_iteratorNext_testIteratorHasNext", testIteratorHasNext(iteratorAfterNext(A_removeFirst_emptyList(), 0), Result.False));
			printTest("emptyList_addA0_A_iteratorNext_testIteratorNext", testIteratorNext(iteratorAfterNext(A_removeFirst_emptyList(), 1), null, Result.NoSuchElement));
			printTest("emptyList_addA0_A_iteratorNext_testIteratorRemove", testIteratorRemove(iteratorAfterNext(A_removeFirst_emptyList(), 1), Result.NoException));
			printTest("emptyList_addA0_A_iteratorNextRemove_testIteratorHasNext", testIteratorHasNext(iteratorAfterRemove(iteratorAfterNext(A_removeFirst_emptyList(), 1)), Result.False));
			printTest("emptyList_addA0_A_iteratorNextRemove_testIteratorNext", testIteratorNext(iteratorAfterRemove(iteratorAfterNext(A_removeFirst_emptyList(), 1)), null, Result.NoSuchElement));
			printTest("emptyList_addA0_A_iteratorNextRemove_testIteratorRemove", testIteratorRemove(iteratorAfterRemove(iteratorAfterNext(A_removeFirst_emptyList(), 1)), Result.IllegalState));
		*/
			
			System.out.println();
		} catch (Exception e) {
			System.out.printf("***UNABLE TO RUN/COMPLETE %s***\n", "test_emptyList_addToFrontA_A");
			e.printStackTrace();
		}
	}
	////////////////////////////////////////////////
	// SCENARIO: [A] -> addToRear(B) -> [A,B]
	//  XXX Scenario 10
	////////////////////////////////////////////////
	
	/** Scenario: [A] -> addToRear(B) -> [A,B] 
	 * @return [A,B] after addToRear(B)
	 */
	private  ListADT<Integer> A_addToRearB_AB() {
		// cast to UnorderedListADT to make addToFront() available
		UnorderedListADT<Integer> list = (UnorderedListADT<Integer>) newList(); 
		list.addToFront(ELEMENT_A);
		list.addToRear(ELEMENT_B);
		return list;
	}

	/** Run all tests on scenario: [A] -> addToRear(B) -> [A,B] */
	private void test_A_addToRearB_AB() {
		// recommended test naming: start_change_result_testName
		// e.g. A_addToFront_BA_testSize
		// AB_addC1_ACB_testFirst
		// A_remove0_empty_testLast

		//try-catch is necessary to prevent an Exception from the scenario builder method from bringing
		//down the whole test suite
		try {
			// ListADT
			System.out.println("[A] -> addToRear(B) -> [A,B]");
			printTest("A_addToRearB_AB_testRemoveFirst", testRemoveFirst(A_addToRearB_AB(), ELEMENT_A, Result.MatchingValue));
			printTest("A_addToRearB_AB_testRemoveLast", testRemoveLast(A_addToRearB_AB(), ELEMENT_B, Result.MatchingValue));
			printTest("A_addToRearB_AB_testRemoveA", testRemoveElement(A_addToRearB_AB(), ELEMENT_A, Result.MatchingValue));
			printTest("A_addToRearB_AB_testRemoveB", testRemoveElement(A_addToRearB_AB(), ELEMENT_B, Result.MatchingValue));
			printTest("A_addToRearB_AB_testFirst", testFirst(A_addToRearB_AB(), ELEMENT_A, Result.MatchingValue));
			printTest("A_addToRearB_AB_testLast", testLast(A_addToRearB_AB(), ELEMENT_B, Result.MatchingValue));
			printTest("A_addToRearB_AB_testContainsA", testContains(A_addToRearB_AB(), ELEMENT_A, Result.True));
			printTest("A_addToRearB_AB_testContainsB", testContains(A_addToRearB_AB(), ELEMENT_B, Result.True));
			printTest("A_addToRearB_AB_testIsEmpty", testIsEmpty(A_addToRearB_AB(), Result.False));
			printTest("A_addToRearB_AB_testSize", testSize(A_addToRearB_AB(), 2));
			printTest("A_addToRearB_AB_testToString", testToString(A_addToRearB_AB(), Result.ValidString));
			// UnorderedListADT
			printTest("A_addToRearB_AB_testAddToFrontB", testAddToFront(A_addToRearB_AB(), ELEMENT_B, Result.NoException));
			printTest("A_addToRearB_AB_testAddToRearB", testAddToRear(A_addToRearB_AB(), ELEMENT_A, Result.NoException));
			printTest("A_addToRearB_AB_testAddAfterCB", testAddAfter(A_addToRearB_AB(), ELEMENT_C, ELEMENT_B, Result.ElementNotFound));
			printTest("A_addToRearB_AB_testAddAfterAB", testAddAfter(A_addToRearB_AB(), ELEMENT_A, ELEMENT_B, Result.NoException));
			// IndexedListADT
			printTest("A_addToRearB_AB_testAddAtIndexNeg1B", testAddAtIndex(A_addToRearB_AB(), -1, ELEMENT_B, Result.IndexOutOfBounds));
			printTest("A_addToRearB_AB_testAddAtIndex0B", testAddAtIndex(A_addToRearB_AB(), 0, ELEMENT_B, Result.NoException));
			printTest("A_addToRearB_AB_testAddAtIndex1B", testAddAtIndex(A_addToRearB_AB(), 1, ELEMENT_B, Result.NoException));
			printTest("A_addToRearB_AB_testSetNeg1B", testSet(A_addToRearB_AB(), -1, ELEMENT_B, Result.IndexOutOfBounds));
			printTest("A_addToRearB_AB_testSet0B", testSet(A_addToRearB_AB(), 0, ELEMENT_B, Result.NoException));
			printTest("A_addToRearB_AB_testAddB", testAdd(A_addToRearB_AB(), ELEMENT_B, Result.NoException));
			printTest("A_addToRearB_AB_testGetNeg1", testGet(A_addToRearB_AB(), -1, ELEMENT_B, Result.IndexOutOfBounds));
			printTest("A_addToRearB_AB_testGet0", testGet(A_addToRearB_AB(), 0, ELEMENT_A, Result.MatchingValue));
			printTest("A_addToRearB_AB_testIndexOfA", testIndexOf(A_addToRearB_AB(), ELEMENT_B, 1));
			printTest("A_addToRearB_AB_testIndexOfB", testIndexOf(A_addToRearB_AB(), ELEMENT_A, 0));
			printTest("A_addToRearB_AB_testRemoveNeg1", testRemoveIndex(A_addToRearB_AB(), -1, null, Result.IndexOutOfBounds));
			printTest("A_addToRearB_AB_testRemove0", testRemoveIndex(A_addToRearB_AB(), 0, ELEMENT_A, Result.MatchingValue));
			printTest("A_addToRearB_AB_testRemove1", testRemoveIndex(A_addToRearB_AB(), 1, ELEMENT_B, Result.MatchingValue));
			//Iterators
		/*	printTest("emptyList_addA0_A_testIterator", testIterator(A_removeFirst_emptyList(), Result.NoException));
			printTest("emptyList_addA0_A_testIteratorHasNext", testIteratorHasNext(A_removeFirst_emptyList().iterator(), Result.False));
			printTest("emptyList_addA0_A_testIteratorNext", testIteratorNext(A_removeFirst_emptyList().iterator(), null, Result.EmptyCollection));
			printTest("emptyList_addA0_A_testIteratorRemove", testIteratorRemove(A_removeFirst_emptyList().iterator(), Result.IllegalState));
			printTest("emptyList_addA0_A_iteratorNext_testIteratorHasNext", testIteratorHasNext(iteratorAfterNext(A_removeFirst_emptyList(), 0), Result.False));
			printTest("emptyList_addA0_A_iteratorNext_testIteratorNext", testIteratorNext(iteratorAfterNext(A_removeFirst_emptyList(), 1), null, Result.NoSuchElement));
			printTest("emptyList_addA0_A_iteratorNext_testIteratorRemove", testIteratorRemove(iteratorAfterNext(A_removeFirst_emptyList(), 1), Result.NoException));
			printTest("emptyList_addA0_A_iteratorNextRemove_testIteratorHasNext", testIteratorHasNext(iteratorAfterRemove(iteratorAfterNext(A_removeFirst_emptyList(), 1)), Result.False));
			printTest("emptyList_addA0_A_iteratorNextRemove_testIteratorNext", testIteratorNext(iteratorAfterRemove(iteratorAfterNext(A_removeFirst_emptyList(), 1)), null, Result.NoSuchElement));
			printTest("emptyList_addA0_A_iteratorNextRemove_testIteratorRemove", testIteratorRemove(iteratorAfterRemove(iteratorAfterNext(A_removeFirst_emptyList(), 1)), Result.IllegalState));
		*/
			
			System.out.println();
		} catch (Exception e) {
			System.out.printf("***UNABLE TO RUN/COMPLETE %s***\n", "test_emptyList_addToFrontA_A");
			e.printStackTrace();
		}
	}
	
	////////////////////////////////////////////////
	// SCENARIO: [A] -> addAfter(A,B) -> [A,B]
	//  XXX Scenario 11
	////////////////////////////////////////////////
	
	/** Scenario: [A] -> addAfter(A,B) -> [A,B] 
	 * @return [A,B] after addAfter(A,B)
	 */
	private  ListADT<Integer> A_addAfterAB_AB() {
		// cast to UnorderedListADT to make addToFront() available
		UnorderedListADT<Integer> list = (UnorderedListADT<Integer>) newList(); 
		list.addToFront(ELEMENT_A);
		list.addAfter(ELEMENT_B, ELEMENT_A);
		return list;
	}

	/** Run all tests on scenario: [A] -> addAfter(A,B) -> [A,B] */
	private void test_A_addAfterAB_AB() {
		// recommended test naming: start_change_result_testName
		// e.g. A_addToFront_BA_testSize
		// AB_addC1_ACB_testFirst
		// A_remove0_empty_testLast

		//try-catch is necessary to prevent an Exception from the scenario builder method from bringing
		//down the whole test suite
		try {
			// ListADT
			System.out.println("[A] -> addToRear(B) -> [A,B]");
			printTest("A_addAfterAB_AB_testRemoveFirst", testRemoveFirst(A_addAfterAB_AB(), ELEMENT_A, Result.MatchingValue));
			printTest("A_addAfterAB_AB_testRemoveLast", testRemoveLast(A_addAfterAB_AB(), ELEMENT_B, Result.MatchingValue));
			printTest("A_addAfterAB_AB_testRemoveA", testRemoveElement(A_addAfterAB_AB(), ELEMENT_A, Result.MatchingValue));
			printTest("A_addAfterAB_AB_testRemoveB", testRemoveElement(A_addAfterAB_AB(), ELEMENT_B, Result.MatchingValue));
			printTest("A_addAfterAB_AB_testFirst", testFirst(A_addAfterAB_AB(), ELEMENT_A, Result.MatchingValue));
			printTest("A_addAfterAB_AB_testLast", testLast(A_addAfterAB_AB(), ELEMENT_B, Result.MatchingValue));
			printTest("A_addAfterAB_AB_testContainsA", testContains(A_addAfterAB_AB(), ELEMENT_A, Result.True));
			printTest("A_addAfterAB_AB_testContainsB", testContains(A_addAfterAB_AB(), ELEMENT_B, Result.True));
			printTest("A_addAfterAB_AB_testIsEmpty", testIsEmpty(A_addAfterAB_AB(), Result.False));
			printTest("A_addAfterAB_AB_testSize", testSize(A_addAfterAB_AB(), 2));
			printTest("A_addAfterAB_AB_testToString", testToString(A_addAfterAB_AB(), Result.ValidString));
			// UnorderedListADT
			printTest("A_addAfterAB_AB_testAddToFrontB", testAddToFront(A_addAfterAB_AB(), ELEMENT_B, Result.NoException));
			printTest("A_addAfterAB_AB_testAddToRearB", testAddToRear(A_addAfterAB_AB(), ELEMENT_A, Result.NoException));
			printTest("A_addAfterAB_AB_testAddAfterCB", testAddAfter(A_addAfterAB_AB(), ELEMENT_C, ELEMENT_B, Result.ElementNotFound));
			printTest("A_addAfterAB_AB_testAddAfterAB", testAddAfter(A_addAfterAB_AB(), ELEMENT_A, ELEMENT_B, Result.NoException));
			// IndexedListADT
			printTest("A_addAfterAB_AB_testAddAtIndexNeg1B", testAddAtIndex(A_addAfterAB_AB(), -1, ELEMENT_B, Result.IndexOutOfBounds));
			printTest("A_addAfterAB_AB_testAddAtIndex0B", testAddAtIndex(A_addAfterAB_AB(), 0, ELEMENT_B, Result.NoException));
			printTest("A_addAfterAB_AB_testAddAtIndex1B", testAddAtIndex(A_addAfterAB_AB(), 1, ELEMENT_B, Result.NoException));
			printTest("A_addAfterAB_AB_testSetNeg1B", testSet(A_addAfterAB_AB(), -1, ELEMENT_B, Result.IndexOutOfBounds));
			printTest("A_addAfterAB_AB_testSet0B", testSet(A_addAfterAB_AB(), 0, ELEMENT_B, Result.NoException));
			printTest("A_addAfterAB_AB_testAddB", testAdd(A_addAfterAB_AB(), ELEMENT_B, Result.NoException));
			printTest("A_addAfterAB_AB_testGetNeg1", testGet(A_addAfterAB_AB(), -1, ELEMENT_B, Result.IndexOutOfBounds));
			printTest("A_addAfterAB_AB_testGet0", testGet(A_addAfterAB_AB(), 0, ELEMENT_A, Result.MatchingValue));
			printTest("A_addAfterAB_AB_testIndexOfA", testIndexOf(A_addAfterAB_AB(), ELEMENT_B, 1));
			printTest("A_addAfterAB_AB_testIndexOfB", testIndexOf(A_addAfterAB_AB(), ELEMENT_A, 0));
			printTest("A_addAfterAB_AB_testRemoveNeg1", testRemoveIndex(A_addAfterAB_AB(), -1, null, Result.IndexOutOfBounds));
			printTest("A_addAfterAB_AB_testRemove0", testRemoveIndex(A_addAfterAB_AB(), 0, ELEMENT_A, Result.MatchingValue));
			printTest("A_addAfterAB_AB_testRemove1", testRemoveIndex(A_addAfterAB_AB(), 1, ELEMENT_B, Result.MatchingValue));
			//Iterators
		/*	printTest("emptyList_addA0_A_testIterator", testIterator(A_removeFirst_emptyList(), Result.NoException));
			printTest("emptyList_addA0_A_testIteratorHasNext", testIteratorHasNext(A_removeFirst_emptyList().iterator(), Result.False));
			printTest("emptyList_addA0_A_testIteratorNext", testIteratorNext(A_removeFirst_emptyList().iterator(), null, Result.EmptyCollection));
			printTest("emptyList_addA0_A_testIteratorRemove", testIteratorRemove(A_removeFirst_emptyList().iterator(), Result.IllegalState));
			printTest("emptyList_addA0_A_iteratorNext_testIteratorHasNext", testIteratorHasNext(iteratorAfterNext(A_removeFirst_emptyList(), 0), Result.False));
			printTest("emptyList_addA0_A_iteratorNext_testIteratorNext", testIteratorNext(iteratorAfterNext(A_removeFirst_emptyList(), 1), null, Result.NoSuchElement));
			printTest("emptyList_addA0_A_iteratorNext_testIteratorRemove", testIteratorRemove(iteratorAfterNext(A_removeFirst_emptyList(), 1), Result.NoException));
			printTest("emptyList_addA0_A_iteratorNextRemove_testIteratorHasNext", testIteratorHasNext(iteratorAfterRemove(iteratorAfterNext(A_removeFirst_emptyList(), 1)), Result.False));
			printTest("emptyList_addA0_A_iteratorNextRemove_testIteratorNext", testIteratorNext(iteratorAfterRemove(iteratorAfterNext(A_removeFirst_emptyList(), 1)), null, Result.NoSuchElement));
			printTest("emptyList_addA0_A_iteratorNextRemove_testIteratorRemove", testIteratorRemove(iteratorAfterRemove(iteratorAfterNext(A_removeFirst_emptyList(), 1)), Result.IllegalState));
		*/
			
			System.out.println();
		} catch (Exception e) {
			System.out.printf("***UNABLE TO RUN/COMPLETE %s***\n", "test_emptyList_addToFrontA_A");
			e.printStackTrace();
		}
	}
	
	////////////////////////////////////////////////
	// SCENARIO: [A] -> remove(0) -> []
	//  XXX Scenario 12
	////////////////////////////////////////////////
	
	/** Scenario: [A] -> remove(0) -> []
	 * @return [] after remove(0)
	 */
	private  ListADT<Integer> A_remove0_empty() {
		// cast to UnorderedListADT to make addToFront() available
		IndexedListADT<Integer> list = (IndexedListADT<Integer>) newList(); 
		list.add(ELEMENT_A);
		list.remove(0);
		return list;
	}

	/** Run all tests on scenario: [A] -> remove(0) -> [] */
	private void test_A_remove0_empty() {
		// recommended test naming: start_change_result_testName
		// e.g. A_addToFront_BA_testSize
		// AB_addC1_ACB_testFirst
		// A_remove0_empty_testLast

		//try-catch is necessary to prevent an Exception from the scenario builder method from bringing
		//down the whole test suite
		try {
			// ListADT
			System.out.println("A] -> remove(0) -> []");
			printTest("A_remove0_empty_testRemoveFirst", testRemoveFirst(A_remove0_empty(), null, Result.EmptyCollection));
			printTest("A_remove0_empty_testRemoveLast", testRemoveLast(A_remove0_empty(), null, Result.EmptyCollection));
			printTest("A_remove0_empty_testRemoveA", testRemoveElement(A_remove0_empty(), ELEMENT_A, Result.ElementNotFound));
			printTest("A_remove0_empty_testRemoveB", testRemoveElement(A_remove0_empty(), ELEMENT_B, Result.ElementNotFound));
			printTest("A_remove0_empty_testFirst", testFirst(A_remove0_empty(), null, Result.EmptyCollection));
			printTest("A_remove0_empty_testLast", testLast(A_remove0_empty(), null, Result.EmptyCollection));
			printTest("A_remove0_empty_testContainsA", testContains(A_remove0_empty(), ELEMENT_A, Result.False));
			printTest("A_remove0_empty_testContainsB", testContains(A_remove0_empty(), ELEMENT_B, Result.False));
			printTest("A_remove0_empty_testIsEmpty", testIsEmpty(A_remove0_empty(), Result.True));
			printTest("A_remove0_empty_testSize", testSize(A_remove0_empty(), 0));
			printTest("A_remove0_empty_testToString", testToString(A_remove0_empty(), Result.ValidString));
			// UnorderedListADT
			printTest("A_remove0_empty_testAddToFrontB", testAddToFront(A_remove0_empty(), ELEMENT_B, Result.NoException));
			printTest("A_remove0_empty_testAddToRearB", testAddToRear(A_remove0_empty(), ELEMENT_A, Result.NoException));
			printTest("A_remove0_empty_testAddAfterCB", testAddAfter(A_remove0_empty(), ELEMENT_C, ELEMENT_B, Result.ElementNotFound));
			printTest("A_remove0_empty_testAddAfterAB", testAddAfter(A_remove0_empty(), ELEMENT_A, ELEMENT_B, Result.ElementNotFound));
			// IndexedListADT
			printTest("A_remove0_empty_testAddAtIndexNeg1B", testAddAtIndex(A_remove0_empty(), -1, ELEMENT_B, Result.IndexOutOfBounds));
			printTest("A_remove0_empty_testAddAtIndex0B", testAddAtIndex(A_remove0_empty(), 0, ELEMENT_B, Result.NoException));
			printTest("A_remove0_empty_testAddAtIndex1B", testAddAtIndex(A_remove0_empty(), 1, ELEMENT_B, Result.IndexOutOfBounds));
			printTest("A_remove0_empty_testSetNeg1B", testSet(A_remove0_empty(), -1, ELEMENT_B, Result.IndexOutOfBounds));
			printTest("A_remove0_empty_testSet0B", testSet(A_remove0_empty(), 0, ELEMENT_B, Result.IndexOutOfBounds));
			printTest("A_remove0_empty_testAddB", testAdd(A_remove0_empty(), ELEMENT_B, Result.NoException));
			printTest("A_remove0_empty_testGetNeg1", testGet(A_remove0_empty(), -1, ELEMENT_B, Result.IndexOutOfBounds));
			printTest("A_remove0_empty_testGet0", testGet(A_remove0_empty(), 0, null, Result.IndexOutOfBounds));
			printTest("A_remove0_empty_testIndexOfA", testIndexOf(A_remove0_empty(), ELEMENT_A, -1));
			printTest("A_remove0_empty_testIndexOfB", testIndexOf(A_remove0_empty(), ELEMENT_B,-1));
			printTest("A_remove0_empty_testRemoveNeg1", testRemoveIndex(A_remove0_empty(), -1, null, Result.IndexOutOfBounds));
			printTest("A_remove0_empty_testRemove0", testRemoveIndex(A_remove0_empty(), 0, ELEMENT_A, Result.IndexOutOfBounds));
			printTest("A_remove0_empty_testRemove1", testRemoveIndex(A_remove0_empty(), 1, ELEMENT_B, Result.IndexOutOfBounds));
			
			
			//Iterators
		/*	printTest("emptyList_addA0_A_testIterator", testIterator(A_removeFirst_emptyList(), Result.NoException));
			printTest("emptyList_addA0_A_testIteratorHasNext", testIteratorHasNext(A_removeFirst_emptyList().iterator(), Result.False));
			printTest("emptyList_addA0_A_testIteratorNext", testIteratorNext(A_removeFirst_emptyList().iterator(), null, Result.EmptyCollection));
			printTest("emptyList_addA0_A_testIteratorRemove", testIteratorRemove(A_removeFirst_emptyList().iterator(), Result.IllegalState));
			printTest("emptyList_addA0_A_iteratorNext_testIteratorHasNext", testIteratorHasNext(iteratorAfterNext(A_removeFirst_emptyList(), 0), Result.False));
			printTest("emptyList_addA0_A_iteratorNext_testIteratorNext", testIteratorNext(iteratorAfterNext(A_removeFirst_emptyList(), 1), null, Result.NoSuchElement));
			printTest("emptyList_addA0_A_iteratorNext_testIteratorRemove", testIteratorRemove(iteratorAfterNext(A_removeFirst_emptyList(), 1), Result.NoException));
			printTest("emptyList_addA0_A_iteratorNextRemove_testIteratorHasNext", testIteratorHasNext(iteratorAfterRemove(iteratorAfterNext(A_removeFirst_emptyList(), 1)), Result.False));
			printTest("emptyList_addA0_A_iteratorNextRemove_testIteratorNext", testIteratorNext(iteratorAfterRemove(iteratorAfterNext(A_removeFirst_emptyList(), 1)), null, Result.NoSuchElement));
			printTest("emptyList_addA0_A_iteratorNextRemove_testIteratorRemove", testIteratorRemove(iteratorAfterRemove(iteratorAfterNext(A_removeFirst_emptyList(), 1)), Result.IllegalState));
		*/
			
			System.out.println();
		} catch (Exception e) {
			System.out.printf("***UNABLE TO RUN/COMPLETE %s***\n", "test_emptyList_addToFrontA_A");
			e.printStackTrace();
		}
	}
	
	////////////////////////////////////////////////
	// SCENARIO: [A,B] -> add(C) -> [A,B,C]
	//  XXX Scenario 28
	////////////////////////////////////////////////
	
	/** Scenario: [A,B] -> add(C) -> [A,B,C]
	 * @return [A,B,C] after add(A,B,C)
	 */
	private  ListADT<Integer> AB_addC_ABC() {
		// cast to UnorderedListADT to make addToFront() available
		IndexedListADT<Integer> list = (IndexedListADT<Integer>) newList(); 
		list.add(ELEMENT_A);
		list.add(ELEMENT_B);
		list.add(ELEMENT_C);
		return list;
	}

	/** Run all tests on scenario: [A,B] -> add(C) -> [A,B,C] */
	private void test_AB_addC_ABC() {
		// recommended test naming: start_change_result_testName
		// e.g. A_addToFront_BA_testSize
		// AB_addC1_ACB_testFirst
		// A_remove0_empty_testLast

		//try-catch is necessary to prevent an Exception from the scenario builder method from bringing
		//down the whole test suite
		try {
			// ListADT
			System.out.println("[A,B] -> add(C) -> [A,B,C]");
			printTest("AB_addC_ABC_testRemoveFirst", testRemoveFirst(AB_addC_ABC(), ELEMENT_A, Result.MatchingValue));
			printTest("AB_addC_ABC_testRemoveLast", testRemoveLast(AB_addC_ABC(), ELEMENT_C, Result.MatchingValue));
			printTest("AB_addC_ABC_testRemoveA", testRemoveElement(AB_addC_ABC(), ELEMENT_A, Result.MatchingValue));
			printTest("AB_addC_ABC_testRemoveB", testRemoveElement(AB_addC_ABC(), ELEMENT_B, Result.MatchingValue));
			printTest("AB_addC_ABC_testFirst", testFirst(AB_addC_ABC(), ELEMENT_A, Result.MatchingValue));
			printTest("AB_addC_ABC_testLast", testLast(AB_addC_ABC(), ELEMENT_C, Result.MatchingValue));
			printTest("AB_addC_ABC_testContainsA", testContains(AB_addC_ABC(), ELEMENT_A, Result.True));
			printTest("AB_addC_ABC_testContainsB", testContains(AB_addC_ABC(), ELEMENT_B, Result.True));
			printTest("AB_addC_ABC_testIsEmpty", testIsEmpty(AB_addC_ABC(), Result.False));
			printTest("AB_addC_ABC_testSize", testSize(AB_addC_ABC(), 3));
			printTest("AB_addC_ABC_testToString", testToString(AB_addC_ABC(), Result.ValidString));
			// UnorderedListADT
			printTest("AB_addC_ABC_testAddToFrontB", testAddToFront(AB_addC_ABC(), ELEMENT_B, Result.NoException));
			printTest("AB_addC_ABC_testAddToRearB", testAddToRear(AB_addC_ABC(), ELEMENT_A, Result.NoException));
			printTest("AB_addC_ABC_testAddAfterCB", testAddAfter(AB_addC_ABC(), ELEMENT_C, ELEMENT_B, Result.NoException));
			printTest("AB_addC_ABC_testAddAfterAB", testAddAfter(AB_addC_ABC(), ELEMENT_A, ELEMENT_B, Result.NoException));
			// IndexedListADT
			printTest("AB_addC_ABC_testAddAtIndexNeg1B", testAddAtIndex(AB_addC_ABC(), -1, ELEMENT_B, Result.IndexOutOfBounds));
			printTest("AB_addC_ABC_testAddAtIndex0B", testAddAtIndex(AB_addC_ABC(), 0, ELEMENT_B, Result.NoException));
			printTest("AB_addC_ABC_testAddAtIndex1B", testAddAtIndex(AB_addC_ABC(), 1, ELEMENT_B, Result.NoException));
			printTest("AB_addC_ABC_testSetNeg1B", testSet(AB_addC_ABC(), -1, ELEMENT_B, Result.IndexOutOfBounds));
			printTest("AB_addC_ABC_testSet0B", testSet(AB_addC_ABC(), 0, ELEMENT_B, Result.NoException));
			printTest("AB_addC_ABC_testAddB", testAdd(AB_addC_ABC(), ELEMENT_B, Result.NoException));
			printTest("AB_addC_ABC_testGetNeg1", testGet(AB_addC_ABC(), -1, ELEMENT_B, Result.IndexOutOfBounds));
			printTest("AB_addC_ABC_testGet0", testGet(AB_addC_ABC(), 0, ELEMENT_A, Result.MatchingValue));
			printTest("AB_addC_ABC_testIndexOfA", testIndexOf(AB_addC_ABC(), ELEMENT_A, 0));
			printTest("AB_addC_ABC_testIndexOfB", testIndexOf(AB_addC_ABC(), ELEMENT_B, 1));
			printTest("AB_addC_ABC_testRemoveNeg1", testRemoveIndex(AB_addC_ABC(), -1, null, Result.IndexOutOfBounds));
			printTest("AB_addC_ABC_testRemove0", testRemoveIndex(AB_addC_ABC(), 0, ELEMENT_A, Result.MatchingValue));
			printTest("AB_addC_ABC_testRemove1", testRemoveIndex(AB_addC_ABC(), 1, ELEMENT_B, Result.MatchingValue));
			//Iterators
		/*	printTest("emptyList_addA0_A_testIterator", testIterator(A_removeFirst_emptyList(), Result.NoException));
			printTest("emptyList_addA0_A_testIteratorHasNext", testIteratorHasNext(A_removeFirst_emptyList().iterator(), Result.False));
			printTest("emptyList_addA0_A_testIteratorNext", testIteratorNext(A_removeFirst_emptyList().iterator(), null, Result.EmptyCollection));
			printTest("emptyList_addA0_A_testIteratorRemove", testIteratorRemove(A_removeFirst_emptyList().iterator(), Result.IllegalState));
			printTest("emptyList_addA0_A_iteratorNext_testIteratorHasNext", testIteratorHasNext(iteratorAfterNext(A_removeFirst_emptyList(), 0), Result.False));
			printTest("emptyList_addA0_A_iteratorNext_testIteratorNext", testIteratorNext(iteratorAfterNext(A_removeFirst_emptyList(), 1), null, Result.NoSuchElement));
			printTest("emptyList_addA0_A_iteratorNext_testIteratorRemove", testIteratorRemove(iteratorAfterNext(A_removeFirst_emptyList(), 1), Result.NoException));
			printTest("emptyList_addA0_A_iteratorNextRemove_testIteratorHasNext", testIteratorHasNext(iteratorAfterRemove(iteratorAfterNext(A_removeFirst_emptyList(), 1)), Result.False));
			printTest("emptyList_addA0_A_iteratorNextRemove_testIteratorNext", testIteratorNext(iteratorAfterRemove(iteratorAfterNext(A_removeFirst_emptyList(), 1)), null, Result.NoSuchElement));
			printTest("emptyList_addA0_A_iteratorNextRemove_testIteratorRemove", testIteratorRemove(iteratorAfterRemove(iteratorAfterNext(A_removeFirst_emptyList(), 1)), Result.IllegalState));
		*/
			
			System.out.println();
		} catch (Exception e) {
			System.out.printf("***UNABLE TO RUN/COMPLETE %s***\n", "test_emptyList_addToFrontA_A");
			e.printStackTrace();
		}
	}
	
	// //////////////////////////
	// LIST_ADT TESTS XXX
	// //////////////////////////

	/**
	 * Runs removeFirst() method on given list and checks result against expectedResult
	 * @param list a list already prepared for a given change scenario
	 * @param expectedElement element or null if expectedResult is an Exception
	 * @param expectedResult
	 * @return test success
	 */
	private boolean testRemoveFirst(ListADT<Integer> list, Integer expectedElement, Result expectedResult) {
		Result result;
		try {
			Integer retVal = list.removeFirst();
			if (retVal.equals(expectedElement)) {
				result = Result.MatchingValue;
			} else {
				result = Result.Fail;
			}
		} catch (EmptyCollectionException e) {
			result = Result.EmptyCollection;
		} catch (Exception e) {
			System.out.printf("%s caught unexpected %s\n", "testRemoveFirst", e.toString());
			e.printStackTrace();
			result = Result.UnexpectedException;
		}
		return result == expectedResult;
	}

	/**
	 * Runs removeLast() method on given list and checks result against expectedResult
	 * @param list a list already prepared for a given change scenario
	 * @param expectedElement element or null if expectedResult is an Exception
	 * @param expectedResult
	 * @return test success
	 */
	private boolean testRemoveLast(ListADT<Integer> list, Integer expectedElement, Result expectedResult) {
		Result result;
		try {
			Integer retVal = list.removeLast();
			if (retVal.equals(expectedElement)) {
				result = Result.MatchingValue;
			} else {
				result = Result.Fail;
			}
		} catch (EmptyCollectionException e) {
			result = Result.EmptyCollection;
		} catch (Exception e) {
			System.out.printf("%s caught unexpected %s\n", "testRemoveLast", e.toString());
			e.printStackTrace();
			result = Result.UnexpectedException;
		}
		return result == expectedResult;
	}

	/**
	 * Runs removeLast() method on given list and checks result against expectedResult
	 * @param list a list already prepared for a given change scenario
	 * @param element element to remove
	 * @param expectedResult
	 * @return test success
	 */
	private boolean testRemoveElement(ListADT<Integer> list, Integer element, Result expectedResult) {
		Result result;
		try {
			Integer retVal = list.remove(element);
			if (retVal.equals(element)) {
				result = Result.MatchingValue;
			} else {
				result = Result.Fail;
			}
		} catch (ElementNotFoundException e) {
			result = Result.ElementNotFound;
		} catch (Exception e) {
			System.out.printf("%s caught unexpected %s\n", "testRemoveElement", e.toString());
			e.printStackTrace();
			result = Result.UnexpectedException;
		}
		return result == expectedResult;
	}

	/**
	 * Runs first() method on a given list and checks result against expectedResult
	 * @param list a list already prepared for a given change scenario
	 * @param expectedElement element or null if expectedResult is an Exception
	 * @param expectedResult
	 * @return test success
	 */
	private boolean testFirst(ListADT<Integer> list, Integer expectedElement, Result expectedResult) {
		Result result;
		try {
			Integer retVal = list.first();
			if (retVal.equals(expectedElement)) {
				result = Result.MatchingValue;
			} else {
				result = Result.Fail;
			}
		} catch (EmptyCollectionException e) {
			result = Result.EmptyCollection;
		} catch (Exception e) {
			System.out.printf("%s caught unexpected %s\n", "testFirst", e.toString());
			e.printStackTrace();
			result = Result.UnexpectedException;
		}
		return result == expectedResult;
	}

	/**
	 * Runs last() method on a given list and checks result against expectedResult
	 * @param list a list already prepared for a given change scenario
	 * @param expectedElement element or null if expectedResult is an Exception
	 * @param expectedResult
	 * @return test success
	 */
	private boolean testLast(ListADT<Integer> list, Integer expectedElement, Result expectedResult) {
		Result result;
		try {
			Integer retVal = list.last();
			if (retVal.equals(expectedElement)) {
				result = Result.MatchingValue;
			} else {
				result = Result.Fail;
			}
		} catch (EmptyCollectionException e) {
			result = Result.EmptyCollection;
		} catch (Exception e) {
			System.out.printf("%s caught unexpected %s\n", "testLast", e.toString());
			e.printStackTrace();
			result = Result.UnexpectedException;
		}
		return result == expectedResult;
	}

	/**
	 * Runs contains() method on a given list and element and checks result against expectedResult
	 * @param list a list already prepared for a given change scenario
	 * @param element
	 * @param expectedResult
	 * @return test success
	 */
	private boolean testContains(ListADT<Integer> list, Integer element, Result expectedResult) {
		Result result;
		try {
			if (list.contains(element)) {
				result = Result.True;
			} else {
				result = Result.False;
			}
		} catch (Exception e) {
			System.out.printf("%s caught unexpected %s\n", "testContains", e.toString());
			e.printStackTrace();
			result = Result.UnexpectedException;
		}
		return result == expectedResult;
	}

	/**
	 * Runs isEmpty() method on a given list and checks result against expectedResult
	 * @param list a list already prepared for a given change scenario
	 * @param expectedResult
	 * @return test success
	 */
	private boolean testIsEmpty(ListADT<Integer> list, Result expectedResult) {
		Result result;
		try {
			if (list.isEmpty()) {
				result = Result.True;
			} else {
				result = Result.False;
			}
		} catch (Exception e) {
			System.out.printf("%s caught unexpected %s\n", "testIsEmpty", e.toString());
			e.printStackTrace();
			result = Result.UnexpectedException;
		}
		return result == expectedResult;
	}

	/**
	 * Runs size() method on a given list and checks result against expectedResult
	 * @param list a list already prepared for a given change scenario
	 * @param expectedSize
	 * @return test success
	 */
	private boolean testSize(ListADT<Integer> list, int expectedSize) {
		try {
			return (list.size() == expectedSize);
		} catch (Exception e) {
			System.out.printf("%s caught unexpected %s\n", "testSize", e.toString());
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Runs toString() method on given list and attempts to confirm non-default or empty String
	 * difficult to test - just confirm that default address output has been overridden
	 * @param list a list already prepared for a given change scenario
	 * @param expectedResult
	 * @return test success
	 */
	private boolean testToString(ListADT<Integer> list, Result expectedResult) {
		Result result;
		try {
			String str = list.toString();
			System.out.println("toString() output: " + str);
			if (str.length() == 0) {
				result = Result.Fail;
			}
			char lastChar = str.charAt(str.length() - 1);
			if (str.contains("@")
					&& !str.contains(" ")
					&& Character.isLetter(str.charAt(0))
					&& (Character.isDigit(lastChar) || (lastChar >= 'a' && lastChar <= 'f'))) {
				result = Result.Fail; // looks like default toString()
			} else {
				result = Result.ValidString;
			}
		} catch (Exception e) {
			System.out.printf("%s caught unexpected %s\n", "testToString", e.toString());
			e.printStackTrace();
			result = Result.UnexpectedException;
		}
		return result == expectedResult;
	}

	// /////////////////////////////////////////
	// UNORDERED_LIST_ADT TESTS XXX
	// /////////////////////////////////////////

	/**
	 * Runs addToFront() method on a given list and checks result against expectedResult
	 * @param list a list already prepared for a given change scenario
	 * @param element
	 * @param expectedResult
	 * @return test success
	 */
	private boolean testAddToFront(ListADT<Integer> list, Integer element, Result expectedResult) {
		Result result;
		try {
			((UnorderedListADT<Integer>) list).addToFront(element);
			result = Result.NoException;
		} catch (Exception e) {
			System.out.printf("%s caught unexpected %s\n", "testAddToFront",  e.toString());
			result = Result.UnexpectedException;
		}
		return result == expectedResult;
	}

	/**
	 * Runs addToRear() method on a given list and checks result against expectedResult
	 * @param list a list already prepared for a given change scenario
	 * @param element
	 * @param expectedResult
	 * @return test success
	 */
	private boolean testAddToRear(ListADT<Integer> list, Integer element, Result expectedResult) {
		Result result;
		try {
			((UnorderedListADT<Integer>) list).addToRear(element);
			result = Result.NoException;
		} catch (Exception e) {
			System.out.printf("%s caught unexpected %s\n", "testAddToRear", e.toString());
			e.printStackTrace();
			result = Result.UnexpectedException;
		}
		return result == expectedResult;
	}

	/**
	 * Runs addAfter() method on a given list and checks result against expectedResult
	 * @param list a list already prepared for a given change scenario
	 * @param target
	 * @param element
	 * @param expectedResult
	 * @return test success
	 */
	private boolean testAddAfter(ListADT<Integer> list, Integer target, Integer element, Result expectedResult) {
		Result result;
		try {
			((UnorderedListADT<Integer>) list).addAfter(element, target);
			result = Result.NoException;
		} catch (ElementNotFoundException e) {
			result = Result.ElementNotFound;
		} catch (Exception e) {
			System.out.printf("%s caught unexpected %s\n", "testAddAfter", e.toString());
			e.printStackTrace();
			result = Result.UnexpectedException;
		}
		return result == expectedResult;
	}

	// /////////////////////////////////////
	// INDEXED_LIST_ADT TESTS XXX
	// /////////////////////////////////////

	/**
	 * Runs add(int, T) method on a given list and checks result against expectedResult
	 * @param list a list already prepared for a given change scenario
	 * @param index
	 * @param element
	 * @param expectedResult
	 * @return test success
	 */
	private boolean testAddAtIndex(ListADT<Integer> list, int index, Integer element, Result expectedResult) {
		Result result;
		try {
			((IndexedListADT<Integer>)list).add(index, element);
			result = Result.NoException;
		} catch (IndexOutOfBoundsException e) {
			result = Result.IndexOutOfBounds;
		} catch (Exception e) {
			System.out.printf("%s caught unexpected %s\n", "testAddAtIndex", e.toString());
			e.printStackTrace();
			result = Result.UnexpectedException;
		}
		return result == expectedResult;
	}
	
	/**
	 * Runs add(T) method on a given list and checks result against expectedResult
	 * @param list a list already prepared for a given change scenario
	 * @param element
	 * @param expectedResult
	 * @return test success
	 */
	private boolean testAdd(ListADT<Integer> list, Integer element, Result expectedResult) {
		Result result;
		try {
			((IndexedListADT<Integer>)list).add(element);
			result = Result.NoException;
		} catch (IndexOutOfBoundsException e) {
			result = Result.IndexOutOfBounds;
		} catch (Exception e) {
			System.out.printf("%s caught unexpected %s\n", "testAddAtIndex", e.toString());
			e.printStackTrace();
			result = Result.UnexpectedException;
		}
		return result == expectedResult;
	}
	
	/**
	 * Runs set(int, T) method on a given list and checks result against expectedResult
	 * @param list a list already prepared for a given change scenario
	 * @param index
	 * @param element
	 * @param expectedResult
	 * @return test success
	 */
	private boolean testSet(ListADT<Integer> list, int index, Integer element, Result expectedResult) {
		Result result;
		try {
			((IndexedListADT<Integer>)list).set(index, element);
			result = Result.NoException;
		} catch (IndexOutOfBoundsException e) {
			result = Result.IndexOutOfBounds;
		} catch (Exception e) {
			System.out.printf("%s caught unexpected %s\n", "testSet", e.toString());
			e.printStackTrace();
			result = Result.UnexpectedException;
		}
		return result == expectedResult;
	}

	/**
	 * Runs get() method on a given list and checks result against expectedResult
	 * @param list a list already prepared for a given change scenario
	 * @param index
	 * @param expectedElement
	 * @param expectedResult
	 * @return test success
	 */
	private boolean testGet(ListADT<Integer> list, int index, Integer expectedElement, Result expectedResult) {
		Result result;
		try {
			Integer retVal = ((IndexedListADT<Integer>)list).get(index);
			if (retVal.equals(expectedElement)) {
				result = Result.MatchingValue;
			} else {
				result = Result.Fail;
			}
		} catch (IndexOutOfBoundsException e) {
			result = Result.IndexOutOfBounds;
		} catch (Exception e) {
			System.out.printf("%s caught unexpected %s\n", "testGet", e.toString());
			e.printStackTrace();
			result = Result.UnexpectedException;
		}
		return result == expectedResult;
	}

	/**
	 * Runs remove(index) method on a given list and checks result against expectedResult
	 * @param list a list already prepared for a given change scenario
	 * @param index
	 * @param expectedElement
	 * @param expectedResult
	 * @return test success
	 */
	private boolean testRemoveIndex(ListADT<Integer> list, int index, Integer expectedElement, Result expectedResult) {
		Result result;
		try {
			Integer retVal = ((IndexedListADT<Integer>)list).remove(index);
			if (retVal.equals(expectedElement)) {
				result = Result.MatchingValue;
			} else {
				result = Result.Fail;
			}
		} catch (IndexOutOfBoundsException e) {
			result = Result.IndexOutOfBounds;
		} catch (Exception e) {
			System.out.printf("%s caught unexpected %s\n", "testRemoveIndex", e.toString());
			e.printStackTrace();
			result = Result.UnexpectedException;
		}
		return result == expectedResult;
	}
	
	/**
	 * Runs indexOf() method on a given list and checks result against expectedResult
	 * @param list a list already prepared for a given change scenario
	 * @param element
	 * @param expectedIndex
	 * @return test success
	 */
	private boolean testIndexOf(ListADT<Integer> list, Integer element, int expectedIndex) {
		Result result;
		try {
			return ((IndexedListADT<Integer>)list).indexOf(element) == expectedIndex;
		} catch (Exception e) {
			System.out.printf("%s caught unexpected %s\n", "testIndexOf", e.toString());
			e.printStackTrace();
			return false;
		}
	}
	
	////////////////////////////
	// ITERATOR TESTS XXX
	////////////////////////////
	
	/**
	 * Runs iterator() method on a given list and checks result against expectedResult
	 * @param list a list already prepared for a given change scenario
	 * @param expectedResult
	 * @return test success
	 */
	private boolean testIterator(ListADT<Integer> list, Result expectedResult) {
		Result result;
		try {
			Iterator<Integer> it = list.iterator();
			result = Result.NoException;
		} catch (Exception e) {
			System.out.printf("%s caught unexpected %s\n", "testIterator", e.toString());
			e.printStackTrace();
			result = Result.UnexpectedException;
		}
		return result == expectedResult;
	}

	/**
	 * Runs list's iterator hasNext() method on a given list and checks result against expectedResult
	 * @param iterator an iterator already positioned for the call to hasNext()
	 * @param expectedResult
	 * @return test success
	 */
	private boolean testIteratorHasNext(Iterator<Integer> iterator, Result expectedResult) {
		Result result;
		try {
			if (iterator.hasNext()) {
				result = Result.True;
			} else {
				result = Result.False;
			}
		} catch (ConcurrentModificationException e) {
			result = Result.ConcurrentModification;
		} catch (Exception e) {
			System.out.printf("%s caught unexpected %s\n", "testIteratorHasNext", e.toString());
			e.printStackTrace();
			result = Result.UnexpectedException;
		}
		return result == expectedResult;
	}

	/**
	 * Runs list's iterator next() method on a given list and checks result against expectedResult
	 * @param iterator an iterator already positioned for the call to hasNext()
	 * @param expectedValue the Integer expected from next() or null if an exception is expected
	 * @param expectedResult MatchingValue or expected exception
	 * @return test success
	 */
	private boolean testIteratorNext(Iterator<Integer> iterator, Integer expectedValue, Result expectedResult) {
		Result result;
		try {
			Integer retVal = iterator.next();
			if (retVal.equals(expectedValue)) {
				result = Result.MatchingValue;
			} else {
				result = Result.Fail;
			}
		} catch (NoSuchElementException e) {
			result = Result.NoSuchElement;
		} catch (ConcurrentModificationException e) {
			result = Result.ConcurrentModification;
		} catch (Exception e) {
			System.out.printf("%s caught unexpected %s\n", "testIteratorNext", e.toString());
			e.printStackTrace();
			result = Result.UnexpectedException;
		}
		return result == expectedResult;
	}

	/**
	 * Runs list's iterator remove() method on a given list and checks result against expectedResult
	 * @param iterator an iterator already positioned for the call to remove()
	 * @param expectedResult
	 * @return test success
	 */
	private boolean testIteratorRemove(Iterator<Integer> iterator, Result expectedResult) {
		Result result;
		try {
			iterator.remove();
			result = Result.NoException;
		} catch (IllegalStateException e) {
			result = Result.IllegalState;
		} catch (ConcurrentModificationException e) {
			result = Result.ConcurrentModification;
		} catch (Exception e) {
			System.out.printf("%s caught unexpected %s\n", "testIteratorRemove", e.toString());
			e.printStackTrace();
			result = Result.UnexpectedException;
		}
		return result == expectedResult;
	}

	//////////////////////////////////////////////////////////
	//HELPER METHODS FOR TESTING ITERATORS XXX
	//////////////////////////////////////////////////////////
	/**
	 * Helper for testing iterators. Return an Iterator that has been advanced numCallsToNext times.
	 * @param list
	 * @param numCallsToNext
	 * @return Iterator for given list, after numCallsToNext
	 */
	private Iterator<Integer> iteratorAfterNext(ListADT<Integer> list, int numCallsToNext) {
		Iterator it = list.iterator();
		for (int i = 0; i < numCallsToNext; i++) {
			it.next();
		}
		return it;
	}
	
	/**
	 * Helper for testing iterators. Return an Iterator that has had remove() called once.
	 * @param iterator
	 * @return same Iterator following a call to remove()
	 */
	private Iterator<Integer> iteratorAfterRemove(Iterator<Integer> iterator) {
		iterator.remove();
		return iterator;
	}
		
}// end class UnorderedListTester
