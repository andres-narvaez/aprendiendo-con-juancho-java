package com.acj.aprendiendoconjuancho;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

class RoundTest {
    private Round instance;
    private Player mockPlayer;
    @BeforeEach
    void setUp() throws FileNotFoundException {
        mockPlayer = Player.getInstance("Jon Doe");
        instance = new Round(Categories.ANIMALS, Difficulty.LOW, mockPlayer);
    }

    @AfterEach
    void tearDown() {
        mockPlayer = null;
        instance = null;
    }

    @Test
    void getRules() {
        assertTrue(instance.getRules() instanceof ComplexityRules);
    }

    @Test
    void getGlossary() {
        assertTrue(instance.getGlossary() instanceof Glossary);
    }

    @Test
    void getLevels() {
        assertNotNull(instance.getLevels().get(Levels.MATCH));
        assertNotNull(instance.getLevels().get(Levels.SORT));
        assertNotNull(instance.getLevels().get(Levels.LISTEN));
    }

    @Test
    void getLevel() {
        assertTrue(instance.getLevel(Levels.SORT) instanceof Level);
    }

    @Test
    void getCategory() {
        assertSame(instance.getCategory(), Categories.ANIMALS);
    }

    @Test
    void getDifficulty() {
        assertSame(instance.getDifficulty(), Difficulty.LOW);
    }

    @Test
    void getPlayer() {
        assertSame(instance.getPlayer(), mockPlayer);
    }

    @Test
    void getScore() {
        assertTrue(instance.getScore() instanceof Score);
    }

    @Test
    void getCurrentLevel() {
        assertSame(instance.getCurrentLevel(), Levels.MATCH);
    }

    @Test
    void setCurrentLevel() {
        instance.setCurrentLevel(Levels.SORT);
        assertSame(instance.getCurrentLevel(), Levels.SORT);
    }
}