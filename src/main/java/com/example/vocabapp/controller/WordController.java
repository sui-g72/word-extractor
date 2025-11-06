package com.example.vocabapp.controller;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.vocabapp.dto.DictionaryResponse;
import com.example.vocabapp.service.DictionaryService;
import com.example.vocabapp.service.PdfParserService;
import com.example.vocabapp.service.WebPageParserService;

@Controller
public class WordController {

    private final PdfParserService pdfService;
    private final WebPageParserService webService;
    private final DictionaryService dictionaryService;

    public WordController(PdfParserService pdfService, WebPageParserService webService, DictionaryService dictionaryService) {
        this.pdfService = pdfService;
        this.webService = webService;
        this.dictionaryService = dictionaryService;
    }

    @PostMapping("/uploadPdf")
    public String uploadPdf(@RequestParam MultipartFile file, Model model) {
        try {
            Set<String> wordsSet = pdfService.extractWords(file.getInputStream());
            List<String> words = new ArrayList<>(wordsSet); // リストに変換
            model.addAttribute("words", words);

            // グループ化
            Map<Character, List<String>> groupedWords = new TreeMap<>();
            for (String w : words) {
                char first = Character.toUpperCase(w.charAt(0));
                groupedWords.computeIfAbsent(first, k -> new ArrayList<>()).add(w);
            }
            model.addAttribute("groupedWords", groupedWords);

            return "words";
        } catch (Exception e) {
            model.addAttribute("error", "PDF解析に失敗しました: " + e.getMessage());
            return "index";
        }
    }

    @PostMapping("/fetchWeb")
    public String fetchWeb(@RequestParam String url, Model model) {
        try {
            Set<String> wordsSet = webService.extractWords(url);
            List<String> words = new ArrayList<>(wordsSet);
            model.addAttribute("words", words);

            Map<Character, List<String>> groupedWords = new TreeMap<>();
            for (String w : words) {
                char first = Character.toUpperCase(w.charAt(0));
                groupedWords.computeIfAbsent(first, k -> new ArrayList<>()).add(w);
            }
            model.addAttribute("groupedWords", groupedWords);

            return "words";
        } catch (Exception e) {
            model.addAttribute("error", "Webページ解析に失敗しました: " + e.getMessage());
            return "index";
        }
    }

    @GetMapping("/words")
    public String listWords(Model model) {
        List<String> words = List.of("Apple", "Ant", "Ball", "Cat", "Car");

        // ALL タブ用
        model.addAttribute("words", words);

        // A–Z タブ用に文字ごとにグループ化
        Map<Character, List<String>> groupedWords = new TreeMap<>();
        for (String word : words) {
            char first = Character.toUpperCase(word.charAt(0));
            groupedWords.computeIfAbsent(first, k -> new ArrayList<>()).add(word);
        }
        model.addAttribute("groupedWords", groupedWords);  // ← 必須！

        return "words";
    }
    
    @GetMapping("/define/{word}")
    public String defineWord(@PathVariable String word, Model model) {
        DictionaryResponse response = dictionaryService.lookupWord(word);

        // 各定義を URL エンコードして追加
        if (response.getMeanings() != null) {
            response.getMeanings().forEach(meaning -> {
                if (meaning.getDefinitions() != null) {
                    meaning.getDefinitions().forEach(def -> {
                        String encoded = URLEncoder.encode(def.getDefinition(), StandardCharsets.UTF_8);
                        def.setEncodedDefinition(encoded); // DictionaryDefinition に setter を追加
                    });
                }
            });
        }

        model.addAttribute("entry", response);
        return "definition";
    }
}