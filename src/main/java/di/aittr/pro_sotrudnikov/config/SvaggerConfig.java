package di.aittr.pro_sotrudnikov.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
        info = @Info(
                title = "Proekt pro_sotrudnikov",
                description = "Application for various operations with employees, projects and tasks",
                version = "1.0.0",
                contact = @Contact(
                        name = "Svetlana",
                        email = "89042480071s@gmail.com",
                        url = "http://ait-tr.de"
                )
        )
)
public class SvaggerConfig {
}
