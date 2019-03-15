package optional;

import java.util.Optional;

/**
 * Option工具类
 *
 * @Author Sun
 * @date 2019-03-15
 */
public class OptionalUtility {

    /**
     * 将给定String类型字符串转换为Integer类型
     *
     * @param s 一个字符串
     * @return Optional类型封装的Integer
     */
    public static Optional<Integer> stringToInt(String s) {
        try {
            // 如果String能转换为对应的Integer，将其封装在Optioal对象中返回
            return Optional.of(Integer.parseInt(s));
        } catch (NumberFormatException e) {
            // 否则返回一个空的Optional对象
            return Optional.empty();
        }
    }

}
