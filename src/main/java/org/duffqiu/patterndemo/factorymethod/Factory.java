/**
 * 
 */
package org.duffqiu.patterndemo.factorymethod;

/**
 * @author macbook
 * 
 *         2014年2月15日
 */
public interface Factory {

    Product createProduct();

    void removeProductFromBuf();

}
