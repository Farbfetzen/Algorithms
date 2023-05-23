package farbfetzen.algorithms.geometry;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GeometryAlgorithmRunner{

    private static final Logger logger = LoggerFactory.getLogger(GeometryAlgorithmRunner.class);

    private static final Map<String, Function<Vector2[], ConvexHull>> algorithms = Map.of(
            "convex hull", ConvexHull::new
    );

    public void run(final String algorithmName, final List<String> args) {
        final Function<Vector2[], ConvexHull> constructor = algorithms.get(algorithmName);
        if (constructor == null) {
            return;
        }
        parseArguments(args);
        logger.info("Running {}.", algorithmName);
        // TODO: Implement the rest.
    }

    private void parseArguments(final List<String> args) {
    }

}
