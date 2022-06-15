package com.acj.aprendiendoconjuancho;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

class GlossaryTest {
    private Glossary instance;
    @BeforeEach
    void setUp() throws FileNotFoundException {
        instance = Glossary.getInstance();
    }

    @AfterEach
    void tearDown() {
        instance = null;
    }

    @Test
    void getInstance() {
        assertTrue(instance instanceof Glossary);
    }

    @Test
    void getCategoryGlossary() {
        WordDTO[] testGlossary = instance.getCategoryGlossary(Categories.ANIMALS);
        assertEquals(Categories.ANIMALS, testGlossary[0].getCategory());
    }

    @Test
    void getCategoryGlossaryByLevel() {
        WordDTO[] testGlossary = instance.getCategoryGlossaryByLevel(Categories.ANIMALS, Difficulty.LOW);
        assertEquals(Difficulty.LOW, testGlossary[0].getDifficulty());
    }
}