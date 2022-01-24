package com.nelson.microservice.outofmemory;

import org.junit.Test;

import java.util.ArrayList;
import java.util.concurrent.*;

/**
 * set jvm memory to 128M
 * –Xms128m –Xmx128m
 */
public class ConditionTest {
    @Test
    public void testThread(){
        int count = 0;
        try {
            while (true){
                new Thread(){
                    @Override
                    public void run() {
                        try {
                            Thread.currentThread().sleep(200000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        super.run();
                    }
                }.start();
                count++;
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            System.out.println(16*1024*1024/count);
        }
    }


    /**
     * test result
     * 64M 6153400
     * 32M 4102267
     * 16M 1823230
     */
    @Test
    public void testList(){
        final ArrayList<Object> objects = new ArrayList<>();
        try {
            while (true){
                objects.add("11111111111");
            }
        }catch (OutOfMemoryError e){
            System.out.println(objects.size());
            System.out.println(64*1024*1024 / objects.size());
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Callable callable = new Callable() {
            @Override
            public Object call() throws Exception {
                System.out.println(System.currentTimeMillis());
                Thread.sleep(2000);
                return "success";
            }
        };
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println(System.currentTimeMillis());
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        final ExecutorService executorService = Executors.newCachedThreadPool();
//        final Future<String> submit = executorService.submit(callable);
        final Future<?> submit = executorService.submit(runnable);
        System.out.println(submit.get());
        System.out.println(System.currentTimeMillis());
    }
}
