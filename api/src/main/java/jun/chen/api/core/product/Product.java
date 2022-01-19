package jun.chen.api.core.product;

import lombok.Getter;

@Getter
public class Product {
    private final int productId;
    private final String name;
    private final int weight;
    private final String serviceAddress;

    public Product() {
        this.productId = 0;
        this.name = null;
        this.weight = 0;
        this.serviceAddress = null;
    }

    public Product(int productId, String name, int weight, String serviceAddress) {
        this.productId = productId;
        this.name = name;
        this.weight = weight;
        this.serviceAddress = serviceAddress;
    }
}
