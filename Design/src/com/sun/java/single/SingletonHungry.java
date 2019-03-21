package single;

/**
 * 饿汉式单例
 *
 * @Author Sun
 * @date 2019-03-05
 */
public class SingletonHungry {

    private static SingletonHungry singletonHungry = new SingletonHungry();

    private SingletonHungry() {
    }

    public static SingletonHungry getInstance() {
        return singletonHungry;
    }

}
