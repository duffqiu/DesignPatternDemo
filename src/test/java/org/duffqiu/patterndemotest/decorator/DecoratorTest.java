/**
 * 
 */
package org.duffqiu.patterndemotest.decorator;

import static org.assertj.core.api.Assertions.assertThat;

import org.duffqiu.patterndemo.decorator.ReceiverType;
import org.duffqiu.patterndemo.decorator.SendMessageImpl;
import org.duffqiu.patterndemo.decorator.SendMessageModule;
import org.junit.Before;
import org.junit.Test;

import com.google.inject.Guice;
import com.google.inject.Injector;

/**
 * @author macbook
 * 
 *         2014年2月22日
 */
public class DecoratorTest {

    private SendMessageImpl sender;

    @Before
    public final void init() {
	Injector injector = Guice.createInjector(new SendMessageModule());
	sender = injector.getInstance(SendMessageImpl.class);
    }

    @Test
    public final void testSendMsg() {
	int status = sender.sendMessage("18680225631",
	        ReceiverType.MSISDN_TYPE, "test send smpp msg");
	assertThat(status).isEqualTo(0);
    }
}
