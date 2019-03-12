package apple;

import java.util.ArrayList;
import java.util.List;

/**
 * 苹果功能类
 *
 * @Author Sun
 * @date 2019-02-20
 */
public class FilteringApples {

    public static List<Apple> filterGreenApples(List<Apple> inventory){
        List<Apple> result = new ArrayList<>();
        for (Apple apple: inventory){
            if ("green".equals(apple.getColor())) {
                result.add(apple);
            }
        }
        return result;
    }

    public static List<Apple> filterHeavyApples(List<Apple> inventory){
        List<Apple> result = new ArrayList<>();
        for (Apple apple: inventory){
            if (apple.getWeight() > 150) {
                result.add(apple);
            }
        }
        return result;
    }

    /**
     * 判断当前苹果是否是绿色
     * @param apple 苹果
     * @return 判断结果 true/false
     */
    public static boolean isGreenApple(Apple apple) {
        return "green".equals(apple.getColor());
    }

    /**
     * 判断当前苹果是否大于150克
     * @param apple 苹果
     * @return 判断结果 true/false
     */
    public static boolean isHeavyApple(Apple apple) {
        return apple.getWeight() > 150;
    }

}
