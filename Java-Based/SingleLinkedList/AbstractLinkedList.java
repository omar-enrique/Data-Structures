import java.util.*;

/**
 * LinkedList represents a linked implementation of a list.
 * 
 * @author Java Foundations
 * @version 4.0
 */
public abstract class AbstractLinkedList<T> implements ListADT<T>
{
	protected int count;
	protected LinearNode<T> head, tail;
	protected int modCount;

	/**
	 * Creates an empty list.
	 */
	public AbstractLinkedList()
	{
		count = 0;
		head = tail = null;
		modCount = 0;
	}

	/**
	 * Removes the first element in this list and returns a reference
	 * to it. Throws an EmptyCollectionException if the list is empty.
	 *
	 * @return a reference to the first element of this list
	 * @throws EmptyCollectionException if the list is empty
	 */
	public T removeFirst() throws EmptyCollectionException
	{
		if(isEmpty())
			throw new EmptyCollectionException("LinkedList");
		T retVal = head.getElement();
		
		if(head == tail)
			tail = null;
		
		head = head.getNext();
		
		modCount++;
		count--;
		
		return retVal;
	}

	/**
	 * Removes the last element in this list and returns a reference
	 * to it. Throws an EmptyCollectionException if the list is empty.
	 *
	 * @return the last element in this list
	 * @throws EmptyCollectionException if the list is empty    
	 */
	public T removeLast() throws EmptyCollectionException
	{
		if(isEmpty())
			throw new EmptyCollectionException("LinkedList");
		
		T retVal = tail.getElement();
		
		if(head == tail)
		{
			head = tail = null;
		}
		
		else
		{
			LinearNode<T> prev = head;
			while(prev.getNext() != tail)
			{
				prev = prev.getNext();
			}
		
			prev.setNext(null);
			tail = prev;
		}
		
		modCount++;
		count--;
		
		return retVal;
	}

	/**
	 * Removes the first instance of the specified element from this
	 * list and returns a reference to it. Throws an EmptyCollectionException 
	 * if the list is empty. Throws a ElementNotFoundException if the 
	 * specified element is not found in the list.
	 *
	 * @param  targetElement the element to be removed from the list
	 * @return a reference to the removed element
	 * @throws ElementNotFoundException if the target element is not found
	 */
	public T remove(T targetElement) throws ElementNotFoundException 
	{
		if (isEmpty())
			throw new ElementNotFoundException("LinkedList");

		boolean found = false;
		LinearNode<T> previous = null;
		LinearNode<T> current = head;

		while (current != null && !found)
			if (targetElement.equals(current.getElement()))
				found = true;
			else
			{
				previous = current;
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
			tail = previous;
			tail.setNext(null);
		}
		else  // target is in the middle
			previous.setNext(current.getNext());

		count--;
		modCount++;

		return current.getElement();
	}

	/**
	 * Returns the first element in this list without removing it. 
	 *
	 * @return the first element in this list
	 * @throws EmptyCollectionException if the list is empty
	 */
	public T first() throws EmptyCollectionException
	{
		if(isEmpty())
			throw new EmptyCollectionException("LinkedList");
		return head.getElement();
	}

	/**
	 * Returns the last element in this list without removing it. 
	 *
	 * @return the last element in this list  
	 * @throws EmptyCollectionException if the list is empty
	 */
	public T last() throws EmptyCollectionException
	{
		if(isEmpty())
			throw new EmptyCollectionException("LinkedList");
		
		return tail.getElement();
	}

	/**
	 * Returns true if the specified element is found in this list and 
	 * false otherwise.
	 *
	 * @param  targetElement the element that is sought in the list
	 * @return true if the element is found in this list
	 */
	public boolean contains(T targetElement) 
	{
		boolean foundIt = false;
		
		Iterator<T> it = this.iterator();
		while(it.hasNext() && !foundIt)
		{
			if(it.next().equals(targetElement))
				foundIt = true;
		}
		
		
		return foundIt;
	}

	/**
	 * Returns true if this list is empty and false otherwise.
	 *
	 * @return true if the list is empty, false otherwise
	 */
	public boolean isEmpty()
	{
		return (head == null);
	}

	/**
	 * Returns the number of elements in this list.
	 *
	 * @return the number of elements in the list
	 */
	public int size()
	{
		return count;
	}

	/**
	 * Returns a string representation of this list.
	 *
	 * @return a string representation of the list    
	 */
	public String toString()
	{
		StringBuilder str = new StringBuilder();
		str.append("[");
		
		
		//Option A
		Iterator<T> it = iterator();
		while(it.hasNext())
		{
			str.append(it.next());
			str.append(",");
		}
		
		
		str.append("]");		
		
		if(count > 0)
			str.delete(str.length()-2, str.length());

		
		return str.toString();
	}

	/**
	 * Returns an iterator for the elements in this list. 
	 *
	 * @return an iterator over the elements of the list
	 */
	public Iterator<T> iterator()
	{
		return new LinkedListIterator();
	}

	/**
	 * LinkedIterator represents an iterator for a linked list of linear nodes.
	 */
	private class LinkedListIterator implements Iterator<T>
	{
		private int iteratorModCount;  // the "version" of the list at the time the Iterator was created
		private LinearNode<T> current;  // the current position
		private boolean canRemove;

		/**
		 * Sets up this iterator using the specified items.
		 *
		 * @param collection  the collection the iterator will move over
		 * @param size        the integer size of the collection
		 */
		public LinkedListIterator()
		{
			current = head;
			iteratorModCount = modCount;
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
			if (iteratorModCount != modCount) 
				throw new ConcurrentModificationException();

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
			if (!hasNext())
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
			if(iteratorModCount != modCount)
			{
				throw new ConcurrentModificationException();
			}
			
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
				}
			}
			count--;
			modCount++;
			iteratorModCount++;
			canRemove = false;
		}
	}

}


