package com.acj.aprendiendoconjuancho;

import java.io.FileNotFoundException;
import java.util.HashMap;

public class Round {
    private final ComplexityRules rules;
    private final Glossary glossary;
    private final HashMap<Levels, Level> levels = new HashMap<>();
    private Levels currentLevel = Levels.MATCH;
    private final Categories category;
    private final Difficulty difficulty;
    private final Player player;
    private final Score score;

    public Round(Categories category, Difficulty difficulty, Player player) throws FileNotFoundException {
        this.category = category;
        this.difficulty = difficulty;
        this.player = player;
        this.glossary = Glossary.getInstance();
        this.rules = new ComplexityRules(this.difficulty);
        this.score = new Score(this.difficulty);
        buildLevels();
    }

    private void buildLevels() {
        Countdown countdown = new Countdown();

        for (Levels level : Levels.values()) {
            Level instance = new Level(
                level,
                this.category,
                countdown,
                this.glossary.getCategoryGlossaryByLevel(this.category, this.difficulty)
            );
            levels.put(level, instance);
        }
    }

    public ComplexityRules getRules() {
        return rules;
    }

    public Glossary getGlossary() {
        return glossary;
    }

    public HashMap<Levels, Level> getLevels() {
        return levels;
    }

    public Level getLevel(Levels level) {
        return levels.get(level);
    }

    public Categories getCategory() {
        return category;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public Player getPlayer() {
        return player;
    }

    public Score getScore() {
        return score;
    }

    public Levels getCurrentLevel() {
        return currentLevel;
    }

    public void setCurrentLevel(Levels currentLevel) {
        this.currentLevel = currentLevel;
    }
}
