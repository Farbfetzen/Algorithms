package farbfetzen.algorithms;

import java.util.List;

public interface AlgorithmRunner {

    boolean hasAlgorithm(final String algorithmName);

    void run(String algorithmName, List<String> args);

}
