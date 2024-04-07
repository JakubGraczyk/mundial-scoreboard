package com.sportradar.mundial;

import com.sportradar.mundial.util.TestArgumentsProvider;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class MatchTest {
    @Nested
    class MatchInitialization {

        @ParameterizedTest
        @ArgumentsSource(TestArgumentsProvider.QualifiedTeamArgumentsProvider.class)
        void matchShouldStartWithScoreNilToNil(QualifiedTeam homeTeam, QualifiedTeam awayTeam) {
            Match underTest = Match.createNewMatchBetween(homeTeam, awayTeam);
            assertThat(underTest.getScore()).isEqualTo(Match.Score.INITIAL_SCORE);
        }

        @ParameterizedTest
        @ArgumentsSource(TestArgumentsProvider.ConflictingArgumentsProvider.class)
        void illegalArgumentExceptionShouldBeThrown_whenBothTeamsAreTheSame(QualifiedTeam homeTeam, QualifiedTeam awayTeam) {
            assertThatThrownBy(() -> Match.createNewMatchBetween(homeTeam, awayTeam))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("Home and away teams cannot be the same");
        }
    }

    @Nested
    class MatchUpdate {

        @ParameterizedTest
        @ArgumentsSource(TestArgumentsProvider.QualifiedTeamArgumentsProvider.class)
        void matchScoreShouldBeSuccessfullyUpdated(QualifiedTeam homeTeam, QualifiedTeam awayTeam) {
            Match underTest = Match.createNewMatchBetween(homeTeam, awayTeam);
            ImmutablePair<QualifiedTeam, QualifiedTeam> rivals = new ImmutablePair<>(homeTeam, awayTeam);

            underTest.updateScore(2, 3);

            assertThat(underTest.getScore()).isEqualTo(Match.Score.of(2, 3));
        }

        @ParameterizedTest
        @ArgumentsSource(TestArgumentsProvider.QualifiedTeamArgumentsProvider.class)
        void illegalArgumentExceptionShouldBeThrown_whenScoreHasNegativeNumber(QualifiedTeam homeTeam, QualifiedTeam awayTeam) {
            Match underTest = Match.createNewMatchBetween(homeTeam, awayTeam);

            assertThatThrownBy(() -> underTest.updateScore(2, -3))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("Score can not be negative");
        }
    }
}