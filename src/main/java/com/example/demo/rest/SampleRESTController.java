package com.example.demo.rest;

import org.springframework.web.bind.annotation.*;

/**
 * Sample REST controller.
 */
@RestController
@RequestMapping("/api")
public class SampleRESTController {

    /**
     * GET /all : get all.
     *
     */
    @GetMapping("/hello")
    public String getAll() {
        return "hello world";
    }
}
