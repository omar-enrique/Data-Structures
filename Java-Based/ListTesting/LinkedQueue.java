
public class LinkedQueue<T> implements QueueADT<T> {
	private SingleLinkedList<T> theQueue;
	
	public LinkedQueue()
	{
		theQueue = new SingleLinkedList<T>();
	}
	
	@Override
	public void enqueue(T element) {
		theQueue.addToRear(element);
	}

	@Override
	public T dequeue() {
		return theQueue.removeFirst();
	}

	@Override
	public T first() {
		return theQueue.first();
	}

	@Override
	public boolean isEmpty() {
		return theQueue.isEmpty();
	}

	@Override
	public int size() {
		return theQueue.size();
	}
	
	public String toString()
	{
		return theQueue.toString();
	}
}
