package de.samples.javaee.todos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Todo {

    private String title;
    private String description;
    private LocalDate dueDate;

    public Todo(String title) {
        this(title, null, null);
    }
}
