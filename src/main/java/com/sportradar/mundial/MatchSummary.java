package com.sportradar.mundial;

import java.util.Objects;

public final class MatchSummary {

    private final QualifiedTeam homeTeam;
    private final Integer homeTeamScore;
    private final QualifiedTeam awayTeam;
    private final Integer awayTeamScore;

    MatchSummary(QualifiedTeam homeTeam, Integer homeTeamScore, QualifiedTeam awayTeam, Integer awayTeamScore) {
        this.homeTeam = homeTeam;
        this.homeTeamScore = homeTeamScore;
        this.awayTeam = awayTeam;
        this.awayTeamScore = awayTeamScore;
    }

    public QualifiedTeam getHomeTeam() {
        return homeTeam;
    }

    public Integer getHomeTeamScore() {
        return homeTeamScore;
    }

    public QualifiedTeam getAwayTeam() {
        return awayTeam;
    }

    public Integer getAwayTeamScore() {
        return awayTeamScore;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MatchSummary that = (MatchSummary) o;
        return homeTeam == that.homeTeam && Objects.equals(homeTeamScore, that.homeTeamScore) && awayTeam == that.awayTeam && Objects.equals(awayTeamScore, that.awayTeamScore);
    }

    @Override
    public int hashCode() {
        return Objects.hash(homeTeam, homeTeamScore, awayTeam, awayTeamScore);
    }
}
