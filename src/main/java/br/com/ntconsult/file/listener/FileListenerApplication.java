package br.com.ntconsult.file.listener;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
public class FileListenerApplication {

    public static void main(String[] args) {
        SpringApplication.run(FileListenerApplication.class, args);
    }

}
