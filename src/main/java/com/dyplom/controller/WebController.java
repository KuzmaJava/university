package com.dyplom.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class WebController {

    @RequestMapping(path = "/accessDeniedPage", method = RequestMethod.GET)
    public String home() {
        return "accessDeniedPage";
    }

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String welcome() {
        return "welcomePage";
    }
}