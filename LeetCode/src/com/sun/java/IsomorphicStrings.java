import java.util.HashMap;
import java.util.Map;

/**
 * Isomorphic Strings        -- 同构字符串
 *
 * @Author Sun
 * @date 2019-03-22
 */
public class IsomorphicStrings {

    /**
     * Given two strings s and t, determine if they are isomorphic.
     *
     * Two strings are isomorphic if the characters in s can be replaced to get t.
     *
     * All occurrences of a character must be replaced with another character while preserving the order of characters.
     * No two characters may map to the same character but a character may map to itself.
     *
     * Example 1:
     *      Input: s = "egg", t = "add"
     *      Output: true
     * Example 2:
     *      Input: s = "foo", t = "bar"
     *      Output: false
     * Example 3:
     *      Input: s = "paper", t = "title"
     *      Output: true
     *
     * Note:
     *      You may assume both s and t have the same length.
     */

    public static void main(String... args) {
        System.out.println(IsomorphicStrings.isIsomorphic("ffffffffffeeeeewwwggg", "ssssssssssrrrrrqqqaaa"));
    }

    public static boolean isIsomorphic(String s, String t) {
        int len = s.length();

        if (len == 0) {
            return true;
        }

        Map<Character, Character> sMap = new HashMap<>(len);
        Map<Character, Character> tMap = new HashMap<>(len);
        char[] sChar = s.toCharArray();
        char[] tChar = t.toCharArray();
        Character sVal;
        Character tVal;

        for (int i = 0; i < len; i++) {
            sVal = sMap.get(sChar[i]);
            tVal = tMap.get(tChar[i]);

            if (sVal == null && tVal == null) {
                sMap.put(sChar[i], tChar[i]);
                tMap.put(tChar[i], sChar[i]);
                continue;
            } else if (sVal == null || sVal != tChar[i] || tVal == null || tVal != sChar[i]) {
                return false;
            }

        }

        return true;
    }

    public static boolean isIsomorphic1(String s, String t) {
        if (s == null || s.length() <= 1) {
            return true;
        }
        HashMap<Character, Character> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char a = s.charAt(i);
            char b = t.charAt(i);
            if (map.containsKey(a)) {
                if (map.get(a).equals(b)) {
                    continue;
                } else {
                    return false;
                }
            } else {
                if (!map.containsValue(b)) {
                    map.put(a, b);
                } else {
                    return false;
                }

            }
        }
        return true;
    }

    public static boolean isIsomorphic2(String s, String t) {
        Map m = new HashMap(s.length());
        for (Integer i = 0; i < s.length(); ++i) {
            if (m.put(s.charAt(i), i) != m.put(t.charAt(i) + "", i)) {
                return false;
            }
        }
        return true;
    }

}
