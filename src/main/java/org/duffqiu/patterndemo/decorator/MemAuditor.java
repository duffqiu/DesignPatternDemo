/**
 * 
 */
package org.duffqiu.patterndemo.decorator;

import java.util.LinkedHashMap;
import java.util.Map;

import org.duffqiu.patterndemo.common.message.ReceiverType;

/**
 * @author macbook
 * 
 *         2014年2月23日
 */
public class MemAuditor implements IAuditor {

    private Map<Long, AuditEntry> auditEntries = new LinkedHashMap<>();
    private long lastestTime = 0;

    /*
     * (non-Javadoc)
     * @see org.duffqiu.patterndemo.decorator.IAuditor#doAudit(java.lang.String,
     * org.duffqiu.patterndemo.decorator.ReceiverType, java.lang.String)
     */
    @Override
    public final void doAudit(String receiver, ReceiverType receiverType,
	    String msg) {
	lastestTime = System.currentTimeMillis();
	AuditEntry entry = new AuditEntry(receiver, msg, receiverType,
	        lastestTime);
	auditEntries.put(lastestTime, entry);

    }

    /*
     * (non-Javadoc)
     * @see org.duffqiu.patterndemo.decorator.IAuditor#fetchLastestAuditEntry()
     */
    @Override
    public final AuditEntry fetchLastestAuditEntry() {

	return auditEntries.get(lastestTime);
    }
}
