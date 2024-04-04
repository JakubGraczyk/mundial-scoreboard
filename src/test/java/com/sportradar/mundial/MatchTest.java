package com.sportradar.mundial;

import com.sportradar.mundial.util.TestArgumentsProvider;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class MatchTest {

    @ParameterizedTest
    @ArgumentsSource(TestArgumentsProvider.QualifiedTeamArgumentsProvider.class)
    void matchShouldStartWithScoreNilToNil(QualifiedTeam homeTeam, QualifiedTeam awayTeam) {
        Match underTest = Match.createNewMatchBetween(homeTeam, awayTeam);
        assertThat(underTest.getScore()).isEqualTo(Match.Score.INITIAL_SCORE);
    }

    @ParameterizedTest
    @ArgumentsSource(TestArgumentsProvider.ConflictingArgumentsProvider.class)
    void illegalArgumentExceptionShouldBeThrown_whenBothTeamsAreTheSame(QualifiedTeam homeTeam, QualifiedTeam awayTeam) {
        assertThatThrownBy(() ->  Match.createNewMatchBetween(homeTeam, awayTeam))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Home and away teams cannot be the same");
    }
}