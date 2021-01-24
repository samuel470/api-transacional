package com.mota.samuel.desafiotransacoes.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;



@EnableSwagger2
@Configuration
public class SwaggerConf {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis( RequestHandlerSelectors.basePackage("com.mota.samuel.desafiotransacoes.controller"))
                .paths(PathSelectors.any())
                .build()
                .useDefaultResponseMessages(false)
                .apiInfo(apiInfo())
                ;
    }




    private ApiInfo apiInfo() {

        return new ApiInfoBuilder()
                .title("Desafio transações")
                .description("Desafio para vaga de desenvolvedor Java Jr.")
                .version("1.0")
                .contact(contact())
                .build();
    }

    private Contact contact(){
        return new Contact("Samuel Mota da Silva",
                "https://www.linkedin.com/in/samuel470/",
                "sssamu.silva@hotmail.com");
    }




    }
