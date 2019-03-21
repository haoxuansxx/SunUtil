package principle;

/**
 * 单一职责原则
 *
 * @Author Sun
 * @date 2019-03-19
 */
public class SingleDuty {

    /**
     * 定义：不要存在多于一个导致类变更的原因。通俗的说，即一个类只负责一项职责。
     *
     * 在职责扩散到我们无法控制的程度之前，立刻对代码进行重构。
     *
     * 遵循单一职责原的优点有：
     *      1、可以降低类的复杂度，一个类只负责一项职责，其逻辑肯定要比负责多项职责简单的多；
     *      2、提高类的可读性，提高系统的可维护性；
     *      3、变更引起的风险降低，变更是必然的，如果单一职责原则遵守的好，当修改一个功能时，可以显著降低对其他功能的影响。
     */
    public static void main(String[] args) {
        // 创建单一职责对象
        SingleDuty singleDuty = new SingleDuty();
        // 例1：输出结果
        singleDuty.example();
        // 例2：输出结果
        singleDuty.example1();
        // 例3：输出结果
        singleDuty.example2();

    }

    /**
     * 举例说明，用一个类描述动物呼吸这个场景：
     */
    class Animal {
        public void breathe(String animal) {
            System.out.println(animal + "呼吸空气");
        }
    }

    /**
     * 运行结果：
     *      牛呼吸空气
     *      羊呼吸空气
     *      猪呼吸空气
     */
    public void example() {
        Animal animal = new SingleDuty().new Animal();
        animal.breathe("牛");
        animal.breathe("羊");
        animal.breathe("猪");
    }

    /**
     * 程序上线后，发现问题了，并不是所有的动物都呼吸空气的，比如鱼就是呼吸水的。
     * 修改时如果遵循单一职责原则，需要将Animal类细分为陆生动物类Terrestrial，水生动物Aquatic，代码如下：
     */
    class Terrestrial {
        public void breathe(String animal) {
            System.out.println(animal + "呼吸空气");
        }
    }

    class Aquatic {
        public void breathe(String animal) {
            System.out.println(animal + "呼吸水");
        }
    }

    /**
     * 运行结果：
     *      牛呼吸空气
     *      羊呼吸空气
     *      猪呼吸空气
     *      鱼呼吸水
     */
    public void example1() {
        Terrestrial terrestrial = new Terrestrial();
        terrestrial.breathe("牛");
        terrestrial.breathe("羊");
        terrestrial.breathe("猪");

        Aquatic aquatic = new Aquatic();
        aquatic.breathe("鱼");
    }

    /**
     * 我们会发现如果这样修改花销是很大的，除了将原来的类分解之外，还需要修改客户端。而直接修改类Animal来达成
     * 目的虽然违背了单一职责原则，但花销却小的多，代码如下：
     */
    class Animal1 {
        public void breathe(String animal) {
            if ("鱼".equals(animal)) {
                System.out.println(animal + "呼吸水");
            } else {
                System.out.println(animal + "呼吸空气");
            }
        }
    }

    /**
     * 运行结果：
     *      牛呼吸空气
     *      羊呼吸空气
     *      猪呼吸空气
     *      鱼呼吸水
     */
    public void example2() {
        Animal1 animal = new SingleDuty().new Animal1();
        animal.breathe("牛");
        animal.breathe("羊");
        animal.breathe("猪");
        animal.breathe("鱼");
    }

    /**
     * 可以看到，这种修改方式要简单的多。但是却存在着隐患：有一天需要将鱼分为呼吸淡水的鱼和呼吸海水的鱼，
     * 则又需要修改Animal类的breathe方法，而对原有代码的修改会对调用“猪”“牛”“羊”等相关功能带来风险，
     * 也许某一天你会发现程序运行的结果变为“牛呼吸水”了。这种修改方式直接在代码级别上违背了单一职责原则，
     * 虽然修改起来最简单，但隐患却是最大的。还有一种修改方式：
     */
    class Animal2 {
        public void breathe(String animal) {
            System.out.println(animal + "呼吸空气");
        }

        public void breathe2(String animal) {
            System.out.println(animal + "呼吸水");
        }
    }

    /**
     * 运行结果：
     *      牛呼吸空气
     *      羊呼吸空气
     *      猪呼吸空气
     *      鱼呼吸水
     */
    public void example3() {
        Animal2 animal = new SingleDuty().new Animal2();
        animal.breathe("牛");
        animal.breathe("羊");
        animal.breathe("猪");
        animal.breathe2("鱼");
    }

    /**
     * 可以看到，这种修改方式没有改动原来的方法，而是在类中新加了一个方法，这样虽然也违背了单一职责原则，但在方法级别上却
     * 是符合单一职责原则的，因为它并没有动原来方法的代码。这三种方式各有优缺点，那么在实际编程中，采用哪一中呢？其实这真
     * 的比较难说，需要根据实际情况来确定。我的原则是：只有逻辑足够简单，才可以在代码级别上违反单一职责原则；只有类中方法
     * 数量足够少，才可以在方法级别上违反单一职责原则；
     * 例如本文所举的这个例子，它太简单了，它只有一个方法，所以，无论是在代码级别上违反单一职责原则，还是在方法级别上违反，
     * 都不会造成太大的影响。实际应用中的类都要复杂的多，一旦发生职责扩散而需要修改类时，除非这个类本身非常简单，否则还是
     * 遵循单一职责原则的好。
     */

}
