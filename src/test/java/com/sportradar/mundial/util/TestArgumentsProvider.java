package com.sportradar.mundial.util;

import com.sportradar.mundial.QualifiedTeam;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.stream.Stream;

public final class TestArgumentsProvider {

     public static class QualifiedTeamArgumentsProvider implements ArgumentsProvider {
        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
            return Stream.of(Arguments.of(QualifiedTeam.POLAND, QualifiedTeam.MEXICO),
                    Arguments.of(QualifiedTeam.BELGIUM, QualifiedTeam.MOROCCO),
                    Arguments.of(QualifiedTeam.JAPAN, QualifiedTeam.GERMANY));
        }
    }

    public static class ConflictingArgumentsProvider implements ArgumentsProvider {
        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
            return Stream.of(Arguments.of(QualifiedTeam.POLAND, QualifiedTeam.POLAND),
                    Arguments.of(QualifiedTeam.MOROCCO, QualifiedTeam.MOROCCO),
                    Arguments.of(QualifiedTeam.JAPAN, QualifiedTeam.JAPAN));
        }
    }
}
