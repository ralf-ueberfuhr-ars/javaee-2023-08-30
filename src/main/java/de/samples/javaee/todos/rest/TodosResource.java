package de.samples.javaee.todos.rest;

import de.samples.javaee.todos.Todo;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Path("/todos")
public class TodosResource {

    // Weitere Features von JAX-RS siehe
    // https://github.com/ueberfuhr-trainings/javaee-vs-spring/blob/master/todos-javaee/src/main/java/de/sample/javax/todos/boundary/rest/TodosController.java

    private final Collection<Todo> todos = Arrays.asList(
            new Todo("Servlets lernen"),
            new Todo(
                    "Jakarta EE lernen",
                    "Alles zusammen braucht Zeit.",
                    LocalDate.now().plusWeeks(10)
            ),
            new Todo("Prüfen, ob a2<a3>a1")
    );

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN})
    public Collection<Todo> readAll(@QueryParam("search") String search) {
        Stream<Todo> todos = this.todos.stream();
        if (null != search) {
            todos = todos.filter(t -> t.getTitle().toLowerCase().contains(search.toLowerCase()));
        }
        return todos.collect(Collectors.toList());
    }

}
