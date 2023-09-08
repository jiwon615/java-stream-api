package com.stream.jiwon;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

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
}
