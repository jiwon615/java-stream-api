package com.stream.jiwon.middle;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class MiddleApiTest {

    @DisplayName("filter / distinct test")
    @Test
    void filter_distinct_test() {
        // given
        List<String> list = List.of("a", "a", "b", "b", "b", "c", "d");

        // when
        List<String> result = list.stream()
                .filter(i -> i.equalsIgnoreCase("a"))
                .toList(); // a, a

        List<String> result2 = list.stream()
                .distinct()
                .toList(); // a, b, c, d

        //then
        assertThat(result).hasSize(2);
        assertThat(result2).hasSize(4);
    }
}
