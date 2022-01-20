package jun.chen.api.composite.product;

import lombok.Getter;

@Getter
public class ServiceAddresses {
    private final String composite;
    private final String product;
    private final String recommendation;
    private final String review;

    public ServiceAddresses() {
        composite = null;
        product = null;
        recommendation = null;
        review = null;
    }

    public ServiceAddresses(String compositeAddress, String productAddress,
                            String recommendationAddress, String reviewAddress) {
        this.composite = compositeAddress;
        this.product = productAddress;
        this.recommendation = recommendationAddress;
        this.review = reviewAddress;
    }
}
