package com.nelson.microservice.ratelimit;

import java.util.Date;

public class FixedTimeFrame implements LimitAlgorithms{
    private int capacity = 100;
    private int used = 0;
    private long timestamp = new Date().getSeconds();

    public boolean tryGet(long currentTimeStamp) {
        if (currentTimeStamp != timestamp) {
            timestamp = currentTimeStamp;
            used = 1;
            return true;
        } else if (used < capacity) {
            used++;
            return true;
        }
        System.out.println("drop task");
        return false;
    }
}