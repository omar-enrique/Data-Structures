/**
 * SingleLinkedList represents a LinearNode-based implementation of both an unordered and indexed list.
 *
 * @author Java Foundations, 
 * @version 4.0
 */
public class SingleLinkedList<T> extends AbstractLinkedList<T> implements UnorderedListADT<T>, IndexedListADT<T>
{	
	/**
	 * Adds the specified element to the front of this list.
	 *
	 * @param element the element to be added to the list
	 */
	public void addToFront(T element)
	{
		LinearNode<T> newNode = new LinearNode<T>(element);
		newNode.setNext(head);
		head = newNode;
		
		if(tail == null)
			tail = newNode;
		
		count++;
		modCount++;
	}

	/**
	 * Adds the specified element to the rear of this list.
	 *
	 * @param element the element to be added to the list
	 */
	public void addToRear(T element)
	{
		LinearNode<T> newNode = new LinearNode<T>(element);
		if(tail == null)
		{
			head = tail = newNode;
		}
		
		else
		{
			tail.setNext(newNode);
			tail = newNode;
		}
		count++;
		modCount++;
	}	

	/**
	 * Adds the specified element to this list after the given target.
	 *
	 * @param  element the element to be added to this list
	 * @param  target the target element to be added after
	 * @throws ElementNotFoundException if the target is not found
	 */
	public void addAfter(T element, T target)
	{
		LinearNode<T> newNode = new LinearNode<T>();
		newNode.setElement(element);
		LinearNode<T> targetElement = head;
		
		boolean found = false;
		
		while (targetElement != null && !found)
			if (target.equals(targetElement.getElement()))
				found = true;
			else
			{
				targetElement = targetElement.getNext();
			}
		if(found == false )
			throw new ElementNotFoundException("SLL");
		
		
		newNode.setNext(targetElement.getNext());
		targetElement.setNext(newNode);
		
		if(newNode.getNext() == null)
			tail = newNode;
		
		count++;
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
	public void add(int index, T element) 
	{
		if(index<0 || index>size())
			throw new IndexOutOfBoundsException();
		
		if(index == 0)
		{
			addToFront(element);
		}
		
		else if(index == size())
			addToRear(element);
		
		else{
			LinearNode<T> prev = head;
			for(int i = 0; i < index-1; i++)
				prev = prev.getNext();
			
			LinearNode<T> newNode = new LinearNode<T>(element);
			prev.setNext(newNode);
			modCount++;
			count++;
		}
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
	 * Sets the element at the specified index. 
	 *
	 * @param index   the index into the array to which the element is to be set
	 * @param element the element to be set into the list
	 * @throws IndexOutOfBoundsException for invalid index
	 */
	public void set(int index, T element) {
		if(index<0 || index>=size() || isEmpty())
			throw new IndexOutOfBoundsException();
		LinearNode<T> node = new LinearNode<T>(element);
		
		if(index == 0)
		{
			node.setNext(head);
		}
		
		else if(index == size())
		{
			node.setNext(tail);
		}
		else
		{
			LinearNode<T> current = head;
			for(int i = 0; i < index-1; i++)
				current = current.getNext();
			node.setNext(current);
			remove(current.getElement());
		}
		modCount++;
	}

	/**  
	 * Returns a reference to the element at the specified index. 
	 *
	 * @param index  the index to which the reference is to be retrieved from
	 * @return the element at the specified index    
	 * @throws IndexOutOfBoundsException for invalid index
	 */
	public T get(int index) {
		if(index<0 || index>=size() || isEmpty())
			throw new IndexOutOfBoundsException();
		
		if(index == 0)
		{
			return head.getElement();
		}
		
		else if(index == size())
		{
			return tail.getElement();
		}
		
		else{
			LinearNode<T> prev = head;
			for(int i = 0; i < index-1; i++)
				prev = prev.getNext();
			return prev.getElement();
		}
	}

	/**  
	 * Returns the index of the specified element. 
	 *
	 * @param element  the element for the index is to be retrieved
	 * @return the integer index for this element or -1 if element is not in the list
	 */
	public int indexOf(T element) {
		LinearNode<T> prev = head;
		
		for(int i = 0; i < size(); i++)
		{
			if(element.equals(prev.getElement()))
				return i;
			prev = prev.getNext();
		}
		return -1;
	}

	/**  
	 * Returns the element at the specified element. 
	 *
	 * @param index the index of the element to be retrieved
	 * @return the element at the given index    
	 * @throws IndexOutOfBoundsException for invalid index
	 */
	public T remove(int index) {
		if(index<0 || index>=size() || isEmpty())
			throw new IndexOutOfBoundsException();
		
		LinearNode<T> current = head;
		LinearNode<T> previous = null;
		
		for(int i = 0; i<index;i++)
		{
			previous = current;
			current = current.getNext();
		}
		
		if(size() == 1)
		{
			head = tail = null;
		}
		
		else if(current.equals(head))
			head = current.getNext();
		else if(current.equals(tail))
		{
			tail = previous;      
			tail.setNext(null);			
		}
		
		else
			previous.setNext(current.getNext());
		
		
		modCount++;
		count--;
		return current.getElement();
	}
}
