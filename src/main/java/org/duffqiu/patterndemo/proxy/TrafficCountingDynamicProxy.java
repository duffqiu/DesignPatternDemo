/**
 * 
 */
package org.duffqiu.patterndemo.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import org.duffqiu.patterndemo.common.counter.IStatisticsable;
import org.duffqiu.patterndemo.common.counter.TrafficCounter;

/**
 * @author macbook
 * 
 *         2014年3月1日
 */
public class TrafficCountingDynamicProxy<T> implements InvocationHandler {

    private T delegate;
    private IStatisticsable counter;

    /*
     * (non-Javadoc)
     * @see java.lang.reflect.InvocationHandler#invoke(java.lang.Object,
     * java.lang.reflect.Method, java.lang.Object[])
     */
    @Override
    public final Object invoke(Object proxy, Method method, Object[] args)
	    throws Throwable {

	counter.increase();
	Object o = method.invoke(delegate, args);
	System.out.println("Current TPS: " + counter.currentTPS());
	return o;
    }

    public TrafficCountingDynamicProxy(T t) {
	this.delegate = t;
	this.counter = new TrafficCounter();
    }

    @SuppressWarnings("unchecked")
    public static <T> T createProxyInstance(T t) {

	return (T) Proxy.newProxyInstance(t.getClass().getClassLoader(), t
	        .getClass().getInterfaces(),
	        new TrafficCountingDynamicProxy<T>(t));
    }
}
