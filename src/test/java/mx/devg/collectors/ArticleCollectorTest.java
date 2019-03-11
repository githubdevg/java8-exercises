package mx.devg.collectors;

import mx.devg.model.Article;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collector;

public class ArticleCollectorTest {

    private List<Article> articleList;


    @Before
    public void setUp() {

        articleList = Arrays.asList(new Article("Title 1", "Description 1", "Author 1", "This is Text 1", 100),
                new Article("Title 2", "Description 2", "Author 2", "This is Text 2", 200),
                new Article("Title 3", "Description 3", "Author 3", "This is Text 3", 300));

    }

    @Test
    public void testSimpleCollector() {
        Collector<Article, long[], long[]> wordCountCollector = Collector.of(() -> new long[1],
                (totalWordCount, article) -> totalWordCount[0] += article.getWordCount(),
                (wordCount1, wordCount2) -> {
                    wordCount1[0] += wordCount2[0];
                    return wordCount1;
                });
        long[] totalWordCount = articleList.stream().collect(wordCountCollector);
        Assert.assertEquals(600, totalWordCount[0]);
    }


    @Test
    public void testFinisherCollector() {
        Collector<Article, long[], Long> wordCountCollector = Collector.of(() -> new long[1],
                (totalWordCount, article) -> totalWordCount[0] += article.getWordCount(),
                (result1, result2) -> {
                    result1[0] += result2[0];
                    return result1;
                }, totalWordCount -> totalWordCount[0]);
        long totalWordCount = articleList.stream().collect(wordCountCollector);
        Assert.assertEquals(600, totalWordCount);
    }

    @Test
    public void testFinisherOptimimsationsCollector() {
        Collector<Article, long[], Long> wordCountCollector = Collector.of(() -> new long[1],
                (totalWordCount, article) -> totalWordCount[0] += article.getWordCount(),
                (result1, result2) -> {
                    result1[0] += result2[0];
                    return result1;
                }, totalWordCount -> totalWordCount[0],
                Collector.Characteristics.CONCURRENT
        );
        long totalWordCount = articleList.stream().collect(wordCountCollector);
        Assert.assertEquals(600, totalWordCount);
    }


    @Test
    public void testFinisherOptimimsationsIdentityCollector() {
        Collector<Article, long[], long[]> wordCountCollector = Collector.of(() -> new long[1],
                (totalWordCount, article) -> totalWordCount[0] += article.getWordCount(),
                (result1, result2) -> {
                    result1[0] += result2[0];
                    return result1;
                },
                Collector.Characteristics.CONCURRENT,
                Collector.Characteristics.IDENTITY_FINISH
        );

        long[] totalWordCount = articleList.stream().collect(wordCountCollector);
        Assert.assertEquals(600, totalWordCount[0]);
    }

}
