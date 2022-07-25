package de.farbfetzen.algorithms.sorting;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import processing.core.PApplet;

@Slf4j
public class SortingVisualisation extends PApplet {

    @Setter
    private static StepWiseSorter algorithm;

    private static final int CANVAS_WIDTH = 1200;
    private static final int CANVAS_HEIGHT = 800;
    private static final int MARGIN_TOP = 50;

    private final int backgroundColor = color(40, 50, 55);
    private final int columnColor = color(25, 215, 240);
    private final int comparisonColor = color(240, 40, 25);
    private final int highlightColor = color(20, 220, 20);
    private int n;
    private float columnWidth;
    private float columnHeightMultiplier;
    private boolean runAuto = false;
    private boolean turbo = false;

    @Override
    public void settings() {
        size(CANVAS_WIDTH, CANVAS_HEIGHT);
    }

    @Override
    public void setup() {
        final var array = algorithm.getArray();
        n = array.length;
        columnWidth = (float) CANVAS_WIDTH / n;
        columnHeightMultiplier = (float) (CANVAS_HEIGHT - MARGIN_TOP) / max(array);

        stroke(backgroundColor);
        if (columnWidth < 3) {
            noStroke();
        } else {
            strokeWeight(1);
        }
        fill(color(25, 215, 240));
    }


    @Override
    public void draw() {
        if (runAuto) {
            if (turbo) {
                for (int i = 0; i < 10; i++) {
                    algorithm.step();
                }
            } else {
                algorithm.step();
            }
        }

        background(backgroundColor);
        for (int i = 0; i < n; i++) {
            if (algorithm.isFinished() || algorithm.getHighlights().contains(i)) {
                fill(highlightColor);
            } else if (algorithm.getComparisons().contains(i)) {
                fill(comparisonColor);
            } else {
                fill(columnColor);
            }
            final var columnHeight = algorithm.getArray()[i] * columnHeightMultiplier;
            rect(i * columnWidth, CANVAS_HEIGHT - columnHeight, columnWidth, columnHeight);
        }
    }

    @Override
    public void keyPressed() {
        switch (key) {
            case 's' -> algorithm.step();
            case ' ' -> runAuto = !runAuto;
            case 't' -> turbo = !turbo;
            default -> {/* Key not bound.*/}
        }
    }

}
