package com.example.demoservlethalyk;

import java.io.*;

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    private String message;

    public void init() {
        message = "Hello World!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String name= request.getParameter("name");
        String position = request.getParameter("position");

        response.getWriter().printf("Employee's name - %s, employee's position - %s ", name, position);

    }

    public void destroy() {
    }
}