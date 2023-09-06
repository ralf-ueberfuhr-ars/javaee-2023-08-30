package de.samples.javaee.todos.boundary;

import de.samples.javaee.todos.domain.Todo;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@WebServlet("read-todos")
public class ReadAllTodosServlet extends HttpServlet {

//    @Inject
//    private Validator validator;

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
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var search = req.getParameter("search");
        Stream<Todo> todos = this.todos.stream();
        if (null != search) {
            todos = todos.filter(t -> t.getTitle().toLowerCase().contains(search.toLowerCase()));
        }
        var accept = req.getHeader("Accept");
        if (accept != null && accept.contains("text/plain")) {
            resp.setContentType("text/plain");
            try (PrintWriter out = resp.getWriter()) {
                out.println("Todos:");
                out.println("======");
                out.println();
                todos.forEach(t -> {
                    out.print(" - " + t.getTitle());
                    if (null != t.getDueDate()) {
                        out.print(" (");
                        out.print(t.getDueDate().format(DateTimeFormatter.ISO_DATE));
                        out.print(")");
                    }
                    out.println();
                });
            }
        } else {
            // Weiterleitung an JSP
            req.setAttribute("todos", todos.collect(Collectors.toList()));
            // SESSION: req.getSession().setAttribute("", ...);
            // APPLICATION: getServletContext().setAttribute("", ...);
            req
                    .getRequestDispatcher("/WEB-INF/jsp/display-todos.jsp")
                    .forward(req, resp);
        }
    }

}
