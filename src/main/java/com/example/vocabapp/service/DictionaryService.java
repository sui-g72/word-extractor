package com.example.vocabapp.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.vocabapp.dto.DictionaryResponse;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class DictionaryService {

    private final WebClient webClient;
    private final ObjectMapper objectMapper;

    public DictionaryService(WebClient.Builder builder, ObjectMapper objectMapper) {
        this.webClient = builder.baseUrl("https://api.dictionaryapi.dev/api/v2/entries/en").build();
        this.objectMapper = objectMapper;
    }

    public DictionaryResponse lookupWord(String word) {
        try {
            String json = webClient.get()
                    .uri("/{word}", word)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();

            // JSON配列の先頭要素を使う
            List<DictionaryResponse> list =
                    objectMapper.readValue(json, new TypeReference<List<DictionaryResponse>>() {});
            return list.get(0);

        } catch (Exception e) {
            DictionaryResponse resp = new DictionaryResponse();
            resp.setWord(word);
            resp.setError("Definition not found");
            return resp;
        }
    }
}