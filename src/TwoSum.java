import java.util.HashMap;
import java.util.Map;

/**
 * 1. 两数之和
 * @author 刘淳
 */
public class TwoSum {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer,Integer> hashmap=new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            // 判断表中是否存在与该数相加等于需要的和的键
            if (hashmap.containsKey(target-nums[i])){
                //存在，将该数的下标与该键的值存入
                return new int[]{i,hashmap.get(target-nums[i])};
            }
            //不存在，将该数和该数下标存入表中
            hashmap.put(nums[i],i );
        }
        return new int[0];
    }
}
