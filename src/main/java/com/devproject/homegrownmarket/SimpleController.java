package com.devproject.homegrownmarket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SimpleController {

    @Autowired
    private GlobalProperties globalProperties;

    @GetMapping("/hello")
    @ResponseBody
    public String homePage() {
        return globalProperties.getName();
    }
}
