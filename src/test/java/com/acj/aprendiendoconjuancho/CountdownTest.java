package com.acj.aprendiendoconjuancho;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

class CountdownTest {
    private Countdown countdown;

    @BeforeEach
    void setUp() {
        countdown = new Countdown();
        countdown.start(3000);
    }

    @AfterEach
    void tearDown() {
        countdown = null;
    }

    @Test
    void start() {
        assertTrue(countdown instanceof Countdown);
    }

    @Test
    @Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
    void getCount() {
        Assertions.assertEquals(null, countdown.getCount());
    }
}