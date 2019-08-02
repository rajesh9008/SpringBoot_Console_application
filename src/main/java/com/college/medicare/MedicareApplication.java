package com.college.medicare;

import com.college.medicare.services.DashBoard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MedicareApplication implements CommandLineRunner {

    @Autowired
    private DashBoard dashBoard;

    public static void main(String[] args) {
        SpringApplication.run(MedicareApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        dashBoard.initDB();
        dashBoard.showMenu();
    }
}
