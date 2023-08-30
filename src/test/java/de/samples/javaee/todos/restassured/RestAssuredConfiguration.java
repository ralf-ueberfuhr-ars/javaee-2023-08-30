package de.samples.javaee.todos.restassured;

import io.restassured.RestAssured;
import org.junit.jupiter.api.extension.ExtendWith;

import java.lang.annotation.*;

@Documented
@Inherited
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@ExtendWith(RestAssuredConfigurationExtension.class)
public @interface RestAssuredConfiguration {

    String baseURI() default RestAssured.DEFAULT_URI;

    int port() default RestAssured.DEFAULT_PORT;

    String basePath() default RestAssured.DEFAULT_PATH;

    // TODO provide static annotation for this
    // AuthenticationScheme authentication();

    String rootPath() default RestAssured.DEFAULT_BODY_ROOT_PATH;
}
