package br.net.silva.daniel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class GeradorRelatorioApplication {

    public static void main(String[] args) {
        SpringApplication.run(GeradorRelatorioApplication.class, args);
    }

}
