package de.samples.javaee.todos.restassured;

import java.lang.annotation.*;

@Documented
@Inherited
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@RestAssuredConfiguration(
        port = 9080
)
public @interface LibertyRestAssuredConfiguration {

}
