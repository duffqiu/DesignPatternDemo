/**
 * 
 */
package org.duffqiu.patterndemo.decorator;

import org.duffqiu.patterndemo.common.message.ConnectionDescription;
import org.duffqiu.patterndemo.common.message.IDescription;

import com.google.inject.Provider;

/**
 * @author macbook
 * 
 *         2014年2月23日
 */
public class SendMessageWithAuditDescriptionProvider implements
        Provider<IDescription> {

    /*
     * (non-Javadoc)
     * @see com.google.inject.Provider#get()
     */
    @Override
    public final IDescription get() {

	return new ConnectionDescription(
	        SendMessageWithAuditImpl.class.getSimpleName(),
	        "Send Msg with audit via SMPP");
    }

}
