package com.sportradar.mundial;

import java.util.Objects;

class Match {

    private final QualifiedTeam homeTeam;
    private final QualifiedTeam awayTeam;
    private Score score;

    private Match(QualifiedTeam homeTeam, QualifiedTeam awayTeam) {
        if (homeTeam == awayTeam) {
            throw new IllegalArgumentException("Home and away teams cannot be the same");
        }
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.score = Score.INITIAL_SCORE;
    }

    static Match createNewMatchBetween(QualifiedTeam homeTeam, QualifiedTeam awayTeam) {
        return new Match(homeTeam, awayTeam);
    }

    Score getScore() {
        return score;
    }

    public void updateScore(int homeTeamScore, int awayTeamScore) {
        if (homeTeamScore < 0 || awayTeamScore < 0) {
            throw new IllegalArgumentException("Score can not be negative");
        }
        this.score = Score.of(homeTeamScore, awayTeamScore);
    }

    static class Score {

        static final Score INITIAL_SCORE = new Score(0, 0);

        private final int homeTeamScore;
        private final int awayTeamScore;


        private Score(int homeTeamScore, int awayTeamScore) {
            this.homeTeamScore = homeTeamScore;
            this.awayTeamScore = awayTeamScore;
        }

        public static Score of(int homeTeamScore, int awayTeamScore) {
            return new Score(homeTeamScore, awayTeamScore);
        }

        @Override
        public int hashCode() {
            return Objects.hash(homeTeamScore, awayTeamScore);
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            Score otherScore = (Score) obj;
            return homeTeamScore == otherScore.homeTeamScore &&
                    awayTeamScore == otherScore.awayTeamScore;
        }
    }
}
