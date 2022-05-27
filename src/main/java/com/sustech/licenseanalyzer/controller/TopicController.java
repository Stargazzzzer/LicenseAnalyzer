package com.sustech.licenseanalyzer.controller;

import com.sustech.licenseanalyzer.service.TopicService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TopicController {
    @RequestMapping("/topic")
    @ResponseBody
    public String licenseHello() {
        return "Get Topic Hello";
    }

    @GetMapping(value = "/topic/details")
    public Object getAllTopic() {
        return TopicService.getAllTopic();
    }
}
