package lib;


import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Shoma Takeo on 2014/11/14.
 */
public class Aes {

    public static void main(String[] args) {
        System.out.println("hoge");
        System.out.println(encrypt("222222ABCDEFG", "3456789012345678", "0f9876gregegegkb"));
        System.out.println(decrypt("oGflmA2OwusfvbIT4cEcJA", "3456789012345678", "0f9876gregegegkb"));
    }
    private static final String UTF8 = "UTF-8";

    public static String encrypt(String text, String encryptKey, String encryptIv) {

        if (StringUtils.isEmpty(text)) {
            return null;
        }
        String resultStr = null;
        try {
            // 文字列をバイト配列へ変換
            byte[] byteText = text.getBytes(UTF8);

            // 暗号化キーと初期化ベクトルをバイト配列へ変換
            byte[] byteKey = encryptKey.getBytes(UTF8);
            byte[] byteIv = encryptIv.getBytes(UTF8);

            // 暗号化キーと初期化ベクトルのオブジェクト生成
            SecretKeySpec key = new SecretKeySpec(byteKey, "AES");
            IvParameterSpec iv = new IvParameterSpec(byteIv);

            // Cipherオブジェクト生成
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");

            // Cipherオブジェクトの初期化
            cipher.init(Cipher.ENCRYPT_MODE, key, iv);

            // 暗号化の結果格納
            byte[] byteResult = cipher.doFinal(byteText);

            // Base64へエンコード
            byte[] base64Result = Base64.encodeBase64(byteResult);
            resultStr = new String(base64Result, UTF8);

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        }

        // 暗号化文字列を返却
        return resultStr;
    }

    public static String decrypt(String encodedText, String encryptKey, String encryptIv) {

        if (StringUtils.isEmpty(encodedText)) {
            return null;
        }
        // 変数初期化
        String strResult = null;

        try {
            // 文字列をバイト配列へ変換
            byte[] base64Byte = encodedText.getBytes(UTF8);

            // Base64をデコード
            byte[] byteText = Base64.decodeBase64(base64Byte);

            // 暗号化キーと初期化ベクトルをバイト配列へ変換
            byte[] byteKey = encryptKey.getBytes(UTF8);
            byte[] byteIv = encryptIv.getBytes(UTF8);

            // 復号化キーと初期化ベクトルのオブジェクト生成
            SecretKeySpec key = new SecretKeySpec(byteKey, "AES");
            IvParameterSpec iv = new IvParameterSpec(byteIv);

            // Cipherオブジェクト生成
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");

            // Cipherオブジェクトの初期化
            cipher.init(Cipher.DECRYPT_MODE, key, iv);

            // 復号化の結果格納
            byte[] byteResult = cipher.doFinal(byteText);

            // バイト配列を文字列へ変換
            strResult = new String(byteResult, UTF8);

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        }

        // 復号化文字列を返却
        return strResult;
    }
}

