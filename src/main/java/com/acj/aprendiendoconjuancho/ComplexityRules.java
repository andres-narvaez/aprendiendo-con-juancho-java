package com.acj.aprendiendoconjuancho;

public class ComplexityRules {
    private final Difficulty baseDifficulty;

    public ComplexityRules(Difficulty difficulty) {
        this.baseDifficulty = difficulty;
    }

    public int getStartTime() {
        return getTimeBasedOnDifficulty(this.baseDifficulty);
    }

    public int getNumberOfWords() {
        return getNumberOfWordsBasedOnDifficulty(this.baseDifficulty);
    }

    public int gePointsPerAnswer() {
        return gePointsPerAnswerBasedOnDifficulty(this.baseDifficulty);
    }

    public Difficulty getDifficulty() {
        return this.baseDifficulty;
    }

    private int getTimeBasedOnDifficulty(Difficulty difficulty) {

        return switch (difficulty) {
            case LOW -> 90;
            case MEDIUM -> 70;
            case HIGH -> 60;
        };
    }

    private int getNumberOfWordsBasedOnDifficulty(Difficulty difficulty) {

        return switch (difficulty) {
            case LOW -> 4;
            case MEDIUM -> 6;
            case HIGH -> 8;
        };
    }

    private int gePointsPerAnswerBasedOnDifficulty(Difficulty difficulty) {

        return switch (difficulty) {
            case LOW -> 10;
            case MEDIUM -> 12;
            case HIGH -> 14;
        };
    }

}
