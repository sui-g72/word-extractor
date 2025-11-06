package com.example.vocabapp.dto;

import java.util.List;

public class DictionaryResponse {
    private String word;
    private List<Phonetic> phonetics;
    private List<Meaning> meanings;
    private String error;

    // getter/setter
    public String getWord() { return word; }
    public void setWord(String word) { this.word = word; }

    public List<Phonetic> getPhonetics() { return phonetics; }
    public void setPhonetics(List<Phonetic> phonetics) { this.phonetics = phonetics; }

    public List<Meaning> getMeanings() { return meanings; }
    public void setMeanings(List<Meaning> meanings) { this.meanings = meanings; }

    public String getError() { return error; }
    public void setError(String error) { this.error = error; }

    public static class Phonetic {
        private String text;
        private String audio;
        public String getText() { return text; }
        public void setText(String text) { this.text = text; }
        public String getAudio() { return audio; }
        public void setAudio(String audio) { this.audio = audio; }
    }

    public static class Meaning {
        private String partOfSpeech;
        private List<Definition> definitions;

        public String getPartOfSpeech() { return partOfSpeech; }
        public void setPartOfSpeech(String partOfSpeech) { this.partOfSpeech = partOfSpeech; }
        public List<Definition> getDefinitions() { return definitions; }
        public void setDefinitions(List<Definition> definitions) { this.definitions = definitions; }
    }

    public static class Definition {
        private String definition;
        private String example;
        private List<String> synonyms;

        public String getDefinition() { return definition; }
        public void setDefinition(String definition) { this.definition = definition; }
        public String getExample() { return example; }
        public void setExample(String example) { this.example = example; }
        public List<String> getSynonyms() { return synonyms; }
        public void setSynonyms(List<String> synonyms) { this.synonyms = synonyms; }
		public void setEncodedDefinition(String encoded) {
			// TODO 自動生成されたメソッド・スタブ
			
		}
    }
}