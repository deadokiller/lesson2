package ru.vtb.javapro.homework.rest;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.vtb.javapro.homework.service.LimitService;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/v1")
public class LimitController {

    private final LimitService limitService;

    public LimitController(LimitService limitService) {
        this.limitService = limitService;
    }

    @PutMapping("/limit/change")
    public void changeLimit(
            @RequestHeader Long userId, @RequestParam BigDecimal sum
    ) {
        limitService.changeLimit(userId, sum);
    }
}