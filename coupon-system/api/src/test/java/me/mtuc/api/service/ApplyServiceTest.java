package me.mtuc.api.service;

import me.mtuc.api.repository.CouponRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AutoClose;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.nio.channels.Pipe;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ApplyServiceTest {

    @Autowired
    private ApplyService applyService;
    @Autowired
    private CouponRepository couponRepository;

    @Test
    public void 한번응모() {
        applyService.apply(1L);

        long count = couponRepository.count();
        org.assertj.core.api.Assertions.assertThat(count).isEqualTo(1);
    }

    @Test
    public void 여러명응모() throws InterruptedException {
        int threadCount = 1000;
        ExecutorService executorService = Executors.newFixedThreadPool(32);
        CountDownLatch latch = new CountDownLatch(threadCount);

        for (int i = 0; i <threadCount; i++) {
            long userId = i;
            executorService.submit(() -> {
                try {
                    applyService.apply(userId);
                } finally {
                    latch.countDown();
                }
            });
        }
        latch.await();
        Thread.sleep(10000);
        long count = couponRepository.count();
        Assertions.assertThat(count).isEqualTo(100);
    }

    @Test
    public void 한명당_한개의_쿠폰() throws InterruptedException {
        int threadCount = 1000;
        ExecutorService executorService = Executors.newFixedThreadPool(32);
        CountDownLatch latch = new CountDownLatch(threadCount);

        for (int i = 0; i <threadCount; i++) {
            long userId = i;
            executorService.submit(() -> {
                try {
                    applyService.apply(1L);
                } finally {
                    latch.countDown();
                }
            });
        }
        latch.await();
        Thread.sleep(10000);
        long count = couponRepository.count();
        Assertions.assertThat(count).isEqualTo(1);
    }
}