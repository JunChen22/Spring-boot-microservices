package jun.chen.api.core.review;

import lombok.Getter;

@Getter
public class Review {
    private final int productId;
    private final int reviewId;
    private final String author;
    private final String subject;
    private final String content;
    private final String ServiceAddress;

    public Review() {
        this.productId = 0;
        this.reviewId = 0;
        this.author = null;
        this.subject = null;
        this.content = null;
        ServiceAddress = null;
    }

    public Review(int productId, int reviewId, String author, String subject, String content, String serviceAddress) {
        this.productId = productId;
        this.reviewId = reviewId;
        this.author = author;
        this.subject = subject;
        this.content = content;
        ServiceAddress = serviceAddress;
    }
}
