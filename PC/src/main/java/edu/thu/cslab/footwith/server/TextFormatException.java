package edu.thu.cslab.footwith.server;

/**
 * Created with IntelliJ IDEA.
 * User: bxl
 * Date: 3/15/13
 * Time: 3:30 PM
 * To change this template use File | Settings | File Templates.
 */
public class TextFormatException extends Exception {

    public TextFormatException(String message) {
        super(message);    //To change body of overridden methods use File | Settings | File Templates.
    }

    public TextFormatException() {
        super();    //To change body of overridden methods use File | Settings | File Templates.
    }

    @Override
    public String getMessage() {
        return super.getMessage();    //To change body of overridden methods use File | Settings | File Templates.
    }

    @Override
    public String toString() {
        return super.toString();    //To change body of overridden methods use File | Settings | File Templates.
    }
}
