package com.acj.aprendiendoconjuancho;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ComplexityRulesTest {
    private ComplexityRules instance;

    @BeforeEach
    void setUp() {
        instance = new ComplexityRules(Difficulty.LOW);
    }

    @AfterEach
    void tearDown() {
        instance = null;
    }

    @Test
    void getStartTime() {
        assertEquals(90, instance.getStartTime());
    }

    @Test
    void getNumberOfWords() {
        assertEquals(4, instance.getNumberOfWords());
    }

    @Test
    void gePointsPerAnswer() {
        assertEquals(10, instance.getPointsPerAnswer());
    }

    @Test
    void getDifficulty() {
        assertEquals(Difficulty.LOW, instance.getDifficulty());
    }
}