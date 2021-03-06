package com.jglee.springboot.web;

import com.jglee.springboot.config.auth.LoginUser;
import com.jglee.springboot.config.auth.dto.SessionUser;
import com.jglee.springboot.domain.user.User;
import com.jglee.springboot.service.posts.PostsService;
import com.jglee.springboot.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;
    private final HttpSession httpSession;

    @GetMapping("/")
    //기존 httpSession.getAttribute("user")로 가져오던 세션 정보 값이 개선 됨
    public String index(Model model, @LoginUser SessionUser user) {
        //서버 템플릿 엔진에서 사용할 수 있는 객체를 저장할 수 있습니다.
        model.addAttribute("posts", postsService.findAllDesc());

        if(user != null) {
            model.addAttribute("user_name", user.getName());
        }
        return "index";
    }

    @GetMapping("/posts/save")
    public String postsSave() {
        return "posts-save";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model) {
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post", dto);

        return "posts-update";
    }
}
