/**
 * 
 */
package org.duffqiu.patterndemo.decorator;

import com.google.inject.Inject;
import com.google.inject.name.Named;

/**
 * @author macbook
 * 
 *         2014年2月23日
 */
public class SendMessageWithAuditImpl implements ISendMessage {

    @Inject
    private ISendMessage sender;

    @Inject
    @Named("SendMsgWithAuditDesc")
    private IDescription desc;

    @Inject
    private IAuditor auditor;

    /*
     * (non-Javadoc)
     * @see
     * org.duffqiu.patterndemo.decorator.ISendMessage#sendMessage(java.lang.
     * String, org.duffqiu.patterndemo.decorator.ReceiverType, java.lang.String)
     */
    @Override
    public final int sendMessage(String receiver, ReceiverType receiverType,
	    String msg) {

	auditor.doAudit(receiver, receiverType, msg);
	return sender.sendMessage(receiver, receiverType, msg);

    }

    /*
     * (non-Javadoc)
     * @see org.duffqiu.patterndemo.decorator.ISendMessage#getDesc()
     */
    @Override
    public final IDescription getDesc() {

	return desc;
    }

    public SendMessageWithAuditImpl(ISendMessage sender, IDescription desc,
	    IAuditor auditor) {
	this.sender = sender;
	this.auditor = auditor;
	this.desc = desc;
    }

    public SendMessageWithAuditImpl() {

    }

    /**
     * @return macbook 2014年2月23日
     */
    public final AuditEntry getLasterEntry() {

	return auditor.fetchLastestAuditEntry();
    }

}
