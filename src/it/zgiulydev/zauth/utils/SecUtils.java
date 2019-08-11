package it.zgiulydev.zauth.utils;

import org.apache.commons.codec.digest.DigestUtils;

public class SecUtils {
    public static String calcuateSha256(String value) {
        return DigestUtils.sha256Hex(value);
    }

    public static boolean compareHexToString(String hex, String value) {
        return DigestUtils.sha256Hex(value).equals(hex);
    }
}
