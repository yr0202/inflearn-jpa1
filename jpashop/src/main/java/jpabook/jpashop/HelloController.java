package jpabook.jpashop;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {
    @GetMapping("hello")
    public String hello(Model model) {
        model.addAttribute("data", "Hello World!");
        return "hello"; // 화면 이름, 스프링부트의 타임리프가 자동으로 templates 폴더 밑에 있는거로 연결해줌
        // 순수한 html을 뿌리고 싶어 => static 폴더 아래로
    }
}
