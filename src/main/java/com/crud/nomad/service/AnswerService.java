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
import java.net.URI;
import java.net.http.HttpRequest;

@Service
@Slf4j
public class AnswerService implements HTTPResponse{
    private final GoogleSearchConfiguration googleSearchConfiguration;

    @Autowired
    public AnswerService(GoogleSearchConfiguration googleSearchConfiguration) {
        this.googleSearchConfiguration = googleSearchConfiguration;
    }

    public Answer generateSnippet(String question) throws SearchException {
        String snippet = "";
        String link = "";

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(googleSearchConfiguration.getGoogleUrl() + question + "&lr=lang_en"))
                .header("X-RapidAPI-Key", googleSearchConfiguration.getGoogleKey())
                .header("X-RapidAPI-Host", googleSearchConfiguration.getGoogleHost())
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();

        String responseBody = getResponseBody(request);

        JsonElement jsonElem = JsonParser.parseString(responseBody);
        if (jsonElem.isJsonObject()) {
            JsonObject jsonObject = jsonElem.getAsJsonObject();

            if (jsonObject.has("items")) {
                JsonArray jsonArray = jsonObject.getAsJsonArray("items");

                if (jsonArray.size() > 0) {
                    JsonObject firstItem = jsonArray.get(0).getAsJsonObject();

                    if (firstItem.has("snippet")) {
                        snippet = firstItem.get("snippet").getAsString();
                    }
                    if (firstItem.has("link")) {
                        link = firstItem.get("link").getAsString();
                    }
                } else {
                    throw new SearchException();
                }
            }
        }
        return Answer.builder()
                .question(question)
                .snippet(snippet)
                .link(link)
                .build();
    }
}