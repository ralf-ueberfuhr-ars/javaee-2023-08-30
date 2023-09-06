package de.samples.javaee.todos.domain;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.Initialized;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@ApplicationScoped
@RequiredArgsConstructor(onConstructor_ = @Inject)
public class TodosInitializer {

    private final TodosService service;

    public void initializeTodos(@Observes @Initialized(ApplicationScoped.class) Object useless) {
        service.create(
                new Todo(
                        null,
                        "CDI lernen"
                ));
        service.create(
                new Todo(
                        null,
                        "Jakarta EE lernen",
                        "Alles zusammen braucht Zeit.",
                        LocalDate.now().plusWeeks(10)
                )
        );
        service.create(
                new Todo(
                        null,
                        "Prüfen, ob a2<a3>a1"
                )
        );
    }


}
