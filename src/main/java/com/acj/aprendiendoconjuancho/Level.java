package com.acj.aprendiendoconjuancho;

/**
 * The Level is basically a game in a Round
 * where the player will find different challenges
 * to be completed within a limit of time based on a Countdown
 */
public class Level {
    private Levels name;
    private Categories category;
    private Countdown countdown;
    private WordDTO[] words;

    /**
     * Create the instance of the Level
     * @param name name of the level
     * @param category category chosen by the player
     * @param countdown instance of a Countdown
     * @param words collection of words
     */
    public Level(Levels name, Categories category, Countdown countdown, WordDTO[] words) {
        this.name = name;
        this.category = category;
        this.countdown = countdown;
        this.words = words;
    }

    /**
     * Get the name of the Level
     * @return name Levels
     */
    public Levels getName() { return name; }

    /**
     * Get the category chosen by the player
     * @return category Categories
     */
    public Categories getCategory() { return category; }

    /**
     * Get the instance of the Countdown
     * @return countdown Countdown
     */
    public Countdown getCountdown() { return countdown; }

    /**
     * Get the collection of word to be used in the level
     * @return words WordDTO[]
     */
    public WordDTO[] getWords() { return words; }
}
