package farbfetzen.algorithms.geometry;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.junit.jupiter.api.Test;

class ConvexHullTest {

    private static final Random random = new Random();

    @Test
    void shouldConstructHullFrom3Points() {
        final var points = Arrays.asList(new Vector2(0, 0), new Vector2(1, -1), new Vector2(1, 0));
        final var algorithm = new ConvexHull(points);
        assertThat(algorithm.getHull()).isEqualTo(points);
    }

    @Test
    void shouldConstructHull() {
        final var n = 1000;
        final var points = new ArrayList<Vector2>(n + 4);
        for (int i = 0; i < n; i++) {
            points.add(new Vector2(random.nextDouble(), random.nextDouble()));
        }
        final var expectedHull = List.of(
                new Vector2(-1, -1),
                new Vector2(2, -1),
                new Vector2(2, 2),
                new Vector2(-1, 2)
        );
        points.addAll(expectedHull);
        final var algorithm = new ConvexHull(points);
        assertThat(algorithm.getHull()).isEqualTo(expectedHull);
    }

}
