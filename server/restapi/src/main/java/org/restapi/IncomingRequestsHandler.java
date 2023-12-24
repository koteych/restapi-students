package org.restapi;

import java.io.*;
import java.net.*;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;

import java.io.IOException;
import java.io.OutputStream;
import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import java.net.InetSocketAddress;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.restapi.StudentService;
import org.restapi.Student;

// curl -X POST -i -d '{"firstName": "John", "lastName": "Doe", "middleName": "Smith", "id": "12345", "group": "A", "birthdate": "2000-01-01"}' -H "Content-Type: application/json" http://localhost:8000/api/v2/students
// curl -H "Accept: application/json" http://localhost:8000/api/v2/students
// curl -X DELETE http://localhost:8000/api/v2/students/4

class IncomingRequestsHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String method = exchange.getRequestMethod();
        String path = exchange.getRequestURI().getPath();

        if (method.equals("GET") && path.startsWith("/api/v2/students")) {
            handleGet(exchange);
        } else if (method.equals("POST") && path.equals("/api/v2/students")) {
            handlePost(exchange);
        } else if (method.equals("DELETE") && path.startsWith("/api/v2/students")) {
            handleDelete(exchange);
        } else {
            sendResponse(exchange, 404, "Not Found");
        }
    }

    private void handleGet(HttpExchange exchange) throws IOException {
        
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

    private void handlePost(HttpExchange exchange) throws IOException {        
        Gson gson = new Gson();

        InputStreamReader isr = new InputStreamReader(exchange.getRequestBody(), "utf-8");
        BufferedReader br = new BufferedReader(isr);
        StringBuilder buf = new StringBuilder();
        int b;

        while ((b = br.read()) != -1) {
            buf.append((char) b);
        }

        br.close();
        isr.close();

        String requestBody = buf.toString();
        JsonObject jsonObject = gson.fromJson(requestBody, JsonObject.class);
        
        String firstName = jsonObject.get("firstName").getAsString();
        String lastName = jsonObject.get("lastName").getAsString();
        String middleName = jsonObject.get("middleName").getAsString();
        String group = jsonObject.get("group").getAsString();
        String birthdate = jsonObject.get("birthdate").getAsString();

        StudentService studentService = new StudentService();
        studentService.addStudent(firstName, lastName, middleName, birthdate, group);

        sendResponse(exchange, 200, "ok");
    }

    private void handleDelete(HttpExchange exchange) throws IOException {
        String[] pathParts = exchange.getRequestURI().getPath().split("/");

        if (pathParts.length != 5) {
            sendResponse(exchange, 400, "Bad Request");
            return;
        }

        int id = Integer.parseInt(pathParts[4]);

        StudentService studentService = new StudentService();
        studentService.deleteStudent(id);

        sendResponse(exchange, 200, "OK");

    }

    private void sendResponse(HttpExchange exchange, int statusCode, String response) throws IOException {
        exchange.getResponseHeaders().set("Content-Type", "application/json");
        exchange.sendResponseHeaders(statusCode, response.length());
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
}

