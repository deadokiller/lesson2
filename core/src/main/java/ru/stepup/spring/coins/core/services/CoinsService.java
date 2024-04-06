package ru.stepup.spring.coins.core.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.stepup.spring.coins.core.api.ExecuteCoinsRequest;
import ru.stepup.spring.coins.core.api.ExecuteCoinsResponse;
import ru.stepup.spring.coins.core.configurations.properties.CoreProperties;
import ru.stepup.spring.coins.core.exceptions.BadRequestException;

import java.math.BigDecimal;

@Service
public class CoinsService {
    private final CoreProperties coreProperties;
    private final ExecutorService executorService;
    private final ProductService productService;
    private final LimitService limitService;

    private static final Logger logger = LoggerFactory.getLogger(CoinsService.class.getName());

    public CoinsService(CoreProperties coreProperties, ExecutorService executorService, ProductService productService, LimitService limitService) {
        this.coreProperties = coreProperties;
        this.executorService = executorService;
        this.productService = productService;
        this.limitService = limitService;
    }

    public ExecuteCoinsResponse execute(ExecuteCoinsRequest request, Long userId) {
        if (coreProperties.getNumbersBlockingEnabled()) {
            if (coreProperties.getBlockedNumbers().contains(request.number())) {
                throw new BadRequestException("Указан заблокированный номер кошелька", "BLOCKED_ACCOUNT_NUMBER");
            }
        }
        try {
            limitService.changeLimit(userId, request.sum());
        } catch (Exception e) {
            throw new BadRequestException("Произошла ошибка при попытки уменьшения дневного лимита: %s".formatted(e.getMessage()), "LIMIT_ERROR");
        }

        var product = productService.getUserProduct(userId, request.productId());
        product
                .filter(p -> p.getBalance().compareTo(BigDecimal.ZERO) > 0)
                .orElseThrow(() -> new BadRequestException("Нет подходящего продукта для исполнения", "PRODUCT_NOT_FOUND"));
        try {
            ExecuteCoinsResponse response = executorService.execute(request);
            return response;
        }
        catch (Exception e){
            logger.error("Ошибка исполнения операции: %s".formatted(e.getMessage()));
            try {
                limitService.changeLimit(userId,request.sum().negate());
            }
            catch (Exception error){
                throw new BadRequestException("Ошибка попытки восстановления лимита: %s".formatted(error.getMessage()),"LIMIT_RETURN_ERROR");
            }
            throw e;
        }
    }
}
