package com.limitedtimedeal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class DealDeactivationScheduler {

    private final DealService dealService;

    @Autowired
    public DealDeactivationScheduler(DealService dealService) {
        this.dealService = dealService;
    }

    @Scheduled(cron = "0 0 * * * *") // Run every hour at the beginning of the hour (00:00)
    public void deactivateExpiredDealsTask() {
        int deactivatedCount = dealService.deactivateExpiredDeals();
        System.out.println("Deactivated " + deactivatedCount + " expired deals at " + java.time.LocalDateTime.now(java.time.ZoneId.of("Asia/Kolkata")));
    }
}