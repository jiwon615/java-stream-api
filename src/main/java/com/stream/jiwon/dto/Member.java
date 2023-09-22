package com.stream.jiwon.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    private String name;
    private int age;
    private List<String> scores;

    @Builder
    private Member(String name, int age, List<String> scores) {
        this.name = name;
        this.age = age;
        this.scores = scores;
    }
}
