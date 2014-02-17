/**
 * 
 */
package org.duffqiu.patterndemo.prototype;

import java.io.Serializable;

/**
 * @author macbook
 * 
 *         2014年2月17日
 */
public class SubProduct implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 7285230893262477089L;
    private float price;
    private String name;

    /**
     * @param price
     * @param name
     */
    public SubProduct(float price, String name) {
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
     * @see java.lang.Object#hashCode()
     */
    @Override
    public final int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((name == null) ? 0 : name.hashCode());
	result = prime * result + Float.floatToIntBits(price);
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
	SubProduct other = (SubProduct) obj;
	if (name == null) {
	    if (other.name != null)
		return false;
	} else if (!name.equals(other.name))
	    return false;
	if (Float.floatToIntBits(price) != Float.floatToIntBits(other.price))
	    return false;
	return true;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public final String toString() {
	return "SubProduct [price=" + price + ", name=" + name + "]";
    }

}
