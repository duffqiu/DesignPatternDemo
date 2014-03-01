/**
 * 
 */
package org.duffqiu.patterndemo.proxy;

import com.google.common.base.Preconditions;

/**
 * @author macbook
 * 
 *         2014年2月25日
 */
public class TrafficCounter implements Statisticsable {

    private static final int INTERNAL_TIME = 200; //ms
    private static final int SEGMENT_NUBMER = 10; //save two second data
    private static final int ONE_SECOND = 1000;
    private static final int SEGMENT_NUBMER_SECOND = ONE_SECOND / INTERNAL_TIME;

    private long currentCounter = 0;
    private long[] counterList = new long[SEGMENT_NUBMER]; //total is two second
    private long[] counterTimeList = new long[SEGMENT_NUBMER]; //total is two second
    private long lastUpdateTime = System.currentTimeMillis();
    private int lastCounterIndex = 0;
    private long totalCounter = 0;
    private long initailTime = System.currentTimeMillis();

    /*
     * (non-Javadoc)
     * @see org.duffqiu.patterndemo.proxy.Statisticsable#increase()
     */
    @Override
    public final synchronized long increase() {

	totalCounter++;

	long currentTime = System.currentTimeMillis();

	if ((currentTime - lastUpdateTime) <= ONE_SECOND) {
	    int currentCounterIndex = (int) ((currentTime - lastUpdateTime + (lastUpdateTime % INTERNAL_TIME))
		    / INTERNAL_TIME + lastCounterIndex);
	    if (currentCounterIndex == lastCounterIndex) {
		currentCounter++;

	    } else {

		currentCounter = 1;
		lastCounterIndex = currentCounterIndex % SEGMENT_NUBMER;
	    }
	} else {
	    clearCountList();
	    currentCounter = 1;

	}

	lastUpdateTime = currentTime;

	//write the data into list
	counterList[lastCounterIndex] = currentCounter;
	counterTimeList[lastCounterIndex] = lastUpdateTime;

	return lastUpdateTime;
    }

    /*
     * (non-Javadoc)
     * @see org.duffqiu.patterndemo.proxy.Statisticsable#totalCounter()
     */
    @Override
    public final long totalCounter() {

	return this.totalCounter;
    }

    /*
     * (non-Javadoc)
     * @see org.duffqiu.patterndemo.proxy.Statisticsable#totalAvgTPS()
     */
    @Override
    public final double totalAvgTPS() {
	long currentTime = System.currentTimeMillis();

	return ((double) totalCounter / (((double) currentTime - initailTime) / ONE_SECOND));
    }

    /*
     * (non-Javadoc)
     * @see org.duffqiu.patterndemo.proxy.Statisticsable#oneMinuteAvgTPS()
     */
    @Override
    public final synchronized long currentTPS() {

	long oneSecondTPS = 0;
	this.clearDutyData();

	for (int i = 0; i < SEGMENT_NUBMER; i++) {
	    oneSecondTPS += counterList[i];
	}

	return oneSecondTPS;
    }

    /**
     * 
     * 
     * macbook 2014年2月25日
     */
    private void clearCountList() {
	//	System.out.println("clear list");
	for (int i = 0; i < counterList.length; i++) {
	    counterList[i] = 0;
	}

	for (int i = 0; i < counterTimeList.length; i++) {
	    counterTimeList[i] = System.currentTimeMillis();
	}

	currentCounter = 0;
	lastCounterIndex = 0;
	lastUpdateTime = System.currentTimeMillis();
    }

    private void clearDutyData() {

	long currentTime = System.currentTimeMillis();
	for (int i = 0; i < SEGMENT_NUBMER; i++) {
	    if (currentTime - this.counterTimeList[i] >= ONE_SECOND) {
		counterList[i] = 0;
	    }
	}
    }

    /**
     * Only for test, tps needs to be tps % 5 == 0
     * 
     * @param startTime
     * @param tps
     *            macbook 2014年3月1日
     */
    public void initAvgTPS(long endTime, long tps) {

	Preconditions.checkArgument((tps % (ONE_SECOND / INTERNAL_TIME) == 0));

	long blockTps = tps / (ONE_SECOND / INTERNAL_TIME);

	this.lastCounterIndex = 3;
	this.initailTime = endTime - INTERNAL_TIME * SEGMENT_NUBMER;
	this.lastUpdateTime = endTime;
	this.currentCounter = blockTps;
	this.totalCounter = blockTps * SEGMENT_NUBMER;

	int timeIncrease = 1;
	for (int i = lastCounterIndex + 1; i < counterList.length
	        + lastCounterIndex + 1; i++) {
	    counterList[(i % SEGMENT_NUBMER)] = blockTps;

	    counterTimeList[(i % SEGMENT_NUBMER)] = initailTime
		    + (timeIncrease++) * INTERNAL_TIME;
	}

    }
}
