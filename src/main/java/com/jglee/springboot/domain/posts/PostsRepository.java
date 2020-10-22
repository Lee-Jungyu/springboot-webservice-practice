package com.jglee.springboot.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;

//Dao라고 불리는 DB Layer 접근자
//JPA에서는 Repository라고 부르며 인터페이스로 생성
//단순히 인터페이스를 생성한 후 JpaRepository<Entity class, PK type>를 상속하면 기본적인 CRUD 메소드가 자동 생성
public interface PostsRepository extends JpaRepository<Posts, Long> {
    
}
