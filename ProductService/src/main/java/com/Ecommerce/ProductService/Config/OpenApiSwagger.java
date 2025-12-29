package com.Ecommerce.ProductService.Config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiSwagger {
    @Bean
    public OpenAPI productServiceOpenAPI(){
        return new OpenAPI()
                .info(new Info()
                        .title("Product Service API")
                        .description("CRUD, Search, Pagination APIs for Products")
                        .version("1.0.0"));
    }
}
