package edu.yu.introtoalgs;

import static org.junit.Assert.assertEquals;

import java.util.Random;

import org.junit.Test;

public class EnqueueTest {
    
    @Test
    public void enqueue1(){
        MaxQueue m = new MaxQueue();
        Random rand = new Random();
        int max = 0;
        long time = System.currentTimeMillis();
        for(int i = 0; i < 15000; i++){
            int r = rand.nextInt(Integer.MAX_VALUE);
            System.out.println(r);
            if( r > max){
                max = r;
            }
            m.enqueue(r);
        }
        long newTime = System.currentTimeMillis();
        double executionTimeInSeconds = (newTime - time) / 1000.0;
        System.out.println("___________");
        System.out.println("execution time: " + executionTimeInSeconds);
        System.out.println(m.size());
        assertEquals(15000, m.size());
        System.out.println(m.max());
        assertEquals(max, m.max());
        System.out.println(m);
    }

    @Test
    public void enqueueDoublingTest(){

        for( int n = 1000; n <= 1310720000; n *= 2 ){
            MaxQueue m = new MaxQueue();
            Random rand = new Random();
            int max = 0;
            long time = System.currentTimeMillis();
            for(int i = 0; i < n; i++){
                int r = rand.nextInt(n);
                if( r > max){
                    max = r;
                }
                m.enqueue(r);
            }
            long newTime = System.currentTimeMillis();
            double executionTimeInSeconds = (newTime - time) / 1000.0;
            System.out.println("___________");
            System.out.println("execution time: " + executionTimeInSeconds);
            System.out.println(m.size());
            assertEquals(n, m.size());
            System.out.println(m.max());
            assertEquals(max, m.max());
        }
    }
}
