package com.jio.callvision.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

@RestController
public class IndexController {

    @GetMapping(value = "/", produces = "text/html")
    public String index() {
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream("static/index.html");
             Scanner scanner = new Scanner(inputStream, StandardCharsets.UTF_8)) {
            return scanner.useDelimiter("\\A").next();
        } catch (Exception e) {
            return "<h1>Error loading index.html</h1>";
        }
    }
}