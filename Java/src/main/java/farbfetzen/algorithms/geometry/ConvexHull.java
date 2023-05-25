package farbfetzen.algorithms.geometry;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class ConvexHull {

    private final List<Vector2> vertices;

    private List<Vector2> hull;

    ConvexHull(final List<Vector2> vertices) {
        this.vertices = vertices;
        run();
    }

    private void run() {
        if (vertices.size() < 3) {
            hull = vertices;
            return;
        }
        // TODO: Check if removing points using the Akl-Toussaint heuristic prior to sorting
        //  makes the algorithm faster for large numbers of points.
        Collections.sort(vertices);
        hull = constructUpperHull();
        final var lowerHull = constructLowerHull();
        hull.addAll(lowerHull.subList(1, lowerHull.size() - 1));
    }

    private List<Vector2> constructUpperHull() {
        final var upperHull = new ArrayList<Vector2>();
        upperHull.add(vertices.get(0));
        upperHull.add(vertices.get(1));
        for (int i = 2; i < vertices.size(); i++) {
            upperHull.add(vertices.get(i));
            while (upperHull.size() >= 3 && lastThreeMakeLeftTurn(upperHull)) {
                upperHull.remove(upperHull.size() - 2);
            }
        }
        return upperHull;
    }

    private List<Vector2> constructLowerHull() {
        final var lowerHull = new ArrayList<Vector2>();
        lowerHull.add(vertices.get(vertices.size() - 1));
        lowerHull.add(vertices.get(vertices.size() - 2));
        for (int i = vertices.size() - 3; i >= 0; i--) {
            lowerHull.add(vertices.get(i));
            while (lowerHull.size() >= 3 && lastThreeMakeLeftTurn(lowerHull)) {
                lowerHull.remove(lowerHull.size() - 2);
            }
        }
        return lowerHull;
    }

    private static boolean lastThreeMakeLeftTurn(final List<Vector2> vertices) {
        final var lastThree = vertices.subList(vertices.size() - 3, vertices.size());
        final var a = lastThree.get(0);
        final var b = lastThree.get(1);
        final var c = lastThree.get(2);
        // If the cross product is < 0 then the points are either collinear or make a left turn.
        // This is only true if y = 0 is at the top. If it is at the bottom then a cross product < 0
        // indicates a right turn.
        return ((b.getX() - a.getX()) * (c.getY() - a.getY()) - (b.getY() - a.getY()) * (c.getX() - a.getX())) < 0;
    }

    List<Vector2> getVertices() {
        return vertices;
    }

    public List<Vector2> getHull() {
        return hull;
    }

}
