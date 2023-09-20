package com.stream.jiwon.middle;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

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

    @DisplayName("map test")
    @Test
    void map_test() {
        // given
        List<String> list = List.of("a", "a", "b", "b", "b", "c", "d");

        // when
        List<String> result = list.stream()
                .map(String::toUpperCase)
                .peek(System.out::print)
                .toList();

        //then
        assertEquals(result.toString(), List.of("A, A, B, B, B, C, D").toString());
    }
}
