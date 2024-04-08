package com.sportradar.mundial;

import org.apache.commons.lang3.tuple.ImmutablePair;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Scoreboard {

    Map<ImmutablePair<QualifiedTeam, QualifiedTeam>, Match> ongoingMatches = new HashMap<>();

    public void startNewMatch(QualifiedTeam homeTeam, QualifiedTeam awayTeam) {
        ongoingMatches.put(new ImmutablePair<>(homeTeam, awayTeam), Match.createNewMatchBetween(homeTeam, awayTeam));
    }

    public void updateScore(ImmutablePair<QualifiedTeam, QualifiedTeam> matchBetween, int homeTeamScore, int awayTeamScore) {
        Match match = ongoingMatches.get(matchBetween);
        if (match == null) {
            throw new IllegalArgumentException(String.format("Match between %s and %s not found",
                    matchBetween.getLeft(), matchBetween.getRight()));
        }
        ongoingMatches.get(matchBetween).updateScore(homeTeamScore, awayTeamScore);
    }

    public void finishMatch(ImmutablePair<QualifiedTeam, QualifiedTeam> matchBetween) {
        Match match = ongoingMatches.get(matchBetween);
        if (match == null) {
            throw new IllegalArgumentException(String.format("Match between %s and %s not found",
                    matchBetween.getLeft(), matchBetween.getRight()));
        }
        ongoingMatches.remove(matchBetween);
    }

    public List<MatchSummary> getMatchesSummary() {
        return ongoingMatches.values()
                .stream()
                .sorted(Collections.reverseOrder())
                .map(Match::toSummary)
                .toList();
    }

    Map<ImmutablePair<QualifiedTeam, QualifiedTeam>, Match> getOngoingMatches() {
        return ongoingMatches;
    }
}
