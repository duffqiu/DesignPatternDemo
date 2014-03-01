/**
 * 
 */
package org.duffqiu.patterndemo.common.message;

/**
 * @author macbook
 * 
 *         2014年2月22日
 */
public class SmppBody {

    private String message;
    private int priority;
    private long timeStamp;

    /**
     * @param message
     * @param priority
     * @param l
     */
    public SmppBody(String message, int priority, long timeStamp) {
	super();
	this.message = message;
	this.priority = priority;
	this.timeStamp = timeStamp;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public final String toString() {
	return "SmppBody [message=" + message + ", priority=" + priority
	        + ", timeStamp=" + timeStamp + "]";
    }

}
