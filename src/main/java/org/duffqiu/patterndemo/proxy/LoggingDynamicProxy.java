/**
 * 
 */
package org.duffqiu.patterndemo.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author macbook
 * 
 *         2014年3月1日
 */
public final class LoggingDynamicProxy<T> implements InvocationHandler {

    private T delegate;

    /*
     * (non-Javadoc)
     * @see java.lang.reflect.InvocationHandler#invoke(java.lang.Object,
     * java.lang.reflect.Method, java.lang.Object[])
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args)
	    throws Throwable {
	System.out.println("Call method: " + method.getName() + ", with args: "
	        + args.toString());
	Object o = method.invoke(delegate, args);
	return o;
    }

    public LoggingDynamicProxy(T t) {
	this.delegate = t;
    }

    /**
     * 
     */
    public LoggingDynamicProxy() {

    }

    @SuppressWarnings("unchecked")
    public static <T> T createProxyInstance(T t) {

	return (T) Proxy.newProxyInstance(t.getClass().getClassLoader(), t
	        .getClass().getInterfaces(), new LoggingDynamicProxy<T>(t));
    }
}
