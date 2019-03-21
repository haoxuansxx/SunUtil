package memorandum;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * 备忘录模式
 *
 * @Author Sun
 * @date 2019-03-05
 */
public class Memorandum {

    /**
     * 定义：在不破坏封装性的前提下，捕获一个对象的内部状态，并在该对象之外保存这个状态。这样就可以将该对象恢复到原先保存的状态。
     *
     * 备忘录模式的结构
     *      发起人：记录当前时刻的内部状态，负责定义哪些属于备份范围的状态，负责创建和恢复备忘录数据。
     *      备忘录：负责存储发起人对象的内部状态，在需要的时候提供发起人需要的内部状态。
     *      管理角色：对备忘录进行管理，保存和提供备忘录。
     *
     * 备忘录模式的优点有：
     *         1. 当发起人角色中的状态改变时，有可能这是个错误的改变，我们使用备忘录模式就可以把这个错误的改变还原。
     *         2. 备份的状态是保存在发起人角色之外的，这样，发起人角色就不需要对各个备份的状态进行管理。
     * 备忘录模式的缺点有：
     *         1. 在实际应用中，备忘录模式都是多状态和多备份的，发起人角色的状态需要存储到备忘录对象中，对资源的消耗是比较严重的。
     *         2. 如果有需要提供回滚操作的需求，使用备忘录模式非常适合，比如jdbc的事务操作，文本编辑器的Ctrl+Z恢复等。
     */
    public static void main(String[] args) {
        // 创建备忘录模式对象
        Memorandum memorandum = new Memorandum();
        System.out.println("--------------------- 我是分割线 ------------------------");
        // 例1：通用代码输出结果
        memorandum.example();
        System.out.println("--------------------- 我是分割线 ------------------------");
        // 例2：多状态多备份输出结果
        memorandum.example1();
    }

    /**
     * 我们在编程的时候，经常需要保存对象的中间状态，当需要的时候，可以恢复到这个状态。比如，我们使用Eclipse进行编程时，
     * 假如编写失误（例如不小心误删除了几行代码），我们希望返回删除前的状态，便可以使用Ctrl+Z来进行返回。这时我们便可以
     * 使用备忘录模式来实现。下面是通用代码实现：
     */
    class Originator {
        private String state = "";

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public Memento createMemento() {
            return new Memento(state);
        }

        public void restoreMemento(Memento memento) {
            this.setState(memento.getState());
        }
    }

    class Memento {
        private String state = "";

        public Memento(String state) {
            this.state = state;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }
    }

    class Caretaker {
        private Memento memento;

        public Memento getMemento() {
            return memento;
        }

        public void setMemento(Memento memento) {
            this.memento = memento;
        }
    }

    public void example() {
        Originator originator = new Originator();
        originator.setState("状态1");
        System.out.println("初始状态:" + originator.getState());
        Caretaker caretaker = new Caretaker();
        caretaker.setMemento(originator.createMemento());
        originator.setState("状态2");
        System.out.println("改变后状态:" + originator.getState());
        originator.restoreMemento(caretaker.getMemento());
        System.out.println("恢复后状态:" + originator.getState());
    }

    /**
     * 代码演示了一个单状态单备份的例子，逻辑非常简单：Originator类中的state变量需要备份，以便在需要的时候恢复；Memento类
     * 中，也有一个state变量，用来存储Originator类中state变量的临时状态；而Caretaker类就是用来管理备忘录类的，用来向备忘录
     * 对象中写入状态或者取回状态。
     */

    /**
     * 多状态多备份备忘录
     *
     * 通用代码演示的例子中，Originator类只有一个state变量需要备份，而通常情况下，发起人角色通常是一个javaBean，对象中需
     * 要备份的变量不止一个，需要备份的状态也不止一个，这就是多状态多备份备忘录。
     *
     * 实现备忘录的方法很多，备忘录模式有很多变形和处理方式，像通用代码那样的方式一般不会用到，多数情况下的备忘录模式，
     * 是多状态多备份的。其实实现多状态多备份也很简单，最常用的方法是，我们在Memento中增加一个Map容器来存储所有的状态，
     * 在Caretaker类中同样使用一个Map容器才存储所有的备份。下面我们给出一个多状态多备份的例子：
     */

    class Originator1 {
        private String state1 = "";
        private String state2 = "";
        private String state3 = "";

        public String getState1() {
            return state1;
        }

        public void setState1(String state1) {
            this.state1 = state1;
        }

        public String getState2() {
            return state2;
        }

        public void setState2(String state2) {
            this.state2 = state2;
        }

        public String getState3() {
            return state3;
        }

        public void setState3(String state3) {
            this.state3 = state3;
        }

        public Memento1 createMemento() {
            return new Memento1(Memorandum.backupProp(this));
        }

        public void restoreMemento(Memento1 memento) {
            Memorandum.restoreProp(this, memento.getStateMap());
        }

        @Override
        public String toString() {
            return "state1=" + state1 + "state2=" + state2 + "state3=" + state3;
        }
    }

    class Memento1 {
        private Map<String, Object> stateMap;

        public Memento1(Map<String, Object> map) {
            this.stateMap = map;
        }

        public Map<String, Object> getStateMap() {
            return stateMap;
        }

        public void setStateMap(Map<String, Object> stateMap) {
            this.stateMap = stateMap;
        }
    }

    public static Map<String, Object> backupProp(Object bean) {
        Map<String, Object> result = new HashMap<>(16);
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(bean.getClass());
            PropertyDescriptor[] descriptors = beanInfo.getPropertyDescriptors();
            for (PropertyDescriptor des : descriptors) {
                String fieldName = des.getName();
                Method getter = des.getReadMethod();
                Object fieldValue = getter.invoke(bean, new Object[]{});
                if (!fieldName.equalsIgnoreCase("class")) {
                    result.put(fieldName, fieldValue);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void restoreProp(Object bean, Map<String, Object> propMap) {
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(bean.getClass());
            PropertyDescriptor[] descriptors = beanInfo.getPropertyDescriptors();
            for (PropertyDescriptor des : descriptors) {
                String fieldName = des.getName();
                if (propMap.containsKey(fieldName)) {
                    Method setter = des.getWriteMethod();
                    setter.invoke(bean, new Object[]{propMap.get(fieldName)});
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    class Caretaker1 {
        private Map<String, Memento1> memMap = new HashMap<>();

        public Memento1 getMemento(String index) {
            return memMap.get(index);
        }

        public void setMemento(String index, Memento1 memento) {
            this.memMap.put(index, memento);
        }
    }

    public void example1() {
        Originator1 ori = new Originator1();
        Caretaker1 caretaker = new Caretaker1();
        ori.setState1("中国");
        ori.setState2("强盛");
        ori.setState3("繁荣");
        System.out.println("===初始化状态===\n" + ori);

        caretaker.setMemento("001", ori.createMemento());
        ori.setState1("软件");
        ori.setState2("架构");
        ori.setState3("优秀");
        System.out.println("===修改后状态===\n" + ori);

        ori.restoreMemento(caretaker.getMemento("001"));
        System.out.println("===恢复后状态===\n" + ori);
    }

}
