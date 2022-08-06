package de.farbfetzen.algorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;

import lombok.extern.slf4j.Slf4j;

import de.farbfetzen.algorithms.sorting.SortingAlgorithmRunner;

@Slf4j
public class Main {

    private static final AlgorithmRunner[] runners = {new SortingAlgorithmRunner()};

    public static void main(final String[] arguments) {
        final var args = new ArrayList<>(Arrays.asList(arguments));
        if (args.isEmpty()) {
            throw new IllegalArgumentException("Please provide the name of an algorithm as the first argument.");
        }
        final var algorithmName = args.remove(0).toLowerCase(Locale.ENGLISH);
        for (final AlgorithmRunner runner : runners) {
            if (runner.init(algorithmName, args)) {
                runner.run();
                return;
            }
        }
        throw new IllegalArgumentException("Unknown algorithm name '" + algorithmName + "'.");
    }

}
