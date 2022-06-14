package com.acj.aprendiendoconjuancho;

import java.util.HashMap;

public class Score {
    private int overallScore = 0;
    private final HashMap<Levels, Integer> scoreByLevel = new HashMap();
    private Difficulty difficulty;
    private ComplexityRules rules;

    public Score(Difficulty difficulty) {
        this.difficulty = difficulty;
        this.rules = new ComplexityRules(this.difficulty);

        scoreByLevel.put(Levels.MATCH, 0);
        scoreByLevel.put(Levels.SORT, 0);
        scoreByLevel.put(Levels.LISTEN, 0);
    }

    public void addScore(Levels level, int rightAnswers) {
        // TODO: validate don't receive greater rightAnswers and numberOfWords
        int numberOfQuestions = rules.getNumberOfWords();
        int pointsPerAnswer = rules.gePointsPerAnswer();
        int score = calculateScore(rightAnswers, numberOfQuestions, pointsPerAnswer);

        scoreByLevel.put(level, score);
        overallScore += score;
    }

    public int getOverallScore() {
        return overallScore;
    }

    public int getLevelScore(Levels level) {
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
