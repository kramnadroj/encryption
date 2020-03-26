import com.encryption.app.JavaKeyStore;
import com.encryption.app.Password;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.crypto.SecretKey;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.KeyStore;

import static org.junit.Assert.assertTrue;

public class EncryptionTest {

    char[] pwdArray = "password".toCharArray();


    @BeforeClass
    public void beforeClass() throws IOException, GeneralSecurityException  {

//        KeyStore ks = KeyStore.getInstance(KeyStore.getDefaultType());
//        ks.load(null, pwdArray);
//
//        try (FileOutputStream fos = new FileOutputStream("newKeyStoreFileName.jks")) {
//            ks.store(fos, pwdArray);
//        }

        JavaKeyStore keyStore = new JavaKeyStore("JKS", "password", "testStore");
        keyStore.createEmptyKeyStore();
//        keyStore.



    }

    @Test
    public void shouldAnswerWithTrue() throws IOException, GeneralSecurityException {

//        KeyStore ks = KeyStore.getInstance("JKS");
//        ks.load(new FileInputStream("newKeyStoreFileName.jks"), pwdArray);
//
//        SecretKey secretKey
//
//        KeyStore.SecretKeyEntry secret
//                = new KeyStore.SecretKeyEntry(secretKey);
//        KeyStore.ProtectionParameter password
//                = new KeyStore.PasswordProtection(pwdArray);
//        ks.setEntry("db-encryption-secret", secret, password);







//        Password pword = new Password();
//        String str = "12345678";
//        char[] ch = new char[str.length()];
//        System.out.println(ch.length);
//        pword.setPassword(ch);
//        pword.checkPassword(ch);
        System.out.println("TEST");
        assertTrue( true );
    }
}
