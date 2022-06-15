package com.acj.aprendiendoconjuancho;

public class Level {
    private Levels name;
    private Categories category;
    private Countdown countdown;
    private WordDTO[] words;

    public Level(Levels name, Categories category, Countdown countdown, WordDTO[] words) {
        this.name = name;
        this.category = category;
        this.countdown = countdown;
        this.words = words;
    }

    public Levels getName() { return name; }
    public Categories getCategory() { return category; }
    public Countdown getCountdown() { return countdown; }
    public WordDTO[] getWords() { return words; }
}
