package single;

/**
 * 懒汉式单例
 *
 * @Author Sun
 * @date 2019-03-05
 */
public class SingletonLazy {

    private static SingletonLazy singletonLazy;

    private SingletonLazy() {
    }

    public static synchronized SingletonLazy getInstance() {
        if (singletonLazy == null) {
            singletonLazy = new SingletonLazy();
        }
        return singletonLazy;
    }

}
