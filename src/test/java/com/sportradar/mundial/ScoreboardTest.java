package com.sportradar.mundial;

import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;


class ScoreboardTest {

    @Test
    void scoreboardShouldBeInitializedWithEmptyMatchSet() {
        Scoreboard underTest = new Scoreboard();
        assertThat(underTest.getOngoingMatches()).isEqualTo(Collections.emptySet());
    }
}
