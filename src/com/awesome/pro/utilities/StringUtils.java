package com.awesome.pro.utilities;

import java.util.HashMap;
import java.util.Map;

/**
 * String operation utilities.
 * @author siddharth.s
 */
public final class StringUtils {

    /**
     * @param text String to split.
     * @param delimeter Character to split by.
     * @return Resulting array after splitting.
     */
    public static String[] split(String text, char delimeter) {
        int count = 1;
        for (int i = 0; i < text.length(); i++)
            if (text.charAt(i) == delimeter)
                count++;

        String[] array = new String[count];

        int a = -1;
        int b = 0;

        for (int i = 0; i < count; i++) {
            while (b < text.length() && text.charAt(b) != delimeter)
                b++;

            array[i] = text.substring(a+1, b);
            a = b;
            b++;
        }
        return array;
    }

    /**
     * @param line Text to take substring of.
     * @param separator Reference string after which the substring
     * will be taken.
     * @return Text after the first occurrence of separator.
     * Null is returned if the separator is not found in the text.
     */
    public static String substringAfter(String line, String separator) {
        int index = line.indexOf(separator);
        if(index == -1)
            return null;
        return line.substring(index + separator.length(), line.length());
    }

    /**
     * @param line Text to take substring from.
     * @param separator Reference string before which the substring
     * will be taken.
     * @return Text before the last occurrence of separator.
     * Null is returned if the separator is not found the text.
     */
    public static String substringBeforeLast(String line, String separator) {
        int index = line.lastIndexOf(separator);
        if(index == -1)
            return null;
        return line.substring(0, index);
    }

    /**
     * @param line Text to take substring from.
     * @param separator Reference string after which the substring
     * will be taken.
     * @return Text after the last occurrence of separator.
     * Null is returned if the separator is not found the text.
     */
    public static String substringAfterLast(String line, String separator) {
        int index = line.lastIndexOf(separator);
        if(index == -1)
            return null;
        return line.substring(index + separator.length(), line.length());
    }

    /**
     *
     * @param line Text to take substring from.
     * @param separator Reference string before which the substring
     * will be taken.
     * @return Text after the first occurrence of separator.
     * Null is returned if the separator is not found the text.
     */
    public static String substringBefore(String line, String separator) {
        int index = line.indexOf(separator);
        if(index == -1)
            return null;
        return line.substring(0, index);
    }

    /**
     * @param line
     * @param search
     * @param replace
     * @return
     */
    public static String replace(String line, String search, String replace) {
        int index = 0;
        String result = "";
        while ((index = line.indexOf(search)) != -1){
            result = result + line.substring(0, index) + replace;
            line = line.substring(index+search.length(), line.length());
        }
        return result+line;
    }

    public static String[] split(String text, String delimit) {
        int count = 0;
        int index = 0;
        Map<Integer, String> arr = new HashMap<>();

        if (text.indexOf(delimit) == -1){
            String[] array = new String[1];
            array[0] = text;
            return array;
        }

        while (text.length() != 0){
            if (text.indexOf(delimit) == -1){
                arr.put(count, text);
                count++;
                break;
            } else {
                while ((index = text.indexOf(delimit)) != -1) {
                    arr.put(count, text.substring(0, index));
                    count ++;
                    text = text.substring(index+delimit.length(), text.length());
                }
            }
        }
        String[] array = new String[count];
        //System.out.println(count);
        for (int i=0; i<count; i++) {
            array[i] = arr.get(i);
        }
        return array;
    }

    /**
     * @param line
     * @param afterString
     * @param beforeString
     * @return string between two char sequence
     */
    public static String stringInBetween(final String line, final String afterString, final String beforeString){
        if(line == null)
            return null;

        if(line.length() == 0)
            return null;

        if(!line.contains(afterString) || !line.contains(beforeString))
            return null;

        String tmp = substringAfter(line, afterString);
        return substringBefore(tmp, beforeString);
    }

}
