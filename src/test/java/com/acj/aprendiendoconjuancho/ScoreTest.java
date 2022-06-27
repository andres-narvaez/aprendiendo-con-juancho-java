package com.acj.aprendiendoconjuancho;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ScoreTest {
    private Difficulty mockDifficulty;
    private Score scoreInstance;

    @BeforeEach
    void setUp() {
        mockDifficulty = Difficulty.LOW;
        scoreInstance = Score.getInstance(mockDifficulty);
    }

    @AfterEach
    void tearDown() {
        mockDifficulty = null;
        scoreInstance = null;
    }

    @Test
    void addScore() {
        scoreInstance.addScore(Levels.MATCH, 2);
        scoreInstance.addScore(Levels.SORT, 3);
        scoreInstance.addScore(Levels.LISTEN, 4);
        Assertions.assertEquals(20, scoreInstance.getLevelScore(Levels.MATCH));
        Assertions.assertEquals(30, scoreInstance.getLevelScore(Levels.SORT));
        Assertions.assertEquals(50, scoreInstance.getLevelScore(Levels.LISTEN));
    }

    @Test
    void getOverallScore() {
        scoreInstance.addScore(Levels.MATCH, 4);
        scoreInstance.addScore(Levels.SORT, 4);
        scoreInstance.addScore(Levels.LISTEN, 4);
        Assertions.assertEquals(144, scoreInstance.getOverallScore());
    }

    @Test
    void getLevelScore() {
        scoreInstance.addScore(Levels.MATCH, 4);
        Assertions.assertEquals(50, scoreInstance.getLevelScore(Levels.MATCH));
    }

    @Test
    void updateDifficulty() {
        Assertions.assertEquals(Difficulty.LOW, scoreInstance.getDifficulty());
        scoreInstance.updateDifficulty(Difficulty.MEDIUM);
        Assertions.assertEquals(Difficulty.MEDIUM, scoreInstance.getDifficulty());
    }
}