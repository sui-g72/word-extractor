import java.util.List;

public class Definition {
    private String definition;
    private String example;
    private List<String> synonyms;

    // ここがないと Thymeleaf は読めない
    private String encodedDefinition;

    public String getEncodedDefinition1() { return encodedDefinition; }
    public void setEncodedDefinition1(String encodedDefinition) { this.encodedDefinition = encodedDefinition; }

    public String getDefinition() { return definition; }
    public void setDefinition(String definition) { this.definition = definition; }

    public String getExample() { return example; }
    public void setExample(String example) { this.example = example; }

    public List<String> getSynonyms() { return synonyms; }
    public void setSynonyms(List<String> synonyms) { this.synonyms = synonyms; }

    public String getEncodedDefinition() { return encodedDefinition; }
    public void setEncodedDefinition(String encodedDefinition) { this.encodedDefinition = encodedDefinition; }
}