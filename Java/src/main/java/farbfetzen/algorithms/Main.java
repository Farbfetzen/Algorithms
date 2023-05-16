package farbfetzen.algorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;

import farbfetzen.algorithms.geometry.GeometryAlgorithmRunner;
import farbfetzen.algorithms.sorting.SortingAlgorithmRunner;

public class Main {

    private static final AlgorithmRunner[] runners = {new GeometryAlgorithmRunner(), new SortingAlgorithmRunner()};

    public static void main(final String[] arguments) {
        final var args = new ArrayList<>(Arrays.asList(arguments));
        if (args.isEmpty()) {
            throw new IllegalArgumentException("Please provide the name of an algorithm as the first argument.");
        }
        final var algorithmName = args.remove(0).toLowerCase(Locale.ENGLISH);
        for (final AlgorithmRunner runner : runners) {
            if (runner.hasAlgorithm(algorithmName)) {
                runner.run(algorithmName, args);
                return;
            }
        }
        throw new IllegalArgumentException("Unknown algorithm name '" + algorithmName + "'.");
    }

}