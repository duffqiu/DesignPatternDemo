/**
 * 
 */
package org.duffqiu.patterndemo.decorator;

import org.duffqiu.patterndemo.common.message.ReceiverType;

/**
 * @author macbook
 * 
 *         2014年2月23日
 */
public interface IAuditor {

    void doAudit(String receiver, ReceiverType receiverType, String msg);

    AuditEntry fetchLastestAuditEntry();
}
