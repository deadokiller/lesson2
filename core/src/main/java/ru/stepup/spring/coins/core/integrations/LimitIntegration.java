package ru.stepup.spring.coins.core.integrations;

import java.math.BigDecimal;

public interface LimitIntegration {

    void changeLimit(Long userId, BigDecimal sum);
}
