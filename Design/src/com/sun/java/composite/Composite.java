package composite;

import java.util.ArrayList;
import java.util.List;

/**
 * 组合模式
 *
 * @Author Sun
 * @date 2019-03-05
 */
public class Composite {

    /**
     * 组合模式又叫做部分-整体模式,它使我们树型结构的问题中,模糊了简单元素和复杂元素的概念,客户程序可以向处理简单元素
     * 一样来处理复杂元素,从而使得客户程序与复杂元素的内部结构解藕.
     *
     * 组合模式可以优化处理递归或分级数据结构.有许多关于分级数据结构的例子,使得组合模式非常有用武之地.
     *
     * 使用场景
     *      1. 你想表示对象的部分-整体层次结构
     *      2. 你希望用户忽略组合对象与单个对象的不同，用户将统一地使用组合结构中的所有对象。
     *
     * 总结
     *      1. 组合模式解耦了客户程序与复杂元素内部结构，从而使客户程序可以向处理简单元素一样来处理复杂元素。
     *      2. 如果你想要创建层次结构，并可以在其中以相同的方式对待所有元素，那么组合模式就是最理想的选择。
     *      3. 例子来举例说明了组合模式的用途。在这个例子中，文件和目录都执行相同的接口，这是组合模式的关键。
     *         通过执行相同的接口，你就可以用相同的方式对待文件和目录，从而实现将文件或者目录储存为目录的子级元素。
     */

    public static void main(String[] args) {
        // 创建组合模式对象
        Composite composite = new Composite();
        // 例1：输出结果
        composite.example();
    }

    /**
     * Component: 为参加组合的对象声明一个公共接口, 不管是组合还是叶结点.
     */
    public abstract class FolderComponent {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(final String name) {
            this.name = name;
        }

        public FolderComponent() {
        }

        public FolderComponent(final String name) {
            this.name = name;
        }

        public abstract void add(FolderComponent component);

        public abstract void remove(FolderComponent component);

        public abstract void display();
    }

    /**
     * Leaf: 在组合中表示叶子结点对象,叶子结点没有子结点.
     */
    public class FileLeaf extends FolderComponent {
        public FileLeaf(final String name) {
            super(name);
        }

        @Override
        public void add(final FolderComponent component) {
            // ...
        }

        @Override
        public void remove(final FolderComponent component) {
            // ...
        }

        @Override
        public void display() {
            System.out.println("FileLeaf:" + this.getName());
        }
    }

    /**
     * Composite: 表示参加组合的有子对象的对象, 并给出树枝购件的行为.
     */
    public class FolderComposite extends FolderComponent {
        private final List<FolderComponent> components;

        public FolderComposite(final String name) {
            super(name);
            this.components = new ArrayList<>();
        }

        public FolderComposite() {
            this.components = new ArrayList<>();
        }

        @Override
        public void add(final FolderComponent component) {
            this.components.add(component);
        }

        @Override
        public void remove(final FolderComponent component) {
            this.components.remove(component);
        }

        @Override
        public void display() {
            System.out.println("FolderComposite---name:" + this.getName());
            for (final FolderComponent component : components) {
                System.out.println("FolderComposite---component-name:" + component.getName());
            }
        }
    }

    /**
     * 输出结果：
     *      FileLeaf:runnable file
     *      FolderComposite---name:new folder
     *      FolderComposite---component-name:content1 in new folder
     *      FolderComposite---component-name:content2 in new folder
     */
    public void example() {
        final FolderComponent leaf = new FileLeaf("runnable file");
        leaf.display();
        final FolderComponent folder = new FolderComposite("new folder");
        folder.add(new FileLeaf("content1 in new folder"));
        folder.add(new FileLeaf("content2 in new folder"));
        folder.display();
    }

}
