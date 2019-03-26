
/**
 * Non-decreasing Array        -- 非递减数列
 *
 * @Author Sun
 * @date 2019-03-25
 */
public class NonDecreasingArray {

    /**
     * Given an array with n integers, your task is to check if it could become non-decreasing by modifying at most 1 element.
     * We define an array is non-decreasing if array[i] <= array[i + 1] holds for every i (1 <= i < n).
     *
     * Example 1:
     *      Input: [4,2,3]
     *      Output: True
     *      Explanation: You could modify the first 4 to 1 to get a non-decreasing array.
     *
     * Example 2:
     *      Input: [4,2,1]
     *      Output: False
     *      Explanation: You can't get a non-decreasing array by modify at most one element.
     *
     * Note: The n belongs to [1, 10,000].
     */
    public static void main(String... args) {
        int[] nums = {2, 6, 14, 22, 35, 45, 67, 74, 88};

        System.out.println(NonDecreasingArray.checkPossibility(nums));
    }

    public static boolean checkPossibility(int[] nums) {
        // 数组长度
        int size = nums.length;
        // 前一位数字
        int num0 = 0;
        // 当前数字
        int num = 0;
        // 后一位数字
        int num1 = 0;
        // 是否以修改一次
        boolean bool = false;

        // 如果size小于等于1则直接返回
        if (size <= 1) {
            return true;
        }

        for (int i = 0; i < (size - 1); i++) {
            num = nums[i];
            num1 = nums[i + 1];
            // 如果当前数字比后面的数字大则需要对数字进行调整
            if (num > num1) {
                if (!bool) {
                    bool = true;
                    if (i == 0) {
                        continue;
                    } else if (i != 0 && num1 >= nums[i - 1]) {
                        // 调整当前数字是否成立
                        continue;
                    } else if (i < (size - 2) && num <= nums[i + 2]) {
                        // 调整后续数字是否成立
                        continue;
                    } else if (i == (size - 2)) {
                        continue;
                    } else {
                        return false;
                    }

                } else {
                    return false;
                }
            }


        }

        return true;

    }
}
