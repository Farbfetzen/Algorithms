package farbfetzen.algorithms;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;

import farbfetzen.algorithms.sorting.SortingAlgorithmRunner;

public class Main {

    private static class MainArgs {

        @Parameter(names = {"-h", "--help"}, description = "Display this help message.", help = true)
        private boolean showHelp;

    }

    public static void main(final String[] args) {
        final var mainArgs = new MainArgs();
        final var sortingArgs = new SortingAlgorithmRunner.SortingArgs();
        final var jc = JCommander
                .newBuilder()
                .programName("algorithms")
                .addObject(mainArgs)
                .addCommand("sort", sortingArgs)
                .build();

        try {
            jc.parse(args);
        } catch (final ParameterException e) {
            System.err.println(e.getMessage());
            jc.usage();
            System.exit(1);
        }
        if (mainArgs.showHelp) {
            jc.usage();
            System.exit(0);
        }

        if (jc.getParsedCommand().equals("sort")) {
            new SortingAlgorithmRunner().run(sortingArgs);
        }
    }

}
