package intermediary;

/**
 * 中介者模式
 *
 * @Author Sun
 * @date 2019-03-05
 */
public class Intermediary {

    /**
     * 定义：用一个中介者对象封装一系列的对象交互，中介者使各对象不需要显示地相互作用，从而使耦合松散，而且可以独立地改变它们之间的交互。
     *
     * 中介者模式的结构，中介者模式又称为调停者模式，共分为3部分：
     *      抽象中介者：定义好同事类对象到中介者对象的接口，用于各个同事类之间的通信。一般包括一个或几个抽象的事件方法，并由子类去实现。
     *      中介者实现类：从抽象中介者继承而来，实现抽象中介者中定义的事件方法。从一个同事类接收消息，然后通过消息影响其他同时类。
     *      同事类：如果一个对象会影响其他的对象，同时也会被其他对象影响，那么这两个对象称为同事类。在类图中，同事类只有一个，这其实
     *              是现实的省略，在实际应用中，同事类一般由多个组成，他们之间相互影响，相互依赖。同事类越多，关系越复杂。并且，
     *              同事类也可以表现为继承了同一个抽象类的一组实现组成。在中介者模式中，同事类之间必须通过中介者才能进行消息传递。
     *
     * 为什么要使用中介者模式，一般来说，同事类之间的关系是比较复杂的，多个同事类之间互相关联时，他们之间的关系会呈现为复杂的网状结构，
     * 这是一种过度耦合的架构，即不利于类的复用，也不稳定。
     *
     * 如果引入中介者模式，那么同事类之间的关系将变为星型结构，任何一个类的变动，只会影响的类本身，以及中介者，这样就减小
     * 了系统的耦合。一个好的设计，必定不会把所有的对象关系处理逻辑封装在本类中，而是使用一个专门的类来管理那些不属于自己的行为。
     *
     * 中介者模式的优点
     *      1. 适当地使用中介者模式可以避免同事类之间的过度耦合，使得各同事类之间可以相对独立地使用。
     *      2. 使用中介者模式可以将对象间一对多的关联转变为一对一的关联，使对象间的关系易于理解和维护。
     *      3. 使用中介者模式可以将对象的行为和协作进行抽象，能够比较灵活的处理对象间的相互作用。
     *
     * 适用场景
     *      1. 在面向对象编程中，一个类必然会与其他的类发生依赖关系，完全独立的类是没有意义的。一个类同时依赖多个类的情况也相当普遍，
     *         既然存在这样的情况，说明，一对多的依赖关系有它的合理性，适当的使用中介者模式可以使原本凌乱的对象关系清晰，但是如果滥用，
     *         则可能会带来反的效果。一般来说，只有对于那种同事类之间是网状结构的关系，才会考虑使用中介者模式。可以将网状结构变为星状结构，
     *         使同事类之间的关系变的清晰一些。
     *      2. 中介者模式是一种比较常用的模式，也是一种比较容易被滥用的模式。对于大多数的情况，同事类之间的关系不会复杂到混乱不堪
     *         的网状结构，因此，大多数情况下，将对象间的依赖关系封装的同事类内部就可以的，没有必要非引入中介者模式。滥用中介者模
     *         式，只会让事情变的更复杂。
     */
    public static void main(String[] args) {
        // 创建中介者模式对象
        Intermediary intermediary = new Intermediary();
        System.out.println("--------------------- 我是分割线 ------------------------");
        // 例1：输出结果
        intermediary.example();
        System.out.println("--------------------- 我是分割线 ------------------------");
        // 例2：输出结果
        intermediary.example1();
    }

    /**
     * 我们使用一个例子来说明一下什么是同事类：有两个类A和B，类中各有一个数字，并且要保证类B中的数字永远是类A中数字的100倍。
     * 也就是说，当修改类A的数时，将这个数字乘以100赋给类B，而修改类B时，要将数除以100赋给类A。类A类B互相影响，就称为同事类。
     * 代码如下：
     */

    abstract class AbstractColleague {
        protected int number;

        public int getNumber() {
            return number;
        }

        public void setNumber(int number) {
            this.number = number;
        }

        /* 抽象方法，修改数字时同时修改关联对象 */
        public abstract void setNumber(int number, AbstractColleague coll);
    }

    class ColleagueA extends AbstractColleague {
        public void setNumber(int number, AbstractColleague coll) {
            this.number = number;
            coll.setNumber(number * 100);
        }
    }

    class ColleagueB extends AbstractColleague {

        public void setNumber(int number, AbstractColleague coll) {
            this.number = number;
            coll.setNumber(number / 100);
        }
    }

    public void example() {
        AbstractColleague collA = new ColleagueA();
        AbstractColleague collB = new ColleagueB();

        System.out.println("==========设置A影响B==========");
        collA.setNumber(1288, collB);
        System.out.println("collA的number值：" + collA.getNumber());
        System.out.println("collB的number值：" + collB.getNumber());

        System.out.println("==========设置B影响A==========");
        collB.setNumber(87635, collA);
        System.out.println("collB的number值：" + collB.getNumber());
        System.out.println("collA的number值：" + collA.getNumber());
    }

    /**
     * 上面的代码中，类A类B通过直接的关联发生关系，假如我们要使用中介者模式，类A类B之间则不可以直接关联，他们之间必须
     * 要通过一个中介者来达到关联的目的。
     *
     * 下面的代码其实就是把原来处理对象关系的代码重新封装到一个中介类中，通过这个中介类来处理对象间的关系。
     */
    abstract class AbstractColleague1 {
        protected int number;

        public int getNumber() {
            return number;
        }

        public void setNumber(int number) {
            this.number = number;
        }

        //注意这里的参数不再是同事类，而是一个中介者
        public abstract void setNumber(int number, AbstractMediator am);
    }

    class ColleagueA1 extends AbstractColleague1 {

        public void setNumber(int number, AbstractMediator am) {
            this.number = number;
            am.AaffectB();
        }
    }

    class ColleagueB1 extends AbstractColleague1 {

        @Override
        public void setNumber(int number, AbstractMediator am) {
            this.number = number;
            am.BaffectA();
        }
    }

    abstract class AbstractMediator {
        protected AbstractColleague1 A;
        protected AbstractColleague1 B;

        public AbstractMediator(AbstractColleague1 a, AbstractColleague1 b) {
            A = a;
            B = b;
        }

        public abstract void AaffectB();

        public abstract void BaffectA();

    }

    class Mediator extends AbstractMediator {

        public Mediator(AbstractColleague1 a, AbstractColleague1 b) {
            super(a, b);
        }

        //处理A对B的影响
        public void AaffectB() {
            int number = A.getNumber();
            B.setNumber(number * 100);
        }

        //处理B对A的影响
        public void BaffectA() {
            int number = B.getNumber();
            A.setNumber(number / 100);
        }
    }

    public void example1() {
        AbstractColleague1 collA = new ColleagueA1();
        AbstractColleague1 collB = new ColleagueB1();

        AbstractMediator am = new Mediator(collA, collB);

        System.out.println("==========通过设置A影响B==========");
        collA.setNumber(1000, am);
        System.out.println("collA的number值为：" + collA.getNumber());
        System.out.println("collB的number值为A的10倍：" + collB.getNumber());

        System.out.println("==========通过设置B影响A==========");
        collB.setNumber(1000, am);
        System.out.println("collB的number值为：" + collB.getNumber());
        System.out.println("collA的number值为B的0.1倍：" + collA.getNumber());

    }

}
