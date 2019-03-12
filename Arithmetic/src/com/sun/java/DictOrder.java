import java.util.Arrays;

/**
 * 寻找最近的换位数----字典序算法
 *
 * @Author Sun
 * @date 2019-03-05
 */
public class DictOrder {

    /**
     * @param args
     */
    public static void main(String[] args) {
        int[] numbers = {1, 2, 3, 4, 5};
        for (int i = 0; i < 1; i++) {
            numbers = findNearestNumber(numbers);
            outputNumbers(numbers);
        }
    }

    /**
     * 主流程，返回最近一个大于自身的相同数字组成的整数
     *
     * @param numbers
     * @return
     */
    public static int[] findNearestNumber(int[] numbers) {
        //拷贝入参，避免直接修改入参
        int[] numbersCopy = Arrays.copyOf(numbers, numbers.length);
        //1. 从后向前查看逆序区域，找到逆序区域的前一位，也就是数字置换的边界
        int index = findTransferPoint(numbersCopy);
        //如果数字置换边界是0，说明整个数组已经逆序，无法得到更大的相同数字组成的整数，返回自身
        if (index == 0) {
            return null;
        }
        //2. 把逆序区域的前一位和逆序区域中刚刚大于它的数字交换位置
        exchangeHead(numbersCopy, index);
        //3. 把原来的逆序区域转为顺序
        reverse(numbersCopy, index);
        return numbersCopy;
    }

    /**
     * 从后向前查看逆序区域，找到逆序区域的前一位，也就是数字置换的边界
     *
     * @param numbers 需要排序的数字集合
     * @return 返回数字置换边界下标
     */
    private static int findTransferPoint(int[] numbers) {

        for (int i = numbers.length - 1; i > 0; i--) {
            if (numbers[i] > numbers[i - 1]) {
                return i;
            }
        }
        return 0;
    }

    /**
     * 把逆序区域的前一位和逆序区域中刚刚大于它的数字交换位置
     *
     * @param numbers 需要排序的数字集合
     * @param index   数字置换边界下标
     * @return 返回交换后的数字
     */
    private static int[] exchangeHead(int[] numbers, int index) {
        int head = numbers[index - 1];
        for (int i = numbers.length - 1; i > 0; i--) {
            if (head < numbers[i]) {
                numbers[index - 1] = numbers[i];
                numbers[i] = head;
                break;
            }
        }
        return numbers;
    }

    /**
     * 把原来的逆序区域转为顺序
     *
     * @param num   需要排序的数字集合
     * @param index 数字置换边界下标
     * @return 返回转换后的数字集合
     */
    private static int[] reverse(int[] num, int index) {
        for (int i = index, j = num.length - 1; i < j; i++, j--) {
            int temp = num[i];
            num[i] = num[j];
            num[j] = temp;
        }
        return num;
    }

    /**
     * 输出数组
     *
     * @param numbers 转换后的数组
     */
    private static void outputNumbers(int[] numbers) {
        for (int i : numbers) {
            System.out.print(i);
        }
        System.out.println();
    }

}

