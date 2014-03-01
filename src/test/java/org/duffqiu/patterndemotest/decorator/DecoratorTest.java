/**
 * 
 */
package org.duffqiu.patterndemotest.decorator;

import static org.assertj.core.api.Assertions.assertThat;

import org.duffqiu.patterndemo.common.message.ConnectionDescription;
import org.duffqiu.patterndemo.common.message.IDescription;
import org.duffqiu.patterndemo.common.message.ISendMessage;
import org.duffqiu.patterndemo.common.message.ReceiverType;
import org.duffqiu.patterndemo.common.message.SendMessageImpl;
import org.duffqiu.patterndemo.common.message.SmppSendMsg;
import org.duffqiu.patterndemo.decorator.AuditEntry;
import org.duffqiu.patterndemo.decorator.IAuditor;
import org.duffqiu.patterndemo.decorator.MemAuditor;
import org.duffqiu.patterndemo.decorator.SendMessageBindingModule;
import org.duffqiu.patterndemo.decorator.SendMessageWithAuditImpl;
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
	assertThat(desc.getTargetId()).isEqualTo("Default ID");
	assertThat(desc.getTargetDescription()).isEqualTo(
	        "This is the default description");
    }

    @Test
    public final void testSendMsgWithAuitWithInject() {
	SendMessageWithAuditImpl auditSender = injector
	        .getInstance(SendMessageWithAuditImpl.class);

	int status = auditSender.sendMessage("18680225631",
	        ReceiverType.MSISDN_TYPE, "test send smpp msg");

	assertThat(status).isEqualTo(0);

	AuditEntry entry = auditSender.getLasterEntry();

	assertThat(entry.getReceiver()).isEqualTo("18680225631");
	assertThat(entry.getMsg()).isEqualTo("test send smpp msg");
	assertThat(entry.getReceiverType()).isEqualTo(ReceiverType.MSISDN_TYPE);
	assertThat(entry.getTimeStamp()).isNotZero();

	IDescription desc = auditSender.getDesc();
	assertThat(desc.getTargetId()).isEqualTo(
	        SendMessageWithAuditImpl.class.getSimpleName());
	assertThat(desc.getTargetDescription()).isEqualTo(
	        "Send Msg with audit via SMPP");
    }

    @Test
    public final void testSendMsgWithAuitWithNew() {
	IDescription desc = new ConnectionDescription("Audit with SMPP",
	        "Send Msg with audit via SMPP by new");
	IAuditor auditor = new MemAuditor();

	ISendMessage senderNew = new SendMessageWithAuditImpl(this.sender,
	        desc, auditor);

	int status = senderNew.sendMessage("13533834738",
	        ReceiverType.MSISDN_TYPE, "test send smpp msg to romanty");

	assertThat(status).isEqualTo(0);

	assertThat(senderNew).isInstanceOf(SendMessageWithAuditImpl.class);

	SendMessageWithAuditImpl auditSender = (SendMessageWithAuditImpl) senderNew;
	AuditEntry entry = auditSender.getLasterEntry();

	assertThat(entry.getReceiver()).isEqualTo("13533834738");
	assertThat(entry.getMsg()).isEqualTo("test send smpp msg to romanty");
	assertThat(entry.getReceiverType()).isEqualTo(ReceiverType.MSISDN_TYPE);
	assertThat(entry.getTimeStamp()).isNotZero();

    }

}
