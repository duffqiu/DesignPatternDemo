/**
 * 
 */
package org.duffqiu.patterndemotest.factorymethod;

import static org.assertj.core.api.Assertions.assertThat;

import org.duffqiu.patterndemo.factorymethod.Client;
import org.duffqiu.patterndemo.factorymethod.ConcreteFactory;
import org.duffqiu.patterndemo.factorymethod.ConcreteProduct;
import org.junit.Test;

/**
 * @author macbook
 * 
 *         2014年2月15日
 */
public class FactoryMethodTest {

    @Test
    public void testSayHi() {
	Client client = new Client(new ConcreteFactory());

	int result = client.doSomething();

	assertThat(result).isEqualTo(ConcreteProduct.TEST_CODE);
    }
}
