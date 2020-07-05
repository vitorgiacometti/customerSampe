package br.example.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.context.annotation.PropertySource;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
@EnableSwagger2
@PropertySource("classpath:swagger.properties")
public class Swagger {

    @Bean
    public Docket Api() {

        ApiInfo apiInfo = new ApiInfoBuilder()
                    .title("Sample of Customer CRUD")
                    .description("API")
                    .version("1.0")
                    .build();

       Docket docket = new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo)
                .pathMapping("/")
                .groupName("Customer")
                .forCodeGeneration(true)
                .select()
                .paths(regex("/v1/.*"))
                .build()
                .useDefaultResponseMessages(false);

        docket = docket.select().paths(PathSelectors.any())
                .apis(RequestHandlerSelectors.basePackage("br.example.app"))
                .build();

        return docket;
    }


}
