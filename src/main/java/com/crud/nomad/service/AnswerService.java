package com.crud.nomad.service;

import com.crud.nomad.config.GoogleSearchConfiguration;
import com.crud.nomad.domain.Answer;
import com.crud.nomad.exceptions.SearchException;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
@Service
@Slf4j
public class AnswerService {
    private final GoogleSearchConfiguration googleSearchConfiguration;

    @Autowired
    public AnswerService(GoogleSearchConfiguration googleSearchConfiguration) {
        this.googleSearchConfiguration = googleSearchConfiguration;
    }

    public Answer getSnippet(String question) throws SearchException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(googleSearchConfiguration.getGoogleUrl() + question))
                .header("X-RapidAPI-Key", googleSearchConfiguration.getGoogleKey())
                .header("X-RapidAPI-Host", googleSearchConfiguration.getGoogleHost())
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();

        String responseBody = "";
        String snippet = "";
        try {
            HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
            responseBody = response.body();
        } catch (InterruptedException | IOException e) {
            log.info(e.getMessage());
        }
        JsonElement jsonElement = JsonParser.parseString(responseBody);
        if (jsonElement.isJsonObject()) {
            JsonObject jsonObject = jsonElement.getAsJsonObject();

            if (jsonObject.has("items")) {
                JsonArray jsonArray = jsonObject.getAsJsonArray("items");

                if (jsonArray.size() > 0) {
                    JsonObject firstItem = jsonArray.get(0).getAsJsonObject();

                    if (firstItem.has("snippet")) {
                        snippet = firstItem.get("snippet").getAsString();
                    }
                } else {
                    throw new SearchException();
                }
            }
        }

        return Answer.builder()
                .question(question)
                .snippet(snippet)
                .build();
    }
}