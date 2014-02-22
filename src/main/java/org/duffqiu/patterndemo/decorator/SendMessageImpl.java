/**
 * 
 */
package org.duffqiu.patterndemo.decorator;

/**
 * @author macbook
 * 
 *         2014年2月22日
 */
public class SendMessageImpl implements ISendMessage {

    private SmppSendMsg smpp;

    /*
     * (non-Javadoc)
     * @see
     * org.duffqiu.patterndemo.decorator.ISendMessage#sendMessage(java.lang.
     * String, int, java.lang.String)
     */
    @Override
    public final int sendMessage(String receiver, ReceiverType receiverType,
	    String msg) {

	if (receiverType == ReceiverType.MSISDN_TYPE) {
	    SmppBody msgBody = new SmppBody(msg, 1, System.currentTimeMillis());
	    return smpp.sendMessage(receiver, msgBody);
	}

	return -1;
    }

    /**
     * @param smpp
     */
    public SendMessageImpl(SmppSendMsg smpp) {
	super();
	this.smpp = smpp;
    }

}
