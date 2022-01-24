package com.nelson.microservice.ratelimit;

public class SlideTimeFrame implements LimitAlgorithms{
    int[] times = new int[10];
    long timestamp;
    @Override
    public boolean tryGet(long currentTimestamp) {
        if(currentTimestamp - 10 > timestamp){

        }else{
            times[(int) (currentTimestamp - timestamp)] = 1;
        }
        return false;
    }
}
