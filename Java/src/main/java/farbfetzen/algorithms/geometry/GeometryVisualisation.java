package farbfetzen.algorithms.geometry;

import processing.core.PApplet;

public class GeometryVisualisation extends PApplet {

    public static final int CANVAS_WIDTH = 1200;
    public static final int CANVAS_HEIGHT = 800;
    private static final int POINT_SIZE = 7;
    private static final int LINE_WIDTH = 2;

    private static ConvexHull algorithm;

    private final int backgroundColor = color(40, 50, 55);
    private final int pointColor = color(0, 255, 0);
    private final int hullColor = color(255, 128, 0);

    @Override
    public void settings() {
        size(CANVAS_WIDTH, CANVAS_HEIGHT);
    }

    @Override
    public void setup() {
        noFill();
    }

    @Override
    public void draw() {
        background(backgroundColor);

        stroke(pointColor);
        strokeWeight(POINT_SIZE);
        algorithm.getVertices().forEach(v -> point(v.getXf(), v.getYf()));

        stroke(hullColor);
        strokeWeight(LINE_WIDTH);
        beginShape();
        algorithm.getHull().forEach(v -> {
            vertex(v.getXf(), v.getYf());
            circle(v.getXf(), v.getYf(), POINT_SIZE);
        });
        endShape(CLOSE);
    }

    public static void setAlgorithm(final ConvexHull algorithm) {
        GeometryVisualisation.algorithm = algorithm;
    }

}
