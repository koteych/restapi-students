package org.restapi;

import java.io.*;
import java.net.*;
import java.util.HashMap;
import java.util.Map;

import java.io.IOException;
import java.io.OutputStream;
import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import java.net.InetSocketAddress;


/* 

class IncomingRequestsHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String method = exchange.getRequestMethod();
        String path = exchange.getRequestURI().getPath();

        if (method.equals("GET") && path.startsWith("/data")) {
            handleGet(exchange);
        } else if (method.equals("POST") && path.equals("/data")) {
            handlePost(exchange);
        } else if (method.equals("DELETE") && path.startsWith("/data/")) {
            handleDelete(exchange);
        } else {
            sendResponse(exchange, 404, "Not Found");
        }
    }

    private void handleGet(HttpExchange exchange) throws IOException {
        String response = "Data: " + data.toString();
        sendResponse(exchange, 200, response);
    }

    private void handlePost(HttpExchange exchange) throws IOException {
        InputStreamReader isr = new InputStreamReader(exchange.getRequestBody(), "utf-8");
        BufferedReader br = new BufferedReader(isr);
        String requestBody = br.readLine();
        data.put(idCounter, requestBody);
        idCounter++;
        sendResponse(exchange, 201, "Created");
    }

    private void handleDelete(HttpExchange exchange) throws IOException {
        String[] pathParts = exchange.getRequestURI().getPath().split("/");
        if (pathParts.length != 3) {
            sendResponse(exchange, 400, "Bad Request");
            return;
        }
        int id = Integer.parseInt(pathParts[2]);
        if (data.containsKey(id)) {
            data.remove(id);
            sendResponse(exchange, 200, "OK");
        } else {
            sendResponse(exchange, 404, "Not Found");
        }
    }

    private void sendResponse(HttpExchange exchange, int statusCode, String response) throws IOException {
        exchange.sendResponseHeaders(statusCode, response.length());
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
}

*/