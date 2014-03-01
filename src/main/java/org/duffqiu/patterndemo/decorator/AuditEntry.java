/**
 * 
 */
package org.duffqiu.patterndemo.decorator;

import org.duffqiu.patterndemo.common.message.ReceiverType;

/**
 * @author macbook
 * 
 *         2014年2月23日
 */
public class AuditEntry {

    private String receiver;
    private String msg;
    private long timeStamp;
    private ReceiverType receiverType;

    /**
     * @param receiver
     * @param msg
     * @param receiverType
     * @param timeStamp
     */
    public AuditEntry(String receiver, String msg, ReceiverType receiverType,
	    long timeStamp) {
	super();
	this.receiver = receiver;
	this.msg = msg;
	this.timeStamp = timeStamp;
	this.receiverType = receiverType;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public final String toString() {
	return "AuditEntry [receiver=" + receiver + ", msg=" + msg
	        + ", timeStamp=" + timeStamp + ", receiverType=" + receiverType
	        + "]";
    }

    /**
     * @return the receiver
     */
    public final String getReceiver() {
	return receiver;
    }

    /**
     * @return the msg
     */
    public final String getMsg() {
	return msg;
    }

    /**
     * @return the timeStamp
     */
    public final long getTimeStamp() {
	return timeStamp;
    }

    /**
     * @return the receiverType
     */
    public final ReceiverType getReceiverType() {
	return receiverType;
    }

}
