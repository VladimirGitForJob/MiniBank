package com.example.minibank.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
        info = @Info(
                title = "Banking service",
                description = "The service is creating bank accounts and transfer money between them",
                contact = @Contact(
                        name = "Vladimir Yaltsev",
                        email = "v.yaltsev@astondevs.ru"
                )
        )
)

public class BankApiConfig {
}
