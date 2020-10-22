package com.jglee.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//스프링 부트의 자동 설정, 스프링 Bean 읽기와 생성을 모두 자동으로 설정
@SpringBootApplication
public class Application {
    public static void main(String[] args) {

        //내장 WAS를 실행함
        //언제 어디서나 같은 환경에서 스프링 부트를 배포할 수 있기 때문에 내장 WAS사용을 권장
        SpringApplication.run(Application.class, args);
    }
}
