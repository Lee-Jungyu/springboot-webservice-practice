package com.jglee.springboot.config.auth;

import com.jglee.springboot.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
//Spring Security 설정들을 활성화시켜줌
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //h2-console 화면을 사용하기 위해 해당 옵션들을 disable
        http.csrf().disable().headers().frameOptions().disable()
                .and()
                //authorizeRequests: URL별 권한 관리를 설정하는 옵션의 시작점
                .authorizeRequests()
                //antMatcher: 권한 관리 대상을 지정하는 옵션. URL, HTTP 메소드별로 관리가 가능함
                .antMatchers("/", "/css/**", "/images/**","/js/**", "/h2-console/**", "/profile")
                // "/" 등 지정된 URL들은 permitAll() 옵션을 통해 전체 열람 권한을 줌
                .permitAll()
                //POST 메소드이면서 "/api/v1/**"주소를 가진 api는 USER 권한을 가진 사람만 가능
                .antMatchers("/api/v1/**").hasRole(Role.USER.name())
                //anyRequest: 설정된 값 이외 나머지 URL들을 나타냄
                .anyRequest().authenticated()
                //로그아웃 기능에 대한 여러 설정의 진입점. 성공 시 / 주소로 이동
                .and().logout().logoutSuccessUrl("/")
                //oauth2Login: OAuth2 로그인 기능에 대한 여러 설정의 진입점
                .and().oauth2Login()
                //userInfoEndpoint: OAuth2 로그인 성공 이후 사용자 정보를 가져올 때 설정들을 담당
                .userInfoEndpoint()
                //userService: 소셜 로그인 성공 시 후속 조치를 진행할 UserService 인터페이스의 구현체를 등록
                .userService(customOAuth2UserService);
    }
}
