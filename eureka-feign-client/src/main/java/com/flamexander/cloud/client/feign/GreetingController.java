package com.flamexander.cloud.client.feign;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class GreetingController {
    private GreetingClient greetingClient;

    @Autowired
    public void setGreetingClient(GreetingClient greetingClient) {
        this.greetingClient = greetingClient;
    }

    @RequestMapping("/get-greeting")
    public String greeting(Model model) {
        String answer = greetingClient.greeting();
        String answer1 = greetingClient.greeting1();
        String answer2 = greetingClient.greeting2();
        System.out.println("Answer: " + answer);
        model.addAttribute("greeting", answer);
        model.addAttribute("greeting1", answer1);
        model.addAttribute("greeting2", answer2);
        return "greeting-view";
    }
}
