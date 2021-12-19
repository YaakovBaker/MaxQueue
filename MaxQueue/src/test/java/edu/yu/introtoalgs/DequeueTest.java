package edu.yu.introtoalgs;

import static org.junit.Assert.assertEquals;

import java.util.Random;

import org.junit.Test;

public class DequeueTest {

    @Test
    public void dequeue1(){
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

        long timeDq = System.currentTimeMillis();
        for(int i = 0; i < 15000; i++){
            m.dequeue();
        }
        long newTimeDq = System.currentTimeMillis();
        double executionTimeInSecondsDq = (newTimeDq - timeDq) / 1000.0;
        System.out.println("___________");
        System.out.println("execution time: " + executionTimeInSecondsDq);
        System.out.println(m.size());
        assertEquals(0, m.size());
    }

    @Test
    public void dequeueAPrevMax(){
        int q[] = {10,9,8,7,6,5,4,3,2,10};
        MaxQueue m = new MaxQueue();
        for(int i = 0; i < q.length; i++){
            m.enqueue(q[i]);
        }
        System.out.println("___________");
        System.out.println(m.max());
        assertEquals(10, m.max());
        m.dequeue();
        System.out.println("max after dq: " + m.max());
        assertEquals(10, m.max());
    }
}
