package ru.stepup.spring.coins.core.integrations;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestClient;
import ru.stepup.spring.coins.core.api.ProductResponse;

import java.math.BigDecimal;

public class LimitIntegrationRestClient implements LimitIntegration {
    private final RestClient restClient;
    private static final Logger logger = LoggerFactory.getLogger(LimitIntegrationRestClient.class.getName());

    public LimitIntegrationRestClient(RestClient restClient) {
        this.restClient = restClient;
    }

    @Override
    public void changeLimit(Long userId, BigDecimal sum) {
        restClient.put()
                .uri("/change", uriBuilder -> uriBuilder
                        .queryParam("sum", sum)
                        .build())
                .header("USERID", userId.toString())
                .header("Accept", "application/json")
                .retrieve()
                .body(ProductResponse.class);
        if(sum.compareTo(BigDecimal.ZERO) > 0) {
            logger.info("Лимит уменьшен для USER_ID: %s".formatted(userId));
        }
        else {
            logger.info("Лимит восстановлен для USER_ID: %s".formatted(userId));
        }
    }
}
