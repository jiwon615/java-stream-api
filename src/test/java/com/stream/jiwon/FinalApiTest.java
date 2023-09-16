package com.stream.jiwon;

import com.stream.jiwon.dto.Member;
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

        LinkedList<String> shortResult3 = new LinkedList<>(list);
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

    @DisplayName("partitionBy test")
    @Test
    void partitionBy_test() {
        // given
        List<Integer> list = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        Map<Boolean, List<Integer>> result = list.stream()
                .collect(Collectors.partitioningBy(i -> i > 3)); // 결과: {false=[1, 2, 3], true=[4, 5, 6, 7, 8, 9, 10]}

        Map<Boolean, Long> result2 = list.stream()
                .collect(Collectors.partitioningBy(i -> i > 3, Collectors.counting())); // 결과: {false=3, true=7}

    }

    @DisplayName("groupingBy test")
    @Test
    void groupingBy_test() {
        // given
        Member member1 = Member.builder().name("테스터1").country("한국").build();
        Member member2 = Member.builder().name("테스터2").country("미국").build();
        Member member3 = Member.builder().name("테스터3").country("캐나다").build();
        Member member4 = Member.builder().name("테스터4").country("한국").build();
        Member member5 = Member.builder().name("테스터5").country("한국").build();
        Member member6 = Member.builder().name("테스터6").country("캐나다").build();
        Member member7 = Member.builder().name("테스터7").country("일본").build();

        List<Member> members = List.of(member1, member2, member3, member4, member5, member6, member7);

        // 국가별 해당하는 Member 값 그룹화
        Map<String, List<Member>> result1 = members.stream()
                .collect(Collectors.groupingBy(Member::getCountry));

        // 국가별 해당하는 Member 의 count 그룹화
        Map<String, Long> result2 = members.stream()
                .collect(Collectors.groupingBy(Member::getCountry, Collectors.counting()));

        // 국가별 해당하는 Member 의 이름(name)을 (이름1,이름2)이런 형태로 그룹화 한 뒤, HashMap 형태로 반환
        HashMap<String, String> result3 = members.stream()
                .collect(
                        Collectors.groupingBy(
                                Member::getCountry,
                                HashMap::new,
                                Collectors.mapping(Member::getName, Collectors.joining(",", "(", ")")))
                );
    }

    @DisplayName("Collectors.mapping test")
    @Test
    void mapping_test() {
        // given
        Member member1 = Member.builder().name("테스터1").country("한국").build();
        Member member2 = Member.builder().name("테스터2").country("미국").build();
        Member member3 = Member.builder().name("테스터3").country("캐나다").build();
        Member member4 = Member.builder().name("테스터4").country("한국").build();
        Member member5 = Member.builder().name("테스터5").country("한국").build();
        Member member6 = Member.builder().name("테스터6").country("캐나다").build();
        Member member7 = Member.builder().name("테스터7").country("일본").build();

        List<Member> members = List.of(member1, member2, member3, member4, member5, member6, member7);


        // when
        List<String> result = members.stream()
                .collect(Collectors.mapping(Member::getCountry, Collectors.toList()));

        List<String> result2 = members.stream()
                .map(Member::getCountry)
                .toList();
    }

    @DisplayName("Collectors.averagingInt test")
    @Test
    void averagingInt_test() {
        // given
        List<String> list = List.of("1", "2", "3", "4", "5");

        // when
        Double result = list.stream()
                .collect(Collectors.averagingInt(Integer::parseInt));

        //then
        System.out.println("result = " + result); // 3.0
    }

    @DisplayName("Collectors.averagingDouble test")
    @Test
    void averagingDouble_test() {
        // given
        List<String> list = List.of("1", "2", "3", "4", "5");

        // when
        Double result = list.stream()
                .collect(Collectors.averagingDouble(Integer::parseInt));

        Double result2 = list.stream()
                .collect(Collectors.averagingLong(Integer::parseInt));

        //then
        System.out.println("result = " + result); // 3.0
        System.out.println("result2 = " + result2); // 3.0
    }
}
