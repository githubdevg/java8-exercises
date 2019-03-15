package mx.devg.collectors;

import mx.devg.model.BlogPost;
import mx.devg.model.Tuple;
import mx.devg.model.enums.BlogPostType;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.*;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;


public class GroupingByCollectorTest {

    private List<BlogPost> posts = null;


    @Before
    public void setUp() {
        posts = Arrays.asList(new BlogPost("Title 1", "Author 1", BlogPostType.GUIDE, 10),
                new BlogPost("Title 2", "Author 2", BlogPostType.NEWS, 20),
                new BlogPost("Title 3", "Author 1", BlogPostType.GUIDE, 30),
                new BlogPost("Title 4", "Author 3", BlogPostType.REVIEW, 30),
                new BlogPost("Title 5", "Author 1", BlogPostType.NEWS, 40));
    }


    @Test
    public void testSimpleGroupingBySingleColumn() {
        Map<BlogPostType, List<BlogPost>> postsPerType = posts.stream()
                .collect(groupingBy(BlogPost::getType));
//        postsPerType.forEach((blogPostType, blogPosts) -> {
//            System.out.println("Blog Type: " + blogPostType + " Number of Blogs: " + blogPosts.size());
//        });
        printResults(postsPerType);

        Assert.assertEquals(3, postsPerType.entrySet().size());
        Assert.assertEquals(2, postsPerType.get(BlogPostType.GUIDE).size());

    }


    @Test
    public void testGroupingByComplexMapKey() {
        Map<Tuple, List<BlogPost>> postsPerTypeAndAuthor = posts.stream()
                .collect(groupingBy(post -> new Tuple(post.getType(), post.getAuthor())));

        printResults(postsPerTypeAndAuthor);

        Assert.assertEquals(4, postsPerTypeAndAuthor.entrySet().size());
    }

    @Test
    public void testGroupingByModifyingValueMapType() {
        Map<BlogPostType, Set<BlogPost>> postsPerType = posts.stream()
                .collect(groupingBy(BlogPost::getType, Collectors.toSet()));
        printResults(postsPerType);
        Assert.assertEquals(3, postsPerType.entrySet().size());
        Assert.assertEquals(2, postsPerType.get(BlogPostType.GUIDE).size());
    }

    @Test
    public void testSecondaryGroupByCollector() {
        Map<String, Map<BlogPostType, List<BlogPost>>> postsByAuthorAndType = posts.stream()
                .collect(groupingBy(BlogPost::getAuthor, groupingBy(BlogPost::getType)));

        Assert.assertEquals(3, postsByAuthorAndType.entrySet().size());
        Assert.assertEquals(2, postsByAuthorAndType.get("Author 1").entrySet().size());
        Assert.assertEquals(1, postsByAuthorAndType.get("Author 2").entrySet().size());
        Assert.assertEquals(1, postsByAuthorAndType.get("Author 3").entrySet().size());

    }

    @Test
    public void testGettingAverageFromGroupedResults() {
        Map<BlogPostType, Double> averagePerType = posts.stream()
                .collect(groupingBy(BlogPost::getType, Collectors.averagingInt(BlogPost::getLikes)));
        averagePerType
                .forEach((key, value) -> System.out.println("For: "  + key + ", average is: " + value));
    }

    @Test
    public void testGettingSumFromGroupedResults() {
        Map<BlogPostType, Integer> averagePerType = posts.stream()
                .collect(groupingBy(BlogPost::getType, Collectors.summingInt(BlogPost::getLikes)));
        averagePerType
                .forEach((key, value) -> System.out.println("For: "  + key + ", average is: " + value));
    }

    @Test
    public void testGettingMaximumFromGroupedResults() {
        Map<BlogPostType, Optional<BlogPost>> maxLikesPerPostType = posts.stream()
                .collect(groupingBy(BlogPost::getType,
                        Collectors.maxBy(Comparator.comparingInt(BlogPost::getLikes))));
        maxLikesPerPostType
                .forEach((key, value) -> System.out.println("For: "  + key
                        + ", maxBy is: " + value.get().getLikes()));
    }

    @Test
    public void testGettingSummaryForAttributeOfGroupedResults() {
        Map<BlogPostType, IntSummaryStatistics> likeStatisticsPerType = posts.stream()
                .collect(groupingBy(BlogPost::getType,
                        Collectors.summarizingInt(BlogPost::getLikes)));

        likeStatisticsPerType
                .forEach((key, value) -> System.out.println("For: "  + key
                        + ", average is: " + value.getAverage()));
    }

    @Test
    public void testMappingGroupedResultsToDifferentType() {
        Map<BlogPostType, String> postsPerType = posts.stream()
                .collect(groupingBy(
                        BlogPost::getType, Collectors
                                .mapping(BlogPost::getTitle,
                                        Collectors.joining(", ", "Post titles [", "]"))));

        postsPerType
                .forEach((key, value) -> System.out.println("For: " + key
                        + ", titles are is: " + value));
    }

    @Test
    public void testModifyingReturnTypeMap() {
        EnumMap<BlogPostType, List<BlogPost>> postPerType = posts.stream()
                .collect(groupingBy(BlogPost::getType,
                        () -> new EnumMap<>(BlogPostType.class),
                        toList()));

        postPerType.forEach((k, v) -> System.out.println("Type is: " + k + " No. of posts: " + v.size()));


    }

    @Test
    public void testConcurrentGroupingByCollector() {
        ConcurrentMap<BlogPostType, List<BlogPost>> postPerType = posts
                .parallelStream().collect(groupingByConcurrent(BlogPost::getType));


        postPerType.forEach((k, v) -> System.out.println("Type is: " + k + " No. of posts: " + v.size()));

    }

    private void printResults(Map<?, ? extends Collection<BlogPost>> map) {
        map.forEach((blogPostType, blogPosts) -> {
            System.out.println("Blog Type: " + blogPostType + " Number of Blogs: " + blogPosts.size());
        });
    }

}
