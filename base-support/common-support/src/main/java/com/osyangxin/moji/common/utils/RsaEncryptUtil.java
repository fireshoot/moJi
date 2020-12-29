package com.osyangxin.moji.common.utils;

import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import javax.crypto.Cipher;
import org.apache.commons.codec.binary.Base64;

/**
 * @author tongwenwu
 * @date 2019/11/07 10:43
 **/
public class RsaEncryptUtil {
    private static final String ALGORIGHTM = "RSA";

    private static final String PUBLIC_KEY =
            "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDUPSaUYBBWHYCMzoIHaMd2MvzB\n" +
            "FFe2K66g0BA54BVv2CMMvRAoF1TTwmJHnIa5nGfF92J3+LTSOV32a0pUPs4O0FbX\n" +
            "7bL2XWpyCI4md5Tlyfk8NdZQ+SnkfjGzrsllClwisCT4y7IfwiBKJ5BQUMqlezvv\n" +
            "PFm2Vuy9UVcEM4otoQIDAQAB";

    private static final String PRIVATE_KEY =
            "MIICeAIBADANBgkqhkiG9w0BAQEFAASCAmIwggJeAgEAAoGBANQ9JpRgEFYdgIzO\n" +
            "ggdox3Yy/MEUV7YrrqDQEDngFW/YIwy9ECgXVNPCYkechrmcZ8X3Ynf4tNI5XfZr\n" +
            "SlQ+zg7QVtftsvZdanIIjiZ3lOXJ+Tw11lD5KeR+MbOuyWUKXCKwJPjLsh/CIEon\n" +
            "kFBQyqV7O+88WbZW7L1RVwQzii2hAgMBAAECgYEArOu1xyYNqVwYJKEMaCUCfldE\n" +
            "f7unSxGEEhnzXnQogxvCUtqnk0KPJok1scriKdA47J6GZ+EG0lFTMTZxNPqGvyci\n" +
            "RjXwtEHarTJ+nOHBKCTQ3e8N4Bn7R37dX/Q9Md/Hzfh4aaWsGgdCLw5BZOYJ6QLk\n" +
            "1tGd4NnnUJmn/PWDnwECQQDvNvs662uOf1/rd0lgidmJkbbedqoDa6PpVFvhfRA7\n" +
            "H0l4/nNC1FD70Sm+e6SsumNsuhA6fF+qhyd5WmVlmRi5AkEA4yGZzmtdYj6hq6fP\n" +
            "VoesUQHZEgxKsqTpaN4J/cEN7Mg40FQieRgb0EfFQgObSQO9XSoWcARByksvZJzC\n" +
            "QaL4KQJBANKUd/yaGYjLoEjMLZAKSbHM0Ept86QnMZfJZ0jMq2kgVbFpbVW7Sb8j\n" +
            "lbfVaFWVjsi70mVp711StPEjHnedmiECQQDH2k79xJb81WNoKVZVKzhCZz/bk5k+\n" +
            "q8D9lzJvZ6mbCWqEVSOoLcB7ektllMrUakf7bAcmNXi2SYXAH/cFziJRAkB3JKI3\n" +
            "4w8cKib7EQk2FxmAfl4wQT/82p8uQEg4nRNzdueFgZpKIVWz5HqmCDlbXO/tLMCs\n" +
            "g4jaUayW4CLT++fu";

    public static String encrypt(String plainText) throws Exception {
        byte[] decoded = Base64.decodeBase64(PUBLIC_KEY);
        RSAPublicKey pubKey =
                (RSAPublicKey) KeyFactory.getInstance(ALGORIGHTM).generatePublic(new X509EncodedKeySpec(decoded));
        Cipher cipher = Cipher.getInstance(ALGORIGHTM);
        cipher.init(Cipher.ENCRYPT_MODE, pubKey);
        return Base64.encodeBase64String(cipher.doFinal(plainText.getBytes(StandardCharsets.UTF_8)));
    }

    public static String decrypt(String cipherText) throws Exception {
        byte[] inputByte = Base64.decodeBase64(cipherText.getBytes(StandardCharsets.UTF_8));
        byte[] decoded = Base64.decodeBase64(PRIVATE_KEY);
        RSAPrivateKey priKey =
                (RSAPrivateKey) KeyFactory.getInstance(ALGORIGHTM).generatePrivate(new PKCS8EncodedKeySpec(decoded));
        Cipher cipher = Cipher.getInstance(ALGORIGHTM);
        cipher.init(Cipher.DECRYPT_MODE, priKey);
        return new String(cipher.doFinal(inputByte));
    }

    public static class RsaStruct {
        private String plainText;
        private String cipherText;
        private String decryptedText;

        RsaStruct(String plainText, String cipherText, String decryptedText) {
            this.plainText = plainText;
            this.cipherText = cipherText;
            this.decryptedText = decryptedText;
        }

        @Override
        public String toString() {
            final StringBuilder builder = new StringBuilder("RsaStruct{\n");
            builder.append("    plainText=\"").append(plainText).append("\",\n");
            builder.append("    cipherText=\"").append(cipherText).append("\",\n");
            builder.append("    decryptedText=\"").append(decryptedText).append("\"\n");
            builder.append('}');
            return builder.toString();
        }

        boolean isDecryptedSucceed() {
            return plainText.equals(decryptedText);
        }
    }

    public static void main(String[] args) throws Exception {
        try {
            String account = "I Can Eat Glass";
            String accountEncrypted = encrypt(account);
            String accountDecrypted = decrypt(accountEncrypted);
            RsaStruct accountRsaStruct = new RsaStruct(account, accountEncrypted, accountDecrypted);
            System.out.println(accountRsaStruct);
            assert accountRsaStruct.isDecryptedSucceed();

            System.out.println();

            String password = "我能吞下玻璃而不伤身体";
            String passwordEncrypted = encrypt(password);
            String passwordDecrypted = decrypt(passwordEncrypted);
            RsaStruct passwordRsaStruct = new RsaStruct(password, passwordEncrypted, passwordDecrypted);
            System.out.println(passwordRsaStruct);
            assert passwordRsaStruct.isDecryptedSucceed();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
