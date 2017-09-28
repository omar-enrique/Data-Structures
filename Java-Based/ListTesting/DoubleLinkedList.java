import java.util.*;

/**
 * A class that builds up the framework for a node-based Double Linked List data structure
 * Implements DoubleLinkedListADT interface
 * 
 * @author Omar
 *
 * @param Generic type <T>
 */
public class DoubleLinkedList <T> implements DoubleLinkedListADT<T> {
	private LinearNode<T> head, tail;
	private int count;
	private long modCount;
	
	/**
	 * Constructor for DoubleLinkedList, instantiates all private variables
	 */
	public DoubleLinkedList()
	{
		count = 0;
		head = tail = null;
		modCount = 0;
	}
	
	/**
	 * Adds an element, which is passed through the method parameter, to the front
	 * 
	 * @param element being added
	 */
	public void addToFront(T element) {
		LinearNode<T> newNode = new LinearNode<T>(element);
		if(isEmpty())
		{
			head = tail = newNode;
		}
		else{
			newNode.setNext(head);
			head.setPrev(newNode);
			head = newNode;
		}
		count++;
		modCount++;
	}

	/**
	 * Adds an element, which is passed through the method parameter, to the rear
	 * 
	 * @param element being added
	 */
	public void addToRear(T element) {
		LinearNode<T> newNode = new LinearNode<T>(element);
		if(tail == null)
		{
			head = tail = newNode;
		}
		
		else
		{
			tail.setNext(newNode);
			newNode.setPrev(tail);
			tail = newNode;
		}
		count++;
		modCount++;
	}

	/**
	 * Adds an element after a specified target element
	 * 
	 * @param element being added, target element that already exists in the list where element will be added after
	 */
	public void addAfter(T element, T target) {
		LinearNode<T> current = head;
		while(current != null && !current.getElement().equals(target))
			current = current.getNext();
		if(current==null)
			throw new ElementNotFoundException("DLL");
		
		LinearNode<T> newNode = new LinearNode<T>(element);
		newNode.setNext(current.getNext());
		newNode.setPrev(current);
		current.setNext(newNode);
		
		if(current == tail){
			tail = newNode;
		}
		else{
			current.getNext().setPrev(current);
		}
		
		count++;
		modCount++;
	}

	/**
	 * Removes the first node of the list
	 * 
	 * @throws EmptyCollectionException if list is empty
	 * @return The element of the node that was removed
	 */
	public T removeFirst() {
		if(isEmpty())
			throw new EmptyCollectionException("LinkedList");
		T retVal = head.getElement();
		
		if(head == tail)
			head = tail = null;
		
		else
		{
			head = head.getNext();
			head.setPrev(null);
		}
		
		modCount++;
		count--;
		
		return retVal;
	}

	/**
	 * Removes the last node of the list
	 * 
	 * @throws EmptyCollectionException if list is empty
	 * @return The element of the node that was removed
	 */	
	public T removeLast() {
		if(isEmpty())
			throw new EmptyCollectionException("LinkedList");
		
		T retVal = tail.getElement();
		
		if(head == tail)
		{
			head = tail = null;
		}
		
		else
		{
			LinearNode<T> prev = tail.getPrev();
			tail.setPrev(prev);
			prev.setNext(null);
			tail = prev;
		}
		
		modCount++;
		count--;
		
		return retVal;
	}

	/**
	 * Removes a specific element from the list if it exists in the list
	 * 
	 * @throws ElementNotFoundException thrown if the list is empty or if the element is not found
	 * @return element within the node that was removed
	 */
	public T remove(T element) {
		if (isEmpty())
			throw new ElementNotFoundException("LinkedList");

		boolean found = false;
		LinearNode<T> current = head;

		while (current != null && !found)
			if (element.equals(current.getElement()))
				found = true;
			else
			{
				current = current.getNext();
			}

		if (!found)
			throw new ElementNotFoundException("LinkedList");

		if (size() == 1)  // only one element in the list
			head = tail = null;
		else if (current.equals(head))  // target is at the head 
			head = current.getNext();
		else if (current.equals(tail))  // target is at the tail
		{
			tail = tail.getPrev();
			tail.setNext(null);
		}
		else  // target is in the middle
			current.getPrev().setNext(current.getNext());

		count--;
		modCount++;

		return current.getElement();
	}

	/**
	 * Returns the first node in the list
	 * 
	 * @return element within the head node in the list
	 */
	public T first() {
		if(isEmpty())
			throw new EmptyCollectionException("DLL");
		return head.getElement();
	}

	/**
	 * Returns the last node in the list
	 * 
	 * @return element within the tail node in the list
	 */
	public T last() {
		if(isEmpty())
			throw new EmptyCollectionException("DLL");
		return tail.getElement();
	}

	/**
	 * Scans through the list and checks whether or not the list contains a target element
	 * 
	 * @param target element to be found
	 * @return true if the list contains the target element
	 */
	public boolean contains(T target) {
		boolean foundIt = false;
		
		Iterator<T> it = this.iterator();
		while(it.hasNext() && !foundIt)
		{
			if(it.next().equals(target))
				foundIt = true;
		}
		
		
		return foundIt;
	}

	/**
	 * Returns true if the list is empty
	 * 
	 * @return true if list is empty
	 */
	public boolean isEmpty() {
		return (head == null);
	}

	/**
	 * Returns the current size of the list
	 * 
	 * @return size of the list
	 */
	public int size() {
		return count;
	}
	
	/**
	 * Adds element to a specified index
	 * 
	 * @throws IndexOutOfBoundsException if invalid index is passed through parameter
	 * @param index of element being added, element that will be added to specified index
	 */
	public void add(int index, T element) {
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
			
			newNode.setNext(prev.getNext());
			newNode.getNext().setPrev(newNode);
			prev.setNext(newNode);
			newNode.setPrev(prev);
			modCount++;
			count++;
		}
	}

	/**
	 * Gets element located at specified index and changes it to the element being passed through the parameter
	 * 
	 * @throws IndexOutOfBoundsException if invalid index is passed through parameter
	 * @param index of element being reset, element the index element will be changed to
	 */
	public void set(int index, T element) {
		if(index<0 || index>=size() || isEmpty())
			throw new IndexOutOfBoundsException();
		
		if(index == 0)
		{
			head.setElement(element);
		}
		
		else if(index == size())
		{
			tail.setElement(element);
		}
		else
		{
			LinearNode<T> current = head;
			for(int i = 0; i < index; i++)
				current = current.getNext();
			current.setElement(element);
		}
		modCount++;
	}

	/**
	 * Performs the same results the addToRear method does
	 */
	public void add(T element) {
		addToRear(element);
	}

	/**
	 * Gets element located at specified index
	 * 
	 * @throws IndexOutOfBoundsException if invalid index is passed through parameter
	 * @param index to get element from
	 * 
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
			for(int i = 0; i < index; i++)
				prev = prev.getNext();
			return prev.getElement();
		}
	}

	/**
	 * Goes through the entire linked list and returns the index at which the element given in the method parameter is located
	 * 
	 * @param element being located
	 * @return index of element
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
	 * Removes a node from a specified index
	 * 
	 * @param index of node to be removed
	 * @return the element of the node that was removed from the list
	 */
	public T remove(int index) {
		if(index<0 || index>=size() || isEmpty()) // makes sure a valid index was entered
			throw new IndexOutOfBoundsException();
		
		LinearNode<T> current = head;
		
		for(int i = 0; i<index;i++)
		{
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
			LinearNode<T> prev = tail.getPrev();
			tail.setPrev(prev);
			prev.setNext(null);
			tail = prev;
		}
		
		else
			current.getPrev().setNext(current.getNext());
		
		
		modCount++;
		count--;
		return current.getElement();
	}
	
	/**
	 * When converting the list into a string, each element is displayed between brackets -> [] <- and separated by comments
	 */
	public String toString()
	{
		StringBuilder str = new StringBuilder();
		str.append("[");
		
		
		Iterator<T> it = iterator();
		while(it.hasNext())
		{
			str.append(it.next());
			str.append(",");
		}
		
		if(count > 0)
			str.delete(str.length()-1, str.length());
		str.append("]");		
		
		return str.toString();
	}

	@Override
	public Iterator<T> iterator() {
		return new LinkedListIterator();
	}
	
	private class LinkedListIterator implements Iterator<T>
	{
		private int iteratorModCount;  // the "version" of the list at the time the Iterator was created
		private LinearNode<T> current;  // the current position
		private boolean canRemove;

		/**
		 * Sets up this iterator using the specified items.
		 *
		 * @param collection the collection the iterator will move over
		 * @param size the integer size of the collection
		 */
		public LinkedListIterator()
		{
			current = head;
		}

		/**
		 * Returns true if this iterator has at least one more element
		 * to deliver in the iteration.
		 *
		 * @return  true if this iterator has at least one more element to deliver
		 *          in the iteration
		 * @throws  ConcurrentModificationException if the collection has changed
		 *          while the iterator is in use
		 */
		public boolean hasNext() throws ConcurrentModificationException
		{
			return (current != null);
		}

		/**
		 * Returns the next element in the iteration. If there are no
		 * more elements in this iteration, a NoSuchElementException is
		 * thrown.
		 *
		 * @return the next element in the iteration
		 * @throws NoSuchElementException if the iterator is empty 
		 */
		public T next() throws ConcurrentModificationException
		{
			if (!hasNext() || isEmpty())
				throw new NoSuchElementException();

			T result = current.getElement();
			current = current.getNext();
			canRemove = true;
			return result;
		}

		/**
		 * The remove operation is not supported.
		 * 
		 * @throws IllegalStateException when remove was not preceded by a call to next
		 *  @throws  ConcurrentModificationException if the collection has changed
		 *          while the iterator is in use
		 */
		public void remove() throws UnsupportedOperationException
		{
			
			if(!canRemove)
				throw new IllegalStateException();
			
			if(head == tail)
				head = tail = null;
			else if(head.getNext() == current)
			{
				head = current;
			}
			
			else{
				LinearNode<T> prevPrev = head;
				if(current == null){
					tail = prevPrev;
				}
				else{
					while(prevPrev != null && !prevPrev.getNext().equals(current))
					{
						prevPrev = prevPrev.getNext();
					}
					prevPrev.setNext(current);
					current.setPrev(prevPrev);
				}
			}
			count--;
			modCount++;
			iteratorModCount++;
			canRemove = false;
		}
	}

	/**
	 * Returns a list iterator over the elements in this list (in proper sequence).
	 * 
	 * @return a list iterator over the elements in this list (in proper sequence)
	 */
	public ListIterator<T> listIterator() {
		return new DLLIterator();
	}

	/**
	 * Returns a list iterator over the elements in this list (in proper sequence), starting at the specified position in the list. The specified index indicates the first element that would be returned by an initial call to next. An initial call to previous would return the element with the specified index minus one.
	 * 
	 * @param startingIndex index of the first element to be returned from the list iterator (by a call to next)
	 * 
	 * @return a list iterator over the elements in this list (in proper sequence), starting at the specified position in the list
	 * 
	 * @throws IndexOutOfBoundsException if the index is out of range (index < 0 || index > size())
	 */
	public ListIterator<T> listIterator(int startingIndex) {
		return new DLLIterator(startingIndex);
	}

	private class DLLIterator implements ListIterator<T>
	{
		private LinearNode<T> nextNode;
		private LinearNode<T> lastReturned;
		private int index;
		private boolean canSet, canRemove;
		
		public DLLIterator(){
			nextNode = head;
			lastReturned = null;
			index = 0;
			canSet = false;
		}
		
		public DLLIterator(int startingIndex)
		{
			if(startingIndex < 0 || startingIndex > count)
				throw new IndexOutOfBoundsException("DLL");
			nextNode = head;
			lastReturned = null;
			index = 0;
			canSet = false;
			canRemove = false;
			
			for(int i = 0; i < startingIndex; i++)
			{
				next();
			}
				
		}

		/**
		 * Inserts the specified element into the list (optional operation). The element is inserted immediately
		 *  before the element that would be returned by next(), if any, and after the element that would be returned by previous(), if any
		 *  
		 *  @param e - the element to insert
		 */
		public void add(T e) {
			LinearNode<T> current = new LinearNode<T>(e);
			LinearNode<T> prev = null;
			
			if(nextNode == null && nextNode == head && nextNode == tail) //if linked list is empty
			{
				head = tail = current;
			}
			
			else if(nextNode == null && nextNode != tail) //if the current index is at the end of the list, next to tail
			{
				tail.setNext(current);
				current.setPrev(tail);
				tail = current;
			}
			
			else if(nextNode == head && nextNode == tail) //if the first condition is false, then this condition will be true if there is a one node list
			{
				nextNode.setPrev(current);
				current.setNext(current);
				current = head;
			}
			
			else if(nextNode == head && nextNode != tail) //if nextNode is head and the list contains more than one node
			{
				nextNode.setPrev(current);
				current.setNext(current);
				current = head;
			}
			
			else{ // any other case
				prev = nextNode.getPrev();
				prev.setNext(current);
				nextNode.setPrev(current);
				current.setPrev(prev);
				current.setNext(nextNode);
				
				if(nextNode.getNext() == null)
					tail = nextNode;
			}
			canSet = false;
			canRemove = false;
		}

		/**
		 * Returns true if this list iterator has more elements when traversing the list in the forward direction
		 * 
		 * @return true if the list iterator has more elements when traversing the list in the forward direction
		 */
		public boolean hasNext() {
			if(head == null)
				return false;
			else
			{
				return (nextNode != null);
			}
		}

		/**
		 * Returns true if this list iterator has more elements when traversing the list in the reverse direction. 
		 * 
		 * @return true if the list iterator has more elements when traversing the list in the reverse direction
		 */
		public boolean hasPrevious() {
			if(head == null)
				return false;
			else
				return (lastReturned != null);
		}

		/**
		 * Returns the next element in the list and advances the cursor position. This method may be called
		 *  repeatedly to iterate through the list, or intermixed with calls to previous() to go back and forth
		 * 
		 * @return the next element in the list
		 * @throws NoSuchElementException - if the iteration has no next element
		 */
		public T next() {
			if(!hasNext()) 
				throw new NoSuchElementException();
			
			lastReturned = nextNode;
			nextNode = nextNode.getNext();
			index++;
			canSet = true;
			canRemove = true;
			
			return lastReturned.getElement();
		}
		
		/**
		 * Returns the previous element in the list and moves the cursor position backwards. This method may be called
		 *  repeatedly to iterate through the list backwards, or intermixed with calls to next() to go back and forth
		 *  
		 *  @return the previous element in the list
		 *  @throws NoSuchElementException - if the iteration has no previous element
		 */
		public T previous() {
			if(!hasPrevious()) 
				throw new NoSuchElementException();
			nextNode = lastReturned;
			
			canSet = true;
			canRemove =true;
			index--;
			return lastReturned.getElement();
		}

		/**
		 * Returns the index of the element that would be returned by a subsequent call to next()
		 * 
		 * @return the index of the element that would be returned by a subsequent call to next, or list size if the list iterator is at the end of the list
		 */
		public int nextIndex() {
			if(!hasNext())
				return size();
			else
				return (index);
		}

		/**
		 * Returns the index of the element that would be returned by a subsequent call to previous()
		 * 
		 * @return the index of the element that would be returned by a subsequent call to previous, or -1 if the list iterator is at the beginning of the list
		 */
		public int previousIndex() {
			if(index-1<0)
				return -1;
			else
				return index-1;
		}

		/**
		 * Removes from the list the last element that was returned by next() or previous() (optional operation)
		 * 
		 * @throws IllegalStateException - if neither next nor previous have been called, or remove or add have been called after the last call to next or previous
		 */
		public void remove() {
			if(lastReturned == null)
				throw new IllegalStateException("DLL");
			if(!canRemove)
			{
				throw new IllegalStateException("DLL");
			}
			if(lastReturned == nextNode) //if previous() was the last called method
			{
				if(head == tail && head != null) //if list has only one node
				{
					head = tail = null;
				}
				
				else if(lastReturned == head && lastReturned != tail) //if lastReturned is at head but the list is more than one node
				{
					LinearNode<T> next = null;
					next = nextNode.getNext();
					next.setPrev(null);
					head = next;
				}
				else if(lastReturned != head && lastReturned == tail){//if lastReturned is at tail but the list is more than one node
					tail = tail.getPrev();
					tail.setNext(null);
				}
					
				else{ // any other case
					LinearNode<T> prev = nextNode.getPrev();
					LinearNode<T> next = nextNode.getNext();
					prev.setNext(next);
					next.setPrev(prev);
				}
			}
			
			else{ //if next() was last called method
				if(head == tail) //if the list has only one node 
				{
					head = tail = null;
				}
				else if(lastReturned == head) //if lastReturned is at the head
				{
					nextNode.setPrev(null);
					head = nextNode;
				}
				else if(lastReturned == tail)// if lastReturned is at the tail
				{
					tail = tail.getPrev();
					tail.setNext(null);
				}
				
				else // any other possible case
				{
					LinearNode<T> prev = null;
					prev = lastReturned.getPrev();
					prev.setNext(nextNode);
					nextNode.setPrev(prev);
				}
			}
			canSet = false;
		}

		/**
		 * Replaces the last element returned by next() or previous() with the specified element (optional operation). This call can be made only if neither
		 *  remove() nor add(E) have been called after the last call to next or previous.
		 * 
		 * @throws IllegalStateException - if neither next nor previous have been called, or remove or add have been called after the last call to next or previous
		 */
		public void set(T element) {
			if(lastReturned == null)
				throw new IllegalStateException("DLL");
			if(!canSet)
				throw new IllegalStateException("DLL");
			
			lastReturned.setElement(element); // set current lastReturned to carry @param element 
		}
	}
}
