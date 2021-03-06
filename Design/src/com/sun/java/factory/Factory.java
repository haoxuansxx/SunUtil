package factory;

/**
 * 工厂模式
 *
 * @Author Sun
 * @date 2019-03-05
 */
public class Factory {

    /**
     * 定义：
     *      1. 工厂模式是 Java 中最常用的设计模式之一。这种类型的设计模式属于创建型模式，它提供了一种创建对象的最佳方式。
     *      2. 工厂模式主要是为创建对象提供过渡接口，以便将创建对象的具体过程屏蔽隔离起来，达到提高灵活性的目的。
     *
     * 工厂模式根据抽象程度的不同分为三种：
     *      1. 简单工厂模式（也叫静态工厂模式）
     *      2. 工厂方法模式（也叫多形性工厂）
     *      3. 抽象工厂模式（也叫工具箱）
     *
     * 简单工厂模式
     *      实质是由一个工厂类根据传入的参数，动态决定应该创建哪一个产品类（这些产品类继承自一个父类或接口）的实例。简单
     *      工厂模式的创建目标，所有创建的对象都是充当这个角色的某个具体类的实例。
     * 工厂方法模式
     *      工厂方法是粒度很小的设计模式，因为模式的表现只是一个抽象的方法。 提前定义用于创建对象的接口，让子类决定实例化
     *      具体的某一个类，即在工厂和产品中间增加接口，工厂不再负责产品的创建，由接口针对不同条件返回具体的类实例，由具体类实例去实现。
     * 抽象工厂模式
     *      当有多个抽象角色时使用的一种工厂模式。抽象工厂模式可以向客户端提供一个接口，使客户端在不必指定产品的具体的情况
     *      下，创建多个产品对象。它有多个抽象产品类，每个抽象产品类可以派生出多个具体产品类，一个抽象工厂类，可以派生出多
     *      个具体工厂类，每个具体工厂类可以创建多个具体产品类的实例。
     *
     * 工厂模式的优点：
     *      1. 一个调用者想创建一个对象，只要知道其名称就可以了，降低了耦合度。
     *      2. 扩展性高，如果想增加一个产品，只要扩展一个工厂类就可以。使得代码结构更加清晰。
     *      3. 屏蔽产品的具体实现，调用者只关心产品的接口。
     *
     * 工厂模式的缺点：
     *      每次增加一个产品时，都需要增加一个具体类和对象实现工厂（这里可以使用反射机制来避免），使得系统中类的个数成倍
     *      增加，在一定程度上增加了系统的复杂度，同时也增加了系统具体类的依赖。所以对于简单对象来说，使用工厂模式反而增加了复杂度。
     *
     * 工厂模式的适用场景：
     *      1. 一个对象拥有很多子类。
     *      2. 创建某个对象时需要进行许多额外的操作。
     *      3. 系统后期需要经常扩展，它把对象实例化的任务交由实现类完成，扩展性好。
     *
     * 总结：无论是简单工厂模式、工厂模式还是抽象工厂模式，它们本质上都是将不变的部分提取出来，将可变的部分留作接口，
     *       以达到最大程度上的复用。究竟用哪种设计模式更适合，这要根据具体的业务需求来决定。
     */
    public static void main(String[] args) {
        // 创建工厂模式对象
        Factory factory = new Factory();
        // 例1：输出结果
        factory.example();
    }

    /**
     * 工厂方法模式应该在实际中用的较多，我们以工厂方法模式举例
     */

    /**
     * 抽象的产品类：定义car  交通工具类
     */
    public interface Car {
        void gotowork();
    }

    /**
     * 定义实际的产品类，总共定义两个，bike 和bus 分别表示不同的交通工具类
     */
    public class Bike implements Car {
        @Override
        public void gotowork() {
            System.out.println("骑自行车去上班！");
        }
    }

    public class Bus implements Car {
        @Override
        public void gotowork() {
            System.out.println("坐公交车去上班！");
        }
    }

    /**
     * 定义抽象的工厂接口
     */
    public interface ICarFactory {
        Car getCar();
    }

    /**
     * 具体的工厂子类，分别为每个具体的产品类创建不同的工厂子类
     */
    public class BikeFactory implements ICarFactory {
        @Override
        public Car getCar() {
            return new Bike();
        }
    }

    public class BusFactory implements ICarFactory {
        @Override
        public Car getCar() {
            return new Bus();
        }
    }

    /**
     * 简单的测试类，来验证不同的工厂能够产生不同的产品对象
     */
    public void example() {
        ICarFactory factory = null;
        // bike
        factory = new BikeFactory();
        Car bike = factory.getCar();
        bike.gotowork();

        // bus
        factory = new BusFactory();
        Car bus = factory.getCar();
        bus.gotowork();
    }

    /**
     * 关于Java中的工厂模式的一些常见问题：
     *      利用父类的向下转型（使用父类类型的引用指向子类的对象）是可以达到类似于工厂模式的效果的，那为什么还要用工厂模式呢？
     *      把指向子类对象的父类引用赋给子类引用叫做向下转型，如：
     *      Class Student extends Person
     *      Person s = new Student();
     *      s = (Student)person;
     *      使用向下转型在客户端实例化子类的时候，严重依赖具体的子类的名字。当我们需要更改子类的构造方法的时候，比如增加一个参数，
     *      或者更改了子类的类名，所有的new出来的子类都需要跟着更改。
     *      但如果我们使用工厂模式，我们仅仅需要在工厂中修改一下new的代码，其余项目中用到此实例的都会跟着改，而不需要我们手动去操作。
     */

}
