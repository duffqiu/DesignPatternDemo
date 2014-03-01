/**
 * 
 */
package org.duffqiu.patterndemo.common.message;

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

    public final void setDesc(IDescription desc) {
	this.desc = desc;
    }

    public SendMessageImpl() {

    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((smpp == null) ? 0 : smpp.hashCode());
	return result;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	SendMessageImpl other = (SendMessageImpl) obj;
	if (smpp == null) {
	    if (other.smpp != null)
		return false;
	} else if (!smpp.equals(other.smpp))
	    return false;
	return true;
    }

}
