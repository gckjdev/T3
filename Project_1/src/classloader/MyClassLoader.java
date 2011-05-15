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

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;

public class MyClassLoader extends URLClassLoader {
    private String thirdPath;

    public MyClassLoader(String path, ClassLoader parent) throws Exception {
        super(new URL[0], parent);
        this.thirdPath = path;
        init();
    }

    /**
     * Add all jar files in the specified path into the class path
     */
    private void init() throws Exception {
        File file = new File(thirdPath);

        if (!file.exists()) {
            System.out.println(file.getAbsolutePath() + " is not exists!");
            return;
        }
        if (file.isFile()) {
            addURL(file.toURL());
        } else {
            setAllJarFile(file);
        }
    }

    private void setAllJarFile(File file) throws Exception {
        for (File jarFile : file.listFiles()) {
            if (jarFile.isFile()) {
                addURL(jarFile.toURL());
            } else {
                setAllJarFile(jarFile);
            }
        }

    }

    @SuppressWarnings("unchecked")
    public Object newInstance(final String name) throws Exception {
        Object object = null;
        Class c = findLoadedClass(name);

        if (c != null) {
            object = c.newInstance();
            return object;
        }

        try {
            Class clazz = findClass(name);
            object = clazz.newInstance();
        } catch (ClassNotFoundException e) {
            Class clazz = loadClass(name);
            object = clazz.newInstance();
        } catch (Exception e) {
            throw new Exception("Please check if " + name, e);
        }

        return object;
    }
}
