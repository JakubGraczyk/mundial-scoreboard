package com.sportradar.mundial;

import com.sportradar.mundial.util.TestArgumentsProvider;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;


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

    @Nested
    class UpdateScore {

        @Test
        void scoreboardShouldSuccessfullyUpdateScores() {
            Scoreboard underTest = new Scoreboard();
            underTest.startNewMatch(QualifiedTeam.BELGIUM, QualifiedTeam.CROATIA);
            underTest.startNewMatch(QualifiedTeam.USA, QualifiedTeam.AUSTRALIA);

            underTest.updateScore(new ImmutablePair<>(QualifiedTeam.BELGIUM, QualifiedTeam.CROATIA), 1, 1);
            underTest.updateScore(new ImmutablePair<>(QualifiedTeam.USA, QualifiedTeam.AUSTRALIA), 1, 4);

            assertThat(underTest.getOngoingMatches().get(new ImmutablePair<>(QualifiedTeam.BELGIUM, QualifiedTeam.CROATIA)).getScore())
                    .isEqualTo(Match.Score.of(1, 1));
            assertThat(underTest.getOngoingMatches().get(new ImmutablePair<>(QualifiedTeam.USA, QualifiedTeam.AUSTRALIA)).getScore())
                    .isEqualTo(Match.Score.of(1, 4));
        }

        @Test
        void illegalArgumentExceptionShouldBeThrown_whenMatchNotFound() {
            Scoreboard underTest = new Scoreboard();
            underTest.startNewMatch(QualifiedTeam.BELGIUM, QualifiedTeam.CROATIA);
            underTest.startNewMatch(QualifiedTeam.USA, QualifiedTeam.AUSTRALIA);

            underTest.updateScore(new ImmutablePair<>(QualifiedTeam.BELGIUM, QualifiedTeam.CROATIA), 1, 1);
            underTest.updateScore(new ImmutablePair<>(QualifiedTeam.USA, QualifiedTeam.AUSTRALIA), 1, 4);

            assertThatThrownBy(() -> underTest.updateScore(new ImmutablePair<>(QualifiedTeam.WALES, QualifiedTeam.AUSTRALIA), 1, 1))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("Match between WALES and AUSTRALIA not found");
            ;
        }
    }
}
