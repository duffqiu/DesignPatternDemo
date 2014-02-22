/**
 * 
 */
package org.duffqiu.patterndemo.decorator;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;

/**
 * @author macbook
 * 
 *         2014年2月22日
 */
public class SendMessageModule extends AbstractModule {

    /*
     * (non-Javadoc)
     * @see com.google.inject.AbstractModule#configure()
     */
    @Override
    protected void configure() {

    }

    @Provides
    final SmppSendMsg provideSmppSendMsg() {
	final int serverPort = 8090;
	SmppSendMsg sender = new SmppSendMsg("http://test.com", serverPort,
	        "duffqiu", "duffqiu");

	return sender;
    }
}
