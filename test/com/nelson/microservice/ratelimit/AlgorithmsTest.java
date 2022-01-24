package com.nelson.microservice.ratelimit;

import org.junit.Test;

import java.util.Date;

public class AlgorithmsTest{
    @Test
    public void testFixedTimeFrame() throws InterruptedException {
        final JobHandler jobHandler = new JobHandler();
        final FixedTimeFrame timeFrame = new FixedTimeFrame();
        do {
            for (int i = 0; i < 10000; i++) {
                if (timeFrame.tryGet(new Date().getSeconds())) {
                    jobHandler.doJob();
                }
            }
            Thread.sleep(100);
        } while (true);
    }

    class JobHandler {
        int count = 0;
        public void doJob() throws InterruptedException {
            System.out.println("do your job" + count++);
        }
    }
}