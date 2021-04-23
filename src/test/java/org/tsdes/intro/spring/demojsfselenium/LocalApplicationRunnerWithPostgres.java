package org.tsdes.intro.spring.demojsfselenium;

import org.springframework.boot.SpringApplication;

public class LocalApplicationRunnerWithPostgres {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, "--spring.profiles.active=postgres");
    }
}
