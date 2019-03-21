package proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 代理模式
 *
 * @Author Sun
 * @date 2019-03-05
 */
public class Proxy {

    /**
     * 定义： 为其他对象提供一种代理以控制对这个对象的访问。在某些情况下，一个对象不适合或者不能直接引用另一个对象，而代理对象可以
     *        在客户端和目标对象之间起到中介的作用
     *
     * 角色：
     *      1. 抽象角色：声明真实对象和代理对象的共同接口。
     *      2. 代理角色：代理对象角色内部含有对真实对象的引用，从而可以操作真实对象，同时代理对象提供与真实对象相同的接口以便在任何
     *         时刻都能代替真实对象。同时，代理对象可以在执行真实对象操作时，附加其他的操作，相当于对真实对象进行封装。
     *      3. 真实角色：代理角色所代表的真实对象，是我们最终要引用的对象。
     *
     * 优点：
     *      1. 业务类只需要关注业务逻辑本身，保证了业务类的重用性。这是代理的共有优点。
     *      2. 能够协调调用者和被调用者，在一定程度上降低了系统的耦合度。
     *
     * 缺点：
     *      1. 由于在客户端和真实主题之间增加了代理对象，因此有些类型的代理模式可能会造成请求的处理速度变慢，例如保护代理。
     *      2. 实现代理模式需要额外的工作，而且有些代理模式的实现过程较为复杂，例如远程代理。
     */
    public static void main(String[] args) {
        // 创建代理模式对象
        Proxy proxy = new Proxy();
        System.out.println("--------------------- 我是分割线 ------------------------");
        // 例1：静态代理输出结果
        proxy.example();
        System.out.println("--------------------- 我是分割线 ------------------------");
        // 例2：动态代理输出结果
        proxy.example1();
    }

    /**
     * 静态代理：静态代理也就是在程序运行前就已经存在代理类的字节码文件，代理类和委托类的关系在运行前就确定了。
     */

    /**
     * 抽象角色，真实对象和代理对象共同的接口
     */
    public interface UserInfo {
        void queryUser();

        void updateUser();
    }

    /**
     * 真实角色
     */
    public class UserImpl implements UserInfo {
        @Override
        public void queryUser() {
            //查询方法略...
        }

        @Override
        public void updateUser() {
            //修改方法略...
        }
    }

    /**
     * 代理角色
     */
    public class UserProxy implements UserInfo {
        private UserInfo userImpl;

        public UserProxy(UserInfo userImpl) {
            this.userImpl = userImpl;
        }

        @Override
        public void queryUser() {
            //这里可以扩展，增加一些查询之前需要执行的方法
            //查询方法略...
            //这里可以扩展，增加一些查询之后需要执行的方法
        }

        @Override
        public void updateUser() {
            //这里可以扩展，增加一些修改之前需要执行的方法
            //修改方法略...
            //这里可以扩展，增加一些修改之后需要执行的方法
        }
    }

    /**
     * 使用代理之后如何调用他的方法？
     */
    public void example() {
        UserInfo userImpl = new UserImpl();
        UserInfo userProxy = new UserProxy(userImpl);
        userProxy.queryUser();
        userProxy.updateUser();
    }

    /**
     * 动态代理：动态代理类的源码是程序在运行期间由JVM根据反射等机制动态生成的，所以不存在代理类的字节码文件。代理角色和真实
     *           角色的联系在程序运行时确定。
     */

    /**
     * 抽象角色，真实对象和代理对象共同的接口
     */
    public interface UserInfo1 {
        void queryUser();

        void updateUser();
    }

    /**
     * 真实角色
     */
    public class UserImpl1 implements UserInfo1 {
        @Override
        public void queryUser() {
            //查询方法略...
        }

        @Override
        public void updateUser() {
            //修改方法略...
        }
    }

    /**
     * 代理角色处理器：
     */
    public class UserHandler implements InvocationHandler {
        private UserInfo1 userImpl;

        public UserHandler(UserInfo1 userImpl2) {
            this.userImpl = userImpl2;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args)
                throws Throwable {
            Object object = null;
            //方法开始前做一些事情
            if (method.getName().equals("queryUser")) {
                object = method.invoke(userImpl, args);
                //激活调用的方法
            }
            //方法结束后做一些事情
            return object;
        }
    }

    /**
     * 如何调用（和静态代理略有不同）
     */
    public void example1() {
        UserInfo1 userImpl = new UserImpl1();
        UserHandler handler = new UserHandler(userImpl);
        UserInfo userProxy = (UserInfo) java.lang.reflect.Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(), new Class[]{UserInfo.class}, handler);
        userProxy.queryUser();
    }

}
