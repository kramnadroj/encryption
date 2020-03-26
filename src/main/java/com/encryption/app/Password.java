package com.encryption.app;

import java.io.File;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.SecureRandom;
import java.security.spec.KeySpec;
import java.util.Arrays;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import org.apache.commons.io.FileUtils;

public class Password {
    private SecureRandom random = new SecureRandom();
    private final int SALT_BYTE_LENGTH = 12;
    private final int ITERATIONS = 100000;
    private final String ALGORITHM = "PBKDF2WithHmacSHA256";

    /* Set password to new value, zeroing out password */
    public void setPassword(char[] pass)
            throws IOException, GeneralSecurityException  {
        System.out.println("Setting Password");
        byte[] salt = new byte[SALT_BYTE_LENGTH];
        random.nextBytes(salt);
        saveBytes(salt, "salt.bin");
        byte[] hashVal = hashPassword(pass, salt);
        saveBytes(hashVal,"password.bin");
        Arrays.fill(hashVal, (byte) 0);
    }

    /* Indicates if given password is correct */
    public boolean checkPassword(char[] pass)
            throws IOException, GeneralSecurityException  {
        System.out.println("Checking Password");

        byte[] salt = loadBytes("salt.bin");
        byte[] hashVal1 = hashPassword(pass, salt);
        // Load the hash value stored in password.bin
        byte[] hashVal2 = loadBytes("password.bin");
        boolean arraysEqual = timingEquals(hashVal1, hashVal2);
        Arrays.fill(hashVal1, (byte) 0);
        Arrays.fill(hashVal2, (byte) 0);
        return arraysEqual;
    }

    /* Encrypts password & salt and zeroes both */
    private byte[] hashPassword(char[] pass, byte[] salt)
            throws GeneralSecurityException {
        KeySpec spec = new PBEKeySpec(pass, salt, ITERATIONS);
        Arrays.fill(pass, (char) 0);
        Arrays.fill(salt, (byte) 0);
        SecretKeyFactory f = SecretKeyFactory.getInstance(ALGORITHM);
        return f.generateSecret(spec).getEncoded();
    }

    /**
     * Indicates if both byte arrays are equal
     * but uses same amount of time if they are the same or different
     * to prevent timing attacks
     */
    public static boolean timingEquals(byte b1[], byte b2[]) {
        boolean result = true;
        int len = b1.length;
        if (len != b2.length) {
            result = false;
        }
        if (len > b2.length) {
            len = b2.length;
        }
        for (int i = 0; i < len; i++) {
            result &= (b1[i] == b2[i]);
        }
        return result;
    }

    private void saveBytes(byte[] bytes, String filename) throws IOException {
        // ... write bytes to the file
        FileUtils.writeByteArrayToFile(new File(filename), bytes);

    }

    private byte[] loadBytes(String filename) throws IOException {
        // ... read bytes to the file
        return FileUtils.readFileToByteArray(new File(filename));
    }
}
