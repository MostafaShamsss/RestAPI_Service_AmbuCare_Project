package com.example.demo.api.rateLimiting;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class SimpleRateLimiter {
    private final int maxRequestPerMinute;
    private final Map<String, AtomicInteger> requestCountsPerIpAddress = new ConcurrentHashMap<>();
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    public SimpleRateLimiter(int maxRequestPerMinute) {
        this.maxRequestPerMinute = maxRequestPerMinute;
        scheduler.scheduleAtFixedRate(this::reset, 1, 1, TimeUnit.MINUTES);
    }

    public boolean tryAcquire(String ipAddress) {
        return requestCountsPerIpAddress.compute(ipAddress, (key, currentCount) -> {
            if (currentCount == null) {
                return new AtomicInteger(1);
            }

            if (currentCount.get() < maxRequestPerMinute) {
                currentCount.incrementAndGet();
                return currentCount;
            }

            return currentCount;
        }).get() <= maxRequestPerMinute;
    }

    public void reset() {
        requestCountsPerIpAddress.clear();
    }
}
