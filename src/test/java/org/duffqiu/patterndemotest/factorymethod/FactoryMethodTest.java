/**
 * 
 */
package org.duffqiu.patterndemotest.factorymethod;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collection;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import org.duffqiu.patterndemo.factorymethod.BuffFactory;
import org.duffqiu.patterndemo.factorymethod.Client;
import org.duffqiu.patterndemo.factorymethod.ConcreteFactory;
import org.duffqiu.patterndemo.factorymethod.ConcreteProduct;
import org.duffqiu.patterndemo.factorymethod.Factory;
import org.duffqiu.patterndemo.factorymethod.Product;
import org.junit.Test;

/**
 * @author macbook
 * 
 *         2014年2月15日
 */
public class FactoryMethodTest {

    @Test
    public final void testSayHi() {
	Client client = new Client(new ConcreteFactory());

	int result = client.doSomething();

	assertThat(result).isEqualTo(ConcreteProduct.TEST_CODE);
    }

    @Test
    public final void testBufferFactoryInOneThread() {
	Factory factory = new BuffFactory();
	Product product1 = factory.createProduct();
	Product product2 = factory.createProduct();
	assertThat(product1).isSameAs(product2);
	factory.removeProductFromBuf();
	product1 = factory.createProduct();
	assertThat(product1).isNotSameAs(product2);
    }

    @Test
    public final void testBufferFactoryInMulitThread()
	    throws InterruptedException, ExecutionException {
	int threadNum = Runtime.getRuntime().availableProcessors() + 1;

	//	final List<Product> productList = new Vector<>();

	final Factory factory = new BuffFactory();

	ExecutorService manager = Executors.newScheduledThreadPool(threadNum);

	Collection<Callable<Product>> tasks = new Vector<Callable<Product>>();

	for (int i = 0; i < threadNum; i++) {
	    tasks.add(new Callable<Product>() {

		@Override
		public Product call() throws Exception {

		    Product p = factory.createProduct();
		    System.out.println("[1] create product: " + p);
		    //if not call destroy, the threadlocal will be shared 
		    factory.removeProductFromBuf();
		    return p;
		}

	    });
	}

	List<Future<Product>> productList = manager.invokeAll(tasks);

	manager.shutdown();

	manager.awaitTermination(2, TimeUnit.SECONDS);

	Product o = productList.get(0).get();

	for (int i = 1; i < productList.size(); i++) {
	    System.out.println("[4] " + o + " : " + productList.get(i).get());
	    assertThat(o).isNotSameAs(productList.get(i).get());

	}

    }

    @Test
    public final void testBufferFactoryMulitThreadInLocalBuffStorage()
	    throws InterruptedException, ExecutionException {
	int threadNum = Runtime.getRuntime().availableProcessors() + 1;

	final List<Product> productList = new Vector<>();

	final Factory factory = new BuffFactory();

	ExecutorService manager = Executors.newScheduledThreadPool(threadNum);

	Collection<Callable<Product>> tasks = new Vector<Callable<Product>>();

	for (int i = 0; i < threadNum; i++) {
	    tasks.add(new Callable<Product>() {

		@Override
		public Product call() throws Exception {

		    Product p = factory.createProduct();
		    productList.add(p);
		    System.out.println("[2] create product: " + p);
		    factory.removeProductFromBuf();
		    return p;
		}

	    });
	}

	manager.invokeAll(tasks);

	manager.shutdown();

	manager.awaitTermination(2, TimeUnit.SECONDS);

	Product o = productList.get(0);

	for (int i = 1; i < productList.size(); i++) {
	    System.out.println("[3] " + o + " : " + productList.get(i));
	    assertThat(o).isNotSameAs(productList.get(i));

	}

    }

}
