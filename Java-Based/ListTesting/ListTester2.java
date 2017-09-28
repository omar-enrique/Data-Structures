import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * A unit test class for lists that implement both UnorderedListADT and
 * IndexedListADT. This is a set of black box tests that should work for any
 * implementation of these interfaces.
 * 
 * NOTE: One example test is given for each interface method using a new list to
 * get you started.
 * 
 * @author mvail, mhthomas, tmanning
 */
public class ListTester2 {
	//possible lists that could be tested
	private enum ListToUse {
		goodList, badList, arrayList, singleLinkedList, doubleLinkedList
	};
	private final ListToUse LIST_TO_USE = ListToUse.doubleLinkedList;

	// possible results expected in tests
	private enum Result {
		EmptyCollection, ElementNotFound, IndexOutOfBounds, IllegalState, ConcurrentModification, NoSuchElement, 
		NoException, UnexpectedException,
		True, False, Pass, Fail, 
		MatchingValue,
		ValidString, 
		ClassCast, IllegalArgument
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
		ListTester2 tester = new ListTester2();
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
		System.out.printf("%-46s\t\t%s\n", testDesc, (result ? "   PASS" : "***FAIL***"));
	}

	/** Print a final summary */
	private void printFinalSummary() {
		System.out.printf("\nTotal Tests: %d,  Passed: %d,  Failed: %d\n",
				total, passes, failures);
	}
	private void runTests() {
		//recommended scenario naming: start_change_result
	System.out.println("**************************Starting test_newList**************************");
	test_newList();
	System.out.println("\n**************************Starting test_emptyList_addToFrontA_A**************************");
	test_emptyList_addToFrontA_A();
	System.out.println("\n**************************Starting test_emptyList_addToRearA_A**************************");
	test_emptyList_addToRearA_A();
	System.out.println("\n**************************Starting test_emptyList_addA_A**************************");
	test_emptyList_addA_A();
	System.out.println("\n**************************Starting test_emptyList_add0A_A**************************");
	test_emptyList_add0A_A();
	System.out.println("\n**************************Starting test_A_removeFirst_empty**************************");
	test_A_removeFirst_empty();
	System.out.println("\n**************************Starting test_A_removeLast_empty**************************");
	test_A_removeLast_empty();
	System.out.println("\n**************************Starting test_A_removeA_empty**************************");
	test_A_removeA_empty();
	System.out.println("\n**************************Starting test_A_addToFrontB_BA**************************");
	test_A_addToFrontB_BA();
	System.out.println("\n**************************Starting test_A_addToRearB_AB**************************");
	test_A_addToRearB_AB();
	System.out.println("\n**************************Starting test_A_addAfterBA_AB**************************");
	test_A_addAfterBA_AB();
	System.out.println("\n**************************Starting test_A_remove0_empty**************************");
	test_A_remove0_empty();
	System.out.println("\n**************************Starting test_A_set0B_B**************************");
	test_A_set0B_B();
	System.out.println("\n**************************Starting test_A_add0B_BA**************************");
	test_A_add0B_BA();
	System.out.println("\n**************************Starting test_A_add1B_AB**************************");
	test_A_add1B_AB();
	System.out.println("\n**************************Starting test_AB_removeFirst_B**************************");
	test_AB_removeFirst_B();
	System.out.println("\n**************************Starting test_AB_removeLast_A**************************");
	test_AB_removeLast_A();
	System.out.println("\n**************************Starting test_AB_removeA_B**************************");
	test_AB_removeA_B();
	System.out.println("\n**************************Starting test_AB_removeB_A**************************");
	test_AB_removeB_A();
	System.out.println("\n**************************Starting test_AB_addToFrontC_CAB**************************");
	test_AB_addToFrontC_CAB();
	System.out.println("\n**************************Starting test_AB_addToRearC_ABC**************************");
	test_AB_addToRearC_ABC();
	System.out.println("\n**************************Starting test_AB_addAfterCA_ACB**************************");
	test_AB_addAfterCA_ACB();
	System.out.println("\n**************************Starting test_AB_addAfterCB_ABC**************************");
	test_AB_addAfterCB_ABC();
	System.out.println("\n**************************Starting test_AB_remove0_B**************************");
	test_AB_remove0_B();
	System.out.println("\n**************************Starting test_AB_remove1_A**************************");
	test_AB_remove1_A();
	System.out.println("\n**************************Starting test_AB_set0C_CB**************************");
	test_AB_set0C_CB();
	System.out.println("\n**************************Starting test_AB_set1C_AC**************************");
	test_AB_set1C_AC();
	System.out.println("\n**************************Starting test_AB_addC_ABC**************************");
	test_AB_addC_ABC();
	System.out.println("\n**************************Starting test_AB_add0C_CAB**************************");
	test_AB_add0C_CAB();
	System.out.println("\n**************************Starting test_AB_add1C_ACB**************************");
	test_AB_add1C_ACB();
	System.out.println("\n**************************Starting test_AB_add2C_ABC**************************");
	test_AB_add2C_ABC();
	System.out.println("\n**************************Starting test_ABC_removeFirst_BC**************************");
	test_ABC_removeFirst_BC();
	System.out.println("\n**************************Starting test_ABC_removeLast_AB**************************");
	test_ABC_removeLast_AB();
	System.out.println("\n**************************Starting test_ABC_removeA_BC**************************");
	test_ABC_removeA_BC();
	System.out.println("\n**************************Starting test_ABC_removeB_AC**************************");
	test_ABC_removeB_AC();
	System.out.println("\n**************************Starting test_ABC_removeC_AB**************************");
	test_ABC_removeC_AB();
	System.out.println("\n**************************Starting test_ABC_remove0_BC**************************");
	test_ABC_remove0_BC();
	System.out.println("\n**************************Starting test_ABC_remove1_AC**************************");
	test_ABC_remove1_AC();
	System.out.println("\n**************************Starting test_ABC_remove2_AB**************************");
	test_ABC_remove2_AB();
	System.out.println("\n**************************Starting test_ABC_set0D_DBC**************************");
	test_ABC_set0D_DBC();
	System.out.println("\n**************************Starting test_ABC_set1D_ADC**************************");
	test_ABC_set1D_ADC();
	System.out.println("\n**************************Starting test_ABC_2D_ABD**************************");
	test_ABC_2D_ABD();
		
		// report final verdict
		printFinalSummary();
	}

	//////////////////////////////////////
	// SCENARIO: no list -> constructor -> []
	//  XXX SCENARIO 1
	//////////////////////////////////////
	private DoubleLinkedList<Integer> testDLL(){
		DoubleLinkedList<Integer> list = new DoubleLinkedList<Integer>();
		return list;
	}
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
			//ListIterator
			if(newList().getClass()==testDLL().getClass()){
			printTest("newList_testListIteratorUnIndexed",testListIteratorUnIndexed(newList(), Result.NoException));
			printTest("newList_testListIteratorIndexed",testListIteratorIndexed(newList(), 0, Result.NoException));
			printTest("newList_testListIteratorIndexed1",testListIteratorIndexed(newList(), 1, Result.IndexOutOfBounds));
			printTest("newList_testListIteratorHasNext", testListIteratorHasNext(newList(),null, null, Result.False));
			printTest("newList_testListIteratorHasNextIndexed0", testListIteratorHasNext(newList(),null, 0, Result.False));
			printTest("newList_testListIteratorNext",testListIteratorNext(newList(),null, null, null, Result.NoSuchElement));
			printTest("newList_testListIteratorNextIndexed0",testListIteratorNext(newList(),null, 0, null, Result.NoSuchElement));
			printTest("newList_testListIteratorHasPrevious",testListIteratorHasPrevious(newList(),null, null, Result.False));
			printTest("newList_testListIteratorHasPreviousIndexed0",testListIteratorHasPrevious(newList(),null, 0, Result.False));
			printTest("newList_testListIteratorPrevious",testListIteratorPrevious(newList(),null, null, null, Result.NoSuchElement));
			printTest("newList_testListIteratorPreviousIndexed0",testListIteratorPrevious(newList(),null, 0, null, Result.NoSuchElement));
			printTest("newList_testListIteratorNextIndex",testListIteratorNextIndex(newList(),null, null, 0, Result.MatchingValue));
			printTest("newList_testListIteratorNextIndexIndexed0",testListIteratorNextIndex(newList(),null, 0, 0, Result.MatchingValue));
			printTest("newList_testListIteratorPreviousIndex",testListIteratorPreviousIndex(newList(),null, null, -1, Result.MatchingValue));
			printTest("newList_testListIteratorPreviousIndexIndexed0",testListIteratorPreviousIndex(newList(),null, 0, -1, Result.MatchingValue));
			printTest("newList_testListIteratorRemove",testListIteratorRemove(newList(),null, null, Result.IllegalState));
			printTest("newList_testListIteratorRemoveIndexed0",testListIteratorRemove(newList(),null, 0, Result.IllegalState));
			printTest("newList_testListIteratorSet",testListIteratorSet(newList(),null, null, ELEMENT_A, Result.IllegalState));
			printTest("newList_testListIteratorSetIndexed0",testListIteratorSet(newList(),null, 0, ELEMENT_A, Result.IllegalState));
			printTest("newList_testListIteratorAdd",testListIteratorAdd(newList(),null, null, ELEMENT_A, Result.NoException));
			printTest("newList_testListIteratorAddIndexed0",testListIteratorAdd(newList(),null, 0, ELEMENT_A, Result.NoException));
			}
		} catch (Exception e) {
			System.out.printf("***UNABLE TO RUN/COMPLETE %s***\n", "test_newList");
			e.printStackTrace();
		}
	}

	////////////////////////////////////////////////
	// SCENARIO: [ ] -> addToFront(A) -> [A]
	//  XXX: SCENARIO 1
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
			printTest("emptyList_addToFrontA_A_iteratorTestRealRemove",testIteratorRealRemove(emptyList_addToFrontA_A(), 1, "[]", Result.MatchingValue));
			//ListIterator
			if(newList().getClass()==testDLL().getClass()){
			printTest("emptyList_addToFrontA_A_testListIteratorUnIndexed", testListIteratorUnIndexed(emptyList_addToFrontA_A(), Result.NoException));
			printTest("emptyList_addToFrontA_A_testListIteratorIndexedNeg1", testListIteratorIndexed(emptyList_addToFrontA_A(), -1, Result.IndexOutOfBounds));
			printTest("emptyList_addToFrontA_A_testListIteratorIndexed0", testListIteratorIndexed(emptyList_addToFrontA_A(), 0, Result.NoException));
			printTest("emptyList_addToFrontA_A_testListIteratorIndexed1", testListIteratorIndexed(emptyList_addToFrontA_A(), 1, Result.NoException));
			printTest("emptyList_addToFrontA_A_testListIteratorIndexed2", testListIteratorIndexed(emptyList_addToFrontA_A(), 2, Result.IndexOutOfBounds));
			
			printTest("emptyList_addToFrontA_A_testListIteratorHasNext", testListIteratorHasNext(emptyList_addToFrontA_A(),null, null, Result.True));
			printTest("emptyList_addToFrontA_A_testListIteratorNext", testListIteratorNext(emptyList_addToFrontA_A(),null, null, ELEMENT_A, Result.MatchingValue));
			printTest("emptyList_addToFrontA_A_testListIteratorNextIndex", testListIteratorNextIndex(emptyList_addToFrontA_A(),null, null, 0, Result.MatchingValue));
			printTest("emptyList_addToFrontA_A_testListIteratorHasPrevious", testListIteratorHasPrevious(emptyList_addToFrontA_A(),null, null, Result.False));
			printTest("emptyList_addToFrontA_A_testListIteratorPrevious", testListIteratorPrevious(emptyList_addToFrontA_A(),null, null, null, Result.NoSuchElement));
			printTest("emptyList_addToFrontA_A_testListIteratorPreviousIndex", testListIteratorPreviousIndex(emptyList_addToFrontA_A(),null, null, -1, Result.MatchingValue));
			printTest("emptyList_addToFrontA_A_testListIteratorRemove", testListIteratorRemove(emptyList_addToFrontA_A(),null, null, Result.IllegalState));
			printTest("emptyList_addToFrontA_A_testListIteratorRealRemove", testListIteratorRealRemoveAfterNext(emptyList_addToFrontA_A(), null, 0, "[1]", Result.MatchingValue));
			printTest("emptyList_addToFrontA_A_testListIteratorSet", testListIteratorSet(emptyList_addToFrontA_A(), null,null, ELEMENT_D, Result.IllegalState));
			printTest("emptyList_addToFrontA_A_testListIteratorRealSet", testListIteratorRealSetAfterNext(emptyList_addToFrontA_A(), null, 0, ELEMENT_D, "[1]", Result.MatchingValue));
			printTest("emptyList_addToFrontA_A_testListIteratorAdd", testListIteratorAdd(emptyList_addToFrontA_A(),null, null, ELEMENT_D, Result.NoException));
			printTest("emptyList_addToFrontA_A_testListIteratorRealAdd", testListIteratorRealAddAfterNext(emptyList_addToFrontA_A(), null, 0, ELEMENT_D, "[4, 1]", Result.MatchingValue));
			
			printTest("emptyList_addToFrontA_A_testListIteratorHasNextAfterNext", testListIteratorHasNext(null, getListIteratorAfterCall(emptyList_addToFrontA_A(), "next", null, null), null, Result.False));
			printTest("emptyList_addToFrontA_A_testListIteratorNextAfterNext", testListIteratorNext(null,getListIteratorAfterCall(emptyList_addToFrontA_A(), "next", null, null),null, null, Result.NoSuchElement));
			printTest("emptyList_addToFrontA_A_testListIteratorNextIndexAfterNext", testListIteratorNextIndex(null,getListIteratorAfterCall(emptyList_addToFrontA_A(), "next", null, null),null, 1, Result.MatchingValue));
			printTest("emptyList_addToFrontA_A_testListIteratorHasPreviousAfterNext", testListIteratorHasPrevious(null,getListIteratorAfterCall(emptyList_addToFrontA_A(), "next", null, null),null, Result.True));
			printTest("emptyList_addToFrontA_A_testListIteratorPreviousAfterNext", testListIteratorPrevious(null, getListIteratorAfterCall(emptyList_addToFrontA_A(), "next", null, null), null, ELEMENT_A, Result.MatchingValue));
			printTest("emptyList_addToFrontA_A_testListIteratorPreviousIndexAfterNext", testListIteratorPreviousIndex(null, getListIteratorAfterCall(emptyList_addToFrontA_A(), "next", null, null), null, 0, Result.MatchingValue));
			printTest("emptyList_addToFrontA_A_testListIteratorRemoveAfterNext", testListIteratorRemove(null, getListIteratorAfterCall(emptyList_addToFrontA_A(), "next", null, null), null, Result.NoException));
			printTest("emptyList_addToFrontA_A_testListIteratorRealRemoveAfterNext", testListIteratorRealRemoveAfterNext(emptyList_addToFrontA_A(), null, 1, "[]", Result.MatchingValue));
			printTest("emptyList_addToFrontA_A_testListIteratorSetAfterNext", testListIteratorSet(null, getListIteratorAfterCall(emptyList_addToFrontA_A(), "next", null, null), null, ELEMENT_D, Result.NoException));
			printTest("emptyList_addToFrontA_A_testListIteratorRealSetAfterNext", testListIteratorRealSetAfterNext(emptyList_addToFrontA_A(), null, 1, ELEMENT_D, "[4]", Result.MatchingValue));
			printTest("emptyList_addToFrontA_A_testListIteratorAddAfterNext", testListIteratorAdd(null, getListIteratorAfterCall(emptyList_addToFrontA_A(), "next", null, null), null, ELEMENT_D, Result.NoException));
			printTest("emptyList_addToFrontA_A_testListIteratorRealAddAfterNext", testListIteratorRealAddAfterNext(emptyList_addToFrontA_A(), null, 1, ELEMENT_D, "[1, 4]", Result.MatchingValue));
			
			printTest("emptyList_addToFrontA_A_testListIterator0HasNext", testListIteratorHasNext(emptyList_addToFrontA_A(), null, 0, Result.True));
			printTest("emptyList_addToFrontA_A_testListIterator0Next", testListIteratorNext(emptyList_addToFrontA_A(), null, 0, ELEMENT_A, Result.MatchingValue));
			printTest("emptyList_addToFrontA_A_testListIterator0NextIndex", testListIteratorNextIndex(emptyList_addToFrontA_A(), null, 0, 0, Result.MatchingValue));
			printTest("emptyList_addToFrontA_A_testListIterator0HasPrevious", testListIteratorHasPrevious(emptyList_addToFrontA_A(), null, 0, Result.False));
			printTest("emptyList_addToFrontA_A_testListIterator0Previous", testListIteratorPrevious(emptyList_addToFrontA_A(), null, 0, null, Result.NoSuchElement));
			printTest("emptyList_addToFrontA_A_testListIterator0PreviousIndex", testListIteratorPreviousIndex(emptyList_addToFrontA_A(), null, 0, -1, Result.MatchingValue));
			printTest("emptyList_addToFrontA_A_testListIterator0Remove", testListIteratorRemove(emptyList_addToFrontA_A(), null, 0, Result.IllegalState));
			printTest("emptyList_addToFrontA_A_testListIterator0RealRemove", testListIteratorRealRemoveAfterNext(emptyList_addToFrontA_A(), 0, 0, "[1]", Result.MatchingValue));
			printTest("emptyList_addToFrontA_A_testListIterator0Set", testListIteratorSet(emptyList_addToFrontA_A(), null, 0, ELEMENT_D, Result.IllegalState));
			printTest("emptyList_addToFrontA_A_testListIterator0RealSet", testListIteratorRealSetAfterNext(emptyList_addToFrontA_A(), 0, 0, ELEMENT_D, "[1]", Result.MatchingValue));
			printTest("emptyList_addToFrontA_A_testListIterator0Add", testListIteratorAdd(emptyList_addToFrontA_A(), null, 0, ELEMENT_D, Result.NoException));
			printTest("emptyList_addToFrontA_A_testListIterator0RealAdd", testListIteratorRealAddAfterNext(emptyList_addToFrontA_A(), 0, 0, ELEMENT_D, "[4, 1]", Result.MatchingValue));
			
			printTest("emptyList_addToFrontA_A_testListIterator0HasNextAfterNext", testListIteratorHasNext(null, getListIteratorAfterCall(emptyList_addToFrontA_A(), "next", 0, null), null, Result.False));
			printTest("emptyList_addToFrontA_A_testListIterator0NextAfterNext", testListIteratorNext(null, getListIteratorAfterCall(emptyList_addToFrontA_A(), "next", 0, null), null, null, Result.NoSuchElement));
			printTest("emptyList_addToFrontA_A_testListIterator0NextIndexAfterNext", testListIteratorNextIndex(null, getListIteratorAfterCall(emptyList_addToFrontA_A(), "next", 0, null), null, 1, Result.MatchingValue));
			printTest("emptyList_addToFrontA_A_testListIterator0HasPreviousAfterNext", testListIteratorHasPrevious(null, getListIteratorAfterCall(emptyList_addToFrontA_A(), "next", 0, null), null, Result.True));
			printTest("emptyList_addToFrontA_A_testListIterator0PreviousAfterNext", testListIteratorPrevious(null, getListIteratorAfterCall(emptyList_addToFrontA_A(), "next", 0, null), null, ELEMENT_A, Result.MatchingValue));
			printTest("emptyList_addToFrontA_A_testListIterator0PreviousIndexAfterNext", testListIteratorPreviousIndex(null, getListIteratorAfterCall(emptyList_addToFrontA_A(), "next", 0, null), null, 0, Result.MatchingValue));
			printTest("emptyList_addToFrontA_A_testListIterator0RemoveAfterNext", testListIteratorRemove(null, getListIteratorAfterCall(emptyList_addToFrontA_A(), "next", 0, null), null, Result.NoException));
			printTest("emptyList_addToFrontA_A_testListIterator0RealRemoveAfterNext", testListIteratorRealRemoveAfterNext(emptyList_addToFrontA_A(), 0, 1, "[]", Result.MatchingValue));
			printTest("emptyList_addToFrontA_A_testListIterator0SetAfterNext", testListIteratorSet(null, getListIteratorAfterCall(emptyList_addToFrontA_A(), "next", 0, null), null, ELEMENT_D, Result.NoException));
			printTest("emptyList_addToFrontA_A_testListIterator0RealSetAfterNext", testListIteratorRealSetAfterNext(emptyList_addToFrontA_A(), 0, 1, ELEMENT_D, "[4]", Result.MatchingValue));
			printTest("emptyList_addToFrontA_A_testListIterator0AddAfterNext", testListIteratorAdd(null, getListIteratorAfterCall(emptyList_addToFrontA_A(), "next", 0, null), null, ELEMENT_D, Result.NoException));
			printTest("emptyList_addToFrontA_A_testListIterator0RealAddAfterNext", testListIteratorRealAddAfterNext(emptyList_addToFrontA_A(), 0, 1, ELEMENT_D, "[1, 4]", Result.MatchingValue));
			
			printTest("emptyList_addToFrontA_A_testListIterator1HasNext", testListIteratorHasNext(emptyList_addToFrontA_A(), null, 1, Result.False));
			printTest("emptyList_addToFrontA_A_testListIterator1Next", testListIteratorNext(emptyList_addToFrontA_A(), null, 1, null, Result.NoSuchElement));
			printTest("emptyList_addToFrontA_A_testListIterator1NextIndex", testListIteratorNextIndex(emptyList_addToFrontA_A(), null, 1, 1, Result.MatchingValue));
			printTest("emptyList_addToFrontA_A_testListIterator1HasPrevious", testListIteratorHasPrevious(emptyList_addToFrontA_A(), null, 1, Result.True));
			printTest("emptyList_addToFrontA_A_testListIterator1Previous", testListIteratorPrevious(emptyList_addToFrontA_A(), null, 1, ELEMENT_A, Result.MatchingValue));
			printTest("emptyList_addToFrontA_A_testListIterator1PreviousIndex", testListIteratorPreviousIndex(emptyList_addToFrontA_A(), null, 1, 0, Result.MatchingValue));
			printTest("emptyList_addToFrontA_A_testListIterator1Remove", testListIteratorRemove(emptyList_addToFrontA_A(), null, 1, Result.IllegalState));
			printTest("emptyList_addToFrontA_A_testListIterator1RealRemove", testListIteratorRealRemoveAfterNext(emptyList_addToFrontA_A(), 1, 0, "[1]", Result.MatchingValue));
			printTest("emptyList_addToFrontA_A_testListIterator1Set", testListIteratorSet(emptyList_addToFrontA_A(), null, 1, ELEMENT_D, Result.IllegalState));
			printTest("emptyList_addToFrontA_A_testListIterator1RealSet", testListIteratorRealSetAfterNext(emptyList_addToFrontA_A(), 1, 0, ELEMENT_D, "[1]", Result.MatchingValue));
			printTest("emptyList_addToFrontA_A_testListIterator1Add", testListIteratorAdd(emptyList_addToFrontA_A(), null, 1, ELEMENT_D, Result.NoException));
			printTest("emptyList_addToFrontA_A_testListIterator1RealAdd", testListIteratorRealAddAfterNext(emptyList_addToFrontA_A(), 1, 0, ELEMENT_D, "[1, 4]", Result.MatchingValue));
			
			printTest("emptyList_addToFrontA_A_testListIterator1HasNextAfterPrev", testListIteratorHasNext(null, getListIteratorAfterCall(emptyList_addToFrontA_A(), "previous", 1, null), 1, Result.True));
			printTest("emptyList_addToFrontA_A_testListIterator1NextAfterPrev", testListIteratorNext(null, getListIteratorAfterCall(emptyList_addToFrontA_A(), "previous", 1, null), 1, ELEMENT_A, Result.MatchingValue));
			printTest("emptyList_addToFrontA_A_testListIterator1NextIndexAfterPrev", testListIteratorNextIndex(null, getListIteratorAfterCall(emptyList_addToFrontA_A(), "previous", 1, null), 1, 0, Result.MatchingValue));
			printTest("emptyList_addToFrontA_A_testListIterator1HasPreviousAfterPrev", testListIteratorHasPrevious(null, getListIteratorAfterCall(emptyList_addToFrontA_A(), "previous", 1, null), 1, Result.False));
			printTest("emptyList_addToFrontA_A_testListIterator1PreviousAfterPrev", testListIteratorPrevious(null, getListIteratorAfterCall(emptyList_addToFrontA_A(), "previous", 1, null), 1, null, Result.NoSuchElement));
			printTest("emptyList_addToFrontA_A_testListIterator1PreviousIndexAfterPrev", testListIteratorPreviousIndex(null, getListIteratorAfterCall(emptyList_addToFrontA_A(), "previous", 1, null), 1, -1, Result.MatchingValue));
			printTest("emptyList_addToFrontA_A_testListIterator1RemoveAfterPrev", testListIteratorRemove(null, getListIteratorAfterCall(emptyList_addToFrontA_A(), "previous", 1, null), 1, Result.NoException));
			printTest("emptyList_addToFrontA_A_testListIterator1RealRemoveAfterPrev", testListIteratorRealRemoveAfterPrev(emptyList_addToFrontA_A(), 1, 1, "[]", Result.MatchingValue));
			printTest("emptyList_addToFrontA_A_testListIterator1SetAfterPrev", testListIteratorSet(null, getListIteratorAfterCall(emptyList_addToFrontA_A(), "previous", 1, null), 1, ELEMENT_D, Result.NoException));
			printTest("emptyList_addToFrontA_A_testListIterator1RealSetAfterPrev", testListIteratorRealSetAfterPrev(emptyList_addToFrontA_A(), 1, 1, ELEMENT_D, "[4]", Result.MatchingValue));
			printTest("emptyList_addToFrontA_A_testListIterator1AddAfterPrev", testListIteratorAdd(null, getListIteratorAfterCall(emptyList_addToFrontA_A(), "previous", 1, null), 1, ELEMENT_D, Result.NoException));
			printTest("emptyList_addToFrontA_A_testListIterator1RealAddAfterPrev", testListIteratorRealAddAfterPrev(emptyList_addToFrontA_A(), 1, 1, ELEMENT_D, "[4, 1]", Result.MatchingValue));
			}
		} catch (Exception e) {
			System.out.printf("***UNABLE TO RUN/COMPLETE %s***\n", "test_emptyList_addToFrontA_A");
			e.printStackTrace();
		}
	}

	//////////////////////////////////////
	// SCENARIO: [] -> addToRear(A) -> [A]
	//  XXX SCENARIO 3
	//////////////////////////////////////
	private ListADT<Integer> emptyList_addToRearA_A() {
		// cast to UnorderedListADT to make addToFront() available
		UnorderedListADT<Integer> list = (UnorderedListADT<Integer>) newList(); 
		list.addToRear(ELEMENT_A);
		return list;
	}
	private void test_emptyList_addToRearA_A(){
		try {
			// ListADT
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
			printTest("emptyList_addToRearA_A_testIteratorNext", testIteratorNext(emptyList_addToRearA_A().iterator(), ELEMENT_A, Result.MatchingValue));
			printTest("emptyList_addToRearA_A_testIteratorRemove", testIteratorRemove(emptyList_addToRearA_A().iterator(), Result.IllegalState));
			printTest("emptyList_addToRearA_A_iteratorNext_testIteratorHasNext", testIteratorHasNext(iteratorAfterNext(emptyList_addToRearA_A(), 1), Result.False));
			printTest("emptyList_addToRearA_A_iteratorNext_testIteratorNext", testIteratorNext(iteratorAfterNext(emptyList_addToRearA_A(), 1), null, Result.NoSuchElement));
			printTest("emptyList_addToRearA_A_iteratorNext_testIteratorRemove", testIteratorRemove(iteratorAfterNext(emptyList_addToRearA_A(), 1), Result.NoException));
			printTest("emptyList_addToRearA_A_iteratorNextRemove_testIteratorHasNext", testIteratorHasNext(iteratorAfterRemove(iteratorAfterNext(emptyList_addToRearA_A(), 1)), Result.False));
			printTest("emptyList_addToRearA_A_iteratorNextRemove_testIteratorNext", testIteratorNext(iteratorAfterRemove(iteratorAfterNext(emptyList_addToRearA_A(), 1)), null, Result.NoSuchElement));
			printTest("emptyList_addToRearA_A_iteratorNextRemove_testIteratorRemove", testIteratorRemove(iteratorAfterRemove(iteratorAfterNext(emptyList_addToRearA_A(), 1)), Result.IllegalState));
			printTest("emptyList_addToRearA_A_iteratorTestRealRemove",testIteratorRealRemove(emptyList_addToRearA_A(), 1, "[]", Result.MatchingValue));
			//ListIterator
			if(newList().getClass()==testDLL().getClass()){
			printTest("emptyList_addToRearA_A_testListIteratorUnIndexed", testListIteratorUnIndexed(emptyList_addToRearA_A(), Result.NoException));
			printTest("emptyList_addToRearA_A_testListIteratorIndexedNeg1", testListIteratorIndexed(emptyList_addToRearA_A(), -1, Result.IndexOutOfBounds));
			printTest("emptyList_addToRearA_A_testListIteratorIndexed0", testListIteratorIndexed(emptyList_addToRearA_A(), 0, Result.NoException));
			printTest("emptyList_addToRearA_A_testListIteratorIndexed1", testListIteratorIndexed(emptyList_addToRearA_A(), 1, Result.NoException));
			printTest("emptyList_addToRearA_A_testListIteratorIndexed2", testListIteratorIndexed(emptyList_addToRearA_A(), 2, Result.IndexOutOfBounds));
			
			printTest("emptyList_addToRearA_A_testListIteratorHasNext", testListIteratorHasNext(emptyList_addToRearA_A(),null, null, Result.True));
			printTest("emptyList_addToRearA_A_testListIteratorNext", testListIteratorNext(emptyList_addToRearA_A(),null, null, ELEMENT_A, Result.MatchingValue));
			printTest("emptyList_addToRearA_A_testListIteratorNextIndex", testListIteratorNextIndex(emptyList_addToRearA_A(),null, null, 0, Result.MatchingValue));
			printTest("emptyList_addToRearA_A_testListIteratorHasPrevious", testListIteratorHasPrevious(emptyList_addToRearA_A(),null, null, Result.False));
			printTest("emptyList_addToRearA_A_testListIteratorPrevious", testListIteratorPrevious(emptyList_addToRearA_A(),null, null, null, Result.NoSuchElement));
			printTest("emptyList_addToRearA_A_testListIteratorPreviousIndex", testListIteratorPreviousIndex(emptyList_addToRearA_A(),null, null, -1, Result.MatchingValue));
			printTest("emptyList_addToRearA_A_testListIteratorRemove", testListIteratorRemove(emptyList_addToRearA_A(),null, null, Result.IllegalState));
			printTest("emptyList_addToRearA_A_testListIteratorRealRemove", testListIteratorRealRemoveAfterNext(emptyList_addToRearA_A(), null, 0, "[1]", Result.MatchingValue));
			printTest("emptyList_addToRearA_A_testListIteratorSet", testListIteratorSet(emptyList_addToRearA_A(), null,null, ELEMENT_D, Result.IllegalState));
			printTest("emptyList_addToRearA_A_testListIteratorRealSet", testListIteratorRealSetAfterNext(emptyList_addToRearA_A(), null, 0, ELEMENT_D, "[1]", Result.MatchingValue));
			printTest("emptyList_addToRearA_A_testListIteratorAdd", testListIteratorAdd(emptyList_addToRearA_A(),null, null, ELEMENT_D, Result.NoException));
			printTest("emptyList_addToRearA_A_testListIteratorRealAdd", testListIteratorRealAddAfterNext(emptyList_addToRearA_A(), null, 0, ELEMENT_D, "[4, 1]", Result.MatchingValue));
			
			printTest("emptyList_addToRearA_A_testListIteratorHasNextAfterNext", testListIteratorHasNext(null, getListIteratorAfterCall(emptyList_addToRearA_A(), "next", null, null), null, Result.False));
			printTest("emptyList_addToRearA_A_testListIteratorNextAfterNext", testListIteratorNext(null,getListIteratorAfterCall(emptyList_addToRearA_A(), "next", null, null),null, null, Result.NoSuchElement));
			printTest("emptyList_addToRearA_A_testListIteratorNextIndexAfterNext", testListIteratorNextIndex(null,getListIteratorAfterCall(emptyList_addToRearA_A(), "next", null, null),null, 1, Result.MatchingValue));
			printTest("emptyList_addToRearA_A_testListIteratorHasPreviousAfterNext", testListIteratorHasPrevious(null,getListIteratorAfterCall(emptyList_addToRearA_A(), "next", null, null),null, Result.True));
			printTest("emptyList_addToRearA_A_testListIteratorPreviousAfterNext", testListIteratorPrevious(null, getListIteratorAfterCall(emptyList_addToRearA_A(), "next", null, null), null, ELEMENT_A, Result.MatchingValue));
			printTest("emptyList_addToRearA_A_testListIteratorPreviousIndexAfterNext", testListIteratorPreviousIndex(null, getListIteratorAfterCall(emptyList_addToRearA_A(), "next", null, null), null, 0, Result.MatchingValue));
			printTest("emptyList_addToRearA_A_testListIteratorRemoveAfterNext", testListIteratorRemove(null, getListIteratorAfterCall(emptyList_addToRearA_A(), "next", null, null), null, Result.NoException));
			printTest("emptyList_addToRearA_A_testListIteratorRealRemoveAfterNext", testListIteratorRealRemoveAfterNext(emptyList_addToRearA_A(), null, 1, "[]", Result.MatchingValue));
			printTest("emptyList_addToRearA_A_testListIteratorSetAfterNext", testListIteratorSet(null, getListIteratorAfterCall(emptyList_addToRearA_A(), "next", null, null), null, ELEMENT_D, Result.NoException));
			printTest("emptyList_addToRearA_A_testListIteratorRealSetAfterNext", testListIteratorRealSetAfterNext(emptyList_addToRearA_A(), null, 1, ELEMENT_D, "[4]", Result.MatchingValue));
			printTest("emptyList_addToRearA_A_testListIteratorAddAfterNext", testListIteratorAdd(null, getListIteratorAfterCall(emptyList_addToRearA_A(), "next", null, null), null, ELEMENT_D, Result.NoException));
			printTest("emptyList_addToRearA_A_testListIteratorRealAddAfterNext", testListIteratorRealAddAfterNext(emptyList_addToRearA_A(), null, 1, ELEMENT_D, "[1, 4]", Result.MatchingValue));
			
			printTest("emptyList_addToRearA_A_testListIterator0HasNext", testListIteratorHasNext(emptyList_addToRearA_A(), null, 0, Result.True));
			printTest("emptyList_addToRearA_A_testListIterator0Next", testListIteratorNext(emptyList_addToRearA_A(), null, 0, ELEMENT_A, Result.MatchingValue));
			printTest("emptyList_addToRearA_A_testListIterator0NextIndex", testListIteratorNextIndex(emptyList_addToRearA_A(), null, 0, 0, Result.MatchingValue));
			printTest("emptyList_addToRearA_A_testListIterator0HasPrevious", testListIteratorHasPrevious(emptyList_addToRearA_A(), null, 0, Result.False));
			printTest("emptyList_addToRearA_A_testListIterator0Previous", testListIteratorPrevious(emptyList_addToRearA_A(), null, 0, null, Result.NoSuchElement));
			printTest("emptyList_addToRearA_A_testListIterator0PreviousIndex", testListIteratorPreviousIndex(emptyList_addToRearA_A(), null, 0, -1, Result.MatchingValue));
			printTest("emptyList_addToRearA_A_testListIterator0Remove", testListIteratorRemove(emptyList_addToRearA_A(), null, 0, Result.IllegalState));
			printTest("emptyList_addToRearA_A_testListIterator0RealRemove", testListIteratorRealRemoveAfterNext(emptyList_addToRearA_A(), 0, 0, "[1]", Result.MatchingValue));
			printTest("emptyList_addToRearA_A_testListIterator0Set", testListIteratorSet(emptyList_addToRearA_A(), null, 0, ELEMENT_D, Result.IllegalState));
			printTest("emptyList_addToRearA_A_testListIterator0RealSet", testListIteratorRealSetAfterNext(emptyList_addToRearA_A(), 0, 0, ELEMENT_D, "[1]", Result.MatchingValue));
			printTest("emptyList_addToRearA_A_testListIterator0Add", testListIteratorAdd(emptyList_addToRearA_A(), null, 0, ELEMENT_D, Result.NoException));
			printTest("emptyList_addToRearA_A_testListIterator0RealAdd", testListIteratorRealAddAfterNext(emptyList_addToRearA_A(), 0, 0, ELEMENT_D, "[4, 1]", Result.MatchingValue));
			
			printTest("emptyList_addToRearA_A_testListIterator0HasNextAfterNext", testListIteratorHasNext(null, getListIteratorAfterCall(emptyList_addToRearA_A(), "next", 0, null), null, Result.False));
			printTest("emptyList_addToRearA_A_testListIterator0NextAfterNext", testListIteratorNext(null, getListIteratorAfterCall(emptyList_addToRearA_A(), "next", 0, null), null, null, Result.NoSuchElement));
			printTest("emptyList_addToRearA_A_testListIterator0NextIndexAfterNext", testListIteratorNextIndex(null, getListIteratorAfterCall(emptyList_addToRearA_A(), "next", 0, null), null, 1, Result.MatchingValue));
			printTest("emptyList_addToRearA_A_testListIterator0HasPreviousAfterNext", testListIteratorHasPrevious(null, getListIteratorAfterCall(emptyList_addToRearA_A(), "next", 0, null), null, Result.True));
			printTest("emptyList_addToRearA_A_testListIterator0PreviousAfterNext", testListIteratorPrevious(null, getListIteratorAfterCall(emptyList_addToRearA_A(), "next", 0, null), null, ELEMENT_A, Result.MatchingValue));
			printTest("emptyList_addToRearA_A_testListIterator0PreviousIndexAfterNext", testListIteratorPreviousIndex(null, getListIteratorAfterCall(emptyList_addToRearA_A(), "next", 0, null), null, 0, Result.MatchingValue));
			printTest("emptyList_addToRearA_A_testListIterator0RemoveAfterNext", testListIteratorRemove(null, getListIteratorAfterCall(emptyList_addToRearA_A(), "next", 0, null), null, Result.NoException));
			printTest("emptyList_addToRearA_A_testListIterator0RealRemoveAfterNext", testListIteratorRealRemoveAfterNext(emptyList_addToRearA_A(), 0, 1, "[]", Result.MatchingValue));
			printTest("emptyList_addToRearA_A_testListIterator0SetAfterNext", testListIteratorSet(null, getListIteratorAfterCall(emptyList_addToRearA_A(), "next", 0, null), null, ELEMENT_D, Result.NoException));
			printTest("emptyList_addToRearA_A_testListIterator0RealSetAfterNext", testListIteratorRealSetAfterNext(emptyList_addToRearA_A(), 0, 1, ELEMENT_D, "[4]", Result.MatchingValue));
			printTest("emptyList_addToRearA_A_testListIterator0AddAfterNext", testListIteratorAdd(null, getListIteratorAfterCall(emptyList_addToRearA_A(), "next", 0, null), null, ELEMENT_D, Result.NoException));
			printTest("emptyList_addToRearA_A_testListIterator0RealAddAfterNext", testListIteratorRealAddAfterNext(emptyList_addToRearA_A(), 0, 1, ELEMENT_D, "[1, 4]", Result.MatchingValue));
			
			printTest("emptyList_addToRearA_A_testListIterator1HasNext", testListIteratorHasNext(emptyList_addToRearA_A(), null, 1, Result.False));
			printTest("emptyList_addToRearA_A_testListIterator1Next", testListIteratorNext(emptyList_addToRearA_A(), null, 1, null, Result.NoSuchElement));
			printTest("emptyList_addToRearA_A_testListIterator1NextIndex", testListIteratorNextIndex(emptyList_addToRearA_A(), null, 1, 1, Result.MatchingValue));
			printTest("emptyList_addToRearA_A_testListIterator1HasPrevious", testListIteratorHasPrevious(emptyList_addToRearA_A(), null, 1, Result.True));
			printTest("emptyList_addToRearA_A_testListIterator1Previous", testListIteratorPrevious(emptyList_addToRearA_A(), null, 1, ELEMENT_A, Result.MatchingValue));
			printTest("emptyList_addToRearA_A_testListIterator1PreviousIndex", testListIteratorPreviousIndex(emptyList_addToRearA_A(), null, 1, 0, Result.MatchingValue));
			printTest("emptyList_addToRearA_A_testListIterator1Remove", testListIteratorRemove(emptyList_addToRearA_A(), null, 1, Result.IllegalState));
			printTest("emptyList_addToRearA_A_testListIterator1RealRemove", testListIteratorRealRemoveAfterNext(emptyList_addToRearA_A(), 1, 0, "[1]", Result.MatchingValue));
			printTest("emptyList_addToRearA_A_testListIterator1Set", testListIteratorSet(emptyList_addToRearA_A(), null, 1, ELEMENT_D, Result.IllegalState));
			printTest("emptyList_addToRearA_A_testListIterator1RealSet", testListIteratorRealSetAfterNext(emptyList_addToRearA_A(), 1, 0, ELEMENT_D, "[1]", Result.MatchingValue));
			printTest("emptyList_addToRearA_A_testListIterator1Add", testListIteratorAdd(emptyList_addToRearA_A(), null, 1, ELEMENT_D, Result.NoException));
			printTest("emptyList_addToRearA_A_testListIterator1RealAdd", testListIteratorRealAddAfterNext(emptyList_addToRearA_A(), 1, 0, ELEMENT_D, "[1, 4]", Result.MatchingValue));
			
			printTest("emptyList_addToRearA_A_testListIterator1HasNextAfterPrev", testListIteratorHasNext(null, getListIteratorAfterCall(emptyList_addToRearA_A(), "previous", 1, null), 1, Result.True));
			printTest("emptyList_addToRearA_A_testListIterator1NextAfterPrev", testListIteratorNext(null, getListIteratorAfterCall(emptyList_addToRearA_A(), "previous", 1, null), 1, ELEMENT_A, Result.MatchingValue));
			printTest("emptyList_addToRearA_A_testListIterator1NextIndexAfterPrev", testListIteratorNextIndex(null, getListIteratorAfterCall(emptyList_addToRearA_A(), "previous", 1, null), 1, 0, Result.MatchingValue));
			printTest("emptyList_addToRearA_A_testListIterator1HasPreviousAfterPrev", testListIteratorHasPrevious(null, getListIteratorAfterCall(emptyList_addToRearA_A(), "previous", 1, null), 1, Result.False));
			printTest("emptyList_addToRearA_A_testListIterator1PreviousAfterPrev", testListIteratorPrevious(null, getListIteratorAfterCall(emptyList_addToRearA_A(), "previous", 1, null), 1, null, Result.NoSuchElement));
			printTest("emptyList_addToRearA_A_testListIterator1PreviousIndexAfterPrev", testListIteratorPreviousIndex(null, getListIteratorAfterCall(emptyList_addToRearA_A(), "previous", 1, null), 1, -1, Result.MatchingValue));
			printTest("emptyList_addToRearA_A_testListIterator1RemoveAfterPrev", testListIteratorRemove(null, getListIteratorAfterCall(emptyList_addToRearA_A(), "previous", 1, null), 1, Result.NoException));
			printTest("emptyList_addToRearA_A_testListIterator1RealRemoveAfterPrev", testListIteratorRealRemoveAfterPrev(emptyList_addToRearA_A(), 1, 1, "[]", Result.MatchingValue));
			printTest("emptyList_addToRearA_A_testListIterator1SetAfterPrev", testListIteratorSet(null, getListIteratorAfterCall(emptyList_addToRearA_A(), "previous", 1, null), 1, ELEMENT_D, Result.NoException));
			printTest("emptyList_addToRearA_A_testListIterator1RealSetAfterPrev", testListIteratorRealSetAfterPrev(emptyList_addToRearA_A(), 1, 1, ELEMENT_D, "[4]", Result.MatchingValue));
			printTest("emptyList_addToRearA_A_testListIterator1AddAfterPrev", testListIteratorAdd(null, getListIteratorAfterCall(emptyList_addToRearA_A(), "previous", 1, null), 1, ELEMENT_D, Result.NoException));
			printTest("emptyList_addToRearA_A_testListIterator1RealAddAfterPrev", testListIteratorRealAddAfterPrev(emptyList_addToRearA_A(), 1, 1, ELEMENT_D, "[4, 1]", Result.MatchingValue));
			}
		} catch (Exception e) {
			System.out.printf("***UNABLE TO RUN/COMPLETE %s***\n", "test_emptyList_addToRearA_A");
			e.printStackTrace();
		}
	}

	//////////////////////////////////////
	// SCENARIO: [] -> add(A) -> [A]
	//  XXX SCENARIO 4
	//////////////////////////////////////
	private ListADT<Integer> emptyList_addA_A() {
		// cast to UnorderedListADT to make addToFront() available
		IndexedListADT<Integer> list = (IndexedListADT<Integer>) newList(); 
		list.add(ELEMENT_A);
		return list;
	}
	private void test_emptyList_addA_A(){
		try {
			// ListADT
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
			printTest(	"emptyList_addA_A_testAddAfterCB", testAddAfter(emptyList_addA_A(), ELEMENT_C, ELEMENT_B, Result.ElementNotFound));
			printTest(	"emptyList_addA_A_testAddAfterAB", testAddAfter(emptyList_addA_A(), ELEMENT_A, ELEMENT_B, Result.NoException));
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
			printTest("emptyList_addA_A_iteratorTestRealRemove",testIteratorRealRemove(emptyList_addA_A(), 1, "[]", Result.MatchingValue));
			//ListIterator
			if(newList().getClass()==testDLL().getClass()){
			printTest("emptyList_addA_A_testListIteratorUnIndexed", testListIteratorUnIndexed(emptyList_addA_A(), Result.NoException));
			printTest("emptyList_addA_A_testListIteratorIndexedNeg1", testListIteratorIndexed(emptyList_addA_A(), -1, Result.IndexOutOfBounds));
			printTest("emptyList_addA_A_testListIteratorIndexed0", testListIteratorIndexed(emptyList_addA_A(), 0, Result.NoException));
			printTest("emptyList_addA_A_testListIteratorIndexed1", testListIteratorIndexed(emptyList_addA_A(), 1, Result.NoException));
			printTest("emptyList_addA_A_testListIteratorIndexed2", testListIteratorIndexed(emptyList_addA_A(), 2, Result.IndexOutOfBounds));
			
			printTest("emptyList_addA_A_testListIteratorHasNext", testListIteratorHasNext(emptyList_addA_A(),null, null, Result.True));
			printTest("emptyList_addA_A_testListIteratorNext", testListIteratorNext(emptyList_addA_A(),null, null, ELEMENT_A, Result.MatchingValue));
			printTest("emptyList_addA_A_testListIteratorNextIndex", testListIteratorNextIndex(emptyList_addA_A(),null, null, 0, Result.MatchingValue));
			printTest("emptyList_addA_A_testListIteratorHasPrevious", testListIteratorHasPrevious(emptyList_addA_A(),null, null, Result.False));
			printTest("emptyList_addA_A_testListIteratorPrevious", testListIteratorPrevious(emptyList_addA_A(),null, null, null, Result.NoSuchElement));
			printTest("emptyList_addA_A_testListIteratorPreviousIndex", testListIteratorPreviousIndex(emptyList_addA_A(),null, null, -1, Result.MatchingValue));
			printTest("emptyList_addA_A_testListIteratorRemove", testListIteratorRemove(emptyList_addA_A(),null, null, Result.IllegalState));
			printTest("emptyList_addA_A_testListIteratorRealRemove", testListIteratorRealRemoveAfterNext(emptyList_addA_A(), null, 0, "[1]", Result.MatchingValue));
			printTest("emptyList_addA_A_testListIteratorSet", testListIteratorSet(emptyList_addA_A(), null,null, ELEMENT_D, Result.IllegalState));
			printTest("emptyList_addA_A_testListIteratorRealSet", testListIteratorRealSetAfterNext(emptyList_addA_A(), null, 0, ELEMENT_D, "[1]", Result.MatchingValue));
			printTest("emptyList_addA_A_testListIteratorAdd", testListIteratorAdd(emptyList_addA_A(),null, null, ELEMENT_D, Result.NoException));
			printTest("emptyList_addA_A_testListIteratorRealAdd", testListIteratorRealAddAfterNext(emptyList_addA_A(), null, 0, ELEMENT_D, "[4, 1]", Result.MatchingValue));
			
			printTest("emptyList_addA_A_testListIteratorHasNextAfterNext", testListIteratorHasNext(null, getListIteratorAfterCall(emptyList_addA_A(), "next", null, null), null, Result.False));
			printTest("emptyList_addA_A_testListIteratorNextAfterNext", testListIteratorNext(null,getListIteratorAfterCall(emptyList_addA_A(), "next", null, null),null, null, Result.NoSuchElement));
			printTest("emptyList_addA_A_testListIteratorNextIndexAfterNext", testListIteratorNextIndex(null,getListIteratorAfterCall(emptyList_addA_A(), "next", null, null),null, 1, Result.MatchingValue));
			printTest("emptyList_addA_A_testListIteratorHasPreviousAfterNext", testListIteratorHasPrevious(null,getListIteratorAfterCall(emptyList_addA_A(), "next", null, null),null, Result.True));
			printTest("emptyList_addA_A_testListIteratorPreviousAfterNext", testListIteratorPrevious(null, getListIteratorAfterCall(emptyList_addA_A(), "next", null, null), null, ELEMENT_A, Result.MatchingValue));
			printTest("emptyList_addA_A_testListIteratorPreviousIndexAfterNext", testListIteratorPreviousIndex(null, getListIteratorAfterCall(emptyList_addA_A(), "next", null, null), null, 0, Result.MatchingValue));
			printTest("emptyList_addA_A_testListIteratorRemoveAfterNext", testListIteratorRemove(null, getListIteratorAfterCall(emptyList_addA_A(), "next", null, null), null, Result.NoException));
			printTest("emptyList_addA_A_testListIteratorRealRemoveAfterNext", testListIteratorRealRemoveAfterNext(emptyList_addA_A(), null, 1, "[]", Result.MatchingValue));
			printTest("emptyList_addA_A_testListIteratorSetAfterNext", testListIteratorSet(null, getListIteratorAfterCall(emptyList_addA_A(), "next", null, null), null, ELEMENT_D, Result.NoException));
			printTest("emptyList_addA_A_testListIteratorRealSetAfterNext", testListIteratorRealSetAfterNext(emptyList_addA_A(), null, 1, ELEMENT_D, "[4]", Result.MatchingValue));
			printTest("emptyList_addA_A_testListIteratorAddAfterNext", testListIteratorAdd(null, getListIteratorAfterCall(emptyList_addA_A(), "next", null, null), null, ELEMENT_D, Result.NoException));
			printTest("emptyList_addA_A_testListIteratorRealAddAfterNext", testListIteratorRealAddAfterNext(emptyList_addA_A(), null, 1, ELEMENT_D, "[1, 4]", Result.MatchingValue));
			
			printTest("emptyList_addA_A_testListIterator0HasNext", testListIteratorHasNext(emptyList_addA_A(), null, 0, Result.True));
			printTest("emptyList_addA_A_testListIterator0Next", testListIteratorNext(emptyList_addA_A(), null, 0, ELEMENT_A, Result.MatchingValue));
			printTest("emptyList_addA_A_testListIterator0NextIndex", testListIteratorNextIndex(emptyList_addA_A(), null, 0, 0, Result.MatchingValue));
			printTest("emptyList_addA_A_testListIterator0HasPrevious", testListIteratorHasPrevious(emptyList_addA_A(), null, 0, Result.False));
			printTest("emptyList_addA_A_testListIterator0Previous", testListIteratorPrevious(emptyList_addA_A(), null, 0, null, Result.NoSuchElement));
			printTest("emptyList_addA_A_testListIterator0PreviousIndex", testListIteratorPreviousIndex(emptyList_addA_A(), null, 0, -1, Result.MatchingValue));
			printTest("emptyList_addA_A_testListIterator0Remove", testListIteratorRemove(emptyList_addA_A(), null, 0, Result.IllegalState));
			printTest("emptyList_addA_A_testListIterator0RealRemove", testListIteratorRealRemoveAfterNext(emptyList_addA_A(), 0, 0, "[1]", Result.MatchingValue));
			printTest("emptyList_addA_A_testListIterator0Set", testListIteratorSet(emptyList_addA_A(), null, 0, ELEMENT_D, Result.IllegalState));
			printTest("emptyList_addA_A_testListIterator0RealSet", testListIteratorRealSetAfterNext(emptyList_addA_A(), 0, 0, ELEMENT_D, "[1]", Result.MatchingValue));
			printTest("emptyList_addA_A_testListIterator0Add", testListIteratorAdd(emptyList_addA_A(), null, 0, ELEMENT_D, Result.NoException));
			printTest("emptyList_addA_A_testListIterator0RealAdd", testListIteratorRealAddAfterNext(emptyList_addA_A(), 0, 0, ELEMENT_D, "[4, 1]", Result.MatchingValue));
			
			printTest("emptyList_addA_A_testListIterator0HasNextAfterNext", testListIteratorHasNext(null, getListIteratorAfterCall(emptyList_addA_A(), "next", 0, null), null, Result.False));
			printTest("emptyList_addA_A_testListIterator0NextAfterNext", testListIteratorNext(null, getListIteratorAfterCall(emptyList_addA_A(), "next", 0, null), null, null, Result.NoSuchElement));
			printTest("emptyList_addA_A_testListIterator0NextIndexAfterNext", testListIteratorNextIndex(null, getListIteratorAfterCall(emptyList_addA_A(), "next", 0, null), null, 1, Result.MatchingValue));
			printTest("emptyList_addA_A_testListIterator0HasPreviousAfterNext", testListIteratorHasPrevious(null, getListIteratorAfterCall(emptyList_addA_A(), "next", 0, null), null, Result.True));
			printTest("emptyList_addA_A_testListIterator0PreviousAfterNext", testListIteratorPrevious(null, getListIteratorAfterCall(emptyList_addA_A(), "next", 0, null), null, ELEMENT_A, Result.MatchingValue));
			printTest("emptyList_addA_A_testListIterator0PreviousIndexAfterNext", testListIteratorPreviousIndex(null, getListIteratorAfterCall(emptyList_addA_A(), "next", 0, null), null, 0, Result.MatchingValue));
			printTest("emptyList_addA_A_testListIterator0RemoveAfterNext", testListIteratorRemove(null, getListIteratorAfterCall(emptyList_addA_A(), "next", 0, null), null, Result.NoException));
			printTest("emptyList_addA_A_testListIterator0RealRemoveAfterNext", testListIteratorRealRemoveAfterNext(emptyList_addA_A(), 0, 1, "[]", Result.MatchingValue));
			printTest("emptyList_addA_A_testListIterator0SetAfterNext", testListIteratorSet(null, getListIteratorAfterCall(emptyList_addA_A(), "next", 0, null), null, ELEMENT_D, Result.NoException));
			printTest("emptyList_addA_A_testListIterator0RealSetAfterNext", testListIteratorRealSetAfterNext(emptyList_addA_A(), 0, 1, ELEMENT_D, "[4]", Result.MatchingValue));
			printTest("emptyList_addA_A_testListIterator0AddAfterNext", testListIteratorAdd(null, getListIteratorAfterCall(emptyList_addA_A(), "next", 0, null), null, ELEMENT_D, Result.NoException));
			printTest("emptyList_addA_A_testListIterator0RealAddAfterNext", testListIteratorRealAddAfterNext(emptyList_addA_A(), 0, 1, ELEMENT_D, "[1, 4]", Result.MatchingValue));
			
			printTest("emptyList_addA_A_testListIterator1HasNext", testListIteratorHasNext(emptyList_addA_A(), null, 1, Result.False));
			printTest("emptyList_addA_A_testListIterator1Next", testListIteratorNext(emptyList_addA_A(), null, 1, null, Result.NoSuchElement));
			printTest("emptyList_addA_A_testListIterator1NextIndex", testListIteratorNextIndex(emptyList_addA_A(), null, 1, 1, Result.MatchingValue));
			printTest("emptyList_addA_A_testListIterator1HasPrevious", testListIteratorHasPrevious(emptyList_addA_A(), null, 1, Result.True));
			printTest("emptyList_addA_A_testListIterator1Previous", testListIteratorPrevious(emptyList_addA_A(), null, 1, ELEMENT_A, Result.MatchingValue));
			printTest("emptyList_addA_A_testListIterator1PreviousIndex", testListIteratorPreviousIndex(emptyList_addA_A(), null, 1, 0, Result.MatchingValue));
			printTest("emptyList_addA_A_testListIterator1Remove", testListIteratorRemove(emptyList_addA_A(), null, 1, Result.IllegalState));
			printTest("emptyList_addA_A_testListIterator1RealRemove", testListIteratorRealRemoveAfterNext(emptyList_addA_A(), 1, 0, "[1]", Result.MatchingValue));
			printTest("emptyList_addA_A_testListIterator1Set", testListIteratorSet(emptyList_addA_A(), null, 1, ELEMENT_D, Result.IllegalState));
			printTest("emptyList_addA_A_testListIterator1RealSet", testListIteratorRealSetAfterNext(emptyList_addA_A(), 1, 0, ELEMENT_D, "[1]", Result.MatchingValue));
			printTest("emptyList_addA_A_testListIterator1Add", testListIteratorAdd(emptyList_addA_A(), null, 1, ELEMENT_D, Result.NoException));
			printTest("emptyList_addA_A_testListIterator1RealAdd", testListIteratorRealAddAfterNext(emptyList_addA_A(), 1, 0, ELEMENT_D, "[1, 4]", Result.MatchingValue));
			
			printTest("emptyList_addA_A_testListIterator1HasNextAfterPrev", testListIteratorHasNext(null, getListIteratorAfterCall(emptyList_addA_A(), "previous", 1, null), 1, Result.True));
			printTest("emptyList_addA_A_testListIterator1NextAfterPrev", testListIteratorNext(null, getListIteratorAfterCall(emptyList_addA_A(), "previous", 1, null), 1, ELEMENT_A, Result.MatchingValue));
			printTest("emptyList_addA_A_testListIterator1NextIndexAfterPrev", testListIteratorNextIndex(null, getListIteratorAfterCall(emptyList_addA_A(), "previous", 1, null), 1, 0, Result.MatchingValue));
			printTest("emptyList_addA_A_testListIterator1HasPreviousAfterPrev", testListIteratorHasPrevious(null, getListIteratorAfterCall(emptyList_addA_A(), "previous", 1, null), 1, Result.False));
			printTest("emptyList_addA_A_testListIterator1PreviousAfterPrev", testListIteratorPrevious(null, getListIteratorAfterCall(emptyList_addA_A(), "previous", 1, null), 1, null, Result.NoSuchElement));
			printTest("emptyList_addA_A_testListIterator1PreviousIndexAfterPrev", testListIteratorPreviousIndex(null, getListIteratorAfterCall(emptyList_addA_A(), "previous", 1, null), 1, -1, Result.MatchingValue));
			printTest("emptyList_addA_A_testListIterator1RemoveAfterPrev", testListIteratorRemove(null, getListIteratorAfterCall(emptyList_addA_A(), "previous", 1, null), 1, Result.NoException));
			printTest("emptyList_addA_A_testListIterator1RealRemoveAfterPrev", testListIteratorRealRemoveAfterPrev(emptyList_addA_A(), 1, 1, "[]", Result.MatchingValue));
			printTest("emptyList_addA_A_testListIterator1SetAfterPrev", testListIteratorSet(null, getListIteratorAfterCall(emptyList_addA_A(), "previous", 1, null), 1, ELEMENT_D, Result.NoException));
			printTest("emptyList_addA_A_testListIterator1RealSetAfterPrev", testListIteratorRealSetAfterPrev(emptyList_addA_A(), 1, 1, ELEMENT_D, "[4]", Result.MatchingValue));
			printTest("emptyList_addA_A_testListIterator1AddAfterPrev", testListIteratorAdd(null, getListIteratorAfterCall(emptyList_addA_A(), "previous", 1, null), 1, ELEMENT_D, Result.NoException));
			printTest("emptyList_addA_A_testListIterator1RealAddAfterPrev", testListIteratorRealAddAfterPrev(emptyList_addA_A(), 1, 1, ELEMENT_D, "[4, 1]", Result.MatchingValue));
			}
		} catch (Exception e) {
			System.out.printf("***UNABLE TO RUN/COMPLETE %s***\n", "test_emptyList_addA_A");
			e.printStackTrace();
		}
	}

	//////////////////////////////////////
	// SCENARIO: [] -> add(0,A) -> [A]
	//  XXX SCENARIO 5
	//////////////////////////////////////
	private ListADT<Integer> emptyList_add0A_A() {
		// cast to UnorderedListADT to make addToFront() available
		IndexedListADT<Integer> list = (IndexedListADT<Integer>) newList(); 
		list.add(0,ELEMENT_A);
		return list;
	}
	private void test_emptyList_add0A_A(){
		try {
			// ListADT
			printTest("emptyList_add0A_A_testRemoveFirst", testRemoveFirst(emptyList_add0A_A(), ELEMENT_A, Result.MatchingValue));
			printTest("emptyList_add0A_A_testRemoveLast", testRemoveLast(emptyList_add0A_A(), ELEMENT_A, Result.MatchingValue));
			printTest("emptyList_add0A_A_testRemoveA", testRemoveElement(emptyList_add0A_A(), ELEMENT_A, Result.MatchingValue));
			printTest("emptyList_add0A_A_testRemoveB", testRemoveElement(emptyList_add0A_A(), ELEMENT_B, Result.ElementNotFound));
			printTest("emptyList_add0A_A_testFirst", testFirst(emptyList_add0A_A(), ELEMENT_A, Result.MatchingValue));
			printTest("emptyList_add0A_A_testLast", testLast(emptyList_add0A_A(), ELEMENT_A, Result.MatchingValue));
			printTest("emptyList_add0A_A_testContainsA", testContains(emptyList_add0A_A(), ELEMENT_A, Result.True));
			printTest("emptyList_add0A_A_testContainsB", testContains(emptyList_add0A_A(), ELEMENT_B, Result.False));
			printTest("emptyList_add0A_A_testIsEmpty", testIsEmpty(emptyList_add0A_A(), Result.False));
			printTest("emptyList_add0A_A_testSize", testSize(emptyList_add0A_A(), 1));
			printTest("emptyList_add0A_A_testToString", testToString(emptyList_add0A_A(), Result.ValidString));
			// UnorderedListADT
			printTest("emptyList_add0A_A_testAddToFrontB", testAddToFront(emptyList_add0A_A(), ELEMENT_B, Result.NoException));
			printTest("emptyList_add0A_A_testAddToRearB", testAddToRear(emptyList_add0A_A(), ELEMENT_A, Result.NoException));
			printTest(	"emptyList_add0A_A_testAddAfterCB", testAddAfter(emptyList_add0A_A(), ELEMENT_C, ELEMENT_B, Result.ElementNotFound));
			printTest(	"emptyList_add0A_A_testAddAfterAB", testAddAfter(emptyList_add0A_A(), ELEMENT_A, ELEMENT_B, Result.NoException));
			// IndexedListADT
			printTest("emptyList_add0A_A_testAddAtIndexNeg1B", testAddAtIndex(emptyList_add0A_A(), -1, ELEMENT_B, Result.IndexOutOfBounds));
			printTest("emptyList_add0A_A_testAddAtIndex0B", testAddAtIndex(emptyList_add0A_A(), 0, ELEMENT_B, Result.NoException));
			printTest("emptyList_add0A_A_testAddAtIndex1B", testAddAtIndex(emptyList_add0A_A(), 1, ELEMENT_B, Result.NoException));
			printTest("emptyList_add0A_A_testSetNeg1B", testSet(emptyList_add0A_A(), -1, ELEMENT_B, Result.IndexOutOfBounds));
			printTest("emptyList_add0A_A_testSet0B", testSet(emptyList_add0A_A(), 0, ELEMENT_B, Result.NoException));
			printTest("emptyList_add0A_A_testAddB", testAdd(emptyList_add0A_A(), ELEMENT_B, Result.NoException));
			printTest("emptyList_add0A_A_testGetNeg1", testGet(emptyList_add0A_A(), -1, null, Result.IndexOutOfBounds));
			printTest("emptyList_add0A_A_testGet0", testGet(emptyList_add0A_A(), 0, ELEMENT_A, Result.MatchingValue));
			printTest("emptyList_add0A_A_testIndexOfA", testIndexOf(emptyList_add0A_A(), ELEMENT_A, 0));
			printTest("emptyList_add0A_A_testIndexOfB", testIndexOf(emptyList_add0A_A(), ELEMENT_B, -1));
			printTest("emptyList_add0A_A_testRemoveNeg1", testRemoveIndex(emptyList_add0A_A(), -1, null, Result.IndexOutOfBounds));
			printTest("emptyList_add0A_A_testRemove0", testRemoveIndex(emptyList_add0A_A(), 0, ELEMENT_A, Result.MatchingValue));
			printTest("emptyList_add0A_A_testRemove1", testRemoveIndex(emptyList_add0A_A(), 1, null, Result.IndexOutOfBounds));
			// Iterator
			printTest("emptyList_add0A_A_testIterator", testIterator(emptyList_add0A_A(), Result.NoException));
			printTest("emptyList_add0A_A_testIteratorHasNext", testIteratorHasNext(emptyList_add0A_A().iterator(), Result.True));
			printTest("emptyList_add0A_A_testIteratorNext", testIteratorNext(emptyList_add0A_A().iterator(), ELEMENT_A, Result.MatchingValue));
			printTest("emptyList_add0A_A_testIteratorRemove", testIteratorRemove(emptyList_add0A_A().iterator(), Result.IllegalState));
			printTest("emptyList_add0A_A_iteratorNext_testIteratorHasNext", testIteratorHasNext(iteratorAfterNext(emptyList_add0A_A(), 1), Result.False));
			printTest("emptyList_add0A_A_iteratorNext_testIteratorNext", testIteratorNext(iteratorAfterNext(emptyList_add0A_A(), 1), null, Result.NoSuchElement));
			printTest("emptyList_add0A_A_iteratorNext_testIteratorRemove", testIteratorRemove(iteratorAfterNext(emptyList_add0A_A(), 1), Result.NoException));
			printTest("emptyList_add0A_A_iteratorNextRemove_testIteratorHasNext", testIteratorHasNext(iteratorAfterRemove(iteratorAfterNext(emptyList_add0A_A(), 1)), Result.False));
			printTest("emptyList_add0A_A_iteratorNextRemove_testIteratorNext", testIteratorNext(iteratorAfterRemove(iteratorAfterNext(emptyList_add0A_A(), 1)), null, Result.NoSuchElement));
			printTest("emptyList_add0A_A_iteratorNextRemove_testIteratorRemove", testIteratorRemove(iteratorAfterRemove(iteratorAfterNext(emptyList_add0A_A(), 1)), Result.IllegalState));
			printTest("emptyList_add0A_A_iteratorTestRealRemove",testIteratorRealRemove(emptyList_add0A_A(), 1, "[]", Result.MatchingValue));
			//ListIterator
			if(newList().getClass()==testDLL().getClass()){
			printTest("emptyList_add0A_A_testListIteratorUnIndexed", testListIteratorUnIndexed(emptyList_add0A_A(), Result.NoException));
			printTest("emptyList_add0A_A_testListIteratorIndexedNeg1", testListIteratorIndexed(emptyList_add0A_A(), -1, Result.IndexOutOfBounds));
			printTest("emptyList_add0A_A_testListIteratorIndexed0", testListIteratorIndexed(emptyList_add0A_A(), 0, Result.NoException));
			printTest("emptyList_add0A_A_testListIteratorIndexed1", testListIteratorIndexed(emptyList_add0A_A(), 1, Result.NoException));
			printTest("emptyList_add0A_A_testListIteratorIndexed2", testListIteratorIndexed(emptyList_add0A_A(), 2, Result.IndexOutOfBounds));
			
			printTest("emptyList_add0A_A_testListIteratorHasNext", testListIteratorHasNext(emptyList_add0A_A(),null, null, Result.True));
			printTest("emptyList_add0A_A_testListIteratorNext", testListIteratorNext(emptyList_add0A_A(),null, null, ELEMENT_A, Result.MatchingValue));
			printTest("emptyList_add0A_A_testListIteratorNextIndex", testListIteratorNextIndex(emptyList_add0A_A(),null, null, 0, Result.MatchingValue));
			printTest("emptyList_add0A_A_testListIteratorHasPrevious", testListIteratorHasPrevious(emptyList_add0A_A(),null, null, Result.False));
			printTest("emptyList_add0A_A_testListIteratorPrevious", testListIteratorPrevious(emptyList_add0A_A(),null, null, null, Result.NoSuchElement));
			printTest("emptyList_add0A_A_testListIteratorPreviousIndex", testListIteratorPreviousIndex(emptyList_add0A_A(),null, null, -1, Result.MatchingValue));
			printTest("emptyList_add0A_A_testListIteratorRemove", testListIteratorRemove(emptyList_add0A_A(),null, null, Result.IllegalState));
			printTest("emptyList_add0A_A_testListIteratorRealRemove", testListIteratorRealRemoveAfterNext(emptyList_add0A_A(), null, 0, "[1]", Result.MatchingValue));
			printTest("emptyList_add0A_A_testListIteratorSet", testListIteratorSet(emptyList_add0A_A(), null,null, ELEMENT_D, Result.IllegalState));
			printTest("emptyList_add0A_A_testListIteratorRealSet", testListIteratorRealSetAfterNext(emptyList_add0A_A(), null, 0, ELEMENT_D, "[1]", Result.MatchingValue));
			printTest("emptyList_add0A_A_testListIteratorAdd", testListIteratorAdd(emptyList_add0A_A(),null, null, ELEMENT_D, Result.NoException));
			printTest("emptyList_add0A_A_testListIteratorRealAdd", testListIteratorRealAddAfterNext(emptyList_add0A_A(), null, 0, ELEMENT_D, "[4, 1]", Result.MatchingValue));
			
			printTest("emptyList_add0A_A_testListIteratorHasNextAfterNext", testListIteratorHasNext(null, getListIteratorAfterCall(emptyList_add0A_A(), "next", null, null), null, Result.False));
			printTest("emptyList_add0A_A_testListIteratorNextAfterNext", testListIteratorNext(null,getListIteratorAfterCall(emptyList_add0A_A(), "next", null, null),null, null, Result.NoSuchElement));
			printTest("emptyList_add0A_A_testListIteratorNextIndexAfterNext", testListIteratorNextIndex(null,getListIteratorAfterCall(emptyList_add0A_A(), "next", null, null),null, 1, Result.MatchingValue));
			printTest("emptyList_add0A_A_testListIteratorHasPreviousAfterNext", testListIteratorHasPrevious(null,getListIteratorAfterCall(emptyList_add0A_A(), "next", null, null),null, Result.True));
			printTest("emptyList_add0A_A_testListIteratorPreviousAfterNext", testListIteratorPrevious(null, getListIteratorAfterCall(emptyList_add0A_A(), "next", null, null), null, ELEMENT_A, Result.MatchingValue));
			printTest("emptyList_add0A_A_testListIteratorPreviousIndexAfterNext", testListIteratorPreviousIndex(null, getListIteratorAfterCall(emptyList_add0A_A(), "next", null, null), null, 0, Result.MatchingValue));
			printTest("emptyList_add0A_A_testListIteratorRemoveAfterNext", testListIteratorRemove(null, getListIteratorAfterCall(emptyList_add0A_A(), "next", null, null), null, Result.NoException));
			printTest("emptyList_add0A_A_testListIteratorRealRemoveAfterNext", testListIteratorRealRemoveAfterNext(emptyList_add0A_A(), null, 1, "[]", Result.MatchingValue));
			printTest("emptyList_add0A_A_testListIteratorSetAfterNext", testListIteratorSet(null, getListIteratorAfterCall(emptyList_add0A_A(), "next", null, null), null, ELEMENT_D, Result.NoException));
			printTest("emptyList_add0A_A_testListIteratorRealSetAfterNext", testListIteratorRealSetAfterNext(emptyList_add0A_A(), null, 1, ELEMENT_D, "[4]", Result.MatchingValue));
			printTest("emptyList_add0A_A_testListIteratorAddAfterNext", testListIteratorAdd(null, getListIteratorAfterCall(emptyList_add0A_A(), "next", null, null), null, ELEMENT_D, Result.NoException));
			printTest("emptyList_add0A_A_testListIteratorRealAddAfterNext", testListIteratorRealAddAfterNext(emptyList_add0A_A(), null, 1, ELEMENT_D, "[1, 4]", Result.MatchingValue));
			
			printTest("emptyList_add0A_A_testListIterator0HasNext", testListIteratorHasNext(emptyList_add0A_A(), null, 0, Result.True));
			printTest("emptyList_add0A_A_testListIterator0Next", testListIteratorNext(emptyList_add0A_A(), null, 0, ELEMENT_A, Result.MatchingValue));
			printTest("emptyList_add0A_A_testListIterator0NextIndex", testListIteratorNextIndex(emptyList_add0A_A(), null, 0, 0, Result.MatchingValue));
			printTest("emptyList_add0A_A_testListIterator0HasPrevious", testListIteratorHasPrevious(emptyList_add0A_A(), null, 0, Result.False));
			printTest("emptyList_add0A_A_testListIterator0Previous", testListIteratorPrevious(emptyList_add0A_A(), null, 0, null, Result.NoSuchElement));
			printTest("emptyList_add0A_A_testListIterator0PreviousIndex", testListIteratorPreviousIndex(emptyList_add0A_A(), null, 0, -1, Result.MatchingValue));
			printTest("emptyList_add0A_A_testListIterator0Remove", testListIteratorRemove(emptyList_add0A_A(), null, 0, Result.IllegalState));
			printTest("emptyList_add0A_A_testListIterator0RealRemove", testListIteratorRealRemoveAfterNext(emptyList_add0A_A(), 0, 0, "[1]", Result.MatchingValue));
			printTest("emptyList_add0A_A_testListIterator0Set", testListIteratorSet(emptyList_add0A_A(), null, 0, ELEMENT_D, Result.IllegalState));
			printTest("emptyList_add0A_A_testListIterator0RealSet", testListIteratorRealSetAfterNext(emptyList_add0A_A(), 0, 0, ELEMENT_D, "[1]", Result.MatchingValue));
			printTest("emptyList_add0A_A_testListIterator0Add", testListIteratorAdd(emptyList_add0A_A(), null, 0, ELEMENT_D, Result.NoException));
			printTest("emptyList_add0A_A_testListIterator0RealAdd", testListIteratorRealAddAfterNext(emptyList_add0A_A(), 0, 0, ELEMENT_D, "[4, 1]", Result.MatchingValue));
			
			printTest("emptyList_add0A_A_testListIterator0HasNextAfterNext", testListIteratorHasNext(null, getListIteratorAfterCall(emptyList_add0A_A(), "next", 0, null), null, Result.False));
			printTest("emptyList_add0A_A_testListIterator0NextAfterNext", testListIteratorNext(null, getListIteratorAfterCall(emptyList_add0A_A(), "next", 0, null), null, null, Result.NoSuchElement));
			printTest("emptyList_add0A_A_testListIterator0NextIndexAfterNext", testListIteratorNextIndex(null, getListIteratorAfterCall(emptyList_add0A_A(), "next", 0, null), null, 1, Result.MatchingValue));
			printTest("emptyList_add0A_A_testListIterator0HasPreviousAfterNext", testListIteratorHasPrevious(null, getListIteratorAfterCall(emptyList_add0A_A(), "next", 0, null), null, Result.True));
			printTest("emptyList_add0A_A_testListIterator0PreviousAfterNext", testListIteratorPrevious(null, getListIteratorAfterCall(emptyList_add0A_A(), "next", 0, null), null, ELEMENT_A, Result.MatchingValue));
			printTest("emptyList_add0A_A_testListIterator0PreviousIndexAfterNext", testListIteratorPreviousIndex(null, getListIteratorAfterCall(emptyList_add0A_A(), "next", 0, null), null, 0, Result.MatchingValue));
			printTest("emptyList_add0A_A_testListIterator0RemoveAfterNext", testListIteratorRemove(null, getListIteratorAfterCall(emptyList_add0A_A(), "next", 0, null), null, Result.NoException));
			printTest("emptyList_add0A_A_testListIterator0RealRemoveAfterNext", testListIteratorRealRemoveAfterNext(emptyList_add0A_A(), 0, 1, "[]", Result.MatchingValue));
			printTest("emptyList_add0A_A_testListIterator0SetAfterNext", testListIteratorSet(null, getListIteratorAfterCall(emptyList_add0A_A(), "next", 0, null), null, ELEMENT_D, Result.NoException));
			printTest("emptyList_add0A_A_testListIterator0RealSetAfterNext", testListIteratorRealSetAfterNext(emptyList_add0A_A(), 0, 1, ELEMENT_D, "[4]", Result.MatchingValue));
			printTest("emptyList_add0A_A_testListIterator0AddAfterNext", testListIteratorAdd(null, getListIteratorAfterCall(emptyList_add0A_A(), "next", 0, null), null, ELEMENT_D, Result.NoException));
			printTest("emptyList_add0A_A_testListIterator0RealAddAfterNext", testListIteratorRealAddAfterNext(emptyList_add0A_A(), 0, 1, ELEMENT_D, "[1, 4]", Result.MatchingValue));
			
			printTest("emptyList_add0A_A_testListIterator1HasNext", testListIteratorHasNext(emptyList_add0A_A(), null, 1, Result.False));
			printTest("emptyList_add0A_A_testListIterator1Next", testListIteratorNext(emptyList_add0A_A(), null, 1, null, Result.NoSuchElement));
			printTest("emptyList_add0A_A_testListIterator1NextIndex", testListIteratorNextIndex(emptyList_add0A_A(), null, 1, 1, Result.MatchingValue));
			printTest("emptyList_add0A_A_testListIterator1HasPrevious", testListIteratorHasPrevious(emptyList_add0A_A(), null, 1, Result.True));
			printTest("emptyList_add0A_A_testListIterator1Previous", testListIteratorPrevious(emptyList_add0A_A(), null, 1, ELEMENT_A, Result.MatchingValue));
			printTest("emptyList_add0A_A_testListIterator1PreviousIndex", testListIteratorPreviousIndex(emptyList_add0A_A(), null, 1, 0, Result.MatchingValue));
			printTest("emptyList_add0A_A_testListIterator1Remove", testListIteratorRemove(emptyList_add0A_A(), null, 1, Result.IllegalState));
			printTest("emptyList_add0A_A_testListIterator1RealRemove", testListIteratorRealRemoveAfterNext(emptyList_add0A_A(), 1, 0, "[1]", Result.MatchingValue));
			printTest("emptyList_add0A_A_testListIterator1Set", testListIteratorSet(emptyList_add0A_A(), null, 1, ELEMENT_D, Result.IllegalState));
			printTest("emptyList_add0A_A_testListIterator1RealSet", testListIteratorRealSetAfterNext(emptyList_add0A_A(), 1, 0, ELEMENT_D, "[1]", Result.MatchingValue));
			printTest("emptyList_add0A_A_testListIterator1Add", testListIteratorAdd(emptyList_add0A_A(), null, 1, ELEMENT_D, Result.NoException));
			printTest("emptyList_add0A_A_testListIterator1RealAdd", testListIteratorRealAddAfterNext(emptyList_add0A_A(), 1, 0, ELEMENT_D, "[1, 4]", Result.MatchingValue));
			
			printTest("emptyList_add0A_A_testListIterator1HasNextAfterPrev", testListIteratorHasNext(null, getListIteratorAfterCall(emptyList_add0A_A(), "previous", 1, null), 1, Result.True));
			printTest("emptyList_add0A_A_testListIterator1NextAfterPrev", testListIteratorNext(null, getListIteratorAfterCall(emptyList_add0A_A(), "previous", 1, null), 1, ELEMENT_A, Result.MatchingValue));
			printTest("emptyList_add0A_A_testListIterator1NextIndexAfterPrev", testListIteratorNextIndex(null, getListIteratorAfterCall(emptyList_add0A_A(), "previous", 1, null), 1, 0, Result.MatchingValue));
			printTest("emptyList_add0A_A_testListIterator1HasPreviousAfterPrev", testListIteratorHasPrevious(null, getListIteratorAfterCall(emptyList_add0A_A(), "previous", 1, null), 1, Result.False));
			printTest("emptyList_add0A_A_testListIterator1PreviousAfterPrev", testListIteratorPrevious(null, getListIteratorAfterCall(emptyList_add0A_A(), "previous", 1, null), 1, null, Result.NoSuchElement));
			printTest("emptyList_add0A_A_testListIterator1PreviousIndexAfterPrev", testListIteratorPreviousIndex(null, getListIteratorAfterCall(emptyList_add0A_A(), "previous", 1, null), 1, -1, Result.MatchingValue));
			printTest("emptyList_add0A_A_testListIterator1RemoveAfterPrev", testListIteratorRemove(null, getListIteratorAfterCall(emptyList_add0A_A(), "previous", 1, null), 1, Result.NoException));
			printTest("emptyList_add0A_A_testListIterator1RealRemoveAfterPrev", testListIteratorRealRemoveAfterPrev(emptyList_add0A_A(), 1, 1, "[]", Result.MatchingValue));
			printTest("emptyList_add0A_A_testListIterator1SetAfterPrev", testListIteratorSet(null, getListIteratorAfterCall(emptyList_add0A_A(), "previous", 1, null), 1, ELEMENT_D, Result.NoException));
			printTest("emptyList_add0A_A_testListIterator1RealSetAfterPrev", testListIteratorRealSetAfterPrev(emptyList_add0A_A(), 1, 1, ELEMENT_D, "[4]", Result.MatchingValue));
			printTest("emptyList_add0A_A_testListIterator1AddAfterPrev", testListIteratorAdd(null, getListIteratorAfterCall(emptyList_add0A_A(), "previous", 1, null), 1, ELEMENT_D, Result.NoException));
			printTest("emptyList_add0A_A_testListIterator1RealAddAfterPrev", testListIteratorRealAddAfterPrev(emptyList_add0A_A(), 1, 1, ELEMENT_D, "[4, 1]", Result.MatchingValue));
			}
		} catch (Exception e) {
			System.out.printf("***UNABLE TO RUN/COMPLETE %s***\n", "test_emptyList_add0A_A");
			e.printStackTrace();
		}
	}
	
	//////////////////////////////////////
	// SCENARIO: [A] -> removeFirst() -> []
	//  XXX SCENARIO 6
	//////////////////////////////////////
	private ListADT<Integer> A_removeFirst_empty() {
		// cast to UnorderedListADT to make addToFront() available
		IndexedListADT<Integer> list = (IndexedListADT<Integer>) newList(); 
		list.add(0,ELEMENT_A);
		list.removeFirst();
		return list;
	}
	private void test_A_removeFirst_empty(){
		try {
			// ListADT
			printTest("A_removeFirst_empty_testRemoveFirst", testRemoveFirst(A_removeFirst_empty(), null, Result.EmptyCollection));
			printTest("A_removeFirst_empty_testRemoveLast", testRemoveLast(A_removeFirst_empty(), null, Result.EmptyCollection));
			printTest("A_removeFirst_empty_testRemoveA", testRemoveElement(A_removeFirst_empty(), null, Result.ElementNotFound));
			printTest("A_removeFirst_empty_testFirst", testFirst(A_removeFirst_empty(), null, Result.EmptyCollection));
			printTest("A_removeFirst_empty_testLast", testLast(A_removeFirst_empty(), null, Result.EmptyCollection));
			printTest("A_removeFirst_empty_testContainsA", testContains(A_removeFirst_empty(), ELEMENT_A, Result.False));
			printTest("A_removeFirst_empty_testIsEmpty", testIsEmpty(A_removeFirst_empty(), Result.True));
			printTest("A_removeFirst_empty_testSize", testSize(A_removeFirst_empty(), 0));
			printTest("A_removeFirst_empty_testToString", testToString(A_removeFirst_empty(), Result.ValidString));
			// UnorderedListADT
			printTest("A_removeFirst_empty_testAddToFrontA", testAddToFront(A_removeFirst_empty(), ELEMENT_A, Result.NoException));
			printTest("A_removeFirst_empty_testAddToRearA", testAddToRear(A_removeFirst_empty(), ELEMENT_A, Result.NoException));
			printTest(	"A_removeFirst_empty_testAddAfterBA", testAddAfter(A_removeFirst_empty(), ELEMENT_B, ELEMENT_A, Result.ElementNotFound));
			// IndexedListADT
			printTest("A_removeFirst_empty_testAddAtIndexNeg1", testAddAtIndex(A_removeFirst_empty(), -1, ELEMENT_A, Result.IndexOutOfBounds));
			printTest("A_removeFirst_empty_testAddAtIndex0", testAddAtIndex(A_removeFirst_empty(), 0, ELEMENT_A, Result.NoException));
			printTest("A_removeFirst_empty_testAddAtIndex1", testAddAtIndex(A_removeFirst_empty(), 1, ELEMENT_A, Result.IndexOutOfBounds));
			printTest("A_removeFirst_empty_testSetNeg1A", testSet(A_removeFirst_empty(), -1, ELEMENT_A, Result.IndexOutOfBounds));
			printTest("A_removeFirst_empty_testSet0A", testSet(A_removeFirst_empty(), 0, ELEMENT_A, Result.IndexOutOfBounds));
			printTest("A_removeFirst_empty_testAddA", testAdd(A_removeFirst_empty(), ELEMENT_A, Result.NoException));
			printTest("A_removeFirst_empty_testGetNeg1", testGet(A_removeFirst_empty(), -1, null, Result.IndexOutOfBounds));
			printTest("A_removeFirst_empty_testGet0", testGet(A_removeFirst_empty(), 0, null, Result.IndexOutOfBounds));
			printTest("A_removeFirst_empty_testIndexOfA", testIndexOf(A_removeFirst_empty(), ELEMENT_A, -1));
			printTest("A_removeFirst_empty_testRemoveNeg1", testRemoveIndex(A_removeFirst_empty(), -1, null, Result.IndexOutOfBounds));
			printTest("A_removeFirst_empty_testRemove0", testRemoveIndex(A_removeFirst_empty(), 0, null, Result.IndexOutOfBounds));
			// Iterator
			printTest("A_removeFirst_empty_testIterator", testIterator(A_removeFirst_empty(), Result.NoException));
			printTest("A_removeFirst_empty_testIteratorHasNext", testIteratorHasNext(A_removeFirst_empty().iterator(), Result.False));
			printTest("A_removeFirst_empty_testIteratorNext", testIteratorNext(A_removeFirst_empty().iterator(), null, Result.NoSuchElement));
			printTest("A_removeFirst_empty_testIteratorRemove", testIteratorRemove(A_removeFirst_empty().iterator(), Result.IllegalState));
			//ListIterator
			if(A_removeFirst_empty().getClass()==testDLL().getClass()){
			printTest("A_removeFirst_empty_testListIteratorUnIndexed",testListIteratorUnIndexed(A_removeFirst_empty(), Result.NoException));
			printTest("A_removeFirst_empty_testListIteratorIndexed",testListIteratorIndexed(A_removeFirst_empty(), 0, Result.NoException));
			printTest("A_removeFirst_empty_testListIteratorIndexed1",testListIteratorIndexed(A_removeFirst_empty(), 1, Result.IndexOutOfBounds));
			printTest("A_removeFirst_empty_testListIteratorHasNext", testListIteratorHasNext(A_removeFirst_empty(), null, null, Result.False));
			printTest("A_removeFirst_empty_testListIteratorHasNextIndexed0", testListIteratorHasNext(A_removeFirst_empty(), null, 0, Result.False));
			printTest("A_removeFirst_empty_testListIteratorNext",testListIteratorNext(A_removeFirst_empty(),null, null, null, Result.NoSuchElement));
			printTest("A_removeFirst_empty_testListIteratorNextIndexed0",testListIteratorNext(A_removeFirst_empty(),null, 0, null, Result.NoSuchElement));
			printTest("A_removeFirst_empty_testListIteratorHasPrevious",testListIteratorHasPrevious(A_removeFirst_empty(),null, null, Result.False));
			printTest("A_removeFirst_empty_testListIteratorHasPreviousIndexed0",testListIteratorHasPrevious(A_removeFirst_empty(),null, 0, Result.False));
			printTest("A_removeFirst_empty_testListIteratorPrevious",testListIteratorPrevious(A_removeFirst_empty(),null, null, null, Result.NoSuchElement));
			printTest("A_removeFirst_empty_testListIteratorPreviousIndexed0",testListIteratorPrevious(A_removeFirst_empty(),null, 0, null, Result.NoSuchElement));
			printTest("A_removeFirst_empty_testListIteratorNextIndex",testListIteratorNextIndex(A_removeFirst_empty(),null, null, 0, Result.MatchingValue));
			printTest("A_removeFirst_empty_testListIteratorNextIndexIndexed0",testListIteratorNextIndex(A_removeFirst_empty(),null, 0, 0, Result.MatchingValue));
			printTest("A_removeFirst_empty_testListIteratorPreviousIndex",testListIteratorPreviousIndex(A_removeFirst_empty(),null, null, -1, Result.MatchingValue));
			printTest("A_removeFirst_empty_testListIteratorPreviousIndexIndexed0",testListIteratorPreviousIndex(A_removeFirst_empty(),null, 0, -1, Result.MatchingValue));
			printTest("A_removeFirst_empty_testListIteratorRemove",testListIteratorRemove(A_removeFirst_empty(),null, null, Result.IllegalState));
			printTest("A_removeFirst_empty_testListIteratorRemoveIndexed0",testListIteratorRemove(A_removeFirst_empty(),null, 0, Result.IllegalState));
			printTest("A_removeFirst_empty_testListIteratorSet",testListIteratorSet(A_removeFirst_empty(),null, null, ELEMENT_A, Result.IllegalState));
			printTest("A_removeFirst_empty_testListIteratorSetIndexed0",testListIteratorSet(A_removeFirst_empty(),null, 0, ELEMENT_A, Result.IllegalState));
			printTest("A_removeFirst_empty_testListIteratorAdd",testListIteratorAdd(A_removeFirst_empty(),null, null, ELEMENT_A, Result.NoException));
			printTest("A_removeFirst_empty_testListIteratorAddIndexed0",testListIteratorAdd(A_removeFirst_empty(),null, 0, ELEMENT_A, Result.NoException));
			}
		} catch (Exception e) {
			System.out.printf("***UNABLE TO RUN/COMPLETE %s***\n", "test_A_removeFirst_empty");
			e.printStackTrace();
		}
	}
	
	//////////////////////////////////////
	// SCENARIO: [A] -> removeLast() -> []
	//  XXX SCENARIO 7
	//////////////////////////////////////
	private ListADT<Integer> A_removeLast_empty() {
		// cast to UnorderedListADT to make addToFront() available
		IndexedListADT<Integer> list = (IndexedListADT<Integer>) newList(); 
		list.add(0,ELEMENT_A);
		list.removeLast();
		return list;
	}
	private void test_A_removeLast_empty(){
		try {
			// ListADT
			printTest("A_removeLast_empty_testRemoveFirst", testRemoveFirst(A_removeLast_empty(), null, Result.EmptyCollection));
			printTest("A_removeLast_empty_testRemoveLast", testRemoveLast(A_removeLast_empty(), null, Result.EmptyCollection));
			printTest("A_removeLast_empty_testRemoveA", testRemoveElement(A_removeLast_empty(), null, Result.ElementNotFound));
			printTest("A_removeLast_empty_testFirst", testFirst(A_removeLast_empty(), null, Result.EmptyCollection));
			printTest("A_removeLast_empty_testLast", testLast(A_removeLast_empty(), null, Result.EmptyCollection));
			printTest("A_removeLast_empty_testContainsA", testContains(A_removeLast_empty(), ELEMENT_A, Result.False));
			printTest("A_removeLast_empty_testIsEmpty", testIsEmpty(A_removeLast_empty(), Result.True));
			printTest("A_removeLast_empty_testSize", testSize(A_removeLast_empty(), 0));
			printTest("A_removeLast_empty_testToString", testToString(A_removeLast_empty(), Result.ValidString));
			// UnorderedListADT
			printTest("A_removeLast_empty_testAddToFrontA", testAddToFront(A_removeLast_empty(), ELEMENT_A, Result.NoException));
			printTest("A_removeLast_empty_testAddToRearA", testAddToRear(A_removeLast_empty(), ELEMENT_A, Result.NoException));
			printTest(	"A_removeLast_empty_testAddAfterBA", testAddAfter(A_removeLast_empty(), ELEMENT_B, ELEMENT_A, Result.ElementNotFound));
			// IndexedListADT
			printTest("A_removeLast_empty_testAddAtIndexNeg1", testAddAtIndex(A_removeLast_empty(), -1, ELEMENT_A, Result.IndexOutOfBounds));
			printTest("A_removeLast_empty_testAddAtIndex0", testAddAtIndex(A_removeLast_empty(), 0, ELEMENT_A, Result.NoException));
			printTest("A_removeLast_empty_testAddAtIndex1", testAddAtIndex(A_removeLast_empty(), 1, ELEMENT_A, Result.IndexOutOfBounds));
			printTest("A_removeLast_empty_testSetNeg1A", testSet(A_removeLast_empty(), -1, ELEMENT_A, Result.IndexOutOfBounds));
			printTest("A_removeLast_empty_testSet0A", testSet(A_removeLast_empty(), 0, ELEMENT_A, Result.IndexOutOfBounds));
			printTest("A_removeLast_empty_testAddA", testAdd(A_removeLast_empty(), ELEMENT_A, Result.NoException));
			printTest("A_removeLast_empty_testGetNeg1", testGet(A_removeLast_empty(), -1, null, Result.IndexOutOfBounds));
			printTest("A_removeLast_empty_testGet0", testGet(A_removeLast_empty(), 0, null, Result.IndexOutOfBounds));
			printTest("A_removeLast_empty_testIndexOfA", testIndexOf(A_removeLast_empty(), ELEMENT_A, -1));
			printTest("A_removeLast_empty_testRemoveNeg1", testRemoveIndex(A_removeLast_empty(), -1, null, Result.IndexOutOfBounds));
			printTest("A_removeLast_empty_testRemove0", testRemoveIndex(A_removeLast_empty(), 0, null, Result.IndexOutOfBounds));
			// Iterator
			printTest("A_removeLast_empty_testIterator", testIterator(A_removeLast_empty(), Result.NoException));
			printTest("A_removeLast_empty_testIteratorHasNext", testIteratorHasNext(A_removeLast_empty().iterator(), Result.False));
			printTest("A_removeLast_empty_testIteratorNext", testIteratorNext(A_removeLast_empty().iterator(), null, Result.NoSuchElement));
			printTest("A_removeLast_empty_testIteratorRemove", testIteratorRemove(A_removeLast_empty().iterator(), Result.IllegalState));
			//ListIterator
			if(A_removeFirst_empty().getClass()==testDLL().getClass()){
			printTest("A_removeLast_empty_testListIteratorUnIndexed",testListIteratorUnIndexed(A_removeLast_empty(), Result.NoException));
			printTest("A_removeLast_empty_testListIteratorIndexed",testListIteratorIndexed(A_removeLast_empty(), 0, Result.NoException));
			printTest("A_removeLast_empty_testListIteratorIndexed1",testListIteratorIndexed(A_removeLast_empty(), 1, Result.IndexOutOfBounds));
			printTest("A_removeLast_empty_testListIteratorHasNext", testListIteratorHasNext(A_removeLast_empty(),null, null, Result.False));
			printTest("A_removeLast_empty_testListIteratorHasNextIndexed0", testListIteratorHasNext(A_removeLast_empty(),null, 0, Result.False));
			printTest("A_removeLast_empty_testListIteratorNext",testListIteratorNext(A_removeLast_empty(),null, null, null, Result.NoSuchElement));
			printTest("A_removeLast_empty_testListIteratorNextIndexed0",testListIteratorNext(A_removeLast_empty(),null, 0, null, Result.NoSuchElement));
			printTest("A_removeLast_empty_testListIteratorHasPrevious",testListIteratorHasPrevious(A_removeLast_empty(),null, null, Result.False));
			printTest("A_removeLast_empty_testListIteratorHasPreviousIndexed0",testListIteratorHasPrevious(A_removeLast_empty(),null, 0, Result.False));
			printTest("A_removeLast_empty_testListIteratorPrevious",testListIteratorPrevious(A_removeLast_empty(),null, null, null, Result.NoSuchElement));
			printTest("A_removeLast_empty_testListIteratorPreviousIndexed0",testListIteratorPrevious(A_removeLast_empty(),null, 0, null, Result.NoSuchElement));
			printTest("A_removeLast_empty_testListIteratorNextIndex",testListIteratorNextIndex(A_removeLast_empty(),null, null, 0, Result.MatchingValue));
			printTest("A_removeLast_empty_testListIteratorNextIndexIndexed0",testListIteratorNextIndex(A_removeLast_empty(),null, 0, 0, Result.MatchingValue));
			printTest("A_removeLast_empty_testListIteratorPreviousIndex",testListIteratorPreviousIndex(A_removeLast_empty(),null, null, -1, Result.MatchingValue));
			printTest("A_removeLast_empty_testListIteratorPreviousIndexIndexed0",testListIteratorPreviousIndex(A_removeLast_empty(),null, 0, -1, Result.MatchingValue));
			printTest("A_removeLast_empty_testListIteratorRemove",testListIteratorRemove(A_removeLast_empty(),null, null, Result.IllegalState));
			printTest("A_removeLast_empty_testListIteratorRemoveIndexed0",testListIteratorRemove(A_removeLast_empty(),null, 0, Result.IllegalState));
			printTest("A_removeLast_empty_testListIteratorSet",testListIteratorSet(A_removeLast_empty(),null, null, ELEMENT_A, Result.IllegalState));
			printTest("A_removeLast_empty_testListIteratorSetIndexed0",testListIteratorSet(A_removeLast_empty(),null, 0, ELEMENT_A, Result.IllegalState));
			printTest("A_removeLast_empty_testListIteratorAdd",testListIteratorAdd(A_removeLast_empty(),null, null, ELEMENT_A, Result.NoException));
			printTest("A_removeLast_empty_testListIteratorAddIndexed0",testListIteratorAdd(A_removeLast_empty(),null, 0, ELEMENT_A, Result.NoException));
			}
		} catch (Exception e) {
			System.out.printf("***UNABLE TO RUN/COMPLETE %s***\n", "test_A_removeLast_empty");
			e.printStackTrace();
		}
	}
	
	//////////////////////////////////////
	// SCENARIO: [A] -> remove(A) -> []
	//  XXX SCENARIO 8
	//////////////////////////////////////
	private ListADT<Integer> A_removeA_empty() {
		// cast to UnorderedListADT to make addToFront() available
		IndexedListADT<Integer> list = (IndexedListADT<Integer>) newList(); 
		list.add(0,ELEMENT_A);
		list.remove(ELEMENT_A);
		return list;
	}
	private void test_A_removeA_empty(){
		try {
			// ListADT
			printTest("A_removeA_empty_testRemoveFirst", testRemoveFirst(A_removeA_empty(), null, Result.EmptyCollection));
			printTest("A_removeA_empty_testRemoveLast", testRemoveLast(A_removeA_empty(), null, Result.EmptyCollection));
			printTest("A_removeA_empty_testRemoveA", testRemoveElement(A_removeA_empty(), null, Result.ElementNotFound));
			printTest("A_removeA_empty_testFirst", testFirst(A_removeA_empty(), null, Result.EmptyCollection));
			printTest("A_removeA_empty_testLast", testLast(A_removeA_empty(), null, Result.EmptyCollection));
			printTest("A_removeA_empty_testContainsA", testContains(A_removeA_empty(), ELEMENT_A, Result.False));
			printTest("A_removeA_empty_testIsEmpty", testIsEmpty(A_removeA_empty(), Result.True));
			printTest("A_removeA_empty_testSize", testSize(A_removeA_empty(), 0));
			printTest("A_removeA_empty_testToString", testToString(A_removeA_empty(), Result.ValidString));
			// UnorderedListADT
			printTest("A_removeA_empty_testAddToFrontA", testAddToFront(A_removeA_empty(), ELEMENT_A, Result.NoException));
			printTest("A_removeA_empty_testAddToRearA", testAddToRear(A_removeA_empty(), ELEMENT_A, Result.NoException));
			printTest(	"A_removeA_empty_testAddAfterBA", testAddAfter(A_removeA_empty(), ELEMENT_B, ELEMENT_A, Result.ElementNotFound));
			// IndexedListADT
			printTest("A_removeA_empty_testAddAtIndexNeg1", testAddAtIndex(A_removeA_empty(), -1, ELEMENT_A, Result.IndexOutOfBounds));
			printTest("A_removeA_empty_testAddAtIndex0", testAddAtIndex(A_removeA_empty(), 0, ELEMENT_A, Result.NoException));
			printTest("A_removeA_empty_testAddAtIndex1", testAddAtIndex(A_removeA_empty(), 1, ELEMENT_A, Result.IndexOutOfBounds));
			printTest("A_removeA_empty_testSetNeg1A", testSet(A_removeA_empty(), -1, ELEMENT_A, Result.IndexOutOfBounds));
			printTest("A_removeA_empty_testSet0A", testSet(A_removeA_empty(), 0, ELEMENT_A, Result.IndexOutOfBounds));
			printTest("A_removeA_empty_testAddA", testAdd(A_removeA_empty(), ELEMENT_A, Result.NoException));
			printTest("A_removeA_empty_testGetNeg1", testGet(A_removeA_empty(), -1, null, Result.IndexOutOfBounds));
			printTest("A_removeA_empty_testGet0", testGet(A_removeA_empty(), 0, null, Result.IndexOutOfBounds));
			printTest("A_removeA_empty_testIndexOfA", testIndexOf(A_removeA_empty(), ELEMENT_A, -1));
			printTest("A_removeA_empty_testRemoveNeg1", testRemoveIndex(A_removeA_empty(), -1, null, Result.IndexOutOfBounds));
			printTest("A_removeA_empty_testRemove0", testRemoveIndex(A_removeA_empty(), 0, null, Result.IndexOutOfBounds));
			// Iterator
			printTest("A_removeA_empty_testIterator", testIterator(A_removeA_empty(), Result.NoException));
			printTest("A_removeA_empty_testIteratorHasNext", testIteratorHasNext(A_removeA_empty().iterator(), Result.False));
			printTest("A_removeA_empty_testIteratorNext", testIteratorNext(A_removeA_empty().iterator(), null, Result.NoSuchElement));
			printTest("A_removeA_empty_testIteratorRemove", testIteratorRemove(A_removeA_empty().iterator(), Result.IllegalState));
			//ListIterator
			if(A_removeFirst_empty().getClass()==testDLL().getClass()){
			printTest("A_removeA_empty_testListIteratorUnIndexed",testListIteratorUnIndexed(A_removeA_empty(), Result.NoException));
			printTest("A_removeA_empty_testListIteratorIndexed",testListIteratorIndexed(A_removeA_empty(), 0, Result.NoException));
			printTest("A_removeA_empty_testListIteratorIndexed1",testListIteratorIndexed(A_removeA_empty(), 1, Result.IndexOutOfBounds));
			printTest("A_removeA_empty_testListIteratorHasNext", testListIteratorHasNext(A_removeA_empty(),null, null, Result.False));
			printTest("A_removeA_empty_testListIteratorHasNextIndexed0", testListIteratorHasNext(A_removeA_empty(),null, 0, Result.False));
			printTest("A_removeA_empty_testListIteratorNext",testListIteratorNext(A_removeA_empty(),null, null, null, Result.NoSuchElement));
			printTest("A_removeA_empty_testListIteratorNextIndexed0",testListIteratorNext(A_removeA_empty(),null, 0, null, Result.NoSuchElement));
			printTest("A_removeA_empty_testListIteratorHasPrevious",testListIteratorHasPrevious(A_removeA_empty(),null, null, Result.False));
			printTest("A_removeA_empty_testListIteratorHasPreviousIndexed0",testListIteratorHasPrevious(A_removeA_empty(),null, 0, Result.False));
			printTest("A_removeA_empty_testListIteratorPrevious",testListIteratorPrevious(A_removeA_empty(),null, null, null, Result.NoSuchElement));
			printTest("A_removeA_empty_testListIteratorPreviousIndexed0",testListIteratorPrevious(A_removeA_empty(),null, 0, null, Result.NoSuchElement));
			printTest("A_removeA_empty_testListIteratorNextIndex",testListIteratorNextIndex(A_removeA_empty(),null, null, 0, Result.MatchingValue));
			printTest("A_removeA_empty_testListIteratorNextIndexIndexed0",testListIteratorNextIndex(A_removeA_empty(),null, 0, 0, Result.MatchingValue));
			printTest("A_removeA_empty_testListIteratorPreviousIndex",testListIteratorPreviousIndex(A_removeA_empty(),null, null, -1, Result.MatchingValue));
			printTest("A_removeA_empty_testListIteratorPreviousIndexIndexed0",testListIteratorPreviousIndex(A_removeA_empty(),null, 0, -1, Result.MatchingValue));
			printTest("A_removeA_empty_testListIteratorRemove",testListIteratorRemove(A_removeA_empty(),null, null, Result.IllegalState));
			printTest("A_removeA_empty_testListIteratorRemoveIndexed0",testListIteratorRemove(A_removeA_empty(),null, 0, Result.IllegalState));
			printTest("A_removeA_empty_testListIteratorSet",testListIteratorSet(A_removeA_empty(),null, null, ELEMENT_A, Result.IllegalState));
			printTest("A_removeA_empty_testListIteratorSetIndexed0",testListIteratorSet(A_removeA_empty(),null, 0, ELEMENT_A, Result.IllegalState));
			printTest("A_removeA_empty_testListIteratorAdd",testListIteratorAdd(A_removeA_empty(),null, null, ELEMENT_A, Result.NoException));
			printTest("A_removeA_empty_testListIteratorAddIndexed0",testListIteratorAdd(A_removeA_empty(),null, 0, ELEMENT_A, Result.NoException));
			}
		} catch (Exception e) {
			System.out.printf("***UNABLE TO RUN/COMPLETE %s***\n", "test_A_removeA_empty");
			e.printStackTrace();
		}
	}
	
	//////////////////////////////////////
	// SCENARIO: [A] -> addToFront(B) -> [B,A]
	//  XXX SCENARIO 9
	//////////////////////////////////////
	private ListADT<Integer> A_addToFrontB_BA() {
		// cast to UnorderedListADT to make addToFront() available
		UnorderedListADT<Integer> list = (UnorderedListADT<Integer>) newList(); 
		list.addToRear(ELEMENT_A);
		list.addToFront(ELEMENT_B);
		return list;
	}
	private void test_A_addToFrontB_BA(){
		try {
			// ListADT
			printTest("A_addToFrontB_BA_testRemoveFirst", testRemoveFirst(A_addToFrontB_BA(), ELEMENT_B, Result.MatchingValue));
			printTest("A_addToFrontB_BA_testRemoveLast", testRemoveLast(A_addToFrontB_BA(), ELEMENT_A, Result.MatchingValue));
			printTest("A_addToFrontB_BA_testRemoveA", testRemoveElement(A_addToFrontB_BA(), ELEMENT_A, Result.MatchingValue));
			printTest("A_addToFrontB_BA_testRemoveB", testRemoveElement(A_addToFrontB_BA(), ELEMENT_B, Result.MatchingValue));
			printTest("A_addToFrontB_BA_testRemoveC", testRemoveElement(A_addToFrontB_BA(), ELEMENT_C, Result.ElementNotFound));
			printTest("A_addToFrontB_BA_testFirst", testFirst(A_addToFrontB_BA(), ELEMENT_B, Result.MatchingValue));
			printTest("A_addToFrontB_BA_testLast", testLast(A_addToFrontB_BA(), ELEMENT_A, Result.MatchingValue));
			printTest("A_addToFrontB_BA_testContainsA", testContains(A_addToFrontB_BA(), ELEMENT_A, Result.True));
			printTest("A_addToFrontB_BA_testContainsB", testContains(A_addToFrontB_BA(), ELEMENT_B, Result.True));
			printTest("A_addToFrontB_BA_testContainsC", testContains(A_addToFrontB_BA(), ELEMENT_C, Result.False));
			printTest("A_addToFrontB_BA_testIsEmpty", testIsEmpty(A_addToFrontB_BA(), Result.False));
			printTest("A_addToFrontB_BA_testSize", testSize(A_addToFrontB_BA(), 2));
			printTest("A_addToFrontB_BA_testToString", testToString(A_addToFrontB_BA(), Result.ValidString));
			// UnorderedListADT
			printTest("A_addToFrontB_BA_testAddToFrontC", testAddToFront(A_addToFrontB_BA(), ELEMENT_C, Result.NoException));
			printTest("A_addToFrontB_BA_testAddToRearC", testAddToRear(A_addToFrontB_BA(), ELEMENT_C, Result.NoException));
			printTest(	"A_addToFrontB_BA_testAddAfterAC", testAddAfter(A_addToFrontB_BA(), ELEMENT_A, ELEMENT_C, Result.NoException));
			printTest(	"A_addToFrontB_BA_testAddAfterBC", testAddAfter(A_addToFrontB_BA(), ELEMENT_B, ELEMENT_C, Result.NoException));
			printTest(	"A_addToFrontB_BA_testAddAfterDC", testAddAfter(A_addToFrontB_BA(), ELEMENT_D, ELEMENT_C, Result.ElementNotFound));
			// IndexedListADT
			printTest("A_addToFrontB_BA_testAddAtIndexNeg1", testAddAtIndex(A_addToFrontB_BA(), -1, null, Result.IndexOutOfBounds));
			printTest("A_addToFrontB_BA_testAddAtIndex0", testAddAtIndex(A_addToFrontB_BA(), 0, ELEMENT_C, Result.NoException));
			printTest("A_addToFrontB_BA_testAddAtIndex1", testAddAtIndex(A_addToFrontB_BA(), 1, ELEMENT_C, Result.NoException));
			printTest("A_addToFrontB_BA_testAddAtIndex2", testAddAtIndex(A_addToFrontB_BA(), 2, ELEMENT_C, Result.NoException));
			printTest("A_addToFrontB_BA_testAddAtIndex3", testAddAtIndex(A_addToFrontB_BA(), 3, null, Result.IndexOutOfBounds));
			printTest("A_addToFrontB_BA_testSetNeg1C", testSet(A_addToFrontB_BA(), -1, null, Result.IndexOutOfBounds));
			printTest("A_addToFrontB_BA_testSet0C", testSet(A_addToFrontB_BA(), 0, ELEMENT_C, Result.NoException));
			printTest("A_addToFrontB_BA_testSet1C", testSet(A_addToFrontB_BA(), 1, ELEMENT_C, Result.NoException));
			printTest("A_addToFrontB_BA_testSet2C", testSet(A_addToFrontB_BA(), 2, null, Result.IndexOutOfBounds));
			printTest("A_addToFrontB_BA_testAddC", testAdd(A_addToFrontB_BA(), ELEMENT_C, Result.NoException));
			printTest("A_addToFrontB_BA_testGetNeg1", testGet(A_addToFrontB_BA(), -1, null, Result.IndexOutOfBounds));
			printTest("A_addToFrontB_BA_testGet0", testGet(A_addToFrontB_BA(), 0, ELEMENT_B , Result.MatchingValue));
			printTest("A_addToFrontB_BA_testGet1", testGet(A_addToFrontB_BA(), 1, ELEMENT_A , Result.MatchingValue));
			printTest("A_addToFrontB_BA_testGet2", testGet(A_addToFrontB_BA(), 2, null, Result.IndexOutOfBounds));
			printTest("A_addToFrontB_BA_testIndexOfB", testIndexOf(A_addToFrontB_BA(), ELEMENT_B, 0));
			printTest("A_addToFrontB_BA_testIndexOfA", testIndexOf(A_addToFrontB_BA(), ELEMENT_A, 1));
			printTest("A_addToFrontB_BA_testIndexOfC", testIndexOf(A_addToFrontB_BA(), ELEMENT_C, -1));
			printTest("A_addToFrontB_BA_testRemoveNeg1", testRemoveIndex(A_addToFrontB_BA(), -1, null, Result.IndexOutOfBounds));
			printTest("A_addToFrontB_BA_testRemove0", testRemoveIndex(A_addToFrontB_BA(), 0, ELEMENT_B, Result.MatchingValue));
			printTest("A_addToFrontB_BA_testRemove1", testRemoveIndex(A_addToFrontB_BA(), 1, ELEMENT_A, Result.MatchingValue));
			printTest("A_addToFrontB_BA_testRemove2", testRemoveIndex(A_addToFrontB_BA(), 2, null, Result.IndexOutOfBounds));
			// Iterator
			printTest("A_addToFrontB_BA_testIterator", testIterator(A_addToFrontB_BA(), Result.NoException));
			printTest("A_addToFrontB_BA_testIteratorHasNext", testIteratorHasNext(A_addToFrontB_BA().iterator(), Result.True));
			printTest("A_addToFrontB_BA_testIteratorNext", testIteratorNext(A_addToFrontB_BA().iterator(), ELEMENT_B, Result.MatchingValue));
			printTest("A_addToFrontB_BA_testIteratorRemove", testIteratorRemove(A_addToFrontB_BA().iterator(), Result.IllegalState));
			printTest("A_addToFrontB_BA_testIteratorHasNextAfterNext", testIteratorHasNext(iteratorAfterNext(A_addToFrontB_BA(),1), Result.True));
			printTest("A_addToFrontB_BA_testIteratorNextAfterNext", testIteratorNext(iteratorAfterNext(A_addToFrontB_BA(),1), ELEMENT_A, Result.MatchingValue));
			printTest("A_addToFrontB_BA_testIteratorRemoveAfterNext", testIteratorRemove(iteratorAfterNext(A_addToFrontB_BA(),1), Result.NoException));
			printTest("A_addToFrontB_BA_testIteratorHasNextAfterNext2", testIteratorHasNext(iteratorAfterNext(A_addToFrontB_BA(),2), Result.False));
			printTest("A_addToFrontB_BA_testIteratorNextAfterNext2", testIteratorNext(iteratorAfterNext(A_addToFrontB_BA(),2), null, Result.NoSuchElement));
			printTest("A_addToFrontB_BA_testIteratorRemoveAfterNext2", testIteratorRemove(iteratorAfterNext(A_addToFrontB_BA(),2), Result.NoException));
			printTest("A_addToFrontB_BA_testIteratorNextAfterRemoveNext", testIteratorNext(iteratorAfterRemove(iteratorAfterNext(A_addToFrontB_BA(),1)),ELEMENT_A,  Result.MatchingValue));
			printTest("A_addToFrontB_BA_testIteratorNextAfterRemoveNext2", testIteratorNext(iteratorAfterRemove(iteratorAfterNext(A_addToFrontB_BA(),2)),null,  Result.NoSuchElement));
			printTest("A_addToFrontB_BA_testIteratorHasNextAfterRemoveNext",testIteratorHasNext(iteratorAfterRemove(iteratorAfterNext(A_addToFrontB_BA(),1)),Result.True));
			printTest("A_addToFrontB_BA_testIteratorHasNextAfterRemoveNext2",testIteratorHasNext(iteratorAfterRemove(iteratorAfterNext(A_addToFrontB_BA(),2)),Result.False));
			printTest("A_addToFrontB_BA_iteratorTestRealRemove",testIteratorRealRemove(A_addToFrontB_BA(), 1, "[1]", Result.MatchingValue));
			printTest("A_addToFrontB_BA_iteratorTestRealRemove2",testIteratorRealRemove(A_addToFrontB_BA(), 2, "[2]", Result.MatchingValue));
		} catch (Exception e) {
			System.out.printf("***UNABLE TO RUN/COMPLETE %s***\n", "test_A_addToFrontB_BA");
			e.printStackTrace();
		}
	}
	
	//////////////////////////////////////
	// SCENARIO: [A] -> addToRear(B) -> [A,B]
	//  XXX SCENARIO 10
	//////////////////////////////////////
	private ListADT<Integer> A_addToRearB_AB() {
		// cast to UnorderedListADT to make addToFront() available
		UnorderedListADT<Integer> list = (UnorderedListADT<Integer>) newList(); 
		list.addToRear(ELEMENT_A);
		list.addToRear(ELEMENT_B);
		return list;
	}
	private void test_A_addToRearB_AB(){
		try {
			// ListADT
			printTest("A_addToRearB_AB_testRemoveFirst", testRemoveFirst(A_addToRearB_AB(), ELEMENT_A, Result.MatchingValue));
			printTest("A_addToRearB_AB_testRemoveLast", testRemoveLast(A_addToRearB_AB(), ELEMENT_B, Result.MatchingValue));
			printTest("A_addToRearB_AB_testRemoveA", testRemoveElement(A_addToRearB_AB(), ELEMENT_A, Result.MatchingValue));
			printTest("A_addToRearB_AB_testRemoveB", testRemoveElement(A_addToRearB_AB(), ELEMENT_B, Result.MatchingValue));
			printTest("A_addToRearB_AB_testRemoveC", testRemoveElement(A_addToRearB_AB(), ELEMENT_C, Result.ElementNotFound));
			printTest("A_addToRearB_AB_testFirst", testFirst(A_addToRearB_AB(), ELEMENT_A, Result.MatchingValue));
			printTest("A_addToRearB_AB_testLast", testLast(A_addToRearB_AB(), ELEMENT_B, Result.MatchingValue));
			printTest("A_addToRearB_AB_testContainsA", testContains(A_addToRearB_AB(), ELEMENT_A, Result.True));
			printTest("A_addToRearB_AB_testContainsB", testContains(A_addToRearB_AB(), ELEMENT_B, Result.True));
			printTest("A_addToRearB_AB_testContainsC", testContains(A_addToRearB_AB(), ELEMENT_C, Result.False));
			printTest("A_addToRearB_AB_testIsEmpty", testIsEmpty(A_addToRearB_AB(), Result.False));
			printTest("A_addToRearB_AB_testSize", testSize(A_addToRearB_AB(), 2));
			printTest("A_addToRearB_AB_testToString", testToString(A_addToRearB_AB(), Result.ValidString));
			// UnorderedListADT
			printTest("A_addToRearB_AB_testAddToFrontA", testAddToFront(A_addToRearB_AB(), ELEMENT_C, Result.NoException));
			printTest("A_addToRearB_AB_testAddToRearA", testAddToRear(A_addToRearB_AB(), ELEMENT_C, Result.NoException));
			printTest(	"A_addToRearB_AB_testAddAfterAC", testAddAfter(A_addToRearB_AB(), ELEMENT_A, ELEMENT_C, Result.NoException));
			printTest(	"A_addToRearB_AB_testAddAfterBC", testAddAfter(A_addToRearB_AB(), ELEMENT_B, ELEMENT_C, Result.NoException));
			printTest(	"A_addToRearB_AB_testAddAfterDC", testAddAfter(A_addToRearB_AB(), ELEMENT_D, ELEMENT_C, Result.ElementNotFound));
			// IndexedListADT
			printTest("A_addToRearB_AB_testAddAtIndexNeg1C", testAddAtIndex(A_addToRearB_AB(), -1, null, Result.IndexOutOfBounds));
			printTest("A_addToRearB_AB_testAddAtIndex0C", testAddAtIndex(A_addToRearB_AB(), 0, ELEMENT_C, Result.NoException));
			printTest("A_addToRearB_AB_testAddAtIndex1C", testAddAtIndex(A_addToRearB_AB(), 1, ELEMENT_C, Result.NoException));
			printTest("A_addToRearB_AB_testAddAtIndex2C", testAddAtIndex(A_addToRearB_AB(), 2, ELEMENT_C, Result.NoException));
			printTest("A_addToRearB_AB_testAddAtIndex3C", testAddAtIndex(A_addToRearB_AB(), 3, null, Result.IndexOutOfBounds));
			printTest("A_addToRearB_AB_testSetNeg1C", testSet(A_addToRearB_AB(), -1, null, Result.IndexOutOfBounds));
			printTest("A_addToRearB_AB_testSet0C", testSet(A_addToRearB_AB(), 0, ELEMENT_C, Result.NoException));
			printTest("A_addToRearB_AB_testSet1C", testSet(A_addToRearB_AB(), 1, ELEMENT_C, Result.NoException));
			printTest("A_addToRearB_AB_testSetNeg2C", testSet(A_addToRearB_AB(), 2, null, Result.IndexOutOfBounds));
			printTest("A_addToRearB_AB_testAddC", testAdd(A_addToRearB_AB(), ELEMENT_C, Result.NoException));
			printTest("A_addToRearB_AB_testGetNeg1", testGet(A_addToRearB_AB(), -1, null, Result.IndexOutOfBounds));
			printTest("A_addToRearB_AB_testGet0", testGet(A_addToRearB_AB(), 0, ELEMENT_A , Result.MatchingValue));
			printTest("A_addToRearB_AB_testGet1", testGet(A_addToRearB_AB(), 1, ELEMENT_B , Result.MatchingValue));
			printTest("A_addToRearB_AB_testGet2", testGet(A_addToRearB_AB(), 2, null, Result.IndexOutOfBounds));
			printTest("A_addToRearB_AB_testIndexOfA", testIndexOf(A_addToRearB_AB(), ELEMENT_A, 0));
			printTest("A_addToRearB_AB_testIndexOfB", testIndexOf(A_addToRearB_AB(), ELEMENT_B, 1));
			printTest("A_addToRearB_AB_testIndexOfC", testIndexOf(A_addToRearB_AB(), ELEMENT_C, -1));
			printTest("A_addToRearB_AB_testRemoveNeg1", testRemoveIndex(A_addToRearB_AB(), -1, null, Result.IndexOutOfBounds));
			printTest("A_addToRearB_AB_testRemove0", testRemoveIndex(A_addToRearB_AB(), 0, ELEMENT_A, Result.MatchingValue));
			printTest("A_addToRearB_AB_testRemove1", testRemoveIndex(A_addToRearB_AB(), 1, ELEMENT_B, Result.MatchingValue));
			printTest("A_addToRearB_AB_testRemove2", testRemoveIndex(A_addToRearB_AB(), 2, null, Result.IndexOutOfBounds));
			// Iterator
			printTest("A_addToRearB_AB_testIterator", testIterator(A_addToRearB_AB(), Result.NoException));
			printTest("A_addToRearB_AB_testIteratorHasNext", testIteratorHasNext(A_addToRearB_AB().iterator(), Result.True));
			printTest("A_addToRearB_AB_testIteratorNext", testIteratorNext(A_addToRearB_AB().iterator(), ELEMENT_A, Result.MatchingValue));
			printTest("A_addToRearB_AB_testIteratorRemove", testIteratorRemove(A_addToRearB_AB().iterator(), Result.IllegalState));
			printTest("A_addToRearB_AB_testIteratorHasNextAfterNext", testIteratorHasNext(iteratorAfterNext(A_addToRearB_AB(),1), Result.True));
			printTest("A_addToRearB_AB_testIteratorNextAfterNext", testIteratorNext(iteratorAfterNext(A_addToRearB_AB(),1), ELEMENT_B, Result.MatchingValue));
			printTest("A_addToRearB_AB_testIteratorRemoveAfterNext", testIteratorRemove(iteratorAfterNext(A_addToRearB_AB(),1), Result.NoException));
			printTest("A_addToRearB_AB_testIteratorHasNextAfterNext2", testIteratorHasNext(iteratorAfterNext(A_addToRearB_AB(),2), Result.False));
			printTest("A_addToRearB_AB_testIteratorNextAfterNext2", testIteratorNext(iteratorAfterNext(A_addToRearB_AB(),2), null, Result.NoSuchElement));
			printTest("A_addToRearB_AB_testIteratorRemoveAfterNext2", testIteratorRemove(iteratorAfterNext(A_addToRearB_AB(),2), Result.NoException));
			printTest("A_addToRearB_AB_testIteratorNextAfterRemoveNext", testIteratorNext(iteratorAfterRemove(iteratorAfterNext(A_addToRearB_AB(),1)),ELEMENT_B,  Result.MatchingValue));
			printTest("A_addToRearB_AB_testIteratorNextAfterRemoveNext2", testIteratorNext(iteratorAfterRemove(iteratorAfterNext(A_addToRearB_AB(),2)),null,  Result.NoSuchElement));
			printTest("A_addToRearB_AB_testIteratorHasNextAfterRemoveNext",testIteratorHasNext(iteratorAfterRemove(iteratorAfterNext(A_addToRearB_AB(),1)),Result.True));
			printTest("A_addToRearB_AB_testIteratorHasNextAfterRemoveNext2",testIteratorHasNext(iteratorAfterRemove(iteratorAfterNext(A_addToRearB_AB(),2)),Result.False));
			printTest("A_addToRearB_AB_iteratorTestRealRemove",testIteratorRealRemove(A_addToRearB_AB(), 1, "[2]", Result.MatchingValue));
			printTest("A_addToRearB_AB_iteratorTestRealRemove2",testIteratorRealRemove(A_addToRearB_AB(), 2, "[1]", Result.MatchingValue));
			//ListIterator
			if(A_removeFirst_empty().getClass()==testDLL().getClass()){
			printTest("A_addToRearB_AB_testListIteratorUnIndexed", testListIteratorUnIndexed(A_addToRearB_AB(), Result.NoException));
			printTest("A_addToRearB_AB_testListIteratorIndexedNeg1", testListIteratorIndexed(A_addToRearB_AB(), -1, Result.IndexOutOfBounds));
			printTest("A_addToRearB_AB_testListIteratorIndexed0", testListIteratorIndexed(A_addToRearB_AB(), 0, Result.NoException));
			printTest("A_addToRearB_AB_testListIteratorIndexed1", testListIteratorIndexed(A_addToRearB_AB(), 1, Result.NoException));
			printTest("A_addToRearB_AB_testListIteratorIndexed2", testListIteratorIndexed(A_addToRearB_AB(), 2, Result.NoException));
			printTest("A_addToRearB_AB_testListIteratorIndexed3", testListIteratorIndexed(A_addToRearB_AB(), 3, Result.IndexOutOfBounds));
			
			printTest("A_addToRearB_AB_testListIteratorHasNext", testListIteratorHasNext(A_addToRearB_AB(),null, null, Result.True));
			printTest("A_addToRearB_AB_testListIteratorNext", testListIteratorNext(A_addToRearB_AB(),null, null, ELEMENT_A, Result.MatchingValue));
			printTest("A_addToRearB_AB_testListIteratorNextIndex", testListIteratorNextIndex(A_addToRearB_AB(),null, null, 0, Result.MatchingValue));
			printTest("A_addToRearB_AB_testListIteratorHasPrevious", testListIteratorHasPrevious(A_addToRearB_AB(),null, null, Result.False));
			printTest("A_addToRearB_AB_testListIteratorPrevious", testListIteratorPrevious(A_addToRearB_AB(),null, null, null, Result.NoSuchElement));
			printTest("A_addToRearB_AB_testListIteratorPreviousIndex", testListIteratorPreviousIndex(A_addToRearB_AB(),null, null, -1, Result.MatchingValue));
			printTest("A_addToRearB_AB_testListIteratorRemove", testListIteratorRemove(A_addToRearB_AB(),null, null, Result.IllegalState));
			printTest("A_addToRearB_AB_testListIteratorRealRemove", testListIteratorRealRemoveAfterNext(A_addToRearB_AB(), null, 0, "[1, 2]", Result.MatchingValue));
			printTest("A_addToRearB_AB_testListIteratorSet", testListIteratorSet(A_addToRearB_AB(), null,null, ELEMENT_D, Result.IllegalState));
			printTest("A_addToRearB_AB_testListIteratorRealSet", testListIteratorRealSetAfterNext(A_addToRearB_AB(), null, 0, ELEMENT_D, "[1, 2]", Result.MatchingValue));
			printTest("A_addToRearB_AB_testListIteratorAdd", testListIteratorAdd(A_addToRearB_AB(),null, null, ELEMENT_D, Result.NoException));
			printTest("A_addToRearB_AB_testListIteratorRealAdd", testListIteratorRealAddAfterNext(A_addToRearB_AB(), null, 0, ELEMENT_D, "[4, 1, 2]", Result.MatchingValue));
			
			printTest("A_addToRearB_AB_testListIteratorHasNextAfterNext", testListIteratorHasNext(null,getListIteratorAfterCall(A_addToRearB_AB(), "next", null, null), null, Result.True));
			printTest("A_addToRearB_AB_testListIteratorNextAfterNext", testListIteratorNext(null,getListIteratorAfterCall(A_addToRearB_AB(), "next", null, null), null, ELEMENT_B, Result.MatchingValue));
			printTest("A_addToRearB_AB_testListIteratorNextIndexAfterNext", testListIteratorNextIndex(null,getListIteratorAfterCall(A_addToRearB_AB(), "next", null, null), null, 1, Result.MatchingValue));
			printTest("A_addToRearB_AB_testListIteratorHasPreviousAfterNext", testListIteratorHasPrevious(null,getListIteratorAfterCall(A_addToRearB_AB(), "next", null, null), null, Result.True));
			printTest("A_addToRearB_AB_testListIteratorPreviousAfterNext", testListIteratorPrevious(null,getListIteratorAfterCall(A_addToRearB_AB(), "next", null, null), null, ELEMENT_A, Result.MatchingValue));
			printTest("A_addToRearB_AB_testListIteratorPreviousIndexAfterNext", testListIteratorPreviousIndex(null,getListIteratorAfterCall(A_addToRearB_AB(), "next", null, null), null, 0, Result.MatchingValue));
			printTest("A_addToRearB_AB_testListIteratorRemoveAfterNext", testListIteratorRemove(null,getListIteratorAfterCall(A_addToRearB_AB(), "next", null, null), null, Result.NoException));
			printTest("A_addToRearB_AB_testListIteratorRealRemoveAfterNext", testListIteratorRealRemoveAfterNext(A_addToRearB_AB(),null, 1, "[2]", Result.MatchingValue));
			printTest("A_addToRearB_AB_testListIteratorSetAfterNext", testListIteratorSet(null, getListIteratorAfterCall(A_addToRearB_AB(), "next", null, null),null, ELEMENT_D, Result.NoException));
			printTest("A_addToRearB_AB_testListIteratorRealSetAfterNext", testListIteratorRealSetAfterNext(A_addToRearB_AB(), null, 1, ELEMENT_D, "[4, 2]", Result.MatchingValue));
			printTest("A_addToRearB_AB_testListIteratorAddAfterNext", testListIteratorAdd(null,getListIteratorAfterCall(A_addToRearB_AB(), "next", null, null), null, ELEMENT_D, Result.NoException));
			printTest("A_addToRearB_AB_testListIteratorRealAddAfterNext", testListIteratorRealAddAfterNext(A_addToRearB_AB(), null, 1, ELEMENT_D, "[1, 4, 2]", Result.MatchingValue));
			
			printTest("A_addToRearB_AB_testListIteratorHasNextAfter2Next", testListIteratorHasNext(null,getListIteratorAfterCallItrItr(getListIteratorAfterCall(A_addToRearB_AB(), "next", null, null), "next", null, null), null, Result.False));
			printTest("A_addToRearB_AB_testListIteratorNextAfter2Next", testListIteratorNext(null,getListIteratorAfterCallItrItr(getListIteratorAfterCall(A_addToRearB_AB(), "next", null, null), "next", null, null), null, null, Result.NoSuchElement));
			printTest("A_addToRearB_AB_testListIteratorNextIndexAfter2Next", testListIteratorNextIndex(null,getListIteratorAfterCallItrItr(getListIteratorAfterCall(A_addToRearB_AB(), "next", null, null), "next", null, null), null, 2, Result.MatchingValue));
			printTest("A_addToRearB_AB_testListIteratorHasPreviousAfter2Next", testListIteratorHasPrevious(null,getListIteratorAfterCallItrItr(getListIteratorAfterCall(A_addToRearB_AB(), "next", null, null), "next", null, null), null, Result.True));
			printTest("A_addToRearB_AB_testListIteratorPreviousAfter2Next", testListIteratorPrevious(null,getListIteratorAfterCallItrItr(getListIteratorAfterCall(A_addToRearB_AB(), "next", null, null), "next", null, null), null, ELEMENT_B, Result.MatchingValue));
			printTest("A_addToRearB_AB_testListIteratorPreviousIndexAfter2Next", testListIteratorPreviousIndex(null,getListIteratorAfterCallItrItr(getListIteratorAfterCall(A_addToRearB_AB(), "next", null, null), "next", null, null), null, 1, Result.MatchingValue));
			printTest("A_addToRearB_AB_testListIteratorRemoveAfter2Next", testListIteratorRemove(null,getListIteratorAfterCallItrItr(getListIteratorAfterCall(A_addToRearB_AB(), "next", null, null), "next", null, null), null, Result.NoException));
			printTest("A_addToRearB_AB_testListIteratorRealRemoveAfter2Next", testListIteratorRealRemoveAfterNext(A_addToRearB_AB(),null, 2, "[1]", Result.MatchingValue));
			printTest("A_addToRearB_AB_testListIteratorSetAfter2Next", testListIteratorSet(null, getListIteratorAfterCallItrItr(getListIteratorAfterCall(A_addToRearB_AB(), "next", null, null), "next", null, null),null, ELEMENT_D, Result.NoException));
			printTest("A_addToRearB_AB_testListIteratorRealSetAfter2Next", testListIteratorRealSetAfterNext(A_addToRearB_AB(), null, 2, ELEMENT_D, "[1, 4]", Result.MatchingValue));
			printTest("A_addToRearB_AB_testListIteratorAddAfter2Next", testListIteratorAdd(null,getListIteratorAfterCallItrItr(getListIteratorAfterCall(A_addToRearB_AB(), "next", null, null), "next", null, null), null, ELEMENT_D, Result.NoException));
			printTest("A_addToRearB_AB_testListIteratorRealAddAfter2Next", testListIteratorRealAddAfterNext(A_addToRearB_AB(), null, 2, ELEMENT_D, "[1, 2, 4]", Result.MatchingValue));
			
			printTest("A_addToRearB_AB_testListIterator0HasNext", testListIteratorHasNext(A_addToRearB_AB(),null, 0, Result.True));
			printTest("A_addToRearB_AB_testListIterator0Next", testListIteratorNext(A_addToRearB_AB(),null, 0, ELEMENT_A, Result.MatchingValue));
			printTest("A_addToRearB_AB_testListIterator0NextIndex", testListIteratorNextIndex(A_addToRearB_AB(),null, 0, 0, Result.MatchingValue));
			printTest("A_addToRearB_AB_testListIterator0HasPrevious", testListIteratorHasPrevious(A_addToRearB_AB(),null, 0, Result.False));
			printTest("A_addToRearB_AB_testListIterator0Previous", testListIteratorPrevious(A_addToRearB_AB(),null, 0, null, Result.NoSuchElement));
			printTest("A_addToRearB_AB_testListIterator0PreviousIndex", testListIteratorPreviousIndex(A_addToRearB_AB(),null, 0, -1, Result.MatchingValue));
			printTest("A_addToRearB_AB_testListIterator0Remove", testListIteratorRemove(A_addToRearB_AB(),null, 0, Result.IllegalState));
			printTest("A_addToRearB_AB_testListIterator0RealRemove", testListIteratorRealRemoveAfterNext(A_addToRearB_AB(), 0, 0, "[1, 2]", Result.MatchingValue));
			printTest("A_addToRearB_AB_testListIterator0Set", testListIteratorSet(A_addToRearB_AB(), null,0, ELEMENT_D, Result.IllegalState));
			printTest("A_addToRearB_AB_testListIterator0RealSet", testListIteratorRealSetAfterNext(A_addToRearB_AB(), 0, 0, ELEMENT_D, "[1, 2]", Result.MatchingValue));
			printTest("A_addToRearB_AB_testListIterator0Add", testListIteratorAdd(A_addToRearB_AB(),null, 0, ELEMENT_D, Result.NoException));
			printTest("A_addToRearB_AB_testListIterator0RealAdd", testListIteratorRealAddAfterNext(A_addToRearB_AB(), 0, 0, ELEMENT_D, "[4, 1, 2]", Result.MatchingValue));
			
			printTest("A_addToRearB_AB_testListIterator0HasNextAfterNext", testListIteratorHasNext(null,getListIteratorAfterCall(A_addToRearB_AB(), "next", 0, null), 0, Result.True));
			printTest("A_addToRearB_AB_testListIterator0NextAfterNext", testListIteratorNext(null,getListIteratorAfterCall(A_addToRearB_AB(), "next", 0, null), 0, ELEMENT_B, Result.MatchingValue));
			printTest("A_addToRearB_AB_testListIterator0NextIndexAfterNext", testListIteratorNextIndex(null,getListIteratorAfterCall(A_addToRearB_AB(), "next", 0, null), 0, 1, Result.MatchingValue));
			printTest("A_addToRearB_AB_testListIterator0HasPreviousAfterNext", testListIteratorHasPrevious(null,getListIteratorAfterCall(A_addToRearB_AB(), "next", 0, null),0, Result.True));
			printTest("A_addToRearB_AB_testListIterator0PreviousAfterNext", testListIteratorPrevious(null,getListIteratorAfterCall(A_addToRearB_AB(), "next", 0, null), 0, ELEMENT_A, Result.MatchingValue));
			printTest("A_addToRearB_AB_testListIterator0PreviousIndexAfterNext", testListIteratorPreviousIndex(null,getListIteratorAfterCall(A_addToRearB_AB(), "next", 0, null), 0, 0, Result.MatchingValue));
			printTest("A_addToRearB_AB_testListIterator0RemoveAfterNext", testListIteratorRemove(null,getListIteratorAfterCall(A_addToRearB_AB(), "next", 0, null), 0, Result.NoException));
			printTest("A_addToRearB_AB_testListIterator0RealRemoveAfterNext", testListIteratorRealRemoveAfterNext(A_addToRearB_AB(),0, 1, "[2]", Result.MatchingValue));
			printTest("A_addToRearB_AB_testListIterator0SetAfterNext", testListIteratorSet(null, getListIteratorAfterCall(A_addToRearB_AB(), "next", 0, null),0, ELEMENT_D, Result.NoException));
			printTest("A_addToRearB_AB_testListIterator0RealSetAfterNext", testListIteratorRealSetAfterNext(A_addToRearB_AB(), 0, 1, ELEMENT_D, "[4, 2]", Result.MatchingValue));
			printTest("A_addToRearB_AB_testListIterator0AddAfterNext", testListIteratorAdd(null,getListIteratorAfterCall(A_addToRearB_AB(), "next", 0, null), 0, ELEMENT_D, Result.NoException));
			printTest("A_addToRearB_AB_testListIterator0RealAddAfterNext", testListIteratorRealAddAfterNext(A_addToRearB_AB(), 0, 1, ELEMENT_D, "[1, 4, 2]", Result.MatchingValue));
			
			printTest("A_addToRearB_AB_testListIterator0HasNextAfter2Next", testListIteratorHasNext(null,getListIteratorAfterCallItrItr(getListIteratorAfterCall(A_addToRearB_AB(), "next", 0, null), "next", null, null), 0, Result.False));
			printTest("A_addToRearB_AB_testListIterator0NextAfter2Next", testListIteratorNext(null,getListIteratorAfterCallItrItr(getListIteratorAfterCall(A_addToRearB_AB(), "next", 0, null), "next", null, null), 0, null, Result.NoSuchElement));
			printTest("A_addToRearB_AB_testListIterator0NextIndexAfter2Next", testListIteratorNextIndex(null,getListIteratorAfterCallItrItr(getListIteratorAfterCall(A_addToRearB_AB(), "next", 0, null), "next", null, null), 0, 2, Result.MatchingValue));
			printTest("A_addToRearB_AB_testListIterator0HasPreviousAfter2Next", testListIteratorHasPrevious(null,getListIteratorAfterCallItrItr(getListIteratorAfterCall(A_addToRearB_AB(), "next", 0, null), "next", null, null), 0, Result.True));
			printTest("A_addToRearB_AB_testListIterator0PreviousAfter2Next", testListIteratorPrevious(null,getListIteratorAfterCallItrItr(getListIteratorAfterCall(A_addToRearB_AB(), "next", 0, null), "next", null, null), 0, ELEMENT_B, Result.MatchingValue));
			printTest("A_addToRearB_AB_testListIterator0PreviousIndexAfter2Next", testListIteratorPreviousIndex(null,getListIteratorAfterCallItrItr(getListIteratorAfterCall(A_addToRearB_AB(), "next", 0, null), "next", null, null), 0, 1, Result.MatchingValue));
			printTest("A_addToRearB_AB_testListIterator0RemoveAfter2Next", testListIteratorRemove(null,getListIteratorAfterCallItrItr(getListIteratorAfterCall(A_addToRearB_AB(), "next", 0, null), "next", null, null), 0, Result.NoException));
			printTest("A_addToRearB_AB_testListIterator0RealRemoveAfter2Next", testListIteratorRealRemoveAfterNext(A_addToRearB_AB(),0, 2, "[1]", Result.MatchingValue));
			printTest("A_addToRearB_AB_testListIterator0SetAfter2Next", testListIteratorSet(null, getListIteratorAfterCallItrItr(getListIteratorAfterCall(A_addToRearB_AB(), "next", 0, null), "next", null, null),0, ELEMENT_D, Result.NoException));
			printTest("A_addToRearB_AB_testListIterator0RealSetAfter2Next", testListIteratorRealSetAfterNext(A_addToRearB_AB(), 0,2, ELEMENT_D, "[1, 4]", Result.MatchingValue));
			printTest("A_addToRearB_AB_testListIterator0AddAfter2Next", testListIteratorAdd(null,getListIteratorAfterCallItrItr(getListIteratorAfterCall(A_addToRearB_AB(), "next", 0, null), "next", null, null), 0, ELEMENT_D, Result.NoException));
			printTest("A_addToRearB_AB_testListIterator0RealAddAfter2Next", testListIteratorRealAddAfterNext(A_addToRearB_AB(), 0, 2, ELEMENT_D, "[1, 2, 4]", Result.MatchingValue));
	
			printTest("A_addToRearB_AB_testListIterator1HasNext", testListIteratorHasNext( A_addToRearB_AB(),null, 1, Result.True));
			printTest("A_addToRearB_AB_testListIterator1Next", testListIteratorNext( A_addToRearB_AB(),null, 1, ELEMENT_B, Result.MatchingValue));
			printTest("A_addToRearB_AB_testListIterator1NextIndex", testListIteratorNextIndex( A_addToRearB_AB(),null, 1, 1, Result.MatchingValue));
			printTest("A_addToRearB_AB_testListIterator1HasPrevious", testListIteratorHasPrevious( A_addToRearB_AB(),null, 1, Result.True));
			printTest("A_addToRearB_AB_testListIterator1Previous", testListIteratorPrevious( A_addToRearB_AB(),null, 1, ELEMENT_A, Result.MatchingValue));
			printTest("A_addToRearB_AB_testListIterator1PreviousIndex", testListIteratorPreviousIndex( A_addToRearB_AB(),null, 1, 0, Result.MatchingValue));
			printTest("A_addToRearB_AB_testListIterator1Remove", testListIteratorRemove( A_addToRearB_AB(),null, 1, Result.IllegalState));
			printTest("A_addToRearB_AB_testListIterator1RealRemove", testListIteratorRealRemoveAfterNext(A_addToRearB_AB(),0, 1, "[2]", Result.MatchingValue));
			printTest("A_addToRearB_AB_testListIterator1Set", testListIteratorSet( A_addToRearB_AB(),null,1, ELEMENT_D, Result.IllegalState));
			printTest("A_addToRearB_AB_testListIterator1RealSet", testListIteratorRealSetAfterNext(A_addToRearB_AB(), 0, 1, ELEMENT_D, "[4, 2]", Result.MatchingValue));
			printTest("A_addToRearB_AB_testListIterator1Add", testListIteratorAdd( A_addToRearB_AB(),null, 1, ELEMENT_D, Result.NoException));
			printTest("A_addToRearB_AB_testListIterator1RealAdd", testListIteratorRealAddAfterNext(A_addToRearB_AB(), 0, 1, ELEMENT_D, "[1, 4, 2]", Result.MatchingValue));
			
			printTest("A_addToRearB_AB_testListIterator1HasNextAfterNext", testListIteratorHasNext(null,getListIteratorAfterCall(A_addToRearB_AB(), "next", 1, null), 1, Result.False));
			printTest("A_addToRearB_AB_testListIterator1NextAfterNext", testListIteratorNext(null,getListIteratorAfterCall(A_addToRearB_AB(), "next", 1, null), 1, null, Result.NoSuchElement));
			printTest("A_addToRearB_AB_testListIterator1NextIndexAfterNext", testListIteratorNextIndex(null,getListIteratorAfterCall(A_addToRearB_AB(), "next", 1, null), 1, 2, Result.MatchingValue));
			printTest("A_addToRearB_AB_testListIterator1HasPreviousAfterNext", testListIteratorHasPrevious(null,getListIteratorAfterCall(A_addToRearB_AB(), "next", 1, null), 1, Result.True));
			printTest("A_addToRearB_AB_testListIterator1PreviousAfterNext", testListIteratorPrevious(null,getListIteratorAfterCall(A_addToRearB_AB(), "next", 1, null), 1, ELEMENT_B, Result.MatchingValue));
			printTest("A_addToRearB_AB_testListIterator1PreviousIndexAfterNext", testListIteratorPreviousIndex(null,getListIteratorAfterCall(A_addToRearB_AB(), "next", 1, null), 1, 1, Result.MatchingValue));
			printTest("A_addToRearB_AB_testListIterator1RemoveAfterNext", testListIteratorRemove(null,getListIteratorAfterCall(A_addToRearB_AB(), "next", 1, null), 1, Result.NoException));
			printTest("A_addToRearB_AB_testListIterator1RealRemoveAfterNext", testListIteratorRealRemoveAfterNext(A_addToRearB_AB(),1, 1, "[1]", Result.MatchingValue));
			printTest("A_addToRearB_AB_testListIterator1SetAfterNext", testListIteratorSet(null, getListIteratorAfterCall(A_addToRearB_AB(), "next", 1, null),1, ELEMENT_D, Result.NoException));
			printTest("A_addToRearB_AB_testListIterator1RealSetAfterNext", testListIteratorRealSetAfterNext(A_addToRearB_AB(), 1, 1, ELEMENT_D, "[1, 4]", Result.MatchingValue));
			printTest("A_addToRearB_AB_testListIterator1AddAfterNext", testListIteratorAdd(null,getListIteratorAfterCall(A_addToRearB_AB(), "next", 1, null), null, ELEMENT_D, Result.NoException));
			printTest("A_addToRearB_AB_testListIterator1RealAddAfterNext", testListIteratorRealAddAfterNext(A_addToRearB_AB(), 1, 1, ELEMENT_D, "[1, 2, 4]", Result.MatchingValue));
			
			printTest("A_addToRearB_AB_testListIterator1HasNextAfterPrev", testListIteratorHasNext(null,getListIteratorAfterCall(A_addToRearB_AB(), "previous", 1, null), null, Result.True));
			printTest("A_addToRearB_AB_testListIterator1NextAfterPrev", testListIteratorNext(null,getListIteratorAfterCall(A_addToRearB_AB(), "previous", 1, null), null, ELEMENT_A, Result.MatchingValue));
			printTest("A_addToRearB_AB_testListIterator1NextIndexAfterPrev", testListIteratorNextIndex(null,getListIteratorAfterCall(A_addToRearB_AB(), "previous", 1, null), null, 0, Result.MatchingValue));
			printTest("A_addToRearB_AB_testListIterator1HasPreviousAfterPrev", testListIteratorHasPrevious(null,getListIteratorAfterCall(A_addToRearB_AB(), "previous", 1, null), null, Result.False));
			printTest("A_addToRearB_AB_testListIterator1PreviousAfterPrev", testListIteratorPrevious(null,getListIteratorAfterCall(A_addToRearB_AB(), "previous", 1, null), null, null, Result.NoSuchElement));
			printTest("A_addToRearB_AB_testListIterator1PreviousIndexAfterPrev", testListIteratorPreviousIndex(null,getListIteratorAfterCall(A_addToRearB_AB(), "previous", 1, null), null, -1, Result.MatchingValue));
			printTest("A_addToRearB_AB_testListIterator1RemoveAfterPrev", testListIteratorRemove(null,getListIteratorAfterCall(A_addToRearB_AB(), "previous", 1, null), null, Result.NoException));
			printTest("A_addToRearB_AB_testListIterator1RealRemoveAfterPrev", testListIteratorRealRemoveAfterPrev(A_addToRearB_AB(), 1, 1, "[2]", Result.MatchingValue));
			printTest("A_addToRearB_AB_testListIterator1SetAfterPrev", testListIteratorSet(null, getListIteratorAfterCall(A_addToRearB_AB(), "previous", 1, null),null, ELEMENT_D, Result.NoException));
			printTest("A_addToRearB_AB_testListIterator1RealSetAfterPrev", testListIteratorRealSetAfterPrev(A_addToRearB_AB(), 1, 1, ELEMENT_D, "[4, 2]", Result.MatchingValue));
			printTest("A_addToRearB_AB_testListIterator1AddAfterPrev", testListIteratorAdd(null,getListIteratorAfterCall(A_addToRearB_AB(), "previous", 1, null), null, ELEMENT_D, Result.NoException));
			printTest("A_addToRearB_AB_testListIterator1RealAddAfterPrev", testListIteratorRealAddAfterPrev(A_addToRearB_AB(), 1, 1, ELEMENT_D, "[4, 1, 2]", Result.MatchingValue));
			
			printTest("A_addToRearB_AB_testListIterator1HasNextAfterPrevNext", testListIteratorHasNext(null,getListIteratorAfterCallItrItr(getListIteratorAfterCall(A_addToRearB_AB(), "previous", 1, null), "next", null, null), null, Result.True));
			printTest("A_addToRearB_AB_testListIterator1NextAfterPrevNext", testListIteratorNext(null,getListIteratorAfterCallItrItr(getListIteratorAfterCall(A_addToRearB_AB(), "previous", 1, null), "next", null, null), null, ELEMENT_B, Result.MatchingValue));
			printTest("A_addToRearB_AB_testListIterator1NextIndexAfterPrevNext", testListIteratorNextIndex(null,getListIteratorAfterCallItrItr(getListIteratorAfterCall(A_addToRearB_AB(), "previous", 1, null), "next", null, null), null, 1, Result.MatchingValue));
			printTest("A_addToRearB_AB_testListIterator1HasPreviousAfterPrevNext", testListIteratorHasPrevious(null,getListIteratorAfterCallItrItr(getListIteratorAfterCall(A_addToRearB_AB(), "previous", 1, null), "next", null, null), null, Result.True));
			printTest("A_addToRearB_AB_testListIterator1PreviousAfterPrevNext", testListIteratorPrevious(null,getListIteratorAfterCallItrItr(getListIteratorAfterCall(A_addToRearB_AB(), "previous", 1, null), "next", null, null), null, ELEMENT_A, Result.MatchingValue));
			printTest("A_addToRearB_AB_testListIterator1PreviousIndexAfterPrevNext", testListIteratorPreviousIndex(null,getListIteratorAfterCallItrItr(getListIteratorAfterCall(A_addToRearB_AB(), "previous", 1, null), "next", null, null), null, 0, Result.MatchingValue));
			printTest("A_addToRearB_AB_testListIterator1RemoveAfterPrevNext", testListIteratorRemove(null,getListIteratorAfterCallItrItr(getListIteratorAfterCall(A_addToRearB_AB(), "previous", 1, null), "next", null, null), null, Result.NoException));
			printTest("A_addToRearB_AB_testListIterator1RealRemoveAfterPrevNext", testListIteratorRealRemoveAfterNext(A_addToRearB_AB(),null, 1, "[2]", Result.MatchingValue));
			printTest("A_addToRearB_AB_testListIterator1SetAfterPrevNext", testListIteratorSet(null, getListIteratorAfterCallItrItr(getListIteratorAfterCall(A_addToRearB_AB(), "previous", 1, null), "next", null, null),null, ELEMENT_D, Result.NoException));
			printTest("A_addToRearB_AB_testListIterator1RealSetAfterPrevNext", testListIteratorRealSetAfterNext(A_addToRearB_AB(), null, 1, ELEMENT_D, "[4, 2]", Result.MatchingValue));
			printTest("A_addToRearB_AB_testListIterator1AddAfterPrevNext", testListIteratorAdd(null,getListIteratorAfterCallItrItr(getListIteratorAfterCall(A_addToRearB_AB(), "previous", 1, null), "next", null, null), null, ELEMENT_D, Result.NoException));
			printTest("A_addToRearB_AB_testListIterator1RealAddAfterPrevNext", testListIteratorRealAddAfterNext(A_addToRearB_AB(), null, 1, ELEMENT_D, "[1, 4, 2]", Result.MatchingValue));
		
			printTest("A_addToRearB_AB_testListIterator1HasNextAfterPrev2Next", testListIteratorHasNext(null,getListIteratorAfterCallItrItr(getListIteratorAfterCallItrItr(getListIteratorAfterCall(A_addToRearB_AB(), "previous", 1, null), "next", null, null), "next", null, null), 1, Result.False));
			printTest("A_addToRearB_AB_testListIterator1NextAfterPrev2Next", testListIteratorNext(null,getListIteratorAfterCallItrItr(getListIteratorAfterCallItrItr(getListIteratorAfterCall(A_addToRearB_AB(), "previous", 1, null), "next", null, null), "next", null, null), 1, null, Result.NoSuchElement));
			printTest("A_addToRearB_AB_testListIterator1NextIndexAfterPrev2Next", testListIteratorNextIndex(null,getListIteratorAfterCallItrItr(getListIteratorAfterCallItrItr(getListIteratorAfterCall(A_addToRearB_AB(), "previous", 1, null), "next", null, null), "next", null, null), 1, 2, Result.MatchingValue));
			printTest("A_addToRearB_AB_testListIterator1HasPreviousAfterPrev2Next", testListIteratorHasPrevious(null,getListIteratorAfterCallItrItr(getListIteratorAfterCallItrItr(getListIteratorAfterCall(A_addToRearB_AB(), "previous", 1, null), "next", null, null), "next", null, null), 1, Result.True));
			printTest("A_addToRearB_AB_testListIterator1PreviousAfterPrev2Next", testListIteratorPrevious(null,getListIteratorAfterCallItrItr(getListIteratorAfterCallItrItr(getListIteratorAfterCall(A_addToRearB_AB(), "previous", 1, null), "next", null, null), "next", null, null), 1, ELEMENT_B, Result.MatchingValue));
			printTest("A_addToRearB_AB_testListIterator1PreviousIndexAfterPrev2Next", testListIteratorPreviousIndex(null,getListIteratorAfterCallItrItr(getListIteratorAfterCallItrItr(getListIteratorAfterCall(A_addToRearB_AB(), "previous", 1, null), "next", null, null), "next", null, null), 1, 1, Result.MatchingValue));
			printTest("A_addToRearB_AB_testListIterator1RemoveAfterPrev2Next", testListIteratorRemove(null,getListIteratorAfterCallItrItr(getListIteratorAfterCallItrItr(getListIteratorAfterCall(A_addToRearB_AB(), "previous", 1, null), "next", null, null), "next", null, null), 1, Result.NoException));
			printTest("A_addToRearB_AB_testListIterator1RealRemoveAfterPrev2Next", testListIteratorRealRemoveAfterNext(A_addToRearB_AB(),1, 1, "[1]", Result.MatchingValue));
			printTest("A_addToRearB_AB_testListIterator1SetAfterPrev2Next", testListIteratorSet(null, getListIteratorAfterCallItrItr(getListIteratorAfterCallItrItr(getListIteratorAfterCall(A_addToRearB_AB(), "previous", 1, null), "next", null, null), "next", null, null),1, ELEMENT_D, Result.NoException));
			printTest("A_addToRearB_AB_testListIterator1RealSetAfterPrev2Next", testListIteratorRealSetAfterNext(A_addToRearB_AB(), 1, 1, ELEMENT_D, "[1, 4]", Result.MatchingValue));
			printTest("A_addToRearB_AB_testListIterator1AddAfterPrev2Next", testListIteratorAdd(null,getListIteratorAfterCallItrItr(getListIteratorAfterCallItrItr(getListIteratorAfterCall(A_addToRearB_AB(), "previous", 1, null), "next", null, null), "next", null, null), null, ELEMENT_D, Result.NoException));
			printTest("A_addToRearB_AB_testListIterator1RealAddAfterPrev2Next", testListIteratorRealAddAfterNext(A_addToRearB_AB(), 1, 1, ELEMENT_D, "[1, 2, 4]", Result.MatchingValue));
			}
		} catch (Exception e) {
			System.out.printf("***UNABLE TO RUN/COMPLETE %s***\n", "test_A_addToRearB_AB");
			e.printStackTrace();
		}
	}
	
	//////////////////////////////////////
	// SCENARIO: [A] -> addAfter(B,A) -> [A,B]
	//  XXX SCENARIO 11
	//////////////////////////////////////
	private ListADT<Integer> A_addAfterB_AB() {
		// cast to UnorderedListADT to make addToFront() available
		UnorderedListADT<Integer> list = (UnorderedListADT<Integer>) newList(); 
		list.addToRear(ELEMENT_A);
		list.addAfter(ELEMENT_B, ELEMENT_A);
		return list;
	}
	private void test_A_addAfterBA_AB(){
		try {
			// ListADT
			printTest("A_addAfterB_AB_testRemoveFirst", testRemoveFirst(A_addAfterB_AB(), ELEMENT_A, Result.MatchingValue));
			printTest("A_addAfterB_AB_testRemoveLast", testRemoveLast(A_addAfterB_AB(), ELEMENT_B, Result.MatchingValue));
			printTest("A_addAfterB_AB_testRemoveA", testRemoveElement(A_addAfterB_AB(), ELEMENT_A, Result.MatchingValue));
			printTest("A_addAfterB_AB_testRemoveB", testRemoveElement(A_addAfterB_AB(), ELEMENT_B, Result.MatchingValue));
			printTest("A_addAfterB_AB_testRemoveC", testRemoveElement(A_addAfterB_AB(), ELEMENT_C, Result.ElementNotFound));
			printTest("A_addAfterB_AB_testFirst", testFirst(A_addAfterB_AB(), ELEMENT_A, Result.MatchingValue));
			printTest("A_addAfterB_AB_testLast", testLast(A_addAfterB_AB(), ELEMENT_B, Result.MatchingValue));
			printTest("A_addAfterB_AB_testContainsA", testContains(A_addAfterB_AB(), ELEMENT_A, Result.True));
			printTest("A_addAfterB_AB_testContainsB", testContains(A_addAfterB_AB(), ELEMENT_B, Result.True));
			printTest("A_addAfterB_AB_testContainsC", testContains(A_addAfterB_AB(), ELEMENT_C, Result.False));
			printTest("A_addAfterB_AB_testIsEmpty", testIsEmpty(A_addAfterB_AB(), Result.False));
			printTest("A_addAfterB_AB_testSize", testSize(A_addAfterB_AB(), 2));
			printTest("A_addAfterB_AB_testToString", testToString(A_addAfterB_AB(), Result.ValidString));
			// UnorderedListADT
			printTest("A_addAfterB_AB_testAddToFrontA", testAddToFront(A_addAfterB_AB(), ELEMENT_C, Result.NoException));
			printTest("A_addAfterB_AB_testAddToRearA", testAddToRear(A_addAfterB_AB(), ELEMENT_C, Result.NoException));
			printTest(	"A_addAfterB_AB_testAddAfterAC", testAddAfter(A_addAfterB_AB(), ELEMENT_A, ELEMENT_C, Result.NoException));
			printTest(	"A_addAfterB_AB_testAddAfterBC", testAddAfter(A_addAfterB_AB(), ELEMENT_B, ELEMENT_C, Result.NoException));
			printTest(	"A_addAfterB_AB_testAddAfterDC", testAddAfter(A_addAfterB_AB(), ELEMENT_D, ELEMENT_C, Result.ElementNotFound));
			// IndexedListADT
			printTest("A_addAfterB_AB_testAddAtIndexNeg1", testAddAtIndex(A_addAfterB_AB(), -1, null, Result.IndexOutOfBounds));
			printTest("A_addAfterB_AB_testAddAtIndex0", testAddAtIndex(A_addAfterB_AB(), 0, ELEMENT_C, Result.NoException));
			printTest("A_addAfterB_AB_testAddAtIndex1", testAddAtIndex(A_addAfterB_AB(), 1, ELEMENT_C, Result.NoException));
			printTest("A_addAfterB_AB_testAddAtIndex2", testAddAtIndex(A_addAfterB_AB(), 2, ELEMENT_C, Result.NoException));
			printTest("A_addAfterB_AB_testAddAtIndex3", testAddAtIndex(A_addAfterB_AB(), 3, null, Result.IndexOutOfBounds));
			printTest("A_addAfterB_AB_testSetNeg1C", testSet(A_addAfterB_AB(), -1, null, Result.IndexOutOfBounds));
			printTest("A_addAfterB_AB_testSet0C", testSet(A_addAfterB_AB(), 0, ELEMENT_C, Result.NoException));
			printTest("A_addAfterB_AB_testSet1C", testSet(A_addAfterB_AB(), 1, ELEMENT_C, Result.NoException));
			printTest("A_addAfterB_AB_testSet2C", testSet(A_addAfterB_AB(), 2, null, Result.IndexOutOfBounds));
			printTest("A_addAfterB_AB_testAddA", testAdd(A_addAfterB_AB(), ELEMENT_C, Result.NoException));
			printTest("A_addAfterB_AB_testGetNeg1", testGet(A_addAfterB_AB(), -1, null, Result.IndexOutOfBounds));
			printTest("A_addAfterB_AB_testGet0", testGet(A_addAfterB_AB(), 0, ELEMENT_A , Result.MatchingValue));
			printTest("A_addAfterB_AB_testGet1", testGet(A_addAfterB_AB(), 1, ELEMENT_B , Result.MatchingValue));
			printTest("A_addAfterB_AB_testGet2", testGet(A_addAfterB_AB(), 2, null, Result.IndexOutOfBounds));
			printTest("A_addAfterB_AB_testIndexOfA", testIndexOf(A_addAfterB_AB(), ELEMENT_A, 0));
			printTest("A_addAfterB_AB_testIndexOfB", testIndexOf(A_addAfterB_AB(), ELEMENT_B, 1));
			printTest("A_addAfterB_AB_testIndexOfC", testIndexOf(A_addAfterB_AB(), ELEMENT_C, -1));
			printTest("A_addAfterB_AB_testRemoveNeg1", testRemoveIndex(A_addAfterB_AB(), -1, null, Result.IndexOutOfBounds));
			printTest("A_addAfterB_AB_testRemove0", testRemoveIndex(A_addAfterB_AB(), 0, ELEMENT_A, Result.MatchingValue));
			printTest("A_addAfterB_AB_testRemove1", testRemoveIndex(A_addAfterB_AB(), 1, ELEMENT_B, Result.MatchingValue));
			printTest("A_addAfterB_AB_testRemove2", testRemoveIndex(A_addAfterB_AB(), 2, null, Result.IndexOutOfBounds));
			// Iterator
			printTest("A_addAfterB_AB_testIterator", testIterator(A_addAfterB_AB(), Result.NoException));
			printTest("A_addAfterB_AB_testIteratorHasNext", testIteratorHasNext(A_addAfterB_AB().iterator(), Result.True));
			printTest("A_addAfterB_AB_testIteratorNext", testIteratorNext(A_addAfterB_AB().iterator(), ELEMENT_A, Result.MatchingValue));
			printTest("A_addAfterB_AB_testIteratorRemove", testIteratorRemove(A_addAfterB_AB().iterator(), Result.IllegalState));
			printTest("A_addAfterB_AB_testIteratorHasNextAfterNext", testIteratorHasNext(iteratorAfterNext(A_addAfterB_AB(),1), Result.True));
			printTest("A_addAfterB_AB_testIteratorNextAfterNext", testIteratorNext(iteratorAfterNext(A_addAfterB_AB(),1), ELEMENT_B, Result.MatchingValue));
			printTest("A_addAfterB_AB_testIteratorRemoveAfterNext", testIteratorRemove(iteratorAfterNext(A_addAfterB_AB(),1), Result.NoException));
			printTest("A_addAfterB_AB_testIteratorHasNextAfterNext2", testIteratorHasNext(iteratorAfterNext(A_addAfterB_AB(),2), Result.False));
			printTest("A_addAfterB_AB_testIteratorNextAfterNext2", testIteratorNext(iteratorAfterNext(A_addAfterB_AB(),2), null, Result.NoSuchElement));
			printTest("A_addAfterB_AB_testIteratorRemoveAfterNext2", testIteratorRemove(iteratorAfterNext(A_addAfterB_AB(),2), Result.NoException));
			printTest("A_addAfterB_AB_testIteratorNextAfterRemoveNext", testIteratorNext(iteratorAfterRemove(iteratorAfterNext(A_addAfterB_AB(),1)),ELEMENT_B,  Result.MatchingValue));
			printTest("A_addAfterB_AB_testIteratorNextAfterRemoveNext2", testIteratorNext(iteratorAfterRemove(iteratorAfterNext(A_addAfterB_AB(),2)),null,  Result.NoSuchElement));
			printTest("A_addAfterB_AB_testIteratorHasNextAfterRemoveNext",testIteratorHasNext(iteratorAfterRemove(iteratorAfterNext(A_addAfterB_AB(),1)),Result.True));
			printTest("A_addAfterB_AB_testIteratorHasNextAfterRemoveNext2",testIteratorHasNext(iteratorAfterRemove(iteratorAfterNext(A_addAfterB_AB(),2)),Result.False));
			printTest("A_addAfterB_AB_iteratorTestRealRemove",testIteratorRealRemove(A_addAfterB_AB(), 1, "[2]", Result.MatchingValue));
			printTest("A_addAfterB_AB_iteratorTestRealRemove2",testIteratorRealRemove(A_addAfterB_AB(), 2, "[1]", Result.MatchingValue));
			//ListIterator
			if(A_removeFirst_empty().getClass()==testDLL().getClass()){
			printTest("A_addAfterB_AB_testListIteratorUnIndexed", testListIteratorUnIndexed(A_addAfterB_AB(), Result.NoException));
			printTest("A_addAfterB_AB_testListIteratorIndexedNeg1", testListIteratorIndexed(A_addAfterB_AB(), -1, Result.IndexOutOfBounds));
			printTest("A_addAfterB_AB_testListIteratorIndexed0", testListIteratorIndexed(A_addAfterB_AB(), 0, Result.NoException));
			printTest("A_addAfterB_AB_testListIteratorIndexed1", testListIteratorIndexed(A_addAfterB_AB(), 1, Result.NoException));
			printTest("A_addAfterB_AB_testListIteratorIndexed2", testListIteratorIndexed(A_addAfterB_AB(), 2, Result.NoException));
			printTest("A_addAfterB_AB_testListIteratorIndexed3", testListIteratorIndexed(A_addAfterB_AB(), 3, Result.IndexOutOfBounds));
			
			printTest("A_addAfterB_AB_testListIteratorHasNext", testListIteratorHasNext(A_addAfterB_AB(),null, null, Result.True));
			printTest("A_addAfterB_AB_testListIteratorNext", testListIteratorNext(A_addAfterB_AB(),null, null, ELEMENT_A, Result.MatchingValue));
			printTest("A_addAfterB_AB_testListIteratorNextIndex", testListIteratorNextIndex(A_addAfterB_AB(),null, null, 0, Result.MatchingValue));
			printTest("A_addAfterB_AB_testListIteratorHasPrevious", testListIteratorHasPrevious(A_addAfterB_AB(),null, null, Result.False));
			printTest("A_addAfterB_AB_testListIteratorPrevious", testListIteratorPrevious(A_addAfterB_AB(),null, null, null, Result.NoSuchElement));
			printTest("A_addAfterB_AB_testListIteratorPreviousIndex", testListIteratorPreviousIndex(A_addAfterB_AB(),null, null, -1, Result.MatchingValue));
			printTest("A_addAfterB_AB_testListIteratorRemove", testListIteratorRemove(A_addAfterB_AB(),null, null, Result.IllegalState));
			printTest("A_addAfterB_AB_testListIteratorRealRemove", testListIteratorRealRemoveAfterNext(A_addAfterB_AB(), null, 0, "[1, 2]", Result.MatchingValue));
			printTest("A_addAfterB_AB_testListIteratorSet", testListIteratorSet(A_addAfterB_AB(), null,null, ELEMENT_D, Result.IllegalState));
			printTest("A_addAfterB_AB_testListIteratorRealSet", testListIteratorRealSetAfterNext(A_addAfterB_AB(), null, 0, ELEMENT_D, "[1, 2]", Result.MatchingValue));
			printTest("A_addAfterB_AB_testListIteratorAdd", testListIteratorAdd(A_addAfterB_AB(),null, null, ELEMENT_D, Result.NoException));
			printTest("A_addAfterB_AB_testListIteratorRealAdd", testListIteratorRealAddAfterNext(A_addAfterB_AB(), null, 0, ELEMENT_D, "[4, 1, 2]", Result.MatchingValue));
			
			printTest("A_addAfterB_AB_testListIteratorHasNextAfterNext", testListIteratorHasNext(null,getListIteratorAfterCall(A_addAfterB_AB(), "next", null, null), null, Result.True));
			printTest("A_addAfterB_AB_testListIteratorNextAfterNext", testListIteratorNext(null,getListIteratorAfterCall(A_addAfterB_AB(), "next", null, null), null, ELEMENT_B, Result.MatchingValue));
			printTest("A_addAfterB_AB_testListIteratorNextIndexAfterNext", testListIteratorNextIndex(null,getListIteratorAfterCall(A_addAfterB_AB(), "next", null, null), null, 1, Result.MatchingValue));
			printTest("A_addAfterB_AB_testListIteratorHasPreviousAfterNext", testListIteratorHasPrevious(null,getListIteratorAfterCall(A_addAfterB_AB(), "next", null, null), null, Result.True));
			printTest("A_addAfterB_AB_testListIteratorPreviousAfterNext", testListIteratorPrevious(null,getListIteratorAfterCall(A_addAfterB_AB(), "next", null, null), null, ELEMENT_A, Result.MatchingValue));
			printTest("A_addAfterB_AB_testListIteratorPreviousIndexAfterNext", testListIteratorPreviousIndex(null,getListIteratorAfterCall(A_addAfterB_AB(), "next", null, null), null, 0, Result.MatchingValue));
			printTest("A_addAfterB_AB_testListIteratorRemoveAfterNext", testListIteratorRemove(null,getListIteratorAfterCall(A_addAfterB_AB(), "next", null, null), null, Result.NoException));
			printTest("A_addAfterB_AB_testListIteratorRealRemoveAfterNext", testListIteratorRealRemoveAfterNext(A_addAfterB_AB(),null, 1, "[2]", Result.MatchingValue));
			printTest("A_addAfterB_AB_testListIteratorSetAfterNext", testListIteratorSet(null, getListIteratorAfterCall(A_addAfterB_AB(), "next", null, null),null, ELEMENT_D, Result.NoException));
			printTest("A_addAfterB_AB_testListIteratorRealSetAfterNext", testListIteratorRealSetAfterNext(A_addAfterB_AB(), null, 1, ELEMENT_D, "[4, 2]", Result.MatchingValue));
			printTest("A_addAfterB_AB_testListIteratorAddAfterNext", testListIteratorAdd(null,getListIteratorAfterCall(A_addAfterB_AB(), "next", null, null), null, ELEMENT_D, Result.NoException));
			printTest("A_addAfterB_AB_testListIteratorRealAddAfterNext", testListIteratorRealAddAfterNext(A_addAfterB_AB(), null, 1, ELEMENT_D, "[1, 4, 2]", Result.MatchingValue));
			
			printTest("A_addAfterB_AB_testListIteratorHasNextAfter2Next", testListIteratorHasNext(null,getListIteratorAfterCallItrItr(getListIteratorAfterCall(A_addAfterB_AB(), "next", null, null), "next", null, null), null, Result.False));
			printTest("A_addAfterB_AB_testListIteratorNextAfter2Next", testListIteratorNext(null,getListIteratorAfterCallItrItr(getListIteratorAfterCall(A_addAfterB_AB(), "next", null, null), "next", null, null), null, null, Result.NoSuchElement));
			printTest("A_addAfterB_AB_testListIteratorNextIndexAfter2Next", testListIteratorNextIndex(null,getListIteratorAfterCallItrItr(getListIteratorAfterCall(A_addAfterB_AB(), "next", null, null), "next", null, null), null, 2, Result.MatchingValue));
			printTest("A_addAfterB_AB_testListIteratorHasPreviousAfter2Next", testListIteratorHasPrevious(null,getListIteratorAfterCallItrItr(getListIteratorAfterCall(A_addAfterB_AB(), "next", null, null), "next", null, null), null, Result.True));
			printTest("A_addAfterB_AB_testListIteratorPreviousAfter2Next", testListIteratorPrevious(null,getListIteratorAfterCallItrItr(getListIteratorAfterCall(A_addAfterB_AB(), "next", null, null), "next", null, null), null, ELEMENT_B, Result.MatchingValue));
			printTest("A_addAfterB_AB_testListIteratorPreviousIndexAfter2Next", testListIteratorPreviousIndex(null,getListIteratorAfterCallItrItr(getListIteratorAfterCall(A_addAfterB_AB(), "next", null, null), "next", null, null), null, 1, Result.MatchingValue));
			printTest("A_addAfterB_AB_testListIteratorRemoveAfter2Next", testListIteratorRemove(null,getListIteratorAfterCallItrItr(getListIteratorAfterCall(A_addAfterB_AB(), "next", null, null), "next", null, null), null, Result.NoException));
			printTest("A_addAfterB_AB_testListIteratorRealRemoveAfter2Next", testListIteratorRealRemoveAfterNext(A_addAfterB_AB(),null, 2, "[1]", Result.MatchingValue));
			printTest("A_addAfterB_AB_testListIteratorSetAfter2Next", testListIteratorSet(null, getListIteratorAfterCallItrItr(getListIteratorAfterCall(A_addAfterB_AB(), "next", null, null), "next", null, null),null, ELEMENT_D, Result.NoException));
			printTest("A_addAfterB_AB_testListIteratorRealSetAfter2Next", testListIteratorRealSetAfterNext(A_addAfterB_AB(), null, 2, ELEMENT_D, "[1, 4]", Result.MatchingValue));
			printTest("A_addAfterB_AB_testListIteratorAddAfter2Next", testListIteratorAdd(null,getListIteratorAfterCallItrItr(getListIteratorAfterCall(A_addAfterB_AB(), "next", null, null), "next", null, null), null, ELEMENT_D, Result.NoException));
			printTest("A_addAfterB_AB_testListIteratorRealAddAfter2Next", testListIteratorRealAddAfterNext(A_addAfterB_AB(), null, 2, ELEMENT_D, "[1, 2, 4]", Result.MatchingValue));
			
			printTest("A_addAfterB_AB_testListIterator0HasNext", testListIteratorHasNext(A_addAfterB_AB(),null, 0, Result.True));
			printTest("A_addAfterB_AB_testListIterator0Next", testListIteratorNext(A_addAfterB_AB(),null, 0, ELEMENT_A, Result.MatchingValue));
			printTest("A_addAfterB_AB_testListIterator0NextIndex", testListIteratorNextIndex(A_addAfterB_AB(),null, 0, 0, Result.MatchingValue));
			printTest("A_addAfterB_AB_testListIterator0HasPrevious", testListIteratorHasPrevious(A_addAfterB_AB(),null, 0, Result.False));
			printTest("A_addAfterB_AB_testListIterator0Previous", testListIteratorPrevious(A_addAfterB_AB(),null, 0, null, Result.NoSuchElement));
			printTest("A_addAfterB_AB_testListIterator0PreviousIndex", testListIteratorPreviousIndex(A_addAfterB_AB(),null, 0, -1, Result.MatchingValue));
			printTest("A_addAfterB_AB_testListIterator0Remove", testListIteratorRemove(A_addAfterB_AB(),null, 0, Result.IllegalState));
			printTest("A_addAfterB_AB_testListIterator0RealRemove", testListIteratorRealRemoveAfterNext(A_addAfterB_AB(), 0, 0, "[1, 2]", Result.MatchingValue));
			printTest("A_addAfterB_AB_testListIterator0Set", testListIteratorSet(A_addAfterB_AB(), null,0, ELEMENT_D, Result.IllegalState));
			printTest("A_addAfterB_AB_testListIterator0RealSet", testListIteratorRealSetAfterNext(A_addAfterB_AB(), 0, 0, ELEMENT_D, "[1, 2]", Result.MatchingValue));
			printTest("A_addAfterB_AB_testListIterator0Add", testListIteratorAdd(A_addAfterB_AB(),null, 0, ELEMENT_D, Result.NoException));
			printTest("A_addAfterB_AB_testListIterator0RealAdd", testListIteratorRealAddAfterNext(A_addAfterB_AB(), 0, 0, ELEMENT_D, "[4, 1, 2]", Result.MatchingValue));
			
			printTest("A_addAfterB_AB_testListIterator0HasNextAfterNext", testListIteratorHasNext(null,getListIteratorAfterCall(A_addAfterB_AB(), "next", 0, null), 0, Result.True));
			printTest("A_addAfterB_AB_testListIterator0NextAfterNext", testListIteratorNext(null,getListIteratorAfterCall(A_addAfterB_AB(), "next", 0, null), 0, ELEMENT_B, Result.MatchingValue));
			printTest("A_addAfterB_AB_testListIterator0NextIndexAfterNext", testListIteratorNextIndex(null,getListIteratorAfterCall(A_addAfterB_AB(), "next", 0, null), 0, 1, Result.MatchingValue));
			printTest("A_addAfterB_AB_testListIterator0HasPreviousAfterNext", testListIteratorHasPrevious(null,getListIteratorAfterCall(A_addAfterB_AB(), "next", 0, null),0, Result.True));
			printTest("A_addAfterB_AB_testListIterator0PreviousAfterNext", testListIteratorPrevious(null,getListIteratorAfterCall(A_addAfterB_AB(), "next", 0, null), 0, ELEMENT_A, Result.MatchingValue));
			printTest("A_addAfterB_AB_testListIterator0PreviousIndexAfterNext", testListIteratorPreviousIndex(null,getListIteratorAfterCall(A_addAfterB_AB(), "next", 0, null), 0, 0, Result.MatchingValue));
			printTest("A_addAfterB_AB_testListIterator0RemoveAfterNext", testListIteratorRemove(null,getListIteratorAfterCall(A_addAfterB_AB(), "next", 0, null), 0, Result.NoException));
			printTest("A_addAfterB_AB_testListIterator0RealRemoveAfterNext", testListIteratorRealRemoveAfterNext(A_addAfterB_AB(),0, 1, "[2]", Result.MatchingValue));
			printTest("A_addAfterB_AB_testListIterator0SetAfterNext", testListIteratorSet(null, getListIteratorAfterCall(A_addAfterB_AB(), "next", 0, null),0, ELEMENT_D, Result.NoException));
			printTest("A_addAfterB_AB_testListIterator0RealSetAfterNext", testListIteratorRealSetAfterNext(A_addAfterB_AB(), 0, 1, ELEMENT_D, "[4, 2]", Result.MatchingValue));
			printTest("A_addAfterB_AB_testListIterator0AddAfterNext", testListIteratorAdd(null,getListIteratorAfterCall(A_addAfterB_AB(), "next", 0, null), 0, ELEMENT_D, Result.NoException));
			printTest("A_addAfterB_AB_testListIterator0RealAddAfterNext", testListIteratorRealAddAfterNext(A_addAfterB_AB(), 0, 1, ELEMENT_D, "[1, 4, 2]", Result.MatchingValue));
			
			printTest("A_addAfterB_AB_testListIterator0HasNextAfter2Next", testListIteratorHasNext(null,getListIteratorAfterCallItrItr(getListIteratorAfterCall(A_addAfterB_AB(), "next", 0, null), "next", null, null), 0, Result.False));
			printTest("A_addAfterB_AB_testListIterator0NextAfter2Next", testListIteratorNext(null,getListIteratorAfterCallItrItr(getListIteratorAfterCall(A_addAfterB_AB(), "next", 0, null), "next", null, null), 0, null, Result.NoSuchElement));
			printTest("A_addAfterB_AB_testListIterator0NextIndexAfter2Next", testListIteratorNextIndex(null,getListIteratorAfterCallItrItr(getListIteratorAfterCall(A_addAfterB_AB(), "next", 0, null), "next", null, null), 0, 2, Result.MatchingValue));
			printTest("A_addAfterB_AB_testListIterator0HasPreviousAfter2Next", testListIteratorHasPrevious(null,getListIteratorAfterCallItrItr(getListIteratorAfterCall(A_addAfterB_AB(), "next", 0, null), "next", null, null), 0, Result.True));
			printTest("A_addAfterB_AB_testListIterator0PreviousAfter2Next", testListIteratorPrevious(null,getListIteratorAfterCallItrItr(getListIteratorAfterCall(A_addAfterB_AB(), "next", 0, null), "next", null, null), 0, ELEMENT_B, Result.MatchingValue));
			printTest("A_addAfterB_AB_testListIterator0PreviousIndexAfter2Next", testListIteratorPreviousIndex(null,getListIteratorAfterCallItrItr(getListIteratorAfterCall(A_addAfterB_AB(), "next", 0, null), "next", null, null), 0, 1, Result.MatchingValue));
			printTest("A_addAfterB_AB_testListIterator0RemoveAfter2Next", testListIteratorRemove(null,getListIteratorAfterCallItrItr(getListIteratorAfterCall(A_addAfterB_AB(), "next", 0, null), "next", null, null), 0, Result.NoException));
			printTest("A_addAfterB_AB_testListIterator0RealRemoveAfter2Next", testListIteratorRealRemoveAfterNext(A_addAfterB_AB(),0, 2, "[1]", Result.MatchingValue));
			printTest("A_addAfterB_AB_testListIterator0SetAfter2Next", testListIteratorSet(null, getListIteratorAfterCallItrItr(getListIteratorAfterCall(A_addAfterB_AB(), "next", 0, null), "next", null, null),0, ELEMENT_D, Result.NoException));
			printTest("A_addAfterB_AB_testListIterator0RealSetAfter2Next", testListIteratorRealSetAfterNext(A_addAfterB_AB(), 0,2, ELEMENT_D, "[1, 4]", Result.MatchingValue));
			printTest("A_addAfterB_AB_testListIterator0AddAfter2Next", testListIteratorAdd(null,getListIteratorAfterCallItrItr(getListIteratorAfterCall(A_addAfterB_AB(), "next", 0, null), "next", null, null), 0, ELEMENT_D, Result.NoException));
			printTest("A_addAfterB_AB_testListIterator0RealAddAfter2Next", testListIteratorRealAddAfterNext(A_addAfterB_AB(), 0, 2, ELEMENT_D, "[1, 2, 4]", Result.MatchingValue));
	
			printTest("A_addAfterB_AB_testListIterator1HasNext", testListIteratorHasNext( A_addAfterB_AB(),null, 1, Result.True));
			printTest("A_addAfterB_AB_testListIterator1Next", testListIteratorNext( A_addAfterB_AB(),null, 1, ELEMENT_B, Result.MatchingValue));
			printTest("A_addAfterB_AB_testListIterator1NextIndex", testListIteratorNextIndex( A_addAfterB_AB(),null, 1, 1, Result.MatchingValue));
			printTest("A_addAfterB_AB_testListIterator1HasPrevious", testListIteratorHasPrevious( A_addAfterB_AB(),null, 1, Result.True));
			printTest("A_addAfterB_AB_testListIterator1Previous", testListIteratorPrevious( A_addAfterB_AB(),null, 1, ELEMENT_A, Result.MatchingValue));
			printTest("A_addAfterB_AB_testListIterator1PreviousIndex", testListIteratorPreviousIndex( A_addAfterB_AB(),null, 1, 0, Result.MatchingValue));
			printTest("A_addAfterB_AB_testListIterator1Remove", testListIteratorRemove( A_addAfterB_AB(),null, 1, Result.IllegalState));
			printTest("A_addAfterB_AB_testListIterator1RealRemove", testListIteratorRealRemoveAfterNext(A_addAfterB_AB(),0, 1, "[2]", Result.MatchingValue));
			printTest("A_addAfterB_AB_testListIterator1Set", testListIteratorSet( A_addAfterB_AB(),null,1, ELEMENT_D, Result.IllegalState));
			printTest("A_addAfterB_AB_testListIterator1RealSet", testListIteratorRealSetAfterNext(A_addAfterB_AB(), 0, 1, ELEMENT_D, "[4, 2]", Result.MatchingValue));
			printTest("A_addAfterB_AB_testListIterator1Add", testListIteratorAdd( A_addAfterB_AB(),null, 1, ELEMENT_D, Result.NoException));
			printTest("A_addAfterB_AB_testListIterator1RealAdd", testListIteratorRealAddAfterNext(A_addAfterB_AB(), 0, 1, ELEMENT_D, "[1, 4, 2]", Result.MatchingValue));
			
			printTest("A_addAfterB_AB_testListIterator1HasNextAfterNext", testListIteratorHasNext(null,getListIteratorAfterCall(A_addAfterB_AB(), "next", 1, null), 1, Result.False));
			printTest("A_addAfterB_AB_testListIterator1NextAfterNext", testListIteratorNext(null,getListIteratorAfterCall(A_addAfterB_AB(), "next", 1, null), 1, null, Result.NoSuchElement));
			printTest("A_addAfterB_AB_testListIterator1NextIndexAfterNext", testListIteratorNextIndex(null,getListIteratorAfterCall(A_addAfterB_AB(), "next", 1, null), 1, 2, Result.MatchingValue));
			printTest("A_addAfterB_AB_testListIterator1HasPreviousAfterNext", testListIteratorHasPrevious(null,getListIteratorAfterCall(A_addAfterB_AB(), "next", 1, null), 1, Result.True));
			printTest("A_addAfterB_AB_testListIterator1PreviousAfterNext", testListIteratorPrevious(null,getListIteratorAfterCall(A_addAfterB_AB(), "next", 1, null), 1, ELEMENT_B, Result.MatchingValue));
			printTest("A_addAfterB_AB_testListIterator1PreviousIndexAfterNext", testListIteratorPreviousIndex(null,getListIteratorAfterCall(A_addAfterB_AB(), "next", 1, null), 1, 1, Result.MatchingValue));
			printTest("A_addAfterB_AB_testListIterator1RemoveAfterNext", testListIteratorRemove(null,getListIteratorAfterCall(A_addAfterB_AB(), "next", 1, null), 1, Result.NoException));
			printTest("A_addAfterB_AB_testListIterator1RealRemoveAfterNext", testListIteratorRealRemoveAfterNext(A_addAfterB_AB(),1, 1, "[1]", Result.MatchingValue));
			printTest("A_addAfterB_AB_testListIterator1SetAfterNext", testListIteratorSet(null, getListIteratorAfterCall(A_addAfterB_AB(), "next", 1, null),1, ELEMENT_D, Result.NoException));
			printTest("A_addAfterB_AB_testListIterator1RealSetAfterNext", testListIteratorRealSetAfterNext(A_addAfterB_AB(), 1, 1, ELEMENT_D, "[1, 4]", Result.MatchingValue));
			printTest("A_addAfterB_AB_testListIterator1AddAfterNext", testListIteratorAdd(null,getListIteratorAfterCall(A_addAfterB_AB(), "next", 1, null), null, ELEMENT_D, Result.NoException));
			printTest("A_addAfterB_AB_testListIterator1RealAddAfterNext", testListIteratorRealAddAfterNext(A_addAfterB_AB(), 1, 1, ELEMENT_D, "[1, 2, 4]", Result.MatchingValue));
			
			printTest("A_addAfterB_AB_testListIterator1HasNextAfterPrev", testListIteratorHasNext(null,getListIteratorAfterCall(A_addAfterB_AB(), "previous", 1, null), null, Result.True));
			printTest("A_addAfterB_AB_testListIterator1NextAfterPrev", testListIteratorNext(null,getListIteratorAfterCall(A_addAfterB_AB(), "previous", 1, null), null, ELEMENT_A, Result.MatchingValue));
			printTest("A_addAfterB_AB_testListIterator1NextIndexAfterPrev", testListIteratorNextIndex(null,getListIteratorAfterCall(A_addAfterB_AB(), "previous", 1, null), null, 0, Result.MatchingValue));
			printTest("A_addAfterB_AB_testListIterator1HasPreviousAfterPrev", testListIteratorHasPrevious(null,getListIteratorAfterCall(A_addAfterB_AB(), "previous", 1, null), null, Result.False));
			printTest("A_addAfterB_AB_testListIterator1PreviousAfterPrev", testListIteratorPrevious(null,getListIteratorAfterCall(A_addAfterB_AB(), "previous", 1, null), null, null, Result.NoSuchElement));
			printTest("A_addAfterB_AB_testListIterator1PreviousIndexAfterPrev", testListIteratorPreviousIndex(null,getListIteratorAfterCall(A_addAfterB_AB(), "previous", 1, null), null, -1, Result.MatchingValue));
			printTest("A_addAfterB_AB_testListIterator1RemoveAfterPrev", testListIteratorRemove(null,getListIteratorAfterCall(A_addAfterB_AB(), "previous", 1, null), null, Result.NoException));
			printTest("A_addAfterB_AB_testListIterator1RealRemoveAfterPrev", testListIteratorRealRemoveAfterPrev(A_addAfterB_AB(), 1, 1, "[2]", Result.MatchingValue));
			printTest("A_addAfterB_AB_testListIterator1SetAfterPrev", testListIteratorSet(null, getListIteratorAfterCall(A_addAfterB_AB(), "previous", 1, null),null, ELEMENT_D, Result.NoException));
			printTest("A_addAfterB_AB_testListIterator1RealSetAfterPrev", testListIteratorRealSetAfterPrev(A_addAfterB_AB(), 1, 1, ELEMENT_D, "[4, 2]", Result.MatchingValue));
			printTest("A_addAfterB_AB_testListIterator1AddAfterPrev", testListIteratorAdd(null,getListIteratorAfterCall(A_addAfterB_AB(), "previous", 1, null), null, ELEMENT_D, Result.NoException));
			printTest("A_addAfterB_AB_testListIterator1RealAddAfterPrev", testListIteratorRealAddAfterPrev(A_addAfterB_AB(), 1, 1, ELEMENT_D, "[4, 1, 2]", Result.MatchingValue));
			
			printTest("A_addAfterB_AB_testListIterator1HasNextAfterPrevNext", testListIteratorHasNext(null,getListIteratorAfterCallItrItr(getListIteratorAfterCall(A_addAfterB_AB(), "previous", 1, null), "next", null, null), null, Result.True));
			printTest("A_addAfterB_AB_testListIterator1NextAfterPrevNext", testListIteratorNext(null,getListIteratorAfterCallItrItr(getListIteratorAfterCall(A_addAfterB_AB(), "previous", 1, null), "next", null, null), null, ELEMENT_B, Result.MatchingValue));
			printTest("A_addAfterB_AB_testListIterator1NextIndexAfterPrevNext", testListIteratorNextIndex(null,getListIteratorAfterCallItrItr(getListIteratorAfterCall(A_addAfterB_AB(), "previous", 1, null), "next", null, null), null, 1, Result.MatchingValue));
			printTest("A_addAfterB_AB_testListIterator1HasPreviousAfterPrevNext", testListIteratorHasPrevious(null,getListIteratorAfterCallItrItr(getListIteratorAfterCall(A_addAfterB_AB(), "previous", 1, null), "next", null, null), null, Result.True));
			printTest("A_addAfterB_AB_testListIterator1PreviousAfterPrevNext", testListIteratorPrevious(null,getListIteratorAfterCallItrItr(getListIteratorAfterCall(A_addAfterB_AB(), "previous", 1, null), "next", null, null), null, ELEMENT_A, Result.MatchingValue));
			printTest("A_addAfterB_AB_testListIterator1PreviousIndexAfterPrevNext", testListIteratorPreviousIndex(null,getListIteratorAfterCallItrItr(getListIteratorAfterCall(A_addAfterB_AB(), "previous", 1, null), "next", null, null), null, 0, Result.MatchingValue));
			printTest("A_addAfterB_AB_testListIterator1RemoveAfterPrevNext", testListIteratorRemove(null,getListIteratorAfterCallItrItr(getListIteratorAfterCall(A_addAfterB_AB(), "previous", 1, null), "next", null, null), null, Result.NoException));
			printTest("A_addAfterB_AB_testListIterator1RealRemoveAfterPrevNext", testListIteratorRealRemoveAfterNext(A_addAfterB_AB(),null, 1, "[2]", Result.MatchingValue));
			printTest("A_addAfterB_AB_testListIterator1SetAfterPrevNext", testListIteratorSet(null, getListIteratorAfterCallItrItr(getListIteratorAfterCall(A_addAfterB_AB(), "previous", 1, null), "next", null, null),null, ELEMENT_D, Result.NoException));
			printTest("A_addAfterB_AB_testListIterator1RealSetAfterPrevNext", testListIteratorRealSetAfterNext(A_addAfterB_AB(), null, 1, ELEMENT_D, "[4, 2]", Result.MatchingValue));
			printTest("A_addAfterB_AB_testListIterator1AddAfterPrevNext", testListIteratorAdd(null,getListIteratorAfterCallItrItr(getListIteratorAfterCall(A_addAfterB_AB(), "previous", 1, null), "next", null, null), null, ELEMENT_D, Result.NoException));
			printTest("A_addAfterB_AB_testListIterator1RealAddAfterPrevNext", testListIteratorRealAddAfterNext(A_addAfterB_AB(), null, 1, ELEMENT_D, "[1, 4, 2]", Result.MatchingValue));
		
			printTest("A_addAfterB_AB_testListIterator1HasNextAfterPrev2Next", testListIteratorHasNext(null,getListIteratorAfterCallItrItr(getListIteratorAfterCallItrItr(getListIteratorAfterCall(A_addAfterB_AB(), "previous", 1, null), "next", null, null), "next", null, null), 1, Result.False));
			printTest("A_addAfterB_AB_testListIterator1NextAfterPrev2Next", testListIteratorNext(null,getListIteratorAfterCallItrItr(getListIteratorAfterCallItrItr(getListIteratorAfterCall(A_addAfterB_AB(), "previous", 1, null), "next", null, null), "next", null, null), 1, null, Result.NoSuchElement));
			printTest("A_addAfterB_AB_testListIterator1NextIndexAfterPrev2Next", testListIteratorNextIndex(null,getListIteratorAfterCallItrItr(getListIteratorAfterCallItrItr(getListIteratorAfterCall(A_addAfterB_AB(), "previous", 1, null), "next", null, null), "next", null, null), 1, 2, Result.MatchingValue));
			printTest("A_addAfterB_AB_testListIterator1HasPreviousAfterPrev2Next", testListIteratorHasPrevious(null,getListIteratorAfterCallItrItr(getListIteratorAfterCallItrItr(getListIteratorAfterCall(A_addAfterB_AB(), "previous", 1, null), "next", null, null), "next", null, null), 1, Result.True));
			printTest("A_addAfterB_AB_testListIterator1PreviousAfterPrev2Next", testListIteratorPrevious(null,getListIteratorAfterCallItrItr(getListIteratorAfterCallItrItr(getListIteratorAfterCall(A_addAfterB_AB(), "previous", 1, null), "next", null, null), "next", null, null), 1, ELEMENT_B, Result.MatchingValue));
			printTest("A_addAfterB_AB_testListIterator1PreviousIndexAfterPrev2Next", testListIteratorPreviousIndex(null,getListIteratorAfterCallItrItr(getListIteratorAfterCallItrItr(getListIteratorAfterCall(A_addAfterB_AB(), "previous", 1, null), "next", null, null), "next", null, null), 1, 1, Result.MatchingValue));
			printTest("A_addAfterB_AB_testListIterator1RemoveAfterPrev2Next", testListIteratorRemove(null,getListIteratorAfterCallItrItr(getListIteratorAfterCallItrItr(getListIteratorAfterCall(A_addAfterB_AB(), "previous", 1, null), "next", null, null), "next", null, null), 1, Result.NoException));
			printTest("A_addAfterB_AB_testListIterator1RealRemoveAfterPrev2Next", testListIteratorRealRemoveAfterNext(A_addAfterB_AB(),1, 1, "[1]", Result.MatchingValue));
			printTest("A_addAfterB_AB_testListIterator1SetAfterPrev2Next", testListIteratorSet(null, getListIteratorAfterCallItrItr(getListIteratorAfterCallItrItr(getListIteratorAfterCall(A_addAfterB_AB(), "previous", 1, null), "next", null, null), "next", null, null),1, ELEMENT_D, Result.NoException));
			printTest("A_addAfterB_AB_testListIterator1RealSetAfterPrev2Next", testListIteratorRealSetAfterNext(A_addAfterB_AB(), 1, 1, ELEMENT_D, "[1, 4]", Result.MatchingValue));
			printTest("A_addAfterB_AB_testListIterator1AddAfterPrev2Next", testListIteratorAdd(null,getListIteratorAfterCallItrItr(getListIteratorAfterCallItrItr(getListIteratorAfterCall(A_addAfterB_AB(), "previous", 1, null), "next", null, null), "next", null, null), null, ELEMENT_D, Result.NoException));
			printTest("A_addAfterB_AB_testListIterator1RealAddAfterPrev2Next", testListIteratorRealAddAfterNext(A_addAfterB_AB(), 1, 1, ELEMENT_D, "[1, 2, 4]", Result.MatchingValue));
			}
		} catch (Exception e) {
			System.out.printf("***UNABLE TO RUN/COMPLETE %s***\n", "test_A_addAfterB_AB");
			e.printStackTrace();
		}
	}
	
	//////////////////////////////////////
	// SCENARIO: [A] -> remove(0) -> []
	//  XXX SCENARIO 12
	//////////////////////////////////////
	private ListADT<Integer> A_remove0_empty() {
		// cast to UnorderedListADT to make addToFront() available
		IndexedListADT<Integer> list = (IndexedListADT<Integer>) newList(); 
		list.add(0,ELEMENT_A);
		list.remove(0);
		return list;
	}
	private void test_A_remove0_empty(){
		try {
			// ListADT
			printTest("A_remove0_empty_testRemoveFirst", testRemoveFirst(A_remove0_empty(), null, Result.EmptyCollection));
			printTest("A_remove0_empty_testRemoveLast", testRemoveLast(A_remove0_empty(), null, Result.EmptyCollection));
			printTest("A_remove0_empty_testRemoveA", testRemoveElement(A_remove0_empty(), null, Result.ElementNotFound));
			printTest("A_remove0_empty_testFirst", testFirst(A_remove0_empty(), null, Result.EmptyCollection));
			printTest("A_remove0_empty_testLast", testLast(A_remove0_empty(), null, Result.EmptyCollection));
			printTest("A_remove0_empty_testContainsA", testContains(A_remove0_empty(), ELEMENT_A, Result.False));
			printTest("A_remove0_empty_testIsEmpty", testIsEmpty(A_remove0_empty(), Result.True));
			printTest("A_remove0_empty_testSize", testSize(A_remove0_empty(), 0));
			printTest("A_remove0_empty_testToString", testToString(A_remove0_empty(), Result.ValidString));
			// UnorderedListADT
			printTest("A_remove0_empty_testAddToFrontA", testAddToFront(A_remove0_empty(), ELEMENT_A, Result.NoException));
			printTest("A_remove0_empty_testAddToRearA", testAddToRear(A_remove0_empty(), ELEMENT_A, Result.NoException));
			printTest(	"A_remove0_empty_testAddAfterBA", testAddAfter(A_remove0_empty(), ELEMENT_B, ELEMENT_A, Result.ElementNotFound));
			// IndexedListADT
			printTest("A_remove0_empty_testAddAtIndexNeg1", testAddAtIndex(A_remove0_empty(), -1, ELEMENT_A, Result.IndexOutOfBounds));
			printTest("A_remove0_empty_testAddAtIndex0", testAddAtIndex(A_remove0_empty(), 0, ELEMENT_A, Result.NoException));
			printTest("A_remove0_empty_testAddAtIndex1", testAddAtIndex(A_remove0_empty(), 1, ELEMENT_A, Result.IndexOutOfBounds));
			printTest("A_remove0_empty_testSetNeg1A", testSet(A_remove0_empty(), -1, ELEMENT_A, Result.IndexOutOfBounds));
			printTest("A_remove0_empty_testSet0A", testSet(A_remove0_empty(), 0, ELEMENT_A, Result.IndexOutOfBounds));
			printTest("A_remove0_empty_testAddA", testAdd(A_remove0_empty(), ELEMENT_A, Result.NoException));
			printTest("A_remove0_empty_testGetNeg1", testGet(A_remove0_empty(), -1, null, Result.IndexOutOfBounds));
			printTest("A_remove0_empty_testGet0", testGet(A_remove0_empty(), 0, null, Result.IndexOutOfBounds));
			printTest("A_remove0_empty_testIndexOfA", testIndexOf(A_remove0_empty(), ELEMENT_A, -1));
			printTest("A_remove0_empty_testRemoveNeg1", testRemoveIndex(A_remove0_empty(), -1, null, Result.IndexOutOfBounds));
			printTest("A_remove0_empty_testRemove0", testRemoveIndex(A_remove0_empty(), 0, null, Result.IndexOutOfBounds));
			// Iterator
			printTest("A_remove0_empty_testIterator", testIterator(A_remove0_empty(), Result.NoException));
			printTest("A_remove0_empty_testIteratorHasNext", testIteratorHasNext(A_remove0_empty().iterator(), Result.False));
			printTest("A_remove0_empty_testIteratorNext", testIteratorNext(A_remove0_empty().iterator(), null, Result.NoSuchElement));
			printTest("A_remove0_empty_testIteratorRemove", testIteratorRemove(A_remove0_empty().iterator(), Result.IllegalState));
			//ListIterator
			if(A_removeFirst_empty().getClass()==testDLL().getClass()){
			printTest("A_remove0_empty_testListIteratorUnIndexed",testListIteratorUnIndexed(A_remove0_empty(), Result.NoException));
			printTest("A_remove0_empty_testListIteratorIndexed",testListIteratorIndexed(A_remove0_empty(), 0, Result.NoException));
			printTest("A_remove0_empty_testListIteratorIndexed1",testListIteratorIndexed(A_remove0_empty(), 1, Result.IndexOutOfBounds));
			printTest("A_remove0_empty_testListIteratorHasNext", testListIteratorHasNext(A_remove0_empty(),null, null, Result.False));
			printTest("A_remove0_empty_testListIteratorHasNextIndexed0", testListIteratorHasNext(A_remove0_empty(),null, 0, Result.False));
			printTest("A_remove0_empty_testListIteratorNext",testListIteratorNext(A_remove0_empty(),null, null, null, Result.NoSuchElement));
			printTest("A_remove0_empty_testListIteratorNextIndexed0",testListIteratorNext(A_remove0_empty(),null, 0, null, Result.NoSuchElement));
			printTest("A_remove0_empty_testListIteratorHasPrevious",testListIteratorHasPrevious(A_remove0_empty(),null, null, Result.False));
			printTest("A_remove0_empty_testListIteratorHasPreviousIndexed0",testListIteratorHasPrevious(A_remove0_empty(),null, 0, Result.False));
			printTest("A_remove0_empty_testListIteratorPrevious",testListIteratorPrevious(A_remove0_empty(),null, null, null, Result.NoSuchElement));
			printTest("A_remove0_empty_testListIteratorPreviousIndexed0",testListIteratorPrevious(A_remove0_empty(),null, 0, null, Result.NoSuchElement));
			printTest("A_remove0_empty_testListIteratorNextIndex",testListIteratorNextIndex(A_remove0_empty(),null, null, 0, Result.MatchingValue));
			printTest("A_remove0_empty_testListIteratorNextIndexIndexed0",testListIteratorNextIndex(A_remove0_empty(),null, 0, 0, Result.MatchingValue));
			printTest("A_remove0_empty_testListIteratorPreviousIndex",testListIteratorPreviousIndex(A_remove0_empty(),null, null, -1, Result.MatchingValue));
			printTest("A_remove0_empty_testListIteratorPreviousIndexIndexed0",testListIteratorPreviousIndex(A_remove0_empty(),null, 0, -1, Result.MatchingValue));
			printTest("A_remove0_empty_testListIteratorRemove",testListIteratorRemove(A_remove0_empty(),null, null, Result.IllegalState));
			printTest("A_remove0_empty_testListIteratorRemoveIndexed0",testListIteratorRemove(A_remove0_empty(),null, 0, Result.IllegalState));
			printTest("A_remove0_empty_testListIteratorSet",testListIteratorSet(A_remove0_empty(),null, null, ELEMENT_A, Result.IllegalState));
			printTest("A_remove0_empty_testListIteratorSetIndexed0",testListIteratorSet(A_remove0_empty(),null, 0, ELEMENT_A, Result.IllegalState));
			printTest("A_remove0_empty_testListIteratorAdd",testListIteratorAdd(A_remove0_empty(),null, null, ELEMENT_A, Result.NoException));
			printTest("A_remove0_empty_testListIteratorAddIndexed0",testListIteratorAdd(A_remove0_empty(), null,0, ELEMENT_A, Result.NoException));
			}
		} catch (Exception e) {
			System.out.printf("***UNABLE TO RUN/COMPLETE %s***\n", "test_A_remove0_empty");
			e.printStackTrace();
		}
	}
	
	//////////////////////////////////////
	// SCENARIO: [A] -> set(0,B) -> [B]
	//  XXX SCENARIO 13
	//////////////////////////////////////
	private ListADT<Integer> A_set0B_B() {
		// cast to UnorderedListADT to make addToFront() available
		IndexedListADT<Integer> list = (IndexedListADT<Integer>) newList(); 
		list.add(0,ELEMENT_A);
		list.set(0, ELEMENT_B);
		return list;
	}
	private void test_A_set0B_B(){
		try {
			// ListADT
			printTest("A_set0B_B_testRemoveFirst", testRemoveFirst(A_set0B_B(), ELEMENT_B, Result.MatchingValue));
			printTest("A_set0B_B_testRemoveLast", testRemoveLast(A_set0B_B(), ELEMENT_B, Result.MatchingValue));
			printTest("A_set0B_B_testRemoveB", testRemoveElement(A_set0B_B(), ELEMENT_B, Result.MatchingValue));
			printTest("A_set0B_B_testRemoveA", testRemoveElement(A_set0B_B(), ELEMENT_A, Result.ElementNotFound));
			printTest("A_set0B_B_testFirst", testFirst(A_set0B_B(), ELEMENT_B, Result.MatchingValue));
			printTest("A_set0B_B_testLast", testLast(A_set0B_B(), ELEMENT_B, Result.MatchingValue));
			printTest("A_set0B_B_testContainsB", testContains(A_set0B_B(), ELEMENT_B, Result.True));
			printTest("A_set0B_B_testContainsA", testContains(A_set0B_B(), ELEMENT_A, Result.False));
			printTest("A_set0B_B_testIsEmpty", testIsEmpty(A_set0B_B(), Result.False));
			printTest("A_set0B_B_testSize", testSize(A_set0B_B(), 1));
			printTest("A_set0B_B_testToString", testToString(A_set0B_B(), Result.ValidString));
			// UnorderedListADT
			printTest("A_set0B_B_testAddToFrontA", testAddToFront(A_set0B_B(), ELEMENT_A, Result.NoException));
			printTest("A_set0B_B_testAddToRearA", testAddToRear(A_set0B_B(), ELEMENT_A, Result.NoException));
			printTest(	"A_set0B_B_testAddAfterCD", testAddAfter(A_set0B_B(), ELEMENT_C, ELEMENT_D, Result.ElementNotFound));
			printTest(	"A_set0B_B_testAddAfterBC", testAddAfter(A_set0B_B(), ELEMENT_B, ELEMENT_C, Result.NoException));
			// IndexedListADT
			printTest("A_set0B_B_testAddAtIndexNeg1B", testAddAtIndex(A_set0B_B(), -1, ELEMENT_A, Result.IndexOutOfBounds));
			printTest("A_set0B_B_testAddAtIndex0A", testAddAtIndex(A_set0B_B(), 0, ELEMENT_A, Result.NoException));
			printTest("A_set0B_B_testAddAtIndex1A", testAddAtIndex(A_set0B_B(), 1, ELEMENT_A, Result.NoException));
			printTest("A_set0B_B_testSetNeg1B", testSet(A_set0B_B(), -1, ELEMENT_A, Result.IndexOutOfBounds));
			printTest("A_set0B_B_testSet0A", testSet(A_set0B_B(), 0, ELEMENT_A, Result.NoException));
			printTest("A_set0B_B_testSet1A", testSet(A_set0B_B(), 1, ELEMENT_A, Result.IndexOutOfBounds));
			printTest("A_set0B_B_testGetNeg1", testGet(A_set0B_B(), -1, null, Result.IndexOutOfBounds));
			printTest("A_set0B_B_testGet0", testGet(A_set0B_B(), 0, ELEMENT_B, Result.MatchingValue));
			printTest("A_set0B_B_testGet1", testGet(A_set0B_B(), 1, ELEMENT_B, Result.IndexOutOfBounds));
			printTest("A_set0B_B_testIndexOfB", testIndexOf(A_set0B_B(), ELEMENT_B, 0));
			printTest("A_set0B_B_testIndexOfA", testIndexOf(A_set0B_B(), ELEMENT_A, -1));
			printTest("A_set0B_B_testRemoveNeg1", testRemoveIndex(A_set0B_B(), -1, null, Result.IndexOutOfBounds));
			printTest("A_set0B_B_testRemove0", testRemoveIndex(A_set0B_B(), 0, ELEMENT_B, Result.MatchingValue));
			printTest("A_set0B_B_testRemove1", testRemoveIndex(A_set0B_B(), 1, null, Result.IndexOutOfBounds));
			// Iterator
			printTest("A_set0B_B_testIterator", testIterator(A_set0B_B(), Result.NoException));
			printTest("A_set0B_B_testIteratorHasNext", testIteratorHasNext(A_set0B_B().iterator(), Result.True));
			printTest("A_set0B_B_testIteratorNext", testIteratorNext(A_set0B_B().iterator(), ELEMENT_B, Result.MatchingValue));
			printTest("A_set0B_B_testIteratorRemove", testIteratorRemove(A_set0B_B().iterator(), Result.IllegalState));
			printTest("A_set0B_B_iteratorNext_testIteratorHasNext", testIteratorHasNext(iteratorAfterNext(A_set0B_B(), 1), Result.False));
			printTest("A_set0B_B_iteratorNext_testIteratorNext", testIteratorNext(iteratorAfterNext(A_set0B_B(), 1), null, Result.NoSuchElement));
			printTest("A_set0B_B_iteratorNext_testIteratorRemove", testIteratorRemove(iteratorAfterNext(A_set0B_B(), 1), Result.NoException));
			printTest("A_set0B_B_iteratorNextRemove_testIteratorHasNext", testIteratorHasNext(iteratorAfterRemove(iteratorAfterNext(A_set0B_B(), 1)), Result.False));
			printTest("A_set0B_B_iteratorNextRemove_testIteratorNext", testIteratorNext(iteratorAfterRemove(iteratorAfterNext(A_set0B_B(), 1)), null, Result.NoSuchElement));
			printTest("A_set0B_B_iteratorNextRemove_testIteratorRemove", testIteratorRemove(iteratorAfterRemove(iteratorAfterNext(A_set0B_B(), 1)), Result.IllegalState));
			printTest("A_set0B_B_iteratorTestRealRemove",testIteratorRealRemove(A_set0B_B(), 1, "[]", Result.MatchingValue));
			//ListIterator
			if(newList().getClass()==testDLL().getClass()){
			printTest("A_set0B_B_testListIteratorUnIndexed", testListIteratorUnIndexed(A_set0B_B(), Result.NoException));
			printTest("A_set0B_B_testListIteratorIndexedNeg1", testListIteratorIndexed(A_set0B_B(), -1, Result.IndexOutOfBounds));
			printTest("A_set0B_B_testListIteratorIndexed0", testListIteratorIndexed(A_set0B_B(), 0, Result.NoException));
			printTest("A_set0B_B_testListIteratorIndexed1", testListIteratorIndexed(A_set0B_B(), 1, Result.NoException));
			printTest("A_set0B_B_testListIteratorIndexed2", testListIteratorIndexed(A_set0B_B(), 2, Result.IndexOutOfBounds));
			
			printTest("A_set0B_B_testListIteratorHasNext", testListIteratorHasNext(A_set0B_B(),null, null, Result.True));
			printTest("A_set0B_B_testListIteratorNext", testListIteratorNext(A_set0B_B(),null, null, ELEMENT_B, Result.MatchingValue));
			printTest("A_set0B_B_testListIteratorNextIndex", testListIteratorNextIndex(A_set0B_B(),null, null, 0, Result.MatchingValue));
			printTest("A_set0B_B_testListIteratorHasPrevious", testListIteratorHasPrevious(A_set0B_B(),null, null, Result.False));
			printTest("A_set0B_B_testListIteratorPrevious", testListIteratorPrevious(A_set0B_B(),null, null, null, Result.NoSuchElement));
			printTest("A_set0B_B_testListIteratorPreviousIndex", testListIteratorPreviousIndex(A_set0B_B(),null, null, -1, Result.MatchingValue));
			printTest("A_set0B_B_testListIteratorRemove", testListIteratorRemove(A_set0B_B(),null, null, Result.IllegalState));
			printTest("A_set0B_B_testListIteratorRealRemove", testListIteratorRealRemoveAfterNext(A_set0B_B(), null, 0, "[2]", Result.MatchingValue));
			printTest("A_set0B_B_testListIteratorSet", testListIteratorSet(A_set0B_B(), null,null, ELEMENT_D, Result.IllegalState));
			printTest("A_set0B_B_testListIteratorRealSet", testListIteratorRealSetAfterNext(A_set0B_B(), null, 0, ELEMENT_D, "[2]", Result.MatchingValue));
			printTest("A_set0B_B_testListIteratorAdd", testListIteratorAdd(A_set0B_B(),null, null, ELEMENT_D, Result.NoException));
			printTest("A_set0B_B_testListIteratorRealAdd", testListIteratorRealAddAfterNext(A_set0B_B(), null, 0, ELEMENT_D, "[4, 2]", Result.MatchingValue));
			
			printTest("A_set0B_B_testListIteratorHasNextAfterNext", testListIteratorHasNext(null, getListIteratorAfterCall(A_set0B_B(), "next", null, null), null, Result.False));
			printTest("A_set0B_B_testListIteratorNextAfterNext", testListIteratorNext(null,getListIteratorAfterCall(A_set0B_B(), "next", null, null),null, null, Result.NoSuchElement));
			printTest("A_set0B_B_testListIteratorNextIndexAfterNext", testListIteratorNextIndex(null,getListIteratorAfterCall(A_set0B_B(), "next", null, null),null, 1, Result.MatchingValue));
			printTest("A_set0B_B_testListIteratorHasPreviousAfterNext", testListIteratorHasPrevious(null,getListIteratorAfterCall(A_set0B_B(), "next", null, null),null, Result.True));
			printTest("A_set0B_B_testListIteratorPreviousAfterNext", testListIteratorPrevious(null, getListIteratorAfterCall(A_set0B_B(), "next", null, null), null, ELEMENT_B, Result.MatchingValue));
			printTest("A_set0B_B_testListIteratorPreviousIndexAfterNext", testListIteratorPreviousIndex(null, getListIteratorAfterCall(A_set0B_B(), "next", null, null), null, 0, Result.MatchingValue));
			printTest("A_set0B_B_testListIteratorRemoveAfterNext", testListIteratorRemove(null, getListIteratorAfterCall(A_set0B_B(), "next", null, null), null, Result.NoException));
			printTest("A_set0B_B_testListIteratorRealRemoveAfterNext", testListIteratorRealRemoveAfterNext(A_set0B_B(), null, 1, "[]", Result.MatchingValue));
			printTest("A_set0B_B_testListIteratorSetAfterNext", testListIteratorSet(null, getListIteratorAfterCall(A_set0B_B(), "next", null, null), null, ELEMENT_D, Result.NoException));
			printTest("A_set0B_B_testListIteratorRealSetAfterNext", testListIteratorRealSetAfterNext(A_set0B_B(), null, 1, ELEMENT_D, "[4]", Result.MatchingValue));
			printTest("A_set0B_B_testListIteratorAddAfterNext", testListIteratorAdd(null, getListIteratorAfterCall(A_set0B_B(), "next", null, null), null, ELEMENT_D, Result.NoException));
			printTest("A_set0B_B_testListIteratorRealAddAfterNext", testListIteratorRealAddAfterNext(A_set0B_B(), null, 1, ELEMENT_D, "[2, 4]", Result.MatchingValue));
			
			printTest("A_set0B_B_testListIterator0HasNext", testListIteratorHasNext(A_set0B_B(), null, 0, Result.True));
			printTest("A_set0B_B_testListIterator0Next", testListIteratorNext(A_set0B_B(), null, 0, ELEMENT_B, Result.MatchingValue));
			printTest("A_set0B_B_testListIterator0NextIndex", testListIteratorNextIndex(A_set0B_B(), null, 0, 0, Result.MatchingValue));
			printTest("A_set0B_B_testListIterator0HasPrevious", testListIteratorHasPrevious(A_set0B_B(), null, 0, Result.False));
			printTest("A_set0B_B_testListIterator0Previous", testListIteratorPrevious(A_set0B_B(), null, 0, null, Result.NoSuchElement));
			printTest("A_set0B_B_testListIterator0PreviousIndex", testListIteratorPreviousIndex(A_set0B_B(), null, 0, -1, Result.MatchingValue));
			printTest("A_set0B_B_testListIterator0Remove", testListIteratorRemove(A_set0B_B(), null, 0, Result.IllegalState));
			printTest("A_set0B_B_testListIterator0RealRemove", testListIteratorRealRemoveAfterNext(A_set0B_B(), 0, 0, "[2]", Result.MatchingValue));
			printTest("A_set0B_B_testListIterator0Set", testListIteratorSet(A_set0B_B(), null, 0, ELEMENT_D, Result.IllegalState));
			printTest("A_set0B_B_testListIterator0RealSet", testListIteratorRealSetAfterNext(A_set0B_B(), 0, 0, ELEMENT_D, "[2]", Result.MatchingValue));
			printTest("A_set0B_B_testListIterator0Add", testListIteratorAdd(A_set0B_B(), null, 0, ELEMENT_D, Result.NoException));
			printTest("A_set0B_B_testListIterator0RealAdd", testListIteratorRealAddAfterNext(A_set0B_B(), 0, 0, ELEMENT_D, "[4, 2]", Result.MatchingValue));
			
			printTest("A_set0B_B_testListIterator0HasNextAfterNext", testListIteratorHasNext(null, getListIteratorAfterCall(A_set0B_B(), "next", 0, null), null, Result.False));
			printTest("A_set0B_B_testListIterator0NextAfterNext", testListIteratorNext(null, getListIteratorAfterCall(A_set0B_B(), "next", 0, null), null, null, Result.NoSuchElement));
			printTest("A_set0B_B_testListIterator0NextIndexAfterNext", testListIteratorNextIndex(null, getListIteratorAfterCall(A_set0B_B(), "next", 0, null), null, 1, Result.MatchingValue));
			printTest("A_set0B_B_testListIterator0HasPreviousAfterNext", testListIteratorHasPrevious(null, getListIteratorAfterCall(A_set0B_B(), "next", 0, null), null, Result.True));
			printTest("A_set0B_B_testListIterator0PreviousAfterNext", testListIteratorPrevious(null, getListIteratorAfterCall(A_set0B_B(), "next", 0, null), null, ELEMENT_B, Result.MatchingValue));
			printTest("A_set0B_B_testListIterator0PreviousIndexAfterNext", testListIteratorPreviousIndex(null, getListIteratorAfterCall(A_set0B_B(), "next", 0, null), null, 0, Result.MatchingValue));
			printTest("A_set0B_B_testListIterator0RemoveAfterNext", testListIteratorRemove(null, getListIteratorAfterCall(A_set0B_B(), "next", 0, null), null, Result.NoException));
			printTest("A_set0B_B_testListIterator0RealRemoveAfterNext", testListIteratorRealRemoveAfterNext(A_set0B_B(), 0, 1, "[]", Result.MatchingValue));
			printTest("A_set0B_B_testListIterator0SetAfterNext", testListIteratorSet(null, getListIteratorAfterCall(A_set0B_B(), "next", 0, null), null, ELEMENT_D, Result.NoException));
			printTest("A_set0B_B_testListIterator0RealSetAfterNext", testListIteratorRealSetAfterNext(A_set0B_B(), 0, 1, ELEMENT_D, "[4]", Result.MatchingValue));
			printTest("A_set0B_B_testListIterator0AddAfterNext", testListIteratorAdd(null, getListIteratorAfterCall(A_set0B_B(), "next", 0, null), null, ELEMENT_D, Result.NoException));
			printTest("A_set0B_B_testListIterator0RealAddAfterNext", testListIteratorRealAddAfterNext(A_set0B_B(), 0, 1, ELEMENT_D, "[2, 4]", Result.MatchingValue));
			
			printTest("A_set0B_B_testListIterator1HasNext", testListIteratorHasNext(A_set0B_B(), null, 1, Result.False));
			printTest("A_set0B_B_testListIterator1Next", testListIteratorNext(A_set0B_B(), null, 1, null, Result.NoSuchElement));
			printTest("A_set0B_B_testListIterator1NextIndex", testListIteratorNextIndex(A_set0B_B(), null, 1, 1, Result.MatchingValue));
			printTest("A_set0B_B_testListIterator1HasPrevious", testListIteratorHasPrevious(A_set0B_B(), null, 1, Result.True));
			printTest("A_set0B_B_testListIterator1Previous", testListIteratorPrevious(A_set0B_B(), null, 1, ELEMENT_B, Result.MatchingValue));
			printTest("A_set0B_B_testListIterator1PreviousIndex", testListIteratorPreviousIndex(A_set0B_B(), null, 1, 0, Result.MatchingValue));
			printTest("A_set0B_B_testListIterator1Remove", testListIteratorRemove(A_set0B_B(), null, 1, Result.IllegalState));
			printTest("A_set0B_B_testListIterator1RealRemove", testListIteratorRealRemoveAfterNext(A_set0B_B(), 1, 0, "[2]", Result.MatchingValue));
			printTest("A_set0B_B_testListIterator1Set", testListIteratorSet(A_set0B_B(), null, 1, ELEMENT_D, Result.IllegalState));
			printTest("A_set0B_B_testListIterator1RealSet", testListIteratorRealSetAfterNext(A_set0B_B(), 1, 0, ELEMENT_D, "[2]", Result.MatchingValue));
			printTest("A_set0B_B_testListIterator1Add", testListIteratorAdd(A_set0B_B(), null, 1, ELEMENT_D, Result.NoException));
			printTest("A_set0B_B_testListIterator1RealAdd", testListIteratorRealAddAfterNext(A_set0B_B(), 1, 0, ELEMENT_D, "[2, 4]", Result.MatchingValue));
			
			printTest("A_set0B_B_testListIterator1HasNextAfterPrev", testListIteratorHasNext(null, getListIteratorAfterCall(A_set0B_B(), "previous", 1, null), 1, Result.True));
			printTest("A_set0B_B_testListIterator1NextAfterPrev", testListIteratorNext(null, getListIteratorAfterCall(A_set0B_B(), "previous", 1, null), 1, ELEMENT_B, Result.MatchingValue));
			printTest("A_set0B_B_testListIterator1NextIndexAfterPrev", testListIteratorNextIndex(null, getListIteratorAfterCall(A_set0B_B(), "previous", 1, null), 1, 0, Result.MatchingValue));
			printTest("A_set0B_B_testListIterator1HasPreviousAfterPrev", testListIteratorHasPrevious(null, getListIteratorAfterCall(A_set0B_B(), "previous", 1, null), 1, Result.False));
			printTest("A_set0B_B_testListIterator1PreviousAfterPrev", testListIteratorPrevious(null, getListIteratorAfterCall(A_set0B_B(), "previous", 1, null), 1, null, Result.NoSuchElement));
			printTest("A_set0B_B_testListIterator1PreviousIndexAfterPrev", testListIteratorPreviousIndex(null, getListIteratorAfterCall(A_set0B_B(), "previous", 1, null), 1, -1, Result.MatchingValue));
			printTest("A_set0B_B_testListIterator1RemoveAfterPrev", testListIteratorRemove(null, getListIteratorAfterCall(A_set0B_B(), "previous", 1, null), 1, Result.NoException));
			printTest("A_set0B_B_testListIterator1RealRemoveAfterPrev", testListIteratorRealRemoveAfterPrev(A_set0B_B(), 1, 1, "[]", Result.MatchingValue));
			printTest("A_set0B_B_testListIterator1SetAfterPrev", testListIteratorSet(null, getListIteratorAfterCall(A_set0B_B(), "previous", 1, null), 1, ELEMENT_D, Result.NoException));
			printTest("A_set0B_B_testListIterator1RealSetAfterPrev", testListIteratorRealSetAfterPrev(A_set0B_B(), 1, 1, ELEMENT_D, "[4]", Result.MatchingValue));
			printTest("A_set0B_B_testListIterator1AddAfterPrev", testListIteratorAdd(null, getListIteratorAfterCall(A_set0B_B(), "previous", 1, null), 1, ELEMENT_D, Result.NoException));
			printTest("A_set0B_B_testListIterator1RealAddAfterPrev", testListIteratorRealAddAfterPrev(A_set0B_B(), 1, 1, ELEMENT_D, "[4, 2]", Result.MatchingValue));
			}
		} catch (Exception e) {
			System.out.printf("***UNABLE TO RUN/COMPLETE %s***\n", "test_A_set0B_B");
			e.printStackTrace();
		}
	}
	
	//////////////////////////////////////
	// SCENARIO: [A] -> add(0,B) -> [B,A]
	//  XXX SCENARIO 14
	//////////////////////////////////////
	private ListADT<Integer> A_add0B_BA() {
		// cast to UnorderedListADT to make addToFront() available
		IndexedListADT<Integer> list = (IndexedListADT<Integer>) newList(); 
		list.add(0,ELEMENT_A);
		list.add(0, ELEMENT_B);
		return list;
	}
	private void test_A_add0B_BA(){
		try {
			// ListADT
			printTest("A_add0B_BA_testRemoveFirst", testRemoveFirst(A_add0B_BA(), ELEMENT_B, Result.MatchingValue));
			printTest("A_add0B_BA_testRemoveLast", testRemoveLast(A_add0B_BA(), ELEMENT_A, Result.MatchingValue));
			printTest("A_add0B_BA_testRemoveA", testRemoveElement(A_add0B_BA(), ELEMENT_A, Result.MatchingValue));
			printTest("A_add0B_BA_testRemoveB", testRemoveElement(A_add0B_BA(), ELEMENT_B, Result.MatchingValue));
			printTest("A_add0B_BA_testRemoveC", testRemoveElement(A_add0B_BA(), ELEMENT_C, Result.ElementNotFound));
			printTest("A_add0B_BA_testFirst", testFirst(A_add0B_BA(), ELEMENT_B, Result.MatchingValue));
			printTest("A_add0B_BA_testLast", testLast(A_add0B_BA(), ELEMENT_A, Result.MatchingValue));
			printTest("A_add0B_BA_testContainsA", testContains(A_add0B_BA(), ELEMENT_A, Result.True));
			printTest("A_add0B_BA_testContainsB", testContains(A_add0B_BA(), ELEMENT_B, Result.True));
			printTest("A_add0B_BA_testContainsC", testContains(A_add0B_BA(), ELEMENT_C, Result.False));
			printTest("A_add0B_BA_testIsEmpty", testIsEmpty(A_add0B_BA(), Result.False));
			printTest("A_add0B_BA_testSize", testSize(A_add0B_BA(), 2));
			printTest("A_add0B_BA_testToString", testToString(A_add0B_BA(), Result.ValidString));
			// UnorderedListADT
			printTest("A_add0B_BA_testAddToFrontA", testAddToFront(A_add0B_BA(), ELEMENT_C, Result.NoException));
			printTest("A_add0B_BA_testAddToRearA", testAddToRear(A_add0B_BA(), ELEMENT_C, Result.NoException));
			printTest(	"A_add0B_BA_testAddAfterAC", testAddAfter(A_add0B_BA(), ELEMENT_A, ELEMENT_C, Result.NoException));
			printTest(	"A_add0B_BA_testAddAfterBC", testAddAfter(A_add0B_BA(), ELEMENT_B, ELEMENT_C, Result.NoException));
			printTest(	"A_add0B_BA_testAddAfterDC", testAddAfter(A_add0B_BA(), ELEMENT_D, ELEMENT_C, Result.ElementNotFound));
			// IndexedListADT
			printTest("A_add0B_BA_testAddAtIndexNeg1", testAddAtIndex(A_add0B_BA(), -1, null, Result.IndexOutOfBounds));
			printTest("A_add0B_BA_testAddAtIndex0", testAddAtIndex(A_add0B_BA(), 0, ELEMENT_C, Result.NoException));
			printTest("A_add0B_BA_testAddAtIndex1", testAddAtIndex(A_add0B_BA(), 1, ELEMENT_C, Result.NoException));
			printTest("A_add0B_BA_testAddAtIndex2", testAddAtIndex(A_add0B_BA(), 2, ELEMENT_C, Result.NoException));
			printTest("A_add0B_BA_testAddAtIndex3", testAddAtIndex(A_add0B_BA(), 3, null, Result.IndexOutOfBounds));
			printTest("A_add0B_BA_testSetNeg1C", testSet(A_add0B_BA(), -1, null, Result.IndexOutOfBounds));
			printTest("A_add0B_BA_testSet0C", testSet(A_add0B_BA(), 0, ELEMENT_C, Result.NoException));
			printTest("A_add0B_BA_testSet1C", testSet(A_add0B_BA(), 1, ELEMENT_C, Result.NoException));
			printTest("A_add0B_BA_testSet2C", testSet(A_add0B_BA(), 2, null, Result.IndexOutOfBounds));
			printTest("A_add0B_BA_testAddC", testAdd(A_add0B_BA(), ELEMENT_C, Result.NoException));
			printTest("A_add0B_BA_testGetNeg1", testGet(A_add0B_BA(), -1, null, Result.IndexOutOfBounds));
			printTest("A_add0B_BA_testGet0", testGet(A_add0B_BA(), 0, ELEMENT_B , Result.MatchingValue));
			printTest("A_add0B_BA_testGet1", testGet(A_add0B_BA(), 1, ELEMENT_A , Result.MatchingValue));
			printTest("A_add0B_BA_testGet2", testGet(A_add0B_BA(), 2, null, Result.IndexOutOfBounds));
			printTest("A_add0B_BA_testIndexOfB", testIndexOf(A_add0B_BA(), ELEMENT_B, 0));
			printTest("A_add0B_BA_testIndexOfA", testIndexOf(A_add0B_BA(), ELEMENT_A, 1));
			printTest("A_add0B_BA_testIndexOfC", testIndexOf(A_add0B_BA(), ELEMENT_C, -1));
			printTest("A_add0B_BA_testRemoveNeg1", testRemoveIndex(A_add0B_BA(), -1, null, Result.IndexOutOfBounds));
			printTest("A_add0B_BA_testRemove0", testRemoveIndex(A_add0B_BA(), 0, ELEMENT_B, Result.MatchingValue));
			printTest("A_add0B_BA_testRemove1", testRemoveIndex(A_add0B_BA(), 1, ELEMENT_A, Result.MatchingValue));
			printTest("A_add0B_BA_testRemove2", testRemoveIndex(A_add0B_BA(), 2, null, Result.IndexOutOfBounds));
			// Iterator
			printTest("A_add0B_BA_testIterator", testIterator(A_add0B_BA(), Result.NoException));
			printTest("A_add0B_BA_testIteratorHasNext", testIteratorHasNext(A_add0B_BA().iterator(), Result.True));
			printTest("A_add0B_BA_testIteratorNext", testIteratorNext(A_add0B_BA().iterator(), ELEMENT_B, Result.MatchingValue));
			printTest("A_add0B_BA_testIteratorRemove", testIteratorRemove(A_add0B_BA().iterator(), Result.IllegalState));
			printTest("A_add0B_BA_testIteratorHasNextAfterNext", testIteratorHasNext(iteratorAfterNext(A_add0B_BA(),1), Result.True));
			printTest("A_add0B_BA_testIteratorNextAfterNext", testIteratorNext(iteratorAfterNext(A_add0B_BA(),1), ELEMENT_A, Result.MatchingValue));
			printTest("A_add0B_BA_testIteratorRemoveAfterNext", testIteratorRemove(iteratorAfterNext(A_add0B_BA(),1), Result.NoException));
			printTest("A_add0B_BA_testIteratorHasNextAfterNext2", testIteratorHasNext(iteratorAfterNext(A_add0B_BA(),2), Result.False));
			printTest("A_add0B_BA_testIteratorNextAfterNext2", testIteratorNext(iteratorAfterNext(A_add0B_BA(),2), null, Result.NoSuchElement));
			printTest("A_add0B_BA_testIteratorRemoveAfterNext2", testIteratorRemove(iteratorAfterNext(A_add0B_BA(),2), Result.NoException));
			printTest("A_add0B_BA_testIteratorNextAfterRemoveNext", testIteratorNext(iteratorAfterRemove(iteratorAfterNext(A_add0B_BA(),1)),ELEMENT_A,  Result.MatchingValue));
			printTest("A_add0B_BA_testIteratorNextAfterRemoveNext2", testIteratorNext(iteratorAfterRemove(iteratorAfterNext(A_add0B_BA(),2)),null,  Result.NoSuchElement));
			printTest("A_add0B_BA_testIteratorHasNextAfterRemoveNext",testIteratorHasNext(iteratorAfterRemove(iteratorAfterNext(A_add0B_BA(),1)),Result.True));
			printTest("A_add0B_BA_testIteratorHasNextAfterRemoveNext2",testIteratorHasNext(iteratorAfterRemove(iteratorAfterNext(A_add0B_BA(),2)),Result.False));
			printTest("A_add0B_BA_iteratorTestRealRemove",testIteratorRealRemove(A_add0B_BA(), 1, "[1]", Result.MatchingValue));
			printTest("A_add0B_BA_iteratorTestRealRemove2",testIteratorRealRemove(A_add0B_BA(), 2, "[2]", Result.MatchingValue));
		} catch (Exception e) {
			System.out.printf("***UNABLE TO RUN/COMPLETE %s***\n", "test_A_add0B_BA");
			e.printStackTrace();
		}
	}
	
	//////////////////////////////////////
	// SCENARIO: [A] -> add(1,B) -> [A,B]
	//  XXX SCENARIO 15
	//////////////////////////////////////
	private ListADT<Integer> A_add1B_AB() {
		// cast to UnorderedListADT to make addToFront() available
		IndexedListADT<Integer> list = (IndexedListADT<Integer>) newList(); 
		list.add(0,ELEMENT_A);
		list.add(1, ELEMENT_B);
		return list;
	}
	private void test_A_add1B_AB(){
		try {
			// ListADT
			printTest("A_add1B_AB_testRemoveFirst", testRemoveFirst(A_add1B_AB(), ELEMENT_A, Result.MatchingValue));
			printTest("A_add1B_AB_testRemoveLast", testRemoveLast(A_add1B_AB(), ELEMENT_B, Result.MatchingValue));
			printTest("A_add1B_AB_testRemoveA", testRemoveElement(A_add1B_AB(), ELEMENT_A, Result.MatchingValue));
			printTest("A_add1B_AB_testRemoveB", testRemoveElement(A_add1B_AB(), ELEMENT_B, Result.MatchingValue));
			printTest("A_add1B_AB_testRemoveC", testRemoveElement(A_add1B_AB(), ELEMENT_C, Result.ElementNotFound));
			printTest("A_add1B_AB_testFirst", testFirst(A_add1B_AB(), ELEMENT_A, Result.MatchingValue));
			printTest("A_add1B_AB_testLast", testLast(A_add1B_AB(), ELEMENT_B, Result.MatchingValue));
			printTest("A_add1B_AB_testContainsA", testContains(A_add1B_AB(), ELEMENT_A, Result.True));
			printTest("A_add1B_AB_testContainsB", testContains(A_add1B_AB(), ELEMENT_B, Result.True));
			printTest("A_add1B_AB_testContainsC", testContains(A_add1B_AB(), ELEMENT_C, Result.False));
			printTest("A_add1B_AB_testIsEmpty", testIsEmpty(A_add1B_AB(), Result.False));
			printTest("A_add1B_AB_testSize", testSize(A_add1B_AB(), 2));
			printTest("A_add1B_AB_testToString", testToString(A_add1B_AB(), Result.ValidString));
			// UnorderedListADT
			printTest("A_add1B_AB_testAddToFrontA", testAddToFront(A_add1B_AB(), ELEMENT_C, Result.NoException));
			printTest("A_add1B_AB_testAddToRearA", testAddToRear(A_add1B_AB(), ELEMENT_C, Result.NoException));
			printTest(	"A_add1B_AB_testAddAfterAC", testAddAfter(A_add1B_AB(), ELEMENT_A, ELEMENT_C, Result.NoException));
			printTest(	"A_add1B_AB_testAddAfterBC", testAddAfter(A_add1B_AB(), ELEMENT_B, ELEMENT_C, Result.NoException));
			printTest(	"A_add1B_AB_testAddAfterDC", testAddAfter(A_add1B_AB(), ELEMENT_D, ELEMENT_C, Result.ElementNotFound));
			// IndexedListADT
			printTest("A_add1B_AB_testAddAtIndexNeg1", testAddAtIndex(A_add1B_AB(), -1, null, Result.IndexOutOfBounds));
			printTest("A_add1B_AB_testAddAtIndex0", testAddAtIndex(A_add1B_AB(), 0, ELEMENT_C, Result.NoException));
			printTest("A_add1B_AB_testAddAtIndex1", testAddAtIndex(A_add1B_AB(), 1, ELEMENT_C, Result.NoException));
			printTest("A_add1B_AB_testAddAtIndex2", testAddAtIndex(A_add1B_AB(), 2, ELEMENT_C, Result.NoException));
			printTest("A_add1B_AB_testAddAtIndex3", testAddAtIndex(A_add1B_AB(), 3, null, Result.IndexOutOfBounds));
			printTest("A_add1B_AB_testSetNeg1C", testSet(A_add1B_AB(), -1, null, Result.IndexOutOfBounds));
			printTest("A_add1B_AB_testSet0C", testSet(A_add1B_AB(), 0, ELEMENT_C, Result.NoException));
			printTest("A_add1B_AB_testSet1C", testSet(A_add1B_AB(), 1, ELEMENT_C, Result.NoException));
			printTest("A_add1B_AB_testSet2C", testSet(A_add1B_AB(), 2, null, Result.IndexOutOfBounds));
			printTest("A_add1B_AB_testAddC", testAdd(A_add1B_AB(), ELEMENT_C, Result.NoException));
			printTest("A_add1B_AB_testGetNeg1", testGet(A_add1B_AB(), -1, null, Result.IndexOutOfBounds));
			printTest("A_add1B_AB_testGet0", testGet(A_add1B_AB(), 0, ELEMENT_A , Result.MatchingValue));
			printTest("A_add1B_AB_testGet1", testGet(A_add1B_AB(), 1, ELEMENT_B , Result.MatchingValue));
			printTest("A_add1B_AB_testGet2", testGet(A_add1B_AB(), 2, null, Result.IndexOutOfBounds));
			printTest("A_add1B_AB_testIndexOfA", testIndexOf(A_add1B_AB(), ELEMENT_A, 0));
			printTest("A_add1B_AB_testIndexOfB", testIndexOf(A_add1B_AB(), ELEMENT_B, 1));
			printTest("A_add1B_AB_testIndexOfC", testIndexOf(A_add1B_AB(), ELEMENT_C, -1));
			printTest("A_add1B_AB_testRemoveNeg1", testRemoveIndex(A_add1B_AB(), -1, null, Result.IndexOutOfBounds));
			printTest("A_add1B_AB_testRemove0", testRemoveIndex(A_add1B_AB(), 0, ELEMENT_A, Result.MatchingValue));
			printTest("A_add1B_AB_testRemove1", testRemoveIndex(A_add1B_AB(), 1, ELEMENT_B, Result.MatchingValue));
			printTest("A_add1B_AB_testRemove2", testRemoveIndex(A_add1B_AB(), 2, null, Result.IndexOutOfBounds));
			// Iterator
			printTest("A_add1B_AB_testIterator", testIterator(A_add1B_AB(), Result.NoException));
			printTest("A_add1B_AB_testIteratorHasNext", testIteratorHasNext(A_add1B_AB().iterator(), Result.True));
			printTest("A_add1B_AB_testIteratorNext", testIteratorNext(A_add1B_AB().iterator(), ELEMENT_A, Result.MatchingValue));
			printTest("A_add1B_AB_testIteratorRemove", testIteratorRemove(A_add1B_AB().iterator(), Result.IllegalState));
			printTest("A_add1B_AB_testIteratorHasNextAfterNext", testIteratorHasNext(iteratorAfterNext(A_add1B_AB(),1), Result.True));
			printTest("A_add1B_AB_testIteratorNextAfterNext", testIteratorNext(iteratorAfterNext(A_add1B_AB(),1), ELEMENT_B, Result.MatchingValue));
			printTest("A_add1B_AB_testIteratorRemoveAfterNext", testIteratorRemove(iteratorAfterNext(A_add1B_AB(),1), Result.NoException));
			printTest("A_add1B_AB_testIteratorHasNextAfterNext2", testIteratorHasNext(iteratorAfterNext(A_add1B_AB(),2), Result.False));
			printTest("A_add1B_AB_testIteratorNextAfterNext2", testIteratorNext(iteratorAfterNext(A_add1B_AB(),2), null, Result.NoSuchElement));
			printTest("A_add1B_AB_testIteratorRemoveAfterNext2", testIteratorRemove(iteratorAfterNext(A_add1B_AB(),2), Result.NoException));
			printTest("A_add1B_AB_testIteratorNextAfterRemoveNext", testIteratorNext(iteratorAfterRemove(iteratorAfterNext(A_add1B_AB(),1)),ELEMENT_B,  Result.MatchingValue));
			printTest("A_add1B_AB_testIteratorNextAfterRemoveNext2", testIteratorNext(iteratorAfterRemove(iteratorAfterNext(A_add1B_AB(),2)),null,  Result.NoSuchElement));
			printTest("A_add1B_AB_testIteratorHasNextAfterRemoveNext",testIteratorHasNext(iteratorAfterRemove(iteratorAfterNext(A_add1B_AB(),1)),Result.True));
			printTest("A_add1B_AB_testIteratorHasNextAfterRemoveNext2",testIteratorHasNext(iteratorAfterRemove(iteratorAfterNext(A_add1B_AB(),2)),Result.False));
			printTest("A_add1B_AB_iteratorTestRealRemove",testIteratorRealRemove(A_add1B_AB(), 1, "[2]", Result.MatchingValue));
			printTest("A_add1B_AB_iteratorTestRealRemove2",testIteratorRealRemove(A_add1B_AB(), 2, "[1]", Result.MatchingValue));
			//ListIterator
			if(A_removeFirst_empty().getClass()==testDLL().getClass()){
			printTest("A_add1B_AB_testListIteratorUnIndexed", testListIteratorUnIndexed(A_add1B_AB(), Result.NoException));
			printTest("A_add1B_AB_testListIteratorIndexedNeg1", testListIteratorIndexed(A_add1B_AB(), -1, Result.IndexOutOfBounds));
			printTest("A_add1B_AB_testListIteratorIndexed0", testListIteratorIndexed(A_add1B_AB(), 0, Result.NoException));
			printTest("A_add1B_AB_testListIteratorIndexed1", testListIteratorIndexed(A_add1B_AB(), 1, Result.NoException));
			printTest("A_add1B_AB_testListIteratorIndexed2", testListIteratorIndexed(A_add1B_AB(), 2, Result.NoException));
			printTest("A_add1B_AB_testListIteratorIndexed3", testListIteratorIndexed(A_add1B_AB(), 3, Result.IndexOutOfBounds));
			
			printTest("A_add1B_AB_testListIteratorHasNext", testListIteratorHasNext(A_add1B_AB(),null, null, Result.True));
			printTest("A_add1B_AB_testListIteratorNext", testListIteratorNext(A_add1B_AB(),null, null, ELEMENT_A, Result.MatchingValue));
			printTest("A_add1B_AB_testListIteratorNextIndex", testListIteratorNextIndex(A_add1B_AB(),null, null, 0, Result.MatchingValue));
			printTest("A_add1B_AB_testListIteratorHasPrevious", testListIteratorHasPrevious(A_add1B_AB(),null, null, Result.False));
			printTest("A_add1B_AB_testListIteratorPrevious", testListIteratorPrevious(A_add1B_AB(),null, null, null, Result.NoSuchElement));
			printTest("A_add1B_AB_testListIteratorPreviousIndex", testListIteratorPreviousIndex(A_add1B_AB(),null, null, -1, Result.MatchingValue));
			printTest("A_add1B_AB_testListIteratorRemove", testListIteratorRemove(A_add1B_AB(),null, null, Result.IllegalState));
			printTest("A_add1B_AB_testListIteratorRealRemove", testListIteratorRealRemoveAfterNext(A_add1B_AB(), null, 0, "[1, 2]", Result.MatchingValue));
			printTest("A_add1B_AB_testListIteratorSet", testListIteratorSet(A_add1B_AB(), null,null, ELEMENT_D, Result.IllegalState));
			printTest("A_add1B_AB_testListIteratorRealSet", testListIteratorRealSetAfterNext(A_add1B_AB(), null, 0, ELEMENT_D, "[1, 2]", Result.MatchingValue));
			printTest("A_add1B_AB_testListIteratorAdd", testListIteratorAdd(A_add1B_AB(),null, null, ELEMENT_D, Result.NoException));
			printTest("A_add1B_AB_testListIteratorRealAdd", testListIteratorRealAddAfterNext(A_add1B_AB(), null, 0, ELEMENT_D, "[4, 1, 2]", Result.MatchingValue));
			
			printTest("A_add1B_AB_testListIteratorHasNextAfterNext", testListIteratorHasNext(null,getListIteratorAfterCall(A_add1B_AB(), "next", null, null), null, Result.True));
			printTest("A_add1B_AB_testListIteratorNextAfterNext", testListIteratorNext(null,getListIteratorAfterCall(A_add1B_AB(), "next", null, null), null, ELEMENT_B, Result.MatchingValue));
			printTest("A_add1B_AB_testListIteratorNextIndexAfterNext", testListIteratorNextIndex(null,getListIteratorAfterCall(A_add1B_AB(), "next", null, null), null, 1, Result.MatchingValue));
			printTest("A_add1B_AB_testListIteratorHasPreviousAfterNext", testListIteratorHasPrevious(null,getListIteratorAfterCall(A_add1B_AB(), "next", null, null), null, Result.True));
			printTest("A_add1B_AB_testListIteratorPreviousAfterNext", testListIteratorPrevious(null,getListIteratorAfterCall(A_add1B_AB(), "next", null, null), null, ELEMENT_A, Result.MatchingValue));
			printTest("A_add1B_AB_testListIteratorPreviousIndexAfterNext", testListIteratorPreviousIndex(null,getListIteratorAfterCall(A_add1B_AB(), "next", null, null), null, 0, Result.MatchingValue));
			printTest("A_add1B_AB_testListIteratorRemoveAfterNext", testListIteratorRemove(null,getListIteratorAfterCall(A_add1B_AB(), "next", null, null), null, Result.NoException));
			printTest("A_add1B_AB_testListIteratorRealRemoveAfterNext", testListIteratorRealRemoveAfterNext(A_add1B_AB(),null, 1, "[2]", Result.MatchingValue));
			printTest("A_add1B_AB_testListIteratorSetAfterNext", testListIteratorSet(null, getListIteratorAfterCall(A_add1B_AB(), "next", null, null),null, ELEMENT_D, Result.NoException));
			printTest("A_add1B_AB_testListIteratorRealSetAfterNext", testListIteratorRealSetAfterNext(A_add1B_AB(), null, 1, ELEMENT_D, "[4, 2]", Result.MatchingValue));
			printTest("A_add1B_AB_testListIteratorAddAfterNext", testListIteratorAdd(null,getListIteratorAfterCall(A_add1B_AB(), "next", null, null), null, ELEMENT_D, Result.NoException));
			printTest("A_add1B_AB_testListIteratorRealAddAfterNext", testListIteratorRealAddAfterNext(A_add1B_AB(), null, 1, ELEMENT_D, "[1, 4, 2]", Result.MatchingValue));
			
			printTest("A_add1B_AB_testListIteratorHasNextAfter2Next", testListIteratorHasNext(null,getListIteratorAfterCallItrItr(getListIteratorAfterCall(A_add1B_AB(), "next", null, null), "next", null, null), null, Result.False));
			printTest("A_add1B_AB_testListIteratorNextAfter2Next", testListIteratorNext(null,getListIteratorAfterCallItrItr(getListIteratorAfterCall(A_add1B_AB(), "next", null, null), "next", null, null), null, null, Result.NoSuchElement));
			printTest("A_add1B_AB_testListIteratorNextIndexAfter2Next", testListIteratorNextIndex(null,getListIteratorAfterCallItrItr(getListIteratorAfterCall(A_add1B_AB(), "next", null, null), "next", null, null), null, 2, Result.MatchingValue));
			printTest("A_add1B_AB_testListIteratorHasPreviousAfter2Next", testListIteratorHasPrevious(null,getListIteratorAfterCallItrItr(getListIteratorAfterCall(A_add1B_AB(), "next", null, null), "next", null, null), null, Result.True));
			printTest("A_add1B_AB_testListIteratorPreviousAfter2Next", testListIteratorPrevious(null,getListIteratorAfterCallItrItr(getListIteratorAfterCall(A_add1B_AB(), "next", null, null), "next", null, null), null, ELEMENT_B, Result.MatchingValue));
			printTest("A_add1B_AB_testListIteratorPreviousIndexAfter2Next", testListIteratorPreviousIndex(null,getListIteratorAfterCallItrItr(getListIteratorAfterCall(A_add1B_AB(), "next", null, null), "next", null, null), null, 1, Result.MatchingValue));
			printTest("A_add1B_AB_testListIteratorRemoveAfter2Next", testListIteratorRemove(null,getListIteratorAfterCallItrItr(getListIteratorAfterCall(A_add1B_AB(), "next", null, null), "next", null, null), null, Result.NoException));
			printTest("A_add1B_AB_testListIteratorRealRemoveAfter2Next", testListIteratorRealRemoveAfterNext(A_add1B_AB(),null, 2, "[1]", Result.MatchingValue));
			printTest("A_add1B_AB_testListIteratorSetAfter2Next", testListIteratorSet(null, getListIteratorAfterCallItrItr(getListIteratorAfterCall(A_add1B_AB(), "next", null, null), "next", null, null),null, ELEMENT_D, Result.NoException));
			printTest("A_add1B_AB_testListIteratorRealSetAfter2Next", testListIteratorRealSetAfterNext(A_add1B_AB(), null, 2, ELEMENT_D, "[1, 4]", Result.MatchingValue));
			printTest("A_add1B_AB_testListIteratorAddAfter2Next", testListIteratorAdd(null,getListIteratorAfterCallItrItr(getListIteratorAfterCall(A_add1B_AB(), "next", null, null), "next", null, null), null, ELEMENT_D, Result.NoException));
			printTest("A_add1B_AB_testListIteratorRealAddAfter2Next", testListIteratorRealAddAfterNext(A_add1B_AB(), null, 2, ELEMENT_D, "[1, 2, 4]", Result.MatchingValue));
			
			printTest("A_add1B_AB_testListIterator0HasNext", testListIteratorHasNext(A_add1B_AB(),null, 0, Result.True));
			printTest("A_add1B_AB_testListIterator0Next", testListIteratorNext(A_add1B_AB(),null, 0, ELEMENT_A, Result.MatchingValue));
			printTest("A_add1B_AB_testListIterator0NextIndex", testListIteratorNextIndex(A_add1B_AB(),null, 0, 0, Result.MatchingValue));
			printTest("A_add1B_AB_testListIterator0HasPrevious", testListIteratorHasPrevious(A_add1B_AB(),null, 0, Result.False));
			printTest("A_add1B_AB_testListIterator0Previous", testListIteratorPrevious(A_add1B_AB(),null, 0, null, Result.NoSuchElement));
			printTest("A_add1B_AB_testListIterator0PreviousIndex", testListIteratorPreviousIndex(A_add1B_AB(),null, 0, -1, Result.MatchingValue));
			printTest("A_add1B_AB_testListIterator0Remove", testListIteratorRemove(A_add1B_AB(),null, 0, Result.IllegalState));
			printTest("A_add1B_AB_testListIterator0RealRemove", testListIteratorRealRemoveAfterNext(A_add1B_AB(), 0, 0, "[1, 2]", Result.MatchingValue));
			printTest("A_add1B_AB_testListIterator0Set", testListIteratorSet(A_add1B_AB(), null,0, ELEMENT_D, Result.IllegalState));
			printTest("A_add1B_AB_testListIterator0RealSet", testListIteratorRealSetAfterNext(A_add1B_AB(), 0, 0, ELEMENT_D, "[1, 2]", Result.MatchingValue));
			printTest("A_add1B_AB_testListIterator0Add", testListIteratorAdd(A_add1B_AB(),null, 0, ELEMENT_D, Result.NoException));
			printTest("A_add1B_AB_testListIterator0RealAdd", testListIteratorRealAddAfterNext(A_add1B_AB(), 0, 0, ELEMENT_D, "[4, 1, 2]", Result.MatchingValue));
			
			printTest("A_add1B_AB_testListIterator0HasNextAfterNext", testListIteratorHasNext(null,getListIteratorAfterCall(A_add1B_AB(), "next", 0, null), 0, Result.True));
			printTest("A_add1B_AB_testListIterator0NextAfterNext", testListIteratorNext(null,getListIteratorAfterCall(A_add1B_AB(), "next", 0, null), 0, ELEMENT_B, Result.MatchingValue));
			printTest("A_add1B_AB_testListIterator0NextIndexAfterNext", testListIteratorNextIndex(null,getListIteratorAfterCall(A_add1B_AB(), "next", 0, null), 0, 1, Result.MatchingValue));
			printTest("A_add1B_AB_testListIterator0HasPreviousAfterNext", testListIteratorHasPrevious(null,getListIteratorAfterCall(A_add1B_AB(), "next", 0, null),0, Result.True));
			printTest("A_add1B_AB_testListIterator0PreviousAfterNext", testListIteratorPrevious(null,getListIteratorAfterCall(A_add1B_AB(), "next", 0, null), 0, ELEMENT_A, Result.MatchingValue));
			printTest("A_add1B_AB_testListIterator0PreviousIndexAfterNext", testListIteratorPreviousIndex(null,getListIteratorAfterCall(A_add1B_AB(), "next", 0, null), 0, 0, Result.MatchingValue));
			printTest("A_add1B_AB_testListIterator0RemoveAfterNext", testListIteratorRemove(null,getListIteratorAfterCall(A_add1B_AB(), "next", 0, null), 0, Result.NoException));
			printTest("A_add1B_AB_testListIterator0RealRemoveAfterNext", testListIteratorRealRemoveAfterNext(A_add1B_AB(),0, 1, "[2]", Result.MatchingValue));
			printTest("A_add1B_AB_testListIterator0SetAfterNext", testListIteratorSet(null, getListIteratorAfterCall(A_add1B_AB(), "next", 0, null),0, ELEMENT_D, Result.NoException));
			printTest("A_add1B_AB_testListIterator0RealSetAfterNext", testListIteratorRealSetAfterNext(A_add1B_AB(), 0, 1, ELEMENT_D, "[4, 2]", Result.MatchingValue));
			printTest("A_add1B_AB_testListIterator0AddAfterNext", testListIteratorAdd(null,getListIteratorAfterCall(A_add1B_AB(), "next", 0, null), 0, ELEMENT_D, Result.NoException));
			printTest("A_add1B_AB_testListIterator0RealAddAfterNext", testListIteratorRealAddAfterNext(A_add1B_AB(), 0, 1, ELEMENT_D, "[1, 4, 2]", Result.MatchingValue));
			
			printTest("A_add1B_AB_testListIterator0HasNextAfter2Next", testListIteratorHasNext(null,getListIteratorAfterCallItrItr(getListIteratorAfterCall(A_add1B_AB(), "next", 0, null), "next", null, null), 0, Result.False));
			printTest("A_add1B_AB_testListIterator0NextAfter2Next", testListIteratorNext(null,getListIteratorAfterCallItrItr(getListIteratorAfterCall(A_add1B_AB(), "next", 0, null), "next", null, null), 0, null, Result.NoSuchElement));
			printTest("A_add1B_AB_testListIterator0NextIndexAfter2Next", testListIteratorNextIndex(null,getListIteratorAfterCallItrItr(getListIteratorAfterCall(A_add1B_AB(), "next", 0, null), "next", null, null), 0, 2, Result.MatchingValue));
			printTest("A_add1B_AB_testListIterator0HasPreviousAfter2Next", testListIteratorHasPrevious(null,getListIteratorAfterCallItrItr(getListIteratorAfterCall(A_add1B_AB(), "next", 0, null), "next", null, null), 0, Result.True));
			printTest("A_add1B_AB_testListIterator0PreviousAfter2Next", testListIteratorPrevious(null,getListIteratorAfterCallItrItr(getListIteratorAfterCall(A_add1B_AB(), "next", 0, null), "next", null, null), 0, ELEMENT_B, Result.MatchingValue));
			printTest("A_add1B_AB_testListIterator0PreviousIndexAfter2Next", testListIteratorPreviousIndex(null,getListIteratorAfterCallItrItr(getListIteratorAfterCall(A_add1B_AB(), "next", 0, null), "next", null, null), 0, 1, Result.MatchingValue));
			printTest("A_add1B_AB_testListIterator0RemoveAfter2Next", testListIteratorRemove(null,getListIteratorAfterCallItrItr(getListIteratorAfterCall(A_add1B_AB(), "next", 0, null), "next", null, null), 0, Result.NoException));
			printTest("A_add1B_AB_testListIterator0RealRemoveAfter2Next", testListIteratorRealRemoveAfterNext(A_add1B_AB(),0, 2, "[1]", Result.MatchingValue));
			printTest("A_add1B_AB_testListIterator0SetAfter2Next", testListIteratorSet(null, getListIteratorAfterCallItrItr(getListIteratorAfterCall(A_add1B_AB(), "next", 0, null), "next", null, null),0, ELEMENT_D, Result.NoException));
			printTest("A_add1B_AB_testListIterator0RealSetAfter2Next", testListIteratorRealSetAfterNext(A_add1B_AB(), 0,2, ELEMENT_D, "[1, 4]", Result.MatchingValue));
			printTest("A_add1B_AB_testListIterator0AddAfter2Next", testListIteratorAdd(null,getListIteratorAfterCallItrItr(getListIteratorAfterCall(A_add1B_AB(), "next", 0, null), "next", null, null), 0, ELEMENT_D, Result.NoException));
			printTest("A_add1B_AB_testListIterator0RealAddAfter2Next", testListIteratorRealAddAfterNext(A_add1B_AB(), 0, 2, ELEMENT_D, "[1, 2, 4]", Result.MatchingValue));
	
			printTest("A_add1B_AB_testListIterator1HasNext", testListIteratorHasNext( A_add1B_AB(),null, 1, Result.True));
			printTest("A_add1B_AB_testListIterator1Next", testListIteratorNext( A_add1B_AB(),null, 1, ELEMENT_B, Result.MatchingValue));
			printTest("A_add1B_AB_testListIterator1NextIndex", testListIteratorNextIndex( A_add1B_AB(),null, 1, 1, Result.MatchingValue));
			printTest("A_add1B_AB_testListIterator1HasPrevious", testListIteratorHasPrevious( A_add1B_AB(),null, 1, Result.True));
			printTest("A_add1B_AB_testListIterator1Previous", testListIteratorPrevious( A_add1B_AB(),null, 1, ELEMENT_A, Result.MatchingValue));
			printTest("A_add1B_AB_testListIterator1PreviousIndex", testListIteratorPreviousIndex( A_add1B_AB(),null, 1, 0, Result.MatchingValue));
			printTest("A_add1B_AB_testListIterator1Remove", testListIteratorRemove( A_add1B_AB(),null, 1, Result.IllegalState));
			printTest("A_add1B_AB_testListIterator1RealRemove", testListIteratorRealRemoveAfterNext(A_add1B_AB(),0, 1, "[2]", Result.MatchingValue));
			printTest("A_add1B_AB_testListIterator1Set", testListIteratorSet( A_add1B_AB(),null,1, ELEMENT_D, Result.IllegalState));
			printTest("A_add1B_AB_testListIterator1RealSet", testListIteratorRealSetAfterNext(A_add1B_AB(), 0, 1, ELEMENT_D, "[4, 2]", Result.MatchingValue));
			printTest("A_add1B_AB_testListIterator1Add", testListIteratorAdd( A_add1B_AB(),null, 1, ELEMENT_D, Result.NoException));
			printTest("A_add1B_AB_testListIterator1RealAdd", testListIteratorRealAddAfterNext(A_add1B_AB(), 0, 1, ELEMENT_D, "[1, 4, 2]", Result.MatchingValue));
			
			printTest("A_add1B_AB_testListIterator1HasNextAfterNext", testListIteratorHasNext(null,getListIteratorAfterCall(A_add1B_AB(), "next", 1, null), 1, Result.False));
			printTest("A_add1B_AB_testListIterator1NextAfterNext", testListIteratorNext(null,getListIteratorAfterCall(A_add1B_AB(), "next", 1, null), 1, null, Result.NoSuchElement));
			printTest("A_add1B_AB_testListIterator1NextIndexAfterNext", testListIteratorNextIndex(null,getListIteratorAfterCall(A_add1B_AB(), "next", 1, null), 1, 2, Result.MatchingValue));
			printTest("A_add1B_AB_testListIterator1HasPreviousAfterNext", testListIteratorHasPrevious(null,getListIteratorAfterCall(A_add1B_AB(), "next", 1, null), 1, Result.True));
			printTest("A_add1B_AB_testListIterator1PreviousAfterNext", testListIteratorPrevious(null,getListIteratorAfterCall(A_add1B_AB(), "next", 1, null), 1, ELEMENT_B, Result.MatchingValue));
			printTest("A_add1B_AB_testListIterator1PreviousIndexAfterNext", testListIteratorPreviousIndex(null,getListIteratorAfterCall(A_add1B_AB(), "next", 1, null), 1, 1, Result.MatchingValue));
			printTest("A_add1B_AB_testListIterator1RemoveAfterNext", testListIteratorRemove(null,getListIteratorAfterCall(A_add1B_AB(), "next", 1, null), 1, Result.NoException));
			printTest("A_add1B_AB_testListIterator1RealRemoveAfterNext", testListIteratorRealRemoveAfterNext(A_add1B_AB(),1, 1, "[1]", Result.MatchingValue));
			printTest("A_add1B_AB_testListIterator1SetAfterNext", testListIteratorSet(null, getListIteratorAfterCall(A_add1B_AB(), "next", 1, null),1, ELEMENT_D, Result.NoException));
			printTest("A_add1B_AB_testListIterator1RealSetAfterNext", testListIteratorRealSetAfterNext(A_add1B_AB(), 1, 1, ELEMENT_D, "[1, 4]", Result.MatchingValue));
			printTest("A_add1B_AB_testListIterator1AddAfterNext", testListIteratorAdd(null,getListIteratorAfterCall(A_add1B_AB(), "next", 1, null), null, ELEMENT_D, Result.NoException));
			printTest("A_add1B_AB_testListIterator1RealAddAfterNext", testListIteratorRealAddAfterNext(A_add1B_AB(), 1, 1, ELEMENT_D, "[1, 2, 4]", Result.MatchingValue));
			
			printTest("A_add1B_AB_testListIterator1HasNextAfterPrev", testListIteratorHasNext(null,getListIteratorAfterCall(A_add1B_AB(), "previous", 1, null), null, Result.True));
			printTest("A_add1B_AB_testListIterator1NextAfterPrev", testListIteratorNext(null,getListIteratorAfterCall(A_add1B_AB(), "previous", 1, null), null, ELEMENT_A, Result.MatchingValue));
			printTest("A_add1B_AB_testListIterator1NextIndexAfterPrev", testListIteratorNextIndex(null,getListIteratorAfterCall(A_add1B_AB(), "previous", 1, null), null, 0, Result.MatchingValue));
			printTest("A_add1B_AB_testListIterator1HasPreviousAfterPrev", testListIteratorHasPrevious(null,getListIteratorAfterCall(A_add1B_AB(), "previous", 1, null), null, Result.False));
			printTest("A_add1B_AB_testListIterator1PreviousAfterPrev", testListIteratorPrevious(null,getListIteratorAfterCall(A_add1B_AB(), "previous", 1, null), null, null, Result.NoSuchElement));
			printTest("A_add1B_AB_testListIterator1PreviousIndexAfterPrev", testListIteratorPreviousIndex(null,getListIteratorAfterCall(A_add1B_AB(), "previous", 1, null), null, -1, Result.MatchingValue));
			printTest("A_add1B_AB_testListIterator1RemoveAfterPrev", testListIteratorRemove(null,getListIteratorAfterCall(A_add1B_AB(), "previous", 1, null), null, Result.NoException));
			printTest("A_add1B_AB_testListIterator1RealRemoveAfterPrev", testListIteratorRealRemoveAfterPrev(A_add1B_AB(), 1, 1, "[2]", Result.MatchingValue));
			printTest("A_add1B_AB_testListIterator1SetAfterPrev", testListIteratorSet(null, getListIteratorAfterCall(A_add1B_AB(), "previous", 1, null),null, ELEMENT_D, Result.NoException));
			printTest("A_add1B_AB_testListIterator1RealSetAfterPrev", testListIteratorRealSetAfterPrev(A_add1B_AB(), 1, 1, ELEMENT_D, "[4, 2]", Result.MatchingValue));
			printTest("A_add1B_AB_testListIterator1AddAfterPrev", testListIteratorAdd(null,getListIteratorAfterCall(A_add1B_AB(), "previous", 1, null), null, ELEMENT_D, Result.NoException));
			printTest("A_add1B_AB_testListIterator1RealAddAfterPrev", testListIteratorRealAddAfterPrev(A_add1B_AB(), 1, 1, ELEMENT_D, "[4, 1, 2]", Result.MatchingValue));
			
			printTest("A_add1B_AB_testListIterator1HasNextAfterPrevNext", testListIteratorHasNext(null,getListIteratorAfterCallItrItr(getListIteratorAfterCall(A_add1B_AB(), "previous", 1, null), "next", null, null), null, Result.True));
			printTest("A_add1B_AB_testListIterator1NextAfterPrevNext", testListIteratorNext(null,getListIteratorAfterCallItrItr(getListIteratorAfterCall(A_add1B_AB(), "previous", 1, null), "next", null, null), null, ELEMENT_B, Result.MatchingValue));
			printTest("A_add1B_AB_testListIterator1NextIndexAfterPrevNext", testListIteratorNextIndex(null,getListIteratorAfterCallItrItr(getListIteratorAfterCall(A_add1B_AB(), "previous", 1, null), "next", null, null), null, 1, Result.MatchingValue));
			printTest("A_add1B_AB_testListIterator1HasPreviousAfterPrevNext", testListIteratorHasPrevious(null,getListIteratorAfterCallItrItr(getListIteratorAfterCall(A_add1B_AB(), "previous", 1, null), "next", null, null), null, Result.True));
			printTest("A_add1B_AB_testListIterator1PreviousAfterPrevNext", testListIteratorPrevious(null,getListIteratorAfterCallItrItr(getListIteratorAfterCall(A_add1B_AB(), "previous", 1, null), "next", null, null), null, ELEMENT_A, Result.MatchingValue));
			printTest("A_add1B_AB_testListIterator1PreviousIndexAfterPrevNext", testListIteratorPreviousIndex(null,getListIteratorAfterCallItrItr(getListIteratorAfterCall(A_add1B_AB(), "previous", 1, null), "next", null, null), null, 0, Result.MatchingValue));
			printTest("A_add1B_AB_testListIterator1RemoveAfterPrevNext", testListIteratorRemove(null,getListIteratorAfterCallItrItr(getListIteratorAfterCall(A_add1B_AB(), "previous", 1, null), "next", null, null), null, Result.NoException));
			printTest("A_add1B_AB_testListIterator1RealRemoveAfterPrevNext", testListIteratorRealRemoveAfterNext(A_add1B_AB(),null, 1, "[2]", Result.MatchingValue));
			printTest("A_add1B_AB_testListIterator1SetAfterPrevNext", testListIteratorSet(null, getListIteratorAfterCallItrItr(getListIteratorAfterCall(A_add1B_AB(), "previous", 1, null), "next", null, null),null, ELEMENT_D, Result.NoException));
			printTest("A_add1B_AB_testListIterator1RealSetAfterPrevNext", testListIteratorRealSetAfterNext(A_add1B_AB(), null, 1, ELEMENT_D, "[4, 2]", Result.MatchingValue));
			printTest("A_add1B_AB_testListIterator1AddAfterPrevNext", testListIteratorAdd(null,getListIteratorAfterCallItrItr(getListIteratorAfterCall(A_add1B_AB(), "previous", 1, null), "next", null, null), null, ELEMENT_D, Result.NoException));
			printTest("A_add1B_AB_testListIterator1RealAddAfterPrevNext", testListIteratorRealAddAfterNext(A_add1B_AB(), null, 1, ELEMENT_D, "[1, 4, 2]", Result.MatchingValue));
		
			printTest("A_add1B_AB_testListIterator1HasNextAfterPrev2Next", testListIteratorHasNext(null,getListIteratorAfterCallItrItr(getListIteratorAfterCallItrItr(getListIteratorAfterCall(A_add1B_AB(), "previous", 1, null), "next", null, null), "next", null, null), 1, Result.False));
			printTest("A_add1B_AB_testListIterator1NextAfterPrev2Next", testListIteratorNext(null,getListIteratorAfterCallItrItr(getListIteratorAfterCallItrItr(getListIteratorAfterCall(A_add1B_AB(), "previous", 1, null), "next", null, null), "next", null, null), 1, null, Result.NoSuchElement));
			printTest("A_add1B_AB_testListIterator1NextIndexAfterPrev2Next", testListIteratorNextIndex(null,getListIteratorAfterCallItrItr(getListIteratorAfterCallItrItr(getListIteratorAfterCall(A_add1B_AB(), "previous", 1, null), "next", null, null), "next", null, null), 1, 2, Result.MatchingValue));
			printTest("A_add1B_AB_testListIterator1HasPreviousAfterPrev2Next", testListIteratorHasPrevious(null,getListIteratorAfterCallItrItr(getListIteratorAfterCallItrItr(getListIteratorAfterCall(A_add1B_AB(), "previous", 1, null), "next", null, null), "next", null, null), 1, Result.True));
			printTest("A_add1B_AB_testListIterator1PreviousAfterPrev2Next", testListIteratorPrevious(null,getListIteratorAfterCallItrItr(getListIteratorAfterCallItrItr(getListIteratorAfterCall(A_add1B_AB(), "previous", 1, null), "next", null, null), "next", null, null), 1, ELEMENT_B, Result.MatchingValue));
			printTest("A_add1B_AB_testListIterator1PreviousIndexAfterPrev2Next", testListIteratorPreviousIndex(null,getListIteratorAfterCallItrItr(getListIteratorAfterCallItrItr(getListIteratorAfterCall(A_add1B_AB(), "previous", 1, null), "next", null, null), "next", null, null), 1, 1, Result.MatchingValue));
			printTest("A_add1B_AB_testListIterator1RemoveAfterPrev2Next", testListIteratorRemove(null,getListIteratorAfterCallItrItr(getListIteratorAfterCallItrItr(getListIteratorAfterCall(A_add1B_AB(), "previous", 1, null), "next", null, null), "next", null, null), 1, Result.NoException));
			printTest("A_add1B_AB_testListIterator1RealRemoveAfterPrev2Next", testListIteratorRealRemoveAfterNext(A_add1B_AB(),1, 1, "[1]", Result.MatchingValue));
			printTest("A_add1B_AB_testListIterator1SetAfterPrev2Next", testListIteratorSet(null, getListIteratorAfterCallItrItr(getListIteratorAfterCallItrItr(getListIteratorAfterCall(A_add1B_AB(), "previous", 1, null), "next", null, null), "next", null, null),1, ELEMENT_D, Result.NoException));
			printTest("A_add1B_AB_testListIterator1RealSetAfterPrev2Next", testListIteratorRealSetAfterNext(A_add1B_AB(), 1, 1, ELEMENT_D, "[1, 4]", Result.MatchingValue));
			printTest("A_add1B_AB_testListIterator1AddAfterPrev2Next", testListIteratorAdd(null,getListIteratorAfterCallItrItr(getListIteratorAfterCallItrItr(getListIteratorAfterCall(A_add1B_AB(), "previous", 1, null), "next", null, null), "next", null, null), null, ELEMENT_D, Result.NoException));
			printTest("A_add1B_AB_testListIterator1RealAddAfterPrev2Next", testListIteratorRealAddAfterNext(A_add1B_AB(), 1, 1, ELEMENT_D, "[1, 2, 4]", Result.MatchingValue));
			}
		} catch (Exception e) {
			System.out.printf("***UNABLE TO RUN/COMPLETE %s***\n", "test_A_add1B_AB");
			e.printStackTrace();
		}
	}
	
	//////////////////////////////////////
	// SCENARIO: [A,B] -> removeFirst() -> [B]
	//  XXX SCENARIO 16
	//////////////////////////////////////
	private ListADT<Integer> AB_removeFirst_B() {
		// cast to UnorderedListADT to make addToFront() available
		IndexedListADT<Integer> list = (IndexedListADT<Integer>) newList(); 
		list.add(0,ELEMENT_A);
		list.add(1, ELEMENT_B);
		list.removeFirst();
		return list;
	}
	private void test_AB_removeFirst_B(){
		try {
			// ListADT
			printTest("AB_removeFirst_B_testRemoveFirst", testRemoveFirst(AB_removeFirst_B(), ELEMENT_B, Result.MatchingValue));
			printTest("AB_removeFirst_B_testRemoveLast", testRemoveLast(AB_removeFirst_B(), ELEMENT_B, Result.MatchingValue));
			printTest("AB_removeFirst_B_testRemoveB", testRemoveElement(AB_removeFirst_B(), ELEMENT_B, Result.MatchingValue));
			printTest("AB_removeFirst_B_testRemoveA", testRemoveElement(AB_removeFirst_B(), ELEMENT_A, Result.ElementNotFound));
			printTest("AB_removeFirst_B_testFirst", testFirst(AB_removeFirst_B(), ELEMENT_B, Result.MatchingValue));
			printTest("AB_removeFirst_B_testLast", testLast(AB_removeFirst_B(), ELEMENT_B, Result.MatchingValue));
			printTest("AB_removeFirst_B_testContainsB", testContains(AB_removeFirst_B(), ELEMENT_B, Result.True));
			printTest("AB_removeFirst_B_testContainsC", testContains(AB_removeFirst_B(), ELEMENT_C, Result.False));
			printTest("AB_removeFirst_B_testIsEmpty", testIsEmpty(AB_removeFirst_B(), Result.False));
			printTest("AB_removeFirst_B_testSize", testSize(AB_removeFirst_B(), 1));
			printTest("AB_removeFirst_B_testToString", testToString(AB_removeFirst_B(), Result.ValidString));
			// UnorderedListADT
			printTest("AB_removeFirst_B_testAddToFrontB", testAddToFront(AB_removeFirst_B(), ELEMENT_A, Result.NoException));
			printTest("AB_removeFirst_B_testAddToRearB", testAddToRear(AB_removeFirst_B(), ELEMENT_A, Result.NoException));
			printTest(	"AB_removeFirst_B_testAddAfterCD", testAddAfter(AB_removeFirst_B(), ELEMENT_C, ELEMENT_D, Result.ElementNotFound));
			printTest(	"AB_removeFirst_B_testAddAfterBC", testAddAfter(AB_removeFirst_B(), ELEMENT_B, ELEMENT_C, Result.NoException));
			// IndexedListADT
			printTest("AB_removeFirst_B_testAddAtIndexNeg1B", testAddAtIndex(AB_removeFirst_B(), -1, ELEMENT_A, Result.IndexOutOfBounds));
			printTest("AB_removeFirst_B_testAddAtIndex0A", testAddAtIndex(AB_removeFirst_B(), 0, ELEMENT_A, Result.NoException));
			printTest("AB_removeFirst_B_testAddAtIndex1A", testAddAtIndex(AB_removeFirst_B(), 1, ELEMENT_A, Result.NoException));
			printTest("AB_removeFirst_B_testSetNeg1A", testSet(AB_removeFirst_B(), -1, ELEMENT_A, Result.IndexOutOfBounds));
			printTest("AB_removeFirst_B_testSet0A", testSet(AB_removeFirst_B(), 0, ELEMENT_A, Result.NoException));
			printTest("AB_removeFirst_B_testSet1A", testSet(AB_removeFirst_B(), 1, ELEMENT_A, Result.IndexOutOfBounds));
			printTest("AB_removeFirst_B_testAddA", testAdd(AB_removeFirst_B(), ELEMENT_A, Result.NoException));
			printTest("AB_removeFirst_B_testGetNeg1", testGet(AB_removeFirst_B(), -1, null, Result.IndexOutOfBounds));
			printTest("AB_removeFirst_B_testGet0", testGet(AB_removeFirst_B(), 0, ELEMENT_B, Result.MatchingValue));
			printTest("AB_removeFirst_B_testGet1", testGet(AB_removeFirst_B(), 1, null, Result.IndexOutOfBounds));
			printTest("AB_removeFirst_B_testIndexOfB", testIndexOf(AB_removeFirst_B(), ELEMENT_B, 0));
			printTest("AB_removeFirst_B_testIndexOfA", testIndexOf(AB_removeFirst_B(), ELEMENT_A, -1));
			printTest("AB_removeFirst_B_testRemoveNeg1", testRemoveIndex(AB_removeFirst_B(), -1, null, Result.IndexOutOfBounds));
			printTest("AB_removeFirst_B_testRemove0", testRemoveIndex(AB_removeFirst_B(), 0, ELEMENT_B, Result.MatchingValue));
			printTest("AB_removeFirst_B_testRemove1", testRemoveIndex(AB_removeFirst_B(), 1, null, Result.IndexOutOfBounds));
			// Iterator
			printTest("AB_removeFirst_B_testIterator", testIterator(AB_removeFirst_B(), Result.NoException));
			printTest("AB_removeFirst_B_testIteratorHasNext", testIteratorHasNext(AB_removeFirst_B().iterator(), Result.True));
			printTest("AB_removeFirst_B_testIteratorNext", testIteratorNext(AB_removeFirst_B().iterator(), ELEMENT_B, Result.MatchingValue));
			printTest("AB_removeFirst_B_testIteratorRemove", testIteratorRemove(AB_removeFirst_B().iterator(), Result.IllegalState));
			printTest("AB_removeFirst_B_iteratorNext_testIteratorHasNext", testIteratorHasNext(iteratorAfterNext(AB_removeFirst_B(), 1), Result.False));
			printTest("AB_removeFirst_B_iteratorNext_testIteratorNext", testIteratorNext(iteratorAfterNext(AB_removeFirst_B(), 1), null, Result.NoSuchElement));
			printTest("AB_removeFirst_B_iteratorNext_testIteratorRemove", testIteratorRemove(iteratorAfterNext(AB_removeFirst_B(), 1), Result.NoException));
			printTest("AB_removeFirst_B_iteratorNextRemove_testIteratorHasNext", testIteratorHasNext(iteratorAfterRemove(iteratorAfterNext(AB_removeFirst_B(), 1)), Result.False));
			printTest("AB_removeFirst_B_iteratorNextRemove_testIteratorNext", testIteratorNext(iteratorAfterRemove(iteratorAfterNext(AB_removeFirst_B(), 1)), null, Result.NoSuchElement));
			printTest("AB_removeFirst_B_iteratorNextRemove_testIteratorRemove", testIteratorRemove(iteratorAfterRemove(iteratorAfterNext(AB_removeFirst_B(), 1)), Result.IllegalState));
			printTest("AB_removeFirst_B_iteratorTestRealRemove",testIteratorRealRemove(AB_removeFirst_B(), 1, "[]", Result.MatchingValue));
			//ListIterator
			if(newList().getClass()==testDLL().getClass()){
			printTest("AB_removeFirst_B_testListIteratorUnIndexed", testListIteratorUnIndexed(AB_removeFirst_B(), Result.NoException));
			printTest("AB_removeFirst_B_testListIteratorIndexedNeg1", testListIteratorIndexed(AB_removeFirst_B(), -1, Result.IndexOutOfBounds));
			printTest("AB_removeFirst_B_testListIteratorIndexed0", testListIteratorIndexed(AB_removeFirst_B(), 0, Result.NoException));
			printTest("AB_removeFirst_B_testListIteratorIndexed1", testListIteratorIndexed(AB_removeFirst_B(), 1, Result.NoException));
			printTest("AB_removeFirst_B_testListIteratorIndexed2", testListIteratorIndexed(AB_removeFirst_B(), 2, Result.IndexOutOfBounds));
			
			printTest("AB_removeFirst_B_testListIteratorHasNext", testListIteratorHasNext(AB_removeFirst_B(),null, null, Result.True));
			printTest("AB_removeFirst_B_testListIteratorNext", testListIteratorNext(AB_removeFirst_B(),null, null, ELEMENT_B, Result.MatchingValue));
			printTest("AB_removeFirst_B_testListIteratorNextIndex", testListIteratorNextIndex(AB_removeFirst_B(),null, null, 0, Result.MatchingValue));
			printTest("AB_removeFirst_B_testListIteratorHasPrevious", testListIteratorHasPrevious(AB_removeFirst_B(),null, null, Result.False));
			printTest("AB_removeFirst_B_testListIteratorPrevious", testListIteratorPrevious(AB_removeFirst_B(),null, null, null, Result.NoSuchElement));
			printTest("AB_removeFirst_B_testListIteratorPreviousIndex", testListIteratorPreviousIndex(AB_removeFirst_B(),null, null, -1, Result.MatchingValue));
			printTest("AB_removeFirst_B_testListIteratorRemove", testListIteratorRemove(AB_removeFirst_B(),null, null, Result.IllegalState));
			printTest("AB_removeFirst_B_testListIteratorRealRemove", testListIteratorRealRemoveAfterNext(AB_removeFirst_B(), null, 0, "[2]", Result.MatchingValue));
			printTest("AB_removeFirst_B_testListIteratorSet", testListIteratorSet(AB_removeFirst_B(), null,null, ELEMENT_D, Result.IllegalState));
			printTest("AB_removeFirst_B_testListIteratorRealSet", testListIteratorRealSetAfterNext(AB_removeFirst_B(), null, 0, ELEMENT_D, "[2]", Result.MatchingValue));
			printTest("AB_removeFirst_B_testListIteratorAdd", testListIteratorAdd(AB_removeFirst_B(),null, null, ELEMENT_D, Result.NoException));
			printTest("AB_removeFirst_B_testListIteratorRealAdd", testListIteratorRealAddAfterNext(AB_removeFirst_B(), null, 0, ELEMENT_D, "[4, 2]", Result.MatchingValue));
			
			printTest("AB_removeFirst_B_testListIteratorHasNextAfterNext", testListIteratorHasNext(null, getListIteratorAfterCall(AB_removeFirst_B(), "next", null, null), null, Result.False));
			printTest("AB_removeFirst_B_testListIteratorNextAfterNext", testListIteratorNext(null,getListIteratorAfterCall(AB_removeFirst_B(), "next", null, null),null, null, Result.NoSuchElement));
			printTest("AB_removeFirst_B_testListIteratorNextIndexAfterNext", testListIteratorNextIndex(null,getListIteratorAfterCall(AB_removeFirst_B(), "next", null, null),null, 1, Result.MatchingValue));
			printTest("AB_removeFirst_B_testListIteratorHasPreviousAfterNext", testListIteratorHasPrevious(null,getListIteratorAfterCall(AB_removeFirst_B(), "next", null, null),null, Result.True));
			printTest("AB_removeFirst_B_testListIteratorPreviousAfterNext", testListIteratorPrevious(null, getListIteratorAfterCall(AB_removeFirst_B(), "next", null, null), null, ELEMENT_B, Result.MatchingValue));
			printTest("AB_removeFirst_B_testListIteratorPreviousIndexAfterNext", testListIteratorPreviousIndex(null, getListIteratorAfterCall(AB_removeFirst_B(), "next", null, null), null, 0, Result.MatchingValue));
			printTest("AB_removeFirst_B_testListIteratorRemoveAfterNext", testListIteratorRemove(null, getListIteratorAfterCall(AB_removeFirst_B(), "next", null, null), null, Result.NoException));
			printTest("AB_removeFirst_B_testListIteratorRealRemoveAfterNext", testListIteratorRealRemoveAfterNext(AB_removeFirst_B(), null, 1, "[]", Result.MatchingValue));
			printTest("AB_removeFirst_B_testListIteratorSetAfterNext", testListIteratorSet(null, getListIteratorAfterCall(AB_removeFirst_B(), "next", null, null), null, ELEMENT_D, Result.NoException));
			printTest("AB_removeFirst_B_testListIteratorRealSetAfterNext", testListIteratorRealSetAfterNext(AB_removeFirst_B(), null, 1, ELEMENT_D, "[4]", Result.MatchingValue));
			printTest("AB_removeFirst_B_testListIteratorAddAfterNext", testListIteratorAdd(null, getListIteratorAfterCall(AB_removeFirst_B(), "next", null, null), null, ELEMENT_D, Result.NoException));
			printTest("AB_removeFirst_B_testListIteratorRealAddAfterNext", testListIteratorRealAddAfterNext(AB_removeFirst_B(), null, 1, ELEMENT_D, "[2, 4]", Result.MatchingValue));
			
			printTest("AB_removeFirst_B_testListIterator0HasNext", testListIteratorHasNext(AB_removeFirst_B(), null, 0, Result.True));
			printTest("AB_removeFirst_B_testListIterator0Next", testListIteratorNext(AB_removeFirst_B(), null, 0, ELEMENT_B, Result.MatchingValue));
			printTest("AB_removeFirst_B_testListIterator0NextIndex", testListIteratorNextIndex(AB_removeFirst_B(), null, 0, 0, Result.MatchingValue));
			printTest("AB_removeFirst_B_testListIterator0HasPrevious", testListIteratorHasPrevious(AB_removeFirst_B(), null, 0, Result.False));
			printTest("AB_removeFirst_B_testListIterator0Previous", testListIteratorPrevious(AB_removeFirst_B(), null, 0, null, Result.NoSuchElement));
			printTest("AB_removeFirst_B_testListIterator0PreviousIndex", testListIteratorPreviousIndex(AB_removeFirst_B(), null, 0, -1, Result.MatchingValue));
			printTest("AB_removeFirst_B_testListIterator0Remove", testListIteratorRemove(AB_removeFirst_B(), null, 0, Result.IllegalState));
			printTest("AB_removeFirst_B_testListIterator0RealRemove", testListIteratorRealRemoveAfterNext(AB_removeFirst_B(), 0, 0, "[2]", Result.MatchingValue));
			printTest("AB_removeFirst_B_testListIterator0Set", testListIteratorSet(AB_removeFirst_B(), null, 0, ELEMENT_D, Result.IllegalState));
			printTest("AB_removeFirst_B_testListIterator0RealSet", testListIteratorRealSetAfterNext(AB_removeFirst_B(), 0, 0, ELEMENT_D, "[2]", Result.MatchingValue));
			printTest("AB_removeFirst_B_testListIterator0Add", testListIteratorAdd(AB_removeFirst_B(), null, 0, ELEMENT_D, Result.NoException));
			printTest("AB_removeFirst_B_testListIterator0RealAdd", testListIteratorRealAddAfterNext(AB_removeFirst_B(), 0, 0, ELEMENT_D, "[4, 2]", Result.MatchingValue));
			
			printTest("AB_removeFirst_B_testListIterator0HasNextAfterNext", testListIteratorHasNext(null, getListIteratorAfterCall(AB_removeFirst_B(), "next", 0, null), null, Result.False));
			printTest("AB_removeFirst_B_testListIterator0NextAfterNext", testListIteratorNext(null, getListIteratorAfterCall(AB_removeFirst_B(), "next", 0, null), null, null, Result.NoSuchElement));
			printTest("AB_removeFirst_B_testListIterator0NextIndexAfterNext", testListIteratorNextIndex(null, getListIteratorAfterCall(AB_removeFirst_B(), "next", 0, null), null, 1, Result.MatchingValue));
			printTest("AB_removeFirst_B_testListIterator0HasPreviousAfterNext", testListIteratorHasPrevious(null, getListIteratorAfterCall(AB_removeFirst_B(), "next", 0, null), null, Result.True));
			printTest("AB_removeFirst_B_testListIterator0PreviousAfterNext", testListIteratorPrevious(null, getListIteratorAfterCall(AB_removeFirst_B(), "next", 0, null), null, ELEMENT_B, Result.MatchingValue));
			printTest("AB_removeFirst_B_testListIterator0PreviousIndexAfterNext", testListIteratorPreviousIndex(null, getListIteratorAfterCall(AB_removeFirst_B(), "next", 0, null), null, 0, Result.MatchingValue));
			printTest("AB_removeFirst_B_testListIterator0RemoveAfterNext", testListIteratorRemove(null, getListIteratorAfterCall(AB_removeFirst_B(), "next", 0, null), null, Result.NoException));
			printTest("AB_removeFirst_B_testListIterator0RealRemoveAfterNext", testListIteratorRealRemoveAfterNext(AB_removeFirst_B(), 0, 1, "[]", Result.MatchingValue));
			printTest("AB_removeFirst_B_testListIterator0SetAfterNext", testListIteratorSet(null, getListIteratorAfterCall(AB_removeFirst_B(), "next", 0, null), null, ELEMENT_D, Result.NoException));
			printTest("AB_removeFirst_B_testListIterator0RealSetAfterNext", testListIteratorRealSetAfterNext(AB_removeFirst_B(), 0, 1, ELEMENT_D, "[4]", Result.MatchingValue));
			printTest("AB_removeFirst_B_testListIterator0AddAfterNext", testListIteratorAdd(null, getListIteratorAfterCall(AB_removeFirst_B(), "next", 0, null), null, ELEMENT_D, Result.NoException));
			printTest("AB_removeFirst_B_testListIterator0RealAddAfterNext", testListIteratorRealAddAfterNext(AB_removeFirst_B(), 0, 1, ELEMENT_D, "[2, 4]", Result.MatchingValue));
			
			printTest("AB_removeFirst_B_testListIterator1HasNext", testListIteratorHasNext(AB_removeFirst_B(), null, 1, Result.False));
			printTest("AB_removeFirst_B_testListIterator1Next", testListIteratorNext(AB_removeFirst_B(), null, 1, null, Result.NoSuchElement));
			printTest("AB_removeFirst_B_testListIterator1NextIndex", testListIteratorNextIndex(AB_removeFirst_B(), null, 1, 1, Result.MatchingValue));
			printTest("AB_removeFirst_B_testListIterator1HasPrevious", testListIteratorHasPrevious(AB_removeFirst_B(), null, 1, Result.True));
			printTest("AB_removeFirst_B_testListIterator1Previous", testListIteratorPrevious(AB_removeFirst_B(), null, 1, ELEMENT_B, Result.MatchingValue));
			printTest("AB_removeFirst_B_testListIterator1PreviousIndex", testListIteratorPreviousIndex(AB_removeFirst_B(), null, 1, 0, Result.MatchingValue));
			printTest("AB_removeFirst_B_testListIterator1Remove", testListIteratorRemove(AB_removeFirst_B(), null, 1, Result.IllegalState));
			printTest("AB_removeFirst_B_testListIterator1RealRemove", testListIteratorRealRemoveAfterNext(AB_removeFirst_B(), 1, 0, "[2]", Result.MatchingValue));
			printTest("AB_removeFirst_B_testListIterator1Set", testListIteratorSet(AB_removeFirst_B(), null, 1, ELEMENT_D, Result.IllegalState));
			printTest("AB_removeFirst_B_testListIterator1RealSet", testListIteratorRealSetAfterNext(AB_removeFirst_B(), 1, 0, ELEMENT_D, "[2]", Result.MatchingValue));
			printTest("AB_removeFirst_B_testListIterator1Add", testListIteratorAdd(AB_removeFirst_B(), null, 1, ELEMENT_D, Result.NoException));
			printTest("AB_removeFirst_B_testListIterator1RealAdd", testListIteratorRealAddAfterNext(AB_removeFirst_B(), 1, 0, ELEMENT_D, "[2, 4]", Result.MatchingValue));
			
			printTest("A_set0B_B_testListIterator1HasNextAfterPrev", testListIteratorHasNext(null, getListIteratorAfterCall(A_set0B_B(), "previous", 1, null), 1, Result.True));
			printTest("A_set0B_B_testListIterator1NextAfterPrev", testListIteratorNext(null, getListIteratorAfterCall(A_set0B_B(), "previous", 1, null), 1, ELEMENT_B, Result.MatchingValue));
			printTest("A_set0B_B_testListIterator1NextIndexAfterPrev", testListIteratorNextIndex(null, getListIteratorAfterCall(A_set0B_B(), "previous", 1, null), 1, 0, Result.MatchingValue));
			printTest("A_set0B_B_testListIterator1HasPreviousAfterPrev", testListIteratorHasPrevious(null, getListIteratorAfterCall(A_set0B_B(), "previous", 1, null), 1, Result.False));
			printTest("A_set0B_B_testListIterator1PreviousAfterPrev", testListIteratorPrevious(null, getListIteratorAfterCall(A_set0B_B(), "previous", 1, null), 1, null, Result.NoSuchElement));
			printTest("A_set0B_B_testListIterator1PreviousIndexAfterPrev", testListIteratorPreviousIndex(null, getListIteratorAfterCall(A_set0B_B(), "previous", 1, null), 1, -1, Result.MatchingValue));
			printTest("A_set0B_B_testListIterator1RemoveAfterPrev", testListIteratorRemove(null, getListIteratorAfterCall(A_set0B_B(), "previous", 1, null), 1, Result.NoException));
			printTest("A_set0B_B_testListIterator1RealRemoveAfterPrev", testListIteratorRealRemoveAfterPrev(A_set0B_B(), 1, 1, "[]", Result.MatchingValue));
			printTest("A_set0B_B_testListIterator1SetAfterPrev", testListIteratorSet(null, getListIteratorAfterCall(A_set0B_B(), "previous", 1, null), 1, ELEMENT_D, Result.NoException));
			printTest("A_set0B_B_testListIterator1RealSetAfterPrev", testListIteratorRealSetAfterPrev(A_set0B_B(), 1, 1, ELEMENT_D, "[4]", Result.MatchingValue));
			printTest("A_set0B_B_testListIterator1AddAfterPrev", testListIteratorAdd(null, getListIteratorAfterCall(A_set0B_B(), "previous", 1, null), 1, ELEMENT_D, Result.NoException));
			printTest("A_set0B_B_testListIterator1RealAddAfterPrev", testListIteratorRealAddAfterPrev(A_set0B_B(), 1, 1, ELEMENT_D, "[4, 2]", Result.MatchingValue));
			}
		} catch (Exception e) {
			System.out.printf("***UNABLE TO RUN/COMPLETE %s***\n", "test_AB_removeFirst_B");
			e.printStackTrace();
		}
	}
	
	//////////////////////////////////////
	// SCENARIO: [A,B] -> removeLast() -> [A]
	//  XXX SCENARIO 17
	//////////////////////////////////////
	private ListADT<Integer> AB_removeLast_A() {
		// cast to UnorderedListADT to make addToFront() available
		IndexedListADT<Integer> list = (IndexedListADT<Integer>) newList(); 
		list.add(0,ELEMENT_A);
		list.add(1, ELEMENT_B);
		list.removeLast();
		return list;
	}
	private void test_AB_removeLast_A(){
		try {
			// ListADT
			printTest("AB_removeLast_A_testRemoveFirst", testRemoveFirst(AB_removeLast_A(), ELEMENT_A, Result.MatchingValue));
			printTest("AB_removeLast_A_testRemoveLast", testRemoveLast(AB_removeLast_A(), ELEMENT_A, Result.MatchingValue));
			printTest("AB_removeLast_A_testRemoveA", testRemoveElement(AB_removeLast_A(), ELEMENT_A, Result.MatchingValue));
			printTest("AB_removeLast_A_testRemoveB", testRemoveElement(AB_removeLast_A(), ELEMENT_B, Result.ElementNotFound));
			printTest("AB_removeLast_A_testFirst", testFirst(AB_removeLast_A(), ELEMENT_A, Result.MatchingValue));
			printTest("AB_removeLast_A_testLast", testLast(AB_removeLast_A(), ELEMENT_A, Result.MatchingValue));
			printTest("AB_removeLast_A_testContainsA", testContains(AB_removeLast_A(), ELEMENT_A, Result.True));
			printTest("AB_removeLast_A_testContainsB", testContains(AB_removeLast_A(), ELEMENT_B, Result.False));
			printTest("AB_removeLast_A_testIsEmpty", testIsEmpty(AB_removeLast_A(), Result.False));
			printTest("AB_removeLast_A_testSize", testSize(AB_removeLast_A(), 1));
			printTest("AB_removeLast_A_testToString", testToString(AB_removeLast_A(), Result.ValidString));
			// UnorderedListADT
			printTest("AB_removeLast_A_testAddToFrontB", testAddToFront(AB_removeLast_A(), ELEMENT_B, Result.NoException));
			printTest("AB_removeLast_A_testAddToRearB", testAddToRear(AB_removeLast_A(), ELEMENT_A, Result.NoException));
			printTest(	"AB_removeLast_A_testAddAfterCB", testAddAfter(AB_removeLast_A(), ELEMENT_C, ELEMENT_B, Result.ElementNotFound));
			printTest(	"AB_removeLast_A_testAddAfterAB", testAddAfter(AB_removeLast_A(), ELEMENT_A, ELEMENT_B, Result.NoException));
			// IndexedListADT
			printTest("AB_removeLast_A_testAddAtIndexNeg1B", testAddAtIndex(AB_removeLast_A(), -1, ELEMENT_B, Result.IndexOutOfBounds));
			printTest("AB_removeLast_A_testAddAtIndex0B", testAddAtIndex(AB_removeLast_A(), 0, ELEMENT_B, Result.NoException));
			printTest("AB_removeLast_A_testAddAtIndex1B", testAddAtIndex(AB_removeLast_A(), 1, ELEMENT_B, Result.NoException));
			printTest("AB_removeLast_A_testSetNeg1B", testSet(AB_removeLast_A(), -1, ELEMENT_B, Result.IndexOutOfBounds));
			printTest("AB_removeLast_A_testSet0B", testSet(AB_removeLast_A(), 0, ELEMENT_B, Result.NoException));
			printTest("AB_removeLast_A_testSet1B", testSet(AB_removeLast_A(), 1, ELEMENT_B, Result.IndexOutOfBounds));
			printTest("AB_removeLast_A_testAddB", testAdd(AB_removeLast_A(), ELEMENT_B, Result.NoException));
			printTest("AB_removeLast_A_testGetNeg1", testGet(AB_removeLast_A(), -1, null, Result.IndexOutOfBounds));
			printTest("AB_removeLast_A_testGet0", testGet(AB_removeLast_A(), 0, ELEMENT_A, Result.MatchingValue));
			printTest("AB_removeLast_A_testGet1", testGet(AB_removeLast_A(), 1, null, Result.IndexOutOfBounds));
			printTest("AB_removeLast_A_testIndexOfA", testIndexOf(AB_removeLast_A(), ELEMENT_A, 0));
			printTest("AB_removeLast_A_testIndexOfB", testIndexOf(AB_removeLast_A(), ELEMENT_B, -1));
			printTest("AB_removeLast_A_testRemoveNeg1", testRemoveIndex(AB_removeLast_A(), -1, null, Result.IndexOutOfBounds));
			printTest("AB_removeLast_A_testRemove0", testRemoveIndex(AB_removeLast_A(), 0, ELEMENT_A, Result.MatchingValue));
			printTest("AB_removeLast_A_testRemove1", testRemoveIndex(AB_removeLast_A(), 1, null, Result.IndexOutOfBounds));
			// Iterator
			printTest("AB_removeLast_A_testIterator", testIterator(AB_removeLast_A(), Result.NoException));
			printTest("AB_removeLast_A_testIteratorHasNext", testIteratorHasNext(AB_removeLast_A().iterator(), Result.True));
			printTest("AB_removeLast_A_testIteratorNext", testIteratorNext(AB_removeLast_A().iterator(), ELEMENT_A, Result.MatchingValue));
			printTest("AB_removeLast_A_testIteratorRemove", testIteratorRemove(AB_removeLast_A().iterator(), Result.IllegalState));
			printTest("AB_removeLast_A_iteratorNext_testIteratorHasNext", testIteratorHasNext(iteratorAfterNext(AB_removeLast_A(), 1), Result.False));
			printTest("AB_removeLast_A_iteratorNext_testIteratorNext", testIteratorNext(iteratorAfterNext(AB_removeLast_A(), 1), null, Result.NoSuchElement));
			printTest("AB_removeLast_A_iteratorNext_testIteratorRemove", testIteratorRemove(iteratorAfterNext(AB_removeLast_A(), 1), Result.NoException));
			printTest("AB_removeLast_A_iteratorNextRemove_testIteratorHasNext", testIteratorHasNext(iteratorAfterRemove(iteratorAfterNext(AB_removeLast_A(), 1)), Result.False));
			printTest("AB_removeLast_A_iteratorNextRemove_testIteratorNext", testIteratorNext(iteratorAfterRemove(iteratorAfterNext(AB_removeLast_A(), 1)), null, Result.NoSuchElement));
			printTest("AB_removeLast_A_iteratorNextRemove_testIteratorRemove", testIteratorRemove(iteratorAfterRemove(iteratorAfterNext(AB_removeLast_A(), 1)), Result.IllegalState));
			printTest("AB_removeLast_A_iteratorTestRealRemove",testIteratorRealRemove(AB_removeLast_A(), 1, "[]", Result.MatchingValue));
			//ListIterator
			if(newList().getClass()==testDLL().getClass()){
			printTest("AB_removeLast_A_testListIteratorUnIndexed", testListIteratorUnIndexed(AB_removeLast_A(), Result.NoException));
			printTest("AB_removeLast_A_testListIteratorIndexedNeg1", testListIteratorIndexed(AB_removeLast_A(), -1, Result.IndexOutOfBounds));
			printTest("AB_removeLast_A_testListIteratorIndexed0", testListIteratorIndexed(AB_removeLast_A(), 0, Result.NoException));
			printTest("AB_removeLast_A_testListIteratorIndexed1", testListIteratorIndexed(AB_removeLast_A(), 1, Result.NoException));
			printTest("AB_removeLast_A_testListIteratorIndexed2", testListIteratorIndexed(AB_removeLast_A(), 2, Result.IndexOutOfBounds));
			
			printTest("AB_removeLast_A_testListIteratorHasNext", testListIteratorHasNext(AB_removeLast_A(),null, null, Result.True));
			printTest("AB_removeLast_A_testListIteratorNext", testListIteratorNext(AB_removeLast_A(),null, null, ELEMENT_A, Result.MatchingValue));
			printTest("AB_removeLast_A_testListIteratorNextIndex", testListIteratorNextIndex(AB_removeLast_A(),null, null, 0, Result.MatchingValue));
			printTest("AB_removeLast_A_testListIteratorHasPrevious", testListIteratorHasPrevious(AB_removeLast_A(),null, null, Result.False));
			printTest("AB_removeLast_A_testListIteratorPrevious", testListIteratorPrevious(AB_removeLast_A(),null, null, null, Result.NoSuchElement));
			printTest("AB_removeLast_A_testListIteratorPreviousIndex", testListIteratorPreviousIndex(AB_removeLast_A(),null, null, -1, Result.MatchingValue));
			printTest("AB_removeLast_A_testListIteratorRemove", testListIteratorRemove(AB_removeLast_A(),null, null, Result.IllegalState));
			printTest("AB_removeLast_A_testListIteratorRealRemove", testListIteratorRealRemoveAfterNext(AB_removeLast_A(), null, 0, "[1]", Result.MatchingValue));
			printTest("AB_removeLast_A_testListIteratorSet", testListIteratorSet(AB_removeLast_A(), null,null, ELEMENT_D, Result.IllegalState));
			printTest("AB_removeLast_A_testListIteratorRealSet", testListIteratorRealSetAfterNext(AB_removeLast_A(), null, 0, ELEMENT_D, "[1]", Result.MatchingValue));
			printTest("AB_removeLast_A_testListIteratorAdd", testListIteratorAdd(AB_removeLast_A(),null, null, ELEMENT_D, Result.NoException));
			printTest("AB_removeLast_A_testListIteratorRealAdd", testListIteratorRealAddAfterNext(AB_removeLast_A(), null, 0, ELEMENT_D, "[4, 1]", Result.MatchingValue));
			
			printTest("AB_removeLast_A_testListIteratorHasNextAfterNext", testListIteratorHasNext(null, getListIteratorAfterCall(AB_removeLast_A(), "next", null, null), null, Result.False));
			printTest("AB_removeLast_A_testListIteratorNextAfterNext", testListIteratorNext(null,getListIteratorAfterCall(AB_removeLast_A(), "next", null, null),null, null, Result.NoSuchElement));
			printTest("AB_removeLast_A_testListIteratorNextIndexAfterNext", testListIteratorNextIndex(null,getListIteratorAfterCall(AB_removeLast_A(), "next", null, null),null, 1, Result.MatchingValue));
			printTest("AB_removeLast_A_testListIteratorHasPreviousAfterNext", testListIteratorHasPrevious(null,getListIteratorAfterCall(AB_removeLast_A(), "next", null, null),null, Result.True));
			printTest("AB_removeLast_A_testListIteratorPreviousAfterNext", testListIteratorPrevious(null, getListIteratorAfterCall(AB_removeLast_A(), "next", null, null), null, ELEMENT_A, Result.MatchingValue));
			printTest("AB_removeLast_A_testListIteratorPreviousIndexAfterNext", testListIteratorPreviousIndex(null, getListIteratorAfterCall(AB_removeLast_A(), "next", null, null), null, 0, Result.MatchingValue));
			printTest("AB_removeLast_A_testListIteratorRemoveAfterNext", testListIteratorRemove(null, getListIteratorAfterCall(AB_removeLast_A(), "next", null, null), null, Result.NoException));
			printTest("AB_removeLast_A_testListIteratorRealRemoveAfterNext", testListIteratorRealRemoveAfterNext(AB_removeLast_A(), null, 1, "[]", Result.MatchingValue));
			printTest("AB_removeLast_A_testListIteratorSetAfterNext", testListIteratorSet(null, getListIteratorAfterCall(AB_removeLast_A(), "next", null, null), null, ELEMENT_D, Result.NoException));
			printTest("AB_removeLast_A_testListIteratorRealSetAfterNext", testListIteratorRealSetAfterNext(AB_removeLast_A(), null, 1, ELEMENT_D, "[4]", Result.MatchingValue));
			printTest("AB_removeLast_A_testListIteratorAddAfterNext", testListIteratorAdd(null, getListIteratorAfterCall(AB_removeLast_A(), "next", null, null), null, ELEMENT_D, Result.NoException));
			printTest("AB_removeLast_A_testListIteratorRealAddAfterNext", testListIteratorRealAddAfterNext(AB_removeLast_A(), null, 1, ELEMENT_D, "[1, 4]", Result.MatchingValue));
			
			printTest("AB_removeLast_A_testListIterator0HasNext", testListIteratorHasNext(AB_removeLast_A(), null, 0, Result.True));
			printTest("AB_removeLast_A_testListIterator0Next", testListIteratorNext(AB_removeLast_A(), null, 0, ELEMENT_A, Result.MatchingValue));
			printTest("AB_removeLast_A_testListIterator0NextIndex", testListIteratorNextIndex(AB_removeLast_A(), null, 0, 0, Result.MatchingValue));
			printTest("AB_removeLast_A_testListIterator0HasPrevious", testListIteratorHasPrevious(AB_removeLast_A(), null, 0, Result.False));
			printTest("AB_removeLast_A_testListIterator0Previous", testListIteratorPrevious(AB_removeLast_A(), null, 0, null, Result.NoSuchElement));
			printTest("AB_removeLast_A_testListIterator0PreviousIndex", testListIteratorPreviousIndex(AB_removeLast_A(), null, 0, -1, Result.MatchingValue));
			printTest("AB_removeLast_A_testListIterator0Remove", testListIteratorRemove(AB_removeLast_A(), null, 0, Result.IllegalState));
			printTest("AB_removeLast_A_testListIterator0RealRemove", testListIteratorRealRemoveAfterNext(AB_removeLast_A(), 0, 0, "[1]", Result.MatchingValue));
			printTest("AB_removeLast_A_testListIterator0Set", testListIteratorSet(AB_removeLast_A(), null, 0, ELEMENT_D, Result.IllegalState));
			printTest("AB_removeLast_A_testListIterator0RealSet", testListIteratorRealSetAfterNext(AB_removeLast_A(), 0, 0, ELEMENT_D, "[1]", Result.MatchingValue));
			printTest("AB_removeLast_A_testListIterator0Add", testListIteratorAdd(AB_removeLast_A(), null, 0, ELEMENT_D, Result.NoException));
			printTest("AB_removeLast_A_testListIterator0RealAdd", testListIteratorRealAddAfterNext(AB_removeLast_A(), 0, 0, ELEMENT_D, "[4, 1]", Result.MatchingValue));
			
			printTest("AB_removeLast_A_testListIterator0HasNextAfterNext", testListIteratorHasNext(null, getListIteratorAfterCall(AB_removeLast_A(), "next", 0, null), null, Result.False));
			printTest("AB_removeLast_A_testListIterator0NextAfterNext", testListIteratorNext(null, getListIteratorAfterCall(AB_removeLast_A(), "next", 0, null), null, null, Result.NoSuchElement));
			printTest("AB_removeLast_A_testListIterator0NextIndexAfterNext", testListIteratorNextIndex(null, getListIteratorAfterCall(AB_removeLast_A(), "next", 0, null), null, 1, Result.MatchingValue));
			printTest("AB_removeLast_A_testListIterator0HasPreviousAfterNext", testListIteratorHasPrevious(null, getListIteratorAfterCall(AB_removeLast_A(), "next", 0, null), null, Result.True));
			printTest("AB_removeLast_A_testListIterator0PreviousAfterNext", testListIteratorPrevious(null, getListIteratorAfterCall(AB_removeLast_A(), "next", 0, null), null, ELEMENT_A, Result.MatchingValue));
			printTest("AB_removeLast_A_testListIterator0PreviousIndexAfterNext", testListIteratorPreviousIndex(null, getListIteratorAfterCall(AB_removeLast_A(), "next", 0, null), null, 0, Result.MatchingValue));
			printTest("AB_removeLast_A_testListIterator0RemoveAfterNext", testListIteratorRemove(null, getListIteratorAfterCall(AB_removeLast_A(), "next", 0, null), null, Result.NoException));
			printTest("AB_removeLast_A_testListIterator0RealRemoveAfterNext", testListIteratorRealRemoveAfterNext(AB_removeLast_A(), 0, 1, "[]", Result.MatchingValue));
			printTest("AB_removeLast_A_testListIterator0SetAfterNext", testListIteratorSet(null, getListIteratorAfterCall(AB_removeLast_A(), "next", 0, null), null, ELEMENT_D, Result.NoException));
			printTest("AB_removeLast_A_testListIterator0RealSetAfterNext", testListIteratorRealSetAfterNext(AB_removeLast_A(), 0, 1, ELEMENT_D, "[4]", Result.MatchingValue));
			printTest("AB_removeLast_A_testListIterator0AddAfterNext", testListIteratorAdd(null, getListIteratorAfterCall(AB_removeLast_A(), "next", 0, null), null, ELEMENT_D, Result.NoException));
			printTest("AB_removeLast_A_testListIterator0RealAddAfterNext", testListIteratorRealAddAfterNext(AB_removeLast_A(), 0, 1, ELEMENT_D, "[1, 4]", Result.MatchingValue));
			
			printTest("AB_removeLast_A_testListIterator1HasNext", testListIteratorHasNext(AB_removeLast_A(), null, 1, Result.False));
			printTest("AB_removeLast_A_testListIterator1Next", testListIteratorNext(AB_removeLast_A(), null, 1, null, Result.NoSuchElement));
			printTest("AB_removeLast_A_testListIterator1NextIndex", testListIteratorNextIndex(AB_removeLast_A(), null, 1, 1, Result.MatchingValue));
			printTest("AB_removeLast_A_testListIterator1HasPrevious", testListIteratorHasPrevious(AB_removeLast_A(), null, 1, Result.True));
			printTest("AB_removeLast_A_testListIterator1Previous", testListIteratorPrevious(AB_removeLast_A(), null, 1, ELEMENT_A, Result.MatchingValue));
			printTest("AB_removeLast_A_testListIterator1PreviousIndex", testListIteratorPreviousIndex(AB_removeLast_A(), null, 1, 0, Result.MatchingValue));
			printTest("AB_removeLast_A_testListIterator1Remove", testListIteratorRemove(AB_removeLast_A(), null, 1, Result.IllegalState));
			printTest("AB_removeLast_A_testListIterator1RealRemove", testListIteratorRealRemoveAfterNext(AB_removeLast_A(), 1, 0, "[1]", Result.MatchingValue));
			printTest("AB_removeLast_A_testListIterator1Set", testListIteratorSet(AB_removeLast_A(), null, 1, ELEMENT_D, Result.IllegalState));
			printTest("AB_removeLast_A_testListIterator1RealSet", testListIteratorRealSetAfterNext(AB_removeLast_A(), 1, 0, ELEMENT_D, "[1]", Result.MatchingValue));
			printTest("AB_removeLast_A_testListIterator1Add", testListIteratorAdd(AB_removeLast_A(), null, 1, ELEMENT_D, Result.NoException));
			printTest("AB_removeLast_A_testListIterator1RealAdd", testListIteratorRealAddAfterNext(AB_removeLast_A(), 1, 0, ELEMENT_D, "[1, 4]", Result.MatchingValue));
			
			printTest("AB_removeLast_A_testListIterator1HasNextAfterPrev", testListIteratorHasNext(null, getListIteratorAfterCall(AB_removeLast_A(), "previous", 1, null), 1, Result.True));
			printTest("AB_removeLast_A_testListIterator1NextAfterPrev", testListIteratorNext(null, getListIteratorAfterCall(AB_removeLast_A(), "previous", 1, null), 1, ELEMENT_A, Result.MatchingValue));
			printTest("AB_removeLast_A_testListIterator1NextIndexAfterPrev", testListIteratorNextIndex(null, getListIteratorAfterCall(AB_removeLast_A(), "previous", 1, null), 1, 0, Result.MatchingValue));
			printTest("AB_removeLast_A_testListIterator1HasPreviousAfterPrev", testListIteratorHasPrevious(null, getListIteratorAfterCall(AB_removeLast_A(), "previous", 1, null), 1, Result.False));
			printTest("AB_removeLast_A_testListIterator1PreviousAfterPrev", testListIteratorPrevious(null, getListIteratorAfterCall(AB_removeLast_A(), "previous", 1, null), 1, null, Result.NoSuchElement));
			printTest("AB_removeLast_A_testListIterator1PreviousIndexAfterPrev", testListIteratorPreviousIndex(null, getListIteratorAfterCall(AB_removeLast_A(), "previous", 1, null), 1, -1, Result.MatchingValue));
			printTest("AB_removeLast_A_testListIterator1RemoveAfterPrev", testListIteratorRemove(null, getListIteratorAfterCall(AB_removeLast_A(), "previous", 1, null), 1, Result.NoException));
			printTest("AB_removeLast_A_testListIterator1RealRemoveAfterPrev", testListIteratorRealRemoveAfterPrev(AB_removeLast_A(), 1, 1, "[]", Result.MatchingValue));
			printTest("AB_removeLast_A_testListIterator1SetAfterPrev", testListIteratorSet(null, getListIteratorAfterCall(AB_removeLast_A(), "previous", 1, null), 1, ELEMENT_D, Result.NoException));
			printTest("AB_removeLast_A_testListIterator1RealSetAfterPrev", testListIteratorRealSetAfterPrev(AB_removeLast_A(), 1, 1, ELEMENT_D, "[4]", Result.MatchingValue));
			printTest("AB_removeLast_A_testListIterator1AddAfterPrev", testListIteratorAdd(null, getListIteratorAfterCall(AB_removeLast_A(), "previous", 1, null), 1, ELEMENT_D, Result.NoException));
			printTest("AB_removeLast_A_testListIterator1RealAddAfterPrev", testListIteratorRealAddAfterPrev(AB_removeLast_A(), 1, 1, ELEMENT_D, "[4, 1]", Result.MatchingValue));
			}
		} catch (Exception e) {
			System.out.printf("***UNABLE TO RUN/COMPLETE %s***\n", "test_AB_removeLast_A");
			e.printStackTrace();
		}
	}
	
	//////////////////////////////////////
	// SCENARIO: [A,B] -> remove(A) -> [B]
	//  XXX SCENARIO 18
	//////////////////////////////////////
	private ListADT<Integer> AB_removeA_B() {
		// cast to UnorderedListADT to make addToFront() available
		IndexedListADT<Integer> list = (IndexedListADT<Integer>) newList(); 
		list.add(0,ELEMENT_A);
		list.add(1, ELEMENT_B);
		list.remove(ELEMENT_A);
		return list;
	}
	private void test_AB_removeA_B(){
		try {
			// ListADT
			printTest("AB_removeA_B_testRemoveFirst", testRemoveFirst(AB_removeA_B(), ELEMENT_B, Result.MatchingValue));
			printTest("AB_removeA_B_testRemoveLast", testRemoveLast(AB_removeA_B(), ELEMENT_B, Result.MatchingValue));
			printTest("AB_removeA_B_testRemoveB", testRemoveElement(AB_removeA_B(), ELEMENT_B, Result.MatchingValue));
			printTest("AB_removeA_B_testRemoveA", testRemoveElement(AB_removeA_B(), ELEMENT_A, Result.ElementNotFound));
			printTest("AB_removeA_B_testFirst", testFirst(AB_removeA_B(), ELEMENT_B, Result.MatchingValue));
			printTest("AB_removeA_B_testLast", testLast(AB_removeA_B(), ELEMENT_B, Result.MatchingValue));
			printTest("AB_removeA_B_testContainsB", testContains(AB_removeA_B(), ELEMENT_B, Result.True));
			printTest("AB_removeA_B_testContainsC", testContains(AB_removeA_B(), ELEMENT_C, Result.False));
			printTest("AB_removeA_B_testIsEmpty", testIsEmpty(AB_removeA_B(), Result.False));
			printTest("AB_removeA_B_testSize", testSize(AB_removeA_B(), 1));
			printTest("AB_removeA_B_testToString", testToString(AB_removeA_B(), Result.ValidString));
			// UnorderedListADT
			printTest("AB_removeA_B_testAddToFrontB", testAddToFront(AB_removeA_B(), ELEMENT_A, Result.NoException));
			printTest("AB_removeA_B_testAddToRearB", testAddToRear(AB_removeA_B(), ELEMENT_A, Result.NoException));
			printTest(	"AB_removeA_B_testAddAfterCD", testAddAfter(AB_removeA_B(), ELEMENT_C, ELEMENT_D, Result.ElementNotFound));
			printTest(	"AB_removeA_B_testAddAfterBC", testAddAfter(AB_removeA_B(), ELEMENT_B, ELEMENT_C, Result.NoException));
			// IndexedListADT
			printTest("AB_removeA_B_testAddAtIndexNeg1B", testAddAtIndex(AB_removeA_B(), -1, ELEMENT_A, Result.IndexOutOfBounds));
			printTest("AB_removeA_B_testAddAtIndex0A", testAddAtIndex(AB_removeA_B(), 0, ELEMENT_A, Result.NoException));
			printTest("AB_removeA_B_testAddAtIndex1A", testAddAtIndex(AB_removeA_B(), 1, ELEMENT_A, Result.NoException));
			printTest("AB_removeA_B_testSetNeg1A", testSet(AB_removeA_B(), -1, ELEMENT_A, Result.IndexOutOfBounds));
			printTest("AB_removeA_B_testSet0A", testSet(AB_removeA_B(), 0, ELEMENT_A, Result.NoException));
			printTest("AB_removeA_B_testSet1A", testSet(AB_removeA_B(), 1, ELEMENT_A, Result.IndexOutOfBounds));
			printTest("AB_removeA_B_testAddA", testAdd(AB_removeA_B(), ELEMENT_A, Result.NoException));
			printTest("AB_removeA_B_testGetNeg1", testGet(AB_removeA_B(), -1, null, Result.IndexOutOfBounds));
			printTest("AB_removeA_B_testGet0", testGet(AB_removeA_B(), 0, ELEMENT_B, Result.MatchingValue));
			printTest("AB_removeA_B_testGet1", testGet(AB_removeA_B(), 1, null, Result.IndexOutOfBounds));
			printTest("AB_removeA_B_testIndexOfB", testIndexOf(AB_removeA_B(), ELEMENT_B, 0));
			printTest("AB_removeA_B_testIndexOfA", testIndexOf(AB_removeA_B(), ELEMENT_A, -1));
			printTest("AB_removeA_B_testRemoveNeg1", testRemoveIndex(AB_removeA_B(), -1, null, Result.IndexOutOfBounds));
			printTest("AB_removeA_B_testRemove0", testRemoveIndex(AB_removeA_B(), 0, ELEMENT_B, Result.MatchingValue));
			printTest("AB_removeA_B_testRemove1", testRemoveIndex(AB_removeA_B(), 1, null, Result.IndexOutOfBounds));
			// Iterator
			printTest("AB_removeA_B_testIterator", testIterator(AB_removeA_B(), Result.NoException));
			printTest("AB_removeA_B_testIteratorHasNext", testIteratorHasNext(AB_removeA_B().iterator(), Result.True));
			printTest("AB_removeA_B_testIteratorNext", testIteratorNext(AB_removeA_B().iterator(), ELEMENT_B, Result.MatchingValue));
			printTest("AB_removeA_B_testIteratorRemove", testIteratorRemove(AB_removeA_B().iterator(), Result.IllegalState));
			printTest("AB_removeA_B_iteratorNext_testIteratorHasNext", testIteratorHasNext(iteratorAfterNext(AB_removeA_B(), 1), Result.False));
			printTest("AB_removeA_B_iteratorNext_testIteratorNext", testIteratorNext(iteratorAfterNext(AB_removeA_B(), 1), null, Result.NoSuchElement));
			printTest("AB_removeA_B_iteratorNext_testIteratorRemove", testIteratorRemove(iteratorAfterNext(AB_removeA_B(), 1), Result.NoException));
			printTest("AB_removeA_B_iteratorNextRemove_testIteratorHasNext", testIteratorHasNext(iteratorAfterRemove(iteratorAfterNext(AB_removeA_B(), 1)), Result.False));
			printTest("AB_removeA_B_iteratorNextRemove_testIteratorNext", testIteratorNext(iteratorAfterRemove(iteratorAfterNext(AB_removeA_B(), 1)), null, Result.NoSuchElement));
			printTest("AB_removeA_B_iteratorNextRemove_testIteratorRemove", testIteratorRemove(iteratorAfterRemove(iteratorAfterNext(AB_removeA_B(), 1)), Result.IllegalState));
			printTest("AB_removeA_B_iteratorTestRealRemove",testIteratorRealRemove(AB_removeA_B(), 1, "[]", Result.MatchingValue));
			//ListIterator
			if(newList().getClass()==testDLL().getClass()){
			printTest("AB_removeA_B_testListIteratorUnIndexed", testListIteratorUnIndexed(AB_removeA_B(), Result.NoException));
			printTest("AB_removeA_B_testListIteratorIndexedNeg1", testListIteratorIndexed(AB_removeA_B(), -1, Result.IndexOutOfBounds));
			printTest("AB_removeA_B_testListIteratorIndexed0", testListIteratorIndexed(AB_removeA_B(), 0, Result.NoException));
			printTest("AB_removeA_B_testListIteratorIndexed1", testListIteratorIndexed(AB_removeA_B(), 1, Result.NoException));
			printTest("AB_removeA_B_testListIteratorIndexed2", testListIteratorIndexed(AB_removeA_B(), 2, Result.IndexOutOfBounds));
			
			printTest("AB_removeA_B_testListIteratorHasNext", testListIteratorHasNext(AB_removeA_B(),null, null, Result.True));
			printTest("AB_removeA_B_testListIteratorNext", testListIteratorNext(AB_removeA_B(),null, null, ELEMENT_B, Result.MatchingValue));
			printTest("AB_removeA_B_testListIteratorNextIndex", testListIteratorNextIndex(AB_removeA_B(),null, null, 0, Result.MatchingValue));
			printTest("AB_removeA_B_testListIteratorHasPrevious", testListIteratorHasPrevious(AB_removeA_B(),null, null, Result.False));
			printTest("AB_removeA_B_testListIteratorPrevious", testListIteratorPrevious(AB_removeA_B(),null, null, null, Result.NoSuchElement));
			printTest("AB_removeA_B_testListIteratorPreviousIndex", testListIteratorPreviousIndex(AB_removeA_B(),null, null, -1, Result.MatchingValue));
			printTest("AB_removeA_B_testListIteratorRemove", testListIteratorRemove(AB_removeA_B(),null, null, Result.IllegalState));
			printTest("AB_removeA_B_testListIteratorRealRemove", testListIteratorRealRemoveAfterNext(AB_removeA_B(), null, 0, "[2]", Result.MatchingValue));
			printTest("AB_removeA_B_testListIteratorSet", testListIteratorSet(AB_removeA_B(), null,null, ELEMENT_D, Result.IllegalState));
			printTest("AB_removeA_B_testListIteratorRealSet", testListIteratorRealSetAfterNext(AB_removeA_B(), null, 0, ELEMENT_D, "[2]", Result.MatchingValue));
			printTest("AB_removeA_B_testListIteratorAdd", testListIteratorAdd(AB_removeA_B(),null, null, ELEMENT_D, Result.NoException));
			printTest("AB_removeA_B_testListIteratorRealAdd", testListIteratorRealAddAfterNext(AB_removeA_B(), null, 0, ELEMENT_D, "[4, 2]", Result.MatchingValue));
			
			printTest("AB_removeA_B_testListIteratorHasNextAfterNext", testListIteratorHasNext(null, getListIteratorAfterCall(AB_removeA_B(), "next", null, null), null, Result.False));
			printTest("AB_removeA_B_testListIteratorNextAfterNext", testListIteratorNext(null,getListIteratorAfterCall(AB_removeA_B(), "next", null, null),null, null, Result.NoSuchElement));
			printTest("AB_removeA_B_testListIteratorNextIndexAfterNext", testListIteratorNextIndex(null,getListIteratorAfterCall(AB_removeA_B(), "next", null, null),null, 1, Result.MatchingValue));
			printTest("AB_removeA_B_testListIteratorHasPreviousAfterNext", testListIteratorHasPrevious(null,getListIteratorAfterCall(AB_removeA_B(), "next", null, null),null, Result.True));
			printTest("AB_removeA_B_testListIteratorPreviousAfterNext", testListIteratorPrevious(null, getListIteratorAfterCall(AB_removeA_B(), "next", null, null), null, ELEMENT_B, Result.MatchingValue));
			printTest("AB_removeA_B_testListIteratorPreviousIndexAfterNext", testListIteratorPreviousIndex(null, getListIteratorAfterCall(AB_removeA_B(), "next", null, null), null, 0, Result.MatchingValue));
			printTest("AB_removeA_B_testListIteratorRemoveAfterNext", testListIteratorRemove(null, getListIteratorAfterCall(AB_removeA_B(), "next", null, null), null, Result.NoException));
			printTest("AB_removeA_B_testListIteratorRealRemoveAfterNext", testListIteratorRealRemoveAfterNext(AB_removeA_B(), null, 1, "[]", Result.MatchingValue));
			printTest("AB_removeA_B_testListIteratorSetAfterNext", testListIteratorSet(null, getListIteratorAfterCall(AB_removeA_B(), "next", null, null), null, ELEMENT_D, Result.NoException));
			printTest("AB_removeA_B_testListIteratorRealSetAfterNext", testListIteratorRealSetAfterNext(AB_removeA_B(), null, 1, ELEMENT_D, "[4]", Result.MatchingValue));
			printTest("AB_removeA_B_testListIteratorAddAfterNext", testListIteratorAdd(null, getListIteratorAfterCall(AB_removeA_B(), "next", null, null), null, ELEMENT_D, Result.NoException));
			printTest("AB_removeA_B_testListIteratorRealAddAfterNext", testListIteratorRealAddAfterNext(AB_removeA_B(), null, 1, ELEMENT_D, "[2, 4]", Result.MatchingValue));
			
			printTest("AB_removeA_B_testListIterator0HasNext", testListIteratorHasNext(AB_removeA_B(), null, 0, Result.True));
			printTest("AB_removeA_B_testListIterator0Next", testListIteratorNext(AB_removeA_B(), null, 0, ELEMENT_B, Result.MatchingValue));
			printTest("AB_removeA_B_testListIterator0NextIndex", testListIteratorNextIndex(AB_removeA_B(), null, 0, 0, Result.MatchingValue));
			printTest("AB_removeA_B_testListIterator0HasPrevious", testListIteratorHasPrevious(AB_removeA_B(), null, 0, Result.False));
			printTest("AB_removeA_B_testListIterator0Previous", testListIteratorPrevious(AB_removeA_B(), null, 0, null, Result.NoSuchElement));
			printTest("AB_removeA_B_testListIterator0PreviousIndex", testListIteratorPreviousIndex(AB_removeA_B(), null, 0, -1, Result.MatchingValue));
			printTest("AB_removeA_B_testListIterator0Remove", testListIteratorRemove(AB_removeA_B(), null, 0, Result.IllegalState));
			printTest("AB_removeA_B_testListIterator0RealRemove", testListIteratorRealRemoveAfterNext(AB_removeA_B(), 0, 0, "[2]", Result.MatchingValue));
			printTest("AB_removeA_B_testListIterator0Set", testListIteratorSet(AB_removeA_B(), null, 0, ELEMENT_D, Result.IllegalState));
			printTest("AB_removeA_B_testListIterator0RealSet", testListIteratorRealSetAfterNext(AB_removeA_B(), 0, 0, ELEMENT_D, "[2]", Result.MatchingValue));
			printTest("AB_removeA_B_testListIterator0Add", testListIteratorAdd(AB_removeA_B(), null, 0, ELEMENT_D, Result.NoException));
			printTest("AB_removeA_B_testListIterator0RealAdd", testListIteratorRealAddAfterNext(AB_removeA_B(), 0, 0, ELEMENT_D, "[4, 2]", Result.MatchingValue));
			
			printTest("AB_removeA_B_testListIterator0HasNextAfterNext", testListIteratorHasNext(null, getListIteratorAfterCall(AB_removeA_B(), "next", 0, null), null, Result.False));
			printTest("AB_removeA_B_testListIterator0NextAfterNext", testListIteratorNext(null, getListIteratorAfterCall(AB_removeA_B(), "next", 0, null), null, null, Result.NoSuchElement));
			printTest("AB_removeA_B_testListIterator0NextIndexAfterNext", testListIteratorNextIndex(null, getListIteratorAfterCall(AB_removeA_B(), "next", 0, null), null, 1, Result.MatchingValue));
			printTest("AB_removeA_B_testListIterator0HasPreviousAfterNext", testListIteratorHasPrevious(null, getListIteratorAfterCall(AB_removeA_B(), "next", 0, null), null, Result.True));
			printTest("AB_removeA_B_testListIterator0PreviousAfterNext", testListIteratorPrevious(null, getListIteratorAfterCall(AB_removeA_B(), "next", 0, null), null, ELEMENT_B, Result.MatchingValue));
			printTest("AB_removeA_B_testListIterator0PreviousIndexAfterNext", testListIteratorPreviousIndex(null, getListIteratorAfterCall(AB_removeA_B(), "next", 0, null), null, 0, Result.MatchingValue));
			printTest("AB_removeA_B_testListIterator0RemoveAfterNext", testListIteratorRemove(null, getListIteratorAfterCall(AB_removeA_B(), "next", 0, null), null, Result.NoException));
			printTest("AB_removeA_B_testListIterator0RealRemoveAfterNext", testListIteratorRealRemoveAfterNext(AB_removeA_B(), 0, 1, "[]", Result.MatchingValue));
			printTest("AB_removeA_B_testListIterator0SetAfterNext", testListIteratorSet(null, getListIteratorAfterCall(AB_removeA_B(), "next", 0, null), null, ELEMENT_D, Result.NoException));
			printTest("AB_removeA_B_testListIterator0RealSetAfterNext", testListIteratorRealSetAfterNext(AB_removeA_B(), 0, 1, ELEMENT_D, "[4]", Result.MatchingValue));
			printTest("AB_removeA_B_testListIterator0AddAfterNext", testListIteratorAdd(null, getListIteratorAfterCall(AB_removeA_B(), "next", 0, null), null, ELEMENT_D, Result.NoException));
			printTest("AB_removeA_B_testListIterator0RealAddAfterNext", testListIteratorRealAddAfterNext(AB_removeA_B(), 0, 1, ELEMENT_D, "[2, 4]", Result.MatchingValue));
			
			printTest("AB_removeA_B_testListIterator1HasNext", testListIteratorHasNext(AB_removeA_B(), null, 1, Result.False));
			printTest("AB_removeA_B_testListIterator1Next", testListIteratorNext(AB_removeA_B(), null, 1, null, Result.NoSuchElement));
			printTest("AB_removeA_B_testListIterator1NextIndex", testListIteratorNextIndex(AB_removeA_B(), null, 1, 1, Result.MatchingValue));
			printTest("AB_removeA_B_testListIterator1HasPrevious", testListIteratorHasPrevious(AB_removeA_B(), null, 1, Result.True));
			printTest("AB_removeA_B_testListIterator1Previous", testListIteratorPrevious(AB_removeA_B(), null, 1, ELEMENT_B, Result.MatchingValue));
			printTest("AB_removeA_B_testListIterator1PreviousIndex", testListIteratorPreviousIndex(AB_removeA_B(), null, 1, 0, Result.MatchingValue));
			printTest("AB_removeA_B_testListIterator1Remove", testListIteratorRemove(AB_removeA_B(), null, 1, Result.IllegalState));
			printTest("AB_removeA_B_testListIterator1RealRemove", testListIteratorRealRemoveAfterNext(AB_removeA_B(), 1, 0, "[2]", Result.MatchingValue));
			printTest("AB_removeA_B_testListIterator1Set", testListIteratorSet(AB_removeA_B(), null, 1, ELEMENT_D, Result.IllegalState));
			printTest("AB_removeA_B_testListIterator1RealSet", testListIteratorRealSetAfterNext(AB_removeA_B(), 1, 0, ELEMENT_D, "[2]", Result.MatchingValue));
			printTest("AB_removeA_B_testListIterator1Add", testListIteratorAdd(AB_removeA_B(), null, 1, ELEMENT_D, Result.NoException));
			printTest("AB_removeA_B_testListIterator1RealAdd", testListIteratorRealAddAfterNext(AB_removeA_B(), 1, 0, ELEMENT_D, "[2, 4]", Result.MatchingValue));
			
			printTest("AB_removeA_B_testListIterator1HasNextAfterPrev", testListIteratorHasNext(null, getListIteratorAfterCall(AB_removeA_B(), "previous", 1, null), 1, Result.True));
			printTest("AB_removeA_B_testListIterator1NextAfterPrev", testListIteratorNext(null, getListIteratorAfterCall(AB_removeA_B(), "previous", 1, null), 1, ELEMENT_B, Result.MatchingValue));
			printTest("AB_removeA_B_testListIterator1NextIndexAfterPrev", testListIteratorNextIndex(null, getListIteratorAfterCall(AB_removeA_B(), "previous", 1, null), 1, 0, Result.MatchingValue));
			printTest("AB_removeA_B_testListIterator1HasPreviousAfterPrev", testListIteratorHasPrevious(null, getListIteratorAfterCall(AB_removeA_B(), "previous", 1, null), 1, Result.False));
			printTest("AB_removeA_B_testListIterator1PreviousAfterPrev", testListIteratorPrevious(null, getListIteratorAfterCall(AB_removeA_B(), "previous", 1, null), 1, null, Result.NoSuchElement));
			printTest("AB_removeA_B_testListIterator1PreviousIndexAfterPrev", testListIteratorPreviousIndex(null, getListIteratorAfterCall(AB_removeA_B(), "previous", 1, null), 1, -1, Result.MatchingValue));
			printTest("AB_removeA_B_testListIterator1RemoveAfterPrev", testListIteratorRemove(null, getListIteratorAfterCall(AB_removeA_B(), "previous", 1, null), 1, Result.NoException));
			printTest("AB_removeA_B_testListIterator1RealRemoveAfterPrev", testListIteratorRealRemoveAfterPrev(AB_removeA_B(), 1, 1, "[]", Result.MatchingValue));
			printTest("AB_removeA_B_testListIterator1SetAfterPrev", testListIteratorSet(null, getListIteratorAfterCall(AB_removeA_B(), "previous", 1, null), 1, ELEMENT_D, Result.NoException));
			printTest("AB_removeA_B_testListIterator1RealSetAfterPrev", testListIteratorRealSetAfterPrev(AB_removeA_B(), 1, 1, ELEMENT_D, "[4]", Result.MatchingValue));
			printTest("AB_removeA_B_testListIterator1AddAfterPrev", testListIteratorAdd(null, getListIteratorAfterCall(AB_removeA_B(), "previous", 1, null), 1, ELEMENT_D, Result.NoException));
			printTest("AB_removeA_B_testListIterator1RealAddAfterPrev", testListIteratorRealAddAfterPrev(AB_removeA_B(), 1, 1, ELEMENT_D, "[4, 2]", Result.MatchingValue));
			}
		} catch (Exception e) {
			System.out.printf("***UNABLE TO RUN/COMPLETE %s***\n", "test_AB_removeA_B");
			e.printStackTrace();
		}
	}
	
	//////////////////////////////////////
	// SCENARIO: [A,B] -> remove(B) -> [A]
	//  XXX SCENARIO 19
	//////////////////////////////////////
	private ListADT<Integer> AB_removeB_A() {
		// cast to UnorderedListADT to make addToFront() available
		IndexedListADT<Integer> list = (IndexedListADT<Integer>) newList(); 
		list.add(0,ELEMENT_A);
		list.add(1, ELEMENT_B);
		list.remove(ELEMENT_B);
		return list;
	}
	private void test_AB_removeB_A(){
		try {
			// ListADT
			printTest("AB_removeB_A_testRemoveFirst", testRemoveFirst(AB_removeB_A(), ELEMENT_A, Result.MatchingValue));
			printTest("AB_removeB_A_testRemoveLast", testRemoveLast(AB_removeB_A(), ELEMENT_A, Result.MatchingValue));
			printTest("AB_removeB_A_testRemoveA", testRemoveElement(AB_removeB_A(), ELEMENT_A, Result.MatchingValue));
			printTest("AB_removeB_A_testRemoveB", testRemoveElement(AB_removeB_A(), ELEMENT_B, Result.ElementNotFound));
			printTest("AB_removeB_A_testFirst", testFirst(AB_removeB_A(), ELEMENT_A, Result.MatchingValue));
			printTest("AB_removeB_A_testLast", testLast(AB_removeB_A(), ELEMENT_A, Result.MatchingValue));
			printTest("AB_removeB_A_testContainsA", testContains(AB_removeB_A(), ELEMENT_A, Result.True));
			printTest("AB_removeB_A_testContainsB", testContains(AB_removeB_A(), ELEMENT_B, Result.False));
			printTest("AB_removeB_A_testIsEmpty", testIsEmpty(AB_removeB_A(), Result.False));
			printTest("AB_removeB_A_testSize", testSize(AB_removeB_A(), 1));
			printTest("AB_removeB_A_testToString", testToString(AB_removeB_A(), Result.ValidString));
			// UnorderedListADT
			printTest("AB_removeB_A_testAddToFrontB", testAddToFront(AB_removeB_A(), ELEMENT_B, Result.NoException));
			printTest("AB_removeB_A_testAddToRearB", testAddToRear(AB_removeB_A(), ELEMENT_A, Result.NoException));
			printTest(	"AB_removeB_A_testAddAfterCB", testAddAfter(AB_removeB_A(), ELEMENT_C, ELEMENT_B, Result.ElementNotFound));
			printTest(	"AB_removeB_A_testAddAfterAB", testAddAfter(AB_removeB_A(), ELEMENT_A, ELEMENT_B, Result.NoException));
			// IndexedListADT
			printTest("AB_removeB_A_testAddAtIndexNeg1B", testAddAtIndex(AB_removeB_A(), -1, ELEMENT_B, Result.IndexOutOfBounds));
			printTest("AB_removeB_A_testAddAtIndex0B", testAddAtIndex(AB_removeB_A(), 0, ELEMENT_B, Result.NoException));
			printTest("AB_removeB_A_testAddAtIndex1B", testAddAtIndex(AB_removeB_A(), 1, ELEMENT_B, Result.NoException));
			printTest("AB_removeB_A_testSetNeg1B", testSet(AB_removeB_A(), -1, ELEMENT_B, Result.IndexOutOfBounds));
			printTest("AB_removeB_A_testSet0B", testSet(AB_removeB_A(), 0, ELEMENT_B, Result.NoException));
			printTest("AB_removeB_A_testSet1B", testSet(AB_removeB_A(), 1, ELEMENT_B, Result.IndexOutOfBounds));
			printTest("AB_removeB_A_testAddB", testAdd(AB_removeB_A(), ELEMENT_B, Result.NoException));
			printTest("AB_removeB_A_testGetNeg1", testGet(AB_removeB_A(), -1, null, Result.IndexOutOfBounds));
			printTest("AB_removeB_A_testGet0", testGet(AB_removeB_A(), 0, ELEMENT_A, Result.MatchingValue));
			printTest("AB_removeB_A_testGet1", testGet(AB_removeB_A(), 1, null, Result.IndexOutOfBounds));
			printTest("AB_removeB_A_testIndexOfA", testIndexOf(AB_removeB_A(), ELEMENT_A, 0));
			printTest("AB_removeB_A_testIndexOfB", testIndexOf(AB_removeB_A(), ELEMENT_B, -1));
			printTest("AB_removeB_A_testRemoveNeg1", testRemoveIndex(AB_removeB_A(), -1, null, Result.IndexOutOfBounds));
			printTest("AB_removeB_A_testRemove0", testRemoveIndex(AB_removeB_A(), 0, ELEMENT_A, Result.MatchingValue));
			printTest("AB_removeB_A_testRemove1", testRemoveIndex(AB_removeB_A(), 1, null, Result.IndexOutOfBounds));
			// Iterator
			printTest("AB_removeB_A_testIterator", testIterator(AB_removeB_A(), Result.NoException));
			printTest("AB_removeB_A_testIteratorHasNext", testIteratorHasNext(AB_removeB_A().iterator(), Result.True));
			printTest("AB_removeB_A_testIteratorNext", testIteratorNext(AB_removeB_A().iterator(), ELEMENT_A, Result.MatchingValue));
			printTest("AB_removeB_A_testIteratorRemove", testIteratorRemove(AB_removeB_A().iterator(), Result.IllegalState));
			printTest("AB_removeB_A_iteratorNext_testIteratorHasNext", testIteratorHasNext(iteratorAfterNext(AB_removeB_A(), 1), Result.False));
			printTest("AB_removeB_A_iteratorNext_testIteratorNext", testIteratorNext(iteratorAfterNext(AB_removeB_A(), 1), null, Result.NoSuchElement));
			printTest("AB_removeB_A_iteratorNext_testIteratorRemove", testIteratorRemove(iteratorAfterNext(AB_removeB_A(), 1), Result.NoException));
			printTest("AB_removeB_A_iteratorNextRemove_testIteratorHasNext", testIteratorHasNext(iteratorAfterRemove(iteratorAfterNext(AB_removeB_A(), 1)), Result.False));
			printTest("AB_removeB_A_iteratorNextRemove_testIteratorNext", testIteratorNext(iteratorAfterRemove(iteratorAfterNext(AB_removeB_A(), 1)), null, Result.NoSuchElement));
			printTest("AB_removeB_A_iteratorNextRemove_testIteratorRemove", testIteratorRemove(iteratorAfterRemove(iteratorAfterNext(AB_removeB_A(), 1)), Result.IllegalState));
			printTest("AB_removeB_A_iteratorTestRealRemove",testIteratorRealRemove(AB_removeB_A(), 1, "[]", Result.MatchingValue));
			//ListIterator
			if(newList().getClass()==testDLL().getClass()){
			printTest("AB_removeB_A_testListIteratorUnIndexed", testListIteratorUnIndexed(AB_removeB_A(), Result.NoException));
			printTest("AB_removeB_A_testListIteratorIndexedNeg1", testListIteratorIndexed(AB_removeB_A(), -1, Result.IndexOutOfBounds));
			printTest("AB_removeB_A_testListIteratorIndexed0", testListIteratorIndexed(AB_removeB_A(), 0, Result.NoException));
			printTest("AB_removeB_A_testListIteratorIndexed1", testListIteratorIndexed(AB_removeB_A(), 1, Result.NoException));
			printTest("AB_removeB_A_testListIteratorIndexed2", testListIteratorIndexed(AB_removeB_A(), 2, Result.IndexOutOfBounds));
			
			printTest("AB_removeB_A_testListIteratorHasNext", testListIteratorHasNext(AB_removeB_A(),null, null, Result.True));
			printTest("AB_removeB_A_testListIteratorNext", testListIteratorNext(AB_removeB_A(),null, null, ELEMENT_A, Result.MatchingValue));
			printTest("AB_removeB_A_testListIteratorNextIndex", testListIteratorNextIndex(AB_removeB_A(),null, null, 0, Result.MatchingValue));
			printTest("AB_removeB_A_testListIteratorHasPrevious", testListIteratorHasPrevious(AB_removeB_A(),null, null, Result.False));
			printTest("AB_removeB_A_testListIteratorPrevious", testListIteratorPrevious(AB_removeB_A(),null, null, null, Result.NoSuchElement));
			printTest("AB_removeB_A_testListIteratorPreviousIndex", testListIteratorPreviousIndex(AB_removeB_A(),null, null, -1, Result.MatchingValue));
			printTest("AB_removeB_A_testListIteratorRemove", testListIteratorRemove(AB_removeB_A(),null, null, Result.IllegalState));
			printTest("AB_removeB_A_testListIteratorRealRemove", testListIteratorRealRemoveAfterNext(AB_removeB_A(), null, 0, "[1]", Result.MatchingValue));
			printTest("AB_removeB_A_testListIteratorSet", testListIteratorSet(AB_removeB_A(), null,null, ELEMENT_D, Result.IllegalState));
			printTest("AB_removeB_A_testListIteratorRealSet", testListIteratorRealSetAfterNext(AB_removeB_A(), null, 0, ELEMENT_D, "[1]", Result.MatchingValue));
			printTest("AB_removeB_A_testListIteratorAdd", testListIteratorAdd(AB_removeB_A(),null, null, ELEMENT_D, Result.NoException));
			printTest("AB_removeB_A_testListIteratorRealAdd", testListIteratorRealAddAfterNext(AB_removeB_A(), null, 0, ELEMENT_D, "[4, 1]", Result.MatchingValue));
			
			printTest("AB_removeB_A_testListIteratorHasNextAfterNext", testListIteratorHasNext(null, getListIteratorAfterCall(AB_removeB_A(), "next", null, null), null, Result.False));
			printTest("AB_removeB_A_testListIteratorNextAfterNext", testListIteratorNext(null,getListIteratorAfterCall(AB_removeB_A(), "next", null, null),null, null, Result.NoSuchElement));
			printTest("AB_removeB_A_testListIteratorNextIndexAfterNext", testListIteratorNextIndex(null,getListIteratorAfterCall(AB_removeB_A(), "next", null, null),null, 1, Result.MatchingValue));
			printTest("AB_removeB_A_testListIteratorHasPreviousAfterNext", testListIteratorHasPrevious(null,getListIteratorAfterCall(AB_removeB_A(), "next", null, null),null, Result.True));
			printTest("AB_removeB_A_testListIteratorPreviousAfterNext", testListIteratorPrevious(null, getListIteratorAfterCall(AB_removeB_A(), "next", null, null), null, ELEMENT_A, Result.MatchingValue));
			printTest("AB_removeB_A_testListIteratorPreviousIndexAfterNext", testListIteratorPreviousIndex(null, getListIteratorAfterCall(AB_removeB_A(), "next", null, null), null, 0, Result.MatchingValue));
			printTest("AB_removeB_A_testListIteratorRemoveAfterNext", testListIteratorRemove(null, getListIteratorAfterCall(AB_removeB_A(), "next", null, null), null, Result.NoException));
			printTest("AB_removeB_A_testListIteratorRealRemoveAfterNext", testListIteratorRealRemoveAfterNext(AB_removeB_A(), null, 1, "[]", Result.MatchingValue));
			printTest("AB_removeB_A_testListIteratorSetAfterNext", testListIteratorSet(null, getListIteratorAfterCall(AB_removeB_A(), "next", null, null), null, ELEMENT_D, Result.NoException));
			printTest("AB_removeB_A_testListIteratorRealSetAfterNext", testListIteratorRealSetAfterNext(AB_removeB_A(), null, 1, ELEMENT_D, "[4]", Result.MatchingValue));
			printTest("AB_removeB_A_testListIteratorAddAfterNext", testListIteratorAdd(null, getListIteratorAfterCall(AB_removeB_A(), "next", null, null), null, ELEMENT_D, Result.NoException));
			printTest("AB_removeB_A_testListIteratorRealAddAfterNext", testListIteratorRealAddAfterNext(AB_removeB_A(), null, 1, ELEMENT_D, "[1, 4]", Result.MatchingValue));
			
			printTest("AB_removeB_A_testListIterator0HasNext", testListIteratorHasNext(AB_removeB_A(), null, 0, Result.True));
			printTest("AB_removeB_A_testListIterator0Next", testListIteratorNext(AB_removeB_A(), null, 0, ELEMENT_A, Result.MatchingValue));
			printTest("AB_removeB_A_testListIterator0NextIndex", testListIteratorNextIndex(AB_removeB_A(), null, 0, 0, Result.MatchingValue));
			printTest("AB_removeB_A_testListIterator0HasPrevious", testListIteratorHasPrevious(AB_removeB_A(), null, 0, Result.False));
			printTest("AB_removeB_A_testListIterator0Previous", testListIteratorPrevious(AB_removeB_A(), null, 0, null, Result.NoSuchElement));
			printTest("AB_removeB_A_testListIterator0PreviousIndex", testListIteratorPreviousIndex(AB_removeB_A(), null, 0, -1, Result.MatchingValue));
			printTest("AB_removeB_A_testListIterator0Remove", testListIteratorRemove(AB_removeB_A(), null, 0, Result.IllegalState));
			printTest("AB_removeB_A_testListIterator0RealRemove", testListIteratorRealRemoveAfterNext(AB_removeB_A(), 0, 0, "[1]", Result.MatchingValue));
			printTest("AB_removeB_A_testListIterator0Set", testListIteratorSet(AB_removeB_A(), null, 0, ELEMENT_D, Result.IllegalState));
			printTest("AB_removeB_A_testListIterator0RealSet", testListIteratorRealSetAfterNext(AB_removeB_A(), 0, 0, ELEMENT_D, "[1]", Result.MatchingValue));
			printTest("AB_removeB_A_testListIterator0Add", testListIteratorAdd(AB_removeB_A(), null, 0, ELEMENT_D, Result.NoException));
			printTest("AB_removeB_A_testListIterator0RealAdd", testListIteratorRealAddAfterNext(AB_removeB_A(), 0, 0, ELEMENT_D, "[4, 1]", Result.MatchingValue));
			
			printTest("AB_removeB_A_testListIterator0HasNextAfterNext", testListIteratorHasNext(null, getListIteratorAfterCall(AB_removeB_A(), "next", 0, null), null, Result.False));
			printTest("AB_removeB_A_testListIterator0NextAfterNext", testListIteratorNext(null, getListIteratorAfterCall(AB_removeB_A(), "next", 0, null), null, null, Result.NoSuchElement));
			printTest("AB_removeB_A_testListIterator0NextIndexAfterNext", testListIteratorNextIndex(null, getListIteratorAfterCall(AB_removeB_A(), "next", 0, null), null, 1, Result.MatchingValue));
			printTest("AB_removeB_A_testListIterator0HasPreviousAfterNext", testListIteratorHasPrevious(null, getListIteratorAfterCall(AB_removeB_A(), "next", 0, null), null, Result.True));
			printTest("AB_removeB_A_testListIterator0PreviousAfterNext", testListIteratorPrevious(null, getListIteratorAfterCall(AB_removeB_A(), "next", 0, null), null, ELEMENT_A, Result.MatchingValue));
			printTest("AB_removeB_A_testListIterator0PreviousIndexAfterNext", testListIteratorPreviousIndex(null, getListIteratorAfterCall(AB_removeB_A(), "next", 0, null), null, 0, Result.MatchingValue));
			printTest("AB_removeB_A_testListIterator0RemoveAfterNext", testListIteratorRemove(null, getListIteratorAfterCall(AB_removeB_A(), "next", 0, null), null, Result.NoException));
			printTest("AB_removeB_A_testListIterator0RealRemoveAfterNext", testListIteratorRealRemoveAfterNext(AB_removeB_A(), 0, 1, "[]", Result.MatchingValue));
			printTest("AB_removeB_A_testListIterator0SetAfterNext", testListIteratorSet(null, getListIteratorAfterCall(AB_removeB_A(), "next", 0, null), null, ELEMENT_D, Result.NoException));
			printTest("AB_removeB_A_testListIterator0RealSetAfterNext", testListIteratorRealSetAfterNext(AB_removeB_A(), 0, 1, ELEMENT_D, "[4]", Result.MatchingValue));
			printTest("AB_removeB_A_testListIterator0AddAfterNext", testListIteratorAdd(null, getListIteratorAfterCall(AB_removeB_A(), "next", 0, null), null, ELEMENT_D, Result.NoException));
			printTest("AB_removeB_A_testListIterator0RealAddAfterNext", testListIteratorRealAddAfterNext(AB_removeB_A(), 0, 1, ELEMENT_D, "[1, 4]", Result.MatchingValue));
			
			printTest("AB_removeB_A_testListIterator1HasNext", testListIteratorHasNext(AB_removeB_A(), null, 1, Result.False));
			printTest("AB_removeB_A_testListIterator1Next", testListIteratorNext(AB_removeB_A(), null, 1, null, Result.NoSuchElement));
			printTest("AB_removeB_A_testListIterator1NextIndex", testListIteratorNextIndex(AB_removeB_A(), null, 1, 1, Result.MatchingValue));
			printTest("AB_removeB_A_testListIterator1HasPrevious", testListIteratorHasPrevious(AB_removeB_A(), null, 1, Result.True));
			printTest("AB_removeB_A_testListIterator1Previous", testListIteratorPrevious(AB_removeB_A(), null, 1, ELEMENT_A, Result.MatchingValue));
			printTest("AB_removeB_A_testListIterator1PreviousIndex", testListIteratorPreviousIndex(AB_removeB_A(), null, 1, 0, Result.MatchingValue));
			printTest("AB_removeB_A_testListIterator1Remove", testListIteratorRemove(AB_removeB_A(), null, 1, Result.IllegalState));
			printTest("AB_removeB_A_testListIterator1RealRemove", testListIteratorRealRemoveAfterNext(AB_removeB_A(), 1, 0, "[1]", Result.MatchingValue));
			printTest("AB_removeB_A_testListIterator1Set", testListIteratorSet(AB_removeB_A(), null, 1, ELEMENT_D, Result.IllegalState));
			printTest("AB_removeB_A_testListIterator1RealSet", testListIteratorRealSetAfterNext(AB_removeB_A(), 1, 0, ELEMENT_D, "[1]", Result.MatchingValue));
			printTest("AB_removeB_A_testListIterator1Add", testListIteratorAdd(AB_removeB_A(), null, 1, ELEMENT_D, Result.NoException));
			printTest("AB_removeB_A_testListIterator1RealAdd", testListIteratorRealAddAfterNext(AB_removeB_A(), 1, 0, ELEMENT_D, "[1, 4]", Result.MatchingValue));
			
			printTest("AB_removeB_A_testListIterator1HasNextAfterPrev", testListIteratorHasNext(null, getListIteratorAfterCall(AB_removeB_A(), "previous", 1, null), 1, Result.True));
			printTest("AB_removeB_A_testListIterator1NextAfterPrev", testListIteratorNext(null, getListIteratorAfterCall(AB_removeB_A(), "previous", 1, null), 1, ELEMENT_A, Result.MatchingValue));
			printTest("AB_removeB_A_testListIterator1NextIndexAfterPrev", testListIteratorNextIndex(null, getListIteratorAfterCall(AB_removeB_A(), "previous", 1, null), 1, 0, Result.MatchingValue));
			printTest("AB_removeB_A_testListIterator1HasPreviousAfterPrev", testListIteratorHasPrevious(null, getListIteratorAfterCall(AB_removeB_A(), "previous", 1, null), 1, Result.False));
			printTest("AB_removeB_A_testListIterator1PreviousAfterPrev", testListIteratorPrevious(null, getListIteratorAfterCall(AB_removeB_A(), "previous", 1, null), 1, null, Result.NoSuchElement));
			printTest("AB_removeB_A_testListIterator1PreviousIndexAfterPrev", testListIteratorPreviousIndex(null, getListIteratorAfterCall(AB_removeB_A(), "previous", 1, null), 1, -1, Result.MatchingValue));
			printTest("AB_removeB_A_testListIterator1RemoveAfterPrev", testListIteratorRemove(null, getListIteratorAfterCall(AB_removeB_A(), "previous", 1, null), 1, Result.NoException));
			printTest("AB_removeB_A_testListIterator1RealRemoveAfterPrev", testListIteratorRealRemoveAfterPrev(AB_removeB_A(), 1, 1, "[]", Result.MatchingValue));
			printTest("AB_removeB_A_testListIterator1SetAfterPrev", testListIteratorSet(null, getListIteratorAfterCall(AB_removeB_A(), "previous", 1, null), 1, ELEMENT_D, Result.NoException));
			printTest("AB_removeB_A_testListIterator1RealSetAfterPrev", testListIteratorRealSetAfterPrev(AB_removeB_A(), 1, 1, ELEMENT_D, "[4]", Result.MatchingValue));
			printTest("AB_removeB_A_testListIterator1AddAfterPrev", testListIteratorAdd(null, getListIteratorAfterCall(AB_removeB_A(), "previous", 1, null), 1, ELEMENT_D, Result.NoException));
			printTest("AB_removeB_A_testListIterator1RealAddAfterPrev", testListIteratorRealAddAfterPrev(AB_removeB_A(), 1, 1, ELEMENT_D, "[4, 1]", Result.MatchingValue));
			}
		} catch (Exception e) {
			System.out.printf("***UNABLE TO RUN/COMPLETE %s***\n", "test_AB_removeB_A");
			e.printStackTrace();
		}
	}
	
	//////////////////////////////////////
	// SCENARIO: [A,B] -> addToFront(C) -> [C,A,B]
	//  XXX SCENARIO 20
	//////////////////////////////////////
	private ListADT<Integer> AB_addToFrontC_CAB() {
		// cast to UnorderedListADT to make addToFront() available
		UnorderedListADT<Integer> list = (UnorderedListADT<Integer>) newList(); 
		list.addToFront(ELEMENT_A);
		list.addToRear(ELEMENT_B);
		list.addToFront(ELEMENT_C);
		return list;
	}
	private void test_AB_addToFrontC_CAB(){
		try {
			// ListADT
			printTest("AB_addToFrontC_CAB_testRemoveFirst", testRemoveFirst(AB_addToFrontC_CAB(), ELEMENT_C, Result.MatchingValue));
			printTest("AB_addToFrontC_CAB_testRemoveLast", testRemoveLast(AB_addToFrontC_CAB(), ELEMENT_B, Result.MatchingValue));
			printTest("AB_addToFrontC_CAB_testRemoveA", testRemoveElement(AB_addToFrontC_CAB(), ELEMENT_A, Result.MatchingValue));
			printTest("AB_addToFrontC_CAB_testRemoveB", testRemoveElement(AB_addToFrontC_CAB(), ELEMENT_B, Result.MatchingValue));
			printTest("AB_addToFrontC_CAB_testRemoveC", testRemoveElement(AB_addToFrontC_CAB(), ELEMENT_C, Result.MatchingValue));
			printTest("AB_addToFrontC_CAB_testRemoveD", testRemoveElement(AB_addToFrontC_CAB(), ELEMENT_D, Result.ElementNotFound));
			printTest("AB_addToFrontC_CAB_testFirst", testFirst(AB_addToFrontC_CAB(), ELEMENT_C, Result.MatchingValue));
			printTest("AB_addToFrontC_CAB_testLast", testLast(AB_addToFrontC_CAB(), ELEMENT_B, Result.MatchingValue));
			printTest("AB_addToFrontC_CAB_testContainsA", testContains(AB_addToFrontC_CAB(), ELEMENT_A, Result.True));
			printTest("AB_addToFrontC_CAB_testContainsB", testContains(AB_addToFrontC_CAB(), ELEMENT_B, Result.True));
			printTest("AB_addToFrontC_CAB_testContainsC", testContains(AB_addToFrontC_CAB(), ELEMENT_C, Result.True));
			printTest("AB_addToFrontC_CAB_testContainsD", testContains(AB_addToFrontC_CAB(), ELEMENT_D, Result.False));
			printTest("AB_addToFrontC_CAB_testIsEmpty", testIsEmpty(AB_addToFrontC_CAB(), Result.False));
			printTest("AB_addToFrontC_CAB_testSize", testSize(AB_addToFrontC_CAB(), 3));
			printTest("AB_addToFrontC_CAB_testToString", testToString(AB_addToFrontC_CAB(), Result.ValidString));
			// UnorderedListADT
			printTest("AB_addToFrontC_CAB_testAddToFrontB", testAddToFront(AB_addToFrontC_CAB(), ELEMENT_D, Result.NoException));
			printTest("AB_addToFrontC_CAB_testAddToRearB", testAddToRear(AB_addToFrontC_CAB(), ELEMENT_D, Result.NoException));
			printTest(	"AB_addToFrontC_CAB_testAddAfterAD", testAddAfter(AB_addToFrontC_CAB(), ELEMENT_A, ELEMENT_D, Result.NoException));
			printTest(	"AB_addToFrontC_CAB_testAddAfterBD", testAddAfter(AB_addToFrontC_CAB(), ELEMENT_B, ELEMENT_D, Result.NoException));
			printTest(	"AB_addToFrontC_CAB_testAddAfterCD", testAddAfter(AB_addToFrontC_CAB(), ELEMENT_C, ELEMENT_D, Result.NoException));
			printTest(	"AB_addToFrontC_CAB_testAddAfterDD", testAddAfter(AB_addToFrontC_CAB(), ELEMENT_D, ELEMENT_D, Result.ElementNotFound));
			// IndexedListADT
			printTest("AB_addToFrontC_CAB_testAddAtIndexNeg1D", testAddAtIndex(AB_addToFrontC_CAB(), -1, ELEMENT_D, Result.IndexOutOfBounds));
			printTest("AB_addToFrontC_CAB_testAddAtIndex0D", testAddAtIndex(AB_addToFrontC_CAB(), 0, ELEMENT_D, Result.NoException));
			printTest("AB_addToFrontC_CAB_testAddAtIndex1D", testAddAtIndex(AB_addToFrontC_CAB(), 1, ELEMENT_D, Result.NoException));
			printTest("AB_addToFrontC_CAB_testAddAtIndex2D", testAddAtIndex(AB_addToFrontC_CAB(), 2, ELEMENT_D, Result.NoException));
			printTest("AB_addToFrontC_CAB_testAddAtIndex3D", testAddAtIndex(AB_addToFrontC_CAB(), 3, ELEMENT_D, Result.NoException));
			printTest("AB_addToFrontC_CAB_testAddAtIndex4D", testAddAtIndex(AB_addToFrontC_CAB(), 4, ELEMENT_D, Result.IndexOutOfBounds));
			printTest("AB_addToFrontC_CAB_testSetNeg1D", testSet(AB_addToFrontC_CAB(), -1, ELEMENT_D, Result.IndexOutOfBounds));
			printTest("AB_addToFrontC_CAB_testSet0D", testSet(AB_addToFrontC_CAB(), 0, ELEMENT_D, Result.NoException));
			printTest("AB_addToFrontC_CAB_testSet1D", testSet(AB_addToFrontC_CAB(), 1, ELEMENT_D, Result.NoException));
			printTest("AB_addToFrontC_CAB_testSet2D", testSet(AB_addToFrontC_CAB(), 2, ELEMENT_D, Result.NoException));
			printTest("AB_addToFrontC_CAB_testSet3D", testSet(AB_addToFrontC_CAB(), 3, ELEMENT_D, Result.IndexOutOfBounds));
			printTest("AB_addToFrontC_CAB_testAddD", testAdd(AB_addToFrontC_CAB(), ELEMENT_D, Result.NoException));
			printTest("AB_addToFrontC_CAB_testGetNeg1", testGet(AB_addToFrontC_CAB(), -1, null, Result.IndexOutOfBounds));
			printTest("AB_addToFrontC_CAB_testGet0", testGet(AB_addToFrontC_CAB(), 0, ELEMENT_C, Result.MatchingValue));
			printTest("AB_addToFrontC_CAB_testGet1", testGet(AB_addToFrontC_CAB(), 1, ELEMENT_A, Result.MatchingValue));
			printTest("AB_addToFrontC_CAB_testGet2", testGet(AB_addToFrontC_CAB(), 2, ELEMENT_B, Result.MatchingValue));
			printTest("AB_addToFrontC_CAB_testGet3", testGet(AB_addToFrontC_CAB(), 3, null, Result.IndexOutOfBounds));
			printTest("AB_addToFrontC_CAB_testIndexOfA", testIndexOf(AB_addToFrontC_CAB(), ELEMENT_A, 1));
			printTest("AB_addToFrontC_CAB_testIndexOfB", testIndexOf(AB_addToFrontC_CAB(), ELEMENT_B, 2));
			printTest("AB_addToFrontC_CAB_testIndexOfC", testIndexOf(AB_addToFrontC_CAB(), ELEMENT_C, 0));
			printTest("AB_addToFrontC_CAB_testIndexOfD", testIndexOf(AB_addToFrontC_CAB(), ELEMENT_D, -1));
			printTest("AB_addToFrontC_CAB_testRemoveNeg1", testRemoveIndex(AB_addToFrontC_CAB(), -1, null, Result.IndexOutOfBounds));
			printTest("AB_addToFrontC_CAB_testRemove0", testRemoveIndex(AB_addToFrontC_CAB(), 0, ELEMENT_C, Result.MatchingValue));
			printTest("AB_addToFrontC_CAB_testRemove0", testRemoveIndex(AB_addToFrontC_CAB(), 1, ELEMENT_A, Result.MatchingValue));
			printTest("AB_addToFrontC_CAB_testRemove0", testRemoveIndex(AB_addToFrontC_CAB(), 2, ELEMENT_B, Result.MatchingValue));
			printTest("AB_addToFrontC_CAB_testRemove3", testRemoveIndex(AB_addToFrontC_CAB(), 3, null, Result.IndexOutOfBounds));
			// Iterator
			printTest("AB_addToFrontC_CAB_testIterator", testIterator(AB_addToFrontC_CAB(), Result.NoException));
			printTest("AB_addToFrontC_CAB_testIteratorHasNext", testIteratorHasNext(AB_addToFrontC_CAB().iterator(), Result.True));
			printTest("AB_addToFrontC_CAB_testIteratorNext", testIteratorNext(AB_addToFrontC_CAB().iterator(), ELEMENT_C, Result.MatchingValue));
			printTest("AB_addToFrontC_CAB_testIteratorRemove", testIteratorRemove(AB_addToFrontC_CAB().iterator(), Result.IllegalState));
			printTest("AB_addToFrontC_CAB_testIteratorHasNextAfterNext", testIteratorHasNext(iteratorAfterNext(AB_addToFrontC_CAB(),1), Result.True));
			printTest("AB_addToFrontC_CAB_testIteratorNextAfterNext", testIteratorNext(iteratorAfterNext(AB_addToFrontC_CAB(),1), ELEMENT_A, Result.MatchingValue));
			printTest("AB_addToFrontC_CAB_testIteratorRemoveAfterNext", testIteratorRemove(iteratorAfterNext(AB_addToFrontC_CAB(),1), Result.NoException));
			printTest("AB_addToFrontC_CAB_testIteratorHasNextAfterNext2", testIteratorHasNext(iteratorAfterNext(AB_addToFrontC_CAB(),2), Result.True));
			printTest("AB_addToFrontC_CAB_testIteratorHasNextAfterNext3", testIteratorHasNext(iteratorAfterNext(AB_addToFrontC_CAB(),3), Result.False));
			printTest("AB_addToFrontC_CAB_testIteratorNextAfterNext2", testIteratorNext(iteratorAfterNext(AB_addToFrontC_CAB(),2), ELEMENT_B, Result.MatchingValue));
			printTest("AB_addToFrontC_CAB_testIteratorNextAfterNext3", testIteratorNext(iteratorAfterNext(AB_addToFrontC_CAB(),3), null, Result.NoSuchElement));
			printTest("AB_addToFrontC_CAB_testIteratorRemoveAfterNext2", testIteratorRemove(iteratorAfterNext(AB_addToFrontC_CAB(),2), Result.NoException));
			printTest("AB_addToFrontC_CAB_testIteratorRemoveAfterNext3", testIteratorRemove(iteratorAfterNext(AB_addToFrontC_CAB(),3), Result.NoException));
			printTest("AB_addToFrontC_CAB_testIteratorNextAfterRemoveNext", testIteratorNext(iteratorAfterRemove(iteratorAfterNext(AB_addToFrontC_CAB(),1)),ELEMENT_A,  Result.MatchingValue));
			printTest("AB_addToFrontC_CAB_testIteratorNextAfterRemoveNext2", testIteratorNext(iteratorAfterRemove(iteratorAfterNext(AB_addToFrontC_CAB(),2)),ELEMENT_B,  Result.MatchingValue));
			printTest("AB_addToFrontC_CAB_testIteratorNextAfterRemoveNext3", testIteratorNext(iteratorAfterRemove(iteratorAfterNext(AB_addToFrontC_CAB(),3)),null,  Result.NoSuchElement));
			printTest("AB_addToFrontC_CAB_testIteratorHasNextAfterRemoveNext",testIteratorHasNext(iteratorAfterRemove(iteratorAfterNext(AB_addToFrontC_CAB(),1)),Result.True));
			printTest("AB_addToFrontC_CAB_testIteratorHasNextAfterRemoveNext2",testIteratorHasNext(iteratorAfterRemove(iteratorAfterNext(AB_addToFrontC_CAB(),2)),Result.True));
			printTest("AB_addToFrontC_CAB_testIteratorHasNextAfterRemoveNext3",testIteratorHasNext(iteratorAfterRemove(iteratorAfterNext(AB_addToFrontC_CAB(),3)),Result.False));
			printTest("AB_addToFrontC_CAB_iteratorTestRealRemove",testIteratorRealRemove(AB_addToFrontC_CAB(), 1, "[1, 2]", Result.MatchingValue));
			printTest("AB_addToFrontC_CAB_iteratorTestRealRemove2",testIteratorRealRemove(AB_addToFrontC_CAB(), 2, "[3, 2]", Result.MatchingValue));
			printTest("AB_addToFrontC_CAB_iteratorTestRealRemove3",testIteratorRealRemove(AB_addToFrontC_CAB(), 3, "[3, 1]", Result.MatchingValue));
		} catch (Exception e) {
			System.out.printf("***UNABLE TO RUN/COMPLETE %s***\n", "test_AB_addToFrontC_CAB");
			e.printStackTrace();
		}
	}
	
	//////////////////////////////////////
	// SCENARIO: [A,B] -> addToRear(C) -> [A,B,C]
	//  XXX SCENARIO 21
	//////////////////////////////////////
	private ListADT<Integer> AB_addToRearC_ABC() {
		// cast to UnorderedListADT to make addToFront() available
		UnorderedListADT<Integer> list = (UnorderedListADT<Integer>) newList(); 
		list.addToFront(ELEMENT_A);
		list.addToRear(ELEMENT_B);
		list.addToRear(ELEMENT_C);
		return list;
	}
	private void test_AB_addToRearC_ABC(){
		try {
			// ListADT
			printTest("AB_addToRearC_ABC_testRemoveFirst", testRemoveFirst(AB_addToRearC_ABC(), ELEMENT_A, Result.MatchingValue));
			printTest("AB_addToRearC_ABC_testRemoveLast", testRemoveLast(AB_addToRearC_ABC(), ELEMENT_C, Result.MatchingValue));
			printTest("AB_addToRearC_ABC_testRemoveA", testRemoveElement(AB_addToRearC_ABC(), ELEMENT_A, Result.MatchingValue));
			printTest("AB_addToRearC_ABC_testRemoveB", testRemoveElement(AB_addToRearC_ABC(), ELEMENT_B, Result.MatchingValue));
			printTest("AB_addToRearC_ABC_testRemoveC", testRemoveElement(AB_addToRearC_ABC(), ELEMENT_C, Result.MatchingValue));
			printTest("AB_addToRearC_ABC_testRemoveD", testRemoveElement(AB_addToRearC_ABC(), ELEMENT_D, Result.ElementNotFound));
			printTest("AB_addToRearC_ABC_testFirst", testFirst(AB_addToRearC_ABC(), ELEMENT_A, Result.MatchingValue));
			printTest("AB_addToRearC_ABC_testLast", testLast(AB_addToRearC_ABC(), ELEMENT_C, Result.MatchingValue));
			printTest("AB_addToRearC_ABC_testContainsA", testContains(AB_addToRearC_ABC(), ELEMENT_A, Result.True));
			printTest("AB_addToRearC_ABC_testContainsB", testContains(AB_addToRearC_ABC(), ELEMENT_B, Result.True));
			printTest("AB_addToRearC_ABC_testContainsC", testContains(AB_addToRearC_ABC(), ELEMENT_C, Result.True));
			printTest("AB_addToRearC_ABC_testContainsD", testContains(AB_addToRearC_ABC(), ELEMENT_D, Result.False));
			printTest("AB_addToRearC_ABC_testIsEmpty", testIsEmpty(AB_addToRearC_ABC(), Result.False));
			printTest("AB_addToRearC_ABC_testSize", testSize(AB_addToRearC_ABC(), 3));
			printTest("AB_addToRearC_ABC_testToString", testToString(AB_addToRearC_ABC(), Result.ValidString));
			// UnorderedListADT
			printTest("AB_addToRearC_ABC_testAddToFrontB", testAddToFront(AB_addToRearC_ABC(), ELEMENT_D, Result.NoException));
			printTest("AB_addToRearC_ABC_testAddToRearB", testAddToRear(AB_addToRearC_ABC(), ELEMENT_D, Result.NoException));
			printTest(	"AB_addToRearC_ABC_testAddAfterAD", testAddAfter(AB_addToRearC_ABC(), ELEMENT_A, ELEMENT_D, Result.NoException));
			printTest(	"AB_addToRearC_ABC_testAddAfterBD", testAddAfter(AB_addToRearC_ABC(), ELEMENT_B, ELEMENT_D, Result.NoException));
			printTest(	"AB_addToRearC_ABC_testAddAfterCD", testAddAfter(AB_addToRearC_ABC(), ELEMENT_C, ELEMENT_D, Result.NoException));
			printTest(	"AB_addToRearC_ABC_testAddAfterDD", testAddAfter(AB_addToRearC_ABC(), ELEMENT_D, ELEMENT_D, Result.ElementNotFound));
			// IndexedListADT
			printTest("AB_addToRearC_ABC_testAddAtIndexNeg1D", testAddAtIndex(AB_addToRearC_ABC(), -1, ELEMENT_D, Result.IndexOutOfBounds));
			printTest("AB_addToRearC_ABC_testAddAtIndex0D", testAddAtIndex(AB_addToRearC_ABC(), 0, ELEMENT_D, Result.NoException));
			printTest("AB_addToRearC_ABC_testAddAtIndex1D", testAddAtIndex(AB_addToRearC_ABC(), 1, ELEMENT_D, Result.NoException));
			printTest("AB_addToRearC_ABC_testAddAtIndex2D", testAddAtIndex(AB_addToRearC_ABC(), 2, ELEMENT_D, Result.NoException));
			printTest("AB_addToRearC_ABC_testAddAtIndex3D", testAddAtIndex(AB_addToRearC_ABC(), 3, ELEMENT_D, Result.NoException));
			printTest("AB_addToRearC_ABC_testAddAtIndex4D", testAddAtIndex(AB_addToRearC_ABC(), 4, ELEMENT_D, Result.IndexOutOfBounds));
			printTest("AB_addToRearC_ABC_testSetNeg1D", testSet(AB_addToRearC_ABC(), -1, ELEMENT_D, Result.IndexOutOfBounds));
			printTest("AB_addToRearC_ABC_testSet0D", testSet(AB_addToRearC_ABC(), 0, ELEMENT_D, Result.NoException));
			printTest("AB_addToRearC_ABC_testSet1D", testSet(AB_addToRearC_ABC(), 1, ELEMENT_D, Result.NoException));
			printTest("AB_addToRearC_ABC_testSet2D", testSet(AB_addToRearC_ABC(), 2, ELEMENT_D, Result.NoException));
			printTest("AB_addToRearC_ABC_testSet3D", testSet(AB_addToRearC_ABC(), 3, ELEMENT_D, Result.IndexOutOfBounds));
			printTest("AB_addToRearC_ABC_testAddD", testAdd(AB_addToRearC_ABC(), ELEMENT_D, Result.NoException));
			printTest("AB_addToRearC_ABC_testGetNeg1", testGet(AB_addToRearC_ABC(), -1, null, Result.IndexOutOfBounds));
			printTest("AB_addToRearC_ABC_testGet0", testGet(AB_addToRearC_ABC(), 0, ELEMENT_A, Result.MatchingValue));
			printTest("AB_addToRearC_ABC_testGet1", testGet(AB_addToRearC_ABC(), 1, ELEMENT_B, Result.MatchingValue));
			printTest("AB_addToRearC_ABC_testGet2", testGet(AB_addToRearC_ABC(), 2, ELEMENT_C, Result.MatchingValue));
			printTest("AB_addToRearC_ABC_testGet3", testGet(AB_addToRearC_ABC(), 3, null, Result.IndexOutOfBounds));
			printTest("AB_addToRearC_ABC_testIndexOfA", testIndexOf(AB_addToRearC_ABC(), ELEMENT_A, 0));
			printTest("AB_addToRearC_ABC_testIndexOfB", testIndexOf(AB_addToRearC_ABC(), ELEMENT_B, 1));
			printTest("AB_addToRearC_ABC_testIndexOfC", testIndexOf(AB_addToRearC_ABC(), ELEMENT_C, 2));
			printTest("AB_addToRearC_ABC_testIndexOfD", testIndexOf(AB_addToRearC_ABC(), ELEMENT_D, -1));
			printTest("AB_addToRearC_ABC_testRemoveNeg1", testRemoveIndex(AB_addToRearC_ABC(), -1, null, Result.IndexOutOfBounds));
			printTest("AB_addToRearC_ABC_testRemove0", testRemoveIndex(AB_addToRearC_ABC(), 0, ELEMENT_A, Result.MatchingValue));
			printTest("AB_addToRearC_ABC_testRemove0", testRemoveIndex(AB_addToRearC_ABC(), 1, ELEMENT_B, Result.MatchingValue));
			printTest("AB_addToRearC_ABC_testRemove0", testRemoveIndex(AB_addToRearC_ABC(), 2, ELEMENT_C, Result.MatchingValue));
			printTest("AB_addToRearC_ABC_testRemove3", testRemoveIndex(AB_addToRearC_ABC(), 3, null, Result.IndexOutOfBounds));
			// Iterator
			printTest("AB_addToRearC_ABC_testIterator", testIterator(AB_addToRearC_ABC(), Result.NoException));
			printTest("AB_addToRearC_ABC_testIteratorHasNext", testIteratorHasNext(AB_addToRearC_ABC().iterator(), Result.True));
			printTest("AB_addToRearC_ABC_testIteratorNext", testIteratorNext(AB_addToRearC_ABC().iterator(), ELEMENT_A, Result.MatchingValue));
			printTest("AB_addToRearC_ABC_testIteratorRemove", testIteratorRemove(AB_addToRearC_ABC().iterator(), Result.IllegalState));
			printTest("AB_addToRearC_ABC_testIteratorHasNextAfterNext", testIteratorHasNext(iteratorAfterNext(AB_addToRearC_ABC(),1), Result.True));
			printTest("AB_addToRearC_ABC_testIteratorNextAfterNext", testIteratorNext(iteratorAfterNext(AB_addToRearC_ABC(),1), ELEMENT_B, Result.MatchingValue));
			printTest("AB_addToRearC_ABC_testIteratorRemoveAfterNext", testIteratorRemove(iteratorAfterNext(AB_addToRearC_ABC(),1), Result.NoException));
			printTest("AB_addToRearC_ABC_testIteratorHasNextAfterNext2", testIteratorHasNext(iteratorAfterNext(AB_addToRearC_ABC(),2), Result.True));
			printTest("AB_addToRearC_ABC_testIteratorHasNextAfterNext3", testIteratorHasNext(iteratorAfterNext(AB_addToRearC_ABC(),3), Result.False));
			printTest("AB_addToRearC_ABC_testIteratorNextAfterNext2", testIteratorNext(iteratorAfterNext(AB_addToRearC_ABC(),2), ELEMENT_C, Result.MatchingValue));
			printTest("AB_addToRearC_ABC_testIteratorNextAfterNext3", testIteratorNext(iteratorAfterNext(AB_addToRearC_ABC(),3), null, Result.NoSuchElement));
			printTest("AB_addToRearC_ABC_testIteratorRemoveAfterNext2", testIteratorRemove(iteratorAfterNext(AB_addToRearC_ABC(),2), Result.NoException));
			printTest("AB_addToRearC_ABC_testIteratorRemoveAfterNext3", testIteratorRemove(iteratorAfterNext(AB_addToRearC_ABC(),3), Result.NoException));
			printTest("AB_addToRearC_ABC_testIteratorNextAfterRemoveNext", testIteratorNext(iteratorAfterRemove(iteratorAfterNext(AB_addToRearC_ABC(),1)),ELEMENT_B,  Result.MatchingValue));
			printTest("AB_addToRearC_ABC_testIteratorNextAfterRemoveNext2", testIteratorNext(iteratorAfterRemove(iteratorAfterNext(AB_addToRearC_ABC(),2)),ELEMENT_C,  Result.MatchingValue));
			printTest("AB_addToRearC_ABC_testIteratorNextAfterRemoveNext3", testIteratorNext(iteratorAfterRemove(iteratorAfterNext(AB_addToRearC_ABC(),3)),null,  Result.NoSuchElement));
			printTest("AB_addToRearC_ABC_testIteratorHasNextAfterRemoveNext",testIteratorHasNext(iteratorAfterRemove(iteratorAfterNext(AB_addToRearC_ABC(),1)),Result.True));
			printTest("AB_addToRearC_ABC_testIteratorHasNextAfterRemoveNext2",testIteratorHasNext(iteratorAfterRemove(iteratorAfterNext(AB_addToRearC_ABC(),2)),Result.True));
			printTest("AB_addToRearC_ABC_testIteratorHasNextAfterRemoveNext3",testIteratorHasNext(iteratorAfterRemove(iteratorAfterNext(AB_addToRearC_ABC(),3)),Result.False));
			printTest("AB_addToRearC_ABC_iteratorTestRealRemove",testIteratorRealRemove(AB_addToRearC_ABC(), 1, "[2, 3]", Result.MatchingValue));
			printTest("AB_addToRearC_ABC_iteratorTestRealRemove2",testIteratorRealRemove(AB_addToRearC_ABC(), 2, "[1, 3]", Result.MatchingValue));
			printTest("AB_addToRearC_ABC_iteratorTestRealRemove3",testIteratorRealRemove(AB_addToRearC_ABC(), 3, "[1, 2]", Result.MatchingValue));
		} catch (Exception e) {
			System.out.printf("***UNABLE TO RUN/COMPLETE %s***\n", "test_AB_addToRearC_ABC");
			e.printStackTrace();
		}
	}
	
	//////////////////////////////////////
	// SCENARIO: [A,B] -> addAfter(C,A) -> [A,C,B]
	//  XXX SCENARIO 22
	//////////////////////////////////////
	private ListADT<Integer> AB_addAfterCA_ACB() {
		// cast to UnorderedListADT to make addToFront() available
		UnorderedListADT<Integer> list = (UnorderedListADT<Integer>) newList(); 
		list.addToFront(ELEMENT_A);
		list.addToRear(ELEMENT_B);
		list.addAfter(ELEMENT_C,ELEMENT_A);
		return list;
	}
	private void test_AB_addAfterCA_ACB(){
		try {
			// ListADT
			printTest("AB_addAfterCA_ACB_testRemoveFirst", testRemoveFirst(AB_addAfterCA_ACB(), ELEMENT_A, Result.MatchingValue));
			printTest("AB_addAfterCA_ACB_testRemoveLast", testRemoveLast(AB_addAfterCA_ACB(), ELEMENT_B, Result.MatchingValue));
			printTest("AB_addAfterCA_ACB_testRemoveA", testRemoveElement(AB_addAfterCA_ACB(), ELEMENT_A, Result.MatchingValue));
			printTest("AB_addAfterCA_ACB_testRemoveB", testRemoveElement(AB_addAfterCA_ACB(), ELEMENT_B, Result.MatchingValue));
			printTest("AB_addAfterCA_ACB_testRemoveC", testRemoveElement(AB_addAfterCA_ACB(), ELEMENT_C, Result.MatchingValue));
			printTest("AB_addAfterCA_ACB_testRemoveD", testRemoveElement(AB_addAfterCA_ACB(), ELEMENT_D, Result.ElementNotFound));
			printTest("AB_addAfterCA_ACB_testFirst", testFirst(AB_addAfterCA_ACB(), ELEMENT_A, Result.MatchingValue));
			printTest("AB_addAfterCA_ACB_testLast", testLast(AB_addAfterCA_ACB(), ELEMENT_B, Result.MatchingValue));
			printTest("AB_addAfterCA_ACB_testContainsA", testContains(AB_addAfterCA_ACB(), ELEMENT_A, Result.True));
			printTest("AB_addAfterCA_ACB_testContainsB", testContains(AB_addAfterCA_ACB(), ELEMENT_B, Result.True));
			printTest("AB_addAfterCA_ACB_testContainsC", testContains(AB_addAfterCA_ACB(), ELEMENT_C, Result.True));
			printTest("AB_addAfterCA_ACB_testContainsD", testContains(AB_addAfterCA_ACB(), ELEMENT_D, Result.False));
			printTest("AB_addAfterCA_ACB_testIsEmpty", testIsEmpty(AB_addAfterCA_ACB(), Result.False));
			printTest("AB_addAfterCA_ACB_testSize", testSize(AB_addAfterCA_ACB(), 3));
			printTest("AB_addAfterCA_ACB_testToString", testToString(AB_addAfterCA_ACB(), Result.ValidString));
			// UnorderedListADT
			printTest("AB_addAfterCA_ACB_testAddToFrontB", testAddToFront(AB_addAfterCA_ACB(), ELEMENT_D, Result.NoException));
			printTest("AB_addAfterCA_ACB_testAddToRearB", testAddToRear(AB_addAfterCA_ACB(), ELEMENT_D, Result.NoException));
			printTest(	"AB_addAfterCA_ACB_testAddAfterAD", testAddAfter(AB_addAfterCA_ACB(), ELEMENT_A, ELEMENT_D, Result.NoException));
			printTest(	"AB_addAfterCA_ACB_testAddAfterBD", testAddAfter(AB_addAfterCA_ACB(), ELEMENT_B, ELEMENT_D, Result.NoException));
			printTest(	"AB_addAfterCA_ACB_testAddAfterCD", testAddAfter(AB_addAfterCA_ACB(), ELEMENT_C, ELEMENT_D, Result.NoException));
			printTest(	"AB_addAfterCA_ACB_testAddAfterDD", testAddAfter(AB_addAfterCA_ACB(), ELEMENT_D, ELEMENT_D, Result.ElementNotFound));
			// IndexedListADT
			printTest("AB_addAfterCA_ACB_testAddAtIndexNeg1D", testAddAtIndex(AB_addAfterCA_ACB(), -1, ELEMENT_D, Result.IndexOutOfBounds));
			printTest("AB_addAfterCA_ACB_testAddAtIndex0D", testAddAtIndex(AB_addAfterCA_ACB(), 0, ELEMENT_D, Result.NoException));
			printTest("AB_addAfterCA_ACB_testAddAtIndex1D", testAddAtIndex(AB_addAfterCA_ACB(), 1, ELEMENT_D, Result.NoException));
			printTest("AB_addAfterCA_ACB_testAddAtIndex2D", testAddAtIndex(AB_addAfterCA_ACB(), 2, ELEMENT_D, Result.NoException));
			printTest("AB_addAfterCA_ACB_testAddAtIndex3D", testAddAtIndex(AB_addAfterCA_ACB(), 3, ELEMENT_D, Result.NoException));
			printTest("AB_addAfterCA_ACB_testAddAtIndex4D", testAddAtIndex(AB_addAfterCA_ACB(), 4, ELEMENT_D, Result.IndexOutOfBounds));
			printTest("AB_addAfterCA_ACB_testSetNeg1D", testSet(AB_addAfterCA_ACB(), -1, ELEMENT_D, Result.IndexOutOfBounds));
			printTest("AB_addAfterCA_ACB_testSet0D", testSet(AB_addAfterCA_ACB(), 0, ELEMENT_D, Result.NoException));
			printTest("AB_addAfterCA_ACB_testSet1D", testSet(AB_addAfterCA_ACB(), 1, ELEMENT_D, Result.NoException));
			printTest("AB_addAfterCA_ACB_testSet2D", testSet(AB_addAfterCA_ACB(), 2, ELEMENT_D, Result.NoException));
			printTest("AB_addAfterCA_ACB_testSet3D", testSet(AB_addAfterCA_ACB(), 3, ELEMENT_D, Result.IndexOutOfBounds));
			printTest("AB_addAfterCA_ACB_testAddD", testAdd(AB_addAfterCA_ACB(), ELEMENT_D, Result.NoException));
			printTest("AB_addAfterCA_ACB_testGetNeg1", testGet(AB_addAfterCA_ACB(), -1, null, Result.IndexOutOfBounds));
			printTest("AB_addAfterCA_ACB_testGet0", testGet(AB_addAfterCA_ACB(), 0, ELEMENT_A, Result.MatchingValue));
			printTest("AB_addAfterCA_ACB_testGet1", testGet(AB_addAfterCA_ACB(), 1, ELEMENT_C, Result.MatchingValue));
			printTest("AB_addAfterCA_ACB_testGet2", testGet(AB_addAfterCA_ACB(), 2, ELEMENT_B, Result.MatchingValue));
			printTest("AB_addAfterCA_ACB_testGet3", testGet(AB_addAfterCA_ACB(), 3, null, Result.IndexOutOfBounds));
			printTest("AB_addAfterCA_ACB_testIndexOfA", testIndexOf(AB_addAfterCA_ACB(), ELEMENT_A, 0));
			printTest("AB_addAfterCA_ACB_testIndexOfB", testIndexOf(AB_addAfterCA_ACB(), ELEMENT_B, 2));
			printTest("AB_addAfterCA_ACB_testIndexOfC", testIndexOf(AB_addAfterCA_ACB(), ELEMENT_C, 1));
			printTest("AB_addAfterCA_ACB_testIndexOfD", testIndexOf(AB_addAfterCA_ACB(), ELEMENT_D, -1));
			printTest("AB_addAfterCA_ACB_testRemoveNeg1", testRemoveIndex(AB_addAfterCA_ACB(), -1, null, Result.IndexOutOfBounds));
			printTest("AB_addAfterCA_ACB_testRemove0", testRemoveIndex(AB_addAfterCA_ACB(), 0, ELEMENT_A, Result.MatchingValue));
			printTest("AB_addAfterCA_ACB_testRemove0", testRemoveIndex(AB_addAfterCA_ACB(), 1, ELEMENT_C, Result.MatchingValue));
			printTest("AB_addAfterCA_ACB_testRemove0", testRemoveIndex(AB_addAfterCA_ACB(), 2, ELEMENT_B, Result.MatchingValue));
			printTest("AB_addAfterCA_ACB_testRemove3", testRemoveIndex(AB_addAfterCA_ACB(), 3, null, Result.IndexOutOfBounds));
			// Iterator
			printTest("AB_addAfterCA_ACB_testIterator", testIterator(AB_addAfterCA_ACB(), Result.NoException));
			printTest("AB_addAfterCA_ACB_testIteratorHasNext", testIteratorHasNext(AB_addAfterCA_ACB().iterator(), Result.True));
			printTest("AB_addAfterCA_ACB_testIteratorNext", testIteratorNext(AB_addAfterCA_ACB().iterator(), ELEMENT_A, Result.MatchingValue));
			printTest("AB_addAfterCA_ACB_testIteratorRemove", testIteratorRemove(AB_addAfterCA_ACB().iterator(), Result.IllegalState));
			printTest("AB_addAfterCA_ACB_testIteratorHasNextAfterNext", testIteratorHasNext(iteratorAfterNext(AB_addAfterCA_ACB(),1), Result.True));
			printTest("AB_addAfterCA_ACB_testIteratorNextAfterNext", testIteratorNext(iteratorAfterNext(AB_addAfterCA_ACB(),1), ELEMENT_C, Result.MatchingValue));
			printTest("AB_addAfterCA_ACB_testIteratorRemoveAfterNext", testIteratorRemove(iteratorAfterNext(AB_addAfterCA_ACB(),1), Result.NoException));
			printTest("AB_addAfterCA_ACB_testIteratorHasNextAfterNext2", testIteratorHasNext(iteratorAfterNext(AB_addAfterCA_ACB(),2), Result.True));
			printTest("AB_addAfterCA_ACB_testIteratorHasNextAfterNext3", testIteratorHasNext(iteratorAfterNext(AB_addAfterCA_ACB(),3), Result.False));
			printTest("AB_addAfterCA_ACB_testIteratorNextAfterNext2", testIteratorNext(iteratorAfterNext(AB_addAfterCA_ACB(),2), ELEMENT_B, Result.MatchingValue));
			printTest("AB_addAfterCA_ACB_testIteratorNextAfterNext3", testIteratorNext(iteratorAfterNext(AB_addAfterCA_ACB(),3), null, Result.NoSuchElement));
			printTest("AB_addAfterCA_ACB_testIteratorRemoveAfterNext2", testIteratorRemove(iteratorAfterNext(AB_addAfterCA_ACB(),2), Result.NoException));
			printTest("AB_addAfterCA_ACB_testIteratorRemoveAfterNext3", testIteratorRemove(iteratorAfterNext(AB_addAfterCA_ACB(),3), Result.NoException));
			printTest("AB_addAfterCA_ACB_testIteratorNextAfterRemoveNext", testIteratorNext(iteratorAfterRemove(iteratorAfterNext(AB_addAfterCA_ACB(),1)),ELEMENT_C,  Result.MatchingValue));
			printTest("AB_addAfterCA_ACB_testIteratorNextAfterRemoveNext2", testIteratorNext(iteratorAfterRemove(iteratorAfterNext(AB_addAfterCA_ACB(),2)),ELEMENT_B,  Result.MatchingValue));
			printTest("AB_addAfterCA_ACB_testIteratorNextAfterRemoveNext3", testIteratorNext(iteratorAfterRemove(iteratorAfterNext(AB_addAfterCA_ACB(),3)),null,  Result.NoSuchElement));
			printTest("AB_addAfterCA_ACB_testIteratorHasNextAfterRemoveNext",testIteratorHasNext(iteratorAfterRemove(iteratorAfterNext(AB_addAfterCA_ACB(),1)),Result.True));
			printTest("AB_addAfterCA_ACB_testIteratorHasNextAfterRemoveNext2",testIteratorHasNext(iteratorAfterRemove(iteratorAfterNext(AB_addAfterCA_ACB(),2)),Result.True));
			printTest("AB_addAfterCA_ACB_testIteratorHasNextAfterRemoveNext3",testIteratorHasNext(iteratorAfterRemove(iteratorAfterNext(AB_addAfterCA_ACB(),3)),Result.False));
			printTest("AB_addAfterCA_ACB_iteratorTestRealRemove",testIteratorRealRemove(AB_addAfterCA_ACB(), 1, "[3, 2]", Result.MatchingValue));
			printTest("AB_addAfterCA_ACB_iteratorTestRealRemove2",testIteratorRealRemove(AB_addAfterCA_ACB(), 2, "[1, 2]", Result.MatchingValue));
			printTest("AB_addAfterCA_ACB_iteratorTestRealRemove3",testIteratorRealRemove(AB_addAfterCA_ACB(), 3, "[1, 3]", Result.MatchingValue));
		} catch (Exception e) {
			System.out.printf("***UNABLE TO RUN/COMPLETE %s***\n", "test_AB_addAfterCA_ACB");
			e.printStackTrace();
		}
	}
	
	//////////////////////////////////////
	// SCENARIO: [A,B] -> addAfter(C,B) -> [A,B,C]
	//  XXX SCENARIO 23
	//////////////////////////////////////
	private ListADT<Integer> AB_addAfterCB_ABC() {
		// cast to UnorderedListADT to make addToFront() available
		UnorderedListADT<Integer> list = (UnorderedListADT<Integer>) newList(); 
		list.addToFront(ELEMENT_A);
		list.addToRear(ELEMENT_B);
		list.addAfter(ELEMENT_C,ELEMENT_B);
		return list;
	}
	private void test_AB_addAfterCB_ABC(){
		try {
			// ListADT
			printTest("AB_addAfterCB_ABC_testRemoveFirst", testRemoveFirst(AB_addAfterCB_ABC(), ELEMENT_A, Result.MatchingValue));
			printTest("AB_addAfterCB_ABC_testRemoveLast", testRemoveLast(AB_addAfterCB_ABC(), ELEMENT_C, Result.MatchingValue));
			printTest("AB_addAfterCB_ABC_testRemoveA", testRemoveElement(AB_addAfterCB_ABC(), ELEMENT_A, Result.MatchingValue));
			printTest("AB_addAfterCB_ABC_testRemoveB", testRemoveElement(AB_addAfterCB_ABC(), ELEMENT_B, Result.MatchingValue));
			printTest("AB_addAfterCB_ABC_testRemoveC", testRemoveElement(AB_addAfterCB_ABC(), ELEMENT_C, Result.MatchingValue));
			printTest("AB_addAfterCB_ABC_testRemoveD", testRemoveElement(AB_addAfterCB_ABC(), ELEMENT_D, Result.ElementNotFound));
			printTest("AB_addAfterCB_ABC_testFirst", testFirst(AB_addAfterCB_ABC(), ELEMENT_A, Result.MatchingValue));
			printTest("AB_addAfterCB_ABC_testLast", testLast(AB_addAfterCB_ABC(), ELEMENT_C, Result.MatchingValue));
			printTest("AB_addAfterCB_ABC_testContainsA", testContains(AB_addAfterCB_ABC(), ELEMENT_A, Result.True));
			printTest("AB_addAfterCB_ABC_testContainsB", testContains(AB_addAfterCB_ABC(), ELEMENT_B, Result.True));
			printTest("AB_addAfterCB_ABC_testContainsC", testContains(AB_addAfterCB_ABC(), ELEMENT_C, Result.True));
			printTest("AB_addAfterCB_ABC_testContainsD", testContains(AB_addAfterCB_ABC(), ELEMENT_D, Result.False));
			printTest("AB_addAfterCB_ABC_testIsEmpty", testIsEmpty(AB_addAfterCB_ABC(), Result.False));
			printTest("AB_addAfterCB_ABC_testSize", testSize(AB_addAfterCB_ABC(), 3));
			printTest("AB_addAfterCB_ABC_testToString", testToString(AB_addAfterCB_ABC(), Result.ValidString));
			// UnorderedListADT
			printTest("AB_addAfterCB_ABC_testAddToFrontB", testAddToFront(AB_addAfterCB_ABC(), ELEMENT_D, Result.NoException));
			printTest("AB_addAfterCB_ABC_testAddToRearB", testAddToRear(AB_addAfterCB_ABC(), ELEMENT_D, Result.NoException));
			printTest(	"AB_addAfterCB_ABC_testAddAfterAD", testAddAfter(AB_addAfterCB_ABC(), ELEMENT_A, ELEMENT_D, Result.NoException));
			printTest(	"AB_addAfterCB_ABC_testAddAfterBD", testAddAfter(AB_addAfterCB_ABC(), ELEMENT_B, ELEMENT_D, Result.NoException));
			printTest(	"AB_addAfterCB_ABC_testAddAfterCD", testAddAfter(AB_addAfterCB_ABC(), ELEMENT_C, ELEMENT_D, Result.NoException));
			printTest(	"AB_addAfterCB_ABC_testAddAfterDD", testAddAfter(AB_addAfterCB_ABC(), ELEMENT_D, ELEMENT_D, Result.ElementNotFound));
			// IndexedListADT
			printTest("AB_addAfterCB_ABC_testAddAtIndexNeg1D", testAddAtIndex(AB_addAfterCB_ABC(), -1, ELEMENT_D, Result.IndexOutOfBounds));
			printTest("AB_addAfterCB_ABC_testAddAtIndex0D", testAddAtIndex(AB_addAfterCB_ABC(), 0, ELEMENT_D, Result.NoException));
			printTest("AB_addAfterCB_ABC_testAddAtIndex1D", testAddAtIndex(AB_addAfterCB_ABC(), 1, ELEMENT_D, Result.NoException));
			printTest("AB_addAfterCB_ABC_testAddAtIndex2D", testAddAtIndex(AB_addAfterCB_ABC(), 2, ELEMENT_D, Result.NoException));
			printTest("AB_addAfterCB_ABC_testAddAtIndex3D", testAddAtIndex(AB_addAfterCB_ABC(), 3, ELEMENT_D, Result.NoException));
			printTest("AB_addAfterCB_ABC_testAddAtIndex4D", testAddAtIndex(AB_addAfterCB_ABC(), 4, ELEMENT_D, Result.IndexOutOfBounds));
			printTest("AB_addAfterCB_ABC_testSetNeg1D", testSet(AB_addAfterCB_ABC(), -1, ELEMENT_D, Result.IndexOutOfBounds));
			printTest("AB_addAfterCB_ABC_testSet0D", testSet(AB_addAfterCB_ABC(), 0, ELEMENT_D, Result.NoException));
			printTest("AB_addAfterCB_ABC_testSet1D", testSet(AB_addAfterCB_ABC(), 1, ELEMENT_D, Result.NoException));
			printTest("AB_addAfterCB_ABC_testSet2D", testSet(AB_addAfterCB_ABC(), 2, ELEMENT_D, Result.NoException));
			printTest("AB_addAfterCB_ABC_testSet3D", testSet(AB_addAfterCB_ABC(), 3, ELEMENT_D, Result.IndexOutOfBounds));
			printTest("AB_addAfterCB_ABC_testAddD", testAdd(AB_addAfterCB_ABC(), ELEMENT_D, Result.NoException));
			printTest("AB_addAfterCB_ABC_testGetNeg1", testGet(AB_addAfterCB_ABC(), -1, null, Result.IndexOutOfBounds));
			printTest("AB_addAfterCB_ABC_testGet0", testGet(AB_addAfterCB_ABC(), 0, ELEMENT_A, Result.MatchingValue));
			printTest("AB_addAfterCB_ABC_testGet1", testGet(AB_addAfterCB_ABC(), 1, ELEMENT_B, Result.MatchingValue));
			printTest("AB_addAfterCB_ABC_testGet2", testGet(AB_addAfterCB_ABC(), 2, ELEMENT_C, Result.MatchingValue));
			printTest("AB_addAfterCB_ABC_testGet3", testGet(AB_addAfterCB_ABC(), 3, null, Result.IndexOutOfBounds));
			printTest("AB_addAfterCB_ABC_testIndexOfA", testIndexOf(AB_addAfterCB_ABC(), ELEMENT_A, 0));
			printTest("AB_addAfterCB_ABC_testIndexOfB", testIndexOf(AB_addAfterCB_ABC(), ELEMENT_B, 1));
			printTest("AB_addAfterCB_ABC_testIndexOfC", testIndexOf(AB_addAfterCB_ABC(), ELEMENT_C, 2));
			printTest("AB_addAfterCB_ABC_testIndexOfD", testIndexOf(AB_addAfterCB_ABC(), ELEMENT_D, -1));
			printTest("AB_addAfterCB_ABC_testRemoveNeg1", testRemoveIndex(AB_addAfterCB_ABC(), -1, null, Result.IndexOutOfBounds));
			printTest("AB_addAfterCB_ABC_testRemove0", testRemoveIndex(AB_addAfterCB_ABC(), 0, ELEMENT_A, Result.MatchingValue));
			printTest("AB_addAfterCB_ABC_testRemove1", testRemoveIndex(AB_addAfterCB_ABC(), 1, ELEMENT_B, Result.MatchingValue));
			printTest("AB_addAfterCB_ABC_testRemove2", testRemoveIndex(AB_addAfterCB_ABC(), 2, ELEMENT_C, Result.MatchingValue));
			printTest("AB_addAfterCB_ABC_testRemove3", testRemoveIndex(AB_addAfterCB_ABC(), 3, null, Result.IndexOutOfBounds));
			// Iterator
			printTest("AB_addAfterCB_ABC_testIterator", testIterator(AB_addAfterCB_ABC(), Result.NoException));
			printTest("AB_addAfterCB_ABC_testIteratorHasNext", testIteratorHasNext(AB_addAfterCB_ABC().iterator(), Result.True));
			printTest("AB_addAfterCB_ABC_testIteratorNext", testIteratorNext(AB_addAfterCB_ABC().iterator(), ELEMENT_A, Result.MatchingValue));
			printTest("AB_addAfterCB_ABC_testIteratorRemove", testIteratorRemove(AB_addAfterCB_ABC().iterator(), Result.IllegalState));
			printTest("AB_addAfterCB_ABC_testIteratorHasNextAfterNext", testIteratorHasNext(iteratorAfterNext(AB_addAfterCB_ABC(),1), Result.True));
			printTest("AB_addAfterCB_ABC_testIteratorNextAfterNext", testIteratorNext(iteratorAfterNext(AB_addAfterCB_ABC(),1), ELEMENT_B, Result.MatchingValue));
			printTest("AB_addAfterCB_ABC_testIteratorRemoveAfterNext", testIteratorRemove(iteratorAfterNext(AB_addAfterCB_ABC(),1), Result.NoException));
			printTest("AB_addAfterCB_ABC_testIteratorHasNextAfterNext2", testIteratorHasNext(iteratorAfterNext(AB_addAfterCB_ABC(),2), Result.True));
			printTest("AB_addAfterCB_ABC_testIteratorHasNextAfterNext3", testIteratorHasNext(iteratorAfterNext(AB_addAfterCB_ABC(),3), Result.False));
			printTest("AB_addAfterCB_ABC_testIteratorNextAfterNext2", testIteratorNext(iteratorAfterNext(AB_addAfterCB_ABC(),2), ELEMENT_C, Result.MatchingValue));
			printTest("AB_addAfterCB_ABC_testIteratorNextAfterNext3", testIteratorNext(iteratorAfterNext(AB_addAfterCB_ABC(),3), null, Result.NoSuchElement));
			printTest("AB_addAfterCB_ABC_testIteratorRemoveAfterNext2", testIteratorRemove(iteratorAfterNext(AB_addAfterCB_ABC(),2), Result.NoException));
			printTest("AB_addAfterCB_ABC_testIteratorRemoveAfterNext3", testIteratorRemove(iteratorAfterNext(AB_addAfterCB_ABC(),3), Result.NoException));
			printTest("AB_addAfterCB_ABC_testIteratorNextAfterRemoveNext", testIteratorNext(iteratorAfterRemove(iteratorAfterNext(AB_addAfterCB_ABC(),1)),ELEMENT_B,  Result.MatchingValue));
			printTest("AB_addAfterCB_ABC_testIteratorNextAfterRemoveNext2", testIteratorNext(iteratorAfterRemove(iteratorAfterNext(AB_addAfterCB_ABC(),2)),ELEMENT_C,  Result.MatchingValue));
			printTest("AB_addAfterCB_ABC_testIteratorNextAfterRemoveNext3", testIteratorNext(iteratorAfterRemove(iteratorAfterNext(AB_addAfterCB_ABC(),3)),null,  Result.NoSuchElement));
			printTest("AB_addAfterCB_ABC_testIteratorHasNextAfterRemoveNext",testIteratorHasNext(iteratorAfterRemove(iteratorAfterNext(AB_addAfterCB_ABC(),1)),Result.True));
			printTest("AB_addAfterCB_ABC_testIteratorHasNextAfterRemoveNext2",testIteratorHasNext(iteratorAfterRemove(iteratorAfterNext(AB_addAfterCB_ABC(),2)),Result.True));
			printTest("AB_addAfterCB_ABC_testIteratorHasNextAfterRemoveNext3",testIteratorHasNext(iteratorAfterRemove(iteratorAfterNext(AB_addAfterCB_ABC(),3)),Result.False));
			printTest("AB_addAfterCB_ABC_iteratorTestRealRemove",testIteratorRealRemove(AB_addAfterCB_ABC(), 1, "[2, 3]", Result.MatchingValue));
			printTest("AB_addAfterCB_ABC_iteratorTestRealRemove2",testIteratorRealRemove(AB_addAfterCB_ABC(), 2, "[1, 3]", Result.MatchingValue));
			printTest("AB_addAfterCB_ABC_iteratorTestRealRemove3",testIteratorRealRemove(AB_addAfterCB_ABC(), 3, "[1, 2]", Result.MatchingValue));
		} catch (Exception e) {
			System.out.printf("***UNABLE TO RUN/COMPLETE %s***\n", "test_AB_addAfterCB_ABC");
			e.printStackTrace();
		}
	}
	
	//////////////////////////////////////
	// SCENARIO: [A,B] -> remove(0) -> [B]
	//  XXX SCENARIO 24
	//////////////////////////////////////
	private ListADT<Integer> AB_remove0_B() {
		// cast to UnorderedListADT to make addToFront() available
		IndexedListADT<Integer> list = (IndexedListADT<Integer>) newList(); 
		list.add(0,ELEMENT_A);
		list.add(1, ELEMENT_B);
		list.remove(0);
		return list;
	}
	private void test_AB_remove0_B(){
		try {
			// ListADT
			printTest("AB_remove0_B_testRemoveFirst", testRemoveFirst(AB_remove0_B(), ELEMENT_B, Result.MatchingValue));
			printTest("AB_remove0_B_testRemoveLast", testRemoveLast(AB_remove0_B(), ELEMENT_B, Result.MatchingValue));
			printTest("AB_remove0_B_testRemoveB", testRemoveElement(AB_remove0_B(), ELEMENT_B, Result.MatchingValue));
			printTest("AB_remove0_B_testRemoveA", testRemoveElement(AB_remove0_B(), ELEMENT_A, Result.ElementNotFound));
			printTest("AB_remove0_B_testFirst", testFirst(AB_remove0_B(), ELEMENT_B, Result.MatchingValue));
			printTest("AB_remove0_B_testLast", testLast(AB_remove0_B(), ELEMENT_B, Result.MatchingValue));
			printTest("AB_remove0_B_testContainsB", testContains(AB_remove0_B(), ELEMENT_B, Result.True));
			printTest("AB_remove0_B_testContainsC", testContains(AB_remove0_B(), ELEMENT_C, Result.False));
			printTest("AB_remove0_B_testIsEmpty", testIsEmpty(AB_remove0_B(), Result.False));
			printTest("AB_remove0_B_testSize", testSize(AB_remove0_B(), 1));
			printTest("AB_remove0_B_testToString", testToString(AB_remove0_B(), Result.ValidString));
			// UnorderedListADT
			printTest("AB_remove0_B_testAddToFrontB", testAddToFront(AB_remove0_B(), ELEMENT_A, Result.NoException));
			printTest("AB_remove0_B_testAddToRearB", testAddToRear(AB_remove0_B(), ELEMENT_A, Result.NoException));
			printTest(	"AB_remove0_B_testAddAfterCD", testAddAfter(AB_remove0_B(), ELEMENT_C, ELEMENT_D, Result.ElementNotFound));
			printTest(	"AB_remove0_B_testAddAfterBC", testAddAfter(AB_remove0_B(), ELEMENT_B, ELEMENT_C, Result.NoException));
			// IndexedListADT
			printTest("AB_remove0_B_testAddAtIndexNeg1B", testAddAtIndex(AB_remove0_B(), -1, ELEMENT_A, Result.IndexOutOfBounds));
			printTest("AB_remove0_B_testAddAtIndex0A", testAddAtIndex(AB_remove0_B(), 0, ELEMENT_A, Result.NoException));
			printTest("AB_remove0_B_testAddAtIndex1A", testAddAtIndex(AB_remove0_B(), 1, ELEMENT_A, Result.NoException));
			printTest("AB_remove0_B_testSetNeg1A", testSet(AB_remove0_B(), -1, ELEMENT_A, Result.IndexOutOfBounds));
			printTest("AB_remove0_B_testSet0A", testSet(AB_remove0_B(), 0, ELEMENT_A, Result.NoException));
			printTest("AB_remove0_B_testSet1A", testSet(AB_remove0_B(), 1, ELEMENT_A, Result.IndexOutOfBounds));
			printTest("AB_remove0_B_testAddA", testAdd(AB_remove0_B(), ELEMENT_A, Result.NoException));
			printTest("AB_remove0_B_testGetNeg1", testGet(AB_remove0_B(), -1, null, Result.IndexOutOfBounds));
			printTest("AB_remove0_B_testGet0", testGet(AB_remove0_B(), 0, ELEMENT_B, Result.MatchingValue));
			printTest("AB_remove0_B_testGet1", testGet(AB_remove0_B(), 1, null, Result.IndexOutOfBounds));
			printTest("AB_remove0_B_testIndexOfB", testIndexOf(AB_remove0_B(), ELEMENT_B, 0));
			printTest("AB_remove0_B_testIndexOfA", testIndexOf(AB_remove0_B(), ELEMENT_A, -1));
			printTest("AB_remove0_B_testRemoveNeg1", testRemoveIndex(AB_remove0_B(), -1, null, Result.IndexOutOfBounds));
			printTest("AB_remove0_B_testRemove0", testRemoveIndex(AB_remove0_B(), 0, ELEMENT_B, Result.MatchingValue));
			printTest("AB_remove0_B_testRemove1", testRemoveIndex(AB_remove0_B(), 1, null, Result.IndexOutOfBounds));
			// Iterator
			printTest("AB_remove0_B_testIterator", testIterator(AB_remove0_B(), Result.NoException));
			printTest("AB_remove0_B_testIteratorHasNext", testIteratorHasNext(AB_remove0_B().iterator(), Result.True));
			printTest("AB_remove0_B_testIteratorNext", testIteratorNext(AB_remove0_B().iterator(), ELEMENT_B, Result.MatchingValue));
			printTest("AB_remove0_B_testIteratorRemove", testIteratorRemove(AB_remove0_B().iterator(), Result.IllegalState));
			printTest("AB_remove0_B_iteratorNext_testIteratorHasNext", testIteratorHasNext(iteratorAfterNext(AB_remove0_B(), 1), Result.False));
			printTest("AB_remove0_B_iteratorNext_testIteratorNext", testIteratorNext(iteratorAfterNext(AB_remove0_B(), 1), null, Result.NoSuchElement));
			printTest("AB_remove0_B_iteratorNext_testIteratorRemove", testIteratorRemove(iteratorAfterNext(AB_remove0_B(), 1), Result.NoException));
			printTest("AB_remove0_B_iteratorNextRemove_testIteratorHasNext", testIteratorHasNext(iteratorAfterRemove(iteratorAfterNext(AB_remove0_B(), 1)), Result.False));
			printTest("AB_remove0_B_iteratorNextRemove_testIteratorNext", testIteratorNext(iteratorAfterRemove(iteratorAfterNext(AB_remove0_B(), 1)), null, Result.NoSuchElement));
			printTest("AB_remove0_B_iteratorNextRemove_testIteratorRemove", testIteratorRemove(iteratorAfterRemove(iteratorAfterNext(AB_remove0_B(), 1)), Result.IllegalState));
			printTest("AB_remove0_B_iteratorTestRealRemove",testIteratorRealRemove(AB_remove0_B(), 1, "[]", Result.MatchingValue));
			//ListIterator
			if(newList().getClass()==testDLL().getClass()){
			printTest("AB_remove0_B_testListIteratorUnIndexed", testListIteratorUnIndexed(AB_remove0_B(), Result.NoException));
			printTest("AB_remove0_B_testListIteratorIndexedNeg1", testListIteratorIndexed(AB_remove0_B(), -1, Result.IndexOutOfBounds));
			printTest("AB_remove0_B_testListIteratorIndexed0", testListIteratorIndexed(AB_remove0_B(), 0, Result.NoException));
			printTest("AB_remove0_B_testListIteratorIndexed1", testListIteratorIndexed(AB_remove0_B(), 1, Result.NoException));
			printTest("AB_remove0_B_testListIteratorIndexed2", testListIteratorIndexed(AB_remove0_B(), 2, Result.IndexOutOfBounds));
			
			printTest("AB_remove0_B_testListIteratorHasNext", testListIteratorHasNext(AB_remove0_B(),null, null, Result.True));
			printTest("AB_remove0_B_testListIteratorNext", testListIteratorNext(AB_remove0_B(),null, null, ELEMENT_B, Result.MatchingValue));
			printTest("AB_remove0_B_testListIteratorNextIndex", testListIteratorNextIndex(AB_remove0_B(),null, null, 0, Result.MatchingValue));
			printTest("AB_remove0_B_testListIteratorHasPrevious", testListIteratorHasPrevious(AB_remove0_B(),null, null, Result.False));
			printTest("AB_remove0_B_testListIteratorPrevious", testListIteratorPrevious(AB_remove0_B(),null, null, null, Result.NoSuchElement));
			printTest("AB_remove0_B_testListIteratorPreviousIndex", testListIteratorPreviousIndex(AB_remove0_B(),null, null, -1, Result.MatchingValue));
			printTest("AB_remove0_B_testListIteratorRemove", testListIteratorRemove(AB_remove0_B(),null, null, Result.IllegalState));
			printTest("AB_remove0_B_testListIteratorRealRemove", testListIteratorRealRemoveAfterNext(AB_remove0_B(), null, 0, "[2]", Result.MatchingValue));
			printTest("AB_remove0_B_testListIteratorSet", testListIteratorSet(AB_remove0_B(), null,null, ELEMENT_D, Result.IllegalState));
			printTest("AB_remove0_B_testListIteratorRealSet", testListIteratorRealSetAfterNext(AB_remove0_B(), null, 0, ELEMENT_D, "[2]", Result.MatchingValue));
			printTest("AB_remove0_B_testListIteratorAdd", testListIteratorAdd(AB_remove0_B(),null, null, ELEMENT_D, Result.NoException));
			printTest("AB_remove0_B_testListIteratorRealAdd", testListIteratorRealAddAfterNext(AB_remove0_B(), null, 0, ELEMENT_D, "[4, 2]", Result.MatchingValue));
			
			printTest("AB_remove0_B_testListIteratorHasNextAfterNext", testListIteratorHasNext(null, getListIteratorAfterCall(AB_remove0_B(), "next", null, null), null, Result.False));
			printTest("AB_remove0_B_testListIteratorNextAfterNext", testListIteratorNext(null,getListIteratorAfterCall(AB_remove0_B(), "next", null, null),null, null, Result.NoSuchElement));
			printTest("AB_remove0_B_testListIteratorNextIndexAfterNext", testListIteratorNextIndex(null,getListIteratorAfterCall(AB_remove0_B(), "next", null, null),null, 1, Result.MatchingValue));
			printTest("AB_remove0_B_testListIteratorHasPreviousAfterNext", testListIteratorHasPrevious(null,getListIteratorAfterCall(AB_remove0_B(), "next", null, null),null, Result.True));
			printTest("AB_remove0_B_testListIteratorPreviousAfterNext", testListIteratorPrevious(null, getListIteratorAfterCall(AB_remove0_B(), "next", null, null), null, ELEMENT_B, Result.MatchingValue));
			printTest("AB_remove0_B_testListIteratorPreviousIndexAfterNext", testListIteratorPreviousIndex(null, getListIteratorAfterCall(AB_remove0_B(), "next", null, null), null, 0, Result.MatchingValue));
			printTest("AB_remove0_B_testListIteratorRemoveAfterNext", testListIteratorRemove(null, getListIteratorAfterCall(AB_remove0_B(), "next", null, null), null, Result.NoException));
			printTest("AB_remove0_B_testListIteratorRealRemoveAfterNext", testListIteratorRealRemoveAfterNext(AB_remove0_B(), null, 1, "[]", Result.MatchingValue));
			printTest("AB_remove0_B_testListIteratorSetAfterNext", testListIteratorSet(null, getListIteratorAfterCall(AB_remove0_B(), "next", null, null), null, ELEMENT_D, Result.NoException));
			printTest("AB_remove0_B_testListIteratorRealSetAfterNext", testListIteratorRealSetAfterNext(AB_remove0_B(), null, 1, ELEMENT_D, "[4]", Result.MatchingValue));
			printTest("AB_remove0_B_testListIteratorAddAfterNext", testListIteratorAdd(null, getListIteratorAfterCall(AB_remove0_B(), "next", null, null), null, ELEMENT_D, Result.NoException));
			printTest("AB_remove0_B_testListIteratorRealAddAfterNext", testListIteratorRealAddAfterNext(AB_remove0_B(), null, 1, ELEMENT_D, "[2, 4]", Result.MatchingValue));
			
			printTest("AB_remove0_B_testListIterator0HasNext", testListIteratorHasNext(AB_remove0_B(), null, 0, Result.True));
			printTest("AB_remove0_B_testListIterator0Next", testListIteratorNext(AB_remove0_B(), null, 0, ELEMENT_B, Result.MatchingValue));
			printTest("AB_remove0_B_testListIterator0NextIndex", testListIteratorNextIndex(AB_remove0_B(), null, 0, 0, Result.MatchingValue));
			printTest("AB_remove0_B_testListIterator0HasPrevious", testListIteratorHasPrevious(AB_remove0_B(), null, 0, Result.False));
			printTest("AB_remove0_B_testListIterator0Previous", testListIteratorPrevious(AB_remove0_B(), null, 0, null, Result.NoSuchElement));
			printTest("AB_remove0_B_testListIterator0PreviousIndex", testListIteratorPreviousIndex(AB_remove0_B(), null, 0, -1, Result.MatchingValue));
			printTest("AB_remove0_B_testListIterator0Remove", testListIteratorRemove(AB_remove0_B(), null, 0, Result.IllegalState));
			printTest("AB_remove0_B_testListIterator0RealRemove", testListIteratorRealRemoveAfterNext(AB_remove0_B(), 0, 0, "[2]", Result.MatchingValue));
			printTest("AB_remove0_B_testListIterator0Set", testListIteratorSet(AB_remove0_B(), null, 0, ELEMENT_D, Result.IllegalState));
			printTest("AB_remove0_B_testListIterator0RealSet", testListIteratorRealSetAfterNext(AB_remove0_B(), 0, 0, ELEMENT_D, "[2]", Result.MatchingValue));
			printTest("AB_remove0_B_testListIterator0Add", testListIteratorAdd(AB_remove0_B(), null, 0, ELEMENT_D, Result.NoException));
			printTest("AB_remove0_B_testListIterator0RealAdd", testListIteratorRealAddAfterNext(AB_remove0_B(), 0, 0, ELEMENT_D, "[4, 2]", Result.MatchingValue));
			
			printTest("AB_remove0_B_testListIterator0HasNextAfterNext", testListIteratorHasNext(null, getListIteratorAfterCall(AB_remove0_B(), "next", 0, null), null, Result.False));
			printTest("AB_remove0_B_testListIterator0NextAfterNext", testListIteratorNext(null, getListIteratorAfterCall(AB_remove0_B(), "next", 0, null), null, null, Result.NoSuchElement));
			printTest("AB_remove0_B_testListIterator0NextIndexAfterNext", testListIteratorNextIndex(null, getListIteratorAfterCall(AB_remove0_B(), "next", 0, null), null, 1, Result.MatchingValue));
			printTest("AB_remove0_B_testListIterator0HasPreviousAfterNext", testListIteratorHasPrevious(null, getListIteratorAfterCall(AB_remove0_B(), "next", 0, null), null, Result.True));
			printTest("AB_remove0_B_testListIterator0PreviousAfterNext", testListIteratorPrevious(null, getListIteratorAfterCall(AB_remove0_B(), "next", 0, null), null, ELEMENT_B, Result.MatchingValue));
			printTest("AB_remove0_B_testListIterator0PreviousIndexAfterNext", testListIteratorPreviousIndex(null, getListIteratorAfterCall(AB_remove0_B(), "next", 0, null), null, 0, Result.MatchingValue));
			printTest("AB_remove0_B_testListIterator0RemoveAfterNext", testListIteratorRemove(null, getListIteratorAfterCall(AB_remove0_B(), "next", 0, null), null, Result.NoException));
			printTest("AB_remove0_B_testListIterator0RealRemoveAfterNext", testListIteratorRealRemoveAfterNext(AB_remove0_B(), 0, 1, "[]", Result.MatchingValue));
			printTest("AB_remove0_B_testListIterator0SetAfterNext", testListIteratorSet(null, getListIteratorAfterCall(AB_remove0_B(), "next", 0, null), null, ELEMENT_D, Result.NoException));
			printTest("AB_remove0_B_testListIterator0RealSetAfterNext", testListIteratorRealSetAfterNext(AB_remove0_B(), 0, 1, ELEMENT_D, "[4]", Result.MatchingValue));
			printTest("AB_remove0_B_testListIterator0AddAfterNext", testListIteratorAdd(null, getListIteratorAfterCall(AB_remove0_B(), "next", 0, null), null, ELEMENT_D, Result.NoException));
			printTest("AB_remove0_B_testListIterator0RealAddAfterNext", testListIteratorRealAddAfterNext(AB_remove0_B(), 0, 1, ELEMENT_D, "[2, 4]", Result.MatchingValue));
			
			printTest("AB_remove0_B_testListIterator1HasNext", testListIteratorHasNext(AB_remove0_B(), null, 1, Result.False));
			printTest("AB_remove0_B_testListIterator1Next", testListIteratorNext(AB_remove0_B(), null, 1, null, Result.NoSuchElement));
			printTest("AB_remove0_B_testListIterator1NextIndex", testListIteratorNextIndex(AB_remove0_B(), null, 1, 1, Result.MatchingValue));
			printTest("AB_remove0_B_testListIterator1HasPrevious", testListIteratorHasPrevious(AB_remove0_B(), null, 1, Result.True));
			printTest("AB_remove0_B_testListIterator1Previous", testListIteratorPrevious(AB_remove0_B(), null, 1, ELEMENT_B, Result.MatchingValue));
			printTest("AB_remove0_B_testListIterator1PreviousIndex", testListIteratorPreviousIndex(AB_remove0_B(), null, 1, 0, Result.MatchingValue));
			printTest("AB_remove0_B_testListIterator1Remove", testListIteratorRemove(AB_remove0_B(), null, 1, Result.IllegalState));
			printTest("AB_remove0_B_testListIterator1RealRemove", testListIteratorRealRemoveAfterNext(AB_remove0_B(), 1, 0, "[2]", Result.MatchingValue));
			printTest("AB_remove0_B_testListIterator1Set", testListIteratorSet(AB_remove0_B(), null, 1, ELEMENT_D, Result.IllegalState));
			printTest("AB_remove0_B_testListIterator1RealSet", testListIteratorRealSetAfterNext(AB_remove0_B(), 1, 0, ELEMENT_D, "[2]", Result.MatchingValue));
			printTest("AB_remove0_B_testListIterator1Add", testListIteratorAdd(AB_remove0_B(), null, 1, ELEMENT_D, Result.NoException));
			printTest("AB_remove0_B_testListIterator1RealAdd", testListIteratorRealAddAfterNext(AB_remove0_B(), 1, 0, ELEMENT_D, "[2, 4]", Result.MatchingValue));
			
			printTest("AB_remove0_B_testListIterator1HasNextAfterPrev", testListIteratorHasNext(null, getListIteratorAfterCall(AB_remove0_B(), "previous", 1, null), 1, Result.True));
			printTest("AB_remove0_B_testListIterator1NextAfterPrev", testListIteratorNext(null, getListIteratorAfterCall(AB_remove0_B(), "previous", 1, null), 1, ELEMENT_B, Result.MatchingValue));
			printTest("AB_remove0_B_testListIterator1NextIndexAfterPrev", testListIteratorNextIndex(null, getListIteratorAfterCall(AB_remove0_B(), "previous", 1, null), 1, 0, Result.MatchingValue));
			printTest("AB_remove0_B_testListIterator1HasPreviousAfterPrev", testListIteratorHasPrevious(null, getListIteratorAfterCall(AB_remove0_B(), "previous", 1, null), 1, Result.False));
			printTest("AB_remove0_B_testListIterator1PreviousAfterPrev", testListIteratorPrevious(null, getListIteratorAfterCall(AB_remove0_B(), "previous", 1, null), 1, null, Result.NoSuchElement));
			printTest("AB_remove0_B_testListIterator1PreviousIndexAfterPrev", testListIteratorPreviousIndex(null, getListIteratorAfterCall(AB_remove0_B(), "previous", 1, null), 1, -1, Result.MatchingValue));
			printTest("AB_remove0_B_testListIterator1RemoveAfterPrev", testListIteratorRemove(null, getListIteratorAfterCall(AB_remove0_B(), "previous", 1, null), 1, Result.NoException));
			printTest("AB_remove0_B_testListIterator1RealRemoveAfterPrev", testListIteratorRealRemoveAfterPrev(AB_remove0_B(), 1, 1, "[]", Result.MatchingValue));
			printTest("AB_remove0_B_testListIterator1SetAfterPrev", testListIteratorSet(null, getListIteratorAfterCall(AB_remove0_B(), "previous", 1, null), 1, ELEMENT_D, Result.NoException));
			printTest("AB_remove0_B_testListIterator1RealSetAfterPrev", testListIteratorRealSetAfterPrev(AB_remove0_B(), 1, 1, ELEMENT_D, "[4]", Result.MatchingValue));
			printTest("AB_remove0_B_testListIterator1AddAfterPrev", testListIteratorAdd(null, getListIteratorAfterCall(AB_remove0_B(), "previous", 1, null), 1, ELEMENT_D, Result.NoException));
			printTest("AB_remove0_B_testListIterator1RealAddAfterPrev", testListIteratorRealAddAfterPrev(AB_remove0_B(), 1, 1, ELEMENT_D, "[4, 2]", Result.MatchingValue));
			}
		} catch (Exception e) {
			System.out.printf("***UNABLE TO RUN/COMPLETE %s***\n", "test_AB_remove0_B");
			e.printStackTrace();
		}
	}
	
	//////////////////////////////////////
	// SCENARIO: [A,B] -> remove(1) -> [A]
	//  XXX SCENARIO 25
	//////////////////////////////////////
	private ListADT<Integer> AB_remove1_A() {
		// cast to UnorderedListADT to make addToFront() available
		IndexedListADT<Integer> list = (IndexedListADT<Integer>) newList(); 
		list.add(0,ELEMENT_A);
		list.add(1, ELEMENT_B);
		list.remove(1);
		return list;
	}
	private void test_AB_remove1_A(){
		try {
			// ListADT
			printTest("AB_remove1_A_testRemoveFirst", testRemoveFirst(AB_remove1_A(), ELEMENT_A, Result.MatchingValue));
			printTest("AB_remove1_A_testRemoveLast", testRemoveLast(AB_remove1_A(), ELEMENT_A, Result.MatchingValue));
			printTest("AB_remove1_A_testRemoveA", testRemoveElement(AB_remove1_A(), ELEMENT_A, Result.MatchingValue));
			printTest("AB_remove1_A_testRemoveB", testRemoveElement(AB_remove1_A(), ELEMENT_B, Result.ElementNotFound));
			printTest("AB_remove1_A_testFirst", testFirst(AB_remove1_A(), ELEMENT_A, Result.MatchingValue));
			printTest("AB_remove1_A_testLast", testLast(AB_remove1_A(), ELEMENT_A, Result.MatchingValue));
			printTest("AB_remove1_A_testContainsA", testContains(AB_remove1_A(), ELEMENT_A, Result.True));
			printTest("AB_remove1_A_testContainsB", testContains(AB_remove1_A(), ELEMENT_B, Result.False));
			printTest("AB_remove1_A_testIsEmpty", testIsEmpty(AB_remove1_A(), Result.False));
			printTest("AB_remove1_A_testSize", testSize(AB_remove1_A(), 1));
			printTest("AB_remove1_A_testToString", testToString(AB_remove1_A(), Result.ValidString));
			// UnorderedListADT
			printTest("AB_remove1_A_testAddToFrontB", testAddToFront(AB_remove1_A(), ELEMENT_B, Result.NoException));
			printTest("AB_remove1_A_testAddToRearB", testAddToRear(AB_remove1_A(), ELEMENT_A, Result.NoException));
			printTest(	"AB_remove1_A_testAddAfterCB", testAddAfter(AB_remove1_A(), ELEMENT_C, ELEMENT_B, Result.ElementNotFound));
			printTest(	"AB_remove1_A_testAddAfterAB", testAddAfter(AB_remove1_A(), ELEMENT_A, ELEMENT_B, Result.NoException));
			// IndexedListADT
			printTest("AB_remove1_A_testAddAtIndexNeg1B", testAddAtIndex(AB_remove1_A(), -1, ELEMENT_B, Result.IndexOutOfBounds));
			printTest("AB_remove1_A_testAddAtIndex0B", testAddAtIndex(AB_remove1_A(), 0, ELEMENT_B, Result.NoException));
			printTest("AB_remove1_A_testAddAtIndex1B", testAddAtIndex(AB_remove1_A(), 1, ELEMENT_B, Result.NoException));
			printTest("AB_remove1_A_testSetNeg1B", testSet(AB_remove1_A(), -1, ELEMENT_B, Result.IndexOutOfBounds));
			printTest("AB_remove1_A_testSet0B", testSet(AB_remove1_A(), 0, ELEMENT_B, Result.NoException));
			printTest("AB_remove1_A_testSet1B", testSet(AB_remove1_A(), 1, ELEMENT_B, Result.IndexOutOfBounds));
			printTest("AB_remove1_A_testAddB", testAdd(AB_remove1_A(), ELEMENT_B, Result.NoException));
			printTest("AB_remove1_A_testGetNeg1", testGet(AB_remove1_A(), -1, null, Result.IndexOutOfBounds));
			printTest("AB_remove1_A_testGet0", testGet(AB_remove1_A(), 0, ELEMENT_A, Result.MatchingValue));
			printTest("AB_remove1_A_testGet1", testGet(AB_remove1_A(), 1, null, Result.IndexOutOfBounds));
			printTest("AB_remove1_A_testIndexOfA", testIndexOf(AB_remove1_A(), ELEMENT_A, 0));
			printTest("AB_remove1_A_testIndexOfB", testIndexOf(AB_remove1_A(), ELEMENT_B, -1));
			printTest("AB_remove1_A_testRemoveNeg1", testRemoveIndex(AB_remove1_A(), -1, null, Result.IndexOutOfBounds));
			printTest("AB_remove1_A_testRemove0", testRemoveIndex(AB_remove1_A(), 0, ELEMENT_A, Result.MatchingValue));
			printTest("AB_remove1_A_testRemove1", testRemoveIndex(AB_remove1_A(), 1, null, Result.IndexOutOfBounds));
			// Iterator
			printTest("AB_remove1_A_testIterator", testIterator(AB_remove1_A(), Result.NoException));
			printTest("AB_remove1_A_testIteratorHasNext", testIteratorHasNext(AB_remove1_A().iterator(), Result.True));
			printTest("AB_remove1_A_testIteratorNext", testIteratorNext(AB_remove1_A().iterator(), ELEMENT_A, Result.MatchingValue));
			printTest("AB_remove1_A_testIteratorRemove", testIteratorRemove(AB_remove1_A().iterator(), Result.IllegalState));
			printTest("AB_remove1_A_iteratorNext_testIteratorHasNext", testIteratorHasNext(iteratorAfterNext(AB_remove1_A(), 1), Result.False));
			printTest("AB_remove1_A_iteratorNext_testIteratorNext", testIteratorNext(iteratorAfterNext(AB_remove1_A(), 1), null, Result.NoSuchElement));
			printTest("AB_remove1_A_iteratorNext_testIteratorRemove", testIteratorRemove(iteratorAfterNext(AB_remove1_A(), 1), Result.NoException));
			printTest("AB_remove1_A_iteratorNextRemove_testIteratorHasNext", testIteratorHasNext(iteratorAfterRemove(iteratorAfterNext(AB_remove1_A(), 1)), Result.False));
			printTest("AB_remove1_A_iteratorNextRemove_testIteratorNext", testIteratorNext(iteratorAfterRemove(iteratorAfterNext(AB_remove1_A(), 1)), null, Result.NoSuchElement));
			printTest("AB_remove1_A_iteratorNextRemove_testIteratorRemove", testIteratorRemove(iteratorAfterRemove(iteratorAfterNext(AB_remove1_A(), 1)), Result.IllegalState));
			printTest("AB_remove1_A_iteratorTestRealRemove",testIteratorRealRemove(AB_remove1_A(), 1, "[]", Result.MatchingValue));
			//ListIterator
			if(newList().getClass()==testDLL().getClass()){
			printTest("AB_remove1_A_testListIteratorUnIndexed", testListIteratorUnIndexed(AB_remove1_A(), Result.NoException));
			printTest("AB_remove1_A_testListIteratorIndexedNeg1", testListIteratorIndexed(AB_remove1_A(), -1, Result.IndexOutOfBounds));
			printTest("AB_remove1_A_testListIteratorIndexed0", testListIteratorIndexed(AB_remove1_A(), 0, Result.NoException));
			printTest("AB_remove1_A_testListIteratorIndexed1", testListIteratorIndexed(AB_remove1_A(), 1, Result.NoException));
			printTest("AB_remove1_A_testListIteratorIndexed2", testListIteratorIndexed(AB_remove1_A(), 2, Result.IndexOutOfBounds));
			
			printTest("AB_remove1_A_testListIteratorHasNext", testListIteratorHasNext(AB_remove1_A(),null, null, Result.True));
			printTest("AB_remove1_A_testListIteratorNext", testListIteratorNext(AB_remove1_A(),null, null, ELEMENT_A, Result.MatchingValue));
			printTest("AB_remove1_A_testListIteratorNextIndex", testListIteratorNextIndex(AB_remove1_A(),null, null, 0, Result.MatchingValue));
			printTest("AB_remove1_A_testListIteratorHasPrevious", testListIteratorHasPrevious(AB_remove1_A(),null, null, Result.False));
			printTest("AB_remove1_A_testListIteratorPrevious", testListIteratorPrevious(AB_remove1_A(),null, null, null, Result.NoSuchElement));
			printTest("AB_remove1_A_testListIteratorPreviousIndex", testListIteratorPreviousIndex(AB_remove1_A(),null, null, -1, Result.MatchingValue));
			printTest("AB_remove1_A_testListIteratorRemove", testListIteratorRemove(AB_remove1_A(),null, null, Result.IllegalState));
			printTest("AB_remove1_A_testListIteratorRealRemove", testListIteratorRealRemoveAfterNext(AB_remove1_A(), null, 0, "[1]", Result.MatchingValue));
			printTest("AB_remove1_A_testListIteratorSet", testListIteratorSet(AB_remove1_A(), null,null, ELEMENT_D, Result.IllegalState));
			printTest("AB_remove1_A_testListIteratorRealSet", testListIteratorRealSetAfterNext(AB_remove1_A(), null, 0, ELEMENT_D, "[1]", Result.MatchingValue));
			printTest("AB_remove1_A_testListIteratorAdd", testListIteratorAdd(AB_remove1_A(),null, null, ELEMENT_D, Result.NoException));
			printTest("AB_remove1_A_testListIteratorRealAdd", testListIteratorRealAddAfterNext(AB_remove1_A(), null, 0, ELEMENT_D, "[4, 1]", Result.MatchingValue));
			
			printTest("AB_remove1_A_testListIteratorHasNextAfterNext", testListIteratorHasNext(null, getListIteratorAfterCall(AB_remove1_A(), "next", null, null), null, Result.False));
			printTest("AB_remove1_A_testListIteratorNextAfterNext", testListIteratorNext(null,getListIteratorAfterCall(AB_remove1_A(), "next", null, null),null, null, Result.NoSuchElement));
			printTest("AB_remove1_A_testListIteratorNextIndexAfterNext", testListIteratorNextIndex(null,getListIteratorAfterCall(AB_remove1_A(), "next", null, null),null, 1, Result.MatchingValue));
			printTest("AB_remove1_A_testListIteratorHasPreviousAfterNext", testListIteratorHasPrevious(null,getListIteratorAfterCall(AB_remove1_A(), "next", null, null),null, Result.True));
			printTest("AB_remove1_A_testListIteratorPreviousAfterNext", testListIteratorPrevious(null, getListIteratorAfterCall(AB_remove1_A(), "next", null, null), null, ELEMENT_A, Result.MatchingValue));
			printTest("AB_remove1_A_testListIteratorPreviousIndexAfterNext", testListIteratorPreviousIndex(null, getListIteratorAfterCall(AB_remove1_A(), "next", null, null), null, 0, Result.MatchingValue));
			printTest("AB_remove1_A_testListIteratorRemoveAfterNext", testListIteratorRemove(null, getListIteratorAfterCall(AB_remove1_A(), "next", null, null), null, Result.NoException));
			printTest("AB_remove1_A_testListIteratorRealRemoveAfterNext", testListIteratorRealRemoveAfterNext(AB_remove1_A(), null, 1, "[]", Result.MatchingValue));
			printTest("AB_remove1_A_testListIteratorSetAfterNext", testListIteratorSet(null, getListIteratorAfterCall(AB_remove1_A(), "next", null, null), null, ELEMENT_D, Result.NoException));
			printTest("AB_remove1_A_testListIteratorRealSetAfterNext", testListIteratorRealSetAfterNext(AB_remove1_A(), null, 1, ELEMENT_D, "[4]", Result.MatchingValue));
			printTest("AB_remove1_A_testListIteratorAddAfterNext", testListIteratorAdd(null, getListIteratorAfterCall(AB_remove1_A(), "next", null, null), null, ELEMENT_D, Result.NoException));
			printTest("AB_remove1_A_testListIteratorRealAddAfterNext", testListIteratorRealAddAfterNext(AB_remove1_A(), null, 1, ELEMENT_D, "[1, 4]", Result.MatchingValue));
			
			printTest("AB_remove1_A_testListIterator0HasNext", testListIteratorHasNext(AB_remove1_A(), null, 0, Result.True));
			printTest("AB_remove1_A_testListIterator0Next", testListIteratorNext(AB_remove1_A(), null, 0, ELEMENT_A, Result.MatchingValue));
			printTest("AB_remove1_A_testListIterator0NextIndex", testListIteratorNextIndex(AB_remove1_A(), null, 0, 0, Result.MatchingValue));
			printTest("AB_remove1_A_testListIterator0HasPrevious", testListIteratorHasPrevious(AB_remove1_A(), null, 0, Result.False));
			printTest("AB_remove1_A_testListIterator0Previous", testListIteratorPrevious(AB_remove1_A(), null, 0, null, Result.NoSuchElement));
			printTest("AB_remove1_A_testListIterator0PreviousIndex", testListIteratorPreviousIndex(AB_remove1_A(), null, 0, -1, Result.MatchingValue));
			printTest("AB_remove1_A_testListIterator0Remove", testListIteratorRemove(AB_remove1_A(), null, 0, Result.IllegalState));
			printTest("AB_remove1_A_testListIterator0RealRemove", testListIteratorRealRemoveAfterNext(AB_remove1_A(), 0, 0, "[1]", Result.MatchingValue));
			printTest("AB_remove1_A_testListIterator0Set", testListIteratorSet(AB_remove1_A(), null, 0, ELEMENT_D, Result.IllegalState));
			printTest("AB_remove1_A_testListIterator0RealSet", testListIteratorRealSetAfterNext(AB_remove1_A(), 0, 0, ELEMENT_D, "[1]", Result.MatchingValue));
			printTest("AB_remove1_A_testListIterator0Add", testListIteratorAdd(AB_remove1_A(), null, 0, ELEMENT_D, Result.NoException));
			printTest("AB_remove1_A_testListIterator0RealAdd", testListIteratorRealAddAfterNext(AB_remove1_A(), 0, 0, ELEMENT_D, "[4, 1]", Result.MatchingValue));
			
			printTest("AB_remove1_A_testListIterator0HasNextAfterNext", testListIteratorHasNext(null, getListIteratorAfterCall(AB_remove1_A(), "next", 0, null), null, Result.False));
			printTest("AB_remove1_A_testListIterator0NextAfterNext", testListIteratorNext(null, getListIteratorAfterCall(AB_remove1_A(), "next", 0, null), null, null, Result.NoSuchElement));
			printTest("AB_remove1_A_testListIterator0NextIndexAfterNext", testListIteratorNextIndex(null, getListIteratorAfterCall(AB_remove1_A(), "next", 0, null), null, 1, Result.MatchingValue));
			printTest("AB_remove1_A_testListIterator0HasPreviousAfterNext", testListIteratorHasPrevious(null, getListIteratorAfterCall(AB_remove1_A(), "next", 0, null), null, Result.True));
			printTest("AB_remove1_A_testListIterator0PreviousAfterNext", testListIteratorPrevious(null, getListIteratorAfterCall(AB_remove1_A(), "next", 0, null), null, ELEMENT_A, Result.MatchingValue));
			printTest("AB_remove1_A_testListIterator0PreviousIndexAfterNext", testListIteratorPreviousIndex(null, getListIteratorAfterCall(AB_remove1_A(), "next", 0, null), null, 0, Result.MatchingValue));
			printTest("AB_remove1_A_testListIterator0RemoveAfterNext", testListIteratorRemove(null, getListIteratorAfterCall(AB_remove1_A(), "next", 0, null), null, Result.NoException));
			printTest("AB_remove1_A_testListIterator0RealRemoveAfterNext", testListIteratorRealRemoveAfterNext(AB_remove1_A(), 0, 1, "[]", Result.MatchingValue));
			printTest("AB_remove1_A_testListIterator0SetAfterNext", testListIteratorSet(null, getListIteratorAfterCall(AB_remove1_A(), "next", 0, null), null, ELEMENT_D, Result.NoException));
			printTest("AB_remove1_A_testListIterator0RealSetAfterNext", testListIteratorRealSetAfterNext(AB_remove1_A(), 0, 1, ELEMENT_D, "[4]", Result.MatchingValue));
			printTest("AB_remove1_A_testListIterator0AddAfterNext", testListIteratorAdd(null, getListIteratorAfterCall(AB_remove1_A(), "next", 0, null), null, ELEMENT_D, Result.NoException));
			printTest("AB_remove1_A_testListIterator0RealAddAfterNext", testListIteratorRealAddAfterNext(AB_remove1_A(), 0, 1, ELEMENT_D, "[1, 4]", Result.MatchingValue));
			
			printTest("AB_remove1_A_testListIterator1HasNext", testListIteratorHasNext(AB_remove1_A(), null, 1, Result.False));
			printTest("AB_remove1_A_testListIterator1Next", testListIteratorNext(AB_remove1_A(), null, 1, null, Result.NoSuchElement));
			printTest("AB_remove1_A_testListIterator1NextIndex", testListIteratorNextIndex(AB_remove1_A(), null, 1, 1, Result.MatchingValue));
			printTest("AB_remove1_A_testListIterator1HasPrevious", testListIteratorHasPrevious(AB_remove1_A(), null, 1, Result.True));
			printTest("AB_remove1_A_testListIterator1Previous", testListIteratorPrevious(AB_remove1_A(), null, 1, ELEMENT_A, Result.MatchingValue));
			printTest("AB_remove1_A_testListIterator1PreviousIndex", testListIteratorPreviousIndex(AB_remove1_A(), null, 1, 0, Result.MatchingValue));
			printTest("AB_remove1_A_testListIterator1Remove", testListIteratorRemove(AB_remove1_A(), null, 1, Result.IllegalState));
			printTest("AB_remove1_A_testListIterator1RealRemove", testListIteratorRealRemoveAfterNext(AB_remove1_A(), 1, 0, "[1]", Result.MatchingValue));
			printTest("AB_remove1_A_testListIterator1Set", testListIteratorSet(AB_remove1_A(), null, 1, ELEMENT_D, Result.IllegalState));
			printTest("AB_remove1_A_testListIterator1RealSet", testListIteratorRealSetAfterNext(AB_remove1_A(), 1, 0, ELEMENT_D, "[1]", Result.MatchingValue));
			printTest("AB_remove1_A_testListIterator1Add", testListIteratorAdd(AB_remove1_A(), null, 1, ELEMENT_D, Result.NoException));
			printTest("AB_remove1_A_testListIterator1RealAdd", testListIteratorRealAddAfterNext(AB_remove1_A(), 1, 0, ELEMENT_D, "[1, 4]", Result.MatchingValue));
			
			printTest("AB_remove1_A_testListIterator1HasNextAfterPrev", testListIteratorHasNext(null, getListIteratorAfterCall(AB_remove1_A(), "previous", 1, null), 1, Result.True));
			printTest("AB_remove1_A_testListIterator1NextAfterPrev", testListIteratorNext(null, getListIteratorAfterCall(AB_remove1_A(), "previous", 1, null), 1, ELEMENT_A, Result.MatchingValue));
			printTest("AB_remove1_A_testListIterator1NextIndexAfterPrev", testListIteratorNextIndex(null, getListIteratorAfterCall(AB_remove1_A(), "previous", 1, null), 1, 0, Result.MatchingValue));
			printTest("AB_remove1_A_testListIterator1HasPreviousAfterPrev", testListIteratorHasPrevious(null, getListIteratorAfterCall(AB_remove1_A(), "previous", 1, null), 1, Result.False));
			printTest("AB_remove1_A_testListIterator1PreviousAfterPrev", testListIteratorPrevious(null, getListIteratorAfterCall(AB_remove1_A(), "previous", 1, null), 1, null, Result.NoSuchElement));
			printTest("AB_remove1_A_testListIterator1PreviousIndexAfterPrev", testListIteratorPreviousIndex(null, getListIteratorAfterCall(AB_remove1_A(), "previous", 1, null), 1, -1, Result.MatchingValue));
			printTest("AB_remove1_A_testListIterator1RemoveAfterPrev", testListIteratorRemove(null, getListIteratorAfterCall(AB_remove1_A(), "previous", 1, null), 1, Result.NoException));
			printTest("AB_remove1_A_testListIterator1RealRemoveAfterPrev", testListIteratorRealRemoveAfterPrev(AB_remove1_A(), 1, 1, "[]", Result.MatchingValue));
			printTest("AB_remove1_A_testListIterator1SetAfterPrev", testListIteratorSet(null, getListIteratorAfterCall(AB_remove1_A(), "previous", 1, null), 1, ELEMENT_D, Result.NoException));
			printTest("AB_remove1_A_testListIterator1RealSetAfterPrev", testListIteratorRealSetAfterPrev(AB_remove1_A(), 1, 1, ELEMENT_D, "[4]", Result.MatchingValue));
			printTest("AB_remove1_A_testListIterator1AddAfterPrev", testListIteratorAdd(null, getListIteratorAfterCall(AB_remove1_A(), "previous", 1, null), 1, ELEMENT_D, Result.NoException));
			printTest("AB_remove1_A_testListIterator1RealAddAfterPrev", testListIteratorRealAddAfterPrev(AB_remove1_A(), 1, 1, ELEMENT_D, "[4, 1]", Result.MatchingValue));
			}
		}  catch (Exception e) {
			System.out.printf("***UNABLE TO RUN/COMPLETE %s***\n", "test_AB_remove1_A");
			e.printStackTrace();
		}
	}
	
	//////////////////////////////////////
	// SCENARIO: [A,B] -> set(0,C) -> [C,B]
	//  XXX SCENARIO 26
	//////////////////////////////////////
	private ListADT<Integer> AB_set0C_CB() {
		// cast to UnorderedListADT to make addToFront() available
		IndexedListADT<Integer> list = (IndexedListADT<Integer>) newList(); 
		list.add(0,ELEMENT_A);
		list.add(1, ELEMENT_B);
		list.set(0, ELEMENT_C);
		return list;
	}
	private void test_AB_set0C_CB(){
		try {
			// ListADT
			printTest("AB_set0C_CB_testRemoveFirst", testRemoveFirst(AB_set0C_CB(), ELEMENT_C, Result.MatchingValue));
			printTest("AB_set0C_CB_testRemoveLast", testRemoveLast(AB_set0C_CB(), ELEMENT_B, Result.MatchingValue));
			printTest("AB_set0C_CB_testRemoveA", testRemoveElement(AB_set0C_CB(), ELEMENT_C, Result.MatchingValue));
			printTest("AB_set0C_CB_testRemoveB", testRemoveElement(AB_set0C_CB(), ELEMENT_B, Result.MatchingValue));
			printTest("AB_set0C_CB_testRemoveC", testRemoveElement(AB_set0C_CB(), ELEMENT_D, Result.ElementNotFound));
			printTest("AB_set0C_CB_testFirst", testFirst(AB_set0C_CB(), ELEMENT_C, Result.MatchingValue));
			printTest("AB_set0C_CB_testLast", testLast(AB_set0C_CB(), ELEMENT_B, Result.MatchingValue));
			printTest("AB_set0C_CB_testContainsA", testContains(AB_set0C_CB(), ELEMENT_C, Result.True));
			printTest("AB_set0C_CB_testContainsB", testContains(AB_set0C_CB(), ELEMENT_B, Result.True));
			printTest("AB_set0C_CB_testContainsC", testContains(AB_set0C_CB(), ELEMENT_D, Result.False));
			printTest("AB_set0C_CB_testIsEmpty", testIsEmpty(AB_set0C_CB(), Result.False));
			printTest("AB_set0C_CB_testSize", testSize(AB_set0C_CB(), 2));
			printTest("AB_set0C_CB_testToString", testToString(AB_set0C_CB(), Result.ValidString));
			// UnorderedListADT
			printTest("AB_set0C_CB_testAddToFrontA", testAddToFront(AB_set0C_CB(), ELEMENT_D, Result.NoException));
			printTest("AB_set0C_CB_testAddToRearA", testAddToRear(AB_set0C_CB(), ELEMENT_D, Result.NoException));
			printTest(	"AB_set0C_CB_testAddAfterAC", testAddAfter(AB_set0C_CB(), ELEMENT_C, ELEMENT_D, Result.NoException));
			printTest(	"AB_set0C_CB_testAddAfterBC", testAddAfter(AB_set0C_CB(), ELEMENT_B, ELEMENT_D, Result.NoException));
			printTest(	"AB_set0C_CB_testAddAfterDC", testAddAfter(AB_set0C_CB(), ELEMENT_A, ELEMENT_D, Result.ElementNotFound));
			// IndexedListADT
			printTest("AB_set0C_CB_testAddAtIndexNeg1", testAddAtIndex(AB_set0C_CB(), -1, null, Result.IndexOutOfBounds));
			printTest("AB_set0C_CB_testAddAtIndex0", testAddAtIndex(AB_set0C_CB(), 0, ELEMENT_D, Result.NoException));
			printTest("AB_set0C_CB_testAddAtIndex1", testAddAtIndex(AB_set0C_CB(), 1, ELEMENT_D, Result.NoException));
			printTest("AB_set0C_CB_testAddAtIndex2", testAddAtIndex(AB_set0C_CB(), 2, ELEMENT_D, Result.NoException));
			printTest("AB_set0C_CB_testAddAtIndex3", testAddAtIndex(AB_set0C_CB(), 3, null, Result.IndexOutOfBounds));
			printTest("AB_set0C_CB_testSetNeg1C", testSet(AB_set0C_CB(), -1, null, Result.IndexOutOfBounds));
			printTest("AB_set0C_CB_testSet0C", testSet(AB_set0C_CB(), 0, ELEMENT_D, Result.NoException));
			printTest("AB_set0C_CB_testSet1C", testSet(AB_set0C_CB(), 1, ELEMENT_D, Result.NoException));
			printTest("AB_set0C_CB_testSet2C", testSet(AB_set0C_CB(), 2, null, Result.IndexOutOfBounds));
			printTest("AB_set0C_CB_testAddC", testAdd(AB_set0C_CB(), ELEMENT_D, Result.NoException));
			printTest("AB_set0C_CB_testGetNeg1", testGet(AB_set0C_CB(), -1, null, Result.IndexOutOfBounds));
			printTest("AB_set0C_CB_testGet0", testGet(AB_set0C_CB(), 0, ELEMENT_C , Result.MatchingValue));
			printTest("AB_set0C_CB_testGet1", testGet(AB_set0C_CB(), 1, ELEMENT_B , Result.MatchingValue));
			printTest("AB_set0C_CB_testGet2", testGet(AB_set0C_CB(), 2, null, Result.IndexOutOfBounds));
			printTest("AB_set0C_CB_testIndexOfA", testIndexOf(AB_set0C_CB(), ELEMENT_C, 0));
			printTest("AB_set0C_CB_testIndexOfB", testIndexOf(AB_set0C_CB(), ELEMENT_B, 1));
			printTest("AB_set0C_CB_testIndexOfC", testIndexOf(AB_set0C_CB(), ELEMENT_D, -1));
			printTest("AB_set0C_CB_testRemoveNeg1", testRemoveIndex(AB_set0C_CB(), -1, null, Result.IndexOutOfBounds));
			printTest("AB_set0C_CB_testRemove0", testRemoveIndex(AB_set0C_CB(), 0, ELEMENT_C, Result.MatchingValue));
			printTest("AB_set0C_CB_testRemove1", testRemoveIndex(AB_set0C_CB(), 1, ELEMENT_B, Result.MatchingValue));
			printTest("AB_set0C_CB_testRemove2", testRemoveIndex(AB_set0C_CB(), 2, null, Result.IndexOutOfBounds));
			// Iterator
			printTest("AB_set0C_CB_testIterator", testIterator(AB_set0C_CB(), Result.NoException));
			printTest("AB_set0C_CB_testIteratorHasNext", testIteratorHasNext(AB_set0C_CB().iterator(), Result.True));
			printTest("AB_set0C_CB_testIteratorNext", testIteratorNext(AB_set0C_CB().iterator(), ELEMENT_C, Result.MatchingValue));
			printTest("AB_set0C_CB_testIteratorRemove", testIteratorRemove(AB_set0C_CB().iterator(), Result.IllegalState));
			printTest("AB_set0C_CB_testIteratorHasNextAfterNext", testIteratorHasNext(iteratorAfterNext(AB_set0C_CB(),1), Result.True));
			printTest("AB_set0C_CB_testIteratorNextAfterNext", testIteratorNext(iteratorAfterNext(AB_set0C_CB(),1), ELEMENT_B, Result.MatchingValue));
			printTest("AB_set0C_CB_testIteratorRemoveAfterNext", testIteratorRemove(iteratorAfterNext(AB_set0C_CB(),1), Result.NoException));
			printTest("AB_set0C_CB_testIteratorHasNextAfterNext2", testIteratorHasNext(iteratorAfterNext(AB_set0C_CB(),2), Result.False));
			printTest("AB_set0C_CB_testIteratorNextAfterNext2", testIteratorNext(iteratorAfterNext(AB_set0C_CB(),2), null, Result.NoSuchElement));
			printTest("AB_set0C_CB_testIteratorRemoveAfterNext2", testIteratorRemove(iteratorAfterNext(AB_set0C_CB(),2), Result.NoException));
			printTest("AB_set0C_CB_testIteratorNextAfterRemoveNext", testIteratorNext(iteratorAfterRemove(iteratorAfterNext(AB_set0C_CB(),1)),ELEMENT_B,  Result.MatchingValue));
			printTest("AB_set0C_CB_testIteratorNextAfterRemoveNext2", testIteratorNext(iteratorAfterRemove(iteratorAfterNext(AB_set0C_CB(),2)),null,  Result.NoSuchElement));
			printTest("AB_set0C_CB_testIteratorHasNextAfterRemoveNext",testIteratorHasNext(iteratorAfterRemove(iteratorAfterNext(AB_set0C_CB(),1)),Result.True));
			printTest("AB_set0C_CB_testIteratorHasNextAfterRemoveNext2",testIteratorHasNext(iteratorAfterRemove(iteratorAfterNext(AB_set0C_CB(),2)),Result.False));
			printTest("AB_set0C_CB_iteratorTestRealRemove",testIteratorRealRemove(AB_set0C_CB(), 1, "[2]", Result.MatchingValue));
			printTest("AB_set0C_CB_iteratorTestRealRemove2",testIteratorRealRemove(AB_set0C_CB(), 2, "[3]", Result.MatchingValue));
		} catch (Exception e) {
			System.out.printf("***UNABLE TO RUN/COMPLETE %s***\n", "test_AB_set0C_CB");
			e.printStackTrace();
		}
	}
	
	//////////////////////////////////////
	// SCENARIO: [A,B] -> set(1,C) -> [A,C]
	//  XXX SCENARIO 27
	//////////////////////////////////////
	private ListADT<Integer> AB_set1C_AC() {
		// cast to UnorderedListADT to make addToFront() available
		IndexedListADT<Integer> list = (IndexedListADT<Integer>) newList(); 
		list.add(0,ELEMENT_A);
		list.add(1, ELEMENT_B);
		list.set(1, ELEMENT_C);
		return list;
	}
	private void test_AB_set1C_AC(){
		try {
			// ListADT
			printTest("AB_set1C_AC_testRemoveFirst", testRemoveFirst(AB_set1C_AC(), ELEMENT_A, Result.MatchingValue));
			printTest("AB_set1C_AC_testRemoveLast", testRemoveLast(AB_set1C_AC(), ELEMENT_C, Result.MatchingValue));
			printTest("AB_set1C_AC_testRemoveA", testRemoveElement(AB_set1C_AC(), ELEMENT_A, Result.MatchingValue));
			printTest("AB_set1C_AC_testRemoveB", testRemoveElement(AB_set1C_AC(), ELEMENT_B, Result.ElementNotFound));
			printTest("AB_set1C_AC_testRemoveC", testRemoveElement(AB_set1C_AC(), ELEMENT_C, Result.MatchingValue));
			printTest("AB_set1C_AC_testFirst", testFirst(AB_set1C_AC(), ELEMENT_A, Result.MatchingValue));
			printTest("AB_set1C_AC_testLast", testLast(AB_set1C_AC(), ELEMENT_C, Result.MatchingValue));
			printTest("AB_set1C_AC_testContainsA", testContains(AB_set1C_AC(), ELEMENT_A, Result.True));
			printTest("AB_set1C_AC_testContainsB", testContains(AB_set1C_AC(), ELEMENT_B, Result.False));
			printTest("AB_set1C_AC_testContainsC", testContains(AB_set1C_AC(), ELEMENT_C, Result.True));
			printTest("AB_set1C_AC_testIsEmpty", testIsEmpty(AB_set1C_AC(), Result.False));
			printTest("AB_set1C_AC_testSize", testSize(AB_set1C_AC(), 2));
			printTest("AB_set1C_AC_testToString", testToString(AB_set1C_AC(), Result.ValidString));
			// UnorderedListADT
			printTest("AB_set1C_AC_testAddToFrontC", testAddToFront(AB_set1C_AC(), ELEMENT_C, Result.NoException));
			printTest("AB_set1C_AC_testAddToRearC", testAddToRear(AB_set1C_AC(), ELEMENT_C, Result.NoException));
			printTest(	"AB_set1C_AC_testAddAfterAD", testAddAfter(AB_set1C_AC(), ELEMENT_A, ELEMENT_D, Result.NoException));
			printTest(	"AB_set1C_AC_testAddAfterBD", testAddAfter(AB_set1C_AC(), ELEMENT_B, ELEMENT_D, Result.ElementNotFound));
			printTest(	"AB_set1C_AC_testAddAfterCD", testAddAfter(AB_set1C_AC(), ELEMENT_C, ELEMENT_D, Result.NoException));
			// IndexedListADT
			printTest("AB_set1C_AC_testAddAtIndexNeg1", testAddAtIndex(AB_set1C_AC(), -1, null, Result.IndexOutOfBounds));
			printTest("AB_set1C_AC_testAddAtIndex0", testAddAtIndex(AB_set1C_AC(), 0, ELEMENT_D, Result.NoException));
			printTest("AB_set1C_AC_testAddAtIndex1", testAddAtIndex(AB_set1C_AC(), 1, ELEMENT_D, Result.NoException));
			printTest("AB_set1C_AC_testAddAtIndex2", testAddAtIndex(AB_set1C_AC(), 2, ELEMENT_D, Result.NoException));
			printTest("AB_set1C_AC_testAddAtIndex3", testAddAtIndex(AB_set1C_AC(), 3, null, Result.IndexOutOfBounds));
			printTest("AB_set1C_AC_testSetNeg1D", testSet(AB_set1C_AC(), -1, null, Result.IndexOutOfBounds));
			printTest("AB_set1C_AC_testSet0D", testSet(AB_set1C_AC(), 0, ELEMENT_D, Result.NoException));
			printTest("AB_set1C_AC_testSet1D", testSet(AB_set1C_AC(), 1, ELEMENT_D, Result.NoException));
			printTest("AB_set1C_AC_testSetNeg2D", testSet(AB_set1C_AC(), 2, null, Result.IndexOutOfBounds));
			printTest("AB_set1C_AC_testAddD", testAdd(AB_set1C_AC(), ELEMENT_D, Result.NoException));
			printTest("AB_set1C_AC_testGetNeg1", testGet(AB_set1C_AC(), -1, null, Result.IndexOutOfBounds));
			printTest("AB_set1C_AC_testGet0", testGet(AB_set1C_AC(), 0, ELEMENT_A , Result.MatchingValue));
			printTest("AB_set1C_AC_testGet1", testGet(AB_set1C_AC(), 1, ELEMENT_C , Result.MatchingValue));
			printTest("AB_set1C_AC_testGet2", testGet(AB_set1C_AC(), 2, null, Result.IndexOutOfBounds));
			printTest("AB_set1C_AC_testIndexOfB", testIndexOf(AB_set1C_AC(), ELEMENT_B, -1));
			printTest("AB_set1C_AC_testIndexOfA", testIndexOf(AB_set1C_AC(), ELEMENT_A, 0));
			printTest("AB_set1C_AC_testIndexOfC", testIndexOf(AB_set1C_AC(), ELEMENT_C, 1));
			printTest("AB_set1C_AC_testRemoveNeg1", testRemoveIndex(AB_set1C_AC(), -1, null, Result.IndexOutOfBounds));
			printTest("AB_set1C_AC_testRemove0", testRemoveIndex(AB_set1C_AC(), 0, ELEMENT_A, Result.MatchingValue));
			printTest("AB_set1C_AC_testRemove1", testRemoveIndex(AB_set1C_AC(), 1, ELEMENT_C, Result.MatchingValue));
			printTest("AB_set1C_AC_testRemove2", testRemoveIndex(AB_set1C_AC(), 2, null, Result.IndexOutOfBounds));
			// Iterator
			printTest("AB_set1C_AC_testIterator", testIterator(AB_set1C_AC(), Result.NoException));
			printTest("AB_set1C_AC_testIteratorHasNext", testIteratorHasNext(AB_set1C_AC().iterator(), Result.True));
			printTest("AB_set1C_AC_testIteratorNext", testIteratorNext(AB_set1C_AC().iterator(), ELEMENT_A, Result.MatchingValue));
			printTest("AB_set1C_AC_testIteratorRemove", testIteratorRemove(AB_set1C_AC().iterator(), Result.IllegalState));
			printTest("AB_set1C_AC_testIteratorHasNextAfterNext", testIteratorHasNext(iteratorAfterNext(AB_set1C_AC(),1), Result.True));
			printTest("AB_set1C_AC_testIteratorNextAfterNext", testIteratorNext(iteratorAfterNext(AB_set1C_AC(),1), ELEMENT_C, Result.MatchingValue));
			printTest("AB_set1C_AC_testIteratorRemoveAfterNext", testIteratorRemove(iteratorAfterNext(AB_set1C_AC(),1), Result.NoException));
			printTest("AB_set1C_AC_testIteratorHasNextAfterNext2", testIteratorHasNext(iteratorAfterNext(AB_set1C_AC(),2), Result.False));
			printTest("AB_set1C_AC_testIteratorNextAfterNext2", testIteratorNext(iteratorAfterNext(AB_set1C_AC(),2), null, Result.NoSuchElement));
			printTest("AB_set1C_AC_testIteratorRemoveAfterNext2", testIteratorRemove(iteratorAfterNext(AB_set1C_AC(),2), Result.NoException));
			printTest("AB_set1C_AC_testIteratorNextAfterRemoveNext", testIteratorNext(iteratorAfterRemove(iteratorAfterNext(AB_set1C_AC(),1)),ELEMENT_C,  Result.MatchingValue));
			printTest("AB_set1C_AC_testIteratorNextAfterRemoveNext2", testIteratorNext(iteratorAfterRemove(iteratorAfterNext(AB_set1C_AC(),2)),null,  Result.NoSuchElement));
			printTest("AB_set1C_AC_testIteratorHasNextAfterRemoveNext",testIteratorHasNext(iteratorAfterRemove(iteratorAfterNext(AB_set1C_AC(),1)),Result.True));
			printTest("AB_set1C_AC_testIteratorHasNextAfterRemoveNext2",testIteratorHasNext(iteratorAfterRemove(iteratorAfterNext(AB_set1C_AC(),2)),Result.False));
			printTest("AB_set1C_AC_iteratorTestRealRemove",testIteratorRealRemove(AB_set1C_AC(), 1, "[3]", Result.MatchingValue));
			printTest("AB_set1C_AC_iteratorTestRealRemove2",testIteratorRealRemove(AB_set1C_AC(), 2, "[1]", Result.MatchingValue));
		} catch (Exception e) {
			System.out.printf("***UNABLE TO RUN/COMPLETE %s***\n", "test_AB_set1C_AC");
			e.printStackTrace();
		}
	}
	
	//////////////////////////////////////
	// SCENARIO: [A,B] -> add(C) -> [A,B,C]
	//  XXX SCENARIO 28
	//////////////////////////////////////
	private ListADT<Integer> AB_addC_ABC() {
		// cast to UnorderedListADT to make addToFront() available
		IndexedListADT<Integer> list = (IndexedListADT<Integer>) newList(); 
		list.add(0,ELEMENT_A);
		list.add(1, ELEMENT_B);
		list.add(ELEMENT_C);
		return list;
	}
	private void test_AB_addC_ABC(){
		try {
			// ListADT
			printTest("AB_addC_ABC_testRemoveFirst", testRemoveFirst(AB_addC_ABC(), ELEMENT_A, Result.MatchingValue));
			printTest("AB_addC_ABC_testRemoveLast", testRemoveLast(AB_addC_ABC(), ELEMENT_C, Result.MatchingValue));
			printTest("AB_addC_ABC_testRemoveA", testRemoveElement(AB_addC_ABC(), ELEMENT_A, Result.MatchingValue));
			printTest("AB_addC_ABC_testRemoveB", testRemoveElement(AB_addC_ABC(), ELEMENT_B, Result.MatchingValue));
			printTest("AB_addC_ABC_testRemoveC", testRemoveElement(AB_addC_ABC(), ELEMENT_C, Result.MatchingValue));
			printTest("AB_addC_ABC_testRemoveD", testRemoveElement(AB_addC_ABC(), ELEMENT_D, Result.ElementNotFound));
			printTest("AB_addC_ABC_testFirst", testFirst(AB_addC_ABC(), ELEMENT_A, Result.MatchingValue));
			printTest("AB_addC_ABC_testLast", testLast(AB_addC_ABC(), ELEMENT_C, Result.MatchingValue));
			printTest("AB_addC_ABC_testContainsA", testContains(AB_addC_ABC(), ELEMENT_A, Result.True));
			printTest("AB_addC_ABC_testContainsB", testContains(AB_addC_ABC(), ELEMENT_B, Result.True));
			printTest("AB_addC_ABC_testContainsC", testContains(AB_addC_ABC(), ELEMENT_C, Result.True));
			printTest("AB_addC_ABC_testContainsD", testContains(AB_addC_ABC(), ELEMENT_D, Result.False));
			printTest("AB_addC_ABC_testIsEmpty", testIsEmpty(AB_addC_ABC(), Result.False));
			printTest("AB_addC_ABC_testSize", testSize(AB_addC_ABC(), 3));
			printTest("AB_addC_ABC_testToString", testToString(AB_addC_ABC(), Result.ValidString));
			// UnorderedListADT
			printTest("AB_addC_ABC_testAddToFrontB", testAddToFront(AB_addC_ABC(), ELEMENT_D, Result.NoException));
			printTest("AB_addC_ABC_testAddToRearB", testAddToRear(AB_addC_ABC(), ELEMENT_D, Result.NoException));
			printTest(	"AB_addC_ABC_testAddAfterAD", testAddAfter(AB_addC_ABC(), ELEMENT_A, ELEMENT_D, Result.NoException));
			printTest(	"AB_addC_ABC_testAddAfterBD", testAddAfter(AB_addC_ABC(), ELEMENT_B, ELEMENT_D, Result.NoException));
			printTest(	"AB_addC_ABC_testAddAfterCD", testAddAfter(AB_addC_ABC(), ELEMENT_C, ELEMENT_D, Result.NoException));
			printTest(	"AB_addC_ABC_testAddAfterDD", testAddAfter(AB_addC_ABC(), ELEMENT_D, ELEMENT_D, Result.ElementNotFound));
			// IndexedListADT
			printTest("AB_addC_ABC_testAddAtIndexNeg1D", testAddAtIndex(AB_addC_ABC(), -1, ELEMENT_D, Result.IndexOutOfBounds));
			printTest("AB_addC_ABC_testAddAtIndex0D", testAddAtIndex(AB_addC_ABC(), 0, ELEMENT_D, Result.NoException));
			printTest("AB_addC_ABC_testAddAtIndex1D", testAddAtIndex(AB_addC_ABC(), 1, ELEMENT_D, Result.NoException));
			printTest("AB_addC_ABC_testAddAtIndex2D", testAddAtIndex(AB_addC_ABC(), 2, ELEMENT_D, Result.NoException));
			printTest("AB_addC_ABC_testAddAtIndex3D", testAddAtIndex(AB_addC_ABC(), 3, ELEMENT_D, Result.NoException));
			printTest("AB_addC_ABC_testAddAtIndex4D", testAddAtIndex(AB_addC_ABC(), 4, ELEMENT_D, Result.IndexOutOfBounds));
			printTest("AB_addC_ABC_testSetNeg1D", testSet(AB_addC_ABC(), -1, ELEMENT_D, Result.IndexOutOfBounds));
			printTest("AB_addC_ABC_testSet0D", testSet(AB_addC_ABC(), 0, ELEMENT_D, Result.NoException));
			printTest("AB_addC_ABC_testSet1D", testSet(AB_addC_ABC(), 1, ELEMENT_D, Result.NoException));
			printTest("AB_addC_ABC_testSet2D", testSet(AB_addC_ABC(), 2, ELEMENT_D, Result.NoException));
			printTest("AB_addC_ABC_testSet3D", testSet(AB_addC_ABC(), 3, ELEMENT_D, Result.IndexOutOfBounds));
			printTest("AB_addC_ABC_testAddD", testAdd(AB_addC_ABC(), ELEMENT_D, Result.NoException));
			printTest("AB_addC_ABC_testGetNeg1", testGet(AB_addC_ABC(), -1, null, Result.IndexOutOfBounds));
			printTest("AB_addC_ABC_testGet0", testGet(AB_addC_ABC(), 0, ELEMENT_A, Result.MatchingValue));
			printTest("AB_addC_ABC_testGet1", testGet(AB_addC_ABC(), 1, ELEMENT_B, Result.MatchingValue));
			printTest("AB_addC_ABC_testGet2", testGet(AB_addC_ABC(), 2, ELEMENT_C, Result.MatchingValue));
			printTest("AB_addC_ABC_testGet3", testGet(AB_addC_ABC(), 3, null, Result.IndexOutOfBounds));
			printTest("AB_addC_ABC_testIndexOfA", testIndexOf(AB_addC_ABC(), ELEMENT_A, 0));
			printTest("AB_addC_ABC_testIndexOfB", testIndexOf(AB_addC_ABC(), ELEMENT_B, 1));
			printTest("AB_addC_ABC_testIndexOfC", testIndexOf(AB_addC_ABC(), ELEMENT_C, 2));
			printTest("AB_addC_ABC_testIndexOfD", testIndexOf(AB_addC_ABC(), ELEMENT_D, -1));
			printTest("AB_addC_ABC_testRemoveNeg1", testRemoveIndex(AB_addC_ABC(), -1, null, Result.IndexOutOfBounds));
			printTest("AB_addC_ABC_testRemove0", testRemoveIndex(AB_addC_ABC(), 0, ELEMENT_A, Result.MatchingValue));
			printTest("AB_addC_ABC_testRemove0", testRemoveIndex(AB_addC_ABC(), 1, ELEMENT_B, Result.MatchingValue));
			printTest("AB_addC_ABC_testRemove0", testRemoveIndex(AB_addC_ABC(), 2, ELEMENT_C, Result.MatchingValue));
			printTest("AB_addC_ABC_testRemove3", testRemoveIndex(AB_addC_ABC(), 3, null, Result.IndexOutOfBounds));
			// Iterator
			printTest("AB_addC_ABC_testIterator", testIterator(AB_addC_ABC(), Result.NoException));
			printTest("AB_addC_ABC_testIteratorHasNext", testIteratorHasNext(AB_addC_ABC().iterator(), Result.True));
			printTest("AB_addC_ABC_testIteratorNext", testIteratorNext(AB_addC_ABC().iterator(), ELEMENT_A, Result.MatchingValue));
			printTest("AB_addC_ABC_testIteratorRemove", testIteratorRemove(AB_addC_ABC().iterator(), Result.IllegalState));
			printTest("AB_addC_ABC_testIteratorHasNextAfterNext", testIteratorHasNext(iteratorAfterNext(AB_addC_ABC(),1), Result.True));
			printTest("AB_addC_ABC_testIteratorNextAfterNext", testIteratorNext(iteratorAfterNext(AB_addC_ABC(),1), ELEMENT_B, Result.MatchingValue));
			printTest("AB_addC_ABC_testIteratorRemoveAfterNext", testIteratorRemove(iteratorAfterNext(AB_addC_ABC(),1), Result.NoException));
			printTest("AB_addC_ABC_testIteratorHasNextAfterNext2", testIteratorHasNext(iteratorAfterNext(AB_addC_ABC(),2), Result.True));
			printTest("AB_addC_ABC_testIteratorHasNextAfterNext3", testIteratorHasNext(iteratorAfterNext(AB_addC_ABC(),3), Result.False));
			printTest("AB_addC_ABC_testIteratorNextAfterNext2", testIteratorNext(iteratorAfterNext(AB_addC_ABC(),2), ELEMENT_C, Result.MatchingValue));
			printTest("AB_addC_ABC_testIteratorNextAfterNext3", testIteratorNext(iteratorAfterNext(AB_addC_ABC(),3), null, Result.NoSuchElement));
			printTest("AB_addC_ABC_testIteratorRemoveAfterNext2", testIteratorRemove(iteratorAfterNext(AB_addC_ABC(),2), Result.NoException));
			printTest("AB_addC_ABC_testIteratorRemoveAfterNext3", testIteratorRemove(iteratorAfterNext(AB_addC_ABC(),3), Result.NoException));
			printTest("AB_addC_ABC_testIteratorNextAfterRemoveNext", testIteratorNext(iteratorAfterRemove(iteratorAfterNext(AB_addC_ABC(),1)),ELEMENT_B,  Result.MatchingValue));
			printTest("AB_addC_ABC_testIteratorNextAfterRemoveNext2", testIteratorNext(iteratorAfterRemove(iteratorAfterNext(AB_addC_ABC(),2)),ELEMENT_C,  Result.MatchingValue));
			printTest("AB_addC_ABC_testIteratorNextAfterRemoveNext3", testIteratorNext(iteratorAfterRemove(iteratorAfterNext(AB_addC_ABC(),3)),null,  Result.NoSuchElement));
			printTest("AB_addC_ABC_testIteratorHasNextAfterRemoveNext",testIteratorHasNext(iteratorAfterRemove(iteratorAfterNext(AB_addC_ABC(),1)),Result.True));
			printTest("AB_addC_ABC_testIteratorHasNextAfterRemoveNext2",testIteratorHasNext(iteratorAfterRemove(iteratorAfterNext(AB_addC_ABC(),2)),Result.True));
			printTest("AB_addC_ABC_testIteratorHasNextAfterRemoveNext3",testIteratorHasNext(iteratorAfterRemove(iteratorAfterNext(AB_addC_ABC(),3)),Result.False));
			printTest("AB_addC_ABC_iteratorTestRealRemove",testIteratorRealRemove(AB_addC_ABC(), 1, "[2, 3]", Result.MatchingValue));
			printTest("AB_addC_ABC_iteratorTestRealRemove2",testIteratorRealRemove(AB_addC_ABC(), 2, "[1, 3]", Result.MatchingValue));
			printTest("AB_addC_ABC_iteratorTestRealRemove3",testIteratorRealRemove(AB_addC_ABC(), 3, "[1, 2]", Result.MatchingValue));
		} catch (Exception e) {
			System.out.printf("***UNABLE TO RUN/COMPLETE %s***\n", "test_AB_addC_ABC");
			e.printStackTrace();
		}
	}
	
	//////////////////////////////////////
	// SCENARIO: [A,B] -> add(0,C) -> [C,A,B]
	//  XXX SCENARIO 29
	//////////////////////////////////////
	private ListADT<Integer> AB_add0C_CAB() {
		// cast to UnorderedListADT to make addToFront() available
		IndexedListADT<Integer> list = (IndexedListADT<Integer>) newList(); 
		list.add(0,ELEMENT_A);
		list.add(1, ELEMENT_B);
		list.add(0,ELEMENT_C);
		return list;
	}
	private void test_AB_add0C_CAB(){
		try {
			// ListADT
			printTest("AB_add0C_CAB_testRemoveFirst", testRemoveFirst(AB_add0C_CAB(), ELEMENT_C, Result.MatchingValue));
			printTest("AB_add0C_CAB_testRemoveLast", testRemoveLast(AB_add0C_CAB(), ELEMENT_B, Result.MatchingValue));
			printTest("AB_add0C_CAB_testRemoveA", testRemoveElement(AB_add0C_CAB(), ELEMENT_A, Result.MatchingValue));
			printTest("AB_add0C_CAB_testRemoveB", testRemoveElement(AB_add0C_CAB(), ELEMENT_B, Result.MatchingValue));
			printTest("AB_add0C_CAB_testRemoveC", testRemoveElement(AB_add0C_CAB(), ELEMENT_C, Result.MatchingValue));
			printTest("AB_add0C_CAB_testRemoveD", testRemoveElement(AB_add0C_CAB(), ELEMENT_D, Result.ElementNotFound));
			printTest("AB_add0C_CAB_testFirst", testFirst(AB_add0C_CAB(), ELEMENT_C, Result.MatchingValue));
			printTest("AB_add0C_CAB_testLast", testLast(AB_add0C_CAB(), ELEMENT_B, Result.MatchingValue));
			printTest("AB_add0C_CAB_testContainsA", testContains(AB_add0C_CAB(), ELEMENT_A, Result.True));
			printTest("AB_add0C_CAB_testContainsB", testContains(AB_add0C_CAB(), ELEMENT_B, Result.True));
			printTest("AB_add0C_CAB_testContainsC", testContains(AB_add0C_CAB(), ELEMENT_C, Result.True));
			printTest("AB_add0C_CAB_testContainsD", testContains(AB_add0C_CAB(), ELEMENT_D, Result.False));
			printTest("AB_add0C_CAB_testIsEmpty", testIsEmpty(AB_add0C_CAB(), Result.False));
			printTest("AB_add0C_CAB_testSize", testSize(AB_add0C_CAB(), 3));
			printTest("AB_add0C_CAB_testToString", testToString(AB_add0C_CAB(), Result.ValidString));
			// UnorderedListADT
			printTest("AB_add0C_CAB_testAddToFrontB", testAddToFront(AB_add0C_CAB(), ELEMENT_D, Result.NoException));
			printTest("AB_add0C_CAB_testAddToRearB", testAddToRear(AB_add0C_CAB(), ELEMENT_D, Result.NoException));
			printTest(	"AB_add0C_CAB_testAddAfterAD", testAddAfter(AB_add0C_CAB(), ELEMENT_A, ELEMENT_D, Result.NoException));
			printTest(	"AB_add0C_CAB_testAddAfterBD", testAddAfter(AB_add0C_CAB(), ELEMENT_B, ELEMENT_D, Result.NoException));
			printTest(	"AB_add0C_CAB_testAddAfterCD", testAddAfter(AB_add0C_CAB(), ELEMENT_C, ELEMENT_D, Result.NoException));
			printTest(	"AB_add0C_CAB_testAddAfterDD", testAddAfter(AB_add0C_CAB(), ELEMENT_D, ELEMENT_D, Result.ElementNotFound));
			// IndexedListADT
			printTest("AB_add0C_CAB_testAddAtIndexNeg1D", testAddAtIndex(AB_add0C_CAB(), -1, ELEMENT_D, Result.IndexOutOfBounds));
			printTest("AB_add0C_CAB_testAddAtIndex0D", testAddAtIndex(AB_add0C_CAB(), 0, ELEMENT_D, Result.NoException));
			printTest("AB_add0C_CAB_testAddAtIndex1D", testAddAtIndex(AB_add0C_CAB(), 1, ELEMENT_D, Result.NoException));
			printTest("AB_add0C_CAB_testAddAtIndex2D", testAddAtIndex(AB_add0C_CAB(), 2, ELEMENT_D, Result.NoException));
			printTest("AB_add0C_CAB_testAddAtIndex3D", testAddAtIndex(AB_add0C_CAB(), 3, ELEMENT_D, Result.NoException));
			printTest("AB_add0C_CAB_testAddAtIndex4D", testAddAtIndex(AB_add0C_CAB(), 4, ELEMENT_D, Result.IndexOutOfBounds));
			printTest("AB_add0C_CAB_testSetNeg1D", testSet(AB_add0C_CAB(), -1, ELEMENT_D, Result.IndexOutOfBounds));
			printTest("AB_add0C_CAB_testSet0D", testSet(AB_add0C_CAB(), 0, ELEMENT_D, Result.NoException));
			printTest("AB_add0C_CAB_testSet1D", testSet(AB_add0C_CAB(), 1, ELEMENT_D, Result.NoException));
			printTest("AB_add0C_CAB_testSet2D", testSet(AB_add0C_CAB(), 2, ELEMENT_D, Result.NoException));
			printTest("AB_add0C_CAB_testSet3D", testSet(AB_add0C_CAB(), 3, ELEMENT_D, Result.IndexOutOfBounds));
			printTest("AB_add0C_CAB_testAddD", testAdd(AB_add0C_CAB(), ELEMENT_D, Result.NoException));
			printTest("AB_add0C_CAB_testGetNeg1", testGet(AB_add0C_CAB(), -1, null, Result.IndexOutOfBounds));
			printTest("AB_add0C_CAB_testGet0", testGet(AB_add0C_CAB(), 0, ELEMENT_C, Result.MatchingValue));
			printTest("AB_add0C_CAB_testGet1", testGet(AB_add0C_CAB(), 1, ELEMENT_A, Result.MatchingValue));
			printTest("AB_add0C_CAB_testGet2", testGet(AB_add0C_CAB(), 2, ELEMENT_B, Result.MatchingValue));
			printTest("AB_add0C_CAB_testGet3", testGet(AB_add0C_CAB(), 3, null, Result.IndexOutOfBounds));
			printTest("AB_add0C_CAB_testIndexOfA", testIndexOf(AB_add0C_CAB(), ELEMENT_A, 1));
			printTest("AB_add0C_CAB_testIndexOfB", testIndexOf(AB_add0C_CAB(), ELEMENT_B, 2));
			printTest("AB_add0C_CAB_testIndexOfC", testIndexOf(AB_add0C_CAB(), ELEMENT_C, 0));
			printTest("AB_add0C_CAB_testIndexOfD", testIndexOf(AB_add0C_CAB(), ELEMENT_D, -1));
			printTest("AB_add0C_CAB_testRemoveNeg1", testRemoveIndex(AB_add0C_CAB(), -1, null, Result.IndexOutOfBounds));
			printTest("AB_add0C_CAB_testRemove0", testRemoveIndex(AB_add0C_CAB(), 0, ELEMENT_C, Result.MatchingValue));
			printTest("AB_add0C_CAB_testRemove0", testRemoveIndex(AB_add0C_CAB(), 1, ELEMENT_A, Result.MatchingValue));
			printTest("AB_add0C_CAB_testRemove0", testRemoveIndex(AB_add0C_CAB(), 2, ELEMENT_B, Result.MatchingValue));
			printTest("AB_add0C_CAB_testRemove3", testRemoveIndex(AB_add0C_CAB(), 3, null, Result.IndexOutOfBounds));
			// Iterator
			printTest("AB_add0C_CAB_testIterator", testIterator(AB_add0C_CAB(), Result.NoException));
			printTest("AB_add0C_CAB_testIteratorHasNext", testIteratorHasNext(AB_add0C_CAB().iterator(), Result.True));
			printTest("AB_add0C_CAB_testIteratorNext", testIteratorNext(AB_add0C_CAB().iterator(), ELEMENT_C, Result.MatchingValue));
			printTest("AB_add0C_CAB_testIteratorRemove", testIteratorRemove(AB_add0C_CAB().iterator(), Result.IllegalState));
			printTest("AB_add0C_CAB_testIteratorHasNextAfterNext", testIteratorHasNext(iteratorAfterNext(AB_add0C_CAB(),1), Result.True));
			printTest("AB_add0C_CAB_testIteratorNextAfterNext", testIteratorNext(iteratorAfterNext(AB_add0C_CAB(),1), ELEMENT_A, Result.MatchingValue));
			printTest("AB_add0C_CAB_testIteratorRemoveAfterNext", testIteratorRemove(iteratorAfterNext(AB_add0C_CAB(),1), Result.NoException));
			printTest("AB_add0C_CAB_testIteratorHasNextAfterNext2", testIteratorHasNext(iteratorAfterNext(AB_add0C_CAB(),2), Result.True));
			printTest("AB_add0C_CAB_testIteratorHasNextAfterNext3", testIteratorHasNext(iteratorAfterNext(AB_add0C_CAB(),3), Result.False));
			printTest("AB_add0C_CAB_testIteratorNextAfterNext2", testIteratorNext(iteratorAfterNext(AB_add0C_CAB(),2), ELEMENT_B, Result.MatchingValue));
			printTest("AB_add0C_CAB_testIteratorNextAfterNext3", testIteratorNext(iteratorAfterNext(AB_add0C_CAB(),3), null, Result.NoSuchElement));
			printTest("AB_add0C_CAB_testIteratorRemoveAfterNext2", testIteratorRemove(iteratorAfterNext(AB_add0C_CAB(),2), Result.NoException));
			printTest("AB_add0C_CAB_testIteratorRemoveAfterNext3", testIteratorRemove(iteratorAfterNext(AB_add0C_CAB(),3), Result.NoException));
			printTest("AB_add0C_CAB_testIteratorNextAfterRemoveNext", testIteratorNext(iteratorAfterRemove(iteratorAfterNext(AB_add0C_CAB(),1)),ELEMENT_A,  Result.MatchingValue));
			printTest("AB_add0C_CAB_testIteratorNextAfterRemoveNext2", testIteratorNext(iteratorAfterRemove(iteratorAfterNext(AB_add0C_CAB(),2)),ELEMENT_B,  Result.MatchingValue));
			printTest("AB_add0C_CAB_testIteratorNextAfterRemoveNext3", testIteratorNext(iteratorAfterRemove(iteratorAfterNext(AB_add0C_CAB(),3)),null,  Result.NoSuchElement));
			printTest("AB_add0C_CAB_testIteratorHasNextAfterRemoveNext",testIteratorHasNext(iteratorAfterRemove(iteratorAfterNext(AB_add0C_CAB(),1)),Result.True));
			printTest("AB_add0C_CAB_testIteratorHasNextAfterRemoveNext2",testIteratorHasNext(iteratorAfterRemove(iteratorAfterNext(AB_add0C_CAB(),2)),Result.True));
			printTest("AB_add0C_CAB_testIteratorHasNextAfterRemoveNext3",testIteratorHasNext(iteratorAfterRemove(iteratorAfterNext(AB_add0C_CAB(),3)),Result.False));
			printTest("AB_add0C_CAB_iteratorTestRealRemove",testIteratorRealRemove(AB_add0C_CAB(), 1, "[1, 2]", Result.MatchingValue));
			printTest("AB_add0C_CAB_iteratorTestRealRemove2",testIteratorRealRemove(AB_add0C_CAB(), 2, "[3, 2]", Result.MatchingValue));
			printTest("AB_add0C_CAB_iteratorTestRealRemove3",testIteratorRealRemove(AB_add0C_CAB(), 3, "[3, 1]", Result.MatchingValue));
		} catch (Exception e) {
			System.out.printf("***UNABLE TO RUN/COMPLETE %s***\n", "test_AB_add0C_CAB");
			e.printStackTrace();
		}
	}
	
	//////////////////////////////////////
	// SCENARIO: [A,B] -> add(1,C) -> [A,C,B]
	//  XXX SCENARIO 30
	//////////////////////////////////////
	private ListADT<Integer> AB_add1C_ACB() {
		// cast to UnorderedListADT to make addToFront() available
		IndexedListADT<Integer> list = (IndexedListADT<Integer>) newList(); 
		list.add(0,ELEMENT_A);
		list.add(1, ELEMENT_B);
		list.add(1,ELEMENT_C);
		return list;
	}
	private void test_AB_add1C_ACB(){
		try {
			// ListADT
			printTest("AB_add1C_ACB_testRemoveFirst", testRemoveFirst(AB_add1C_ACB(), ELEMENT_A, Result.MatchingValue));
			printTest("AB_add1C_ACB_testRemoveLast", testRemoveLast(AB_add1C_ACB(), ELEMENT_B, Result.MatchingValue));
			printTest("AB_add1C_ACB_testRemoveA", testRemoveElement(AB_add1C_ACB(), ELEMENT_A, Result.MatchingValue));
			printTest("AB_add1C_ACB_testRemoveB", testRemoveElement(AB_add1C_ACB(), ELEMENT_B, Result.MatchingValue));
			printTest("AB_add1C_ACB_testRemoveC", testRemoveElement(AB_add1C_ACB(), ELEMENT_C, Result.MatchingValue));
			printTest("AB_add1C_ACB_testRemoveD", testRemoveElement(AB_add1C_ACB(), ELEMENT_D, Result.ElementNotFound));
			printTest("AB_add1C_ACB_testFirst", testFirst(AB_add1C_ACB(), ELEMENT_A, Result.MatchingValue));
			printTest("AB_add1C_ACB_testLast", testLast(AB_add1C_ACB(), ELEMENT_B, Result.MatchingValue));
			printTest("AB_add1C_ACB_testContainsA", testContains(AB_add1C_ACB(), ELEMENT_A, Result.True));
			printTest("AB_add1C_ACB_testContainsB", testContains(AB_add1C_ACB(), ELEMENT_B, Result.True));
			printTest("AB_add1C_ACB_testContainsC", testContains(AB_add1C_ACB(), ELEMENT_C, Result.True));
			printTest("AB_add1C_ACB_testContainsD", testContains(AB_add1C_ACB(), ELEMENT_D, Result.False));
			printTest("AB_add1C_ACB_testIsEmpty", testIsEmpty(AB_add1C_ACB(), Result.False));
			printTest("AB_add1C_ACB_testSize", testSize(AB_add1C_ACB(), 3));
			printTest("AB_add1C_ACB_testToString", testToString(AB_add1C_ACB(), Result.ValidString));
			// UnorderedListADT
			printTest("AB_add1C_ACB_testAddToFrontB", testAddToFront(AB_add1C_ACB(), ELEMENT_D, Result.NoException));
			printTest("AB_add1C_ACB_testAddToRearB", testAddToRear(AB_add1C_ACB(), ELEMENT_D, Result.NoException));
			printTest(	"AB_add1C_ACB_testAddAfterAD", testAddAfter(AB_add1C_ACB(), ELEMENT_A, ELEMENT_D, Result.NoException));
			printTest(	"AB_add1C_ACB_testAddAfterBD", testAddAfter(AB_add1C_ACB(), ELEMENT_B, ELEMENT_D, Result.NoException));
			printTest(	"AB_add1C_ACB_testAddAfterCD", testAddAfter(AB_add1C_ACB(), ELEMENT_C, ELEMENT_D, Result.NoException));
			printTest(	"AB_add1C_ACB_testAddAfterDD", testAddAfter(AB_add1C_ACB(), ELEMENT_D, ELEMENT_D, Result.ElementNotFound));
			// IndexedListADT
			printTest("AB_add1C_ACB_testAddAtIndexNeg1D", testAddAtIndex(AB_add1C_ACB(), -1, ELEMENT_D, Result.IndexOutOfBounds));
			printTest("AB_add1C_ACB_testAddAtIndex0D", testAddAtIndex(AB_add1C_ACB(), 0, ELEMENT_D, Result.NoException));
			printTest("AB_add1C_ACB_testAddAtIndex1D", testAddAtIndex(AB_add1C_ACB(), 1, ELEMENT_D, Result.NoException));
			printTest("AB_add1C_ACB_testAddAtIndex2D", testAddAtIndex(AB_add1C_ACB(), 2, ELEMENT_D, Result.NoException));
			printTest("AB_add1C_ACB_testAddAtIndex3D", testAddAtIndex(AB_add1C_ACB(), 3, ELEMENT_D, Result.NoException));
			printTest("AB_add1C_ACB_testAddAtIndex4D", testAddAtIndex(AB_add1C_ACB(), 4, ELEMENT_D, Result.IndexOutOfBounds));
			printTest("AB_add1C_ACB_testSetNeg1D", testSet(AB_add1C_ACB(), -1, ELEMENT_D, Result.IndexOutOfBounds));
			printTest("AB_add1C_ACB_testSet0D", testSet(AB_add1C_ACB(), 0, ELEMENT_D, Result.NoException));
			printTest("AB_add1C_ACB_testSet1D", testSet(AB_add1C_ACB(), 1, ELEMENT_D, Result.NoException));
			printTest("AB_add1C_ACB_testSet2D", testSet(AB_add1C_ACB(), 2, ELEMENT_D, Result.NoException));
			printTest("AB_add1C_ACB_testSet3D", testSet(AB_add1C_ACB(), 3, ELEMENT_D, Result.IndexOutOfBounds));
			printTest("AB_add1C_ACB_testAddD", testAdd(AB_add1C_ACB(), ELEMENT_D, Result.NoException));
			printTest("AB_add1C_ACB_testGetNeg1", testGet(AB_add1C_ACB(), -1, null, Result.IndexOutOfBounds));
			printTest("AB_add1C_ACB_testGet0", testGet(AB_add1C_ACB(), 0, ELEMENT_A, Result.MatchingValue));
			printTest("AB_add1C_ACB_testGet1", testGet(AB_add1C_ACB(), 1, ELEMENT_C, Result.MatchingValue));
			printTest("AB_add1C_ACB_testGet2", testGet(AB_add1C_ACB(), 2, ELEMENT_B, Result.MatchingValue));
			printTest("AB_add1C_ACB_testGet3", testGet(AB_add1C_ACB(), 3, null, Result.IndexOutOfBounds));
			printTest("AB_add1C_ACB_testIndexOfA", testIndexOf(AB_add1C_ACB(), ELEMENT_A, 0));
			printTest("AB_add1C_ACB_testIndexOfB", testIndexOf(AB_add1C_ACB(), ELEMENT_B, 2));
			printTest("AB_add1C_ACB_testIndexOfC", testIndexOf(AB_add1C_ACB(), ELEMENT_C, 1));
			printTest("AB_add1C_ACB_testIndexOfD", testIndexOf(AB_add1C_ACB(), ELEMENT_D, -1));
			printTest("AB_add1C_ACB_testRemoveNeg1", testRemoveIndex(AB_add1C_ACB(), -1, null, Result.IndexOutOfBounds));
			printTest("AB_add1C_ACB_testRemove0", testRemoveIndex(AB_add1C_ACB(), 0, ELEMENT_A, Result.MatchingValue));
			printTest("AB_add1C_ACB_testRemove0", testRemoveIndex(AB_add1C_ACB(), 1, ELEMENT_C, Result.MatchingValue));
			printTest("AB_add1C_ACB_testRemove0", testRemoveIndex(AB_add1C_ACB(), 2, ELEMENT_B, Result.MatchingValue));
			printTest("AB_add1C_ACB_testRemove3", testRemoveIndex(AB_add1C_ACB(), 3, null, Result.IndexOutOfBounds));
			// Iterator
			printTest("AB_add1C_ACB_testIterator", testIterator(AB_add1C_ACB(), Result.NoException));
			printTest("AB_add1C_ACB_testIteratorHasNext", testIteratorHasNext(AB_add1C_ACB().iterator(), Result.True));
			printTest("AB_add1C_ACB_testIteratorNext", testIteratorNext(AB_add1C_ACB().iterator(), ELEMENT_A, Result.MatchingValue));
			printTest("AB_add1C_ACB_testIteratorRemove", testIteratorRemove(AB_add1C_ACB().iterator(), Result.IllegalState));
			printTest("AB_add1C_ACB_testIteratorHasNextAfterNext", testIteratorHasNext(iteratorAfterNext(AB_add1C_ACB(),1), Result.True));
			printTest("AB_add1C_ACB_testIteratorNextAfterNext", testIteratorNext(iteratorAfterNext(AB_add1C_ACB(),1), ELEMENT_C, Result.MatchingValue));
			printTest("AB_add1C_ACB_testIteratorRemoveAfterNext", testIteratorRemove(iteratorAfterNext(AB_add1C_ACB(),1), Result.NoException));
			printTest("AB_add1C_ACB_testIteratorHasNextAfterNext2", testIteratorHasNext(iteratorAfterNext(AB_add1C_ACB(),2), Result.True));
			printTest("AB_add1C_ACB_testIteratorHasNextAfterNext3", testIteratorHasNext(iteratorAfterNext(AB_add1C_ACB(),3), Result.False));
			printTest("AB_add1C_ACB_testIteratorNextAfterNext2", testIteratorNext(iteratorAfterNext(AB_add1C_ACB(),2), ELEMENT_B, Result.MatchingValue));
			printTest("AB_add1C_ACB_testIteratorNextAfterNext3", testIteratorNext(iteratorAfterNext(AB_add1C_ACB(),3), null, Result.NoSuchElement));
			printTest("AB_add1C_ACB_testIteratorRemoveAfterNext2", testIteratorRemove(iteratorAfterNext(AB_add1C_ACB(),2), Result.NoException));
			printTest("AB_add1C_ACB_testIteratorRemoveAfterNext3", testIteratorRemove(iteratorAfterNext(AB_add1C_ACB(),3), Result.NoException));
			printTest("AB_add1C_ACB_testIteratorNextAfterRemoveNext", testIteratorNext(iteratorAfterRemove(iteratorAfterNext(AB_add1C_ACB(),1)),ELEMENT_C,  Result.MatchingValue));
			printTest("AB_add1C_ACB_testIteratorNextAfterRemoveNext2", testIteratorNext(iteratorAfterRemove(iteratorAfterNext(AB_add1C_ACB(),2)),ELEMENT_B,  Result.MatchingValue));
			printTest("AB_add1C_ACB_testIteratorNextAfterRemoveNext3", testIteratorNext(iteratorAfterRemove(iteratorAfterNext(AB_add1C_ACB(),3)),null,  Result.NoSuchElement));
			printTest("AB_add1C_ACB_testIteratorHasNextAfterRemoveNext",testIteratorHasNext(iteratorAfterRemove(iteratorAfterNext(AB_add1C_ACB(),1)),Result.True));
			printTest("AB_add1C_ACB_testIteratorHasNextAfterRemoveNext2",testIteratorHasNext(iteratorAfterRemove(iteratorAfterNext(AB_add1C_ACB(),2)),Result.True));
			printTest("AB_add1C_ACB_testIteratorHasNextAfterRemoveNext3",testIteratorHasNext(iteratorAfterRemove(iteratorAfterNext(AB_add1C_ACB(),3)),Result.False));
			printTest("AB_add1C_ACB_iteratorTestRealRemove",testIteratorRealRemove(AB_add1C_ACB(), 1, "[3, 2]", Result.MatchingValue));
			printTest("AB_add1C_ACB_iteratorTestRealRemove2",testIteratorRealRemove(AB_add1C_ACB(), 2, "[1, 2]", Result.MatchingValue));
			printTest("AB_add1C_ACB_iteratorTestRealRemove3",testIteratorRealRemove(AB_add1C_ACB(), 3, "[1, 3]", Result.MatchingValue));
		} catch (Exception e) {
			System.out.printf("***UNABLE TO RUN/COMPLETE %s***\n", "test_AB_add1C_ACB");
			e.printStackTrace();
		}
	}
	
	//////////////////////////////////////
	// SCENARIO: [A,B] -> add (2,C) -> [A,B,C]
	//  XXX SCENARIO 31
	//////////////////////////////////////
	private ListADT<Integer> AB_add2C_ABC() {
		// cast to UnorderedListADT to make addToFront() available
		IndexedListADT<Integer> list = (IndexedListADT<Integer>) newList(); 
		list.add(0,ELEMENT_A);
		list.add(1, ELEMENT_B);
		list.add(2,ELEMENT_C);
		return list;
	}
	private void test_AB_add2C_ABC(){
		try {
			// ListADT
			printTest("AB_add2C_ABC_testRemoveFirst", testRemoveFirst(AB_add2C_ABC(), ELEMENT_A, Result.MatchingValue));
			printTest("AB_add2C_ABC_testRemoveLast", testRemoveLast(AB_add2C_ABC(), ELEMENT_C, Result.MatchingValue));
			printTest("AB_add2C_ABC_testRemoveA", testRemoveElement(AB_add2C_ABC(), ELEMENT_A, Result.MatchingValue));
			printTest("AB_add2C_ABC_testRemoveB", testRemoveElement(AB_add2C_ABC(), ELEMENT_B, Result.MatchingValue));
			printTest("AB_add2C_ABC_testRemoveC", testRemoveElement(AB_add2C_ABC(), ELEMENT_C, Result.MatchingValue));
			printTest("AB_add2C_ABC_testRemoveD", testRemoveElement(AB_add2C_ABC(), ELEMENT_D, Result.ElementNotFound));
			printTest("AB_add2C_ABC_testFirst", testFirst(AB_add2C_ABC(), ELEMENT_A, Result.MatchingValue));
			printTest("AB_add2C_ABC_testLast", testLast(AB_add2C_ABC(), ELEMENT_C, Result.MatchingValue));
			printTest("AB_add2C_ABC_testContainsA", testContains(AB_add2C_ABC(), ELEMENT_A, Result.True));
			printTest("AB_add2C_ABC_testContainsB", testContains(AB_add2C_ABC(), ELEMENT_B, Result.True));
			printTest("AB_add2C_ABC_testContainsC", testContains(AB_add2C_ABC(), ELEMENT_C, Result.True));
			printTest("AB_add2C_ABC_testContainsD", testContains(AB_add2C_ABC(), ELEMENT_D, Result.False));
			printTest("AB_add2C_ABC_testIsEmpty", testIsEmpty(AB_add2C_ABC(), Result.False));
			printTest("AB_add2C_ABC_testSize", testSize(AB_add2C_ABC(), 3));
			printTest("AB_add2C_ABC_testToString", testToString(AB_add2C_ABC(), Result.ValidString));
			// UnorderedListADT
			printTest("AB_add2C_ABC_testAddToFrontB", testAddToFront(AB_add2C_ABC(), ELEMENT_D, Result.NoException));
			printTest("AB_add2C_ABC_testAddToRearB", testAddToRear(AB_add2C_ABC(), ELEMENT_D, Result.NoException));
			printTest(	"AB_add2C_ABC_testAddAfterAD", testAddAfter(AB_add2C_ABC(), ELEMENT_A, ELEMENT_D, Result.NoException));
			printTest(	"AB_add2C_ABC_testAddAfterBD", testAddAfter(AB_add2C_ABC(), ELEMENT_B, ELEMENT_D, Result.NoException));
			printTest(	"AB_add2C_ABC_testAddAfterCD", testAddAfter(AB_add2C_ABC(), ELEMENT_C, ELEMENT_D, Result.NoException));
			printTest(	"AB_add2C_ABC_testAddAfterDD", testAddAfter(AB_add2C_ABC(), ELEMENT_D, ELEMENT_D, Result.ElementNotFound));
			// IndexedListADT
			printTest("AB_add2C_ABC_testAddAtIndexNeg1D", testAddAtIndex(AB_add2C_ABC(), -1, ELEMENT_D, Result.IndexOutOfBounds));
			printTest("AB_add2C_ABC_testAddAtIndex0D", testAddAtIndex(AB_add2C_ABC(), 0, ELEMENT_D, Result.NoException));
			printTest("AB_add2C_ABC_testAddAtIndex1D", testAddAtIndex(AB_add2C_ABC(), 1, ELEMENT_D, Result.NoException));
			printTest("AB_add2C_ABC_testAddAtIndex2D", testAddAtIndex(AB_add2C_ABC(), 2, ELEMENT_D, Result.NoException));
			printTest("AB_add2C_ABC_testAddAtIndex3D", testAddAtIndex(AB_add2C_ABC(), 3, ELEMENT_D, Result.NoException));
			printTest("AB_add2C_ABC_testAddAtIndex4D", testAddAtIndex(AB_add2C_ABC(), 4, ELEMENT_D, Result.IndexOutOfBounds));
			printTest("AB_add2C_ABC_testSetNeg1D", testSet(AB_add2C_ABC(), -1, ELEMENT_D, Result.IndexOutOfBounds));
			printTest("AB_add2C_ABC_testSet0D", testSet(AB_add2C_ABC(), 0, ELEMENT_D, Result.NoException));
			printTest("AB_add2C_ABC_testSet1D", testSet(AB_add2C_ABC(), 1, ELEMENT_D, Result.NoException));
			printTest("AB_add2C_ABC_testSet2D", testSet(AB_add2C_ABC(), 2, ELEMENT_D, Result.NoException));
			printTest("AB_add2C_ABC_testSet3D", testSet(AB_add2C_ABC(), 3, ELEMENT_D, Result.IndexOutOfBounds));
			printTest("AB_add2C_ABC_testAddD", testAdd(AB_add2C_ABC(), ELEMENT_D, Result.NoException));
			printTest("AB_add2C_ABC_testGetNeg1", testGet(AB_add2C_ABC(), -1, null, Result.IndexOutOfBounds));
			printTest("AB_add2C_ABC_testGet0", testGet(AB_add2C_ABC(), 0, ELEMENT_A, Result.MatchingValue));
			printTest("AB_add2C_ABC_testGet1", testGet(AB_add2C_ABC(), 1, ELEMENT_B, Result.MatchingValue));
			printTest("AB_add2C_ABC_testGet2", testGet(AB_add2C_ABC(), 2, ELEMENT_C, Result.MatchingValue));
			printTest("AB_add2C_ABC_testGet3", testGet(AB_add2C_ABC(), 3, null, Result.IndexOutOfBounds));
			printTest("AB_add2C_ABC_testIndexOfA", testIndexOf(AB_add2C_ABC(), ELEMENT_A, 0));
			printTest("AB_add2C_ABC_testIndexOfB", testIndexOf(AB_add2C_ABC(), ELEMENT_B, 1));
			printTest("AB_add2C_ABC_testIndexOfC", testIndexOf(AB_add2C_ABC(), ELEMENT_C, 2));
			printTest("AB_add2C_ABC_testIndexOfD", testIndexOf(AB_add2C_ABC(), ELEMENT_D, -1));
			printTest("AB_add2C_ABC_testRemoveNeg1", testRemoveIndex(AB_add2C_ABC(), -1, null, Result.IndexOutOfBounds));
			printTest("AB_add2C_ABC_testRemove0", testRemoveIndex(AB_add2C_ABC(), 0, ELEMENT_A, Result.MatchingValue));
			printTest("AB_add2C_ABC_testRemove0", testRemoveIndex(AB_add2C_ABC(), 1, ELEMENT_B, Result.MatchingValue));
			printTest("AB_add2C_ABC_testRemove0", testRemoveIndex(AB_add2C_ABC(), 2, ELEMENT_C, Result.MatchingValue));
			printTest("AB_add2C_ABC_testRemove3", testRemoveIndex(AB_add2C_ABC(), 3, null, Result.IndexOutOfBounds));
			// Iterator
			printTest("AB_add2C_ABC_testIterator", testIterator(AB_add2C_ABC(), Result.NoException));
			printTest("AB_add2C_ABC_testIteratorHasNext", testIteratorHasNext(AB_add2C_ABC().iterator(), Result.True));
			printTest("AB_add2C_ABC_testIteratorNext", testIteratorNext(AB_add2C_ABC().iterator(), ELEMENT_A, Result.MatchingValue));
			printTest("AB_add2C_ABC_testIteratorRemove", testIteratorRemove(AB_add2C_ABC().iterator(), Result.IllegalState));
			printTest("AB_add2C_ABC_testIteratorHasNextAfterNext", testIteratorHasNext(iteratorAfterNext(AB_add2C_ABC(),1), Result.True));
			printTest("AB_add2C_ABC_testIteratorNextAfterNext", testIteratorNext(iteratorAfterNext(AB_add2C_ABC(),1), ELEMENT_B, Result.MatchingValue));
			printTest("AB_add2C_ABC_testIteratorRemoveAfterNext", testIteratorRemove(iteratorAfterNext(AB_add2C_ABC(),1), Result.NoException));
			printTest("AB_add2C_ABC_testIteratorHasNextAfterNext2", testIteratorHasNext(iteratorAfterNext(AB_add2C_ABC(),2), Result.True));
			printTest("AB_add2C_ABC_testIteratorHasNextAfterNext3", testIteratorHasNext(iteratorAfterNext(AB_add2C_ABC(),3), Result.False));
			printTest("AB_add2C_ABC_testIteratorNextAfterNext2", testIteratorNext(iteratorAfterNext(AB_add2C_ABC(),2), ELEMENT_C, Result.MatchingValue));
			printTest("AB_add2C_ABC_testIteratorNextAfterNext3", testIteratorNext(iteratorAfterNext(AB_add2C_ABC(),3), null, Result.NoSuchElement));
			printTest("AB_add2C_ABC_testIteratorRemoveAfterNext2", testIteratorRemove(iteratorAfterNext(AB_add2C_ABC(),2), Result.NoException));
			printTest("AB_add2C_ABC_testIteratorRemoveAfterNext3", testIteratorRemove(iteratorAfterNext(AB_add2C_ABC(),3), Result.NoException));
			printTest("AB_add2C_ABC_testIteratorNextAfterRemoveNext", testIteratorNext(iteratorAfterRemove(iteratorAfterNext(AB_add2C_ABC(),1)),ELEMENT_B,  Result.MatchingValue));
			printTest("AB_add2C_ABC_testIteratorNextAfterRemoveNext2", testIteratorNext(iteratorAfterRemove(iteratorAfterNext(AB_add2C_ABC(),2)),ELEMENT_C,  Result.MatchingValue));
			printTest("AB_add2C_ABC_testIteratorNextAfterRemoveNext3", testIteratorNext(iteratorAfterRemove(iteratorAfterNext(AB_add2C_ABC(),3)),null,  Result.NoSuchElement));
			printTest("AB_add2C_ABC_testIteratorHasNextAfterRemoveNext",testIteratorHasNext(iteratorAfterRemove(iteratorAfterNext(AB_add2C_ABC(),1)),Result.True));
			printTest("AB_add2C_ABC_testIteratorHasNextAfterRemoveNext2",testIteratorHasNext(iteratorAfterRemove(iteratorAfterNext(AB_add2C_ABC(),2)),Result.True));
			printTest("AB_add2C_ABC_testIteratorHasNextAfterRemoveNext3",testIteratorHasNext(iteratorAfterRemove(iteratorAfterNext(AB_add2C_ABC(),3)),Result.False));
			printTest("AB_add2C_ABC_iteratorTestRealRemove",testIteratorRealRemove(AB_add2C_ABC(), 1, "[2, 3]", Result.MatchingValue));
			printTest("AB_add2C_ABC_iteratorTestRealRemove2",testIteratorRealRemove(AB_add2C_ABC(), 2, "[1, 3]", Result.MatchingValue));
			printTest("AB_add2C_ABC_iteratorTestRealRemove3",testIteratorRealRemove(AB_add2C_ABC(), 3, "[1, 2]", Result.MatchingValue));
		} catch (Exception e) {
			System.out.printf("***UNABLE TO RUN/COMPLETE %s***\n", "test_AB_add2C_ABC");
			e.printStackTrace();
		}
	}
	
	//////////////////////////////////////
	// SCENARIO: [A,B,C] -> removeFirst() -> [B,C]
	//  XXX SCENARIO 32
	//////////////////////////////////////
	private ListADT<Integer> ABC_removeFirst_BC() {
		// cast to UnorderedListADT to make addToFront() available
		UnorderedListADT<Integer> list = (UnorderedListADT<Integer>) newList(); 
		list.addToFront(ELEMENT_C);
		list.addToFront(ELEMENT_B);
		list.addToFront(ELEMENT_A);
		list.removeFirst();
		return list;
	}
	private void test_ABC_removeFirst_BC(){
		try {
			// ListADT
			printTest("ABC_removeFirst_BC_testRemoveFirst", testRemoveFirst(ABC_removeFirst_BC(), ELEMENT_B, Result.MatchingValue));
			printTest("ABC_removeFirst_BC_testRemoveLast", testRemoveLast(ABC_removeFirst_BC(), ELEMENT_C, Result.MatchingValue));
			printTest("ABC_removeFirst_BC_testRemoveA", testRemoveElement(ABC_removeFirst_BC(), ELEMENT_A, Result.ElementNotFound));
			printTest("ABC_removeFirst_BC_testRemoveB", testRemoveElement(ABC_removeFirst_BC(), ELEMENT_B, Result.MatchingValue));
			printTest("ABC_removeFirst_BC_testRemoveC", testRemoveElement(ABC_removeFirst_BC(), ELEMENT_C, Result.MatchingValue));
			printTest("ABC_removeFirst_BC_testFirst", testFirst(ABC_removeFirst_BC(), ELEMENT_B, Result.MatchingValue));
			printTest("ABC_removeFirst_BC_testLast", testLast(ABC_removeFirst_BC(), ELEMENT_C, Result.MatchingValue));
			printTest("ABC_removeFirst_BC_testContainsA", testContains(ABC_removeFirst_BC(), ELEMENT_A, Result.False));
			printTest("ABC_removeFirst_BC_testContainsB", testContains(ABC_removeFirst_BC(), ELEMENT_B, Result.True));
			printTest("ABC_removeFirst_BC_testContainsC", testContains(ABC_removeFirst_BC(), ELEMENT_C, Result.True));
			printTest("ABC_removeFirst_BC_testIsEmpty", testIsEmpty(ABC_removeFirst_BC(), Result.False));
			printTest("ABC_removeFirst_BC_testSize", testSize(ABC_removeFirst_BC(), 2));
			printTest("ABC_removeFirst_BC_testToString", testToString(ABC_removeFirst_BC(), Result.ValidString));
			// UnorderedListADT
			printTest("ABC_removeFirst_BC_testAddToFrontD", testAddToFront(ABC_removeFirst_BC(), ELEMENT_D, Result.NoException));
			printTest("ABC_removeFirst_BC_testAddToRearD", testAddToRear(ABC_removeFirst_BC(), ELEMENT_D, Result.NoException));
			printTest(	"ABC_removeFirst_BC_testAddAfterAD", testAddAfter(ABC_removeFirst_BC(), ELEMENT_A, ELEMENT_D, Result.ElementNotFound));
			printTest(	"ABC_removeFirst_BC_testAddAfterBD", testAddAfter(ABC_removeFirst_BC(), ELEMENT_B, ELEMENT_D, Result.NoException));
			printTest(	"ABC_removeFirst_BC_testAddAfterCD", testAddAfter(ABC_removeFirst_BC(), ELEMENT_C, ELEMENT_D, Result.NoException));
			// IndexedListADT
			printTest("ABC_removeFirst_BC_testAddAtIndexNeg1", testAddAtIndex(ABC_removeFirst_BC(), -1, null, Result.IndexOutOfBounds));
			printTest("ABC_removeFirst_BC_testAddAtIndex0D", testAddAtIndex(ABC_removeFirst_BC(), 0, ELEMENT_D, Result.NoException));
			printTest("ABC_removeFirst_BC_testAddAtIndex1D", testAddAtIndex(ABC_removeFirst_BC(), 1, ELEMENT_D, Result.NoException));
			printTest("ABC_removeFirst_BC_testAddAtIndex2D", testAddAtIndex(ABC_removeFirst_BC(), 2, ELEMENT_D, Result.NoException));
			printTest("ABC_removeFirst_BC_testAddAtIndex3D", testAddAtIndex(ABC_removeFirst_BC(), 3, null, Result.IndexOutOfBounds));
			printTest("ABC_removeFirst_BC_testSetNeg1A", testSet(ABC_removeFirst_BC(), -1, null, Result.IndexOutOfBounds));
			printTest("ABC_removeFirst_BC_testSet0D", testSet(ABC_removeFirst_BC(), 0, ELEMENT_D, Result.NoException));
			printTest("ABC_removeFirst_BC_testSet1D", testSet(ABC_removeFirst_BC(), 1, ELEMENT_D, Result.NoException));
			printTest("ABC_removeFirst_BC_testSet2D", testSet(ABC_removeFirst_BC(), 2, null, Result.IndexOutOfBounds));
			printTest("ABC_removeFirst_BC_testAddD", testAdd(ABC_removeFirst_BC(), ELEMENT_D, Result.NoException));
			printTest("ABC_removeFirst_BC_testGetNeg1", testGet(ABC_removeFirst_BC(), -1, null, Result.IndexOutOfBounds));
			printTest("ABC_removeFirst_BC_testGet0", testGet(ABC_removeFirst_BC(), 0, ELEMENT_B , Result.MatchingValue));
			printTest("ABC_removeFirst_BC_testGet1", testGet(ABC_removeFirst_BC(), 1, ELEMENT_C , Result.MatchingValue));
			printTest("ABC_removeFirst_BC_testGet2", testGet(ABC_removeFirst_BC(), 2, null, Result.IndexOutOfBounds));
			printTest("ABC_removeFirst_BC_testIndexOfA", testIndexOf(ABC_removeFirst_BC(), ELEMENT_A, -1));
			printTest("ABC_removeFirst_BC_testIndexOfB", testIndexOf(ABC_removeFirst_BC(), ELEMENT_B, 0));
			printTest("ABC_removeFirst_BC_testIndexOfC", testIndexOf(ABC_removeFirst_BC(), ELEMENT_C, 1));
			printTest("ABC_removeFirst_BC_testRemoveNeg1", testRemoveIndex(ABC_removeFirst_BC(), -1, null, Result.IndexOutOfBounds));
			printTest("ABC_removeFirst_BC_testRemove0", testRemoveIndex(ABC_removeFirst_BC(), 0, ELEMENT_B, Result.MatchingValue));
			printTest("ABC_removeFirst_BC_testRemove1", testRemoveIndex(ABC_removeFirst_BC(), 1, ELEMENT_C, Result.MatchingValue));
			printTest("ABC_removeFirst_BC_testRemove2", testRemoveIndex(ABC_removeFirst_BC(), 2, null, Result.IndexOutOfBounds));
			// Iterator
			printTest("ABC_removeFirst_BC_testIterator", testIterator(ABC_removeFirst_BC(), Result.NoException));
			printTest("ABC_removeFirst_BC_testIteratorHasNext", testIteratorHasNext(ABC_removeFirst_BC().iterator(), Result.True));
			printTest("ABC_removeFirst_BC_testIteratorNext", testIteratorNext(ABC_removeFirst_BC().iterator(), ELEMENT_B, Result.MatchingValue));
			printTest("ABC_removeFirst_BC_testIteratorRemove", testIteratorRemove(ABC_removeFirst_BC().iterator(), Result.IllegalState));
			printTest("ABC_removeFirst_BC_testIteratorHasNextAfterNext", testIteratorHasNext(iteratorAfterNext(ABC_removeFirst_BC(),1), Result.True));
			printTest("ABC_removeFirst_BC_testIteratorNextAfterNext", testIteratorNext(iteratorAfterNext(ABC_removeFirst_BC(),1), ELEMENT_C, Result.MatchingValue));
			printTest("ABC_removeFirst_BC_testIteratorRemoveAfterNext", testIteratorRemove(iteratorAfterNext(ABC_removeFirst_BC(),1), Result.NoException));
			printTest("ABC_removeFirst_BC_testIteratorHasNextAfterNext2", testIteratorHasNext(iteratorAfterNext(ABC_removeFirst_BC(),2), Result.False));
			printTest("ABC_removeFirst_BC_testIteratorNextAfterNext2", testIteratorNext(iteratorAfterNext(ABC_removeFirst_BC(),2), null, Result.NoSuchElement));
			printTest("ABC_removeFirst_BC_testIteratorRemoveAfterNext2", testIteratorRemove(iteratorAfterNext(ABC_removeFirst_BC(),2), Result.NoException));
			printTest("ABC_removeFirst_BC_testIteratorNextAfterRemoveNext", testIteratorNext(iteratorAfterRemove(iteratorAfterNext(ABC_removeFirst_BC(),1)),ELEMENT_C,  Result.MatchingValue));
			printTest("ABC_removeFirst_BC_testIteratorNextAfterRemoveNext2", testIteratorNext(iteratorAfterRemove(iteratorAfterNext(ABC_removeFirst_BC(),2)),null,  Result.NoSuchElement));
			printTest("ABC_removeFirst_BC_testIteratorHasNextAfterRemoveNext",testIteratorHasNext(iteratorAfterRemove(iteratorAfterNext(ABC_removeFirst_BC(),1)),Result.True));
			printTest("ABC_removeFirst_BC_testIteratorHasNextAfterRemoveNext2",testIteratorHasNext(iteratorAfterRemove(iteratorAfterNext(ABC_removeFirst_BC(),2)),Result.False));
			printTest("ABC_removeFirst_BC_iteratorTestRealRemove",testIteratorRealRemove(ABC_removeFirst_BC(), 1, "[3]", Result.MatchingValue));
			printTest("ABC_removeFirst_BC_iteratorTestRealRemove2",testIteratorRealRemove(ABC_removeFirst_BC(), 2, "[2]", Result.MatchingValue));
		} catch (Exception e) {
			System.out.printf("***UNABLE TO RUN/COMPLETE %s***\n", "test_ABC_removeFirst_BC");
			e.printStackTrace();
		}
	}
	
	//////////////////////////////////////
	// SCENARIO: [A,B,C] -> removeLast() -> [A,B]
	//  XXX SCENARIO 33
	//////////////////////////////////////
	private ListADT<Integer> ABC_removeLast_AB() {
		// cast to UnorderedListADT to make addToFront() available
		UnorderedListADT<Integer> list = (UnorderedListADT<Integer>) newList(); 
		list.addToFront(ELEMENT_C);
		list.addToFront(ELEMENT_B);
		list.addToFront(ELEMENT_A);
		list.removeLast();
		return list;
	}
	private void test_ABC_removeLast_AB(){
		try {
			// ListADT
			printTest("ABC_removeLast_AB_testRemoveFirst", testRemoveFirst(ABC_removeLast_AB(), ELEMENT_A, Result.MatchingValue));
			printTest("ABC_removeLast_AB_testRemoveLast", testRemoveLast(ABC_removeLast_AB(), ELEMENT_B, Result.MatchingValue));
			printTest("ABC_removeLast_AB_testRemoveA", testRemoveElement(ABC_removeLast_AB(), ELEMENT_A, Result.MatchingValue));
			printTest("ABC_removeLast_AB_testRemoveB", testRemoveElement(ABC_removeLast_AB(), ELEMENT_B, Result.MatchingValue));
			printTest("ABC_removeLast_AB_testRemoveC", testRemoveElement(ABC_removeLast_AB(), ELEMENT_C, Result.ElementNotFound));
			printTest("ABC_removeLast_AB_testFirst", testFirst(ABC_removeLast_AB(), ELEMENT_A, Result.MatchingValue));
			printTest("ABC_removeLast_AB_testLast", testLast(ABC_removeLast_AB(), ELEMENT_B, Result.MatchingValue));
			printTest("ABC_removeLast_AB_testContainsA", testContains(ABC_removeLast_AB(), ELEMENT_A, Result.True));
			printTest("ABC_removeLast_AB_testContainsB", testContains(ABC_removeLast_AB(), ELEMENT_B, Result.True));
			printTest("ABC_removeLast_AB_testContainsC", testContains(ABC_removeLast_AB(), ELEMENT_C, Result.False));
			printTest("ABC_removeLast_AB_testIsEmpty", testIsEmpty(ABC_removeLast_AB(), Result.False));
			printTest("ABC_removeLast_AB_testSize", testSize(ABC_removeLast_AB(), 2));
			printTest("ABC_removeLast_AB_testToString", testToString(ABC_removeLast_AB(), Result.ValidString));
			// UnorderedListADT
			printTest("ABC_removeLast_AB_testAddToFrontC", testAddToFront(ABC_removeLast_AB(), ELEMENT_C, Result.NoException));
			printTest("ABC_removeLast_AB_testAddToRearC", testAddToRear(ABC_removeLast_AB(), ELEMENT_C, Result.NoException));
			printTest(	"ABC_removeLast_AB_testAddAfterAC", testAddAfter(ABC_removeLast_AB(), ELEMENT_A, ELEMENT_C, Result.NoException));
			printTest(	"ABC_removeLast_AB_testAddAfterBC", testAddAfter(ABC_removeLast_AB(), ELEMENT_B, ELEMENT_C, Result.NoException));
			printTest(	"ABC_removeLast_AB_testAddAfterDC", testAddAfter(ABC_removeLast_AB(), ELEMENT_D, ELEMENT_C, Result.ElementNotFound));
			// IndexedListADT
			printTest("ABC_removeLast_AB_testAddAtIndexNeg1", testAddAtIndex(ABC_removeLast_AB(), -1, null, Result.IndexOutOfBounds));
			printTest("ABC_removeLast_AB_testAddAtIndex0C", testAddAtIndex(ABC_removeLast_AB(), 0, ELEMENT_C, Result.NoException));
			printTest("ABC_removeLast_AB_testAddAtIndex1C", testAddAtIndex(ABC_removeLast_AB(), 1, ELEMENT_C, Result.NoException));
			printTest("ABC_removeLast_AB_testAddAtIndex2C", testAddAtIndex(ABC_removeLast_AB(), 2, ELEMENT_C, Result.NoException));
			printTest("ABC_removeLast_AB_testAddAtIndex3", testAddAtIndex(ABC_removeLast_AB(), 3, null, Result.IndexOutOfBounds));
			printTest("ABC_removeLast_AB_testSetNeg1A", testSet(ABC_removeLast_AB(), -1, null, Result.IndexOutOfBounds));
			printTest("ABC_removeLast_AB_testSet0C", testSet(ABC_removeLast_AB(), 0, ELEMENT_C, Result.NoException));
			printTest("ABC_removeLast_AB_testSet1C", testSet(ABC_removeLast_AB(), 1, ELEMENT_C, Result.NoException));
			printTest("ABC_removeLast_AB_testSet2C", testSet(ABC_removeLast_AB(), 2, null, Result.IndexOutOfBounds));
			printTest("ABC_removeLast_AB_testAddC", testAdd(ABC_removeLast_AB(), ELEMENT_C, Result.NoException));
			printTest("ABC_removeLast_AB_testGetNeg1", testGet(ABC_removeLast_AB(), -1, null, Result.IndexOutOfBounds));
			printTest("ABC_removeLast_AB_testGet0", testGet(ABC_removeLast_AB(), 0, ELEMENT_A , Result.MatchingValue));
			printTest("ABC_removeLast_AB_testGet1", testGet(ABC_removeLast_AB(), 1, ELEMENT_B , Result.MatchingValue));
			printTest("ABC_removeLast_AB_testGet2", testGet(ABC_removeLast_AB(), 2, null, Result.IndexOutOfBounds));
			printTest("ABC_removeLast_AB_testIndexOfA", testIndexOf(ABC_removeLast_AB(), ELEMENT_A, 0));
			printTest("ABC_removeLast_AB_testIndexOfB", testIndexOf(ABC_removeLast_AB(), ELEMENT_B, 1));
			printTest("ABC_removeLast_AB_testIndexOfC", testIndexOf(ABC_removeLast_AB(), ELEMENT_C, -1));
			printTest("ABC_removeLast_AB_testRemoveNeg1", testRemoveIndex(ABC_removeLast_AB(), -1, null, Result.IndexOutOfBounds));
			printTest("ABC_removeLast_AB_testRemove0", testRemoveIndex(ABC_removeLast_AB(), 0, ELEMENT_A, Result.MatchingValue));
			printTest("ABC_removeLast_AB_testRemove1", testRemoveIndex(ABC_removeLast_AB(), 1, ELEMENT_B, Result.MatchingValue));
			printTest("ABC_removeLast_AB_testRemove2", testRemoveIndex(ABC_removeLast_AB(), 2, null, Result.IndexOutOfBounds));
			// Iterator
			printTest("ABC_removeLast_AB_testIterator", testIterator(ABC_removeLast_AB(), Result.NoException));
			printTest("ABC_removeLast_AB_testIteratorHasNext", testIteratorHasNext(ABC_removeLast_AB().iterator(), Result.True));
			printTest("ABC_removeLast_AB_testIteratorNext", testIteratorNext(ABC_removeLast_AB().iterator(), ELEMENT_A, Result.MatchingValue));
			printTest("ABC_removeLast_AB_testIteratorRemove", testIteratorRemove(ABC_removeLast_AB().iterator(), Result.IllegalState));
			printTest("ABC_removeLast_AB_testIteratorHasNextAfterNext", testIteratorHasNext(iteratorAfterNext(ABC_removeLast_AB(),1), Result.True));
			printTest("ABC_removeLast_AB_testIteratorNextAfterNext", testIteratorNext(iteratorAfterNext(ABC_removeLast_AB(),1), ELEMENT_B, Result.MatchingValue));
			printTest("ABC_removeLast_AB_testIteratorRemoveAfterNext", testIteratorRemove(iteratorAfterNext(ABC_removeLast_AB(),1), Result.NoException));
			printTest("ABC_removeLast_AB_testIteratorHasNextAfterNext2", testIteratorHasNext(iteratorAfterNext(ABC_removeLast_AB(),2), Result.False));
			printTest("ABC_removeLast_AB_testIteratorNextAfterNext2", testIteratorNext(iteratorAfterNext(ABC_removeLast_AB(),2), null, Result.NoSuchElement));
			printTest("ABC_removeLast_AB_testIteratorRemoveAfterNext2", testIteratorRemove(iteratorAfterNext(ABC_removeLast_AB(),2), Result.NoException));
			printTest("ABC_removeLast_AB_testIteratorNextAfterRemoveNext", testIteratorNext(iteratorAfterRemove(iteratorAfterNext(ABC_removeLast_AB(),1)),ELEMENT_B,  Result.MatchingValue));
			printTest("ABC_removeLast_AB_testIteratorNextAfterRemoveNext2", testIteratorNext(iteratorAfterRemove(iteratorAfterNext(ABC_removeLast_AB(),2)),null,  Result.NoSuchElement));
			printTest("ABC_removeLast_AB_testIteratorHasNextAfterRemoveNext",testIteratorHasNext(iteratorAfterRemove(iteratorAfterNext(ABC_removeLast_AB(),1)),Result.True));
			printTest("ABC_removeLast_AB_testIteratorHasNextAfterRemoveNext2",testIteratorHasNext(iteratorAfterRemove(iteratorAfterNext(ABC_removeLast_AB(),2)),Result.False));
			printTest("ABC_removeLast_AB_iteratorTestRealRemove",testIteratorRealRemove(ABC_removeLast_AB(), 1, "[2]", Result.MatchingValue));
			printTest("ABC_removeLast_AB_iteratorTestRealRemove2",testIteratorRealRemove(ABC_removeLast_AB(), 2, "[1]", Result.MatchingValue));
			//ListIterator
			if(A_removeFirst_empty().getClass()==testDLL().getClass()){
			printTest("ABC_removeLast_AB_testListIteratorUnIndexed", testListIteratorUnIndexed(ABC_removeLast_AB(), Result.NoException));
			printTest("ABC_removeLast_AB_testListIteratorIndexedNeg1", testListIteratorIndexed(ABC_removeLast_AB(), -1, Result.IndexOutOfBounds));
			printTest("ABC_removeLast_AB_testListIteratorIndexed0", testListIteratorIndexed(ABC_removeLast_AB(), 0, Result.NoException));
			printTest("ABC_removeLast_AB_testListIteratorIndexed1", testListIteratorIndexed(ABC_removeLast_AB(), 1, Result.NoException));
			printTest("ABC_removeLast_AB_testListIteratorIndexed2", testListIteratorIndexed(ABC_removeLast_AB(), 2, Result.NoException));
			printTest("ABC_removeLast_AB_testListIteratorIndexed3", testListIteratorIndexed(ABC_removeLast_AB(), 3, Result.IndexOutOfBounds));
			
			printTest("ABC_removeLast_AB_testListIteratorHasNext", testListIteratorHasNext(ABC_removeLast_AB(),null, null, Result.True));
			printTest("ABC_removeLast_AB_testListIteratorNext", testListIteratorNext(ABC_removeLast_AB(),null, null, ELEMENT_A, Result.MatchingValue));
			printTest("ABC_removeLast_AB_testListIteratorNextIndex", testListIteratorNextIndex(ABC_removeLast_AB(),null, null, 0, Result.MatchingValue));
			printTest("ABC_removeLast_AB_testListIteratorHasPrevious", testListIteratorHasPrevious(ABC_removeLast_AB(),null, null, Result.False));
			printTest("ABC_removeLast_AB_testListIteratorPrevious", testListIteratorPrevious(ABC_removeLast_AB(),null, null, null, Result.NoSuchElement));
			printTest("ABC_removeLast_AB_testListIteratorPreviousIndex", testListIteratorPreviousIndex(ABC_removeLast_AB(),null, null, -1, Result.MatchingValue));
			printTest("ABC_removeLast_AB_testListIteratorRemove", testListIteratorRemove(ABC_removeLast_AB(),null, null, Result.IllegalState));
			printTest("ABC_removeLast_AB_testListIteratorRealRemove", testListIteratorRealRemoveAfterNext(ABC_removeLast_AB(), null, 0, "[1, 2]", Result.MatchingValue));
			printTest("ABC_removeLast_AB_testListIteratorSet", testListIteratorSet(ABC_removeLast_AB(), null,null, ELEMENT_D, Result.IllegalState));
			printTest("ABC_removeLast_AB_testListIteratorRealSet", testListIteratorRealSetAfterNext(ABC_removeLast_AB(), null, 0, ELEMENT_D, "[1, 2]", Result.MatchingValue));
			printTest("ABC_removeLast_AB_testListIteratorAdd", testListIteratorAdd(ABC_removeLast_AB(),null, null, ELEMENT_D, Result.NoException));
			printTest("ABC_removeLast_AB_testListIteratorRealAdd", testListIteratorRealAddAfterNext(ABC_removeLast_AB(), null, 0, ELEMENT_D, "[4, 1, 2]", Result.MatchingValue));
			
			printTest("ABC_removeLast_AB_testListIteratorHasNextAfterNext", testListIteratorHasNext(null,getListIteratorAfterCall(ABC_removeLast_AB(), "next", null, null), null, Result.True));
			printTest("ABC_removeLast_AB_testListIteratorNextAfterNext", testListIteratorNext(null,getListIteratorAfterCall(ABC_removeLast_AB(), "next", null, null), null, ELEMENT_B, Result.MatchingValue));
			printTest("ABC_removeLast_AB_testListIteratorNextIndexAfterNext", testListIteratorNextIndex(null,getListIteratorAfterCall(ABC_removeLast_AB(), "next", null, null), null, 1, Result.MatchingValue));
			printTest("ABC_removeLast_AB_testListIteratorHasPreviousAfterNext", testListIteratorHasPrevious(null,getListIteratorAfterCall(ABC_removeLast_AB(), "next", null, null), null, Result.True));
			printTest("ABC_removeLast_AB_testListIteratorPreviousAfterNext", testListIteratorPrevious(null,getListIteratorAfterCall(ABC_removeLast_AB(), "next", null, null), null, ELEMENT_A, Result.MatchingValue));
			printTest("ABC_removeLast_AB_testListIteratorPreviousIndexAfterNext", testListIteratorPreviousIndex(null,getListIteratorAfterCall(ABC_removeLast_AB(), "next", null, null), null, 0, Result.MatchingValue));
			printTest("ABC_removeLast_AB_testListIteratorRemoveAfterNext", testListIteratorRemove(null,getListIteratorAfterCall(ABC_removeLast_AB(), "next", null, null), null, Result.NoException));
			printTest("ABC_removeLast_AB_testListIteratorRealRemoveAfterNext", testListIteratorRealRemoveAfterNext(ABC_removeLast_AB(),null, 1, "[2]", Result.MatchingValue));
			printTest("ABC_removeLast_AB_testListIteratorSetAfterNext", testListIteratorSet(null, getListIteratorAfterCall(ABC_removeLast_AB(), "next", null, null),null, ELEMENT_D, Result.NoException));
			printTest("ABC_removeLast_AB_testListIteratorRealSetAfterNext", testListIteratorRealSetAfterNext(ABC_removeLast_AB(), null, 1, ELEMENT_D, "[4, 2]", Result.MatchingValue));
			printTest("ABC_removeLast_AB_testListIteratorAddAfterNext", testListIteratorAdd(null,getListIteratorAfterCall(ABC_removeLast_AB(), "next", null, null), null, ELEMENT_D, Result.NoException));
			printTest("ABC_removeLast_AB_testListIteratorRealAddAfterNext", testListIteratorRealAddAfterNext(ABC_removeLast_AB(), null, 1, ELEMENT_D, "[1, 4, 2]", Result.MatchingValue));
			
			printTest("ABC_removeLast_AB_testListIteratorHasNextAfter2Next", testListIteratorHasNext(null,getListIteratorAfterCallItrItr(getListIteratorAfterCall(ABC_removeLast_AB(), "next", null, null), "next", null, null), null, Result.False));
			printTest("ABC_removeLast_AB_testListIteratorNextAfter2Next", testListIteratorNext(null,getListIteratorAfterCallItrItr(getListIteratorAfterCall(ABC_removeLast_AB(), "next", null, null), "next", null, null), null, null, Result.NoSuchElement));
			printTest("ABC_removeLast_AB_testListIteratorNextIndexAfter2Next", testListIteratorNextIndex(null,getListIteratorAfterCallItrItr(getListIteratorAfterCall(ABC_removeLast_AB(), "next", null, null), "next", null, null), null, 2, Result.MatchingValue));
			printTest("ABC_removeLast_AB_testListIteratorHasPreviousAfter2Next", testListIteratorHasPrevious(null,getListIteratorAfterCallItrItr(getListIteratorAfterCall(ABC_removeLast_AB(), "next", null, null), "next", null, null), null, Result.True));
			printTest("ABC_removeLast_AB_testListIteratorPreviousAfter2Next", testListIteratorPrevious(null,getListIteratorAfterCallItrItr(getListIteratorAfterCall(ABC_removeLast_AB(), "next", null, null), "next", null, null), null, ELEMENT_B, Result.MatchingValue));
			printTest("ABC_removeLast_AB_testListIteratorPreviousIndexAfter2Next", testListIteratorPreviousIndex(null,getListIteratorAfterCallItrItr(getListIteratorAfterCall(ABC_removeLast_AB(), "next", null, null), "next", null, null), null, 1, Result.MatchingValue));
			printTest("ABC_removeLast_AB_testListIteratorRemoveAfter2Next", testListIteratorRemove(null,getListIteratorAfterCallItrItr(getListIteratorAfterCall(ABC_removeLast_AB(), "next", null, null), "next", null, null), null, Result.NoException));
			printTest("ABC_removeLast_AB_testListIteratorRealRemoveAfter2Next", testListIteratorRealRemoveAfterNext(ABC_removeLast_AB(),null, 2, "[1]", Result.MatchingValue));
			printTest("ABC_removeLast_AB_testListIteratorSetAfter2Next", testListIteratorSet(null, getListIteratorAfterCallItrItr(getListIteratorAfterCall(ABC_removeLast_AB(), "next", null, null), "next", null, null),null, ELEMENT_D, Result.NoException));
			printTest("ABC_removeLast_AB_testListIteratorRealSetAfter2Next", testListIteratorRealSetAfterNext(ABC_removeLast_AB(), null, 2, ELEMENT_D, "[1, 4]", Result.MatchingValue));
			printTest("ABC_removeLast_AB_testListIteratorAddAfter2Next", testListIteratorAdd(null,getListIteratorAfterCallItrItr(getListIteratorAfterCall(ABC_removeLast_AB(), "next", null, null), "next", null, null), null, ELEMENT_D, Result.NoException));
			printTest("ABC_removeLast_AB_testListIteratorRealAddAfter2Next", testListIteratorRealAddAfterNext(ABC_removeLast_AB(), null, 2, ELEMENT_D, "[1, 2, 4]", Result.MatchingValue));
			
			printTest("ABC_removeLast_AB_testListIterator0HasNext", testListIteratorHasNext(ABC_removeLast_AB(),null, 0, Result.True));
			printTest("ABC_removeLast_AB_testListIterator0Next", testListIteratorNext(ABC_removeLast_AB(),null, 0, ELEMENT_A, Result.MatchingValue));
			printTest("ABC_removeLast_AB_testListIterator0NextIndex", testListIteratorNextIndex(ABC_removeLast_AB(),null, 0, 0, Result.MatchingValue));
			printTest("ABC_removeLast_AB_testListIterator0HasPrevious", testListIteratorHasPrevious(ABC_removeLast_AB(),null, 0, Result.False));
			printTest("ABC_removeLast_AB_testListIterator0Previous", testListIteratorPrevious(ABC_removeLast_AB(),null, 0, null, Result.NoSuchElement));
			printTest("ABC_removeLast_AB_testListIterator0PreviousIndex", testListIteratorPreviousIndex(ABC_removeLast_AB(),null, 0, -1, Result.MatchingValue));
			printTest("ABC_removeLast_AB_testListIterator0Remove", testListIteratorRemove(ABC_removeLast_AB(),null, 0, Result.IllegalState));
			printTest("ABC_removeLast_AB_testListIterator0RealRemove", testListIteratorRealRemoveAfterNext(ABC_removeLast_AB(), 0, 0, "[1, 2]", Result.MatchingValue));
			printTest("ABC_removeLast_AB_testListIterator0Set", testListIteratorSet(ABC_removeLast_AB(), null,0, ELEMENT_D, Result.IllegalState));
			printTest("ABC_removeLast_AB_testListIterator0RealSet", testListIteratorRealSetAfterNext(ABC_removeLast_AB(), 0, 0, ELEMENT_D, "[1, 2]", Result.MatchingValue));
			printTest("ABC_removeLast_AB_testListIterator0Add", testListIteratorAdd(ABC_removeLast_AB(),null, 0, ELEMENT_D, Result.NoException));
			printTest("ABC_removeLast_AB_testListIterator0RealAdd", testListIteratorRealAddAfterNext(ABC_removeLast_AB(), 0, 0, ELEMENT_D, "[4, 1, 2]", Result.MatchingValue));
			
			printTest("ABC_removeLast_AB_testListIterator0HasNextAfterNext", testListIteratorHasNext(null,getListIteratorAfterCall(ABC_removeLast_AB(), "next", 0, null), 0, Result.True));
			printTest("ABC_removeLast_AB_testListIterator0NextAfterNext", testListIteratorNext(null,getListIteratorAfterCall(ABC_removeLast_AB(), "next", 0, null), 0, ELEMENT_B, Result.MatchingValue));
			printTest("ABC_removeLast_AB_testListIterator0NextIndexAfterNext", testListIteratorNextIndex(null,getListIteratorAfterCall(ABC_removeLast_AB(), "next", 0, null), 0, 1, Result.MatchingValue));
			printTest("ABC_removeLast_AB_testListIterator0HasPreviousAfterNext", testListIteratorHasPrevious(null,getListIteratorAfterCall(ABC_removeLast_AB(), "next", 0, null),0, Result.True));
			printTest("ABC_removeLast_AB_testListIterator0PreviousAfterNext", testListIteratorPrevious(null,getListIteratorAfterCall(ABC_removeLast_AB(), "next", 0, null), 0, ELEMENT_A, Result.MatchingValue));
			printTest("ABC_removeLast_AB_testListIterator0PreviousIndexAfterNext", testListIteratorPreviousIndex(null,getListIteratorAfterCall(ABC_removeLast_AB(), "next", 0, null), 0, 0, Result.MatchingValue));
			printTest("ABC_removeLast_AB_testListIterator0RemoveAfterNext", testListIteratorRemove(null,getListIteratorAfterCall(ABC_removeLast_AB(), "next", 0, null), 0, Result.NoException));
			printTest("ABC_removeLast_AB_testListIterator0RealRemoveAfterNext", testListIteratorRealRemoveAfterNext(ABC_removeLast_AB(),0, 1, "[2]", Result.MatchingValue));
			printTest("ABC_removeLast_AB_testListIterator0SetAfterNext", testListIteratorSet(null, getListIteratorAfterCall(ABC_removeLast_AB(), "next", 0, null),0, ELEMENT_D, Result.NoException));
			printTest("ABC_removeLast_AB_testListIterator0RealSetAfterNext", testListIteratorRealSetAfterNext(ABC_removeLast_AB(), 0, 1, ELEMENT_D, "[4, 2]", Result.MatchingValue));
			printTest("ABC_removeLast_AB_testListIterator0AddAfterNext", testListIteratorAdd(null,getListIteratorAfterCall(ABC_removeLast_AB(), "next", 0, null), 0, ELEMENT_D, Result.NoException));
			printTest("ABC_removeLast_AB_testListIterator0RealAddAfterNext", testListIteratorRealAddAfterNext(ABC_removeLast_AB(), 0, 1, ELEMENT_D, "[1, 4, 2]", Result.MatchingValue));
			
			printTest("ABC_removeLast_AB_testListIterator0HasNextAfter2Next", testListIteratorHasNext(null,getListIteratorAfterCallItrItr(getListIteratorAfterCall(ABC_removeLast_AB(), "next", 0, null), "next", null, null), 0, Result.False));
			printTest("ABC_removeLast_AB_testListIterator0NextAfter2Next", testListIteratorNext(null,getListIteratorAfterCallItrItr(getListIteratorAfterCall(ABC_removeLast_AB(), "next", 0, null), "next", null, null), 0, null, Result.NoSuchElement));
			printTest("ABC_removeLast_AB_testListIterator0NextIndexAfter2Next", testListIteratorNextIndex(null,getListIteratorAfterCallItrItr(getListIteratorAfterCall(ABC_removeLast_AB(), "next", 0, null), "next", null, null), 0, 2, Result.MatchingValue));
			printTest("ABC_removeLast_AB_testListIterator0HasPreviousAfter2Next", testListIteratorHasPrevious(null,getListIteratorAfterCallItrItr(getListIteratorAfterCall(ABC_removeLast_AB(), "next", 0, null), "next", null, null), 0, Result.True));
			printTest("ABC_removeLast_AB_testListIterator0PreviousAfter2Next", testListIteratorPrevious(null,getListIteratorAfterCallItrItr(getListIteratorAfterCall(ABC_removeLast_AB(), "next", 0, null), "next", null, null), 0, ELEMENT_B, Result.MatchingValue));
			printTest("ABC_removeLast_AB_testListIterator0PreviousIndexAfter2Next", testListIteratorPreviousIndex(null,getListIteratorAfterCallItrItr(getListIteratorAfterCall(ABC_removeLast_AB(), "next", 0, null), "next", null, null), 0, 1, Result.MatchingValue));
			printTest("ABC_removeLast_AB_testListIterator0RemoveAfter2Next", testListIteratorRemove(null,getListIteratorAfterCallItrItr(getListIteratorAfterCall(ABC_removeLast_AB(), "next", 0, null), "next", null, null), 0, Result.NoException));
			printTest("ABC_removeLast_AB_testListIterator0RealRemoveAfter2Next", testListIteratorRealRemoveAfterNext(ABC_removeLast_AB(),0, 2, "[1]", Result.MatchingValue));
			printTest("ABC_removeLast_AB_testListIterator0SetAfter2Next", testListIteratorSet(null, getListIteratorAfterCallItrItr(getListIteratorAfterCall(ABC_removeLast_AB(), "next", 0, null), "next", null, null),0, ELEMENT_D, Result.NoException));
			printTest("ABC_removeLast_AB_testListIterator0RealSetAfter2Next", testListIteratorRealSetAfterNext(ABC_removeLast_AB(), 0,2, ELEMENT_D, "[1, 4]", Result.MatchingValue));
			printTest("ABC_removeLast_AB_testListIterator0AddAfter2Next", testListIteratorAdd(null,getListIteratorAfterCallItrItr(getListIteratorAfterCall(ABC_removeLast_AB(), "next", 0, null), "next", null, null), 0, ELEMENT_D, Result.NoException));
			printTest("ABC_removeLast_AB_testListIterator0RealAddAfter2Next", testListIteratorRealAddAfterNext(ABC_removeLast_AB(), 0, 2, ELEMENT_D, "[1, 2, 4]", Result.MatchingValue));
	
			printTest("ABC_removeLast_AB_testListIterator1HasNext", testListIteratorHasNext( ABC_removeLast_AB(),null, 1, Result.True));
			printTest("ABC_removeLast_AB_testListIterator1Next", testListIteratorNext( ABC_removeLast_AB(),null, 1, ELEMENT_B, Result.MatchingValue));
			printTest("ABC_removeLast_AB_testListIterator1NextIndex", testListIteratorNextIndex( ABC_removeLast_AB(),null, 1, 1, Result.MatchingValue));
			printTest("ABC_removeLast_AB_testListIterator1HasPrevious", testListIteratorHasPrevious( ABC_removeLast_AB(),null, 1, Result.True));
			printTest("ABC_removeLast_AB_testListIterator1Previous", testListIteratorPrevious( ABC_removeLast_AB(),null, 1, ELEMENT_A, Result.MatchingValue));
			printTest("ABC_removeLast_AB_testListIterator1PreviousIndex", testListIteratorPreviousIndex( ABC_removeLast_AB(),null, 1, 0, Result.MatchingValue));
			printTest("ABC_removeLast_AB_testListIterator1Remove", testListIteratorRemove( ABC_removeLast_AB(),null, 1, Result.IllegalState));
			printTest("ABC_removeLast_AB_testListIterator1RealRemove", testListIteratorRealRemoveAfterNext(ABC_removeLast_AB(),0, 1, "[2]", Result.MatchingValue));
			printTest("ABC_removeLast_AB_testListIterator1Set", testListIteratorSet( ABC_removeLast_AB(),null,1, ELEMENT_D, Result.IllegalState));
			printTest("ABC_removeLast_AB_testListIterator1RealSet", testListIteratorRealSetAfterNext(ABC_removeLast_AB(), 0, 1, ELEMENT_D, "[4, 2]", Result.MatchingValue));
			printTest("ABC_removeLast_AB_testListIterator1Add", testListIteratorAdd( ABC_removeLast_AB(),null, 1, ELEMENT_D, Result.NoException));
			printTest("ABC_removeLast_AB_testListIterator1RealAdd", testListIteratorRealAddAfterNext(ABC_removeLast_AB(), 0, 1, ELEMENT_D, "[1, 4, 2]", Result.MatchingValue));
			
			printTest("ABC_removeLast_AB_testListIterator1HasNextAfterNext", testListIteratorHasNext(null,getListIteratorAfterCall(ABC_removeLast_AB(), "next", 1, null), 1, Result.False));
			printTest("ABC_removeLast_AB_testListIterator1NextAfterNext", testListIteratorNext(null,getListIteratorAfterCall(ABC_removeLast_AB(), "next", 1, null), 1, null, Result.NoSuchElement));
			printTest("ABC_removeLast_AB_testListIterator1NextIndexAfterNext", testListIteratorNextIndex(null,getListIteratorAfterCall(ABC_removeLast_AB(), "next", 1, null), 1, 2, Result.MatchingValue));
			printTest("ABC_removeLast_AB_testListIterator1HasPreviousAfterNext", testListIteratorHasPrevious(null,getListIteratorAfterCall(ABC_removeLast_AB(), "next", 1, null), 1, Result.True));
			printTest("ABC_removeLast_AB_testListIterator1PreviousAfterNext", testListIteratorPrevious(null,getListIteratorAfterCall(ABC_removeLast_AB(), "next", 1, null), 1, ELEMENT_B, Result.MatchingValue));
			printTest("ABC_removeLast_AB_testListIterator1PreviousIndexAfterNext", testListIteratorPreviousIndex(null,getListIteratorAfterCall(ABC_removeLast_AB(), "next", 1, null), 1, 1, Result.MatchingValue));
			printTest("ABC_removeLast_AB_testListIterator1RemoveAfterNext", testListIteratorRemove(null,getListIteratorAfterCall(ABC_removeLast_AB(), "next", 1, null), 1, Result.NoException));
			printTest("ABC_removeLast_AB_testListIterator1RealRemoveAfterNext", testListIteratorRealRemoveAfterNext(ABC_removeLast_AB(),1, 1, "[1]", Result.MatchingValue));
			printTest("ABC_removeLast_AB_testListIterator1SetAfterNext", testListIteratorSet(null, getListIteratorAfterCall(ABC_removeLast_AB(), "next", 1, null),1, ELEMENT_D, Result.NoException));
			printTest("ABC_removeLast_AB_testListIterator1RealSetAfterNext", testListIteratorRealSetAfterNext(ABC_removeLast_AB(), 1, 1, ELEMENT_D, "[1, 4]", Result.MatchingValue));
			printTest("ABC_removeLast_AB_testListIterator1AddAfterNext", testListIteratorAdd(null,getListIteratorAfterCall(ABC_removeLast_AB(), "next", 1, null), null, ELEMENT_D, Result.NoException));
			printTest("ABC_removeLast_AB_testListIterator1RealAddAfterNext", testListIteratorRealAddAfterNext(ABC_removeLast_AB(), 1, 1, ELEMENT_D, "[1, 2, 4]", Result.MatchingValue));
			
			printTest("ABC_removeLast_AB_testListIterator1HasNextAfterPrev", testListIteratorHasNext(null,getListIteratorAfterCall(ABC_removeLast_AB(), "previous", 1, null), null, Result.True));
			printTest("ABC_removeLast_AB_testListIterator1NextAfterPrev", testListIteratorNext(null,getListIteratorAfterCall(ABC_removeLast_AB(), "previous", 1, null), null, ELEMENT_A, Result.MatchingValue));
			printTest("ABC_removeLast_AB_testListIterator1NextIndexAfterPrev", testListIteratorNextIndex(null,getListIteratorAfterCall(ABC_removeLast_AB(), "previous", 1, null), null, 0, Result.MatchingValue));
			printTest("ABC_removeLast_AB_testListIterator1HasPreviousAfterPrev", testListIteratorHasPrevious(null,getListIteratorAfterCall(ABC_removeLast_AB(), "previous", 1, null), null, Result.False));
			printTest("ABC_removeLast_AB_testListIterator1PreviousAfterPrev", testListIteratorPrevious(null,getListIteratorAfterCall(ABC_removeLast_AB(), "previous", 1, null), null, null, Result.NoSuchElement));
			printTest("ABC_removeLast_AB_testListIterator1PreviousIndexAfterPrev", testListIteratorPreviousIndex(null,getListIteratorAfterCall(ABC_removeLast_AB(), "previous", 1, null), null, -1, Result.MatchingValue));
			printTest("ABC_removeLast_AB_testListIterator1RemoveAfterPrev", testListIteratorRemove(null,getListIteratorAfterCall(ABC_removeLast_AB(), "previous", 1, null), null, Result.NoException));
			printTest("ABC_removeLast_AB_testListIterator1RealRemoveAfterPrev", testListIteratorRealRemoveAfterPrev(ABC_removeLast_AB(), 1, 1, "[2]", Result.MatchingValue));
			printTest("ABC_removeLast_AB_testListIterator1SetAfterPrev", testListIteratorSet(null, getListIteratorAfterCall(ABC_removeLast_AB(), "previous", 1, null),null, ELEMENT_D, Result.NoException));
			printTest("ABC_removeLast_AB_testListIterator1RealSetAfterPrev", testListIteratorRealSetAfterPrev(ABC_removeLast_AB(), 1, 1, ELEMENT_D, "[4, 2]", Result.MatchingValue));
			printTest("ABC_removeLast_AB_testListIterator1AddAfterPrev", testListIteratorAdd(null,getListIteratorAfterCall(ABC_removeLast_AB(), "previous", 1, null), null, ELEMENT_D, Result.NoException));
			printTest("ABC_removeLast_AB_testListIterator1RealAddAfterPrev", testListIteratorRealAddAfterPrev(ABC_removeLast_AB(), 1, 1, ELEMENT_D, "[4, 1, 2]", Result.MatchingValue));
			
			printTest("ABC_removeLast_AB_testListIterator1HasNextAfterPrevNext", testListIteratorHasNext(null,getListIteratorAfterCallItrItr(getListIteratorAfterCall(ABC_removeLast_AB(), "previous", 1, null), "next", null, null), null, Result.True));
			printTest("ABC_removeLast_AB_testListIterator1NextAfterPrevNext", testListIteratorNext(null,getListIteratorAfterCallItrItr(getListIteratorAfterCall(ABC_removeLast_AB(), "previous", 1, null), "next", null, null), null, ELEMENT_B, Result.MatchingValue));
			printTest("ABC_removeLast_AB_testListIterator1NextIndexAfterPrevNext", testListIteratorNextIndex(null,getListIteratorAfterCallItrItr(getListIteratorAfterCall(ABC_removeLast_AB(), "previous", 1, null), "next", null, null), null, 1, Result.MatchingValue));
			printTest("ABC_removeLast_AB_testListIterator1HasPreviousAfterPrevNext", testListIteratorHasPrevious(null,getListIteratorAfterCallItrItr(getListIteratorAfterCall(ABC_removeLast_AB(), "previous", 1, null), "next", null, null), null, Result.True));
			printTest("ABC_removeLast_AB_testListIterator1PreviousAfterPrevNext", testListIteratorPrevious(null,getListIteratorAfterCallItrItr(getListIteratorAfterCall(ABC_removeLast_AB(), "previous", 1, null), "next", null, null), null, ELEMENT_A, Result.MatchingValue));
			printTest("ABC_removeLast_AB_testListIterator1PreviousIndexAfterPrevNext", testListIteratorPreviousIndex(null,getListIteratorAfterCallItrItr(getListIteratorAfterCall(ABC_removeLast_AB(), "previous", 1, null), "next", null, null), null, 0, Result.MatchingValue));
			printTest("ABC_removeLast_AB_testListIterator1RemoveAfterPrevNext", testListIteratorRemove(null,getListIteratorAfterCallItrItr(getListIteratorAfterCall(ABC_removeLast_AB(), "previous", 1, null), "next", null, null), null, Result.NoException));
			printTest("ABC_removeLast_AB_testListIterator1RealRemoveAfterPrevNext", testListIteratorRealRemoveAfterNext(ABC_removeLast_AB(),null, 1, "[2]", Result.MatchingValue));
			printTest("ABC_removeLast_AB_testListIterator1SetAfterPrevNext", testListIteratorSet(null, getListIteratorAfterCallItrItr(getListIteratorAfterCall(ABC_removeLast_AB(), "previous", 1, null), "next", null, null),null, ELEMENT_D, Result.NoException));
			printTest("ABC_removeLast_AB_testListIterator1RealSetAfterPrevNext", testListIteratorRealSetAfterNext(ABC_removeLast_AB(), null, 1, ELEMENT_D, "[4, 2]", Result.MatchingValue));
			printTest("ABC_removeLast_AB_testListIterator1AddAfterPrevNext", testListIteratorAdd(null,getListIteratorAfterCallItrItr(getListIteratorAfterCall(ABC_removeLast_AB(), "previous", 1, null), "next", null, null), null, ELEMENT_D, Result.NoException));
			printTest("ABC_removeLast_AB_testListIterator1RealAddAfterPrevNext", testListIteratorRealAddAfterNext(ABC_removeLast_AB(), null, 1, ELEMENT_D, "[1, 4, 2]", Result.MatchingValue));
		
			printTest("ABC_removeLast_AB_testListIterator1HasNextAfterPrev2Next", testListIteratorHasNext(null,getListIteratorAfterCallItrItr(getListIteratorAfterCallItrItr(getListIteratorAfterCall(ABC_removeLast_AB(), "previous", 1, null), "next", null, null), "next", null, null), 1, Result.False));
			printTest("ABC_removeLast_AB_testListIterator1NextAfterPrev2Next", testListIteratorNext(null,getListIteratorAfterCallItrItr(getListIteratorAfterCallItrItr(getListIteratorAfterCall(ABC_removeLast_AB(), "previous", 1, null), "next", null, null), "next", null, null), 1, null, Result.NoSuchElement));
			printTest("ABC_removeLast_AB_testListIterator1NextIndexAfterPrev2Next", testListIteratorNextIndex(null,getListIteratorAfterCallItrItr(getListIteratorAfterCallItrItr(getListIteratorAfterCall(ABC_removeLast_AB(), "previous", 1, null), "next", null, null), "next", null, null), 1, 2, Result.MatchingValue));
			printTest("ABC_removeLast_AB_testListIterator1HasPreviousAfterPrev2Next", testListIteratorHasPrevious(null,getListIteratorAfterCallItrItr(getListIteratorAfterCallItrItr(getListIteratorAfterCall(ABC_removeLast_AB(), "previous", 1, null), "next", null, null), "next", null, null), 1, Result.True));
			printTest("ABC_removeLast_AB_testListIterator1PreviousAfterPrev2Next", testListIteratorPrevious(null,getListIteratorAfterCallItrItr(getListIteratorAfterCallItrItr(getListIteratorAfterCall(ABC_removeLast_AB(), "previous", 1, null), "next", null, null), "next", null, null), 1, ELEMENT_B, Result.MatchingValue));
			printTest("ABC_removeLast_AB_testListIterator1PreviousIndexAfterPrev2Next", testListIteratorPreviousIndex(null,getListIteratorAfterCallItrItr(getListIteratorAfterCallItrItr(getListIteratorAfterCall(ABC_removeLast_AB(), "previous", 1, null), "next", null, null), "next", null, null), 1, 1, Result.MatchingValue));
			printTest("ABC_removeLast_AB_testListIterator1RemoveAfterPrev2Next", testListIteratorRemove(null,getListIteratorAfterCallItrItr(getListIteratorAfterCallItrItr(getListIteratorAfterCall(ABC_removeLast_AB(), "previous", 1, null), "next", null, null), "next", null, null), 1, Result.NoException));
			printTest("ABC_removeLast_AB_testListIterator1RealRemoveAfterPrev2Next", testListIteratorRealRemoveAfterNext(ABC_removeLast_AB(),1, 1, "[1]", Result.MatchingValue));
			printTest("ABC_removeLast_AB_testListIterator1SetAfterPrev2Next", testListIteratorSet(null, getListIteratorAfterCallItrItr(getListIteratorAfterCallItrItr(getListIteratorAfterCall(ABC_removeLast_AB(), "previous", 1, null), "next", null, null), "next", null, null),1, ELEMENT_D, Result.NoException));
			printTest("ABC_removeLast_AB_testListIterator1RealSetAfterPrev2Next", testListIteratorRealSetAfterNext(ABC_removeLast_AB(), 1, 1, ELEMENT_D, "[1, 4]", Result.MatchingValue));
			printTest("ABC_removeLast_AB_testListIterator1AddAfterPrev2Next", testListIteratorAdd(null,getListIteratorAfterCallItrItr(getListIteratorAfterCallItrItr(getListIteratorAfterCall(ABC_removeLast_AB(), "previous", 1, null), "next", null, null), "next", null, null), null, ELEMENT_D, Result.NoException));
			printTest("ABC_removeLast_AB_testListIterator1RealAddAfterPrev2Next", testListIteratorRealAddAfterNext(ABC_removeLast_AB(), 1, 1, ELEMENT_D, "[1, 2, 4]", Result.MatchingValue));
			}
		} catch (Exception e) {
			System.out.printf("***UNABLE TO RUN/COMPLETE %s***\n", "test_ABC_removeLast_AB");
			e.printStackTrace();
		}
	}
	
	//////////////////////////////////////
	// SCENARIO: [A,B,C] -> remove(A) -> [B,C]
	//  XXX SCENARIO 34
	//////////////////////////////////////
	private ListADT<Integer> ABC_removeA_BC() {
		// cast to UnorderedListADT to make addToFront() available
		UnorderedListADT<Integer> list = (UnorderedListADT<Integer>) newList(); 
		list.addToFront(ELEMENT_C);
		list.addToFront(ELEMENT_B);
		list.addToFront(ELEMENT_A);
		list.remove(ELEMENT_A);
		return list;
	}
	private void test_ABC_removeA_BC(){
		try {
			// ListADT
			printTest("ABC_removeA_BC_testRemoveFirst", testRemoveFirst(ABC_removeA_BC(), ELEMENT_B, Result.MatchingValue));
			printTest("ABC_removeA_BC_testRemoveLast", testRemoveLast(ABC_removeA_BC(), ELEMENT_C, Result.MatchingValue));
			printTest("ABC_removeA_BC_testRemoveA", testRemoveElement(ABC_removeA_BC(), ELEMENT_A, Result.ElementNotFound));
			printTest("ABC_removeA_BC_testRemoveB", testRemoveElement(ABC_removeA_BC(), ELEMENT_B, Result.MatchingValue));
			printTest("ABC_removeA_BC_testRemoveC", testRemoveElement(ABC_removeA_BC(), ELEMENT_C, Result.MatchingValue));
			printTest("ABC_removeA_BC_testFirst", testFirst(ABC_removeA_BC(), ELEMENT_B, Result.MatchingValue));
			printTest("ABC_removeA_BC_testLast", testLast(ABC_removeA_BC(), ELEMENT_C, Result.MatchingValue));
			printTest("ABC_removeA_BC_testContainsA", testContains(ABC_removeA_BC(), ELEMENT_A, Result.False));
			printTest("ABC_removeA_BC_testContainsB", testContains(ABC_removeA_BC(), ELEMENT_B, Result.True));
			printTest("ABC_removeA_BC_testContainsC", testContains(ABC_removeA_BC(), ELEMENT_C, Result.True));
			printTest("ABC_removeA_BC_testIsEmpty", testIsEmpty(ABC_removeA_BC(), Result.False));
			printTest("ABC_removeA_BC_testSize", testSize(ABC_removeA_BC(), 2));
			printTest("ABC_removeA_BC_testToString", testToString(ABC_removeA_BC(), Result.ValidString));
			// UnorderedListADT
			printTest("ABC_removeA_BC_testAddToFrontD", testAddToFront(ABC_removeA_BC(), ELEMENT_D, Result.NoException));
			printTest("ABC_removeA_BC_testAddToRearD", testAddToRear(ABC_removeA_BC(), ELEMENT_D, Result.NoException));
			printTest(	"ABC_removeA_BC_testAddAfterAD", testAddAfter(ABC_removeA_BC(), ELEMENT_A, ELEMENT_D, Result.ElementNotFound));
			printTest(	"ABC_removeA_BC_testAddAfterBD", testAddAfter(ABC_removeA_BC(), ELEMENT_B, ELEMENT_D, Result.NoException));
			printTest(	"ABC_removeA_BC_testAddAfterCD", testAddAfter(ABC_removeA_BC(), ELEMENT_C, ELEMENT_D, Result.NoException));
			// IndexedListADT
			printTest("ABC_removeA_BC_testAddAtIndexNeg1", testAddAtIndex(ABC_removeA_BC(), -1, null, Result.IndexOutOfBounds));
			printTest("ABC_removeA_BC_testAddAtIndex0D", testAddAtIndex(ABC_removeA_BC(), 0, ELEMENT_D, Result.NoException));
			printTest("ABC_removeA_BC_testAddAtIndex1D", testAddAtIndex(ABC_removeA_BC(), 1, ELEMENT_D, Result.NoException));
			printTest("ABC_removeA_BC_testAddAtIndex2D", testAddAtIndex(ABC_removeA_BC(), 2, ELEMENT_D, Result.NoException));
			printTest("ABC_removeA_BC_testAddAtIndex3D", testAddAtIndex(ABC_removeA_BC(), 3, null, Result.IndexOutOfBounds));
			printTest("ABC_removeA_BC_testSetNeg1A", testSet(ABC_removeA_BC(), -1, null, Result.IndexOutOfBounds));
			printTest("ABC_removeA_BC_testSet0D", testSet(ABC_removeA_BC(), 0, ELEMENT_D, Result.NoException));
			printTest("ABC_removeA_BC_testSet1D", testSet(ABC_removeA_BC(), 1, ELEMENT_D, Result.NoException));
			printTest("ABC_removeA_BC_testSet2D", testSet(ABC_removeA_BC(), 2, null, Result.IndexOutOfBounds));
			printTest("ABC_removeA_BC_testAddD", testAdd(ABC_removeA_BC(), ELEMENT_D, Result.NoException));
			printTest("ABC_removeA_BC_testGetNeg1", testGet(ABC_removeA_BC(), -1, null, Result.IndexOutOfBounds));
			printTest("ABC_removeA_BC_testGet0", testGet(ABC_removeA_BC(), 0, ELEMENT_B , Result.MatchingValue));
			printTest("ABC_removeA_BC_testGet1", testGet(ABC_removeA_BC(), 1, ELEMENT_C , Result.MatchingValue));
			printTest("ABC_removeA_BC_testGet2", testGet(ABC_removeA_BC(), 2, null, Result.IndexOutOfBounds));
			printTest("ABC_removeA_BC_testIndexOfA", testIndexOf(ABC_removeA_BC(), ELEMENT_A, -1));
			printTest("ABC_removeA_BC_testIndexOfB", testIndexOf(ABC_removeA_BC(), ELEMENT_B, 0));
			printTest("ABC_removeA_BC_testIndexOfC", testIndexOf(ABC_removeA_BC(), ELEMENT_C, 1));
			printTest("ABC_removeA_BC_testRemoveNeg1", testRemoveIndex(ABC_removeA_BC(), -1, null, Result.IndexOutOfBounds));
			printTest("ABC_removeA_BC_testRemove0", testRemoveIndex(ABC_removeA_BC(), 0, ELEMENT_B, Result.MatchingValue));
			printTest("ABC_removeA_BC_testRemove1", testRemoveIndex(ABC_removeA_BC(), 1, ELEMENT_C, Result.MatchingValue));
			printTest("ABC_removeA_BC_testRemove2", testRemoveIndex(ABC_removeA_BC(), 2, null, Result.IndexOutOfBounds));
			// Iterator
			printTest("ABC_removeA_BC_testIterator", testIterator(ABC_removeA_BC(), Result.NoException));
			printTest("ABC_removeA_BC_testIteratorHasNext", testIteratorHasNext(ABC_removeA_BC().iterator(), Result.True));
			printTest("ABC_removeA_BC_testIteratorNext", testIteratorNext(ABC_removeA_BC().iterator(), ELEMENT_B, Result.MatchingValue));
			printTest("ABC_removeA_BC_testIteratorRemove", testIteratorRemove(ABC_removeA_BC().iterator(), Result.IllegalState));
			printTest("ABC_removeA_BC_testIteratorHasNextAfterNext", testIteratorHasNext(iteratorAfterNext(ABC_removeA_BC(),1), Result.True));
			printTest("ABC_removeA_BC_testIteratorNextAfterNext", testIteratorNext(iteratorAfterNext(ABC_removeA_BC(),1), ELEMENT_C, Result.MatchingValue));
			printTest("ABC_removeA_BC_testIteratorRemoveAfterNext", testIteratorRemove(iteratorAfterNext(ABC_removeA_BC(),1), Result.NoException));
			printTest("ABC_removeA_BC_testIteratorHasNextAfterNext2", testIteratorHasNext(iteratorAfterNext(ABC_removeA_BC(),2), Result.False));
			printTest("ABC_removeA_BC_testIteratorNextAfterNext2", testIteratorNext(iteratorAfterNext(ABC_removeA_BC(),2), null, Result.NoSuchElement));
			printTest("ABC_removeA_BC_testIteratorRemoveAfterNext2", testIteratorRemove(iteratorAfterNext(ABC_removeA_BC(),2), Result.NoException));
			printTest("ABC_removeA_BC_testIteratorNextAfterRemoveNext", testIteratorNext(iteratorAfterRemove(iteratorAfterNext(ABC_removeA_BC(),1)),ELEMENT_C,  Result.MatchingValue));
			printTest("ABC_removeA_BC_testIteratorNextAfterRemoveNext2", testIteratorNext(iteratorAfterRemove(iteratorAfterNext(ABC_removeA_BC(),2)),null,  Result.NoSuchElement));
			printTest("ABC_removeA_BC_testIteratorHasNextAfterRemoveNext",testIteratorHasNext(iteratorAfterRemove(iteratorAfterNext(ABC_removeA_BC(),1)),Result.True));
			printTest("ABC_removeA_BC_testIteratorHasNextAfterRemoveNext2",testIteratorHasNext(iteratorAfterRemove(iteratorAfterNext(ABC_removeA_BC(),2)),Result.False));
			printTest("ABC_removeA_BC_iteratorTestRealRemove",testIteratorRealRemove(ABC_removeA_BC(), 1, "[3]", Result.MatchingValue));
			printTest("ABC_removeA_BC_iteratorTestRealRemove2",testIteratorRealRemove(ABC_removeA_BC(), 2, "[2]", Result.MatchingValue));
		} catch (Exception e) {
			System.out.printf("***UNABLE TO RUN/COMPLETE %s***\n", "test_ABC_removeA_BC");
			e.printStackTrace();
		}
	}
	
	//////////////////////////////////////
	// SCENARIO: [A,B,C] -> remove(B) -> [A,C]
	//  XXX SCENARIO 35
	//////////////////////////////////////
	private ListADT<Integer> ABC_removeB_AC() {
		// cast to UnorderedListADT to make addToFront() available
		UnorderedListADT<Integer> list = (UnorderedListADT<Integer>) newList(); 
		list.addToFront(ELEMENT_C);
		list.addToFront(ELEMENT_B);
		list.addToFront(ELEMENT_A);
		list.remove(ELEMENT_B);
		return list;
	}
	private void test_ABC_removeB_AC(){
		try {
			// ListADT
			printTest("ABC_removeB_AC_testRemoveFirst", testRemoveFirst(ABC_removeB_AC(), ELEMENT_A, Result.MatchingValue));
			printTest("ABC_removeB_AC_testRemoveLast", testRemoveLast(ABC_removeB_AC(), ELEMENT_C, Result.MatchingValue));
			printTest("ABC_removeB_AC_testRemoveA", testRemoveElement(ABC_removeB_AC(), ELEMENT_A, Result.MatchingValue));
			printTest("ABC_removeB_AC_testRemoveB", testRemoveElement(ABC_removeB_AC(), ELEMENT_B, Result.ElementNotFound));
			printTest("ABC_removeB_AC_testRemoveC", testRemoveElement(ABC_removeB_AC(), ELEMENT_C, Result.MatchingValue));
			printTest("ABC_removeB_AC_testFirst", testFirst(ABC_removeB_AC(), ELEMENT_A, Result.MatchingValue));
			printTest("ABC_removeB_AC_testLast", testLast(ABC_removeB_AC(), ELEMENT_C, Result.MatchingValue));
			printTest("ABC_removeB_AC_testContainsA", testContains(ABC_removeB_AC(), ELEMENT_A, Result.True));
			printTest("ABC_removeB_AC_testContainsB", testContains(ABC_removeB_AC(), ELEMENT_B, Result.False));
			printTest("ABC_removeB_AC_testContainsC", testContains(ABC_removeB_AC(), ELEMENT_C, Result.True));
			printTest("ABC_removeB_AC_testIsEmpty", testIsEmpty(ABC_removeB_AC(), Result.False));
			printTest("ABC_removeB_AC_testSize", testSize(ABC_removeB_AC(), 2));
			printTest("ABC_removeB_AC_testToString", testToString(ABC_removeB_AC(), Result.ValidString));
			// UnorderedListADT
			printTest("ABC_removeB_AC_testAddToFrontC", testAddToFront(ABC_removeB_AC(), ELEMENT_C, Result.NoException));
			printTest("ABC_removeB_AC_testAddToRearC", testAddToRear(ABC_removeB_AC(), ELEMENT_C, Result.NoException));
			printTest(	"ABC_removeB_AC_testAddAfterAD", testAddAfter(ABC_removeB_AC(), ELEMENT_A, ELEMENT_D, Result.NoException));
			printTest(	"ABC_removeB_AC_testAddAfterBD", testAddAfter(ABC_removeB_AC(), ELEMENT_B, ELEMENT_D, Result.ElementNotFound));
			printTest(	"ABC_removeB_AC_testAddAfterCD", testAddAfter(ABC_removeB_AC(), ELEMENT_C, ELEMENT_D, Result.NoException));
			// IndexedListADT
			printTest("ABC_removeB_AC_testAddAtIndexNeg1", testAddAtIndex(ABC_removeB_AC(), -1, null, Result.IndexOutOfBounds));
			printTest("ABC_removeB_AC_testAddAtIndex0", testAddAtIndex(ABC_removeB_AC(), 0, ELEMENT_D, Result.NoException));
			printTest("ABC_removeB_AC_testAddAtIndex1", testAddAtIndex(ABC_removeB_AC(), 1, ELEMENT_D, Result.NoException));
			printTest("ABC_removeB_AC_testAddAtIndex2", testAddAtIndex(ABC_removeB_AC(), 2, ELEMENT_D, Result.NoException));
			printTest("ABC_removeB_AC_testAddAtIndex3", testAddAtIndex(ABC_removeB_AC(), 3, null, Result.IndexOutOfBounds));
			printTest("ABC_removeB_AC_testSetNeg1D", testSet(ABC_removeB_AC(), -1, null, Result.IndexOutOfBounds));
			printTest("ABC_removeB_AC_testSet0D", testSet(ABC_removeB_AC(), 0, ELEMENT_D, Result.NoException));
			printTest("ABC_removeB_AC_testSet1D", testSet(ABC_removeB_AC(), 1, ELEMENT_D, Result.NoException));
			printTest("ABC_removeB_AC_testSetNeg2D", testSet(ABC_removeB_AC(), 2, null, Result.IndexOutOfBounds));
			printTest("ABC_removeB_AC_testAddD", testAdd(ABC_removeB_AC(), ELEMENT_D, Result.NoException));
			printTest("ABC_removeB_AC_testGetNeg1", testGet(ABC_removeB_AC(), -1, null, Result.IndexOutOfBounds));
			printTest("ABC_removeB_AC_testGet0", testGet(ABC_removeB_AC(), 0, ELEMENT_A , Result.MatchingValue));
			printTest("ABC_removeB_AC_testGet1", testGet(ABC_removeB_AC(), 1, ELEMENT_C , Result.MatchingValue));
			printTest("ABC_removeB_AC_testGet2", testGet(ABC_removeB_AC(), 2, null, Result.IndexOutOfBounds));
			printTest("ABC_removeB_AC_testIndexOfB", testIndexOf(ABC_removeB_AC(), ELEMENT_B, -1));
			printTest("ABC_removeB_AC_testIndexOfA", testIndexOf(ABC_removeB_AC(), ELEMENT_A, 0));
			printTest("ABC_removeB_AC_testIndexOfC", testIndexOf(ABC_removeB_AC(), ELEMENT_C, 1));
			printTest("ABC_removeB_AC_testRemoveNeg1", testRemoveIndex(ABC_removeB_AC(), -1, null, Result.IndexOutOfBounds));
			printTest("ABC_removeB_AC_testRemove0", testRemoveIndex(ABC_removeB_AC(), 0, ELEMENT_A, Result.MatchingValue));
			printTest("ABC_removeB_AC_testRemove1", testRemoveIndex(ABC_removeB_AC(), 1, ELEMENT_C, Result.MatchingValue));
			printTest("ABC_removeB_AC_testRemove2", testRemoveIndex(ABC_removeB_AC(), 2, null, Result.IndexOutOfBounds));
			// Iterator
			printTest("ABC_removeB_AC_testIterator", testIterator(ABC_removeB_AC(), Result.NoException));
			printTest("ABC_removeB_AC_testIteratorHasNext", testIteratorHasNext(ABC_removeB_AC().iterator(), Result.True));
			printTest("ABC_removeB_AC_testIteratorNext", testIteratorNext(ABC_removeB_AC().iterator(), ELEMENT_A, Result.MatchingValue));
			printTest("ABC_removeB_AC_testIteratorRemove", testIteratorRemove(ABC_removeB_AC().iterator(), Result.IllegalState));
			printTest("ABC_removeB_AC_testIteratorHasNextAfterNext", testIteratorHasNext(iteratorAfterNext(ABC_removeB_AC(),1), Result.True));
			printTest("ABC_removeB_AC_testIteratorNextAfterNext", testIteratorNext(iteratorAfterNext(ABC_removeB_AC(),1), ELEMENT_C, Result.MatchingValue));
			printTest("ABC_removeB_AC_testIteratorRemoveAfterNext", testIteratorRemove(iteratorAfterNext(ABC_removeB_AC(),1), Result.NoException));
			printTest("ABC_removeB_AC_testIteratorHasNextAfterNext2", testIteratorHasNext(iteratorAfterNext(ABC_removeB_AC(),2), Result.False));
			printTest("ABC_removeB_AC_testIteratorNextAfterNext2", testIteratorNext(iteratorAfterNext(ABC_removeB_AC(),2), null, Result.NoSuchElement));
			printTest("ABC_removeB_AC_testIteratorRemoveAfterNext2", testIteratorRemove(iteratorAfterNext(ABC_removeB_AC(),2), Result.NoException));
			printTest("ABC_removeB_AC_testIteratorNextAfterRemoveNext", testIteratorNext(iteratorAfterRemove(iteratorAfterNext(ABC_removeB_AC(),1)),ELEMENT_C,  Result.MatchingValue));
			printTest("ABC_removeB_AC_testIteratorNextAfterRemoveNext2", testIteratorNext(iteratorAfterRemove(iteratorAfterNext(ABC_removeB_AC(),2)),null,  Result.NoSuchElement));
			printTest("ABC_removeB_AC_testIteratorHasNextAfterRemoveNext",testIteratorHasNext(iteratorAfterRemove(iteratorAfterNext(ABC_removeB_AC(),1)),Result.True));
			printTest("ABC_removeB_AC_testIteratorHasNextAfterRemoveNext2",testIteratorHasNext(iteratorAfterRemove(iteratorAfterNext(ABC_removeB_AC(),2)),Result.False));
			printTest("ABC_removeB_AC_iteratorTestRealRemove",testIteratorRealRemove(ABC_removeB_AC(), 1, "[3]", Result.MatchingValue));
			printTest("ABC_removeB_AC_iteratorTestRealRemove2",testIteratorRealRemove(ABC_removeB_AC(), 2, "[1]", Result.MatchingValue));
		}catch (Exception e) {
			System.out.printf("***UNABLE TO RUN/COMPLETE %s***\n", "test_ABC_removeB_AC()");
			e.printStackTrace();
		}
	}
	
	//////////////////////////////////////
	// SCENARIO: [A,B,C] -> remove(C) -> [A,B]
	//  XXX SCENARIO 36
	//////////////////////////////////////
	private ListADT<Integer> ABC_removeC_AB() {
		// cast to UnorderedListADT to make addToFront() available
		UnorderedListADT<Integer> list = (UnorderedListADT<Integer>) newList(); 
		list.addToFront(ELEMENT_C);
		list.addToFront(ELEMENT_B);
		list.addToFront(ELEMENT_A);
		list.remove(ELEMENT_C);
		return list;
	}
	private void test_ABC_removeC_AB(){
		try {
			// ListADT
			printTest("ABC_removeC_AB_testRemoveFirst", testRemoveFirst(ABC_removeC_AB(), ELEMENT_A, Result.MatchingValue));
			printTest("ABC_removeC_AB_testRemoveLast", testRemoveLast(ABC_removeC_AB(), ELEMENT_B, Result.MatchingValue));
			printTest("ABC_removeC_AB_testRemoveA", testRemoveElement(ABC_removeC_AB(), ELEMENT_A, Result.MatchingValue));
			printTest("ABC_removeC_AB_testRemoveB", testRemoveElement(ABC_removeC_AB(), ELEMENT_B, Result.MatchingValue));
			printTest("ABC_removeC_AB_testRemoveC", testRemoveElement(ABC_removeC_AB(), ELEMENT_C, Result.ElementNotFound));
			printTest("ABC_removeC_AB_testFirst", testFirst(ABC_removeC_AB(), ELEMENT_A, Result.MatchingValue));
			printTest("ABC_removeC_AB_testLast", testLast(ABC_removeC_AB(), ELEMENT_B, Result.MatchingValue));
			printTest("ABC_removeC_AB_testContainsA", testContains(ABC_removeC_AB(), ELEMENT_A, Result.True));
			printTest("ABC_removeC_AB_testContainsB", testContains(ABC_removeC_AB(), ELEMENT_B, Result.True));
			printTest("ABC_removeC_AB_testContainsC", testContains(ABC_removeC_AB(), ELEMENT_C, Result.False));
			printTest("ABC_removeC_AB_testIsEmpty", testIsEmpty(ABC_removeC_AB(), Result.False));
			printTest("ABC_removeC_AB_testSize", testSize(ABC_removeC_AB(), 2));
			printTest("ABC_removeC_AB_testToString", testToString(ABC_removeC_AB(), Result.ValidString));
			// UnorderedListADT
			printTest("ABC_removeC_AB_testAddToFrontC", testAddToFront(ABC_removeC_AB(), ELEMENT_C, Result.NoException));
			printTest("ABC_removeC_AB_testAddToRearC", testAddToRear(ABC_removeC_AB(), ELEMENT_C, Result.NoException));
			printTest(	"ABC_removeC_AB_testAddAfterAC", testAddAfter(ABC_removeC_AB(), ELEMENT_A, ELEMENT_C, Result.NoException));
			printTest(	"ABC_removeC_AB_testAddAfterBC", testAddAfter(ABC_removeC_AB(), ELEMENT_B, ELEMENT_C, Result.NoException));
			printTest(	"ABC_removeC_AB_testAddAfterDC", testAddAfter(ABC_removeC_AB(), ELEMENT_D, ELEMENT_C, Result.ElementNotFound));
			// IndexedListADT
			printTest("ABC_removeC_AB_testAddAtIndexNeg1", testAddAtIndex(ABC_removeC_AB(), -1, null, Result.IndexOutOfBounds));
			printTest("ABC_removeC_AB_testAddAtIndex0C", testAddAtIndex(ABC_removeC_AB(), 0, ELEMENT_C, Result.NoException));
			printTest("ABC_removeC_AB_testAddAtIndex1C", testAddAtIndex(ABC_removeC_AB(), 1, ELEMENT_C, Result.NoException));
			printTest("ABC_removeC_AB_testAddAtIndex2C", testAddAtIndex(ABC_removeC_AB(), 2, ELEMENT_C, Result.NoException));
			printTest("ABC_removeC_AB_testAddAtIndex3", testAddAtIndex(ABC_removeC_AB(), 3, null, Result.IndexOutOfBounds));
			printTest("ABC_removeC_AB_testSetNeg1A", testSet(ABC_removeC_AB(), -1, null, Result.IndexOutOfBounds));
			printTest("ABC_removeC_AB_testSet0C", testSet(ABC_removeC_AB(), 0, ELEMENT_C, Result.NoException));
			printTest("ABC_removeC_AB_testSet1C", testSet(ABC_removeC_AB(), 1, ELEMENT_C, Result.NoException));
			printTest("ABC_removeC_AB_testSet2C", testSet(ABC_removeC_AB(), 2, null, Result.IndexOutOfBounds));
			printTest("ABC_removeC_AB_testAddC", testAdd(ABC_removeC_AB(), ELEMENT_C, Result.NoException));
			printTest("ABC_removeC_AB_testGetNeg1", testGet(ABC_removeC_AB(), -1, null, Result.IndexOutOfBounds));
			printTest("ABC_removeC_AB_testGet0", testGet(ABC_removeC_AB(), 0, ELEMENT_A , Result.MatchingValue));
			printTest("ABC_removeC_AB_testGet1", testGet(ABC_removeC_AB(), 1, ELEMENT_B , Result.MatchingValue));
			printTest("ABC_removeC_AB_testGet2", testGet(ABC_removeC_AB(), 2, null, Result.IndexOutOfBounds));
			printTest("ABC_removeC_AB_testIndexOfA", testIndexOf(ABC_removeC_AB(), ELEMENT_A, 0));
			printTest("ABC_removeC_AB_testIndexOfB", testIndexOf(ABC_removeC_AB(), ELEMENT_B, 1));
			printTest("ABC_removeC_AB_testIndexOfC", testIndexOf(ABC_removeC_AB(), ELEMENT_C, -1));
			printTest("ABC_removeC_AB_testRemoveNeg1", testRemoveIndex(ABC_removeC_AB(), -1, null, Result.IndexOutOfBounds));
			printTest("ABC_removeC_AB_testRemove0", testRemoveIndex(ABC_removeC_AB(), 0, ELEMENT_A, Result.MatchingValue));
			printTest("ABC_removeC_AB_testRemove1", testRemoveIndex(ABC_removeC_AB(), 1, ELEMENT_B, Result.MatchingValue));
			printTest("ABC_removeC_AB_testRemove2", testRemoveIndex(ABC_removeC_AB(), 2, null, Result.IndexOutOfBounds));
			// Iterator
			printTest("ABC_removeC_AB_testIterator", testIterator(ABC_removeC_AB(), Result.NoException));
			printTest("ABC_removeC_AB_testIteratorHasNext", testIteratorHasNext(ABC_removeC_AB().iterator(), Result.True));
			printTest("ABC_removeC_AB_testIteratorNext", testIteratorNext(ABC_removeC_AB().iterator(), ELEMENT_A, Result.MatchingValue));
			printTest("ABC_removeC_AB_testIteratorRemove", testIteratorRemove(ABC_removeC_AB().iterator(), Result.IllegalState));
			printTest("ABC_removeC_AB_testIteratorHasNextAfterNext", testIteratorHasNext(iteratorAfterNext(ABC_removeC_AB(),1), Result.True));
			printTest("ABC_removeC_AB_testIteratorNextAfterNext", testIteratorNext(iteratorAfterNext(ABC_removeC_AB(),1), ELEMENT_B, Result.MatchingValue));
			printTest("ABC_removeC_AB_testIteratorRemoveAfterNext", testIteratorRemove(iteratorAfterNext(ABC_removeC_AB(),1), Result.NoException));
			printTest("ABC_removeC_AB_testIteratorHasNextAfterNext2", testIteratorHasNext(iteratorAfterNext(ABC_removeC_AB(),2), Result.False));
			printTest("ABC_removeC_AB_testIteratorNextAfterNext2", testIteratorNext(iteratorAfterNext(ABC_removeC_AB(),2), null, Result.NoSuchElement));
			printTest("ABC_removeC_AB_testIteratorRemoveAfterNext2", testIteratorRemove(iteratorAfterNext(ABC_removeC_AB(),2), Result.NoException));
			printTest("ABC_removeC_AB_testIteratorNextAfterRemoveNext", testIteratorNext(iteratorAfterRemove(iteratorAfterNext(ABC_removeC_AB(),1)),ELEMENT_B,  Result.MatchingValue));
			printTest("ABC_removeC_AB_testIteratorNextAfterRemoveNext2", testIteratorNext(iteratorAfterRemove(iteratorAfterNext(ABC_removeC_AB(),2)),null,  Result.NoSuchElement));
			printTest("ABC_removeC_AB_testIteratorHasNextAfterRemoveNext",testIteratorHasNext(iteratorAfterRemove(iteratorAfterNext(ABC_removeC_AB(),1)),Result.True));
			printTest("ABC_removeC_AB_testIteratorHasNextAfterRemoveNext2",testIteratorHasNext(iteratorAfterRemove(iteratorAfterNext(ABC_removeC_AB(),2)),Result.False));
			printTest("ABC_removeC_AB_iteratorTestRealRemove",testIteratorRealRemove(ABC_removeC_AB(), 1, "[2]", Result.MatchingValue));
			printTest("ABC_removeC_AB_iteratorTestRealRemove2",testIteratorRealRemove(ABC_removeC_AB(), 2, "[1]", Result.MatchingValue));
			//ListIterator
			if(A_removeFirst_empty().getClass()==testDLL().getClass()){
			printTest("ABC_removeC_AB_testListIteratorUnIndexed", testListIteratorUnIndexed(ABC_removeC_AB(), Result.NoException));
			printTest("ABC_removeC_AB_testListIteratorIndexedNeg1", testListIteratorIndexed(ABC_removeC_AB(), -1, Result.IndexOutOfBounds));
			printTest("ABC_removeC_AB_testListIteratorIndexed0", testListIteratorIndexed(ABC_removeC_AB(), 0, Result.NoException));
			printTest("ABC_removeC_AB_testListIteratorIndexed1", testListIteratorIndexed(ABC_removeC_AB(), 1, Result.NoException));
			printTest("ABC_removeC_AB_testListIteratorIndexed2", testListIteratorIndexed(ABC_removeC_AB(), 2, Result.NoException));
			printTest("ABC_removeC_AB_testListIteratorIndexed3", testListIteratorIndexed(ABC_removeC_AB(), 3, Result.IndexOutOfBounds));
			
			printTest("ABC_removeC_AB_testListIteratorHasNext", testListIteratorHasNext(ABC_removeC_AB(),null, null, Result.True));
			printTest("ABC_removeC_AB_testListIteratorNext", testListIteratorNext(ABC_removeC_AB(),null, null, ELEMENT_A, Result.MatchingValue));
			printTest("ABC_removeC_AB_testListIteratorNextIndex", testListIteratorNextIndex(ABC_removeC_AB(),null, null, 0, Result.MatchingValue));
			printTest("ABC_removeC_AB_testListIteratorHasPrevious", testListIteratorHasPrevious(ABC_removeC_AB(),null, null, Result.False));
			printTest("ABC_removeC_AB_testListIteratorPrevious", testListIteratorPrevious(ABC_removeC_AB(),null, null, null, Result.NoSuchElement));
			printTest("ABC_removeC_AB_testListIteratorPreviousIndex", testListIteratorPreviousIndex(ABC_removeC_AB(),null, null, -1, Result.MatchingValue));
			printTest("ABC_removeC_AB_testListIteratorRemove", testListIteratorRemove(ABC_removeC_AB(),null, null, Result.IllegalState));
			printTest("ABC_removeC_AB_testListIteratorRealRemove", testListIteratorRealRemoveAfterNext(ABC_removeC_AB(), null, 0, "[1, 2]", Result.MatchingValue));
			printTest("ABC_removeC_AB_testListIteratorSet", testListIteratorSet(ABC_removeC_AB(), null,null, ELEMENT_D, Result.IllegalState));
			printTest("ABC_removeC_AB_testListIteratorRealSet", testListIteratorRealSetAfterNext(ABC_removeC_AB(), null, 0, ELEMENT_D, "[1, 2]", Result.MatchingValue));
			printTest("ABC_removeC_AB_testListIteratorAdd", testListIteratorAdd(ABC_removeC_AB(),null, null, ELEMENT_D, Result.NoException));
			printTest("ABC_removeC_AB_testListIteratorRealAdd", testListIteratorRealAddAfterNext(ABC_removeC_AB(), null, 0, ELEMENT_D, "[4, 1, 2]", Result.MatchingValue));
			
			printTest("ABC_removeC_AB_testListIteratorHasNextAfterNext", testListIteratorHasNext(null,getListIteratorAfterCall(ABC_removeC_AB(), "next", null, null), null, Result.True));
			printTest("ABC_removeC_AB_testListIteratorNextAfterNext", testListIteratorNext(null,getListIteratorAfterCall(ABC_removeC_AB(), "next", null, null), null, ELEMENT_B, Result.MatchingValue));
			printTest("ABC_removeC_AB_testListIteratorNextIndexAfterNext", testListIteratorNextIndex(null,getListIteratorAfterCall(ABC_removeC_AB(), "next", null, null), null, 1, Result.MatchingValue));
			printTest("ABC_removeC_AB_testListIteratorHasPreviousAfterNext", testListIteratorHasPrevious(null,getListIteratorAfterCall(ABC_removeC_AB(), "next", null, null), null, Result.True));
			printTest("ABC_removeC_AB_testListIteratorPreviousAfterNext", testListIteratorPrevious(null,getListIteratorAfterCall(ABC_removeC_AB(), "next", null, null), null, ELEMENT_A, Result.MatchingValue));
			printTest("ABC_removeC_AB_testListIteratorPreviousIndexAfterNext", testListIteratorPreviousIndex(null,getListIteratorAfterCall(ABC_removeC_AB(), "next", null, null), null, 0, Result.MatchingValue));
			printTest("ABC_removeC_AB_testListIteratorRemoveAfterNext", testListIteratorRemove(null,getListIteratorAfterCall(ABC_removeC_AB(), "next", null, null), null, Result.NoException));
			printTest("ABC_removeC_AB_testListIteratorRealRemoveAfterNext", testListIteratorRealRemoveAfterNext(ABC_removeC_AB(),null, 1, "[2]", Result.MatchingValue));
			printTest("ABC_removeC_AB_testListIteratorSetAfterNext", testListIteratorSet(null, getListIteratorAfterCall(ABC_removeC_AB(), "next", null, null),null, ELEMENT_D, Result.NoException));
			printTest("ABC_removeC_AB_testListIteratorRealSetAfterNext", testListIteratorRealSetAfterNext(ABC_removeC_AB(), null, 1, ELEMENT_D, "[4, 2]", Result.MatchingValue));
			printTest("ABC_removeC_AB_testListIteratorAddAfterNext", testListIteratorAdd(null,getListIteratorAfterCall(ABC_removeC_AB(), "next", null, null), null, ELEMENT_D, Result.NoException));
			printTest("ABC_removeC_AB_testListIteratorRealAddAfterNext", testListIteratorRealAddAfterNext(ABC_removeC_AB(), null, 1, ELEMENT_D, "[1, 4, 2]", Result.MatchingValue));
			
			printTest("ABC_removeC_AB_testListIteratorHasNextAfter2Next", testListIteratorHasNext(null,getListIteratorAfterCallItrItr(getListIteratorAfterCall(ABC_removeC_AB(), "next", null, null), "next", null, null), null, Result.False));
			printTest("ABC_removeC_AB_testListIteratorNextAfter2Next", testListIteratorNext(null,getListIteratorAfterCallItrItr(getListIteratorAfterCall(ABC_removeC_AB(), "next", null, null), "next", null, null), null, null, Result.NoSuchElement));
			printTest("ABC_removeC_AB_testListIteratorNextIndexAfter2Next", testListIteratorNextIndex(null,getListIteratorAfterCallItrItr(getListIteratorAfterCall(ABC_removeC_AB(), "next", null, null), "next", null, null), null, 2, Result.MatchingValue));
			printTest("ABC_removeC_AB_testListIteratorHasPreviousAfter2Next", testListIteratorHasPrevious(null,getListIteratorAfterCallItrItr(getListIteratorAfterCall(ABC_removeC_AB(), "next", null, null), "next", null, null), null, Result.True));
			printTest("ABC_removeC_AB_testListIteratorPreviousAfter2Next", testListIteratorPrevious(null,getListIteratorAfterCallItrItr(getListIteratorAfterCall(ABC_removeC_AB(), "next", null, null), "next", null, null), null, ELEMENT_B, Result.MatchingValue));
			printTest("ABC_removeC_AB_testListIteratorPreviousIndexAfter2Next", testListIteratorPreviousIndex(null,getListIteratorAfterCallItrItr(getListIteratorAfterCall(ABC_removeC_AB(), "next", null, null), "next", null, null), null, 1, Result.MatchingValue));
			printTest("ABC_removeC_AB_testListIteratorRemoveAfter2Next", testListIteratorRemove(null,getListIteratorAfterCallItrItr(getListIteratorAfterCall(ABC_removeC_AB(), "next", null, null), "next", null, null), null, Result.NoException));
			printTest("ABC_removeC_AB_testListIteratorRealRemoveAfter2Next", testListIteratorRealRemoveAfterNext(ABC_removeC_AB(),null, 2, "[1]", Result.MatchingValue));
			printTest("ABC_removeC_AB_testListIteratorSetAfter2Next", testListIteratorSet(null, getListIteratorAfterCallItrItr(getListIteratorAfterCall(ABC_removeC_AB(), "next", null, null), "next", null, null),null, ELEMENT_D, Result.NoException));
			printTest("ABC_removeC_AB_testListIteratorRealSetAfter2Next", testListIteratorRealSetAfterNext(ABC_removeC_AB(), null, 2, ELEMENT_D, "[1, 4]", Result.MatchingValue));
			printTest("ABC_removeC_AB_testListIteratorAddAfter2Next", testListIteratorAdd(null,getListIteratorAfterCallItrItr(getListIteratorAfterCall(ABC_removeC_AB(), "next", null, null), "next", null, null), null, ELEMENT_D, Result.NoException));
			printTest("ABC_removeC_AB_testListIteratorRealAddAfter2Next", testListIteratorRealAddAfterNext(ABC_removeC_AB(), null, 2, ELEMENT_D, "[1, 2, 4]", Result.MatchingValue));
			
			printTest("ABC_removeC_AB_testListIterator0HasNext", testListIteratorHasNext(ABC_removeC_AB(),null, 0, Result.True));
			printTest("ABC_removeC_AB_testListIterator0Next", testListIteratorNext(ABC_removeC_AB(),null, 0, ELEMENT_A, Result.MatchingValue));
			printTest("ABC_removeC_AB_testListIterator0NextIndex", testListIteratorNextIndex(ABC_removeC_AB(),null, 0, 0, Result.MatchingValue));
			printTest("ABC_removeC_AB_testListIterator0HasPrevious", testListIteratorHasPrevious(ABC_removeC_AB(),null, 0, Result.False));
			printTest("ABC_removeC_AB_testListIterator0Previous", testListIteratorPrevious(ABC_removeC_AB(),null, 0, null, Result.NoSuchElement));
			printTest("ABC_removeC_AB_testListIterator0PreviousIndex", testListIteratorPreviousIndex(ABC_removeC_AB(),null, 0, -1, Result.MatchingValue));
			printTest("ABC_removeC_AB_testListIterator0Remove", testListIteratorRemove(ABC_removeC_AB(),null, 0, Result.IllegalState));
			printTest("ABC_removeC_AB_testListIterator0RealRemove", testListIteratorRealRemoveAfterNext(ABC_removeC_AB(), 0, 0, "[1, 2]", Result.MatchingValue));
			printTest("ABC_removeC_AB_testListIterator0Set", testListIteratorSet(ABC_removeC_AB(), null,0, ELEMENT_D, Result.IllegalState));
			printTest("ABC_removeC_AB_testListIterator0RealSet", testListIteratorRealSetAfterNext(ABC_removeC_AB(), 0, 0, ELEMENT_D, "[1, 2]", Result.MatchingValue));
			printTest("ABC_removeC_AB_testListIterator0Add", testListIteratorAdd(ABC_removeC_AB(),null, 0, ELEMENT_D, Result.NoException));
			printTest("ABC_removeC_AB_testListIterator0RealAdd", testListIteratorRealAddAfterNext(ABC_removeC_AB(), 0, 0, ELEMENT_D, "[4, 1, 2]", Result.MatchingValue));
			
			printTest("ABC_removeC_AB_testListIterator0HasNextAfterNext", testListIteratorHasNext(null,getListIteratorAfterCall(ABC_removeC_AB(), "next", 0, null), 0, Result.True));
			printTest("ABC_removeC_AB_testListIterator0NextAfterNext", testListIteratorNext(null,getListIteratorAfterCall(ABC_removeC_AB(), "next", 0, null), 0, ELEMENT_B, Result.MatchingValue));
			printTest("ABC_removeC_AB_testListIterator0NextIndexAfterNext", testListIteratorNextIndex(null,getListIteratorAfterCall(ABC_removeC_AB(), "next", 0, null), 0, 1, Result.MatchingValue));
			printTest("ABC_removeC_AB_testListIterator0HasPreviousAfterNext", testListIteratorHasPrevious(null,getListIteratorAfterCall(ABC_removeC_AB(), "next", 0, null),0, Result.True));
			printTest("ABC_removeC_AB_testListIterator0PreviousAfterNext", testListIteratorPrevious(null,getListIteratorAfterCall(ABC_removeC_AB(), "next", 0, null), 0, ELEMENT_A, Result.MatchingValue));
			printTest("ABC_removeC_AB_testListIterator0PreviousIndexAfterNext", testListIteratorPreviousIndex(null,getListIteratorAfterCall(ABC_removeC_AB(), "next", 0, null), 0, 0, Result.MatchingValue));
			printTest("ABC_removeC_AB_testListIterator0RemoveAfterNext", testListIteratorRemove(null,getListIteratorAfterCall(ABC_removeC_AB(), "next", 0, null), 0, Result.NoException));
			printTest("ABC_removeC_AB_testListIterator0RealRemoveAfterNext", testListIteratorRealRemoveAfterNext(ABC_removeC_AB(),0, 1, "[2]", Result.MatchingValue));
			printTest("ABC_removeC_AB_testListIterator0SetAfterNext", testListIteratorSet(null, getListIteratorAfterCall(ABC_removeC_AB(), "next", 0, null),0, ELEMENT_D, Result.NoException));
			printTest("ABC_removeC_AB_testListIterator0RealSetAfterNext", testListIteratorRealSetAfterNext(ABC_removeC_AB(), 0, 1, ELEMENT_D, "[4, 2]", Result.MatchingValue));
			printTest("ABC_removeC_AB_testListIterator0AddAfterNext", testListIteratorAdd(null,getListIteratorAfterCall(ABC_removeC_AB(), "next", 0, null), 0, ELEMENT_D, Result.NoException));
			printTest("ABC_removeC_AB_testListIterator0RealAddAfterNext", testListIteratorRealAddAfterNext(ABC_removeC_AB(), 0, 1, ELEMENT_D, "[1, 4, 2]", Result.MatchingValue));
			
			printTest("ABC_removeC_AB_testListIterator0HasNextAfter2Next", testListIteratorHasNext(null,getListIteratorAfterCallItrItr(getListIteratorAfterCall(ABC_removeC_AB(), "next", 0, null), "next", null, null), 0, Result.False));
			printTest("ABC_removeC_AB_testListIterator0NextAfter2Next", testListIteratorNext(null,getListIteratorAfterCallItrItr(getListIteratorAfterCall(ABC_removeC_AB(), "next", 0, null), "next", null, null), 0, null, Result.NoSuchElement));
			printTest("ABC_removeC_AB_testListIterator0NextIndexAfter2Next", testListIteratorNextIndex(null,getListIteratorAfterCallItrItr(getListIteratorAfterCall(ABC_removeC_AB(), "next", 0, null), "next", null, null), 0, 2, Result.MatchingValue));
			printTest("ABC_removeC_AB_testListIterator0HasPreviousAfter2Next", testListIteratorHasPrevious(null,getListIteratorAfterCallItrItr(getListIteratorAfterCall(ABC_removeC_AB(), "next", 0, null), "next", null, null), 0, Result.True));
			printTest("ABC_removeC_AB_testListIterator0PreviousAfter2Next", testListIteratorPrevious(null,getListIteratorAfterCallItrItr(getListIteratorAfterCall(ABC_removeC_AB(), "next", 0, null), "next", null, null), 0, ELEMENT_B, Result.MatchingValue));
			printTest("ABC_removeC_AB_testListIterator0PreviousIndexAfter2Next", testListIteratorPreviousIndex(null,getListIteratorAfterCallItrItr(getListIteratorAfterCall(ABC_removeC_AB(), "next", 0, null), "next", null, null), 0, 1, Result.MatchingValue));
			printTest("ABC_removeC_AB_testListIterator0RemoveAfter2Next", testListIteratorRemove(null,getListIteratorAfterCallItrItr(getListIteratorAfterCall(ABC_removeC_AB(), "next", 0, null), "next", null, null), 0, Result.NoException));
			printTest("ABC_removeC_AB_testListIterator0RealRemoveAfter2Next", testListIteratorRealRemoveAfterNext(ABC_removeC_AB(),0, 2, "[1]", Result.MatchingValue));
			printTest("ABC_removeC_AB_testListIterator0SetAfter2Next", testListIteratorSet(null, getListIteratorAfterCallItrItr(getListIteratorAfterCall(ABC_removeC_AB(), "next", 0, null), "next", null, null),0, ELEMENT_D, Result.NoException));
			printTest("ABC_removeC_AB_testListIterator0RealSetAfter2Next", testListIteratorRealSetAfterNext(ABC_removeC_AB(), 0,2, ELEMENT_D, "[1, 4]", Result.MatchingValue));
			printTest("ABC_removeC_AB_testListIterator0AddAfter2Next", testListIteratorAdd(null,getListIteratorAfterCallItrItr(getListIteratorAfterCall(ABC_removeC_AB(), "next", 0, null), "next", null, null), 0, ELEMENT_D, Result.NoException));
			printTest("ABC_removeC_AB_testListIterator0RealAddAfter2Next", testListIteratorRealAddAfterNext(ABC_removeC_AB(), 0, 2, ELEMENT_D, "[1, 2, 4]", Result.MatchingValue));
	
			printTest("ABC_removeC_AB_testListIterator1HasNext", testListIteratorHasNext( ABC_removeC_AB(),null, 1, Result.True));
			printTest("ABC_removeC_AB_testListIterator1Next", testListIteratorNext( ABC_removeC_AB(),null, 1, ELEMENT_B, Result.MatchingValue));
			printTest("ABC_removeC_AB_testListIterator1NextIndex", testListIteratorNextIndex( ABC_removeC_AB(),null, 1, 1, Result.MatchingValue));
			printTest("ABC_removeC_AB_testListIterator1HasPrevious", testListIteratorHasPrevious( ABC_removeC_AB(),null, 1, Result.True));
			printTest("ABC_removeC_AB_testListIterator1Previous", testListIteratorPrevious( ABC_removeC_AB(),null, 1, ELEMENT_A, Result.MatchingValue));
			printTest("ABC_removeC_AB_testListIterator1PreviousIndex", testListIteratorPreviousIndex( ABC_removeC_AB(),null, 1, 0, Result.MatchingValue));
			printTest("ABC_removeC_AB_testListIterator1Remove", testListIteratorRemove( ABC_removeC_AB(),null, 1, Result.IllegalState));
			printTest("ABC_removeC_AB_testListIterator1RealRemove", testListIteratorRealRemoveAfterNext(ABC_removeC_AB(),0, 1, "[2]", Result.MatchingValue));
			printTest("ABC_removeC_AB_testListIterator1Set", testListIteratorSet( ABC_removeC_AB(),null,1, ELEMENT_D, Result.IllegalState));
			printTest("ABC_removeC_AB_testListIterator1RealSet", testListIteratorRealSetAfterNext(ABC_removeC_AB(), 0, 1, ELEMENT_D, "[4, 2]", Result.MatchingValue));
			printTest("ABC_removeC_AB_testListIterator1Add", testListIteratorAdd( ABC_removeC_AB(),null, 1, ELEMENT_D, Result.NoException));
			printTest("ABC_removeC_AB_testListIterator1RealAdd", testListIteratorRealAddAfterNext(ABC_removeC_AB(), 0, 1, ELEMENT_D, "[1, 4, 2]", Result.MatchingValue));
			
			printTest("ABC_removeC_AB_testListIterator1HasNextAfterNext", testListIteratorHasNext(null,getListIteratorAfterCall(ABC_removeC_AB(), "next", 1, null), 1, Result.False));
			printTest("ABC_removeC_AB_testListIterator1NextAfterNext", testListIteratorNext(null,getListIteratorAfterCall(ABC_removeC_AB(), "next", 1, null), 1, null, Result.NoSuchElement));
			printTest("ABC_removeC_AB_testListIterator1NextIndexAfterNext", testListIteratorNextIndex(null,getListIteratorAfterCall(ABC_removeC_AB(), "next", 1, null), 1, 2, Result.MatchingValue));
			printTest("ABC_removeC_AB_testListIterator1HasPreviousAfterNext", testListIteratorHasPrevious(null,getListIteratorAfterCall(ABC_removeC_AB(), "next", 1, null), 1, Result.True));
			printTest("ABC_removeC_AB_testListIterator1PreviousAfterNext", testListIteratorPrevious(null,getListIteratorAfterCall(ABC_removeC_AB(), "next", 1, null), 1, ELEMENT_B, Result.MatchingValue));
			printTest("ABC_removeC_AB_testListIterator1PreviousIndexAfterNext", testListIteratorPreviousIndex(null,getListIteratorAfterCall(ABC_removeC_AB(), "next", 1, null), 1, 1, Result.MatchingValue));
			printTest("ABC_removeC_AB_testListIterator1RemoveAfterNext", testListIteratorRemove(null,getListIteratorAfterCall(ABC_removeC_AB(), "next", 1, null), 1, Result.NoException));
			printTest("ABC_removeC_AB_testListIterator1RealRemoveAfterNext", testListIteratorRealRemoveAfterNext(ABC_removeC_AB(),1, 1, "[1]", Result.MatchingValue));
			printTest("ABC_removeC_AB_testListIterator1SetAfterNext", testListIteratorSet(null, getListIteratorAfterCall(ABC_removeC_AB(), "next", 1, null),1, ELEMENT_D, Result.NoException));
			printTest("ABC_removeC_AB_testListIterator1RealSetAfterNext", testListIteratorRealSetAfterNext(ABC_removeC_AB(), 1, 1, ELEMENT_D, "[1, 4]", Result.MatchingValue));
			printTest("ABC_removeC_AB_testListIterator1AddAfterNext", testListIteratorAdd(null,getListIteratorAfterCall(ABC_removeC_AB(), "next", 1, null), null, ELEMENT_D, Result.NoException));
			printTest("ABC_removeC_AB_testListIterator1RealAddAfterNext", testListIteratorRealAddAfterNext(ABC_removeC_AB(), 1, 1, ELEMENT_D, "[1, 2, 4]", Result.MatchingValue));
			
			printTest("ABC_removeC_AB_testListIterator1HasNextAfterPrev", testListIteratorHasNext(null,getListIteratorAfterCall(ABC_removeC_AB(), "previous", 1, null), null, Result.True));
			printTest("ABC_removeC_AB_testListIterator1NextAfterPrev", testListIteratorNext(null,getListIteratorAfterCall(ABC_removeC_AB(), "previous", 1, null), null, ELEMENT_A, Result.MatchingValue));
			printTest("ABC_removeC_AB_testListIterator1NextIndexAfterPrev", testListIteratorNextIndex(null,getListIteratorAfterCall(ABC_removeC_AB(), "previous", 1, null), null, 0, Result.MatchingValue));
			printTest("ABC_removeC_AB_testListIterator1HasPreviousAfterPrev", testListIteratorHasPrevious(null,getListIteratorAfterCall(ABC_removeC_AB(), "previous", 1, null), null, Result.False));
			printTest("ABC_removeC_AB_testListIterator1PreviousAfterPrev", testListIteratorPrevious(null,getListIteratorAfterCall(ABC_removeC_AB(), "previous", 1, null), null, null, Result.NoSuchElement));
			printTest("ABC_removeC_AB_testListIterator1PreviousIndexAfterPrev", testListIteratorPreviousIndex(null,getListIteratorAfterCall(ABC_removeC_AB(), "previous", 1, null), null, -1, Result.MatchingValue));
			printTest("ABC_removeC_AB_testListIterator1RemoveAfterPrev", testListIteratorRemove(null,getListIteratorAfterCall(ABC_removeC_AB(), "previous", 1, null), null, Result.NoException));
			printTest("ABC_removeC_AB_testListIterator1RealRemoveAfterPrev", testListIteratorRealRemoveAfterPrev(ABC_removeC_AB(), 1, 1, "[2]", Result.MatchingValue));
			printTest("ABC_removeC_AB_testListIterator1SetAfterPrev", testListIteratorSet(null, getListIteratorAfterCall(ABC_removeC_AB(), "previous", 1, null),null, ELEMENT_D, Result.NoException));
			printTest("ABC_removeC_AB_testListIterator1RealSetAfterPrev", testListIteratorRealSetAfterPrev(ABC_removeC_AB(), 1, 1, ELEMENT_D, "[4, 2]", Result.MatchingValue));
			printTest("ABC_removeC_AB_testListIterator1AddAfterPrev", testListIteratorAdd(null,getListIteratorAfterCall(ABC_removeC_AB(), "previous", 1, null), null, ELEMENT_D, Result.NoException));
			printTest("ABC_removeC_AB_testListIterator1RealAddAfterPrev", testListIteratorRealAddAfterPrev(ABC_removeC_AB(), 1, 1, ELEMENT_D, "[4, 1, 2]", Result.MatchingValue));
			
			printTest("ABC_removeC_AB_testListIterator1HasNextAfterPrevNext", testListIteratorHasNext(null,getListIteratorAfterCallItrItr(getListIteratorAfterCall(ABC_removeC_AB(), "previous", 1, null), "next", null, null), null, Result.True));
			printTest("ABC_removeC_AB_testListIterator1NextAfterPrevNext", testListIteratorNext(null,getListIteratorAfterCallItrItr(getListIteratorAfterCall(ABC_removeC_AB(), "previous", 1, null), "next", null, null), null, ELEMENT_B, Result.MatchingValue));
			printTest("ABC_removeC_AB_testListIterator1NextIndexAfterPrevNext", testListIteratorNextIndex(null,getListIteratorAfterCallItrItr(getListIteratorAfterCall(ABC_removeC_AB(), "previous", 1, null), "next", null, null), null, 1, Result.MatchingValue));
			printTest("ABC_removeC_AB_testListIterator1HasPreviousAfterPrevNext", testListIteratorHasPrevious(null,getListIteratorAfterCallItrItr(getListIteratorAfterCall(ABC_removeC_AB(), "previous", 1, null), "next", null, null), null, Result.True));
			printTest("ABC_removeC_AB_testListIterator1PreviousAfterPrevNext", testListIteratorPrevious(null,getListIteratorAfterCallItrItr(getListIteratorAfterCall(ABC_removeC_AB(), "previous", 1, null), "next", null, null), null, ELEMENT_A, Result.MatchingValue));
			printTest("ABC_removeC_AB_testListIterator1PreviousIndexAfterPrevNext", testListIteratorPreviousIndex(null,getListIteratorAfterCallItrItr(getListIteratorAfterCall(ABC_removeC_AB(), "previous", 1, null), "next", null, null), null, 0, Result.MatchingValue));
			printTest("ABC_removeC_AB_testListIterator1RemoveAfterPrevNext", testListIteratorRemove(null,getListIteratorAfterCallItrItr(getListIteratorAfterCall(ABC_removeC_AB(), "previous", 1, null), "next", null, null), null, Result.NoException));
			printTest("ABC_removeC_AB_testListIterator1RealRemoveAfterPrevNext", testListIteratorRealRemoveAfterNext(ABC_removeC_AB(),null, 1, "[2]", Result.MatchingValue));
			printTest("ABC_removeC_AB_testListIterator1SetAfterPrevNext", testListIteratorSet(null, getListIteratorAfterCallItrItr(getListIteratorAfterCall(ABC_removeC_AB(), "previous", 1, null), "next", null, null),null, ELEMENT_D, Result.NoException));
			printTest("ABC_removeC_AB_testListIterator1RealSetAfterPrevNext", testListIteratorRealSetAfterNext(ABC_removeC_AB(), null, 1, ELEMENT_D, "[4, 2]", Result.MatchingValue));
			printTest("ABC_removeC_AB_testListIterator1AddAfterPrevNext", testListIteratorAdd(null,getListIteratorAfterCallItrItr(getListIteratorAfterCall(ABC_removeC_AB(), "previous", 1, null), "next", null, null), null, ELEMENT_D, Result.NoException));
			printTest("ABC_removeC_AB_testListIterator1RealAddAfterPrevNext", testListIteratorRealAddAfterNext(ABC_removeC_AB(), null, 1, ELEMENT_D, "[1, 4, 2]", Result.MatchingValue));
		
			printTest("ABC_removeC_AB_testListIterator1HasNextAfterPrev2Next", testListIteratorHasNext(null,getListIteratorAfterCallItrItr(getListIteratorAfterCallItrItr(getListIteratorAfterCall(ABC_removeC_AB(), "previous", 1, null), "next", null, null), "next", null, null), 1, Result.False));
			printTest("ABC_removeC_AB_testListIterator1NextAfterPrev2Next", testListIteratorNext(null,getListIteratorAfterCallItrItr(getListIteratorAfterCallItrItr(getListIteratorAfterCall(ABC_removeC_AB(), "previous", 1, null), "next", null, null), "next", null, null), 1, null, Result.NoSuchElement));
			printTest("ABC_removeC_AB_testListIterator1NextIndexAfterPrev2Next", testListIteratorNextIndex(null,getListIteratorAfterCallItrItr(getListIteratorAfterCallItrItr(getListIteratorAfterCall(ABC_removeC_AB(), "previous", 1, null), "next", null, null), "next", null, null), 1, 2, Result.MatchingValue));
			printTest("ABC_removeC_AB_testListIterator1HasPreviousAfterPrev2Next", testListIteratorHasPrevious(null,getListIteratorAfterCallItrItr(getListIteratorAfterCallItrItr(getListIteratorAfterCall(ABC_removeC_AB(), "previous", 1, null), "next", null, null), "next", null, null), 1, Result.True));
			printTest("ABC_removeC_AB_testListIterator1PreviousAfterPrev2Next", testListIteratorPrevious(null,getListIteratorAfterCallItrItr(getListIteratorAfterCallItrItr(getListIteratorAfterCall(ABC_removeC_AB(), "previous", 1, null), "next", null, null), "next", null, null), 1, ELEMENT_B, Result.MatchingValue));
			printTest("ABC_removeC_AB_testListIterator1PreviousIndexAfterPrev2Next", testListIteratorPreviousIndex(null,getListIteratorAfterCallItrItr(getListIteratorAfterCallItrItr(getListIteratorAfterCall(ABC_removeC_AB(), "previous", 1, null), "next", null, null), "next", null, null), 1, 1, Result.MatchingValue));
			printTest("ABC_removeC_AB_testListIterator1RemoveAfterPrev2Next", testListIteratorRemove(null,getListIteratorAfterCallItrItr(getListIteratorAfterCallItrItr(getListIteratorAfterCall(ABC_removeC_AB(), "previous", 1, null), "next", null, null), "next", null, null), 1, Result.NoException));
			printTest("ABC_removeC_AB_testListIterator1RealRemoveAfterPrev2Next", testListIteratorRealRemoveAfterNext(ABC_removeC_AB(),1, 1, "[1]", Result.MatchingValue));
			printTest("ABC_removeC_AB_testListIterator1SetAfterPrev2Next", testListIteratorSet(null, getListIteratorAfterCallItrItr(getListIteratorAfterCallItrItr(getListIteratorAfterCall(ABC_removeC_AB(), "previous", 1, null), "next", null, null), "next", null, null),1, ELEMENT_D, Result.NoException));
			printTest("ABC_removeC_AB_testListIterator1RealSetAfterPrev2Next", testListIteratorRealSetAfterNext(ABC_removeC_AB(), 1, 1, ELEMENT_D, "[1, 4]", Result.MatchingValue));
			printTest("ABC_removeC_AB_testListIterator1AddAfterPrev2Next", testListIteratorAdd(null,getListIteratorAfterCallItrItr(getListIteratorAfterCallItrItr(getListIteratorAfterCall(ABC_removeC_AB(), "previous", 1, null), "next", null, null), "next", null, null), null, ELEMENT_D, Result.NoException));
			printTest("ABC_removeC_AB_testListIterator1RealAddAfterPrev2Next", testListIteratorRealAddAfterNext(ABC_removeC_AB(), 1, 1, ELEMENT_D, "[1, 2, 4]", Result.MatchingValue));
			}
		} catch (Exception e) {
			System.out.printf("***UNABLE TO RUN/COMPLETE %s***\n", "test_ABC_removeC_AB");
			e.printStackTrace();
		}
	}
	
	//////////////////////////////////////
	// SCENARIO: [A,B,C] -> remove(0) -> [B,C]
	//  XXX SCENARIO 37
	//////////////////////////////////////
	private ListADT<Integer> ABC_remove0_BC() {
		IndexedListADT<Integer> list = (IndexedListADT<Integer>) newList(); 
		list.add(ELEMENT_A);
		list.add(ELEMENT_B);
		list.add(ELEMENT_C);
		list.remove(0);
		return list;
	}
	private void test_ABC_remove0_BC(){
		try {
			// ListADT
			printTest("ABC_remove0_BC_testRemoveFirst", testRemoveFirst(ABC_remove0_BC(), ELEMENT_B, Result.MatchingValue));
			printTest("ABC_remove0_BC_testRemoveLast", testRemoveLast(ABC_remove0_BC(), ELEMENT_C, Result.MatchingValue));
			printTest("ABC_remove0_BC_testRemoveA", testRemoveElement(ABC_remove0_BC(), ELEMENT_A, Result.ElementNotFound));
			printTest("ABC_remove0_BC_testRemoveB", testRemoveElement(ABC_remove0_BC(), ELEMENT_B, Result.MatchingValue));
			printTest("ABC_remove0_BC_testRemoveC", testRemoveElement(ABC_remove0_BC(), ELEMENT_C, Result.MatchingValue));
			printTest("ABC_remove0_BC_testFirst", testFirst(ABC_remove0_BC(), ELEMENT_B, Result.MatchingValue));
			printTest("ABC_remove0_BC_testLast", testLast(ABC_remove0_BC(), ELEMENT_C, Result.MatchingValue));
			printTest("ABC_remove0_BC_testContainsA", testContains(ABC_remove0_BC(), ELEMENT_A, Result.False));
			printTest("ABC_remove0_BC_testContainsB", testContains(ABC_remove0_BC(), ELEMENT_B, Result.True));
			printTest("ABC_remove0_BC_testContainsC", testContains(ABC_remove0_BC(), ELEMENT_C, Result.True));
			printTest("ABC_remove0_BC_testIsEmpty", testIsEmpty(ABC_remove0_BC(), Result.False));
			printTest("ABC_remove0_BC_testSize", testSize(ABC_remove0_BC(), 2));
			printTest("ABC_remove0_BC_testToString", testToString(ABC_remove0_BC(), Result.ValidString));
			// UnorderedListADT
			printTest("ABC_remove0_BC_testAddToFrontD", testAddToFront(ABC_remove0_BC(), ELEMENT_D, Result.NoException));
			printTest("ABC_remove0_BC_testAddToRearD", testAddToRear(ABC_remove0_BC(), ELEMENT_D, Result.NoException));
			printTest(	"ABC_remove0_BC_testAddAfterAD", testAddAfter(ABC_remove0_BC(), ELEMENT_A, ELEMENT_D, Result.ElementNotFound));
			printTest(	"ABC_remove0_BC_testAddAfterBD", testAddAfter(ABC_remove0_BC(), ELEMENT_B, ELEMENT_D, Result.NoException));
			printTest(	"ABC_remove0_BC_testAddAfterCD", testAddAfter(ABC_remove0_BC(), ELEMENT_C, ELEMENT_D, Result.NoException));
			// IndexedListADT
			printTest("ABC_remove0_BC_testAddAtIndexNeg1", testAddAtIndex(ABC_remove0_BC(), -1, null, Result.IndexOutOfBounds));
			printTest("ABC_remove0_BC_testAddAtIndex0D", testAddAtIndex(ABC_remove0_BC(), 0, ELEMENT_D, Result.NoException));
			printTest("ABC_remove0_BC_testAddAtIndex1D", testAddAtIndex(ABC_remove0_BC(), 1, ELEMENT_D, Result.NoException));
			printTest("ABC_remove0_BC_testAddAtIndex2D", testAddAtIndex(ABC_remove0_BC(), 2, ELEMENT_D, Result.NoException));
			printTest("ABC_remove0_BC_testAddAtIndex3D", testAddAtIndex(ABC_remove0_BC(), 3, null, Result.IndexOutOfBounds));
			printTest("ABC_remove0_BC_testSetNeg1A", testSet(ABC_remove0_BC(), -1, null, Result.IndexOutOfBounds));
			printTest("ABC_remove0_BC_testSet0D", testSet(ABC_remove0_BC(), 0, ELEMENT_D, Result.NoException));
			printTest("ABC_remove0_BC_testSet1D", testSet(ABC_remove0_BC(), 1, ELEMENT_D, Result.NoException));
			printTest("ABC_remove0_BC_testSet2D", testSet(ABC_remove0_BC(), 2, null, Result.IndexOutOfBounds));
			printTest("ABC_remove0_BC_testAddD", testAdd(ABC_remove0_BC(), ELEMENT_D, Result.NoException));
			printTest("ABC_remove0_BC_testGetNeg1", testGet(ABC_remove0_BC(), -1, null, Result.IndexOutOfBounds));
			printTest("ABC_remove0_BC_testGet0", testGet(ABC_remove0_BC(), 0, ELEMENT_B , Result.MatchingValue));
			printTest("ABC_remove0_BC_testGet1", testGet(ABC_remove0_BC(), 1, ELEMENT_C , Result.MatchingValue));
			printTest("ABC_remove0_BC_testGet2", testGet(ABC_remove0_BC(), 2, null, Result.IndexOutOfBounds));
			printTest("ABC_remove0_BC_testIndexOfA", testIndexOf(ABC_remove0_BC(), ELEMENT_A, -1));
			printTest("ABC_remove0_BC_testIndexOfB", testIndexOf(ABC_remove0_BC(), ELEMENT_B, 0));
			printTest("ABC_remove0_BC_testIndexOfC", testIndexOf(ABC_remove0_BC(), ELEMENT_C, 1));
			printTest("ABC_remove0_BC_testRemoveNeg1", testRemoveIndex(ABC_remove0_BC(), -1, null, Result.IndexOutOfBounds));
			printTest("ABC_remove0_BC_testRemove0", testRemoveIndex(ABC_remove0_BC(), 0, ELEMENT_B, Result.MatchingValue));
			printTest("ABC_remove0_BC_testRemove1", testRemoveIndex(ABC_remove0_BC(), 1, ELEMENT_C, Result.MatchingValue));
			printTest("ABC_remove0_BC_testRemove2", testRemoveIndex(ABC_remove0_BC(), 2, null, Result.IndexOutOfBounds));
			// Iterator
			printTest("ABC_remove0_BC_testIterator", testIterator(ABC_remove0_BC(), Result.NoException));
			printTest("ABC_remove0_BC_testIteratorHasNext", testIteratorHasNext(ABC_remove0_BC().iterator(), Result.True));
			printTest("ABC_remove0_BC_testIteratorNext", testIteratorNext(ABC_remove0_BC().iterator(), ELEMENT_B, Result.MatchingValue));
			printTest("ABC_remove0_BC_testIteratorRemove", testIteratorRemove(ABC_remove0_BC().iterator(), Result.IllegalState));
			printTest("ABC_remove0_BC_testIteratorHasNextAfterNext", testIteratorHasNext(iteratorAfterNext(ABC_remove0_BC(),1), Result.True));
			printTest("ABC_remove0_BC_testIteratorNextAfterNext", testIteratorNext(iteratorAfterNext(ABC_remove0_BC(),1), ELEMENT_C, Result.MatchingValue));
			printTest("ABC_remove0_BC_testIteratorRemoveAfterNext", testIteratorRemove(iteratorAfterNext(ABC_remove0_BC(),1), Result.NoException));
			printTest("ABC_remove0_BC_testIteratorHasNextAfterNext2", testIteratorHasNext(iteratorAfterNext(ABC_remove0_BC(),2), Result.False));
			printTest("ABC_remove0_BC_testIteratorNextAfterNext2", testIteratorNext(iteratorAfterNext(ABC_remove0_BC(),2), null, Result.NoSuchElement));
			printTest("ABC_remove0_BC_testIteratorRemoveAfterNext2", testIteratorRemove(iteratorAfterNext(ABC_remove0_BC(),2), Result.NoException));
			printTest("ABC_remove0_BC_testIteratorNextAfterRemoveNext", testIteratorNext(iteratorAfterRemove(iteratorAfterNext(ABC_remove0_BC(),1)),ELEMENT_C,  Result.MatchingValue));
			printTest("ABC_remove0_BC_testIteratorNextAfterRemoveNext2", testIteratorNext(iteratorAfterRemove(iteratorAfterNext(ABC_remove0_BC(),2)),null,  Result.NoSuchElement));
			printTest("ABC_remove0_BC_testIteratorHasNextAfterRemoveNext",testIteratorHasNext(iteratorAfterRemove(iteratorAfterNext(ABC_remove0_BC(),1)),Result.True));
			printTest("ABC_remove0_BC_testIteratorHasNextAfterRemoveNext2",testIteratorHasNext(iteratorAfterRemove(iteratorAfterNext(ABC_remove0_BC(),2)),Result.False));
			printTest("ABC_remove0_BC_iteratorTestRealRemove",testIteratorRealRemove(ABC_remove0_BC(), 1, "[3]", Result.MatchingValue));
			printTest("ABC_remove0_BC_iteratorTestRealRemove2",testIteratorRealRemove(ABC_remove0_BC(), 2, "[2]", Result.MatchingValue));
		} catch (Exception e) {
			System.out.printf("***UNABLE TO RUN/COMPLETE %s***\n", "test_ABC_remove0_BC");
			e.printStackTrace();
		}
	}
	
	//////////////////////////////////////
	// SCENARIO: [A,B,C] -> remove(1) -> [A,C]
	//  XXX SCENARIO 38
	//////////////////////////////////////
	private ListADT<Integer> ABC_remove1_AC() {
		IndexedListADT<Integer> list = (IndexedListADT<Integer>) newList(); 
		list.add(ELEMENT_A);
		list.add(ELEMENT_B);
		list.add(ELEMENT_C);
		list.remove(1);
		return list;
	}
	private void test_ABC_remove1_AC(){
		try {
			// ListADT
			printTest("ABC_remove1_AC_testRemoveFirst", testRemoveFirst(ABC_remove1_AC(), ELEMENT_A, Result.MatchingValue));
			printTest("ABC_remove1_AC_testRemoveLast", testRemoveLast(ABC_remove1_AC(), ELEMENT_C, Result.MatchingValue));
			printTest("ABC_remove1_AC_testRemoveA", testRemoveElement(ABC_remove1_AC(), ELEMENT_A, Result.MatchingValue));
			printTest("ABC_remove1_AC_testRemoveB", testRemoveElement(ABC_remove1_AC(), ELEMENT_B, Result.ElementNotFound));
			printTest("ABC_remove1_AC_testRemoveC", testRemoveElement(ABC_remove1_AC(), ELEMENT_C, Result.MatchingValue));
			printTest("ABC_remove1_AC_testFirst", testFirst(ABC_remove1_AC(), ELEMENT_A, Result.MatchingValue));
			printTest("ABC_remove1_AC_testLast", testLast(ABC_remove1_AC(), ELEMENT_C, Result.MatchingValue));
			printTest("ABC_remove1_AC_testContainsA", testContains(ABC_remove1_AC(), ELEMENT_A, Result.True));
			printTest("ABC_remove1_AC_testContainsB", testContains(ABC_remove1_AC(), ELEMENT_B, Result.False));
			printTest("ABC_remove1_AC_testContainsC", testContains(ABC_remove1_AC(), ELEMENT_C, Result.True));
			printTest("ABC_remove1_AC_testIsEmpty", testIsEmpty(ABC_remove1_AC(), Result.False));
			printTest("ABC_remove1_AC_testSize", testSize(ABC_remove1_AC(), 2));
			printTest("ABC_remove1_AC_testToString", testToString(ABC_remove1_AC(), Result.ValidString));
			// UnorderedListADT
			printTest("ABC_remove1_AC_testAddToFrontC", testAddToFront(ABC_remove1_AC(), ELEMENT_C, Result.NoException));
			printTest("ABC_remove1_AC_testAddToRearC", testAddToRear(ABC_remove1_AC(), ELEMENT_C, Result.NoException));
			printTest(	"ABC_remove1_AC_testAddAfterAD", testAddAfter(ABC_remove1_AC(), ELEMENT_A, ELEMENT_D, Result.NoException));
			printTest(	"ABC_remove1_AC_testAddAfterBD", testAddAfter(ABC_remove1_AC(), ELEMENT_B, ELEMENT_D, Result.ElementNotFound));
			printTest(	"ABC_remove1_AC_testAddAfterCD", testAddAfter(ABC_remove1_AC(), ELEMENT_C, ELEMENT_D, Result.NoException));
			// IndexedListADT
			printTest("ABC_remove1_AC_testAddAtIndexNeg1", testAddAtIndex(ABC_remove1_AC(), -1, null, Result.IndexOutOfBounds));
			printTest("ABC_remove1_AC_testAddAtIndex0", testAddAtIndex(ABC_remove1_AC(), 0, ELEMENT_D, Result.NoException));
			printTest("ABC_remove1_AC_testAddAtIndex1", testAddAtIndex(ABC_remove1_AC(), 1, ELEMENT_D, Result.NoException));
			printTest("ABC_remove1_AC_testAddAtIndex2", testAddAtIndex(ABC_remove1_AC(), 2, ELEMENT_D, Result.NoException));
			printTest("ABC_remove1_AC_testAddAtIndex3", testAddAtIndex(ABC_remove1_AC(), 3, null, Result.IndexOutOfBounds));
			printTest("ABC_remove1_AC_testSetNeg1D", testSet(ABC_remove1_AC(), -1, null, Result.IndexOutOfBounds));
			printTest("ABC_remove1_AC_testSet0D", testSet(ABC_remove1_AC(), 0, ELEMENT_D, Result.NoException));
			printTest("ABC_remove1_AC_testSet1D", testSet(ABC_remove1_AC(), 1, ELEMENT_D, Result.NoException));
			printTest("ABC_remove1_AC_testSetNeg2D", testSet(ABC_remove1_AC(), 2, null, Result.IndexOutOfBounds));
			printTest("ABC_remove1_AC_testAddD", testAdd(ABC_remove1_AC(), ELEMENT_D, Result.NoException));
			printTest("ABC_remove1_AC_testGetNeg1", testGet(ABC_remove1_AC(), -1, null, Result.IndexOutOfBounds));
			printTest("ABC_remove1_AC_testGet0", testGet(ABC_remove1_AC(), 0, ELEMENT_A , Result.MatchingValue));
			printTest("ABC_remove1_AC_testGet1", testGet(ABC_remove1_AC(), 1, ELEMENT_C , Result.MatchingValue));
			printTest("ABC_remove1_AC_testGet2", testGet(ABC_remove1_AC(), 2, null, Result.IndexOutOfBounds));
			printTest("ABC_remove1_AC_testIndexOfB", testIndexOf(ABC_remove1_AC(), ELEMENT_B, -1));
			printTest("ABC_remove1_AC_testIndexOfA", testIndexOf(ABC_remove1_AC(), ELEMENT_A, 0));
			printTest("ABC_remove1_AC_testIndexOfC", testIndexOf(ABC_remove1_AC(), ELEMENT_C, 1));
			printTest("ABC_remove1_AC_testRemoveNeg1", testRemoveIndex(ABC_remove1_AC(), -1, null, Result.IndexOutOfBounds));
			printTest("ABC_remove1_AC_testRemove0", testRemoveIndex(ABC_remove1_AC(), 0, ELEMENT_A, Result.MatchingValue));
			printTest("ABC_remove1_AC_testRemove1", testRemoveIndex(ABC_remove1_AC(), 1, ELEMENT_C, Result.MatchingValue));
			printTest("ABC_remove1_AC_testRemove2", testRemoveIndex(ABC_remove1_AC(), 2, null, Result.IndexOutOfBounds));
			// Iterator
			printTest("ABC_remove1_AC_testIterator", testIterator(ABC_remove1_AC(), Result.NoException));
			printTest("ABC_remove1_AC_testIteratorHasNext", testIteratorHasNext(ABC_remove1_AC().iterator(), Result.True));
			printTest("ABC_remove1_AC_testIteratorNext", testIteratorNext(ABC_remove1_AC().iterator(), ELEMENT_A, Result.MatchingValue));
			printTest("ABC_remove1_AC_testIteratorRemove", testIteratorRemove(ABC_remove1_AC().iterator(), Result.IllegalState));
			printTest("ABC_remove1_AC_testIteratorHasNextAfterNext", testIteratorHasNext(iteratorAfterNext(ABC_remove1_AC(),1), Result.True));
			printTest("ABC_remove1_AC_testIteratorNextAfterNext", testIteratorNext(iteratorAfterNext(ABC_remove1_AC(),1), ELEMENT_C, Result.MatchingValue));
			printTest("ABC_remove1_AC_testIteratorRemoveAfterNext", testIteratorRemove(iteratorAfterNext(ABC_remove1_AC(),1), Result.NoException));
			printTest("ABC_remove1_AC_testIteratorHasNextAfterNext2", testIteratorHasNext(iteratorAfterNext(ABC_remove1_AC(),2), Result.False));
			printTest("ABC_remove1_AC_testIteratorNextAfterNext2", testIteratorNext(iteratorAfterNext(ABC_remove1_AC(),2), null, Result.NoSuchElement));
			printTest("ABC_remove1_AC_testIteratorRemoveAfterNext2", testIteratorRemove(iteratorAfterNext(ABC_remove1_AC(),2), Result.NoException));
			printTest("ABC_remove1_AC_testIteratorNextAfterRemoveNext", testIteratorNext(iteratorAfterRemove(iteratorAfterNext(ABC_remove1_AC(),1)),ELEMENT_C,  Result.MatchingValue));
			printTest("ABC_remove1_AC_testIteratorNextAfterRemoveNext2", testIteratorNext(iteratorAfterRemove(iteratorAfterNext(ABC_remove1_AC(),2)),null,  Result.NoSuchElement));
			printTest("ABC_remove1_AC_testIteratorHasNextAfterRemoveNext",testIteratorHasNext(iteratorAfterRemove(iteratorAfterNext(ABC_remove1_AC(),1)),Result.True));
			printTest("ABC_remove1_AC_testIteratorHasNextAfterRemoveNext2",testIteratorHasNext(iteratorAfterRemove(iteratorAfterNext(ABC_remove1_AC(),2)),Result.False));
			printTest("ABC_remove1_AC_iteratorTestRealRemove",testIteratorRealRemove(ABC_remove1_AC(), 1, "[3]", Result.MatchingValue));
			printTest("ABC_remove1_AC_iteratorTestRealRemove2",testIteratorRealRemove(ABC_remove1_AC(), 2, "[1]", Result.MatchingValue));
		} catch (Exception e) {
			System.out.printf("***UNABLE TO RUN/COMPLETE %s***\n", "test_ABC_remove1_AC");
			e.printStackTrace();
		}
	}
	
	//////////////////////////////////////
	// SCENARIO: [A,B,C] -> remove(2) -> [A,B]
	//  XXX SCENARIO 39
	//////////////////////////////////////
	private ListADT<Integer> ABC_remove2_AB() {
		IndexedListADT<Integer> list = (IndexedListADT<Integer>) newList(); 
		list.add(ELEMENT_A);
		list.add(ELEMENT_B);
		list.add(ELEMENT_C);
		list.remove(2);
		return list;
	}
	private void test_ABC_remove2_AB(){
		try {
			// ListADT
			printTest("ABC_remove2_AB_testRemoveFirst", testRemoveFirst(ABC_remove2_AB(), ELEMENT_A, Result.MatchingValue));
			printTest("ABC_remove2_AB_testRemoveLast", testRemoveLast(ABC_remove2_AB(), ELEMENT_B, Result.MatchingValue));
			printTest("ABC_remove2_AB_testRemoveA", testRemoveElement(ABC_remove2_AB(), ELEMENT_A, Result.MatchingValue));
			printTest("ABC_remove2_AB_testRemoveB", testRemoveElement(ABC_remove2_AB(), ELEMENT_B, Result.MatchingValue));
			printTest("ABC_remove2_AB_testRemoveC", testRemoveElement(ABC_remove2_AB(), ELEMENT_C, Result.ElementNotFound));
			printTest("ABC_remove2_AB_testFirst", testFirst(ABC_remove2_AB(), ELEMENT_A, Result.MatchingValue));
			printTest("ABC_remove2_AB_testLast", testLast(ABC_remove2_AB(), ELEMENT_B, Result.MatchingValue));
			printTest("ABC_remove2_AB_testContainsA", testContains(ABC_remove2_AB(), ELEMENT_A, Result.True));
			printTest("ABC_remove2_AB_testContainsB", testContains(ABC_remove2_AB(), ELEMENT_B, Result.True));
			printTest("ABC_remove2_AB_testContainsC", testContains(ABC_remove2_AB(), ELEMENT_C, Result.False));
			printTest("ABC_remove2_AB_testIsEmpty", testIsEmpty(ABC_remove2_AB(), Result.False));
			printTest("ABC_remove2_AB_testSize", testSize(ABC_remove2_AB(), 2));
			printTest("ABC_remove2_AB_testToString", testToString(ABC_remove2_AB(), Result.ValidString));
			// UnorderedListADT
			printTest("ABC_remove2_AB_testAddToFrontC", testAddToFront(ABC_remove2_AB(), ELEMENT_C, Result.NoException));
			printTest("ABC_remove2_AB_testAddToRearC", testAddToRear(ABC_remove2_AB(), ELEMENT_C, Result.NoException));
			printTest(	"ABC_remove2_AB_testAddAfterAC", testAddAfter(ABC_remove2_AB(), ELEMENT_A, ELEMENT_C, Result.NoException));
			printTest(	"ABC_remove2_AB_testAddAfterBC", testAddAfter(ABC_remove2_AB(), ELEMENT_B, ELEMENT_C, Result.NoException));
			printTest(	"ABC_remove2_AB_testAddAfterDC", testAddAfter(ABC_remove2_AB(), ELEMENT_D, ELEMENT_C, Result.ElementNotFound));
			// IndexedListADT
			printTest("ABC_remove2_AB_testAddAtIndexNeg1", testAddAtIndex(ABC_remove2_AB(), -1, null, Result.IndexOutOfBounds));
			printTest("ABC_remove2_AB_testAddAtIndex0C", testAddAtIndex(ABC_remove2_AB(), 0, ELEMENT_C, Result.NoException));
			printTest("ABC_remove2_AB_testAddAtIndex1C", testAddAtIndex(ABC_remove2_AB(), 1, ELEMENT_C, Result.NoException));
			printTest("ABC_remove2_AB_testAddAtIndex2C", testAddAtIndex(ABC_remove2_AB(), 2, ELEMENT_C, Result.NoException));
			printTest("ABC_remove2_AB_testAddAtIndex3", testAddAtIndex(ABC_remove2_AB(), 3, null, Result.IndexOutOfBounds));
			printTest("ABC_remove2_AB_testSetNeg1A", testSet(ABC_remove2_AB(), -1, null, Result.IndexOutOfBounds));
			printTest("ABC_remove2_AB_testSet0C", testSet(ABC_remove2_AB(), 0, ELEMENT_C, Result.NoException));
			printTest("ABC_remove2_AB_testSet1C", testSet(ABC_remove2_AB(), 1, ELEMENT_C, Result.NoException));
			printTest("ABC_remove2_AB_testSet2C", testSet(ABC_remove2_AB(), 2, null, Result.IndexOutOfBounds));
			printTest("ABC_remove2_AB_testAddC", testAdd(ABC_remove2_AB(), ELEMENT_C, Result.NoException));
			printTest("ABC_remove2_AB_testGetNeg1", testGet(ABC_remove2_AB(), -1, null, Result.IndexOutOfBounds));
			printTest("ABC_remove2_AB_testGet0", testGet(ABC_remove2_AB(), 0, ELEMENT_A , Result.MatchingValue));
			printTest("ABC_remove2_AB_testGet1", testGet(ABC_remove2_AB(), 1, ELEMENT_B , Result.MatchingValue));
			printTest("ABC_remove2_AB_testGet2", testGet(ABC_remove2_AB(), 2, null, Result.IndexOutOfBounds));
			printTest("ABC_remove2_AB_testIndexOfA", testIndexOf(ABC_remove2_AB(), ELEMENT_A, 0));
			printTest("ABC_remove2_AB_testIndexOfB", testIndexOf(ABC_remove2_AB(), ELEMENT_B, 1));
			printTest("ABC_remove2_AB_testIndexOfC", testIndexOf(ABC_remove2_AB(), ELEMENT_C, -1));
			printTest("ABC_remove2_AB_testRemoveNeg1", testRemoveIndex(ABC_remove2_AB(), -1, null, Result.IndexOutOfBounds));
			printTest("ABC_remove2_AB_testRemove0", testRemoveIndex(ABC_remove2_AB(), 0, ELEMENT_A, Result.MatchingValue));
			printTest("ABC_remove2_AB_testRemove1", testRemoveIndex(ABC_remove2_AB(), 1, ELEMENT_B, Result.MatchingValue));
			printTest("ABC_remove2_AB_testRemove2", testRemoveIndex(ABC_remove2_AB(), 2, null, Result.IndexOutOfBounds));
			// Iterator
			printTest("ABC_remove2_AB_testIterator", testIterator(ABC_remove2_AB(), Result.NoException));
			printTest("ABC_remove2_AB_testIteratorHasNext", testIteratorHasNext(ABC_remove2_AB().iterator(), Result.True));
			printTest("ABC_remove2_AB_testIteratorNext", testIteratorNext(ABC_remove2_AB().iterator(), ELEMENT_A, Result.MatchingValue));
			printTest("ABC_remove2_AB_testIteratorRemove", testIteratorRemove(ABC_remove2_AB().iterator(), Result.IllegalState));
			printTest("ABC_remove2_AB_testIteratorHasNextAfterNext", testIteratorHasNext(iteratorAfterNext(ABC_remove2_AB(),1), Result.True));
			printTest("ABC_remove2_AB_testIteratorNextAfterNext", testIteratorNext(iteratorAfterNext(ABC_remove2_AB(),1), ELEMENT_B, Result.MatchingValue));
			printTest("ABC_remove2_AB_testIteratorRemoveAfterNext", testIteratorRemove(iteratorAfterNext(ABC_remove2_AB(),1), Result.NoException));
			printTest("ABC_remove2_AB_testIteratorHasNextAfterNext2", testIteratorHasNext(iteratorAfterNext(ABC_remove2_AB(),2), Result.False));
			printTest("ABC_remove2_AB_testIteratorNextAfterNext2", testIteratorNext(iteratorAfterNext(ABC_remove2_AB(),2), null, Result.NoSuchElement));
			printTest("ABC_remove2_AB_testIteratorRemoveAfterNext2", testIteratorRemove(iteratorAfterNext(ABC_remove2_AB(),2), Result.NoException));
			printTest("ABC_remove2_AB_testIteratorNextAfterRemoveNext", testIteratorNext(iteratorAfterRemove(iteratorAfterNext(ABC_remove2_AB(),1)),ELEMENT_B,  Result.MatchingValue));
			printTest("ABC_remove2_AB_testIteratorNextAfterRemoveNext2", testIteratorNext(iteratorAfterRemove(iteratorAfterNext(ABC_remove2_AB(),2)),null,  Result.NoSuchElement));
			printTest("ABC_remove2_AB_testIteratorHasNextAfterRemoveNext",testIteratorHasNext(iteratorAfterRemove(iteratorAfterNext(ABC_remove2_AB(),1)),Result.True));
			printTest("ABC_remove2_AB_testIteratorHasNextAfterRemoveNext2",testIteratorHasNext(iteratorAfterRemove(iteratorAfterNext(ABC_remove2_AB(),2)),Result.False));
			printTest("ABC_remove2_AB_iteratorTestRealRemove",testIteratorRealRemove(ABC_remove2_AB(), 1, "[2]", Result.MatchingValue));
			printTest("ABC_remove2_AB_iteratorTestRealRemove2",testIteratorRealRemove(ABC_remove2_AB(), 2, "[1]", Result.MatchingValue));
			//ListIterator
			if(A_removeFirst_empty().getClass()==testDLL().getClass()){
			printTest("ABC_remove2_AB_testListIteratorUnIndexed", testListIteratorUnIndexed(ABC_remove2_AB(), Result.NoException));
			printTest("ABC_remove2_AB_testListIteratorIndexedNeg1", testListIteratorIndexed(ABC_remove2_AB(), -1, Result.IndexOutOfBounds));
			printTest("ABC_remove2_AB_testListIteratorIndexed0", testListIteratorIndexed(ABC_remove2_AB(), 0, Result.NoException));
			printTest("ABC_remove2_AB_testListIteratorIndexed1", testListIteratorIndexed(ABC_remove2_AB(), 1, Result.NoException));
			printTest("ABC_remove2_AB_testListIteratorIndexed2", testListIteratorIndexed(ABC_remove2_AB(), 2, Result.NoException));
			printTest("ABC_remove2_AB_testListIteratorIndexed3", testListIteratorIndexed(ABC_remove2_AB(), 3, Result.IndexOutOfBounds));
			
			printTest("ABC_remove2_AB_testListIteratorHasNext", testListIteratorHasNext(ABC_remove2_AB(),null, null, Result.True));
			printTest("ABC_remove2_AB_testListIteratorNext", testListIteratorNext(ABC_remove2_AB(),null, null, ELEMENT_A, Result.MatchingValue));
			printTest("ABC_remove2_AB_testListIteratorNextIndex", testListIteratorNextIndex(ABC_remove2_AB(),null, null, 0, Result.MatchingValue));
			printTest("ABC_remove2_AB_testListIteratorHasPrevious", testListIteratorHasPrevious(ABC_remove2_AB(),null, null, Result.False));
			printTest("ABC_remove2_AB_testListIteratorPrevious", testListIteratorPrevious(ABC_remove2_AB(),null, null, null, Result.NoSuchElement));
			printTest("ABC_remove2_AB_testListIteratorPreviousIndex", testListIteratorPreviousIndex(ABC_remove2_AB(),null, null, -1, Result.MatchingValue));
			printTest("ABC_remove2_AB_testListIteratorRemove", testListIteratorRemove(ABC_remove2_AB(),null, null, Result.IllegalState));
			printTest("ABC_remove2_AB_testListIteratorRealRemove", testListIteratorRealRemoveAfterNext(ABC_remove2_AB(), null, 0, "[1, 2]", Result.MatchingValue));
			printTest("ABC_remove2_AB_testListIteratorSet", testListIteratorSet(ABC_remove2_AB(), null,null, ELEMENT_D, Result.IllegalState));
			printTest("ABC_remove2_AB_testListIteratorRealSet", testListIteratorRealSetAfterNext(ABC_remove2_AB(), null, 0, ELEMENT_D, "[1, 2]", Result.MatchingValue));
			printTest("ABC_remove2_AB_testListIteratorAdd", testListIteratorAdd(ABC_remove2_AB(),null, null, ELEMENT_D, Result.NoException));
			printTest("ABC_remove2_AB_testListIteratorRealAdd", testListIteratorRealAddAfterNext(ABC_remove2_AB(), null, 0, ELEMENT_D, "[4, 1, 2]", Result.MatchingValue));
			
			printTest("ABC_remove2_AB_testListIteratorHasNextAfterNext", testListIteratorHasNext(null,getListIteratorAfterCall(ABC_remove2_AB(), "next", null, null), null, Result.True));
			printTest("ABC_remove2_AB_testListIteratorNextAfterNext", testListIteratorNext(null,getListIteratorAfterCall(ABC_remove2_AB(), "next", null, null), null, ELEMENT_B, Result.MatchingValue));
			printTest("ABC_remove2_AB_testListIteratorNextIndexAfterNext", testListIteratorNextIndex(null,getListIteratorAfterCall(ABC_remove2_AB(), "next", null, null), null, 1, Result.MatchingValue));
			printTest("ABC_remove2_AB_testListIteratorHasPreviousAfterNext", testListIteratorHasPrevious(null,getListIteratorAfterCall(ABC_remove2_AB(), "next", null, null), null, Result.True));
			printTest("ABC_remove2_AB_testListIteratorPreviousAfterNext", testListIteratorPrevious(null,getListIteratorAfterCall(ABC_remove2_AB(), "next", null, null), null, ELEMENT_A, Result.MatchingValue));
			printTest("ABC_remove2_AB_testListIteratorPreviousIndexAfterNext", testListIteratorPreviousIndex(null,getListIteratorAfterCall(ABC_remove2_AB(), "next", null, null), null, 0, Result.MatchingValue));
			printTest("ABC_remove2_AB_testListIteratorRemoveAfterNext", testListIteratorRemove(null,getListIteratorAfterCall(ABC_remove2_AB(), "next", null, null), null, Result.NoException));
			printTest("ABC_remove2_AB_testListIteratorRealRemoveAfterNext", testListIteratorRealRemoveAfterNext(ABC_remove2_AB(),null, 1, "[2]", Result.MatchingValue));
			printTest("ABC_remove2_AB_testListIteratorSetAfterNext", testListIteratorSet(null, getListIteratorAfterCall(ABC_remove2_AB(), "next", null, null),null, ELEMENT_D, Result.NoException));
			printTest("ABC_remove2_AB_testListIteratorRealSetAfterNext", testListIteratorRealSetAfterNext(ABC_remove2_AB(), null, 1, ELEMENT_D, "[4, 2]", Result.MatchingValue));
			printTest("ABC_remove2_AB_testListIteratorAddAfterNext", testListIteratorAdd(null,getListIteratorAfterCall(ABC_remove2_AB(), "next", null, null), null, ELEMENT_D, Result.NoException));
			printTest("ABC_remove2_AB_testListIteratorRealAddAfterNext", testListIteratorRealAddAfterNext(ABC_remove2_AB(), null, 1, ELEMENT_D, "[1, 4, 2]", Result.MatchingValue));
			
			printTest("ABC_remove2_AB_testListIteratorHasNextAfter2Next", testListIteratorHasNext(null,getListIteratorAfterCallItrItr(getListIteratorAfterCall(ABC_remove2_AB(), "next", null, null), "next", null, null), null, Result.False));
			printTest("ABC_remove2_AB_testListIteratorNextAfter2Next", testListIteratorNext(null,getListIteratorAfterCallItrItr(getListIteratorAfterCall(ABC_remove2_AB(), "next", null, null), "next", null, null), null, null, Result.NoSuchElement));
			printTest("ABC_remove2_AB_testListIteratorNextIndexAfter2Next", testListIteratorNextIndex(null,getListIteratorAfterCallItrItr(getListIteratorAfterCall(ABC_remove2_AB(), "next", null, null), "next", null, null), null, 2, Result.MatchingValue));
			printTest("ABC_remove2_AB_testListIteratorHasPreviousAfter2Next", testListIteratorHasPrevious(null,getListIteratorAfterCallItrItr(getListIteratorAfterCall(ABC_remove2_AB(), "next", null, null), "next", null, null), null, Result.True));
			printTest("ABC_remove2_AB_testListIteratorPreviousAfter2Next", testListIteratorPrevious(null,getListIteratorAfterCallItrItr(getListIteratorAfterCall(ABC_remove2_AB(), "next", null, null), "next", null, null), null, ELEMENT_B, Result.MatchingValue));
			printTest("ABC_remove2_AB_testListIteratorPreviousIndexAfter2Next", testListIteratorPreviousIndex(null,getListIteratorAfterCallItrItr(getListIteratorAfterCall(ABC_remove2_AB(), "next", null, null), "next", null, null), null, 1, Result.MatchingValue));
			printTest("ABC_remove2_AB_testListIteratorRemoveAfter2Next", testListIteratorRemove(null,getListIteratorAfterCallItrItr(getListIteratorAfterCall(ABC_remove2_AB(), "next", null, null), "next", null, null), null, Result.NoException));
			printTest("ABC_remove2_AB_testListIteratorRealRemoveAfter2Next", testListIteratorRealRemoveAfterNext(ABC_remove2_AB(),null, 2, "[1]", Result.MatchingValue));
			printTest("ABC_remove2_AB_testListIteratorSetAfter2Next", testListIteratorSet(null, getListIteratorAfterCallItrItr(getListIteratorAfterCall(ABC_remove2_AB(), "next", null, null), "next", null, null),null, ELEMENT_D, Result.NoException));
			printTest("ABC_remove2_AB_testListIteratorRealSetAfter2Next", testListIteratorRealSetAfterNext(ABC_remove2_AB(), null, 2, ELEMENT_D, "[1, 4]", Result.MatchingValue));
			printTest("ABC_remove2_AB_testListIteratorAddAfter2Next", testListIteratorAdd(null,getListIteratorAfterCallItrItr(getListIteratorAfterCall(ABC_remove2_AB(), "next", null, null), "next", null, null), null, ELEMENT_D, Result.NoException));
			printTest("ABC_remove2_AB_testListIteratorRealAddAfter2Next", testListIteratorRealAddAfterNext(ABC_remove2_AB(), null, 2, ELEMENT_D, "[1, 2, 4]", Result.MatchingValue));
			
			printTest("ABC_remove2_AB_testListIterator0HasNext", testListIteratorHasNext(ABC_remove2_AB(),null, 0, Result.True));
			printTest("ABC_remove2_AB_testListIterator0Next", testListIteratorNext(ABC_remove2_AB(),null, 0, ELEMENT_A, Result.MatchingValue));
			printTest("ABC_remove2_AB_testListIterator0NextIndex", testListIteratorNextIndex(ABC_remove2_AB(),null, 0, 0, Result.MatchingValue));
			printTest("ABC_remove2_AB_testListIterator0HasPrevious", testListIteratorHasPrevious(ABC_remove2_AB(),null, 0, Result.False));
			printTest("ABC_remove2_AB_testListIterator0Previous", testListIteratorPrevious(ABC_remove2_AB(),null, 0, null, Result.NoSuchElement));
			printTest("ABC_remove2_AB_testListIterator0PreviousIndex", testListIteratorPreviousIndex(ABC_remove2_AB(),null, 0, -1, Result.MatchingValue));
			printTest("ABC_remove2_AB_testListIterator0Remove", testListIteratorRemove(ABC_remove2_AB(),null, 0, Result.IllegalState));
			printTest("ABC_remove2_AB_testListIterator0RealRemove", testListIteratorRealRemoveAfterNext(ABC_remove2_AB(), 0, 0, "[1, 2]", Result.MatchingValue));
			printTest("ABC_remove2_AB_testListIterator0Set", testListIteratorSet(ABC_remove2_AB(), null,0, ELEMENT_D, Result.IllegalState));
			printTest("ABC_remove2_AB_testListIterator0RealSet", testListIteratorRealSetAfterNext(ABC_remove2_AB(), 0, 0, ELEMENT_D, "[1, 2]", Result.MatchingValue));
			printTest("ABC_remove2_AB_testListIterator0Add", testListIteratorAdd(ABC_remove2_AB(),null, 0, ELEMENT_D, Result.NoException));
			printTest("ABC_remove2_AB_testListIterator0RealAdd", testListIteratorRealAddAfterNext(ABC_remove2_AB(), 0, 0, ELEMENT_D, "[4, 1, 2]", Result.MatchingValue));
			
			printTest("ABC_remove2_AB_testListIterator0HasNextAfterNext", testListIteratorHasNext(null,getListIteratorAfterCall(ABC_remove2_AB(), "next", 0, null), 0, Result.True));
			printTest("ABC_remove2_AB_testListIterator0NextAfterNext", testListIteratorNext(null,getListIteratorAfterCall(ABC_remove2_AB(), "next", 0, null), 0, ELEMENT_B, Result.MatchingValue));
			printTest("ABC_remove2_AB_testListIterator0NextIndexAfterNext", testListIteratorNextIndex(null,getListIteratorAfterCall(ABC_remove2_AB(), "next", 0, null), 0, 1, Result.MatchingValue));
			printTest("ABC_remove2_AB_testListIterator0HasPreviousAfterNext", testListIteratorHasPrevious(null,getListIteratorAfterCall(ABC_remove2_AB(), "next", 0, null),0, Result.True));
			printTest("ABC_remove2_AB_testListIterator0PreviousAfterNext", testListIteratorPrevious(null,getListIteratorAfterCall(ABC_remove2_AB(), "next", 0, null), 0, ELEMENT_A, Result.MatchingValue));
			printTest("ABC_remove2_AB_testListIterator0PreviousIndexAfterNext", testListIteratorPreviousIndex(null,getListIteratorAfterCall(ABC_remove2_AB(), "next", 0, null), 0, 0, Result.MatchingValue));
			printTest("ABC_remove2_AB_testListIterator0RemoveAfterNext", testListIteratorRemove(null,getListIteratorAfterCall(ABC_remove2_AB(), "next", 0, null), 0, Result.NoException));
			printTest("ABC_remove2_AB_testListIterator0RealRemoveAfterNext", testListIteratorRealRemoveAfterNext(ABC_remove2_AB(),0, 1, "[2]", Result.MatchingValue));
			printTest("ABC_remove2_AB_testListIterator0SetAfterNext", testListIteratorSet(null, getListIteratorAfterCall(ABC_remove2_AB(), "next", 0, null),0, ELEMENT_D, Result.NoException));
			printTest("ABC_remove2_AB_testListIterator0RealSetAfterNext", testListIteratorRealSetAfterNext(ABC_remove2_AB(), 0, 1, ELEMENT_D, "[4, 2]", Result.MatchingValue));
			printTest("ABC_remove2_AB_testListIterator0AddAfterNext", testListIteratorAdd(null,getListIteratorAfterCall(ABC_remove2_AB(), "next", 0, null), 0, ELEMENT_D, Result.NoException));
			printTest("ABC_remove2_AB_testListIterator0RealAddAfterNext", testListIteratorRealAddAfterNext(ABC_remove2_AB(), 0, 1, ELEMENT_D, "[1, 4, 2]", Result.MatchingValue));
			
			printTest("ABC_remove2_AB_testListIterator0HasNextAfter2Next", testListIteratorHasNext(null,getListIteratorAfterCallItrItr(getListIteratorAfterCall(ABC_remove2_AB(), "next", 0, null), "next", null, null), 0, Result.False));
			printTest("ABC_remove2_AB_testListIterator0NextAfter2Next", testListIteratorNext(null,getListIteratorAfterCallItrItr(getListIteratorAfterCall(ABC_remove2_AB(), "next", 0, null), "next", null, null), 0, null, Result.NoSuchElement));
			printTest("ABC_remove2_AB_testListIterator0NextIndexAfter2Next", testListIteratorNextIndex(null,getListIteratorAfterCallItrItr(getListIteratorAfterCall(ABC_remove2_AB(), "next", 0, null), "next", null, null), 0, 2, Result.MatchingValue));
			printTest("ABC_remove2_AB_testListIterator0HasPreviousAfter2Next", testListIteratorHasPrevious(null,getListIteratorAfterCallItrItr(getListIteratorAfterCall(ABC_remove2_AB(), "next", 0, null), "next", null, null), 0, Result.True));
			printTest("ABC_remove2_AB_testListIterator0PreviousAfter2Next", testListIteratorPrevious(null,getListIteratorAfterCallItrItr(getListIteratorAfterCall(ABC_remove2_AB(), "next", 0, null), "next", null, null), 0, ELEMENT_B, Result.MatchingValue));
			printTest("ABC_remove2_AB_testListIterator0PreviousIndexAfter2Next", testListIteratorPreviousIndex(null,getListIteratorAfterCallItrItr(getListIteratorAfterCall(ABC_remove2_AB(), "next", 0, null), "next", null, null), 0, 1, Result.MatchingValue));
			printTest("ABC_remove2_AB_testListIterator0RemoveAfter2Next", testListIteratorRemove(null,getListIteratorAfterCallItrItr(getListIteratorAfterCall(ABC_remove2_AB(), "next", 0, null), "next", null, null), 0, Result.NoException));
			printTest("ABC_remove2_AB_testListIterator0RealRemoveAfter2Next", testListIteratorRealRemoveAfterNext(ABC_remove2_AB(),0, 2, "[1]", Result.MatchingValue));
			printTest("ABC_remove2_AB_testListIterator0SetAfter2Next", testListIteratorSet(null, getListIteratorAfterCallItrItr(getListIteratorAfterCall(ABC_remove2_AB(), "next", 0, null), "next", null, null),0, ELEMENT_D, Result.NoException));
			printTest("ABC_remove2_AB_testListIterator0RealSetAfter2Next", testListIteratorRealSetAfterNext(ABC_remove2_AB(), 0,2, ELEMENT_D, "[1, 4]", Result.MatchingValue));
			printTest("ABC_remove2_AB_testListIterator0AddAfter2Next", testListIteratorAdd(null,getListIteratorAfterCallItrItr(getListIteratorAfterCall(ABC_remove2_AB(), "next", 0, null), "next", null, null), 0, ELEMENT_D, Result.NoException));
			printTest("ABC_remove2_AB_testListIterator0RealAddAfter2Next", testListIteratorRealAddAfterNext(ABC_remove2_AB(), 0, 2, ELEMENT_D, "[1, 2, 4]", Result.MatchingValue));
	
			printTest("ABC_remove2_AB_testListIterator1HasNext", testListIteratorHasNext( ABC_remove2_AB(),null, 1, Result.True));
			printTest("ABC_remove2_AB_testListIterator1Next", testListIteratorNext( ABC_remove2_AB(),null, 1, ELEMENT_B, Result.MatchingValue));
			printTest("ABC_remove2_AB_testListIterator1NextIndex", testListIteratorNextIndex( ABC_remove2_AB(),null, 1, 1, Result.MatchingValue));
			printTest("ABC_remove2_AB_testListIterator1HasPrevious", testListIteratorHasPrevious( ABC_remove2_AB(),null, 1, Result.True));
			printTest("ABC_remove2_AB_testListIterator1Previous", testListIteratorPrevious( ABC_remove2_AB(),null, 1, ELEMENT_A, Result.MatchingValue));
			printTest("ABC_remove2_AB_testListIterator1PreviousIndex", testListIteratorPreviousIndex( ABC_remove2_AB(),null, 1, 0, Result.MatchingValue));
			printTest("ABC_remove2_AB_testListIterator1Remove", testListIteratorRemove( ABC_remove2_AB(),null, 1, Result.IllegalState));
			printTest("ABC_remove2_AB_testListIterator1RealRemove", testListIteratorRealRemoveAfterNext(ABC_remove2_AB(),0, 1, "[2]", Result.MatchingValue));
			printTest("ABC_remove2_AB_testListIterator1Set", testListIteratorSet( ABC_remove2_AB(),null,1, ELEMENT_D, Result.IllegalState));
			printTest("ABC_remove2_AB_testListIterator1RealSet", testListIteratorRealSetAfterNext(ABC_remove2_AB(), 0, 1, ELEMENT_D, "[4, 2]", Result.MatchingValue));
			printTest("ABC_remove2_AB_testListIterator1Add", testListIteratorAdd( ABC_remove2_AB(),null, 1, ELEMENT_D, Result.NoException));
			printTest("ABC_remove2_AB_testListIterator1RealAdd", testListIteratorRealAddAfterNext(ABC_remove2_AB(), 0, 1, ELEMENT_D, "[1, 4, 2]", Result.MatchingValue));
			
			printTest("ABC_remove2_AB_testListIterator1HasNextAfterNext", testListIteratorHasNext(null,getListIteratorAfterCall(ABC_remove2_AB(), "next", 1, null), 1, Result.False));
			printTest("ABC_remove2_AB_testListIterator1NextAfterNext", testListIteratorNext(null,getListIteratorAfterCall(ABC_remove2_AB(), "next", 1, null), 1, null, Result.NoSuchElement));
			printTest("ABC_remove2_AB_testListIterator1NextIndexAfterNext", testListIteratorNextIndex(null,getListIteratorAfterCall(ABC_remove2_AB(), "next", 1, null), 1, 2, Result.MatchingValue));
			printTest("ABC_remove2_AB_testListIterator1HasPreviousAfterNext", testListIteratorHasPrevious(null,getListIteratorAfterCall(ABC_remove2_AB(), "next", 1, null), 1, Result.True));
			printTest("ABC_remove2_AB_testListIterator1PreviousAfterNext", testListIteratorPrevious(null,getListIteratorAfterCall(ABC_remove2_AB(), "next", 1, null), 1, ELEMENT_B, Result.MatchingValue));
			printTest("ABC_remove2_AB_testListIterator1PreviousIndexAfterNext", testListIteratorPreviousIndex(null,getListIteratorAfterCall(ABC_remove2_AB(), "next", 1, null), 1, 1, Result.MatchingValue));
			printTest("ABC_remove2_AB_testListIterator1RemoveAfterNext", testListIteratorRemove(null,getListIteratorAfterCall(ABC_remove2_AB(), "next", 1, null), 1, Result.NoException));
			printTest("ABC_remove2_AB_testListIterator1RealRemoveAfterNext", testListIteratorRealRemoveAfterNext(ABC_remove2_AB(),1, 1, "[1]", Result.MatchingValue));
			printTest("ABC_remove2_AB_testListIterator1SetAfterNext", testListIteratorSet(null, getListIteratorAfterCall(ABC_remove2_AB(), "next", 1, null),1, ELEMENT_D, Result.NoException));
			printTest("ABC_remove2_AB_testListIterator1RealSetAfterNext", testListIteratorRealSetAfterNext(ABC_remove2_AB(), 1, 1, ELEMENT_D, "[1, 4]", Result.MatchingValue));
			printTest("ABC_remove2_AB_testListIterator1AddAfterNext", testListIteratorAdd(null,getListIteratorAfterCall(ABC_remove2_AB(), "next", 1, null), null, ELEMENT_D, Result.NoException));
			printTest("ABC_remove2_AB_testListIterator1RealAddAfterNext", testListIteratorRealAddAfterNext(ABC_remove2_AB(), 1, 1, ELEMENT_D, "[1, 2, 4]", Result.MatchingValue));
			
			printTest("ABC_remove2_AB_testListIterator1HasNextAfterPrev", testListIteratorHasNext(null,getListIteratorAfterCall(ABC_remove2_AB(), "previous", 1, null), null, Result.True));
			printTest("ABC_remove2_AB_testListIterator1NextAfterPrev", testListIteratorNext(null,getListIteratorAfterCall(ABC_remove2_AB(), "previous", 1, null), null, ELEMENT_A, Result.MatchingValue));
			printTest("ABC_remove2_AB_testListIterator1NextIndexAfterPrev", testListIteratorNextIndex(null,getListIteratorAfterCall(ABC_remove2_AB(), "previous", 1, null), null, 0, Result.MatchingValue));
			printTest("ABC_remove2_AB_testListIterator1HasPreviousAfterPrev", testListIteratorHasPrevious(null,getListIteratorAfterCall(ABC_remove2_AB(), "previous", 1, null), null, Result.False));
			printTest("ABC_remove2_AB_testListIterator1PreviousAfterPrev", testListIteratorPrevious(null,getListIteratorAfterCall(ABC_remove2_AB(), "previous", 1, null), null, null, Result.NoSuchElement));
			printTest("ABC_remove2_AB_testListIterator1PreviousIndexAfterPrev", testListIteratorPreviousIndex(null,getListIteratorAfterCall(ABC_remove2_AB(), "previous", 1, null), null, -1, Result.MatchingValue));
			printTest("ABC_remove2_AB_testListIterator1RemoveAfterPrev", testListIteratorRemove(null,getListIteratorAfterCall(ABC_remove2_AB(), "previous", 1, null), null, Result.NoException));
			printTest("ABC_remove2_AB_testListIterator1RealRemoveAfterPrev", testListIteratorRealRemoveAfterPrev(ABC_remove2_AB(), 1, 1, "[2]", Result.MatchingValue));
			printTest("ABC_remove2_AB_testListIterator1SetAfterPrev", testListIteratorSet(null, getListIteratorAfterCall(ABC_remove2_AB(), "previous", 1, null),null, ELEMENT_D, Result.NoException));
			printTest("ABC_remove2_AB_testListIterator1RealSetAfterPrev", testListIteratorRealSetAfterPrev(ABC_remove2_AB(), 1, 1, ELEMENT_D, "[4, 2]", Result.MatchingValue));
			printTest("ABC_remove2_AB_testListIterator1AddAfterPrev", testListIteratorAdd(null,getListIteratorAfterCall(ABC_remove2_AB(), "previous", 1, null), null, ELEMENT_D, Result.NoException));
			printTest("ABC_remove2_AB_testListIterator1RealAddAfterPrev", testListIteratorRealAddAfterPrev(ABC_remove2_AB(), 1, 1, ELEMENT_D, "[4, 1, 2]", Result.MatchingValue));
			
			printTest("ABC_remove2_AB_testListIterator1HasNextAfterPrevNext", testListIteratorHasNext(null,getListIteratorAfterCallItrItr(getListIteratorAfterCall(ABC_remove2_AB(), "previous", 1, null), "next", null, null), null, Result.True));
			printTest("ABC_remove2_AB_testListIterator1NextAfterPrevNext", testListIteratorNext(null,getListIteratorAfterCallItrItr(getListIteratorAfterCall(ABC_remove2_AB(), "previous", 1, null), "next", null, null), null, ELEMENT_B, Result.MatchingValue));
			printTest("ABC_remove2_AB_testListIterator1NextIndexAfterPrevNext", testListIteratorNextIndex(null,getListIteratorAfterCallItrItr(getListIteratorAfterCall(ABC_remove2_AB(), "previous", 1, null), "next", null, null), null, 1, Result.MatchingValue));
			printTest("ABC_remove2_AB_testListIterator1HasPreviousAfterPrevNext", testListIteratorHasPrevious(null,getListIteratorAfterCallItrItr(getListIteratorAfterCall(ABC_remove2_AB(), "previous", 1, null), "next", null, null), null, Result.True));
			printTest("ABC_remove2_AB_testListIterator1PreviousAfterPrevNext", testListIteratorPrevious(null,getListIteratorAfterCallItrItr(getListIteratorAfterCall(ABC_remove2_AB(), "previous", 1, null), "next", null, null), null, ELEMENT_A, Result.MatchingValue));
			printTest("ABC_remove2_AB_testListIterator1PreviousIndexAfterPrevNext", testListIteratorPreviousIndex(null,getListIteratorAfterCallItrItr(getListIteratorAfterCall(ABC_remove2_AB(), "previous", 1, null), "next", null, null), null, 0, Result.MatchingValue));
			printTest("ABC_remove2_AB_testListIterator1RemoveAfterPrevNext", testListIteratorRemove(null,getListIteratorAfterCallItrItr(getListIteratorAfterCall(ABC_remove2_AB(), "previous", 1, null), "next", null, null), null, Result.NoException));
			printTest("ABC_remove2_AB_testListIterator1RealRemoveAfterPrevNext", testListIteratorRealRemoveAfterNext(ABC_remove2_AB(),null, 1, "[2]", Result.MatchingValue));
			printTest("ABC_remove2_AB_testListIterator1SetAfterPrevNext", testListIteratorSet(null, getListIteratorAfterCallItrItr(getListIteratorAfterCall(ABC_remove2_AB(), "previous", 1, null), "next", null, null),null, ELEMENT_D, Result.NoException));
			printTest("ABC_remove2_AB_testListIterator1RealSetAfterPrevNext", testListIteratorRealSetAfterNext(ABC_remove2_AB(), null, 1, ELEMENT_D, "[4, 2]", Result.MatchingValue));
			printTest("ABC_remove2_AB_testListIterator1AddAfterPrevNext", testListIteratorAdd(null,getListIteratorAfterCallItrItr(getListIteratorAfterCall(ABC_remove2_AB(), "previous", 1, null), "next", null, null), null, ELEMENT_D, Result.NoException));
			printTest("ABC_remove2_AB_testListIterator1RealAddAfterPrevNext", testListIteratorRealAddAfterNext(ABC_remove2_AB(), null, 1, ELEMENT_D, "[1, 4, 2]", Result.MatchingValue));
		
			printTest("ABC_remove2_AB_testListIterator1HasNextAfterPrev2Next", testListIteratorHasNext(null,getListIteratorAfterCallItrItr(getListIteratorAfterCallItrItr(getListIteratorAfterCall(ABC_remove2_AB(), "previous", 1, null), "next", null, null), "next", null, null), 1, Result.False));
			printTest("ABC_remove2_AB_testListIterator1NextAfterPrev2Next", testListIteratorNext(null,getListIteratorAfterCallItrItr(getListIteratorAfterCallItrItr(getListIteratorAfterCall(ABC_remove2_AB(), "previous", 1, null), "next", null, null), "next", null, null), 1, null, Result.NoSuchElement));
			printTest("ABC_remove2_AB_testListIterator1NextIndexAfterPrev2Next", testListIteratorNextIndex(null,getListIteratorAfterCallItrItr(getListIteratorAfterCallItrItr(getListIteratorAfterCall(ABC_remove2_AB(), "previous", 1, null), "next", null, null), "next", null, null), 1, 2, Result.MatchingValue));
			printTest("ABC_remove2_AB_testListIterator1HasPreviousAfterPrev2Next", testListIteratorHasPrevious(null,getListIteratorAfterCallItrItr(getListIteratorAfterCallItrItr(getListIteratorAfterCall(ABC_remove2_AB(), "previous", 1, null), "next", null, null), "next", null, null), 1, Result.True));
			printTest("ABC_remove2_AB_testListIterator1PreviousAfterPrev2Next", testListIteratorPrevious(null,getListIteratorAfterCallItrItr(getListIteratorAfterCallItrItr(getListIteratorAfterCall(ABC_remove2_AB(), "previous", 1, null), "next", null, null), "next", null, null), 1, ELEMENT_B, Result.MatchingValue));
			printTest("ABC_remove2_AB_testListIterator1PreviousIndexAfterPrev2Next", testListIteratorPreviousIndex(null,getListIteratorAfterCallItrItr(getListIteratorAfterCallItrItr(getListIteratorAfterCall(ABC_remove2_AB(), "previous", 1, null), "next", null, null), "next", null, null), 1, 1, Result.MatchingValue));
			printTest("ABC_remove2_AB_testListIterator1RemoveAfterPrev2Next", testListIteratorRemove(null,getListIteratorAfterCallItrItr(getListIteratorAfterCallItrItr(getListIteratorAfterCall(ABC_remove2_AB(), "previous", 1, null), "next", null, null), "next", null, null), 1, Result.NoException));
			printTest("ABC_remove2_AB_testListIterator1RealRemoveAfterPrev2Next", testListIteratorRealRemoveAfterNext(ABC_remove2_AB(),1, 1, "[1]", Result.MatchingValue));
			printTest("ABC_remove2_AB_testListIterator1SetAfterPrev2Next", testListIteratorSet(null, getListIteratorAfterCallItrItr(getListIteratorAfterCallItrItr(getListIteratorAfterCall(ABC_remove2_AB(), "previous", 1, null), "next", null, null), "next", null, null),1, ELEMENT_D, Result.NoException));
			printTest("ABC_remove2_AB_testListIterator1RealSetAfterPrev2Next", testListIteratorRealSetAfterNext(ABC_remove2_AB(), 1, 1, ELEMENT_D, "[1, 4]", Result.MatchingValue));
			printTest("ABC_remove2_AB_testListIterator1AddAfterPrev2Next", testListIteratorAdd(null,getListIteratorAfterCallItrItr(getListIteratorAfterCallItrItr(getListIteratorAfterCall(ABC_remove2_AB(), "previous", 1, null), "next", null, null), "next", null, null), null, ELEMENT_D, Result.NoException));
			printTest("ABC_remove2_AB_testListIterator1RealAddAfterPrev2Next", testListIteratorRealAddAfterNext(ABC_remove2_AB(), 1, 1, ELEMENT_D, "[1, 2, 4]", Result.MatchingValue));
			}
		} catch (Exception e) {
			System.out.printf("***UNABLE TO RUN/COMPLETE %s***\n", "test_ABC_remove2_AB");
			e.printStackTrace();
		}
	}

	//////////////////////////////////////
	// SCENARIO: [A,B,C] -> set(0,D) -> [D,B,C]
	//  XXX SCENARIO 40
	//////////////////////////////////////
	private ListADT<Integer> ABC_set0D_DBC() {
		// cast to UnorderedListADT to make addToFront() available
		IndexedListADT<Integer> list = (IndexedListADT<Integer>) newList(); 
		list.add(0,ELEMENT_A);
		list.add(1, ELEMENT_B);
		list.add(2,ELEMENT_C);
		list.set(0, ELEMENT_D);
		return list;
	}
	private void test_ABC_set0D_DBC(){
		try {
			// ListADT
			printTest("ABC_set0D_DBC_testRemoveFirst", testRemoveFirst(ABC_set0D_DBC(), ELEMENT_D, Result.MatchingValue));
			printTest("ABC_set0D_DBC_testRemoveLast", testRemoveLast(ABC_set0D_DBC(), ELEMENT_C, Result.MatchingValue));
			printTest("ABC_set0D_DBC_testRemoveA", testRemoveElement(ABC_set0D_DBC(), ELEMENT_D, Result.MatchingValue));
			printTest("ABC_set0D_DBC_testRemoveB", testRemoveElement(ABC_set0D_DBC(), ELEMENT_B, Result.MatchingValue));
			printTest("ABC_set0D_DBC_testRemoveC", testRemoveElement(ABC_set0D_DBC(), ELEMENT_C, Result.MatchingValue));
			printTest("ABC_set0D_DBC_testRemoveD", testRemoveElement(ABC_set0D_DBC(), ELEMENT_A, Result.ElementNotFound));
			printTest("ABC_set0D_DBC_testFirst", testFirst(ABC_set0D_DBC(), ELEMENT_D, Result.MatchingValue));
			printTest("ABC_set0D_DBC_testLast", testLast(ABC_set0D_DBC(), ELEMENT_C, Result.MatchingValue));
			printTest("ABC_set0D_DBC_testContainsA", testContains(ABC_set0D_DBC(), ELEMENT_D, Result.True));
			printTest("ABC_set0D_DBC_testContainsB", testContains(ABC_set0D_DBC(), ELEMENT_B, Result.True));
			printTest("ABC_set0D_DBC_testContainsC", testContains(ABC_set0D_DBC(), ELEMENT_C, Result.True));
			printTest("ABC_set0D_DBC_testContainsD", testContains(ABC_set0D_DBC(), ELEMENT_A, Result.False));
			printTest("ABC_set0D_DBC_testIsEmpty", testIsEmpty(ABC_set0D_DBC(), Result.False));
			printTest("ABC_set0D_DBC_testSize", testSize(ABC_set0D_DBC(), 3));
			printTest("ABC_set0D_DBC_testToString", testToString(ABC_set0D_DBC(), Result.ValidString));
			// UnorderedListADT
			printTest("ABC_set0D_DBC_testAddToFrontB", testAddToFront(ABC_set0D_DBC(), ELEMENT_D, Result.NoException));
			printTest("ABC_set0D_DBC_testAddToRearB", testAddToRear(ABC_set0D_DBC(), ELEMENT_D, Result.NoException));
			printTest(	"ABC_set0D_DBC_testAddAfterAD", testAddAfter(ABC_set0D_DBC(), ELEMENT_D, ELEMENT_A, Result.NoException));
			printTest(	"ABC_set0D_DBC_testAddAfterBD", testAddAfter(ABC_set0D_DBC(), ELEMENT_B, ELEMENT_A, Result.NoException));
			printTest(	"ABC_set0D_DBC_testAddAfterCD", testAddAfter(ABC_set0D_DBC(), ELEMENT_C, ELEMENT_A, Result.NoException));
			printTest(	"ABC_set0D_DBC_testAddAfterDD", testAddAfter(ABC_set0D_DBC(), ELEMENT_A, ELEMENT_A, Result.ElementNotFound));
			// IndexedListADT
			printTest("ABC_set0D_DBC_testAddAtIndexNeg1D", testAddAtIndex(ABC_set0D_DBC(), -1, ELEMENT_A, Result.IndexOutOfBounds));
			printTest("ABC_set0D_DBC_testAddAtIndex0D", testAddAtIndex(ABC_set0D_DBC(), 0, ELEMENT_A, Result.NoException));
			printTest("ABC_set0D_DBC_testAddAtIndex1D", testAddAtIndex(ABC_set0D_DBC(), 1, ELEMENT_A, Result.NoException));
			printTest("ABC_set0D_DBC_testAddAtIndex2D", testAddAtIndex(ABC_set0D_DBC(), 2, ELEMENT_A, Result.NoException));
			printTest("ABC_set0D_DBC_testAddAtIndex3D", testAddAtIndex(ABC_set0D_DBC(), 3, ELEMENT_A, Result.NoException));
			printTest("ABC_set0D_DBC_testAddAtIndex4D", testAddAtIndex(ABC_set0D_DBC(), 4, ELEMENT_A, Result.IndexOutOfBounds));
			printTest("ABC_set0D_DBC_testSetNeg1D", testSet(ABC_set0D_DBC(), -1, ELEMENT_A, Result.IndexOutOfBounds));
			printTest("ABC_set0D_DBC_testSet0D", testSet(ABC_set0D_DBC(), 0, ELEMENT_A, Result.NoException));
			printTest("ABC_set0D_DBC_testSet1D", testSet(ABC_set0D_DBC(), 1, ELEMENT_A, Result.NoException));
			printTest("ABC_set0D_DBC_testSet2D", testSet(ABC_set0D_DBC(), 2, ELEMENT_A, Result.NoException));
			printTest("ABC_set0D_DBC_testSet3D", testSet(ABC_set0D_DBC(), 3, ELEMENT_A, Result.IndexOutOfBounds));
			printTest("ABC_set0D_DBC_testAddD", testAdd(ABC_set0D_DBC(), ELEMENT_A, Result.NoException));
			printTest("ABC_set0D_DBC_testGetNeg1", testGet(ABC_set0D_DBC(), -1, null, Result.IndexOutOfBounds));
			printTest("ABC_set0D_DBC_testGet0", testGet(ABC_set0D_DBC(), 0, ELEMENT_D, Result.MatchingValue));
			printTest("ABC_set0D_DBC_testGet1", testGet(ABC_set0D_DBC(), 1, ELEMENT_B, Result.MatchingValue));
			printTest("ABC_set0D_DBC_testGet2", testGet(ABC_set0D_DBC(), 2, ELEMENT_C, Result.MatchingValue));
			printTest("ABC_set0D_DBC_testGet3", testGet(ABC_set0D_DBC(), 3, null, Result.IndexOutOfBounds));
			printTest("ABC_set0D_DBC_testIndexOfA", testIndexOf(ABC_set0D_DBC(), ELEMENT_D, 0));
			printTest("ABC_set0D_DBC_testIndexOfB", testIndexOf(ABC_set0D_DBC(), ELEMENT_B, 1));
			printTest("ABC_set0D_DBC_testIndexOfC", testIndexOf(ABC_set0D_DBC(), ELEMENT_C, 2));
			printTest("ABC_set0D_DBC_testIndexOfD", testIndexOf(ABC_set0D_DBC(), ELEMENT_A, -1));
			printTest("ABC_set0D_DBC_testRemoveNeg1", testRemoveIndex(ABC_set0D_DBC(), -1, null, Result.IndexOutOfBounds));
			printTest("ABC_set0D_DBC_testRemove0", testRemoveIndex(ABC_set0D_DBC(), 0, ELEMENT_D, Result.MatchingValue));
			printTest("ABC_set0D_DBC_testRemove0", testRemoveIndex(ABC_set0D_DBC(), 1, ELEMENT_B, Result.MatchingValue));
			printTest("ABC_set0D_DBC_testRemove0", testRemoveIndex(ABC_set0D_DBC(), 2, ELEMENT_C, Result.MatchingValue));
			printTest("ABC_set0D_DBC_testRemove3", testRemoveIndex(ABC_set0D_DBC(), 3, null, Result.IndexOutOfBounds));
			// Iterator
			printTest("ABC_set0D_DBC_testIterator", testIterator(ABC_set0D_DBC(), Result.NoException));
			printTest("ABC_set0D_DBC_testIteratorHasNext", testIteratorHasNext(ABC_set0D_DBC().iterator(), Result.True));
			printTest("ABC_set0D_DBC_testIteratorNext", testIteratorNext(ABC_set0D_DBC().iterator(), ELEMENT_D, Result.MatchingValue));
			printTest("ABC_set0D_DBC_testIteratorRemove", testIteratorRemove(ABC_set0D_DBC().iterator(), Result.IllegalState));
			printTest("ABC_set0D_DBC_testIteratorHasNextAfterNext", testIteratorHasNext(iteratorAfterNext(ABC_set0D_DBC(),1), Result.True));
			printTest("ABC_set0D_DBC_testIteratorNextAfterNext", testIteratorNext(iteratorAfterNext(ABC_set0D_DBC(),1), ELEMENT_B, Result.MatchingValue));
			printTest("ABC_set0D_DBC_testIteratorRemoveAfterNext", testIteratorRemove(iteratorAfterNext(ABC_set0D_DBC(),1), Result.NoException));
			printTest("ABC_set0D_DBC_testIteratorHasNextAfterNext2", testIteratorHasNext(iteratorAfterNext(ABC_set0D_DBC(),2), Result.True));
			printTest("ABC_set0D_DBC_testIteratorHasNextAfterNext3", testIteratorHasNext(iteratorAfterNext(ABC_set0D_DBC(),3), Result.False));
			printTest("ABC_set0D_DBC_testIteratorNextAfterNext2", testIteratorNext(iteratorAfterNext(ABC_set0D_DBC(),2), ELEMENT_C, Result.MatchingValue));
			printTest("ABC_set0D_DBC_testIteratorNextAfterNext3", testIteratorNext(iteratorAfterNext(ABC_set0D_DBC(),3), null, Result.NoSuchElement));
			printTest("ABC_set0D_DBC_testIteratorRemoveAfterNext2", testIteratorRemove(iteratorAfterNext(ABC_set0D_DBC(),2), Result.NoException));
			printTest("ABC_set0D_DBC_testIteratorRemoveAfterNext3", testIteratorRemove(iteratorAfterNext(ABC_set0D_DBC(),3), Result.NoException));
			printTest("ABC_set0D_DBC_testIteratorNextAfterRemoveNext", testIteratorNext(iteratorAfterRemove(iteratorAfterNext(ABC_set0D_DBC(),1)),ELEMENT_B,  Result.MatchingValue));
			printTest("ABC_set0D_DBC_testIteratorNextAfterRemoveNext2", testIteratorNext(iteratorAfterRemove(iteratorAfterNext(ABC_set0D_DBC(),2)),ELEMENT_C,  Result.MatchingValue));
			printTest("ABC_set0D_DBC_testIteratorNextAfterRemoveNext3", testIteratorNext(iteratorAfterRemove(iteratorAfterNext(ABC_set0D_DBC(),3)),null,  Result.NoSuchElement));
			printTest("ABC_set0D_DBC_testIteratorHasNextAfterRemoveNext",testIteratorHasNext(iteratorAfterRemove(iteratorAfterNext(ABC_set0D_DBC(),1)),Result.True));
			printTest("ABC_set0D_DBC_testIteratorHasNextAfterRemoveNext2",testIteratorHasNext(iteratorAfterRemove(iteratorAfterNext(ABC_set0D_DBC(),2)),Result.True));
			printTest("ABC_set0D_DBC_testIteratorHasNextAfterRemoveNext3",testIteratorHasNext(iteratorAfterRemove(iteratorAfterNext(ABC_set0D_DBC(),3)),Result.False));
			printTest("ABC_set0D_DBC_iteratorTestRealRemove",testIteratorRealRemove(ABC_set0D_DBC(), 1, "[2, 3]", Result.MatchingValue));
			printTest("ABC_set0D_DBC_iteratorTestRealRemove2",testIteratorRealRemove(ABC_set0D_DBC(), 2, "[4, 3]", Result.MatchingValue));
			printTest("ABC_set0D_DBC_iteratorTestRealRemove3",testIteratorRealRemove(ABC_set0D_DBC(), 3, "[4, 2]", Result.MatchingValue));
		} catch (Exception e) {
			System.out.printf("***UNABLE TO RUN/COMPLETE %s***\n", "test_ABC_set0D_DBC");
			e.printStackTrace();
		}
	}

	//////////////////////////////////////
	// SCENARIO: [A,B,C] -> set(1,D) -> [A,D,C]
	//  XXX SCENARIO 41
	//////////////////////////////////////
	private ListADT<Integer> ABC_set1D_ADC() {
		// cast to UnorderedListADT to make addToFront() available
		IndexedListADT<Integer> list = (IndexedListADT<Integer>) newList(); 
		list.add(0,ELEMENT_A);
		list.add(1, ELEMENT_B);
		list.add(2,ELEMENT_C);
		list.set(1, ELEMENT_D);
		return list;
	}
	private void test_ABC_set1D_ADC(){
		try {
			// ListADT
			printTest("ABC_set1D_ADC_testRemoveFirst", testRemoveFirst(ABC_set1D_ADC(), ELEMENT_A, Result.MatchingValue));
			printTest("ABC_set1D_ADC_testRemoveLast", testRemoveLast(ABC_set1D_ADC(), ELEMENT_C, Result.MatchingValue));
			printTest("ABC_set1D_ADC_testRemoveA", testRemoveElement(ABC_set1D_ADC(), ELEMENT_A, Result.MatchingValue));
			printTest("ABC_set1D_ADC_testRemoveB", testRemoveElement(ABC_set1D_ADC(), ELEMENT_D, Result.MatchingValue));
			printTest("ABC_set1D_ADC_testRemoveC", testRemoveElement(ABC_set1D_ADC(), ELEMENT_C, Result.MatchingValue));
			printTest("ABC_set1D_ADC_testRemoveD", testRemoveElement(ABC_set1D_ADC(), ELEMENT_B, Result.ElementNotFound));
			printTest("ABC_set1D_ADC_testFirst", testFirst(ABC_set1D_ADC(), ELEMENT_A, Result.MatchingValue));
			printTest("ABC_set1D_ADC_testLast", testLast(ABC_set1D_ADC(), ELEMENT_C, Result.MatchingValue));
			printTest("ABC_set1D_ADC_testContainsA", testContains(ABC_set1D_ADC(), ELEMENT_A, Result.True));
			printTest("ABC_set1D_ADC_testContainsB", testContains(ABC_set1D_ADC(), ELEMENT_D, Result.True));
			printTest("ABC_set1D_ADC_testContainsC", testContains(ABC_set1D_ADC(), ELEMENT_C, Result.True));
			printTest("ABC_set1D_ADC_testContainsD", testContains(ABC_set1D_ADC(), ELEMENT_B, Result.False));
			printTest("ABC_set1D_ADC_testIsEmpty", testIsEmpty(ABC_set1D_ADC(), Result.False));
			printTest("ABC_set1D_ADC_testSize", testSize(ABC_set1D_ADC(), 3));
			printTest("ABC_set1D_ADC_testToString", testToString(ABC_set1D_ADC(), Result.ValidString));
			// UnorderedListADT
			printTest("ABC_set1D_ADC_testAddToFrontB", testAddToFront(ABC_set1D_ADC(), ELEMENT_D, Result.NoException));
			printTest("ABC_set1D_ADC_testAddToRearB", testAddToRear(ABC_set1D_ADC(), ELEMENT_D, Result.NoException));
			printTest(	"ABC_set1D_ADC_testAddAfterAD", testAddAfter(ABC_set1D_ADC(), ELEMENT_A, ELEMENT_B, Result.NoException));
			printTest(	"ABC_set1D_ADC_testAddAfterBD", testAddAfter(ABC_set1D_ADC(), ELEMENT_D, ELEMENT_B, Result.NoException));
			printTest(	"ABC_set1D_ADC_testAddAfterCD", testAddAfter(ABC_set1D_ADC(), ELEMENT_C, ELEMENT_B, Result.NoException));
			printTest(	"ABC_set1D_ADC_testAddAfterDD", testAddAfter(ABC_set1D_ADC(), ELEMENT_B, ELEMENT_B, Result.ElementNotFound));
			// IndexedListADT
			printTest("ABC_set1D_ADC_testAddAtIndexNeg1D", testAddAtIndex(ABC_set1D_ADC(), -1, ELEMENT_B, Result.IndexOutOfBounds));
			printTest("ABC_set1D_ADC_testAddAtIndex0D", testAddAtIndex(ABC_set1D_ADC(), 0, ELEMENT_B, Result.NoException));
			printTest("ABC_set1D_ADC_testAddAtIndex1D", testAddAtIndex(ABC_set1D_ADC(), 1, ELEMENT_B, Result.NoException));
			printTest("ABC_set1D_ADC_testAddAtIndex2D", testAddAtIndex(ABC_set1D_ADC(), 2, ELEMENT_B, Result.NoException));
			printTest("ABC_set1D_ADC_testAddAtIndex3D", testAddAtIndex(ABC_set1D_ADC(), 3, ELEMENT_B, Result.NoException));
			printTest("ABC_set1D_ADC_testAddAtIndex4D", testAddAtIndex(ABC_set1D_ADC(), 4, ELEMENT_B, Result.IndexOutOfBounds));
			printTest("ABC_set1D_ADC_testSetNeg1D", testSet(ABC_set1D_ADC(), -1, ELEMENT_B, Result.IndexOutOfBounds));
			printTest("ABC_set1D_ADC_testSet0D", testSet(ABC_set1D_ADC(), 0, ELEMENT_B, Result.NoException));
			printTest("ABC_set1D_ADC_testSet1D", testSet(ABC_set1D_ADC(), 1, ELEMENT_B, Result.NoException));
			printTest("ABC_set1D_ADC_testSet2D", testSet(ABC_set1D_ADC(), 2, ELEMENT_B, Result.NoException));
			printTest("ABC_set1D_ADC_testSet3D", testSet(ABC_set1D_ADC(), 3, ELEMENT_B, Result.IndexOutOfBounds));
			printTest("ABC_set1D_ADC_testAddD", testAdd(ABC_set1D_ADC(), ELEMENT_B, Result.NoException));
			printTest("ABC_set1D_ADC_testGetNeg1", testGet(ABC_set1D_ADC(), -1, null, Result.IndexOutOfBounds));
			printTest("ABC_set1D_ADC_testGet0", testGet(ABC_set1D_ADC(), 0, ELEMENT_A, Result.MatchingValue));
			printTest("ABC_set1D_ADC_testGet1", testGet(ABC_set1D_ADC(), 1, ELEMENT_D, Result.MatchingValue));
			printTest("ABC_set1D_ADC_testGet2", testGet(ABC_set1D_ADC(), 2, ELEMENT_C, Result.MatchingValue));
			printTest("ABC_set1D_ADC_testGet3", testGet(ABC_set1D_ADC(), 3, null, Result.IndexOutOfBounds));
			printTest("ABC_set1D_ADC_testIndexOfA", testIndexOf(ABC_set1D_ADC(), ELEMENT_A, 0));
			printTest("ABC_set1D_ADC_testIndexOfB", testIndexOf(ABC_set1D_ADC(), ELEMENT_D, 1));
			printTest("ABC_set1D_ADC_testIndexOfC", testIndexOf(ABC_set1D_ADC(), ELEMENT_C, 2));
			printTest("ABC_set1D_ADC_testIndexOfD", testIndexOf(ABC_set1D_ADC(), ELEMENT_B, -1));
			printTest("ABC_set1D_ADC_testRemoveNeg1", testRemoveIndex(ABC_set1D_ADC(), -1, null, Result.IndexOutOfBounds));
			printTest("ABC_set1D_ADC_testRemove0", testRemoveIndex(ABC_set1D_ADC(), 0, ELEMENT_A, Result.MatchingValue));
			printTest("ABC_set1D_ADC_testRemove1", testRemoveIndex(ABC_set1D_ADC(), 1, ELEMENT_D, Result.MatchingValue));
			printTest("ABC_set1D_ADC_testRemove2", testRemoveIndex(ABC_set1D_ADC(), 2, ELEMENT_C, Result.MatchingValue));
			printTest("ABC_set1D_ADC_testRemove3", testRemoveIndex(ABC_set1D_ADC(), 3, null, Result.IndexOutOfBounds));
			// Iterator
			printTest("ABC_set1D_ADC_testIterator", testIterator(ABC_set1D_ADC(), Result.NoException));
			printTest("ABC_set1D_ADC_testIteratorHasNext", testIteratorHasNext(ABC_set1D_ADC().iterator(), Result.True));
			printTest("ABC_set1D_ADC_testIteratorNext", testIteratorNext(ABC_set1D_ADC().iterator(), ELEMENT_A, Result.MatchingValue));
			printTest("ABC_set1D_ADC_testIteratorRemove", testIteratorRemove(ABC_set1D_ADC().iterator(), Result.IllegalState));
			printTest("ABC_set1D_ADC_testIteratorHasNextAfterNext", testIteratorHasNext(iteratorAfterNext(ABC_set1D_ADC(),1), Result.True));
			printTest("ABC_set1D_ADC_testIteratorNextAfterNext", testIteratorNext(iteratorAfterNext(ABC_set1D_ADC(),1), ELEMENT_D, Result.MatchingValue));
			printTest("ABC_set1D_ADC_testIteratorRemoveAfterNext", testIteratorRemove(iteratorAfterNext(ABC_set1D_ADC(),1), Result.NoException));
			printTest("ABC_set1D_ADC_testIteratorHasNextAfterNext2", testIteratorHasNext(iteratorAfterNext(ABC_set1D_ADC(),2), Result.True));
			printTest("ABC_set1D_ADC_testIteratorHasNextAfterNext3", testIteratorHasNext(iteratorAfterNext(ABC_set1D_ADC(),3), Result.False));
			printTest("ABC_set1D_ADC_testIteratorNextAfterNext2", testIteratorNext(iteratorAfterNext(ABC_set1D_ADC(),2), ELEMENT_C, Result.MatchingValue));
			printTest("ABC_set1D_ADC_testIteratorNextAfterNext3", testIteratorNext(iteratorAfterNext(ABC_set1D_ADC(),3), null, Result.NoSuchElement));
			printTest("ABC_set1D_ADC_testIteratorRemoveAfterNext2", testIteratorRemove(iteratorAfterNext(ABC_set1D_ADC(),2), Result.NoException));
			printTest("ABC_set1D_ADC_testIteratorRemoveAfterNext3", testIteratorRemove(iteratorAfterNext(ABC_set1D_ADC(),3), Result.NoException));
			printTest("ABC_set1D_ADC_testIteratorNextAfterRemoveNext", testIteratorNext(iteratorAfterRemove(iteratorAfterNext(ABC_set1D_ADC(),1)),ELEMENT_D,  Result.MatchingValue));
			printTest("ABC_set1D_ADC_testIteratorNextAfterRemoveNext2", testIteratorNext(iteratorAfterRemove(iteratorAfterNext(ABC_set1D_ADC(),2)),ELEMENT_C,  Result.MatchingValue));
			printTest("ABC_set1D_ADC_testIteratorNextAfterRemoveNext3", testIteratorNext(iteratorAfterRemove(iteratorAfterNext(ABC_set1D_ADC(),3)),null,  Result.NoSuchElement));
			printTest("ABC_set1D_ADC_testIteratorHasNextAfterRemoveNext",testIteratorHasNext(iteratorAfterRemove(iteratorAfterNext(ABC_set1D_ADC(),1)),Result.True));
			printTest("ABC_set1D_ADC_testIteratorHasNextAfterRemoveNext2",testIteratorHasNext(iteratorAfterRemove(iteratorAfterNext(ABC_set1D_ADC(),2)),Result.True));
			printTest("ABC_set1D_ADC_testIteratorHasNextAfterRemoveNext3",testIteratorHasNext(iteratorAfterRemove(iteratorAfterNext(ABC_set1D_ADC(),3)),Result.False));
			printTest("ABC_set1D_ADC_iteratorTestRealRemove",testIteratorRealRemove(ABC_set1D_ADC(), 1, "[4, 3]", Result.MatchingValue));
			printTest("ABC_set1D_ADC_iteratorTestRealRemove2",testIteratorRealRemove(ABC_set1D_ADC(), 2, "[1, 3]", Result.MatchingValue));
			printTest("ABC_set1D_ADC_iteratorTestRealRemove3",testIteratorRealRemove(ABC_set1D_ADC(), 3, "[1, 4]", Result.MatchingValue));
		} catch (Exception e) {
			System.out.printf("***UNABLE TO RUN/COMPLETE %s***\n", "test_ABC_set1D_ADC");
			e.printStackTrace();
		}
	}

	//////////////////////////////////////
	// SCENARIO: [A,B,C] -> set(2,D) -> [A,B,D]
	//  XXX SCENARIO 42
	//////////////////////////////////////
	private ListADT<Integer> ABC_set2D_ABD() {
		// cast to UnorderedListADT to make addToFront() available
		IndexedListADT<Integer> list = (IndexedListADT<Integer>) newList(); 
		list.add(0,ELEMENT_A);
		list.add(1, ELEMENT_B);
		list.add(2,ELEMENT_C);
		list.set(2, ELEMENT_D);
		return list;
	}
	private void test_ABC_2D_ABD(){
		
	}
	
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
			@SuppressWarnings("unused")
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
		Iterator<Integer> it = list.iterator();
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
	/**
	 * Helper for testing iterators. Compares the output of toString() after the remove method has been called to the expected output string.
	 * @param list
	 * @param numCallsToNext
	 * @param expectedToString
	 * @param expectedResult
	 * @return
	 */
	private boolean testIteratorRealRemove(ListADT<Integer> list, int numCallsToNext, String expectedToString, Result expectedResult){
		Iterator<Integer> it=iteratorAfterNext(list, numCallsToNext);
		it.remove();
		String realOutput=list.toString();
		Result result=null;
		try{
			for(int i=0; i<expectedToString.length(); i++){
				if(realOutput.charAt(i)!=expectedToString.charAt(i)){
					result=Result.Fail;
				}
			}
			if(result!=Result.Fail){
				result=Result.MatchingValue;
			}
		}catch (Exception e){
			result=Result.UnexpectedException;
			System.out.println(realOutput);
		}
		return result==expectedResult;
	}
	/**
	 * Helper for testing ListIterators calls the ListIterator() constructor
	 * @param list
	 * @param expectedResult
	 * @return
	 */
	private boolean testListIteratorUnIndexed(ListADT<Integer> list, Result expectedResult) {
		DoubleLinkedList<Integer> listDLL = new DoubleLinkedList<Integer>();
		Result result;
		try {
			@SuppressWarnings("unused")
			ListIterator<Integer> it = listDLL.listIterator();
			result = Result.NoException;
		} catch (Exception e) {
			System.out.printf("%s caught unexpected %s\n", "testIterator", e.toString());
			e.printStackTrace();
			result = Result.UnexpectedException;
		}
		return result == expectedResult;
	}
	/**
	 * Helper for testing ListIterators calls the ListIterator(index) constructor
	 * @param list
	 * @param index
	 * @param expectedResult
	 * @return
	 */
	private boolean testListIteratorIndexed(ListADT<Integer> list,Integer index, Result expectedResult) {
		DoubleLinkedList<Integer> listDLL = (DoubleLinkedList<Integer>) list;
		Result result;
		try {
			@SuppressWarnings("unused")
			ListIterator<Integer> it = listDLL.listIterator(index);
			result = Result.NoException;
		} catch(IndexOutOfBoundsException e){
			result=Result.IndexOutOfBounds;
		}catch (Exception e) {
			System.out.printf("%s caught unexpected %s\n", "testIterator", e.toString());
			e.printStackTrace();
			result = Result.UnexpectedException;
		}
		return result == expectedResult;
	}
	/**
	 * Helper method for testing ListIterators, this will just call a ListIterator method but not test if its working.
	 * @param list
	 * @param methodToCall
	 * @param startIndex
	 * @param addObject
	 * @return
	 */
	private ListIterator<Integer> getListIteratorAfterCall(ListADT<Integer> list, String methodToCall, Integer startIndex,Integer addObject){
		DoubleLinkedList<Integer> listDLL;
		ListIterator<Integer> itDLL;
		if(startIndex==null){
			listDLL=(DoubleLinkedList<Integer>) list;
			itDLL=listDLL.listIterator();
			switch(methodToCall){
			case "hasNext":
				itDLL.hasNext();
			case "next":
				itDLL.next();
				break;
			case "hasPrevious":
				itDLL.hasPrevious();
				break;
			case "revious":
				itDLL.previous();
				break;
			case "nextIndex":
				itDLL.nextIndex();
				break;
			case "previousIndex":
				itDLL.previousIndex();
				break;
			case "remove":
				itDLL.remove();
				break;
			case "set":
				itDLL.set((Integer) addObject);
				break;
			case "add":
				itDLL.add((Integer) addObject);
				break;
			}
		}else{
			listDLL= (DoubleLinkedList<Integer>) list;
			itDLL=listDLL.listIterator(startIndex);
			switch(methodToCall){
			case "hasNext":
				itDLL.hasNext();
			case "next":
				itDLL.next();
				break;
			case "hasPrevious":
				itDLL.hasPrevious();
				break;
			case "previous":
				itDLL.previous();
				break;
			case "nextIndex":
				itDLL.nextIndex();
				break;
			case "previousIndex":
				itDLL.previousIndex();
				break;
			case "remove":
				itDLL.remove();
				break;
			case "set":
				itDLL.set((Integer) addObject);
				break;
			case "add":
				itDLL.add((Integer) addObject);
				break;
			}
		}
		return itDLL;
	}
	private ListIterator<Integer> getListIteratorAfterCallItrItr(ListIterator<Integer> list, String methodToCall, Integer startIndex,Integer addObject){
		if(startIndex==null){
			switch(methodToCall){
			case "hasNext":
				list.hasNext();
			case "next":
				list.next();
				break;
			case "hasPrevious":
				list.hasPrevious();
				break;
			case "revious":
				list.previous();
				break;
			case "nextIndex":
				list.nextIndex();
				break;
			case "previousIndex":
				list.previousIndex();
				break;
			case "remove":
				list.remove();
				break;
			case "set":
				list.set((Integer) addObject);
				break;
			case "add":
				list.add((Integer) addObject);
				break;
			}
		}else{
			switch(methodToCall){
			case "hasNext":
				list.hasNext();
			case "next":
				list.next();
				break;
			case "hasPrevious":
				list.hasPrevious();
				break;
			case "previous":
				list.previous();
				break;
			case "nextIndex":
				list.nextIndex();
				break;
			case "previousIndex":
				list.previousIndex();
				break;
			case "remove":
				list.remove();
				break;
			case "set":
				list.set((Integer) addObject);
				break;
			case "add":
				list.add((Integer) addObject);
				break;
			}
		}
		return list;
	}
	/**
	 * Helper method for ListIterators, tests the hasNext() method
	 * @param list
	 * @param startIndex
	 * @param expectedResult
	 * @return
	 */
	private boolean testListIteratorHasNext(ListADT<Integer> list, ListIterator<Integer> itr, Integer startIndex, Result expectedResult){
		DoubleLinkedList<Integer> listDLL;
		ListIterator<Integer> itDLL;
		Result result;
		if(list==null){
			itDLL=itr;
			try{
				if(itDLL.hasNext()){
					result=Result.True;
				}else{
					result=Result.False;
				}
			}catch(ConcurrentModificationException e){
				result=Result.ConcurrentModification;
			}catch (Exception e) {
				System.out.printf("%s caught unexpected %s\n", "testListteratorHasNext", e.toString());
				e.printStackTrace();
				result = Result.UnexpectedException;
			}
		}else if(startIndex==null){
			listDLL=(DoubleLinkedList<Integer>) list;
			itDLL=listDLL.listIterator();
			try{
				if(itDLL.hasNext()){
					result=Result.True;
				}else{
					result=Result.False;
				}
			}catch(ConcurrentModificationException e){
				result=Result.ConcurrentModification;
			}catch (Exception e) {
				System.out.printf("%s caught unexpected %s\n", "testListteratorHasNext", e.toString());
				e.printStackTrace();
				result = Result.UnexpectedException;
			}
			
		}else{
			listDLL= (DoubleLinkedList<Integer>) list;
			itDLL=listDLL.listIterator(startIndex);
			try{
				if(itDLL.hasNext()){
					result=Result.True;
				}else{
					result=Result.False;
				}
			}catch(ConcurrentModificationException e){
				result=Result.ConcurrentModification;
			}catch (Exception e) {
				System.out.printf("%s caught unexpected %s\n", "testListIteratorHasNext", e.toString());
				e.printStackTrace();
				result = Result.UnexpectedException;
			}
		}
		return result==expectedResult;
	}
	/**
	 * Helper method for ListIterators, tests the next() method
	 * @param list
	 * @param startIndex
	 * @param expectedValue
	 * @param expectedResult
	 * @return
	 */
	private boolean testListIteratorNext(ListADT<Integer> list, ListIterator<Integer> itr, Integer startIndex, Integer expectedValue, Result expectedResult){
		DoubleLinkedList<Integer> listDLL;
		ListIterator<Integer> itDLL;
		Result result;
		if(list==null){
			itDLL=itr;
			try {
				Integer retVal = itDLL.next();
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
				System.out.printf("%s caught unexpected %s\n", "testListIteratorNext", e.toString());
				e.printStackTrace();
				result = Result.UnexpectedException;
			}
		}else if(startIndex==null){
			listDLL=(DoubleLinkedList<Integer>) list;
			itDLL=listDLL.listIterator();
			try {
				Integer retVal = itDLL.next();
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
				System.out.printf("%s caught unexpected %s\n", "testListIteratorNext", e.toString());
				e.printStackTrace();
				result = Result.UnexpectedException;
			}
			
		}else{
			listDLL= (DoubleLinkedList<Integer>) list;
			itDLL=listDLL.listIterator(startIndex);
			try {
				Integer retVal = itDLL.next();
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
				System.out.printf("%s caught unexpected %s\n", "testListIteratorNext", e.toString());
				e.printStackTrace();
				result = Result.UnexpectedException;
			}
		}
		return result==expectedResult;
	}
	/**
	 * Helper method for ListIterators, tests the hasPrevious() method
	 * @param list
	 * @param startIndex
	 * @param expectedResult
	 * @return
	 */
	private boolean testListIteratorHasPrevious(ListADT<Integer> list, ListIterator<Integer> itr,Integer startIndex, Result expectedResult){
		DoubleLinkedList<Integer> listDLL;
		ListIterator<Integer> itDLL;
		Result result;
		if(list==null){
			itDLL=itr;
			try{
				if(itDLL.hasPrevious()){
					result=Result.True;
				}else{
					result=Result.False;
				}
			}catch(ConcurrentModificationException e){
				result = Result.ConcurrentModification;
			}catch (Exception e) {
				System.out.printf("%s caught unexpected %s\n", "testListIteratorHasPrevious", e.toString());
				e.printStackTrace();
				result = Result.UnexpectedException;
			}
		}else if(startIndex==null){
			listDLL=(DoubleLinkedList<Integer>) list;
			itDLL=listDLL.listIterator();
			try{
				if(itDLL.hasPrevious()){
					result=Result.True;
				}else{
					result=Result.False;
				}
			}catch(ConcurrentModificationException e){
				result = Result.ConcurrentModification;
			}catch (Exception e) {
				System.out.printf("%s caught unexpected %s\n", "testListIteratorHasPrevious", e.toString());
				e.printStackTrace();
				result = Result.UnexpectedException;
			}
			
		}else{
			listDLL= (DoubleLinkedList<Integer>) list;
			itDLL=listDLL.listIterator(startIndex);
			try{
				if(itDLL.hasPrevious()){
					result=Result.True;
				}else{
					result=Result.False;
				}
			}catch(ConcurrentModificationException e){
				result = Result.ConcurrentModification;
			}catch (Exception e) {
				System.out.printf("%s caught unexpected %s\n", "testListIteratorHasPrevious", e.toString());
				e.printStackTrace();
				result = Result.UnexpectedException;
			}
		}
		return result==expectedResult;
	}
	/**
	 * Helper method for ListIterators, tests the previous() method
	 * @param list
	 * @param startIndex
	 * @param expectedValue
	 * @param expectedResult
	 * @return
	 */
	private boolean testListIteratorPrevious(ListADT<Integer> list, ListIterator<Integer> itr, Integer startIndex, Integer expectedValue, Result expectedResult){
		DoubleLinkedList<Integer> listDLL;
		ListIterator<Integer> itDLL;
		Result result;
		if(list==null){
			itDLL=itr;
			try{
				Integer retVal = itDLL.previous();
				if(retVal.equals(expectedValue)){
					result=Result.MatchingValue;
				}else{
					result=Result.Fail;
				}
			}catch (NoSuchElementException e) {
				result = Result.NoSuchElement;
			} catch (ConcurrentModificationException e) {
				result = Result.ConcurrentModification;
			} catch (Exception e) {
				System.out.printf("%s caught unexpected %s\n", "testListIteratorPrevious", e.toString());
				e.printStackTrace();
				result = Result.UnexpectedException;
			}
		}else if(startIndex==null){
			listDLL=(DoubleLinkedList<Integer>) list;
			itDLL=listDLL.listIterator();
			try{
				Integer retVal = itDLL.previous();
				if(retVal.equals(expectedValue)){
					result=Result.MatchingValue;
				}else{
					result=Result.Fail;
				}
			}catch (NoSuchElementException e) {
				result = Result.NoSuchElement;
			} catch (ConcurrentModificationException e) {
				result = Result.ConcurrentModification;
			} catch (Exception e) {
				System.out.printf("%s caught unexpected %s\n", "testListIteratorPrevious", e.toString());
				e.printStackTrace();
				result = Result.UnexpectedException;
			}
			
		}else{
			listDLL= (DoubleLinkedList<Integer>) list;
			itDLL=listDLL.listIterator(startIndex);
			try{
				Integer retVal = itDLL.previous();
				if(retVal.equals(expectedValue)){
					result=Result.MatchingValue;
				}else{
					result=Result.Fail;
				}
			}catch (NoSuchElementException e) {
				result = Result.NoSuchElement;
			} catch (ConcurrentModificationException e) {
				result = Result.ConcurrentModification;
			} catch (Exception e) {
				System.out.printf("%s caught unexpected %s\n", "testListIteratorPrevious", e.toString());
				e.printStackTrace();
				result = Result.UnexpectedException;
			}
		}
		return result==expectedResult;
	}	
	/**
	 * Helper method for ListIterators, tests the nextIndex() method
	 * @param list
	 * @param startIndex
	 * @param expectedValue
	 * @param expectedResult
	 * @return
	 */
	private boolean testListIteratorNextIndex(ListADT<Integer> list, ListIterator<Integer> itr, Integer startIndex, Integer expectedValue, Result expectedResult){
		DoubleLinkedList<Integer> listDLL;
		ListIterator<Integer> itDLL;
		Result result;
		if(list==null){
			itDLL=itr;
			try{
				Integer retVal = itDLL.nextIndex();
				if(retVal.equals(expectedValue)){
					result=Result.MatchingValue;
				}else{
					result=Result.Fail;
				}
			}catch (ConcurrentModificationException e) {
				result = Result.ConcurrentModification;
			} catch (Exception e) {
				System.out.printf("%s caught unexpected %s\n", "testListIteratorPrevious", e.toString());
				e.printStackTrace();
				result = Result.UnexpectedException;
			}
		}else if(startIndex==null){
			listDLL=(DoubleLinkedList<Integer>) list;
			itDLL=listDLL.listIterator();
			try{
				Integer retVal = itDLL.nextIndex();
				if(retVal.equals(expectedValue)){
					result=Result.MatchingValue;
				}else{
					result=Result.Fail;
				}
			}catch (ConcurrentModificationException e) {
				result = Result.ConcurrentModification;
			} catch (Exception e) {
				System.out.printf("%s caught unexpected %s\n", "testListIteratorPrevious", e.toString());
				e.printStackTrace();
				result = Result.UnexpectedException;
			}
			
		}else{
			listDLL= (DoubleLinkedList<Integer>) list;
			itDLL=listDLL.listIterator(startIndex);
			try{
				Integer retVal = itDLL.nextIndex();
				if(retVal.equals(expectedValue)){
					result=Result.MatchingValue;
				}else{
					result=Result.Fail;
				}
			}catch (ConcurrentModificationException e) {
				result = Result.ConcurrentModification;
			} catch (Exception e) {
				System.out.printf("%s caught unexpected %s\n", "testListIteratorPrevious", e.toString());
				e.printStackTrace();
				result = Result.UnexpectedException;
			}
		}
		return result==expectedResult;
	}
	/**
	 * Helper method for ListIterators, tests the previousIndex() method
	 * @param list
	 * @param startIndex
	 * @param expectedValue
	 * @param expectedResult
	 * @return
	 */
	private boolean testListIteratorPreviousIndex(ListADT<Integer> list, ListIterator<Integer> itr, Integer startIndex, Integer expectedValue, Result expectedResult){
		DoubleLinkedList<Integer> listDLL;
		ListIterator<Integer> itDLL;
		Result result;
		if(list==null){
			itDLL=itr;
			try{
				Integer retVal = itDLL.previousIndex();
				if(retVal.equals(expectedValue)){
					result=Result.MatchingValue;
				}else{
					result=Result.Fail;
				}
			}catch (ConcurrentModificationException e) {
				result = Result.ConcurrentModification;
			} catch (Exception e) {
				System.out.printf("%s caught unexpected %s\n", "testListIteratorPrevious", e.toString());
				e.printStackTrace();
				result = Result.UnexpectedException;
			}
		}else if(startIndex==null){
			listDLL=(DoubleLinkedList<Integer>) list;
			itDLL=listDLL.listIterator();
			try{
				Integer retVal = itDLL.previousIndex();
				if(retVal.equals(expectedValue)){
					result=Result.MatchingValue;
				}else{
					result=Result.Fail;
				}
			}catch (ConcurrentModificationException e) {
				result = Result.ConcurrentModification;
			} catch (Exception e) {
				System.out.printf("%s caught unexpected %s\n", "testListIteratorPrevious", e.toString());
				e.printStackTrace();
				result = Result.UnexpectedException;
			}
			
		}else{
			listDLL= (DoubleLinkedList<Integer>) list;
			itDLL=listDLL.listIterator(startIndex);
			try{
				Integer retVal = itDLL.previousIndex();
				if(retVal.equals(expectedValue)){
					result=Result.MatchingValue;
				}else{
					result=Result.Fail;
				}
			}catch (ConcurrentModificationException e) {
				result = Result.ConcurrentModification;
			} catch (Exception e) {
				System.out.printf("%s caught unexpected %s\n", "testListIteratorPrevious", e.toString());
				e.printStackTrace();
				result = Result.UnexpectedException;
			}
		}
		return result==expectedResult;
	}
	/**
	 * Helper method for ListIterators, tests the remove() method
	 * @param list
	 * @param startIndex
	 * @param expectedValue
	 * @param expectedResult
	 * @return
	 */
	private boolean testListIteratorRemove(ListADT<Integer> list, ListIterator<Integer> itr, Integer startIndex, Result expectedResult){
		DoubleLinkedList<Integer> listDLL;
		ListIterator<Integer> itDLL;
		Result result;
		if(list==null){
			itDLL=itr;
			try{
				itDLL.remove();
				result=Result.NoException;
			}catch(IllegalStateException e){
				result=Result.IllegalState;
			}catch (ConcurrentModificationException e) {
				result = Result.ConcurrentModification;
			} catch (Exception e) {
				System.out.printf("%s caught unexpected %s\n", "testListIteratorPrevious", e.toString());
				e.printStackTrace();
				result = Result.UnexpectedException;
			}
			
		}else if(startIndex==null){
			listDLL=(DoubleLinkedList<Integer>) list;
			itDLL=listDLL.listIterator();
			try{
				itDLL.remove();
				result=Result.NoException;
			}catch(IllegalStateException e){
				result=Result.IllegalState;
			}catch (ConcurrentModificationException e) {
				result = Result.ConcurrentModification;
			} catch (Exception e) {
				System.out.printf("%s caught unexpected %s\n", "testListIteratorPrevious", e.toString());
				e.printStackTrace();
				result = Result.UnexpectedException;
			}
			
		}else{
			listDLL= (DoubleLinkedList<Integer>) list;
			itDLL=listDLL.listIterator(startIndex);
			try{
				itDLL.remove();
				result=Result.NoException;
			}catch(IllegalStateException e){
				result=Result.IllegalState;
			}catch (ConcurrentModificationException e) {
				result = Result.ConcurrentModification;
			} catch (Exception e) {
				System.out.printf("%s caught unexpected %s\n", "testListIteratorPrevious", e.toString());
				e.printStackTrace();
				result = Result.UnexpectedException;
			}
		}
		return result==expectedResult;
	}
	/**
	 * Helper method for ListIterators, tests the set() method
	 * @param list
	 * @param startIndex
	 * @param setVal
	 * @param expectedResult
	 * @return
	 */
	private boolean testListIteratorSet(ListADT<Integer> list, ListIterator<Integer> itr, Integer startIndex,Integer setVal, Result expectedResult){
		DoubleLinkedList<Integer> listDLL;
		ListIterator<Integer> itDLL;
		Result result;
		if(list==null){
			itDLL=itr;
			try{
				itDLL.set(setVal);
				result=Result.NoException;
			}catch(ClassCastException e){
				result=Result.ClassCast;
			}catch(IllegalArgumentException e){
				result=Result.IllegalArgument;
			}catch(IllegalStateException e){
				result=Result.IllegalState;
			}catch (ConcurrentModificationException e) {
				result = Result.ConcurrentModification;
			} catch (Exception e) {
				System.out.printf("%s caught unexpected %s\n", "testListIteratorSet", e.toString());
				e.printStackTrace();
				result = Result.UnexpectedException;
			}
		}else if(startIndex==null){
			listDLL=(DoubleLinkedList<Integer>) list;
			itDLL=listDLL.listIterator();
			try{
				itDLL.set(setVal);
				result=Result.NoException;
			}catch(ClassCastException e){
				result=Result.ClassCast;
			}catch(IllegalArgumentException e){
				result=Result.IllegalArgument;
			}catch(IllegalStateException e){
				result=Result.IllegalState;
			}catch (ConcurrentModificationException e) {
				result = Result.ConcurrentModification;
			} catch (Exception e) {
				System.out.printf("%s caught unexpected %s\n", "testListIteratorSet", e.toString());
				e.printStackTrace();
				result = Result.UnexpectedException;
			}
			
		}else{
			listDLL= (DoubleLinkedList<Integer>) list;
			itDLL=listDLL.listIterator(startIndex);
			try{
				itDLL.set(setVal);
				result=Result.NoException;
			}catch(ClassCastException e){
				result=Result.ClassCast;
			}catch(IllegalArgumentException e){
				result=Result.IllegalArgument;
			}catch(IllegalStateException e){
				result=Result.IllegalState;
			}catch (ConcurrentModificationException e) {
				result = Result.ConcurrentModification;
			} catch (Exception e) {
				System.out.printf("%s caught unexpected %s\n", "testListIteratorSet", e.toString());
				e.printStackTrace();
				result = Result.UnexpectedException;
			}
		}
		return result==expectedResult;
	}
	/**
	 * Helper method for ListIterators, tests the add() method
	 * @param list
	 * @param startIndex
	 * @param addVal
	 * @param expectedResult
	 * @return
	 */
	private boolean testListIteratorAdd(ListADT<Integer> list, ListIterator<Integer> itr, Integer startIndex,Integer addVal, Result expectedResult){
		DoubleLinkedList<Integer> listDLL;
		ListIterator<Integer> itDLL;
		Result result;
		if(list==null){
			itDLL=itr;
			try{
				itDLL.add(addVal);
				result=Result.NoException;
			}catch(ClassCastException e){
				result=Result.ClassCast;
			}catch(IllegalArgumentException e){
				result=Result.IllegalArgument;
			}catch (ConcurrentModificationException e) {
				result = Result.ConcurrentModification;
			} catch (Exception e) {
				System.out.printf("%s caught unexpected %s\n", "testListIteratorSet", e.toString());
				e.printStackTrace();
				result = Result.UnexpectedException;
			}
		}else if(startIndex==null){
			listDLL=(DoubleLinkedList<Integer>) list;
			itDLL=listDLL.listIterator();
			try{
				itDLL.add(addVal);
				result=Result.NoException;
			}catch(ClassCastException e){
				result=Result.ClassCast;
			}catch(IllegalArgumentException e){
				result=Result.IllegalArgument;
			}catch (ConcurrentModificationException e) {
				result = Result.ConcurrentModification;
			} catch (Exception e) {
				System.out.printf("%s caught unexpected %s\n", "testListIteratorSet", e.toString());
				e.printStackTrace();
				result = Result.UnexpectedException;
			}
			
		}else{
			listDLL= (DoubleLinkedList<Integer>) list;
			itDLL=listDLL.listIterator(startIndex);
			try{
				itDLL.add(addVal);
				result=Result.NoException;
			}catch(ClassCastException e){
				result=Result.ClassCast;
			}catch(IllegalArgumentException e){
				result=Result.IllegalArgument;
			}catch (ConcurrentModificationException e) {
				result = Result.ConcurrentModification;
			} catch (Exception e) {
				System.out.printf("%s caught unexpected %s\n", "testListIteratorSet", e.toString());
				e.printStackTrace();
				result = Result.UnexpectedException;
			}
		}
		return result==expectedResult;
	}
	/**
	 * Helper for ListIterator, compares output strings after remove() is called after calling next()
	 * @param list
	 * @param numCallsToNext
	 * @param constructor
	 * @param numToNext
	 * @param expectedToString
	 * @param expectedResult
	 * @return
	 */
	private boolean testListIteratorRealRemoveAfterNext(ListADT<Integer> list, Integer constructor,int numToNext, String expectedToString, Result expectedResult){
		DoubleLinkedList<Integer> listDLL;
		Result result=null;
		String realOutput;
		if(constructor==null){
			listDLL =(DoubleLinkedList<Integer>) list;
			ListIterator<Integer> it=listDLL.listIterator();
			for(int i=0; i<numToNext; i++){
				it.next();
			}
			try{
			it.remove();
			}catch(IllegalStateException e){}
			realOutput=listDLL.toString();
			try{
				for(int i=0; i<expectedToString.length(); i++){
					if(realOutput.charAt(i)!=expectedToString.charAt(i)){
						result=Result.Fail;
					}
				}
				if(result!=Result.Fail){
					result=Result.MatchingValue;
				}
			}catch (Exception e){
				result=Result.UnexpectedException;
				System.out.println(realOutput);
			}
		}else{
			listDLL =(DoubleLinkedList<Integer>) list;
			ListIterator<Integer> it=listDLL.listIterator(constructor);
			for(int i=0; i<numToNext; i++){
				it.next();
			}
			try{
				it.remove();
				}catch(IllegalStateException e){}
			
			realOutput=listDLL.toString();
			try{
				for(int i=0; i<expectedToString.length(); i++){
					if(realOutput.charAt(i)!=expectedToString.charAt(i)){
						result=Result.Fail;
					}
				}
				if(result!=Result.Fail){
					result=Result.MatchingValue;
				}
			}catch (Exception e){
				result=Result.UnexpectedException;
				System.out.println(realOutput);
			}
		}
		if(result!=expectedResult){
			System.out.println(realOutput);
		}
		return result==expectedResult;
	}
	/**
	 * Helper for ListIterator, compares output strings after remove() is called after calling previous()
	 * @param list
	 * @param numCallsToNext
	 * @param constructor
	 * @param numToPrev
	 * @param expectedToString
	 * @param expectedResult
	 * @return
	 */
	private boolean testListIteratorRealRemoveAfterPrev(ListADT<Integer> list,Integer constructor,int numToPrev, String expectedToString, Result expectedResult){
		DoubleLinkedList<Integer> listDLL;
		Result result=null;
		String realOutput;
		if(constructor==null){
			listDLL =(DoubleLinkedList<Integer>) list;
			ListIterator<Integer> it=listDLL.listIterator();
			for(int i=0; i<numToPrev; i++){
				it.previous();
			}
			try{
				it.remove();
				}catch(IllegalStateException e){}
			realOutput=listDLL.toString();
			try{
				for(int i=0; i<expectedToString.length(); i++){
					if(realOutput.charAt(i)!=expectedToString.charAt(i)){
						result=Result.Fail;
					}
				}
				if(result!=Result.Fail){
					result=Result.MatchingValue;
				}
			}catch (Exception e){
				result=Result.UnexpectedException;
				System.out.println(realOutput);
			}
		}else{
			listDLL =(DoubleLinkedList<Integer>) list;
			ListIterator<Integer> it=listDLL.listIterator(constructor);
			for(int i=0; i<numToPrev; i++){
				it.previous();
			}
			try{
				it.remove();
				}catch(IllegalStateException e){}
			realOutput=listDLL.toString();
			try{
				for(int i=0; i<expectedToString.length(); i++){
					if(realOutput.charAt(i)!=expectedToString.charAt(i)){
						result=Result.Fail;
					}
				}
				if(result!=Result.Fail){
					result=Result.MatchingValue;
				}
			}catch (Exception e){
				result=Result.UnexpectedException;
				System.out.println(realOutput);
			}
		}
		if(result!=expectedResult){
			System.out.println(realOutput);
		}
		return result==expectedResult;
	}
	/**
	 * Helper for ListIterator, compares output strings after set() is called after calling next()
	 * @param list
	 * @param numCallsToNext
	 * @param constructor
	 * @param numToNext
	 * @param setElement
	 * @param expectedToString
	 * @param expectedResult
	 * @return
	 */
	private boolean testListIteratorRealSetAfterNext(ListADT<Integer> list,Integer constructor,int numToNext, Integer setElement, String expectedToString, Result expectedResult){
		DoubleLinkedList<Integer> listDLL;
		Result result=null;
		String realOutput;
		if(constructor==null){
			listDLL =(DoubleLinkedList<Integer>) list;
			ListIterator<Integer> it=listDLL.listIterator();
			for(int i=0; i<numToNext; i++){
				it.next();
			}
			try{
				it.set(setElement);
			}catch(IllegalStateException e){}
			realOutput=listDLL.toString();
			try{
				for(int i=0; i<expectedToString.length(); i++){
					if(realOutput.charAt(i)!=expectedToString.charAt(i)){
						result=Result.Fail;
					}
				}
				if(result!=Result.Fail){
					result=Result.MatchingValue;
				}
			}catch (Exception e){
				result=Result.UnexpectedException;
				System.out.println(realOutput);
			}
		}else{
			listDLL =(DoubleLinkedList<Integer>) list;
			ListIterator<Integer> it=listDLL.listIterator(constructor);
			for(int i=0; i<numToNext; i++){
				it.next();
			}
			try{
				it.set(setElement);
			}catch(IllegalStateException e){}
			realOutput=listDLL.toString();
			try{
				for(int i=0; i<expectedToString.length(); i++){
					if(realOutput.charAt(i)!=expectedToString.charAt(i)){
						result=Result.Fail;
					}
				}
				if(result!=Result.Fail){
					result=Result.MatchingValue;
				}
			}catch (Exception e){
				result=Result.UnexpectedException;
				System.out.println(realOutput);
			}
		}
		if(result!=expectedResult){
			System.out.println(realOutput);
		}
		return result==expectedResult;
	}
	/**
	 * Helper for ListIterator, compares output strings after set() is called after calling previous()
	 * @param list
	 * @param numCallsToNext
	 * @param constructor
	 * @param numToPrev
	 * @param setElement
	 * @param expectedToString
	 * @param expectedResult
	 * @return
	 */
	private boolean testListIteratorRealSetAfterPrev(ListADT<Integer> list,Integer constructor,int numToPrev, Integer setElement, String expectedToString, Result expectedResult){
		DoubleLinkedList<Integer> listDLL;
		Result result=null;
		String realOutput;
		if(constructor==null){
			listDLL =(DoubleLinkedList<Integer>) list;
			ListIterator<Integer> it=listDLL.listIterator();
			for(int i=0; i<numToPrev; i++){
				it.previous();
			}
			try{
				it.set(setElement);
			}catch(IllegalStateException e){}
			realOutput=listDLL.toString();
			try{
				for(int i=0; i<expectedToString.length(); i++){
					if(realOutput.charAt(i)!=expectedToString.charAt(i)){
						result=Result.Fail;
					}
				}
				if(result!=Result.Fail){
					result=Result.MatchingValue;
				}
			}catch (Exception e){
				result=Result.UnexpectedException;
				System.out.println(realOutput);
			}
		}else{
			listDLL =(DoubleLinkedList<Integer>) list;
			ListIterator<Integer> it=listDLL.listIterator(constructor);
			for(int i=0; i<numToPrev; i++){
				it.previous();
			}
			try{
				it.set(setElement);
			}catch(IllegalStateException e){}
			realOutput=listDLL.toString();
			try{
				for(int i=0; i<expectedToString.length(); i++){
					if(realOutput.charAt(i)!=expectedToString.charAt(i)){
						result=Result.Fail;
					}
				}
				if(result!=Result.Fail){
					result=Result.MatchingValue;
				}
			}catch (Exception e){
				result=Result.UnexpectedException;
				System.out.println(realOutput);
			}
		}
		if(result!=expectedResult){
			System.out.println(realOutput);
		}
		return result==expectedResult;
	}
	private boolean testListIteratorRealAddAfterNext(ListADT<Integer> list,Integer constructor,int numToNext, Integer addElement, String expectedToString, Result expectedResult){
		DoubleLinkedList<Integer> listDLL;
		Result result=null;
		String realOutput;
		if(constructor==null){
			listDLL =(DoubleLinkedList<Integer>) list;
			ListIterator<Integer> it=listDLL.listIterator();
			for(int i=0; i<numToNext; i++){
				it.next();
			}
			it.add(addElement);
			realOutput=listDLL.toString();
			try{
				for(int i=0; i<expectedToString.length(); i++){
					if(realOutput.charAt(i)!=expectedToString.charAt(i)){
						result=Result.Fail;
					}
				}
				if(result!=Result.Fail){
					result=Result.MatchingValue;
				}
			}catch (Exception e){
				result=Result.UnexpectedException;
				System.out.println(realOutput);
			}
		}else{
			listDLL =(DoubleLinkedList<Integer>) list;
			ListIterator<Integer> it=listDLL.listIterator(constructor);
			for(int i=0; i<numToNext; i++){
				it.next();
			}
			it.add(addElement);
			realOutput=listDLL.toString();
			try{
				for(int i=0; i<expectedToString.length(); i++){
					if(realOutput.charAt(i)!=expectedToString.charAt(i)){
						result=Result.Fail;
					}
				}
				if(result!=Result.Fail){
					result=Result.MatchingValue;
				}
			}catch (Exception e){
				result=Result.UnexpectedException;
				System.out.println(realOutput);
			}
		}
		if(result!=expectedResult){
			System.out.println(realOutput);
		}
		return result==expectedResult;
	}
	private boolean testListIteratorRealAddAfterPrev(ListADT<Integer> list, Integer constructor,int numToPrev, Integer addElement, String expectedToString, Result expectedResult){
		DoubleLinkedList<Integer> listDLL;
		Result result=null;
		String realOutput;
		if(constructor==null){
			listDLL =(DoubleLinkedList<Integer>) list;
			ListIterator<Integer> it=listDLL.listIterator();
			for(int i=0; i<numToPrev; i++){
				it.previous();
			}
			it.add(addElement);
			realOutput=listDLL.toString();
			try{
				for(int i=0; i<expectedToString.length(); i++){
					if(realOutput.charAt(i)!=expectedToString.charAt(i)){
						result=Result.Fail;
					}
				}
				if(result!=Result.Fail){
					result=Result.MatchingValue;
				}
			}catch (Exception e){
				result=Result.UnexpectedException;
				System.out.println(realOutput);
			}
		}else{
			listDLL =(DoubleLinkedList<Integer>) list;
			ListIterator<Integer> it=listDLL.listIterator(constructor);
			for(int i=0; i<numToPrev; i++){
				it.previous();
			}
			it.add(addElement);
			realOutput=listDLL.toString();
			try{
				for(int i=0; i<expectedToString.length(); i++){
					if(realOutput.charAt(i)!=expectedToString.charAt(i)){
						result=Result.Fail;
					}
				}
				if(result!=Result.Fail){
					result=Result.MatchingValue;
				}
			}catch (Exception e){
				result=Result.UnexpectedException;
				System.out.println(realOutput);
			}
		}
		if(result!=expectedResult){
			System.out.println(realOutput);
		}
		return result==expectedResult;
	}
}// end class UnorderedListTester