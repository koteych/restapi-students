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

import com.google.gson.Gson;

class HelloHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String response = "Hello, this is a simple REST service!";
        exchange.sendResponseHeaders(200, response.length());
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
}

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

        List<Student> retrievedStudents = new ArrayList<>();

        StudentService studentService = new StudentService();

        retrievedStudents = studentService.getAllStudents();

        for (Student student : retrievedStudents) {
            System.out.println(student.getName());
        }

        studentService.deleteStudent(2);
        studentService.addStudent("hello");

        DatabaseConnector dc = new DatabaseConnector();

        System.out.println("Hello, let's rebuld");

        int port = 8000;
        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
        server.createContext("/hello", new HelloHandler());
        server.createContext("/api/students", new JsonHandler());
        server.setExecutor(null); // creates a default executor
        server.start();
        System.out.println("Server is listening on port " + port);

    }
}