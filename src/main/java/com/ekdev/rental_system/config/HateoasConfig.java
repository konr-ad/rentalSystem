package com.ekdev.rental_system.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.web.HateoasPageableHandlerMethodArgumentResolver;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class HateoasConfig implements WebMvcConfigurer {

    @Bean
    public HateoasPageableHandlerMethodArgumentResolver hateoasPageableResolver() {
        return new HateoasPageableHandlerMethodArgumentResolver();
    }

    @Bean
    public PagedResourcesAssembler<Object> pagedResourcesAssembler(
            HateoasPageableHandlerMethodArgumentResolver resolver) {
        return new PagedResourcesAssembler<>(resolver, null);
    }
}
