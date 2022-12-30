package farbfetzen.algorithms;

import java.util.List;

public interface AlgorithmRunner {

    boolean init(String algorithmName, List<String> args);

    void run();

}
