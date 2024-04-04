package com.sportradar.mundial;

import java.util.HashSet;
import java.util.Set;

public class Scoreboard {

    Set<Match> ongoingMatches = new HashSet<>();

    public Set<Match> getOngoingMatches() {
        return ongoingMatches;
    }

    public void startNewMatch(QualifiedTeam homeTeam, QualifiedTeam awayTeam) {
        ongoingMatches.add(new Match(homeTeam, awayTeam));
    }
}
