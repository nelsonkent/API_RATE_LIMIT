package com.nelson.microservice.ratelimit;

public interface LimitAlgorithms {
    public boolean tryGet(long timestamp);
}