package com.nelson.microservice.ratelimit;

import java.util.Date;

public class LeakBucket<T> implements LimitAlgorithms{
    public int capacity = 100;
    public int leakRate = 10; // 100 per s
    long timestamp = new Date().getSeconds();

    public boolean tryGet(long currentTimeStamp) {
        if (capacity + (currentTimeStamp - timestamp) * leakRate > 0) {
            capacity--;
            return true;
        }
        System.out.println("drop task");
        return false;
    }
}