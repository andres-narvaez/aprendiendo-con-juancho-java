package com.acj.aprendiendoconjuancho;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WordDTOTest {
    private WordDTO testWord;

    @BeforeEach
    void setUp() {
        testWord = new WordDTO(
            1,
            "test name",
            Difficulty.HIGH,
            "https://myfakeurl.com",
            "https://myfakeurltwo.com",
            Categories.CLOTHES,
            "ES"
        );
    }

    @AfterEach
    void tearDown() {
        testWord = null;
    }

    @Test
    void getId() {
        assertEquals(1, testWord.getId());
    }

    @Test
    void setId() {
        testWord.setId(2);
        assertEquals(2, testWord.getId());
    }

    @Test
    void getValue() {
        assertEquals("test name", testWord.getValue());
    }

    @Test
    void setValue() {
        testWord.setValue("test name 2");
        assertEquals("test name 2", testWord.getValue());
    }

    @Test
    void getDifficulty() {
        assertEquals(Difficulty.HIGH, testWord.getDifficulty());
    }

    @Test
    void setDifficulty() {
        testWord.setDifficulty(Difficulty.MEDIUM);
        assertEquals(Difficulty.MEDIUM, testWord.getDifficulty());
    }

    @Test
    void getImagePath() {
        assertEquals("https://myfakeurl.com", testWord.getImagePath());
    }

    @Test
    void setImagePath() {
        testWord.setImagePath("https://myfakeurl2.com");
        assertEquals("https://myfakeurl2.com", testWord.getImagePath());
    }

    @Test
    void getCategory() {
        assertEquals(Categories.CLOTHES, testWord.getCategory());
    }

    @Test
    void setCategory() {
        testWord.setCategory(Categories.ANIMALS);
        assertEquals(Categories.ANIMALS, testWord.getCategory());
    }

    @Test
    void getLanguage() {
        assertEquals("ES", testWord.getLanguage());
    }

    @Test
    void setLanguage() {
        testWord.setLanguage("EN");
        assertEquals("EN", testWord.getLanguage());
    }

    @Test
    void testToString() {
        assertEquals("id=1, value=test name, difficulty=HIGH, category=CLOTHES, language=ES", testWord.toString());
    }
}