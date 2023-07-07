package com.example.restfullwebservice;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "WebService", value = "/users/*")
public class WebService extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        PrintWriter out = resp.getWriter();
        String path = req.getPathInfo();
        if (path == null) {
            String data = UserStatic.usersToGSon();
            out.println(data);
        } else {
            User user = UserStatic.getUser(Long.parseLong(path.substring(1)));
            String data = user.userToGSon();
            out.println(data);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // chuyen doi chuoi json sang doi tuong java
        ObjectMapper mapper = new ObjectMapper();
        User user = mapper.readValue(req.getInputStream(), User.class);

        UserStatic.addUser(user);
        resp.setContentType("application/json");
        PrintWriter out = resp.getWriter();
        String data = UserStatic.usersToGSon();
        out.println(data);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // chuyen doi chuoi json sang doi tuong java
        ObjectMapper mapper = new ObjectMapper();
        User user = mapper.readValue(req.getInputStream(), User.class);

        UserStatic.updateUser(user);
        resp.setContentType("application/json");
        PrintWriter out = resp.getWriter();
        String data = UserStatic.usersToGSon();
        out.println(data);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getPathInfo();
        UserStatic.deleteUser(Long.parseLong(path.substring(1)));
        resp.setContentType("application/json");
        PrintWriter out = resp.getWriter();
        String data = UserStatic.usersToGSon();
        out.println(data);
    }
}