package com.acj.aprendiendoconjuancho;

import javafx.geometry.BoundingBox;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.shape.Rectangle;

public class SortRoundItem {
    private double startX;
    private double startY;
    private final Label letterNode;
    private Point2D letter;
    private final Rectangle rectangle;
    private Boolean isCorrect = false;

    public Boolean getCorrect() {
        return isCorrect;
    }

    public void setCorrect(Boolean correct) {
        isCorrect = correct;
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public Point2D getLetter() {
        return letter;
    }

    public Label getLetterNode() {
        return letterNode;
    }

    public SortRoundItem(Label wordNode, Rectangle container) {
        this.letterNode = wordNode;
        this.rectangle = container;
        this.letter = this.letterNode.localToScene(0.0,0.0);
    }

    public void makeDraggable() {
        letterNode.setOnMousePressed(e -> {
            startX = e.getSceneX() - letterNode.getTranslateX();
            startY = e.getSceneY() - letterNode.getTranslateY();
        });

        letterNode.setOnMouseDragged(e -> {
            letterNode.setTranslateX(e.getSceneX() -startX);
            letterNode.setTranslateY(e.getSceneY() -startY);

        });

        letterNode.setOnMouseReleased(e -> {
            letter = letterNode.localToScene(0.0,0.0);
        });
    }

    public void removeDraggable() {
        letterNode.setOnMousePressed(null);
        letterNode.setOnMouseDragged(null);
        letterNode.setOnMouseReleased(null);
    }
}
