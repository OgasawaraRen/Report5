package jp.ac.uryukyu.ie.e205701;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Parse {
    static Map<Character, Integer> makeKanjiMap() {
        Map<Character, Integer> mp = new HashMap<>();
        mp.put('零', 0);
        mp.put('一', 1);
        mp.put('二', 2);
        mp.put('三', 3);
        mp.put('四', 4);
        mp.put('五', 5);
        mp.put('六', 6);
        mp.put('七', 7);
        mp.put('八', 8);
        mp.put('九', 9);
        mp.put('十', 10);
        mp.put('百', 100);
        mp.put('千', 1000);
        mp.put('万', 10000);
        mp.put('億', 100000000);

        return mp;
    }

    final static Map<Character, Integer> PARSE_MAP = makeKanjiMap();
    final static String[] DIGIT_NAMES = { "億", "万" };

    static int partValue(String str, int charIndex, int beforeValue, int totalValue) {
        int nowVal = PARSE_MAP.get(str.charAt(charIndex));
        if (charIndex == str.length() - 1) {
            return beforeValue * nowVal + totalValue;
        }
        int nextVal = PARSE_MAP.get(str.charAt(charIndex + 1));
        if (nowVal > nextVal) {
            return partValue(str, charIndex + 1, 1, totalValue + beforeValue * nowVal);
        } else {
            return partValue(str, charIndex + 1, beforeValue * nowVal, totalValue);
        }
    }

    static ArrayList<String[]> dividString(int i, ArrayList<String[]> returnStrArray, String baseStr) {
        if (i >= DIGIT_NAMES.length) {
            String[] strAndDigit = { baseStr, "一" };
            returnStrArray.add(strAndDigit);
            return returnStrArray;
        }
        String[] splitStrs = baseStr.split(DIGIT_NAMES[i]);
        if (splitStrs.length == 1) {
            return dividString(i + 1, returnStrArray, baseStr);
        }
        String[] strAndDigit = { splitStrs[0], DIGIT_NAMES[i] };
        returnStrArray.add(strAndDigit);
        return dividString(i + 1, returnStrArray, splitStrs[1]);
    }

    public static int kanjiParse(String str) {
        int total = 0;
        ArrayList<String[]> dividedStrArray = dividString(0, new ArrayList<String[]>(), str);
        for (String[] strAndDigit : dividedStrArray) {
            total += partValue(strAndDigit[0], 0, 1, 0) * PARSE_MAP.get(strAndDigit[1].toCharArray()[0]);
        }

        return total;
    }

}