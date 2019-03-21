package bridging;

/**
 * 桥接模式             -- 桥接模式是一种结构式模式。
 *
 * @Author Sun
 * @date 2019-03-05
 */
public class Bridging {

    /**
     * 桥接模式 (Bridge)将抽象部分与实现部分分离，使它们都可以独立的变化。
     *
     * 应用场景
     *      1. 如果你不希望在抽象和实现部分采用固定的绑定关系，可以采用桥接模式，来把抽象和实现部分分开，
     *      2. 然后在程序运行期间来动态的设置抽象部分需要用到的具体的实现，还可以动态切换具体的实现。如果出现抽象部
     *         分和实现部分都应该可以扩展的情况，可以采用桥接模式，让抽象部分和实现部分可以
     *      3. 独立的变化，从而可以灵活的进行单独扩展，而不是搅在一起，扩展一边会影响到另一边。如果希望实现部分的修
     *         改，不会对客户产生影响，可以采用桥接模式，客户是面向抽象的接口在运行，
     *      4. 实现部分的修改，可以独立于抽象部分，也就不会对客户产生影响了，也可以说对客户是透明的。如果采用继承的
     *         实现方案，会导致产生很多子类，对于这种情况，可以考虑采用桥接模式，分析功能变化的原因，看看是否能分离
     *         成不同的纬度，然后通过桥接模式来分离它们，从而减少子类的数目。
     *
     * 要点
     *      1. 如果一个系统需要在构件的抽象化角色和具体化角色之间增加更多的灵活性，避免在两个层次之间建立静态的联系。
     *      2. 抽象化角色和具体化角色都应该可以被子类扩展。在这种情况下，桥接模式可以灵活地组合不同的抽象化角色和具体化角色，并独立化地扩展。
     *      3. 设计要求实现化角色的任何改变不应当影响客户端，或者说实现化角色的改变对客户端是完全透明的。
     */

    public static void main(String[] args) {
        // 创建桥接模式对象
        Bridging bridging = new Bridging();
        // 例1：输出结果
        bridging.example();
    }

    /**
     * Implementor : 定义实现接口。
     */
    interface Implementor {
        // 实现抽象部分需要的某些具体功能
        void operationImpl();
    }

    /**
     * Abstraction : 定义抽象接口。
     */
    abstract class Abstraction {
        // 持有一个 Implementor 对象，形成聚合关系
        protected Implementor implementor;

        public Abstraction(Implementor implementor) {
            this.implementor = implementor;
        }

        // 可能需要转调实现部分的具体实现
        public void operation() {
            implementor.operationImpl();
        }
    }

    /**
     * ConcreteImplementor :  实现 Implementor 中定义的接口。
     */
    class ConcreteImplementorA implements Implementor {
        @Override
        public void operationImpl() {
            // 真正的实现
            System.out.println("具体实现A");
        }
    }

    class ConcreteImplementorB implements Implementor {
        @Override
        public void operationImpl() {
            // 真正的实现
            System.out.println("具体实现B");
        }
    }

    /**
     * RefinedAbstraction : 扩展 Abstraction 类。
     */
    class RefinedAbstraction extends Abstraction {
        public RefinedAbstraction(Implementor implementor) {
            super(implementor);
        }

        public void otherOperation() {
            // 实现一定的功能，可能会使用具体实现部分的实现方法,
            // 但是本方法更大的可能是使用 Abstraction 中定义的方法，
            // 通过组合使用 Abstraction 中定义的方法来完成更多的功能。
        }
    }

    /**
     * 运行结果
     *      具体实现A
     */
    public void example() {
        Implementor implementor = new ConcreteImplementorA();
        RefinedAbstraction abstraction = new RefinedAbstraction(implementor);
        abstraction.operation();
        abstraction.otherOperation();
    }

}
