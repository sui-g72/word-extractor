package com.example.vocabapp.service;

import java.util.Set;
import org.jsoup.nodes.Document;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.springframework.stereotype.Service;

@Service
public class WebPageParserService {
    public Set<String> extractWords(String url) {
        Set<String> words = new TreeSet<>();
        try {
            Document doc = Jsoup.connect(url).get();
            String text = doc.body().text();
            words.addAll(extractEnglishWords(text));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return words;
    }

    private Set<String> extractEnglishWords(String text) {
        Set<String> result = new TreeSet<>();
        Pattern p = Pattern.compile("\\b[a-zA-Z]{2,}\\b");
        Matcher m = p.matcher(text);
        while (m.find()) {
            result.add(m.group().toLowerCase());
        }
        return result;
    }
}