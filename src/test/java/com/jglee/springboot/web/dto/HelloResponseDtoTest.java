package com.jglee.springboot.web.dto;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class HelloResponseDtoTest {

    @Test
    public void test_lombok_function() {

        //given
        String name = "test";
        int amount = 1000;

        //when
        HelloResponseDto dto = new HelloResponseDto(name, amount);

        //then
        //assertThat: assertJ라는 테스트 검증 라이브러리의 검증 메소드, 검증하고 싶은 대상을 인자로 받음
        //isEqualTo: assertJ의 동등 비교 메소드
        assertThat(dto.getName()).isEqualTo(name);
        assertThat(dto.getAmount()).isEqualTo(amount);
    }
}
