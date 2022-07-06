package com.example.demoservlethalyk;

import java.io.*;
import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;


import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.json.JsonObject;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.json.JSONObject;

@WebServlet(/*urlPatterns = "/organizations/*"*/name = "organizations", value = "/organizations/*")
public class HelloServlet extends HttpServlet {
    private String message;
    AtomicInteger idCounter;

    Organization o1 = new Organization(1,"Halyk","Almaty", LocalDate.of(2008,04,12));
    Organization o2 = new Organization(2,"Halyk_2","Almaty", LocalDate.of(2009,04,12));
    Organization o3 = new Organization(3,"Halyk_3","Almaty", LocalDate.of(20010,04,12));

    List<Organization> organizations = new ArrayList<>(List.of(o1,o2,o3));

    public void init() {
        message = "Hello";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String pathInfo = request.getPathInfo();
        String[] parts = pathInfo.split("/");
        String param = parts[1];

        int orgId = Integer.parseInt(param);
        request.setCharacterEncoding("UTF-8");

        final Organization organization = organizations.get(orgId-1);
        final  String json = new ObjectMapper().writeValueAsString(organization);

        response.setContentType("application/json; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.write(json);
    }

   public void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {

        request.setCharacterEncoding("UTF-8");
        final  String title = request.getParameter("title");
        final  String address = request.getParameter("address");

        final LocalDate creationDate = LocalDate.parse(request.getParameter("creationDate"));

        Organization organization = new Organization(idCounter.decrementAndGet(),title,address,creationDate);

        organizations.add(organization);

       final  String json = new ObjectMapper().writeValueAsString(organization);

       response.setContentType("application/json; charset=UTF-8");
       PrintWriter out = response.getWriter();
       out.write(json);

    }
    public void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String pathInfo = request.getPathInfo();
        String[] parts = pathInfo.split("/");
        String param = parts[1];

        final Organization organization = organizations.get(Integer.parseInt(param)-1);

        organizations.remove(Integer.parseInt(param));
        response.setStatus(202);
        PrintWriter out = response.getWriter();
        out.write("Delete" + organization.getTitle());

    }
        public void destroy() {
    }
}