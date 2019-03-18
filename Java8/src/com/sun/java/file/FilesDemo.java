package file;

/**
 * Java 8 Files文件类新添加方法示例
 *
 * @Author Sun
 * @date 2019-03-18
 */
public class FilesDemo {

    /**
     * Files.lines：     -- 通过该方法你可以以延迟方式读取文件的内容，并将其作为一个流。
     * Files.list：      -- 生成由指定目录中所有条目构成的Stream<Path>。这个列表不是递归包含的。由于流是延迟消费的，处理包含内容非常庞大的目录时，
     *                     这个方法非常有用。
     * Files.walk：      -- 和Files.list有些类似，它也生成包含给定目录中所有条目的Stream<Path>。不过这个列表是递归的，你可以设定递归的深度。
     *                      注意，该遍历是依照深度优先进行的。
     * Files.find：      -- 通过递归地遍历一个目录找到符合条件的条目，并生成一个Stream<Path>对象。
     */
    public static void main(String... args){
        System.out.println("Java 8 Number数字基础类型新添加方法示例！");
    }

}
