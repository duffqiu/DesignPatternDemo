/**
 * 
 */
package org.duffqiu.patterndemo.proxy;

import org.duffqiu.patterndemo.common.counter.IStatisticsable;
import org.duffqiu.patterndemo.common.message.IDescription;
import org.duffqiu.patterndemo.common.message.ISendMessage;
import org.duffqiu.patterndemo.common.message.ReceiverType;
import org.duffqiu.patterndemo.common.message.SendMessageImpl;

import com.google.common.base.Preconditions;
import com.google.inject.Inject;
import com.google.inject.name.Named;

/**
 * @author macbook
 * 
 *         2014年3月1日
 */
public class SendMessageProxy implements ISendMessage {

    @Inject
    private SendMessageImpl sender;
    @Inject
    @Named("SendMsgProxyDesc")
    private IDescription desc;
    @Inject
    private IStatisticsable counter;

    /*
     * (non-Javadoc)
     * @see
     * org.duffqiu.patterndemo.common.message.ISendMessage#sendMessage(java.
     * lang.String, org.duffqiu.patterndemo.common.message.ReceiverType,
     * java.lang.String)
     */
    @Override
    public final int sendMessage(String receiver, ReceiverType receiverType,
	    String msg) {

	Preconditions.checkNotNull(sender);
	Preconditions.checkNotNull(counter);

	counter.increase();

	return sender.sendMessage(receiver, receiverType, msg);
    }

    /*
     * (non-Javadoc)
     * @see org.duffqiu.patterndemo.common.message.ISendMessage#getDesc()
     */
    @Override
    public final IDescription getDesc() {

	return desc;
    }

}
