package com.example.restfulwebservices.helloworld;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@RestController
public class HelloWorldController {

    @Autowired
    private MessageSource bundleMessageSource;

    @GetMapping("/hello-world")
    public String helloWorldInternationalization(@RequestHeader(name = "Accept-Language", required = false) Locale locale){

     return bundleMessageSource.getMessage("good.morning.message", null, locale);
    }
}
