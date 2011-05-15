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
import java.io.IOException;
import java.util.zip.GZIPOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ToSort {
    public static void gzipFile(String from, String to) throws IOException {
        FileInputStream in = new FileInputStream(from);
        GZIPOutputStream out = new GZIPOutputStream(new FileOutputStream(to));
        byte[] buffer = new byte[4096];
        int bytesRead;
        while ((bytesRead = in.read(buffer)) != -1) {
            out.write(buffer, 0, bytesRead);
        }
        in.close();
        out.close();
    }

    /** Zip the contents of the directory, and save it in the zipfile */
    public static void zipDirectory(String dir, String zipfile) throws IOException, IllegalArgumentException {
        // Check that the directory is a directory, and get its contents
        File d = new File(dir);
        if (!d.isDirectory()) {
            throw new IllegalArgumentException("Not a directory:  " + dir);
        }
        String[] entries = d.list();
        byte[] buffer = new byte[4096]; // Create a buffer for copying
        int bytesRead;

        ZipOutputStream out = new ZipOutputStream(new FileOutputStream(zipfile));

        for (String entrie : entries) {
            File f = new File(d, entrie);
            if (f.isDirectory()) {
                continue;//Ignore directory
            }
            FileInputStream in = new FileInputStream(f); // Stream to read file
            ZipEntry entry = new ZipEntry(f.getPath()); // Make a ZipEntry
            out.putNextEntry(entry); // Store entry
            while ((bytesRead = in.read(buffer)) != -1) {
                out.write(buffer, 0, bytesRead);
            }
            in.close();
        }
        out.close();
    }

    public static void main(String args[]) throws IOException {
        String from = "src/zip";
        File f = new File(from);
        boolean directory = f.isDirectory(); // Is it a file or directory?

        ToSort.zipDirectory(from, from + ".zip");
        ToSort.gzipFile(from, from + ".gz");
    }
}