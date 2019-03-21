package appearance;

/**
 * 外观模式
 *
 * @Author Sun
 * @date 2019-03-05
 */
public class Appearance {

    /**
     * 定义：为子系统中的一组接口提供一个一致的界面，Facade模式定义了一个高层接口，这个接口使得这一子系统更加容易使用。
     *
     * 优点：
     *      实现了子系统与客户端之间的松耦合关系。
     *      客户端屏蔽了子系统组件，减少了客户端所需处理的对象数目，并使得子系统使用起来更加容易。
     *
     * 适用场景：
     *      设计初期阶段，应该有意识的将不同层分离，层与层之间建立外观模式。
     *      开发阶段，子系统越来越复杂，增加外观模式提供一个简单的调用接口。
     *      维护一个大型遗留系统的时候，可能这个系统已经非常难以维护和扩展，但又包含非常重要的功能，为其开发一个外观类，以便新系统与其交互。
     *
     * 外观模式总结：
     *      1. 外观模式为复杂子系统提供了一个简单接口，并不为子系统添加新的功能和行为。
     *      2. 外观模式实现了子系统与客户端之间的松耦合关系。
     *      3. 外观模式没有封装子系统的类，只是提供了简单的接口。 如果应用需要，它并不限制客户使用子系统类。因此可以
     *         灵活的在系统易用性与通用性之间选择。
     *      4. 外观模式注重的是简化接口，它更多的时候是从架构的层次去看整个系统，而并非单个类的层次。
     */

    public static void main(String[] args) {
        // 创建外观模式对象
        Appearance appearance = new Appearance();
        // 例1：输出结果
        appearance.example();
    }

    /**
     * 1. 外观(Facade)角色 ：客户端可以调用这个角色的方法。此角色知晓相关子系统的功能和责任。在正常情况下，本角色
     *                       会将所有从客户端发来的请求委派到相应的子系统去。
     *
     * 2. 子系统(SubSystem)角色 ：可以同时有一个或者多个子系统。每个子系统都不是一个单独的类，而是一个类的集合。每
     *                            个子系统都可以被客户端直接调用，或者被外观角色调用。子系统并不知道外观角色的存在，
     *                            对于子系统而言，外观角色仅仅是另外一个客户端而已。
     */

    /**
     *
     * 1. 子系统角色，由若干个类组成
     */
    public class SubClass1 {
        public void method1() {
            System.out.println("这是子系统类1中的方法1");
        }

        public void method2() {
            System.out.println("这是子系统类1中的方法2");
        }
    }

    public class SubClass2 {
        public void method1() {
            System.out.println("这是子系统类2中的方法1");
        }

        public void method2() {
            System.out.println("这是子系统类2中的方法2");
        }
    }

    public class SubClass3 {
        public void method1() {
            System.out.println("这是子系统类3中的方法1");
        }

        public void method2() {
            System.out.println("这是子系统类3中的方法2");
        }
    }

    /**
     * 2. 外观角色类
     */
    public class FacadeClass {
        public void FacadeMethod() {
            SubClass1 s1 = new SubClass1();
            s1.method1();
            SubClass2 s2 = new SubClass2();
            s2.method1();
            SubClass3 s3 = new SubClass3();
            s3.method1();
        }
    }

    /**
     * 3. 客户端测试方法
     */
    public void example() {
        FacadeClass fc = new FacadeClass();
        fc.FacadeMethod();
    }

    /**
     * Facade类其实相当于子系统中SubClass类的外观界面，有了这个Facade类，那么客户端就不需要亲自调用子系统中的那些具
     * 体实现的子类了，也不需要知道系统内部的实现细节，甚至都不需要知道这些子类的存在，客户端只需要跟Facade类交互就
     * 好了，从而更好地实现了客户端和子系统中具体类的解耦，让客户端更容易地使用系统。
     *
     * 同时，这样定义一个Facade类可以有效地屏蔽内部的细节，免得客户端去调用Module类时，发现一些不需要它知道的方法。
     * 如上代码，我的所有子类中的方法二都是方法一调用的方法，是配合方法一的，他们不需要被客户端调用，而且具有一定的
     * 保密性，这样使用外观模式时就可以不被客户端知道。
     */

}
