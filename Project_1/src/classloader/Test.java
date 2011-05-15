/*------------------------------------------------------------------------------
 * COPYRIGHT Ericsson 2007
 *
 * The copyright to the computer program(s) herein is the property of
 * Ericsson Inc. The programs may be used and/or copied only with written
 * permission from Ericsson Inc. or in accordance with the terms and
 * conditions stipulated in the agreement/contract under which the
 * program(s) have been supplied.
 *----------------------------------------------------------------------------*/
package classloader;

public class Test {

    public static void main(String[] args) throws Exception {
        System.out.println(ClassLoader.getSystemClassLoader().getResource(""));

        MyClassLoader loader = new MyClassLoader("./src/bundle", ClassLoader.getSystemClassLoader());
        Object hello = loader.newInstance("org.apache.commons.logging.LogConfigurationException"); // only could init a non-constructor-parameter class

        System.out.println("Class loaded successfully.");
    }
}
