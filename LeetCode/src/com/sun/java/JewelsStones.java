import java.util.HashMap;
import java.util.Map;

/**
 * Jewels and Stones        -- 珠宝和石头
 *
 * @Author Sun
 * @date 2019-03-21
 */
public class JewelsStones {

    /**
     * You're given strings J representing the types of stones that are jewels, and S representing the stones you have.
     * Each character in S is a type of stone you have.  You want to know how many of the stones you have are also jewels.
     * <p>
     * The letters in J are guaranteed distinct, and all characters in J and S are letters. Letters are case sensitive,
     * so "a" is considered a different type of stone from "A".
     * <p>
     * Example 1:
     * Input: J = "aA", S = "aAAbbbb"
     * Output: 3
     * Example 2:
     * Input: J = "z", S = "ZZ"
     * Output: 0
     * <p>
     * Note:
     * 1. S and J will consist of letters and have length at most 50.
     * 2. The characters in J are distinct.
     */

    public static void main(String... args) {
        System.out.println(JewelsStones.numJewelsInStones("aA", "aAAbbbb"));
    }

    public static int numJewelsInStones(String J, String S) {
        Integer count = 0;
        char[] jArray = J.toCharArray();

        for (char s : S.toCharArray()) {
            for (char j : jArray) {
                if (s == j) {
                    count++;
                    break;
                }
            }
        }
        return count;
    }

    public static int numJewelsInStones1(String J, String S) {
        Integer count = 0;
        char[] jArray = J.toCharArray();
        Map<Character, Character> jMap = new HashMap<>(16);

        for (char j : jArray) {
            jMap.put(j, j);
        }

        for (char s : S.toCharArray()) {
            if (jMap.get(s) != null) {
                count++;
            }
        }
        return count;
    }

    public static int numJewelsInStones2(String J, String S) {
        int[] map = new int[128];
        for (char j : J.toCharArray()) {
            map[j] = 1;
        }
        int count = 0;
        for (char s : S.toCharArray()) {
            if (map[s] == 1) {
                count++;
            }
        }
        return count;
    }

    public static int numJewelsInStones3(String J, String S) {
        if (J.isEmpty()) {
            return 0;
        }
        int cnt = 0;
        for (int i = 0; i < S.length(); i++) {
            if (J.indexOf(S.charAt(i)) != -1) {
                cnt++;
            }
        }
        return cnt;
    }

    public static int numJewelsInStones4(String J, String S) {

        return S.length() - S.replaceAll("[" + J + "*]", "").length();
    }

}
