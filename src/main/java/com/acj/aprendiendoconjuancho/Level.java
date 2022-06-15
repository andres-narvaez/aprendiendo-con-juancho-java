package com.acj.aprendiendoconjuancho;

public class Level {
    private Levels name;
    private Categories category;
    private Countdown timer;
    private WordDTO[] words;

    public Level(Levels name, Categories category, Countdown timer, WordDTO[] words) {
        this.name = name;
        this.category = category;
        this.timer = timer;
        this.words = words;
    }
}
