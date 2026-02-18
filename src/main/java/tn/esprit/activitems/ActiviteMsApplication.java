package tn.esprit.activitems;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ActiviteMsApplication {

    public static void main(String[] args) {
        SpringApplication.run(ActiviteMsApplication.class, args);
    }

}
