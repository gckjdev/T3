package io.properties;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

/**
 * 注意，这个类中读取了一个UTF-8编码格式的文件，这个类文件也需要用UTF-8的编码格式，否则不能正确读取中文。
 * （具体原因不详，JDK要求要这样？还是Eclipse的问题？）
 */
public class LoadPropertiesFromFile {

    final String ASCII_FILE_PATH = "./io/properties/test_text_file.ascii.txt";
    final String UTF8_FILE_PATH = "./io/properties/test_text_file.utf-8.txt";

    public static void main(String[] args) throws Exception {
        LoadPropertiesFromFile lp = new LoadPropertiesFromFile();
        lp.loadPropertiesFromAsciiFile();
        lp.loadPropertiesFromUTF8File();
    }

    public void loadPropertiesFromAsciiFile() throws Exception {
        System.out.println("--> Finding files at path: " + this.getClass().getClassLoader().getResource(""));

        // load a ASCII file
        InputStream in_a = this.getClass().getClassLoader().getResourceAsStream(ASCII_FILE_PATH);
        Properties p = new Properties();
        p.load(in_a);
        System.out.println(p.get("dirverClass"));
        System.out.println(p.get("user"));

        in_a.close();
    }

    public void loadPropertiesFromUTF8File() throws Exception {
        System.out.println("--> Finding files at path: " + this.getClass().getClassLoader().getResource(""));

        // load a UTF-8 file
        InputStream in_u = this.getClass().getClassLoader().getResourceAsStream(UTF8_FILE_PATH);
        InputStreamReader reader = new InputStreamReader(in_u, "UTF-8");
        Properties properties = new Properties();
        properties.load(reader);
        System.out.println(properties.get("aaa"));
        System.out.println(properties.get("ccc"));

        reader.close();
        in_u.close();
    }
}
