/*------------------------------------------------------------------------------
 * COPYRIGHT Sample 2007
 *
 * The copyright to the computer program(s) herein is the property of
 * Sample Inc. The programs may be used and/or copied only with written
 * permission from Sample Inc. or in accordance with the terms and
 * conditions stipulated in the agreement/contract under which the
 * program(s) have been supplied.
 *----------------------------------------------------------------------------*/
package compress.zip;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import compress.Constants;

public class SampleOnFile {

    static byte buf[] = new byte[Constants.BufferSize];
    static int count;

    public static void main(String[] args) throws Exception {
        cleanup(Constants.destZipFile);
        cleanup(Constants.destFile);
        zipFile(Constants.srcFileA, Constants.destZipFile);
        unzipFile(Constants.destZipFile, Constants.destFile);
    }

    private static void cleanup(String name) {
        File file = new File(name);
        if (file.exists()) {
            file.delete();
        }
    }

    private static void zipFile(String src, String dest) throws Exception {
        File srcFile = new File(src);

        FileInputStream srcIs = new FileInputStream(srcFile);
        ZipOutputStream destOs = new ZipOutputStream(new FileOutputStream(dest));

        ZipEntry entry = new ZipEntry(srcFile.getName());
        destOs.putNextEntry(entry);
        while ((count = srcIs.read(buf)) > 0) {
            destOs.write(buf, 0, count);
        }

        destOs.closeEntry();
        destOs.close();
        srcIs.close();
    }

    private static void unzipFile(String src, String dest) throws Exception {
        ZipInputStream srcIs = new ZipInputStream(new FileInputStream(src));

        ZipEntry entry = null;
        if ((entry = srcIs.getNextEntry()) != null) {
            FileOutputStream destOs = new FileOutputStream(dest); // here could use entry.getName() 
            while ((count = srcIs.read(buf)) > 0) {
                destOs.write(buf, 0, count);
            }
            destOs.close();
        }

        srcIs.close();
    }
}
