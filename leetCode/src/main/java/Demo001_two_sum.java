import java.util.HashMap;
import java.util.Map;

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
public class Demo001_two_sum {

    public static void main(String[] args) {
        int[] result = towSum2(new int[]{2, 4, 8, 34}, 6);
        for (int t : result) {
            System.out.println(t);
        }
    }

    /**
     * solution one
     *  time complexity O(n^2)
     *  space complexity O(n)
     */

    public static int[] towSum(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] == target - nums[j]) {
                    return new int[]{nums[i], nums[j]};
                }
            }
        }
        throw new IllegalArgumentException("no two sum solution");
    }

    /**
     * solution two
     */
    public static int[] towSum2(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                return new int[]{target - nums[i], nums[i]};
            }
            map.put(nums[i], i);
        }
        throw new IllegalArgumentException("no tow sum solution");
    }
}
