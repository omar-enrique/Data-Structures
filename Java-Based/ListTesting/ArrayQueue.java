import java.util.Arrays;


public class ArrayQueue<T> implements QueueADT<T> {
	private T[] theQueue;
	private int size;
	private int first;
	private int rear;
	
	public ArrayQueue(){
		theQueue = (T[])new Object[10];
		size = 0;
		first = 0;
		rear = 0;
	}
	
	private void expandCapacity()
	{
		T[] bigOne = (T[]) new Object[theQueue.length*2];
		for(int index = 0; index < size(); index++)
		{
			bigOne[index] = theQueue[first];
			first = (first + 1) % theQueue.length;
		}
		
		first = 0;
		rear = size();
		theQueue = bigOne;
	}
	
	@Override
	public void enqueue(T element) {
		if(size() == theQueue.length)
			expandCapacity();
		
		theQueue[rear] = element;
		size++;
		rear = (rear+1)%theQueue.length;
	}

	@Override
	public T dequeue() {
		if(isEmpty())
			throw new EmptyCollectionException("Queue");
		T retVal = theQueue[first];
		
		theQueue[first] = null;
		
		first = (first + 1) % theQueue.length;
		size--;
		
		return retVal;
	}

	@Override
	public T first() {
		if(isEmpty())
			throw new EmptyCollectionException("Queue");
		return theQueue[first];
	}

	@Override
	public boolean isEmpty() {
		return (first == rear);
	}

	@Override
	public int size() {
		return size;
	}
	
}
