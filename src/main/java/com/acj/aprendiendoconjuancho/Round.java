package com.acj.aprendiendoconjuancho;

import java.io.FileNotFoundException;
import java.util.HashMap;

/**
 * Handles a Round in the game, which consists of 3 Levels,
 * based on a Category chosen by the Player
 * and with a level of Difficulty
 */
public class Round {
    private final ComplexityRules rules;
    private final Glossary glossary;
    private final HashMap<Levels, Level> levels = new HashMap<>();
    private Levels currentLevel = Levels.MATCH;
    private final Categories category;
    private final Difficulty difficulty;
    private final Player player;
    private final Score score;

    /**
     * Create a new instance of Round
     * @param category topic chosen by the player
     * @param difficulty level of difficulty based on the player's progress
     * @param player the player of the game
     * @throws FileNotFoundException in case any file can't be found
     */
    public Round(Categories category, Difficulty difficulty, Player player) throws FileNotFoundException {
        this.category = category;
        this.difficulty = difficulty;
        this.player = player;
        this.glossary = Glossary.getInstance();
        this.rules = new ComplexityRules(this.difficulty);
        this.score = Score.getInstance(this.difficulty);
        buildLevels();
    }

    /**
     * Builds all the Levels within a Round based on Category and Difficulty
     */
    private void buildLevels() {
        Countdown countdown = new Countdown();

        for (Levels level : Levels.values()) {
            Level instance = new Level(
                level,
                this.category,
                countdown,
                this.glossary.getCategoryGlossaryByLevel(this.category, this.difficulty, this.rules.getNumberOfWords())
            );
            levels.put(level, instance);
        }
    }

    /**
     * Get the rules of the Round which will determine its level of difficulty
     * @return ComplexityRules rules for the Round
     */
    public ComplexityRules getRules() {
        return rules;
    }

    /**
     * Get the Glossary with the words to be used in the Round
     * @return Glossary collection of words and their properties
     */
    public Glossary getGlossary() {
        return glossary;
    }

    /**
     * Get the Level to be played in the Round
     * @return HashMap<Levels, Level>
     */
    public HashMap<Levels, Level> getLevels() {
        return levels;
    }

    /**
     * Get the Level to be played
     * @param level level to be played
     * @return Level the instance of the level to be played
     */
    public Level getLevel(Levels level) {
        return levels.get(level);
    }

    /**
     * Get the Category chosen by the player
     * @return Category
     */
    public Categories getCategory() {
        return category;
    }

    /**
     * Get the level of difficulty of the round
     * @return Difficulty
     */
    public Difficulty getDifficulty() {
        return difficulty;
    }

    /**
     * Get the player of the Round
     * @return Player
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Get the score of the Round
     * @return Score
     */
    public Score getScore() {
        return score;
    }

    /**
     * Get the current level played by the player
     * @return Levels currentLevel
     */
    public Levels getCurrentLevel() {
        return currentLevel;
    }

    /**
     * Set the level to be played by the player
     * @param currentLevel Levels
     */
    public void setCurrentLevel(Levels currentLevel) {
        this.currentLevel = currentLevel;
    }
}
