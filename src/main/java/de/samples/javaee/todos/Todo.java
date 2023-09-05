package de.samples.javaee.todos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

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

    public Date getDueDateAsDate() {
        return null != dueDate
                ? Date.from(dueDate.atStartOfDay(ZoneId.systemDefault()).toInstant())
                : null;
    }
}
