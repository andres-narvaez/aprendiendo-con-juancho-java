package com.acj.aprendiendoconjuancho;

import java.util.HashMap;

/**
 * Stores and calculates the score for a round
 */
public final class Score {
    private static Score instance;
    private int overallScore = 0;
    private final HashMap<Levels, Integer> scoreByLevel = new HashMap<>();
    private Difficulty difficulty;
    private ComplexityRules rules;

    /**
     * @param difficulty Difficulty could be LOW, MEDIUM, HIGH
     */
    private Score(Difficulty difficulty) {
        this.difficulty = difficulty;
        this.rules = new ComplexityRules(this.difficulty);

        scoreByLevel.put(Levels.MATCH, 0);
        scoreByLevel.put(Levels.SORT, 0);
        scoreByLevel.put(Levels.LISTEN, 0);
    }

    /**
     * @param level could be MATCH, SORT, LISTEN
     * @param rightAnswers int with the number of asserts this could not be greater than the number of words per level
     */
    public void addScore(Levels level, int rightAnswers) {
        int numberOfQuestions = rules.getNumberOfWords();
        int pointsPerAnswer = rules.getPointsPerAnswer();
        int score = calculateScore(rightAnswers, numberOfQuestions, pointsPerAnswer);

        if(scoreByLevel.get(level) > 0) {
            overallScore -= scoreByLevel.get(level);
        }

        scoreByLevel.put(level, score);
        overallScore += score;
    }

    /**
     * @return Difficulty could be LOW, MEDIUM, HIGH
     */
    public Difficulty getDifficulty() {
        return difficulty;
    }

    /**
     * @return int overall score for the round
     */
    public int getOverallScore() {
        return overallScore;
    }

    /**
     * @param level could be LOW, MEDIUM, HIGH
     * @return int score for the specified level
     */
    public int getLevelScore(Levels level) {
        return scoreByLevel.get(level);
    }

    /**
     * @param difficulty could be LOW, MEDIUM, HIGH
     */
    public void updateDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
        this.rules = new ComplexityRules(this.difficulty);
    }

    /**
     * If the asserts are 100% the player gets a score bonus
     * @param rightAnswers number of asserts
     * @param totalWords number of words per level
     * @param pointsPerAnswer points to be earned per assert
     * @return int score for an specific level
     */
    private int calculateScore(int rightAnswers, int totalWords, int pointsPerAnswer) {
        int succeedRatio = (rightAnswers * 100) / totalWords;
        int baseScore = rightAnswers * pointsPerAnswer;

        return succeedRatio == 100 ? baseScore + pointsPerAnswer : baseScore;
    }

    /**
     * Creates a singleton instance of Score
     * @param difficulty Difficulty could be LOW, MEDIUM, HIGH
     */
    public static  Score getInstance(Difficulty difficulty) {
        if (instance == null) {
            instance = new Score(difficulty);
        }

        return instance;
    }
}
