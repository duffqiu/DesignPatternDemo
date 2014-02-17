/**
 * 
 */
package org.duffqiu.patterndemo.prototype;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.SerializationUtils;

/**
 * @author macbook
 * 
 *         2014年2月17日
 */
public class Product implements Cloneable, Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 2532948798422832799L;
    private float price;
    private String name;
    private List<SubProduct> subProducts = new ArrayList<>();

    public Product() {

    }

    /**
     * @param price
     * @param name
     */
    public Product(float price, String name) {
	super();
	this.price = price;
	this.name = name;
    }

    /**
     * @return the price
     */
    public final float getPrice() {
	return price;
    }

    /**
     * @param price
     *            the price to set
     */
    public final void setPrice(float price) {
	this.price = price;
    }

    /**
     * @return the name
     */
    public final String getName() {
	return name;
    }

    /**
     * @param name
     *            the name to set
     */
    public final void setName(String name) {
	this.name = name;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#clone()
     */
    @Override
    public final Object clone() throws CloneNotSupportedException {

	try {
	    return super.clone();
	} catch (CloneNotSupportedException e) {
	    return null;
	}
    }

    /**
     * 
     * @param price
     * @param name
     * @return macbook 2014年2月17日
     */
    public final Product softCloneProduct(float price, String name) {
	Product p = null;
	try {
	    p = (Product) this.clone();
	} catch (CloneNotSupportedException e) {
	    System.out.println();
	}

	p.setName(name);
	p.setPrice(price);

	return p;
    }

    /**
     * 
     * @param price
     * @param name
     * @return macbook 2014年2月17日
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public final Product hardCloneProduct(float price, String name)
	    throws IOException, ClassNotFoundException {

	//serialization
	byte[] bytes = null;
	ByteArrayOutputStream baos = new ByteArrayOutputStream();
	ObjectOutputStream oos = new ObjectOutputStream(baos);
	oos.writeObject(this);
	bytes = baos.toByteArray();

	//de-serialization

	InputStream is = new ByteArrayInputStream(bytes);
	ObjectInputStream ois = new ObjectInputStream(is);
	Product p = (Product) ois.readObject();

	p.setName(name);
	p.setPrice(price);

	return p;
    }

    public final Product hardCloneProductByCommonLang(float price, String name) {

	//must make sure all the sub objects have implements serializable interface
	Product p = SerializationUtils.clone(this);
	p.setName(name);
	p.setPrice(price);

	return p;
    }

    /**
     * 
     * @param subProduct
     * @return macbook 2014年2月17日
     */
    public final int addSubProduct(SubProduct subProduct) {

	if (subProduct == null) {
	    return -1;
	}

	subProducts.add(subProduct);
	return subProducts.indexOf(subProduct);
    }

    /**
     * 
     * @param subProduct
     * @return macbook 2014年2月17日
     */
    public final boolean removeSubProduct(SubProduct subProduct) {
	if (subProduct == null) {
	    return false;
	}

	return subProducts.remove(subProduct);
    }

    /**
     * 
     * @param index
     * @return macbook 2014年2月17日
     */
    public final SubProduct getSubProductByIndex(int index) {
	if (index < 0 || index >= subProducts.size()) {
	    return null;
	}

	return subProducts.get(index);
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public final int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((name == null) ? 0 : name.hashCode());
	result = prime * result + Float.floatToIntBits(price);
	result = prime * result
	        + ((subProducts == null) ? 0 : subProducts.hashCode());
	return result;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public final boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	Product other = (Product) obj;
	if (name == null) {
	    if (other.name != null)
		return false;
	} else if (!name.equals(other.name))
	    return false;
	if (Float.floatToIntBits(price) != Float.floatToIntBits(other.price))
	    return false;
	if (subProducts == null) {
	    if (other.subProducts != null)
		return false;
	} else if (!subProducts.equals(other.subProducts))
	    return false;
	return true;
    }

    public static Product createProduct() {
	final float defaultPrice = 10.5f;
	return new Product(defaultPrice, "default proudct");
    }

}
