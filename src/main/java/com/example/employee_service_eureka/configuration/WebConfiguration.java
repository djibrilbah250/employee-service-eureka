package com.example.employee_service_eureka.configuration;


import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfiguration implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        // Redirige la racine vers la page des alias pour faciliter les tests
        registry.addViewController("/").setViewName("redirect:/swagger-ui/index.html");
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
    }
}


