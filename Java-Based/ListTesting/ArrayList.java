/**
 * ArrayList represents an array implementation of both an unordered and indexed list.
 *
 * @author Java Foundations, 
 * @version 4.0
 */
public class ArrayList<T> extends AbstractArrayList<T> implements UnorderedListADT<T>, IndexedListADT<T>
{
	/**
	 * Creates an empty list using the default capacity.
	 */
	public ArrayList()
	{
		super();
	}

	/**
	 * Creates an empty list using the specified capacity.
	 *
	 * @param initialCapacity the intial size of the list
	 */
	public ArrayList(int initialCapacity)
	{
		super(initialCapacity);
	}

	/**
	 * Adds the specified element to the front of this list.
	 * 
	 * @param element the element to be added to the front of the list
	 */
	public void addToFront(T element)
	{
		if(rear == list.length)
			expandCapacity();
		
		for(int cur = rear; cur > 0; cur--)
		{
			list[cur] = list[cur-1];
		}
		
		list[0] = element;
		modCount++;
		rear++;
	}

	/**
	 * Adds the specified element to the rear of this list.
	 *
	 * @param element the element to be added to the list
	 */
	public void addToRear(T element)
	{
		if(rear == list.length)
			expandCapacity();
		
		list[rear] = element;
		modCount++;
		rear++;
	}

	/**
	 * Adds the specified element after the specified target element.
	 *
	 * @param element the element to be added after the target element
	 * @param target  the target that the element is to be added after
	 * @throws ElementNotFoundException if the target is not found.
	 */
	public void addAfter(T element, T target)
	{
		if (size() == list.length)
			expandCapacity();

		int scan = 0;

		// find the insertion point
		while (scan < rear && !target.equals(list[scan])) 
			scan++;

		if (scan == rear)
			throw new ElementNotFoundException("UnorderedList");

		scan++;

		// shift elements up one
		for (int shift=rear; shift > scan; shift--)
			list[shift] = list[shift-1];

		// insert element
		list[scan] = element;
		rear++;
		modCount++;
	}

	/**  
	 * Inserts the specified element at the specified index. 
	 * 
	 * @param index   the index into the array to which the element is to be
	 *                inserted.
	 * @param element the element to be inserted into the array   
	 * @throws IndexOutOfBoundsException for invalid index
	 */
	public void add(int index, T element) {
		if(index<0 || index>rear)
			throw new IndexOutOfBoundsException();
		if(size() == list.length)
			expandCapacity();
		
		for(int i = index; i<rear; i++)
		{
			list[i+1] = list[i];
		}
		
		rear++;
		
		modCount++;
		
		list[index] = element;
	}

	/**  
	 * Sets the element at the specified index. 
	 *
	 * @param index   the index into the array to which the element is to be set
	 * @param element the element to be set into the list
	 * @throws IndexOutOfBoundsException for invalid index
	 */
	public void set(int index, T element) {
		if(index<0 || index>rear-1)
			throw new IndexOutOfBoundsException();
		list[index] = element;		
		modCount++;
	}

	/**  
	 * Adds the specified element to the rear of this list. 
	 *
	 * @param element  the element to be added to the rear of the list    
	 */
	public void add(T element) {
		addToRear(element);
	}

	/**  
	 * Returns a reference to the element at the specified index. 
	 *
	 * @param index  the index to which the reference is to be retrieved from
	 * @return the element at the specified index    
	 * @throws IndexOutOfBoundsException for invalid index
	 */
	public T get(int index) {
		if(index<0 || index>=rear)
			throw new IndexOutOfBoundsException();
		return list[index];
	}

	/**  
	 * Returns the index of the specified element. 
	 *
	 * @param element  the element for the index is to be retrieved
	 * @return the integer index for this element    
	 */
	public int indexOf(T element) {
		
		return find(element);
	}

	/**  
	 * Returns the element at the specified element. 
	 *
	 * @param index the index of the element to be retrieved
	 * @return the element at the given index    
	 * @throws IndexOutOfBoundsException for invalid index
	 */
	public T remove(int index) {
		
		if(index<0 || index>rear-1 || list[index]==null)
			throw new IndexOutOfBoundsException("Unordered List");
		
		T result = list[index];
		rear--;

		// shift the appropriate elements 
		for (int scan=index; scan < rear; scan++)
			list[scan] = list[scan+1];

		list[rear] = null;
		modCount++;

		return result;
	}
}
