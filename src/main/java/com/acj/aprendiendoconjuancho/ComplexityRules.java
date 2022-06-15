package com.acj.aprendiendoconjuancho;

/**
 * This class handles the rules of the game like time, scoring points,
 * number of words and complexity based on a difficulty
 */
public class ComplexityRules {
    private final Difficulty baseDifficulty;

    /**
     *
     * @param difficulty Difficulty could be LOW, MEDIUM, HIGHT
     */
    public ComplexityRules(Difficulty difficulty) {
        this.baseDifficulty = difficulty;
    }

    /**
     *
     * @return int start time for counter
     */
    public int getStartTime() {
        return getTimeBasedOnDifficulty(this.baseDifficulty);
    }

    /**
     *
     * @return int to define the number of words per level
     */
    public int getNumberOfWords() {
        return getNumberOfWordsBasedOnDifficulty(this.baseDifficulty);
    }

    /**
     *
     * @return int Points to score per right answer
     */
    public int getPointsPerAnswer() {
        return gePointsPerAnswerBasedOnDifficulty(this.baseDifficulty);
    }

    /**
     *
     * @return Difficulty that is currently set
     */
    public Difficulty getDifficulty() {
        return this.baseDifficulty;
    }

    /**
     *
     * @param difficulty Difficulty could be LOW, MEDIUM, HIGH
     * @return int start time based on the difficulty
     */
    private int getTimeBasedOnDifficulty(Difficulty difficulty) {

        return switch (difficulty) {
            case LOW -> 90;
            case MEDIUM -> 70;
            case HIGH -> 60;
        };
    }

    /**
     *
     * @param difficulty Difficulty could be LOW, MEDIUM, HIGH
     * @return int number of words per level based on the difficulty
     */
    private int getNumberOfWordsBasedOnDifficulty(Difficulty difficulty) {

        return switch (difficulty) {
            case LOW -> 4;
            case MEDIUM -> 6;
            case HIGH -> 8;
        };
    }

    /**
     *
     * @param difficulty Difficulty could be LOW, MEDIUM, HIGH
     * @return int points earned per right question based on the difficulty
     */
    private int gePointsPerAnswerBasedOnDifficulty(Difficulty difficulty) {

        return switch (difficulty) {
            case LOW -> 10;
            case MEDIUM -> 12;
            case HIGH -> 14;
        };
    }

}
