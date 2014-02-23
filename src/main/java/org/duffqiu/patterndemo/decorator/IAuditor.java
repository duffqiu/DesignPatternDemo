/**
 * 
 */
package org.duffqiu.patterndemo.decorator;

/**
 * @author macbook
 * 
 *         2014年2月23日
 */
public interface IAuditor {

    void doAudit(String receiver, ReceiverType receiverType, String msg);

    AuditEntry fetchLastestAuditEntry();
}
