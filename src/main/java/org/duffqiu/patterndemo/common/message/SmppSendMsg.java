/**
 * 
 */
package org.duffqiu.patterndemo.common.message;

import com.google.inject.Singleton;

/**
 * @author macbook
 * 
 *         2014年2月22日
 */
@Singleton
public final class SmppSendMsg {

    private String serverURL = "";

    private int serverPort = 0;

    private String userName = "";

    private String passwd = "";
    private int connectStatus = -1;

    public int sendMessage(String receiver, SmppBody smppBody) {
	if (connectStatus == 0) {
	    System.out.println("Send SMPP:  " + smppBody + " to receiver: "
		    + receiver);
	    return 0;
	}
	return -1;
    }

    /**
     * @param serverURL
     * @param serverPort
     * @param userName
     * @param passwd
     */
    public SmppSendMsg(String serverURL, int serverPort, String userName,
	    String passwd) {
	super();
	this.serverURL = serverURL;
	this.serverPort = serverPort;
	this.userName = userName;
	this.passwd = passwd;
    }

    /**
     * 
     */
    private SmppSendMsg() {

    }

    /**
     * 
     * @return macbook 2014年2月22日
     */
    public int connect() {

	if (serverURL.isEmpty() || serverPort == 0 || userName.isEmpty()
	        || passwd.isEmpty()) {
	    return -1;
	}

	connectStatus = 0;

	return connectStatus;
    }

    /**
     * 
     * 
     * macbook 2014年2月22日
     */
    public void disConnect() {
	connectStatus = -1;
    }

    public boolean isConnected() {
	if (connectStatus == 0) {
	    return true;
	}

	return false;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + connectStatus;
	result = prime * result + ((passwd == null) ? 0 : passwd.hashCode());
	result = prime * result + serverPort;
	result = prime * result
	        + ((serverURL == null) ? 0 : serverURL.hashCode());
	result = prime * result
	        + ((userName == null) ? 0 : userName.hashCode());
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
	SmppSendMsg other = (SmppSendMsg) obj;
	if (connectStatus != other.connectStatus)
	    return false;
	if (passwd == null) {
	    if (other.passwd != null)
		return false;
	} else if (!passwd.equals(other.passwd))
	    return false;
	if (serverPort != other.serverPort)
	    return false;
	if (serverURL == null) {
	    if (other.serverURL != null)
		return false;
	} else if (!serverURL.equals(other.serverURL))
	    return false;
	if (userName == null) {
	    if (other.userName != null)
		return false;
	} else if (!userName.equals(other.userName))
	    return false;
	return true;
    }

}
