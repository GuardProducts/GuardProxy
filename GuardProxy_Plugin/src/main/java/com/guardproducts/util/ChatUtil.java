package com.guardproducts.util;

public class ChatUtil {

    public static String fixColor(String s) {
        s = s.replaceAll("&", "§");
        return s;
    }
}
