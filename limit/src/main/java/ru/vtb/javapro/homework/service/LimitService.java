package ru.vtb.javapro.homework.service;

import java.math.BigDecimal;

public interface LimitService {
    void changeLimit(Long userId, BigDecimal sum);
}
