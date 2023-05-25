package farbfetzen.algorithms;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;

import farbfetzen.algorithms.geometry.GeometryAlgorithmRunner;
import farbfetzen.algorithms.sorting.SortingAlgorithmRunner;

public class Main {

    private static class MainArguments {

        @Parameter(names = {"-h", "--help"}, description = "Display this help message.", help = true)
        private boolean showHelp;

    }

    public static void main(final String[] arguments) {
        final var mainArguments = new MainArguments();
        final var sortingArguments = new SortingAlgorithmRunner.SortingArguments();
        final var geometryArguments = new GeometryAlgorithmRunner.GeometryArguments();
        final var jc = JCommander
                .newBuilder()
                .programName("algorithms")
                .addObject(mainArguments)
                .addCommand("sort", sortingArguments)
                .addCommand("geometry", geometryArguments)
                .build();

        try {
            jc.parse(arguments);
        } catch (final ParameterException e) {
            System.err.println(e.getMessage());
            jc.usage();
            System.exit(1);
        }
        if (mainArguments.showHelp) {
            jc.usage();
            System.exit(0);
        }

        switch (jc.getParsedCommand()) {
            case "sort" -> new SortingAlgorithmRunner().run(sortingArguments);
            case "geometry" -> new GeometryAlgorithmRunner().run(geometryArguments);
            default -> System.err.println("Unknown command '" + jc.getParsedCommand() + "'.");
        }
    }

}
