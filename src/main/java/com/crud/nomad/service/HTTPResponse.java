package com.crud.nomad.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public interface HTTPResponse {
    Logger log = LoggerFactory.getLogger(HTTPResponse.class);

    default String getResponseBody(HttpRequest request) {
        String responseBody = "";
        try {
            HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
            responseBody = response.body();
        } catch (InterruptedException | IOException e) {
            log.info(e.getMessage());
        }
        return responseBody;
    }
}