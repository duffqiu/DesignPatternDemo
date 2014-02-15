/**
 * 
 */
package org.duffqiu.patterndemo.factorymethod;

/**
 * @author macbook
 * 
 *         2014年2月15日
 */
public class ConcreteFactory implements Factory {

    /*
     * (non-Javadoc)
     * @see org.duffqiu.patterndemo.factorymethod.Factory#createProduct()
     */
    @Override
    public final Product createProduct() {

	return new ConcreteProduct();
    }

    /*
     * (non-Javadoc)
     * @see org.duffqiu.patterndemo.factorymethod.Factory#destroyProduct()
     */
    @Override
    public void destroyProduct() {

    }

}
