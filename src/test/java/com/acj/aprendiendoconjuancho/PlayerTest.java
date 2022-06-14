package com.acj.aprendiendoconjuancho;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {
    private String mockName;
    private Player playerInstance;

    @BeforeEach
    void setUp() {
        mockName = "Joe Doe";
        playerInstance = new Player(mockName);
    }

    @AfterEach
    void tearDown() {
        mockName = null;
        playerInstance = null;
    }

    @Test
    void getName() {
        Assertions.assertEquals(mockName, playerInstance.getName());
    }
}