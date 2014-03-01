/**
 * 
 */
package org.duffqiu.patterndemo.decorator;

import org.duffqiu.patterndemo.common.message.ConnectionDescription;

import com.google.inject.Provider;

/**
 * @author macbook
 * 
 *         2014年2月23日
 */
public class SendMessageWithAuditDescriptionProvider implements
        Provider<ConnectionDescription> {

    /*
     * (non-Javadoc)
     * @see com.google.inject.Provider#get()
     */
    @Override
    public final ConnectionDescription get() {

	return new ConnectionDescription("Audit with SMPP",
	        "Send Msg with audit via SMPP");
    }

}
