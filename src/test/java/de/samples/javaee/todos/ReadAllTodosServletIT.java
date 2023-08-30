package de.samples.javaee.todos;

import de.samples.javaee.todos.restassured.LibertyRestAssuredConfiguration;
import org.junit.jupiter.api.Test;

import java.util.function.Function;

import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.*;

@LibertyRestAssuredConfiguration
public class ReadAllTodosServletIT {

    private static Function<String, String> withoutCharset() {
        return s -> s.split(";")[0];
    }

    @Test
    void shouldReturnPlainTextOnReadAll() {
        when()
                .get("/read-todos")
        .then()
                .assertThat()
                .statusCode(200)
                .header("Content-Type", withoutCharset(), is("text/plain"))
                .body(is(not(emptyString())));
    }

}
