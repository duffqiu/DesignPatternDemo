/**
 * 
 */
package org.duffqiu.patterndemo.proxy;

import java.lang.reflect.Method;

import com.google.common.reflect.AbstractInvocationHandler;

/**
 * @author macbook
 * 
 *         2014年3月2日
 */
public class LoggingGuavaDynamicProxy<T> extends AbstractInvocationHandler {

    private T delegate;

    /*
     * (non-Javadoc)
     * @see
     * com.google.common.reflect.AbstractInvocationHandler#handleInvocation(
     * java.lang.Object, java.lang.reflect.Method, java.lang.Object[])
     */
    @Override
    protected final Object handleInvocation(Object proxy, Method method,
	    Object[] args) throws Throwable {
	System.out.println("Call method: " + method.getName() + ", with args: "
	        + args.toString());
	Object o = method.invoke(delegate, args);
	return o;
    }

    public LoggingGuavaDynamicProxy(T t) {
	this.delegate = t;
    }

}
