package com.example.userservicespring.configs;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;

@Configuration
@SecurityScheme(
        name = "Bearer Authentication",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        scheme = "bearer"
)
@OpenAPIDefinition(
        info = @Info(
                contact = @Contact(
                        name = "Vinh"
                ),
                description = "OpenApi documentation for Project",
                title = "OpenApi specification",
                version = "1.0"
        ),

        servers = {
                @Server(url = "${springdoc.server}"),
        },

        security = {
        @SecurityRequirement(
                name = "Bearer Authentication"
        )
}
)
public class OpenApiConfig {}
