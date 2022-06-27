package com.acj.aprendiendoconjuancho;

import javafx.geometry.BoundingBox;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.control.Label;

public class MatchRoundItem {
    private double startX;
    private double startY;
    private Point2D word;
    private Point2D circle;
    private BoundingBox bound;
    private final Label wordNode;
    private final Node imageTarget;
    private Boolean isOnTarget;

    public Label getWordNode() {
        return wordNode;
    }

    public Node getImageTarget() {
        return imageTarget;
    }



    public Boolean getOnTarget() {
        return isOnTarget;
    }

    public MatchRoundItem(Label wordNode, Node imageTarget) {
        this.wordNode = wordNode;
        this.imageTarget = imageTarget;
        isOnTarget = false;
    }

    public void makeDraggable() {
        wordNode.setOnMousePressed(e -> {
            startX = e.getSceneX() - wordNode.getTranslateX();
            startY = e.getSceneY() - wordNode.getTranslateY();
        });

        wordNode.setOnMouseDragged(e -> {
            wordNode.setTranslateX(e.getSceneX() -startX);
            wordNode.setTranslateY(e.getSceneY() -startY);

        });

        wordNode.setOnMouseReleased(e -> {
            word = wordNode.localToScene(0.0,0.0);
            circle = imageTarget.localToScene(0.0, 0.0);
            bound = new BoundingBox(circle.getX(), circle.getY(), 160, 160);
            isOnTarget = bound.contains(word);
        });
    }

    public void removeDraggable() {
        wordNode.setOnMousePressed(null);
        wordNode.setOnMouseDragged(null);
        wordNode.setOnMouseReleased(null);
    }
}
