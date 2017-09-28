import java.util.*;

/**
 * Class for searching and sorting DoubleLinkedLists 
 * using either natural order or a Comparator.
 *
 * @author spanter, mvail, Omar Finol-Evans
 */
public class SearchAndSort
{
	private static int count = 0;
	/**
	 * Sorts a list that implements the DoubleLinkedListADT using the
	 * natural ordering of list elements.
	 * DO NOT MODIFY THIS METHOD SIGNATURE
	 * 
	 * @param <T>
	 *            The data type in the list must extend Comparable
	 * @param list
	 *            The list that will be sorted
	 * @see DoubleLinkedListADT 
	 */
	public static <T extends Comparable<T>> void sort(DoubleLinkedListADT<T> list) 
	{
		if(list.size()>=2)
		{
			WrappedDLL<T> firstHalf = new WrappedDLL<T>();
			WrappedDLL<T> secondHalf = new WrappedDLL<T>();
			for(int index = 0; index < list.size()/2; index++)
			{
				firstHalf.add(list.first());
				list.removeFirst();
			}
			for(int index = 0; index < list.size(); index++)
			{
				secondHalf.add(list.first());
				list.removeFirst();
			}
			
			sort(firstHalf);
			sort(secondHalf);
			merge(list, firstHalf, secondHalf);
		}
	}

	/**
	 * Sorts a DoubleLinkedListADT with the provided Comparator.
	 * DO NOT MODIFY THIS METHOD SIGNATURE
	 * 
	 * @param <T>
	 *            The type of list to sort
	 * @param list
	 *            The list to sort
	 * @param c
	 *            The Comparator to use
	 * @see DoubleLinkedListADT
	 */
	public static <T> void sort(DoubleLinkedListADT<T> list, Comparator<T> c) {
		if(list.size()>=2)
		{
			WrappedDLL<T> firstHalf = new WrappedDLL<T>();
			WrappedDLL<T> secondHalf = new WrappedDLL<T>();
			for(int index = 0; index < list.size()/2; index++)
			{
				firstHalf.add(list.first());
				list.removeFirst();
			}
			for(int index = 0; index < list.size(); index++)
			{
				secondHalf.add(list.first());
				list.removeFirst();
			}
			
			sort(firstHalf, c);
			sort(secondHalf, c);
			merge(list, firstHalf, secondHalf, c);
			
		}
	}
	
	public static <T extends Comparable<T>> void merge(DoubleLinkedListADT<T> list, DoubleLinkedListADT<T> firstHalf, DoubleLinkedListADT<T> secondHalf)
	{
		for(int index = 0; index <= (firstHalf.size() + secondHalf.size()); index++)
		{
			if(!firstHalf.isEmpty() && !secondHalf.isEmpty())	
			{
				if(firstHalf.first().compareTo(secondHalf.first()) < 0)
				{
					list.addToRear(firstHalf.first());
					firstHalf.removeFirst();
				}
				else if(firstHalf.first().compareTo(secondHalf.first()) == 0)
				{
					list.addToRear(firstHalf.first());
					list.addToRear(secondHalf.first());
					firstHalf.removeFirst();
					secondHalf.removeFirst();
				}
				
				else
				{
					list.addToRear(secondHalf.first());
					secondHalf.removeFirst();
				}
			}
			
			else if(!firstHalf.isEmpty() && secondHalf.isEmpty())
			{
				list.addToRear(firstHalf.first());
				firstHalf.removeFirst();
				
			}
			
			else if(firstHalf.isEmpty() && !secondHalf.isEmpty())
			{
				secondHalf.addToRear(secondHalf.first());
				secondHalf.removeFirst();
			}
		}
	}
	
	public static <T> void merge(DoubleLinkedListADT<T> list, DoubleLinkedListADT<T> firstHalf, DoubleLinkedListADT<T> secondHalf, Comparator<T> c)
	{
		for(int index = 0; index <= (firstHalf.size() + secondHalf.size()); index++)
		{
			if(!firstHalf.isEmpty() && !secondHalf.isEmpty())	
			{
				if(c.compare(firstHalf.first(), secondHalf.first()) < 0)
				{
					list.addToRear(firstHalf.first());
					firstHalf.removeFirst();
				}
				else if(c.compare(firstHalf.first(), secondHalf.first()) == 0)
				{
					list.addToRear(firstHalf.first());
					list.addToRear(secondHalf.first());
					firstHalf.removeFirst();
					secondHalf.removeFirst();
				}
				
				else
				{
					list.addToRear(secondHalf.first());
					secondHalf.removeFirst();
				}
			}
			
			else if(!firstHalf.isEmpty() && secondHalf.isEmpty())
			{
				list.addToRear(firstHalf.first());
				firstHalf.removeFirst();
				
			}
			
			else if(firstHalf.isEmpty() && !secondHalf.isEmpty())
			{
				secondHalf.addToRear(secondHalf.first());
				secondHalf.removeFirst();
			}
		}
	}

	/**
	 * Finds the smallest element in a list according to the natural ordering of 
	 * list elements. Does not alter the order of list elements.
	 * DO NOT MODIFY THIS METHOD SIGNATURE
	 * 
	 * @param <T>
	 *            The type of object we are comparing
	 * @param list
	 *            The list we are passed
	 * @return The smallest element or null if list is empty
	 * @see DoubleLinkedListADT
	 */
	public static <T extends Comparable<T>> T findSmallest(DoubleLinkedListADT<T> list) {
		if(list.isEmpty())
			return null;
		
		T retVal = list.first();
		T test;
		if(list.size() == 1)
			return retVal;
		else{
			retVal = list.first();
			list.removeFirst();
			test = findSmallest(list);
			list.addToFront(retVal);
		}
		
		if(retVal.compareTo(test) <= 0)
			return retVal;
		else
			return test;
	}

	/**
	 * Finds the smallest element in a list with a Custom comparator. Does not
	 * alter the order of list elements.
	 * DO NOT MODIFY THIS METHOD SIGNATURE
	 * 
	 * @param <T>
	 *            The type of object we are comparing
	 * @param list
	 *            The list we are passed
	 * @param c
	 *            The comparator to use
	 * @return The smallest element or null if list is empty
	 * @see DoubleLinkedListADT
	 */
	public static <T> T findSmallest(DoubleLinkedListADT<T> list, Comparator<T> c) 
	{
		if(list.isEmpty())
			return null;
		
		T retVal = list.first();
		T test;
		if(list.size() == 1)
			return retVal;
		else{
			retVal = list.first();
			list.removeFirst();
			test = findSmallest(list, c);
			list.addToFront(retVal);
		}
		
		if(c.compare(retVal, test) <= 0)
			return retVal;
		else
			return test;
	}

	/**
	 * Finds the largest element in a list according to the natural ordering of
	 * list elements. Does not alter the order of list elements.
	 * DO NOT MODIFY THIS METHOD SIGNATURE
	 * 
	 * @param <T>
	 *            The type of object we are comparing
	 * @param list
	 *            The list we are passed
	 * @return The largest element or null if list is empty
	 * @see DoubleLinkedListADT
	 */
	public static <T extends Comparable<T>> T findLargest(DoubleLinkedListADT<T> list) {
		if(list.isEmpty())
			return null;
		
		T retVal = list.first();
		T test;
		if(list.size() == 1)
			return retVal;
		else{
			retVal = list.first();
			list.removeFirst();
			test = findLargest(list);
			list.addToFront(retVal);
		}
		
		if(retVal.compareTo(test) >= 0)
			return retVal;
		else
			return test;
	}

	/**
	 * Finds the largest element in a list with a Custom comparator. Does not
	 * alter the order of list elements.
	 * DO NOT MODIFY THIS METHOD SIGNATURE
	 * 
	 * @param <T>
	 *            The type of object we are comparing
	 * @param list
	 *            The list we are passed
	 * @param c
	 *            The comparator to use
	 * @return The largest element or null if list is empty
	 * @see DoubleLinkedListADT
	 */
	public static <T> T findLargest(DoubleLinkedListADT<T> list, Comparator<T> c) {
		if(list.isEmpty())
			return null;
		
		T retVal = list.first();
		T test;
		if(list.size() == 1)
			return retVal;
		else{
			retVal = list.first();
			list.removeFirst();
			test = findLargest(list, c);
			list.addToFront(retVal);
		}
		
		if(c.compare(retVal, test)>= 0)
			return retVal;
		else
			return test;
	}
}