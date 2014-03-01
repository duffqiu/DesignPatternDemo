/**
 * 
 */
package org.duffqiu.patterndemo.common.counter;

/**
 * @author macbook
 * 
 *         2014年2月25日
 */
public interface IStatisticsable {

    /**
     * 
     * @return increase time macbook 2014年2月26日
     */
    long increase();

    long totalCounter();

    double totalAvgTPS();

    long currentTPS();

}
