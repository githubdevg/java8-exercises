package mx.devg.model;

public class Article {
    private String title;
    private String description;
    private String author;
    private String text;
    private long wordCount;

    public Article(String title, String description, String author, String text, long wordCount) {
        this.title = title;
        this.description = description;
        this.author = author;
        this.text = text;
        this.wordCount = wordCount;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public long getWordCount() {
        return wordCount;
    }

    public void setWordCount(long wordCount) {
        this.wordCount = wordCount;
    }

}
