/**
 * 
 */
package org.duffqiu.patterndemo.decorator;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.google.inject.name.Names;

/**
 * @author macbook
 * 
 *         2014年2月22日
 */
public class SendMessageBindingModule extends AbstractModule {

    /*
     * (non-Javadoc)
     * @see com.google.inject.AbstractModule#configure()
     */
    @Override
    protected final void configure() {

	//For SendMessageImpl
	//use this instead of @provide if need singleton
	bind(SmppSendMsg.class).toProvider(SmppSendMsgProvider.class).in(
	        Singleton.class);
	bind(IDescription.class).to(ConnectionDescription.class);

	//For SendMessageWithAuditImpl
	bind(IDescription.class).annotatedWith(
	        Names.named("SendMsgWithAuditDesc")).toProvider(
	        SendMessageWithAuditDescriptionProvider.class);

	bind(IAuditor.class).to(MemAuditor.class);

	bind(ISendMessage.class).to(SendMessageImpl.class);
    }

}
