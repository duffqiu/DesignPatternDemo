/**
 * 
 */
package org.duffqiu.patterndemo.decorator;

/**
 * @author macbook
 * 
 *         2014年2月22日
 */
public interface ISendMessage {

    int sendMessage(String receiver, ReceiverType receiverType, String msg);

}
