package unibo.citysimulation.utilities;

/**
 * Represents a generic pair of values.
 *
 * @param <T1> the type of the first value
 * @param <T2> the type of the second value
 */
public class Pair<T1, T2> {
    private T1 first;
    private T2 second;

    /**
     * Constructs a new Pair object with the specified values.
     *
     * @param first  the first value
     * @param second the second value
     */
    public Pair(T1 first, T2 second) {
        this.first = first;
        this.second = second;
    }

    /**
     * Returns the first value of the pair.
     *
     * @return the first value
     */
    public T1 getFirst() {
        return first;
    }

    /**
     * Returns the second value of the pair.
     *
     * @return the second value
     */
    public T2 getSecond() {
        return second;
    }

    /**
     * Checks if this Pair is equal to another object.
     *
     * @param obj the object to compare with
     * @return true if the objects are equal, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Pair) {
            Pair<?, ?> other = (Pair<?, ?>) obj;
            return this.first.equals(other.first) && this.second.equals(other.second);
        }
        return false;
    }

    /**
     * Returns the hash code value for this Pair.
     *
     * @return the hash code value
     */
    @Override
    public int hashCode() {
        return first.hashCode() + second.hashCode();
    }
}
