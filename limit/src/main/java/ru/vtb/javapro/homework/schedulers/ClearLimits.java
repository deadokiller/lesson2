package ru.vtb.javapro.homework.schedulers;

import org.springframework.scheduling.annotation.Scheduled;
import ru.vtb.javapro.homework.repository.LimitRepository;

public class ClearLimits {
    private final LimitRepository limitRepository;

    public ClearLimits(LimitRepository limitRepository) {
        this.limitRepository = limitRepository;
    }

    @Scheduled(cron = "${limit.cron}")
    public void execute() {
        try {
            limitRepository.deleteAll();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
