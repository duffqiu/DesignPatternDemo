/**
 * 
 */
package org.duffqiu.patterndemo.factorymethod;

/**
 * @author macbook
 * 
 *         2014年2月15日
 */
public class BuffFactory implements Factory {

    private ThreadLocal<Product> products = new ThreadLocal<>();

    /*
     * (non-Javadoc)
     * @see org.duffqiu.patterndemo.factorymethod.Factory#createProduct()
     */
    @Override
    public final Product createProduct() {
	Product product = products.get();

	if (product == null) {
	    product = new ConcreteProduct();
	    products.set(product);
	} else {
	    System.out.println("return existing product");
	}

	return product;

    }

    public final void destroyProduct() {
	products.remove();
    }

}
