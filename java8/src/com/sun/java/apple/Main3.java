package apple;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * Apple类 测试程序入口3  -- 排序
 *
 * @Author Sun
 * @date 2019-02-22
 */
public class Main3 {

    public static class AppleComparator implements Comparator<Apple>{
        @Override
        public int compare(Apple a1, Apple a2){
            return a1.getWeight().compareTo(a2.getWeight());
        }
    }

    public static void main(String... args){
        List<Apple> inventory = Arrays.asList(new Apple(80, "green"),
                new Apple(155, "green"),
                new Apple(120, "red"));

        // inventory.sort(new AppleComparator());

        /* inventory.sort(new Comparator<Apple>() {
            @Override
            public int compare(Apple apple, Apple t1) {
                return apple.getWeight().compareTo(t1.getWeight());
            }
        }); */

        // inventory.sort((Apple a1, Apple a2) -> a1.getWeight().compareTo(a2.getWeight()));

        // inventory.sort(Comparator.comparing((Apple a1) -> a1.getWeight()));

        inventory.sort(Comparator.comparing(Apple::getWeight));

        for(Apple apple : inventory){
            System.out.println(apple.toString());
        }

    }

}
