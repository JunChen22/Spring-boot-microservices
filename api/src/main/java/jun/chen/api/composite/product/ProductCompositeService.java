package jun.chen.api.composite.product;

import org.springframework.web.bind.annotation.*;

public interface ProductCompositeService {

    @PostMapping(
            value = "/product-composite",
            consumes = "application/json")
    void createCompositeProduct(@RequestBody ProductAggregate body);

    @GetMapping(
            value = "/product-composite/{productId}",
            produces = "application/json")
    ProductAggregate getCompositeProduct(@PathVariable int productId);

    @DeleteMapping(value = "/product-composite/{productId}")
    void deleteCompositeProduct(@PathVariable int productId);
}