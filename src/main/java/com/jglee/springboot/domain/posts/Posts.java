package com.jglee.springboot.domain.posts;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

//클래스 내 모든 필드의 Getter 메소드를 자동생성
@Getter
//기본 생성자 자동 추가
@NoArgsConstructor
//테이블과 링크될 클래스임을 나타냄
@Entity
public class Posts {

    //PK Field
    @Id
    //PK의 생성 규칙
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //테이블의 칼럼을 나타냄 (굳이 선언하지 않아도 해당 클래스의 필드는 모두 칼럼이 됨)
    //사용 이유는 기본값 외에 추가로 변경이 필요한 옵션이 있을 시 사용
    @Column(length = 150, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    //해당 클래스의 빌더 패턴 클래스를 생성
    //생성자 상단에 선언 시 생성자에 포함된 필드만 빌더에 포함
    @Builder
    public Posts(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
