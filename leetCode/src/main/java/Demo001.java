/**
 *
 * 1. Two Sum
 *  Given an array of integers, return indices of two numbers such that they add up to specific target.
 *  You may assume that each input would have exactly one solution, and you may not use the same element twice.
 *
 *  Example:
 *      Give nums = [2, 7, 11, 15], target = 9,
 *      Because nums[0] + nums[1] = 2 + 7 = 9,
 *      return [0, 1]
 *
 * Created by upnoob on 2017/12/20.
 */
public class Demo001 {

    public static void main(String[] args) {
        int[] result = towSum(new int[]{2, 4, 8, 34}, 23);
        for (int t : result) {
            System.out.println(t);
        }
    }

    /**
     * 解决方案1
     */
    public static int[] towSum(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (target == nums[i] + nums[j]) {
                    return new int[]{nums[i], nums[j]};
                }
            }
        }
        throw new IllegalArgumentException("now solution");
    }

}
