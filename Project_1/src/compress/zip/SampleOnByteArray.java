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

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import compress.Constants;

public class SampleOnByteArray {

    static byte[] b = null;
    static byte[] buf = new byte[1024];
    static int count = -1;

    public static void main(String args[]) throws Exception {
        byte[] b1 = zip(Constants.textForCompress.getBytes());
        byte[] b2 = unzip(b1);
        String newStr = new String(b2);
        System.out.println();

        if (Constants.textForCompress.equals(newStr)) {
            System.out.println("-- Demo success!");
        } else {
            System.out.println("-- Demo failed!");
        }
    }

    private static byte[] zip(byte[] data) throws Exception {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ZipOutputStream destOs = new ZipOutputStream(bos);

        // always need an ZipEntry, while GZIPInputStream not need, see {@link compress.gzip.SampleOnByteArray} 
        destOs.putNextEntry(new ZipEntry("uselessName"));
        destOs.write(data);
        destOs.finish();

        b = bos.toByteArray();

        bos.close();
        destOs.close();
        return b;
    }

    private static byte[] unzip(byte[] data) throws Exception {
        ByteArrayInputStream bis = new ByteArrayInputStream(data);
        ZipInputStream srcIs = new ZipInputStream(bis);

        ByteArrayOutputStream destOs = new ByteArrayOutputStream();
        ZipEntry entry = null;
        if ((entry = srcIs.getNextEntry()) != null) {
            while ((count = srcIs.read(buf)) > 0) {
                destOs.write(buf, 0, count);
            }
        }
        destOs.flush();
        b = destOs.toByteArray();
        destOs.close();

        destOs.close();
        srcIs.close();
        bis.close();
        return b;
    }
}
