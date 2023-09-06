package de.samples.javaee.todos.boundary.rest;

import de.samples.javaee.todos.domain.Todo;
import de.samples.javaee.todos.domain.TodosService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import lombok.RequiredArgsConstructor;

import java.util.Collection;
import java.util.UUID;

@Path("/todos")
@RequiredArgsConstructor(onConstructor_ = @Inject)
public class TodosResource {

    // Weitere Features von JAX-RS siehe
    // https://github.com/ueberfuhr-trainings/javaee-vs-spring/blob/master/todos-javaee/src/main/java/de/sample/javax/todos/boundary/rest/TodosController.java

    private final TodosService service;

//    @Inject
//    public TodosResource(TodosService service) {
//        this.service = service;
//    }

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN})
    public Collection<Todo> readAll(@QueryParam("search") String search) {
        return null != search
                ? service.findByTitle(search)
                : service.findAll();
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
        return this.service.findByUUID(uuid).get();
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
    public Response create(@Valid Todo input, @Context UriInfo info) {
        this.service.create(input);
        var location = info.getAbsolutePathBuilder()
                .path(input.getUuid().toString())
                .build();
        return Response
                .created(location)
                .entity(input)
                .build();
    }

}
