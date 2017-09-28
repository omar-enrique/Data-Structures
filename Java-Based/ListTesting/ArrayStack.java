
public class ArrayStack<T> implements StackADT<T>{
	private ArrayList<T> theStack;
	
	public ArrayStack(){
		theStack = new ArrayList<T>();
	}
	
	@Override
	public void push(T element) {
		theStack.addToRear(element);
	}

	@Override
	public T pop() {
		return theStack.removeLast();
	}

	@Override
	public T peek() {
		return theStack.last();
	}

	@Override
	public boolean isEmpty() {
		return theStack.isEmpty();
	}

	@Override
	public int size() {
		return theStack.size();
	}

	public String toString()
	{
		return theStack.toString();
	}
}
