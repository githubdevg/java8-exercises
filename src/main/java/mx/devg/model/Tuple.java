package mx.devg.model;

import mx.devg.model.enums.BlogPostType;

import java.util.Objects;

public class Tuple {
    private BlogPostType type;
    private String author;

    public Tuple(BlogPostType type, String author) {
        this.type = type;
        this.author = author;
    }

    public BlogPostType getType() {
        return type;
    }

    public void setType(BlogPostType type) {
        this.type = type;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Tuple)) return false;
        Tuple tuple = (Tuple) o;
        return type == tuple.type &&
                Objects.equals(author, tuple.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, author);
    }
}
