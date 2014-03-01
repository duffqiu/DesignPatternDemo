/**
 * 
 */
package org.duffqiu.patterndemo.proxy;

import org.duffqiu.patterndemo.common.message.ConnectionDescription;
import org.duffqiu.patterndemo.common.message.IDescription;

import com.google.inject.Provider;

/**
 * @author macbook
 * 
 *         2014年3月1日
 */
public class SendMsgProxyDescriptionProvider implements Provider<IDescription> {

    /*
     * (non-Javadoc)
     * @see com.google.inject.Provider#get()
     */
    @Override
    public final IDescription get() {

	return new ConnectionDescription(
	        SendMessageProxy.class.getSimpleName(),
	        "Send message proxy description");
    }

}
