package me.javaexample.javademo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    private final CustomResourceTransform customResourceTransform;

    public WebMvcConfig(CustomResourceTransform customResourceTransform){
        this.customResourceTransform = customResourceTransform;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/views/**")
                .addResourceLocations("classpath:/views/")
                .setCachePeriod(0)
                .resourceChain(true)
                .addResolver(new PathResourceResolver())
                .addTransformer(customResourceTransform)
        ;
    }
}
