package me.javaexample.javademo.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {

    @GetMapping("/")
    public String redirect() {
        return "redirect:/views/main/index.html";
    }

    @GetMapping("/views")
    public String redirect2() {
        return "redirect:/views/main/index.html";
    }

    @GetMapping("/views/main")
    public String redirect3() {
        return "redirect:/views/main/index.html";
    }
}
