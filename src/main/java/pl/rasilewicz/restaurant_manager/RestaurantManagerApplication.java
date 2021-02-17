package pl.rasilewicz.restaurant_manager;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
public class RestaurantManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestaurantManagerApplication.class, args);
    }

}
