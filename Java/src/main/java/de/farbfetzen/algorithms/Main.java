package de.farbfetzen.algorithms;

import lombok.extern.slf4j.Slf4j;

import de.farbfetzen.algorithms.sorting.SortingAlgorithmRunner;

@Slf4j
public class Main {

    public static void main(final String[] args) {
        // TODO: Proper argument parsing.
        if (args.length > 0 && !args[0].isBlank()) {
            final var algorithmName = args[0];
            if (SortingAlgorithmRunner.isSortingAlgorithm(algorithmName)) {
                SortingAlgorithmRunner.run(algorithmName, args);
            } else {
                logger.error("Unknown algorithm name '{}'", algorithmName);
            }
        } else {
            logger.error("Missing argument. Please provide the name of an algorithm as the first argument. "
                    + "If it contains spaces it must be quoted.");
            System.exit(1);
        }
    }

}
