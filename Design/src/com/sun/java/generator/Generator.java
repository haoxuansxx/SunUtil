package generator;

/**
 * 生成器模式
 *
 * @Author Sun
 * @date 2019-03-05
 */
public class Generator {

    /**
     * 定义：将一个复杂对象的构建与它的表示分离，使得同样的构建过程可以创建不同的表示。生成器模式利用一个导演者对象和具体建造者对象一个一个地建造出所有的零件，从而建造出完整的对象。
     * <p>
     * 四个要素：
     * Builder：生成器接口，定义创建一个Product对象所需要的各个部件的操作。
     * ConcreteBuilder：具体的生成器实现，实现各个部件的创建，并负责组装Product对象的各个部件，同时还提供一个让用户获取组装完成后的产品对象的方法。
     * Director：指导者，也被称导向者，主要用来使用Builder接口，以一个统一的过程来构建所需要的Product对象。
     * Product：产品，表示被生成器构建的复杂对象，包含多个部件。
     * <p>
     * 优点：
     * 1. 使用生成器模式可以使客户端不必知道产品内部组成的细节。
     * 2. 具体的建造者类之间是相互独立的，对系统的扩展非常有利。
     * 3. 由于具体的建造者是独立的，因此可以对建造过程逐步细化，而不对其他的模块产生任何影响。
     * 缺点：
     * 建造者模式的“加工工艺”是暴露的，这样使得建造者模式更加灵活，也使得工艺变得对客户不透明。（待考证，笔者这里不是很理解，欢迎说自己的见解）
     * 应用场景：
     * 1. 需要生成一个产品对象有复杂的内部结构。每一个内部成分本身可以是对象，也可以使一个对象的一个组成部分。
     * 2. 需要生成的产品对象的属性相互依赖。建造模式可以强制实行一种分步骤进行的建造过程。
     * 3. 在对象创建过程中会使用到系统中的其他一些对象，这些对象在产品对象的创建过程中不易得到
     */
    public static void main(String[] args) {
        // 创建生成器模式对象
        Generator generator = new Generator();
        // 例1：输出结果
        generator.example();
    }

    /**
     * 网上有用KFC的例子来描述生成器模式，比较通俗易懂。
     * 假设KFC推出两种套餐：奥尔良鸡腿堡套餐和香辣鸡腿堡套餐。
     * 奥尔良套餐包括：一个奥尔良鸡腿堡、一个炸鸡翅、一杯雪碧。
     * 鸡腿堡套餐包括：一个香辣鸡腿堡、一份薯条、一杯可乐。
     * 每份套餐都是：主食、副食、饮料。
     *
     * KFC服务员要根据顾客的要求来提供套餐，那这个需求里面什么是固定的，什么是变化的呢？很明显顾客都是要的套餐，顾客的
     * 目的是一样的。 套餐里面都是主食、副食、饮料，这也是固定的。至于主食是什么、副食是什么、饮料是什么，这个是变化的。
     * 在实际的软件开发过程中，有时候面临着“一个复杂对象”的创建工作，其通常由各个部分的子对象采用一定的组合构成，
     *
     * 由于需求的变化，这个复杂对象的各个部分或者其子对象经常要变化（例如，鸡腿堡套餐的顾客不喜欢可乐，要换奶茶），
     * 但是他们的结构却相对稳定（套餐都得是一份主食，副食及饮料）。当遇到这种场景时，使用生成器模式比较合适。
     */

    /**
     * 定义一个产品类：
     */
    public class Entity1 {
    }

    public class Entity2 {
    }

    public class Entity3 {
    }

    public class Product {
        Entity1 entity1;
        Entity2 entity2;
        Entity3 entity3;
    }

    /**
     * 产品类中的各个小模块是不一样的，由他们建造组成产品。
     * 根据具体场景要求，定义n个生成器类：
     */
    public interface IBuild {
        public void createEntity1();

        public void createEntity2();

        public void createEntity3();

        public Product composite();

        public Product create();
    }

    public class BuildProduct implements IBuild {
        Product p = new Product();

        @Override
        public void createEntity1() {
            //p.entity1 = ...
        }

        @Override
        public void createEntity2() {
            //p.entity2 = ...
        }

        @Override
        public void createEntity3() {
            //p.entity3 = ...
        }

        @Override
        public Product composite() {
            //p.composite = ...
            return this.p;
        }

        @Override
        public Product create() {
            return composite();
        }
    }

    public class BuildProduct1 implements IBuild {
        Product p = new Product();

        @Override
        public void createEntity1() {
            //p.entity1 = ...
        }

        @Override
        public void createEntity2() {
            //p.entity2 = ...
        }

        @Override
        public void createEntity3() {
            //p.entity3 = ...
        }

        @Override
        public Product composite() {
            //p.composite = ...
            return this.p;
        }

        @Override
        public Product create() {
            return composite();
        }

    }

    /**
     * 定义一个指挥者类，统一调度project：
     */
    public class Director {
        private IBuild build;

        public Director(IBuild build) {
            this.build = build;
        }

        public Product build() {
            return build.create();
        }

    }

    public void example() {
        IBuild build = new BuildProduct();
        Director director = new Director(build);
        Product p = director.build();
    }

}
