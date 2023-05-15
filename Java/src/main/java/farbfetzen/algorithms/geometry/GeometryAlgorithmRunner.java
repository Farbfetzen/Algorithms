package farbfetzen.algorithms.geometry;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import farbfetzen.algorithms.AlgorithmRunner;
import farbfetzen.algorithms.util.Vector2;

public class GeometryAlgorithmRunner implements AlgorithmRunner {

    private static final Logger logger = LoggerFactory.getLogger(GeometryAlgorithmRunner.class);

    private String algorithmName;
    private GeoAlgo geoAlgo;

    enum GeoAlgo {
        CONVEX_HULL("convex hull", ConvexHull::new);

        final String name;
        final Function<Vector2[], ConvexHull> constructor;

        GeoAlgo(final String name, final Function<Vector2[], ConvexHull> constructor) {
            this.name = name;
            this.constructor = constructor;
        }

        static Optional<GeoAlgo> getByName(final String name) {
            return Arrays.stream(values()).filter(v -> v.name.equals(name)).findFirst();
        }
    }

    @Override
    public boolean init(final String algorithmName, final List<String> args) {
        final Optional<GeoAlgo> maybeAlgo = GeoAlgo.getByName(algorithmName);
        if (maybeAlgo.isEmpty()) {
            return false;
        }
        geoAlgo = maybeAlgo.get();
        this.algorithmName = algorithmName;
        return true;
    }

    @Override
    public void run() {
        logger.info("Running {}.", algorithmName);
    }

}
