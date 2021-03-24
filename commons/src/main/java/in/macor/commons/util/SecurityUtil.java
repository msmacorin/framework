/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package in.macor.commons.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author macorin
 */
public class SecurityUtil {

    public static String encrypt(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        if (StringUtil.empty(str)) {
            return null;
        }
        
        MessageDigest algorithm = MessageDigest.getInstance("MD5");
        byte[] cipher = algorithm.digest(str.getBytes("UTF-8"));

        StringBuilder hexString = new StringBuilder();
        for (byte b : cipher) {
            hexString.append(String.format("%02X", 0xFF & b));
        }
        
        return hexString.toString();
    }
}
