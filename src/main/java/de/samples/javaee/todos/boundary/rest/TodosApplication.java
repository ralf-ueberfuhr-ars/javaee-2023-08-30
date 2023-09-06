package de.samples.javaee.todos.boundary.rest;

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

@ApplicationPath("/api/v1")
public class TodosApplication extends Application {
}
