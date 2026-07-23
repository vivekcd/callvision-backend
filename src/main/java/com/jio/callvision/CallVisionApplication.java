package com.jio.callvision;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CallVisionApplication {

    public static void main(String[] args) {

        System.out.println("MYSQL_URL = " + System.getenv("MYSQL_URL"));
        System.out.println("MYSQLUSER = " + System.getenv("MYSQLUSER"));
        System.out.println("MYSQLPASSWORD = " + System.getenv("MYSQLPASSWORD"));
        System.out.println("SPRING_DATASOURCE_URL = " + System.getenv("SPRING_DATASOURCE_URL"));

        SpringApplication.run(CallVisionApplication.class, args);
    }
}