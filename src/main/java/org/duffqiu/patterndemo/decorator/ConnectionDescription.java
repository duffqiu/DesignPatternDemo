/**
 * 
 */
package org.duffqiu.patterndemo.decorator;

/**
 * @author macbook
 * 
 *         2014年2月22日
 */
public class ConnectionDescription implements IDescription {

    private String id = "Default ID";
    private String description = "This is the default description";

    /*
     * (non-Javadoc)
     * @see org.duffqiu.patterndemo.decorator.IDescription#getTargetId()
     */
    @Override
    public final String getTargetId() {
	// TODO Auto-generated method stub
	return id;
    }

    /*
     * (non-Javadoc)
     * @see
     * org.duffqiu.patterndemo.decorator.IDescription#getTargetDescription()
     */
    @Override
    public final String getTargetDescription() {
	// TODO Auto-generated method stub
	return description;
    }

    /**
     * @param id
     * @param description
     */
    public ConnectionDescription(String id, String description) {
	super();
	this.id = id;
	this.description = description;
    }

    public ConnectionDescription() {

    }

}
