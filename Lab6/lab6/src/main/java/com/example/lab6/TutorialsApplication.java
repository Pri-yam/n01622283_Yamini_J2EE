package com.example.lab6;
import com.example.lab6.config.AppConfig;
import com.example.lab6.service.GreetingService;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


@SpringBootApplication
public class TutorialsApplication {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        GreetingService greetingService = context.getBean(GreetingService.class);
        System.out.println(greetingService.sayHello());

        greetingService.destroy();
        context.close();

//        SpringApplication.run(TutorialsApplication.class, args);
    }
}