package de.samples.javaee.todos.domain;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Todo {

    private UUID uuid;
    @NotNull
    @Size(min = 3)
    private String title;
    private String description;
    private LocalDate dueDate;

    public Todo(UUID uuid, String title) {
        this(uuid, title, null, null);
    }

    // TODO: JSP
    public Date getDueDateAsDate() {
        return null != dueDate
                ? Date.from(dueDate.atStartOfDay(ZoneId.systemDefault()).toInstant())
                : null;
    }
}
