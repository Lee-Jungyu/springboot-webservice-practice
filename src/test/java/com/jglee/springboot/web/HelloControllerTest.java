package com.jglee.springboot.web;

import com.jglee.springboot.config.auth.SecurityConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

//테스트를 진행할 때 JUnit에 내장된 실행자 외에 다른 실행자를 실행
//여기서는 SpringRunner라는 스프링 실행자를 사용
//스프링 부트 테스트와 JUnit사이에 연결자 역할을 함
@RunWith(SpringRunner.class)
//여러 스프링 테스트 어노테이션 중, Web(Spring MVC)에 집중할 수 있는 어노테이션
//여기서는 컨트롤러만 사용하기 때문에 선언
@WebMvcTest(controllers = HelloController.class,
        excludeFilters = {
        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = SecurityConfig.class)
        }
)
public class HelloControllerTest {

    //스프링이 관리하는 빈(Bean)을 주입 받음
    @Autowired
    //웹 API를 테스트할 때 사용
    //스프링 MVC테스트의 시작점
    //이 클래스를 통해 HTTP GET, POST 등에 대한 API 테스트 가능
    private MockMvc mvc;

    @WithMockUser(roles="USER")
    @Test
    public void return_hello() throws Exception {
        String hello = "hello";

        mvc.perform(get("/hello")) //MockMvc를 통해 /hello 주소로 HTTP GET 요청을 함
                .andExpect(status().isOk()) //mvc.perform의 결과를 검증, HTTP Header의 Status를 검증 -> isOK: 200인지 체크
                .andExpect(content().string(hello)); //mvc.perform의 결과를 검증, 응답 본문의 내용을 검증 -> "hello"인지 체크
    }

    @WithMockUser(roles="USER")
    @Test
    public void return_helloDto() throws Exception {
        String name = "hello";
        int amount = 1000;

        mvc.perform(get("/hello/dto")
                .param("name", name) //param: API 테스트할 때 사용될 요청 파라미터를 설정 (단, String만 허용)
                .param("amount", String.valueOf(amount)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(name))) //jsonPath: JSON 응답값을 필드별로 검증할 수 있는 메소드, $를 기준으로 필드명 명시
                .andExpect(jsonPath("$.amount", is(amount)));
    }
}
