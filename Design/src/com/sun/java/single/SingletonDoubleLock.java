package single;

/**
 * 双重锁的形式单例
 *
 * @Author Sun
 * @date 2019-03-05
 */
public class SingletonDoubleLock {

    private static volatile SingletonDoubleLock instance = null;

    private SingletonDoubleLock() {
        //do something
    }

    public static SingletonDoubleLock getInstance() {
        if (instance == null) {
            synchronized (SingletonDoubleLock.class) {
                if (instance == null) {
                    instance = new SingletonDoubleLock();
                }
            }
        }
        return instance;
    }

}
