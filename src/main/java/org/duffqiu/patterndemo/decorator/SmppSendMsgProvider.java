/**
 * 
 */
package org.duffqiu.patterndemo.decorator;

import com.google.inject.Provider;

/**
 * @author macbook
 * 
 *         2014年2月22日
 */
public class SmppSendMsgProvider implements Provider<SmppSendMsg> {

    /*
     * (non-Javadoc)
     * @see com.google.inject.Provider#get()
     */
    @Override
    public final SmppSendMsg get() {
	final int serverPort = 8090;
	return new SmppSendMsg("http://test.com", serverPort, "duffqiu",
	        "duffqiu");
    }

}
