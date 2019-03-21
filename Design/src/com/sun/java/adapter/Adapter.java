package adapter;

/**
 * 适配器模式
 *
 * @Author Sun
 * @date 2019-03-05
 */
public class Adapter {

    /**
     * 将一个类的接口转换成客户希望的另外一个接口。适配器模式使得原本由于接口不兼容而不能一起工作的那些类可以一起工作。
     *
     * 优点：
     *      1. 复用性：系统需要使用现有的类，而此类的接口不符合系统的需要。那么通过适配器模式就可以让这些功能得到更好的复用。
     *      2. 扩展性：在实现适配器功能的时候，可以自由调用自己开发的功能，从而自然地扩展系统的功能。
     * 缺点：
     *     1. 过多的使用适配器，会让系统非常零乱，不易整体进行把握。比如，明明看到调用的是A接口，其实内部被适配成了B接口
     *        的实现。
     *
     * 适用场景：
     *      1. 已经存在的类的接口不符合我们的需求；
     *      2. 创建一个可以复用的类，使得该类可以与其他不相关的类或不可预见的类协同工作；
     *      3. 使用一些已经存在的子类而不需要对其进行子类化来匹配接口。
     *      4. 旧的系统开发的类已经实现了一些功能，但是客户端却只能以另外接口的形式访问，但我们不希望手动更改原有类的时候。
     *
     * 小结：适配器模式不适合在详细设计阶段使用它，它是一种补偿模式，专用来在系统后期扩展、修改时所用，适配器模式更像是一种补救措施。
     */

    public static void main(String[] args) {
        // 创建适配器模式对象
        Adapter adapter = new Adapter();
        // 例1：输出结果
        adapter.example();
        // 例2：输出结果
        adapter.example1();
    }

    /**
     *
     * 目标(Target)角色：  这就是所期待得到的接口，也就是这类的接口是符合我们要求的。
     * 源(Adapee)角色：    我们要使用的接口，但是这个接口不符合我们的要求，也就是现在需要适配的接口。
     * 适配器(Adaper)角色：适配器类是适配器模式的核心。适配器把源接口转换成目标接口。显然，这一角色不可以是接口，而必须是具体类。
     */

    /**
     * 1. 类适配器模式
     */
    class Adaptee {
        public void specificRequest() {
            System.out.println("特殊请求，这个是源角色");
        }
    }

    /**
     * 这个是目标角色，所期待的接口
     */
    interface Target {
        public void request();
    }

    /**
     * 现在想要实现这个Target接口，但是不想重构，想要用上已有的Adaptee类，这时可以定义一个适配器类，继承想要使用的类，并且实现期待的接口。
     */
    class Adapter1 extends Adaptee implements Target {
        @Override
        public void request() {
            super.specificRequest();
        }
    }

    /**
     * 这样，使用适配器类和实现目标接口就完成了计划，测试：
     */
    public void example() {
        //使用特殊功能类，即适配类
        Target adapter = new Adapter1();
        adapter.request();
    }

    /**
     * 2. 对象适配器模式
     * 适配器类关联已有的Adaptee类，并且实现标准接口，这样做的好处是不再需要继承。
     */
    class Adapter2 implements Target {
        private Adaptee adaptee;

        public Adapter2(Adaptee adaptee) {
            this.adaptee = adaptee;
        }

        @Override
        public void request() {
            this.adaptee.specificRequest();
        }
    }

    /**
     * 我们可以想到，此时输出结果和类适配器模式是相同的，测试：
     */
    public void example1() {
        Target adapter = new Adapter2(new Adaptee());
        adapter.request();
    }

    /**
     * 区别：对象的适配器模式不是使用继承关系连接到Adaptee类，而是使用委派关系连接到Adaptee类。
     */

}
