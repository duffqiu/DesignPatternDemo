/**
 * 
 */
package org.duffqiu.patterndemotest.proxy;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Random;

import org.duffqiu.patterndemo.proxy.Statisticsable;
import org.duffqiu.patterndemo.proxy.TrafficCounter;
import org.junit.Test;

/**
 * @author macbook
 * 
 *         2014年2月26日
 */
public class TrafficCounterTest {

    @Test
    public final void testValueOrNot() throws InterruptedException {
	Statisticsable counter = new TrafficCounter();

	long increaseNumber = new Random().nextInt(100);

	for (int i = 0; i < increaseNumber; i++) {
	    counter.increase();
	}

	long total = increaseNumber;

	assertThat(counter.currentTPS()).isEqualTo(increaseNumber);

	Thread.sleep(1001);

	assertThat(counter.currentTPS()).isEqualTo(0L);

	increaseNumber = new Random().nextInt(100);

	for (int i = 0; i < increaseNumber; i++) {
	    counter.increase();
	}

	total += increaseNumber;

	assertThat(counter.currentTPS()).isEqualTo(increaseNumber);
	assertThat(counter.totalCounter()).isEqualTo(total);

    }

    @Test
    public final void testInOneSecond() throws InterruptedException {

	Statisticsable counter = new TrafficCounter();

	long increaseNumber = new Random().nextInt(100);

	for (int i = 0; i < increaseNumber; i++) {
	    counter.increase();
	}

	assertThat(counter.currentTPS()).isEqualTo(increaseNumber);

	Thread.sleep(200);

	assertThat(counter.currentTPS()).isEqualTo(counter.totalCounter());

	increaseNumber = new Random().nextInt(100);

	for (int i = 0; i < increaseNumber; i++) {
	    counter.increase();
	}

	Thread.sleep(200);

	assertThat(counter.currentTPS()).isEqualTo(counter.totalCounter());

	increaseNumber = new Random().nextInt(100);

	for (int i = 0; i < increaseNumber; i++) {
	    counter.increase();
	}

	assertThat(counter.currentTPS()).isEqualTo(counter.totalCounter());

    }

    @Test
    public final void testBeyoneOneSecond() throws InterruptedException {
	Statisticsable counter = new TrafficCounter();

	long increaseNumber1 = new Random().nextInt(100);

	for (int i = 0; i < increaseNumber1; i++) {
	    counter.increase();
	}

	//	System.out.println("[test]time: " + System.currentTimeMillis()
	//	        + " increase: " + increaseNumber1 + " tps: "
	//	        + counter.currentTPS());

	Thread.sleep(200);

	long increaseNumber2 = new Random().nextInt(100);

	for (int i = 0; i < increaseNumber2; i++) {
	    counter.increase();
	}

	//	System.out.println("[test]time: " + System.currentTimeMillis()
	//	        + " increase: " + increaseNumber2 + " tps: "
	//	        + counter.currentTPS());

	Thread.sleep(500);

	long increaseNumber3 = new Random().nextInt(100);

	for (int i = 0; i < increaseNumber3; i++) {
	    counter.increase();
	}

	//	System.out.println("[test]time: " + System.currentTimeMillis()
	//	        + " increase: " + increaseNumber3 + " tps: "
	//	        + counter.currentTPS());

	Thread.sleep(220);

	long increaseNumber4 = new Random().nextInt(100);

	for (int i = 0; i < increaseNumber4; i++) {
	    counter.increase();
	}

	//	System.out.println("[test]time: " + System.currentTimeMillis()
	//	        + " increase: " + increaseNumber4 + " tps: "
	//	        + counter.currentTPS());

	long current = counter.currentTPS();

	//	System.out
	//	        .println("[test]time: "
	//	                + System.currentTimeMillis()
	//	                + " tps: "
	//	                + current
	//	                + " increaseNumber1 + increaseNumber2+ increaseNumber3 + increaseNumber4 "
	//	                + (increaseNumber1 + increaseNumber2 + increaseNumber3 + increaseNumber4));

	assertThat(current)
	        .isEqualTo(
	                (increaseNumber1 + increaseNumber2 + increaseNumber3 + increaseNumber4));

	Thread.sleep(150);

	current = counter.currentTPS();

	//	System.out.println("[test]time: " + System.currentTimeMillis() + " tps: "
	//	        + current
	//	        + " increaseNumber2+ increaseNumber3 + increaseNumber4 "
	//	        + (increaseNumber2 + increaseNumber3 + increaseNumber4));

	assertThat(current).isEqualTo(
	        (increaseNumber2 + increaseNumber3 + increaseNumber4));

	Thread.sleep(150);

	current = counter.currentTPS();

	//	System.out.println("[test]time: " + System.currentTimeMillis() + " tps: "
	//	        + current + "increaseNumber3 + increaseNumber4 "
	//	        + (increaseNumber3 + increaseNumber4));

	assertThat(current).isEqualTo((increaseNumber3 + increaseNumber4));

    }

    @Test
    public final void testAvgTPS() throws InterruptedException {
	long startTime = System.currentTimeMillis();
	TrafficCounter counter = new TrafficCounter();

	long increaseNumber = new Random().nextInt(100);

	for (int i = 0; i < increaseNumber; i++) {
	    counter.increase();
	    Thread.sleep(20);
	}

	Thread.sleep(increaseNumber);

	long increaseNumber2 = new Random().nextInt(100);

	for (int i = 0; i < increaseNumber2; i++) {
	    counter.increase();
	    Thread.sleep(20);
	}

	Thread.sleep(1000 + increaseNumber);

	long increaseNumber3 = new Random().nextInt(100);

	for (int i = 0; i < increaseNumber3; i++) {
	    counter.increase();
	    Thread.sleep(20);
	}

	long endTime = System.currentTimeMillis();

	double tpsTest = (double) (increaseNumber + increaseNumber2 + increaseNumber3)
	        / ((double) (endTime - startTime) / 1000);
	double tpsInCounter = counter.totalAvgTPS();

	//	System.out.println("total time: " + (double) (endTime - startTime)
	//	        / 1000 + ", increase: " + increaseNumber);
	//	System.out.println("test value: " + tpsTest + " counter value: "
	//	        + tpsInCounter);
	assertThat(tpsTest).isBetween(tpsInCounter * (1 - 0.001),
	        tpsInCounter * (1 + 0.001));

    }

    @Test(expected = IllegalArgumentException.class)
    public final void testInitAvgTPSException() {
	TrafficCounter counter = new TrafficCounter();
	counter.initAvgTPS(System.currentTimeMillis(), 1901);
    }

    @Test()
    public final void testInitAvgTPS() throws InterruptedException {
	TrafficCounter counter = new TrafficCounter();
	long initTPS = 2000;
	counter.initAvgTPS(System.currentTimeMillis(), initTPS);
	long tps = counter.currentTPS();
	assertThat(initTPS).isEqualTo(tps);

	Thread.sleep(200);

	long increaseNumber = new Random().nextInt(100);

	for (int i = 0; i < increaseNumber; i++) {
	    counter.increase();
	}

	tps = counter.currentTPS();

	assertThat(tps).isEqualTo(400 * 4 + increaseNumber);

	Thread.sleep(200);

	tps = counter.currentTPS();

	assertThat(tps).isEqualTo(300 * 4 + increaseNumber);
    }
}
