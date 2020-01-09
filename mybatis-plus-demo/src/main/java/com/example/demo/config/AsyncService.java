package com.example.demo.config;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

@Component
public class AsyncService {

    @Async
    public void testAsync() {
        AtomicInteger size = new AtomicInteger(100000);
        try {
            while(size.decrementAndGet() > 0) {
                Thread.sleep(300);
                System.out.println(size);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
