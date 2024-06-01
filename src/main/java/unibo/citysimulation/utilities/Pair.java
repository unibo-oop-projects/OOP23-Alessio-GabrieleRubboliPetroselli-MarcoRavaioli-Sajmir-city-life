package unibo.citysimulation.utilities;

import java.io.Serializable;

/**
 * Represents a generic pair of values.
 *
 * @param <F> the type of the first value
 * @param <S> the type of the second value
 */
public class Pair<F, S> implements Serializable{
    private static final long serialVersionUID = 1L;
    
    private final F first;
    private final S second;

    /**
     * Constructs a new Pair object with the specified values.
     *
     * @param first  the first value
     * @param second the second value
     */
    public Pair(final F first, final S second) {
        this.first = first;
        this.second = second;
    }

    /**
     * Returns the first value of the pair.
     *
     * @return the first value
     */
    public F getFirst() {
        return first;
    }

    /**
     * Returns the second value of the pair.
     *
     * @return the second value
     */
    public S getSecond() {
        return second;
    }

    /**
     * Checks if this Pair is equal to another object.
     *
     * @param obj the object to compare with
     * @return true if the objects are equal, false otherwise
     */
    @Override
    public boolean equals(final Object obj) {
        if (obj instanceof Pair) {
            final Pair<?, ?> other = (Pair<?, ?>) obj;
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
