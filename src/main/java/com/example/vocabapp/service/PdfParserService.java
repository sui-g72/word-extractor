package com.example.vocabapp.service;

import java.io.InputStream;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.stereotype.Service;

@Service
public class PdfParserService {
    public Set<String> extractWords(InputStream inputStream) {
        Set<String> words = new TreeSet<>();
        try (PDDocument document = PDDocument.load(inputStream)) {
            PDFTextStripper stripper = new PDFTextStripper();
            String text = stripper.getText(document);
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