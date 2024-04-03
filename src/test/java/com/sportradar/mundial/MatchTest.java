package com.sportradar.mundial;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class MatchTest {


    @ParameterizedTest
    @ArgumentsSource(QualifiedTeamArgumentsProvider.class)
    void matchShouldStartWithScoreNilToNil(QualifiedTeam homeTeam, QualifiedTeam awayTeam) {
        Match underTest = new Match(homeTeam, awayTeam);
        assertThat(underTest.getScore()).isEqualTo(new Match.Score(0, 0));
    }


    private static class QualifiedTeamArgumentsProvider implements ArgumentsProvider {
        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
            return Stream.of(Arguments.of(QualifiedTeam.POLAND, QualifiedTeam.MEXICO),
                    Arguments.of(QualifiedTeam.BELGIUM, QualifiedTeam.MOROCCO),
                    Arguments.of(QualifiedTeam.JAPAN, QualifiedTeam.GERMANY));
        }
    }
}