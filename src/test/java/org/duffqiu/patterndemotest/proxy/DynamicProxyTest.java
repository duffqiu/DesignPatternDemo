/**
 * 
 */
package org.duffqiu.patterndemotest.proxy;

import static org.assertj.core.api.Assertions.assertThat;

import java.lang.reflect.InvocationHandler;

import org.duffqiu.patterndemo.common.message.ISendMessage;
import org.duffqiu.patterndemo.common.message.ReceiverType;
import org.duffqiu.patterndemo.common.message.SendMessageImpl;
import org.duffqiu.patterndemo.proxy.LoggingDynamicProxy;
import org.duffqiu.patterndemo.proxy.LoggingGuavaDynamicProxy;
import org.duffqiu.patterndemo.proxy.SendMsgProxyBindingModule;
import org.duffqiu.patterndemo.proxy.TrafficCountingDynamicProxy;
import org.junit.Before;
import org.junit.Test;

import com.google.common.reflect.Reflection;
import com.google.inject.Guice;
import com.google.inject.Injector;

/**
 * @author macbook
 * 
 *         2014年3月1日
 */
public class DynamicProxyTest {

    private Injector injector;
    private ISendMessage sender;

    @Before
    public final void init() {
	injector = Guice.createInjector(new SendMsgProxyBindingModule());
	sender = injector.getInstance(SendMessageImpl.class);
    }

    @Test
    public final void testDynamicProxyInterface() {
	//the class which is delegated must has an interface
	ISendMessage sendDynamicProxy = LoggingDynamicProxy
	        .createProxyInstance(sender);

	int result = sendDynamicProxy.sendMessage("13533834738",
	        ReceiverType.MSISDN_TYPE, "test proxy");
	assertThat(result).isEqualTo(0);
    }

    @Test
    public final void testDynamicProxyOverlay() {

	ISendMessage sendLoggingDynamicProxy = LoggingDynamicProxy
	        .createProxyInstance(sender);

	ISendMessage sendCountingDynamicProxy = TrafficCountingDynamicProxy
	        .createProxyInstance(sendLoggingDynamicProxy);

	int result = sendCountingDynamicProxy.sendMessage("13533834738",
	        ReceiverType.MSISDN_TYPE, "test proxy");
	assertThat(result).isEqualTo(0);
    }

    @Test
    public final void testGuavaDynamicProxy() {

	ISendMessage sendLoggingDynamicProxy = Reflection.newProxy(
	        ISendMessage.class, new LoggingDynamicProxy<>(sender));

	ISendMessage sendCountingDynamicProxy = Reflection.newProxy(
	        ISendMessage.class, new TrafficCountingDynamicProxy<>(
	                sendLoggingDynamicProxy));

	int result = sendCountingDynamicProxy.sendMessage("13533834738",
	        ReceiverType.MSISDN_TYPE, "test proxy");
	assertThat(result).isEqualTo(0);
    }

    @Test
    public final void testGuavaHandler() {
	InvocationHandler handler = new LoggingGuavaDynamicProxy<>(sender);

	ISendMessage sendLoggingDynamicProxy = Reflection.newProxy(
	        ISendMessage.class, handler);

	int result = sendLoggingDynamicProxy.sendMessage("13533834738",
	        ReceiverType.MSISDN_TYPE, "test proxy");
	assertThat(result).isEqualTo(0);

	ISendMessage sendLoggingDynamicProxy2 = Reflection.newProxy(
	        ISendMessage.class, handler);

	assertThat(sendLoggingDynamicProxy).isEqualTo(sendLoggingDynamicProxy2);

	assertThat(sendLoggingDynamicProxy).isNotSameAs(
	        sendLoggingDynamicProxy2);

    }
}
