/**
 * 
 */
package org.duffqiu.patterndemo.factorymethod;

/**
 * @author macbook
 * 
 *         2014年2月15日
 */
public class Client {
    private Factory factory;

    public Client(Factory factory) {
	this.factory = factory;
    }

    public final int doSomething() {
	Product product = factory.createProduct();
	return product.sayHi();

    }

}
