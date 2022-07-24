package de.farbfetzen.algorithms;

import lombok.extern.slf4j.Slf4j;

import de.farbfetzen.algorithms.sorting.SortingAlgorithmRunner;

@Slf4j
public class Main {

    private static final AlgorithmRunner[] runners = {new SortingAlgorithmRunner()};

    public static void main(final String[] args) {
        // TODO: Proper argument parsing.
        if (args.length > 0 && !args[0].isBlank()) {
            final var algorithmName = args[0];
            boolean foundAlgorithm = false;
            for (final AlgorithmRunner runner : runners) {
                if (runner.hasAlgorithm(algorithmName)) {
                    foundAlgorithm = true;
                    runner.run(algorithmName, args);
                }
            }
            if (!foundAlgorithm) {
                logger.error("Unknown algorithm name '{}'", algorithmName);
            }
        } else {
            logger.error("Missing argument. Please provide the name of an algorithm as the first argument. "
                    + "If it contains spaces it must be quoted.");
            System.exit(1);
        }
    }

}
