package prototype;

import java.util.ArrayList;

/**
 * 原型模式
 *
 * @Author Sun
 * @date 2019-03-05
 */
public class Prototype {

    /**
     * 定义：通过复制现有的对象实例来创建新的对象实例。
     *
     * 实现：
     *      1. 实现Cloneable接口：Cloneable接口的作用是在运行时通知虚拟机可以安全地在实现了此接口的类上使用clone方法。在java虚拟机中，
     *         只有实现了这个接口的类才可以被拷贝，否则在运行时会抛出CloneNotSupportedException异常。
     *      2. 重写Object类中的clone方法：Java中，所有类的父类都是Object类，Object类中有一个clone方法，作用是返回对象的一个拷贝，但是
     *         其作用域protected类型的，一般的类无法调用，因此，原型类需要将clone方法的作用域修改为public类型。
     *
     * 优点：
     *      1. 使用原型模型创建一个对象比直接new一个对象更有效率，因为它直接操作内存中的二进制流，特别是复制大对象时，性能的差别非常明显。
     *      2. 隐藏了制造新实例的复杂性，使得创建对象就像我们在编辑文档时的复制粘贴一样简单。
     *
     * 缺点：
     *      1. 由于使用原型模式复制对象时不会调用类的构造方法，所以原型模式无法和单例模式组合使用，因为原型类需要将clone方法的作用域修
     *         改为public类型，那么单例模式的条件就无法满足了。
     *      2. 使用原型模式时不能有final对象。
     *      3. Object类的clone方法只会拷贝对象中的基本数据类型，对于数组，引用对象等只能另行拷贝。这里涉及到深拷贝和浅拷贝的概念。
     *
     * 适用场景：
     *      1. 复制对象的结构和数据。
     *      2. 希望对目标对象的修改不影响既有的原型对象。
     *      3. 创建一个对象的成本比较大。
     *
     * 深拷贝与浅拷贝：
     *      浅拷贝：将一个对象复制后，基本数据类型的变量都会重新创建，而引用类型，指向的还是原对象所指向的（这样不安全）。
     *      深拷贝：将一个对象复制后，不论是基本数据类型还有引用类型，都是重新创建的。
     */
    public static void main(String[] args) {
        // 创建原型模式对象
        Prototype prototype = new Prototype();
        System.out.println("--------------------- 我是分割线 ------------------------");
        // 例1：浅拷贝输出结果
        prototype.example();
        System.out.println("--------------------- 我是分割线 ------------------------");
        // 例2：深拷贝输出结果
        prototype.example1();
    }

    /**
     * 例如，对于拿邮件发邀请函，邮件类大部分内容都是一样的：邀请原由、相邀地点，相聚时间等等，但对于被邀请者的名称和发送的邮件地址是不同的。
     */

    /**
     * 浅拷贝实现
     */
    public class Mail implements Cloneable {
        private String receiver;
        private String subject;
        private String content;
        private String tail;

        public Mail(EventTemplate et) {
            this.tail = et.geteventContent();
            this.subject = et.geteventSubject();
        }

        @Override
        public Mail clone() {
            Mail mail = null;
            try {
                mail = (Mail) super.clone();
            } catch (CloneNotSupportedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return mail;
        }

        public void setReceiver(String receiver) {
            this.receiver = receiver;
        }


        public void setContent(String content) {
            this.content = content;
        }

        public String getTail() {
            return tail;
        }

        @Override
        public String toString() {
            return "{" + this.content + ", " +
                    "content: " + this.content + ", " +
                    "receiver:" + this.receiver + "}";
        }

    }

    public class EventTemplate {
        private String eventContent;
        private String eventSubject;

        public EventTemplate(String eventContent1, String eventSubject1) {
            eventContent = eventContent1;
            eventSubject = eventSubject1;
        }

        public String geteventContent() {
            return eventContent;
        }

        public String geteventSubject() {
            return eventSubject;
        }
    }

    /**
     * 测试方法：
     */
    public void example() {
        int i = 0;
        int MAX_COUNT = 10;
        EventTemplate et = new EventTemplate("邀请函（不变）", "婚嫁生日啥的....（不变部分）");
        Mail mail = new Mail(et);
        while (i < MAX_COUNT) {
            Mail cloneMail = mail.clone();
            cloneMail.setContent("XXX先生（女士）（变化部分）" + mail.getTail());
            cloneMail.setReceiver("每个人的邮箱地址...com（变化部分）");
            System.out.println(cloneMail.toString());
            i++;
        }

    }

    /**
     * 深拷贝实现
     * 继续上面的例子，增加了一个ArrayList属性。
     */
    public class Mail1 implements Cloneable {
        private String receiver;
        private String subject;
        private String content;
        private String tail;
        private ArrayList<String> ars;

        public Mail1(EventTemplate1 et) {
            this.tail = et.geteventContent();
            this.subject = et.geteventSubject();
            this.ars = et.getArs();
        }

        @Override
        public Mail1 clone() {
            Mail1 mail = null;
            try {
                mail = (Mail1) super.clone();
            } catch (CloneNotSupportedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return mail;
        }

        public void setReceiver(String receiver) {
            this.receiver = receiver;
        }


        public void setContent(String content) {
            this.content = content;
        }

        public String getTail() {
            return tail;
        }

        @Override
        public String toString() {
            return "{" + this.content + ", " +
                    "content: " + this.content + ", " +
                    "receiver:" + this.receiver + "}";
        }

    }

    public class EventTemplate1 {
        private String eventContent;
        private String eventSubject;
        private ArrayList<String> ars;

        public EventTemplate1(String eventContent1, String eventSubject1, ArrayList<String> ars1) {
            eventContent = eventContent1;
            eventSubject = eventSubject1;
            ars = ars1;
        }

        public String geteventContent() {
            return eventContent;
        }

        public String geteventSubject() {
            return eventSubject;
        }

        public ArrayList<String> getArs() {
            return ars;
        }
    }

    /**
     * 测试方法1：
     */
    public void example1() {
        int i = 0;
        int MAX_COUNT = 10;
        EventTemplate1 et = new EventTemplate1("邀请函（不变）", "婚嫁生日啥的....（不变部分）", new ArrayList<>());
        Mail1 mail = new Mail1(et);
        while (i < MAX_COUNT) {
            /* 此时，单mail = (Mail) super.clone();无法将ars指向的地址区域改变，必须另行拷贝： */
            Mail1 cloneMail = mail.clone();
            cloneMail.ars = (ArrayList<String>) mail.ars.clone();
            cloneMail.setContent("XXX先生（女士）（变化部分）" + mail.getTail());
            cloneMail.setReceiver("每个人的邮箱地址...com（变化部分）");
            System.out.println(cloneMail.toString());
            i++;
        }

    }

}