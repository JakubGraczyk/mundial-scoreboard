package com.sportradar.mundial;

import com.sportradar.mundial.util.TestArgumentsProvider;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;


class ScoreboardTest {

    @Test
    void scoreboardShouldBeInitializedWithEmptyMatchSet() {
        Scoreboard underTest = new Scoreboard();
        assertThat(underTest.getOngoingMatches()).isEqualTo(Collections.emptySet());
    }

    @Nested
    class StartNewMatch {
        @ParameterizedTest
        @ArgumentsSource(TestArgumentsProvider.QualifiedTeamArgumentsProvider.class)
        void scoreboardShouldStartFirstMatchWithNilToNilScoreAndAddItToOngoingMatchesSet(QualifiedTeam homeTeam, QualifiedTeam awayTeam) {
            Scoreboard underTest = new Scoreboard();

            underTest.startNewMatch(homeTeam, awayTeam);

            assertThat(underTest.getOngoingMatches()).hasSize(1);
            Match onlyMatch = underTest.getOngoingMatches().iterator().next();
            assertThat(onlyMatch.getScore()).isEqualTo(new Match.Score(0,0));
        }

        @Test
        void scoreboardShouldStartSuccessfullyMultipleMatches() {
            Scoreboard underTest = new Scoreboard();

            underTest.startNewMatch(QualifiedTeam.POLAND, QualifiedTeam.MEXICO);
            underTest.startNewMatch(QualifiedTeam.JAPAN, QualifiedTeam.GERMANY);

            assertThat(underTest.getOngoingMatches()).hasSize(2);
            assertThat(underTest.getOngoingMatches().stream()
                    .map(Match::getScore))
                    .allMatch(s -> new Match.Score(0,0).equals(s));
        }
    }
}
