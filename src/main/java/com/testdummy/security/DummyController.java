package com.testdummy.security;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class DummyController {

    @GetMapping("/viewemployees")
    public String endPoint1Get(){
        return "List Of Employeeds";
    }

    @GetMapping("/viewfeedback")
    public String endPoint2Get(){
        return "List of feedback";
    }
    @PostMapping("/addnewemployee")
    public String endPoint2Post(){
        return "Added Arpit Thumar";
    }
    @PutMapping("/updateemployee")
    public String endPoint2Put(){
        return "Arpit Address changed from Gondal to Gondal State";
    }
    @DeleteMapping("/fireemployee")
    public String endPoint2Delete(){
        return "Jap Fired";
    }
    @GetMapping("/literallytest")
    public String testget(){
        return "Test for Get Granted";
    }

    @PostMapping("/literallytest")
    public String testpost(){
        return "Test for Post Granted";
    }

}
