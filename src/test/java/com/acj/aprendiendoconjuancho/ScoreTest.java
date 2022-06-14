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
    }

    @Test
    void addScore() {
        score.addScore(Levels.MATCH, 2);
        score.addScore(Levels.SORT, 3);
        score.addScore(Levels.LISTEN, 4);
        Assertions.assertEquals(20, score.getLevelScore(Levels.MATCH));
        Assertions.assertEquals(30, score.getLevelScore(Levels.SORT));
        Assertions.assertEquals(50, score.getLevelScore(Levels.LISTEN));
    }

    @Test
    void getOverallScore() {
    }

    @Test
    void getLevelScore() {
    }

    @Test
    void updateDifficulty() {
    }
}