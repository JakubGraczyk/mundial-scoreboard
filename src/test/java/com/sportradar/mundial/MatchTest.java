package com.sportradar.mundial;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MatchTest {

    @Test
    void matchShouldStartWithScoreNilToNil() {
        Match underTest = new Match(QualifiedTeam.POLAND, QualifiedTeam.MEXICO);
        assertThat(underTest.getScore()).isEqualTo(new Match.Score(0, 0));
    }
}