package jun.chen.microservices.composite.product.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import jun.chen.api.core.product.Product;
import jun.chen.api.core.product.ProductService;
import jun.chen.api.core.recommendation.Recommendation;
import jun.chen.api.core.recommendation.RecommendationService;
import jun.chen.api.core.review.Review;
import jun.chen.api.core.review.ReviewService;
import jun.chen.util.exceptions.InvalidInputException;
import jun.chen.util.exceptions.NotFoundException;
import jun.chen.util.http.HttpErrorInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class ProductCompositeIntegration implements ProductService, RecommendationService, ReviewService {

    private static final Logger LOG = LoggerFactory.getLogger(ProductCompositeIntegration.class);

    private final RestTemplate restTemplate;
    private final ObjectMapper mapper;

    private final String productServiceUrl;
    private final String recommendationServiceUrl;
    private final String reviewServiceUrl;

    public ProductCompositeIntegration(
            RestTemplate restTemplate,
            ObjectMapper mapper,

            @Value("${app.product-service.host") String productServiceHost,
            @Value("${app.product-service.port") int productServicePort,

            @Value("${app.recommendation-service.host") String recommendationServiceHost,
            @Value("${app.recommendation-service.port") int recommendationServicePort,

            @Value("${app.review-service.host") String reviewServiceHost,
            @Value("${app.review-service.port") int reviewServicePort
    ) {
        this.restTemplate = restTemplate;
        this.mapper = mapper;

        productServiceUrl = "http://" + productServiceHost + ":" + productServicePort + "/product/";
        recommendationServiceUrl = "http://" + recommendationServiceHost + ":" + recommendationServicePort + "/recommendation/";
        reviewServiceUrl = "http://" + reviewServiceHost + ":" + reviewServicePort + "/review/";
    }

    @Override
    public Product getProduct(int productId) {

        try {
            String url = productServiceUrl + productId;
            LOG.debug("will call getProduct API on URL: {}", url);

            Product product = restTemplate.getForObject(url, Product.class);
            LOG.debug("Found a product with id: {}", product.getProductId());
            return product;
        } catch (HttpClientErrorException ex) {

            switch (ex.getStatusCode()) {

                case NOT_FOUND:
                    throw new NotFoundException(getErrorMessage(ex));

                case UNPROCESSABLE_ENTITY:
                    throw new InvalidInputException(getErrorMessage(ex));

                default:
                    LOG.warn("Got a unexpected HTTP error: {}, will rethrow it", ex.getStatusCode());
                    LOG.warn("Error body: {}", ex.getResponseBodyAsString());
                    throw ex;
            }
        }
    }

    private String getErrorMessage(HttpClientErrorException ex){
        try {
            return mapper.readValue(ex.getResponseBodyAsString(), HttpErrorInfo.class).getMessage();
        } catch(IOException ioex) {
            ex.getMessage();
        }
    }

    @Override
    public List<Recommendation> getRecommendation(int productId) {
        try {
            String url = recommendationServiceUrl + productId;

            LOG.debug("will call getRecommendation API on URL: {}", url);
            List<Recommendation> recommendations = restTemplate.exchange(url, HttpMethod.GET,
                    null, new ParameterizedTypeReference<List<Recommendation>>() {}).getBody();
            return recommendations;
        } catch (Exception ex) {
            LOG.warn("Got an exception while requesting recommendations, return zero recommendations: {}", ex.getMessage());
            return new ArrayList<>();
        }
    }

    @Override
    public List<Review> getReview(int productId) {
        try {
            String url = reviewServiceUrl + productId;

            LOG.debug("Will call getReviews API on URL: {}", url);
            List<Review> reviews = restTemplate.exchange(url, HttpMethod.GET,
                    null, new ParameterizedTypeReference<List<Review>>() {}).getBody();
            return reviews;

        } catch (Exception ex) {
            LOG.warn("Got an exception while requesting reviews, return zero reviews: {}", ex.getMessage());
            return new ArrayList<>();
        }
    }
}
