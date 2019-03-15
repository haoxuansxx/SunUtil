package lombok;

import javax.validation.constraints.NotNull;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * Lombok是一个通过注解以达到减少代码的Java库,如通过注解的方式减少get,set方法,构造方法等。
 *
 * @Author Sun
 * @date 2019-03-11
 */
public class LombokApi {

    /**
     * 输出主入口
     */
    public static void main(String... args) {
        // @Data 注解
        Data();
        // @Getter @Setter 注解
        GetterSetter();
        // NonNull 注解
        NonNull();
        // Synchronized 注解
        Synchronized();
        // ToString 注解
        ToString();
        // Cleanup 注解
        Cleanup();
        // EqualsAndHashCode 注解
        EqualsAndHashCode();
        // SneakyThrows 注解
        SneakyThrows();
        // NoArgsConstructor 无参构造器
        NoArgsConstructor();
        // RequiredArgsConstructor 部分参数构造器
        RequiredArgsConstructor();
        // AllArgsConstructor 全参构造器
        AllArgsConstructor();
    }

    /**
     * 安装
     */
    public void initMathod() {
        // 方法1：通过向lib文件夹中添加lombok.jar包即可。

        // 方法2：如果使用的是Maven,则可在项目的pom.xml文件中添加以下依赖。
        /*
        <dependency>
            <groupId > org.projectlombok </groupId >
            <artifactId > lombok </artifactId >
            <version > 1.16 .12 </version >
          </dependency >
        */

        // idea加入Lombok插件：  File -> settings -> Plugins -> Browse repositories -> Lombok
    }

    /**
     * 该注解相当于同时加上@Setter@Getter,@ToString,@EqualsAndHashCode，作用于类
     */
    public static void Data() {
        @Data
        class Person {
            private String name;
        }
        System.out.println("------------------------------------ 输出类注解Data情况 ------------------------------------");
        Arrays.stream(Person.class.getMethods()).forEach(System.out::println);
    }

    /**
     * 作用于属性上,自动生成当前属性的get,set方法。
     * 作用于类上，生成所有属性的get，set方法。
     */
    public static void GetterSetter() {
        @Getter
        @Setter
        class Person {
            private String name;
        }
        System.out.println("------------------------------------ 输出类注解Getter和Setter情况 ------------------------------------");
        Arrays.stream(Person.class.getMethods()).forEach(System.out::println);
        class Person1 {
            @Getter
            @Setter
            private String name;
        }
        System.out.println("------------------------------------ 输出属性注解Getter和Setter情况 ------------------------------------");
        Arrays.stream(Person1.class.getMethods()).forEach(System.out::println);
    }

    /**
     * 该注解快速判断是否为空,如果为空,则抛出java.lang.NullPointerException
     */
    public static void NonNull() {
        class Person {
            @NotNull
            private String name;
        }
        System.out.println("------------------------------------ 输出属性注解NotNull情况 ------------------------------------");
        Arrays.stream(Person.class.getMethods()).forEach(System.out::println);
    }

    /**
     * 该注解自动添加到同步机制,有趣的是,生成的代码并不是直接锁方法,而是锁代码块，作用范围是方法上。
     */
    public static void Synchronized() {
        class Person {
            DateFormat format = new SimpleDateFormat("MM-dd-YYYY");

            @Synchronized
            public String synchronizedFormat(Date date) {
                return format.format(date);
            }
        }
        System.out.println("------------------------------------ 输出属性注解Synchronized情况 ------------------------------------");
        System.out.println("输出属性注解Synchronized情况：" + new Person().synchronizedFormat(new Date()));
        // 上面源代码等价于下面
        class Person1 {
            private final java.lang.Object $lock = new java.lang.Object[0];
            DateFormat format = new SimpleDateFormat("MM-dd-YYYY");

            @Synchronized
            public String synchronizedFormat(Date date) {
                synchronized ($lock) {
                    return format.format(date);
                }
            }
        }
        System.out.println("------------------------------------ 输出属性注解Synchronized等价源代码情况 ------------------------------------");
        System.out.println("输出属性注解Synchronized等价源代码情况：" + new Person1().synchronizedFormat(new Date()));
    }

    /**
     * 该方法大家应该非常熟悉，但需要注意的是:@ToString有多个属性可以进一步设置:
     * 1. callSuper 是否输出父类的toString方法,默认为false
     * 2. includeFieldNames 是否包含字段名称,默认为true
     * 3. exclude 排除生成tostring的字段
     */
    public static void ToString() {
        @ToString(callSuper = true, exclude = {"name"})
        class Person {
            private String name;
            private String sex;
        }
        System.out.println("------------------------------------ 输出属性注解NotNull情况 ------------------------------------");
        System.out.println("输出属性注解NotNull情况：" + new Person().toString());

        // 上面源代码等价于下面
        class Person1 {
            private String name;
            private String sex;

            @Override
            public String toString() {
                return "Person{" + "address='" + name + '\'' + '}';
            }
        }
        System.out.println("------------------------------------ 输出属性注解NotNull等价源代码情况 ------------------------------------");
        System.out.println("输出属性注解NotNull等价源代码情况：" + new Person1().toString());

    }

    /**
     * 该注释可用于确保已分配的资源被释放,如IO的连接关闭。
     */
    public static void Cleanup() {
        class Person {
            String testCleanUp() {
                try {
                    @Cleanup ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    baos.write(new byte[]{'Y', 'e', 's'});
                    return baos.toString();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }
        }
        System.out.println("------------------------------------ 输出属性注解Cleanup情况 ------------------------------------");
        System.out.println("输出属性注解Cleanup情况：" + new Person().testCleanUp());

        class Person1 {
            String testCleanUp() {
                try {
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    try {
                        baos.write(new byte[]{'Y', 'e', 's'});
                        return baos.toString();
                    } finally {
                        baos.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }
        }
        System.out.println("------------------------------------ 输出属性注解Cleanup等价源代码情况 ------------------------------------");
        System.out.println("输出属性注解Cleanup等价源代码情况：" + new Person1().testCleanUp());
    }

    /**
     * 默认情况下，会使用所有非静态（non-static）和非瞬态（non-transient）属性来生成equals和hasCode，也能通过exclude注解来排除一些属性。
     */
    public static void EqualsAndHashCode() {
        @EqualsAndHashCode(exclude = {"id", "name"})
        class Person {
            private String id;
            private String name;
        }
    }

    /**
     * SneakyThrows 省略需自己写抛出异常
     */
    @SneakyThrows(Exception.class)
    public static void SneakyThrows() {
        class Person {
            @NotNull
            private String name;
        }
    }

    /**
     * 无参构造器
     */
    public static void NoArgsConstructor() {
        @NoArgsConstructor
        class Person {
            private String name;
        }
    }

    /**
     * 部分参数构造器
     */
    public static void RequiredArgsConstructor() {
        // @RequiredArgsConstructor(staticName = "of")
        class Person {
            private String name;
        }
    }

    /**
     * 全参构造器
     */
    public static void AllArgsConstructor() {
        @AllArgsConstructor(access = AccessLevel.PROTECTED)
        class Person {
            private String name;
        }
    }

}
