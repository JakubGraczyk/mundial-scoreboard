package com.sportradar.mundial;

import com.sportradar.mundial.util.TestArgumentsProvider;
import org.apache.commons.lang3.tuple.ImmutablePair;
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
        assertThat(underTest.getOngoingMatches()).isEqualTo(Collections.emptyMap());
    }

    @Nested
    class StartNewMatch {
        @ParameterizedTest
        @ArgumentsSource(TestArgumentsProvider.QualifiedTeamArgumentsProvider.class)
        void scoreboardShouldStartFirstMatchWithNilToNilScoreAndAddItToOngoingMatchesSet(QualifiedTeam homeTeam, QualifiedTeam awayTeam) {
            Scoreboard underTest = new Scoreboard();

            underTest.startNewMatch(homeTeam, awayTeam);

            assertThat(underTest.getOngoingMatches()).hasSize(1);
            assertThat(underTest.getOngoingMatches().get(new ImmutablePair<>(homeTeam, awayTeam)).getScore()).isEqualTo(Match.Score.INITIAL_SCORE);
        }

        @Test
        void scoreboardShouldStartSuccessfullyMultipleMatches() {
            Scoreboard underTest = new Scoreboard();

            underTest.startNewMatch(QualifiedTeam.POLAND, QualifiedTeam.MEXICO);
            underTest.startNewMatch(QualifiedTeam.JAPAN, QualifiedTeam.GERMANY);

            assertThat(underTest.getOngoingMatches()).hasSize(2);
            assertThat(underTest.getOngoingMatches().values()
                    .stream()
                    .map(Match::getScore))
                    .allMatch(Match.Score.INITIAL_SCORE::equals);
        }
    }
}
