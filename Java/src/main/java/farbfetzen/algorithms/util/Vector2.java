package farbfetzen.algorithms.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class Vector2 implements Comparable<Vector2> {

    private double x;
    private double y;

    /**
     * Sort Vectors in ascending order. First sort by x and break ties by sorting y.
     */
    @Override
    public int compareTo(final Vector2 other) {
        final var compareX = Double.compare(x, other.x);
        return compareX != 0 ? compareX : Double.compare(y, other.y);
    }

}
