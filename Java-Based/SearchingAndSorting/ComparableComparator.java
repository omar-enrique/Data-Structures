import java.util.Comparator;

/**
 * Returns natural order
 * 
 * @author Omar
 *
 * @param <T> a Comparable type
 */

public class ComparableComparator <T extends Comparable<T>> implements Comparator<T> {
	@Override
	public int compare(T o1, T o2) {
		return (o1.compareTo(o2));
	}
}
