package de.samples.javaee.todos.boundary;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/hello")
public class HelloWorldServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // REQ auslesen (Parameter, Header, ...)
        String name = req.getParameter("name");
        // auf NULL prüfen
        if(null == name || name.isEmpty()) {
            // resp.sendError(400);
            // return;
            name = "World";
        }
        // Konvertieren / Validieren
        // Aktion
        // Antwortgenerierung
        resp.setContentType("text/html");
        try(PrintWriter out = resp.getWriter()) {
            out.println("<h1>Hello</h1><p>Hello " + name + "</p>");
        }
    }
}

