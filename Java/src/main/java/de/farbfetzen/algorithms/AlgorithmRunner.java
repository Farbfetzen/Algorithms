package de.farbfetzen.algorithms;

public interface AlgorithmRunner {

    boolean hasAlgorithm(String algorithmName);

    void run(String algorithmName, String[] args);

}
