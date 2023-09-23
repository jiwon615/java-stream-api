package com.stream.jiwon.middle;

import com.stream.jiwon.dto.Member;
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
        List<Integer> intList = List.of(1, 2, 3, 4, 5);

        // when
        List<String> result = list.stream()
                .map(String::toUpperCase)
                .peek(System.out::print)
                .toList();

        int[] intResult = intList.stream()
                .mapToInt(i -> i) // Integer -> int
                .peek(System.out::println) // 1 2 3 4 5
                .toArray();

        //then
        assertEquals(result.toString(), List.of("A, A, B, B, B, C, D").toString());
    }

    @DisplayName("flatMap test")
    @Test
    void flatMap_test() {
        // given
        Member member1 = Member.builder()
                .age(10)
                .name("정지원")
                .scores(List.of("10", "20", "30", "40", "50"))
                .build();

        Member member2 = Member.builder()
                .age(22)
                .name("정지원2")
                .scores(List.of("44", "55", "60", "70", "80", "90"))
                .build();

        List<Member> list = List.of(member1, member2);

        // when
        String result = list.stream()
                .flatMap(i -> i.getScores().stream())
                .mapToInt(Integer::parseInt)
                .filter(i -> i >= 40)
                .mapToObj(String::valueOf)
                .collect(Collectors.joining("!! "));

        //then
        System.out.println("result = " + result); // result = 40!! 50!! 44!! 55!! 60!! 70!! 80!! 90
    }

    @DisplayName("dropWhile, takeWhile test")
    @Test
    void dropWhile_takeWhile_test() {
        // given
        List<Integer> list = List.of(12, 17, 29, 35, 41, 44, 50, 66, 72, 80);

        // when
        List<Integer> result = list.stream()
                .takeWhile(i -> i < 50)
                .toList();

        List<Integer> result2 = list.stream()
                .dropWhile(i -> i < 50)
                .toList();

        //then
        result.forEach(System.out::println); // 12, 17, 29, 35, 41, 44
        result2.forEach(System.out::println); // 50, 66, 72, 80
    }
}
