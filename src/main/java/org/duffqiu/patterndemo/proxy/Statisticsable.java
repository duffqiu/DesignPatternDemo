/**
 * 
 */
package org.duffqiu.patterndemo.proxy;

/**
 * @author macbook
 * 
 *         2014年2月25日
 */
public interface Statisticsable {

    /**
     * 
     * @return increase time macbook 2014年2月26日
     */
    long increase();

    long totalCounter();

    double totalAvgTPS();

    long currentTPS();

}
