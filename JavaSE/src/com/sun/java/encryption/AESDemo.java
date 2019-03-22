package encryption;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * AES加密类
 *
 * @Author Sun
 * @date 2019-02-20
 */
public class AESDemo {

    public static void main(String... args) {
        String password = "123465";
        String content = "加密内容";

        try {

            // 创建AES的Key生产者
            KeyGenerator kgen = KeyGenerator.getInstance("AES");
            // 利用用户密码作为随机数初始化
            kgen.init(128, new SecureRandom(password.getBytes()));
            // 根据用户密码，生成一个秘钥
            SecretKey secretKey = kgen.generateKey();
            // 返回基本编码格式的秘钥
            byte[] enCodeFormat = secretKey.getEncoded();
            // 转换为AES专用秘钥
            SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
            // 创建加密器    -- A填充方式是PKCS5Padding，工作模式是CBC模式。
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            byte[] byteContent = content.getBytes("utf-8");
            // 初始化为加密模式的密码器
            cipher.init(Cipher.ENCRYPT_MODE, key);
            // 加密
            byte[] result = cipher.doFinal(byteContent);
            // 输出加密后密文
            System.out.println("加密后密文：" + result.toString());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }

    }

}
