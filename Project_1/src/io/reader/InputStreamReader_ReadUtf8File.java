package io.reader;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/**
 * 注意，这个类中读取了一个UTF-8编码格式的文件，这个类文件也需要用UTF-8的编码格式，否则不能正确读取中文。
 * （具体原因不详，JDK要求要这样？还是Eclipse的问题？）
 */
public class InputStreamReader_ReadUtf8File {

    public static void main(String[] args) throws Exception {
        String filePath = "./src/io/reader/test_text_file.utf-8.txt";

        InputStreamReader isr = new InputStreamReader(new FileInputStream(filePath), "UTF-8");
        BufferedReader reader = new BufferedReader(isr);

        System.out.println("--> The encoding of the file is: " + isr.getEncoding() + ",  with content: ");
        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }

        isr.close();
    }
}
