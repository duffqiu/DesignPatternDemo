/**
 * 
 */
package org.duffqiu.patterndemo.proxy;

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

	long currentTime = System.currentTimeMillis();

	System.out.println("current time: " + currentTime
	        + " last update time: " + lastUpdateTime);

	if ((currentTime - lastUpdateTime) > ONE_SECOND) {
	    clearCountList();
	    return 0;
	}

	long oneSecondTPS = 0;

	int currentCounterIndex = (int) ((currentTime - lastUpdateTime + (lastUpdateTime % INTERNAL_TIME))
	        / INTERNAL_TIME + lastCounterIndex);

	clearDutyData(lastCounterIndex,
	        (currentCounterIndex - lastCounterIndex));

	int firstIndexLastSecond = 0;

	if (currentCounterIndex < (SEGMENT_NUBMER_SECOND - 1)) {
	    firstIndexLastSecond = currentCounterIndex + SEGMENT_NUBMER_SECOND + 1;
	} else {
	    firstIndexLastSecond = currentCounterIndex - SEGMENT_NUBMER_SECOND  + 1;
	}

	for (int i = 0; i < SEGMENT_NUBMER_SECOND; i++) {
	    int index = (firstIndexLastSecond + i) % SEGMENT_NUBMER;
	    oneSecondTPS += counterList[index];
	    System.out.println("get index: " + index + " value: "
		    + counterList[index] + ", update time: " + counterTimeList[index]);
	}
	
	//include previous data
	int previousIndex = (firstIndexLastSecond - 1 + SEGMENT_NUBMER) % SEGMENT_NUBMER;
	
	if ((currentTime - counterTimeList[previousIndex]) <= ONE_SECOND) {
		
		oneSecondTPS += counterList[previousIndex];
		
		System.out.println("append previous index: " + previousIndex + " value: "
			    + counterList[previousIndex] + ", update time: " + counterTimeList[previousIndex]);
	}
		

	System.out.println("return current tps: " + oneSecondTPS);

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

    private void clearDutyData(int startIndex, int length) {

	if (length <= 0) {
	    return;
	}

	System.out.println("clear duty data: " + startIndex + ", length: "
	        + length);
	for (int i = 0; i < length - 1; i++) {
	    int index = (startIndex + 1 + i) % SEGMENT_NUBMER;
	    counterList[index] = 0;
	}
    }

}
