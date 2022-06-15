package com.acj.aprendiendoconjuancho;

import java.util.HashMap;

public class Score {
    private int overallScore = 0;
    private final HashMap<Levels, Integer> scoreByLevel = new HashMap<>();
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
        int numberOfQuestions = rules.getNumberOfWords();
        if(rightAnswers > numberOfQuestions) {
            throw new RuntimeException("Right answers could not be greater than number of words per level");
        }
        int pointsPerAnswer = rules.getPointsPerAnswer();
        int score = calculateScore(rightAnswers, numberOfQuestions, pointsPerAnswer);

        scoreByLevel.put(level, score);
        overallScore += score;
    }

    public Difficulty getDifficulty() {
        return difficulty;
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
