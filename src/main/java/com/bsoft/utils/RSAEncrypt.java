package com.bsoft.utils;

/**
 * Created by weilishan on 2020/2/13.
 */
import com.alibaba.fastjson.JSON;
import com.bsoft.entity.SysDomain;
import com.bsoft.entity.SysMenus;
import com.bsoft.entity.SysPersonnel;
import com.bsoft.entity.SysRoles;
import com.sun.tools.javac.util.ArrayUtils;
import org.apache.commons.codec.binary.Base64;
import javax.crypto.Cipher;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.*;

public class RSAEncrypt {
    public static Map<Integer, String> keyMap = new HashMap<Integer, String>();  //用于封装随机产生的公钥与私钥
    public static String privateKey = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAIyFmAAAoaAWJgKDxVgjsomlWNfnsId/AaB4rvPZP8pq10tkfP+FfPAlOlHsW8Zo7G+roEqQKwrFs38CpoZqJ6whcA7WBWJzJRXcna5y82TMnIBN6TKDwjpDJcse2BmHaqLxgJY3YClzBRTDqdvSPMhp/SvyUbAD/aajz2ME/6hdAgMBAAECgYAwtgawyYc9dTAL4x2/pfA34sdfYINDFPW9aNhM01xXkJ29FsJ12RIsNNt4lpRhvPFo7HeOIVeqTkoglwyev6Gsds9HYYKQJL9dpw9g4P590yScCUNE5bPah1JcSMHw18alLBxxVk6DiDFVItkLONqS1QrRTqLkFI9YtfcVkiVFwQJBANnmuNhoHyMAdaeal3ihmGVNe8XLZwsrXYYc+GmH9AzPzS5YqrAdvshwV13KFIgX1pOvIKMBtxqOmvl5a3j0aqkCQQClF1z34/dNiMbO+X1WaeGpBbDgBO86BIDLw4aQpHwuxd7QUm5wQCot8MoP0zl21n8Jd96I2P2JxRQI0GTtwXSVAkAaVTehb3PT1wpg5t3HRkAUffinPp2FTAhRXM7ocfWcVsCFCVIXVoDn2Cu2ueUhTUAxOMjI6jwmiXVREMtcIq2BAkEAogpqgA3LwNiNer27XkQS8FnboCZ3wNSGrnm97N7l3Orupj8ikx8Hx4veaOYEMzaZ/msfi+agBMfhwDz7s7n8eQJAa34qQO9ikkY3q6BUlAa52ayyC4l4gmUcLiZESIBaJlhXajD0hKFxpochGQM/TmACs7MxXlgbRozUgOyLFFqbBw==";
    public static String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCMhZgAAKGgFiYCg8VYI7KJpVjX57CHfwGgeK7z2T/KatdLZHz/hXzwJTpR7FvGaOxvq6BKkCsKxbN/AqaGaiesIXAO1gVicyUV3J2ucvNkzJyATekyg8I6QyXLHtgZh2qi8YCWN2ApcwUUw6nb0jzIaf0r8lGwA/2mo89jBP+oXQIDAQAB";
    public static void main(String[] args) throws Exception {
        //生成公钥和私钥
        genKeyPair();
        //加密字符串
        String message = "HAv53/wqx/9gk2gpXj0a/zQwBJu2v8oHsoPn2n0NwmnFEjq4oqjIH7IqsD73cqRx0DLU/88X1W5r/sSE7XYaaYRnJnep6fi2O7vqXt6i3DvETmD62S9Ve1d9bHXVZ1Jpf9s8kSg+7oMmqTS1VqnyNHQMWtYJfevFTgyht77dfv1lQojUvGDohN4v+NIprBHPF5QFmgnQVNdWvHMP4PC28Tp0ZGLExQL6t6u4UWC+G0FF5b6TUWlBu6+S+mK1dAQZH6Wpt4jSd+VYa3Ja4a33Anru8jCk07Vok7arVGwkzVjLWBGIz7DYgv2R4YY0r01+JIirWKJ4HJlhSWliRXMBgR47WIfPjWKk0YZCqoRTfaGuzA7RrdmTvzFg7ildYqPbGTmb89LaRPGf4mq0zN9nkin1dZ/a1PQTGo52VV8ow8kBH2AAX07QJTZhhNYjYJij1PWOwESocFgKup5iX0xYdJwzjksCvoItCmUzS2ZyItl4LM01ej/buJsA1TEmyGFoOGQ1O7BFTowomdAtFpJlg4hp9o658VprKylxVPFg9OrbBt1FLhZcKJrvz20h+zJZuHS0y06nskTDF8IiQUqHK81E2NKlESgvH7b9EQFlVSP7i4DmF7Mcs2abCnnqk4O/tYNVFENbErPu2Dd+LXDA2JaLkkysRFqtpOFjpw7A3V9HN8zaY8ivYhpMQwJIYDD9Ul/mgDYkCXoAqCqEjXGWBPjYNR6VuTSCGajZdX4YFF2JR8kywmtkNLS7qndmhSNnaz5f3UMFzx18RPjg4xW5TozKCh6+S+o+ebF3439YcuHvsmQh6IhSi8P0gMOMg3tXYmFXhs46iouQBmluPKCIKYQmQzUanKHNiOT6oX3+mwqRQu+jHeExRJkHNmsV9ZzuG1DLSVXcd9/3FRx0ixv3wVtS10xVLM2GowpqTHmFkVR42i/Trt8Fl0DckFMwPus/FcOHrd9FpXYyh0kAFUrq0yEmC1+Uchd4ej53f4VT3u1PgqtDFYQhTf6svBkWNfimiKR6LTYldVBp7KJ+gr+PSD9g9sAHK1/HyvWXbPaYqjnK9WVD6FsfDif60p6JFqCm0PLx7Qli+RnN4IQ97jULTP6oNsGY1P9vrf5H5NaTOysvrkYs8sw9kJedaZcxeH91nw6IMUnsBZO9jyOgMpNt77OigN8htnbsrvlBGc1A8xKI2gzV91FVwMM8EZeaaiev6h/uV6bQOXASJ4uQ/MDaKHcaVJM41RmJhCl+R/z9k4c8T03nIXuhGZe9sjEMOmRIjxqMLeEIT4I4vb3Z5iHDEp24daWI6TCJVn8XItL/HrMUQt6Fb8vZ6rAmosDReN7pa7VLrPwbxpWbmlggWWBT3Xp5lYDiDv8KPJePp8FyQbqenhQbrU9++chmUmtOr+HdR6mN4P+19pbjcz+pxnix7oikluDc32Ugua/HoANdQys/fvidHu4fGRLuW0svEGbwroxidfO63q5ZuUYgveguUde6G4NNEL37QvYkfyZImA0A6/kHNFS3vdR01dmyhDVecBzdvKuJ8PtevFZjYDpUq3MgPMjNTPmEPtVvQ4KqDSj766XOoDVWS2MmkParYoID93Q/OvwGhCZaSJl+ueypMzhkL/FVPi9hqjQVHzCCs2lLkEQIzA/w22PTbcwe93Cj1/GyS8jpbTF4Y5q8PqzlTSbvTqX4/pIeYA+wcfP/f3k=";

//        message = "123";

//        System.out.println("随机生成的公钥为:" + keyMap.get(0));
//        System.out.println("随机生成的私钥为:" + keyMap.get(1));
//        String messageEn = encrypt(message,publicKey);
//        System.out.println(message + "\t加密后的字符串为:" + messageEn);
//        String messageDe = decrypt(messageEn,privateKey);
//        System.out.println("还原后的字符串为:" + messageDe);
        String messageDe = decrypt(message,privateKey);
        System.out.println("还原后的字符串为:" + messageDe);

    }

    /**
     * 随机生成密钥对
     * @throws NoSuchAlgorithmException
     */
    public static void genKeyPair() throws NoSuchAlgorithmException {
        // KeyPairGenerator类用于生成公钥和私钥对，基于RSA算法生成对象
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
        // 初始化密钥对生成器，密钥大小为96-1024位
        keyPairGen.initialize(1024,new SecureRandom());
        // 生成一个密钥对，保存在keyPair中
        KeyPair keyPair = keyPairGen.generateKeyPair();
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();   // 得到私钥
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();  // 得到公钥
        String publicKeyString = new String(Base64.encodeBase64(publicKey.getEncoded()));
        // 得到私钥字符串
        String privateKeyString = new String(Base64.encodeBase64((privateKey.getEncoded())));
        // 将公钥和私钥保存到Map
        keyMap.put(0,publicKeyString);  //0表示公钥
        keyMap.put(1,privateKeyString);  //1表示私钥
    }
    /**
     * RSA公钥加密
     *
     * @param str
     *            加密字符串
     * @param publicKey
     *            公钥
     * @return 密文
     * @throws Exception
     *             加密过程中的异常信息
     */
    public static String encrypt1( String str, String publicKey ) throws Exception{
        //base64编码的公钥
        byte[] decoded = Base64.decodeBase64(publicKey);
        RSAPublicKey pubKey = (RSAPublicKey) KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(decoded));
        //RSA加密
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, pubKey);
        String outStr = Base64.encodeBase64String(cipher.doFinal(str.getBytes("UTF-8")));
        return outStr;
    }
    public static String encrypt( String str, String publicKey ) throws Exception{
        //base64编码的公钥
        byte[] decoded = Base64.decodeBase64(publicKey);
        RSAPublicKey pubKey = (RSAPublicKey) KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(decoded));
        //RSA加密
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, pubKey);
        byte[] inputArray = str.getBytes("UTF-8");
        return Base64.encodeBase64String(splitByte(inputArray,cipher,117));
    }

    /**
     * RSA私钥解密
     *
     * @param str
     *            加密字符串
     * @param privateKey
     *            私钥
     * @return 铭文
     * @throws Exception
     *             解密过程中的异常信息
     */
    public static String decrypt1(String str, String privateKey) throws Exception{
        //64位解码加密后的字符串
        byte[] inputByte = Base64.decodeBase64(str.getBytes("UTF-8"));
        //base64编码的私钥
        byte[] decoded = Base64.decodeBase64(privateKey);
        RSAPrivateKey priKey = (RSAPrivateKey) KeyFactory.getInstance("RSA").generatePrivate(new PKCS8EncodedKeySpec(decoded));
        //RSA解密
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, priKey);
        return new String(cipher.doFinal(inputByte));
    }
    public static String decrypt(String str, String privateKey) throws Exception{
        //64位解码加密后的字符串
        byte[] inputByte = Base64.decodeBase64(str.getBytes("UTF-8"));
        //base64编码的私钥
        byte[] decoded = Base64.decodeBase64(privateKey);
        RSAPrivateKey priKey = (RSAPrivateKey) KeyFactory.getInstance("RSA").generatePrivate(new PKCS8EncodedKeySpec(decoded));
        //RSA解密
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, priKey);
        return new String(splitByte(inputByte,cipher,128));
    }

    private static byte[] splitByte(byte[] inputByte,Cipher cipher,int maxBlock) throws Exception{
        // 标识
        int offSet = 0;
        byte[] resultBytes = {};
        byte[] cache = {};
        int inputLength = inputByte.length;
        while (inputLength - offSet > 0) {
            if (inputLength - offSet > maxBlock) {
                cache = cipher.doFinal(inputByte, offSet, maxBlock);
                offSet += maxBlock;
            } else {
                cache = cipher.doFinal(inputByte, offSet, inputLength - offSet);
                offSet = inputLength;
            }
            resultBytes = Arrays.copyOf(resultBytes, resultBytes.length + cache.length);
            System.arraycopy(cache, 0, resultBytes, resultBytes.length - cache.length, cache.length);
        }
        return resultBytes;
    }
}


