package mk.ukim.finki.db.distributorapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class DistributorAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(DistributorAppApplication.class, args);
    }

}
