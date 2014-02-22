/**
 * 
 */
package org.duffqiu.patterndemo.decorator;

/**
 * @author macbook
 * 
 *         2014年2月22日
 */
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
}
