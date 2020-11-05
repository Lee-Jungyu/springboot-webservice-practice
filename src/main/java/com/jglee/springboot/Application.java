package com.jglee.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

//JPA Auditing 활성화
//@EnableJpaAuditing
//스프링 부트의 자동 설정, 스프링 Bean 읽기와 생성을 모두 자동으로 설정
//@SpringBootApplication이 있는 위치부터 설정을 읽어나가기 때문에 이 클래스는 항상 프로젝트의 최상단에 위치
@SpringBootApplication
public class Application {
    public static void main(String[] args) {

        //내장 WAS를 실행함
        //언제 어디서나 같은 환경에서 스프링 부트를 배포할 수 있기 때문에 내장 WAS사용을 권장
        SpringApplication.run(Application.class, args);
    }
}
