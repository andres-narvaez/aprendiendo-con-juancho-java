package com.acj.aprendiendoconjuancho;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ScoreTest {
    private Score score;

    @BeforeEach
    void setUp() {
        score = new Score(Difficulty.LOW);
    }

    @AfterEach
    void tearDown() {
        score = null;
    }

    @Test
    void addScore() {
        score.addScore(Levels.MATCH, 2);
        score.addScore(Levels.SORT, 3);
        score.addScore(Levels.LISTEN, 4);
        assertEquals(20, score.getLevelScore(Levels.MATCH));
        assertEquals(30, score.getLevelScore(Levels.SORT));
        assertEquals(50, score.getLevelScore(Levels.LISTEN));
    }

    @Test
    void getOverallScore() {
        score.addScore(Levels.MATCH, 4);
        score.addScore(Levels.SORT, 4);
        score.addScore(Levels.LISTEN, 4);
        assertEquals(150, score.getOverallScore());
    }

    @Test
    void getLevelScore() {
        score.addScore(Levels.MATCH, 4);
        assertEquals(50, score.getLevelScore(Levels.MATCH));
    }

    @Test
    void updateDifficulty() {
        assertEquals(Difficulty.LOW, score.getDifficulty());
        score.updateDifficulty(Difficulty.MEDIUM);
        assertEquals(Difficulty.MEDIUM, score.getDifficulty());
    }
}