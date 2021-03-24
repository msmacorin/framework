/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package in.macor.commons.util;

import java.util.Arrays;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author macorin
 */
public class StringUtil {

    public static String uncapitalize(String str) {
        if (empty(str)) {
            return null;
        }
        return StringUtils.uncapitalize(str);
    }
    
    public static String capitalize(String str) {
        if (empty(str)) {
            return null;
        }
        
        return StringUtils.capitalize(str);
    }
    
    public static String encodeBase64(String str) {
        if (empty(str)) {
            return null;
        }
        
        byte[] byteArray = Base64.encodeBase64(str.getBytes());
        return new String(byteArray);
    }

    public static boolean empty(String str) {
        if (str == null) {
            return true;
        }
        
        return str.trim().isEmpty();
    }
    
}
