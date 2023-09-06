package de.samples.javaee.todos.rest;

import de.samples.javaee.todos.Todo;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;

import java.net.URI;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Path("/todos")
public class TodosResource {

    // Weitere Features von JAX-RS siehe
    // https://github.com/ueberfuhr-trainings/javaee-vs-spring/blob/master/todos-javaee/src/main/java/de/sample/javax/todos/boundary/rest/TodosController.java

    private final Collection<Todo> todos = new LinkedList<>(Arrays.asList(
            new Todo(
                    UUID.randomUUID(),
                    "Servlets lernen"
            ),
            new Todo(
                    UUID.randomUUID(),
                    "Jakarta EE lernen",
                    "Alles zusammen braucht Zeit.",
                    LocalDate.now().plusWeeks(10)
            ),
            new Todo(
                    UUID.randomUUID(),
                    "Prüfen, ob a2<a3>a1"
            )
    ));

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN})
    public Collection<Todo> readAll(@QueryParam("search") String search) {
        Stream<Todo> todos = this.todos.stream();
        if (null != search) {
            todos = todos.filter(t -> t.getTitle().toLowerCase().contains(search.toLowerCase()));
        }
        return todos.collect(Collectors.toList());
    }

    /*
     * Abfragen des einzelnen Todos
     *  - GET /todos/{uuid}
     *    - Antwort
     *       - 200 OK mit Todo im Body
     *       - 404 Not Found
     */

    @GET
    @Path("/{uuid}")
    @Produces(MediaType.APPLICATION_JSON)
    public Todo findByUUID(@PathParam("uuid") UUID uuid) {
        return this.todos
                .stream()
                .filter(todo -> todo.getUuid().equals(uuid))
                .findFirst()
                .get();
    }

    /*
     * Anlegen eines Todos:
     *  - POST /todos
     *     - neue Todo im Body
     *     - Antwort:
     *        - 201 Created (Location-Header)
     *        - 400 (Validierungfehler)
     */

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(Todo input, @Context UriInfo info) {
        input.setUuid(UUID.randomUUID());
        this.todos.add(input);
        var location = info.getAbsolutePathBuilder()
                .path(input.getUuid().toString())
                .build();
        return Response
                .created(location)
                .entity(input)
                .build();
    }

}
