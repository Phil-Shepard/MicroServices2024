package org.example.kafkaexample.controllers;

import org.example.kafkaexample.services.ExampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExampleController {
    private ExampleService exampleService;

    @Autowired
    public ExampleController(ExampleService exampleService) {
        this.exampleService = exampleService;
    }

    @PostMapping("/send-message")
    public String exampleMethod(@RequestParam String message) {
        exampleService.sendMessage(message);
        return "message sent";
    }
}
