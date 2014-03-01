/**
 * 
 */
package org.duffqiu.patterndemotest.proxy;

import static org.assertj.core.api.Assertions.assertThat;

import org.duffqiu.patterndemo.common.counter.IStatisticsable;
import org.duffqiu.patterndemo.common.message.IDescription;
import org.duffqiu.patterndemo.common.message.ISendMessage;
import org.duffqiu.patterndemo.common.message.ReceiverType;
import org.duffqiu.patterndemo.proxy.SendMessageProxy;
import org.duffqiu.patterndemo.proxy.SendMsgProxyBindingModule;
import org.junit.Before;
import org.junit.Test;

import com.google.inject.Guice;
import com.google.inject.Injector;

/**
 * @author macbook
 * 
 *         2014年3月1日
 */
public class ProxyTest {

    private Injector injector;
    private ISendMessage sender;

    @Before
    public void init() {
	injector = Guice.createInjector(new SendMsgProxyBindingModule());
	sender = injector.getInstance(SendMessageProxy.class);
    }

    @Test
    public final void testProxy() {
	int result = sender.sendMessage("13533834738",
	        ReceiverType.MSISDN_TYPE, "test proxy");
	assertThat(result).isEqualTo(0);

	IStatisticsable counter = (IStatisticsable) sender;
	assertThat(1L).isEqualTo(counter.currentTPS());

    }

    @Test(expected = UnsupportedOperationException.class)
    public final void testUnSupportedOperation() {
	IStatisticsable counter = (IStatisticsable) sender;
	counter.increase();
    }

    @Test
    public final void testDescription() {
	IDescription desc = sender.getDesc();
	assertThat(desc.getTargetId()).isEqualTo(
	        SendMessageProxy.class.getSimpleName());
    }
}
