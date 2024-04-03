package com.sportradar.mundial;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class MatchTest {

    @ParameterizedTest
    @ArgumentsSource(QualifiedTeamArgumentsProvider.class)
    void matchShouldStartWithScoreNilToNil(QualifiedTeam homeTeam, QualifiedTeam awayTeam) {
        Match underTest = new Match(homeTeam, awayTeam);
        assertThat(underTest.getScore()).isEqualTo(new Match.Score(0, 0));
    }

    @ParameterizedTest
    @ArgumentsSource(ConflictingArgumentsProvider.class)
    void illegalArgumentExceptionShouldBeThrown_whenBothTeamsAreTheSame(QualifiedTeam homeTeam, QualifiedTeam awayTeam) {
        assertThatThrownBy(() -> new Match(homeTeam, awayTeam))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Home and away teams cannot be the same");
    }

    private static class QualifiedTeamArgumentsProvider implements ArgumentsProvider {
        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
            return Stream.of(Arguments.of(QualifiedTeam.POLAND, QualifiedTeam.MEXICO),
                    Arguments.of(QualifiedTeam.BELGIUM, QualifiedTeam.MOROCCO),
                    Arguments.of(QualifiedTeam.JAPAN, QualifiedTeam.GERMANY));
        }
    }

    private static class ConflictingArgumentsProvider implements ArgumentsProvider {
        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
            return Stream.of(Arguments.of(QualifiedTeam.POLAND, QualifiedTeam.POLAND),
                    Arguments.of(QualifiedTeam.MOROCCO, QualifiedTeam.MOROCCO),
                    Arguments.of(QualifiedTeam.JAPAN, QualifiedTeam.JAPAN));
        }
    }
}