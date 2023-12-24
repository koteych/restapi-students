package org.restapi;

import java.io.IOException;
import java.io.OutputStream;
import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import java.net.InetSocketAddress;

import java.util.List;
import java.util.ArrayList;

import org.restapi.StudentService;
import org.restapi.Student;
import org.restapi.IncomingRequestsHandler;

import com.google.gson.Gson;

class JsonHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        Gson gson = new Gson();
        StudentService studentService = new StudentService();

        List<Student> retrievedStudents = new ArrayList<>();
        retrievedStudents = studentService.getAllStudents();

        String jsonResponse = gson.toJson(retrievedStudents);

        exchange.getResponseHeaders().set("Content-Type", "application/json");
        exchange.sendResponseHeaders(200, jsonResponse.length());
        OutputStream os = exchange.getResponseBody();
        os.write(jsonResponse.getBytes());
        os.close();
    }
}

public class App 
{
    public static void main(String[] args) throws IOException {
        int port = 8000;
        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
        server.createContext("/api/students", new JsonHandler());
        server.createContext("/api/v2/students", new IncomingRequestsHandler());
        server.setExecutor(null);
        server.start();
        System.out.println("Server is listening on port " + port);
    }
}