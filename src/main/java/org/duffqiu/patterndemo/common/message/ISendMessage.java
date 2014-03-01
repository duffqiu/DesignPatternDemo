/**
 * 
 */
package org.duffqiu.patterndemo.common.message;


/**
 * @author macbook
 * 
 *         2014年2月22日
 */
public interface ISendMessage {

    int sendMessage(String receiver, ReceiverType receiverType, String msg);

    /**
     * @return macbook 2014年2月23日
     */
    IDescription getDesc();

}
