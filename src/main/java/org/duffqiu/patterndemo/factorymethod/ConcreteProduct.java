/**
 * 
 */
package org.duffqiu.patterndemo.factorymethod;

/**
 * @author macbook
 * 
 *         2014年2月15日
 */
public class ConcreteProduct implements Product {
    public static final int TEST_CODE = 100;

    /*
     * (non-Javadoc)
     * @see org.duffqiu.patterndemo.factorymethod.Product#sayHi()
     */
    @Override
    public final int sayHi() {
	System.out.println("I am a real product");
	return TEST_CODE;
    }

}
