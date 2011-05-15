/*------------------------------------------------------------------------------
 * COPYRIGHT Sample 2007
 *
 * The copyright to the computer program(s) herein is the property of
 * Sample Inc. The programs may be used and/or copied only with written
 * permission from Sample Inc. or in accordance with the terms and
 * conditions stipulated in the agreement/contract under which the
 * program(s) have been supplied.
 *----------------------------------------------------------------------------*/
package compress;

public interface Constants {
    public final int BufferSize = 2048;

    public final String srcZipDir = "src/compress/sample/sample_dir.zip";
    public final String srcZipFile = "src/compress/sample/sample_file.zip";
    public final String srcDir = "src/compress/sample/sample_dir";
    public final String srcFileA = "src/compress/sample/sample_file_A.txt";
    public final String srcFileB = "src/compress/sample/sample_file_B.txt";
    public final String destFile = "src/compress/generated/Sample.txt";
    public final String destDir = "src/compress/generated/";
    public final String destZipFile = "src/compress/generated/Sample.zip";

    public final String textForCompress = "This is a text for compress 1 " + //
            "This is a text for compress 2 " + //
            "This is a text for compress 3 " + //
            "This is a text for compress 4 " + //
            "This is a text for compress 5 ";
}
