/**
 * 
 */
package org.duffqiu.patterndemo.proxy;

import org.duffqiu.patterndemo.common.counter.IStatisticsable;
import org.duffqiu.patterndemo.common.counter.TrafficCounter;
import org.duffqiu.patterndemo.common.message.ConnectionDescription;
import org.duffqiu.patterndemo.common.message.IDescription;
import org.duffqiu.patterndemo.common.message.SmppSendMsg;
import org.duffqiu.patterndemo.common.message.SmppSendMsgProvider;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.google.inject.name.Names;

/**
 * @author macbook
 * 
 *         2014年3月1日
 */
public class SendMsgProxyBindingModule extends AbstractModule {

    /*
     * (non-Javadoc)
     * @see com.google.inject.AbstractModule#configure()
     */
    @Override
    protected final void configure() {

	//binding for SendMessage.class
	bind(SmppSendMsg.class).toProvider(SmppSendMsgProvider.class).in(
	        Singleton.class);
	bind(IDescription.class).to(ConnectionDescription.class).in(
	        Singleton.class);

	//binding for SendMessageProxy.class
	bind(IDescription.class).annotatedWith(Names.named("SendMsgProxyDesc"))
	        .toProvider(SendMsgProxyDescriptionProvider.class)
	        .in(Singleton.class);

	bind(IStatisticsable.class).to(TrafficCounter.class);

    }

}
