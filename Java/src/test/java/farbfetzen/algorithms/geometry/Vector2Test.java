package farbfetzen.algorithms.geometry;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

class Vector2Test {

    @Test
    void shouldSortByXAndThenByY() {
        final var p1 = new Vector2(-5.1, -3);
        final var p2 = new Vector2(0.1, 1.2);
        final var p3 = new Vector2(1, -1);
        final var p4 = new Vector2(1, 1);
        final var p5 = new Vector2(2.5, 0);
        final var p6 = new Vector2(2.5, 9.7);
        final var p7 = new Vector2(2.5, 9.7);
        final Vector2[] points = {p6, p4, p1, p5, p7, p3, p2};
        Arrays.sort(points);
        assertThat(points).containsExactly(p1, p2, p3, p4, p5, p6, p7);
    }

    @Test
    void noArgsConstructorShouldSetZeros() {
        final var vector = new Vector2();
        assertThat(vector.getX()).isZero();
        assertThat(vector.getY()).isZero();
    }

}
