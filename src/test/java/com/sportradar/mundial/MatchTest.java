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
        Match underTest = new Match(homeTeam, awayTeam);
        assertThat(underTest.getScore()).isEqualTo(new Match.Score(0, 0));
    }

    @ParameterizedTest
    @ArgumentsSource(TestArgumentsProvider.ConflictingArgumentsProvider.class)
    void illegalArgumentExceptionShouldBeThrown_whenBothTeamsAreTheSame(QualifiedTeam homeTeam, QualifiedTeam awayTeam) {
        assertThatThrownBy(() -> new Match(homeTeam, awayTeam))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Home and away teams cannot be the same");
    }
}