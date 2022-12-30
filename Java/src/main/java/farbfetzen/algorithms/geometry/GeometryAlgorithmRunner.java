package farbfetzen.algorithms.geometry;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import farbfetzen.algorithms.AlgorithmRunner;
import farbfetzen.algorithms.util.Vector2;

@Slf4j
public class GeometryAlgorithmRunner implements AlgorithmRunner {

    private String algorithmName;
    private GeoAlgo geoAlgo;

    @RequiredArgsConstructor
    enum GeoAlgo {
        CONVEX_HULL("convex hull", ConvexHull::new);

        final String name;
        final Function<Vector2[], ConvexHull> constructor;

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
