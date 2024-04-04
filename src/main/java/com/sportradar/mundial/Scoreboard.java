package com.sportradar.mundial;

import org.apache.commons.lang3.tuple.ImmutablePair;

import java.util.HashMap;
import java.util.Map;

public class Scoreboard {

    Map<ImmutablePair<QualifiedTeam, QualifiedTeam>, Match> ongoingMatches = new HashMap<>();

    public Map<ImmutablePair<QualifiedTeam, QualifiedTeam>, Match> getOngoingMatches() {
        return ongoingMatches;
    }

    public void startNewMatch(QualifiedTeam homeTeam, QualifiedTeam awayTeam) {
        ongoingMatches.put(new ImmutablePair<>(homeTeam, awayTeam), Match.createNewMatchBetween(homeTeam, awayTeam));
    }
}
