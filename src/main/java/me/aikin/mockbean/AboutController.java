package me.aikin.mockbean;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/about")
public class AboutController {

    @GetMapping
    public String about() {
        return "just try";
    }
}
