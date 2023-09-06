package de.samples.javaee.todos.domain;

import jakarta.enterprise.context.ApplicationScoped;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@ApplicationScoped
public class TodosService {

    private final Collection<Todo> todos = new LinkedList<>();

    public Collection<Todo> findAll() {
        return todos;
    }

    public Collection<Todo> findByTitle(String title) {
        return todos
                .stream()
                .filter(t -> t.getTitle().toLowerCase().contains(title.toLowerCase()))
                .collect(Collectors.toList());
    }

    public Optional<Todo> findByUUID(UUID uuid) {
        return this.todos
                .stream()
                .filter(todo -> todo.getUuid().equals(uuid))
                .findFirst();
    }

    public void create(Todo todo) {
        todo.setUuid(UUID.randomUUID());
        this.todos.add(todo);
    }

}
