package jun.chen.api.composite.product;

import lombok.Getter;

@Getter
public class ReviewSummary {

    private final int reviewId;
    private final String author;
    private final String subject;

    public ReviewSummary(int reviewId, String author, String subject) {
        this.reviewId = reviewId;
        this.author = author;
        this.subject = subject;
    }
}