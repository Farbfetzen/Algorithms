package farbfetzen.algorithms.geometry;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.function.Function;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;
import processing.core.PApplet;

public class GeometryAlgorithmRunner{

    @Parameters(commandDescription = "Arguments for geometry algorithms.")
    public static class GeometryArguments {

        @Parameter(description = "algorithm name", required = true)
        private String name;

        @Parameter(names = "-n", description = "The number of points.")
        private Integer numberOfElements = 10;

    }

    private static final Map<String, Function<List<Vector2>, ConvexHull>> algorithms = Map.of(
            "convex_hull", ConvexHull::new
    );

    private static final double MARGIN = 50;

    private static final Random random = new Random();

        public void run(final GeometryArguments arguments) {
        final var constructor = algorithms.get(arguments.name);
        if (constructor == null) {
            System.err.println("Unknown geometry algorithm '" + arguments.name + "'.");
            return;
        }
        System.out.printf("Running %s with %d points.%n", arguments.name, arguments.numberOfElements);
        final var points = new ArrayList<Vector2>(10);
        for (int i = 0; i < arguments.numberOfElements; i++) {
            points.add(new Vector2(
                    random.nextDouble(MARGIN, GeometryVisualisation.CANVAS_WIDTH - MARGIN),
                    random.nextDouble(MARGIN, GeometryVisualisation.CANVAS_HEIGHT - MARGIN)
            ));
        }
        final var algorithm = constructor.apply(points);
        GeometryVisualisation.setAlgorithm(algorithm);
        PApplet.main(GeometryVisualisation.class);
    }

}
