package state;

/**
 * 状态模式
 *
 * @Author Sun
 * @date 2019-03-05
 */
public class State {

    /**
     * 定义：当系统中某个对象存在多个状态，这些状态之间可以进行转换，而且对象在不同状态下行为不相同时可以使用状态模式。
     *       状态模式将一个对象的状态从该对象中分离出来，封装到专门的状态类中，使得对象状态可以灵活变化。状态模式是一种对象行为型模式。
     *
     * 适用场景
     *      用于解决系统中复杂对象的多种状态转换以及不同状态下行为的封装问题。简单说就是处理对象的多种状态及其相互转换。
     *
     * 结构
     *      1. AbstractState(抽象状态类):在抽象状态类中定义申明了不同状态下的行为抽象方法，而由子类(不同的状态子类)中实现不同的行为操作。
     *      2. ConcreteState(实现具体状态下行为的状态子类):抽象状态类的子类，每一个子类实现一个与环境类(Context)的一个状态相关的行为，
     *         每一个具体的状态类对应环境的一种具体状态，不同的具体状态其行为有所不同。
     *      3. Context(拥有状态对象的环境类):拥有状态属性，因环境的多样性，它可拥有不同的状态，且在不同状态下行为也不一样。在环境类中
     *         维护一个抽象的状态实例，这个实例定义当前环境的状态(setState()方法)，而将具体的状态行为分离出来由不同的状态子类去完成。
     */
    public static void main(String[] args) {
        // 创建状态模式对象
        State state = new State();
        // 例1：输出结果
        state.example();
    }

    /**
     * 抽象状态类：
     */
    public abstract class State1 {

        /* 状态行为抽象方法,由具体的状态子类去实现不同的行为逻辑 */
        public abstract void Behavior();

    }

    /**
     * 具体的状态子类A
     */
    public class ConcreteStateA extends State1 {
        @Override
        public void Behavior() {
            // 状态A的业务行为, 及当为该状态下时，能干什么
            // 如：手机在未欠费停机状态下, 能正常拨打电话
            System.out.println("手机在未欠费停机状态下, 能正常拨打电话");
        }

    }

    /**
     * 具体的状态子类B
     */
    public class ConcreteStateB extends State1 {
        @Override
        public void Behavior() {
            // 状态B的业务行为, 及当为该状态下时，能干什么
            // 如：手机在欠费停机状态下, 不能拨打电话
            System.out.println("手机在欠费停机状态下, 不能拨打电话");
        }

        public ConcreteStateB() {

        }

        public ConcreteStateB(Context b) {

        }

    }

    /**
     * 环境/上下文类
     * 拥有状态对象，且可以完成状态间的转换 [状态的改变/切换 在环境类中实现]
     */
    public class Context {
        // 维护一个抽象状态对象的引用
        private State1 state;

        /*
         * 模拟手机的话费属性
         * 环境状态如下：
         * 1、当  bill >= 0.00$ : 状态正常   还能拨打电话
         * 2、当  bill < 0.00$ : 手机欠费   不能拨打电话
         */
        private double bill;

        /*
         * 环境处理函数，调用状态实例行为完成业务逻辑
         * 根据不同的状态实例引用在不同状态下处理不同的行为
         */
        public void Handle() {
            checkState();
            state.Behavior();
        }


        /*
         * 检查环境状态:状态的改变/切换 在环境类中实现
         */
        private void checkState() {
            if (bill >= 0.00) {
                setState(new ConcreteStateA());
            } else {
                setState(new ConcreteStateB());
            }
        }

        /*
         * 设置环境状态
         * 私有方法，目的是让环境的状态由系统环境自身来控制/切换,外部使用者无需关心环境内部的状态
         * @param state
         */
        private void setState(State1 state) {
            this.state = state;
        }


        public double getBill() {
            return bill;
        }

        public void setBill(double bill) {
            this.bill = bill;
        }
    }

    /**
     * 程序运行结果：
     *      当前话费余额：5.5$
     *      手机在未欠费停机状态下, 能正常拨打电话
     *      当前话费余额：-1.5$
     *      手机在欠费停机状态下, 不能拨打电话
     *      当前话费余额：50.0$
     *      手机在未欠费停机状态下, 能正常拨打电话
     */
    public void example() {
        Context context = new Context();
        context.setBill(5.50);
        System.out.println("当前话费余额：" + context.getBill() + "$");
        context.Handle();

        context.setBill(-1.50);
        System.out.println("当前话费余额：" + context.getBill() + "$");
        context.Handle();

        context.setBill(50.00);
        System.out.println("当前话费余额：" + context.getBill() + "$");
        context.Handle();
    }

    /**
     * 扩展:状态模式中关于状态的切换有两种不同的实现方式
     *
     * 方式一：状态的改变/切换  在环境类中实现。  如上面的用例代码Context类中的checkState()方法。
     *
     * 方式二：状态的改变/切换 在具体的状态子类中实现。实现步骤如下：
     *      1. 在环境类Context类中初始化一个状态实例对象，并将环境Context对象作为子类状态的构造参数传递到具体的状态子类实例中。
     *         如在Context.java类中，设置初始状态：this.state = new ConcreteStateA(this);
     *      2. 在具体的子类状态类中根据构造进来的context对象，通过调用context对象的属性值进行业务逻辑判断进行状态的检查和切换。
     */

    /**
     * 如在 具体的状态子类ConcreteStateA.java类中：
     */
    public class ConcreteStateA1 extends State1 {
        private Context ctx;

        public ConcreteStateA1(Context context) {
            ctx = context;
        }

        @Override
        public void Behavior() {
            // 状态A 的业务行为, 及当为该状态下时，能干什么
            // 如：手机在未欠费停机状态下, 能正常拨打电话
            System.out.println("手机在未欠费停机状态下, 能正常拨打电话");
            checkState();

        }

        /**
         * 检查状态 是否需要进行状态的转换
         * 状态的切换由具体状态子类中实现
         */
        private void checkState() {
            if (ctx.getBill() < 0.00) {
                ctx.setState(new ConcreteStateB(ctx));
            }
        }
    }

}
