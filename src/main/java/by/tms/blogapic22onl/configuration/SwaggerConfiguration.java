package by.tms.blogapic22onl.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfiguration {

    private final ApplicationProperties applicationProperties;

    @Bean
    public Docket createDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .useDefaultResponseMessages(false)
                .protocols(Set.of("https", "http"))
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage(applicationProperties.getBasePackage()))
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(applicationProperties.getTitle())
                .description(applicationProperties.getDescription())
                .termsOfServiceUrl(applicationProperties.getTerms())
                .version(applicationProperties.getVersion())
                .build();
    }
}
}
