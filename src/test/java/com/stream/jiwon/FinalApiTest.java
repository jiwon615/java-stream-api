package com.stream.jiwon;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

public class FinalApiTest {

    @DisplayName("reduce test")
    @Test
    void reduce_test() {
        // given
        List<Integer> list = List.of(1, 2, 3, 4);

        // when
        Integer test1 = list.stream()
                .reduce(Integer::sum)
                .orElseThrow();

        Integer test2 = list.stream()
                .reduce(10, (a, b) -> a + b);

        Integer test3 = list.stream()
                .reduce(10, (a, b) -> a + b, (a, b) -> a + b);
        //then
        System.out.println("test1 = " + test1);
        System.out.println("test2 = " + test2);
        System.out.println("test3 = " + test3);

    }

    @DisplayName("collect test")
    @Test
    void collect_test() {
        // given
        List<String> list = List.of("a", "a", "b", "b", "b", "c", "d");

        // when
        List<String> result = list.stream()
                .collect(Collectors.toList());

        List<String> shortResult = list.stream().toList();

        Set<String> result2 = list.stream()
                .collect(Collectors.toSet());
        Set<String> shortResult2 = new HashSet<>(list);

        LinkedList<String>shortResult3 = new LinkedList<>(list);
        LinkedList<String> result3 = list.stream()
                .collect(Collectors.toCollection(() -> new LinkedList<>()));

        // then
        result.forEach(System.out::println);
    }

    @DisplayName("collectingAndThen test")
    @Test
    void collectingAndThen_test() {
        // given
        List<Integer> list = Arrays.asList(1, 3, 6);

        // when
        Integer result = list.stream()
                .collect(Collectors.collectingAndThen(
                        Collectors.toList(),
                        i -> i.stream().reduce(Integer::sum)))
                .orElseThrow();

        //then
        System.out.println("result = " + result);

    }

    @DisplayName("joining test")
    @Test
    void joining_test() {
        // given
        List<String> list = List.of("aa", "bb", "cc", "dd");

        // when
        String result1 = list.stream()
                .collect(Collectors.joining());

        String result2 = list.stream()
                .collect(Collectors.joining(", "));

        String result3 = list.stream()
                .collect(Collectors.joining(", ", "[", "]"));

        //then
        System.out.println("result1 = " + result1);
        System.out.println("result2 = " + result2);
        System.out.println("result3 = " + result3);
    }
}
