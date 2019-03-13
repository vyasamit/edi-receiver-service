package com.trimble.tcx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * @author arajan
 * @apiNote EDI Receiver Application
 */
@SpringBootApplication
@EnableMongoRepositories
public class EDIReceiverServiceApp {
    public static void main(String[] args) {
        SpringApplication.run(EDIReceiverServiceApp.class, args);
    }
}
