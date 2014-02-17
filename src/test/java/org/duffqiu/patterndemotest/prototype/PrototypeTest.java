/**
 * 
 */
package org.duffqiu.patterndemotest.prototype;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;

import org.duffqiu.patterndemo.prototype.Product;
import org.duffqiu.patterndemo.prototype.SubProduct;
import org.junit.Before;
import org.junit.Test;

/**
 * @author macbook
 * 
 *         2014年2月17日
 */
public class PrototypeTest {
    private Product product = Product.createProduct();
    private SubProduct subProduct1 = new SubProduct(4.5f, "sub product1");
    private SubProduct subProduct2 = new SubProduct(5.5f, "sub product2");

    @Before
    public final void initialization() {

	product.addSubProduct(subProduct1);
	product.addSubProduct(subProduct2);
    }

    @Test
    public final void testClone() throws CloneNotSupportedException {

	//	Product product = Product.createProduct();
	//	SubProduct subProduct1 = new SubProduct(4.5f, "sub product1");
	//	SubProduct subProduct2 = new SubProduct(5.5f, "sub product2");
	//	product.addSubProduct(subProduct1);
	//	product.addSubProduct(subProduct2);

	Product productClone = (Product) product.clone();

	SubProduct subProduct3 = productClone.getSubProductByIndex(0);
	SubProduct subProduct4 = productClone.getSubProductByIndex(1);

	assertThat(product).isNotSameAs(productClone);
	assertThat(product).isEqualTo(productClone);
	assertThat(subProduct1).isSameAs(subProduct3);
	assertThat(subProduct2).isSameAs(subProduct4);

    }

    @Test
    public final void testSoftClone() {

	Product softProduct = product.softCloneProduct(30.8f, "soft clone");
	SubProduct subProduct3 = softProduct.getSubProductByIndex(0);
	SubProduct subProduct4 = softProduct.getSubProductByIndex(1);

	assertThat(product).isNotSameAs(softProduct);
	assertThat(product).isNotEqualTo(softProduct);
	assertThat(subProduct1).isSameAs(subProduct3);
	assertThat(subProduct2).isSameAs(subProduct4);

    }

    @Test
    public final void testHardClone() throws ClassNotFoundException,
	    IOException {
	Product hardProduct = product.hardCloneProduct(40.8f, "hard clone");
	SubProduct subProduct3 = hardProduct.getSubProductByIndex(0);
	SubProduct subProduct4 = hardProduct.getSubProductByIndex(1);

	assertThat(product).isNotSameAs(hardProduct);
	assertThat(product).isNotEqualTo(hardProduct);
	assertThat(subProduct1).isNotSameAs(subProduct3);
	assertThat(subProduct2).isNotSameAs(subProduct4);
	assertThat(subProduct1).isEqualTo(subProduct3);
	assertThat(subProduct2).isEqualTo(subProduct4);
    }

    @Test
    public final void testHardCloneByCommonLand() {
	Product hardProduct = product.hardCloneProductByCommonLang(50.8f,
	        "hard clone by common land");
	SubProduct subProduct3 = hardProduct.getSubProductByIndex(0);
	SubProduct subProduct4 = hardProduct.getSubProductByIndex(1);

	assertThat(product).isNotSameAs(hardProduct);
	assertThat(product).isNotEqualTo(hardProduct);
	assertThat(subProduct1).isNotSameAs(subProduct3);
	assertThat(subProduct2).isNotSameAs(subProduct4);
	assertThat(subProduct1).isEqualTo(subProduct3);
	assertThat(subProduct2).isEqualTo(subProduct4);
    }

}
