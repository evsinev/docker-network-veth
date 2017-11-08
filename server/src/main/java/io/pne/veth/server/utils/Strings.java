package io.pne.veth.server.utils;

public class Strings {

    public static String padRight(String aText, int aLength) {
        StringBuilder sb = new StringBuilder(aLength);
        sb.append(aText);
        while(sb.length() < aLength) {
            sb.append(' ');
        }
        return sb.toString();
    }
}
