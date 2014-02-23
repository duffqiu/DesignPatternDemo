/**
 * 
 */
package org.duffqiu.patterndemo.decorator;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;

/**
 * @author macbook
 * 
 *         2014年2月22日
 */
public class SendMessageBindingModule extends AbstractModule {

    private SmppSendMsg sender;

    /*
     * (non-Javadoc)
     * @see com.google.inject.AbstractModule#configure()
     */
    @Override
    protected final void configure() {

	//use this instead of @provide if need singleton
	bind(SmppSendMsg.class).toProvider(SmppSendMsgProvider.class).in(
	        Singleton.class);
	bind(IDescription.class).to(ConnectionDescription.class);
    }

}
