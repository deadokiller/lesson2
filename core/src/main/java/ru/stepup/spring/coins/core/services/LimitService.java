package ru.stepup.spring.coins.core.services;


import org.springframework.stereotype.Service;
import ru.stepup.spring.coins.core.integrations.LimitIntegration;

import java.math.BigDecimal;

@Service
public class LimitService {
    private final LimitIntegration limitIntegration;

    public LimitService(LimitIntegration limitIntegration) {
        this.limitIntegration = limitIntegration;
    }

    public void changeLimit(Long userId, BigDecimal sum) {
        limitIntegration.changeLimit(userId, sum);
    }
}