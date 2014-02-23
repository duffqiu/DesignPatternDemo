/**
 * 
 */
package org.duffqiu.patterndemo.decorator;

import com.google.inject.Inject;

/**
 * @author macbook
 * 
 *         2014年2月22日
 */
public class SendMessageImpl implements ISendMessage {

    @Inject
    private SmppSendMsg smpp;

    @Inject
    private IDescription desc;

    /*
     * (non-Javadoc)
     * @see
     * org.duffqiu.patterndemo.decorator.ISendMessage#sendMessage(java.lang.
     * String, int, java.lang.String)
     */
    @Override
    public final int sendMessage(String receiver, ReceiverType receiverType,
	    String msg) {

	if (!smpp.isConnected()) {
	    int connectResult = smpp.connect();
	    if (connectResult != 0) {
		return -1;
	    }
	}

	if (receiverType == ReceiverType.MSISDN_TYPE) {
	    SmppBody msgBody = new SmppBody(msg, 1, System.currentTimeMillis());
	    return smpp.sendMessage(receiver, msgBody);
	}

	return -1;
    }

    /**
     * @return the desc
     */
    public final IDescription getDesc() {
	return desc;
    }

    public SendMessageImpl() {

    }

}
