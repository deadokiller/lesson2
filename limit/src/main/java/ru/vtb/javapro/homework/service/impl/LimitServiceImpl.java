package ru.vtb.javapro.homework.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.vtb.javapro.homework.entity.Limit;
import ru.vtb.javapro.homework.repository.LimitRepository;
import ru.vtb.javapro.homework.service.LimitService;

import java.math.BigDecimal;

@Service
public class LimitServiceImpl implements LimitService {
    private final LimitRepository limitRepository;

    @Value("${limit.params.default-limit}")
    private BigDecimal defaultLimit;

    public LimitServiceImpl(LimitRepository limitRepository) {
        this.limitRepository = limitRepository;
    }
    @Override
    public void changeLimit(Long userId, BigDecimal sum) {
        var entity = limitRepository.findFirstByUserId(userId)
                .orElse(new Limit(defaultLimit, userId));
        var limit = entity.getLimit();

        if (limit.compareTo(sum) < 0) {
            throw new IllegalArgumentException("Недостаточно лимита, для проведения операции: %s".formatted(entity.getLimit()));
        }

        entity.setLimit(limit.add(sum.negate()));
        limitRepository.save(entity);
    }
}
