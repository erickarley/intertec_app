package com.intertec.usernamegenerator;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    		UserValidator validator = new UserValidator();
        System.out.println("Testing the username: erickarley2 and this is the result:" + validator.checkUserName("erickarley2"));
        System.out.println("Testing the username: erickarley and this is the result:" + validator.checkUserName("erickarley"));
        System.out.println("Testing the username: cannabis and this is the result:" + validator.checkUserName("cannabis"));
    }
}
