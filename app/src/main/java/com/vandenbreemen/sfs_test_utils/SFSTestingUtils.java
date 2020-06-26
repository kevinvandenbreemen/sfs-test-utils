package com.vandenbreemen.sfs_test_utils;

import com.vandenbreemen.mobilesecurestorage.file.ChunkedMediumException;
import com.vandenbreemen.mobilesecurestorage.security.SecureString;
import com.vandenbreemen.mobilesecurestorage.security.crypto.persistence.SecureFileSystem;

import java.io.File;
import java.io.IOException;

/**
 * Utilities for testing Secure File System code
 * @author kevin
 */
public class SFSTestingUtils {

    /**
     * Location at which to store files during tests
     */
    public static final String TEST_DIR = "testOutput";

    /**
     * Gets a new test file.  The file will be deleted on program completion.
     *
     * @param name
     * @return
     */
    public static File getTestFile(String name) {

        File dir = new File(TEST_DIR);
        dir.mkdir();

        File file = new File(TEST_DIR + File.separator + name);
        try {
            file.createNewFile();
        } catch (IOException ioe) {
            ioe.printStackTrace();
            throw new RuntimeException("Unexpected - Unable to create test file!", ioe);
        }
        file.deleteOnExit();
        return file;
    }

    /**
     * @param name   Name of file
     * @param create Whether to actually create the file or provide a handle to an existing file
     * @return
     */
    public static File getTestFile(String name, boolean create) {
        File dir = new File(TEST_DIR);
        dir.mkdir();

        File file = new File(TEST_DIR + File.separator + name);
        if (create) {
            try {
                file.createNewFile();
            } catch (IOException ioe) {
                ioe.printStackTrace();
                throw new RuntimeException("Unexpected - Unable to create test file!", ioe);
            }
        }
        file.deleteOnExit();
        return file;
    }

    /**
     * Generate a new secure file system in test mode
     *
     * @param tempFile
     * @return
     * @throws ChunkedMediumException
     */
    public static SecureFileSystem getNewSecureFileSystem(File tempFile)
            throws ChunkedMediumException {

        SecureString password = SecureFileSystem.generatePassword(new SecureString("password123".getBytes()));

        SecureFileSystem fs = new SecureFileSystem(tempFile) {
            protected SecureString getPassword() {
                return password;
            }

        };
        return fs;
    }

}
