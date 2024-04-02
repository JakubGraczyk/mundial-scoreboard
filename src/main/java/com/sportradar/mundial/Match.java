package com.sportradar.mundial;

import java.util.Objects;

class Match {

    private final QualifiedTeam homeTeam;
    private final QualifiedTeam awayTeam;
    private final Score score = new Score(0, 0);

    Match(QualifiedTeam homeTeam, QualifiedTeam awayTeam) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
    }

    Score getScore() {
        return score;
    }

    static class Score {
        private final int homeTeamScore;
        private final int awayTeamScore;

        public Score(int homeTeamScore, int awayTeamScore) {
            this.homeTeamScore = homeTeamScore;
            this.awayTeamScore = awayTeamScore;
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
