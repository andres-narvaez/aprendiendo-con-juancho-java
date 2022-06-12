package com.acj.aprendiendoconjuancho;

import java.util.HashMap;

public class Score {
    private int overallScore = 0;
    private final HashMap<Level, int> scoreByLevel = new HashMap<Level, int>();
    private Difficulty difficulty;
    private ComplexityRules rules;

    public Score(Difficulty difficulty) {
        this.difficulty = difficulty;
        this.rules = new ComplexityRules(this.difficulty);

        scoreByLevel.put(Level.MATCH, 0);
        scoreByLevel.put(Level.SORT, 0);
        scoreByLevel.put(Level.LISTEN, 0);
    }

    public void addScore(Level level, int rightAnswers) {
        int numberOfQuestions = rules.getNumberOfWords();
        int pointsPerAnswer = rules.gePointsPerAnswer();
        int score = calculateScore(rightAnswers, numberOfQuestions, pointsPerAnswer);

        scoreByLevel.put(level, score);
        overallScore += score;
    }

    public int getOverallScore() {
        return overallScore;
    }

    public int getLevelScore(Level level) {
        return scoreByLevel.get(level);
    }

    public void updateDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
        this.rules = new ComplexityRules(this.difficulty);
    }

    private int calculateScore(int rightAnswers, int totalWords, int pointsPerAnswer) {
        int succeedRatio = (rightAnswers * 100) / totalWords;
        int baseScore = rightAnswers * pointsPerAnswer;

        return succeedRatio == 100 ? baseScore + pointsPerAnswer : baseScore;
    }
}
