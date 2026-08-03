package com.prep.user_ms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.jdbc.autoconfigure.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {
        DataSourceAutoConfiguration.class
})
public class UserMsApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserMsApplication.class, args);
    }

}
