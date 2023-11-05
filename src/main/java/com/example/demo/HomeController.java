package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    @GetMapping("")
    public String index() {
        return "Hello from Demo application";
    }
    
    @GetMapping("/comment")
    public String readComment() {
        return "This is my comment";
    }
    
    @PostMapping("/comment")
    public String postComment() {
        return "Comment has been posted!";
    }
}
