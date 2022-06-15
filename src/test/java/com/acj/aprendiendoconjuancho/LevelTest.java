package com.acj.aprendiendoconjuancho;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

class LevelTest {
    private Level level;
    private Levels mockName = Levels.MATCH;
    private Categories mockCategory = Categories.ANIMALS;
    private Countdown mockCountdown = new Countdown();
    private WordDTO[] mockWords = Glossary.getInstance().getCategoryGlossary(Categories.ANIMALS);


    LevelTest() throws FileNotFoundException {
    }

    @BeforeEach
    void setUp() {
        level =  new Level(mockName, mockCategory, mockCountdown, mockWords);
    }

    @AfterEach
    void tearDown() {
        level = null;
        mockName = null;
        mockCategory = null;
        mockCountdown = null;
        mockWords = null;
    }

    @Test
    void getName() {
        assertEquals(mockName, level.getName());
    }

    @Test
    void getCategory() {
        assertEquals(mockCategory, level.getCategory());
    }

    @Test
    void getTimer() {
        assertEquals(mockCountdown, level.getCountdown());
    }

    @Test
    void getWords() {
        assertEquals(mockWords, level.getWords());
    }
}