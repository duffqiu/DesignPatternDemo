/**
 * 
 */
package org.duffqiu.patterndemotest.decorator;

import static org.assertj.core.api.Assertions.assertThat;

import org.duffqiu.patterndemo.decorator.IDescription;
import org.duffqiu.patterndemo.decorator.ISendMessage;
import org.duffqiu.patterndemo.decorator.ReceiverType;
import org.duffqiu.patterndemo.decorator.SendMessageBindingModule;
import org.duffqiu.patterndemo.decorator.SendMessageImpl;
import org.duffqiu.patterndemo.decorator.SmppSendMsg;
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

    private Injector injector;
    private ISendMessage sender;

    @Before
    public final void init() {
	injector = Guice.createInjector(new SendMessageBindingModule());
	sender = injector.getInstance(SendMessageImpl.class);
    }

    @Test
    public final void testSendMsg() {
	int status = sender.sendMessage("18680225631",
	        ReceiverType.MSISDN_TYPE, "test send smpp msg");
	assertThat(status).isEqualTo(0);
    }

    @Test
    public final void testSendMsgFailedWithErrorReceiverType() {
	int status = sender.sendMessage("duffqiu@gmail.com",
	        ReceiverType.EMAIL_TYPE, "test send email msg");
	assertThat(status).isEqualTo(-1);
    }

    @Test
    public final void testGuiceSingletonOnSmppSendMessage() {
	SmppSendMsg smppSender1 = injector.getInstance(SmppSendMsg.class);
	SmppSendMsg smppSender2 = injector.getInstance(SmppSendMsg.class);
	assertThat(smppSender1).isSameAs(smppSender2);
    }

    @Test
    public final void testGuicePropertyBinding() {
	IDescription desc = sender.getDesc();
	assertThat(desc.getTargetId()).describedAs("Default ID");
	assertThat(desc.getTargetDescription()).describedAs(
	        "This is the default description");
    }
}
